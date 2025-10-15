package udistrital.avanzada.parcial.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Clase {@code PanelConsultar} que representa el panel de la interfaz gráfica
 * encargado de realizar consultas sobre los animales registrados.
 *
 * <p>
 * Este panel permite filtrar la información de las mascotas o animales por
 * distintos criterios como apodo, familia, clasificación o tipo de
 * alimentación. Además, muestra los resultados en una tabla.</p>
 *
 * @author Paula Martínez
 * @version 5.0
 * @since 2025-10-10
 */
public class PanelConsultar extends javax.swing.JPanel {

    /**
     * Constructor del panel de consulta. Inicializa todos los componentes
     * gráficos del panel.
     */
    public PanelConsultar() {
        initComponents();
    }

    /**
     * Obtiene la caja de texto donde se ingresa el apodo del animal.
     *
     * @return campo de texto para el apodo
     */
    public JTextField getCajaApodo() {
        return cajaApodo;
    }

    /**
     * Obtiene la caja de texto donde se ingresa la familia del animal.
     *
     * @return campo de texto para la familia
     */
    public JTextField getCajaFamilia() {
        return cajaFamilia;
    }

    /**
     * Obtiene el combo box que permite seleccionar la clasificación del animal.
     *
     * @return combo box de clasificación
     */
    public JComboBox getComboClasificacion() {
        return comboClasificacion;
    }

    /**
     * Obtiene el combo box que permite seleccionar el tipo de alimentación del
     * animal.
     *
     * @return combo box de alimentación
     */
    public JComboBox getComboAlimentacion() {
        return comboAlimentacion;
    }

    /**
     * Obtiene la tabla donde se muestran los resultados de la consulta.
     *
     * @return tabla de animales
     */
    public JTable getTablaAnimales() {
        return tablaAnimales;
    }

    /**
     * Obtiene el botón que ejecuta la consulta con los filtros seleccionados.
     *
     * @return botón de consulta
     */
    public JButton getBotonConsultar() {
        return botonConsultar;
    }

    /**
     * Inicializa los componentes gráficos del panel, definiendo etiquetas,
     * cajas de texto, combos, tabla y el botón de consulta.
     * <p>
     * Este método es generado automáticamente por el editor de interfaces
     * (NetBeans GUI Builder).
     * </p>
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        lTitulo = new javax.swing.JLabel();
        lApodo = new javax.swing.JLabel();
        cajaApodo = new javax.swing.JTextField();
        lClasificacion = new javax.swing.JLabel();
        comboClasificacion = new javax.swing.JComboBox<>();
        lFamilia = new javax.swing.JLabel();
        cajaFamilia = new javax.swing.JTextField();
        lAlimentacion = new javax.swing.JLabel();
        comboAlimentacion = new javax.swing.JComboBox<>();
        contenedorTabla = new javax.swing.JScrollPane();
        tablaAnimales = new javax.swing.JTable();
        botonConsultar = new javax.swing.JButton();

        lTitulo.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lTitulo.setForeground(new java.awt.Color(2, 68, 129));
        lTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitulo.setText("Consultar");

        lApodo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lApodo.setText("Apodo:");

        lClasificacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lClasificacion.setText("Clasificación:");

        lFamilia.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lFamilia.setText("Familia:");

        lAlimentacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lAlimentacion.setText("Alimentación:");

        tablaAnimales.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        contenedorTabla.setViewportView(tablaAnimales);

        botonConsultar.setBackground(new java.awt.Color(2, 68, 129));
        botonConsultar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonConsultar.setForeground(new java.awt.Color(255, 255, 255));
        botonConsultar.setText("Consultar");
        botonConsultar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 159, 224), null, null));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
                bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(contenedorTabla)
                                        .addGroup(bgLayout.createSequentialGroup()
                                                .addComponent(lApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cajaApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboClasificacion, 0, 104, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cajaFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(373, 373, 373))
        );
        bgLayout.setVerticalGroup(
                bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bgLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(lTitulo)
                                .addGap(24, 24, 24)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lApodo)
                                        .addComponent(cajaApodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lClasificacion)
                                        .addComponent(comboClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lFamilia)
                                        .addComponent(cajaFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lAlimentacion)
                                        .addComponent(comboAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(contenedorTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton botonConsultar;
    private javax.swing.JTextField cajaApodo;
    private javax.swing.JTextField cajaFamilia;
    private javax.swing.JComboBox<String> comboAlimentacion;
    private javax.swing.JComboBox<String> comboClasificacion;
    private javax.swing.JScrollPane contenedorTabla;
    private javax.swing.JLabel lAlimentacion;
    private javax.swing.JLabel lApodo;
    private javax.swing.JLabel lClasificacion;
    private javax.swing.JLabel lFamilia;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JTable tablaAnimales;
    // End of variables declaration//GEN-END:variables
}
