package es.sacyl.hnss.entity;

import java.sql.Blob;
import java.sql.Clob;

public class Campos_i {

    private Long id;
    private Long informe;
    private Long item;
    private String userid;
    private Long fecha;
    private Long hora;
    private String descripcion;
    private String unidades;
    private String referencias;
    private String flags;
    private Clob dato;
    private String codigo;
    private String tipo_codigo;
    private int estado;
    private int pegado_en_informe;
    private Blob datobin;
    private int nivel_visibilidad;
    private Long lateralidad;
    private Long itemvalor;
    private Long canal;
    private Long prueba;

    public Campos_i() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInforme() {
        return informe;
    }

    public void setInforme(Long informe) {
        this.informe = informe;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public Clob getDato() {
        return dato;
    }

    public void setDato(Clob dato) {
        this.dato = dato;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo_codigo() {
        return tipo_codigo;
    }

    public void setTipo_codigo(String tipo_codigo) {
        this.tipo_codigo = tipo_codigo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPegado_en_informe() {
        return pegado_en_informe;
    }

    public void setPegado_en_informe(int pegado_en_informe) {
        this.pegado_en_informe = pegado_en_informe;
    }

    public Blob getDatobin() {
        return datobin;
    }

    public void setDatobin(Blob datobin) {
        this.datobin = datobin;
    }

    public int getNivel_visibilidad() {
        return nivel_visibilidad;
    }

    public void setNivel_visibilida(int nivel_visibilidad) {
        this.nivel_visibilidad = nivel_visibilidad;
    }

    public Long getLateralidad() {
        return lateralidad;
    }

    public void setLateralidad(Long lateralidad) {
        this.lateralidad = lateralidad;
    }

    public Long getItemvalor() {
        return itemvalor;
    }

    public void setItemvalor(Long itemvalor) {
        this.itemvalor = itemvalor;
    }

    public Long getCanal() {
        return canal;
    }

    public void setCanal(Long canal) {
        this.canal = canal;
    }

    public Long getPrueba() {
        return prueba;
    }

    public void setPrueba(Long prueba) {
        this.prueba = prueba;
    }

}
