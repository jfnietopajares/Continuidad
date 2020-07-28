/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author JuanNieto
 */
public class FMFormulaFrabicar implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer numero;
    private LocalDate fecha;
    private FMFormula formula;
    private String medico;
    private String paciente;
    private Integer caducidad;
    private Integer registro;
    private String lote;
    private String ccalidad;
    private Integer unidades;
    private String preparado;
    private String farmaceutico;
    private BigDecimal formulas;
    private String ndescripcion;
    private String observaciones;
    private Integer uni_dispen;

    public static final String labelFrom = "Fabricación de la fórmula ";

    public FMFormulaFrabicar() {

    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public FMFormula getFormula() {
        return formula;
    }

    public void setFormula(FMFormula formula) {
        this.formula = formula;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Integer getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Integer caducidad) {
        this.caducidad = caducidad;
    }

    public Integer getRegistro() {
        return registro;
    }

    public void setRegistro(Integer registro) {
        this.registro = registro;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getCcalidad() {
        return ccalidad;
    }

    public void setCcalidad(String ccalidad) {
        this.ccalidad = ccalidad;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public String getPreparado() {
        return preparado;
    }

    public void setPreparado(String preparado) {
        this.preparado = preparado;
    }

    public String getFarmaceutico() {
        return farmaceutico;
    }

    public void setFarmaceutico(String farmaceutico) {
        this.farmaceutico = farmaceutico;
    }

    public BigDecimal getFormulas() {
        return formulas;
    }

    public void setFormulas(BigDecimal formulas) {
        this.formulas = formulas;
    }

    public String getNdescripcion() {
        return ndescripcion;
    }

    public void setNdescripcion(String ndescripcion) {
        this.ndescripcion = ndescripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getUni_dispen() {
        return uni_dispen;
    }

    public void setUni_dispen(Integer uni_dispen) {
        this.uni_dispen = uni_dispen;
    }

    public static String getLabelFrom() {
        return labelFrom;
    }

}
