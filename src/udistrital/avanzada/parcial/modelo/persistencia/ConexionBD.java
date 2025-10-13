package udistrital.avanzada.parcial.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase ConexionBD
 * 
 * Implementa un Singleton que maneja la conexión con la base de datos.
 * 
 * El diseño permite adaptarse fácilmente a distintos motores (MySQL, SQLite, etc.)
 * cambiando solo los valores de URL, usuario y contraseña.
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    // Ajusta estos valores según tu BD real
    private static final String URL = "jdbc:sqlite:mascotas.db";
    private static final String USUARIO = "";   // no aplica para SQLite
    private static final String CLAVE = "";     // no aplica para SQLite

    /**
     * Constructor privado (patrón Singleton)
     */
    private ConexionBD() throws ConexionException {
        try {
            // Cargar el driver (SQLite es liviano, pero puedes cambiar a MySQL)
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (Exception e) {
            throw new ConexionException("Error al conectar con la base de datos.", e);
        }
    }

    /**
     * Obtiene la única instancia de la clase.
     */
    public static synchronized ConexionBD getInstance() {
        if (instancia == null) {
            try {
                instancia = new ConexionBD();
            } catch (ConexionException e) {
                System.err.println("No se pudo establecer conexión: " + e.getMessage());
            }
        }
        return instancia;
    }

    /**
     * Retorna la conexión actual (crea una nueva si está cerrada).
     */
    public Connection obtenerConexion() throws ConexionException {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            }
            return conexion;
        } catch (SQLException e) {
            throw new ConexionException("No se pudo obtener la conexión activa.", e);
        }
    }

    /**
     * Cierra la conexión si está abierta.
     */
    public void cerrarConexion() throws ConexionException {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new ConexionException("Error al cerrar la conexión.", e);
        }
    }
}
