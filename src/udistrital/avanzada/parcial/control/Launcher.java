package udistrital.avanzada.parcial.control;
/**
 * Clase Launcher que contiene el método principal para iniciar la aplicación.
 * 
 * <p>Esta clase es el punto de entrada del programa y se encarga de crear
 * una instancia de la clase ControlLogica, que maneja la lógica principal.</p>
 * 
 * <p>Ejemplo de ejecución:</p>
 * <pre>
 * java Launcher
 * </pre>
 * 
 * @author Paula Martinez
 * @version 1.0
 * @since 2025-10-09
 */
public class Launcher {

    /**
     * Método principal que se ejecuta al iniciar el programa.
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        new ControlLogica();
    }
}
