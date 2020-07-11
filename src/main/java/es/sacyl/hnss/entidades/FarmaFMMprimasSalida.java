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
public class FarmaFMMprimasSalida extends FarmaFMMPrimas {

    private Integer numero;
    private String producto;
    private LocalDate fecha;
    private Integer cantidad;
    private String comentario1;

    public static final String labelFrom = "Salidas de Materias primas ";

    public FarmaFMMprimasSalida() {
        // super();
    }

    public FarmaFMMprimasSalida(FarmaFMMPrimas farmaFMMPrimas) {
        //super();
        this.setCod_inte(farmaFMMPrimas.getCod_inte());
        this.setProducto(farmaFMMPrimas.getProducto());
        this.setCod_labo(farmaFMMPrimas.getCod_labo());
        this.setLaboratorio(farmaFMMPrimas.getLaboratorio());
        this.setHomologado(farmaFMMPrimas.getHomologado());
        this.setN_labo(farmaFMMPrimas.getN_labo());
        this.setStock_min(farmaFMMPrimas.getStock_min());
        this.setObservaciones(farmaFMMPrimas.getObservaciones());
        this.setEspecifica(farmaFMMPrimas.getEspecifica());
        this.setUlti_revi(farmaFMMPrimas.getUlti_revi());
        this.setFarmacetuico(farmaFMMPrimas.getFarmacetuico());
        this.setExistencias(farmaFMMPrimas.getExistencias());
        this.setNlaboratorio(farmaFMMPrimas.getNlaboratorio());
        this.setPresentacion(farmaFMMPrimas.getPresentacion());
        this.setDescripcion(farmaFMMPrimas.getDescripcion());
        this.setRequisitos(farmaFMMPrimas.getRequisitos());
        this.setConservacion(farmaFMMPrimas.getConservacion());
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
    public void setDatosMprima(FarmaFMMPrimas farmaFMMPrimas) {
        this.setCod_inte(farmaFMMPrimas.getCod_inte());
        this.setProducto(farmaFMMPrimas.getProducto());
        this.setCod_labo(farmaFMMPrimas.getCod_labo());
        this.setLaboratorio(farmaFMMPrimas.getLaboratorio());
        this.setHomologado(farmaFMMPrimas.getHomologado());
        this.setN_labo(farmaFMMPrimas.getN_labo());
        this.setStock_min(farmaFMMPrimas.getStock_min());
        this.setObservaciones(farmaFMMPrimas.getObservaciones());
        this.setEspecifica(farmaFMMPrimas.getEspecifica());
        this.setUlti_revi(farmaFMMPrimas.getUlti_revi());
        this.setFarmacetuico(farmaFMMPrimas.getFarmacetuico());
        this.setExistencias(farmaFMMPrimas.getExistencias());
        this.setNlaboratorio(farmaFMMPrimas.getNlaboratorio());
        this.setPresentacion(farmaFMMPrimas.getPresentacion());
        this.setDescripcion(farmaFMMPrimas.getDescripcion());
        this.setRequisitos(farmaFMMPrimas.getRequisitos());
        this.setConservacion(farmaFMMPrimas.getConservacion());
    }
     */
}
