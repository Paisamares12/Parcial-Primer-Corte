package udistrital.avanzada.parcial.control;

import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.persistencia.SerializadorMascotas;
import udistrital.avanzada.parcial.modelo.persistencia.AccesoAleatorioMascotas;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase ControlArchivos
 * 
 * <p>
 * Gestiona la serialización y acceso aleatorio de los datos de las mascotas.
 * </p>
 * 
 * <p>
 * <b>Serialización:</b> Guarda las mascotas SIN el tipo de alimento (campo transient)
 * para envío al Instituto Distrital de Protección y Bienestar Animal (IDPYBA).
 * </p>
 * 
 * <p>
 * <b>Acceso Aleatorio:</b> Guarda las mascotas CON todos los datos incluyendo
 * alimentación, representando el estado completo del sistema al salir.
 * </p>
 * 
 * Modificado por: Juan Ariza y Juan Sebastián Bravo Rojas
 * 
 * @author Paula Martínez
 * @version 4.0
 * @since 2025-10-13
 */
public class ControlArchivos {

    private SerializadorMascotas serializador;
    private AccesoAleatorioMascotas accesoAleatorio;

    /**
     * Constructor que inicializa los componentes de persistencia.
     */
    public ControlArchivos() {
        serializador = new SerializadorMascotas("mascotas.dat");
        accesoAleatorio = new AccesoAleatorioMascotas("mascotasAleatorio.dat");
    }

    /**
     * Serializa la lista completa de mascotas en archivo binario.
     * 
     * <p>
     * Este archivo NO incluye el tipo de alimento (campo transient en MascotaVO)
     * y está destinado para ser enviado al IDPYBA en Bogotá.
     * </p>
     * 
     * @param lista lista de mascotas a serializar
     * @throws ConexionException si hay error al serializar
     */
    public void serializarMascotas(List<MascotaVO> lista) throws ConexionException {
        try {
            serializador.guardar(lista);
        } catch (Exception e) {
            throw new ConexionException("Error al serializar mascotas", e);
        }
    }

    /**
     * Carga todas las mascotas desde el archivo binario serializado.
     * 
     * <p>
     * NOTA: Este método existe por compatibilidad pero no se usa en el flujo
     * normal. Las mascotas cargadas NO tendrán el campo alimentacion.
     * La fuente de verdad es la base de datos.
     * </p>
     * 
     * @return lista de mascotas sin el campo alimentación
     * @throws ConexionException si hay error al deserializar
     */
    public List<MascotaVO> deserializarMascotas() throws ConexionException {
        try {
            return serializador.cargar();
        } catch (Exception e) {
            throw new ConexionException("Error al deserializar mascotas", e);
        }
    }

    /**
     * Guarda la lista en archivo de acceso aleatorio.
     * 
     * <p>
     * Este archivo incluye TODOS los datos de las mascotas (incluyendo
     * alimentación) y representa el estado final del sistema al salir
     * de la aplicación.
     * </p>
     * 
     * @param lista lista de mascotas a guardar
     * @throws ConexionException si hay error al guardar
     */
    public void guardarAleatorio(List<MascotaVO> lista) throws ConexionException {
        try {
            accesoAleatorio.guardarMascotas(lista);
        } catch (Exception e) {
            throw new ConexionException("Error al guardar en archivo aleatorio", e);
        }
    }

    /**
    * Carga la lista desde archivo de acceso aleatorio.
    * 
    * <p>
    * Este método recupera las mascotas con TODOS sus datos incluyendo
    * alimentación. Si detecta mascotas con datos incompletos, las
    * filtra y notifica cuántas necesitan corrección antes de iniciar
    * el resto de la aplicación.
    * </p>
    * 
    * @return lista de mascotas con todos los datos válidos
    * @throws ConexionException si hay error al leer
    */
    public List<MascotaVO> cargarAleatorio() throws ConexionException {
        try {
            List<MascotaVO> lista = accesoAleatorio.cargarMascotas();

            // 🔹 Validar y limpiar datos incompletos
            List<MascotaVO> incompletas = ValidadorDatos.filtrarIncompletas(lista);
            if (!incompletas.isEmpty()) {
                System.out.println("⚠️ Se encontraron " + incompletas.size()
                + " registros incompletos. Deben ser corregidos.");
            }

            // Puedes decidir si las eliminas o solo las reportas:
            ValidadorDatos.limpiarIncompletas(lista);

            return lista;

        } catch (Exception e) {
            throw new ConexionException("Error al leer archivo aleatorio", e);
        }
    }
}

