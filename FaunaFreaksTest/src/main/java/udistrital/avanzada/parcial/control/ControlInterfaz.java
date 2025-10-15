package udistrital.avanzada.parcial.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.Clasificacion;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.vista.VentanaPrincipal;

/**
 * Clase ControlInterfaz que conecta el control con la vista.
 * 
 * <p>
 * Implementa ActionListener para manejar todos los eventos de la interfaz
 * gráfica. Coordina las acciones del usuario con la lógica del negocio
 * sin usar diálogos emergentes (JOptionPane).
 * 
 * Modificado y documentado: Juan Ariza y Juan Sebastian Bravo Rojas
 * 
 * (Se agregaron las relaciones entre las nuevas funcionalidades de los controladores y la vista)
 * </p>
 *
 * @author Paula Martínez
 * @version 4.0
 * @since 2025-10-09
 */
public class ControlInterfaz implements ActionListener {

    private ControlLogica cLogica;
    private VentanaPrincipal vPrincipal;
    private static String panelActual = "Inicio";

    /**
     * Constructor que inicializa la interfaz y configura los eventos.
     * 
     * @param cLogica controlador de lógica del negocio
     */
    public ControlInterfaz(ControlLogica cLogica) {
        this.cLogica = cLogica;
        this.vPrincipal = new VentanaPrincipal(this);

        this.vPrincipal.setVisible(true);
        configurarVentanas();
        conectarEventos();
        configurarListenersTablas();
        verificarInicializacion();
    }

    /**
     * Configura las ventanas iniciales (combos y tips).
     */
    public void configurarVentanas() {
        datoRandom();
        inicializarCombos();
    }

    /**
     * Conecta todos los eventos de botones con sus listeners.
     */
    private void conectarEventos() {
        // Botones del menú (navegación)
        this.vPrincipal.getPanelMenu().getBotonConsultar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonInicio().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonAdicionar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonEliminar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonModificar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonLimpiar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonSalir().addActionListener(this);

        // Botones de acción en cada panel
        this.vPrincipal.getPanelMain().getPanelAdicionar().getBotonAdicionar().addActionListener(this);
        this.vPrincipal.getPanelMain().getPanelConsultar().getBotonConsultar().addActionListener(this);
        this.vPrincipal.getPanelMain().getPanelModificar().getBotonModificar().addActionListener(this);
        this.vPrincipal.getPanelMain().getPanelEliminar().getBotonEliminar().addActionListener(this);
    }

    /**
     * Configura los listeners de selección en las tablas.
     */
    private void configurarListenersTablas() {
        // Listener para tabla de modificar - carga datos al seleccionar fila
        this.vPrincipal.getPanelMain().getPanelModificar().getTablaAnimales()
            .getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        cargarDatosEnModificar();
                    }
                }
            });
    }

    /**
     * Carga los datos de la fila seleccionada en los campos de modificar.
     */
    private void cargarDatosEnModificar() {
        int filaSeleccionada = vPrincipal.getPanelMain().getPanelModificar().getTablaAnimales().getSelectedRow();
        
        if (filaSeleccionada >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) vPrincipal.getPanelMain().getPanelModificar().getTablaAnimales().getModel();
            
            String apodo = (String) modelo.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
            String clasificacion = (String) modelo.getValueAt(filaSeleccionada, 2);
            String alimentacion = (String) modelo.getValueAt(filaSeleccionada, 6);
            
            vPrincipal.getPanelMain().getPanelModificar().getCajaApodo().setText(apodo);
            vPrincipal.getPanelMain().getPanelModificar().getCajaNombre().setText(nombre);
            vPrincipal.getPanelMain().getPanelModificar().getComboClasificacion().setSelectedItem(clasificacion);
            vPrincipal.getPanelMain().getPanelModificar().getComboAlimentacion().setSelectedItem(alimentacion);
        }
    }

    /**
     * Inicializa los combos de clasificación y alimentación en todos los paneles.
     */
    public void inicializarCombos() {
        // Panel Adicionar
        vPrincipal.getPanelMain().getPanelAdicionar().getComboClasificacion().removeAllItems();
        vPrincipal.getPanelMain().getPanelAdicionar().getComboAlimentacion().removeAllItems();
        for (String c : cLogica.obtenerClasificaciones()) {
            vPrincipal.getPanelMain().getPanelAdicionar().getComboClasificacion().addItem(c);
        }
        for (String a : cLogica.obtenerAlimentaciones()) {
            vPrincipal.getPanelMain().getPanelAdicionar().getComboAlimentacion().addItem(a);
        }

        // Panel Consultar
        vPrincipal.getPanelMain().getPanelConsultar().getComboClasificacion().removeAllItems();
        vPrincipal.getPanelMain().getPanelConsultar().getComboAlimentacion().removeAllItems();
        vPrincipal.getPanelMain().getPanelConsultar().getComboClasificacion().addItem(""); // Opción vacía
        vPrincipal.getPanelMain().getPanelConsultar().getComboAlimentacion().addItem(""); // Opción vacía
        for (String c : cLogica.obtenerClasificaciones()) {
            vPrincipal.getPanelMain().getPanelConsultar().getComboClasificacion().addItem(c);
        }
        for (String a : cLogica.obtenerAlimentaciones()) {
            vPrincipal.getPanelMain().getPanelConsultar().getComboAlimentacion().addItem(a);
        }

        // Panel Modificar
        vPrincipal.getPanelMain().getPanelModificar().getComboClasificacion().removeAllItems();
        vPrincipal.getPanelMain().getPanelModificar().getComboAlimentacion().removeAllItems();
        for (String c : cLogica.obtenerClasificaciones()) {
            vPrincipal.getPanelMain().getPanelModificar().getComboClasificacion().addItem(c);
        }
        for (String a : cLogica.obtenerAlimentaciones()) {
            vPrincipal.getPanelMain().getPanelModificar().getComboAlimentacion().addItem(a);
        }
    }

    /**
     * Genera un tip aleatorio del día y lo muestra en el panel de inicio.
     */
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

    /**
     * Verifica la inicialización de la base de datos y carga inicial.
     */
    private void verificarInicializacion() {
        boolean hayConexion = cLogica.probarConexion();
        
        if (!hayConexion) {
            return;
        }
        
        try {
            List<MascotaVO> mascotas = cLogica.listarMascotas();
            
            List<MascotaVO> incompletas = cLogica.getMascotasIncompletas();
            if (incompletas != null && !incompletas.isEmpty()) {
                // Las mascotas incompletas se manejan internamente
            }
        } catch (Exception e) {
            // Error manejado internamente
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Navegación entre paneles
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonConsultar()) {
            this.vPrincipal.getPanelMain().mostrarPanelConsultar();
            panelActual = "Consultar";
            cargarTodasMascotas();
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
            cargarTablaMascotas(this.vPrincipal.getPanelMain().getPanelModificar().getTablaAnimales());
        }
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonEliminar()) {
            this.vPrincipal.getPanelMain().mostrarPanelEliminar();
            panelActual = "Eliminar";
            cargarTablaMascotas(this.vPrincipal.getPanelMain().getPanelEliminar().getTablaAnimales());
        }
        
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonLimpiar()) {
            limpiarCampos();
        }
        
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonSalir()) {
            salirAplicacion();
        }

        if (e.getSource() == this.vPrincipal.getPanelMain().getPanelAdicionar().getBotonAdicionar()) {
            adicionarMascota();
        }
        
        if (e.getSource() == this.vPrincipal.getPanelMain().getPanelConsultar().getBotonConsultar()) {
            consultarMascota();
        }
        
        if (e.getSource() == this.vPrincipal.getPanelMain().getPanelModificar().getBotonModificar()) {
            modificarMascota();
        }
        
        if (e.getSource() == this.vPrincipal.getPanelMain().getPanelEliminar().getBotonEliminar()) {
            eliminarMascota();
        }
    }

    /**
     * Limpia los campos del panel actual.
     */
    private void limpiarCampos() {
        if (panelActual.equals("Consultar")) {
            this.vPrincipal.getPanelMain().getPanelConsultar().getCajaApodo().setText("");
            this.vPrincipal.getPanelMain().getPanelConsultar().getCajaFamilia().setText("");
            this.vPrincipal.getPanelMain().getPanelConsultar().getComboClasificacion().setSelectedIndex(0);
            this.vPrincipal.getPanelMain().getPanelConsultar().getComboAlimentacion().setSelectedIndex(0);
            cargarTodasMascotas();
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
            this.vPrincipal.getPanelMain().getPanelModificar().getCajaNombre().setText("");
            this.vPrincipal.getPanelMain().getPanelModificar().getComboClasificacion().setSelectedIndex(0);
            this.vPrincipal.getPanelMain().getPanelModificar().getComboAlimentacion().setSelectedIndex(0);
            this.vPrincipal.getPanelMain().getPanelModificar().getTablaAnimales().clearSelection();
        }
    }

    /**
     * Adiciona una nueva mascota y la muestra inmediatamente en consultar.
     */
    private void adicionarMascota() {
        try {
            String nombre = vPrincipal.getPanelMain().getPanelAdicionar().getCajaNombre().getText().trim();
            String apodo = vPrincipal.getPanelMain().getPanelAdicionar().getCajaApodo().getText().trim();
            String clasificacionStr = (String) vPrincipal.getPanelMain().getPanelAdicionar().getComboClasificacion().getSelectedItem();
            String familia = vPrincipal.getPanelMain().getPanelAdicionar().getCajaFamilia().getText().trim();
            String genero = vPrincipal.getPanelMain().getPanelAdicionar().getCajaGenero().getText().trim();
            String especie = vPrincipal.getPanelMain().getPanelAdicionar().getCajaEspecie().getText().trim();
            String alimentacionStr = (String) vPrincipal.getPanelMain().getPanelAdicionar().getComboAlimentacion().getSelectedItem();

            if (nombre.isEmpty() || apodo.isEmpty() || familia.isEmpty() || 
                genero.isEmpty() || especie.isEmpty()) {
                return;
            }

            Clasificacion clasificacion = Clasificacion.valueOf(clasificacionStr);
            Alimentacion alimentacion = Alimentacion.valueOf(alimentacionStr);

            MascotaVO mascota = new MascotaVO(apodo, alimentacion, nombre, clasificacion, familia, genero, especie);
            
            try {
                cLogica.registrarMascota(mascota);
                
                limpiarCampos();
                
                vPrincipal.getPanelMain().mostrarPanelConsultar();
                panelActual = "Consultar";
                cargarTodasMascotas();
                
            } catch (IllegalArgumentException ex) {
                // Mascota duplicada - se captura silenciosamente
            }

        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Consulta mascotas aplicando los filtros seleccionados.
     * Filtros disponibles: apodo, clasificación, familia, alimentación.
     */
    private void consultarMascota() {
        try {
            String apodo = vPrincipal.getPanelMain().getPanelConsultar().getCajaApodo().getText().trim();
            String familia = vPrincipal.getPanelMain().getPanelConsultar().getCajaFamilia().getText().trim();
            
            Object clasificacionObj = vPrincipal.getPanelMain().getPanelConsultar().getComboClasificacion().getSelectedItem();
            String clasificacion = (clasificacionObj != null && !clasificacionObj.toString().isEmpty()) 
                                  ? clasificacionObj.toString() : null;
            
            Object alimentacionObj = vPrincipal.getPanelMain().getPanelConsultar().getComboAlimentacion().getSelectedItem();
            String alimentacion = (alimentacionObj != null && !alimentacionObj.toString().isEmpty()) 
                                 ? alimentacionObj.toString() : null;
            
            boolean hayFiltros = !apodo.isEmpty() || !familia.isEmpty() || 
                                (clasificacion != null && !clasificacion.isEmpty()) ||
                                (alimentacion != null && !alimentacion.isEmpty());
            
            List<MascotaVO> mascotas;
            
            if (hayFiltros) {
                mascotas = cLogica.consultarConFiltros(
                    apodo.isEmpty() ? null : apodo,
                    clasificacion,
                    familia.isEmpty() ? null : familia,
                    alimentacion
                );
            } else {
                mascotas = cLogica.listarMascotas();
            }
            
            mostrarMascotasEnTabla(vPrincipal.getPanelMain().getPanelConsultar().getTablaAnimales(), mascotas);

        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Modifica una mascota seleccionada.
     * Solo permite modificar: nombre, clasificación y alimentación.
     * NO permite modificar: familia, género, especie.
     */
    private void modificarMascota() {
        try {
            String apodoOriginal = vPrincipal.getPanelMain().getPanelModificar().getCajaApodo().getText().trim();
            
            if (apodoOriginal.isEmpty()) {
                return;
            }

            MascotaVO mascota = cLogica.buscarMascota(apodoOriginal);
            if (mascota != null) {
                String nombreNuevo = vPrincipal.getPanelMain().getPanelModificar().getCajaNombre().getText().trim();
                String clasificacionStr = (String) vPrincipal.getPanelMain().getPanelModificar().getComboClasificacion().getSelectedItem();
                String alimentacionStr = (String) vPrincipal.getPanelMain().getPanelModificar().getComboAlimentacion().getSelectedItem();

                if (!nombreNuevo.isEmpty()) {
                    mascota.setNombre(nombreNuevo);
                }
                
                mascota.setClasificacion(Clasificacion.valueOf(clasificacionStr));
                mascota.setAlimentacion(Alimentacion.valueOf(alimentacionStr));

                cLogica.actualizarMascota(mascota);
                
                cargarTablaMascotas(vPrincipal.getPanelMain().getPanelModificar().getTablaAnimales());
                limpiarCampos();
            }

        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Elimina una mascota seleccionada de la tabla.
     * La mascota debe estar visualizada en la tabla para poder eliminarla.
     */
    private void eliminarMascota() {
        try {
            int filaSeleccionada = vPrincipal.getPanelMain().getPanelEliminar().getTablaAnimales().getSelectedRow();
            
            if (filaSeleccionada == -1) {
                return;
            }

            DefaultTableModel modelo = (DefaultTableModel) vPrincipal.getPanelMain().getPanelEliminar().getTablaAnimales().getModel();
            String apodo = (String) modelo.getValueAt(filaSeleccionada, 0);

            cLogica.eliminarMascota(apodo);
            
            cargarTablaMascotas(vPrincipal.getPanelMain().getPanelEliminar().getTablaAnimales());

        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Cierra la aplicación guardando datos en archivo de acceso aleatorio.
     * El archivo contiene el estado final de todas las mascotas CON alimentación.
     */
    private void salirAplicacion() {
        try {
            cLogica.guardarAccesoAleatorio();
        } catch (Exception ex) {
            // Error capturado
        }
        System.exit(0);
    }

    /**
     * Carga todas las mascotas en el panel consultar.
     */
    private void cargarTodasMascotas() {
        try {
            if (!cLogica.probarConexion()) {
                return;
            }
            
            List<MascotaVO> mascotas = cLogica.listarMascotas();
            
            if (mascotas.isEmpty()) {
                DefaultTableModel modeloVacio = new DefaultTableModel(
                    new String[]{"Apodo", "Nombre", "Clasificación", "Familia", "Género", "Especie", "Alimentación"}, 0
                );
                this.vPrincipal.getPanelMain().getPanelConsultar().getTablaAnimales().setModel(modeloVacio);
                return;
            }
            
            mostrarMascotasEnTabla(this.vPrincipal.getPanelMain().getPanelConsultar().getTablaAnimales(), mascotas);

        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Carga todas las mascotas en una tabla específica.
     * 
     * @param tabla JTable donde cargar los datos
     */
    private void cargarTablaMascotas(javax.swing.JTable tabla) {
        try {
            if (!cLogica.probarConexion()) {
                return;
            }
            
            List<MascotaVO> mascotas = cLogica.listarMascotas();
            mostrarMascotasEnTabla(tabla, mascotas);

        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Muestra una lista de mascotas en la tabla.
     * 
     * @param tabla JTable donde mostrar las mascotas
     * @param mascotas Lista de mascotas a mostrar
     */
    private void mostrarMascotasEnTabla(javax.swing.JTable tabla, List<MascotaVO> mascotas) {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"Apodo", "Nombre", "Clasificación", "Familia", "Género", "Especie", "Alimentación"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (MascotaVO m : mascotas) {
            modelo.addRow(new Object[]{
                m.getApodo(),
                m.getNombre(),
                m.getClasificacion().name(),
                m.getFamilia(),
                m.getGenero(),
                m.getEspecie(),
                m.getAlimentacion().name()
            });
        }

        tabla.setModel(modelo);
    }
}
