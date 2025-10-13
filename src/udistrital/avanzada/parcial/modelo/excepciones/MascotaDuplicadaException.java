package udistrital.avanzada.parcial.modelo.excepciones;

/**
 * Excepción lanzada cuando se intenta registrar una mascota
 * con un apodo que ya existe en el sistema.
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class MascotaDuplicadaException extends Exception {

    public MascotaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
