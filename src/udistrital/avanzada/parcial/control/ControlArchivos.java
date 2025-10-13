package udistrital.avanzada.parcial.control;

import java.util.List;
import udistrital.avanzada.parcial.modelo.MascotaVO;
import udistrital.avanzada.parcial.modelo.persistencia.SerializadorMascotas;
import udistrital.avanzada.parcial.modelo.persistencia.AccesoAleatorioMascotas;
import udistrital.avanzada.parcial.modelo.excepciones.ConexionException;

/**
 * Clase ControlArchivos
 * Gestiona la serialización y acceso aleatorio de los datos de las mascotas.
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
public class ControlArchivos {

    private SerializadorMascotas serializador;
    private AccesoAleatorioMascotas accesoAleatorio;

    public ControlArchivos() {
        serializador = new SerializadorMascotas("mascotas.dat");
        accesoAleatorio = new AccesoAleatorioMascotas("mascotasAleatorio.dat");
    }

    /**
     * Serializa la lista completa de mascotas en archivo binario.
     */
    public void serializarMascotas(List<MascotaVO> lista) throws ConexionException {
        try {
            serializador.guardar(lista);
        } catch (Exception e) {
            throw new ConexionException("Error al serializar mascotas", e);
        }
    }

    /**
     * Carga todas las mascotas desde el archivo binario.
     */
    public List<MascotaVO> deserializarMascotas() throws ConexionException {
        try {
            return serializador.cargar();
        } catch (Exception e) {
            throw new ConexionException("Error al deserializar mascotas", e);
        }
    }

    /**
     * Guarda la lista en archivo de acceso aleatorio.
     */
    public void guardarAleatorio(List<MascotaVO> lista) throws ConexionException {
        try {
            accesoAleatorio.guardarMascotas(lista);
        } catch (Exception e) {
            throw new ConexionException("Error al guardar en archivo aleatorio", e);
        }
    }

    /**
     * Carga la lista desde archivo de acceso aleatorio.
     */
    public List<MascotaVO> cargarAleatorio() throws ConexionException {
        try {
            return accesoAleatorio.cargarMascotas();
        } catch (Exception e) {
            throw new ConexionException("Error al leer archivo aleatorio", e);
        }
    }
}
