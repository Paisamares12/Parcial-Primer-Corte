package udistrital.avanzada.parcial.vista;

import javax.swing.JButton;
import javax.swing.JTable;

/**
 * Clase PanelEliminar
 *
 * Representa el panel de la interfaz gráfica donde el usuario puede visualizar
 * las mascotas registradas y seleccionar una para eliminarla del sistema.
 *
 * Incluye una tabla con la información de los animales y un botón para ejecutar
 * la acción de eliminación. Este panel se utiliza dentro del contenedor
 * principal {@link MainForm}.
 *
 * @author Paula Martínez
 * @version 5.0
 * @since 2025-10-10
 */
public class PanelEliminar extends javax.swing.JPanel {

    /**
     * Constructor del panel PanelEliminar. Inicializa los componentes gráficos
     * del panel.
     */
    public PanelEliminar() {
        initComponents();
    }

    /**
     * Retorna la tabla donde se muestran los animales registrados.
     *
     * @return JTable que contiene los datos de las mascotas.
     */
    public JTable getTablaAnimales() {
        return tablaAnimales;
    }

    /**
     * Retorna el botón utilizado para eliminar una mascota seleccionada.
     *
     * @return JButton con la acción de eliminación.
     */
    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    /**
     * Inicializa y organiza los componentes gráficos del panel.
     *
     * Este método es generado automáticamente por el editor de formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        lTitulo = new javax.swing.JLabel();
        lTextoExtra = new javax.swing.JLabel();
        botonEliminar = new javax.swing.JButton();
        contenedorTabla = new javax.swing.JScrollPane();
        tablaAnimales = new javax.swing.JTable();

        lTitulo.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lTitulo.setForeground(new java.awt.Color(2, 68, 129));
        lTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitulo.setText("Eliminar");

        lTextoExtra.setBackground(new java.awt.Color(2, 160, 225));
        lTextoExtra.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        lTextoExtra.setForeground(new java.awt.Color(0, 159, 224));
        lTextoExtra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTextoExtra.setText("Revise bien antes de eliminar la mascota");

        botonEliminar.setBackground(new java.awt.Color(2, 68, 129));
        botonEliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminar.setText("Eliminar");
        botonEliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 159, 224), null, null));

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

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
                bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bgLayout.createSequentialGroup()
                                .addGap(288, 288, 288)
                                .addComponent(lTextoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(bgLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE))
                                        .addGroup(bgLayout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(contenedorTabla, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(358, 358, 358)))))
                                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
                bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bgLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(lTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(lTextoExtra)
                                .addGap(18, 18, 18)
                                .addComponent(contenedorTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
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
    private javax.swing.JButton botonEliminar;
    private javax.swing.JScrollPane contenedorTabla;
    private javax.swing.JLabel lTextoExtra;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JTable tablaAnimales;
    // End of variables declaration//GEN-END:variables
}
