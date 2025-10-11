/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.parcial.control;

import java.util.Arrays;
import udistrital.avanzada.parcial.modelo.Alimentacion;
import udistrital.avanzada.parcial.modelo.Clasificacion;

/**
 * Clase ControlMascota que controla las mascotas
 *
 * @author Paula Martínez
 * @version 1.0
 * @since 2025-10-09
 */
public class ControlMascota {

    public ControlMascota() {
    }


    public String[] getClasificaciones() {
        return Arrays.stream(Clasificacion.values())
                .map(Enum::name)
                .toArray(String[]::new);
    }

    public String[] getAlimentaciones() {
        return Arrays.stream(Alimentacion.values())
                .map(Enum::name)
                .toArray(String[]::new);
    }

}
