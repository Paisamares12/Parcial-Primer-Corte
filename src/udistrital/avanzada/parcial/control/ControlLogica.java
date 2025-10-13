package udistrital.avanzada.parcial.control;

import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;

/**
 * Clase ControlLogica
 * 
 * Coordina y gestiona la comunicación entre los diferentes
 * controladores del sistema (interfaz, archivos, conexión y
 * manejo de mascotas). 
 * 
 * <p>
 * Actúa como el "centro de operaciones" del aplicativo,
 * aplicando el principio de responsabilidad única:
 * cada control secundario atiende su ámbito (archivos, BD, lógica de negocio),
 * mientras que esta clase orquesta la interacción entre ellos.
 * </p>
 * 
 * <p>
 * Originalmente creada por Paula Martínez.<br>
 * Modificada y documentada por Juan Sebastián Bravo Rojas.
 * </p>
 * 
 * @author Paula Martínez
 * @version 2.0
 * @since 2025-10-13
 */
public class ControlLogica {

    /** Controlador de operaciones de archivo (serialización y acceso aleatorio) */
    private ControlArchivos cArchivos;

    /** Controlador encargado de la lógica de negocio y validaciones de mascotas */
    private ControlMascota cMascota;

    /** Controlador de conexión con la base de datos (prueba y cierre) */
    private ControlConexion cConexion;

    /** Controlador de interfaz que maneja la GUI principal */
    private ControlInterfaz cInterfaz;

    /**
     * Constructor por defecto.
     * 
     * <p>
     * Inicializa todos los subcontroles y establece el flujo
     * principal del aplicativo, donde ControlInterfaz es la
     * capa superior que comunica las acciones del usuario
     * con el resto de la lógica.
     * </p>
     */
    public ControlLogica() {
        this.cArchivos = new ControlArchivos();   // Control de persistencia en archivos
        this.cConexion = new ControlConexion();   // Control de conexión con la BD
        this.cMascota = new ControlMascota();     // Control de lógica de negocio
        this.cInterfaz = new ControlInterfaz(this); // Control de interfaz gráfica
    }

    // -------------------------------------------------------------------------
    // MÉTODOS DE CONSULTA (usados por ControlInterfaz)
    // -------------------------------------------------------------------------

    /**
     * Retorna el conjunto de clasificaciones taxonómicas disponibles.
     * 
     * @return arreglo de nombres de clasificaciones
     */
    public String[] obtenerClasificaciones() {
        return cMascota.getClasificaciones();
    }

    /**
     * Retorna los tipos de alimentación posibles para una mascota.
     * 
     * @return arreglo de nombres de alimentaciones
     */
    public String[] obtenerAlimentaciones() {
        return cMascota.getAlimentaciones();
    }

    // -------------------------------------------------------------------------
    // OPERACIONES CRUD (delegadas al ControlMascota)
    // -------------------------------------------------------------------------

    /**
     * Registra una nueva mascota en el sistema.
     * 
     * @param m objeto MascotaVO a registrar
     * @throws Exception si hay datos incompletos o mascota duplicada
     */
    public void registrarMascota(MascotaVO m) throws Exception {
        cMascota.registrarMascota(m);
    }

    /**
     * Elimina una mascota registrada por su apodo.
     * 
     * @param apodo identificador coloquial de la mascota
     * @throws Exception si no se encuentra o hay error de acceso
     */
    public void eliminarMascota(String apodo) throws Exception {
        cMascota.eliminarMascota(apodo);
    }

    /**
     * Busca una mascota específica por su apodo.
     * 
     * @param apodo apodo de la mascota
     * @return objeto MascotaVO si se encuentra
     * @throws Exception si no existe la mascota o hay error de búsqueda
     */
    public MascotaVO buscarMascota(String apodo) throws Exception {
        return cMascota.buscarMascota(apodo);
    }

    /**
     * Lista todas las mascotas registradas en memoria o en la base de datos.
     * 
     * @return lista de mascotas
     * @throws Exception si ocurre un error de lectura
     */
    public List<MascotaVO> listarMascotas() throws Exception {
        return cMascota.listarMascotas();
    }

    // -------------------------------------------------------------------------
    // OPERACIONES DE PERSISTENCIA
    // -------------------------------------------------------------------------

    /**
     * Guarda todas las mascotas en un archivo de respaldo mediante serialización.
     * 
     * @throws Exception si ocurre un error al serializar los datos
     */
    public void guardarArchivo() throws Exception {
        cArchivos.serializarMascotas(cMascota.listarMascotas());
    }

    // -------------------------------------------------------------------------
    // OPERACIONES DE CONEXIÓN A BASE DE DATOS
    // -------------------------------------------------------------------------

    /**
     * Prueba la conexión con la base de datos.
     * 
     * @return true si la conexión es válida, false si no lo es
     */
    public boolean probarConexion() {
        return cConexion.verificarConexion();
    }
}
