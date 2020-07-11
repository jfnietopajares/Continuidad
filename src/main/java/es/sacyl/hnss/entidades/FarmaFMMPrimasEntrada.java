package es.sacyl.hnss.entidades;

import java.time.LocalDate;

/**
 *
 * @author JuanNieto
 */
public class FarmaFMMPrimasEntrada extends FarmaFMMPrimas {

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

    public FarmaFMMPrimasEntrada() {
        //  super();
    }

    public FarmaFMMPrimasEntrada(FarmaFMMPrimas farmaFMMPrimas) {
        //super();
        this.setCod_inte(farmaFMMPrimas.getCod_inte());
        this.setProducto(farmaFMMPrimas.getProducto());
        this.setCod_labo(farmaFMMPrimas.getCod_labo());
        this.setLaboratorio(farmaFMMPrimas.getLaboratorio());
        this.setHomologado(farmaFMMPrimas.getHomologado());
        this.setN_labo(farmaFMMPrimas.getN_labo());
        this.setStock_min(farmaFMMPrimas.getStock_min());
        this.setObservaciones(farmaFMMPrimas.getObservaciones());
        this.setEspecifica(farmaFMMPrimas.getEspecifica());
        this.setUlti_revi(farmaFMMPrimas.getUlti_revi());
        this.setFarmacetuico(farmaFMMPrimas.getFarmacetuico());
        this.setExistencias(farmaFMMPrimas.getExistencias());
        this.setNlaboratorio(farmaFMMPrimas.getNlaboratorio());
        this.setPresentacion(farmaFMMPrimas.getPresentacion());
        this.setDescripcion(farmaFMMPrimas.getDescripcion());
        this.setRequisitos(farmaFMMPrimas.getRequisitos());
        this.setConservacion(farmaFMMPrimas.getConservacion());
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
    public void setDatosMprima(FarmaFMMPrimas farmaFMMPrimas) {
        this.setCod_inte(farmaFMMPrimas.getCod_inte());
        this.setProducto(farmaFMMPrimas.getProducto());
        this.setCod_labo(farmaFMMPrimas.getCod_labo());
        this.setLaboratorio(farmaFMMPrimas.getLaboratorio());
        this.setHomologado(farmaFMMPrimas.getHomologado());
        this.setN_labo(farmaFMMPrimas.getN_labo());
        this.setStock_min(farmaFMMPrimas.getStock_min());
        this.setObservaciones(farmaFMMPrimas.getObservaciones());
        this.setEspecifica(farmaFMMPrimas.getEspecifica());
        this.setUlti_revi(farmaFMMPrimas.getUlti_revi());
        this.setFarmacetuico(farmaFMMPrimas.getFarmacetuico());
        this.setExistencias(farmaFMMPrimas.getExistencias());
        this.setNlaboratorio(farmaFMMPrimas.getNlaboratorio());
        this.setPresentacion(farmaFMMPrimas.getPresentacion());
        this.setDescripcion(farmaFMMPrimas.getDescripcion());
        this.setRequisitos(farmaFMMPrimas.getRequisitos());
        this.setConservacion(farmaFMMPrimas.getConservacion());
    }
     */
}
