package udistrital.avanzada.parcial.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Panel para modificar mascotas.
 * Permite modificar: nombre, clasificación y alimentación.
 * NO permite modificar: familia, género, especie.
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

    public JTextField getCajaNombre() {
        return cajaNombre;
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
        lAlimentacion = new javax.swing.JLabel();
        comboAlimentacion = new javax.swing.JComboBox<>();
        botonModificar = new javax.swing.JButton();
        cajaApodo = new javax.swing.JTextField();
        lNombre = new javax.swing.JLabel();
        cajaNombre = new javax.swing.JTextField();
        lInstruccion = new javax.swing.JLabel();

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

        lAlimentacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lAlimentacion.setText("Alimentación:");

        comboAlimentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        botonModificar.setBackground(new java.awt.Color(2, 68, 129));
        botonModificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonModificar.setForeground(new java.awt.Color(255, 255, 255));
        botonModificar.setText("Modificar");
        botonModificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 159, 224), null, null));

        lNombre.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lNombre.setText("Nombre:");

        lInstruccion.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        lInstruccion.setForeground(new java.awt.Color(0, 159, 224));
        lInstruccion.setText("Seleccione una mascota de la tabla y modifique los campos deseados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorTabla, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboClasificacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(358, 358, 358))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lInstruccion)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lInstruccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lApodo)
                    .addComponent(cajaApodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNombre)
                    .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lClasificacion)
                    .addComponent(comboClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lAlimentacion)
                    .addComponent(comboAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(contenedorTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonModificar;
    private javax.swing.JTextField cajaApodo;
    private javax.swing.JTextField cajaNombre;
    private javax.swing.JComboBox<String> comboAlimentacion;
    private javax.swing.JComboBox<String> comboClasificacion;
    private javax.swing.JScrollPane contenedorTabla;
    private javax.swing.JLabel lAlimentacion;
    private javax.swing.JLabel lApodo;
    private javax.swing.JLabel lClasificacion;
    private javax.swing.JLabel lInstruccion;
    private javax.swing.JLabel lNombre;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JTable tablaAnimales;
    // End of variables declaration//GEN-END:variables
}