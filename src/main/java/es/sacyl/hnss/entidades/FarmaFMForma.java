/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

/**
 *
 * @author JuanNieto
 */
public class FarmaFMForma {

    private String codigo;
    private String nombre;
    public static final String labelFrom = "Formas farmacéuticas ";

    public FarmaFMForma() {

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

}
