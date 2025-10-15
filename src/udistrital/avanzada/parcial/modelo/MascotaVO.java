package udistrital.avanzada.parcial.modelo;

import java.io.Serializable;

/**
 * Clase Mascota que extiende de Animal e incluye atributos adicionales
 * específicos para una mascota, como apodo y tipo de alimentación.
 *
 * <p>
 * Esta clase representa a una mascota exótica con características generales
 * heredadas de AnimalVO y propiedades propias para su identificación y
 * cuidado. Implementa Serializable para permitir su persistencia.
 * </p>
 * 
 * <p>
 * El campo alimentacion se marca como transient para que NO se serialice
 * cuando se envían los datos al Instituto Distrital de Protección y 
 * Bienestar Animal (IDPYBA) según los requerimientos.
 * </p>
 * 
 * Modificado y documentado: Juan Ariza
 *
 * @author Paula Martinez
 * @version 4.0
 * @since 2025-10-09
 */
public class MascotaVO extends AnimalVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String apodo;
    
    /**
     * Tipo de alimentación de la mascota.
     * Se marca como transient para que NO se incluya al serializar
     * (requerimiento para envío al IDPYBA).
     */
    private transient Alimentacion alimentacion;

    /**
     * Constructor para crear una Mascota con sus atributos específicos y las
     * propiedades heredadas de la clase AnimalVO.
     *
     * @param apodo nombre coloquial o apodo de la mascota
     * @param alimentacion tipo o dieta de alimentación de la mascota
     * @param nombre nombre común del animal
     * @param clasificacion clasificación taxonómica del animal
     * @param familia familia taxonómica del animal
     * @param genero género taxonómico del animal
     * @param especie especie específica del animal
     */
    public MascotaVO(String apodo, Alimentacion alimentacion, String nombre, Clasificacion clasificacion, String familia, String genero, String especie) {
        super(nombre, clasificacion, familia, genero, especie);
        this.apodo = apodo;
        this.alimentacion = alimentacion;
    }

    /**
     * Obtiene el apodo o nombre coloquial de la mascota.
     *
     * @return el apodo de la mascota
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * Modifica el apodo o nombre coloquial de la mascota.
     *
     * @param apodo nuevo apodo para la mascota
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    /**
     * Obtiene el tipo o dieta de alimentación de la mascota.
     *
     * @return la alimentación de la mascota
     */
    public Alimentacion getAlimentacion() {
        return alimentacion;
    }

    /**
     * Modifica el tipo o dieta de alimentación de la mascota.
     *
     * @param alimentacion nueva alimentación para la mascota
     */
    public void setAlimentacion(Alimentacion alimentacion) {
        this.alimentacion = alimentacion;
    }
}