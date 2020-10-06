package es.sacyl.hnss.entity;

public class EpisodioClase {

    private Long id;
    private String descripcion;
    private int tipoZona;
    private boolean andirActivo;

    public EpisodioClase() {
    }

    public EpisodioClase(Long id) {
        this.id = id;
    }

    public EpisodioClase(Long id, String des, int tipoZona, boolean andirActivo) {
        this.id = id;
        this.descripcion = des;
        this.tipoZona = tipoZona;
        this.andirActivo = andirActivo;
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

    public int getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(int tipoZona) {
        this.tipoZona = tipoZona;
    }

    public boolean isAndirActivo() {
        return andirActivo;
    }

    public void setAndirActivo(boolean andirActivo) {
        this.andirActivo = andirActivo;
    }

    public String toString() {
        return this.getId() + " " + this.getDescripcion() + " " + this.getTipoZona();
    }
}
