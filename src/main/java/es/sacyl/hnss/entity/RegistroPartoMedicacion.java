package es.sacyl.hnss.entity;

public class RegistroPartoMedicacion extends Registro {

    // private Variable analgesia;
    private Variable oxitocina;
    private Variable analgesiaAntihemetico;
    private Variable sbg;
    private Variable ram;
    private Variable diabetes;
    private Variable preeclampsia;
    private Variable cesarea;
    private Variable otraMedicacion;

    // public final Variable VAR_PARTO_MEDICA_ANALGESIA = new Variable("38433004",
    // "SNM3", new Long(46002096),
    // "Analgesia");
    public final Variable VAR_PARTO_MEDICA_OXITOCINA = new Variable("2718-5", "LN", new Long(609), "Oxitocina");

    public final Variable VAR_PARTO_MEDICA_ANALGESIAANTIHEMETIC = new Variable("322770009", "SNM3", new Long(13994654),
            "Analgesia Antihemético");
    public final Variable VAR_PARTO_MEDICA_SBG = new Variable("13994655", "99G2", new Long(13994655), "Protocolo SBG");
    public final Variable VAR_PARTO_MEDICA_RAM = new Variable("13994656", "99G2", new Long(13994656),
            "Protocolo RAM  ");
    public final Variable VAR_PARTO_MEDICA_DIABETES = new Variable("13994657", "99G2", new Long(13994657),
            "Protocolo Diabetes");
    public final Variable VAR_PARTO_MEDICA_PRECLAMPSIA = new Variable("13994658", "99G2", new Long(13994658),
            "Protocolo Preclampsia ");
    public final Variable VAR_PARTO_MEDICA_CESAREA = new Variable("13994659", "99G2", new Long(13994659),
            "Protocolo Cesárea ");
    public final Variable VAR_PARTO_MEDICA_OTRAS = new Variable("13994660", "99G2", new Long(13994660),
            "Otra medicación  ");

    public final static Long PLANTILLLA_EDITOR_PAR_MEDICAMENTO = new Long(794875959);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public RegistroPartoMedicacion() {
        super();
        iniciaMedicacion();
    }

    public RegistroPartoMedicacion(Long id) {
        super(id);
        iniciaMedicacion();
    }

    public RegistroPartoMedicacion(RegistroPartoMedicacion r) {
        super(r);
        this.oxitocina = r.getOxitocina();
        this.analgesiaAntihemetico = r.getAnalgesiaAntihemetico();
        this.sbg = r.getSbg();
        this.ram = r.getRam();
        this.diabetes = r.getDiabetes();
        this.preeclampsia = r.getPreeclampsia();
        this.cesarea = r.getCesarea();
        this.otraMedicacion = r.getOtraMedicacion();
    }

    public void iniciaMedicacion() {
        this.plantilla_editor = PLANTILLLA_EDITOR_PAR_MEDICAMENTO;
        this.tiporegistro = TIPO_REGISTRO_PARTO;
        this.setDescripcion("7.Medicación");
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));

        this.oxitocina = VAR_PARTO_MEDICA_OXITOCINA;
        this.analgesiaAntihemetico = VAR_PARTO_MEDICA_ANALGESIAANTIHEMETIC;
        this.sbg = VAR_PARTO_MEDICA_RAM;
        this.ram = VAR_PARTO_MEDICA_RAM;
        this.diabetes = VAR_PARTO_MEDICA_DIABETES;
        this.preeclampsia = VAR_PARTO_MEDICA_PRECLAMPSIA;
        this.cesarea = VAR_PARTO_MEDICA_CESAREA;
        this.otraMedicacion = VAR_PARTO_MEDICA_OTRAS;

    }

    public Variable getOxitocina() {
        return oxitocina;
    }

    public Variable getVariableOxitocina() {
        return oxitocina;
    }

    public Boolean getOxitocinaBoolen() {
        if (oxitocina != null && oxitocina.getValor() != null && !oxitocina.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setOxitocina(Variable oxitocina) {
        this.oxitocina = oxitocina;
    }

    public void setOxitocina(Boolean valor) {
        if (valor == true) {
            oxitocina.setValor("Oxitocina ");
        } else {
            oxitocina.setValor("");
        }

    }

    public Variable getAnalgesiaAntihemetico() {
        return analgesiaAntihemetico;
    }

    public Variable getVariableAnalgesiaAntihemetico() {
        return analgesiaAntihemetico;
    }

    public String getAnalgesiaAntihemeticoString() {
        return analgesiaAntihemetico.getValor();
    }

    public boolean getAnalgesiaAntihemeticoBoolean() {
        if (analgesiaAntihemetico != null && analgesiaAntihemetico.getValor() != null
                && !analgesiaAntihemetico.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAnalgesiaAntihemetico(Variable analgesiaAntihemetico) {
        this.analgesiaAntihemetico = analgesiaAntihemetico;
    }

    public void setAnalgesiaAntihemetico(Boolean valor) {
        if (valor == true) {
            analgesiaAntihemetico.setValor("Analgesia y Antihemético");
        } else {
            analgesiaAntihemetico.setValor("");
        }
    }

    public Variable getSbg() {
        return sbg;
    }

    public Variable getVariableSbg() {
        return sbg;
    }

    public String getSbgString() {
        return sbg.getValor();
    }

    public boolean getSbgBoolean() {
        if (sbg != null && sbg.getValor() != null && !sbg.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSbg(Variable sbg) {
        this.sbg = sbg;
    }

    public void setSbg(Boolean valor) {
        if (valor == true) {
            sbg.setValor("Protocolo SBG");
        } else {
            sbg.setValor("");
        }
    }

    public Variable getRam() {
        return ram;
    }

    public Variable getVariableRam() {
        return ram;
    }

    public String getRamString() {
        return ram.getValor();
    }

    public Boolean getRamBoolean() {
        if (ram != null && ram.getValor() != null && !ram.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRam(Variable ram) {
        this.ram = ram;
    }

    public void setRam(Boolean valor) {
        if (valor == true) {
            ram.setValor("Protocolo RAM");
        } else {
            ram.setValor("");
        }
    }

    public Variable getDiabetes() {
        return diabetes;
    }

    public Variable getVariableDiabetes() {
        return diabetes;
    }

    public String getDiabetesString() {
        return diabetes.getValor();
    }

    public boolean getDiabetesBoolean() {
        if (diabetes != null && diabetes.getValor() != null && !diabetes.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setDiabetes(Variable diabetes) {
        this.diabetes = diabetes;
    }

    public void setDiabetes(Boolean valor) {
        if (valor == true) {
            diabetes.setValor("Protocolo Diabetes");
        } else {
            diabetes.setValor("");
        }
    }

    public Variable getPreeclampsia() {
        return preeclampsia;
    }

    public Variable getVariablePreeclampsia() {
        return preeclampsia;
    }

    public String getPreeclampsiaString() {
        return preeclampsia.getValor();
    }

    public boolean getPreeclampsiBooleana() {
        if (preeclampsia != null && preeclampsia.getValor() != null && !preeclampsia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPreeclampsia(Variable preeclampsia) {
        this.preeclampsia = preeclampsia;
    }

    public void setPreeclampsia(boolean valor) {
        if (valor == true) {
            preeclampsia.setValor("Protocolo Preeclampsia");
        } else {
            preeclampsia.setValor("");
        }
    }

    public Variable getCesarea() {
        return cesarea;
    }

    public Variable getVariableCesarea() {
        return cesarea;
    }

    public String getCesareaString() {
        return cesarea.getValor();
    }

    public Boolean getCesareaBoolean() {
        if (cesarea != null && cesarea.getValor() != null && !cesarea.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCesarea(Variable cesarea) {
        this.cesarea = cesarea;
    }

    public void setCesarea(Boolean valor) {
        if (valor == true) {
            cesarea.setValor("Protocolo Cesárea");
        } else {
            cesarea.setValor("");
        }
    }

    public Variable getOtraMedicacion() {
        return otraMedicacion;
    }

    public Variable getVariableOtraMedicacion() {
        return otraMedicacion;
    }

    public String getOtraMedicacionString() {
        return otraMedicacion.getValor();
    }

    public void setOtraMedicacion(Variable otraMedicacion) {
        this.otraMedicacion = otraMedicacion;
    }

    public void setOtraMedicacion(String valor) {
        this.otraMedicacion.setValor(valor);
    }

    public String getMedicacionesTodas() {
        String texto = "";
        if (oxitocina != null) {
            texto = texto.concat(oxitocina.getValor());
        }

        if (analgesiaAntihemetico != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(analgesiaAntihemetico.getValor());
        }

        if (sbg != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(sbg.getValor());
        }
        if (ram != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(ram.getValor());
        }
        if (diabetes != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(diabetes.getValor());
        }
        if (preeclampsia != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(preeclampsia.getValor());
        }
        if (cesarea != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(cesarea.getValor());
        }
        if (otraMedicacion != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(otraMedicacion.getValor());
        }
        return texto;
    }

}
