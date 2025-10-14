package udistrital.avanzada.parcial.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
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
 * </p>
 *
 * @author Paula Martínez
 * @version 1.0
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
        this.vPrincipal.getPanelMenu().getBotonSerializar().addActionListener(this);
        this.vPrincipal.getPanelMenu().getBotonSalir().addActionListener(this);

        // Botones de acción en cada panel
        this.vPrincipal.getPanelMain().getPanelAdicionar().getBotonAdicionar().addActionListener(this);
        this.vPrincipal.getPanelMain().getPanelConsultar().getBotonConsultar().addActionListener(this);
        this.vPrincipal.getPanelMain().getPanelModificar().getBotonModificar().addActionListener(this);
        this.vPrincipal.getPanelMain().getPanelEliminar().getBotonEliminar().addActionListener(this);
    }

    /**
     * Inicializa los combos de clasificación y alimentación en todos los paneles.
     */
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
        
        if (e.getSource() == this.vPrincipal.getPanelMenu().getBotonSerializar()) {
            serializarDatos();
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
            this.vPrincipal.getPanelMain().getPanelModificar().getCajaFamilia().setText("");
            this.vPrincipal.getPanelMain().getPanelModificar().getComboClasificacion().setSelectedIndex(0);
            this.vPrincipal.getPanelMain().getPanelModificar().getComboAlimentacion().setSelectedIndex(0);
        }
    }

    /**
     * Adiciona una nueva mascota a la base de datos.
     */
    private void adicionarMascota() {
        try {
            String nombre = vPrincipal.getPanelMain().getPanelAdicionar().getCajaNombre().getText();
            String apodo = vPrincipal.getPanelMain().getPanelAdicionar().getCajaApodo().getText();
            String clasificacionStr = (String) vPrincipal.getPanelMain().getPanelAdicionar().getComboClasificacion().getSelectedItem();
            String familia = vPrincipal.getPanelMain().getPanelAdicionar().getCajaFamilia().getText();
            String genero = vPrincipal.getPanelMain().getPanelAdicionar().getCajaGenero().getText();
            String especie = vPrincipal.getPanelMain().getPanelAdicionar().getCajaEspecie().getText();
            String alimentacionStr = (String) vPrincipal.getPanelMain().getPanelAdicionar().getComboAlimentacion().getSelectedItem();

            Clasificacion clasificacion = Clasificacion.valueOf(clasificacionStr);
            Alimentacion alimentacion = Alimentacion.valueOf(alimentacionStr);

            MascotaVO mascota = new MascotaVO(apodo, alimentacion, nombre, clasificacion, familia, genero, especie);
            cLogica.registrarMascota(mascota);

            limpiarCampos();
            vPrincipal.getPanelMain().mostrarPanelConsultar();
            panelActual = "Consultar";
            cargarTodasMascotas();

        } catch (Exception ex) {
            // Error capturado, no se hace nada visible
        }
    }

    /**
     * Consulta mascotas según criterios ingresados.
     */
    private void consultarMascota() {
        try {
            String apodo = vPrincipal.getPanelMain().getPanelConsultar().getCajaApodo().getText().trim();
            
            if (!apodo.isEmpty()) {
                MascotaVO mascota = cLogica.buscarMascota(apodo);
                if (mascota != null) {
                    mostrarMascotaEnTabla(vPrincipal.getPanelMain().getPanelConsultar().getTablaAnimales(), mascota);
                } else {
                    DefaultTableModel modeloVacio = new DefaultTableModel(
                        new String[]{"Apodo", "Nombre", "Clasificación", "Familia", "Género", "Especie", "Alimentación"}, 0
                    );
                    vPrincipal.getPanelMain().getPanelConsultar().getTablaAnimales().setModel(modeloVacio);
                }
            } else {
                cargarTodasMascotas();
            }

        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Modifica una mascota seleccionada de la tabla.
     */
    private void modificarMascota() {
        try {
            int filaSeleccionada = vPrincipal.getPanelMain().getPanelModificar().getTablaAnimales().getSelectedRow();
            
            if (filaSeleccionada == -1) {
                return;
            }

            DefaultTableModel modelo = (DefaultTableModel) vPrincipal.getPanelMain().getPanelModificar().getTablaAnimales().getModel();
            String apodo = (String) modelo.getValueAt(filaSeleccionada, 0);

            MascotaVO mascota = cLogica.buscarMascota(apodo);
            if (mascota != null) {
                String clasificacionStr = (String) vPrincipal.getPanelMain().getPanelModificar().getComboClasificacion().getSelectedItem();
                String alimentacionStr = (String) vPrincipal.getPanelMain().getPanelModificar().getComboAlimentacion().getSelectedItem();

                mascota.setClasificacion(Clasificacion.valueOf(clasificacionStr));
                mascota.setAlimentacion(Alimentacion.valueOf(alimentacionStr));

                cLogica.actualizarMascota(mascota);
                cargarTablaMascotas(vPrincipal.getPanelMain().getPanelModificar().getTablaAnimales());
            }

        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Elimina una mascota seleccionada de la tabla.
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
     * Serializa los datos actuales.
     */
    private void serializarDatos() {
        try {
            cLogica.guardarArchivo();
        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Cierra la aplicación guardando datos en archivo de acceso aleatorio.
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

            this.vPrincipal.getPanelMain().getPanelConsultar().getTablaAnimales().setModel(modelo);

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

        } catch (Exception ex) {
            // Error capturado
        }
    }

    /**
     * Muestra una mascota específica en la tabla.
     * 
     * @param tabla JTable donde mostrar la mascota
     * @param mascota MascotaVO a mostrar
     */
    private void mostrarMascotaEnTabla(javax.swing.JTable tabla, MascotaVO mascota) {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"Apodo", "Nombre", "Clasificación", "Familia", "Género", "Especie", "Alimentación"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addRow(new Object[]{
            mascota.getApodo(),
            mascota.getNombre(),
            mascota.getClasificacion().name(),
            mascota.getFamilia(),
            mascota.getGenero(),
            mascota.getEspecie(),
            mascota.getAlimentacion().name()
        });

        tabla.setModel(modelo);
    }
}
