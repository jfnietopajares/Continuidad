/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entity;

/**
 *
 * @author 06551256M
 */
public class RegistroEnfPruebas extends Registro {

    private Variable analSangreVenosa;
    public final Variable VAR_ENF_PRUEB_ANALSANGREVENA = new Variable("A.S.Venosa", "13819511", "99G2", new Long(13819511), "Analítica sangre venosa");

    private Variable analSangreArterial;
    public final Variable VAR_ENF_PRUEB_ANALSANGREARTER = new Variable("A.S.Arterial", "13819512", "99G2", new Long(13819512), "Analítica sangre arterial");

    private Variable analSangreHemocultivo;
    public final Variable VAR_ENF_PRUEB_ANALHEMOCUL = new Variable("Hemocultivo", "30088009", "SNM3", new Long(13438908), "Analítica hemocultivo");

    private Variable analOrina;
    public final Variable VAR_ENF_PRUEB_ANAORINA = new Variable("Orina", "50070", "99G2", new Long(50070), "Analítica orina");

    private Variable rx;
    public final Variable VAR_ENF_PRUEB_RX = new Variable("RX", "168537006", "SNM3", new Long(13819510), "Prueba de radiología");

    private Variable ecg;
    public final Variable VAR_ENF_PRUEB_ECG = new Variable("ECG", "29303009", "SNM3", new Long(7654641), "Electrocardiograma");

    private Variable ecocardiograma;
    public final Variable VAR_ENF_PRUEB_ECOCAR = new Variable("EcoCar", "40701008", "SNM3", new Long(100013), "Ecocardiograma");

    private Variable puncionLumbar;
    public final Variable VAR_ENF_PRUEB_PUNCION = new Variable("P.Lumbar", "50157", "99G2", new Long(50157), "Punción Lumbar");

    private Variable biopsia;
    public final Variable VAR_ENF_PRUEB_BOPSIA = new Variable("Biopsia", "8692", "99G2", new Long(8692), "Biopsia");

    private Variable MAPA;
    public final Variable VAR_ENF_PRUEB_MAPA = new Variable("MAPA", "13819865", "99G2", new Long(13819865), "MAPA");

    private Variable frotis;
    public final Variable VAR_ENF_PRUEB_FROTIS = new Variable("FROTIS", "13819998", "99G2", new Long(13819998), "FROTIS");

    private Variable cultivo;
    public final Variable VAR_ENF_PRUEB_CULTIVO = new Variable("CULTIVO", "50198", "99G2", new Long(50198), "CULTIVO");

    private Variable caterizacionViaCentral;
    public final Variable VAR_ENF_PRUEB_CATERIZAVIA = new Variable("Cateter.ViaCentral", "13820006", "99G2", new Long(13820006), "Cateterización vía central");

    private Variable paracentesis;
    public final Variable VAR_ENF_PRUEB_PARACENTESIS = new Variable("Paracentesis", "50001", "99G2", new Long(50001), "Paracentesis");

    private Variable artrocentesis;
    public final Variable VAR_ENF_PRUEB_ARTROCEN = new Variable("Artrocentesis", "81.91", "I9C", new Long(103432), "Artrocentesis");

    private Variable toracocentesis;
    public final Variable VAR_ENF_PRUEB_TORACO = new Variable("Artrocentesis", "13820007", "99G2", new Long(13820007), "toracocentesis");

    private Variable drenajeTora;
    public final Variable VAR_ENF_PRUEB_DRENAJETORACO = new Variable("DrenajeT.", "13820008", "99G2", new Long(13820008), "Drenaje torácico");

    private Variable observaciones;
    public final Variable VAR_ENF_PRUEB_OBSERVACIONES = new Variable("Obs.", "246453008", "SNM3", new Long(46293677), "Observaciones ");

    private Variable flebotomia;
    public final Variable VAR_ENF_PRUEB_FLEBOTOMIA = new Variable("Flebotomía.", "13995523", "99G2", new Long(13995523), "Flebotomía ");

    private Variable sangria;
    public final Variable VAR_ENF_PRUEB_SANGRIA = new Variable("Sangría.", "8047", "99G2", new Long(8047), "Sangría terapeútica ");

    public final static Long PLANTILLLA_EDITOR_ENF_PRUEBAS = new Long(342179111);
    public final static Long TIPO_REGISTRO_ENF_PRUEBAS = new Long(6);

    public RegistroEnfPruebas() {
        super();
        this.iniciaPreubas();
    }

    public RegistroEnfPruebas(Long id) {
        super(id);
        this.iniciaPreubas();
    }

    public RegistroEnfPruebas(RegistroEnfPruebas r) {
        super(r);
        this.analSangreVenosa = r.getAnalSangreVenosa();
        this.analSangreArterial = r.getAnalSangreArterial();
        this.analSangreHemocultivo = r.getAnalSangreHemocultivo();
        this.analOrina = r.getAnalOrina();
        this.rx = r.getRx();
        this.ecg = r.getEcg();
        this.ecocardiograma = r.getEcocardiograma();
        this.puncionLumbar = r.getPuncionLumbar();
        this.biopsia = r.getBiopsia();
        this.MAPA = r.getMAPA();
        this.frotis = r.getFrotis();
        this.cultivo = r.getCultivo();
        this.caterizacionViaCentral = r.getCaterizacionViaCentral();
        this.paracentesis = r.getParacentesis();
        this.artrocentesis = r.getArtrocentesis();
        this.toracocentesis = r.getToracocentesis();
        this.drenajeTora = r.getDrenajeTora();
        this.observaciones = r.getObservaciones();
        this.sangria = r.getSangria();
        this.flebotomia = r.getSangria();
    }

    public void iniciaPreubas() {
        this.setTiporegistro(RegistroEnfPruebas.TIPO_REGISTRO_ENF_PRUEBAS);
        this.setDescripcion("Pruebas diagnósticas y/o procedimientos");
        this.setPlantilla_edior(RegistroEnfPruebas.PLANTILLLA_EDITOR_ENF_PRUEBAS);
        this.analSangreVenosa = VAR_ENF_PRUEB_ANALSANGREVENA;
        this.analSangreArterial = VAR_ENF_PRUEB_ANALSANGREARTER;
        this.analSangreHemocultivo = VAR_ENF_PRUEB_ANALHEMOCUL;
        this.analOrina = VAR_ENF_PRUEB_ANAORINA;
        this.rx = VAR_ENF_PRUEB_RX;
        this.ecg = VAR_ENF_PRUEB_ECG;
        this.ecocardiograma = VAR_ENF_PRUEB_ECOCAR;
        this.puncionLumbar = VAR_ENF_PRUEB_PUNCION;
        this.biopsia = VAR_ENF_PRUEB_BOPSIA;
        this.MAPA = VAR_ENF_PRUEB_MAPA;
        this.frotis = VAR_ENF_PRUEB_FROTIS;
        this.cultivo = VAR_ENF_PRUEB_CULTIVO;
        this.caterizacionViaCentral = VAR_ENF_PRUEB_CATERIZAVIA;
        this.paracentesis = VAR_ENF_PRUEB_PARACENTESIS;
        this.artrocentesis = VAR_ENF_PRUEB_ARTROCEN;
        this.toracocentesis = VAR_ENF_PRUEB_TORACO;
        this.drenajeTora = VAR_ENF_PRUEB_DRENAJETORACO;
        this.observaciones = VAR_ENF_PRUEB_OBSERVACIONES;
        this.sangria = VAR_ENF_PRUEB_SANGRIA;
        this.flebotomia = VAR_ENF_PRUEB_FLEBOTOMIA;
    }

    public Variable getAnalSangreVenosa() {
        return analSangreVenosa;
    }

    public Variable getVariableAnalSangreVenosa() {
        return analSangreVenosa;
    }

    public Boolean getAnalSangreVenosaBoolean() {
        if (analSangreVenosa != null && !analSangreVenosa.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAnalSangreVenosa(Variable analSangreVenosa) {
        this.analSangreVenosa = analSangreVenosa;
    }

    public void setAnalSangreVenosa(Boolean valor) {
        if (valor == true) {
            this.analSangreVenosa.setValor("Analítica en sangre venosa");
        } else {
            this.analSangreVenosa.setValor("");
        }
    }

    public Variable getAnalSangreArterial() {
        return analSangreArterial;
    }

    public Variable getVariableAnalSangreArterial() {
        return analSangreArterial;
    }

    public Boolean getAnalSangreArterialBoolean() {
        if (analSangreArterial != null && !analSangreArterial.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAnalSangreArterial(Variable analSangreArterial) {
        this.analSangreArterial = analSangreArterial;
    }

    public void setAnalSangreArterial(Boolean valor) {
        if (valor == true) {
            this.analSangreArterial.setValor("Analítica en sangre arterial");
        } else {
            this.analSangreArterial.setValor("");
        }
    }

    public Variable getAnalSangreHemocultivo() {
        return analSangreHemocultivo;
    }

    public Variable getVariableAnalSangreHemocultivo() {
        return analSangreHemocultivo;
    }

    public Boolean getAnalSangreHemocultivoBoolean() {
        if (analSangreHemocultivo != null && !analSangreHemocultivo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAnalSangreHemocultivo(Variable analSangreHemocultivo) {
        this.analSangreHemocultivo = analSangreHemocultivo;
    }

    public void setAnalSangreHemocultivo(Boolean valor) {
        if (valor == true) {
            this.analSangreHemocultivo.setValor("Analítica en hemocultivo");
        } else {
            this.analSangreHemocultivo.setValor("");
        }

    }

    public Variable getAnalOrina() {
        return analOrina;
    }

    public Variable getVariableAnalOrina() {
        return analOrina;
    }

    public Boolean getAnalOrinaBoolean() {
        if (analOrina != null && !analOrina.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAnalOrina(Variable analOrina) {
        this.analOrina = analOrina;
    }

    public void setAnalOrina(Boolean valor) {
        if (valor == true) {
            this.analOrina.setValor("Analítica en orina");
        } else {
            this.analOrina.setValor("");
        }
    }

    public Variable getRx() {
        return rx;
    }

    public Variable getVariableRx() {
        return rx;
    }

    public Boolean getRxBoolean() {
        if (rx != null && !rx.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRx(Variable rx) {
        this.rx = rx;
    }

    public void setRx(Boolean valor) {
        if (valor == true) {
            this.rx.setValor("Prueba de radiología");
        } else {
            this.rx.setValor("");
        }
    }

    public Variable getEcg() {
        return ecg;
    }

    public Variable getVariableEcg() {
        return ecg;
    }

    public Boolean getEcgBoolean() {
        if (ecg != null && !ecg.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEcg(Variable ecg) {
        this.ecg = ecg;
    }

    public void setEcg(Boolean valor) {
        if (valor == true) {
            this.ecg.setValor("Electrocardiograma");
        } else {
            this.ecg.setValor("");
        }
    }

    public Variable getEcocardiograma() {
        return ecocardiograma;
    }

    public Variable getVariableEcocardiograma() {
        return ecocardiograma;
    }

    public Boolean getEcocardiogramaBoolean() {
        if (ecocardiograma != null && !ecocardiograma.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEcocardiograma(Variable ecocardiograma) {
        this.ecocardiograma = ecocardiograma;
    }

    public void setEcocardiograma(Boolean valor) {
        if (valor == true) {
            this.ecocardiograma.setValor("Ecocardiograma");
        } else {
            this.ecocardiograma.setValor("");
        }
    }

    public Variable getPuncionLumbar() {
        return puncionLumbar;
    }

    public Variable getVariablePuncionLumbar() {
        return puncionLumbar;
    }

    public Boolean getPuncionLumbaBoolean() {
        if (puncionLumbar != null && !puncionLumbar.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPuncionLumbar(Variable puncionLumbar) {
        this.puncionLumbar = puncionLumbar;
    }

    public void setPuncionLumbar(Boolean valor) {
        if (valor == true) {
            this.puncionLumbar.setValor("Punción Lumbar");
        } else {
            this.puncionLumbar.setValor("");
        }
    }

    public Variable getBiopsia() {
        return biopsia;
    }

    public Variable getVariableBiopsia() {
        return biopsia;
    }

    public Boolean getBiopsiaBoolean() {
        if (biopsia != null && !biopsia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setBiopsia(Variable biopsia) {
        this.biopsia = biopsia;
    }

    public void setBiopsia(Boolean valor) {
        if (valor == true) {
            this.biopsia.setValor("Biopsia");
        } else {
            this.biopsia.setValor("");
        }
    }

    public Variable getMAPA() {
        return MAPA;
    }

    public Variable getVariableMAPA() {
        return MAPA;
    }

    public Boolean getMAPABoolean() {
        if (MAPA != null && !MAPA.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMAPA(Variable MAPA) {
        this.MAPA = MAPA;
    }

    public void setMAPA(Boolean valor) {
        if (valor == true) {
            this.MAPA.setValor("MAPA");
        } else {
            this.MAPA.setValor("");
        }
    }

    public Variable getFrotis() {
        return frotis;
    }

    public Variable getVariableFrotis() {
        return frotis;
    }

    public Boolean getFrotisBoolean() {
        if (frotis != null && !frotis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFrotis(Variable frotis) {
        this.frotis = frotis;
    }

    public void setFrois(Boolean valor) {
        if (valor == true) {
            this.frotis.setValor("Frotis");
        } else {
            this.frotis.setValor("");
        }
    }

    public Variable getCultivo() {
        return cultivo;
    }

    public Variable getVariableCultivo() {
        return cultivo;
    }

    public Boolean getCultivoBoolean() {
        if (cultivo != null && !cultivo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCultivo(Variable cultivo) {
        this.cultivo = cultivo;
    }

    public void setCultivo(Boolean valor) {
        if (valor == true) {
            this.cultivo.setValor("Cultivo");
        } else {
            this.cultivo.setValor("");
        }
    }

    public Variable getCaterizacionViaCentral() {
        return caterizacionViaCentral;
    }

    public Variable getVariableCaterizacionViaCentral() {
        return caterizacionViaCentral;
    }

    public Boolean getCaterizacionViaCentralBoolean() {
        if (caterizacionViaCentral != null && !caterizacionViaCentral.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCaterizacionViaCentral(Boolean valor) {
        if (valor == true) {
            this.caterizacionViaCentral.setValor("Cateterización vía central");
        } else {
            this.caterizacionViaCentral.setValor("");
        }
    }

    public void setCaterizacionViaCentral(Variable caterizacionViaCentral) {
        this.caterizacionViaCentral = caterizacionViaCentral;
    }

    public Variable getParacentesis() {
        return paracentesis;
    }

    public Variable getVariableParacentesis() {
        return paracentesis;
    }

    public Boolean getParacentesisBoolean() {
        if (paracentesis != null && !paracentesis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setParacentesis(Variable paracentesis) {
        this.paracentesis = paracentesis;
    }

    public void setParacentesis(Boolean valor) {
        if (valor == true) {
            this.paracentesis.setValor("Paracentesis");
        } else {
            this.paracentesis.setValor("");
        }
    }

    public Variable getArtrocentesis() {
        return artrocentesis;
    }

    public Variable getVariableArtrocentesis() {
        return artrocentesis;
    }

    public Boolean getArtrocentesisBoolean() {
        if (artrocentesis != null && !artrocentesis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setArtrocentesis(Variable artrocentesis) {
        this.artrocentesis = artrocentesis;
    }

    public void setArtrocentesis(Boolean valor) {
        if (valor == true) {
            this.artrocentesis.setValor("Artrocentesis");
        } else {
            this.artrocentesis.setValor("");
        }
    }

    public Variable getToracocentesis() {
        return toracocentesis;
    }

    public Variable getVariableToracocentesis() {
        return toracocentesis;
    }

    public Boolean getToracocentesisBoolean() {
        if (toracocentesis != null && !toracocentesis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setToracocentesis(Variable toracocentesis) {
        this.toracocentesis = toracocentesis;
    }

    public void setToracocentesis(Boolean valor) {
        if (valor == true) {
            this.toracocentesis.setValor("Toracocentesis");
        } else {
            this.toracocentesis.setValor("");
        }
    }

    public Variable getDrenajeTora() {
        return drenajeTora;
    }

    public Variable getVariableDrenajeTora() {
        return drenajeTora;
    }

    public Boolean getDrenajeToraBoolean() {
        if (drenajeTora != null && !drenajeTora.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setDrenajeTora(Variable drenajeTora) {
        this.drenajeTora = drenajeTora;
    }

    public void setDrenajeTora(Boolean valor) {
        if (valor == true) {
            this.drenajeTora.setValor("Drenaje torácico");
        } else {
            this.drenajeTora.setValor("");
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

    public Variable getSangria() {
        return sangria;
    }

    public Variable getVariableSangria() {
        return sangria;
    }

    public Boolean getSangriaBoolean() {
        if (sangria != null && !sangria.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSangria(Variable sangria) {
        this.sangria = sangria;
    }

    public void setSangria(Boolean valor) {
        if (valor == true) {
            this.sangria.setValor("Sangría terapéutica");
        } else {
            this.sangria.setValor("");
        }
    }

    public Variable getFlebotomia() {
        return flebotomia;
    }

    public Variable getVariableFlebotomia() {
        return flebotomia;
    }

    public Boolean getFlebotomiaBoolean() {
        if (flebotomia != null && !flebotomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFlebotomia(Variable flebotomia) {
        this.flebotomia = flebotomia;
    }

    public void setFlebotomia(Boolean valor) {
        if (valor == true) {
            this.flebotomia.setValor("Flebotomía");
        } else {
            this.flebotomia.setValor("");
        }
    }

    public String getTodasLasPruebas() {
        String cadena = "";
        if (analSangreVenosa != null && !analSangreVenosa.getValor().isEmpty()) {
            cadena = cadena.concat(analSangreVenosa.getValor() + ". ");
        }

        if (analSangreArterial != null && !analSangreArterial.getValor().isEmpty()) {
            cadena = cadena.concat(analSangreArterial.getValor() + ". ");
        }

        if (analSangreHemocultivo != null && !analSangreHemocultivo.getValor().isEmpty()) {
            cadena = cadena.concat(analSangreHemocultivo.getValor() + ". ");
        }

        if (analOrina != null && !analOrina.getValor().isEmpty()) {
            cadena = cadena.concat(analOrina.getValor() + ". ");
        }

        if (rx != null && !rx.getValor().isEmpty()) {
            cadena = cadena.concat(rx.getValor() + ". ");
        }

        if (ecg != null && !ecg.getValor().isEmpty()) {
            cadena = cadena.concat(ecg.getValor() + ". ");
        }

        if (ecocardiograma != null && !ecocardiograma.getValor().isEmpty()) {
            cadena = cadena.concat(ecocardiograma.getValor() + ". ");
        }

        if (puncionLumbar != null && !puncionLumbar.getValor().isEmpty()) {
            cadena = cadena.concat(puncionLumbar.getValor() + ". ");
        }

        if (biopsia != null && !biopsia.getValor().isEmpty()) {
            cadena = cadena.concat(biopsia.getValor() + ". ");
        }

        if (MAPA != null && !MAPA.getValor().isEmpty()) {
            cadena = cadena.concat(MAPA.getValor() + ". ");
        }

        if (frotis != null && !frotis.getValor().isEmpty()) {
            cadena = cadena.concat(frotis.getValor() + ". ");
        }

        if (cultivo != null && !cultivo.getValor().isEmpty()) {
            cadena = cadena.concat(cultivo.getValor() + ". ");
        }

        if (caterizacionViaCentral != null && !caterizacionViaCentral.getValor().isEmpty()) {
            cadena = cadena.concat(caterizacionViaCentral.getValor() + ". ");
        }
        if (paracentesis != null && !paracentesis.getValor().isEmpty()) {
            cadena = cadena.concat(paracentesis.getValor() + ". ");
        }
        if (artrocentesis != null && !artrocentesis.getValor().isEmpty()) {
            cadena = cadena.concat(artrocentesis.getValor() + ". ");
        }
        if (toracocentesis != null && !toracocentesis.getValor().isEmpty()) {
            cadena = cadena.concat(toracocentesis.getValor() + ". ");
        }
        if (drenajeTora != null && !drenajeTora.getValor().isEmpty()) {
            cadena = cadena.concat(drenajeTora.getValor() + ". ");
        }

        if (sangria != null && !sangria.getValor().isEmpty()) {
            cadena = cadena.concat(sangria.getValor() + ". ");
        }

        if (flebotomia != null && !flebotomia.getValor().isEmpty()) {
            cadena = cadena.concat(flebotomia.getValor() + ". ");
        }
        if (observaciones != null && !observaciones.getValor().isEmpty()) {
            cadena = cadena.concat(observaciones.getValor() + ". ");
        }

        return cadena;
    }

}
