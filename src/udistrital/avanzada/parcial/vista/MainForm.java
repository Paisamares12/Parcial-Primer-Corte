package udistrital.avanzada.parcial.vista;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Clase MainForm es el panel principal de la ventana principal del proyecto
 *
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-10
 */
public class MainForm extends javax.swing.JPanel {

    private PanelAdicionar adicionar;
    private PanelConsultar consultar;
    private PanelEliminar eliminar;
    private PanelModificar modificar;
    private PanelInicio inicio;

    public MainForm() {
        setLayout(new MigLayout("fill"));
        initPanels();
        mostrarPanelInicio();
    }

    public void mostrarPanelInicio() {
        removeAll();
        add(inicio, "grow");
        revalidate();
        repaint();
    }
    public void mostrarPanelAdicionar() {
        removeAll();
        add(adicionar, "grow");
        revalidate();
        repaint();
    }
    public void mostrarPanelConsultar() {
        removeAll();
        add(consultar, "grow");
        revalidate();
        repaint();
    }
    public void mostrarPanelEliminar() {
        removeAll();
        add(eliminar, "grow");
        revalidate();
        repaint();
    }
    public void mostrarPanelModificar() {
        removeAll();
        add(modificar, "grow");
        revalidate();
        repaint();
    }
    
    private void initPanels() {
        inicio = new PanelInicio();
        adicionar = new PanelAdicionar();
        consultar = new PanelConsultar();
        eliminar = new PanelEliminar();
        modificar = new PanelModificar();
    }

    public PanelAdicionar getPanelAdicionar() {
        return adicionar;
    }

    public PanelConsultar getPanelConsultar() {
        return consultar;
    }

    public PanelInicio getPanelInicio() {
        return inicio;
    }
    public PanelEliminar getPanelEliminar() {
        return eliminar;
    }
    public PanelModificar getPanelModificar() {
        return modificar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 255, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 970, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
