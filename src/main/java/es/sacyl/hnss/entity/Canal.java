package es.sacyl.hnss.entity;

public class Canal {

    private Long id;
    private String descripcion;

    public final static long CANAL_LABORATORIO = 5;

    public final static long CANAL_RADIOLOGIA = 8;
    public final static long CANAL_ANATOMIA = 2;
    public final static long CANAL_JIMENA = 6;

    public Canal() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
