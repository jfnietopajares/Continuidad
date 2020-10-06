package es.sacyl.hnss.entity;

public class RegistroQuiSegSalida extends Registro {

    private Variable nombreProce;
    private Variable contaje;
    private Variable glucemiaRealizado;
    private Variable glucemiaNoproce;
    private Variable hipotermiaRealizado;
    private Variable hipotermiaNoproce;
    private Variable identiMuestras;
    private Variable identiMuestrasNoproce;
    private Variable profiTrombo;
    private Variable profiTromboNoproce;
    private Variable profiAntibi;
    private Variable profiAntibioNoproce;
    private Variable pasosCritRecu;
    private Variable observaciones;
    private Variable checkFinalizado;
    public final static Long PLANTILLLA_EDITOR_QUISEGSALIDA = new Long(327529971);
    public final static Long TIPO_REGISTRO_QUI_SEG = new Long(50);

    public final Variable VAR_QUISEG_SALIDA_NOMBREPRO = new Variable("13825710", "99G2", new Long(13825710),
            "Nombre del procedimiento", "Nombre del procedimiento que se registra", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_SALIDA_CONTAJE = new Variable("13825711", "99G2", new Long(13825711),
            "Contaje de gasas, agujas e instrumental correcto", "Contaje de gasas, agujas e instrumental correcto",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_SALIDA_GLUCEMIA = new Variable("13990344", "99G2", new Long(13990344),
            "Control glucemia realizado", "Control glucemia realizado", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_SALIDA_GLUCEMIANOPROCE = new Variable("13990364", "99G2", new Long(13990364),
            "Control glucemia no procede", "Control glucemia no procede", Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_SALIDA_HIPOTER = new Variable("13990365", "99G2", new Long(13990365),
            "Prevención hipotermia confirmado", "Prevención hipotermia confirmado", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_SALIDA_HIPOTERNOPROCE = new Variable("13990366", "99G2", new Long(13990366),
            "Prevención hipotermia no procede", "Prevención hipotermia no procede", Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_SALIDA_IDENMUESTR = new Variable("13825712", "99G2", new Long(13825712),
            "Identificación y gestión de las muestras biológicas",
            "Identificación y gestión de las muestras biológicas", Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_SALIDA_IDENMUESTRNOPROCE = new Variable("13825715", "99G2", new Long(13825715),
            "Identificación muestras biológicas no procede", "Identificación muestras biológicas no procede",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_SALIDA_TROMBO = new Variable("13822084", "99G2", new Long(13822084),
            "Profilaxis de enfermedad tromboembólica venosa", "Profilaxis de enfermedad tromboembólica venosa",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_SALIDA_TROBONOPROCE = new Variable("13821951", "99G2", new Long(13821951),
            "Profilaxis antitrombótica no procede", "Profilaxis antitrombótica no procede",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_SALIDA_ANTIBIO = new Variable("13819045", "99G2", new Long(13819045),
            " Profilaxis antibiótica", " Profilaxis antibiótica (salida checklist quirúrgico)",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_SALIDA_ANTIBIONOPROCE = new Variable("13821949", "99G2", new Long(13821949),
            "Profilaxis antibiótica no procede", "Profilaxis antibiótica no procede", Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_SALIDA_PASOSCRIT = new Variable("13819044", "99G2", new Long(13819044),
            "Pasos críticos recuperación y tratamiento", "Pasos críticos recuperación y tratamiento",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_SALIDA_OBSERVA = new Variable("246453008", "SNM3", new Long(46293677),
            "Observaciones", "Observaciones", Variable.TIPO_VARIABLE_STRING);

    public final Variable VAR_QUISEG_SALIDA_FINCHECK = new Variable("13822188", "99G2", new Long(13822188),
            "Checklist quirúrgico finalizado", "Checklist quirúrgico finalizado", Variable.TIPO_VARIABLE_CHECK);

    public RegistroQuiSegSalida() {
        super();
        iniciaRegistroSalida();
    }

    public RegistroQuiSegSalida(Long id) {
        super(id);
        iniciaRegistroSalida();
    }

    public RegistroQuiSegSalida(RegistroQuiSegSalida r) {
        super(r);
        this.nombreProce = r.getNombreProce();
        this.contaje = r.getContaje();
        this.glucemiaRealizado = r.getGlucemiaRealizado();
        this.glucemiaNoproce = r.getGlucemiaNoproce();
        this.hipotermiaRealizado = r.getHipotermiaRealizado();
        this.hipotermiaNoproce = r.getHipotermiaNoproce();
        this.identiMuestras = r.getIdentiMuestras();
        this.identiMuestrasNoproce = r.getIdentiMuestrasNoproce();
        this.profiTrombo = r.getProfiTrombo();
        this.profiTromboNoproce = r.getProfiTromboNoproce();
        this.profiAntibi = r.getProfiAntibi();
        this.profiAntibioNoproce = r.getProfiAntibioNoproce();
        this.pasosCritRecu = r.getPasosCritRecu();
        this.observaciones = r.getObservaciones();
        this.checkFinalizado = r.getCheckFinalizado();
    }

    public void iniciaRegistroSalida() {
        this.setTiporegistro(TIPO_REGISTRO_QUI_SEG);
        this.setDescripcion("Registro checkList salida ");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_QUISEGSALIDA);
        // this.setServicio();
        this.nombreProce = VAR_QUISEG_SALIDA_NOMBREPRO;
        this.contaje = VAR_QUISEG_SALIDA_CONTAJE;
        this.glucemiaRealizado = VAR_QUISEG_SALIDA_GLUCEMIA;
        this.glucemiaNoproce = VAR_QUISEG_SALIDA_GLUCEMIANOPROCE;
        this.hipotermiaRealizado = VAR_QUISEG_SALIDA_HIPOTER;
        this.hipotermiaNoproce = VAR_QUISEG_SALIDA_HIPOTERNOPROCE;
        this.identiMuestras = VAR_QUISEG_SALIDA_IDENMUESTR;
        this.identiMuestrasNoproce = VAR_QUISEG_SALIDA_IDENMUESTRNOPROCE;
        this.profiTrombo = VAR_QUISEG_SALIDA_TROMBO;
        this.profiTromboNoproce = VAR_QUISEG_SALIDA_TROBONOPROCE;
        this.profiAntibi = VAR_QUISEG_SALIDA_ANTIBIO;
        this.profiAntibioNoproce = VAR_QUISEG_SALIDA_ANTIBIONOPROCE;
        this.pasosCritRecu = VAR_QUISEG_SALIDA_PASOSCRIT;
        this.observaciones = VAR_QUISEG_SALIDA_OBSERVA;
        this.checkFinalizado = VAR_QUISEG_SALIDA_FINCHECK;

    }

//---
    public Variable getNombreProce() {
        return nombreProce;
    }

    public Variable getVariableNombreProce() {
        return nombreProce;
    }

    public Boolean getNombreProceBoolean() {
        if (nombreProce != null && !nombreProce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setNombreProce(Variable nombreProce) {
        this.nombreProce = nombreProce;
    }

    public void setNombreProce(Boolean valor) {
        if (valor == true) {
            this.nombreProce.setValor("Nombre del procedimiento");
        } else {
            this.nombreProce.setValor("");
        }
    }

    public Variable getContaje() {
        return contaje;
    }

    public Variable getVariableContaje() {
        return contaje;
    }

    public Boolean getContajeBoolean() {
        if (contaje != null && !contaje.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setContaje(Variable contaje) {
        this.contaje = contaje;
    }

    public void setContaje(Boolean valor) {
        if (valor == true) {
            this.contaje.setValor(" Contaje de gasas, agujas e instrumental correcto");
        } else {
            this.contaje.setValor("");
        }
    }

    public Variable getGlucemiaRealizado() {
        return glucemiaRealizado;
    }

    public Variable getVariableGlucemiaRealizado() {
        return glucemiaRealizado;
    }

    public Boolean getGlucemiaRealizadoBoolean() {
        if (glucemiaRealizado != null && !glucemiaRealizado.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setGlucemiaRealizado(Variable glucemiaRealizado) {
        this.glucemiaRealizado = glucemiaRealizado;
    }

    public void setGlucemiaRealizado(Boolean valor) {
        if (valor == true) {
            this.glucemiaRealizado.setValor("Control glucemia realizado");
        } else {
            this.glucemiaRealizado.setValor("");
        }
    }

    public Variable getGlucemiaNoproce() {
        return glucemiaNoproce;
    }

    public Variable getVariableGlucemiaNoproce() {
        return glucemiaNoproce;
    }

    public Boolean getGlucemiaNoproceBoolean() {
        if (glucemiaNoproce != null && !glucemiaNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setGlucemiaNoproce(Variable glucemiaNoproce) {
        this.glucemiaNoproce = glucemiaNoproce;
    }

    public void setGlucemiaNoproce(Boolean valor) {
        if (valor == true) {
            this.glucemiaNoproce.setValor("Control glucemia no procede");
        } else {
            this.glucemiaNoproce.setValor("");
        }
    }

    public Variable getHipotermiaRealizado() {
        return hipotermiaRealizado;
    }

    public Variable getVariableHipotermiaRealizado() {
        return hipotermiaRealizado;
    }

    public Boolean getHipotermiaRealizadoBoolean() {
        if (hipotermiaRealizado != null && !hipotermiaRealizado.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHipotermiaRealizado(Variable hipotermiaRealizado) {
        this.hipotermiaRealizado = hipotermiaRealizado;
    }

    public void setHipotermiaRealizado(Boolean valor) {
        if (valor == true) {
            this.hipotermiaRealizado.setValor("Prevención hipotermia confirmado");
        } else {
            this.hipotermiaRealizado.setValor("");
        }
    }

    public Variable getHipotermiaNoproce() {
        return hipotermiaNoproce;
    }

    public Variable getVariableHipotermiaNoproce() {
        return hipotermiaNoproce;
    }

    public Boolean getHipotermiaNoproceBoolean() {
        if (hipotermiaNoproce != null && !hipotermiaNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHipotermiaNoproce(Variable hipotermiaNoproce) {
        this.hipotermiaNoproce = hipotermiaNoproce;
    }

    public void setHipotermiaNoproce(Boolean valor) {
        if (valor == true) {
            this.hipotermiaNoproce.setValor("Prevención hipotermia no procede");
        } else {
            this.hipotermiaNoproce.setValor("");
        }
    }

    public Variable getIdentiMuestras() {
        return identiMuestras;
    }

    public Variable getVariableIdentiMuestras() {
        return identiMuestras;
    }

    public Boolean getIdentiMuestrasBoolean() {
        if (identiMuestras != null && !identiMuestras.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIdentiMuestras(Variable identiMuestras) {
        this.identiMuestras = identiMuestras;
    }

    public void setIdentiMuestras(Boolean valor) {
        if (valor == true) {
            this.identiMuestras.setValor("Identificación y gestión de las muestras biológicas");
        } else {
            this.identiMuestras.setValor("");
        }
    }

    public Variable getIdentiMuestrasNoproce() {
        return identiMuestrasNoproce;
    }

    public Variable getVariableIdentiMuestrasNoproce() {
        return identiMuestrasNoproce;
    }

    public Boolean getIdentiMuestrasNoproceBoolean() {
        if (identiMuestrasNoproce != null && !identiMuestrasNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIdentiMuestrasNoproce(Variable identiMuestrasNoproce) {
        this.identiMuestrasNoproce = identiMuestrasNoproce;
    }

    public void setIdentiMuestrasNoproce(Boolean valor) {
        if (valor == true) {
            this.identiMuestrasNoproce.setValor("Identificación muestras biológicas no procede");
        } else {
            this.identiMuestrasNoproce.setValor("");
        }
    }

    public Variable getProfiTrombo() {
        return profiTrombo;
    }

    public Variable getVariableProfiTrombo() {
        return profiTrombo;
    }

    public Boolean getProfiTromboBoolean() {
        if (profiTrombo != null && !profiTrombo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setProfiTrombo(Variable profiTrombo) {
        this.profiTrombo = profiTrombo;
    }

    public void setProfiTrombo(Boolean valor) {
        if (valor == true) {
            this.profiTrombo.setValor(" Profilaxis de enfermedad tromboembólica venosa");
        } else {
            this.profiTrombo.setValor("");
        }
    }

    public Variable getProfiTromboNoproce() {
        return profiTromboNoproce;
    }

    public Variable getVariableProfiTromboNoproce() {
        return profiTromboNoproce;
    }

    public Boolean getProfiTromboNoproceBoolean() {
        if (profiTromboNoproce != null && !profiTromboNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setProfiTromboNoproce(Variable profiTromboNoproce) {
        this.profiTromboNoproce = profiTromboNoproce;
    }

    public void setProfiTromboNoproce(Boolean valor) {
        if (valor == true) {
            this.profiTromboNoproce.setValor("Profilaxis antitrombótica no procede");
        } else {
            this.profiTromboNoproce.setValor("");
        }
    }

    public Variable getProfiAntibi() {
        return profiAntibi;
    }

    public Variable getVariableProfiAntibi() {
        return profiAntibi;
    }

    public Boolean getProfiAntibiBoolean() {
        if (profiAntibi != null && !profiAntibi.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setProfiAntibi(Variable profiAntibi) {
        this.profiAntibi = profiAntibi;
    }

    public void setProfiAntibi(Boolean valor) {
        if (valor == true) {
            this.profiAntibi.setValor("Profilaxis antibiótica");
        } else {
            this.profiAntibi.setValor("");
        }
    }

    public Variable getProfiAntibioNoproce() {
        return profiAntibioNoproce;
    }

    public Variable getVariableProfiAntibioNoproce() {
        return profiAntibioNoproce;
    }

    public Boolean getProfiAntibioNoproceBoolean() {
        if (profiAntibioNoproce != null && !profiAntibioNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setProfiAntibioNoproce(Variable profiAntibioNoproce) {
        this.profiAntibioNoproce = profiAntibioNoproce;
    }

    public void setProfiAntibioNoproce(Boolean valor) {
        if (valor == true) {
            this.profiAntibioNoproce.setValor("Profilaxis antibiótica no procede");
        } else {
            this.profiAntibioNoproce.setValor("");
        }
    }

    public Variable getPasosCritRecu() {
        return pasosCritRecu;
    }

    public Variable getVariablePasosCritRecu() {
        return pasosCritRecu;
    }

    public Boolean getPasosCritRecuBoolean() {
        if (pasosCritRecu != null && !pasosCritRecu.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPasosCritRecu(Variable pasosCritRecu) {
        this.pasosCritRecu = pasosCritRecu;
    }

    public void setPasosCritRecu(Boolean valor) {
        if (valor == true) {
            this.pasosCritRecu.setValor("Pasos críticos recuperación y tratamiento");
        } else {
            this.pasosCritRecu.setValor("");
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

    public Variable getCheckFinalizado() {
        return checkFinalizado;
    }

    public Variable getVariableCheckFinalizado() {
        return checkFinalizado;
    }

    public Boolean getCheckFinalizadoBoolean() {
        if (checkFinalizado != null && !checkFinalizado.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCheckFinalizado(Variable checkFinalizado) {
        this.checkFinalizado = checkFinalizado;
    }

    public void setCheckFinalizado(Boolean valor) {
        if (valor == true) {
            this.checkFinalizado.setValor("Checklist quirúrgico finalizado");
        } else {
            this.checkFinalizado.setValor("");
        }
    }

}
