package udistrital.avanzada.parcial.control;

import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.Clasificacion;
import udistrital.avanzada.parcial.modelo.dao.AnimalDAO;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase que gestiona las operaciones relacionadas con las mascotas.
 *
 * Actúa como intermediario entre la lógica principal ({@link ControlLogica})
 * y los objetos del modelo ({@link MascotaVO}). Se encarga de registrar, buscar,
 * eliminar y listar mascotas usando el patrón DAO.
 * 
 * Modificado y documentado: Juan Ariza y Juan Sebastian Bravo Rojas
 * 
 *
 * @author Paula Martinez
 * @version 4.0
 * @since 2025-10-13
 */
public class ControlMascota {

    private final AnimalDAO animalDAO;
    private static String mensaje;

    /**
     * Constructor que inicializa el DAO.
     */
    public ControlMascota() {
        this.animalDAO = new AnimalDAO();
    }

    /**
     * Retorna los nombres de todas las clasificaciones.
     * @return arreglo de nombres de clasificación
     */
    public String[] getClasificaciones() {
        Clasificacion[] valores = Clasificacion.values();
        String[] salida = new String[valores.length];
        for (int i = 0; i < valores.length; i++) {
            salida[i] = valores[i].name();
        }
        return salida;
    }

    /**
     * Retorna los nombres de todas las alimentaciones.
     * @return arreglo de nombres de alimentaciones
     */
    public String[] getAlimentaciones() {
        Alimentacion[] valores = Alimentacion.values();
        String[] salida = new String[valores.length];
        for (int i = 0; i < valores.length; i++) {
            salida[i] = valores[i].name();
        }
        return salida;
    }

    /**
     * Registra una nueva mascota en la base de datos.
     *
     * @param m MascotaVO con todos sus campos llenos
     * @throws Exception si hay validaciones que fallan o error de BD
     */
    public void registrarMascota(MascotaVO m) throws Exception {
        if (m == null) {
            throw new IllegalArgumentException("Objeto mascota nulo.");
        }

        if (m.getApodo() == null || m.getApodo().isBlank()) {
            throw new IllegalArgumentException("El apodo no puede estar vacío.");
        }

        if (m.getNombre() == null || m.getNombre().isBlank() ||
            m.getFamilia() == null || m.getFamilia().isBlank() ||
            m.getGenero() == null || m.getGenero().isBlank() ||
            m.getEspecie() == null || m.getEspecie().isBlank()) {
            throw new IllegalArgumentException("Ningún campo puede estar vacío.");
        }

        if (animalDAO.existeMascotaCompleta(m)) {
            throw new IllegalArgumentException("Ya existe una mascota con esos datos exactos.");
        }

        boolean exito = animalDAO.insertarMascota(m);
        
        if (exito) {
            mensaje = "Mascota registrada correctamente.";
        } else {
            throw new Exception("No se pudo registrar la mascota en la base de datos.");
        }
    }

    /**
     * Busca una mascota en la base de datos por su apodo.
     *
     * @param apodo Apodo de la mascota a buscar.
     * @return El objeto {@link MascotaVO} correspondiente o {@code null} si no se encuentra.
     * @throws Exception si ocurre un error de base de datos
     */
    public MascotaVO buscarMascota(String apodo) throws Exception {
        if (apodo == null || apodo.isBlank()) {
            return null;
        }
        
        try {
            MascotaVO mascota = (MascotaVO) animalDAO.consultarAnimalApodo(apodo);
            if (mascota == null) {
                mensaje = "No se encontró ninguna mascota con ese apodo.";
            }
            return mascota;
        } catch (Exception e) {
            throw new Exception("Error al buscar mascota: " + e.getMessage());
        }
    }

    /**
     * Consulta mascotas aplicando filtros opcionales.
     * 
     * @param apodo Apodo a buscar (puede ser null o vacío)
     * @param clasificacion Clasificación a buscar (puede ser null)
     * @param familia Familia a buscar (puede ser null o vacío)
     * @param alimentacion Alimentación a buscar (puede ser null)
     * @return Lista de mascotas que cumplen con los filtros
     * @throws Exception si hay error de base de datos
     */
    public List<MascotaVO> consultarConFiltros(String apodo, String clasificacion, 
                                               String familia, String alimentacion) throws Exception {
        try {
            Clasificacion clasifEnum = null;
            if (clasificacion != null && !clasificacion.isEmpty()) {
                try {
                    clasifEnum = Clasificacion.valueOf(clasificacion);
                } catch (IllegalArgumentException e) {
                    // Si no es válido, se ignora
                }
            }

            Alimentacion alimentEnum = null;
            if (alimentacion != null && !alimentacion.isEmpty()) {
                try {
                    alimentEnum = Alimentacion.valueOf(alimentacion);
                } catch (IllegalArgumentException e) {
                    // Si no es válido, se ignora
                }
            }

            return animalDAO.consultarConFiltros(apodo, clasifEnum, familia, alimentEnum);
        } catch (ConexionException e) {
            throw new Exception("Error al consultar con filtros: " + e.getMessage());
        }
    }

    /**
     * Elimina una mascota existente de la base de datos.
     *
     * @param apodo Apodo de la mascota a eliminar.
     * @throws Exception si la mascota no existe o hay error de BD
     */
    public void eliminarMascota(String apodo) throws Exception {
        if (apodo == null || apodo.isBlank()) {
            throw new IllegalArgumentException("El apodo no puede estar vacío.");
        }

        try {
            boolean exito = animalDAO.eliminarMascota(apodo);
            if (exito) {
                mensaje = "Mascota eliminada correctamente.";
            } else {
                throw new Exception("No se encontró ninguna mascota con ese apodo.");
            }
        } catch (ConexionException e) {
            throw new Exception("Error al eliminar mascota: " + e.getMessage());
        }
    }

    /**
     * Lista todas las mascotas desde la base de datos.
     *
     * @return lista de mascotas
     * @throws Exception si ocurre un error de base de datos
     */
    public List<MascotaVO> listarMascotas() throws Exception {
        try {
            return animalDAO.listarTodasMascotas();
        } catch (ConexionException e) {
            throw new Exception("Error al listar mascotas: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos modificables de una mascota.
     * Solo se puede modificar: nombre, clasificación y alimentación.
     *
     * @param mascota Mascota con los datos actualizados
     * @throws Exception si hay error en la actualización
     */
    public void actualizarMascota(MascotaVO mascota) throws Exception {
        if (mascota == null) {
            throw new IllegalArgumentException("Objeto mascota nulo.");
        }

        try {
            boolean exito = animalDAO.actualizarMascota(mascota);
            if (exito) {
                mensaje = "Mascota actualizada correctamente.";
            } else {
                throw new Exception("No se pudo actualizar la mascota.");
            }
        } catch (ConexionException e) {
            throw new Exception("Error al actualizar mascota: " + e.getMessage());
        }
    }

    /**
     * Devuelve el mensaje más reciente del sistema.
     *
     * @return Mensaje descriptivo de estado o error.
     */
    public static String getMensaje() {
        return mensaje;
    }
}