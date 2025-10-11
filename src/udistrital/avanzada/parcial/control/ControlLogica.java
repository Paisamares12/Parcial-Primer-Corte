package udistrital.avanzada.parcial.control;
//Importar librerias

import java.util.List;
//Importar clases desde otros paquetes
import udistrital.avanzada.parcial.modelo.DAO.AnimalDAO;
import udistrital.avanzada.parcial.modelo.MascotaVO;

/**
 * Clase ControlLogica que controla todo el aplicativo
 *
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-09
 */
public class ControlLogica {

    private ControlArchivos cArchivos;
    private ControlMascota cMascota;
    private ControlConexion cConexion;
    private ControlInterfaz cInterfaz;

    public ControlLogica() {
        
        this.cMascota = new ControlMascota();
        this.cInterfaz = new ControlInterfaz(this);
    }

    public String[] obtenerClasificaciones() {
        return cMascota.getClasificaciones();
    }

    public String[] obtenerAlimentaciones() {
        return cMascota.getAlimentaciones();
    }

}
