package es.sacyl.hnss.entity;

/**
 * The Class RegistroOxiAerosol. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroOxiAerosol extends RegistroOxi {

    private Variable sesionesDia;

    private Variable numeroDias;

    public static String TERAPIA_VALOR_DEFECTO = "Aerosolterapia";

    public static Long PLANTILLLA_EDITOR_REGISTROAEROSOL = new Long(588941950);
    public static String PLANTILLLA_EDITOR_REGISTROAEROSOLString = "588941950";

    public Variable VAR_SESIONES_DIA = new Variable("13818048", "99G2", new Long(13818048), "Sesiones día");

    public Variable VAR_NUMERO_DE_DIAS = new Variable("13818049", "99G2", new Long(13818049), "Número de días");

    /**
     * Instantiates a new registro oxi aerosol.
     */
    public RegistroOxiAerosol() {
        super();
        this.iniciaAerosol();
    }

    /**
     * Instantiates a new registro oxi aerosol.
     *
     * @param id the id
     */
    public RegistroOxiAerosol(Long id) {
        super(id);
        this.iniciaAerosol();
    }

    /**
     * Instantiates a new registro oxi aerosol.
     *
     * @param r the r
     */
    public RegistroOxiAerosol(RegistroOxiAerosol r) {
        super(r);
        this.sesionesDia = r.sesionesDia;
        this.numeroDias = r.numeroDias;
    }

    /**
     * Inicia aerosol.
     */
    public void iniciaAerosol() {
        setTerapia(RegistroOxiAerosol.TERAPIA_VALOR_DEFECTO);
        setPlantilla_edior(RegistroOxiAerosol.PLANTILLLA_EDITOR_REGISTROAEROSOL);
        setDescripcion(RegistroOxiAerosol.TERAPIA_VALOR_DEFECTO);
        this.sesionesDia = VAR_SESIONES_DIA;
        this.numeroDias = VAR_NUMERO_DE_DIAS;
    }

    public Variable getVariableSesionesDia() {
        return this.sesionesDia;
    }

    public String getSesionesDiaString() {
        return this.sesionesDia.getValor();
    }

    public void setSesionesDia(Variable sesionesDia) {
        this.sesionesDia = sesionesDia;
    }

    public void setSesionesDia(String valor) {
        this.sesionesDia.setValor(valor);
    }

    public Variable getVariableNumeroDias() {
        return this.numeroDias;
    }

    public String getNumeroDiasString() {
        return this.numeroDias.getValor();
    }

    public void setNumeroDias(Variable numeroDias) {
        this.numeroDias = numeroDias;
    }

    public void setNumeroDias(String valor) {
        this.numeroDias.setValor(valor);
    }

    public static String getTerapiaValorDefecto() {
        return TERAPIA_VALOR_DEFECTO;
    }

    public static void setTerapiaValorDefecto(String terapiaValorDefecto) {
        TERAPIA_VALOR_DEFECTO = terapiaValorDefecto;
    }

    public static Long getPlantilllaEditorRegistroaerosol() {
        return PLANTILLLA_EDITOR_REGISTROAEROSOL;
    }

    public static void setPlantilllaEditorRegistroaerosol(Long plantilllaEditorRegistroaerosol) {
        PLANTILLLA_EDITOR_REGISTROAEROSOL = plantilllaEditorRegistroaerosol;
    }

    public Variable getVAR_SESIONES_DIA() {
        return VAR_SESIONES_DIA;
    }

    public void setVAR_SESIONES_DIA(Variable vAR_SESIONES_DIA) {
        VAR_SESIONES_DIA = vAR_SESIONES_DIA;
    }

    public Variable getVAR_NUMERO_DE_DIAS() {
        return VAR_NUMERO_DE_DIAS;
    }

    public void setVAR_NUMERO_DE_DIAS(Variable vAR_NUMERO_DE_DIAS) {
        VAR_NUMERO_DE_DIAS = vAR_NUMERO_DE_DIAS;
    }

    public Variable getSesionesDia() {
        return sesionesDia;
    }

    public Variable getNumeroDias() {
        return numeroDias;
    }
}
