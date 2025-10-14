package udistrital.avanzada.parcial.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.dao.AnimalDAO;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase InicializadorBD que gestiona la carga inicial de datos
 * desde el archivo de propiedades hacia la base de datos.
 * 
 * <p>
 * Esta clase coordina el proceso de inicialización, leyendo las
 * mascotas del archivo properties y almacenándolas en la base de
 * datos, evitando duplicados y registrando cuáles tienen datos
 * incompletos para posterior corrección.
 * </p>
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 4.0
 * @since 2025-10-13
 */
public class InicializadorBD {

    /** Cargador de propiedades para leer el archivo de configuración */
    private CargadorPropiedades cargador;
    
    /** DAO para operaciones con la base de datos */
    private AnimalDAO dao;

    /**
     * Constructor por defecto que inicializa los componentes necesarios.
     */
    public InicializadorBD() {
        this.cargador = new CargadorPropiedades();
        this.dao = new AnimalDAO();
    }

    /**
     * Inicializa la base de datos con las mascotas del archivo properties.
     * 
     * <p>
     * Lee el archivo de propiedades y carga cada mascota válida en la
     * base de datos. Solo inserta las mascotas que no existan previamente
     * (evita duplicados). Retorna una lista de mascotas con datos
     * incompletos que requieren ser completados manualmente.
     * </p>
     * 
     * <p>
     * Este método NO detiene la ejecución si ocurren errores individuales
     * al insertar mascotas, permitiendo que continúe con las siguientes.
     * Los errores se propagan a la capa superior para su manejo.
     * </p>
     * 
     * @return lista de mascotas que tienen datos incompletos y no fueron
     *         insertadas en la base de datos
     * @throws Exception si ocurre un error crítico al leer el archivo
     *         de propiedades o conectar con la base de datos
     */
    public List<MascotaVO> inicializarDatos() throws Exception {
        List<MascotaVO> mascotasIncompletas = new ArrayList<>();
        List<MascotaVO> mascotasCargadas = cargador.cargarMascotas();

        for (MascotaVO mascota : mascotasCargadas) {
            if (cargador.tienesDatosIncompletos(mascota)) {
                mascotasIncompletas.add(mascota);
                continue;
            }

            try {
                if (!dao.existeMascotaCompleta(mascota)) {
                    dao.insertarMascota(mascota);
                }
            } catch (ConexionException e) {
                throw new Exception("Error al inicializar mascota: " + 
                                  mascota.getApodo(), e);
            }
        }

        return mascotasIncompletas;
    }
}