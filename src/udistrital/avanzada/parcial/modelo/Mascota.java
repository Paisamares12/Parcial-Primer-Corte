package udistrital.avanzada.parcial.modelo;

/**
 * Clase Mascota que extiende de Animal e incluye atributos adicionales
 * específicos para una mascota, como apodo y tipo de alimentación.
 * 
 * <p>Esta clase representa a una mascota exótica con características
 * generales heredadas de Animal y propiedades propias para su identificación
 * y cuidado.</p>
 * 
 * @author Paula Martinez
 * @version 1.0
 * @since 2025-10-09
 */
public class Mascota extends Animal {
    
    private String apodo;
    private String alimentacion;

    /**
     * Constructor para crear una Mascota con sus atributos específicos y
     * las propiedades heredadas de la clase Animal.
     * 
     * @param apodo nombre coloquial o apodo de la mascota
     * @param alimentacion tipo o dieta de alimentación de la mascota
     * @param nombre nombre común del animal
     * @param clasificacion clasificación taxonómica del animal
     * @param familia familia taxonómica del animal
     * @param genero género taxonómico del animal
     * @param especie especie específica del animal
     */
    public Mascota(String apodo, String alimentacion, String nombre, String clasificacion, String familia, String genero, String especie) {
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
    public String getAlimentacion() {
        return alimentacion;
    }

    /**
     * Modifica el tipo o dieta de alimentación de la mascota.
     * 
     * @param alimentacion nueva alimentación para la mascota
     */
    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }
    
    @Override
    public String getDescripcion(){
        return "Maldito Bravo";
    }
}
