package udistrital.avanzada.parcial.modelo.excepciones;

/**
 * Excepción para cuando faltan datos obligatorios
 * en el registro o modificación de una mascota.
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 4.0
 * @since 2025-10-13
 */
public class DatosIncompletosException extends Exception {

    public DatosIncompletosException(String mensaje) {
        super(mensaje);
    }
}
