package es.sacyl.hnss.entity;

public class Zona {

    private Long id;
    private String zona;
    private int tipo;
    private Centro centro;
    private String zonaHis;

    public static final int ZONA_TIPO_HOSP = 1;
    public static final int ZONA_TIPO_URG = 2;
    public static final int ZONA_TIPO_QUIRO = 3;
    public static final int ZONA_TIPO_HDIA = 4;
    public static final int ZONA_TIPO_URPA = 5;
    // public static final int ZONA_TIPO_CONSULTAS = 6;

    public static final Zona ZONA_HDIA_ONCO = new Zona(new Long(27), "HDIA_ONCO", 4, Centro.HNSS, "HDONCO");
    public static final Zona ZONA_HDIA_MED = new Zona(new Long(38), "HDIA_MEDICO", 4, Centro.HNSS, "HDMEDI");
    public static final Zona ZONA_QUIROHNSS = new Zona(new Long(24), "QUIROFANOS", 4, Centro.HNSS, "QUIRO");
    public static final Zona ZONA_QUIROPROV = new Zona(new Long(25), "QUIROFANOS", 3, Centro.PROVINCIAL, "");
    public static final Zona ZONA_QUIROARE = new Zona(new Long(26), "QUIROFANOS", 3, Centro.CEPAREANAS, "");
    public static final Zona ZONA_PARTOS = new Zona(new Long(32), "Partos", 2, Centro.HNSS, "PARTOS");

    public static final Zona ZONA_UCA = new Zona(new Long(39), "UCA", 4, Centro.PROVINCIAL, "HDMEDI");

    public static final Zona ZONA_UE6D = new Zona(new Long(11), "UE6D", 1, Centro.HNSS, "HOS");
    public static final Zona ZONA_UE4P = new Zona(new Long(8), "UE4P", 1, Centro.HNSS, "HOS");
    public static final Zona ZONA_UE4N = new Zona(new Long(7), "UE4n", 1, Centro.HNSS, "HOS");

    public static final Zona ZONA_URG_POLIVAL = new Zona(new Long(35), "S. Poliv.", 2, Centro.HNSS, "URG");

    public Zona() {
    }

    public Zona(Long id, String zona, int tipo, Centro centro, String zonaHis) {
        this.id = id;
        this.zona = zona;
        this.centro = centro;
        this.tipo = tipo;
        this.zonaHis = zonaHis;
    }

    public Zona(Long id, String zona) {
        this.id = id;
        this.zona = zona;
    }

    public Zona(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public String getZonaHis() {
        return zonaHis;
    }

    @Override
    public String toString() {
        return "Zona{" + "id=" + id + ", zona=" + zona + ", tipo=" + tipo + ", centro=" + centro + ", zonaHis=" + zonaHis + '}';
    }

}
