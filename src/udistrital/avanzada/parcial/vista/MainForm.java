package udistrital.avanzada.parcial.vista;

import java.awt.CardLayout;

/**
 * Clase {@code MainForm} representa el panel principal de la aplicación.
 *
 * <p>
 * Este panel funciona como un contenedor que administra las diferentes vistas o
 * secciones del programa (Inicio, Adicionar, Consultar, Eliminar y Modificar)
 * utilizando un {@link java.awt.CardLayout}. De esta manera, solo se muestra un
 * panel a la vez y se puede cambiar fácilmente entre ellos.</p>
 *
 * <p>
 * El uso de {@code CardLayout} permite una navegación sencilla y ordenada
 * dentro de la interfaz gráfica, eliminando la necesidad de layouts externos
 * como MigLayout.</p>
 *
 * @author Paula Martínez
 * @version 5.0
 * @since 2025-10-10
 */
public class MainForm extends javax.swing.JPanel {

    /**
     * Panel que muestra la vista de inicio.
     */
    private PanelInicio inicio;

    /**
     * Panel que permite adicionar nuevas mascotas.
     */
    private PanelAdicionar adicionar;

    /**
     * Panel que permite consultar mascotas registradas.
     */
    private PanelConsultar consultar;

    /**
     * Panel que permite eliminar mascotas.
     */
    private PanelEliminar eliminar;

    /**
     * Panel que permite modificar los datos de una mascota existente.
     */
    private PanelModificar modificar;

    /**
     * Layout que controla qué panel se muestra actualmente.
     */
    private CardLayout layout;

    /**
     * Constructor de la clase {@code MainForm}.
     *
     * <p>
     * Inicializa el layout principal como {@code CardLayout}, crea las
     * instancias de todos los subpaneles y muestra el panel de inicio por
     * defecto.</p>
     */
    public MainForm() {
        layout = new CardLayout(); // Se usa CardLayout para manejar las "pantallas"
        setLayout(layout);
        initPanels();
        mostrarPanelInicio(); // Muestra el panel de inicio al abrir la aplicación
    }

    /**
     * Inicializa los diferentes subpaneles del formulario principal y los añade
     * al {@code CardLayout} con un nombre identificador.
     */
    private void initPanels() {
        inicio = new PanelInicio();
        adicionar = new PanelAdicionar();
        consultar = new PanelConsultar();
        eliminar = new PanelEliminar();
        modificar = new PanelModificar();
        
        // Se añaden al contenedor con un identificador único
        add(inicio, "Inicio");
        add(adicionar, "Adicionar");
        add(consultar, "Consultar");
        add(eliminar, "Eliminar");
        add(modificar, "Modificar");
    }

    // ---------------- MÉTODOS PARA MOSTRAR LOS PANELES ----------------
    /**
     * Muestra el panel de inicio.
     */
    public void mostrarPanelInicio() {
        layout.show(this, "Inicio");
    }

    /**
     * Muestra el panel para adicionar una nueva mascota.
     */
    public void mostrarPanelAdicionar() {
        layout.show(this, "Adicionar");
    }

    /**
     * Muestra el panel de consulta de mascotas.
     */
    public void mostrarPanelConsultar() {
        layout.show(this, "Consultar");
    }

    /**
     * Muestra el panel de eliminación de mascotas.
     */
    public void mostrarPanelEliminar() {
        layout.show(this, "Eliminar");
    }

    /**
     * Muestra el panel para modificar la información de una mascota.
     */
    public void mostrarPanelModificar() {
        layout.show(this, "Modificar");
    }

    // ---------------- MÉTODOS GETTERS ----------------
    /**
     * Obtiene el panel de adición.
     *
     * @return instancia de {@link PanelAdicionar}
     */
    public PanelAdicionar getPanelAdicionar() {
        return adicionar;
    }

    /**
     * Obtiene el panel de consulta.
     *
     * @return instancia de {@link PanelConsultar}
     */
    public PanelConsultar getPanelConsultar() {
        return consultar;
    }

    /**
     * Obtiene el panel de inicio.
     *
     * @return instancia de {@link PanelInicio}
     */
    public PanelInicio getPanelInicio() {
        return inicio;
    }

    /**
     * Obtiene el panel de eliminación.
     *
     * @return instancia de {@link PanelEliminar}
     */
    public PanelEliminar getPanelEliminar() {
        return eliminar;
    }

    /**
     * Obtiene el panel de modificación.
     *
     * @return instancia de {@link PanelModificar}
     */
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
