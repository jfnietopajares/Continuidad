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
public class CitosDiluciones {
 
    
    private Integer id;
    private String base;
    private String concentracion;
    private Integer volumen;
    private String volumenu;
    
    private String tipovolumen  ;

    private String presentacion ;
    private String 
	observaciones	;

 public static final String labelFrom = "Citostáticos diluciones  ";

    public CitosDiluciones(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public Integer getVolumen() {
        return volumen;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }

    public String getVolumenu() {
        return volumenu;
    }

    public void setVolumenu(String volumenu) {
        this.volumenu = volumenu;
    }

    public String getTipovolumen() {
        return tipovolumen;
    }

    public void setTipovolumen(String tipovolumen) {
        this.tipovolumen = tipovolumen;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


    @Override
    public String toString() {
        return "CitoDiluciones{" + "id=" + id + ", base=" + base + ", concentracion=" + concentracion + ", volumen=" + volumen + ", volumenu=" + volumenu + ", tipovolumen=" + tipovolumen + ", presentacion=" + presentacion + ", observaciones=" + observaciones + '}';
    }
    
}
