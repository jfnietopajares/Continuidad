/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Constantes;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class RegistroEnfIntervenciones extends Registro {

    private static final Logger logger = LogManager.getLogger(RegistroEnfIntervenciones.class);

    private Variable manejoHipercalcemia;
    public final Variable VAR_ENF_INTERV_HIPERCALCEMIA = new Variable("Hipercalcemia", "13995508", "99G2", new Long(13995508), "Manejo de electrólitos: hipercalcemia  ");

    private Variable saludBucal;
    public final Variable VAR_ENF_INTERV_SALUDBUCAL = new Variable("Salud Bucal ", "13995509", "99G2", new Long(13995509), "Restablecimiento de la salud bucal  ");

    private Variable manejoDiarrea;
    public final Variable VAR_ENF_INTERV_DIARREA = new Variable("Diarrea ", "13995510", "99G2", new Long(13995510), "Manejo de la diarrea  ");

    private Variable manejoHipertermia;
    public final Variable VAR_ENF_INTERV_HIPERTERMIA = new Variable("Hipertermia", "13995511", "99G2", new Long(13995511), "Tratamiento de la hipertermia  ");

    private Variable liquElectroliticos;
    public final Variable VAR_ENF_INTERV_LIQUIELECTROLITOS = new Variable("M.Elecrolitos", "13995512", "99G2", new Long(13995512), "Manejo de líquidos/electrólitos");

    private Variable manejoQuimioterapia;
    public final Variable VAR_ENF_INTERV_QUIMIO = new Variable("M.Quimio", "13995513", "99G2", new Long(13995513), "Manejo de la quimioterapia ");

    private Variable manejoHipoglucemia;
    public final Variable VAR_ENF_INTERV_HIPOGLUCEMIA = new Variable("M.Hipoglucemia", "13995514", "99G2", new Long(13995514), "Manejo de la hiperglucemia");

    private Variable manejoNutricion;
    public final Variable VAR_ENF_INTERV_NUTRICIOON = new Variable("M.Nutrición", "13995515", "99G2", new Long(13995515), "Manejo de la nutrición");

    private Variable adminHemoderivados;
    public final Variable VAR_ENF_INTERV_HEMODERIVADOS = new Variable("A.Hemoderivados", "13995516", "99G2", new Long(13995516), "Administración de hemoderivados");

    private Variable puncionVenosa;
    public final Variable VAR_ENF_INTERV_PUNCIONVENOSA = new Variable("Punción Venosa", "13995518", "99G2", new Long(13995518), "Punción intravenosa (Inserción de una aguja hueca en una vena ");

    private Variable admMediOral;
    public final Variable VAR_ENF_INTERV_ADMMEDIORAL = new Variable("Adm Medi Oral", "13995519", "99G2", new Long(13995519), "Administración de medicación: oral");
//
    private Variable admMediSubcuta;
    public final Variable VAR_ENF_INTERV_ADMMEDISUBCUTA = new Variable("Adm Medi Subcutánea", "13995520", "99G2", new Long(13995520), "Administración de medicación: subcutánea");

    private Variable admMediIntrav;
    public final Variable VAR_ENF_INTERV_ADMMEDIINTRAVENOSA = new Variable("Adm Medi Intravenosa", "13995521", "99G2", new Long(13995521), "Administración de medicación: intravenosa");

    private Variable manejoDisCentral;
    public final Variable VAR_ENF_INTERV_DISPOCENTRAL = new Variable("M.DispoCentral", "13995522", "99G2", new Long(13995522), "Manejo de un dispositivo de acceso venoso central  ");

    private Variable insercionPICC; // YA TIENE CODIGO CATALOGO
    public final Variable VAR_ENF_INTERV_PICC = new Variable("Inserción PICC", "13993736", "99G2", new Long(13993736), "Cuidados (inserci del catéter central de inserción periférica(PICC)");

    private Variable feblotomiaVia;
    public final Variable VAR_ENF_INTERV_FLEBOTOMIAVIA = new Variable("Feblotomía vía", "13995523", "99G2", new Long(13995523), "Flebotomía: vía canalizada");

    private Variable feblotomiaMuestra;
    public final Variable VAR_ENF_INTERV_FLEBOTOMIAMUESTRA = new Variable("Flebotomía: sangre ", "13995524", "99G2", new Long(13995524), "Flebotomía: muestra de sangre");

    private Variable cuidadoIncision;
    public final Variable VAR_ENF_INTERV_CUIDADOINCISION = new Variable("Cuidado Incisión", "13995525", "99G2", new Long(13995525), "Cuidados del sitio de incisión (heridas quirúrgicas, puntos incisión drenaje)");

    private Variable cuidadoUlcerasP;
    public final Variable VAR_ENF_INTERV_CUIDADOULCERASP = new Variable("Cuid.Úlceras P", "13995526", "99G2", new Long(13995526), "Cuidados de las úlceras por presión");

    private Variable cuidadoHeridas;
    public final Variable VAR_ENF_INTERV_CUIDADOHERIDAS = new Variable("Cuidado heridas", "13995527", "99G2", new Long(13995527), "Cuidado de las heridas (traumáticas, quemaduras)");

    private Variable apoyoCuidador;
    public final Variable VAR_ENF_INTERV_APOYOCUIDADOR = new Variable("Apoyo cuidador ", "13995528", "99G2", new Long(13995528), "Apoyo al cuidador principal");

    private Variable manejoDolor;
    public final Variable VAR_ENF_INTERV_MANEJODOLOR = new Variable("Manejo del dolor ", "13995548", "99G2", new Long(13995548), "Manejo del dolor");

    private Variable medicaIntramuscular;
    public final Variable VAR_ENF_INTERV_ADMMEDICAINTRAMUSCULAR = new Variable("Medicación intramuscular ", "13995521", "99G2", new Long(13995521), " Administración de medicación: intramuscular (i.m.) ");

    public final static Long PLANTILLLA_EDITOR_INTERV = new Long(903263075);
    public final static Long TIPO_REGISTRO_INTERV = new Long(6);

    public RegistroEnfIntervenciones() {
        super();
        doIniciaIntervenciones();
    }

    public RegistroEnfIntervenciones(Long id) {
        super(id);
        doIniciaIntervenciones();
    }

    public RegistroEnfIntervenciones(RegistroEnfIntervenciones r) {
        super();
        this.manejoHipercalcemia = r.getManejoHipercalcemia();
        this.saludBucal = r.getSaludBucal();
        this.manejoDiarrea = r.getManejoDiarrea();
        this.manejoHipertermia = r.getManejoHipertermia();
        this.liquElectroliticos = r.getLiquElectroliticos();
        this.manejoQuimioterapia = r.getManejoQuimioterapia();
        this.manejoHipoglucemia = r.getManejoHipoglucemia();
        this.manejoNutricion = r.getManejoNutricion();
        this.adminHemoderivados = r.getAdminHemoderivados();
        this.puncionVenosa = r.getPuncionVenosa();
        this.admMediOral = r.getAdmMediOral();
        this.admMediSubcuta = r.getAdmMediSubcuta();
        this.admMediIntrav = r.getAdmMediIntrav();
        this.manejoDisCentral = r.getManejoDisCentral();
        this.insercionPICC = r.getInsercionPICC();
        this.feblotomiaVia = r.getFeblotomiaVia();
        this.feblotomiaMuestra = r.getFeblotomiaMuestra();
        this.cuidadoIncision = r.getCuidadoIncision();
        this.cuidadoUlcerasP = r.getCuidadoUlcerasP();
        this.cuidadoHeridas = r.getCuidadoHeridas();
        this.apoyoCuidador = r.getApoyoCuidador();
        this.manejoDolor = r.getManejoDolor();
        this.medicaIntramuscular = r.getMedicaIntramuscular();
    }

    public void doIniciaIntervenciones() {
        this.setTiporegistro(TIPO_REGISTRO_INTERV);
        this.setDescripcion("Intervenciones de enfermería.");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_INTERV);

        this.manejoHipercalcemia = VAR_ENF_INTERV_HIPERCALCEMIA;
        this.saludBucal = VAR_ENF_INTERV_SALUDBUCAL;
        this.manejoDiarrea = VAR_ENF_INTERV_DIARREA;
        this.manejoHipertermia = VAR_ENF_INTERV_HIPERTERMIA;
        this.liquElectroliticos = VAR_ENF_INTERV_LIQUIELECTROLITOS;
        this.manejoQuimioterapia = VAR_ENF_INTERV_QUIMIO;
        this.manejoHipoglucemia = VAR_ENF_INTERV_HIPOGLUCEMIA;
        this.manejoNutricion = VAR_ENF_INTERV_NUTRICIOON;
        this.adminHemoderivados = VAR_ENF_INTERV_HEMODERIVADOS;
        this.puncionVenosa = VAR_ENF_INTERV_PUNCIONVENOSA;
        this.admMediOral = VAR_ENF_INTERV_ADMMEDIORAL;
        this.admMediSubcuta = VAR_ENF_INTERV_ADMMEDISUBCUTA;
        this.admMediIntrav = VAR_ENF_INTERV_ADMMEDIINTRAVENOSA;
        this.manejoDisCentral = VAR_ENF_INTERV_DISPOCENTRAL;
        this.insercionPICC = VAR_ENF_INTERV_PICC;
        this.feblotomiaVia = VAR_ENF_INTERV_FLEBOTOMIAVIA;
        this.feblotomiaMuestra = VAR_ENF_INTERV_FLEBOTOMIAMUESTRA;
        this.cuidadoIncision = VAR_ENF_INTERV_CUIDADOINCISION;
        this.cuidadoUlcerasP = VAR_ENF_INTERV_CUIDADOULCERASP;
        this.cuidadoHeridas = VAR_ENF_INTERV_CUIDADOHERIDAS;
        this.apoyoCuidador = VAR_ENF_INTERV_APOYOCUIDADOR;
        this.manejoDolor = VAR_ENF_INTERV_MANEJODOLOR;
        this.medicaIntramuscular = VAR_ENF_INTERV_ADMMEDICAINTRAMUSCULAR;

    }

    public Variable getManejoHipercalcemia() {
        return manejoHipercalcemia;
    }

    public Variable getVariableManejoHipercalcemia() {
        return manejoHipercalcemia;
    }

    public Boolean getManejoHipercalcemiaBoolean() {
        if (manejoHipercalcemia != null && !manejoHipercalcemia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setManejoHipercalcemia(Variable manejoHipercalcemia) {
        this.manejoHipercalcemia = manejoHipercalcemia;
    }

    public void setManejoHipercalcemia(Boolean valor) {
        if (valor == true) {
            this.manejoHipercalcemia.setValor("Manejo de electrólitos: hipercalcemia");
        } else {
            this.manejoHipercalcemia.setValor("");
        }

    }

    public Variable getSaludBucal() {
        return saludBucal;
    }

    public Variable getVariableSaludBucal() {
        return saludBucal;
    }

    public Boolean getSaludBucalBoolean() {
        if (saludBucal != null && !saludBucal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSaludBucal(Variable saludBucal) {
        this.saludBucal = saludBucal;
    }

    public void setSaludBucal(Boolean valor) {
        if (valor == true) {
            this.saludBucal.setValor("Restablecimiento de la salud bucal");
        } else {
            this.saludBucal.setValor("");
        }
    }

    public Variable getManejoDiarrea() {
        return manejoDiarrea;
    }

    public Variable getVariableManejoDiarrea() {
        return manejoDiarrea;
    }

    public Boolean getManejoDiarreaBoolean() {
        if (manejoDiarrea != null && !manejoDiarrea.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setManejoDiarrea(Variable manejoDiarrea) {
        this.manejoDiarrea = manejoDiarrea;
    }

    public void setManejoDiarrea(Boolean valor) {
        if (valor == true) {
            this.manejoDiarrea.setValor("Manejo de la diarrea");
        } else {
            this.manejoDiarrea.setValor("");
        }
    }

    public Variable getManejoHipertermia() {
        return manejoHipertermia;
    }

    public Variable getVariableManejoHipertermia() {
        return manejoHipertermia;
    }

    public Boolean getManejoHipertermiaBoolean() {
        if (manejoHipertermia != null && !manejoHipertermia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setManejoHipertermia(Variable manejoHipertermia) {
        this.manejoHipertermia = manejoHipertermia;
    }

    public void setManejoHipertermia(Boolean valor) {
        if (valor == true) {
            this.manejoHipertermia.setValor("Tratamiento de la hipertermia");
        } else {
            this.manejoHipertermia.setValor("");
        }

    }

    public Variable getLiquElectroliticos() {
        return liquElectroliticos;
    }

    public Variable getVariableLiquElectroliticos() {
        return liquElectroliticos;
    }

    public Boolean getLiquElectroliticosBoolean() {
        if (liquElectroliticos != null && !liquElectroliticos.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setLiquElectroliticos(Variable liquElectroliticos) {
        this.liquElectroliticos = liquElectroliticos;
    }

    public void setLiquElectroliticos(Boolean valor) {
        if (valor == true) {
            this.liquElectroliticos.setValor("Manejo de líquidos/electrólitos");
        } else {
            this.liquElectroliticos.setValor("");
        }
    }

    public Variable getManejoQuimioterapia() {
        return manejoQuimioterapia;
    }

    public Variable getVariableManejoQuimioterapia() {
        return manejoQuimioterapia;
    }

    public Boolean getManejoQuimioterapiaBoolean() {
        if (manejoQuimioterapia != null && !manejoQuimioterapia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setManejoQuimioterapia(Variable manejoQuimioterapia) {
        this.manejoQuimioterapia = manejoQuimioterapia;
    }

    public void setManejoQuimioterapia(Boolean valor) {
        if (valor == true) {
            this.manejoQuimioterapia.setValor("Manejo de la quimioterapia");
        } else {
            this.manejoQuimioterapia.setValor("");
        }

    }

    public Variable getManejoHipoglucemia() {
        return manejoHipoglucemia;
    }

    public Variable getVariableManejoHipoglucemia() {
        return manejoHipoglucemia;
    }

    public Boolean getManejoHipoglucemiaBoolean() {
        if (manejoHipoglucemia != null && !manejoHipoglucemia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setManejoHipoglucemia(Variable manejoHipoglucemia) {
        this.manejoHipoglucemia = manejoHipoglucemia;
    }

    public void setManejoHipoglucemia(Boolean valor) {
        if (valor == true) {
            this.manejoHipoglucemia.setValor("Manejo de la hiperglucemia");
        } else {
            this.manejoHipoglucemia.setValor("");
        }
    }

    public Variable getManejoNutricion() {
        return manejoNutricion;
    }

    public Variable getVariableManejoNutricion() {
        return manejoNutricion;
    }

    public Boolean getManejoNutricionBoolean() {
        if (manejoNutricion != null && !manejoNutricion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setManejoNutricion(Variable manejoNutricion) {
        this.manejoNutricion = manejoNutricion;
    }

    public void setManejoNutricion(Boolean valor) {
        if (valor == true) {
            this.manejoNutricion.setValor("Manejo de la nutrición");
        } else {
            this.manejoNutricion.setValor("");
        }
    }

    public Variable getAdminHemoderivados() {
        return adminHemoderivados;
    }

    public Variable getVariableAdminHemoderivados() {
        return adminHemoderivados;
    }

    public Boolean getAdminHemoderivadosBoolean() {
        if (adminHemoderivados != null && !adminHemoderivados.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAdminHemoderivados(Variable adminHemoderivados) {
        this.adminHemoderivados = adminHemoderivados;
    }

    public void setAdminHemoderivados(Boolean valor) {
        if (valor == true) {
            this.adminHemoderivados.setValor("Administración de hemoderivados.");
        } else {
            this.adminHemoderivados.setValor("");
        }
    }

    public Variable getPuncionVenosa() {
        return puncionVenosa;
    }

    public Variable getVariablePuncionVenosa() {
        return puncionVenosa;
    }

    public Boolean getPuncionVenosaBoolean() {
        if (puncionVenosa != null && !puncionVenosa.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setPuncionVenosa(Variable puncionVenosa) {
        this.puncionVenosa = puncionVenosa;
    }

    public void setPuncionVenosa(Boolean valor) {
        if (valor == true) {
            this.puncionVenosa.setValor("Punción intravenosa.");
        } else {
            this.puncionVenosa.setValor("");
        }

    }

    public Variable getAdmMediOral() {
        return admMediOral;
    }

    public Variable getVariableAdmMediOral() {
        return admMediOral;
    }

    public Boolean getAdmMediOralBoolean() {
        if (admMediOral != null && !admMediOral.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAdmMediOral(Variable admMediOral) {
        this.admMediOral = admMediOral;
    }

    public void setAdmMediOral(Boolean valor) {
        if (valor == true) {
            this.admMediOral.setValor("Administración de medicación: oral");
        } else {
            this.admMediOral.setValor("");
        }
    }

    public Variable getAdmMediSubcuta() {
        return admMediSubcuta;
    }

    public Variable getVariableAdmMediSubcuta() {
        return admMediSubcuta;
    }

    public Boolean getAdmMediSubcutaBoolean() {
        if (admMediSubcuta != null && !admMediSubcuta.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAdmMediSubcuta(Variable admMediSubcuta) {
        this.admMediSubcuta = admMediSubcuta;
    }

    public void setAdmMediSubcuta(Boolean valor) {
        if (valor == true) {
            this.admMediSubcuta.setValor("Administración de medicación: subcutánea");
        } else {
            this.admMediSubcuta.setValor("");
        }
    }

    public Variable getAdmMediIntrav() {
        return admMediIntrav;
    }

    public Variable getVariableAdmMediIntrav() {
        return admMediIntrav;
    }

    public Boolean getAdmMediIntravBoolean() {
        if (admMediIntrav != null && !admMediIntrav.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAdmMediIntrav(Variable admMediIntrav) {
        this.admMediIntrav = admMediIntrav;
    }

    public void setAdmMediIntrav(Boolean valor) {
        if (valor == true) {
            this.admMediIntrav.setValor("Administración de medicación: intravenosa.");
        } else {
            this.admMediIntrav.setValor("");
        }
    }

    public Variable getManejoDisCentral() {
        return manejoDisCentral;
    }

    public Variable getVariableManejoDisCentral() {
        return manejoDisCentral;
    }

    public Boolean getManejoDisCentralBooelan() {
        if (manejoDisCentral != null && !manejoDisCentral.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setManejoDisCentral(Variable manejoDisCentral) {
        this.manejoDisCentral = manejoDisCentral;
    }

    public void setManejoDisCentral(boolean valor) {
        if (valor == true) {
            this.manejoDisCentral.setValor("Manejo de un dispositivo de acceso venoso central	");
        } else {
            this.manejoDisCentral.setValor("");
        }
    }

    public Variable getInsercionPICC() {
        return insercionPICC;
    }

    public Variable getVariableInsercionPICC() {
        return insercionPICC;
    }

    public Boolean getInsercionPICCBoolean() {
        if (insercionPICC != null && !insercionPICC.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInsercionPICC(Variable insercionPICC) {
        this.insercionPICC = insercionPICC;
    }

    public void setInsercionPICC(Boolean valor) {
        if (valor == true) {
            this.insercionPICC.setValor(" Cuidados (inserci del catéter central de inserción periférica(PICC)");
        } else {
            this.insercionPICC.setValor("");
        }
    }

    public Variable getFeblotomiaVia() {
        return feblotomiaVia;
    }

    public Variable getVariableFeblotomiaVia() {
        return feblotomiaVia;
    }

    public Boolean getFeblotomiaViaBoolean() {
        if (feblotomiaVia != null && !feblotomiaVia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFeblotomiaVia(Variable feblotomiaVia) {
        this.feblotomiaVia = feblotomiaVia;
    }

    public void setFeblotomiaVia(Boolean valor) {
        if (valor == true) {
            this.feblotomiaVia.setValor("Flebotomía: vía canalizada");
        } else {
            this.feblotomiaVia.setValor("");
        }
    }

    public Variable getFeblotomiaMuestra() {
        return feblotomiaMuestra;
    }

    public Variable getVariableFeblotomiaMuestra() {
        return feblotomiaMuestra;
    }

    public Boolean getFeblotomiaMuestraBooelan() {
        if (feblotomiaMuestra != null && !feblotomiaMuestra.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFeblotomiaMuestra(Variable feblotomiaMuestra) {
        this.feblotomiaMuestra = feblotomiaMuestra;
    }

    public void setFeblotomiaMuestra(Boolean valor) {
        if (valor == true) {
            this.feblotomiaMuestra.setValor("Flebotomía: muestra de sangre ");
        } else {
            this.feblotomiaMuestra.setValor("");
        }
    }

    public Variable getCuidadoIncision() {
        return cuidadoIncision;
    }

    public Variable getVariableCuidadoIncision() {
        return cuidadoIncision;
    }

    public Boolean getCuidadoIncisionBoolean() {
        if (cuidadoIncision != null && !cuidadoIncision.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCuidadoIncision(Variable cuidadoIncision) {
        this.cuidadoIncision = cuidadoIncision;
    }

    public void setCuidadoIncision(Boolean valor) {
        if (valor == true) {
            this.cuidadoIncision.setValor("Cuidados del sitio de incision (heridas quirúrgicas, puntos incision drenaje)");
        } else {
            this.cuidadoIncision.setValor("");
        }
    }

    public Variable getCuidadoUlcerasP() {
        return cuidadoUlcerasP;
    }

    public Variable getVariableCuidadoUlcerasP() {
        return cuidadoUlcerasP;
    }

    public Boolean getCuidadoUlcerasPBoolean() {
        if (cuidadoUlcerasP != null && !cuidadoUlcerasP.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCuidadoUlcerasP(Variable cuidadoUlcerasP) {
        this.cuidadoUlcerasP = cuidadoUlcerasP;
    }

    public void setCuidadoUlcerasP(Boolean valor) {
        if (valor == true) {
            this.cuidadoUlcerasP.setValor("Cuidados de las úlceras por presión");
        } else {
            this.cuidadoUlcerasP.setValor("");
        }
    }

    public Variable getCuidadoHeridas() {
        return cuidadoHeridas;
    }

    public Variable getVariableCuidadoHeridas() {
        return cuidadoHeridas;
    }

    public Boolean getCuidadoHeridasBoolean() {
        if (cuidadoHeridas != null && !cuidadoHeridas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCuidadoHeridas(Variable cuidadoHeridas) {
        this.cuidadoHeridas = cuidadoHeridas;
    }

    public void setCuidadoHeridas(Boolean valor) {
        if (valor == true) {
            this.cuidadoHeridas.setValor("Cuidado de las heridas (traumáticas, quemaduras)");
        } else {
            this.cuidadoHeridas.setValor("");
        }
    }

    public Variable getApoyoCuidador() {
        return apoyoCuidador;
    }

    public Variable getVariableApoyoCuidador() {
        return apoyoCuidador;
    }

    public Boolean getApoyoCuidadorBoolean() {
        if (apoyoCuidador != null && !apoyoCuidador.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setApoyoCuidador(Variable apoyoCuidador) {
        this.apoyoCuidador = apoyoCuidador;
    }

    public void setApoyoCuidador(Boolean valor) {
        if (valor == true) {
            this.apoyoCuidador.setValor("Apoyo al cuidador principal");
        } else {
            this.apoyoCuidador.setValor("");
        }
    }

    public Variable getManejoDolor() {
        return manejoDolor;
    }

    public Variable getvARIABLEManejoDolor() {
        return manejoDolor;
    }

    public Boolean getManejoDolorBoolean() {
        if (manejoDolor != null && !manejoDolor.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setManejoDolor(Variable manejoDolor) {
        this.manejoDolor = manejoDolor;
    }

    public void setManejoDolor(Boolean valor) {
        if (valor == true) {
            this.manejoDolor.setValor("Manejo dolor");
        } else {
            this.manejoDolor.setValor("");
        }
    }

    public Variable getMedicaIntramuscular() {
        return medicaIntramuscular;
    }

    public Variable getVariableMedicaIntramuscular() {
        return medicaIntramuscular;
    }

    public Boolean getMedicaIntramuscularBoolean() {
        if (medicaIntramuscular != null && !medicaIntramuscular.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMedicaIntramuscular(Variable medicaIntramuscular) {
        this.medicaIntramuscular = medicaIntramuscular;
    }

    public void setMedicaIntramuscular(Boolean valor) {
        if (valor == true) {
            this.medicaIntramuscular.setValor("Administración de medicación: intramuscular (i.m.)");
        } else {
            this.medicaIntramuscular.setValor("");
        }
    }

    public String getTodasLasPruebas(String separador) {
        String contenido = "";

        Method[] metodos = this.getClass().getMethods();
        for (Method m : metodos) {
            if (m.getName().length() > 10) {
                if (m.getName().substring(0, 11).equals("getVariable")) {
                    try {
                        if (m.invoke(this, null) instanceof Variable) {
                            Variable variable = (Variable) m.invoke(this, null);
                            if (variable != null) {
                                if (variable.getValor() != null && !variable.getValor().isEmpty()) {
                                    // contenido = contenido.concat(variable.getDescripcion() + ".");
                                    contenido = contenido.concat(variable.getValor() + separador);
                                    //   contenido = contenido.concat("<hr>");
                                }
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        logger.error(Constantes.EXCEPTION_ERROR, e);
                    } catch (Exception e) {
                        logger.error(Constantes.EXCEPTION_ERROR, e);
                    } finally {
                        //   return contenido;
                    }
                }
            }
        }
        return contenido;
    }
}
