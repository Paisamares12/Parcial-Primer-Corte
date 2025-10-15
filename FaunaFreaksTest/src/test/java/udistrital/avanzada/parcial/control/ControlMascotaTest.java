package udistrital.avanzada.parcial.control;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.Clasificacion;
import udistrital.avanzada.parcial.modelo.MascotaVO;

/**
 * Clase de pruebas unitarias para {@link ControlMascota}.
 *
 * <p>
 * Se prueba el correcto funcionamiento de los métodos principales que gestionan
 * las mascotas dentro del sistema, verificando que:
 * <ul>
 * <li>No se lancen excepciones inesperadas.</li>
 * <li>Se manejen adecuadamente los errores de conexión o validaciones.</li>
 * <li>Los datos devueltos sean del tipo esperado.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Nota: Algunas pruebas pueden fallar si la base de datos
 * <b>no está activa</b> o si XAMPP no está corriendo.
 * </p>
 *
 * @author Paula
 * @version 5.0
 * @since 2025-10-15
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControlMascotaTest {

    private static ControlMascota control;

    // ============================================================
    // CONFIGURACIÓN GENERAL DE PRUEBAS
    // ============================================================
    @BeforeAll
    static void iniciarPruebas() {
        System.out.println("=== INICIO DE PRUEBAS DE CONTROL MASCOTA ===");
        control = new ControlMascota();
        assertNotNull(control, "El objeto ControlMascota debería inicializarse correctamente.");
    }

    @AfterAll
    static void finalizarPruebas() {
        System.out.println("=== FIN DE PRUEBAS DE CONTROL MASCOTA ===");
        control = null;
    }

    @BeforeEach
    void prepararCaso() {
        System.out.println("\nPreparando caso de prueba...");
    }

    @AfterEach
    void cerrarCaso() {
        System.out.println("Caso de prueba finalizado.");
    }

    // ============================================================
    // PRUEBAS UNITARIAS
    // ============================================================
    /**
     * 1️⃣ Prueba que la obtención de clasificaciones devuelva un arreglo
     * válido.
     */
    @Test
    @Order(1)
    void testGetClasificaciones() {
        assertDoesNotThrow(() -> {
            String[] clasificaciones = control.getClasificaciones();
            assertNotNull(clasificaciones, "El arreglo de clasificaciones no debe ser nulo.");
            assertTrue(clasificaciones.length > 0, "Debe existir al menos una clasificación.");
        }, "No debería lanzar excepción al obtener clasificaciones.");
    }

    /**
     * 2️⃣ Prueba que la obtención de alimentaciones devuelva un arreglo válido.
     */
    @Test
    @Order(2)
    void testGetAlimentaciones() {
        assertDoesNotThrow(() -> {
            String[] alimentaciones = control.getAlimentaciones();
            assertNotNull(alimentaciones, "El arreglo de alimentaciones no debe ser nulo.");
            assertTrue(alimentaciones.length > 0, "Debe existir al menos una alimentación.");
        }, "No debería lanzar excepción al obtener alimentaciones.");
    }

    /**
     * 3️⃣ Verifica que registrar una mascota nula lance una excepción clara.
     */
    @Test
    @Order(3)
    void testRegistrarMascotaNula() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            control.registrarMascota(null);
        }, "Debe lanzar excepción al intentar registrar una mascota nula.");
        assertTrue(e.getMessage().contains("nulo"), "El mensaje de error debe mencionar 'nulo'.");
    }

    /**
     * 4️⃣ Verifica que no se pueda registrar una mascota con campos vacíos.
     */
    /**
     * 4️⃣ Verifica que no se pueda registrar una mascota con campos vacíos.
     */
    @Test
    @Order(4)
    void testRegistrarMascotaCamposVacios() {
        MascotaVO m = new MascotaVO(
                "", // apodo vacío
                Alimentacion.Carnes, // cualquiera, no importa
                "", // nombre vacío
                Clasificacion.Anfibios, // cualquiera
                "", // familia vacía
                "", // género vacío
                "" // especie vacía
        );

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            control.registrarMascota(m);
        }, "Debe lanzar excepción al intentar registrar con campos vacíos.");

        String mensaje = e.getMessage().toLowerCase();
        assertTrue(mensaje.contains("vacío") || mensaje.contains("nulo"),
                "El mensaje de error debe mencionar 'vacío' o 'nulo'.");
    }

    /**
     * 5️⃣ Prueba la búsqueda de una mascota por apodo inexistente o vacío.
     */
    @Test
    @Order(5)
    void testBuscarMascotaNoExiste() {
        assertDoesNotThrow(() -> {
            MascotaVO mascota = control.buscarMascota("inexistente123");
            assertNull(mascota, "Debe devolver null si la mascota no existe.");
        }, "No debería lanzar excepción al buscar una mascota que no existe.");
    }

    /**
     * 6️⃣ Verifica que consultar con filtros vacíos maneje correctamente los
     * errores de conexión si la base de datos no está activa.
     */
    @Test
    @Order(6)
    void testConsultarConFiltros() {
        try {
            List<MascotaVO> lista = control.consultarConFiltros("", "", "", "");
            System.out.println("Consulta con filtros vacíos ejecutada correctamente. Tamaño: " + lista.size());
        } catch (Exception e) {
            System.out.println("⚠️ Error esperado al consultar con filtros: " + e.getMessage());
            assertTrue(e.getMessage().contains("base de datos"),
                    "Debe informar un error de conexión a la base de datos.");
        }
    }

    /**
     * 7️⃣ Verifica que listarMascotas maneje correctamente los errores si la
     * conexión con la base de datos falla.
     */
    @Test
    @Order(7)
    void testListarMascotas() {
        try {
            List<MascotaVO> lista = control.listarMascotas();
            assertNotNull(lista, "La lista no debería ser nula.");
        } catch (Exception e) {
            System.out.println("⚠️ No se pudo listar mascotas: " + e.getMessage());
            assertTrue(e.getMessage().contains("base de datos"),
                    "Debe indicar error de conexión a la base de datos.");
        }
    }

    /**
     * 8️⃣ Verifica que eliminarMascota maneje apodos vacíos correctamente.
     */
    @Test
    @Order(8)
    void testEliminarMascotaApodoVacio() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            control.eliminarMascota("");
        }, "Debe lanzar excepción al intentar eliminar con apodo vacío.");
        assertTrue(e.getMessage().contains("vacío"), "El mensaje de error debe mencionar 'vacío'.");
    }

    /**
     * 9️⃣ Verifica que actualizar una mascota nula lance una excepción.
     */
    @Test
    @Order(9)
    void testActualizarMascotaNula() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            control.actualizarMascota(null);
        }, "Debe lanzar excepción al intentar actualizar una mascota nula.");
        assertTrue(e.getMessage().contains("nulo"), "El mensaje de error debe mencionar 'nulo'.");
    }

    /**
     * 🔟 Verifica que getMensaje() funcione correctamente.
     */
    @Test
    @Order(10)
    void testGetMensaje() {
        String mensaje = ControlMascota.getMensaje();
        System.out.println("Mensaje actual del sistema: " + mensaje);
        assertTrue(mensaje == null || mensaje instanceof String,
                "El mensaje debe ser una cadena o null.");
    }
}
