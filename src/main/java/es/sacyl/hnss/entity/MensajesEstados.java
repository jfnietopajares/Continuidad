package es.sacyl.hnss.entity;

import java.util.ArrayList;

/**
 * The Class MensajesEstados.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class MensajesEstados {

    private int estado;

    private String descripcion;

    public static ArrayList<MensajesEstados> LISTAMENSAJESESTADOS = new ArrayList<MensajesEstados>() {
        /**
         *
         */
        private static final long serialVersionUID = -6313598321160462599L;

        {
            add(new MensajesEstados(1, "Pendiente"));
            add(new MensajesEstados(2, "Enviado"));
            add(new MensajesEstados(3, "Error"));
        }
    };

    /**
     * Instantiates a new mensajes estados.
     *
     * @param estado the estado
     */
    public MensajesEstados(int estado) {
        this.estado = estado;

    }

    /**
     * Instantiates a new mensajes estados.
     *
     * @param estado the estado
     * @param des the des
     */
    public MensajesEstados(int estado, String des) {
        this.estado = estado;
        this.descripcion = des;

    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
