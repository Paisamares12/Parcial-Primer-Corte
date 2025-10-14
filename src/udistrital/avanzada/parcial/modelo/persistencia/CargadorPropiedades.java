/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.parcial.modelo.persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.Clasificacion;
import udistrital.avanzada.parcial.modelo.MascotaVO;

/**
 * Clase CargadorPropiedades que gestiona la lectura del archivo
 * de configuración inicial de mascotas.
 * 
 * <p>
 * Lee un archivo .properties que contiene información de mascotas
 * en formato CSV y las convierte en objetos MascotaVO. Valida que
 * los datos estén completos y sean correctos según los enums definidos.
 * </p>
 * 
 * <p>
 * Formato esperado en el archivo properties:<br>
 * Animal[N]=Nombre,Apodo,Clasificacion,Familia,Genero,Especie,Alimentacion
 * </p>
 * 
 * @author Juan Estevan Ariza Ortiz
 * @version 4.0
 * @since 2025-10-14
 */
public class CargadorPropiedades {

    /** Ruta relativa al archivo de propiedades de mascotas */
    private static final String RUTA_ARCHIVO = "src/data/mascotas.properties";

    /**
     * Carga todas las mascotas definidas en el archivo de propiedades.
     * 
     * <p>
     * Lee el archivo properties y convierte cada entrada válida en un
     * objeto MascotaVO. Si una entrada tiene datos incompletos o inválidos,
     * se omite y se continúa con las siguientes entradas.
     * </p>
     * 
     * <p>
     * Intenta cargar el archivo desde el sistema de archivos primero,
     * y si no lo encuentra, intenta cargarlo como recurso desde el classpath.
     * </p>
     * 
     * @return lista de objetos MascotaVO cargados desde el archivo
     * @throws IOException si no se puede leer el archivo de propiedades
     */
    public List<MascotaVO> cargarMascotas() throws IOException {
        List<MascotaVO> mascotas = new ArrayList<>();
        Properties props = new Properties();

        // Intentar cargar desde el sistema de archivos
        try (FileInputStream fis = new FileInputStream(RUTA_ARCHIVO)) {
            props.load(fis);
        } catch (IOException e) {
            // Si falla, intentar cargar como recurso desde el classpath
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("data/mascotas.properties")) {
                if (is != null) {
                    props.load(is);
                } else {
                    throw new IOException("No se encontró el archivo mascotas.properties en: " + RUTA_ARCHIVO + 
                                        " ni en el classpath (data/mascotas.properties)");
                }
            }
        }

        // Parsear las mascotas del archivo
        int i = 1;
        while (props.containsKey("Animal" + i)) {
            String datos = props.getProperty("Animal" + i);
            MascotaVO mascota = parsearMascota(datos, i);
            
            if (mascota != null) {
                mascotas.add(mascota);
            }
            i++;
        }

        return mascotas;
    }

    /**
     * Convierte una línea de texto en un objeto MascotaVO.
     * 
     * <p>
     * Parsea una cadena en formato CSV y crea un objeto MascotaVO con
     * los datos correspondientes. Valida que todos los campos estén
     * presentes y que los valores de enum sean válidos.
     * </p>
     * 
     * <p>
     * Formato esperado:<br>
     * Nombre,Apodo,Clasificacion,Familia,Genero,Especie,Alimentacion
     * </p>
     * 
     * @param linea cadena con los datos separados por comas
     * @param numero número del animal en el archivo (para identificación)
     * @return objeto MascotaVO creado o null si hay datos inválidos
     */
    private MascotaVO parsearMascota(String linea, int numero) {
        if (linea == null || linea.trim().isEmpty()) {
            return null;
        }

        String[] partes = linea.split(",");
        
        if (partes.length < 7) {
            return null;
        }

        try {
            String nombre = partes[0].trim();
            String apodo = partes[1].trim();
            Clasificacion clasificacion = Clasificacion.valueOf(partes[2].trim());
            String familia = partes[3].trim();
            String genero = partes[4].trim();
            String especie = partes[5].trim();
            Alimentacion alimentacion = Alimentacion.valueOf(partes[6].trim());

            if (nombre.isEmpty() || apodo.isEmpty() || familia.isEmpty() || 
                genero.isEmpty() || especie.isEmpty()) {
                return null;
            }

            return new MascotaVO(apodo, alimentacion, nombre, clasificacion, 
                                 familia, genero, especie);

        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Verifica si una mascota tiene datos incompletos o inválidos.
     * 
     * <p>
     * Comprueba que todos los campos obligatorios de la mascota estén
     * presentes y no sean vacíos. Útil para validar antes de insertar
     * en la base de datos.
     * </p>
     * 
     * @param mascota objeto MascotaVO a verificar
     * @return true si faltan datos obligatorios, false si está completa
     */
    public boolean tienesDatosIncompletos(MascotaVO mascota) {
        return mascota.getNombre() == null || mascota.getNombre().isBlank() ||
               mascota.getApodo() == null || mascota.getApodo().isBlank() ||
               mascota.getFamilia() == null || mascota.getFamilia().isBlank() ||
               mascota.getGenero() == null || mascota.getGenero().isBlank() ||
               mascota.getEspecie() == null || mascota.getEspecie().isBlank() ||
               mascota.getClasificacion() == null ||
               mascota.getAlimentacion() == null;
    }
}
