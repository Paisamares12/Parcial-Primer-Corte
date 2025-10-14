package udistrital.avanzada.parcial.modelo.persistencia;

import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.dao.AnimalDAO;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase InicializadorBD
 * 
 * Se encarga de cargar las mascotas del archivo properties
 * a la base de datos al iniciar la aplicación.
 * 
 * @author Juan Sebastián Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class InicializadorBD {

    private CargadorPropiedades cargador;
    private AnimalDAO dao;

    public InicializadorBD() {
        this.cargador = new CargadorPropiedades();
        this.dao = new AnimalDAO();
    }

    /**
     * Carga las mascotas del archivo properties a la base de datos.
     * Solo inserta las que no existen ya.
     * 
     * @return Lista de mascotas que tenían datos incompletos
     */
    public List<MascotaVO> inicializarDatos() {
        List<MascotaVO> mascotasIncompletas = null;

        try {
            List<MascotaVO> mascotasCargadas = cargador.cargarMascotas();
            mascotasIncompletas = new java.util.ArrayList<>();

            for (MascotaVO mascota : mascotasCargadas) {
                // Verificar si tiene datos completos
                if (cargador.tienesDatosIncompletos(mascota)) {
                    mascotasIncompletas.add(mascota);
                    System.out.println("Mascota con datos incompletos: " + mascota.getApodo());
                    continue;
                }

                // Verificar si ya existe en la BD
                try {
                    if (!dao.existeMascotaCompleta(mascota)) {
                        dao.insertarMascota(mascota);
                        System.out.println("Mascota cargada: " + mascota.getApodo());
                    } else {
                        System.out.println("Mascota ya existe: " + mascota.getApodo());
                    }
                } catch (ConexionException e) {
                    System.err.println("Error al insertar " + mascota.getApodo() + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Error al cargar propiedades: " + e.getMessage());
        }

        return mascotasIncompletas;
    }
}