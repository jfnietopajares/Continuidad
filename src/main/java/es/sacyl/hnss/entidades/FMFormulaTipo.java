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
public class FMFormulaTipo {

    public String codigo;
    public String descripcion;

    public static FMFormulaTipo FORMULAMAGISTRAL = new FMFormulaTipo("FM", "FOR.MAGISTRAL");
    public static FMFormulaTipo FORMULANORMALIZADA = new FMFormulaTipo("FN", "FOR.NORMALIZADA");

    public static ArrayList<FMFormulaTipo> LISTAFORMULASTIPOS = new ArrayList<FMFormulaTipo>() {
        {
            add(FMFormulaTipo.FORMULAMAGISTRAL);
            add(FMFormulaTipo.FORMULANORMALIZADA);
        }
    };

    public FMFormulaTipo(String codigo) {
        this.codigo = codigo;
        if (codigo.equals("FM")) {
            descripcion = "FOR.MAGISTRAL";
        }
        if (codigo.equals("FN")) {
            descripcion = "FOR.NORMALIZADA";
        }

    }

    public FMFormulaTipo(String codigo, String descripcion) {
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
