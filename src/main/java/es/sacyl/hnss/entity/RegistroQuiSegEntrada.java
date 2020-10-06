package es.sacyl.hnss.entity;

public class RegistroQuiSegEntrada extends Registro {

    private Variable identidad;
    private Variable procedimiento;
    private Variable localizacion;
    private Variable localizacionNoproce;
    private Variable localizacionOkcorr;
    private Variable alergia;
    private Variable alergiaOkcorre;

    private Variable antibioti60min;
    private Variable antibiotiNoporce;
    private Variable antibiotiOkcorre;

    private Variable tep_tvp;
    private Variable tep_tvpNoproce;
    private Variable tep_tvpOkcorre;

    private Variable implante;
    private Variable implanteNoproce;
    private Variable implanteOkcorre;

    private Variable intuDificil;
    private Variable intuDificilNoproce;
    private Variable pulsioxi;

    private Variable riesgoAspira;
    private Variable hemoderiv;
    private Variable hemoderivNoproce;
    private Variable hemoderivOkcorre;

    private Variable observaciones;

    public final static Long PLANTILLLA_EDITOR_QUISEGENTRA = new Long(327368933);
    public final static Long TIPO_REGISTRO_QUI_SEG = new Long(50);

    public final Variable VAR_QUISEG_ENTRADA_IDENTIDAD = new Variable("370786008", "SNM3", new Long(13825666),
            "Identidad del paciente.", "Confirmación previa de la identidad del paciente.",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_ENTRADA_PROCEDIMIENTO = new Variable("422140007", "SNM3", new Long(1989),
            "Confirmación procedimiento", "Confirmación previa del procedimiento a realizar",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_LOCALI = new Variable("10546003", "SNM3", new Long(13822330),
            "Confiración localización", "Confirmación de la localización de la intervención.",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_LOCALIDANOPRO = new Variable("13821945", "99G2", new Long(13821945),
            "Confirmación localización no procede.", "No procede confirmar la localización.",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_LOCALIOKCORR = new Variable("13821946", "99G2", new Long(13821946),
            " Localización ok tras corrección", "Correción en la localización.Ok tras corrección.",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_ENTRADA_ALERGIA = new Variable("13827444", "99G2", new Long(13827444),
            " Alergia  comprobada", "Alergia a medicamentos comprobada", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_ALERGIAOKCORR = new Variable("13821948", "99G2", new Long(13821948),
            "Alergias medicamentosa ok tras corrección.", "Corrección alergia a medicamentos.Ok tras corrección.",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_ENTRADA_ANTIBIO = new Variable("13825668", "99G2", new Long(13825668),
            "Profilaxis antibiótica en los últimos 60 minutos.", "Profilaxis antibiótico en 60 minutos.",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_ANTIBIONOPRO = new Variable("13821949", "99G2", new Long(13821949),
            "Profilaxis antibiótica no procede", "No procede profilasis antitronbótica.", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_ANTIBIOOKCORR = new Variable("13821950", "99G2", new Long(13821950),
            "Profilaxis antibiótica ok tras corrección", "Corrección profilasis antitronbótica.Ok tras corrección.",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_ENTRADA_TEVTVP = new Variable("13825669", "99G2", new Long(13825669),
            "Profilaxis TEP/TVP ", "Profilaxis  antitrombótica.", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_TEVTVPNOPRO = new Variable("13821951", "99G2", new Long(13821951),
            "Profilaxis antitrombótica no procede.", "No procede profilasis antitrombótica.",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_TEVTVPOKCORR = new Variable("13821952", "99G2", new Long(13821952),
            "Profilaxis antitrombótica ok tras corrección.", "Profilasis antitrombótica corregida.Ok tras corrección",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_ENTRADA_INPLAN = new Variable("90134004", "SNM3", new Long(13823925),
            "Implante o prótesis metalica.", "Varificación de implantes o prótesis metálicas.",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_INPLANNOPRO = new Variable("13821953", "99G2", new Long(13821953),
            " Implantes/material especial no procede.", "No procede verificación de implantes.",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_INPLANOKCORR = new Variable("13821954", "99G2", new Long(13821954),
            "Implantes/material especial ok tras corrección.", "Implantes/material especial ok tras corrección.",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_ENTRADA_INTU = new Variable("13821955", "99G2", new Long(13821955),
            "Preparado equipo intubación dificil.", "Preparado equipo intubación dificil.",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_INTUPULXI = new Variable("13825684", "99G2", new Long(13825684),
            "Pulsioxímetro en el paciente ", "Pulsioxímetro en el paciente ", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_INTUNOPROC = new Variable("13822106", "99G2", new Long(13822106),
            "Preparado equipo intubación dificil no procede ", "Preparado equipo intubación dificil no procede ",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_INTURIESASPI = new Variable("371736008", "SNM3", new Long(13825670),
            "Riesgo de aspiración ", "Riesgo de aspiración ", Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_ENTRADA_HEMO = new Variable("13819032", "99G2", new Long(13819032),
            "Necesidad hemoderivados ", "Necesidad hemoderivados ", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_HEMONOPRO = new Variable("13821956", "99G2", new Long(13821956),
            "Necesidad hemoderivados no procede ", "Necesidad hemoderivados no procede ", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_ENTRADA_HEMOOKCORR = new Variable("13821957", "99G2", new Long(13821957),
            "Necesidad hemoderivados ok tras corrección ", "Necesidad hemoderivados ok tras corrección ",
            Variable.TIPO_VARIABLE_CHECK);

    public final Variable VAR_QUISEG_ENTRADA_OBSSER = new Variable("246453008", "SNM3", new Long(46293677),
            "Observaciones ", Variable.TIPO_VARIABLE_STRING);

    public RegistroQuiSegEntrada() {
        super();
        this.iniciaEntrada();
    }

    public RegistroQuiSegEntrada(Long id) {
        super(id);
        this.iniciaEntrada();
    }

    public RegistroQuiSegEntrada(RegistroQuiSegEntrada r) {
        super(r);
        this.identidad = this.getIdentidad();
        this.procedimiento = r.getProcedimiento();
        this.localizacion = r.getLocalizacion();
        this.localizacionNoproce = r.getLocalizacionNoproce();
        this.localizacionOkcorr = r.getLocalizacionOkcorr();
        this.alergia = r.getAlergia();
        this.alergiaOkcorre = r.getAlergiaOkcorre();

        this.antibioti60min = r.getAntibioti60min();
        this.antibiotiNoporce = r.getAntibiotiNoporce();
        this.antibiotiOkcorre = r.getAntibiotiOkcorre();

        this.tep_tvp = r.getTep_tvp();
        this.tep_tvpNoproce = r.getTep_tvpNoproce();
        this.tep_tvpOkcorre = r.getTep_tvpOkcorre();

        this.implante = r.getImplante();
        this.implanteNoproce = r.getImplanteNoproce();
        this.implanteOkcorre = r.getImplanteOkcorre();

        this.intuDificil = r.getIntuDificil();
        this.intuDificilNoproce = r.getIntuDificilNoproce();
        this.pulsioxi = r.getPulsioxi();

        this.riesgoAspira = r.getRiesgoAspira();
        this.hemoderiv = r.getHemoderiv();
        this.hemoderivNoproce = r.getHemoderivNoproce();
        this.hemoderivOkcorre = r.getHemoderivOkcorre();

        this.observaciones = r.getObservaciones();
    }

    public void iniciaEntrada() {
        this.setTiporegistro(TIPO_REGISTRO_QUI_SEG);
        this.setDescripcion("2.CheckList entrada ");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_QUISEGENTRA);

        this.identidad = VAR_QUISEG_ENTRADA_IDENTIDAD;
        this.procedimiento = VAR_QUISEG_ENTRADA_PROCEDIMIENTO;
        this.localizacion = VAR_QUISEG_ENTRADA_LOCALI;
        this.localizacionNoproce = VAR_QUISEG_ENTRADA_LOCALIDANOPRO;
        this.localizacionOkcorr = VAR_QUISEG_ENTRADA_LOCALIOKCORR;
        this.alergia = VAR_QUISEG_ENTRADA_ALERGIA;
        this.alergiaOkcorre = VAR_QUISEG_ENTRADA_ALERGIAOKCORR;

        this.antibioti60min = VAR_QUISEG_ENTRADA_ANTIBIO;
        this.antibiotiNoporce = VAR_QUISEG_ENTRADA_ANTIBIONOPRO;
        this.antibiotiOkcorre = VAR_QUISEG_ENTRADA_ANTIBIOOKCORR;

        this.tep_tvp = VAR_QUISEG_ENTRADA_TEVTVP;
        this.tep_tvpNoproce = VAR_QUISEG_ENTRADA_TEVTVPNOPRO;
        this.tep_tvpOkcorre = VAR_QUISEG_ENTRADA_TEVTVPOKCORR;

        this.implante = VAR_QUISEG_ENTRADA_INPLAN;
        this.implanteNoproce = VAR_QUISEG_ENTRADA_INPLANNOPRO;
        this.implanteOkcorre = VAR_QUISEG_ENTRADA_INPLANOKCORR;

        this.intuDificil = VAR_QUISEG_ENTRADA_INTU;
        this.intuDificilNoproce = VAR_QUISEG_ENTRADA_INTUNOPROC;
        this.pulsioxi = VAR_QUISEG_ENTRADA_INTUPULXI;

        this.riesgoAspira = VAR_QUISEG_ENTRADA_INTURIESASPI;
        this.hemoderiv = VAR_QUISEG_ENTRADA_HEMO;
        this.hemoderivNoproce = VAR_QUISEG_ENTRADA_HEMONOPRO;
        this.hemoderivOkcorre = VAR_QUISEG_ENTRADA_HEMOOKCORR;

        this.observaciones = VAR_QUISEG_ENTRADA_OBSSER;
    }

    public Variable getIdentidad() {
        return identidad;
    }

    public Variable getVariableIdentidad() {
        return identidad;
    }

    public Boolean getIdentidadBoolean() {
        if (identidad != null && !identidad.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIdentidad(Variable identidad) {
        this.identidad = identidad;
    }

    public void setIdentidad(Boolean valor) {
        if (valor == true) {
            this.identidad.setValor("Identidad paciente.");
        } else {
            this.identidad.setValor("");
        }

    }

    public Variable getProcedimiento() {
        return procedimiento;
    }

    public Variable getVariableProcedimiento() {
        return procedimiento;
    }

    public Boolean getProcedimientoBoolean() {
        if (procedimiento != null && !procedimiento.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setProcedimiento(Variable procedimiento) {
        this.procedimiento = procedimiento;
    }

    public void setProcedimiento(Boolean valor) {
        if (valor == true) {
            this.procedimiento.setValor("Procedimiento verificado.");
        } else {
            this.procedimiento.setValor("");
        }
    }

    public Variable getLocalizacion() {
        return localizacion;
    }

    public Variable getVariableLocalizacion() {
        return localizacion;
    }

    public Boolean getLocalizacionBoolean() {
        if (localizacion != null && !localizacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setLocalizacion(Variable localizacion) {
        this.localizacion = localizacion;
    }

    public void setLocalizacion(Boolean valor) {
        if (valor == true) {
            this.procedimiento.setValor("Localización verificada.");
        } else {
            this.procedimiento.setValor("");
        }
    }

    public Variable getLocalizacionNoproce() {
        return localizacionNoproce;
    }

    public Variable getVariableLocalizacionNoproce() {
        return localizacionNoproce;
    }

    public Boolean getLocalizacionNoproceBoolean() {
        if (localizacionNoproce != null && !localizacionNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setLocalizacionNoproce(Variable localizacionNoproce) {
        this.localizacionNoproce = localizacionNoproce;
    }

    public void setLocalizacionNoproce(Boolean valor) {
        if (valor == true) {
            this.localizacionNoproce.setValor("Localización no procede verificación.");
        } else {
            this.localizacionNoproce.setValor("");
        }
    }

    public Variable getLocalizacionOkcorr() {
        return localizacionOkcorr;
    }

    public Variable getVariableLocalizacionOkcorr() {
        return localizacionOkcorr;
    }

    public Boolean getLocalizacionOkcorrBoolean() {
        if (localizacionOkcorr != null && !localizacionOkcorr.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setLocalizacionOkcorr(Variable localizacionOkcorr) {
        this.localizacionOkcorr = localizacionOkcorr;
    }

    public void setLocalizacionOkcorr(Boolean valor) {
        if (valor == true) {
            this.localizacionOkcorr.setValor("Localización ok tras corrección");
        } else {
            this.localizacionOkcorr.setValor("");
        }
    }

    public Variable getAlergia() {
        return alergia;
    }

    public Variable getVariableAlergia() {
        return alergia;
    }

    public Boolean getAlergiaBoolean() {
        if (alergia != null && !alergia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAlergia(Variable alergia) {
        this.alergia = alergia;
    }

    public void setAlergia(Boolean valor) {
        if (valor == true) {
            this.alergia.setValor("Alergia a medicamentos verificada.");
        } else {
            this.alergia.setValor("");
        }
    }

    public Variable getAlergiaOkcorre() {
        return alergiaOkcorre;
    }

    public Variable getVariableAlergiaOkcorre() {
        return alergiaOkcorre;
    }

    public Boolean getAlergiaOkcorreBoolean() {
        if (alergiaOkcorre != null && !alergiaOkcorre.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAlergiaOkcorre(Variable alergiaOkcorre) {
        this.alergiaOkcorre = alergiaOkcorre;
    }

    public void setAlergiaOkcorre(Boolean valor) {
        if (valor == true) {
            this.alergiaOkcorre.setValor("Alergia a medicamentos ok tras corrección.");
        } else {
            this.alergiaOkcorre.setValor("");
        }
    }

    public Variable getAntibioti60min() {
        return antibioti60min;
    }

    public Variable getVariableAntibioti60min() {
        return antibioti60min;
    }

    public Boolean getAntibioti60minBoolean() {
        if (antibioti60min != null && !antibioti60min.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAntibioti60min(Variable antibioti60min) {
        this.antibioti60min = antibioti60min;
    }

    public void setAntibioti60min(Boolean valor) {
        if (valor == true) {
            this.antibioti60min.setValor("Profilasis antibiótica en los últimos 60 minutos.");
        } else {
            this.antibioti60min.setValor("");
        }
    }

    public Variable getAntibiotiNoporce() {
        return antibiotiNoporce;
    }

    public Variable getVariableAntibiotiNoporce() {
        return antibiotiNoporce;
    }

    public Boolean getAntibiotiNoporceBoolean() {
        if (antibiotiNoporce != null && !antibiotiNoporce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAntibiotiNoporce(Variable antibiotiNoporce) {
        this.antibiotiNoporce = antibiotiNoporce;
    }

    public void setAntibiotiNoporce(Boolean valor) {
        if (valor == true) {
            this.antibiotiNoporce.setValor("Profilasis antibiótica no procede");
        } else {
            this.antibiotiNoporce.setValor("");
        }
    }

    public Variable getAntibiotiOkcorre() {
        return antibiotiOkcorre;
    }

    public Variable getVariableAntibiotiOkcorre() {
        return antibiotiOkcorre;
    }

    public Boolean getAntibiotiOkcorreBoolean() {
        if (antibiotiOkcorre != null && !antibiotiOkcorre.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAntibiotiOkcorre(Variable antibiotiOkcorre) {
        this.antibiotiOkcorre = antibiotiOkcorre;
    }

    public void setAntibiotiOkcorre(Boolean valor) {
        if (valor == true) {
            this.antibiotiOkcorre.setValor("Profilasis antibiótica no procede");
        } else {
            this.antibiotiOkcorre.setValor("");
        }
    }

    public Variable getTep_tvp() {
        return tep_tvp;
    }

    public Variable getVariableTep_tvp() {
        return tep_tvp;
    }

    public boolean getTep_tvpBoolean() {
        if (tep_tvp != null && !tep_tvp.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTep_tvp(Variable tep_tvp) {
        this.tep_tvp = tep_tvp;
    }

    public void setTep_tvp(Boolean valor) {
        if (valor == true) {
            this.tep_tvp.setValor("Profilaxis Antitrombótica verificada.");
        } else {
            this.tep_tvp.setValor("");
        }
    }

    public Variable getTep_tvpNoproce() {
        return tep_tvpNoproce;
    }

    public Variable getVariableTep_tvpNoproce() {
        return tep_tvpNoproce;
    }

    public Boolean getTep_tvpNoproceBoolean() {
        if (tep_tvpNoproce != null && !tep_tvpNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTep_tvpNoproce(Variable tep_tvpNoproce) {
        this.tep_tvpNoproce = tep_tvpNoproce;
    }

    public void setTep_tvpNoproce(Boolean valor) {
        if (valor == true) {
            this.tep_tvpNoproce.setValor("Profilaxis Antitrombótica no procede.");
        } else {
            this.tep_tvpNoproce.setValor("");
        }
    }

    public Variable getTep_tvpOkcorre() {
        return tep_tvpOkcorre;
    }

    public Variable getVariableTep_tvpOkcorre() {
        return tep_tvpOkcorre;
    }

    public Boolean getTep_tvpOkcorreBoolean() {
        if (tep_tvpOkcorre != null && !tep_tvpOkcorre.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTep_tvpOkcorre(Variable tep_tvpOkcorre) {
        this.tep_tvpOkcorre = tep_tvpOkcorre;
    }

    public void setTep_tvpOkcorre(Boolean valor) {
        if (valor == true) {
            this.tep_tvpOkcorre.setValor("Profilaxis Antitrombótica ok tras corrección.");
        } else {
            this.tep_tvpOkcorre.setValor("");
        }
    }

    public Variable getImplante() {
        return implante;
    }

    public Variable getVariableImplante() {
        return implante;
    }

    public Boolean getImplanteBoolean() {
        if (implante != null && !implante.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setImplante(Variable implante) {
        this.implante = implante;
    }

    public void setImplante(Boolean valor) {
        if (valor == true) {
            this.implante.setValor("Implantes / Material especial verificado.");
        } else {
            this.implante.setValor("");
        }
    }

    public Variable getImplanteNoproce() {
        return implanteNoproce;
    }

    public Variable getVariableImplanteNoproce() {
        return implanteNoproce;
    }

    public Boolean getImplanteNoproceBoolean() {
        if (implanteNoproce != null && !implanteNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setImplanteNoproce(Variable implanteNoproce) {
        this.implanteNoproce = implanteNoproce;
    }

    public void setImplanteNoproce(Boolean valor) {
        if (valor == true) {
            this.implanteNoproce.setValor("Implantes / Material no procede.");
        } else {
            this.implanteNoproce.setValor("");
        }
    }

    public Variable getImplanteOkcorre() {
        return implanteOkcorre;
    }

    public Variable getVariableImplanteOkcorre() {
        return implanteOkcorre;
    }

    public Boolean getImplanteOkcorreBoolean() {
        if (implanteOkcorre != null && !implanteOkcorre.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setImplanteOkcorre(Variable implanteOkcorre) {
        this.implanteOkcorre = implanteOkcorre;
    }

    public void setImplanteOkcorre(Boolean valor) {
        if (valor == true) {
            this.implanteOkcorre.setValor("Implantes / Material ok tras corrección.");
        } else {
            this.implanteOkcorre.setValor("");
        }
    }

    public Variable getIntuDificil() {
        return intuDificil;
    }

    public Variable getVariableIntuDificil() {
        return intuDificil;
    }

    public Boolean getIntuDificilBoolean() {
        if (intuDificil != null && !intuDificil.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIntuDificil(Variable intuDificil) {
        this.intuDificil = intuDificil;
    }

    public void setIntuDificil(Boolean valor) {
        if (valor == true) {
            this.intuDificil.setValor("Preparado equipo intubación dificil.");
        } else {
            this.intuDificil.setValor("");
        }
    }

    public Variable getIntuDificilNoproce() {
        return intuDificilNoproce;
    }

    public Variable getVariableIntuDificilNoproce() {
        return intuDificilNoproce;
    }

    public Boolean getIntuDificilNoproceBoolean() {
        if (intuDificilNoproce != null && !intuDificilNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIntuDificilNoproce(Variable intuDificilNoproce) {
        this.intuDificilNoproce = intuDificilNoproce;
    }

    public void setIntuDificilNoproce(Boolean valor) {
        if (valor == true) {
            this.intuDificilNoproce.setValor("Equipo intubación dificil no procede.");
        } else {
            this.intuDificilNoproce.setValor("");
        }
    }

    public Variable getPulsioxi() {
        return pulsioxi;
    }

    public Variable getVariablePulsioxi() {
        return pulsioxi;
    }

    public Boolean getPulsioxiBollean() {
        if (pulsioxi != null && !pulsioxi.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPulsioxi(Variable pulsioxi) {
        this.pulsioxi = pulsioxi;
    }

    public void setPulsioxi(Boolean valor) {
        if (valor == true) {
            this.pulsioxi.setValor("Pulsiox / chequeo respirador verificado.");
        } else {
            this.pulsioxi.setValor("");
        }
    }

    public Variable getRiesgoAspira() {
        return riesgoAspira;
    }

    public Variable getVariableRiesgoAspira() {
        return riesgoAspira;
    }

    public Boolean getRiesgoAspiraBoolean() {
        if (riesgoAspira != null && !riesgoAspira.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRiesgoAspira(Variable riesgoAspira) {
        this.riesgoAspira = riesgoAspira;
    }

    public void setRiesgoAspira(Boolean valor) {
        if (valor == true) {
            this.riesgoAspira.setValor(" Vía aérea / riesgo de aspiración verificado");
        } else {
            this.riesgoAspira.setValor("");
        }
    }

    public Variable getHemoderiv() {
        return hemoderiv;
    }

    public Variable getVariableHemoderiv() {
        return hemoderiv;
    }

    public Boolean getHemoderivBoolean() {
        if (hemoderiv != null && !hemoderiv.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHemoderiv(Variable hemoderiv) {
        this.hemoderiv = hemoderiv;
    }

    public void setHemoderiv(Boolean valor) {
        if (valor == true) {
            this.hemoderiv.setValor("Necesidad de hemoderivados confirmada.");
        } else {
            this.hemoderiv.setValor("");
        }
    }

    public Variable getHemoderivNoproce() {
        return hemoderivNoproce;
    }

    public Variable getVariableHemoderivNoproce() {
        return hemoderivNoproce;
    }

    public Boolean getHemoderivNoproceBoolean() {
        if (hemoderivNoproce != null && !hemoderivNoproce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHemoderivNoproce(Variable hemoderivNoproce) {
        this.hemoderivNoproce = hemoderivNoproce;
    }

    public void setHemoderivNoproce(Boolean valor) {
        if (valor == true) {
            this.hemoderivNoproce.setValor("Necesidad de hemoderivados no procede.");
        } else {
            this.hemoderivNoproce.setValor("");
        }
    }

    public Variable getHemoderivOkcorre() {
        return hemoderivOkcorre;
    }

    public Variable getVariableHemoderivOkcorre() {
        return hemoderivOkcorre;
    }

    public Boolean getHemoderivOkcorreBoolean() {
        if (hemoderivOkcorre != null && !hemoderivOkcorre.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHemoderivOkcorre(Variable hemoderivOkcorre) {
        this.hemoderivOkcorre = hemoderivOkcorre;
    }

    public void setHemoderivOkcorre(Boolean valor) {
        if (valor == true) {
            this.hemoderivOkcorre.setValor("Necesidad de hemoderivados on tras corrección");
        } else {
            this.hemoderivOkcorre.setValor("");
        }
    }

    public Variable getObservaciones() {
        return observaciones;
    }

    public Variable getVariableObservaciones() {
        return observaciones;
    }

    public String getObservacionesStgring() {
        return observaciones.getValor();
    }

    public void setObservaciones(Variable observaciones) {
        this.observaciones = observaciones;
    }

    public void setObservaciones(String valor) {
        this.observaciones.setValor(valor);
    }

}
