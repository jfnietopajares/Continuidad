/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class FMFormula {

    private Integer numero;
    private String nombre;
    private FMFormulaTipo tipo;
    private String esteril;
    private FMForma forma;
    private FMViasAdm via;
    private Integer unidades_f;
    private String indicacion;
    private String conservacion;
    private String caducidad;
    private String controles;
    private String dunidades_f;
    private String indicacion1;
    private String observaciones;
    private FMFormulaAutoriza p_autorizado;
    private String pedirweb;
    private String realizado;
    private LocalDate fecha_r;
    private String actualizado;
    private LocalDate fecha_a;
    private String hoja_paci;
    private Blob hoja_paci_fichero;
    private String etiqueta1;
    private String etiqueta2;
    private String observaciones1;

    private ArrayList<FMFormulaBibliografia> lisaBibliografias = new ArrayList<>();

    public static final String labelFrom = "Fórmulas magistrales  ";

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public FMFormulaTipo getTipo() {
        return tipo;
    }

    public void setTipo(FMFormulaTipo tipo) {
        this.tipo = tipo;
    }

    public String getEsteril() {
        return esteril;
    }

    public void setEsteril(String esteril) {
        this.esteril = esteril;
    }

    public FMForma getForma() {
        return forma;
    }

    public void setForma(FMForma forma) {
        this.forma = forma;
    }

    public FMViasAdm getVia() {
        return via;
    }

    public void setVia(FMViasAdm via) {
        this.via = via;
    }

    public Integer getUnidades_f() {
        return unidades_f;
    }

    public void setUnidades_f(Integer unidades_f) {
        this.unidades_f = unidades_f;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }

    public String getConservacion() {
        return conservacion;
    }

    public void setConservacion(String conservacion) {
        this.conservacion = conservacion;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getControles() {
        return controles;
    }

    public void setControles(String controles) {
        this.controles = controles;
    }

    public String getDunidades_f() {
        return dunidades_f;
    }

    public void setDunidades_f(String dunidades_f) {
        this.dunidades_f = dunidades_f;
    }

    public String getIndicacion1() {
        return indicacion1;
    }

    public void setIndicacion1(String indicacion1) {
        this.indicacion1 = indicacion1;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public FMFormulaAutoriza getP_autorizado() {
        return p_autorizado;
    }

    public void setP_autorizado(FMFormulaAutoriza p_autorizado) {
        this.p_autorizado = p_autorizado;
    }

    public String getPedirweb() {
        return pedirweb;
    }

    public void setPedirweb(String pedirweb) {
        this.pedirweb = pedirweb;
    }

    public String getRealizado() {
        return realizado;
    }

    public void setRealizado(String realizado) {
        this.realizado = realizado;
    }

    public LocalDate getFecha_r() {
        return fecha_r;
    }

    public void setFecha_r(LocalDate fecha_r) {
        this.fecha_r = fecha_r;
    }

    public String getActualizado() {
        return actualizado;
    }

    public void setActualizado(String actualizado) {
        this.actualizado = actualizado;
    }

    public LocalDate getFecha_a() {
        return fecha_a;
    }

    public void setFecha_a(LocalDate fecha_a) {
        this.fecha_a = fecha_a;
    }

    public String getHoja_paci() {
        return hoja_paci;
    }

    public void setHoja_paci(String hoja_paci) {
        this.hoja_paci = hoja_paci;
    }

    public Blob getHoja_paci_fichero() {
        return hoja_paci_fichero;
    }

    public void setHoja_paci_fichero(Blob hoja_paci_fichero) {
        this.hoja_paci_fichero = hoja_paci_fichero;
    }

    public String getEtiqueta1() {
        return etiqueta1;
    }

    public void setEtiqueta1(String etiqueta1) {
        this.etiqueta1 = etiqueta1;
    }

    public String getEtiqueta2() {
        return etiqueta2;
    }

    public void setEtiqueta2(String etiqueta2) {
        this.etiqueta2 = etiqueta2;
    }

    public String getObservaciones1() {
        return observaciones1;
    }

    public void setObservaciones1(String observaciones1) {
        this.observaciones1 = observaciones1;
    }

    public static String getLabelFrom() {
        return labelFrom;
    }

    public ArrayList<FMFormulaBibliografia> getLisaBibliografias() {
        return lisaBibliografias;
    }

    public void setLisaBibliografias(ArrayList<FMFormulaBibliografia> lisaBibliografias) {
        this.lisaBibliografias = lisaBibliografias;
    }

}
