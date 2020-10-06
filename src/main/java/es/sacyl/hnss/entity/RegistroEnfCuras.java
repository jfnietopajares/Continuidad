package es.sacyl.hnss.entity;

public class RegistroEnfCuras extends Registro {

    private Variable curaTipo;
    public final Variable VAR_ENF_CURA_TIPO = new Variable("Tipo", "13822604", "99G2", new Long(13822604),
            "Tipo herida");

    private Variable curaGrado;
    public final Variable VAR_ENF_CURA_GRADO = new Variable("Tipo", "13819485", "99G2", new Long(13819485),
            "Grado de herida");

    private Variable infeccion;
    public final Variable VAR_ENF_CURA_INFECCION = new Variable("Infección", "13819486", "99G2", new Long(13819486),
            "Infección de herida");

    private Variable localizacion;
    public final Variable VAR_ENF_CURA_LOCALIZACION = new Variable("Localización", "10546003", "SNM3",
            new Long(13822330), "Localización de herida");

    private Variable tratamiento;
    public final Variable VAR_ENF_CURA_TRATAMIENTO = new Variable("Tratamiento", "182991002", "SNM3",
            new Long(13743109), "Tratamietno de herida");

    private Variable pauta;
    public final Variable VAR_ENF_CURA_PAUTA = new Variable("Pauta", "35004464", "99G2", new Long(35004464), "Pauta ");

    private Variable observaciones;
    public final Variable VAR_ENF_CURA_OBSERVACIONES = new Variable("Observaciones", "35000983", "99G2",
            new Long(35000983), "Observaciones al tratamiento ");

    public final static Long PLANTILLLA_EDITOR_ENF_CURA = new Long(342132114);
    public final static Long TIPO_REGISTRO_ENF_CURA = new Long(6);

    public RegistroEnfCuras() {
        super();
        this.iniciaCura();
    }

    public RegistroEnfCuras(Long id) {
        super(id);

        this.iniciaCura();
    }

    public RegistroEnfCuras(RegistroEnfCuras r) {

        super(r);

        this.curaTipo = r.getCuraTipO();

        this.curaGrado = getCuraGrado();

        this.infeccion = getInfeccion();

        this.localizacion = getLocalizacion();

        this.tratamiento = getTratamiento();

        this.pauta = getPauta();

        this.observaciones = getObservaciones();

    }

    public void iniciaCura() {

        this.setTiporegistro(RegistroEnfCuras.TIPO_REGISTRO_ENF_CURA);

        this.setDescripcion("Cura de heridas  ");

        this.setPlantilla_edior(RegistroEnfCuras.PLANTILLLA_EDITOR_ENF_CURA);

        this.curaTipo = VAR_ENF_CURA_TIPO;

        this.curaGrado = VAR_ENF_CURA_GRADO;

        this.infeccion = VAR_ENF_CURA_INFECCION;

        this.localizacion = VAR_ENF_CURA_LOCALIZACION;

        this.tratamiento = VAR_ENF_CURA_TRATAMIENTO;

        this.pauta = VAR_ENF_CURA_PAUTA;

        this.observaciones = VAR_ENF_CURA_OBSERVACIONES;

    }

    public Variable getCuraTipO() {
        return curaTipo;
    }

    public Variable getVariableCuraTipO() {
        return curaTipo;
    }

    public String getCuraTipOString() {
        return curaTipo.getValor();
    }

    public void setCuraTipO(Variable curaTipo) {
        this.curaTipo = curaTipo;
    }

    public void setCuraTipO(String valor) {
        this.curaTipo.setValor(valor);
    }

    public Variable getCuraGrado() {
        return curaGrado;
    }

    public Variable getVariableCuraGrado() {
        return curaGrado;
    }

    public String getCuraGradoString() {
        return curaGrado.getValor();
    }

    public void setCuraGrado(Variable curaGrado) {
        this.curaGrado = curaGrado;
    }

    public void setCuraGrado(String valor) {
        this.curaGrado.setValor(valor);
    }

    public Variable getInfeccion() {
        return infeccion;
    }

    public Variable getVariableInfeccion() {
        return infeccion;
    }

    public String getInfeccionString() {
        return infeccion.getValor();
    }

    public void setInfeccion(Variable infeccion) {
        this.infeccion = infeccion;
    }

    public void setInfeccion(String valor) {
        this.infeccion.setValor(valor);
    }

    public Variable getLocalizacion() {
        return localizacion;
    }

    public Variable getVariableLocalizacion() {
        return localizacion;
    }

    public String getLocalizacionString() {
        return localizacion.getValor();
    }

    public void setLocalizacion(Variable localizacion) {
        this.localizacion = localizacion;
    }

    public void setLocalizacion(String valor) {
        this.localizacion.setValor(valor);
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

    public String getContenidoHtml(Boolean conuser, Boolean conFecha) {
        String texto = "";
        if (conuser == true) {
            texto = texto.concat("<b>" + this.getUserid().getApellidosNombre() + "</b>");
        }
        if (conFecha == true) {
            texto = texto.concat("&nbsp;&nbsp;" + this.getFechaHora() + "<br>");
        }

        texto = texto.concat("<b>Tipo :</b>" + this.getCuraTipO().getValor() + "<b>Grado:</b>" + this.getCuraGrado().getValor() + "<b>Infección</b>" + this.getInfeccion().getValor() + "<br>");

        texto = texto.concat("<b>Localizacion</b>: " + this.getLocalizacion().getValor() + "<br>");
        texto = texto.concat("<b>Tratamiento</b>: " + this.getTratamiento().getValor() + "<br>");
        texto = texto.concat("<b>Pauta</b>: " + this.getPauta().getValor() + "<br>");
        texto = texto.concat("<b>Observaciones</b>: " + this.getObservaciones().getValor() + "<hr>");
        return texto;
    }
}
