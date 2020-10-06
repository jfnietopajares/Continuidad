/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entity;

import java.sql.Clob;
import java.time.LocalDate;

/**
 *
 * @author 06551256M
 */
public class Alerta {

    private int numeroorden;
    private Long id;
    private Paciente paciente;
    private String gravedad;
    private LocalDate fechaalta;
    private Long horaalta;
    private LocalDate fechabaja;
    private Long horabaja;
    private Usuario userid;
    private String descripcion;
    private String certeza;
    private String comentario;
    private String motivo;
    private Proceso problema;
    private Long tipo;
    private Clob informe_externo;

    public static final Long ID_ALERTA_COVID19 = new Long(200116);

    public Alerta() {

    }

    public int getNumeroorden() {
        return numeroorden;
    }

    public void setNumeroorden(int numeroorden) {
        this.numeroorden = numeroorden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public LocalDate getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(LocalDate fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Long getHoraalta() {
        return horaalta;
    }

    public void setHoraalta(Long horaalta) {
        this.horaalta = horaalta;
    }

    public Long getHorabaja() {
        return horabaja;
    }

    public void setHorabaja(Long horabaja) {
        this.horabaja = horabaja;
    }

    public LocalDate getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(LocalDate fechabaja) {
        this.fechabaja = fechabaja;
    }

    public Usuario getUserid() {
        return userid;
    }

    public void setUserid(Usuario userid) {
        this.userid = userid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCerteza() {
        return certeza;
    }

    public void setCerteza(String certeza) {
        this.certeza = certeza;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Proceso getProblema() {
        return problema;
    }

    public void setProblema(Proceso problema) {
        this.problema = problema;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public Clob getInforme_externo() {
        return informe_externo;
    }

    public void setInforme_externo(Clob informe_externo) {
        this.informe_externo = informe_externo;
    }

}
