package es.sacyl.hnss.entity;

/**
 * The Class RegistroOxiMapnea. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroOxiMapnea extends RegistroOxi {

    private Variable presionRampa;

    private Variable tiempoRampa;

    private Variable presion;

    public final static String TERAPIA_VALOR_DEFECTO = "Apnea de sueño";

    public final static Long PLANTILLLA_EDITOR_REGISTROMAPNEA = new Long(588938432);

    /*
	 * 
	 * public final Variable VAR_APENA_ALARMA_APNEA = new Variable("13991414",
	 * "99G2", new Long(13991414), "Alarma apnea");
	 * 
	 * public final Variable VAR_APENA_TIEMPO_TAQUI = new Variable("13991412",
	 * "99G2", new Long(13991412), "Tiempo taquicardia");
	 * 
	 * public final Variable VAR_APENA_ALARMA_TAQUI = new Variable("13991410",
	 * "99G2", new Long(13991410), "Alarma Taquicardia");
	 * 
	 * public final Variable VAR_APENA_TIEMPO_BRADI = new Variable("13991413",
	 * "99G2", new Long(13991413), "Tiempo bradicardia");
	 * 
	 * public final Variable VAR_APENA_ALARMA_BARDI = new Variable("13991411",
	 * "99G2", new Long(13991411), "Alarma Bradicardia");
	 * 
     */
    /**
     * The var apena presion rampa.
     */
    public final Variable VAR_APENA_PRESION_RAMPA = new Variable("13991384", "99G2", new Long(13991384),
            "Presión rampa");

    /**
     * The var apena tiempo rampa.
     */
    public final Variable VAR_APENA_TIEMPO_RAMPA = new Variable("13991385", "99G2", new Long(13991385), "Tiempo rampa");

    /**
     * The var apena presion.
     */
    public final Variable VAR_APENA_PRESION = new Variable("99999", "99G2", new Long(99999), "Presión ");

    /**
     * Instantiates a new registro oxi mapnea.
     */
    public RegistroOxiMapnea() {
        super();
        this.iniciaMapnea();
    }

    /**
     * Instantiates a new registro oxi mapnea.
     *
     * @param id the id
     */
    public RegistroOxiMapnea(Long id) {
        super(id);
        this.iniciaMapnea();
    }

    /**
     * Instantiates a new registro oxi mapnea.
     *
     * @param ro the ro
     */
    public RegistroOxiMapnea(RegistroOxiMapnea ro) {
        super(ro);
        this.tiempoRampa = ro.tiempoRampa;
        this.presionRampa = ro.presionRampa;
        this.presion = ro.presion;
    }

    /**
     * Inicia mapnea.
     */
    public void iniciaMapnea() {
        setTerapia(TERAPIA_VALOR_DEFECTO);
        setPlantilla_edior(PLANTILLLA_EDITOR_REGISTROMAPNEA);
        setDescripcion(TERAPIA_VALOR_DEFECTO);
        tiempoRampa = VAR_APENA_TIEMPO_RAMPA;
        presionRampa = VAR_APENA_PRESION_RAMPA;
        presion = VAR_APENA_PRESION;
    }

    public Variable getVariablePresionRampa() {
        return presionRampa;
    }

    public String getPresionRampaString() {
        return presionRampa.getValor();
    }

    public void setPresionRampa(Variable presionRampa) {
        this.presionRampa = presionRampa;
    }

    public void setPresionRampa(String valor) {
        this.presionRampa.setValor(valor);
    }

    public Variable getVariableTiempoRampa() {
        return tiempoRampa;
    }

    public String getTiempoRampaString() {
        return tiempoRampa.getValor();
    }

    public void setTiempoRampa(Variable tiempoRampa) {
        this.tiempoRampa = tiempoRampa;
    }

    public void setTiempoRampa(String valor) {
        this.tiempoRampa.setValor(valor);
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

}
