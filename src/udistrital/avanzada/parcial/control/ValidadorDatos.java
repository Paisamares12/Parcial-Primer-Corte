package udistrital.avanzada.parcial.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;

/**
 * Clase ValidadorDatos
 * 
 * <p>Encargada de verificar la integridad de los datos cargados
 * desde archivos o fuentes externas. Identifica mascotas con 
 * información incompleta y permite solicitar su corrección.</p>
 * 
 * <p>Implementa el principio SRP (Responsabilidad Única): 
 * se dedica exclusivamente a validar los datos.</p>
 * 
 * 
 * @author Juan Sebastián Bravo
 * @version 1.0
 * @since 2025-10-14
 */
public class ValidadorDatos {

    /**
     * Verifica si una mascota tiene campos faltantes o nulos.
     * 
     * @param mascota la mascota a validar
     * @return true si tiene información incompleta, false si está completa
     */
    public static boolean tieneDatosIncompletos(MascotaVO mascota) {
        return mascota == null
                || esVacio(mascota.getApodo())
                || mascota.getAlimentacion() == null
                || esVacio(mascota.getNombre())
                || mascota.getClasificacion() == null
                || esVacio(mascota.getFamilia())
                || esVacio(mascota.getGenero())
                || esVacio(mascota.getEspecie());
    }

    /**
     * Retorna una lista con todas las mascotas que tienen información incompleta.
     * 
     * @param lista lista completa de mascotas
     * @return lista con mascotas incompletas
     */
    public static List<MascotaVO> filtrarIncompletas(List<MascotaVO> lista) {
        List<MascotaVO> incompletas = new ArrayList<>();
        if (lista != null) {
            for (MascotaVO m : lista) {
                if (tieneDatosIncompletos(m)) {
                    incompletas.add(m);
                }
            }
        }
        return incompletas;
    }

    /**
     * Elimina del listado aquellas mascotas con información incompleta.
     * 
     * @param lista lista completa de mascotas
     * @return número de registros eliminados
     */
    public static int limpiarIncompletas(List<MascotaVO> lista) {
        if (lista == null) return 0;

        int contador = 0;
        Iterator<MascotaVO> it = lista.iterator();
        while (it.hasNext()) {
            MascotaVO m = it.next();
            if (tieneDatosIncompletos(m)) {
                it.remove();
                contador++;
            }
        }
        return contador;
    }

    // -------------------------------------------------------------------------
    // MÉTODOS PRIVADOS AUXILIARES
    // -------------------------------------------------------------------------
    private static boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}

