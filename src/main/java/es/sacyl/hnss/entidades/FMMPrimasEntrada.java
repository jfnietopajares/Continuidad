package es.sacyl.hnss.entidades;

import java.time.LocalDate;

/**
 *
 * @author JuanNieto
 */
public class FMMPrimasEntrada extends FMMPrimas {

    private LocalDate fecha;
    private Integer registro;
    private Integer envases;
    private String lote;
    private Boolean verificacion;
    private String ctrlAnalitico;
    private String farmaceutico;
    private String caducidad;
    private Integer numero;

    public static final String labelFrom = "Entradas de Materias primas ";

    public FMMPrimasEntrada() {
        //  super();
    }

    public FMMPrimasEntrada(FMMPrimas fMMPrimas) {
        //super();
        this.setCod_inte(fMMPrimas.getCod_inte());
        this.setProducto(fMMPrimas.getProducto());
        this.setCod_labo(fMMPrimas.getCod_labo());
        this.setLaboratorio(fMMPrimas.getLaboratorio());
        this.setHomologado(fMMPrimas.getHomologado());
        this.setN_labo(fMMPrimas.getN_labo());
        this.setStock_min(fMMPrimas.getStock_min());
        this.setObservaciones(fMMPrimas.getObservaciones());
        this.setEspecifica(fMMPrimas.getEspecifica());
        this.setUlti_revi(fMMPrimas.getUlti_revi());
        this.setFarmacetuico(fMMPrimas.getFarmacetuico());
        this.setExistencias(fMMPrimas.getExistencias());
        this.setNlaboratorio(fMMPrimas.getNlaboratorio());
        this.setPresentacion(fMMPrimas.getPresentacion());
        this.setDescripcion(fMMPrimas.getDescripcion());
        this.setRequisitos(fMMPrimas.getRequisitos());
        this.setConservacion(fMMPrimas.getConservacion());
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

    public static String getLabelFrom() {
        return labelFrom;
    }

    public String getCodigoNumero() {
        String cadena = "";
        if (getCod_inte() != null) {
            cadena = cadena.concat(getCod_inte() + "/");
        }
        if (numero != null) {
            cadena = cadena.concat(Integer.toString(numero));
        }
        return cadena;
    }

    public Integer getVariacionExistencias(Integer existenciasAnteriores, String accion) {
        Integer resultado = 0;
        switch (accion) {
            case "GRABAR":
                if (existenciasAnteriores == null) {
                    // nuevo registro se suman las existencias
                    resultado = this.getEnvases();
                } else if (existenciasAnteriores > this.getEnvases()) {
                    //ha modificado las existencias disnminuyendo el valor de la entrada
                    resultado = existenciasAnteriores - this.getEnvases();
                } else {
                    // no se han modificado las existencias
                    resultado = 0;
                }
                break;
            case "BORRAR":
                // se descuentan las existencias de la entrada
                resultado = this.getEnvases() * (-1);
                break;
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "FarmaFMMPrimasEntrada{" + super.toString() + "fecha=" + fecha + ", registro=" + registro + ", envases=" + envases + ", lote=" + lote + ", verificacion=" + verificacion + ", ctrlAnalitico=" + ctrlAnalitico + ", farmaceutico=" + farmaceutico + ", caducidad=" + caducidad + ", numero=" + numero + ", cod_inte=" + '}';
    }

    /*
    public void setDatosMprima(FMMPrimas fMMPrimas) {
        this.setCod_inte(fMMPrimas.getCod_inte());
        this.setProducto(fMMPrimas.getProducto());
        this.setCod_labo(fMMPrimas.getCod_labo());
        this.setLaboratorio(fMMPrimas.getLaboratorio());
        this.setHomologado(fMMPrimas.getHomologado());
        this.setN_labo(fMMPrimas.getN_labo());
        this.setStock_min(fMMPrimas.getStock_min());
        this.setObservaciones(fMMPrimas.getObservaciones());
        this.setEspecifica(fMMPrimas.getEspecifica());
        this.setUlti_revi(fMMPrimas.getUlti_revi());
        this.setFarmacetuico(fMMPrimas.getFarmacetuico());
        this.setExistencias(fMMPrimas.getExistencias());
        this.setNlaboratorio(fMMPrimas.getNlaboratorio());
        this.setPresentacion(fMMPrimas.getPresentacion());
        this.setDescripcion(fMMPrimas.getDescripcion());
        this.setRequisitos(fMMPrimas.getRequisitos());
        this.setConservacion(fMMPrimas.getConservacion());
    }
     */
}
