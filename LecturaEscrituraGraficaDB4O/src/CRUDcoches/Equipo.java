/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDcoches;

import java.io.Serializable;

/**
 *
 * @author anton
 */
public class Equipo implements Serializable {
    private int id_equipo;
    private String nombreEquipo;
    private int campeonatosGanados;
    private int numeroEmpleados;

    public Equipo() {
        
    }
    
    

    public Equipo(int id_equipo, String nombreEquipo, int campeonatosGanados, int numeroEmpleados) {
        this.id_equipo = id_equipo;
        this.nombreEquipo = nombreEquipo;
        this.campeonatosGanados = campeonatosGanados;
        this.numeroEmpleados = numeroEmpleados;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getCampeonatosGanados() {
        return campeonatosGanados;
    }

    public void setCampeonatosGanados(int campeonatosGanados) {
        this.campeonatosGanados = campeonatosGanados;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(int numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }
    
    
}
