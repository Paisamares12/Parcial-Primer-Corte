package udistrital.avanzada.parcial.control;
//Importar vistas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import udistrital.avanzada.parcial.vista.VentanaPrincipal;

/**
 * Clase ControlInterfaz que conecta el control con la vista
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-09
 */
public class ControlInterfaz implements ActionListener {
    //Otras clases como atributo
    private ControlLogica cLogica;
    private VentanaPrincipal vPrincipal;
    
    public ControlInterfaz(ControlLogica cLogica) {
        this.cLogica = cLogica;
        this.vPrincipal = new VentanaPrincipal(this);
        
        this.vPrincipal.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonConsultar()){
            
        }
    }
    
}
