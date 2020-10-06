package es.sacyl.hnss.entity;

import java.io.Serializable;

/**
 * The Class Funcionalidad.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Funcionalidad implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5578289954884202010L;

    private int numeroOrden;

    private Long id;

    private String descripcion;

    private boolean activa;

    public final static Long MENU_MAMA_FUNCIONALIDAD = new Long(98);
    public final static Long MENU_COLON_FUNCIONALIDAD = new Long(99);
    public final static Long MENU_OXIGENO_FUNCIONALIDAD = new Long(100);
    public final static Long MENU_PALIATIVOS_FUNCIONALIDAD = new Long(101);
    public final static Long MENU_PALIATIVOS_PACIENTES = new Long(102);
    public final static Long MENU_REHABILITACION_FUNCIONALIDAD = new Long(103);
    public final static Long MENU_PARTOS_FUNCIONALIDAD = new Long(130);

    public final static Long MENU_ADMIN_FUNCIONALIDAD = new Long(126);
    public final static Long MENU_PACI_FUNCIONALIDAD = new Long(102);
    public final static Long MENU_HDIA_FUNCIONALIDAD = new Long(5);

    public final static Long MENU_CONSULTA_FUNCIONALIDAD = new Long(1);
    public final static Long MENU_INGRESADOS_FUNCIONALIDAD = new Long(2);
    public final static Long MENU_URGENCIAS_FUNCIONALIDAD = new Long(3);
    public final static Long MENU_QUIROFANO_FUNCIONALIDAD = new Long(4);

    public final static Long EVOLUTIVOS_FUNCIONALIDAD = new Long(15);

    /**
     * Instantiates a new funcionalidad.
     */
    public Funcionalidad() {
        this.id = new Long(0);
    }

    /**
     * Instantiates a new funcionalidad.
     *
     * @param id the id
     */
    public Funcionalidad(Long id) {
        this.setId(id);
    }

    /**
     * Instantiates a new funcionalidad.
     *
     * @param id the id
     * @param descripcion the descripcion
     * @param activa the activa
     */
    public Funcionalidad(Long id, String descripcion, boolean activa) {
        this.setId(id);
        this.setDescripcion(descripcion);
        this.activa = activa;
    }

    /**
     * Gets the numero orden.
     *
     * @return the numero orden
     */
    public int getNumeroOrden() {
        return numeroOrden;
    }

    /**
     * Sets the numero orden.
     *
     * @param numeroOrden the new numero orden
     */
    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
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
     * Gets the id string.
     *
     * @return the id string
     */
    public String getIdString() {
        return Long.toString(getId());
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
     * Sets the id string.
     *
     * @param id the new id string
     */
    public void setIdString(String id) {
        this.id = Long.parseLong(id);
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
     * Checks if is activa.
     *
     * @return true, if is activa
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Sets the activa.
     *
     * @param activa the new activa
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

}
