/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author JuanNieto
 */
public class FMMprimasSalida extends FMMPrimas implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer numero;
    private String producto;
    private LocalDate fecha;
    private Integer cantidad;
    private String comentario1;

    public static final String labelFrom = "Salidas de Materias primas ";

    public FMMprimasSalida() {
        // super();
    }

    public FMMprimasSalida(FMMPrimas fMMPrimas) {
        //super();
        this.setCod_inte(fMMPrimas.getCod_inte());
        this.setProducto(fMMPrimas.getProducto());
        this.setCod_labo(fMMPrimas.getCod_labo());
        this.setLaboratorio(fMMPrimas.getLaboratorio());
        this.setHomologado(fMMPrimas.getHomologado());
        this.setN_labo(fMMPrimas.getN_labo());
        this.setStock_min(fMMPrimas.getStock_min());
        this.setObservaciones(fMMPrimas.getObservaciones());
        this.setEspecifica(fMMPrimas.getEspecifica());
        this.setUlti_revi(fMMPrimas.getUlti_revi());
        this.setFarmacetuico(fMMPrimas.getFarmacetuico());
        this.setExistencias(fMMPrimas.getExistencias());
        this.setNlaboratorio(fMMPrimas.getNlaboratorio());
        this.setPresentacion(fMMPrimas.getPresentacion());
        this.setDescripcion(fMMPrimas.getDescripcion());
        this.setRequisitos(fMMPrimas.getRequisitos());
        this.setConservacion(fMMPrimas.getConservacion());
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCodigoNumero() {
        String cadena = "";
        if (getCod_inte() != null) {
            cadena = cadena.concat(getCod_inte() + "/");
        }
        if (numero != null) {
            cadena = cadena.concat(Integer.toString(numero));
        }
        return cadena;
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

    public Integer getVariacionExistencias(Integer existenciasAnteriores, String accion) {
        Integer resultado = 0;
        switch (accion) {
            case "GRABAR":
                if (existenciasAnteriores == null) {
                    // nuevo registro se resta  las existencias
                    resultado = this.getCantidad() * (-1);
                } else if (existenciasAnteriores > this.getCantidad()) {
                    //ha modificado las existencias disnminuyendo el valor de la salida
                    resultado = existenciasAnteriores + this.getCantidad();
                } else {
                    // no se han modificado las existencias
                    resultado = 0;
                }
                break;
            case "BORRAR":
                // se suman  las existencias de la salida
                resultado = this.getCantidad();
                break;
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "FarmaFMMprimasSalidas{" + super.toString() + ", numero=" + numero + ", producto=" + producto + ", fecha=" + fecha + ", cantidad=" + cantidad + ", comentario1=" + comentario1 + '}';
    }

    /*
    public void setDatosMprima(FMMPrimas fMMPrimas) {
        this.setCod_inte(fMMPrimas.getCod_inte());
        this.setProducto(fMMPrimas.getProducto());
        this.setCod_labo(fMMPrimas.getCod_labo());
        this.setLaboratorio(fMMPrimas.getLaboratorio());
        this.setHomologado(fMMPrimas.getHomologado());
        this.setN_labo(fMMPrimas.getN_labo());
        this.setStock_min(fMMPrimas.getStock_min());
        this.setObservaciones(fMMPrimas.getObservaciones());
        this.setEspecifica(fMMPrimas.getEspecifica());
        this.setUlti_revi(fMMPrimas.getUlti_revi());
        this.setFarmacetuico(fMMPrimas.getFarmacetuico());
        this.setExistencias(fMMPrimas.getExistencias());
        this.setNlaboratorio(fMMPrimas.getNlaboratorio());
        this.setPresentacion(fMMPrimas.getPresentacion());
        this.setDescripcion(fMMPrimas.getDescripcion());
        this.setRequisitos(fMMPrimas.getRequisitos());
        this.setConservacion(fMMPrimas.getConservacion());
    }
     */
}
