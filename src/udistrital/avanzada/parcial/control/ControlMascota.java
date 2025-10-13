package udistrital.avanzada.parcial.control;

import java.util.ArrayList;
import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.Clasificacion;

/**
 * Clase que gestiona las operaciones relacionadas con las mascotas.
 *
 * Actúa como intermediario entre la lógica principal ({@link ControlLogica})
 * y los objetos del modelo ({@link MascotaVO}). Se encarga de registrar, buscar,
 * eliminar y listar mascotas.
 *
 * Originalmente creada por Paula Martínez.
 * Modificada y documentada por Juan Sebastián Bravo Rojas.
 *
 * @author Paula Martinez
 * @version 2.0
 * @since 2025-10-13
 */
public class ControlMascota {

    /** Lista que contiene todas las mascotas registradas. */
    private final ArrayList<MascotaVO> listaMascotas;

    /** Mensaje de estado o error para ser mostrado en la GUI. */
    private static String mensaje;

    /**
     * Constructor por defecto.
     * Inicializa la lista de mascotas vacía.
     */
    public ControlMascota() {
        listaMascotas = new ArrayList<>();
    }

    // ------------------------------
    // Métodos que ControlLogica espera
    // ------------------------------

    /**
     * Retorna los nombres de todas las clasificaciones (para poblar combos).
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
     * Retorna los nombres de todas las alimentaciones (para poblar combos).
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
     * Registra una nueva mascota a partir de un objeto MascotaVO.
     * Método compatible con la firma que usa ControlLogica.
     *
     * @param m MascotaVO con todos sus campos llenos (incluye alimentacion como enum)
     * @throws Exception si hay validaciones que fallan (apodo duplicado, datos vacíos)
     */
    public void registrarMascota(MascotaVO m) throws Exception {
        // Validaciones mínimas reusando tu lógica previa
        if (m == null) {
            throw new IllegalArgumentException("Objeto mascota nulo.");
        }
        boolean ok = registrarMascota(
                m.getApodo(),
                m.getAlimentacion(),
                m.getNombre(),
                m.getClasificacion(),
                m.getFamilia(),
                m.getGenero(),
                m.getEspecie()
        );

        if (!ok) {
            throw new Exception("No se pudo registrar la mascota: " + getMensaje());
        }
    }

    /**
     * Devuelve la lista de mascotas como List (compatible con ControlLogica).
     * @return lista de mascotas
     */
    public List<MascotaVO> listarMascotas() {
        // devolvemos copia para evitar modificaciones externas accidentales
        return new ArrayList<>(listaMascotas);
    }

    // ------------------------------
    // Métodos originales / auxiliares
    // ------------------------------

    /**
     * Registra una nueva mascota en el sistema.
     * Esta versión recibe campos individuales (útil para la vista).
     *
     * @return true si se registró correctamente, false en caso contrario.
     */
    public boolean registrarMascota(String apodo, Alimentacion alimentacion, String nombre,
                                    Clasificacion clasificacion, String familia,
                                    String genero, String especie) {
        try {
            // Validaciones básicas
            if (apodo == null || apodo.isBlank()) {
                throw new IllegalArgumentException("El apodo no puede estar vacío.");
            }
            if (buscarMascota(apodo) != null) {
                throw new IllegalArgumentException("Ya existe una mascota con ese apodo.");
            }
            if (nombre == null || nombre.isBlank() ||
                familia == null || familia.isBlank() ||
                genero == null || genero.isBlank() ||
                especie == null || especie.isBlank()) {
                throw new IllegalArgumentException("Ningún campo puede estar vacío.");
            }

            // Crea el objeto MascotaVO y lo agrega a la lista
            MascotaVO mascota = new MascotaVO(apodo, alimentacion, nombre, clasificacion, familia, genero, especie);
            listaMascotas.add(mascota);
            mensaje = "Mascota registrada correctamente.";
            return true;

        } catch (IllegalArgumentException ex) {
            mensaje = ex.getMessage();
            return false;
        }
    }

    /**
     * Busca una mascota en la lista por su apodo.
     *
     * @param apodo Apodo de la mascota a buscar.
     * @return El objeto {@link MascotaVO} correspondiente o {@code null} si no se encuentra.
     */
    public MascotaVO buscarMascota(String apodo) {
        if (apodo == null) return null;
        for (MascotaVO mascota : listaMascotas) {
            if (mascota.getApodo().equalsIgnoreCase(apodo)) {
                return mascota;
            }
        }
        return null;
    }

    /**
     * Elimina una mascota existente del sistema.
     *
     * @param apodo Apodo de la mascota a eliminar.
     * @return {@code true} si la mascota fue eliminada, {@code false} si no existe.
     */
    public boolean eliminarMascota(String apodo) {
        MascotaVO mascota = buscarMascota(apodo);
        if (mascota != null) {
            listaMascotas.remove(mascota);
            mensaje = "Mascota eliminada correctamente.";
            return true;
        } else {
            mensaje = "No se encontró ninguna mascota con ese apodo.";
            return false;
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
