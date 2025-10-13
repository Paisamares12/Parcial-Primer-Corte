package udistrital.avanzada.parcial.control;

import java.sql.Connection;
import udistrital.avanzada.parcial.modelo.persistencia.ConexionBD;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase ControlConexion
 * 
 * Gestiona la conexión general con la base de datos,
 * validando el estado de la conexión y encapsulando 
 * las operaciones básicas.
 * 
 * <p>
 * Originalmente creada por Paula Martínez.<br>
 * Modificada y documentada por Juan Sebastián Bravo Rojas.
 * </p>
 * 
 * @author Paula Martinez
 * @version 2.0
 * @since 2025-10-13
 */
public class ControlConexion {

    private ConexionBD conexion;

    public ControlConexion() {
        conexion = ConexionBD.getInstance();
    }

    /**
     * Verifica si la conexión con la base de datos está activa.
     * 
     * @return true si hay conexión, false en caso contrario.
     */
    public boolean verificarConexion() {
        try (Connection conn = conexion.obtenerConexion()) {
            return (conn != null && !conn.isClosed());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Cierra la conexión principal si está abierta.
     * 
     * @throws ConexionException si ocurre un error al cerrar
     */
    public void cerrarConexion() throws ConexionException {
        try {
            conexion.cerrarConexion();
        } catch (Exception e) {
            throw new ConexionException("Error al cerrar la conexión.", e);
        }
    }
}
