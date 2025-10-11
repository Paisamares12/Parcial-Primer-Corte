package udistrital.avanzada.parcial.control;
//Importar vistas

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import udistrital.avanzada.parcial.vista.VentanaPrincipal;

/**
 * Clase ControlInterfaz que conecta el control con la vista
 *
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-09
 */
public class ControlInterfaz implements ActionListener {

    //Otras clases como atributo
    private ControlLogica cLogica;
    private VentanaPrincipal vPrincipal;

    private static String panelActual = "Inicio";

    public ControlInterfaz(ControlLogica cLogica) {
        this.cLogica = cLogica;
        this.vPrincipal = new VentanaPrincipal(this);

        this.vPrincipal.setVisible(true);
        configurarVentanas();

        this.vPrincipal.getPanelMenu().getBotonConsultar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonInicio().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonAdicionar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonEliminar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonModificar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonLimpiar().addActionListener(this);
    }

    public void configurarVentanas() {
        datoRandom();
        inicializarCombos();
    }

    public void inicializarCombos() {
        // Panel Adicionar
        for (String c : cLogica.obtenerClasificaciones()) {
            vPrincipal.getPanelMain().getPanelAdicionar().getComboClasificacion().addItem(c);
        }
        for (String a : cLogica.obtenerAlimentaciones()) {
            vPrincipal.getPanelMain().getPanelAdicionar().getComboAlimentacion().addItem(a);
        }

        // Panel Consultar
        for (String c : cLogica.obtenerClasificaciones()) {
            vPrincipal.getPanelMain().getPanelConsultar().getComboClasificacion().addItem(c);
        }
        for (String a : cLogica.obtenerAlimentaciones()) {
            vPrincipal.getPanelMain().getPanelConsultar().getComboAlimentacion().addItem(a);
        }

        // Panel Modificar
        for (String c : cLogica.obtenerClasificaciones()) {
            vPrincipal.getPanelMain().getPanelModificar().getComboClasificacion().addItem(c);
        }
        for (String a : cLogica.obtenerAlimentaciones()) {
            vPrincipal.getPanelMain().getPanelModificar().getComboAlimentacion().addItem(a);
        }
    }

    public void datoRandom() {
        String[] tips = {
            "A los camaleones les gusta la luz UVB para su salud.</html>",
            "Los hurones pueden dormir hasta 18 horas al día.</html>",
            "Un loro puede imitar tu voz si lo entrenas con paciencia.</html>",
            "Revisa siempre la alimentación de tus mascotas exóticas.</html>",
            "Las serpientes necesitan humedad adecuada según su especie.</html>",
            "Los geckos nocturnos son más activos de noche y necesitan poca luz.</html>",
            "Nunca uses tierra de maceta común para reptiles,puede ser peligrosa.</html>",
            "Las iguanas necesitan una dieta rica en vegetales y calcio.</html>",
            "A los erizos les encanta explorar, pero necesitan un espacio seguro.</html>",
            "Lava siempre los recipientes de comida y agua de tus mascotas.</html>",
            "Los loros necesitan interacción diaria para no aburrirse.</html>",
            "Algunos roedores exóticos son sensibles a cambios bruscos de temperatura.</html>",
            "Los camaleones no deben compartir terrario con otros reptiles; se estresan.</html>",
            "Usa siempre guantes al manipular ciertas serpientes para evitar estrés o mordidas.</html>",
            "Observa el comportamiento diario de tu mascota; cambios sutiles pueden indicar enfermedad.</html>"
        };
        Random rand = new Random();
        String tipLabel = "<html><span style=\"color:blue; font-weight:bold;\">Tip del día: </span>" + tips[rand.nextInt(tips.length)];
        this.vPrincipal.getPanelMain().getPanelInicio().getlTips().setText(tipLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonConsultar()) {
            this.vPrincipal.getPanelMain().mostrarPanelConsultar();
            panelActual = "Consultar";
        }
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonInicio()) {
            this.vPrincipal.getPanelMain().mostrarPanelInicio();
            panelActual = "Inicio";
        }
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonAdicionar()) {
            this.vPrincipal.getPanelMain().mostrarPanelAdicionar();
            panelActual = "Adicionar";
        }
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonModificar()) {
            this.vPrincipal.getPanelMain().mostrarPanelModificar();
            panelActual = "Modificar";
        }
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonEliminar()) {
            this.vPrincipal.getPanelMain().mostrarPanelEliminar();
            panelActual = "Eliminar";
        }
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonLimpiar()) {
            if (panelActual.equals("Consultar")) {
                this.vPrincipal.getPanelMain().getPanelConsultar().getCajaApodo().setText("");
                this.vPrincipal.getPanelMain().getPanelConsultar().getCajaFamilia().setText("");
                this.vPrincipal.getPanelMain().getPanelConsultar().getComboClasificacion().setSelectedIndex(0);
                this.vPrincipal.getPanelMain().getPanelConsultar().getComboAlimentacion().setSelectedIndex(0);
            } else if (panelActual.equals("Adicionar")) {
                this.vPrincipal.getPanelMain().getPanelAdicionar().getCajaApodo().setText("");
                this.vPrincipal.getPanelMain().getPanelAdicionar().getCajaEspecie().setText("");
                this.vPrincipal.getPanelMain().getPanelAdicionar().getCajaFamilia().setText("");
                this.vPrincipal.getPanelMain().getPanelAdicionar().getCajaGenero().setText("");
                this.vPrincipal.getPanelMain().getPanelAdicionar().getCajaNombre().setText("");
                this.vPrincipal.getPanelMain().getPanelAdicionar().getComboClasificacion().setSelectedIndex(0);
                this.vPrincipal.getPanelMain().getPanelAdicionar().getComboAlimentacion().setSelectedIndex(0);
            } else if (panelActual.equals("Modificar")) {
                this.vPrincipal.getPanelMain().getPanelModificar().getCajaApodo().setText("");
                this.vPrincipal.getPanelMain().getPanelModificar().getCajaFamilia().setText("");
                this.vPrincipal.getPanelMain().getPanelModificar().getComboClasificacion().setSelectedIndex(0);
                this.vPrincipal.getPanelMain().getPanelModificar().getComboAlimentacion().setSelectedIndex(0);
            }
        }
    }

}
