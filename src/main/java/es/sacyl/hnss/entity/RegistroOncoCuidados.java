package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.ConstantesClinicas;



public class RegistroOncoCuidados extends Registro {

    // estas variable uso las mismas del formulario valoración seguridad accesos
    private Variable accesoVenoso;
    private Variable accesoLocalizacion;
    private Variable accesoExtraccion;
    private Variable accesoExtraDescri;
    private Variable ttoDosis;
    private Variable ttoVia;
    // falta esta variable
    private Variable ttoHoraini;

    private Variable tas;
    private Variable tad;
    private Variable temperatura;
    private Variable freCardiaca;
    private Variable satOxigeno;
    private Variable glucosa;

    private Variable reaccAlergica;
    private Variable flebitis;
    private Variable extravasacion;
    private Variable obstruccionCate;
    private Variable nauseas;
    private Variable vomitos;
    private Variable otrasComplica;

    private Variable infoPaProcedimiento;
    private Variable infoPaEfectos;
    private Variable infoPaSigAlarma;
    private Variable observaciones;

    public final static Long PLANTILLLA_EDITOR_ONCO_CUIDA = new Long(285779456);
    public final static Long TIPO_REGISTRO_ONCO = new Long(6);

    public final Variable VAR_ONCO_CUIDADOS_ACCESO = new Variable("13822328", "99G2", new Long(13822328),
            "Portador acceso venoso");
    public final Variable VAR_ONCO_CUIDADOS_ACCESOLOCA = new Variable("13825350", "99G2", new Long(13825350),
            "Localización acceso");
    public final Variable VAR_ONCO_CUIDADOS_EXTRACCION = new Variable("13817904", "99G2", new Long(13817904),
            "Extracción sangre ");
    public final Variable VAR_ONCO_CUIDADOS_EXTRADESCRI = new Variable("13817905", "99G2", new Long(13817905),
            "Extracción sangre descripción");
    public final Variable VAR_ONCO_CUIDADOS_TTODOSIS = new Variable("398232005", "SNM3", new Long(13822864),
            "Tratamiento dosis fármaco ");
    public final Variable VAR_ONCO_CUIDADOS_TTOVIA = new Variable("103389009", "SNM3", new Long(13827945),
            "Tratamiento vía administración  ");
    public final Variable VAR_ONCO_CUIDADOS_TTOHORAINI = new Variable("103389009", "SNM3", new Long(13827945),
            "Hora inicio tratamiento  ");

    public final Variable VAR_ONCO_CUIDADOS_TAS = new ConstantesClinicas().VAR_CTES_TAS;

    public final Variable VAR_ONCO_CUIDADOS_TAD = new ConstantesClinicas().VAR_CTES_TAD;

    public final Variable VAR_ONCO_CUIDADOS_T = new ConstantesClinicas().VAR_CTES_T;

    public final Variable VAR_ONCO_CUIDADOS_FC = new ConstantesClinicas().VAR_CTES_FC;

    public final Variable VAR_ONCO_CUIDADOS_SAT02 = new ConstantesClinicas().VAR_CTES_SATO2;

    public final Variable VAR_ONCO_CUIDADOS_GLU = new ConstantesClinicas().VAR_CTES_GLU;

    public final Variable VAR_ONCO_CUIDADOS_ALERGIA = new Variable("212999007", "SNM3", new Long(13817906),
            "Reacción alérgica   ");
    public final Variable VAR_ONCO_CUIDADOS_FLEBI = new Variable("61599003", "SNM3", new Long(13821004), "Flebitis ");
    public final Variable VAR_ONCO_CUIDADOS_EXTRASVA = new Variable("76676007", "SNM3", new Long(35001942),
            "Extravasación");
    public final Variable VAR_ONCO_CUIDADOS_OBSTRUC = new Variable("275413005", "SNM3", new Long(13817907),
            "Obstrucción CATETER");
    public final Variable VAR_ONCO_CUIDADOS_NAUSEAS = new Variable("73879007", "SNM3", new Long(13822632), "Naúseas");
    public final Variable VAR_ONCO_CUIDADOS_VOMITOS = new Variable("422400008", "SNM3", new Long(13820925), "Vómitos");
    public final Variable VAR_ONCO_CUIDADOS_OTRASCOMPL = new Variable("HR02", "99G2", new Long(13536366),
            "Otras complicaciones");

    public final Variable VAR_ONCO_CUIDADOS_INFOPROCE = new Variable("71388002", "SNM3", new Long(14124584),
            "Información procedimiento");
    public final Variable VAR_ONCO_CUIDADOS_SIGNOSALARMA = new Variable("13817908", "99G2", new Long(13817908),
            "Información signos alarma");
    public final Variable VAR_ONCO_CUIDADOS_EFECTOSADVERSO = new Variable("45376003", "SNM3", new Long(7756634),
            "Información  efectos adversos");

    public final Variable VAR_ONCO_CUIDADOS_OBSERVACIONES = new Variable("246453008", "SNM3", new Long(46293677),
            "Observaciones");

    public RegistroOncoCuidados() {
        super();
        iniciaOncoCuidados();
    }

    public RegistroOncoCuidados(Long id) {
        super(id);
        iniciaOncoCuidados();
    }

    public RegistroOncoCuidados(RegistroOncoCuidados r) {
        super(r);
        this.accesoVenoso = r.getAccesoVenoso();
        this.accesoLocalizacion = r.getAccesoLocalizacion();
        this.accesoExtraccion = r.getAccesoExtraccion();
        this.accesoExtraDescri = r.getAccesoExtraDescri();
        this.ttoDosis = r.getTtoDosis();
        this.ttoVia = r.getTtoVia();
        this.ttoHoraini = r.getTtoVia();
//
        this.tas = r.getTas();
        this.tad = r.getTad();
        this.temperatura = r.getTemperatura();
        this.freCardiaca = r.getFreCardiaca();
        this.satOxigeno = r.getSatOxigeno();
        this.glucosa = r.getGlucosa();

        this.reaccAlergica = r.getReaccAlergica();
        this.flebitis = r.getFlebitis();
        this.extravasacion = r.getExtravasacion();
        this.obstruccionCate = r.getObstruccionCate();
        this.nauseas = r.getNauseas();
        this.vomitos = r.getVomitos();
        this.otrasComplica = r.getOtrasComplica();

        this.infoPaProcedimiento = r.getInfoPaProcedimiento();
        this.infoPaEfectos = r.getInfoPaEfectos();
        this.infoPaSigAlarma = r.getInfoPaSigAlarma();
        this.observaciones = r.getObservaciones();
    }

    public void iniciaOncoCuidados() {
        this.setTiporegistro(TIPO_REGISTRO_ONCO);
        this.setDescripcion("Registro de cuidados  HDIA oncológico");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_ONCO_CUIDA);
        this.setServicio(Servicio.SERVICIO_ONCOLOGIA);

        this.accesoVenoso = VAR_ONCO_CUIDADOS_ACCESO;
        this.accesoLocalizacion = VAR_ONCO_CUIDADOS_ACCESOLOCA;
        this.accesoExtraccion = VAR_ONCO_CUIDADOS_EXTRACCION;
        this.accesoExtraDescri = VAR_ONCO_CUIDADOS_EXTRADESCRI;
        this.ttoDosis = VAR_ONCO_CUIDADOS_TTODOSIS;
        this.ttoVia = VAR_ONCO_CUIDADOS_TTOVIA;
        // falta esta variable
        this.ttoHoraini = VAR_ONCO_CUIDADOS_TTOHORAINI;
//
        this.tas = VAR_ONCO_CUIDADOS_TAS;
        this.tad = VAR_ONCO_CUIDADOS_TAD;
        this.temperatura = VAR_ONCO_CUIDADOS_T;
        this.freCardiaca = VAR_ONCO_CUIDADOS_FC;
        this.satOxigeno = VAR_ONCO_CUIDADOS_SAT02;
        this.glucosa = VAR_ONCO_CUIDADOS_GLU;

        this.reaccAlergica = VAR_ONCO_CUIDADOS_ALERGIA;
        this.flebitis = VAR_ONCO_CUIDADOS_FLEBI;
        this.extravasacion = VAR_ONCO_CUIDADOS_EXTRASVA;
        this.obstruccionCate = VAR_ONCO_CUIDADOS_OBSTRUC;
        this.nauseas = VAR_ONCO_CUIDADOS_NAUSEAS;
        this.vomitos = VAR_ONCO_CUIDADOS_VOMITOS;
        this.otrasComplica = VAR_ONCO_CUIDADOS_OTRASCOMPL;

        this.infoPaProcedimiento = VAR_ONCO_CUIDADOS_INFOPROCE;
        this.infoPaEfectos = VAR_ONCO_CUIDADOS_EFECTOSADVERSO;
        this.infoPaSigAlarma = VAR_ONCO_CUIDADOS_SIGNOSALARMA;
        this.observaciones = VAR_ONCO_CUIDADOS_OBSERVACIONES;
    }

    public Variable getAccesoVenoso() {
        return accesoVenoso;
    }

    public Variable getVariableAccesoVenoso() {
        return accesoVenoso;
    }

    public String getAccesoVenosoString() {
        return accesoVenoso.getValor();
    }

    public void setAccesoVenoso(Variable accesoVenoso) {
        this.accesoVenoso = accesoVenoso;
    }

    public void setAccesoVenoso(String valor) {
        this.accesoVenoso.setValor(valor);
    }

    public Variable getAccesoLocalizacion() {
        return accesoLocalizacion;
    }

    public Variable getVariableAccesoLocalizacion() {
        return accesoLocalizacion;
    }

    public String getAccesoLocalizacionString() {
        return accesoLocalizacion.getValor();
    }

    public void setAccesoLocalizacion(String valor) {
        this.accesoLocalizacion.setValor(valor);
    }

    public void setAccesoLocalizacion(Variable valor) {
        this.accesoLocalizacion = valor;
    }

    public Variable getAccesoExtraccion() {
        return accesoExtraccion;
    }

    public Variable getVariableAccesoExtraccion() {
        return accesoExtraccion;
    }

    public Boolean getAccesoExtraccionBoolean() {
        if (accesoExtraccion != null && !accesoExtraccion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAccesoExtraccion(Variable accesoExtraccion) {
        this.accesoExtraccion = accesoExtraccion;
    }

    public void setAccesoExtraccion(Boolean valor) {
        if (valor == true) {
            this.accesoExtraccion.setValor("Extracción de sangre");
        } else {
            this.accesoExtraccion.setValor("");
        }
    }

    public Variable getAccesoExtraDescri() {
        return accesoExtraDescri;
    }

    public Variable getVariableAccesoExtraDescri() {
        return accesoExtraDescri;
    }

    public String getAccesoExtraDescriString() {
        return accesoExtraDescri.getValor();
    }

    public void setAccesoExtraDescri(Variable accesoExtraDescri) {
        this.accesoExtraDescri = accesoExtraDescri;
    }

    public void setAccesoExtraDescri(String valor) {
        this.accesoExtraDescri.setValor(valor);
    }

    public Variable getTtoDosis() {
        return ttoDosis;
    }

    public Variable getVariableTtoDosis() {
        return ttoDosis;
    }

    public String getTtoDosisString() {
        return ttoDosis.getValor();
    }

    public void setTtoDosis(Variable ttoDosis) {
        this.ttoDosis = ttoDosis;
    }

    public void setTtoDosis(String valor) {
        this.ttoDosis.setValor(valor);
    }

    public Variable getTtoVia() {
        return ttoVia;
    }

    public Variable getVariableTtoVia() {
        return ttoVia;
    }

    public String getTtoViaString() {
        return ttoVia.getValor();
    }

    public void setTtoVia(Variable ttoVia) {
        this.ttoVia = ttoVia;
    }

    public void setTtoVia(String valor) {
        this.ttoVia.setValor(valor);
    }

    public Variable getTtoHoraini() {
        return ttoHoraini;
    }

    public Variable getVariableTtoHoraini() {
        return ttoHoraini;
    }

    public String getTtoHorainiString() {
        return ttoHoraini.getValor();
    }

    public void setTtoHoraini(Variable ttoHoraini) {
        this.ttoHoraini = ttoHoraini;
    }

    public void setTtoHoraini(String valor) {
        this.ttoHoraini.setValor(valor);
    }

    public Variable getTas() {
        return tas;
    }

    public void setTas(Variable tas) {
        this.tas = tas;
    }

    public void setTas(String valor) {
        this.tas.setValor(valor);
    }

    public Variable getTad() {
        return tad;
    }

    public Variable getVariableTad() {
        return tad;
    }

    public String getTadString() {
        return tad.getValor();
    }

    public void setTad(Variable tad) {
        this.tad = tad;
    }

    public void setTad(String valor) {
        this.tad.setValor(valor);
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

    public Variable getFreCardiaca() {
        return freCardiaca;
    }

    public Variable getVariableFreCardiaca() {
        return freCardiaca;
    }

    public String getFreCardiacaString() {
        return freCardiaca.getValor();
    }

    public void setFreCardiaca(Variable freCardiaca) {
        this.freCardiaca = freCardiaca;
    }

    public void setFreCardiaca(String valor) {
        this.freCardiaca.setValor(valor);
    }

    public Variable getSatOxigeno() {
        return satOxigeno;
    }

    public Variable getVariableSatOxigeno() {
        return satOxigeno;
    }

    public String getSatOxigenoString() {
        return satOxigeno.getValor();
    }

    public void setSatOxigeno(Variable satOxigeno) {
        this.satOxigeno = satOxigeno;
    }

    public void setSatOxigeno(String valor) {
        this.satOxigeno.setValor(valor);
    }

    public Variable getGlucosa() {
        return glucosa;
    }

    public Variable getVariableGlucosa() {
        return glucosa;
    }

    public String getGlucosaString() {
        return glucosa.getValor();
    }

    public void setGlucosa(Variable glucosa) {
        this.glucosa = glucosa;
    }

    public void setGlucosa(String valor) {
        this.glucosa.setValor(valor);
    }

    public Variable getReaccAlergica() {
        return reaccAlergica;
    }

    public Variable getVariableReaccAlergica() {
        return reaccAlergica;
    }

    public Boolean getReaccAlergicaBolean() {
        if (reaccAlergica != null && !reaccAlergica.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setReaccAlergica(Variable reaccAlergica) {
        this.reaccAlergica = reaccAlergica;
    }

    public void setReaccAlergica(Boolean valor) {
        if (valor == true) {
            this.reaccAlergica.setValor("Relación alérgica");
        } else {
            this.reaccAlergica.setValor("");
        }

    }

    public Variable getFlebitis() {
        return flebitis;
    }

    public Variable getVariableFlebitis() {
        return flebitis;
    }

    public Boolean getFlebitisBoolean() {
        if (flebitis != null && !flebitis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFlebitis(Variable flebitis) {
        this.flebitis = flebitis;
    }

    public void setFlebitis(Boolean valor) {
        if (valor == true) {
            this.flebitis.setValor("Flebitis");
        } else {
            this.flebitis.setValor("");
        }
    }

    public Variable getExtravasacion() {
        return extravasacion;
    }

    public Variable getVariableExtravasacion() {
        return extravasacion;
    }

    public Boolean getExtravasacionBoolean() {
        if (extravasacion != null && !extravasacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setExtravasacion(Variable extravasacion) {
        this.extravasacion = extravasacion;
    }

    public void setExtravasacion(Boolean valor) {
        if (valor == true) {
            this.extravasacion.setValor("Extrasvasación");
        } else {
            this.extravasacion.setValor("");
        }
    }

    public Variable getObstruccionCate() {
        return obstruccionCate;
    }

    public Variable getVariableObstruccionCate() {
        return obstruccionCate;
    }

    public Boolean getObstruccionCateBoolean() {
        if (obstruccionCate != null && !obstruccionCate.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setObstruccionCate(Variable obstruccionCate) {
        this.obstruccionCate = obstruccionCate;
    }

    public void setObstruccionCate(Boolean valor) {
        if (valor == true) {
            this.obstruccionCate.setValor("Obstrucción del catéter");
        } else {
            this.obstruccionCate.setValor("");
        }
    }

    public void setObstruccionCate(String valor) {
        this.obstruccionCate.setValor(valor);
    }

    public Variable getNauseas() {
        return nauseas;
    }

    public Variable getVariableNauseas() {
        return nauseas;
    }

    public Boolean getNauseasBoolean() {
        if (nauseas != null && !nauseas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setNauseas(Variable nauseas) {
        this.nauseas = nauseas;
    }

    public void setNauseas(Boolean valor) {
        if (valor == true) {
            this.nauseas.setValor("Nauseas");
        } else {
            this.nauseas.setValor("");
        }
    }

    public Variable getVomitos() {
        return vomitos;
    }

    public Variable getVariableVomitos() {
        return vomitos;
    }

    public Boolean getVomitosBoolean() {
        if (vomitos != null && !vomitos.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setVomitos(Variable vomitos) {
        this.vomitos = vomitos;
    }

    public void setVomitos(Boolean valor) {
        if (valor == true) {
            this.vomitos.setValor("Vómitos");
        } else {
            this.vomitos.setValor("");
        }
    }

    public Variable getOtrasComplica() {
        return otrasComplica;
    }

    public Variable getVariableOtrasComplica() {
        return otrasComplica;
    }

    public String getOtrasComplicaString() {
        return otrasComplica.getValor();
    }

    public void setOtrasComplica(Variable otrasComplica) {
        this.otrasComplica = otrasComplica;
    }

    public void setOtrasComplica(String valor) {
        this.otrasComplica.setValor(valor);
    }

    public Variable getInfoPaProcedimiento() {
        return infoPaProcedimiento;
    }

    public Variable getVariableInfoPaProcedimiento() {
        return infoPaProcedimiento;
    }

    public Boolean getInfoPaProcedimientoBoolean() {
        if (infoPaProcedimiento != null && !infoPaProcedimiento.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInfoPaProcedimiento(Variable infoPaProcedimiento) {
        this.infoPaProcedimiento = infoPaProcedimiento;
    }

    public void setInfoPaProcedimiento(Boolean valor) {
        if (valor == true) {
            this.infoPaProcedimiento.setValor("Información del procedimiento");
        } else {
            this.infoPaProcedimiento.setValor("");
        }
    }

    public Variable getInfoPaEfectos() {
        return infoPaEfectos;
    }

    public Variable getVariableInfoPaEfectos() {
        return infoPaEfectos;
    }

    public String getInfoPaEfectosString() {
        return infoPaEfectos.getValor();
    }

    public void setInfoPaEfectos(Variable infoPaEfectos) {
        this.infoPaEfectos = infoPaEfectos;
    }

    public void setInfoPaEfectos(String valor) {
        this.infoPaEfectos.setValor(valor);
    }

    public Variable getInfoPaSigAlarma() {
        return infoPaSigAlarma;
    }

    public Variable getVariableInfoPaSigAlarma() {
        return infoPaSigAlarma;
    }

    public Boolean getInfoPaSigAlarmaBoolean() {
        if (infoPaSigAlarma != null && !infoPaSigAlarma.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInfoPaSigAlarma(Variable infoPaSigAlarma) {
        this.infoPaSigAlarma = infoPaSigAlarma;
    }

    public void setInfoPaSigAlarma(Boolean valor) {
        if (valor == true) {
            this.infoPaSigAlarma.setValor("Paciente informado de signos de alarma");
        } else {
            this.infoPaSigAlarma.setValor("");
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

    public String getComplicacionesTodas() {
        String cadena = "";
        if (this.getReaccAlergica() != null && !this.getReaccAlergica().getValor().isEmpty()) {
            cadena = cadena.concat(this.getReaccAlergica().getValor() + ".");
        }
        if (this.getFlebitis() != null && !this.getFlebitis().getValor().isEmpty()) {
            cadena = cadena.concat(this.getFlebitis().getValor() + ".");
        }
        if (this.getExtravasacion() != null && !this.getExtravasacion().getValor().isEmpty()) {
            cadena = cadena.concat(this.getExtravasacion().getValor() + ".");
        }
        if (this.getObstruccionCate() != null && !this.getObstruccionCate().getValor().isEmpty()) {
            cadena = cadena.concat(this.getObstruccionCate().getValor() + ".");
        }
        if (this.getNauseas() != null && !this.getNauseas().getValor().isEmpty()) {
            cadena = cadena.concat(this.getNauseas().getValor() + ".");
        }
        if (this.getVomitos() != null && !this.getVomitos().getValor().isEmpty()) {
            cadena = cadena.concat(this.getVomitos().getValor() + ".");
        }
        if (this.getOtrasComplica() != null && !this.getOtrasComplica().getValor().isEmpty()) {
            cadena = cadena.concat(this.getOtrasComplica().getValor() + ".");
        }
        return cadena;
    }

    public String getContenidoHtml() {
        String texto = "";
        texto = texto.concat("<b>" + this.getUserid().getApellidosNombre() + "</b>");
        texto = texto.concat("&nbsp;&nbsp;" + this.getFechaHora() + "<br>");
        texto = texto.concat("<b>Acceso</b>: " + this.getAccesoVenoso().getValor() + ":" + this.getAccesoExtraDescri().getValor() + "<br>");
        texto = texto.concat("<b>Extracción</b>: " + this.getAccesoExtraccion().getValor() + ":" + this.getAccesoExtraDescri().getValor() + "<br>");
        texto = texto.concat("<b>Administración</b>: " + this.getTtoHoraini().getValor() + "." + this.getTtoDosis().getValor() + "." + this.getTtoVia().getValor() + "<br>");
        texto = texto.concat("<b>Complicaciones:</b>: " + this.getComplicacionesTodas() + "<hr>");
        texto = texto.concat("<b>Información:</b>: " + this.getInfoPaEfectos().getValor() + "." + this.getInfoPaProcedimiento().getValor() + "<hr>");
        texto = texto.concat("<b>Efectos secundarios</b>: " + this.getInfoPaEfectos().getValor() + "<hr>");
        texto = texto.concat("<b>Observaciones</b>: " + this.getObservacionesString() + "<hr>");
        return texto;
    }
}
