package es.sacyl.hnss.entity;

public class Agenda {

    private Long id;
    private Centro centro;
    private String codigo;
    private String descripcion;
    private Servicio servicio;

    private String subservicio;
    private int activa;

    public Agenda() {
        this.id = new Long(0);
    }

    public Agenda(Long id, Centro centro, String codigo, String descripcion, Servicio servicio) {
        this.id = id;
        this.centro = centro;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.servicio = servicio;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getSubservicio() {
        return subservicio;
    }

    public void setSubservicio(String subservicio) {
        this.subservicio = subservicio;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

}
