package udistrital.avanzada.parcial.control;

import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.persistencia.InicializadorBD;

/**
 * Clase ControlLogica que actúa como coordinador central del sistema.
 * 
 * <p>
 * Orquesta la comunicación entre los diferentes controladores (interfaz,
 * archivos, conexión y mascotas), aplicando el principio de responsabilidad
 * única donde cada control secundario maneja su ámbito específico.
 * </p>
 * 
 * <p>
 * Al inicializar, esta clase intenta cargar los datos desde el archivo
 * de propiedades a la base de datos. Si la conexión no está disponible,
 * la aplicación continúa pero las operaciones de BD fallarán hasta que
 * se establezca la conexión.
 * </p>
 * 
 * @author Paula Martínez
 * @author Juan Sebastián Bravo Rojas
 * @version 2.0
 * @since 2025-10-13
 */
public class ControlLogica {

    /** Controlador de operaciones de archivo */
    private ControlArchivos cArchivos;
    
    /** Controlador de lógica de negocio de mascotas */
    private ControlMascota cMascota;
    
    /** Controlador de conexión con la base de datos */
    private ControlConexion cConexion;
    
    /** Controlador de la interfaz gráfica */
    private ControlInterfaz cInterfaz;
    
    /** Inicializador para cargar datos desde properties */
    private InicializadorBD inicializador;
    
    /** Lista de mascotas con datos incompletos en la carga inicial */
    private List<MascotaVO> mascotasIncompletas;

    /**
     * Constructor que inicializa todos los controladores y carga datos iniciales.
     * 
     * <p>
     * El orden de inicialización es importante: primero se crean todos los
     * controladores, luego se verifica la conexión, se cargan los datos
     * iniciales y finalmente se crea la interfaz gráfica.
     * </p>
     */
    public ControlLogica() {
        this.cArchivos = new ControlArchivos();
        this.cConexion = new ControlConexion();
        this.cMascota = new ControlMascota();
        this.inicializador = new InicializadorBD();
        
        if (cConexion.verificarConexion()) {
            try {
                cargarDatosIniciales();
            } catch (Exception e) {
                // La excepción se captura aquí para permitir que la interfaz
                // se inicialice y pueda mostrar el error al usuario
            }
        }
        
        this.cInterfaz = new ControlInterfaz(this);
    }
    
    /**
 * Muestra un mensaje de advertencia si hay problemas en la inicialización.
 */

    /**
     * Carga las mascotas desde el archivo properties hacia la base de datos.
     * 
     * <p>
     * Este método se ejecuta al iniciar la aplicación y carga todas las
     * mascotas definidas en el archivo properties que no existan ya en la BD.
     * Las mascotas con datos incompletos se guardan para posterior procesamiento.
     * </p>
     * 
     * @throws Exception si ocurre un error al leer el archivo o conectar a la BD
     */
    private void cargarDatosIniciales() throws Exception {
        mascotasIncompletas = inicializador.inicializarDatos();
    }

    /**
     * Retorna la lista de mascotas con datos incompletos de la carga inicial.
     * 
     * @return lista de mascotas incompletas o null si no se realizó la carga
     */
    public List<MascotaVO> getMascotasIncompletas() {
        return mascotasIncompletas;
    }

    /**
     * Obtiene los nombres de todas las clasificaciones taxonómicas disponibles.
     * 
     * @return arreglo de strings con los nombres de clasificaciones
     */
    public String[] obtenerClasificaciones() {
        return cMascota.getClasificaciones();
    }

    /**
     * Obtiene los nombres de todos los tipos de alimentación disponibles.
     * 
     * @return arreglo de strings con los nombres de alimentaciones
     */
    public String[] obtenerAlimentaciones() {
        return cMascota.getAlimentaciones();
    }

    /**
     * Registra una nueva mascota en el sistema.
     * 
     * @param m objeto MascotaVO con todos los datos de la mascota
     * @throws Exception si hay errores de validación o al insertar en la BD
     */
    public void registrarMascota(MascotaVO m) throws Exception {
        cMascota.registrarMascota(m);
    }

    /**
     * Elimina una mascota del sistema por su apodo.
     * 
     * @param apodo identificador único de la mascota a eliminar
     * @throws Exception si la mascota no existe o hay error al eliminar
     */
    public void eliminarMascota(String apodo) throws Exception {
        cMascota.eliminarMascota(apodo);
    }

    /**
     * Busca una mascota específica por su apodo.
     * 
     * @param apodo identificador único de la mascota
     * @return objeto MascotaVO encontrado
     * @throws Exception si hay error al buscar en la base de datos
     */
    public MascotaVO buscarMascota(String apodo) throws Exception {
        return cMascota.buscarMascota(apodo);
    }

    /**
     * Lista todas las mascotas registradas en el sistema.
     * 
     * @return lista de todas las mascotas
     * @throws Exception si hay error al consultar la base de datos
     */
    public List<MascotaVO> listarMascotas() throws Exception {
        return cMascota.listarMascotas();
    }

    /**
     * Guarda todas las mascotas en un archivo serializado.
     * 
     * @throws Exception si hay error al serializar o escribir el archivo
     */
    public void guardarArchivo() throws Exception {
        cArchivos.serializarMascotas(cMascota.listarMascotas());
    }

    /**
     * Verifica si la conexión con la base de datos está activa.
     * 
     * @return true si hay conexión activa, false en caso contrario
     */
    public boolean probarConexion() {
        return cConexion.verificarConexion();
    }
    
    /**
     * Actualiza los datos modificables de una mascota existente.
     * 
     * @param mascota objeto MascotaVO con los datos actualizados
     * @throws Exception si hay error al actualizar en la base de datos
     */
    public void actualizarMascota(MascotaVO mascota) throws Exception {
        cMascota.actualizarMascota(mascota);
    }
    
    /**
     * Guarda el estado final en archivo de acceso aleatorio al salir.
     * 
     * @throws Exception si hay error al escribir el archivo
     */
    public void guardarAccesoAleatorio() throws Exception {
        cArchivos.guardarAleatorio(cMascota.listarMascotas());
    }
}
