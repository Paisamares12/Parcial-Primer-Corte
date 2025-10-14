/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.parcial.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author paisa
 */
public class PanelModificar extends javax.swing.JPanel {

    public PanelModificar() {
        initComponents();
    }

    public JTextField getCajaApodo() {
        return cajaApodo;
    }

    public JTextField getCajaFamilia() {
        return cajaFamilia;
    }

    public JComboBox getComboClasificacion() {
        return comboClasificacion;
    }

    public JComboBox getComboAlimentacion() {
        return comboAlimentacion;
    }
    
    public JTable getTablaAnimales(){
        return tablaAnimales;
    }
    
    public JButton getBotonModificar(){
        return botonModificar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lTitulo = new javax.swing.JLabel();
        lApodo = new javax.swing.JLabel();
        contenedorTabla = new javax.swing.JScrollPane();
        tablaAnimales = new javax.swing.JTable();
        lClasificacion = new javax.swing.JLabel();
        comboClasificacion = new javax.swing.JComboBox<>();
        lFamilia = new javax.swing.JLabel();
        cajaFamilia = new javax.swing.JTextField();
        lAlimentacion = new javax.swing.JLabel();
        comboAlimentacion = new javax.swing.JComboBox<>();
        botonModificar = new javax.swing.JButton();
        cajaApodo = new javax.swing.JTextField();

        lTitulo.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lTitulo.setForeground(new java.awt.Color(2, 68, 129));
        lTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitulo.setText("Modificar");

        lApodo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lApodo.setText("Apodo:");

        tablaAnimales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        contenedorTabla.setViewportView(tablaAnimales);

        lClasificacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lClasificacion.setText("Clasificación:");

        lFamilia.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lFamilia.setText("Familia:");

        lAlimentacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lAlimentacion.setText("Alimentación:");

        comboAlimentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        botonModificar.setBackground(new java.awt.Color(2, 68, 129));
        botonModificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonModificar.setForeground(new java.awt.Color(255, 255, 255));
        botonModificar.setText("Modificar");
        botonModificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 159, 224), null, null));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(contenedorTabla)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cajaApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboClasificacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(lFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cajaFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(comboAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(358, 358, 358)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lApodo)
                    .addComponent(lClasificacion)
                    .addComponent(comboClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFamilia)
                    .addComponent(cajaFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lAlimentacion)
                    .addComponent(comboAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaApodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(contenedorTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonModificar;
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
