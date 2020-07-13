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
public class FarmaFMFormulaAutoriza {

    private String codigo;
    private String descripcion;

    public static FarmaFMFormulaAutoriza AUTORIZAAXUSILIAR = new FarmaFMFormulaAutoriza("AUX", "AUXILIAR");
    public static FarmaFMFormulaAutoriza AUTORIZAENFERMERA = new FarmaFMFormulaAutoriza("ENF", "ENFERMERA");
    public static FarmaFMFormulaAutoriza AUTORIZAFARMACEUTICO = new FarmaFMFormulaAutoriza("FAR", "FARMACEUTICO");

    public static ArrayList<FarmaFMFormulaAutoriza> LISTAAUTORIZA = new ArrayList<FarmaFMFormulaAutoriza>() {
        {
            add(FarmaFMFormulaAutoriza.AUTORIZAAXUSILIAR);
            add(FarmaFMFormulaAutoriza.AUTORIZAENFERMERA);
            add(FarmaFMFormulaAutoriza.AUTORIZAFARMACEUTICO);
        }
    };

    public FarmaFMFormulaAutoriza(String codigo) {
        this.codigo = codigo;
        if (codigo.equals("AUX")) {
            descripcion = "AUXILIAR";
        } else if (codigo.equals("ENF")) {
            descripcion = "FOR.NORMALIZADA";
        } else if (codigo.equals("FAR")) {
            descripcion = "FARMACEUTICO";
        }
    }

    public FarmaFMFormulaAutoriza(String codigo, String descripcion) {
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
