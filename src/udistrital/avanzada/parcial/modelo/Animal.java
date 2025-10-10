package udistrital.avanzada.parcial.modelo;

/**
 * Clase Animal que representa las características básicas de una mascota exótica.
 * 
 * <p>Esta clase incluye atributos como nombre, clasificación, familia, género y especie,
 * y proporciona métodos para acceder y modificar dichos atributos.</p>
 * 
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-09
 */
public abstract class Animal {
    
    private String nombre;
    private String clasificacion;
    private String familia;
    private String genero;
    private String especie;

    /**
     * Constructor para crear un objeto Animal con sus características principales.
     * 
     * @param nombre nombre común del animal
     * @param clasificacion clasificación taxonómica (por ejemplo: mamífero, reptil)
     * @param familia familia a la que pertenece el animal
     * @param genero género taxonómico del animal
     * @param especie especie específica del animal
     */
    public Animal(String nombre, String clasificacion, String familia, String genero, String especie) {
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.familia = familia;
        this.genero = genero;
        this.especie = especie;
    }

    /**
     * Obtiene la clasificación del animal.
     * 
     * @return la clasificación taxonómica del animal
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * Modifica la clasificación del animal.
     * 
     * @param clasificacion nueva clasificación taxonómica
     */
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * Obtiene la familia del animal.
     * 
     * @return la familia taxonómica del animal
     */
    public String getFamilia() {
        return familia;
    }

    /**
     * Modifica la familia del animal.
     * 
     * @param familia nueva familia taxonómica
     */
    public void setFamilia(String familia) {
        this.familia = familia;
    }

    /**
     * Obtiene el género del animal.
     * 
     * @return el género taxonómico del animal
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Modifica el género del animal.
     * 
     * @param genero nuevo género taxonómico
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la especie del animal.
     * 
     * @return la especie específica del animal
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Modifica la especie del animal.
     * 
     * @param especie nueva especie específica
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    /**
     * Obtiene el nombre común del animal.
     * 
     * @return el nombre común
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Modifica el nombre común del animal.
     * 
     * @param nombre nuevo nombre común
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public abstract String getDescripcion();
}
