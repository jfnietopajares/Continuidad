package es.sacyl.hnss.entity;

public class RegistroEvolutivoMedico extends Registro {

    private Variable sitActual;
    private Variable tratamiento;

    public final static Long PLANTILLLA_EDITOR_EVOLUTIVOMEDICO = new Long(1);
    public final static Long TIPO_REGISTRO_EVOLUTIVO = new Long(3);

    public final Variable VAR_EVOLUTIVO_SITACTU = new Variable("8675-1", "LN", new Long(13524548), "Situación actual");
    public final Variable VAR_EVOLUTIVO_TRATAMIENTO = new Variable("413467001", "SNM3", new Long(13596253),
            "Tratamiento");

    public RegistroEvolutivoMedico() {
        super();
        iniciaEvolutivo();
    }

    public RegistroEvolutivoMedico(Long id) {
        super(id);
        iniciaEvolutivo();
    }

    public RegistroEvolutivoMedico(RegistroEvolutivoMedico r) {
        super(r);
        this.sitActual = r.sitActual;
        this.tratamiento = r.tratamiento;
    }

    public void iniciaEvolutivo() {
        this.setTiporegistro(TIPO_REGISTRO_EVOLUTIVO);
        this.setDescripcion("Evolutivo de paciente");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_EVOLUTIVOMEDICO);
        // this.setServicio(Servicio.SERVICIO_ONCOLOGIA);
        this.sitActual = VAR_EVOLUTIVO_SITACTU;
        this.tratamiento = VAR_EVOLUTIVO_TRATAMIENTO;
    }

    public Variable getSitActual() {
        return sitActual;
    }

    public Variable getVariableSitActual() {
        return sitActual;
    }

    public String getSitActualString() {
        return sitActual.getValor();
    }

    public void setSitActual(Variable sitActual) {
        this.sitActual = sitActual;
    }

    public void setSitActual(String valor) {
        this.sitActual.setValor(valor);
    }

    public Variable getTratamiento() {
        return tratamiento;
    }

    public Variable getVariableTratamiento() {
        return tratamiento;
    }

    public String getTratamientoString() {
        return tratamiento.getValor();
    }

    public void setTratamiento(Variable tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setTratamiento(String valor) {
        this.tratamiento.setValor(valor);
    }

}
