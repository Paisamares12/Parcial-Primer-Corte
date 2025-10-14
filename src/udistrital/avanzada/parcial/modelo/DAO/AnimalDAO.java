package udistrital.avanzada.parcial.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.AnimalVO;
import udistrital.avanzada.parcial.modelo.Clasificacion;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.conexion.ConexionBD;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase AnimalDAO
 * <p>
 * Gestiona las operaciones de consulta e inserción en la base de datos relacionadas con los
 * animales y mascotas. Implementa la capa de persistencia siguiendo el patrón DAO
 * y aplica el Principio de Sustitución de Liskov devolviendo objetos tipo
 * {@link AnimalVO}.
 * </p>
 * 
 * Modificado y documentado: Juan Ariza
 * 
 * @author Juan Ariza
 * @version 4.0
 * @since 2025-10-14
 */
public class AnimalDAO {

    private ConexionBD conexionBD;

    /**
     * Constructor que obtiene la instancia de conexión.
     */
    public AnimalDAO() {
        this.conexionBD = ConexionBD.getInstance();
    }

    /**
     * Consulta un animal en la base de datos a partir de su apodo.
     *
     * @param apodo Apodo de la mascota a consultar.
     * @return Objeto {@link AnimalVO} encontrado o {@code null} si no existe.
     */
    public AnimalVO consultarAnimalApodo(String apodo) {
        String sql = "SELECT * FROM Animales WHERE apodo = ?";
        return ejecutarConsulta(sql, apodo);
    }

    /**
     * Consulta un animal en la base de datos a partir de su clasificación.
     *
     * @param clasificacion Clasificación taxonómica de la mascota.
     * @return Objeto {@link AnimalVO} encontrado o {@code null} si no existe.
     */
    public AnimalVO consultarAnimalClasificacion(Clasificacion clasificacion) {
        String sql = "SELECT * FROM Animales WHERE clasificacion = ?";
        return ejecutarConsulta(sql, clasificacion.name());
    }

    /**
     * Consulta un animal en la base de datos a partir de su familia.
     *
     * @param familia Familia taxonómica de la mascota.
     * @return Objeto {@link AnimalVO} encontrado o {@code null} si no existe.
     */
    public AnimalVO consultarAnimalFamilia(String familia) {
        String sql = "SELECT * FROM Animales WHERE familia = ?";
        return ejecutarConsulta(sql, familia);
    }

    /**
     * Consulta un animal en la base de datos a partir de su tipo de alimentación.
     *
     * @param alimentacion Tipo de alimentación de la mascota.
     * @return Objeto {@link AnimalVO} encontrado o {@code null} si no existe.
     */
    public AnimalVO consultarAnimalAlimentacion(Alimentacion alimentacion) {
        String sql = "SELECT * FROM Animales WHERE alimentacion = ?";
        return ejecutarConsulta(sql, alimentacion.name());
    }

    /**
     * Consulta mascotas aplicando múltiples filtros opcionales.
     * 
     * @param apodo Apodo a buscar (null o vacío para ignorar)
     * @param clasificacion Clasificación a buscar (null para ignorar)
     * @param familia Familia a buscar (null o vacío para ignorar)
     * @param alimentacion Alimentación a buscar (null para ignorar)
     * @return Lista de mascotas que cumplen con los filtros
     * @throws ConexionException si hay error de conexión
     */
    public List<MascotaVO> consultarConFiltros(String apodo, Clasificacion clasificacion, 
                                               String familia, Alimentacion alimentacion) 
                                               throws ConexionException {
        List<MascotaVO> resultado = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Animales WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (apodo != null && !apodo.trim().isEmpty()) {
            sql.append(" AND apodo LIKE ?");
            parametros.add("%" + apodo + "%");
        }

        if (clasificacion != null) {
            sql.append(" AND clasificacion = ?");
            parametros.add(clasificacion.name());
        }

        if (familia != null && !familia.trim().isEmpty()) {
            sql.append(" AND familia LIKE ?");
            parametros.add("%" + familia + "%");
        }

        if (alimentacion != null) {
            sql.append(" AND alimentacion = ?");
            parametros.add(alimentacion.name());
        }

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.add(crearMascota(rs));
                }
            }

        } catch (SQLException e) {
            throw new ConexionException("Error al consultar con filtros: " + e.getMessage(), e);
        }

        return resultado;
    }

    /**
     * Inserta una nueva mascota en la base de datos.
     *
     * @param mascota Objeto MascotaVO a insertar
     * @return true si la inserción fue exitosa
     * @throws ConexionException si ocurre un error de base de datos
     */
    public boolean insertarMascota(MascotaVO mascota) throws ConexionException {
        String sql = "INSERT INTO Animales (apodo, alimentacion, nombre, clasificacion, familia, genero, especie) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, mascota.getApodo());
            ps.setString(2, mascota.getAlimentacion().name());
            ps.setString(3, mascota.getNombre());
            ps.setString(4, mascota.getClasificacion().name());
            ps.setString(5, mascota.getFamilia());
            ps.setString(6, mascota.getGenero());
            ps.setString(7, mascota.getEspecie());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new ConexionException("Error al insertar mascota: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina una mascota de la base de datos por su apodo.
     *
     * @param apodo Apodo de la mascota a eliminar
     * @return true si la eliminación fue exitosa
     * @throws ConexionException si ocurre un error de base de datos
     */
    public boolean eliminarMascota(String apodo) throws ConexionException {
        String sql = "DELETE FROM Animales WHERE apodo = ?";
        
        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, apodo);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new ConexionException("Error al eliminar mascota: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza los datos modificables de una mascota.
     * Solo permite modificar: nombre, clasificación y alimentación.
     *
     * @param mascota Objeto MascotaVO con los datos actualizados
     * @return true si la actualización fue exitosa
     * @throws ConexionException si ocurre un error de base de datos
     */
    public boolean actualizarMascota(MascotaVO mascota) throws ConexionException {
        String sql = "UPDATE Animales SET nombre = ?, clasificacion = ?, alimentacion = ? WHERE apodo = ?";
        
        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getClasificacion().name());
            ps.setString(3, mascota.getAlimentacion().name());
            ps.setString(4, mascota.getApodo());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new ConexionException("Error al actualizar mascota: " + e.getMessage(), e);
        }
    }

    /**
     * Lista todas las mascotas de la base de datos.
     *
     * @return Lista con todas las mascotas
     * @throws ConexionException si ocurre un error de base de datos
     */
    public List<MascotaVO> listarTodasMascotas() throws ConexionException {
        List<MascotaVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Animales";
        
        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(crearMascota(rs));
            }
            
        } catch (SQLException e) {
            throw new ConexionException("Error al listar mascotas: " + e.getMessage(), e);
        }
        
        return lista;
    }

    /**
     * Verifica si existe una mascota con todos los datos idénticos.
     *
     * @param mascota Mascota a verificar
     * @return true si ya existe una mascota con esos datos
     * @throws ConexionException si ocurre un error de base de datos
     */
    public boolean existeMascotaCompleta(MascotaVO mascota) throws ConexionException {
        String sql = "SELECT COUNT(*) FROM Animales WHERE apodo = ? AND alimentacion = ? AND nombre = ? " +
                     "AND clasificacion = ? AND familia = ? AND genero = ? AND especie = ?";
        
        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, mascota.getApodo());
            ps.setString(2, mascota.getAlimentacion().name());
            ps.setString(3, mascota.getNombre());
            ps.setString(4, mascota.getClasificacion().name());
            ps.setString(5, mascota.getFamilia());
            ps.setString(6, mascota.getGenero());
            ps.setString(7, mascota.getEspecie());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            throw new ConexionException("Error al verificar existencia: " + e.getMessage(), e);
        }
        
        return false;
    }

    /**
     * Ejecuta una consulta SQL genérica según un parámetro y devuelve un objeto
     * {@link MascotaVO}.
     *
     * @param sql Consulta SQL con un parámetro (WHERE ... = ?)
     * @param parametro Valor a buscar.
     * @return Un objeto {@link MascotaVO} o {@code null} si no se encuentra.
     */
    private AnimalVO ejecutarConsulta(String sql, String parametro) {
        AnimalVO animal = null;

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, parametro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    animal = crearMascota(rs);
                }
            }

        } catch (Exception e) {
            // Error manejado internamente
        }

        return animal;
    }

    /**
     * Crea un objeto {@link MascotaVO} a partir de un registro de la base de datos.
     *
     * @param rs ResultSet con los datos obtenidos de la consulta.
     * @return Un objeto {@link MascotaVO} con los datos del animal.
     * @throws SQLException si ocurre un error al leer el ResultSet.
     */
    private MascotaVO crearMascota(ResultSet rs) throws SQLException {
        Alimentacion tipoAlimentacion = Alimentacion.valueOf(rs.getString("alimentacion"));
        Clasificacion tipoClasificacion = Clasificacion.valueOf(rs.getString("clasificacion"));

        return new MascotaVO(
                rs.getString("apodo"),
                tipoAlimentacion,
                rs.getString("nombre"),
                tipoClasificacion,
                rs.getString("familia"),
                rs.getString("genero"),
                rs.getString("especie")
        );
    }
}