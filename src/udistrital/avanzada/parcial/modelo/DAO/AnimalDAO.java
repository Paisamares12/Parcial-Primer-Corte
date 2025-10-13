package udistrital.avanzada.parcial.modelo.dao;

// Importaciones de librerías
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importaciones desde otros paquetes
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.AnimalVO;
import udistrital.avanzada.parcial.modelo.Clasificacion;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.conexion.Conexion;

/**
 * Clase AnimalDAO
 * <p>
 * Gestiona las operaciones de consulta en la base de datos relacionadas con los
 * animales y mascotas. Implementa la capa de persistencia siguiendo el patrón DAO
 * y aplica el Principio de Sustitución de Liskov devolviendo objetos tipo
 * {@link AnimalVO}.
 * </p>
 * 
 * <p>
 * <b>Mejoras realizadas:</b><br>
 * - Uso de PreparedStatement para evitar inyección SQL.<br>
 * - Método privado reutilizable para crear objetos {@code MascotaVO}.<br>
 * - Cierre seguro de recursos JDBC.<br>
 * </p>
 * 
 * Originalmente creada por Paula Martínez.<br>
 * Modificada y optimizada por Juan Sebastián Bravo Rojas.
 * 
 * @author Paula Martínez
 * @version 2.0
 * @since 2025-10-13
 */
public class AnimalDAO {

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
     * Ejecuta una consulta SQL genérica según un parámetro y devuelve un objeto
     * {@link MascotaVO}.
     *
     * @param sql Consulta SQL con un parámetro (WHERE ... = ?)
     * @param parametro Valor a buscar.
     * @return Un objeto {@link MascotaVO} o {@code null} si no se encuentra.
     */
    private AnimalVO ejecutarConsulta(String sql, String parametro) {
        AnimalVO animal = null;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, parametro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    animal = crearMascota(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar animal: " + e.getMessage());
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
