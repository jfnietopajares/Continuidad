/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

/**
 *
 * @author JuanNieto
 */
public class CitosMedicamentos {

    private Integer id;
    private PrActivo pr_activo;
    private String comercial;
    private String concentraciono;
    private String estabilidado;
    private String estabilidadiv;
    private String vesicante;
    private String siglas;
    private String csobrante;
    private String reconstitucion;
    private String observaciones;
    private String extravasacion;
    private String derrames;
    private String exposicion;
    private String obscsobrante;
    private String peligroso;
    public static final String LABELFORM = "Citostáticos Medicamentos  ";

    public CitosMedicamentos() {
        this.id = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PrActivo getPr_activo() {
        return pr_activo;
    }

    public void setPr_activo(PrActivo pr_activo) {
        this.pr_activo = pr_activo;
    }

 

    public String getComercial() {
        return comercial;
    }

    public void setComercial(String comercial) {
        this.comercial = comercial;
    }

    public String getConcentraciono() {
        return concentraciono;
    }

    public void setConcentraciono(String concentraciono) {
        this.concentraciono = concentraciono;
    }

    public String getEstabilidado() {
        return estabilidado;
    }

    public void setEstabilidado(String estabilidado) {
        this.estabilidado = estabilidado;
    }

    public String getEstabilidadiv() {
        return estabilidadiv;
    }

    public void setEstabilidadiv(String estabilidadiv) {
        this.estabilidadiv = estabilidadiv;
    }

    public String getVesicante() {
        return vesicante;
    }

    public void setVesicante(String vesicante) {
        this.vesicante = vesicante;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getCsobrante() {
        return csobrante;
    }

    public void setCsobrante(String csobrante) {
        this.csobrante = csobrante;
    }

    public String getReconstitucion() {
        return reconstitucion;
    }

    public void setReconstitucion(String reconstitucion) {
        this.reconstitucion = reconstitucion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getExtravasacion() {
        return extravasacion;
    }

    public void setExtravasacion(String extravasacion) {
        this.extravasacion = extravasacion;
    }

    public String getDerrames() {
        return derrames;
    }

    public void setDerrames(String derrames) {
        this.derrames = derrames;
    }

    public String getExposicion() {
        return exposicion;
    }

    public void setExposicion(String exposicion) {
        this.exposicion = exposicion;
    }

    public String getObscsobrante() {
        return obscsobrante;
    }

    public void setObscsobrante(String obscsobrante) {
        this.obscsobrante = obscsobrante;
    }

    public String getPeligroso() {
        return peligroso;
    }

    public void setPeligroso(String peligroso) {
        this.peligroso = peligroso;
    }

    public static String getLabelFrom() {
        return LABELFORM;
    }

    @Override
    public String toString() {
        return "CitosMedicamentos{" + "id=" + id + ", pr_activo=" + pr_activo + ", comercial=" + comercial + ", concentraciono=" + concentraciono + ", estabilidado=" + estabilidado + ", estabilidadiv=" + estabilidadiv + ", vesicante=" + vesicante + ", siglas=" + siglas + ", csobrante=" + csobrante + ", reconstitucion=" + reconstitucion + ", observaciones=" + observaciones + ", extravasacion=" + extravasacion + ", derrames=" + derrames + ", exposicion=" + exposicion + ", obscsobrante=" + obscsobrante + ", peligroso=" + peligroso + '}';
    }

}
