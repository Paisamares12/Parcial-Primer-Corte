package udistrital.avanzada.parcial.control;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.Clasificacion;

/**
 * Pruebas unitarias para la clase {@link ValidadorDatos}.
 *
 * <p>
 * Estas pruebas verifican la correcta identificación y manejo de mascotas con
 * datos incompletos, así como la limpieza de listas.
 * </p>
 *
 * @author Paula
 * @version 5.0
 * @since 2025-10-15
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidadorDatosTest {

    private static MascotaVO completa;
    private static MascotaVO incompletaApodo;
    private static MascotaVO incompletaCampos;

    private static List<MascotaVO> listaMascotas;

    // ==============================
    // CONFIGURACIÓN GLOBAL
    // ==============================
    @BeforeAll
    static void iniciarPruebas() {
        System.out.println(">>> Iniciando pruebas de ValidadorDatos...");

        // Mascota completamente válida
        completa = new MascotaVO("Panchito", Alimentacion.Carnes, "Pancho",
                Clasificacion.Mamiferos, "Felidae", "Macho", "Panthera");

        // Mascota con apodo vacío
        incompletaApodo = new MascotaVO("", Alimentacion.Carnes, "Leo",
                Clasificacion.Mamiferos, "Felidae", "Macho", "Panthera");

        // Mascota con varios campos incompletos
        incompletaCampos = new MascotaVO(null, null, "", null, "", "", "");

        // Lista con todas las mascotas
        listaMascotas = new ArrayList<>();
        listaMascotas.add(completa);
        listaMascotas.add(incompletaApodo);
        listaMascotas.add(incompletaCampos);
    }

    @AfterAll
    static void finalizarPruebas() {
        System.out.println(">>> Finalizando pruebas de ValidadorDatos.");
        completa = null;
        incompletaApodo = null;
        incompletaCampos = null;
        listaMascotas = null;
    }

    @BeforeEach
    void prepararCaso() {
        System.out.println("Preparando un nuevo caso de prueba...");
    }

    @AfterEach
    void cerrarCaso() {
        System.out.println("Caso de prueba finalizado.\n");
    }

    // ==============================
    // TESTS UNITARIOS
    // ==============================
    /**
     * 1️⃣ Verifica que una mascota completa no sea marcada como incompleta.
     */
    @Test
    @Order(1)
    void testMascotaCompleta() {
        assertFalse(ValidadorDatos.tieneDatosIncompletos(completa),
                "Una mascota completa no debe ser marcada como incompleta.");
    }

    /**
     * 2️⃣ Verifica que una mascota con apodo vacío sea marcada como incompleta.
     */
    @Test
    @Order(2)
    void testMascotaIncompletaApodo() {
        assertTrue(ValidadorDatos.tieneDatosIncompletos(incompletaApodo),
                "Una mascota con apodo vacío debe ser marcada como incompleta.");
    }

    /**
     * 3️⃣ Verifica que una mascota con múltiples campos vacíos/nulos sea
     * marcada como incompleta.
     */
    @Test
    @Order(3)
    void testMascotaIncompletaCampos() {
        assertTrue(ValidadorDatos.tieneDatosIncompletos(incompletaCampos),
                "Una mascota con varios campos vacíos o nulos debe ser marcada como incompleta.");
    }

    /**
     * 4️⃣ Verifica que filtrarIncompletas devuelva solo las mascotas
     * incompletas.
     */
    @Test
    @Order(4)
    void testFiltrarIncompletas() {
        List<MascotaVO> incompletas = ValidadorDatos.filtrarIncompletas(listaMascotas);
        assertNotNull(incompletas, "La lista devuelta no debe ser nula.");
        assertEquals(2, incompletas.size(), "Debe devolver exactamente 2 mascotas incompletas.");
        assertTrue(incompletas.contains(incompletaApodo), "Debe incluir la mascota con apodo vacío.");
        assertTrue(incompletas.contains(incompletaCampos), "Debe incluir la mascota con múltiples campos vacíos.");
    }

    /**
     * 5️⃣ Verifica que limpiarIncompletas elimine las mascotas incompletas de
     * la lista original y devuelva el número correcto de eliminaciones.
     */
    @Test
    @Order(5)
    void testLimpiarIncompletas() {
        List<MascotaVO> copiaLista = new ArrayList<>(listaMascotas);
        int eliminadas = ValidadorDatos.limpiarIncompletas(copiaLista);
        assertEquals(2, eliminadas, "Se deben eliminar 2 mascotas incompletas.");
        assertEquals(1, copiaLista.size(), "Debe quedar solo 1 mascota en la lista.");
        assertTrue(copiaLista.contains(completa), "La mascota completa debe permanecer en la lista.");
    }

    /**
     * 6️⃣ Verifica que métodos manejen correctamente una lista nula.
     */
    @Test
    @Order(6)
    void testListaNula() {
        assertDoesNotThrow(() -> {
            List<MascotaVO> filtradas = ValidadorDatos.filtrarIncompletas(null);
            assertNotNull(filtradas, "La lista filtrada nunca debe ser nula, incluso si la original es nula.");
            assertEquals(0, filtradas.size(), "La lista filtrada debe estar vacía.");

            int eliminadas = ValidadorDatos.limpiarIncompletas(null);
            assertEquals(0, eliminadas, "No se deben eliminar elementos si la lista es nula.");
        });
    }

    /**
     * 7️⃣ Verifica que pasar null como mascota sea identificado como
     * incompleta.
     */
    @Test
    @Order(7)
    void testMascotaNula() {
        assertTrue(ValidadorDatos.tieneDatosIncompletos(null),
                "Una mascota nula debe ser considerada incompleta.");
    }
}
