package es.sacyl.hnss.entity;

import es.sacyl.hnss.dao.CamaDAO;
import es.sacyl.hnss.utilidades.Utilidades;



public class Cama {

    private Long id;
    private Zona zona;
    private String estado;
    private String cama;

    public static String PREFIJOSILLON = "SILLON";
    public static String PREFIJOCAMA = "CAMA";

    public static final Cama camaDefectoHdiaOnco = new CamaDAO().getRegistrPorId(new Long(704), null);
    // Cama(new Long(704), " CONSULTA", "U", Zona.ZONA_HDIA_ONCO);
//	public static final Cama camaDefectoHdiaMedi = new Cama(new Long(374), "HDIAMED_SILLON1", "U", Zona.ZONA_HDIA_MED);
    public static final Cama camaDefectoHdiaMedi = new CamaDAO().getRegistrPorId(new Long(374), null);
    // "HDIAMED_SILLON1", "U", Zona.ZONA_HDIA_MED);
    // =new CamaDAO().getRegistrPorId(new Long(704),null);

    public Cama() {
        this.id = new Long(0);
    }

    public Cama(Long id, String cama, String estado, Zona zona) {
        this.id = id;
        this.cama = cama;
        this.estado = estado;
        this.zona = zona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getCamaNumero() {
        String cadena = "";
        if (this.getCama() != null) {
            if (Utilidades.isNumeric(this.getCama().substring(0, 1))) {
                System.out.println(this.getCama().substring(0, 1));
                cadena = this.getCama();
            } else if (this.getCama().length() >= 6 && this.getCama().subSequence(0, 6).equals(Cama.PREFIJOSILLON)) {
                cadena = this.getCama().substring(6, this.getCama().length());
            } else if (this.getCama().length() > 4 && this.getCama().subSequence(0, 4).equals(Cama.PREFIJOCAMA)) {
                cadena = this.getCama().substring(4, this.getCama().length());
            }
        }
        return cadena;
    }

    @Override
    public String toString() {
        return "Cama [id=" + id + ", zona=" + zona + ", estado=" + estado + ", cama=" + cama + "]";
    }

}
