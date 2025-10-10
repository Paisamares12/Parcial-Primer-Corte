package udistrital.avanzada.parcial.modelo.conexion;
//Libreria que conecta el programa Java con una base de datos

import java.sql.Connection;
//Gestiona los drivers JDBC que usa Java para conectarse a diferentes bases de datos
import java.sql.DriverManager;
/**
 * Una excepcion para java.sql, sirve para lanzar excepciones al encontrar
 * Errores al conectar, error de sintaxis, leer datos inexistentes y/o cerrar
 * una conexión ya cerrada. Todas las operaciones JDBC (getConnection,
 * executeQuery,close) pueden lanzar una SQLException al usar cualquiera de
 * estos netbeans de una vez manda a usar un catch para atrapar la SQLException
 */
import java.sql.SQLException;

/**
 * Clase {@code Conexion} que gestiona la conexión entre la aplicación y la base de datos MySQL.
 * <p>
 * Esta clase se encarga de establecer y cerrar la conexión utilizando el 
 * {@link java.sql.DriverManager}, permitiendo que las demás clases del modelo 
 * accedan a la base de datos sin preocuparse por los detalles técnicos de la conexión.
 * Manteniendo así el modelo MVC.
 * </p>
 *
 * <p>
 * Además, mantiene un mensaje informativo sobre el estado de la conexión 
 * para mostrar las excepciones cuando haya algun fallo
 * </p>
 *
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-10
 */
public class Conexion {

    //Se inicia la variable static cn nula para asi hacer uso del singleton
    private static Connection cn = null;
    //La url a la base de datos
    private static String URLBD = "jdcv:mysql://localhost/";
    //Usuarios y contraseña para conectar facilmente a la base de datos
    private static String usuario = "root";
    private static String contrasena = "";
    //String para mandar mensajes cuando ocurren excepciones
    private static String mensaje;

    /**
     * Establece la conexión con la base de datos.
     * <p>
     * Este método utiliza el {@link DriverManager} para buscar un driver JDBC
     * compatible con la URL configurada (en este caso, uno de MySQL) e intenta
     * establecer la conexión con las credenciales proporcionadas.
     * </p>
     *
     * <p>
     * Si ocurre algún error durante el proceso, se captura una excepción
     * {@link SQLException} y se almacena un mensaje descriptivo en la variable
     * {@code mensaje}.
     * </p>
     *
     * @return el objeto {@link Connection} si la conexión fue exitosa;
     * {@code null} si ocurrió un error al conectar.
     */
    public static Connection getConexion() {
        try {
            /**
             * Aquí cn conecta la aplicación con la base de datos SQL y el
             * DriverManager busca un driver JDCB compatible con la URL en este
             * caso uno de mysql
             */
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            //SQLException captura si la conexión falla
            mensaje = "Error al conextar: " + ex.getMessage();
        }
        //Si la conexion se establece correctamente, se devuelve cn
        return cn;
    }

    /**
     * Cierra la conexión actual con la base de datos.
     * <p>
     * Este método verifica si la conexión ({@code cn}) existe y está abierta.
     * Si está activa, intenta cerrarla mediante {@link Connection#close()}.
     * </p>
     *
     * <p>
     * En caso de que ocurra una excepción al intentar cerrarla, se captura la
     * {@link SQLException} y se guarda un mensaje descriptivo en la variable
     * {@code mensaje}. Finalmente, el bloque {@code finally} asegura que la
     * referencia a la conexión se libere (se asigne a {@code null}) para evitar
     * fugas de recursos.
     * </p>
     */
    public static void desconectar() {
        //Verifica si la conexión es nula 
        if (cn != null) {
            //Si la conexión no es nula inicia un bloque try
            try {
                if (!cn.isClosed()) {
                    //Si la conexión no está cerrada, la cierra
                    cn.close();
                }
            } catch (SQLException ex) {
                //Captura por si ocurre alguna excepcion al intentar cerrar la conexión
                mensaje = "Error al cerrar conexión: " + ex.getMessage();
            } finally {
                //Asegura que la conexión se cierre
                cn = null;
            }
        }
    }
    
    /**
     * Devuelve un mensaje cuando se captura alguna excepción
     * 
     * @return una cadena de texto para cuando ocurra alguna excepción 
     */
    public static String getMensaje(){
        return mensaje;
    }
    
}
