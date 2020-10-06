package es.sacyl.hnss.entity;

public class GruposCatalogo {

    private Long id;
    private String grupo;
    private String descripcion;
    private Long catalogo;
    private Integer orden;
    private Long pertence;
    private String rama;
    private String valor;

    public static final String GRUPO_PRUEBASCUTANEAS_PEDIATRIA = "PRUEBASCUTANEAS";
    public static final String GRUPO_PRUEBASCUTANEAS_DERMA = "PRUEBASCUTANEASDER";
    public static final String GRUPO_PRUEBASCUTANEAS_ALERGIA = "PRUEBASCUTANEASALG";

    public GruposCatalogo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Long catalogo) {
        this.catalogo = catalogo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Long getPertence() {
        return pertence;
    }

    public void setPertence(Long pertence) {
        this.pertence = pertence;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "GruposCatalogo [id=" + id + ", grupo=" + grupo + ", descripcion=" + descripcion + ", catalogo="
                + catalogo + ", orden=" + orden + ", pertence=" + pertence + ", rama=" + rama + ", valor=" + valor
                + "]";
    }

}
