/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entity;

/**
 *
 * @author 06551256M
 */
public class Ambito {

    private String codigo;
    public static String AMBITO_CEX = "CEX";
    public static String AMBITO_QUI = "QUI";
    public static String AMBITO_HOS = "HOS";
    public static String AMBITO_URG = "URG";

    public Ambito() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
