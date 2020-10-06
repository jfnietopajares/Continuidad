package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;


/**
 * The Class RegistroIncidencia. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroO2Incidencia extends Registro {

    private Variable fechaInicio;

    private Variable fechaFin;

    private Variable tipo;

    private Variable observaciones;

    public final static Long PLANTILLLA_EDITOR_REGISTROINCIDENCIA = new Long(650740699);

    public final static Long TIPO_REGISTRO = new Long(63);

    public final static String REGISTRO_DESCRIPCION_STRING = "Registro Inicdencia Oxigeno";

    public final static Servicio SERVICIO_DEFECTO_SERVICIO = new Servicio(new Long(37), "NML", "Neumología");

    public final static String GRUPOSC_O2_TIPOS_INCIDENCIA = "O2_TIPO_INCIDENICIA";

    public final static String TIPO_INCIDENCIA_DEFECTO = "INGRESO HOSPITALARIO";

    /*
	 * public final static TipoRegistros MODELO_REGISTROTINCIDENCIA = new
	 * TipoRegistros(new Long(710580873),
	 * "Indicentra Tratamiento Terapias oxigeno domiciliarias", new Long(99),
	 * Centro.CENTRO_DEFECTO, Constantes.SERVICIO_DEFECTO);
     */
    public final Variable VAR_INCI_FECHA_INICIO = new Variable("298059007", "SNM3", new Long(35001161),
            "Fecha inicio incidencia");

    /**
     * IMPORTANTE. La fecha de fin debe ser la misma para todos los registros
     * relacionados con la oxigenoterparapia ya que la recuperación de los
     * diferentes registros en función de la fecha de cierre se realiza por esta
     * variable
     */
    // 13818104
    public final Variable VAR_INCI_FECHA_FIN = new Variable("13818104", "99G2", new Long(13818104),
            "Fecha fin incidencia");

    public final Variable VAR_INCI_TIPO = new Variable("410657003", "SNM3", new Long(13822329), "Tipo Incidencia");

    public final Variable VAR_INCI_OBSERVACIONES = new Variable("246453008", "SNM3", new Long(46293677),
            "Observaciones ");

    public RegistroO2Incidencia() {
        super();
        iniciaIncidencia();
    }

    public RegistroO2Incidencia(Long id) {
        super(id);
        iniciaIncidencia();
    }

    public RegistroO2Incidencia(RegistroO2Incidencia rin) {
        super(rin);
        this.fechaInicio = rin.fechaInicio;
        this.fechaFin = rin.fechaFin;
        this.tipo = rin.tipo;
        this.observaciones = rin.observaciones;
    }

    public void iniciaIncidencia() {
        this.setServicio(SERVICIO_DEFECTO_SERVICIO);
        this.setPlantilla_edior(PLANTILLLA_EDITOR_REGISTROINCIDENCIA);
        this.setTiporegistro(TIPO_REGISTRO);
        this.setDescripcion(REGISTRO_DESCRIPCION_STRING);
        this.fechaInicio = VAR_INCI_FECHA_INICIO;
        this.fechaFin = VAR_INCI_FECHA_FIN;
        this.tipo = VAR_INCI_TIPO;
        this.observaciones = VAR_INCI_OBSERVACIONES;
    }

    public Variable getVariableFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioDate() {
        LocalDate fecha = null;
        if (this.fechaInicio != null) {
            if (this.fechaInicio.getValor() != null) {
                if (Utilidades.isNumero(this.fechaInicio.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechaInicio.getValor()));
                }
            }
        }
        return fecha;
    }

    public void setFechaInicio(Variable fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicio(LocalDate fecha) {
        this.fechaInicio.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getVariableFechaFin() {
        return fechaFin;
    }

    public LocalDate getFechaFinDate() {
        LocalDate fecha = null;
        if (this.fechaFin != null) {
            if (this.fechaFin.getValor() != null) {
                if (Utilidades.isNumero(this.fechaFin.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechaFin.getValor()));
                }
            }
        }
        return fecha;
    }

    public void setFechaFin(Variable fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaFin(LocalDate fecha) {
        this.fechaFin.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getVariableTipo() {
        return tipo;
    }

    public String getTipoString() {
        return tipo.getValor();
    }

    public void setTipo(Variable tipo) {
        this.tipo = tipo;
    }

    public void setTipo(String valor) {
        this.tipo.setValor(valor);
    }

    public Variable getVariableObservaciones() {
        return observaciones;
    }

    public String getObservacionesString() {
        return observaciones.getValor();
    }

    public void setObservaciones(Variable observaciones) {
        this.observaciones = observaciones;
    }

    public void setObservaciones(String valor) {
        this.observaciones.setValor(valor);
    }
}
