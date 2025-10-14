/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.parcial.modelo.persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.Clasificacion;
import udistrital.avanzada.parcial.modelo.MascotaVO;

/**
 * Clase CargadorPropiedades
 * 
 * Lee el archivo de propiedades y carga las mascotas iniciales.
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class CargadorPropiedades {

    private static final String RUTA_ARCHIVO = "data/mascotas.properties";

    /**
     * Carga todas las mascotas desde el archivo de propiedades.
     * 
     * @return Lista de mascotas leídas del archivo
     * @throws IOException si hay error al leer el archivo
     */
    public List<MascotaVO> cargarMascotas() throws IOException {
        List<MascotaVO> mascotas = new ArrayList<>();
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream(RUTA_ARCHIVO)) {
            props.load(fis);

            int i = 1;
            while (props.containsKey("Animal" + i)) {
                String datos = props.getProperty("Animal" + i);
                MascotaVO mascota = parsearMascota(datos, i);
                
                if (mascota != null) {
                    mascotas.add(mascota);
                }
                i++;
            }
        }

        return mascotas;
    }

    /**
     * Convierte una línea del archivo properties en un objeto MascotaVO.
     * Formato esperado: Nombre,Apodo,Clasificacion,Familia,Genero,Especie,Alimentacion
     * 
     * @param linea Línea con los datos separados por comas
     * @param numero Número del animal (para referencia en errores)
     * @return MascotaVO creado o null si hay datos incompletos
     */
    private MascotaVO parsearMascota(String linea, int numero) {
        if (linea == null || linea.trim().isEmpty()) {
            System.err.println("Animal" + numero + " está vacío");
            return null;
        }

        String[] partes = linea.split(",");
        
        if (partes.length < 7) {
            System.err.println("Animal" + numero + " tiene datos incompletos: " + linea);
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

            // Validar que no haya campos vacíos
            if (nombre.isEmpty() || apodo.isEmpty() || familia.isEmpty() || 
                genero.isEmpty() || especie.isEmpty()) {
                System.err.println("Animal" + numero + " tiene campos vacíos");
                return null;
            }

            return new MascotaVO(apodo, alimentacion, nombre, clasificacion, familia, genero, especie);

        } catch (IllegalArgumentException e) {
            System.err.println("Error al parsear Animal" + numero + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Verifica si una mascota tiene datos incompletos.
     * 
     * @param mascota Mascota a verificar
     * @return true si le falta información
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
