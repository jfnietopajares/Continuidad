package es.sacyl.hnss.entity;

public class RegistroPartoBuenasPracticas extends Registro {

    private Variable protocoloAcogida;
    private Variable planParto;
    private Variable acompanamiento;
    private Variable moviDilatacion;
    private Variable ingestaLiq;
    private Variable eleccPostura;
    private Variable donaCordon;
    private Variable buenasRN;

    public final Variable VAR_PARTO_BP_PROTOCOLOACOGIDA = new Variable("13994664", "99G2", new Long(13994664),
            "Protocolo Acogida");
    public final Variable VAR_PARTO_BP_PLANPLARTO = new Variable("13994665", "99G2", new Long(13994665), "Plan Parto");
    public final Variable VAR_PARTO_BP_ELECCIONA = new Variable("13994666", "99G2", new Long(13994666),
            "Elección acompañamiento");
    public final Variable VAR_PARTO_BP_MOVIMIENTODILA = new Variable("13994667", "99G2", new Long(13994667),
            "Movimiento dilatación");
    public final Variable VAR_PARTO_BP_INGESTALIQ = new Variable("251992000", "SNM3", new Long(35000655),
            "Inicio lactancia materna paritorio");
    public final Variable VAR_PARTO_BP_ELECCIONPOST = new Variable("13994668", "99G2", new Long(13994668),
            "Elección postura expulsivo");

    public final Variable VAR_PARTO_BP_DONACIONC = new Variable("N420101.DONCORD", "99G2", new Long(104992),
            "Donación sangre cordón");

    public final Variable VAR_PARTO_BP_BUENASPRACRN = new Variable("13994670", "99G2", new Long(13994670),
            "Buenas Prácticas RN ");

    public final static Long PLANTILLLA_EDITOR_PAR_BUENASPRACTICAS = new Long(794877182);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public RegistroPartoBuenasPracticas() {
        iniciaBuenasPracticas();
    }

    public RegistroPartoBuenasPracticas(Long id) {
        super(id);
        iniciaBuenasPracticas();
    }

    public RegistroPartoBuenasPracticas(RegistroPartoBuenasPracticas r) {
        super(r);
        this.protocoloAcogida = r.getProtocoloAcogida();
        this.planParto = r.getPlanParto();
        this.acompanamiento = r.getAcompanamiento();
        this.moviDilatacion = r.getMoviDilatacion();
        this.ingestaLiq = r.getIngestaLiq();
        this.eleccPostura = r.getIngestaLiq();
        this.donaCordon = r.getDonaCordon();
        this.buenasRN = r.getBuenasRN();
    }

    public void iniciaBuenasPracticas() {
        this.plantilla_editor = PLANTILLLA_EDITOR_PAR_BUENASPRACTICAS;
        this.tiporegistro = TIPO_REGISTRO_PARTO;
        this.descripcion = "9.-Buenas Prácticas";
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));

        this.protocoloAcogida = VAR_PARTO_BP_PROTOCOLOACOGIDA;
        this.planParto = VAR_PARTO_BP_PLANPLARTO;
        this.acompanamiento = VAR_PARTO_BP_ELECCIONA;
        this.moviDilatacion = VAR_PARTO_BP_MOVIMIENTODILA;
        this.ingestaLiq = VAR_PARTO_BP_INGESTALIQ;
        this.eleccPostura = VAR_PARTO_BP_ELECCIONA;
        this.donaCordon = VAR_PARTO_BP_DONACIONC;
        this.buenasRN = VAR_PARTO_BP_BUENASPRACRN;

    }

    public Variable getProtocoloAcogida() {
        return protocoloAcogida;
    }

    public Variable getVariableProtocoloAcogida() {
        return protocoloAcogida;
    }

    public String getProtocoloAcogidaString() {
        return protocoloAcogida.getValor();
    }

    public boolean getProtocoloAcogidaBoolean() {
        if (protocoloAcogida != null && protocoloAcogida.getValor() != null && !protocoloAcogida.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setProtocoloAcogida(Variable protocoloAcogida) {
        this.protocoloAcogida = protocoloAcogida;
    }

    public void setProtocoloAcogida(boolean valor) {
        if (valor == true) {
            protocoloAcogida.setValor("Protocolo acogida");
        } else {
            protocoloAcogida.setValor("");
        }
    }

    public Variable getPlanParto() {
        return planParto;
    }

    public Variable getVariablePlanParto() {
        return planParto;
    }

    public String getPlanPartoString() {
        return planParto.getValor();
    }

    public Boolean getPlanPartoBoolean() {
        if (planParto != null && planParto.getValor() != null && !planParto.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPlanParto(Variable planParto) {
        this.planParto = planParto;
    }

    public void setPlanParto(Boolean valor) {
        if (valor == true) {
            planParto.setValor("Plan de parto");
        } else {
            planParto.setValor("");
        }
    }

    public Variable getAcompanamiento() {
        return acompanamiento;
    }

    public Variable getVariableAcompanamiento() {
        return acompanamiento;
    }

    public String getAcompanamientoString() {
        return acompanamiento.getValor();
    }

    public Boolean getAcompanamientoBoolean() {
        if (acompanamiento != null && acompanamiento.getValor() != null && !acompanamiento.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAcompanamiento(Variable acompanamiento) {
        this.acompanamiento = acompanamiento;
    }

    public void setAcompanamiento(Boolean valor) {
        this.acompanamiento.setValor("Elección de acompañamiento y asesoramiento");
    }

    public Variable getMoviDilatacion() {
        return moviDilatacion;
    }

    public Variable getVariableMoviDilatacion() {
        return moviDilatacion;
    }

    public String getMoviDilatacionString() {
        return moviDilatacion.getValor();
    }

    public Boolean getMoviDilatacionBoolean() {
        if (moviDilatacion != null && moviDilatacion.getValor() != null && !moviDilatacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMoviDilatacion(Variable moviDilatacion) {
        this.moviDilatacion = moviDilatacion;
    }

    public void setMoviDilatacion(Boolean valor) {
        if (valor == true) {
            moviDilatacion.setValor("Movimiento durante la dilatación ");
        } else {
            moviDilatacion.setValor("");
        }
    }

    public Variable getIngestaLiq() {
        return ingestaLiq;
    }

    public Variable getVariableIngestaLiq() {
        return ingestaLiq;
    }

    public String getIngestaLiqString() {
        return ingestaLiq.getValor();
    }

    public Boolean getIngestaLiqBoolean() {
        if (ingestaLiq != null && ingestaLiq.getValor() != null && !ingestaLiq.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIngestaLiq(Variable ingestaLiq) {
        this.ingestaLiq = ingestaLiq;
    }

    public void setIngestaLiq(Boolean valor) {
        if (valor == true) {
            ingestaLiq.setValor("Inicio lactancia materna paritorio");
        } else {
            ingestaLiq.setValor("");
        }
    }

    public Variable getEleccPostura() {
        return eleccPostura;
    }

    public Variable getVariableEleccPostura() {
        return eleccPostura;
    }

    public String getEleccPosturaString() {
        return eleccPostura.getValor();
    }

    public Boolean getEleccPosturaBoolean() {
        if (eleccPostura != null && eleccPostura.getValor() != null && !eleccPostura.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEleccPostura(Variable eleccPostura) {
        this.eleccPostura = eleccPostura;
    }

    public void setEleccPostura(Boolean valor) {
        if (valor == true) {
            eleccPostura.setValor("Elección postura expulsivo");
        } else {
            eleccPostura.setValor("");
        }
    }

    public Variable getDonaCordon() {
        return donaCordon;
    }

    public Variable getVariableDonaCordon() {
        return donaCordon;
    }

    public String getDonaCordonString() {
        return donaCordon.getValor();
    }

    public Boolean getDonaCordonBoolean() {
        if (donaCordon != null && donaCordon.getValor() != null && !donaCordon.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setDonaCordon(Variable donaCordon) {
        this.donaCordon = donaCordon;
    }

    public void setDonaCordon(String valor) {
        this.donaCordon.setValor(valor);
    }

    public void setDonaCordon(Boolean valor) {
        if (valor == true) {
            this.donaCordon.setValor("Donación sangre cordón");
        } else {
            this.donaCordon.setValor("");
        }
    }

    public Variable getBuenasRN() {
        return buenasRN;
    }

    public Variable getVariableBuenasRN() {
        return buenasRN;
    }

    public String getBuenasRNString() {
        return buenasRN.getValor();
    }

    public Boolean getBuenasRNBoolean() {
        if (buenasRN != null && buenasRN.getValor() != null && !buenasRN.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setBuenasRN(Variable buenasRN) {
        this.buenasRN = buenasRN;
    }

    public void setBuenasRN(String valor) {
        this.buenasRN.setValor(valor);
    }

    public void setBuenasRN(Boolean valor) {
        if (valor == true) {
            this.buenasRN.setValor("Buenas prácticas RN: piel con piel/lactancia materna");
        } else {
            this.buenasRN.setValor("");
        }

    }

}
