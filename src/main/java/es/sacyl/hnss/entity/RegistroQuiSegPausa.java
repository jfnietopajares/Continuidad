package es.sacyl.hnss.entity;

public class RegistroQuiSegPausa extends Registro {

    private Variable procePosicion;
    private Variable pasosCriticos;
    private Variable imagenes;
    private Variable viaAerea;
    private Variable pasosCriticosAnes;
    private Variable glucema;
    private Variable glucemaNoProce;
    private Variable hiptermia;
    private Variable hiptermiaNoProce;
    private Variable instrumental;
    private Variable instrumentalOkcorre;
    private Variable equipos;
    private Variable equiposOkCoree;
    private Variable observaciones;

    public final static Long PLANTILLLA_EDITOR_QUISEGPAUSA = new Long(327529625);
    public final static Long TIPO_REGISTRO_QUI_SEG = new Long(50);

    public final Variable VAR_QUISEG_PAUSA_PROCEPOS = new Variable("13819033", "99G2", new Long(13819033),
            "Procedimiento (posición)", "Procedimiento (posición)", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_PASOSCRICIR = new Variable("13819034", "99G2", new Long(13819034),
            "Pasos críticos (cirujano)", "Pasos críticos (cirujano)", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_IMAGENES = new Variable("13819036", "99G2", new Long(13819036),
            "Imágenes diagnósticas", "Imágenes diagnósticas", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_VIAAERE = new Variable("13819037", "99G2", new Long(13819037),
            "Vía aerea/riesgo aspiración", "Vía aerea/riesgo aspiración (anestesista pausa en checklist quirúrgico)",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_PASOCRIANES = new Variable("13819035", "99G2", new Long(13819035),
            "Pasos críticos (anestesista)", "Pasos críticos (anestesista)", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_GLUCE = new Variable("13990344", "99G2", new Long(13990344),
            "Control glucemia realizado", "Control glucemia realizado", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_GLUNOPRO = new Variable("13990364", "99G2", new Long(13990364),
            "Control glucemia no procede", "Control glucemia no procede", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_HIPOTER = new Variable("13990365", "99G2", new Long(13990365),
            "Prevención hipotermia confirmado", "Prevención hipotermia confirmado", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_HIPONOPROCE = new Variable("13990366", "99G2", new Long(13990366),
            "Prevención hipotermia no procede", "Prevención hipotermia no procede", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_INSTRU = new Variable("13819038", "99G2", new Long(13819038),
            "Instrumental quirúrgico", "Instrumental quirúrgico", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_INTRUOKCORR = new Variable("13821958", "99G2", new Long(13821958),
            "Instrumental ok tras corrección", "Instrumental ok tras corrección", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_EQUIPO = new Variable("13819039", "99G2", new Long(13819039), "Equipos",
            "Equipos", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_EQUIPOOKCORR = new Variable("13821959", "99G2", new Long(13821959),
            "Equipos ok tras corrección", "Equipos ok tras corrección", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PAUSA_OBSERVA = new Variable("246453008", "99G2", new Long(246453008),
            "Observaciones", "Observaciones", Variable.TIPO_VARIABLE_STRING);

    public RegistroQuiSegPausa() {
        super();
        iniciaQuiSegPausa();
    }

    public RegistroQuiSegPausa(Long id) {
        super(id);
        iniciaQuiSegPausa();
    }

    public RegistroQuiSegPausa(RegistroQuiSegPausa r) {
        super(r);
        this.setTiporegistro(TIPO_REGISTRO_QUI_SEG);
        this.setDescripcion("Registro checkList pausa ");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_QUISEGPAUSA);

        this.procePosicion = r.getProcePosicion();
        this.pasosCriticos = r.getPasosCriticos();
        this.imagenes = r.getImagenes();
        this.viaAerea = r.getViaAerea();
        this.pasosCriticosAnes = r.getPasosCriticos();
        this.glucema = r.getGlucema();
        this.glucemaNoProce = r.getGlucemaNoProce();
        this.hiptermia = r.getHiptermia();
        this.hiptermiaNoProce = r.getHiptermiaNoProce();
        this.instrumental = r.getInstrumental();
        this.instrumentalOkcorre = r.getInstrumentalOkcorre();
        this.equipos = r.getEquipos();
        this.equiposOkCoree = r.getEquiposOkCoree();
        this.observaciones = r.getObservaciones();
    }

    public void iniciaQuiSegPausa() {
        this.setTiporegistro(TIPO_REGISTRO_QUI_SEG);
        this.setDescripcion("Registro checkLis pausa ");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_QUISEGPAUSA);

        this.procePosicion = VAR_QUISEG_PAUSA_PROCEPOS;
        this.pasosCriticos = VAR_QUISEG_PAUSA_PASOSCRICIR;
        this.imagenes = VAR_QUISEG_PAUSA_IMAGENES;
        this.viaAerea = VAR_QUISEG_PAUSA_VIAAERE;
        this.pasosCriticosAnes = VAR_QUISEG_PAUSA_PASOCRIANES;
        this.glucema = VAR_QUISEG_PAUSA_GLUCE;
        this.glucemaNoProce = VAR_QUISEG_PAUSA_HIPONOPROCE;
        this.hiptermia = VAR_QUISEG_PAUSA_HIPOTER;
        this.hiptermiaNoProce = VAR_QUISEG_PAUSA_HIPONOPROCE;
        this.instrumental = VAR_QUISEG_PAUSA_INSTRU;
        this.instrumentalOkcorre = VAR_QUISEG_PAUSA_INTRUOKCORR;
        this.equipos = VAR_QUISEG_PAUSA_EQUIPO;
        this.equiposOkCoree = VAR_QUISEG_PAUSA_EQUIPOOKCORR;
        this.observaciones = VAR_QUISEG_PAUSA_OBSERVA;
    }

//--
    public Variable getProcePosicion() {
        return procePosicion;
    }

    public Variable getVariableProcePosicion() {
        return procePosicion;
    }

    public Boolean getProcePosicionBoolean() {
        if (procePosicion != null && !procePosicion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setProcePosicion(Variable procePosicion) {
        this.procePosicion = procePosicion;
    }

    public void setProcePosicion(Boolean valor) {
        if (valor == true) {
            this.procePosicion.setValor("Pocedimiento posición.");
        } else {
            this.procePosicion.setValor("");
        }
    }

    public Variable getPasosCriticos() {
        return pasosCriticos;
    }

    public Variable getVariablePasosCriticos() {
        return pasosCriticos;
    }

    public Boolean getPasosCriticosBoolean() {
        if (pasosCriticos != null && !pasosCriticos.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPasosCriticos(Variable pasosCriticos) {
        this.pasosCriticos = pasosCriticos;
    }

    public void setPasosCriticos(Boolean valor) {
        if (valor == true) {
            this.pasosCriticos.setValor("Pasos críticos cirujano.");
        } else {
            this.pasosCriticos.setValor("");
        }
    }

    public Variable getImagenes() {
        return imagenes;
    }

    public Variable getVariableImagenes() {
        return imagenes;
    }

    public Boolean getImagenesBoolean() {
        if (imagenes != null && !imagenes.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setImagenes(Variable imagenes) {
        this.imagenes = imagenes;
    }

    public void setImagenes(Boolean valor) {
        if (valor == true) {
            this.imagenes.setValor("Imágenes diagnósticas.");
        } else {
            this.imagenes.setValor("");
        }
    }

    public Variable getViaAerea() {
        return viaAerea;
    }

    public Variable getVariableViaAerea() {
        return viaAerea;
    }

    public Boolean getViaAereaBoolean() {
        if (viaAerea != null && !viaAerea.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setViaAerea(Variable viaAerea) {
        this.viaAerea = viaAerea;
    }

    public void setViaAerea(Boolean valor) {
        if (valor == true) {
            this.viaAerea.setValor("Vía aerea/riesgo aspiración (anestesista pausa en checklist quirúrgico).");
        } else {
            this.viaAerea.setValor("");
        }
    }

    public Variable getGlucema() {
        return glucema;
    }

    public Variable getVariableGlucema() {
        return glucema;
    }

    public Boolean getGlucemaBoolean() {
        if (glucema != null && !glucema.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Variable getPasosCriticosAnes() {
        return pasosCriticosAnes;
    }

    public Variable getVariablePasosCriticosAnes() {
        return pasosCriticosAnes;
    }

    public Boolean getPasosCriticosAnesBoolean() {
        if (pasosCriticosAnes != null && !pasosCriticosAnes.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPasosCriticosAnes(Variable pasosCriticosAnes) {
        this.pasosCriticosAnes = pasosCriticosAnes;
    }

    public void setPasosCriticosAnes(Boolean valor) {
        if (valor == true) {
            this.pasosCriticosAnes.setValor("   Pasos críticos (anestesista)");
        } else {
            this.pasosCriticosAnes.setValor("");
        }
    }

    public void setGlucema(Variable glucema) {
        this.glucema = glucema;
    }

    public void setGlucema(Boolean valor) {
        if (valor == true) {
            this.glucema.setValor("Control glucemia realizado.");
        } else {
            this.glucema.setValor("");
        }
    }

    public Variable getGlucemaNoProce() {
        return glucemaNoProce;
    }

    public Variable getVariableGlucemaNoProce() {
        return glucemaNoProce;
    }

    public Boolean getGlucemaNoProceBoolean() {
        if (glucemaNoProce != null && !glucemaNoProce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setGlucemaNoProce(Variable glucemaNoProce) {
        this.glucemaNoProce = glucemaNoProce;
    }

    public void setGlucemaNoProce(Boolean valor) {
        if (valor == true) {
            this.glucemaNoProce.setValor("Control glucemia no procede");
        } else {
            this.glucemaNoProce.setValor("");
        }
    }

    public Variable getHiptermia() {
        return hiptermia;
    }

    public Variable getVariableHiptermia() {
        return hiptermia;
    }

    public Boolean getHiptermiaBoolean() {
        if (hiptermia != null && !hiptermia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHiptermia(Variable hiptermia) {
        this.hiptermia = hiptermia;
    }

    public void setHiptermia(Boolean valor) {
        if (valor == true) {
            this.hiptermia.setValor("Prevención hipotermia confirmado");
        } else {
            this.hiptermia.setValor("");
        }
    }

    public Variable getHiptermiaNoProce() {
        return hiptermiaNoProce;
    }

    public Variable getVariableHiptermiaNoProce() {
        return hiptermiaNoProce;
    }

    public Boolean getHiptermiaNoProceBoolean() {
        if (hiptermiaNoProce != null && !hiptermiaNoProce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHiptermiaNoProce(Variable hiptermiaNoProce) {
        this.hiptermiaNoProce = hiptermiaNoProce;
    }

    public void setHiptermiaNoProce(Boolean valor) {
        if (valor == true) {
            this.hiptermiaNoProce.setValor("Prevención hipotermia no procede.");
        } else {
            this.hiptermiaNoProce.setValor("");
        }
    }

    public Variable getInstrumental() {
        return instrumental;
    }

    public Variable getVariableInstrumental() {
        return instrumental;
    }

    public Boolean getInstrumentalBoolean() {
        if (instrumental != null && !instrumental.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInstrumental(Variable instrumental) {
        this.instrumental = instrumental;
    }

    public void setInstrumental(Boolean valor) {
        if (valor == true) {
            this.instrumental.setValor("Instrumental quirúrgico");
        } else {
            this.instrumental.setValor("");
        }
    }

    public Variable getInstrumentalOkcorre() {
        return instrumentalOkcorre;
    }

    public Variable getVariableInstrumentalOkcorre() {
        return instrumentalOkcorre;
    }

    public Boolean getInstrumentalOkcorreBoolean() {
        if (instrumentalOkcorre != null && !instrumentalOkcorre.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInstrumentalOkcorre(Variable instrumentalOkcorre) {
        this.instrumentalOkcorre = instrumentalOkcorre;
    }

    public void setInstrumentalOkcorre(Boolean valor) {
        if (valor == true) {
            this.instrumentalOkcorre.setValor("Instrumental ok tras corrección");
        } else {
            this.instrumentalOkcorre.setValor("");
        }
    }

    public Variable getEquipos() {
        return equipos;
    }

    public Variable getVariableEquipos() {
        return equipos;
    }

    public Boolean getEquiposBoolean() {
        if (equipos != null && !equipos.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEquipos(Variable equipos) {
        this.equipos = equipos;
    }

    public void setEquipos(Boolean valor) {
        if (valor == true) {
            this.equipos.setValor("Equipos");
        } else {
            this.equipos.setValor("");
        }
    }

    public Variable getEquiposOkCoree() {
        return equiposOkCoree;
    }

    public Variable getVariableEquiposOkCoree() {
        return equiposOkCoree;
    }

    public Boolean getEquiposOkCoreeBoolean() {
        if (equiposOkCoree != null && !equiposOkCoree.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEquiposOkCoree(Variable equiposOkCoree) {
        this.equiposOkCoree = equiposOkCoree;
    }

    public void setEquiposOkCoree(Boolean valor) {
        if (valor == true) {
            this.equiposOkCoree.setValor("Equipos ok tras corrección.");
        } else {
            this.equiposOkCoree.setValor("");
        }
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

}
