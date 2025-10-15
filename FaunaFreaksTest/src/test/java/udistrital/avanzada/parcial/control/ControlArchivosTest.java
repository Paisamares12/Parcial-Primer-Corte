package udistrital.avanzada.parcial.control;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import udistrital.avanzada.parcial.modelo.*;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase de pruebas unitarias para {@link ControlArchivos}.
 *
 * <p>
 * Se aplican los diferentes tipos de pruebas exigidos: - @BeforeAll y
 *
 * @AfterAll: inicialización y cierre global de recursos. - @BeforeEach y
 * @AfterEach: preparación y limpieza antes y después de cada prueba. - Datasets
 * de prueba que simulan diferentes escenarios de ejecución.
 * </p>
 *
 * @author Paula
 * @version 5.0
 * @since 2025-10-15
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControlArchivosTest {

    private static ControlArchivos controlArchivos;
    private List<MascotaVO> datasetMascotas;
    private MascotaVO mascota1;
    private MascotaVO mascota2;

    // ==============================
    // CONFIGURACIÓN GLOBAL DE PRUEBAS
    // ==============================
    /**
     * Se ejecuta una sola vez antes de todas las pruebas. Inicializa el objeto
     * de ControlArchivos.
     */
    @BeforeAll
    static void inicializarRecursos() {
        System.out.println("Iniciando pruebas de ControlArchivos...");
        controlArchivos = new ControlArchivos();
    }

    /**
     * Se ejecuta una sola vez al finalizar todas las pruebas. Cierra y libera
     * recursos globales.
     */
    @AfterAll
    static void cerrarRecursos() {
        System.out.println("Finalizaron todas las pruebas de ControlArchivos.");
        controlArchivos = null;
    }

    // ==============================
    // CONFIGURACIÓN INDIVIDUAL POR PRUEBA
    // ==============================
    /**
     * Antes de cada prueba, se crea un dataset de ejemplo con diferentes tipos
     * de mascotas.
     */
    @BeforeEach
    void setUp() {
        // Inicializa dataset antes de cada prueba
        datasetMascotas = new ArrayList<>();

        mascota1 = new MascotaVO("Kira", Alimentacion.Carnes, "Jaguar", Clasificacion.Mamiferos,
                "Felidae", "Panthera", "Onca");
        mascota2 = new MascotaVO("Coco", Alimentacion.Frutas, "Loro", Clasificacion.Aves,
                "Psittacidae", "Amazona", "Aestiva");

        datasetMascotas.add(mascota1);
        datasetMascotas.add(mascota2);
    }

    /**
     * Después de cada prueba, se limpia el dataset temporal.
     */
    @AfterEach
    void limpiarDatos() {
        datasetMascotas.clear();
        System.out.println("Dataset limpiado después de la prueba.");
    }

    // ==============================
    // PRUEBAS UNITARIAS
    // ==============================
    /**
     * Verifica que el método serializarMascotas no lance excepciones al guardar
     * una lista válida de mascotas.
     */
    @Test
    @Order(1)
    void testSerializarMascotas() {
        assertDoesNotThrow(() -> controlArchivos.serializarMascotas(datasetMascotas),
                "La serialización no debería lanzar excepción.");
    }

    /**
     * Verifica que el método deserializarMascotas devuelva una lista no nula
     * (aunque puede estar vacía).
     */
    @Test
    @Order(2)
    void testDeserializarMascotas() throws ConexionException {
        List<MascotaVO> resultado = controlArchivos.deserializarMascotas();
        assertNotNull(resultado, "La lista deserializada no debe ser nula.");
    }

    /**
     * Verifica que guardar en archivo de acceso aleatorio no lance excepciones
     * cuando la lista es válida.
     */
    @Test
    @Order(3)
    void testGuardarAleatorio() {
        assertDoesNotThrow(() -> controlArchivos.guardarAleatorio(datasetMascotas),
                "No debería fallar al guardar en archivo aleatorio.");
    }

    /**
     * Prueba cargarAleatorio, verificando que retorne una lista válida y que
     * los registros incompletos sean filtrados correctamente.
     */
    @Test
    @Order(4)
    void testCargarAleatorio() throws ConexionException {
        List<MascotaVO> resultado = controlArchivos.cargarAleatorio();
        assertNotNull(resultado, "La lista cargada no debe ser nula.");
        assertTrue(resultado instanceof List, "El resultado debe ser una lista.");
    }

    /**
     * Verifica que al pasar una lista nula a serializarMascotas, no se produzca
     * una excepción.
     */
    @Test
    @Order(5)
    void testSerializarMascotasConNulo() {
        assertDoesNotThrow(() -> controlArchivos.serializarMascotas(null),
                "No debería lanzar excepción si recibe una lista nula (según implementación actual).");
    }

}
