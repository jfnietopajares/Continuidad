package es.sacyl.hnss.entity;

public class RegistroEnfCanalizacionVias extends Registro {

    private Variable tipoVia;
    public final Variable VAR_ENF_CANALI_TIPOVIA = new Variable("Tipo", "13826585", "99G2", new Long(13826585),
            "Tipo de via");
    private Variable localizacionVia;
    public final Variable VAR_ENF_CANALI_LOCALIVIA = new Variable("Localización", "13826584", "99G2",
            new Long(13826584), "Localización de la via");

    private Variable numeroVia;
    public final Variable VAR_ENF_CANALI_NUMEROVIA = new Variable("Número", "410680006", "SNM3", new Long(13822333),
            "Número de la via");

    private Variable observaVia;
    public final Variable VAR_ENF_CANALI_OBSERVAVIA = new Variable("Observaciones", "246453008", "SNM3",
            new Long(46293677), "Observaciones de la via");

    public final static Long PLANTILLLA_EDITOR_ENF_CANALI = new Long(342179211);
    public final static Long TIPO_REGISTRO_ENF_CANALI = new Long(6);

    public RegistroEnfCanalizacionVias() {
        super();
        this.iniciaCanali();
    }

    public RegistroEnfCanalizacionVias(Long id) {
        super(id);
        this.iniciaCanali();
    }

    public RegistroEnfCanalizacionVias(RegistroEnfCanalizacionVias r) {
        super(r);
        this.tipoVia = r.getTipoVia();
        this.localizacionVia = getLocalizacionVia();
        this.numeroVia = getNumeroVia();
        this.observaVia = getObservaVia();
    }

    public void iniciaCanali() {
        this.setTiporegistro(RegistroEnfCanalizacionVias.TIPO_REGISTRO_ENF_CANALI);
        this.setDescripcion("Canalización vías ");
        this.setPlantilla_edior(RegistroEnfCanalizacionVias.PLANTILLLA_EDITOR_ENF_CANALI);

        this.tipoVia = VAR_ENF_CANALI_TIPOVIA;
        this.localizacionVia = VAR_ENF_CANALI_LOCALIVIA;
        this.numeroVia = VAR_ENF_CANALI_NUMEROVIA;
        this.observaVia = VAR_ENF_CANALI_OBSERVAVIA;
    }

    public Variable getTipoVia() {
        return tipoVia;
    }

    public Variable getVariableTipoVia() {
        return tipoVia;
    }

    public String getTipoViaString() {
        return tipoVia.getValor();
    }

    public void setTipoVia(Variable tipoVia) {
        this.tipoVia = tipoVia;
    }

    public void setTipoVia(String valor) {
        this.tipoVia.setValor(valor);
    }

    public Variable getLocalizacionVia() {
        return localizacionVia;
    }

    public Variable getVariableLocalizacionVia() {
        return localizacionVia;
    }

    public String getLocalizacionViaString() {
        return localizacionVia.getValor();
    }

    public void setLocalizacionVia(Variable localizacionVia) {
        this.localizacionVia = localizacionVia;
    }

    public void setLocalizacionVia(String valor) {
        this.localizacionVia.setValor(valor);
    }

    public Variable getNumeroVia() {
        return numeroVia;
    }

    public Variable getVariableNumeroVia() {
        return numeroVia;
    }

    public String getNumeroViaString() {
        return numeroVia.getValor();
    }

    public void setNumeroVia(Variable numeroVia) {
        this.numeroVia = numeroVia;
    }

    public void setNumeroVia(String valor) {
        this.numeroVia.setValor(valor);
    }

    public Variable getObservaVia() {
        return observaVia;
    }

    public Variable getVariableObservaVia() {
        return observaVia;
    }

    public String getObservaViaString() {
        return observaVia.getValor();
    }

    public void setObservaVia(Variable observaVia) {
        this.observaVia = observaVia;
    }

    public void setObservaVia(String valor) {
        this.observaVia.setValor(valor);
    }

    public String getContenidoHtml(Boolean conuser, Boolean conFecha) {
        String texto = "";
        if (conuser == true) {
            texto = texto.concat("<b>" + this.getUserid().getApellidosNombre() + "</b>");
        }
        if (conFecha == true) {
            texto = texto.concat("&nbsp;&nbsp;" + this.getFechaHora() + "<br>");
        }
        texto = texto.concat("<b>Tipo via:</b>" + this.getTipoVia().getValor() + "<b>Localización:</b>" + this.getLocalizacionVia().getValor() + "<b>Nº</b>" + this.getNumeroVia().getValor() + "<br>");
        texto = texto.concat("<b>Observaciones</b>: " + this.getObservaVia().getValor() + "<hr>");
        return texto;
    }
}
