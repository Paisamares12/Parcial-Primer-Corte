package udistrital.avanzada.parcial.modelo.excepciones;

/**
 * Excepción lanzada cuando ocurre un problema de conexión
 * con la base de datos o con el archivo de persistencia.
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 4.0
 * @since 2025-10-13
 */
public class ConexionException extends Exception {

    public ConexionException(String mensaje) {
        super(mensaje);
    }

    public ConexionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
