package udistrital.avanzada.parcial.modelo.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.Clasificacion;
import udistrital.avanzada.parcial.modelo.MascotaVO;

/**
 * Clase AccesoAleatorioMascotas
 * 
 * <p>
 * Permite guardar y recuperar información de mascotas mediante un archivo
 * de acceso aleatorio. Este archivo se crea al salir de la aplicación y
 * contiene el estado final de todas las mascotas (incluyendo alimentación).
 * </p>
 *
 * Modificado y documentado: Juan Ariza
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class AccesoAleatorioMascotas {

    private final String rutaArchivo;

    /**
     * Constructor que establece la ruta del archivo.
     * 
     * @param rutaArchivo ruta del archivo de acceso aleatorio
     */
    public AccesoAleatorioMascotas(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Guarda todas las mascotas en archivo de acceso aleatorio.
     * 
     * <p>
     * Sobrescribe el contenido anterior del archivo. Cada registro
     * se guarda con todos sus campos separados por '|' incluyendo
     * el tipo de alimentación.
     * </p>
     *
     * @param lista lista de mascotas a guardar
     * @throws IOException si hay error al escribir el archivo
     */
    public void guardarMascotas(List<MascotaVO> lista) throws IOException {
        File archivo = new File(rutaArchivo);
        File parent = archivo.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            raf.setLength(0);
            
            for (MascotaVO m : lista) {
                String registro = m.getApodo() + "|" +
                                  m.getNombre() + "|" +
                                  m.getClasificacion().name() + "|" +
                                  m.getFamilia() + "|" +
                                  m.getGenero() + "|" +
                                  m.getEspecie() + "|" +
                                  m.getAlimentacion().name() + "\n";
                raf.writeUTF(registro);
            }
        }
    }

    /**
     * Carga todas las mascotas desde el archivo de acceso aleatorio.
     * 
     * <p>
     * Lee cada registro y reconstruye los objetos MascotaVO completos,
     * convirtiendo los strings a sus enums correspondientes.
     * </p>
     *
     * @return lista de mascotas cargadas
     * @throws IOException si hay error al leer el archivo
     */
    public List<MascotaVO> cargarMascotas() throws IOException {
        List<MascotaVO> lista = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists() || archivo.length() == 0) {
            return lista;
        }

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                String linea = raf.readUTF();
                String[] partes = linea.split("\\|");

                if (partes.length == 7) {
                    Clasificacion clasificacionEnum = parseClasificacion(partes[2]);
                    Alimentacion alimentacionEnum = parseAlimentacion(partes[6]);

                    MascotaVO m = new MascotaVO(
                        partes[0].trim(),
                        alimentacionEnum,
                        partes[1].trim(),
                        clasificacionEnum,
                        partes[3].trim(),
                        partes[4].trim(),
                        partes[5].trim()
                    );
                    lista.add(m);
                }
            }
        }

        return lista;
    }

    /**
     * Convierte un String a un valor del enum Alimentacion.
     * 
     * @param texto string a convertir
     * @return valor del enum Alimentacion
     */
    private Alimentacion parseAlimentacion(String texto) {
        if (texto == null) return null;

        String normalizado = Normalizer.normalize(texto.trim(), Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toUpperCase();

        try {
            return Alimentacion.valueOf(capitalizar(normalizado));
        } catch (IllegalArgumentException e) {
            return Alimentacion.Omnivoros;
        }
    }

    /**
     * Convierte un String a un valor del enum Clasificacion.
     * 
     * @param texto string a convertir
     * @return valor del enum Clasificacion
     */
    private Clasificacion parseClasificacion(String texto) {
        if (texto == null) return null;

        String normalizado = Normalizer.normalize(texto.trim(), Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toUpperCase();

        try {
            return Clasificacion.valueOf(capitalizar(normalizado));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Capitaliza una cadena (primera letra mayúscula, resto minúsculas).
     * 
     * @param texto cadena a capitalizar
     * @return cadena capitalizada
     */
    private String capitalizar(String texto) {
        if (texto == null || texto.isBlank()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}

