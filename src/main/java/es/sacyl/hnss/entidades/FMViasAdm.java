package es.sacyl.hnss.entidades;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author 06551256M
 */
public class FMViasAdm implements Serializable {

    private String codigo;

    private String nombre;

    public static final String labelFrom = "Vías de administración ";

    public FMViasAdm() {

    }

    public FMViasAdm(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String getLabelFrom() {
        return labelFrom;
    }

    @Override
    public String toString() {
        return "FarmaFMViasAdm{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }

}
