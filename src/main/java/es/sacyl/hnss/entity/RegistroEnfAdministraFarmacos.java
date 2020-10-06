package es.sacyl.hnss.entity;

public class RegistroEnfAdministraFarmacos extends Registro {

    private Variable medicamento;
    public final Variable VAR_ENF_ADMI_MEDICAMENTO = new Variable("Fármaco", "277045009", "SNM3", new Long(13823964),
            "Medicamento");

    private Variable dosis;
    public final Variable VAR_ENF_ADMI_DOSIS = new Variable("Dosis", "398232005", "SNM3", new Long(13822864), "Dosis");
    private Variable dosisUnidades;
    public final Variable VAR_ENF_ADMI_DOSISUNIDADES = new Variable("Unidades", "43413-4", "LN", new Long(2938),
            " Unidades");

    private Variable viaAdm;
    public final Variable VAR_ENF_ADMI_VIAADM = new Variable("Via Adm", "103389009", "SNM3", new Long(13827945),
            "Vía ADM");

    private Variable pauta;
    public final Variable VAR_ENF_ADMI_PAUTA = new Variable("Pauda", "35004464", "99G2", new Long(35004464), "Pauta");

    private Variable observaciones;
    public final Variable VAR_ENF_ADMI_OBSERVA = new Variable("Observaciones", "35000983", "99G2", new Long(35000983),
            "Observaciones de administracion");

    public final static Long PLANTILLLA_EDITOR_ENF_ADMIMED = new Long(338977867);
    public final static Long TIPO_REGISTRO_ENF_ADMIMED = new Long(6);

    public RegistroEnfAdministraFarmacos() {
        super();
        this.iniciaAdm();
    }

    public RegistroEnfAdministraFarmacos(Long id) {
        super(id);
        this.iniciaAdm();
    }

    public RegistroEnfAdministraFarmacos(RegistroEnfAdministraFarmacos r) {
        super(r);
        this.medicamento = r.getMedicamento();
        this.dosis = r.getDosis();
        this.dosisUnidades = r.getDosisUnidades();
        this.viaAdm = r.getViaAdm();
        this.pauta = r.getPauta();
        this.observaciones = r.getObservaciones();
    }

    public void iniciaAdm() {
        this.setTiporegistro(RegistroEnfAdministraFarmacos.TIPO_REGISTRO_ENF_ADMIMED);
        this.setDescripcion("Administración medicamentos");
        this.setPlantilla_edior(RegistroEnfAdministraFarmacos.PLANTILLLA_EDITOR_ENF_ADMIMED);

        this.medicamento = VAR_ENF_ADMI_MEDICAMENTO;
        this.dosis = VAR_ENF_ADMI_DOSIS;
        this.dosisUnidades = VAR_ENF_ADMI_DOSISUNIDADES;
        this.viaAdm = VAR_ENF_ADMI_VIAADM;
        this.pauta = VAR_ENF_ADMI_PAUTA;
        this.observaciones = VAR_ENF_ADMI_OBSERVA;
    }

    public Variable getMedicamento() {
        return medicamento;
    }

    public Variable getVariableMedicamento() {
        return medicamento;
    }

    public String getMedicamentoString() {
        return medicamento.getValor();
    }

    public void setMedicamento(Variable medicamento) {
        this.medicamento = medicamento;
    }

    public void setMedicamento(String valor) {
        this.medicamento.setValor(valor);
    }

    public Variable getDosis() {
        return dosis;
    }

    public Variable getVariableDosis() {
        return dosis;
    }

    public String getDosisString() {
        return dosis.getValor();
    }

    public String getDosisyUnidadesString() {
        String cadena = "";
        if (dosis != null && !dosis.getValor().isEmpty()) {
            cadena = dosis.getValor();
        }
        if (dosisUnidades != null && !dosisUnidades.getValor().isEmpty()) {
            cadena = cadena.concat(" " + dosisUnidades.getValor());
        }
        return cadena;
    }

    public void setDosis(Variable dosis) {
        this.dosis = dosis;
    }

    public void setDosis(String valor) {
        this.dosis.setValor(valor);
    }

    public Variable getDosisUnidades() {
        return dosisUnidades;
    }

    // esta variable se pega a la dosis
    /*
	 * public Variable getVariableDosisUnidades() { return dosisUnidades; }
     */
    public String getDosisUnidadesString() {
        return dosisUnidades.getValor();
    }

    public void setDosisUnidades(Variable dosisUnidades) {
        this.dosisUnidades = dosisUnidades;
        if (dosis != null) {
            dosis.setUnidades(dosisUnidades.getValor());
        }
    }

    public void setDosisUnidades(String valor) {
        this.dosisUnidades.setValor(valor);
        if (dosis != null) {
            dosis.setUnidades(valor);
        }
    }

    public Variable getPauta() {
        return pauta;
    }

    public Variable getVariablePauta() {
        return pauta;
    }

    public String getPautaString() {
        return pauta.getValor();
    }

    public void setPauta(Variable pauta) {
        this.pauta = pauta;
    }

    public void setPauta(String valor) {
        this.pauta.setValor(valor);
    }

    public Variable getObservaciones() {
        return observaciones;
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

    public Variable getViaAdm() {
        return viaAdm;
    }

    public Variable getVariableViaAdm() {
        return viaAdm;
    }

    public String getViaAdmString() {
        return viaAdm.getValor();
    }

    public void setViaAdm(Variable viaAdm) {
        this.viaAdm = viaAdm;
    }

    public void setViaAdm(String valor) {
        this.viaAdm.setValor(valor);
    }

}
