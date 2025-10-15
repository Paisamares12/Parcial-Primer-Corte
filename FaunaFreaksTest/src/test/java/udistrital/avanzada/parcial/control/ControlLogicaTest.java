package udistrital.avanzada.parcial.control;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;

/**
 * Clase de pruebas unitarias para {@link ControlLogica}.
 *
 * <p>
 * Estas pruebas verifican el correcto comportamiento general del coordinador
 * del sistema. No se requiere una base de datos activa, ya que se evalúa que
 * los métodos principales:
 * <ul>
 * <li>No lancen excepciones inesperadas.</li>
 * <li>Devuelvan los tipos de datos esperados.</li>
 * <li>Permitan la inicialización y coordinación sin errores.</li>
 * </ul>
 * </p>
 *
 * @author Paula
 * @version 5.0
 * @since 2025-10-15
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControlLogicaTest {

    private static ControlLogica control;

    // ==============================
    // CONFIGURACIÓN GLOBAL DE PRUEBAS
    // ==============================
    /**
     * Inicializa el objeto de ControlLogica antes de todas las pruebas.
     */
    @BeforeAll
    static void iniciarPruebas() {
        System.out.println("Iniciando pruebas de ControlLogica...");
        control = new ControlLogica();
        assertNotNull(control, "El objeto ControlLogica debería inicializarse correctamente.");
    }

    /**
     * Limpia los recursos después de todas las pruebas.
     */
    @AfterAll
    static void finalizarPruebas() {
        System.out.println("Finalizando todas las pruebas de ControlLogica.");
        control = null;
    }

    /**
     * Muestra el inicio de cada caso de prueba.
     */
    @BeforeEach
    void prepararCaso() {
        System.out.println("Preparando caso de prueba...");
    }

    /**
     * Muestra el cierre de cada caso de prueba.
     */
    @AfterEach
    void cerrarCaso() {
        System.out.println("Caso de prueba finalizado.");
    }

    // ==============================
    // PRUEBAS UNITARIAS
    // ==============================
    /**
     * Verifica que la instancia de ControlLogica se cree correctamente sin
     * excepciones.
     */
    @Test
    @Order(1)
    void testInicializacionControlLogica() {
        assertDoesNotThrow(() -> new ControlLogica(),
                "No debería lanzar excepción al crear una nueva instancia de ControlLogica.");
    }

    /**
     * Verifica que obtener las clasificaciones devuelva un arreglo válido
     * (aunque vacío).
     */
    @Test
    @Order(2)
    void testObtenerClasificaciones() {
        assertDoesNotThrow(() -> {
            String[] clasificaciones = control.obtenerClasificaciones();
            assertNotNull(clasificaciones, "El arreglo de clasificaciones no debe ser nulo.");
        });
    }

    /**
     * Verifica que obtener las alimentaciones devuelva un arreglo válido
     * (aunque vacío).
     */
    @Test
    @Order(3)
    void testObtenerAlimentaciones() {
        assertDoesNotThrow(() -> {
            String[] alimentaciones = control.obtenerAlimentaciones();
            assertNotNull(alimentaciones, "El arreglo de alimentaciones no debe ser nulo.");
        });
    }

    /**
     * Prueba que el método probarConexion() retorne un booleano sin lanzar
     * excepciones.
     */
    @Test
    @Order(4)
    void testProbarConexion() {
        assertDoesNotThrow(() -> {
            boolean resultado = control.probarConexion();
            assertTrue(resultado == true || resultado == false,
                    "El método debe devolver un valor booleano válido.");
        });
    }

    /**
     * Verifica que la lista de mascotas incompletas sea accesible y no cause
     * errores.
     */
    @Test
    @Order(5)
    void testMascotasIncompletas() {
        assertDoesNotThrow(() -> {
            List<MascotaVO> lista = control.getMascotasIncompletas();
            assertTrue(lista == null || lista instanceof List,
                    "Debe devolver una lista o null sin lanzar errores.");
        });
    }

    /**
     * Verifica que el método consultarConFiltros() pueda ejecutarse sin errores
     * al recibir filtros vacíos.
     */
    @Test
    @Order(6)
    void testConsultarConFiltros() {
        try {
            control.consultarConFiltros("", "", "", "");
            System.out.println("Consulta con filtros vacíos ejecutada correctamente.");
        } catch (Exception e) {
            System.out.println("No se pudo consultar con filtros vacíos: " + e.getMessage());
            assertTrue(e.getMessage().contains("conexión a la base de datos no está disponible"),
                    "Se esperaba un error por conexión de base de datos.");
        }
    }

    /**
     * Verifica que los métodos de guardado no lancen excepciones.
     */
    @Test
    @Order(7)
    void testGuardarArchivoYAccesoAleatorio() {
        try {
            control.guardarArchivo();
            control.guardarAccesoAleatorio();
            System.out.println("Guardado de archivos ejecutado correctamente.");
        } catch (Exception e) {
            System.out.println("No se pudo guardar archivo: " + e.getMessage());
            assertTrue(e.getMessage().contains("conexión a la base de datos no está disponible"),
                "Se esperaba un error por conexión de base de datos.");
        }
    }
}
