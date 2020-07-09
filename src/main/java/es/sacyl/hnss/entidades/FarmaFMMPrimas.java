package es.sacyl.hnss.entidades;

import java.time.LocalDate;

/**
 *
 * @author JuanNieto
 */
public class FarmaFMMPrimas {

    protected Integer cod_inte;
    protected String producto;
    protected String cod_labo;
    protected String laboratorio;
    protected Boolean homologado;
    protected Integer n_labo;
    protected Integer stock_min;
    protected String observaciones;
    protected String especifica;
    protected LocalDate ulti_revi;
    protected String farmacetuico;
    protected Integer existencias;
    protected String nlaboratorio;
    protected String presentacion;
    protected String descripcion;
    protected String requisitos;
    protected String conservacion;

    public static final String labelFrom = "Materias primas ";

    public FarmaFMMPrimas() {
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

    @Override
    public String toString() {
        return "FarmaFMMPrimas{" + "cod_inte=" + cod_inte + ", producto=" + producto + ", cod_labo=" + cod_labo + ", laboratorio=" + laboratorio + ", homologado=" + homologado + ", n_labo=" + n_labo + ", stock_min=" + stock_min + ", observaciones=" + observaciones + ", especifica=" + especifica + ", ulti_revi=" + ulti_revi + ", farmacetuico=" + farmacetuico + ", existencias=" + existencias + ", nlaboratorio=" + nlaboratorio + ", presentacion=" + presentacion + ", descripcion=" + descripcion + ", requisitos=" + requisitos + ", conservacion=" + conservacion + '}';
    }

}
