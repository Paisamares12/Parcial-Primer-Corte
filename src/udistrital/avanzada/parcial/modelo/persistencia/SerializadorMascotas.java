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
 * Gestiona la serialización y deserialización de objetos MascotaVO.
 * Utiliza flujo binario (ObjectOutputStream / ObjectInputStream)
 * para guardar y cargar listas completas de mascotas.
 *
 * Incluye validaciones para evitar fallos cuando el archivo no existe
 * o está dañado.
 *
 * @author Juan Sebastián Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class SerializadorMascotas {

    private final String rutaArchivo;

    public SerializadorMascotas(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Guarda (serializa) la lista completa de mascotas en un archivo binario.
     * Si el archivo no existe, se crea automáticamente.
     *
     * @param lista lista de mascotas a guardar
     * @throws IOException si ocurre un error de escritura
     */
    public void guardar(List<MascotaVO> lista) throws IOException {
        if (lista == null) {
            lista = new ArrayList<>();
        }

        File archivo = new File(rutaArchivo);
        File parent = archivo.getParentFile();

        // Crear directorios si no existen
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        }
    }

    /**
     * Carga (deserializa) la lista de mascotas desde el archivo binario.
     * Si el archivo no existe o está vacío, retorna una lista vacía.
     *
     * @return lista de mascotas recuperadas
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
                // Si el archivo no contiene una lista, se ignora
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            // Si hay error, retornar lista vacía en lugar de romper la ejecución
            return new ArrayList<>();
        }
    }
}
