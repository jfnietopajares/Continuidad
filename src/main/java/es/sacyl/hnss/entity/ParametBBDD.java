package es.sacyl.hnss.entity;

/**
 * The Class Parametro.
 *
 * *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class ParametBBDD {

    private int numeroOrden;

    private Long id;

    private String codigo;

    private String descripcion;

    private String valor;

    private int canal;

    public static final String MENSAJES_CANALES = "mensajes.canales";

    public static final String MENSAJES_MAMA_CORREO_ENVIOS = "mensajes.mama.mail";

    public static final String MENSAJES_MAMA_PANTALLA = "mensajes.mama.pantalla";

    public static final String MENSAJES_MAMA_WHATSAPP = "mensajes.mama.whatsapp";

    public static final String URL_WHATSAPP = "url.whatsapp";

    public static final String CLAVE_APLICACIONES = "app.externa.clave";
    public static final String URL_JIMENA = "url.jimena";

    /**
     * Instantiates a new parametro.
     */
    public ParametBBDD() {
        this.id = new Long(0);
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
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the string id.
     *
     * @return the string id
     */
    public String getStringId() {
        return Long.toString(getId());
    }

    /**
     * Sets the string id.
     *
     * @param id the new string id
     */
    public void setStringId(String id) {
        this.id = Long.parseLong(id);
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
     * Gets the valor.
     *
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * Sets the valor.
     *
     * @param valor the new valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Gets the canal.
     *
     * @return the canal
     */
    public int getCanal() {
        return canal;
    }

    /**
     * Sets the canal.
     *
     * @param canal the new canal
     */
    public void setCanal(int canal) {
        this.canal = canal;
    }

}
