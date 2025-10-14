package udistrital.avanzada.parcial.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase ConexionBD
 * 
 * Implementa un Singleton que maneja la conexión con la base de datos MySQL.
 * Configurada para trabajar con XAMPP.
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    private static final String URL = "jdbc:mysql://localhost:3306/mascotas_exoticas";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    /**
     * Constructor privado (patrón Singleton)
     */
    private ConexionBD() throws ConexionException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (ClassNotFoundException e) {
            throw new ConexionException("Driver MySQL no encontrado.", e);
        } catch (SQLException e) {
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