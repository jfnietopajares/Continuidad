/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

import java.time.LocalDate;

/**
 *
 * @author JuanNieto
 */
public class FarmaFMMprimasSalidas extends FarmaFMMPrimas {

    private FarmaFMMPrimas mprima;
    private Integer numero;
    private String producto;
    private LocalDate fecha;
    private Integer cantidad;
    private String comentario1;

    public FarmaFMMprimasSalidas() {

    }

    public FarmaFMMPrimas getMprima() {
        return mprima;
    }

    public void setMprima(FarmaFMMPrimas mprima) {
        this.mprima = mprima;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getComentario1() {
        return comentario1;
    }

    public void setComentario1(String comentario1) {
        this.comentario1 = comentario1;
    }

    @Override
    public String toString() {
        return "FarmaFMMprimasSalidas{" + super.toString() + "mprima=" + mprima + ", numero=" + numero + ", producto=" + producto + ", fecha=" + fecha + ", cantidad=" + cantidad + ", comentario1=" + comentario1 + '}';
    }

}
