/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class FMFormulaAutoriza implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String descripcion;

    public static FMFormulaAutoriza AUTORIZAAXUSILIAR = new FMFormulaAutoriza("AUX", "AUXILIAR");
    public static FMFormulaAutoriza AUTORIZAENFERMERA = new FMFormulaAutoriza("ENF", "ENFERMERA");
    public static FMFormulaAutoriza AUTORIZAFARMACEUTICO = new FMFormulaAutoriza("FAR", "FARMACEUTICO");

    public static ArrayList<FMFormulaAutoriza> LISTAAUTORIZA = new ArrayList<FMFormulaAutoriza>() {
        {
            add(FMFormulaAutoriza.AUTORIZAAXUSILIAR);
            add(FMFormulaAutoriza.AUTORIZAENFERMERA);
            add(FMFormulaAutoriza.AUTORIZAFARMACEUTICO);
        }
    };

    public FMFormulaAutoriza(String codigo) {
        this.codigo = codigo.trim();
        if (this.codigo.equals("AUX")) {
            descripcion = "AUXILIAR";
        } else if (this.codigo.equals("ENF")) {
            descripcion = "FOR.NORMALIZADA";
        } else if (this.codigo.equals("FAR")) {
            descripcion = "FARMACEUTICO";
        }
    }

    public FMFormulaAutoriza(String codigo, String descripcion) {
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
