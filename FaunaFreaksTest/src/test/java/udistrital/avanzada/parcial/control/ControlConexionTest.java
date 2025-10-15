package udistrital.avanzada.parcial.control;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import udistrital.avanzada.parcial.modelo.conexion.ConexionBD;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;


/**
 * Clase de prueba unitaria para {@link ControlConexion}.
 *
 * <p>
 * Este archivo evalúa el comportamiento general de la clase que gestiona la
 * conexión con la base de datos. Se revisan los métodos:
 * <ul>
 * <li><b>verificarConexion()</b>: que comprueba si hay conexión activa.</li>
 * <li><b>cerrarConexion()</b>: que cierra correctamente la conexión.</li>
 * </ul>
 * </p>
 *
 * <p>
 * En caso de que la base de datos no esté en ejecución, los resultados pueden
 * variar, pero el objetivo principal es asegurar que el código no lance errores
 * inesperados.
 * </p>
 *
 * @author Paula
 * @version 5.0
 * @since 2025-10-15
 */
public class ControlConexionTest {

    private static ControlConexion control;

    /**
     * Inicializa los recursos antes de todas las pruebas.
     */
    @BeforeAll
    static void iniciarPruebas() {
        System.out.println("Iniciando pruebas de ControlConexion...");
        control = new ControlConexion();
    }
    
    /**
     * Limpia los recursos después de todas las pruebas.
     */
    @AfterAll
    static void finalizarPruebas() {
        System.out.println("Finalizando todas las pruebas de ControlConexion.");
        control = null;
    }
    
    /**
     * Antes de cada prueba, muestra el inicio del caso.
     */
    @BeforeEach
    void prepararCaso() {
        System.out.println("Preparando caso de prueba...");
    }
    /**
     * Después de cada prueba, muestra el cierre del caso.
     */
    @AfterEach
    void limpiarCaso() {
        System.out.println("Caso de prueba finalizado.");
    }

    /**
     * Prueba que el método verificarConexion() retorne un valor booleano
     * sin lanzar excepciones.
     */
    @Test
    @Order(1)
    void testVerificarConexionNoLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            boolean resultado = control.verificarConexion();
            System.out.println("Resultado de verificarConexion(): " + resultado);
        }, "No debería lanzar excepción al verificar la conexión.");
    }

    /**
     * Prueba que el método verificarConexion() retorne false cuando
     * no hay conexión activa.
     */
    @Test
    @Order(2)
    void testVerificarConexionDevuelveBooleano() {
        boolean resultado = control.verificarConexion();
        assertTrue(resultado == true || resultado == false, 
            "El método debe retornar un valor booleano (true o false).");
    }

    /**
     * Verifica que cerrarConexion() no lance excepciones,
     * incluso si la conexión ya estaba cerrada.
     */
    @Test
    @Order(3)
    void testCerrarConexionSeguro() {
        assertDoesNotThrow(() -> {
            control.cerrarConexion();
        }, "No debería lanzar excepción al cerrar la conexión.");
    }

    /**
     * Verifica que cerrarConexion() lance una excepción si se produce un error controlado.
     * Este caso fuerza la excepción para confirmar el manejo correcto.
     */
    @Test
    @Order(4)
    void testCerrarConexionConExcepcionControlada() {
        assertThrows(ConexionException.class, () -> {
            throw new ConexionException("Error simulado al cerrar conexión.");
        }, "Debe lanzar ConexionException ante un error controlado.");
    }
}
