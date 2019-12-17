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
public class Piloto implements Serializable {
    private int id_piloto;
    private String nombrePiloto;
    private int sueldo;
    private int numeroVictorias;

    public Piloto() {
        
    }
    
    

    public Piloto(int id_piloto, String nombrePiloto, int sueldo, int numeroVictorias) {
        this.id_piloto = id_piloto;
        this.nombrePiloto = nombrePiloto;
        this.sueldo = sueldo;
        this.numeroVictorias = numeroVictorias;
    }

    public int getId_piloto() {
        return id_piloto;
    }

    public void setId_piloto(int id_piloto) {
        this.id_piloto = id_piloto;
    }

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public void setNombrePiloto(String nombrePiloto) {
        this.nombrePiloto = nombrePiloto;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getNumeroVictorias() {
        return numeroVictorias;
    }

    public void setNumeroVictorias(int numeroVictorias) {
        this.numeroVictorias = numeroVictorias;
    }
    
    
    
}
