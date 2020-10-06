package es.sacyl.hnss.entity;

/**
 * The Class RegistroOxiEapoyo.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroOxiHipercapnia extends RegistroOxi {

    private Variable presiinspiracion = null;

    private Variable presiespiracion = null;

    /**
     * The Constant TERAPIA_VALOR_DEFECTO.
     */
    public final static String TERAPIA_VALOR_DEFECTO = "Equipo de hipercapnia";

    /**
     * The Constant PLANTILLLA_EDITOR_REGISTROEQPOYO.
     */
    public final static Long PLANTILLLA_EDITOR_REGISTROEQPOYO = new Long(10000006);

    public final Variable VAR_HIPER_PRESION_INSPIRA = new Variable("13991419", "99G2", new Long(13991419),
            "Presión positiva (inspiratoria)");

    public final Variable VAR_HIPER_PRESION_ESPIRACION = new Variable("13991420", "99G2", new Long(13991420),
            "Presión negativa (espiratoria)	");

    /**
     * Instantiates a new registro oxi eapoyo.
     */
    public RegistroOxiHipercapnia() {
        super();
        iniciaEapoyo();
    }

    /**
     * Instantiates a new registro oxi eapoyo.
     *
     * @param id the id
     */
    public RegistroOxiHipercapnia(Long id) {
        super(id);
        iniciaEapoyo();
    }

    /**
     * Instantiates a new registro oxi eapoyo.
     *
     * @param reap the reap
     */
    public RegistroOxiHipercapnia(RegistroOxiHipercapnia reap) {
        super(reap);
        presiinspiracion = reap.presiinspiracion;
        presiespiracion = reap.presiespiracion;
    }

    /**
     * Inicia eapoyo.
     */
    public void iniciaEapoyo() {

        setTerapia(TERAPIA_VALOR_DEFECTO);
        setPlantilla_edior(PLANTILLLA_EDITOR_REGISTROEQPOYO);
        setDescripcion(TERAPIA_VALOR_DEFECTO);

        presiinspiracion = VAR_HIPER_PRESION_INSPIRA;
        presiespiracion = VAR_HIPER_PRESION_ESPIRACION;
    }

    public Variable getVariablePresiinspiracion() {
        return presiinspiracion;
    }

    public String getPresiinspiracionString() {
        return presiinspiracion.getValor();
    }

    public void setPresiinspiracion(Variable presiinspiracion) {
        this.presiinspiracion = presiinspiracion;
    }

    public void setPresiinspiracion(String valor) {
        this.presiinspiracion.setValor(valor);
    }

    public Variable getVariablePresiespiracion() {
        return presiespiracion;
    }

    public String getPresiespiracionString() {
        return presiespiracion.getValor();
    }

    public void setPresiespiracion(Variable presiespiracion) {
        this.presiespiracion = presiespiracion;
    }

    public void setPresiespiracion(String valor) {
        this.presiespiracion.setValor(valor);
    }
}
