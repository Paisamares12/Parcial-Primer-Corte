package udistrital.avanzada.parcial.vista;

import javax.swing.JButton;

/**
 * JPanel Menu es el Menu de la ventana principal del proyecto
 *
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-10
 */
public class Menu extends javax.swing.JPanel {
    /**
     * Constructor del panel menu
     */
    public Menu() {
        initComponents();
    }
    
        /**
     * Obtiene el botón que permite adicionar una nueva mascota o registro.
     * 
     * @return el botón de adicionar
     */
    public JButton getBotonAdicionar() {
        return botonAdicionar;
    }

    /**
     * Obtiene el botón que permite consultar las mascotas registradas.
     * 
     * @return el botón de consultar
     */
    public JButton getBotonConsultar() {
        return botonConsultar;
    }

    /**
     * Obtiene el botón que permite eliminar una mascota del registro.
     * 
     * @return el botón de eliminar
     */
    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    /**
     * Obtiene el botón que redirige al panel o vista de inicio.
     * 
     * @return el botón de inicio
     */
    public JButton getBotonInicio() {
        return botonInicio;
    }

    /**
     * Obtiene el botón que permite limpiar los campos del formulario.
     * 
     * @return el botón de limpiar
     */
    public JButton getBotonLimpiar() {
        return botonLimpiar;
    }

    /**
     * Obtiene el botón que permite modificar los datos de una mascota existente.
     * 
     * @return el botón de modificar
     */
    public JButton getBotonModificar() {
        return botonModificar;
    }

    /**
     * Obtiene el botón que permite cerrar la aplicación o salir del sistema.
     * 
     * @return el botón de salir
     */
    public JButton getBotonSalir() {
        return botonSalir;
    }

    /**
     * Obtiene el botón que permite serializar los datos de las mascotas,
     * guardándolos en un archivo o formato persistente.
     * 
     * @return el botón de serializar
     */
    public JButton getBotonSerializar() {
        return botonSerializar;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonInicio = new javax.swing.JButton();
        botonAdicionar = new javax.swing.JButton();
        botonConsultar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonSerializar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setBackground(new java.awt.Color(23, 97, 235));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonInicio.setBackground(new java.awt.Color(23, 97, 235));
        botonInicio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonInicio.setForeground(new java.awt.Color(255, 255, 255));
        botonInicio.setText("Inicio\n");
        botonInicio.setToolTipText("");
        botonInicio.setBorder(null);
        add(botonInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 230, 70));

        botonAdicionar.setBackground(new java.awt.Color(23, 97, 235));
        botonAdicionar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        botonAdicionar.setText("Adicionar");
        botonAdicionar.setToolTipText("");
        botonAdicionar.setBorder(null);
        add(botonAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 108, 230, 70));

        botonConsultar.setBackground(new java.awt.Color(23, 97, 235));
        botonConsultar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonConsultar.setForeground(new java.awt.Color(255, 255, 255));
        botonConsultar.setText("Consultar");
        botonConsultar.setToolTipText("");
        botonConsultar.setBorder(null);
        add(botonConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 178, 230, 70));

        botonEliminar.setBackground(new java.awt.Color(23, 97, 235));
        botonEliminar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminar.setText("Eliminar");
        botonEliminar.setToolTipText("");
        botonEliminar.setBorder(null);
        add(botonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 248, 230, 70));

        botonModificar.setBackground(new java.awt.Color(23, 97, 235));
        botonModificar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonModificar.setForeground(new java.awt.Color(255, 255, 255));
        botonModificar.setText("Modificar");
        botonModificar.setToolTipText("");
        botonModificar.setBorder(null);
        add(botonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 318, 230, 70));

        botonSerializar.setBackground(new java.awt.Color(23, 97, 235));
        botonSerializar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonSerializar.setForeground(new java.awt.Color(255, 255, 255));
        botonSerializar.setText("Serializar");
        botonSerializar.setToolTipText("");
        botonSerializar.setBorder(null);
        add(botonSerializar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 388, 230, 70));

        botonLimpiar.setBackground(new java.awt.Color(23, 97, 235));
        botonLimpiar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        botonLimpiar.setText("Limpiar");
        botonLimpiar.setToolTipText("");
        botonLimpiar.setBorder(null);
        add(botonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 458, 230, 70));

        botonSalir.setBackground(new java.awt.Color(23, 97, 235));
        botonSalir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonSalir.setForeground(new java.awt.Color(255, 0, 0));
        botonSalir.setText("Salir");
        botonSalir.setToolTipText("");
        botonSalir.setBorder(null);
        add(botonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 528, 230, 70));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAdicionar;
    private javax.swing.JButton botonConsultar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonInicio;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton botonSerializar;
    // End of variables declaration//GEN-END:variables
}
