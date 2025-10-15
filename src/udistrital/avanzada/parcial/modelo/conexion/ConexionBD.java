package udistrital.avanzada.parcial.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase ConexionBD que implementa el patrón Singleton para manejar
 * la conexión con la base de datos MySQL.
 * 
 * <p>
 * Esta clase garantiza que solo exista una instancia de conexión
 * durante toda la ejecución de la aplicación. Está configurada para
 * trabajar con MySQL a través de XAMPP en el puerto 3306.
 * </p>
 * 
 * <p>
 * La conexión utiliza el driver mysql-connector-java-5.1.46 y se
 * conecta a la base de datos "mascotas_exoticas" con el usuario root
 * sin contraseña (configuración por defecto de XAMPP).
 * </p>
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class ConexionBD {

    /** Instancia única de la clase (patrón Singleton) */
    private static ConexionBD instancia;
    
    /** Objeto Connection que mantiene la conexión activa */
    private Connection conexion;
    
    /** Indica si la conexión está disponible y funcionando */
    private boolean conexionDisponible;

    /** URL de conexión a la base de datos MySQL */
    private static final String URL = "jdbc:mysql://localhost:3306/mascotas_exoticas";
    
    /** Usuario de la base de datos (por defecto root en XAMPP) */
    private static final String USUARIO = "root";
    
    /** Contraseña del usuario (vacía por defecto en XAMPP) */
    private static final String CLAVE = "";

    /**
     * Constructor privado que establece la conexión inicial.
     * 
     * <p>
     * Carga el driver MySQL y establece la conexión con la base de datos.
     * Si ocurre algún error, marca la conexión como no disponible pero
     * no detiene la ejecución para permitir que la aplicación maneje
     * el error apropiadamente.
     * </p>
     */
    private ConexionBD() {
        conexionDisponible = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            conexionDisponible = true;
        } catch (ClassNotFoundException e) {
            conexion = null;
        } catch (SQLException e) {
            conexion = null;
        }
    }

    /**
     * Obtiene la única instancia de la clase ConexionBD.
     * 
     * <p>
     * Este método implementa el patrón Singleton de forma thread-safe,
     * garantizando que solo exista una instancia de conexión en toda
     * la aplicación.
     * </p>
     * 
     * @return la instancia única de ConexionBD
     */
    public static synchronized ConexionBD getInstance() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    /**
     * Retorna la conexión actual con la base de datos.
     * 
     * <p>
     * Si la conexión está cerrada, intenta crear una nueva.
     * Si no hay conexión disponible, lanza una excepción.
     * </p>
     * 
     * @return objeto Connection activo con la base de datos
     * @throws ConexionException si no se puede obtener la conexión
     */
    public Connection obtenerConexion() throws ConexionException {
        if (!conexionDisponible) {
            throw new ConexionException(
                "La conexión a la base de datos no está disponible. " +
                "Verifique que XAMPP esté corriendo y que la base de datos " +
                "'mascotas_exoticas' exista."
            );
        }
        
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            }
            return conexion;
        } catch (SQLException e) {
            throw new ConexionException(
                "No se pudo obtener una conexión activa con la base de datos.", e
            );
        }
    }

    /**
     * Cierra la conexión con la base de datos si está abierta.
     * 
     * <p>
     * Este método debe ser llamado al finalizar la aplicación para
     * liberar los recursos de conexión correctamente.
     * </p>
     * 
     * @throws ConexionException si ocurre un error al cerrar la conexión
     */
    public void cerrarConexion() throws ConexionException {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                conexionDisponible = false;
            }
        } catch (SQLException e) {
            throw new ConexionException("Error al cerrar la conexión.", e);
        }
    }
    
    /**
     * Verifica si la conexión está disponible y activa.
     * 
     * @return true si la conexión está disponible, false en caso contrario
     */
    public boolean isConexionDisponible() {
        return conexionDisponible && conexion != null;
    }
}