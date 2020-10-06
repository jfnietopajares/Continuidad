package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.ConstantesClinicas;
import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RegistroEnfConstantes extends Registro {

    private static final Logger logger = LogManager.getLogger(RegistroEnfConstantes.class);

    private Variable peso;
    private Variable talla;
    private Variable imc;
    private Variable tas;
    private Variable tad;
    private Variable temperatura;
    private Variable freCardiaca;
    private Variable satOxigeno;
    private Variable freRespiratorio;
    private Variable eva;
    private Variable glucemia;
    private Variable observaciones;

    public final static Long PLANTILLLA_EDITOR_ENF_CTES = new Long(337290380);
    public final static Long TIPO_REGISTRO_ENF_CTES = new Long(6);

    public final Variable VAR_ENF_CTES_PESO = new ConstantesClinicas().VAR_CTES_PESO;

    public final Variable VAR_ENF_CTES_TALLA = new ConstantesClinicas().VAR_CTES_TALLA;

    public final Variable VAR_ENF_CTES_IMC = new ConstantesClinicas().VAR_CTES_IMC;

    public final Variable VAR_ENF_CTES_TAS = new ConstantesClinicas().VAR_CTES_TAS;

    public final Variable VAR_ENF_CTES_TAD = new ConstantesClinicas().VAR_CTES_TAD;

    public final Variable VAR_ENF_CTES_T = new ConstantesClinicas().VAR_CTES_T;

    public final Variable VAR_ENF_CTES_FC = new ConstantesClinicas().VAR_CTES_FC;

    public final Variable VAR_ENF_CTES_SATO2 = new ConstantesClinicas().VAR_CTES_SATO2;

    public final Variable VAR_ENF_CTES_FR = new ConstantesClinicas().VAR_CTES_FR;

    public final Variable VAR_ENF_CTES_EVA = new ConstantesClinicas().VAR_CTES_EVA;

    public final Variable VAR_ENF_CTES_GLUCEMIA = new ConstantesClinicas().VAR_CTES_GLU;

    public final Variable VAR_ENF_CTES_OBSER = new ConstantesClinicas().VAR_OBSERVACIONES;

    public RegistroEnfConstantes() {
        super();
        this.iniciaEnfCtesn();
    }

    public RegistroEnfConstantes(Long id) {
        super(id);
        this.iniciaEnfCtesn();
    }

    public RegistroEnfConstantes(RegistroEnfConstantes rc) {
        super(rc);
        this.peso = rc.getVariablePeso();
        this.talla = rc.getVariableTalla();
        this.imc = rc.getVariableImc();
        this.tas = rc.getVariableTas();
        this.tad = rc.getVariableTad();
        this.temperatura = rc.getVariableTemperatura();
        this.freCardiaca = rc.getVariableFreCardiaca();
        this.satOxigeno = rc.getVariableSatOxigeno();
        this.freRespiratorio = rc.getVariableFreRespiratorio();
        this.eva = rc.getVariableEva();
        this.glucemia = rc.getGlucemia();
        this.observaciones = rc.getObservaciones();

    }

    public void iniciaEnfCtesn() {
        this.setTiporegistro(RegistroEnfConstantes.TIPO_REGISTRO_ENF_CTES);
        this.setDescripcion("Registro de constantes ");
        this.setPlantilla_edior(RegistroEnfConstantes.PLANTILLLA_EDITOR_ENF_CTES);
        this.peso = VAR_ENF_CTES_PESO;
        this.talla = VAR_ENF_CTES_TALLA;
        this.imc = VAR_ENF_CTES_IMC;
        this.tas = VAR_ENF_CTES_TAS;
        this.tad = VAR_ENF_CTES_TAD;
        this.temperatura = VAR_ENF_CTES_T;
        this.freCardiaca = VAR_ENF_CTES_FC;
        this.satOxigeno = VAR_ENF_CTES_SATO2;
        this.freRespiratorio = VAR_ENF_CTES_FR;
        this.eva = VAR_ENF_CTES_EVA;
        this.glucemia = VAR_ENF_CTES_GLUCEMIA;
        this.observaciones = VAR_ENF_CTES_OBSER;
    }

    public Variable getPeso() {
        return peso;
    }

    public Variable getVariablePeso() {
        return peso;
    }

    public String getPesoString() {
        return peso.getValor();
    }

    public Double getPesoDouble() {
        Double numeroDouble = null;
        try {
            // numeroDouble = new Double(peso.getValor());
            numeroDouble = Double.parseDouble(peso.getValor());
        } catch (Exception e) {
            logger.error("Conversion a decimal" + peso.getValor());
        }
        return numeroDouble;
    }

    public BigDecimal getPesoBigDecimal() {
        BigDecimal numeroDouble = null;
        try {
            numeroDouble = new BigDecimal(peso.getValor());
        } catch (Exception e) {
            logger.error("Conversion a decimal" + peso.getValor());
        }
        return numeroDouble;
    }

    public void setPeso(Variable peso) {
        this.peso = peso;
    }

    public void setPeso(BigDecimal pesob) {
        this.peso.setValor(pesob.toString());
    }

    public void setPeso(String valor) {
        this.peso.setValor(valor);
    }

    public void setPeso(Double valor) {
        this.peso.setValor(valor.toString());
    }

    public Variable getVariableTalla() {
        return talla;
    }

    public Variable getTalla() {
        return talla;
    }

    public String getTallaSrting() {
        return talla.getValor();
    }

    public Double getTallaDouble() {
        Double numeroDouble = null;
        try {
            numeroDouble = new Double(talla.getValor());
        } catch (Exception e) {
            logger.error("Conversion a decimal" + talla.getValor());
        }

        return numeroDouble;

    }

    public void setTalla(Variable talla) {
        this.talla = talla;
    }

    public void setTalla(String valor) {
        this.talla.setValor(valor);
    }

    public void setTalla(Double valor) {
        this.talla.setValor(valor.toString());
    }

    public String pestoTallaString() {
        return getPesoString() + "/" + getTallaSrting();
    }

    public String getImcString() {
        return imc.getValor();
    }

    public Variable getVariableImc() {
        return imc;
    }

    public Variable getImc() {
        return imc;
    }

    public void setImc(Variable imc) {
        this.imc = imc;
    }

    public void setImc(String valor) {
        this.imc.setValor(valor);
        ;
    }

    public Variable getVariableTas() {
        return tas;
    }

    public Variable getTas() {
        return tas;
    }

    public String getTasString() {
        return tas.getValor();
    }

    public void setTas(Variable tas) {
        this.tas = tas;
    }

    public void setTas(String valor) {
        this.tas.setValor(valor);
    }

    public Variable getVariableTad() {
        return tad;
    }

    public Variable getTad() {
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
        ;
    }

    public String getTasTad() {
        String texto = "";
        if (tas != null && !tas.getValor().isEmpty()) {
            texto = tas.getValor();
        }
        texto = texto.concat("/");
        if (tad != null && !tad.getValor().isEmpty()) {
            texto = texto.concat(tad.getValor());
        }
        return texto;
    }

    public Variable getVariableFreCardiaca() {
        return freCardiaca;
    }

    public Variable getFreCardiaca() {
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

    public String getFreCardiacaFreRespiraString() {
        return getFreCardiacaString() + "/" + getFreRespiratorioString();
    }

    public String getSatOxigenoString() {
        return satOxigeno.getValor();
    }

    public Variable getVariableSatOxigeno() {
        return satOxigeno;
    }

    public Variable getSatOxigeno() {
        return satOxigeno;
    }

    public void setSatOxigeno(Variable satOxigeno) {
        this.satOxigeno = satOxigeno;
    }

    public void setSatOxigeno(String valor) {
        this.satOxigeno.setValor(valor);
    }

    public String getFreRespiratorioString() {
        return freRespiratorio.getValor();
    }

    public Variable getVariableFreRespiratorio() {
        return freRespiratorio;
    }

    public Variable getFreRespiratorio() {
        return freRespiratorio;
    }

    public void setFreRespiratorio(Variable freRespiratorio) {
        this.freRespiratorio = freRespiratorio;
    }

    public void setFreRespiratorio(String valor) {
        this.freRespiratorio.setValor(valor);
    }

    public String getEvaString() {
        return eva.getValor();
    }

    public Variable getVariableEva() {
        return eva;
    }

    public Variable getEva() {
        return eva;
    }

    public void setEva(Variable eva) {
        this.eva = eva;
    }

    public void setEva(String valor) {
        this.eva.setValor(valor);
    }

    public String getTemperaturaString() {
        return temperatura.getValor();
    }

    public String getSatO2EvaString() {
        return getSatOxigenoString() + "/" + getEvaString();
    }

    public Double getTemperaturaDouble() {
        Double numeroDouble = null;
        try {
            numeroDouble = new Double(temperatura.getValor());
        } catch (Exception e) {
            logger.error("Conversion a decimal" + temperatura.getValor());
        }

        return numeroDouble;

    }

    public Variable getVariableTemperatura() {
        return temperatura;
    }

    public Variable getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Variable temperatura) {
        this.temperatura = temperatura;
    }

    public void setTemperatura(String valor) {
        this.temperatura.setValor(valor);
    }

    public void setTemperatura(Double valor) {
        this.temperatura.setValor(valor.toString());
    }

    public Variable getGlucemia() {
        return glucemia;
    }

    public Variable getVariableGlucemia() {
        return glucemia;
    }

    public String getGlucemiaString() {
        return glucemia.getValor();
    }

    public void setGlucemia(Variable glucemia) {
        this.glucemia = glucemia;
    }

    public void setGlucemia(String valor) {
        this.glucemia.setValor(valor);
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
        ;
    }

    @Override
    public String toString() {
        return "RegistroEnfConstantes [peso=" + peso + ", talla=" + talla + ", imc=" + imc + ", tas=" + tas + ", tad="
                + tad + ", temperatura=" + temperatura + ", freCardiaca=" + freCardiaca + ", satOxigeno=" + satOxigeno
                + ", freRespiratorio=" + freRespiratorio + ", eva=" + eva + ", observaciones=" + observaciones + "]";
    }

    public String getContenidoHtmlFilaCabecera() {
        String texto = "<tr>";
        texto = texto.concat("<td>Fecha</td>");
        texto = texto.concat("<td>Peso</td>");
        texto = texto.concat("<td>Talla</td>");
        texto = texto.concat("<td>TAD</td>");
        texto = texto.concat("<td>TAS</td>");
        texto = texto.concat("<td>Tª</td>");
        texto = texto.concat("<td>FC</td>");
        texto = texto.concat("<td>FR</td>");
        texto = texto.concat("<td>SatO2</td>");
        texto = texto.concat("<td>EVA</td>");
        texto = texto.concat("</tr>");
        return texto;
    }

    public String getContenidoHtmlFila() {
        String texto = "<tr>";
        texto = texto.concat("<td>" + this.getFechaHora() + "</td>");
        texto = texto.concat("<td>" + this.getPeso().getValor() + "</td>");
        texto = texto.concat("<td>" + this.getTalla().getValor() + "</td>");
        texto = texto.concat("<td>" + this.getTad().getValor() + "</td>");
        texto = texto.concat("<td>" + this.getTas().getValor() + "</td>");
        texto = texto.concat("<td>" + this.getTemperatura().getValor() + "</td>");
        texto = texto.concat("<td>" + this.getFreCardiaca().getValor() + "</td>");
        texto = texto.concat("<td>" + this.getFreRespiratorio().getValor() + "</td>");
        texto = texto.concat("<td>" + this.getSatOxigeno().getValor() + "</td>");
        texto = texto.concat("<td>" + this.getEva().getValor() + "</td>");
        texto = texto.concat("</tr>");
        return texto;
    }
}
