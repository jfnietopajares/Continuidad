package es.sacyl.hnss.entity;

/**
 * The Class Municipio. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Municipio {

    /**
     * The id.
     */
    private Long id;

    /**
     * The codigo.
     */
    private String codigo;

    /**
     * The provincia.
     */
    private Provincia provincia;

    /**
     * The descripcion.
     */
    private String descripcion;

    /**
     * Instantiates a new municipio.
     */
    public Municipio() {

    }

    public static Municipio PIDRALAVES = new Municipio(new Long(551), "1873", Provincia.AVILA, "Piedralaves");

    /**
     * Instantiates a new municipio.
     *
     * @param id the id
     * @param codigo the codigo
     * @param provincia the provincia
     * @param descripcion the descripcion
     */
    public Municipio(Long id, String codigo, Provincia provincia, String descripcion) {
        this.id = id;
        this.codigo = codigo;
        this.provincia = provincia;
        this.descripcion = descripcion;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
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
     * Gets the provincia.
     *
     * @return the provincia
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * Sets the provincia.
     *
     * @param provincia the new provincia
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    public String getDescripcionCorta(int ancho) {
        if (descripcion != null) {
            if (descripcion.length() > ancho) {
                return descripcion.substring(0, ancho - 1);
            } else {
                return descripcion;
            }
        } else {
            return "";
        }
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
        return "id=" + id + "\n" + " codigo=" + codigo + "\n" + "descipcion=" + descripcion + "\n" + "provincia="
                + provincia;
    }

}
