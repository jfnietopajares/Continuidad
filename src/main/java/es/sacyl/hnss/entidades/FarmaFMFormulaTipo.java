/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class FarmaFMFormulaTipo {

    public String codigo;
    public String descripcion;

    public static FarmaFMFormulaTipo FORMULAMAGISTRAL = new FarmaFMFormulaTipo("FM", "FOR.MAGISTRAL");
    public static FarmaFMFormulaTipo FORMULANORMALIZADA = new FarmaFMFormulaTipo("FN", "FOR.NORMALIZADA");

    public static ArrayList<FarmaFMFormulaTipo> LISTAFORMULASTIPOS = new ArrayList<FarmaFMFormulaTipo>() {
        {
            add(FarmaFMFormulaTipo.FORMULAMAGISTRAL);
            add(FarmaFMFormulaTipo.FORMULANORMALIZADA);
        }
    };

    public FarmaFMFormulaTipo(String codigo) {
        this.codigo = codigo;
        if (codigo.equals("FM")) {
            descripcion = "FOR.MAGISTRAL";
        }
        if (codigo.equals("FN")) {
            descripcion = "FOR.NORMALIZADA";
        }

    }

    public FarmaFMFormulaTipo(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
