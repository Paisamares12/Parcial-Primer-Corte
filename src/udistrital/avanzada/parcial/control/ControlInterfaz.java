package udistrital.avanzada.parcial.control;
//Importar vistas
import udistrital.avanzada.parcial.vista.VentanaPrincipal;

/**
 * Clase ControlInterfaz que conecta el control con la vista
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-09
 */
public class ControlInterfaz {
    //Otras clases como atributo
    private ControlLogica cLogica;
    private VentanaPrincipal vPrincipal;
    
    public ControlInterfaz(ControlLogica cLogica) {
        this.cLogica = cLogica;
        new VentanaPrincipal(this);
    }
    
}
