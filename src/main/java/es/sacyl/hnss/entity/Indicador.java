package es.sacyl.hnss.entity;

import java.util.Comparator;

/**
 * The Class Indicador.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Indicador implements Comparator<Object> {

    /**
     * The ano.
     */
    private int ano;

    /**
     * The mes.
     */
    private int mes;

    /**
     * The altas.
     */
    private int altas;

    /**
     * The bajas.
     */
    private int bajas;

    /**
     * Instantiates a new indicador.
     *
     * @param ano the ano
     * @param mes the mes
     */
    public Indicador(int ano, int mes) {
        this.mes = mes;
        this.ano = ano;
        this.altas = 0;
        this.bajas = 0;
    }

    /**
     * Gets the ano.
     *
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * Sets the ano.
     *
     * @param ano the new ano
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Gets the mes.
     *
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * Sets the mes.
     *
     * @param mes the new mes
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Gets the altas.
     *
     * @return the altas
     */
    public int getAltas() {
        return altas;
    }

    /**
     * Sets the altas.
     *
     * @param altas the new altas
     */
    public void setAltas(int altas) {
        this.altas = altas;
    }

    /**
     * Gets the bajas.
     *
     * @return the bajas
     */
    public int getBajas() {
        return bajas;
    }

    /**
     * Sets the bajas.
     *
     * @param bajas the new bajas
     */
    public void setBajas(int bajas) {
        this.bajas = bajas;
    }

    /**
     * Compare.
     *
     * @param indicador1 the indicador 1
     * @param indicador2 the indicador 2
     * @return the int
     */
    @Override
    public int compare(Object indicador1, Object indicador2) {
        Indicador i1 = (Indicador) indicador1;
        Indicador i2 = (Indicador) indicador2;
        if (i1.getAno() == i2.getAno() && i1.getMes() > i2.getMes()) {
            return 1;
        } else if (i1.getAno() > i2.getAno()) {
            return 1;
        }
        return 0;
    }
}
