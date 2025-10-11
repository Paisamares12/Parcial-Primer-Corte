package udistrital.avanzada.parcial.modelo.DAO;
//Importaciones de librerias

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import udistrital.avanzada.parcial.modelo.Alimentacion;
//Importanciones desde otros paquetes
import udistrital.avanzada.parcial.modelo.AnimalVO;
import udistrital.avanzada.parcial.modelo.Clasificacion;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.conexion.Conexion;

/**
 * Clase AnimalDAO daojeje
 *
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-09
 */
public class AnimalDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    private static String mensaje;

    public AnimalDAO() {
        con = null;
        st = null;
        rs = null;
    }

    /**
     * Consulta un animal en la base de datos a partir de su apodo.
     * <p>
     * Este método busca en la tabla <strong>Animales</strong> un registro que
     * coincida con el apodo especificado. Si se encuentra una coincidencia, se
     * crea un objeto {@link MascotaVO} con los datos obtenidos de la base de
     * datos y se devuelve como tipo {@link AnimalVO}, respetando el principio
     * de sustitución de Liskov.
     * </p>
     *
     * <p>
     * En caso de que ocurra una excepción durante la consulta, se asigna un
     * mensaje de error a la variable {@code mensaje}.
     * </p>
     *
     * @param apodo el apodo de la mascota a consultar.
     * @return un objeto {@link AnimalVO} que representa la mascota encontrada,
     * o {@code null} si no existe una coincidencia en la base de datos.
     */
    public AnimalVO consultarAnimalApodo(String apodo) {
        // Objeto que almacenará el resultado de la consulta
        AnimalVO animal = null;
        // Consulta SQL para buscar el animal según el apodo
        String consulta = "SELECT * FROM Animales where apodo='" + apodo + "'";
        try {
            // Obtiene la conexión desde la clase Conexion
            con = (Connection) Conexion.getConexion();
            // Crea un Statement para ejecutar la consulta SQL
            st = con.createStatement();
            // Ejecuta la consulta y almacena el resultado en ResultSet
            rs = st.executeQuery(consulta);
            // Si hay al menos un resultado, crea un objeto MascotaVO con los datos obtenidos
            if (rs.next()) {
                // 🔹 Conversión del texto de la BD al tipo enum Alimentacion y Clasificaicon
                Alimentacion tipoAlimentacion = Alimentacion.valueOf(rs.getString("alimentacion"));
                Clasificacion clasificacion = Clasificacion.valueOf(rs.getString("clasificacion"));
                animal = new MascotaVO(
                        rs.getString("apodo"),
                        tipoAlimentacion,
                        rs.getString("nombre"),
                        clasificacion,
                        rs.getString("familia"),
                        rs.getString("genero"),
                        rs.getString("especie")
                );
            }
            // Cierra el Statement para liberar recursos
            st.close();
            // Desconecta la base de datos mediante el método estático de Conexion
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Si ocurre un error durante la consulta, guarda un mensaje descriptivo
            mensaje = "No se pudo realizar la consulta";
        }
        // Devuelve el objeto AnimalVO (MascotaVO) o null si no se encontró
        return animal;
    }

    /**
     * Consulta un animal en la base de datos a partir de su clasificaci[on.
     * <p>
     * Este método busca en la tabla <strong>Animales</strong> un registro que
     * coincida con la clasificaci[on especificada. Si se encuentra una
     * coincidencia, se crea un objeto {@link MascotaVO} con los datos obtenidos
     * de la base de datos y se devuelve como tipo {@link AnimalVO}, respetando
     * el principio de sustitución de Liskov.
     * </p>
     *
     * <p>
     * En caso de que ocurra una excepción durante la consulta, se asigna un
     * mensaje de error a la variable {@code mensaje}.
     * </p>
     *
     * @param clasificacion la clasificacion de la mascota a consultar.
     * @return un objeto {@link AnimalVO} que representa la mascota encontrada,
     * o {@code null} si no existe una coincidencia en la base de datos.
     */
    public AnimalVO consultarAnimalClasificacion(String clasificacion) {
        // Objeto que almacenará el resultado de la consulta
        AnimalVO animal = null;
        // Consulta SQL para buscar el animal según la clasificaci[on
        String consulta = "SELECT * FROM Animales where clasificacion='" + clasificacion + "'";
        try {
            // Obtiene la conexión desde la clase Conexion
            con = (Connection) Conexion.getConexion();
            // Crea un Statement para ejecutar la consulta SQL
            st = con.createStatement();
            // Ejecuta la consulta y almacena el resultado en ResultSet
            rs = st.executeQuery(consulta);
            // Si hay al menos un resultado, crea un objeto MascotaVO con los datos obtenidos
            if (rs.next()) {
                // 🔹 Conversión del texto de la BD al tipo enum Alimentacion y Clasificaicon
                Alimentacion tipoAlimentacion = Alimentacion.valueOf(rs.getString("alimentacion"));
                Clasificacion tipoClasificacion = Clasificacion.valueOf(rs.getString("clasificacion"));
                animal = new MascotaVO(
                        rs.getString("apodo"),
                        tipoAlimentacion,
                        rs.getString("nombre"),
                        tipoClasificacion,
                        rs.getString("familia"),
                        rs.getString("genero"),
                        rs.getString("especie")
                );
            }
            // Cierra el Statement para liberar recursos
            st.close();
            // Desconecta la base de datos mediante el método estático de Conexion
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Si ocurre un error durante la consulta, guarda un mensaje descriptivo
            mensaje = "No se pudo realizar la consulta";
        }
        // Devuelve el objeto AnimalVO (MascotaVO) o null si no se encontró
        return animal;
    }

    /**
     * Consulta un animal en la base de datos a partir de su familia.
     * <p>
     * Este método busca en la tabla <strong>Animales</strong> un registro que
     * coincida con la familia especificada. Si se encuentra una coincidencia,
     * se crea un objeto {@link MascotaVO} con los datos obtenidos de la base de
     * datos y se devuelve como tipo {@link AnimalVO}, respetando el principio
     * de sustitución de Liskov.
     * </p>
     *
     * <p>
     * En caso de que ocurra una excepción durante la consulta, se asigna un
     * mensaje de error a la variable {@code mensaje}.
     * </p>
     *
     * @param familia la familia de la mascota a consultar.
     * @return un objeto {@link AnimalVO} que representa la mascota encontrada,
     * o {@code null} si no existe una coincidencia en la base de datos.
     */
    public AnimalVO consultarAnimalFamilia(String familia) {
        // Objeto que almacenará el resultado de la consulta
        AnimalVO animal = null;
        // Consulta SQL para buscar el animal según la familia
        String consulta = "SELECT * FROM Animales where familia='" + familia + "'";
        try {
            // Obtiene la conexión desde la clase Conexion
            con = (Connection) Conexion.getConexion();
            // Crea un Statement para ejecutar la consulta SQL
            st = con.createStatement();
            // Ejecuta la consulta y almacena el resultado en ResultSet
            rs = st.executeQuery(consulta);
            // Si hay al menos un resultado, crea un objeto MascotaVO con los datos obtenidos
            if (rs.next()) {
                // 🔹 Conversión del texto de la BD al tipo enum Alimentacion y Clasificaicon
                Alimentacion tipoAlimentacion = Alimentacion.valueOf(rs.getString("alimentacion"));
                Clasificacion clasificacion = Clasificacion.valueOf(rs.getString("clasificacion"));
                animal = new MascotaVO(
                        rs.getString("apodo"),
                        tipoAlimentacion,
                        rs.getString("nombre"),
                        clasificacion,
                        rs.getString("familia"),
                        rs.getString("genero"),
                        rs.getString("especie")
                );
            }
            // Cierra el Statement para liberar recursos
            st.close();
            // Desconecta la base de datos mediante el método estático de Conexion
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Si ocurre un error durante la consulta, guarda un mensaje descriptivo
            mensaje = "No se pudo realizar la consulta";
        }
        // Devuelve el objeto AnimalVO (MascotaVO) o null si no se encontró
        return animal;
    }

    /**
     * Consulta un animal en la base de datos a partir de su tipo de
     * alimentacion.
     * <p>
     * Este método busca en la tabla <strong>Animales</strong> un registro que
     * coincida con el tipo de alimentación especificado. Si se encuentra una
     * coincidencia, se crea un objeto {@link MascotaVO} con los datos obtenidos
     * de la base de datos y se devuelve como tipo {@link AnimalVO}, respetando
     * el principio de sustitución de Liskov.
     * </p>
     *
     * <p>
     * En caso de que ocurra una excepción durante la consulta, se asigna un
     * mensaje de error a la variable {@code mensaje}.
     * </p>
     *
     * @param alimentacion el tipo de alimentación de la mascota a consultar.
     * @return un objeto {@link AnimalVO} que representa la mascota encontrada,
     * o {@code null} si no existe una coincidencia en la base de datos.
     */
    public AnimalVO consultarAnimalAlimentacion(Alimentacion alimentacion) {
        // Objeto que almacenará el resultado de la consulta
        AnimalVO animal = null;
        // Consulta SQL para buscar el animal según el apodo
        String consulta = "SELECT * FROM Animales where alimentacion='" + alimentacion + "'";
        try {
            // Obtiene la conexión desde la clase Conexion
            con = (Connection) Conexion.getConexion();
            // Crea un Statement para ejecutar la consulta SQL
            st = con.createStatement();
            // Ejecuta la consulta y almacena el resultado en ResultSet
            rs = st.executeQuery(consulta);
            // Si hay al menos un resultado, crea un objeto MascotaVO con los datos obtenidos
            if (rs.next()) {
                // 🔹 Conversión del texto de la BD al tipo enum Alimentacion y Clasificaicon
                Alimentacion tipoAlimentacion = Alimentacion.valueOf(rs.getString("alimentacion"));
                Clasificacion clasificacion = Clasificacion.valueOf(rs.getString("clasificacion"));
                animal = new MascotaVO(
                        rs.getString("apodo"),
                        tipoAlimentacion,
                        rs.getString("nombre"),
                        clasificacion,
                        rs.getString("familia"),
                        rs.getString("genero"),
                        rs.getString("especie")
                );
            }
            // Cierra el Statement para liberar recursos
            st.close();
            // Desconecta la base de datos mediante el método estático de Conexion
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Si ocurre un error durante la consulta, guarda un mensaje descriptivo
            mensaje = "No se pudo realizar la consulta";
        }
        // Devuelve el objeto AnimalVO (MascotaVO) o null si no se encontró
        return animal;
    }

    /**
     * Devuelve un mensaje cuando se captura alguna excepción
     *
     * @return una cadena de texto para cuando ocurra alguna excepción
     */
    public static String getMensaje() {
        return mensaje;
    }

}
