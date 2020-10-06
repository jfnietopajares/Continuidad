package es.sacyl.hnss.entity;

public class Combo {

    private Long id;
    private String descripcion;
    private Integer orden;
    private Long pertenece;
    private String rama;
    private String valor;
    private String grupo;

    public static String COMBO_QUIROFANOS_GRUPO = "QUIROFANOS";
    public static String COMBO_QUIROFANOS_RAMAHNSS = "HNSS";
    public static String COMBO_QUIROFANOS_RAMASERV = "SERVICIOSQUI";
    public static String COMBO_QUIROFANOS_PRGURG = "PRGURG";
    public static String COMBO_QUIROFANOS_VIASVENOSASTIPOS = "VIAVENOSA";
    public static String COMBO_QUIROFANOS_SONDAVESICAL = "SONDAVESICAL";
    public static String COMBO_QUIROFANOS_VIASCANALIZACIONES = "VIASCANLIZACIONES";
    public static String COMBO_QUIROFANOS_SONDAVESICALTIPOS = "SONDASJEVESICALESTIPOS";
    public static String COMBO_QUIROFANOS_SONDANASOGASTRICATIPOS = "SONDANASOGASTRICATIPO";
    public static String COMBO_QUIROFANOS_ANESTESIASTIPOS = "ANESTESIASTIPOS";
    public static String COMBO_QUIROFANOS_TUBOSMASCARILLAS = "TUBOSMASCARILLAS";
    public static String COMBO_QUIROFANOS_OXIGENOTERAPIA = "OXIGENOTERAPIA";
    public static String COMBO_QUIROFANOS_POSICIONPACIENTE = "POSICIONPACIENTE";
    public static String COMBO_QUIROFANOS_COMPLEMETOS = "COMPLEMETOS";
    public static String COMBO_QUIROFANOS_PLACABISTURI = "PLACABISTURI";

    public static String COMBO_QUIROFANOS_INSTRUMENTAL_TRA = "INSTRUMENTAL_TRUMA";
    public static String COMBO_QUIROFANOS_INSTRUMENTAL_GIN = "INSTRUMENTAL_GINE";
    public static String COMBO_QUIROFANOS_INSTRUMENTAL_DER = "INSTRUMENTAL_DEMA";
    public static String COMBO_QUIROFANOS_INSTRUMENTAL_OFT = "INSTRUMENTAL_OFT";
    public static String COMBO_QUIROFANOS_INSTRUMENTAL_ORL = "INSTRUMENTAL_ORL";
    public static String COMBO_QUIROFANOS_INSTRUMENTAL_URO = "INSTRUMENTAL_URO";
    public static String COMBO_QUIROFANOS_INSTRUMENTAL_CGD = "INSTRUMENTAL_CGD";

    public static String COMBO_QUIROFANOS_ISQUEMIA_LUGAR = "ISQUEMIA_LUGAR";
    public static String COMBO_QUIROFANOS_QUI_DESTINO = "QUI_DESTINO";

    public Combo() {
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Long getPertenece() {
        return pertenece;
    }

    public void setPertenece(Long pertenece) {
        this.pertenece = pertenece;
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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Combo [id=" + id + ", descripcion=" + descripcion + ", orden=" + orden + ", pertenece=" + pertenece
                + ", rama=" + rama + ", valor=" + valor + "]";
    }

}
