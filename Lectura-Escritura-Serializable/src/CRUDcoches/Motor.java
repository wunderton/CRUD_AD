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
public class Motor implements Serializable {
    private int id_motor;
    private int numeroUsos;
    private int kilometos;
    private int caballos;

    public Motor() {
    }

    public Motor(int id_motor, int numeroUsos, int kilometos, int caballos) {
        this.id_motor = id_motor;
        this.numeroUsos = numeroUsos;
        this.kilometos = kilometos;
        this.caballos = caballos;
    }

    public int getId_motor() {
        return id_motor;
    }

    public void setId_motor(int id_motor) {
        this.id_motor = id_motor;
    }

    public int getNumeroUsos() {
        return numeroUsos;
    }

    public void setNumeroUsos(int numeroUsos) {
        this.numeroUsos = numeroUsos;
    }

    public int getKilometos() {
        return kilometos;
    }

    public void setKilometos(int kilometos) {
        this.kilometos = kilometos;
    }

    public int getCaballos() {
        return caballos;
    }

    public void setCaballos(int caballos) {
        this.caballos = caballos;
    }

    public int getKilometros() {
        return kilometos;
    }

    public int setKilometros(int parseInt) {
        return this.kilometos = kilometos;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}
