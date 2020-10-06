package es.sacyl.hnss.entity;

import java.util.ArrayList;

/**
 * The Class MensajesTipos. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class MensajesTipos {

    /**
     * The tipo.
     */
    private int tipo;

    /**
     * The descripcion.
     */
    private String descripcion;

    /**
     * The listamensajestipos.
     */
    public static ArrayList<MensajesTipos> LISTAMENSAJESTIPOS = new ArrayList<MensajesTipos>() {
        /**
         *
         */
        private static final long serialVersionUID = 7773710983011565775L;

        {
            add(new MensajesTipos(1, "MAIL"));
            add(new MensajesTipos(2, "SMS"));
            add(new MensajesTipos(3, "WHATSAPP"));
            add(new MensajesTipos(4, "Pantalla"));
        }
    };

    /**
     * Instantiates a new mensajes tipos.
     *
     * @param tipo the tipo
     */
    public MensajesTipos(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Instantiates a new mensajes tipos.
     *
     * @param tipo the tipo
     * @param des the des
     */
    public MensajesTipos(int tipo, String des) {
        this.tipo = tipo;
        this.descripcion = des;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
