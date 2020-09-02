/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author JuanNieto
 */
public class FMFormulaCompo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer formula;
    private Integer orden;
    private BigDecimal cantidad;
    private String unidades;
    private FMMPrimas mprima;

    public static final String LABELFORM = "Composición de la fórmula ";

    public FMFormulaCompo() {

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

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public FMMPrimas getMprima() {
        return mprima;
    }

    public void setMprima(FMMPrimas mprima) {
        this.mprima = mprima;
    }

    public String getMprimaProducto() {
        if (mprima != null && mprima.getProducto()!=null) {
            return mprima.getProducto();
        } else {
            return "";
        }
    }

    public static String getLabelFrom() {
        return LABELFORM;
    }

}
