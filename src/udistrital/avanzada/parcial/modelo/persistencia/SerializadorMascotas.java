package udistrital.avanzada.parcial.modelo.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;

/**
 * Clase SerializadorMascotas
 * 
 * <p>
 * Gestiona la serialización de objetos MascotaVO en formato binario.
 * Al serializar, el campo 'alimentacion' NO se incluye porque está
 * marcado como transient, cumpliendo con el requerimiento de enviar
 * todas las características EXCEPTO el tipo de alimento al Instituto
 * Distrital de Protección y Bienestar Animal (IDPYBA).
 * </p>
 * 
 * Modificado y docuementado: Juan Ariza
 *
 * @author Juan Sebastián Bravo Rojas
 * @version 4.0
 * @since 2025-10-13
 */
public class SerializadorMascotas {

    private final String rutaArchivo;

    /**
     * Constructor que establece la ruta del archivo de serialización.
     * 
     * @param rutaArchivo ruta donde se guardará el archivo serializado
     */
    public SerializadorMascotas(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Serializa la lista de mascotas en un archivo binario.
     * 
     * <p>
     * Guarda todas las características de las mascotas EXCEPTO el tipo
     * de alimento (campo transient), según los requerimientos para envío
     * al IDPYBA en Bogotá.
     * </p>
     * 
     * <p>
     * Si el archivo no existe, se crea automáticamente. Si existe, se
     * sobrescribe con la nueva información.
     * </p>
     *
     * @param lista lista de mascotas a serializar
     * @throws IOException si ocurre un error de escritura
     */
    public void guardar(List<MascotaVO> lista) throws IOException {
        if (lista == null) {
            lista = new ArrayList<>();
        }

        File archivo = new File(rutaArchivo);
        File parent = archivo.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        }
    }

    /**
     * Carga (deserializa) la lista de mascotas desde el archivo binario.
     * 
     * <p>
     * NOTA: Las mascotas cargadas NO tendrán el campo alimentacion porque
     * no fue serializado (es transient). Este método existe por compatibilidad
     * pero no se usa en el flujo normal, ya que la fuente de verdad es la
     * base de datos.
     * </p>
     * 
     * <p>
     * Si el archivo no existe o está vacío, retorna una lista vacía.
     * </p>
     *
     * @return lista de mascotas recuperadas (sin alimentación)
     * @throws IOException si ocurre un error de lectura
     * @throws ClassNotFoundException si hay incompatibilidad de clases
     */
    @SuppressWarnings("unchecked")
    public List<MascotaVO> cargar() throws IOException, ClassNotFoundException {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                return (List<MascotaVO>) obj;
            } else {
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
