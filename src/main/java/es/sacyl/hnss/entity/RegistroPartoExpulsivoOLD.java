package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.ConstantesClinicas;



public class RegistroPartoExpulsivoOLD extends Registro {

    private Variable horas;
    private Variable partoTipo;
    private Variable phFetal;
    private Variable temperatura;
    private Variable interpretacioRC;
    private Variable indicacionCesarea;

    public final Variable VAR_PARTO_EXPUL_HORAS = new Variable("13994645", "99G2", new Long(13994645), "Horas ");
    public final Variable VAR_PARTO_EXPUL_TIPOPARTO_ = new Variable("13994646", "99G2", new Long(13994646),
            "Tipo Parto ");
    // Parto vaginal ,Eutócico,Vantosa,Forceps,Espátulas
    public final Variable VAR_PARTO_EXPUL_PHFETAL = new Variable("199785006", "SNM3", new Long(13994643), "Ph Fetal ");
    // Valor PH fetal SI,NO

    public final Variable VAR_PARTO_EXPUL_INTERPRETACIONRC = new Variable("13994642", "99G2", new Long(13994642),
            "Interpretación Reg. Cardiotocográfico ");
    // ,Urgente,Programada

    public final Variable VAR_PARTO_EXPUL_INDICACIONCESAREA = new Variable("13994653", "99G2", new Long(13994653),
            "Indicación cesárea ");
    // ,A,B,C

    public final Variable VAR_PARTO_EXPUL_T = new ConstantesClinicas().VAR_CTES_T;

    public final static Long PLANTILLLA_EDITOR_PAR_EXPULSIVO = new Long(794874238);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public RegistroPartoExpulsivoOLD() {
        super();
        iniciaExpulsivo();
    }

    public RegistroPartoExpulsivoOLD(Long id) {
        super(id);
        iniciaExpulsivo();
    }

    public RegistroPartoExpulsivoOLD(RegistroPartoExpulsivoOLD r) {
        super(r);
        this.horas = r.getHoras();
        this.partoTipo = r.getPartoTipo();
        this.phFetal = r.getPhFetal();
        this.temperatura = r.getTemperatura();
        this.interpretacioRC = r.getInterpretacioRC();
        this.indicacionCesarea = r.getIndicacionCesarea();
    }

    public void iniciaExpulsivo() {
        this.plantilla_editor = PLANTILLLA_EDITOR_PAR_EXPULSIVO;
        this.tiporegistro = TIPO_REGISTRO_PARTO;
        this.descripcion = " Parto Expulsivo";
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));

        this.horas = VAR_PARTO_EXPUL_HORAS;
        this.partoTipo = VAR_PARTO_EXPUL_TIPOPARTO_;
        this.phFetal = VAR_PARTO_EXPUL_PHFETAL;
        this.temperatura = VAR_PARTO_EXPUL_T;
        this.interpretacioRC = VAR_PARTO_EXPUL_INTERPRETACIONRC;
        this.indicacionCesarea = VAR_PARTO_EXPUL_INDICACIONCESAREA;
    }

    public Variable getHoras() {
        return horas;
    }

    public Variable getVariableHoras() {
        return horas;
    }

    public String getHorasString() {
        return horas.getValor();
    }

    public void setHoras(Variable horas) {
        this.horas = horas;
    }

    public void setHoras(String valor) {
        this.horas.setValor(valor);
    }

    public Variable getPartoTipo() {
        return partoTipo;
    }

    public Variable getVariablePartoTipo() {
        return partoTipo;
    }

    public String getPartoTipoString() {
        return partoTipo.getValor();
    }

    public void setPartoTipo(Variable partoTipo) {
        this.partoTipo = partoTipo;
    }

    public void setPartoTipo(String valor) {
        this.partoTipo.setValor(valor);
    }

    public Variable getPhFetal() {
        return phFetal;
    }

    public Variable getVariablePhFetal() {
        return phFetal;
    }

    public String getPhFetalString() {
        return phFetal.getValor();
    }

    public void setPhFetal(Variable phFetal) {
        this.phFetal = phFetal;
    }

    public void setPhFetal(String valor) {
        this.phFetal.setValor(valor);
    }

    public Variable getTemperatura() {
        return temperatura;
    }

    public Variable getVariableTemperatura() {
        return temperatura;
    }

    public String getTemperaturaString() {
        return temperatura.getValor();
    }

    public void setTemperatura(Variable temperatura) {
        this.temperatura = temperatura;
    }

    public void setTemperatura(String valor) {
        this.temperatura.setValor(valor);
    }

    public Variable getInterpretacioRC() {
        return interpretacioRC;
    }

    public Variable getVariableInterpretacioRC() {
        return interpretacioRC;
    }

    public String getInterpretacioRCString() {
        return interpretacioRC.getValor();
    }

    public void setInterpretacioRC(Variable interpretacioRC) {
        this.interpretacioRC = interpretacioRC;
    }

    public void setInterpretacioRC(String valor) {
        this.interpretacioRC.setValor(valor);
    }

    public Variable getIndicacionCesarea() {
        return indicacionCesarea;
    }

    public Variable getVariableIndicacionCesarea() {
        return indicacionCesarea;
    }

    public String getIndicacionCesareaString() {
        return indicacionCesarea.getValor();
    }

    public void setIndicacionCesarea(Variable indicacionCesarea) {
        this.indicacionCesarea = indicacionCesarea;
    }

    public void setIndicacionCesarea(String valor) {
        this.indicacionCesarea.setValor(valor);
    }
}
