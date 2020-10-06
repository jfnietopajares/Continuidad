package es.sacyl.hnss.entity;

/**
 * The Class Provincia.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Provincia {

    private String codigo;

    private String descripcion;

    public final static Provincia PROVINCIA_DEFECTO = new Provincia("05", "ÁVILA");

    public final static Provincia AVILA = new Provincia("05", "ÁVILA");

    /**
     * Instantiates a new provincia.
     */
    public Provincia() {
    }

    /**
     * Instantiates a new provincia.
     *
     * @param codigo the codigo
     * @param descripcion the descripcion
     */
    public Provincia(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the codigo.
     *
     * @param codigo the new codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "codigo=" + this.getCodigo() + " descripción=" + this.getDescripcion();
    }

}
