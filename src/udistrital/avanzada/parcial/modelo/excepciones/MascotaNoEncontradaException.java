package udistrital.avanzada.parcial.modelo.excepciones;

/**
 * Excepción lanzada cuando se intenta buscar o eliminar
 * una mascota que no existe en la base de datos.
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 4.0
 * @since 2025-10-13
 */
public class MascotaNoEncontradaException extends Exception {

    public MascotaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
