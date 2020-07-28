/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

import static es.sacyl.hnss.entidades.FMFormulaBibliografia.labelFrom;
import java.io.Serializable;

/**
 *
 * @author JuanNieto
 */
public class FMFormulaElabora implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer formula;
    private Integer orden;
    private String texto;

    public static final String labelFrom = "Elaboración de la fórmula ";

    public FMFormulaElabora() {

    }

    public Integer getFormula() {
        return formula;
    }

    public void setFormula(Integer formula) {
        this.formula = formula;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public static String getLabelFrom() {
        return labelFrom;
    }

}
