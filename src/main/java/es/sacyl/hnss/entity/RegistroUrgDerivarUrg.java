/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entity;


import es.sacyl.hnss.dao.CentroDAO;
import es.sacyl.hnss.utilidades.ConstantesClinicas;
import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author 06551256M
 */
public class RegistroUrgDerivarUrg extends Registro {

    DateTimeFormatter fechayyymmddhhss = DateTimeFormatter.ofPattern("YYYYMMddHHmm");

    private Variable numero;
    public final Variable URG_DERIVA_NUMERO = new Variable("N420101.NUMORD", "99G2", new Long(104868),
            "Número de orden ", "Número de orden", Variable.TIPO_VARIABLE_DATE);

    private LocalDateTime fechaHoraLlamada;

    private Variable fechahoravar;

    public final Variable URG_DERIVA_FECHA = new Variable("410671006", "SNM3", new Long(13822264),
            "Fecha ", "Fecha", Variable.TIPO_VARIABLE_DATE);

    private Centro centroSal;
    private Variable cs;
    public final Variable URG_DERIVA_CS = new Variable("13824044", "99G2", new Long(13824044),
            "Centro de salud", "Centro de salud", Variable.TIPO_VARIABLE_STRING);

    private Variable medicoPeticionario;
    public final Variable URG_DERIVA_MEDICOPETICIONARIO = new Variable("10163", "99G2", new Long(10163),
            "Médico Peticionario", "Médico que realizar la consulta al servicio de urgencias", Variable.TIPO_VARIABLE_STRING);

    private Variable domicilio;
    public final Variable URG_DERIVA_DOMICILIO = new Variable("35005007", "99G2", new Long(35005007),
            "Domicilio", "Domicilio", Variable.TIPO_VARIABLE_STRING);

    private Variable patologiaHta;
    public final Variable URG_DERIVA_PATOLOGIAHTA = new Variable("10510", "99G2", new Long(10510),
            "HTA", "HTA", Variable.TIPO_VARIABLE_STRING);

    private Variable patologiaDiabetes;
    public final Variable URG_DERIVA_PATOLOGIADIABETES = new Variable("13822009", "99G2", new Long(13822009),
            "Diabetes", "Diabetes", Variable.TIPO_VARIABLE_STRING);

    private Variable patologiaIRespira;
    public final Variable URG_DERIVA_PATOLOGIAIRESPIRA = new Variable("9644", "99G2", new Long(9644),
            "Insuficiencia respiratoria", "Insuficiencia respiratoria", Variable.TIPO_VARIABLE_STRING);

    private Variable patologiaO2domicilio;
    public final Variable URG_DERIVA_PATOLOGIAO2DOMICI = new Variable("10087", "99G2", new Long(10087),
            "Oxigenoterapia domiciliciaria", "Oxigenoterapia domiciliciaria", Variable.TIPO_VARIABLE_STRING);

    private Variable patologiaIrenal;
    public final Variable URG_DERIVA_PATOLOGIAIREANL = new Variable("35004567", "99G2", new Long(35004567),
            "Insuficiencia Renal ", "Insuficiencia Renal", Variable.TIPO_VARIABLE_STRING);

    private Variable patologiaInmunosupresion;
    public final Variable URG_DERIVA_PATOLOGIAINMUNO = new Variable("D84.9", "I10C", new Long(60003583),
            "Inmunosupresión  ", "Inmunosupresión", Variable.TIPO_VARIABLE_STRING);

    private Variable patologiaTrataOncologico;
    public final Variable URG_DERIVA_PATOLOGIATRATAONCO = new Variable("P0-00B55", "SNM", new Long(13824284),
            "Tratamiento Oncológico  ", "Tratamiento Oncológico", Variable.TIPO_VARIABLE_STRING);

    private Variable patologiaEmbarazo;
    public final Variable URG_DERIVA_PATOLOGIAEMBARAZO = new Variable("289908002", "SNM3", new Long(7752627),
            "Embarazo ", "Embarazo", Variable.TIPO_VARIABLE_STRING);

    private Variable situacionBasal;
    public final Variable URG_DERIVA_SITUACIONBASAL = new Variable("40209", "99G2", new Long(300209),
            "Situación Basal ", "Situación Basal", Variable.TIPO_VARIABLE_STRING);

    private Variable tiempoEvolucion;
    public final Variable URG_DERIVA_TIEMPOEVOL = new Variable("35004726", "99G2", new Long(35004726),
            "Tiempo de evolución del proceso ", "Tiempo de evolución del proceso", Variable.TIPO_VARIABLE_STRING);

    private Variable tas;
    public final Variable URG_DERIVA_CTES_TAS = new ConstantesClinicas().VAR_CTES_TAS;

    private Variable tad;
    public final Variable URG_DERIVA_CTES_TAD = new ConstantesClinicas().VAR_CTES_TAD;

    private Variable temperatura;
    public final Variable URG_DERIVA_CTES_T = new ConstantesClinicas().VAR_CTES_T;

    private Variable fc;
    public final Variable URG_DERIVA_CTES_FC = new ConstantesClinicas().VAR_CTES_FC;

    private Variable fr;
    public final Variable URG_DERIVA_CTES_FR = new ConstantesClinicas().VAR_CTES_FR;

    private Variable satO2;
    public final Variable URG_DERIVA_SATO2 = new ConstantesClinicas().VAR_CTES_SATO2;

    private Variable sintomasTos;
    public final Variable URG_DERIVA_SINTOMASTOS = new Variable("13822586", "99G2", new Long(13822586),
            "Tos ", "Tos", Variable.TIPO_VARIABLE_STRING);

    private Variable sintomasDisnea;
    public final Variable URG_DERIVA_SINTOMASDISNEA = new Variable("R06.0", "I10C", new Long(60027411),
            "Disnea ", "Disnea", Variable.TIPO_VARIABLE_STRING);

    private Variable sintomasAutoexplora;
    public final Variable URG_DERIVA_SINTOMASAUTOEXPLORA = new Variable("35001997", "99G2", new Long(35001997),
            "Autoexploración ", "Autoexploración", Variable.TIPO_VARIABLE_STRING);

    private Variable sintomasAlateraMenta;
    public final Variable URG_DERIVA_SINTOMASALTERAMENTAL = new Variable("74732009", "SNM3", new Long(13824665),
            "Alteración mental ", "Alteración mental", Variable.TIPO_VARIABLE_STRING);

    private Variable sintomasOtros;
    public final Variable URG_DERIVA_SINTOMASOTROS = new Variable("R68.89", "I10C", new Long(60027933),
            "Otros sÍntomas  ", "Otros síntomas ", Variable.TIPO_VARIABLE_STRING);

    private Variable observaciones;
    public final Variable URG_DERIVA_OBSER = new ConstantesClinicas().VAR_OBSERVACIONES;

    private Variable destino;
    public final Variable URG_DERIVA_DESTINO = new Variable("V", "LN", new Long(768),
            "Destino  ", "Destino", Variable.TIPO_VARIABLE_STRING);

    public final static Long PLANTILLLA_VALORACION_DERIVACIONES_PRIMARIA = new Long(899641736);
    public final static Long TIPO_REGISTRO_ENF_CTES = new Long(6);

    public RegistroUrgDerivarUrg() {
        super();
        this.iniciaUrgDeriva();
    }

    public RegistroUrgDerivarUrg(Long id) {
        super(id);
        this.iniciaUrgDeriva();
    }

    public RegistroUrgDerivarUrg(RegistroUrgDerivarUrg rc) {
        super(rc);

        this.numero = rc.getNumero();
        this.fechaHoraLlamada = rc.getFechaHoraLlamada();
        this.fechahoravar = rc.getFechahoravar();
        this.cs = rc.getCs();
        this.medicoPeticionario = rc.getMedicoPeticionario();
        this.domicilio = rc.getDomicilio();
        this.patologiaHta = rc.getPatologiaHta();
        this.patologiaDiabetes = rc.getPatologiaDiabetes();
        this.patologiaIRespira = rc.getPatologiaIRespira();
        this.patologiaO2domicilio = rc.getPatologiaO2domicilio();
        this.patologiaIrenal = rc.getPatologiaIrenal();
        this.patologiaInmunosupresion = rc.getPatologiaInmunosupresion();
        this.patologiaTrataOncologico = rc.patologiaTrataOncologico;
        this.patologiaEmbarazo = rc.patologiaEmbarazo;
        this.situacionBasal = rc.situacionBasal;
        this.tiempoEvolucion = rc.getTiempoEvolucion();
        this.tas = rc.tas;
        this.tad = rc.getTad();
        this.temperatura = rc.getTemperatura();
        this.fc = rc.getFc();
        this.fr = rc.getFr();
        this.satO2 = rc.getSatO2();
        this.sintomasTos = rc.getSintomasTos();
        this.sintomasDisnea = rc.sintomasDisnea;
        this.sintomasAutoexplora = rc.getSintomasAutoexplora();
        this.sintomasAlateraMenta = rc.sintomasAlateraMenta;
        this.sintomasOtros = rc.getSintomasOtros();
        this.observaciones = rc.observaciones;
        this.destino = rc.destino;
    }

    public void iniciaUrgDeriva() {
        this.setTiporegistro(RegistroUrgDerivarUrg.TIPO_REGISTRO_ENF_CTES);
        this.setDescripcion("Registro de valoración de  para llamadas desde primaria");
        this.setPlantilla_edior(RegistroUrgDerivarUrg.PLANTILLLA_VALORACION_DERIVACIONES_PRIMARIA);
        this.servicio = Servicio.SERVICIO_URGENCIAS;
        this.numero = URG_DERIVA_NUMERO;
        this.fechahoravar = URG_DERIVA_FECHA;
        this.cs = URG_DERIVA_CS;
        this.medicoPeticionario = URG_DERIVA_MEDICOPETICIONARIO;
        this.domicilio = URG_DERIVA_DOMICILIO;
        this.patologiaHta = URG_DERIVA_PATOLOGIAHTA;
        this.patologiaDiabetes = URG_DERIVA_PATOLOGIADIABETES;
        this.patologiaIRespira = URG_DERIVA_PATOLOGIAIRESPIRA;
        this.patologiaO2domicilio = URG_DERIVA_PATOLOGIAO2DOMICI;
        this.patologiaIrenal = URG_DERIVA_PATOLOGIAIREANL;
        this.patologiaInmunosupresion = URG_DERIVA_PATOLOGIAINMUNO;
        this.patologiaTrataOncologico = URG_DERIVA_PATOLOGIATRATAONCO;
        this.patologiaEmbarazo = URG_DERIVA_PATOLOGIAEMBARAZO;
        this.situacionBasal = URG_DERIVA_SITUACIONBASAL;
        this.tiempoEvolucion = URG_DERIVA_TIEMPOEVOL;
        this.tas = URG_DERIVA_CTES_TAS;
        this.tad = URG_DERIVA_CTES_TAD;
        this.temperatura = URG_DERIVA_CTES_T;
        this.fc = URG_DERIVA_CTES_FC;
        this.fr = URG_DERIVA_CTES_FR;
        this.satO2 = URG_DERIVA_SATO2;
        this.sintomasTos = URG_DERIVA_SINTOMASTOS;
        this.sintomasDisnea = URG_DERIVA_SINTOMASDISNEA;
        this.sintomasAutoexplora = URG_DERIVA_SINTOMASAUTOEXPLORA;
        this.sintomasAlateraMenta = URG_DERIVA_SINTOMASALTERAMENTAL;
        this.sintomasOtros = URG_DERIVA_SINTOMASOTROS;
        this.observaciones = URG_DERIVA_OBSER;
        this.destino = URG_DERIVA_DESTINO;
    }

    public Variable getNumero() {
        return numero;
    }

    public Variable getVariableNumero() {
        return numero;
    }

    public String getNumeroString() {
        return numero.getValor();
    }

    public String getNumeroHC() {
        if (this.getPaciente() != null && !this.getPaciente().getNumerohc().isEmpty()) {
            return this.getPaciente().getNumerohc();
        } else {
            return " ";
        }
    }

    public void setNumerohc(String valor) {

    }

    public void setNumero(Variable numero) {
        this.numero = numero;
    }

    public void setNumero(String valor) {
        this.numero.setValor(valor);
    }

    public LocalDateTime getFechaHoraLlamada() {
        return fechaHoraLlamada;
    }

    public void setFechaHoraLlamada(LocalDateTime fechaHoraLlamada) {
        this.fechaHoraLlamada = fechaHoraLlamada;
    }

    public Variable getFechahoravar() {
        return fechahoravar;
    }

    public Variable getVariableFechahoravar() {
        return fechahoravar;
    }

    public String getFechahoravarString() {
        return fechahoravar.getValor();
    }

    public void setFechahoravar(Variable fechahoravar) {
        this.fechahoravar = fechahoravar;
        if (fechahoravar != null) {
            fechaHoraLlamada = Utilidades.getFechaLocalDateTime(fechahoravar.getValor());
        }
    }

    public void setFechahoravar(String valor) {
        this.fechahoravar.setValor(valor);
        if (valor != null) {
            fechaHoraLlamada = Utilidades.getFechaLocalDateTime(valor);
        }
    }

    public Centro getCentroSal() {
        return centroSal;
    }

    public String getCsNombre() {
        if (centroSal != null) {
            return centroSal.getDescripcion();
        } else {
            return "";
        }
    }

    public void setCentroSal(Centro centro) {
        this.centroSal = centro;
    }

    public Variable getCs() {
        return cs;
    }

    public Variable getVariableCs() {
        return cs;
    }

    public String getCsString() {
        return cs.getValor();
    }

    public void setCs(Variable cs) {
        this.cs = cs;
        if (cs != null && !cs.getValor().isEmpty()) {
            //   this.centroSal=new Centro();
            this.setCentroSal(new CentroDAO().getRegistroDescrip(cs.getValor()));
        }
    }

    public void setCs(String valor) {
        this.cs.setValor(valor);
        if (!valor.isEmpty()) {
            //     this.centroSal=new Centro();
            setCentroSal(new CentroDAO().getRegistroDescrip(valor));
        }
    }

    public Variable getDomicilio() {
        return domicilio;
    }

    public Variable getVariableDomicilio() {
        return domicilio;
    }

    public String getDomicilioString() {
        return domicilio.getValor();
    }

    public void setDomicilio(Variable domicilio) {
        this.domicilio = domicilio;
    }

    public void setDomicilio(String valor) {
        this.domicilio.setValor(valor);
    }

    public Variable getPatologiaO2domicilio() {
        return patologiaO2domicilio;
    }

    public Variable getVariablePatologiaO2domicilio() {
        return patologiaO2domicilio;
    }

    public Boolean getPatologiaO2domicilioBoolean() {
        if (patologiaO2domicilio != null && !patologiaO2domicilio.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatologiaO2domicilio(Variable patologiaO2domicilio) {
        this.patologiaO2domicilio = patologiaO2domicilio;
    }

    public void setPatologiaO2domicilio(Boolean valor) {
        if (valor == true) {
            this.patologiaO2domicilio.setValor("Necesita oxigenoterapia a domicilio");
        } else {
            this.patologiaO2domicilio.setValor("");
        }
    }

    public Variable getPatologiaIrenal() {
        return patologiaIrenal;
    }

    public Variable getVariablePatologiaIrenal() {
        return patologiaIrenal;
    }

    public Boolean getPatologiaIrenalBoolean() {
        if (patologiaIrenal != null && !patologiaIrenal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setPatologiaIrenal(Variable patologiaIrenal) {
        this.patologiaIrenal = patologiaIrenal;
    }

    public void setPatologiaIrenal(Boolean valor) {
        if (valor == true) {
            this.patologiaIrenal.setValor("Patología renal.");
        } else {
            this.patologiaIrenal.setValor("");
        }
    }

    public Variable getPatologiaTrataOncologico() {
        return patologiaTrataOncologico;
    }

    public Variable getVariablePatologiaTrataOncologico() {
        return patologiaTrataOncologico;
    }

    public Boolean getPatologiaTrataOncologicoBoolean() {
        if (patologiaTrataOncologico != null && !patologiaTrataOncologico.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setPatologiaTrataOncologico(Variable patologiaTrataOncologico) {
        this.patologiaTrataOncologico = patologiaTrataOncologico;
    }

    public void setPatologiaTrataOncologico(Boolean valor) {
        if (valor == true) {
            this.patologiaTrataOncologico.setValor("Tratamiento oncológico.");
        } else {
            this.patologiaTrataOncologico.setValor("");
        }
    }

    public Variable getSituacionBasal() {
        return situacionBasal;
    }

    public Variable getVariableSituacionBasal() {
        return situacionBasal;
    }

    public String getSituacionBasalString() {
        return situacionBasal.getValor();
    }

    public void setSituacionBasal(Variable situacionBasal) {
        this.situacionBasal = situacionBasal;
    }

    public void setSituacionBasal(String valor) {
        this.situacionBasal.setValor(valor);
    }

    public Variable getTas() {
        return tas;
    }

    public Variable getVariableTas() {
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

    public Variable getSintomasTos() {
        return sintomasTos;
    }

    public Variable getVariableSintomasTos() {
        return sintomasTos;
    }

    public Boolean getSintomasTosBoolean() {
        if (sintomasTos != null && !sintomasTos.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSintomasTos(Variable sintomasTos) {
        this.sintomasTos = sintomasTos;
    }

    public void setSintomasTos(Boolean valor) {
        if (valor == true) {
            this.sintomasTos.setValor("Tos");
        } else {
            this.sintomasTos.setValor("");
        }
    }

    public Variable getSintomasDisnea() {
        return sintomasDisnea;
    }

    public Variable getVariableSintomasDisnea() {
        return sintomasDisnea;
    }

    public Boolean getSintomasDisneaBoolean() {
        if (sintomasDisnea != null && !sintomasDisnea.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSintomasDisnea(Variable sintomasDisnea) {
        this.sintomasDisnea = sintomasDisnea;
    }

    public void setSintomasDisnea(Boolean valor) {
        if (valor == true) {
            this.sintomasDisnea.setValor("Disnea");
        } else {
            this.sintomasDisnea.setValor("");
        }
    }

    public Variable getSintomasAutoexplora() {
        return sintomasAutoexplora;
    }

    public Variable getVariableSintomasAutoexplora() {
        return sintomasAutoexplora;
    }

    public String getSintomasAutoexploraString() {
        return sintomasAutoexplora.getValor();
    }

    public void setSintomasAutoexplora(Variable sintomasAutoexplora) {
        this.sintomasAutoexplora = sintomasAutoexplora;
    }

    public void setSintomasAutoexplora(String valor) {
        this.sintomasAutoexplora.setValor(valor);
    }

    public Variable getSintomasAlateraMenta() {
        return sintomasAlateraMenta;
    }

    public Variable getVariableSintomasAlateraMenta() {
        return sintomasAlateraMenta;
    }

    public Boolean getSintomasAlateraMentaBoolean() {
        if (sintomasAlateraMenta != null && !sintomasAlateraMenta.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSintomasAlateraMenta(Variable sintomasAlateraMenta) {
        this.sintomasAlateraMenta = sintomasAlateraMenta;
    }

    public void setSintomasAlateraMenta(Boolean valor) {
        if (valor == true) {
            this.sintomasAlateraMenta.setValor("Alteración mental");
        } else {
            this.sintomasAlateraMenta.setValor("");
        }
    }

    public Variable getSintomasOtros() {
        return sintomasOtros;
    }

    public String getSintomasOtrosString() {
        return sintomasOtros.getValor();
    }

    public Variable getVariableSintomasOtros() {
        return sintomasOtros;
    }

    public void setSintomasOtros(Variable sintomasOtros) {
        this.sintomasOtros = sintomasOtros;
    }

    public void setSintomasOtros(String valor) {
        this.sintomasOtros.setValor(valor);
    }

    public Variable getMedicoPeticionario() {
        return medicoPeticionario;
    }

    public Variable getVariableMedicoPeticionario() {
        return medicoPeticionario;
    }

    public String getMedicoPeticionarioString() {
        return medicoPeticionario.getValor();
    }

    public void setMedicoPeticionario(Variable medicoPeticionario) {
        this.medicoPeticionario = medicoPeticionario;
    }

    public void setMedicoPeticionario(String valor) {
        this.medicoPeticionario.setValor(valor);
    }

    public Variable getPatologiaHta() {
        return patologiaHta;
    }

    public Variable getVariablePatologiaHta() {
        return patologiaHta;
    }

    public Boolean getPatologiaHtaBoolean() {
        if (patologiaHta != null && !patologiaHta.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatologiaHta(Variable patologiaHta) {
        this.patologiaHta = patologiaHta;
    }

    public void setPatologiaHta(Boolean valor) {
        if (valor == true) {
            this.patologiaHta.setValor("HTA");
        } else {
            this.patologiaHta.setValor("");
        }
    }

    public Variable getPatologiaDiabetes() {
        return patologiaDiabetes;
    }

    public Variable getVariablePatologiaDiabetes() {
        return patologiaDiabetes;
    }

    public Boolean getPatologiaDiabetesbBoolean() {
        if (patologiaDiabetes != null && !patologiaDiabetes.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatologiaDiabetes(Variable patologiaDiabetes) {
        this.patologiaDiabetes = patologiaDiabetes;
    }

    public void setPatologiaDiabetes(Boolean valor) {
        if (valor == true) {
            this.patologiaDiabetes.setValor("Diabetes");
        } else {
            this.patologiaDiabetes.setValor("");
        }
    }

    public Variable getPatologiaIRespira() {
        return patologiaIRespira;
    }

    public Variable getVariablePatologiaIRespira() {
        return patologiaIRespira;
    }

    public Boolean getPatologiaIRespiraBoolean() {
        if (patologiaIRespira != null && !patologiaIRespira.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatologiaIRespira(Variable patologiaIRespira) {
        this.patologiaIRespira = patologiaIRespira;
    }

    public void setPatologiaIRespira(Boolean valor) {
        if (valor == true) {
            this.patologiaIRespira.setValor("Insuficiencia respiratorio");
        } else {
            this.patologiaIRespira.setValor("");
        }
    }

    public Variable getPatologiaInmunosupresion() {
        return patologiaInmunosupresion;
    }

    public Variable getVariablePatologiaInmunosupresion() {
        return patologiaInmunosupresion;
    }

    public Boolean getPatologiaInmunosupresionBoolean() {
        if (patologiaInmunosupresion != null && !patologiaInmunosupresion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatologiaInmunosupresion(Variable patologiaInmunosupresion) {
        this.patologiaInmunosupresion = patologiaInmunosupresion;
    }

    public void setPatologiaInmunosupresion(Boolean valor) {
        if (valor == true) {
            this.patologiaInmunosupresion.setValor("Inmunosupresión");
        } else {
            this.patologiaInmunosupresion.setValor("");
        }
    }

    public Variable getPatologiaEmbarazo() {
        return patologiaEmbarazo;
    }

    public Variable getVarialbePatologiaEmbarazo() {
        return patologiaEmbarazo;
    }

    public Variable getVariablePatologiaEmbarazo() {
        return patologiaEmbarazo;
    }

    public Boolean getPatologiaEmbarazoBoolean() {
        if (patologiaEmbarazo != null && !patologiaEmbarazo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatologiaEmbarazo(Variable patologiaEmbarazo) {
        this.patologiaEmbarazo = patologiaEmbarazo;
    }

    public void setPatologiaEmbarazo(Boolean valor) {
        if (valor == true) {
            this.patologiaEmbarazo.setValor("Embarazo");
        } else {
            this.patologiaEmbarazo.setValor("");
        }
    }

    public Variable getTiempoEvolucion() {
        return tiempoEvolucion;
    }

    public Variable getVariableTiempoEvolucion() {
        return tiempoEvolucion;
    }

    public String getTiempoEvolucionString() {
        return tiempoEvolucion.getValor();
    }

    public void setTiempoEvolucion(Variable tiempoEvolucion) {
        this.tiempoEvolucion = tiempoEvolucion;
    }

    public void setTiempoEvolucion(String valor) {
        this.tiempoEvolucion.setValor(valor);
    }

    public Variable getFc() {
        return fc;
    }

    public Variable getVariableFc() {
        return fc;
    }

    public String getFcString() {
        return fc.getValor();
    }

    public void setFc(Variable fc) {
        this.fc = fc;
    }

    public void setFc(String valor) {
        this.fc.setValor(valor);
    }

    public Variable getFr() {
        return fr;
    }

    public Variable getVariableFr() {
        return fr;
    }

    public String getFrString() {
        return fr.getValor();
    }

    public void setFr(Variable fr) {
        this.fr = fr;
    }

    public void setFr(String valor) {
        this.fr.setValor(valor);
    }

    public Variable getSatO2() {
        return satO2;
    }

    public Variable getVariableSatO2() {
        return satO2;
    }

    public String getSatO2String() {
        return satO2.getValor();
    }

    public void setSatO2(Variable satO2) {
        this.satO2 = satO2;
    }

    public void setSatO2(String valor) {
        this.satO2.setValor(valor);
    }

    public Variable getObservaciones() {
        return observaciones;
    }

    public Variable getVariableObservaciones() {
        return observaciones;
    }

    public String getObservacioneString() {
        return observaciones.getValor();
    }

    public void setObservaciones(Variable observaciones) {
        this.observaciones = observaciones;
    }

    public void setObservaciones(String valor) {
        this.observaciones.setValor(valor);
    }

    public Variable getDestino() {
        return destino;
    }

    public Variable getVariableDestino() {
        return destino;
    }

    public String getDestinoString() {
        return destino.getValor();
    }

    public void setDestino(Variable destino) {
        this.destino = destino;
    }

    public void setDestino(String valor) {
        this.destino.setValor(valor);
    }
}
