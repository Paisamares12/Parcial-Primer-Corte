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
 * Permite guardar y recuperar información de mascotas
 * mediante un archivo de acceso aleatorio.
 *
 * Cada registro se guarda con delimitadores '|' en texto codificado UTF.
 *
 * @author Juan Sebastian Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class AccesoAleatorioMascotas {

    private final String rutaArchivo;

    public AccesoAleatorioMascotas(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Guarda todas las mascotas en el archivo (sobrescribe el contenido anterior).
     */
    public void guardarMascotas(List<MascotaVO> lista) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(new File(rutaArchivo), "rw")) {
            raf.setLength(0); // limpiar archivo antes de escribir
            for (MascotaVO m : lista) {
                String registro = m.getApodo() + "|" +
                                  m.getAlimentacion().name() + "|" + // Enum a String
                                  m.getNombre() + "|" +
                                  m.getClasificacion().name() + "|" +
                                  m.getFamilia() + "|" +
                                  m.getGenero() + "|" +
                                  m.getEspecie() + "\n";
                raf.writeUTF(registro);
            }
        }
    }

    /**
     * Carga todas las mascotas desde el archivo de acceso aleatorio.
     * Convierte los Strings a sus enums correspondientes.
     */
    public List<MascotaVO> cargarMascotas() throws IOException {
        List<MascotaVO> lista = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        // Si el archivo no existe o está vacío, devolver lista vacía
        if (!archivo.exists() || archivo.length() == 0) {
            return lista;
        }

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                String linea = raf.readUTF();
                String[] partes = linea.split("\\|");

                if (partes.length == 7) {
                    // Conversión segura de texto a enum Alimentacion
                    Alimentacion alimentacionEnum = parseAlimentacion(partes[1]);

                    // Conversión segura de texto a enum Clasificacion
                    Clasificacion clasificacionEnum = parseClasificacion(partes[3]);

                    // Crear objeto MascotaVO con tipos correctos
                    MascotaVO m = new MascotaVO(
                        partes[0].trim(),               // apodo
                        alimentacionEnum,               // enum Alimentacion
                        partes[2].trim(),               // nombre
                        clasificacionEnum,               // enum Clasificacion
                        partes[4].trim(),               // familia
                        partes[5].trim(),               // genero
                        partes[6].trim()                // especie
                    );
                    lista.add(m);
                }
            }
        }

        return lista;
    }

    /**
     * Convierte un String a un valor del enum Alimentacion.
     * Realiza limpieza de tildes, espacios y mayúsculas/minúsculas.
     */
    private Alimentacion parseAlimentacion(String texto) {
        if (texto == null) return null;

        // Normalizar texto (quitar tildes, espacios y pasar a mayúsculas)
        String normalizado = Normalizer.normalize(texto.trim(), Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toUpperCase();

        try {
            // Intentar convertir directamente
            return Alimentacion.valueOf(capitalizar(normalizado));
        } catch (IllegalArgumentException e) {
            // Si falla, usar un valor por defecto o manejar según necesidad
            return Alimentacion.Omnivoros;
        }
    }

    /**
     * Convierte un String a un valor del enum Clasificacion.
     * (Mismo proceso de limpieza que Alimentacion)
     */
    private Clasificacion parseClasificacion(String texto) {
        if (texto == null) return null;

        String normalizado = Normalizer.normalize(texto.trim(), Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toUpperCase();

        try {
            return Clasificacion.valueOf(capitalizar(normalizado));
        } catch (IllegalArgumentException e) {
            // Valor por defecto o nulo si no coincide
            return null;
        }
    }

    /**
     * Convierte una cadena en formato "Lacteos" desde "LACTEOS"
     * (para que coincida con los nombres de tus enums).
     */
    private String capitalizar(String texto) {
        if (texto == null || texto.isBlank()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}

