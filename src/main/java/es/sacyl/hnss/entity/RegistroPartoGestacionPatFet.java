package es.sacyl.hnss.entity;

public class RegistroPartoGestacionPatFet extends Registro {

    private Variable patoFetoNo;
    private Variable patoFetoCIRTipo1;
    private Variable patoFetoCIRTipo2;
    private Variable patoFetoCIRTipo3;
    private Variable patoFetoPrematuro;
    private Variable patoFetopeg;
    private Variable patoFetoMacrosomia;

    private Variable patoFetoAlteracionLiq;
    private Variable patoFetoMalformacion;
    private Variable patoFetoHidramnios;
    private Variable patoFetoOligoamnios;
    private Variable patoFetoOtros;
    private Variable eco20semanas;
    private Variable eco20alteracion;

    private Variable amniocentesis;
    private Variable biopsiacorial;
    private Variable TPN;

    private Variable maduracionPulmonar;
    private Variable maduracionSemanas;
    private Variable tocolisis;

    public final static Long PLANTILLLA_EDITOR_PAR_GESTACIONPATFET = new Long(847321393);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public final Variable VAR_PARTO_GESTA_PAT_FET_NO = new Variable("13994582", "99G2", new Long(13994582), "PatFetNo");
    public final Variable VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO1 = new Variable("13995113", "99G2", new Long(13995113),
            "Pato.Feto CIR Tipo1");
    public final Variable VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO2 = new Variable("13995114", "99G2", new Long(13995114),
            "Pato.Feto CIR Tipo2");
    public final Variable VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO3 = new Variable("13995115", "99G2", new Long(13995115),
            "Pato.Feto CIR Tipo3");
    public final Variable VAR_PARTO_GESTA_PAT_FET_PREMATUIDAD = new Variable("44247006", "SNM3", new Long(3008),
            "Prematuridad");
    public final Variable VAR_PARTO_GESTA_PAT_FET_PEG = new Variable("P05.1", "I10C", new Long(60026021),
            "Prematuridad");
    public final Variable VAR_PARTO_GESTA_PAT_FET_MACROSOMIA = new Variable("13995201", "99G2", new Long(13995201),
            "Macrosomia");

    public final Variable VAR_PARTO_GESTA_PAT_FET_LIQADMI = new Variable("13994598", "99G2", new Long(13994598),
            "PatFetLiaAqdmi");
    public final Variable VAR_PARTO_GESTA_PAT_FET_MALFORMACIÓN = new Variable("13995105", "99G2", new Long(13995105),
            "Malformación");

    public final Variable VAR_PARTO_GESTA_PAT_FET_HIDRAMNIOS = new Variable("13995106", "99G2", new Long(13995106),
            "Hidramnios");
    public final Variable VAR_PARTO_GESTA_PAT_FET_OLIGOAMNIOS = new Variable("13995107", "99G2", new Long(13995107),
            "Oligoamnios");
    public final Variable VAR_PARTO_GESTA_PAT_FET_OTROS = new Variable("13994584", "99G2", new Long(13994584),
            "PatFetOtros");

    public final Variable VAR_PARTO_GESTA_PAT_FET_ECO20 = new Variable("13994585", "99G2", new Long(13994585), "ECO20");
    public final Variable VAR_PARTO_GESTA_PAT_FET_ECO20ALTERA = new Variable("20018", "99G2", new Long(100044),
            "ECO20 Alteración");

    public final Variable VAR_PARTO_GESTA_PAT_FET_AMNIO = new Variable("34536000", "SNM3", new Long(100077),
            "Amniocentesis");
    public final Variable VAR_PARTO_GESTA_PAT_FET_BIOCORI = new Variable("8820", "99G2", new Long(8820),
            "Biopsia corial");

    public final Variable VAR_PARTO_GESTA_PAT_FET_TPN = new Variable("13995116", "99G2", new Long(13995116),
            "Test Perinatal No invasivo");

    public final Variable VAR_PARTO_GESTA_PAT_FET_MADURACION = new Variable("13995203", "99G2", new Long(13995203),
            "Maduración cervical");
    public final Variable VAR_PARTO_GESTA_PAT_FET_MADURACIONSEMANAS = new Variable("13995204", "99G2",
            new Long(13995204), "Maduración cervical");
    public final Variable VAR_PARTO_GESTA_PAT_FET_TOCOLISIS = new Variable("13995205", "99G2", new Long(13995205),
            "Tocolisis");

    public RegistroPartoGestacionPatFet() {
        this.iniciaGestacionpATfET();
    }

    public RegistroPartoGestacionPatFet(Long id) {
        super(id);
        this.iniciaGestacionpATfET();
    }

    public RegistroPartoGestacionPatFet(RegistroPartoGestacionPatFet r) {
        super(r);
        this.patoFetoNo = r.getPatoFetoNo();
        this.patoFetoCIRTipo1 = r.getPatoFetoCIRTipo1();
        this.patoFetoCIRTipo2 = r.getPatoFetoCIRTipo2();
        this.patoFetoCIRTipo3 = r.getPatoFetoCIRTipo3();
        this.patoFetoPrematuro = r.getPatoFetoPrematuro();
        this.patoFetopeg = r.getPatoFetopeg();
        this.patoFetoMacrosomia = r.getPatoFetoMacrosomia();

        this.patoFetoAlteracionLiq = r.getPatoFetoAlteracionLiq();
        this.patoFetoMalformacion = r.getPatoFetoMalformacion();
        this.patoFetoHidramnios = r.getPatoFetoHidramnios();
        this.patoFetoOligoamnios = r.getPatoFetoOligoamnios();
        this.patoFetoOtros = r.getPatoFetoOtros();

        this.eco20semanas = r.getEco20semanas();
        this.eco20alteracion = r.getEco20alteracion();
        this.amniocentesis = r.getAmniocentesis();
        this.biopsiacorial = r.getBiopsiacorial();
        this.TPN = r.getTPN();

        this.maduracionPulmonar = r.getMaduracionPulmonar();
        this.maduracionSemanas = r.getMaduracionSemanas();
        this.tocolisis = r.getTocolisis();

    }

    public void iniciaGestacionpATfET() {

        this.setTiporegistro(RegistroPartoGestacionPatFet.TIPO_REGISTRO_PARTO);
        this.setDescripcion("2.1-Patología Fetal");
        this.setPlantilla_edior(RegistroPartoGestacionPatFet.PLANTILLLA_EDITOR_PAR_GESTACIONPATFET);
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));

        this.patoFetoNo = this.VAR_PARTO_GESTA_PAT_FET_NO;
        this.patoFetoCIRTipo1 = this.VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO1;
        this.patoFetoCIRTipo2 = this.VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO2;
        this.patoFetoCIRTipo3 = this.VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO3;
        this.patoFetoPrematuro = this.VAR_PARTO_GESTA_PAT_FET_PREMATUIDAD;
        this.patoFetopeg = this.VAR_PARTO_GESTA_PAT_FET_PEG;
        this.patoFetoMacrosomia = this.VAR_PARTO_GESTA_PAT_FET_MACROSOMIA;

        this.patoFetoAlteracionLiq = this.VAR_PARTO_GESTA_PAT_FET_LIQADMI;
        this.patoFetoMalformacion = this.VAR_PARTO_GESTA_PAT_FET_MALFORMACIÓN;
        this.patoFetoHidramnios = this.VAR_PARTO_GESTA_PAT_FET_HIDRAMNIOS;
        this.patoFetoOligoamnios = this.VAR_PARTO_GESTA_PAT_FET_OLIGOAMNIOS;
        this.patoFetoOtros = this.VAR_PARTO_GESTA_PAT_FET_OTROS;

        this.eco20semanas = this.VAR_PARTO_GESTA_PAT_FET_ECO20;
        this.eco20alteracion = this.VAR_PARTO_GESTA_PAT_FET_ECO20ALTERA;
        this.amniocentesis = this.VAR_PARTO_GESTA_PAT_FET_AMNIO;
        this.biopsiacorial = this.VAR_PARTO_GESTA_PAT_FET_BIOCORI;
        this.TPN = this.VAR_PARTO_GESTA_PAT_FET_TPN;

        this.maduracionPulmonar = this.VAR_PARTO_GESTA_PAT_FET_MADURACION;
        this.maduracionSemanas = this.VAR_PARTO_GESTA_PAT_FET_MADURACIONSEMANAS;
        this.tocolisis = this.VAR_PARTO_GESTA_PAT_FET_TOCOLISIS;

    }

    public Variable getPatoFetoNo() {
        return patoFetoNo;
    }

    public Variable getVariablePatoFetoNo() {
        return patoFetoNo;
    }

    public void setPatoFetoNo(Variable patoFetoNo) {
        this.patoFetoNo = patoFetoNo;
    }

    public Boolean getPatoFetoNoBoolean() {
        if (patoFetoNo != null && patoFetoNo.getValor() != null && !patoFetoNo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoNo(String valor) {
        this.patoFetoNo.setValor(valor);
    }

    public void setPatoFetoNo(Boolean valor) {
        if (valor == true) {
            this.patoFetoNo.setValor("No patología fetal");
        } else {
            this.patoFetoNo.setValor(null);
        }
    }

    public Variable getPatoFetoCIRTipo1() {
        return patoFetoCIRTipo1;
    }

    public Variable getVariablePatoFetoCIRTipo1() {
        return patoFetoCIRTipo1;
    }

    public Boolean getPatoFetoCIRTipo1Boolean() {
        if (patoFetoCIRTipo1 != null && patoFetoCIRTipo1.getValor() != null && !patoFetoCIRTipo1.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoCIRTipo1(Variable patoFetoCIRTipo1) {
        this.patoFetoCIRTipo1 = patoFetoCIRTipo1;
    }

    public void setPatoFetoCIRTipo1(Boolean valor) {
        if (valor == true) {
            this.patoFetoCIRTipo1.setValor("Feto CIR Tipo1");
        } else {
            this.patoFetoCIRTipo1.setValor(null);
        }
    }

    public Variable getPatoFetoCIRTipo2() {
        return patoFetoCIRTipo2;
    }

    public Variable getVariablePatoFetoCIRTipo2() {
        return patoFetoCIRTipo2;
    }

    public Boolean getPatoFetoCIRTipo2Boolean() {
        if (patoFetoCIRTipo2 != null && patoFetoCIRTipo2.getValor() != null && !patoFetoCIRTipo2.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoCIRTipo2(Variable patoFetoCIRTipo2) {
        this.patoFetoCIRTipo2 = patoFetoCIRTipo2;
    }

    public void setPatoFetoCIRTipo2(Boolean valor) {
        if (valor == true) {
            this.patoFetoCIRTipo2.setValor("Feto CIR Tipo2");
        } else {
            this.patoFetoCIRTipo2.setValor(null);
        }
    }

    public Variable getPatoFetoCIRTipo3() {
        return patoFetoCIRTipo3;
    }

    public Variable getVariablePatoFetoCIRTipo3() {
        return patoFetoCIRTipo3;
    }

    public Boolean getPatoFetoCIRTipo3Boolean() {
        if (patoFetoCIRTipo3 != null && patoFetoCIRTipo3.getValor() != null && !patoFetoCIRTipo3.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoCIRTipo3(Variable patoFetoCIRTipo3) {
        this.patoFetoCIRTipo3 = patoFetoCIRTipo3;
    }

    public void setPatoFetoCIRTipo3(Boolean valor) {

        if (valor == true) {
            this.patoFetoCIRTipo3.setValor("Feto CIR Tipo3");
        } else {
            this.patoFetoCIRTipo3.setValor(null);
        }

    }

    public Variable getPatoFetoPrematuro() {
        return patoFetoPrematuro;
    }

    public Variable getVariablePatoFetoPrematuro() {
        return patoFetoPrematuro;
    }

    public Boolean getPatoFetoPrematuroBoolena() {
        if (patoFetoPrematuro != null && patoFetoPrematuro.getValor() != null
                && !patoFetoPrematuro.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoPrematuro(Variable patoFetoPrematuro) {
        this.patoFetoPrematuro = patoFetoPrematuro;
    }

    public Variable getPatoFetopeg() {
        return patoFetopeg;
    }

    public Variable getVariablePatoFetopeg() {
        return patoFetopeg;
    }

    public Boolean getPatoFetopegBoolean() {
        if (patoFetopeg != null && patoFetopeg.getValor() != null && !patoFetopeg.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetopeg(Variable patoFetopeg) {
        this.patoFetopeg = patoFetopeg;
    }

    public void setPatoFetopeg(Boolean valor) {
        if (valor == true) {
            patoFetopeg.setValor("Pequeño para la edad gestacional");
        } else {
            patoFetopeg.setValor("");
        }
    }

    public Variable getPatoFetoMacrosomia() {
        return patoFetoMacrosomia;
    }

    public Variable getVariablePatoFetoMacrosomia() {
        return patoFetoMacrosomia;
    }

    public Boolean getPatoFetoMacrosomiaBoolean() {
        if (patoFetoMacrosomia != null && patoFetoMacrosomia.getValor() != null
                && !patoFetoMacrosomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoMacrosomia(Variable patoFetoMacrosomia) {
        this.patoFetoMacrosomia = patoFetoMacrosomia;
    }

    public void setPatoFetoMacrosomia(Boolean valor) {
        if (valor == true) {
            patoFetoMacrosomia.setValor("Macrosomia");
        } else {
            patoFetoMacrosomia.setValor("");
        }
    }

    public void setPatoFetoPrematuro(Boolean valor) {
        if (valor == true) {
            this.patoFetoCIRTipo3.setValor("Prematuridad");
        } else {
            this.patoFetoCIRTipo3.setValor(null);
        }
    }

    public Variable getPatoFetoAlteracionLiq() {
        return patoFetoAlteracionLiq;
    }

    public Variable getVariablePatoFetoAlteracionLiq() {
        return patoFetoAlteracionLiq;
    }

    public Boolean getPatoFetoAlteracionLiqBoolean() {
        if (patoFetoAlteracionLiq != null && patoFetoAlteracionLiq.getValor() != null
                && !patoFetoAlteracionLiq.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoAlteracionLiq(Variable patoFetoAlteracionLiq) {
        this.patoFetoAlteracionLiq = patoFetoAlteracionLiq;
    }

    public void setPatoFetoAlteracionLiq(String valor) {
        this.patoFetoAlteracionLiq.setValor(valor);
    }

    public void setPatoFetoAlteracionLiq(Boolean valor) {
        if (valor == true) {
            this.patoFetoAlteracionLiq.setValor("Alteración líquido amniótico ");
        } else {
            this.patoFetoAlteracionLiq.setValor(null);
        }
    }

    public Variable getPatoFetoMalformacion() {
        return patoFetoMalformacion;
    }

    public Variable getVariablePatoFetoMalformacion() {
        return patoFetoMalformacion;
    }

    public Boolean getPatoFetoMalformacionBoolean() {
        if (patoFetoMalformacion != null && patoFetoMalformacion.getValor() != null
                && !patoFetoMalformacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoMalformacion(Variable patoFetoMalformacion) {
        this.patoFetoMalformacion = patoFetoMalformacion;
    }

    public void setPatoFetoMalformacion(Boolean valor) {
        if (valor == true) {
            this.patoFetoMalformacion.setValor("Malformación");
        } else {
            this.patoFetoMalformacion.setValor(null);
        }
    }

    public Variable getPatoFetoHidramnios() {
        return patoFetoHidramnios;
    }

    public Variable getVariablePatoFetoHidramnios() {
        return patoFetoHidramnios;
    }

    public Boolean getPatoFetoHidramniosoBoolean() {
        if (patoFetoHidramnios != null && patoFetoHidramnios.getValor() != null
                && !patoFetoHidramnios.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoHidramnios(Variable patoFetoHidramnios) {
        this.patoFetoHidramnios = patoFetoHidramnios;
    }

    public void setPatoFetoHidramnios(Boolean valor) {
        if (valor == true) {
            this.patoFetoHidramnios.setValor("Polihidramnios");
        } else {
            this.patoFetoHidramnios.setValor(null);
        }
    }

    public Variable getPatoFetoOligoamnios() {
        return patoFetoOligoamnios;
    }

    public Variable getVariablePatoFetoOligoamnios() {
        return patoFetoOligoamnios;
    }

    public Boolean getPatoFetoOligoamniosolBoolean() {
        if (patoFetoOligoamnios != null && patoFetoOligoamnios.getValor() != null
                && !patoFetoOligoamnios.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoFetoOligoamnios(Variable patoFetoOligoamnios) {
        this.patoFetoOligoamnios = patoFetoOligoamnios;
    }

    public void setPatoFetoOligoamnios(Boolean valor) {
        if (valor == true) {
            this.patoFetoOligoamnios.setValor("Oligoamnios");
        } else {
            this.patoFetoOligoamnios.setValor(null);
        }
    }

    public Variable getPatoFetoOtros() {
        return patoFetoOtros;
    }

    public Variable getVariablePatoFetoOtros() {
        return patoFetoOtros;
    }

    public String getPatoFetoOtrosString() {
        return patoFetoOtros.getValor();
    }

    public void setPatoFetoOtros(Variable patoFetoOtros) {
        this.patoFetoOtros = patoFetoOtros;
    }

    public void setPatoFetoOtros(String valor) {
        this.patoFetoOtros.setValor(valor);
    }

    public Variable getEco20semanas() {
        return eco20semanas;
    }

    public Variable getVariableEco20semanas() {
        return eco20semanas;
    }

    public String getEco20semanasString() {
        return eco20semanas.getValor();
    }

    public void setEco20semanas(String valor) {
        this.eco20semanas.setValor(valor);
    }

    public void setEco20semanas(Variable eco20semanas) {
        this.eco20semanas = eco20semanas;
    }

    public void setVariableEco20semanas(Variable eco20semanas) {
        this.eco20semanas = eco20semanas;
    }

    public Variable getEco20alteracion() {
        return eco20alteracion;
    }

    public Variable getVariableEco20alteracion() {
        return eco20alteracion;
    }

    public String getEco20alteracionString() {
        return eco20alteracion.getValor();
    }

    public void setEco20alteracion(Variable eco20alteracion) {
        this.eco20alteracion = eco20alteracion;
    }

    public void setEco20alteracion(String valor) {
        this.eco20alteracion.setValor(valor);
    }

    public Variable getAmniocentesis() {
        return amniocentesis;
    }

    public Variable getVariableAmniocentesis() {
        return amniocentesis;
    }

    public String getAmniocentesisString() {
        return amniocentesis.getValor();
    }

    public void setAmniocentesis(Variable amniocentesis) {
        this.amniocentesis = amniocentesis;
    }

    public void setAmniocentesis(String valor) {
        this.amniocentesis.setValor(valor);
    }

    public Variable getBiopsiacorial() {
        return biopsiacorial;
    }

    public Variable getVariableBiopsiacorial() {
        return biopsiacorial;
    }

    public String getBiopsiacorialString() {
        return biopsiacorial.getValor();
    }

    public void setBiopsiacorial(Variable biopsiacorial) {
        this.biopsiacorial = biopsiacorial;
    }

    public void setBiopsiacorial(String valor) {
        this.biopsiacorial.setValor(valor);
    }

    public Variable getTPN() {
        return TPN;
    }

    public Variable getVariableTPN() {
        return TPN;
    }

    public String getTPNString() {
        return TPN.getValor();
    }

    public void setTPN(Variable tPN) {
        TPN = tPN;
    }

    public void setTPN(String valor) {
        TPN.setValor(valor);
    }

    public Variable getMaduracionPulmonar() {
        return maduracionPulmonar;
    }

    public Variable getVariableMaduracionPulmonar() {
        return maduracionPulmonar;
    }

    public Boolean getMaduracionPulmonarBoolena() {
        if (maduracionPulmonar != null && !maduracionPulmonar.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setMaduracionPulmonar(Variable maduracionPulmonar) {
        this.maduracionPulmonar = maduracionPulmonar;
    }

    public void setMaduracionPulmonar(Boolean valor) {
        if (valor == true) {
            this.maduracionPulmonar.setValor("Maduracion Pulmonar");
        } else {
            this.maduracionPulmonar.setValor("");
        }
    }

    public Variable getMaduracionSemanas() {
        return maduracionSemanas;
    }

    public Variable getVariableMaduracionSemanas() {
        return maduracionSemanas;
    }

    public String getMaduracionSemanasString() {
        return maduracionSemanas.getValor();
    }

    public void setMaduracionSemanas(Variable maduracionSemanas) {
        this.maduracionSemanas = maduracionSemanas;
    }

    public void setMaduracionSemanas(String valor) {
        this.maduracionSemanas.setValor(valor);
    }

    public Variable getTocolisis() {
        return tocolisis;
    }

    public Variable getVariableTocolisis() {
        return tocolisis;
    }

    public Boolean getTocolisisBoolean() {
        if (tocolisis != null && !tocolisis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTocolisis(Variable tocolisis) {
        this.tocolisis = tocolisis;
    }

    public void setTocolisis(Boolean valor) {
        if (valor == true) {
            this.tocolisis.setValor("Tocolisis");
        } else {
            this.tocolisis.setValor("");
        }
    }

    public String getPatologiasFeto() {
        String texto = "";
        if (patoFetoNo != null) {
            texto = texto.concat(patoFetoNo.getValor());
        }

        if (patoFetoCIRTipo1 != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patoFetoCIRTipo1.getValor());
        }
        if (patoFetoCIRTipo2 != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patoFetoCIRTipo2.getValor());
        }
        if (patoFetoCIRTipo3 != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patoFetoCIRTipo3.getValor());
        }
        if (patoFetoAlteracionLiq != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patoFetoAlteracionLiq.getValor());
        }
        if (patoFetoMalformacion != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patoFetoMalformacion.getValor());
        }
        if (patoFetoHidramnios != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patoFetoHidramnios.getValor());
        }
        if (patoFetoOligoamnios != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patoFetoOligoamnios.getValor());
        }
        if (patoFetoOtros != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patoFetoOtros.getValor());
        }
        if (maduracionPulmonar != null && !maduracionPulmonar.getValor().isEmpty()) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(maduracionPulmonar.getValor());
            if (maduracionSemanas != null && !maduracionSemanas.getValor().isEmpty()) {

                texto = texto.concat(" semanas =" + maduracionSemanas.getValor());
            }
        }
        if (tocolisis != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(tocolisis.getValor());
        }

        return texto;
    }

}
