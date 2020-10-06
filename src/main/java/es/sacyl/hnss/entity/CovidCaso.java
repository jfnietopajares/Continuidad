/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entity;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author 06551256M
 */
public class CovidCaso {

    private LocalDate fecha;
    private String origen;
    private String idLocal;
    private ArrayList<CovidDireccion> direcciones;

    public CovidCaso() {
        this.direcciones = new ArrayList<>();

    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public ArrayList<CovidDireccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<CovidDireccion> direcciones) {
        this.direcciones = direcciones;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

}
