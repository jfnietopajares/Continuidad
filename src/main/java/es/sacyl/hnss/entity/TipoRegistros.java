package es.sacyl.hnss.entity;

/**
 * The Class TipoRegistros. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class TipoRegistros {

    private Long plantilla_editor;

    private String descripcion;

    private Long tipo_registro;

    private Centro centro;

    private Servicio servicio;

    /**
     * Instantiates a new tipo registros.
     */
    public TipoRegistros() {

    }

    /**
     * Instantiates a new tipo registros.
     *
     * @param plantilla_editor the plantilla editor
     * @param descripcion the descripcion
     * @param tipo_registro the tipo registro
     * @param centro the centro
     * @param servicio the servicio
     */
    public TipoRegistros(Long plantilla_editor, String descripcion, Long tipo_registro, Centro centro,
            Servicio servicio) {
        this.plantilla_editor = plantilla_editor;
        this.descripcion = descripcion;
        this.tipo_registro = tipo_registro;
        this.centro = centro;
        this.servicio = servicio;
    }

    /**
     * Gets the plantilla editor.
     *
     * @return the plantilla editor
     */
    public Long getPlantilla_editor() {
        return plantilla_editor;
    }

    /**
     * Sets the plantilla editor.
     *
     * @param plantilla_editor the new plantilla editor
     */
    public void setPlantilla_editor(Long plantilla_editor) {
        this.plantilla_editor = plantilla_editor;
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
     * Gets the tipo registro.
     *
     * @return the tipo registro
     */
    public Long getTipo_registro() {
        return tipo_registro;
    }

    /**
     * Sets the tipo registro.
     *
     * @param tipo_registro the new tipo registro
     */
    public void setTipo_registro(Long tipo_registro) {
        this.tipo_registro = tipo_registro;
    }

    /**
     * Gets the centro.
     *
     * @return the centro
     */
    public Centro getCentro() {
        return centro;
    }

    /**
     * Sets the centro.
     *
     * @param centro the new centro
     */
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    /**
     * Gets the servicio.
     *
     * @return the servicio
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * Sets the servicio.
     *
     * @param servicio the new servicio
     */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

}
