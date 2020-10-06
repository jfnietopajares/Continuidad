package es.sacyl.hnss.entity;

/**
 * The Class RegistroOxiDomi. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroOxiDomi extends RegistroOxi {

    /**
     * The tipo flujo.
     */
    private Variable tipoFlujo;

    /**
     * The flujo.
     */
    private Variable flujo;

    /**
     * The horas dia.
     */
    private Variable horasDia;

    /**
     * The interfase.
     */
    private Variable interfase;

    /**
     * The presion.
     */
    private Variable presion;

    /**
     * The saturacion.
     */
    private Variable saturacion;

    /**
     * The Constant TERAPIA_VALOR_DEFECTO.
     */
    public final static String TERAPIA_VALOR_DEFECTO = "Oxigenoterapia Domiciliaria";

    /**
     * The Constant PLANTILLLA_EDITOR_REGISTRODOMI.
     */
    public final static Long PLANTILLLA_EDITOR_REGISTRODOMI = new Long(10000003);

    /**
     * The var o2 presion.
     */
    public final Variable VAR_O2_PRESION = new Variable("13991324", "99G2", new Long(13991324), "PaO2 (mmHg)");

    /**
     * The var o2 interfase.
     */
    public final Variable VAR_O2_INTERFASE = new Variable("13991305", "99G2", new Long(13991305),
            "Tipo de interfase	");

    /**
     * The var o2 horas dia.
     */
    public final Variable VAR_O2_HORAS_DIA = new Variable("13818077", "LN", new Long(13818077), "Horas dia");

    /**
     * The var o2 tipo flujo.
     */
    public final Variable VAR_O2_TIPO_FLUJO = new Variable("13991644", "99G2", new Long(13991644),
            "Tipo Flujo oxígeno ");

    /*
	 * OJO confirmar estos valoes
     */
    /**
     * The var o2 flujo.
     */
    public final Variable VAR_O2_FLUJO = new Variable("13991306", "99G2", new Long(13991306), "Flujo oxígeno ");

    /**
     * The var o2 saturacion.
     */
    public final Variable VAR_O2_SATURACION = new Variable("2708-6", "LN", new Long(598), "Saturación O2 (%)");

    /**
     * Instantiates a new registro oxi domi.
     */
    public RegistroOxiDomi() {
        super();
        this.iniciaDomi();
    }

    /**
     * Instantiates a new registro oxi domi.
     *
     * @param id the id
     */
    public RegistroOxiDomi(Long id) {
        super(id);
        this.iniciaDomi();
    }

    /**
     * Instantiates a new registro oxi domi.
     *
     * @param rm the rm
     */
    public RegistroOxiDomi(RegistroOxiDomi rm) {
        super(rm);
        tipoFlujo = rm.tipoFlujo;
        flujo = rm.flujo;
        horasDia = rm.horasDia;
        interfase = rm.interfase;
        presion = rm.presion;
        saturacion = rm.saturacion;
    }

    /**
     * Inicia domi.
     */
    public void iniciaDomi() {
        setTerapia(TERAPIA_VALOR_DEFECTO);
        setPlantilla_edior(PLANTILLLA_EDITOR_REGISTRODOMI);
        setDescripcion(TERAPIA_VALOR_DEFECTO);

        tipoFlujo = VAR_O2_TIPO_FLUJO;
        flujo = VAR_O2_FLUJO;
        horasDia = VAR_O2_HORAS_DIA;
        interfase = VAR_O2_INTERFASE;
        presion = VAR_O2_PRESION;
        saturacion = VAR_O2_SATURACION;
    }

    public Variable getVariableTipoFlujo() {
        return tipoFlujo;
    }

    public String getTipoFlujoString() {
        if (tipoFlujo != null) {
            if (tipoFlujo.getValor() != null) {
                return tipoFlujo.getValor();
            }
        }
        return "";
    }

    public void setTipoFlujo(Variable tipoFlujo) {
        this.tipoFlujo = tipoFlujo;
    }

    public void setTipoFlujo(String valor) {
        this.tipoFlujo.setValor(valor);
    }

    public Variable getVariableFlujo() {
        return flujo;
    }

    public String getFlujoString() {
        if (flujo != null) {
            return flujo.getValor();
        } else {
            return "";
        }
    }

    public void setFlujo(Variable flujo) {
        this.flujo = flujo;
    }

    public void setFlujo(String valor) {
        this.flujo.setValor(valor);
    }

    public Variable getVariableHorasDia() {
        return horasDia;
    }

    public String getHorasDiaString() {
        return horasDia.getValor();
    }

    public void setHorasDia(Variable horasDia) {
        this.horasDia = horasDia;
    }

    public void setHorasDia(String valor) {
        this.horasDia.setValor(valor);
    }

    public Variable getVariableInterfase() {
        return interfase;
    }

    public String getInterfaseString() {
        return interfase.getValor();
    }

    public void setInterfase(Variable interfase) {
        this.interfase = interfase;
    }

    public void setInterfase(String valor) {
        this.interfase.setValor(valor);
    }

    public Variable getVariablePresion() {
        return presion;
    }

    public String getPresionString() {
        return presion.getValor();
    }

    public void setPresion(Variable presion) {
        this.presion = presion;
    }

    public void setPresion(String valor) {
        this.presion.setValor(valor);
    }

    public Variable getVariableSaturacion() {
        return saturacion;
    }

    public String getSaturacionString() {
        return saturacion.getValor();
    }

    public void setSaturacion(Variable saturacion) {
        this.saturacion = saturacion;
    }

    public void setSaturacion(String valor) {
        this.saturacion.setValor(valor);
    }

    public Variable getVAR_O2_PRESION() {
        return VAR_O2_PRESION;
    }

    public Variable getVAR_O2_INTERFASE() {
        return VAR_O2_INTERFASE;
    }

    public Variable getVAR_O2_HORAS_DIA() {
        return VAR_O2_HORAS_DIA;
    }

    public Variable getVAR_O2_TIPO_FLUJO() {
        return VAR_O2_TIPO_FLUJO;
    }

    public Variable getVAR_O2_FLUJO() {
        return VAR_O2_FLUJO;
    }

    public Variable getVAR_O2_SATURACION() {
        return VAR_O2_SATURACION;
    }

}
