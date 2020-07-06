package es.sacyl.hnss.entidades;

import java.time.LocalDate;

/**
 *
 * @author JuanNieto
 */
public class FarmaFMMPrimasEntrada {

    private LocalDate fecha;
    private Integer registro;
    private Integer envases;
    private String lote;
    private Boolean verificacion;
    private String ctrlAnalitico;
    private String farmaceutico;
    private String caducidad;
    private Integer numero;
    private FarmaFMMPrimas mprima;

    public static final String labelFrom = "Entradas de Materias primas ";

    public FarmaFMMPrimasEntrada() {

    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getRegistro() {
        return registro;
    }

    public void setRegistro(Integer registro) {
        this.registro = registro;
    }

    public Integer getEnvases() {
        return envases;
    }

    public void setEnvases(Integer envases) {
        this.envases = envases;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Boolean getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(Boolean verificacion) {
        this.verificacion = verificacion;
    }

    public String getCtrlAnalitico() {
        return ctrlAnalitico;
    }

    public void setCtrlAnalitico(String ctrlAnalitico) {
        this.ctrlAnalitico = ctrlAnalitico;
    }

    public String getFarmaceutico() {
        return farmaceutico;
    }

    public void setFarmaceutico(String farmaceutico) {
        this.farmaceutico = farmaceutico;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public FarmaFMMPrimas getMprima() {
        return mprima;
    }

    public void setMprima(FarmaFMMPrimas mprima) {
        this.mprima = mprima;
    }

    public String getProducto() {
        if (this.mprima != null && this.getMprima().getProducto() != null) {
            return getMprima().getProducto();
        } else {
            return "";
        }
    }

    public static String getLabelFrom() {
        return labelFrom;
    }

    @Override
    public String toString() {
        return "FarmaFMMPrimasEntrada{" + "fecha=" + fecha + ", registro=" + registro + ", envases=" + envases + ", lote=" + lote + ", verificacion=" + verificacion + ", ctrlAnalitico=" + ctrlAnalitico + ", farmaceutico=" + farmaceutico + ", caducidad=" + caducidad + ", numero=" + numero + ", cod_inte=" + mprima.toString() + '}';
    }

}
