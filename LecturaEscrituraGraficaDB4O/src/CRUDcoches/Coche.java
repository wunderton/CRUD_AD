/*
 *  Antonio Carrasco Villegas.
 */
package CRUDcoches;

/**
 *
 * @author wunderton
 */
import java.io.Serializable;

public class Coche implements Serializable{
    private String marca;
    private String modelo;
    private int caballos;
    private int kilometros;

    public Coche() {
    }

    public Coche(String marca, String modelo, int caballos, int kilometros) {
        this.marca = marca;
        this.modelo = modelo;
        this.caballos = caballos;
        this.kilometros = kilometros;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCaballos() {
        return caballos;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCaballos(int caballos) {
        this.caballos = caballos;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }
}

