/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entity;

import java.sql.Blob;

/**
 *
 * @author 06551256M
 */
public class Impresora {

    private Long id;
    private String ip;
    private Long puerto;
    private Long tipo;
    private String descripcion;
    private Blob plantilla;
    private Zona zona;

    public static final Long IMPRESORA_TIPO_FIRMA = new Long(4);

    public static final Impresora IMPRESORA_URG_HOJASENF_POLIVALENTE = new Impresora("10.37.12.194");

    public Impresora() {

    }

    public Impresora(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getPuerto() {
        return puerto;
    }

    public void setPuerto(Long puerto) {
        this.puerto = puerto;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Blob getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Blob plantilla) {
        this.plantilla = plantilla;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

}
