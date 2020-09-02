package es.sacyl.hnss.entidades;

import java.io.Serializable;

/**
 *
 * @author JuanNieto
 */
public class FMInstrumento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;

    private String nombre;

    public static final String LABELFORM = "Intrumentos y materiales ";

    public FMInstrumento() {

    }

    public FMInstrumento(String codigo, String nombre) {

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
        return LABELFORM;
    }

    @Override
    public String toString() {
        return "FMInstrumento{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }

}
