package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;



/**
 * The Class RegistroOxi. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroOxi extends Registro {

    /**
     * The idgrupos catalogo.
     */
    private Long idgrupos_catalogo;

    /**
     * The terapia.
     */
    protected Variable terapia = null;

    /**
     * The modalidad.
     */
    protected Variable modalidad = null;

    /**
     * The tipo prescripcion.
     */
    protected Variable tipoPrescripcion;

    /**
     * The fecha inicio.
     */
    protected Variable fechaInicio;

    /**
     * The duracion.
     */
    protected Variable duracion;

    /**
     * The observaciones.
     */
    protected Variable observaciones;

    /**
     * The fecha baja.
     */
    protected Variable fechaBaja;

    /**
     * The motivo baja.
     */
    protected Variable motivoBaja;

    /**
     * The equipo.
     */
    protected Variable equipo;

    /**
     * The farmaco.
     */
    protected Variable farmaco;

    /**
     * The prueba diagnostica.
     */
    protected Variable pruebaDiagnostica;

    /**
     * The resultados prueba.
     */
    protected Variable resultadosPrueba;

    /**
     * The id peticion.
     */
    protected Long idPeticion;

    /**
     * The Constant TIPO_REGISTRO.
     */
    public final static Long TIPO_REGISTRO = new Long(63);

    /**
     * The Constant ITEM_FECHAFIN.
     */
    public final static Long ITEM_FECHAFIN = new Long(13818104);

    /**
     * The Constant TIPO_PRES_DEFINITIVA.
     */
    public final static String TIPO_PRES_DEFINITIVA = "Definitiva";

    /**
     * The Constant TIPO_DURACION_DEFINITIVA.
     */
    public final static String TIPO_DURACION_DEFINITIVA = "Indefinida";

    /**
     * The Constant TIPO_PRESCPCION_DEFECTO.
     */
    public final static String TIPO_PRESCPCION_DEFECTO = "Povisional";

    /**
     * The Constant SERVICIO_DEFECTO.
     */
    public final static Servicio SERVICIO_DEFECTO = new Servicio(new Long(37), "NML", "Neumología");

    /**
     * The Constant DURACION_DEFECTO.
     */
    public final static String DURACION_DEFECTO = "3 meses";

    /**
     * The Constant GRUPOSC_O2_TERAPIA.
     */
    public final static String GRUPOSC_O2_TERAPIA = "O2_TERAPIAS";

    /**
     * The Constant GRUPOSC_O2_MODALIDAD_TERAPIA.
     */
    public final static String GRUPOSC_O2_MODALIDAD_TERAPIA = "O2_TERAPIAS_MODALIDAD";

    /**
     * The Constant GRUPOSC_O2_TIPOS_FLUJOS.
     */
    public final static String GRUPOSC_O2_TIPOS_FLUJOS = "O2_TIPOS_FLUJOS";

    /**
     * The Constant TIPO_FLUJO_DEFECTO.
     */
    public final static String TIPO_FLUJO_DEFECTO = "Contínuo";

    /**
     * The Constant GRUPOSC_O2_TIPOS_PRESCRIPCION.
     */
    public final static String GRUPOSC_O2_TIPOS_PRESCRIPCION = "O2_TIPOS_PRESCIPCION";

    /**
     * The Constant GRUPOSC_O2_DURACION_PRESCIPCION.
     */
    public final static String GRUPOSC_O2_DURACION_PRESCIPCION = "O2_DURACION_PRESCIPCION";

    /**
     * The Constant GRUPOSC_O2_TIPO_INTERFASE.
     */
    public final static String GRUPOSC_O2_TIPO_INTERFASE = "O2_TIPO_INTERFASE";

    /**
     * The Constant TIPO_INTERFASE_DEFECTO.
     */
    public final static String TIPO_INTERFASE_DEFECTO = "Gafas nasales";

    /**
     * The var terapia.
     */
    public final Variable VAR_TERAPIA = new Variable("93.9", "I9C", new Long(104272), "Terapia");

    /**
     * The var modalidad.
     */
    public final Variable VAR_MODALIDAD = new Variable("13818073", "99G2", new Long(13818073), "Modalidad");

    /**
     * The var equipo.
     */
    public final Variable VAR_EQUIPO = new Variable("13818046", "99G2", new Long(13818046), "Equipo");

    /**
     * The var farmaco.
     */
    public final Variable VAR_FARMACO = new Variable("13818047", "99G2", new Long(13818047), "Fármaco");

    /**
     * The var prueba diagnostica.
     */
    public final Variable VAR_PRUEBA_DIAGNOSTICA = new Variable("43782000", "SNM3", new Long(13822384),
            "Prueba diagnóstica");

    /**
     * The var resultados prueba diagnostica.
     */
    public final Variable VAR_RESULTADOS_PRUEBA_DIAGNOSTICA = new Variable("13991444", "99G2", new Long(13991444),
            "Resultados Prueba diagnóstica");

    /**
     * The var tipo prescripcion.
     */
    public final Variable VAR_TIPO_PRESCRIPCION = new Variable("13991304", "99G2", new Long(13991304),
            "Tipo prescripción");

    /**
     * The var fecha inicio.
     */
    public final Variable VAR_FECHA_INICIO = new Variable("298059007", "SNM3", new Long(35001161), "Fecha inicio");

    /**
     * The var duracion.
     */
    public final Variable VAR_DURACION = new Variable("398295005", "SNM3", new Long(13833108),
            "Duración del tratamiento");

    /**
     * The var observaciones.
     */
    public final Variable VAR_OBSERVACIONES = new Variable("246453008", "SNM3", new Long(46293677), "Observaciones ");

    /**
     * The var fecha baja.
     */
    public final Variable VAR_FECHA_BAJA = new Variable("13818104", "99G2", new Long(13818104), "Fecha baja");

    /**
     * The var motivo baja.
     */
    public final Variable VAR_MOTIVO_BAJA = new Variable("13818276", "99G2", new Long(13818276), "Motivo baja");

    /**
     * Instantiates a new registro oxi.
     */
    public RegistroOxi() {
        super();
        this.iniciaoxi();
    }

    /**
     * Instantiates a new registro oxi.
     *
     * @param id the id
     */
    public RegistroOxi(Long id) {
        super(id);
        this.iniciaoxi();
    }

    /**
     * Instantiates a new registro oxi.
     *
     * @param r the r
     */
    public RegistroOxi(RegistroOxi r) {
        super(r);
        terapia = r.getVariableTerapia();
        modalidad = r.getVariableModalidad();
        tipoPrescripcion = r.getVariableTipoPrescripcion();
        fechaInicio = r.getVariableFechaInicio();
        duracion = r.getVariableDuracion();
        observaciones = r.getVariableObservaciones();
        fechaBaja = r.getVariableFechaBaja();
        motivoBaja = r.getVariableMotivoBaja();
        equipo = r.getVariableEquipo();
        farmaco = r.getVariableFarmaco();
        pruebaDiagnostica = r.getVariablePruebaDiagnostica();
        resultadosPrueba = r.getVariableResultadosPrueba();
        idPeticion = r.getIdPeticion();
    }

    /**
     * Iniciaoxi.
     */
    public void iniciaoxi() {
        id = new Long(0);
        tiporegistro = RegistroOxi.TIPO_REGISTRO;
        servicio = RegistroOxi.SERVICIO_DEFECTO;

        terapia = VAR_TERAPIA;
        modalidad = VAR_MODALIDAD;
        tipoPrescripcion = VAR_TIPO_PRESCRIPCION;
        fechaInicio = VAR_FECHA_INICIO;
        duracion = VAR_DURACION;
        observaciones = VAR_OBSERVACIONES;
        fechaBaja = VAR_FECHA_BAJA;
        motivoBaja = VAR_MOTIVO_BAJA;
        equipo = VAR_EQUIPO;
        farmaco = VAR_FARMACO;
        pruebaDiagnostica = VAR_PRUEBA_DIAGNOSTICA;
        resultadosPrueba = VAR_RESULTADOS_PRUEBA_DIAGNOSTICA;
        /*
		 * terapia = new Variable("93.9", "I9C", new Long(104272), "Terapia"); modalidad
		 * = new Variable("13818073", "99G2", new Long(13818073), "Modalidad");
		 * tipoPrescripcion = new Variable("13991304", "99G2", new Long(13991304),
		 * "Tipo prescripción"); fechaInicio = new Variable("298059007", "SNM3", new
		 * Long(35001161), "Fecha inicio"); duracion = new Variable("398295005", "SNM3",
		 * new Long(13833108), "Duración del tratamiento"); observaciones = new
		 * Variable("246453008", "SNM3", new Long(46293677), "Observaciones ");
		 * fechaBaja = new Variable("13818104", "99G2", new Long(13818104),
		 * "Fecha baja"); motivoBaja = new Variable("13818276", "99G2", new
		 * Long(13818276), "Motivo baja"); equipo = new Variable("13818046", "99G2", new
		 * Long(13818046), "Equipo"); farmaco = new Variable("13818047", "99G2", new
		 * Long(13818047), "Fármaco"); pruebaDiagnostica = new Variable("43782000",
		 * "SNM3", new Long(13822384), "Prueba diagnóstica"); resultadosPrueba = new
		 * Variable("13991444", "99G2", new Long(13991444),
		 * "Resultados Prueba diagnóstica");
         */
    }

    public Long getIdgrupos_catalogo() {
        return idgrupos_catalogo;
    }

    public void setIdgrupos_catalogo(Long idgrupos_catalogo) {
        this.idgrupos_catalogo = idgrupos_catalogo;
    }

    public Variable getVariableTerapia() {
        return terapia;
    }

    public String getTerapiaString() {
        return terapia.getValor();
    }

    public void setTerapia(Variable terapia) {
        this.terapia = terapia;
    }

    public void setTerapia(String terapia) {
        this.terapia.setValor(terapia);
    }

    public Variable getVariableModalidad() {
        return modalidad;
    }

    public String getModalidadString() {
        return modalidad.getValor();
    }

    public void setModalidad(Variable modalidad) {
        this.modalidad = modalidad;
    }

    public void setModalidad(String valor) {
        this.modalidad.setValor(valor);
    }

    public Variable getVariableTipoPrescripcion() {
        return tipoPrescripcion;
    }

    public String getTipoPrescripcionString() {
        return tipoPrescripcion.getValor();
    }

    public void setTipoPrescripcion(Variable tipoPrescripcion) {
        this.tipoPrescripcion = tipoPrescripcion;
    }

    public void setTipoPrescripcion(String valor) {
        this.tipoPrescripcion.setValor(valor);
        ;
    }

    public Variable getVariableFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicio() {
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

    public Variable getVariableDuracion() {
        return duracion;
    }

    public String getDuracionString() {
        return duracion.getValor();
    }

    public void setDuracion(Variable duracion) {
        this.duracion = duracion;
    }

    public void setDuracion(String valor) {
        this.duracion.setValor(valor);
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

    public Variable getVariableFechaBaja() {
        return fechaBaja;
    }

    public LocalDate getFechaBaja() {
        LocalDate fecha = null;
        if (this.fechaBaja != null) {
            if (this.fechaBaja.getValor() != null) {
                if (Utilidades.isNumero(this.fechaBaja.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechaBaja.getValor()));
                }
            }
        }
        return fecha;
    }

    public void setFechaBaja(Variable fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public void setFechaBaja(LocalDate fecha) {
        this.fechaBaja.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getVariableMotivoBaja() {
        return motivoBaja;
    }

    public String getMotivoBajaString() {
        return motivoBaja.getValor();
    }

    public void setMotivoBaja(Variable motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public void setMotivoBaja(String valor) {
        this.motivoBaja.setValor(valor);
    }

    public Variable getVariableEquipo() {
        return equipo;
    }

    public String getEquipoString() {
        return equipo.getValor();
    }

    public void setEquipo(Variable equipo) {
        this.equipo = equipo;
    }

    public void setEquipo(String valor) {
        this.equipo.setValor(valor);
    }

    public Variable getVariableFarmaco() {
        return farmaco;
    }

    public String getFarmacoString() {
        return farmaco.getValor();
    }

    public void setFarmaco(Variable farmaco) {
        this.farmaco = farmaco;
    }

    public void setFarmaco(String valor) {
        this.farmaco.getValor();
    }

    public Variable getVariablePruebaDiagnostica() {
        return pruebaDiagnostica;
    }

    public String getPruebaDiagnosticaString() {
        return pruebaDiagnostica.getValor();
    }

    public void setPruebaDiagnostica(Variable variable) {
        this.pruebaDiagnostica = variable;
    }

    public void setPruebaDiagnostica(String valor) {
        this.pruebaDiagnostica.setValor(valor);
        ;
    }

    public Variable getVariableResultadosPrueba() {
        return resultadosPrueba;
    }

    public String getResultadosPruebaString() {
        return resultadosPrueba.getValor();
    }

    public void setResultadosPrueba(Variable resultadosPrueba) {
        this.resultadosPrueba = resultadosPrueba;
    }

    public void setResultadosPrueba(String valor) {
        this.resultadosPrueba.setValor(valor);
        ;
    }

    public Variable getVAR_TERAPIA() {
        return VAR_TERAPIA;
    }

    public Variable getVAR_MODALIDAD() {
        return VAR_MODALIDAD;
    }

    public Variable getVAR_EQUIPO() {
        return VAR_EQUIPO;
    }

    public Variable getVAR_FARMACO() {
        return VAR_FARMACO;
    }

    public Variable getVAR_PRUEBA_DIAGNOSTICA() {
        return VAR_PRUEBA_DIAGNOSTICA;
    }

    public Variable getVAR_RESULTADOS_PRUEBA_DIAGNOSTICA() {
        return VAR_RESULTADOS_PRUEBA_DIAGNOSTICA;
    }

    public Variable getVAR_TIPO_PRESCRIPCION() {
        return VAR_TIPO_PRESCRIPCION;
    }

    public Variable getVAR_FECHA_INICIO() {
        return VAR_FECHA_INICIO;
    }

    public Variable getVAR_DURACION() {
        return VAR_DURACION;
    }

    public Variable getVAR_OBSERVACIONES() {
        return VAR_OBSERVACIONES;
    }

    public Variable getVAR_FECHA_BAJA() {
        return VAR_FECHA_BAJA;
    }

    public Variable getVAR_MOTIVO_BAJA() {
        return VAR_MOTIVO_BAJA;
    }

    public Long getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(Long idPeticion) {
        this.idPeticion = idPeticion;
    }

}
