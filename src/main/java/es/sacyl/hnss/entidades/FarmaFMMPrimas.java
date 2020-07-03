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
public class FarmaFMMPrimas {

    private Integer cod_inte;
    private String producto;
    private String cod_labo;
    private String laboratorio;
    private Boolean homologado;
    private Integer n_labo;
    private Integer stock_min;
    private String observaciones;
    private String especifica;
    private LocalDate ulti_revi;
    private String farmacetuico;
    private Integer existencias;
    private String nlaboratorio;
    private String presentacion;
    private String descripcion;
    private String requisitos;
    private String conservacion;

    public static final String labelFrom = "Materias primas ";

    public FarmaFMMPrimas() {
        /*
        cod_inte = 0;
        producto = "";
        cod_labo = "";
        laboratorio = "";
        homologado = "";
        n_labo = 0;
        stock_min = 0;
        observaciones = "";
        especifica = "";
        //private LocalDate ulti_revi;
        farmacetuico = "";
        existencias = 0;
        nlaboratorio = "";
        presentacion = "";
        descripcion = "";
        requisitcos = "";
        conservacion = "";
         */
    }

    public static String getLabelFrom() {
        return labelFrom;
    }

    public Integer getCod_inte() {
        return cod_inte;
    }

    public void setCod_inte(Integer cod_inte) {
        this.cod_inte = cod_inte;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCod_labo() {
        return cod_labo;
    }

    public void setCod_labo(String cod_labo) {
        this.cod_labo = cod_labo;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Boolean getHomologado() {
        return homologado;
    }

    public void setHomologado(Boolean homologado) {
        this.homologado = homologado;
    }

    public Integer getN_labo() {
        return n_labo;
    }

    public void setN_labo(Integer n_labo) {
        this.n_labo = n_labo;
    }

    public Integer getStock_min() {
        return stock_min;
    }

    public void setStock_min(Integer stock_min) {
        this.stock_min = stock_min;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEspecifica() {
        return especifica;
    }

    public void setEspecifica(String especifica) {
        this.especifica = especifica;
    }

    public LocalDate getUlti_revi() {
        return ulti_revi;
    }

    public void setUlti_revi(LocalDate ulti_revi) {
        this.ulti_revi = ulti_revi;
    }

    public String getFarmacetuico() {
        return farmacetuico;
    }

    public void setFarmacetuico(String farmacetuico) {
        this.farmacetuico = farmacetuico;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public String getNlaboratorio() {
        return nlaboratorio;
    }

    public void setNlaboratorio(String nlaboratorio) {
        this.nlaboratorio = nlaboratorio;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getConservacion() {
        return conservacion;
    }

    public void setConservacion(String conservacion) {
        this.conservacion = conservacion;
    }

}
