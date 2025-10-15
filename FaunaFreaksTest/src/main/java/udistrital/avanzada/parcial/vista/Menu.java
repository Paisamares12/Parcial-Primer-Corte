package udistrital.avanzada.parcial.vista;

import javax.swing.JButton;

/**
 * Clase {@code Menu} representa el panel lateral de navegación de la ventana
 * principal del proyecto.
 *
 * <p>
 * Este menú contiene los botones principales que permiten al usuario acceder a
 * las diferentes secciones del sistema: Inicio, Adicionar, Consultar, Eliminar,
 * Modificar, Limpiar y Salir. Cada botón puede ser escuchado desde el
 * controlador para realizar la acción correspondiente.</p>
 *
 * <p>
 * Su diseño visual mantiene la coherencia de la interfaz gráfica, con un fondo
 * azul institucional y botones blancos, distribuidos verticalmente.</p>
 *
 * @author Paula Martínez
 * @version 5.0
 * @since 2025-10-10
 */
public class Menu extends javax.swing.JPanel {

    /**
     * Constructor del panel {@code Menu}.
     *
     * <p>
     * Inicializa los componentes gráficos del panel (botones y diseño). Este
     * panel actúa como el menú lateral del sistema.</p>
     */
    public Menu() {
        initComponents();
    }

    // ---------------- MÉTODOS GETTERS ----------------
    /**
     * Obtiene el botón que permite adicionar una nueva mascota o registro.
     *
     * @return el botón {@code Adicionar}
     */
    public JButton getBotonAdicionar() {
        return botonAdicionar;
    }

    /**
     * Obtiene el botón que permite consultar las mascotas registradas.
     *
     * @return el botón {@code Consultar}
     */
    public JButton getBotonConsultar() {
        return botonConsultar;
    }

    /**
     * Obtiene el botón que permite eliminar una mascota del registro.
     *
     * @return el botón {@code Eliminar}
     */
    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    /**
     * Obtiene el botón que redirige al panel o vista de inicio.
     *
     * @return el botón {@code Inicio}
     */
    public JButton getBotonInicio() {
        return botonInicio;
    }

    /**
     * Obtiene el botón que permite limpiar los campos del formulario activo.
     *
     * @return el botón {@code Limpiar}
     */
    public JButton getBotonLimpiar() {
        return botonLimpiar;
    }

    /**
     * Obtiene el botón que permite modificar los datos de una mascota
     * existente.
     *
     * @return el botón {@code Modificar}
     */
    public JButton getBotonModificar() {
        return botonModificar;
    }

    /**
     * Obtiene el botón que permite cerrar la aplicación o salir del sistema.
     *
     * @return el botón {@code Salir}
     */
    public JButton getBotonSalir() {
        return botonSalir;
    }
    
    // ---------------- CÓDIGO GENERADO AUTOMÁTICAMENTE POR EL FORM EDITOR ----------------
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonInicio = new javax.swing.JButton();
        botonAdicionar = new javax.swing.JButton();
        botonConsultar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setBackground(new java.awt.Color(2, 68, 129));

        botonInicio.setBackground(new java.awt.Color(2, 68, 129));
        botonInicio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonInicio.setForeground(new java.awt.Color(255, 255, 255));
        botonInicio.setText("Inicio\n");
        botonInicio.setToolTipText("");
        botonInicio.setBorder(null);

        botonAdicionar.setBackground(new java.awt.Color(2, 68, 129));
        botonAdicionar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        botonAdicionar.setText("Adicionar");
        botonAdicionar.setToolTipText("");
        botonAdicionar.setBorder(null);

        botonConsultar.setBackground(new java.awt.Color(2, 68, 129));
        botonConsultar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonConsultar.setForeground(new java.awt.Color(255, 255, 255));
        botonConsultar.setText("Consultar");
        botonConsultar.setToolTipText("");
        botonConsultar.setBorder(null);

        botonEliminar.setBackground(new java.awt.Color(2, 68, 129));
        botonEliminar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminar.setText("Eliminar");
        botonEliminar.setToolTipText("");
        botonEliminar.setBorder(null);

        botonModificar.setBackground(new java.awt.Color(2, 68, 129));
        botonModificar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonModificar.setForeground(new java.awt.Color(255, 255, 255));
        botonModificar.setText("Modificar");
        botonModificar.setToolTipText("");
        botonModificar.setBorder(null);

        botonLimpiar.setBackground(new java.awt.Color(2, 68, 129));
        botonLimpiar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        botonLimpiar.setText("Limpiar");
        botonLimpiar.setToolTipText("");
        botonLimpiar.setBorder(null);

        botonSalir.setBackground(new java.awt.Color(2, 68, 129));
        botonSalir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botonSalir.setForeground(new java.awt.Color(255, 0, 0));
        botonSalir.setText("Salir");
        botonSalir.setToolTipText("");
        botonSalir.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(botonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(botonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(botonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(botonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(botonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(botonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAdicionar;
    private javax.swing.JButton botonConsultar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonInicio;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonSalir;
    // End of variables declaration//GEN-END:variables
}
