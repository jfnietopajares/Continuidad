package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;



public class RegistroPartoGestacion extends Registro {

    private Variable edad;
    private Variable edadGestacional;
    private Variable fechaUltimaRegla;
    private Variable fechaprobableParto;

    private Variable paridad;
    private Variable grupoABO;
    private Variable IMC;

    private Variable habitoxTabaco;
    private Variable habitoxAlcohol;
    private Variable habitoxCanabis;
    private Variable habitoxOtras;

    private Variable patoMatNo;
    private Variable patoMatTrombofilia;
    private Variable patoMatDiabetesGestional;
    private Variable TVP;
    private Variable DMPG;
    private Variable DMG;
    private Variable EII;
    private Variable reumatogicas;
    private Variable cancer;
    private Variable IPuterinas_95;
    private Variable AR;
    private Variable HTA;
    private Variable APPE;
    private Variable epilepsia;

    private Variable hipotiropre;
    private Variable hipotiropos;
    private Variable plaquetopenia;
    private Variable infeccion;
    private Variable patoMatOtros;

    private Variable alergia;
    private Variable tratamientoEmbarazo;

    private Variable sero1TRubeola;
    private Variable sero1Tsifilis;
    private Variable sero1TToxo;
    private Variable sero1TVHB;
    private Variable sero1TVHC;
    private Variable sero1TVIH;

    private Variable sero3TRubeola;
    private Variable sero3Tsifilis;
    private Variable sero3TToxo;
    private Variable sero3TVHB;
    private Variable sero3TVHC;
    private Variable sero3TVIH;

    private Variable esteGrupoBFecha;
    private Variable esteGrupoBValor;

    private Variable tecReproAsist;
    // private Variable pesoEstimado;

    private Variable gemelar;
    private Variable numeroFetos;

    private Variable insulina;
    private Variable insulinaTiempo;
    private Variable dietaejercicio;

    public final static Long PLANTILLLA_EDITOR_PAR_GESTACION = new Long(794855482);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public final Variable VAR_PARTO_GESTA_EDAD = new Variable("397669002", "SNM3", new Long(13391551), "Edad");
    public final Variable VAR_PARTO_GESTA_EGESTAIONAL = new Variable("57036006", "SNM3", new Long(7967),
            " E.Gestacional");
    public final Variable VAR_PARTO_GESTA_FUR = new Variable("21840007", "SNM3", new Long(7747091), " FUR");
    public final Variable VAR_PARTO_GESTA_FPP = new Variable("161714006", "SNM3", new Long(47311455), " FPP");

    public final Variable VAR_PARTO_GESTA_PARIDAD = new Variable("364325004", "SNM3", new Long(7747089), " Paridad");

    public final Variable VAR_PARTO_GESTA_GABO = new Variable("882-1", "LN", new Long(1495), " GABO");
    public final Variable VAR_PARTO_GESTA_IMC = new Variable("9673", "99G2", new Long(9673), " IMC");

    public final Variable VAR_PARTO_GESTA_HABITOXTABACO = new Variable("266918002", "SNM3", new Long(13829364),
            " Hábitos tóxicos tabaco");
    public final Variable VAR_PARTO_GESTA_HABITOXALCOHOL = new Variable("160573003", "SNM3", new Long(13824124),
            " Hábitos tóxicos alcohol");
    public final Variable VAR_PARTO_GESTA_HABITOXCANABIS = new Variable("F12.9", "I10C", new Long(60004673),
            " Hábitos tóxicos canabis");

    public final Variable VAR_PARTO_GESTA_HABITOXOROS = new Variable("F19.9", "I10C", new Long(60005044),
            " Hábitos tóxicos otros");

    public final Variable VAR_PARTO_GESTA_PAT_MAT_NO = new Variable("13994579", "99G2", new Long(13994579),
            " PatMatNo");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_TROMBO = new Variable("13994580", "99G2", new Long(13994580),
            "PatMatTrombofilia");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_DIABE = new Variable("11687002", "SNM3", new Long(35000250),
            "PatMatDiabetesGes");
    // VARIABLES NUEVAS
    public final Variable VAR_PARTO_GESTA_PAT_MAT_TVP = new Variable("13995093", "99G2", new Long(13995093), "TVP");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_DMPG = new Variable("13995094", "99G2", new Long(13995094), "DMPG");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_DMP = new Variable("13995095", "99G2", new Long(13995095), "DMP");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_EII = new Variable("13995096", "99G2", new Long(13995096), "EII");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_REUMATOL = new Variable("13995097", "99G2", new Long(13995097),
            "Reumatol");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_CANCER = new Variable("13995098", "99G2", new Long(13995098),
            "Cáncer");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_IP_UTERINAS = new Variable("13995099", "99G2", new Long(13995099),
            "IPuterinas>95");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_AR = new Variable("13995100", "99G2", new Long(13995100), "AR");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_HTA = new Variable("13995101", "99G2", new Long(13995101), "HTA");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_APPE = new Variable("13995102", "99G2", new Long(13995102), "APPE");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_EPILEPSIA = new Variable("13995103", "99G2", new Long(13995103),
            "Epilepsia");

    public final Variable VAR_PARTO_GESTA_PAT_MAT_HIPOTIPRE = new Variable("13995207", "99G2", new Long(13995207),
            "Hipotiroidismo pregestacional");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_HIPOGESE = new Variable("13995208", "99G2", new Long(13995208),
            "Hipotiroidismo gestacional");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_PLAQUETO = new Variable("13995209", "99G2", new Long(13995209),
            "Plaquetopenia");
    public final Variable VAR_PARTO_GESTA_PAT_MAT_INFECCGES = new Variable("13995210", "99G2", new Long(13995210),
            "Infección gestacional");

    public final Variable VAR_PARTO_GESTA_PAT_MAT_OTROS = new Variable("13994581", "99G2", new Long(13994581),
            "PatMatOtros");

    public final Variable VAR_PARTO_GESTA_ALERGIA = new Variable("106190000", "99G2", new Long(13524547), "Alergia");
    public final Variable VAR_PARTO_GESTA_TRATAMIENTOEMBARAZO = new Variable("13831959", "99G2", new Long(13831959),
            "Tratamiento embarazo");

    public final Variable VAR_PARTO_GESTA_SERO1_RUBE = new Variable("13994586", "99G2", new Long(13994586),
            "Sero1 Rubeola");
    public final Variable VAR_PARTO_GESTA_SERO1_SIFI = new Variable("13994587", "99G2", new Long(13994587),
            "Sero1 Sífilis");
    public final Variable VAR_PARTO_GESTA_SERO1_TOXO = new Variable("13994588", "99G2", new Long(13994588),
            "Sero1 Toxo");
    public final Variable VAR_PARTO_GESTA_SERO1_VHB = new Variable("13994589", "99G2", new Long(13994589), "Sero1 VHB");
    public final Variable VAR_PARTO_GESTA_SERO1_VHC = new Variable("13994590", "99G2", new Long(13994590), "Sero1 VHC");
    public final Variable VAR_PARTO_GESTA_SERO1_VIH = new Variable("13994591", "99G2", new Long(13994591), "Sero1 VIH");

    public final Variable VAR_PARTO_GESTA_SERO3_RUBE = new Variable("13994592", "99G2", new Long(13994592),
            "Sero3 Rubeola");
    public final Variable VAR_PARTO_GESTA_SERO3_SIFI = new Variable("13994593", "99G2", new Long(13994593),
            "Sero3 Sífilis");
    public final Variable VAR_PARTO_GESTA_SERO3_TOXO = new Variable("13994594", "99G2", new Long(13994594),
            "Sero Toxo");
    public final Variable VAR_PARTO_GESTA_SERO3_VHB = new Variable("13994595", "99G2", new Long(13994595), "Sero3 VHB");
    public final Variable VAR_PARTO_GESTA_SERO3_VHC = new Variable("13994596", "99G2", new Long(13994596), "Sero3 VHC");
    public final Variable VAR_PARTO_GESTA_SERO3_VIH = new Variable("13994597", "99G2", new Long(13994597), "Sero3 VIH");

    public final Variable VAR_PARTO_GESTA_SGB_FECHA = new Variable("13994599", "99G2", new Long(13994599), "SGB Fecha");
    public final Variable VAR_PARTO_GESTA_SGB_VALOR = new Variable("13994600", "99G2", new Long(13994600), "SGB Valor");

    public final Variable VAR_PARTO_GESTA_TECREPUASIST = new Variable("13995108", "99G2", new Long(13995108),
            "Técnicas de reproducción asistida");

    public final Variable VAR_PARTO_GESTA_GEMELAR = new Variable("65147003", "SNM3", new Long(35004087), "Gemalar");

    public final Variable VAR_PARTO_GESTA_NUMEROFETOS = new Variable("246435002", "SNM3", new Long(35000247),
            "Nº Fetos");

    public final Variable VAR_PARTO_GESTA_INSULINA = new Variable("3695-4", "LN", new Long(3858), "Insulina");
    public final Variable VAR_PARTO_GESTA_DIETAEJERCICIO = new Variable("13995206", "99G2", new Long(13995206),
            "Dieta ejercicio");
    public final Variable VAR_PARTO_GESTA_INSULINATIEMPO = new Variable("13826104", "99G2", new Long(13826104),
            "Insulina tiempo tratamiento");

    public RegistroPartoGestacion() {
        super();
        this.iniciaGestacion();
    }

    public RegistroPartoGestacion(Long id) {
        super(id);
        this.iniciaGestacion();
    }

    public RegistroPartoGestacion(RegistroPartoGestacion r) {
        super(r);
        this.edad = r.getEdad();
        this.edadGestacional = r.getEdadGestacional();
        this.fechaUltimaRegla = r.getFechaUltimaRegla();
        this.fechaprobableParto = r.getFechaprobableParto();

        this.paridad = r.getParidad();
        this.grupoABO = r.getGrupoABO();
        this.IMC = r.getIMC();
        this.habitoxTabaco = r.getHabitoxTabaco();
        this.habitoxAlcohol = r.getHabitoxAlcohol();
        this.habitoxCanabis = r.getHabitoxCanabis();
        this.habitoxOtras = r.getHabitoxOtras();

        this.patoMatNo = r.getPatoMatNo();
        this.patoMatTrombofilia = r.getPatoMatTrombofilia();
        this.patoMatDiabetesGestional = r.getPatoMatDiabetesGestional();
        this.TVP = r.getTVP();
        this.DMPG = r.getDMPG();
        this.DMG = r.getDMG();
        this.EII = r.getEII();
        this.reumatogicas = r.reumatogicas;
        this.cancer = r.getCancer();
        this.IPuterinas_95 = r.getIPuterinas_95();
        this.AR = r.getAR();
        this.HTA = r.getHTA();
        this.APPE = r.getAPPE();
        this.epilepsia = r.getEpilepsia();

        this.hipotiropre = r.getHipotiropre();
        this.hipotiropos = r.getHipotiropos();
        this.plaquetopenia = r.getPlaquetopenia();
        this.infeccion = r.getInfeccion();
        this.patoMatOtros = r.getPatoMatOtros();

        this.alergia = r.getAlergia();
        this.tratamientoEmbarazo = r.getTratamientoEmbarazo();

        this.sero1TRubeola = r.getSero1TRubeola();
        this.sero1Tsifilis = r.getSero1Tsifilis();
        this.sero1TToxo = r.getSero1TToxo();
        this.sero1TVHB = r.getSero1TVHB();
        this.sero1TVHC = r.getSero1TVHC();
        this.sero1TVIH = r.getSero1TVIH();

        this.sero3TRubeola = r.getSero3TRubeola();
        this.sero3Tsifilis = r.getSero3Tsifilis();
        this.sero3TToxo = r.getSero3TToxo();
        this.sero3TVHB = r.getSero3TVHB();
        this.sero3TVHC = r.getSero3TVHC();
        this.sero3TVIH = r.getSero3TVIH();

        this.esteGrupoBFecha = r.getEsteGrupoBFecha();
        this.esteGrupoBValor = r.getEsteGrupoBValor();
        this.tecReproAsist = r.getTecReproAsist();
        this.gemelar = r.getGemelar();
        this.numeroFetos = r.getNumeroFetos();

        this.insulina = r.getInsulina();
        this.insulinaTiempo = r.getInsulinaTiempo();
        this.dietaejercicio = r.getDietaejercicio();

    }

    public void iniciaGestacion() {
        this.setTiporegistro(RegistroPartoGestacion.TIPO_REGISTRO_PARTO);
        this.setDescripcion("2.-Gestación");
        this.setPlantilla_edior(RegistroPartoGestacion.PLANTILLLA_EDITOR_PAR_GESTACION);
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));

        this.edad = this.VAR_PARTO_GESTA_EDAD;
        this.edadGestacional = this.VAR_PARTO_GESTA_EGESTAIONAL;
        this.fechaUltimaRegla = this.VAR_PARTO_GESTA_FUR;
        this.fechaprobableParto = this.VAR_PARTO_GESTA_FPP;

        this.paridad = this.VAR_PARTO_GESTA_PARIDAD;

        this.grupoABO = this.VAR_PARTO_GESTA_GABO;
        this.IMC = this.VAR_PARTO_GESTA_IMC;
        this.habitoxTabaco = VAR_PARTO_GESTA_HABITOXTABACO;
        this.habitoxAlcohol = VAR_PARTO_GESTA_HABITOXALCOHOL;
        this.habitoxCanabis = VAR_PARTO_GESTA_HABITOXCANABIS;
        this.habitoxOtras = VAR_PARTO_GESTA_HABITOXOROS;

        this.patoMatNo = this.VAR_PARTO_GESTA_PAT_MAT_NO;
        this.patoMatTrombofilia = this.VAR_PARTO_GESTA_PAT_MAT_TROMBO;
        this.patoMatDiabetesGestional = this.VAR_PARTO_GESTA_PAT_MAT_DIABE;
        this.TVP = this.VAR_PARTO_GESTA_PAT_MAT_TVP;
        this.DMPG = this.VAR_PARTO_GESTA_PAT_MAT_DMPG;
        this.DMG = this.VAR_PARTO_GESTA_PAT_MAT_DMP;
        this.EII = this.VAR_PARTO_GESTA_PAT_MAT_EII;
        this.reumatogicas = this.VAR_PARTO_GESTA_PAT_MAT_REUMATOL;
        this.cancer = this.VAR_PARTO_GESTA_PAT_MAT_CANCER;
        this.IPuterinas_95 = this.VAR_PARTO_GESTA_PAT_MAT_IP_UTERINAS;
        this.AR = this.VAR_PARTO_GESTA_PAT_MAT_AR;
        this.HTA = this.VAR_PARTO_GESTA_PAT_MAT_HTA;
        this.APPE = this.VAR_PARTO_GESTA_PAT_MAT_APPE;
        this.epilepsia = this.VAR_PARTO_GESTA_PAT_MAT_EPILEPSIA;

        this.hipotiropre = this.VAR_PARTO_GESTA_PAT_MAT_HIPOTIPRE;
        this.hipotiropos = this.VAR_PARTO_GESTA_PAT_MAT_HIPOGESE;
        this.plaquetopenia = this.VAR_PARTO_GESTA_PAT_MAT_PLAQUETO;
        this.infeccion = this.VAR_PARTO_GESTA_PAT_MAT_INFECCGES;

        this.patoMatOtros = this.VAR_PARTO_GESTA_PAT_MAT_OTROS;

        this.alergia = this.VAR_PARTO_GESTA_ALERGIA;
        this.tratamientoEmbarazo = this.VAR_PARTO_GESTA_TRATAMIENTOEMBARAZO;

        this.sero1TRubeola = this.VAR_PARTO_GESTA_SERO1_RUBE;
        this.sero1Tsifilis = this.VAR_PARTO_GESTA_SERO1_SIFI;
        this.sero1TToxo = this.VAR_PARTO_GESTA_SERO1_TOXO;
        this.sero1TVHB = this.VAR_PARTO_GESTA_SERO1_VHB;
        this.sero1TVHC = this.VAR_PARTO_GESTA_SERO1_VHC;
        this.sero1TVIH = this.VAR_PARTO_GESTA_SERO1_VIH;

        this.sero3TRubeola = this.VAR_PARTO_GESTA_SERO3_RUBE;
        this.sero3Tsifilis = this.VAR_PARTO_GESTA_SERO3_SIFI;
        this.sero3TToxo = this.VAR_PARTO_GESTA_SERO3_TOXO;
        this.sero3TVHB = this.VAR_PARTO_GESTA_SERO3_VHB;
        this.sero3TVHC = this.VAR_PARTO_GESTA_SERO3_VHC;
        this.sero3TVIH = this.VAR_PARTO_GESTA_SERO3_VIH;

        this.esteGrupoBFecha = this.VAR_PARTO_GESTA_SGB_FECHA;
        this.esteGrupoBValor = this.VAR_PARTO_GESTA_SGB_VALOR;

        this.tecReproAsist = this.VAR_PARTO_GESTA_TECREPUASIST;
        this.gemelar = this.VAR_PARTO_GESTA_GEMELAR;
        this.numeroFetos = this.VAR_PARTO_GESTA_NUMEROFETOS;

        this.insulina = this.VAR_PARTO_GESTA_INSULINA;
        this.insulinaTiempo = this.VAR_PARTO_GESTA_INSULINATIEMPO;
        this.dietaejercicio = this.VAR_PARTO_GESTA_DIETAEJERCICIO;
    }

    public Variable getEdad() {
        return edad;
    }

    public Variable getVariableEdad() {
        return edad;
    }

    public String getEdadString() {
        return edad.getValor();
    }

    public void setEdad(Variable edad) {
        this.edad = edad;
    }

    public void setEdad(String valor) {
        this.edad.setValor(valor);
    }

    public void setEdad(int valor) {
        this.edad.setValor(Integer.toString(valor));
    }

    public Variable getEdadGestacional() {
        return edadGestacional;
    }

    public String getEdadGestacionalString() {
        return edadGestacional.getValor();
    }

    public Variable getVariableEdadGestacional() {
        return edadGestacional;
    }

    public void setEdadGestacional(Variable edadGestacional) {
        this.edadGestacional = edadGestacional;
    }

    public void setEdadGestacional(String valor) {
        this.edadGestacional.setValor(valor);
    }

    public Variable getFechaUltimaRegla() {
        return fechaUltimaRegla;
    }

    public LocalDate getFechaUltimaReglaDate() {
        LocalDate fecha = null;
        if (this.fechaUltimaRegla != null) {
            if (this.fechaUltimaRegla.getValor() != null && !this.fechaUltimaRegla.getValor().isEmpty()) {
                if (Utilidades.isNumero(this.fechaUltimaRegla.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(this.fechaUltimaRegla.getValor());
                }
            }
        }
        return fecha;
    }

    public Variable getVariableFechaUltimaRegla() {
        return fechaUltimaRegla;
    }

    public void setFechaUltimaRegla(Variable fechaUltimaRegla) {
        this.fechaUltimaRegla = fechaUltimaRegla;
    }

    public void setFechaUltimaRegla(String valor) {
        this.fechaUltimaRegla.setValor(valor);
    }

    public void setFechaUltimaRegla(LocalDate fecha) {
        this.fechaUltimaRegla.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getFechaprobableParto() {
        return fechaprobableParto;
    }

    public Variable getVariableFechaprobableParto() {
        return fechaprobableParto;
    }

    public LocalDate getFechaprobablePartoDate() {
        LocalDate fecha = null;
        if (this.fechaprobableParto != null) {
            if (this.fechaprobableParto.getValor() != null && !this.fechaprobableParto.getValor().isEmpty()) {
                if (Utilidades.isNumero(this.fechaprobableParto.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechaprobableParto.getValor()));
                }
            }
        }
        return fecha;
    }

    public void setFechaprobableParto(Variable fechaprobableParto) {
        this.fechaprobableParto = fechaprobableParto;
    }

    public void setFechaprobableParto(String valor) {
        this.fechaprobableParto.setValor(valor);
    }

    public void setFechaprobableParto(LocalDate fecha) {
        this.fechaprobableParto.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getParidad() {
        return paridad;
    }

    public String getParidadString() {
        return paridad.getValor();
    }

    public Variable getVariableParidad() {
        return paridad;
    }

    public void setParidad(Variable paridad) {
        this.paridad = paridad;
    }

    public void setParidad(String valor) {
        this.paridad.setValor(valor);
    }

    public Variable getGrupoABO() {
        return grupoABO;
    }

    public Variable getVariableGrupoABO() {
        return grupoABO;
    }

    public String getGrupoABOString() {
        return grupoABO.getValor();
    }

    public void setGrupoABO(Variable grupoABO) {
        this.grupoABO = grupoABO;
    }

    public void setGrupoABO(String valor) {
        this.grupoABO.setValor(valor);
    }

    public Variable getIMC() {
        return IMC;
    }

    public Variable getVariableIMC() {
        return IMC;
    }

    public String getIMCString() {
        return IMC.getValor();
    }

    public void setIMC(Variable imc) {
        IMC = imc;
    }

    public void setIMC(String valor) {
        IMC.setValor(valor);
    }

    public Variable getHabitoxTabaco() {
        return habitoxTabaco;
    }

    public Variable getVariableHabitoxTabaco() {
        return habitoxTabaco;
    }

    public Boolean getHabitoxTabacoBoolean() {
        if (habitoxTabaco != null && !habitoxTabaco.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHabitoxTabaco(Variable habitoxTabaco) {
        this.habitoxTabaco = habitoxTabaco;
    }

    public void setHabitoxTabaco(Boolean valor) {
        if (valor == true) {
            this.habitoxTabaco.setValor("Tabaco");
        } else {
            this.habitoxTabaco.setValor("");
        }
    }

    public Variable getHabitoxAlcohol() {
        return habitoxAlcohol;
    }

    public Variable getVariableHabitoxAlcohol() {
        return habitoxAlcohol;
    }

    public Boolean getHabitoxAlcoholBoolean() {
        if (habitoxAlcohol != null && !habitoxAlcohol.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHabitoxAlcohol(Variable habitoxAlcohol) {
        this.habitoxAlcohol = habitoxAlcohol;
    }

    public void setHabitoxAlcohol(Boolean valor) {
        if (valor == true) {
            this.habitoxAlcohol.setValor("Alcohol");
        } else {
            this.habitoxAlcohol.setValor("");
        }
    }

    public Variable getHabitoxCanabis() {
        return habitoxCanabis;
    }

    public Variable getVariableHabitoxCanabis() {
        return habitoxCanabis;
    }

    public Boolean getHabitoxCanabisBoolean() {
        if (habitoxCanabis != null && !habitoxCanabis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHabitoxCanabis(Variable habitoxCanabis) {
        this.habitoxCanabis = habitoxCanabis;
    }

    public void setHabitoxCanabis(Boolean valor) {
        if (valor == true) {
            this.habitoxCanabis.setValor("Canabis");
        } else {
            this.habitoxCanabis.setValor("");
        }
    }

    public Variable getHabitoxOtras() {
        return habitoxOtras;
    }

    public Variable getVariableHabitoxOtras() {
        return habitoxOtras;
    }

    public String getHabitoxOtrasString() {
        return habitoxOtras.getValor();
    }

    public void setHabitoxOtras(Variable habitoxOtras) {
        this.habitoxOtras = habitoxOtras;
    }

    public void setHabitoxOtras(String valor) {
        this.habitoxOtras.setValor(valor);
    }

    public Variable getPatoMatNo() {
        return patoMatNo;
    }

    public Variable getVariablePatoMatNo() {
        return patoMatNo;
    }

    public String getPatoMatNoString() {
        return patoMatNo.getValor();
    }

    public Boolean getPatoMatNoBoolean() {
        if (patoMatNo != null && patoMatNo.getValor() != null && !patoMatNo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoMatNo(Variable patoMatNo) {
        this.patoMatNo = patoMatNo;
    }

    public void setPatoMatNo(String valor) {
        this.patoMatNo.setValor(valor);
    }

    public void setPatoMatNo(Boolean valor) {
        if (valor == true) {
            this.patoMatNo.setValor("No patología madre");
        } else {
            this.patoMatNo.setValor(null);
        }
    }

    public Variable getPatoMatTrombofilia() {
        return patoMatTrombofilia;
    }

    public Variable getVariablePatoMatTrombofilia() {
        return patoMatTrombofilia;
    }

    public Boolean getPatoMatTrombofiliaBoolean() {
        if (patoMatTrombofilia != null && patoMatTrombofilia.getValor() != null
                && !patoMatTrombofilia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String getPatoMatTrombofiliaString() {
        return patoMatTrombofilia.getValor();
    }

    public void setPatoMatTrombofilia(Variable patoMatTrombofilia) {
        this.patoMatTrombofilia = patoMatTrombofilia;
    }

    public void setPatoMatTrombofilia(String valor) {
        this.patoMatTrombofilia.setValor(valor);
    }

    public void setPatoMatTrombofilia(Boolean valor) {
        if (valor == true) {
            this.patoMatTrombofilia.setValor("Trombofilia");
        } else {
            this.patoMatTrombofilia.setValor(null);
        }
    }

    public Variable getPatoMatDiabetesGestional() {
        return patoMatDiabetesGestional;
    }

    public Variable getVariablePatoMatDiabetesGestional() {
        return patoMatDiabetesGestional;
    }

    public Boolean getPatoMatDiabetesGestionalBoolean() {
        if (patoMatDiabetesGestional != null && patoMatDiabetesGestional.getValor() != null
                && !patoMatDiabetesGestional.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatoMatDiabetesGestional(Variable patoMatDiabetesGestional) {
        this.patoMatDiabetesGestional = patoMatDiabetesGestional;
    }

    public String getVariablePatoMatDiabetesGestionalString() {
        return patoMatDiabetesGestional.getValor();
    }

    public void setPatoMatDiabetesGestional(String valor) {
        this.patoMatDiabetesGestional.setValor(valor);
    }

    public void setPatoMatDiabetesGestional(Boolean valor) {
        if (valor == true) {
            this.patoMatDiabetesGestional.setValor("Diabetes gestacional");
        } else {
            this.patoMatDiabetesGestional.setValor(null);
        }
    }

    public Variable getTVP() {
        return TVP;
    }

    public Variable getVariableTVP() {
        return TVP;
    }

    public Boolean getTVPBoolean() {
        if (TVP != null && TVP.getValor() != null && !TVP.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTVP(Variable tVP) {
        TVP = tVP;
    }

    public void setTVP(Boolean valor) {
        if (valor == true) {
            TVP.setValor("TVP");
        } else {
            TVP.setValor("");
        }
    }

    public Variable getDMPG() {
        return DMPG;
    }

    public Variable getVariableDMPG() {
        return DMPG;
    }

    public Boolean getDMPGBooelan() {
        if (DMPG != null && DMPG.getValor() != null && !DMPG.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setDMPG(Variable dMPG) {
        DMPG = dMPG;
    }

    public void setDMPG(Boolean valor) {
        if (valor == true) {
            DMPG.setValor("DMPG");
        } else {
            DMPG.setValor("");
        }
    }

    public Variable getDMG() {
        return DMG;
    }

    public Variable getVariableDMG() {
        return DMG;
    }

    public Boolean getDMGBooelan() {
        if (DMG != null && DMG.getValor() != null && !DMG.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setDMG(Variable dMG) {
        DMG = dMG;
    }

    public void setDMG(Boolean valor) {
        if (valor == true) {
            DMG.setValor("DMG");
        } else {
            DMG.setValor("");
        }
    }

    public Variable getEII() {
        return EII;
    }

    public Variable getVariableEII() {
        return EII;
    }

    public Boolean getEIIBoolean() {
        if (EII != null && EII.getValor() != null && !EII.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEII(Variable eII) {
        EII = eII;
    }

    public void setEII(Boolean valor) {
        if (valor == true) {
            EII.setValor("EII");
        } else {
            EII.setValor("");
        }
    }

    public Variable getReumatogicas() {
        return reumatogicas;
    }

    public Variable getVariableReumatogicas() {
        return reumatogicas;
    }

    public Boolean getReumatogicasBoolean() {
        if (reumatogicas != null && reumatogicas.getValor() != null && !reumatogicas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setReumatogicas(Variable reumatogicas) {
        this.reumatogicas = reumatogicas;
    }

    public void setReumatogicas(Boolean valor) {
        if (valor == true) {
            reumatogicas.setValor("reumatogicas");
        } else {
            reumatogicas.setValor("");
        }
    }

    public Variable getCancer() {
        return cancer;
    }

    public Variable getVariableCancer() {
        return cancer;
    }

    public Boolean getCancerBoolean() {
        if (cancer != null && cancer.getValor() != null && !cancer.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCancer(Variable cancer) {
        this.cancer = cancer;
    }

    public void setCancer(Boolean valor) {
        if (valor == true) {
            cancer.setValor("cancer");
        } else {
            cancer.setValor("");
        }
    }

    public Variable getIPuterinas_95() {
        return IPuterinas_95;
    }

    public Variable getVariableIPuterinas_95() {
        return IPuterinas_95;
    }

    public Boolean getIPuterinas_95Boolean() {
        if (IPuterinas_95 != null && IPuterinas_95.getValor() != null && !IPuterinas_95.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIPuterinas_95(Variable iPuterinas_95) {
        IPuterinas_95 = iPuterinas_95;
    }

    public void setIPuterinas_95(Boolean valor) {
        if (valor == true) {
            IPuterinas_95.setValor("IPuterinas>95");
        } else {
            IPuterinas_95.setValor("");
        }
    }

    public Variable getAR() {
        return AR;
    }

    public Variable getVariableAR() {
        return AR;
    }

    public Boolean getARBoolean() {
        if (AR != null && AR.getValor() != null && !AR.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAR(Variable aR) {
        AR = aR;
    }

    public void setAR(Boolean valor) {
        if (valor == true) {
            AR.setValor("AR>95");
        } else {
            AR.setValor("");
        }
    }

    public Variable getHTA() {
        return HTA;
    }

    public Variable getVariableHTA() {
        return HTA;
    }

    public Boolean getHTABoolean() {
        if (HTA != null && HTA.getValor() != null && !HTA.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHTA(Variable hTA) {
        HTA = hTA;
    }

    public void setHTA(Boolean valor) {
        if (valor == true) {
            HTA.setValor("HTA");
        } else {
            HTA.setValor("");
        }
    }

    public Variable getAPPE() {
        return APPE;
    }

    public Variable getVariableAPPE() {
        return APPE;
    }

    public Boolean getAPPEBoolean() {
        if (APPE != null && APPE.getValor() != null && !APPE.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAPPE(Variable aPPE) {
        APPE = aPPE;
    }

    public void setAPPE(Boolean valor) {
        if (valor == true) {
            APPE.setValor("APPE");
        } else {
            APPE.setValor("");
        }
    }

    public Variable getEpilepsia() {
        return epilepsia;
    }

    public Variable getVariableEpilepsia() {
        return epilepsia;
    }

    public Boolean getEpilepsiaBoolean() {
        if (epilepsia != null && epilepsia.getValor() != null && !epilepsia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEpilepsia(Variable epilepsia) {
        epilepsia = epilepsia;
    }

    public void setEpilepsia(Boolean valor) {
        if (valor == true) {
            epilepsia.setValor("Epilepsia");
        } else {
            epilepsia.setValor("");
        }
    }

    public Variable getHipotiropre() {
        return hipotiropre;
    }

    public Variable getVariableHipotiropre() {
        return hipotiropre;
    }

    public Boolean getHipotiropreBoolean() {
        if (hipotiropre != null && hipotiropre.getValor() != null && !hipotiropre.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHipotiropre(Variable hipotiropre) {
        this.hipotiropre = hipotiropre;
    }

    public void setHipotiropre(Boolean valor) {
        if (valor == true) {
            hipotiropre.setValor("Hipotiroidismo pregestacional");
        } else {
            hipotiropre.setValor("");
        }
    }

    public Variable getHipotiropos() {
        return hipotiropos;
    }

    public Variable getVariableHipotiropos() {
        return hipotiropos;
    }

    public Boolean getHipotiroposBoolean() {
        if (hipotiropos != null && hipotiropos.getValor() != null && !hipotiropos.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHipotiropos(Variable hipotiropos) {
        this.hipotiropos = hipotiropos;
    }

    public void setHipotiropos(Boolean valor) {
        if (valor == true) {
            hipotiropos.setValor("Hipotiroidismo gestacional");
        } else {
            hipotiropos.setValor("");
        }
    }

    public Variable getPlaquetopenia() {
        return plaquetopenia;
    }

    public Variable getVariablePlaquetopenia() {
        return plaquetopenia;
    }

    public Boolean getPlaquetopeniaBoolean() {
        if (plaquetopenia != null && plaquetopenia.getValor() != null && !plaquetopenia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPlaquetopenia(Variable plaquetopenia) {
        this.plaquetopenia = plaquetopenia;
    }

    public void setPlaquetopenia(Boolean valor) {
        if (valor == true) {
            plaquetopenia.setValor("Plaquetopenia");
        } else {
            plaquetopenia.setValor("");
        }
    }

    public Variable getInfeccion() {
        return infeccion;
    }

    public Variable getVariableInfeccion() {
        return infeccion;
    }

    public Boolean getInfeccionboolean() {
        if (infeccion != null && infeccion.getValor() != null && !infeccion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInfeccion(Variable infeccion) {
        this.infeccion = infeccion;
    }

    public void setInfeccion(Boolean valor) {
        if (valor == true) {
            infeccion.setValor("Infección gestacional");
        } else {
            infeccion.setValor("");
        }
    }

    public Variable getPatoMatOtros() {
        return patoMatOtros;
    }

    public Variable getVariablePatoMatOtros() {
        return patoMatOtros;
    }

    public String getPatoMatOtrosString() {
        return patoMatOtros.getValor();
    }

    public void setPatoMatOtros(Variable patoMatOtros) {
        this.patoMatOtros = patoMatOtros;
    }

    public void setPatoMatOtros(String valor) {
        this.patoMatOtros.setValor(valor);
    }

    public Variable getAlergia() {
        return alergia;
    }

    public Variable getVariableAlergia() {
        return alergia;
    }

    public String getAlergiaString() {
        return alergia.getValor();
    }

    public void setAlergia(Variable alergia) {
        this.alergia = alergia;
    }

    public void setAlergia(String valor) {
        this.alergia.setValor(valor);
    }

    public Variable getTratamientoEmbarazo() {
        return tratamientoEmbarazo;
    }

    public Variable getVariableTratamientoEmbarazo() {
        return tratamientoEmbarazo;
    }

    public String getTratamientoEmbarazoString() {
        return tratamientoEmbarazo.getValor();
    }

    public void setTratamientoEmbarazo(Variable tratamientoEmbarazo) {
        this.tratamientoEmbarazo = tratamientoEmbarazo;
    }

    public void setTratamientoEmbarazo(String valor) {
        this.tratamientoEmbarazo.setValor(valor);
    }

    public Variable getSero1TRubeola() {
        return sero1TRubeola;
    }

    public Variable getVariableSero1TRubeola() {
        return sero1TRubeola;
    }

    public String getSero1TRubeolaString() {
        return sero1TRubeola.getValor();
    }

    public void setSero1TRubeola(Variable sero1tRubeola) {
        sero1TRubeola = sero1tRubeola;
    }

    public void setSero1TRubeola(String valor) {
        sero1TRubeola.setValor(valor);
    }

    public Variable getSero1Tsifilis() {
        return sero1Tsifilis;
    }

    public Variable getVariableSero1Tsifilis() {
        return sero1Tsifilis;
    }

    public String getSero1TsifilisString() {
        return sero1Tsifilis.getValor();
    }

    public void setSero1Tsifilis(Variable sero1Tsifilis) {
        this.sero1Tsifilis = sero1Tsifilis;
    }

    public void setSero1Tsifilis(String valor) {
        this.sero1Tsifilis.setValor(valor);
    }

    public Variable getSero1TToxo() {
        return sero1TToxo;
    }

    public Variable getVariableSero1TToxo() {
        return sero1TToxo;
    }

    public String getSero1TToxoString() {
        return sero1TToxo.getValor();
    }

    public void setSero1TToxo(Variable sero1tToxo) {
        sero1TToxo = sero1tToxo;
    }

    public void setSero1TToxo(String valor) {
        sero1TToxo.setValor(valor);
    }

    public Variable getSero1TVHB() {
        return sero1TVHB;
    }

    public Variable getVariableSero1TVHB() {
        return sero1TVHB;
    }

    public String getSero1TVHBString() {
        return sero1TVHB.getValor();
    }

    public void setSero1TVHB(Variable sero1tvhb) {
        sero1TVHB = sero1tvhb;
    }

    public void setSero1TVHB(String valor) {
        sero1TVHB.setValor(valor);
    }

    public Variable getSero1TVHC() {
        return sero1TVHC;
    }

    public Variable getVariableSero1TVHC() {
        return sero1TVHC;
    }

    public String getSero1TVHCString() {
        return sero1TVHC.getValor();
    }

    public void setSero1TVHC(Variable sero1tvhc) {
        sero1TVHC = sero1tvhc;
    }

    public void setSero1TVHC(String valor) {
        sero1TVHC.setValor(valor);
    }

    public Variable getSero1TVIH() {
        return sero1TVIH;
    }

    public Variable getVariableSero1TVIH() {
        return sero1TVIH;
    }

    public String getSero1TVIHString() {
        return sero1TVIH.getValor();
    }

    public void setSero1TVIH(Variable sero1tvih) {
        sero1TVIH = sero1tvih;
    }

    public void setSero1TVIH(String valor) {
        sero1TVIH.setValor(valor);
    }

    public Variable getSero3TRubeola() {
        return sero3TRubeola;
    }

    public Variable getVariableSero3TRubeola() {
        return sero3TRubeola;
    }

    public String getSero3TRubeolaString() {
        return sero3TRubeola.getValor();
    }

    public void setSero3TRubeola(Variable sero3tRubeola) {
        sero3TRubeola = sero3tRubeola;
    }

    public void setSero3TRubeola(String valor) {
        sero3TRubeola.setValor(valor);
    }

    public Variable getSero3Tsifilis() {
        return sero3Tsifilis;
    }

    public Variable getVariableSero3Tsifilis() {
        return sero3Tsifilis;
    }

    public String getSero3TsifilisString() {
        return sero3Tsifilis.getValor();
    }

    public void setSero3Tsifilis(Variable variable) {
        this.sero3Tsifilis = variable;
    }

    public void setSero3Tsifilis(String valor) {
        this.sero3Tsifilis.setValor(valor);
    }

    public Variable getSero3TToxo() {
        return sero3TToxo;
    }

    public Variable getVariableSero3TToxo() {
        return sero3TToxo;
    }

    public String getSero3TToxoString() {
        return sero3TToxo.getValor();
    }

    public void setSero3TToxo(Variable sero3tToxo) {
        sero3TToxo = sero3tToxo;
    }

    public void setSero3TToxo(String valor) {
        sero3TToxo.setValor(valor);
    }

    public Variable getSero3TVHB() {
        return sero3TVHB;
    }

    public Variable getVariableSero3TVHB() {
        return sero3TVHB;
    }

    public String getSero3TVHBString() {
        return sero3TVHB.getValor();
    }

    public void setSero3TVHB(Variable sero3tvhb) {
        sero3TVHB = sero3tvhb;
    }

    public void setSero3TVHB(String valor) {
        sero3TVHB.setValor(valor);
    }

    public Variable getSero3TVHC() {
        return sero3TVHC;
    }

    public Variable getVariableSero3TVHC() {
        return sero3TVHC;
    }

    public String getSero3TVHCString() {
        return sero3TVHC.getValor();
    }

    public void setSero3TVHC(Variable sero3tvhc) {
        sero3TVHC = sero3tvhc;
    }

    public void setSero3TVHC(String valor) {
        sero3TVHC.setValor(valor);
    }

    public Variable getSero3TVIH() {
        return sero3TVIH;
    }

    public Variable getVariableSero3TVIH() {
        return sero3TVIH;
    }

    public String getSero3TVIHString() {
        return sero3TVIH.getValor();
    }

    public void setSero3TVIH(Variable sero3tvih) {
        sero3TVIH = sero3tvih;
    }

    public void setSero3TVIH(String valor) {
        sero3TVIH.setValor(valor);
    }

    public Variable getEsteGrupoBFecha() {
        return esteGrupoBFecha;
    }

    public Variable getVariableEsteGrupoBFecha() {
        return esteGrupoBFecha;
    }

    public LocalDate getEsteGrupoBFechaDate() {
        LocalDate fecha = null;
        if (this.esteGrupoBFecha != null) {
            if (this.esteGrupoBFecha.getValor() != null && !this.esteGrupoBFecha.getValor().isEmpty()) {
                if (Utilidades.isNumero(this.esteGrupoBFecha.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.esteGrupoBFecha.getValor()));
                }
            }
        }
        return fecha;
    }

    public void setEsteGrupoBFecha(Variable esteGrupoBFecha) {
        this.esteGrupoBFecha = esteGrupoBFecha;
    }

    public void setEsteGrupoBFecha(String valor) {
        this.esteGrupoBFecha.setValor(valor);
    }

    public void setEsteGrupoBFecha(LocalDate fecha) {
        this.esteGrupoBFecha.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getEsteGrupoBValor() {
        return esteGrupoBValor;
    }

    public Variable getVariableEsteGrupoBValor() {
        return esteGrupoBValor;
    }

    public String getEsteGrupoBValorString() {
        return esteGrupoBValor.getValor();
    }

    public void setEsteGrupoBValor(Variable esteGrupoBValor) {
        this.esteGrupoBValor = esteGrupoBValor;
    }

    public void setEsteGrupoBValor(String valor) {
        this.esteGrupoBValor.setValor(valor);
    }

    public Variable getTecReproAsist() {
        return tecReproAsist;
    }

    public Variable getVariableTecReproAsist() {
        return tecReproAsist;
    }

    public String getTecReproAsistString() {
        if (tecReproAsist != null) {
            return tecReproAsist.getValor();
        } else {
            return "";
        }
    }

    public void setTecReproAsist(Variable tecReproAsist) {
        this.tecReproAsist = tecReproAsist;
    }

    public void setTecReproAsist(String valor) {
        this.tecReproAsist.setValor(valor);
    }

    public Variable getGemelar() {
        return gemelar;
    }

    public Variable getVariableGemelar() {
        return gemelar;
    }

    public String getGemelarString() {
        return gemelar.getValor();
    }

    public void setGemelar(Variable gemelar) {
        this.gemelar = gemelar;
    }

    public void setGemelar(String valor) {
        this.gemelar.setValor(valor);
    }

    public Variable getNumeroFetos() {
        return numeroFetos;
    }

    public Variable getVariableNumeroFetos() {
        return numeroFetos;
    }

    public String getNumeroFetosString() {
        return numeroFetos.getValor();
    }

    public void setNumeroFetos(Variable numeroFetos) {
        this.numeroFetos = numeroFetos;
    }

    public void setNumeroFetos(String valor) {
        this.numeroFetos.setValor(valor);
    }

    public Variable getInsulina() {
        return insulina;
    }

    public Variable getVariableInsulina() {
        return insulina;
    }

    public Boolean getInsulinaBoolean() {
        if (insulina != null && !insulina.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInsulina(Variable insulina) {
        this.insulina = insulina;
    }

    public void setInsulina(Boolean valor) {
        if (valor == true) {
            this.insulina.setValor("Insulina");
        } else {
            this.insulina.setValor("");
        }
    }

    public Variable getInsulinaTiempo() {
        return insulinaTiempo;
    }

    public Variable getVariableInsulinaTiempo() {
        return insulinaTiempo;
    }

    public String getInsulinaTiempoString() {
        return insulinaTiempo.getValor();
    }

    public void setInsulinaTiempo(Variable insulinaTiempo) {
        this.insulinaTiempo = insulinaTiempo;
    }

    public void setInsulinaTiempo(String valor) {
        this.insulinaTiempo.setValor(valor);
    }

    public Variable getDietaejercicio() {
        return dietaejercicio;
    }

    public Variable getVariableDietaejercicio() {
        return dietaejercicio;
    }

    public Boolean getDietaejercicioBoolean() {
        if (dietaejercicio != null && !dietaejercicio.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setDietaejercicio(Variable dietaejercicio) {
        this.dietaejercicio = dietaejercicio;
    }

    public void setDietaejercicio(Boolean valor) {
        if (valor == true) {
            this.dietaejercicio.setValor("Dieta y ejercicio");
        } else {
            this.dietaejercicio.setValor("");
        }
    }

    public String getPatologiasMadre() {
        String texto = "";
        if (patoMatNo != null) {
            texto = texto.concat(patoMatNo.getValor());
        }

        if (patoMatTrombofilia != null) {

            texto = texto.concat(" ");
            texto = texto.concat(patoMatTrombofilia.getValor());
        }
        if (patoMatDiabetesGestional != null) {

            texto = texto.concat(" ");
            texto = texto.concat(patoMatDiabetesGestional.getValor());
        }
        if (TVP != null) {

            texto = texto.concat(" ");
            texto = texto.concat(TVP.getValor());
        }
        if (DMPG != null) {

            texto = texto.concat("");
            texto = texto.concat(DMPG.getValor());
        }
        if (DMG != null) {

            texto = texto.concat("");
            texto = texto.concat(DMG.getValor());
        }
        if (insulina != null) {
            texto = texto.concat(" " + insulina.getValor());
        }
        if (insulinaTiempo != null) {
            texto = texto.concat(" " + insulinaTiempo.getValor());
        }
        if (dietaejercicio != null) {
            texto = texto.concat(" " + dietaejercicio.getValor());
        }

        if (EII != null) {
            texto = texto.concat(" ");
            texto = texto.concat(EII.getValor());
        }
        if (reumatogicas != null) {
            texto = texto.concat(" ");
            texto = texto.concat(reumatogicas.getValor());
        }
        if (cancer != null) {

            texto = texto.concat(" ");
            texto = texto.concat(cancer.getValor());
        }
        if (IPuterinas_95 != null) {
            texto = texto.concat(" ");
            texto = texto.concat(IPuterinas_95.getValor());
        }
        if (AR != null) {

            texto = texto.concat(" ");
            texto = texto.concat(AR.getValor());
        }
        if (HTA != null) {
            texto = texto.concat(" ");
            texto = texto.concat(HTA.getValor());
        }
        if (APPE != null) {
            texto = texto.concat(" ");
            texto = texto.concat(APPE.getValor());
        }
        if (epilepsia != null) {
            texto = texto.concat(" ");
            texto = texto.concat(epilepsia.getValor());
        }
        if (patoMatOtros != null) {
            texto = texto.concat(" ");
            texto = texto.concat(patoMatOtros.getValor());
        }
        if (hipotiropos != null) {
            texto = texto.concat(" ");
            texto = texto.concat(hipotiropos.getValor());
        }
        if (hipotiropre != null) {
            texto = texto.concat(" ");
            texto = texto.concat(hipotiropre.getValor());
        }
        if (plaquetopenia != null) {
            texto = texto.concat(" ");
            texto = texto.concat(plaquetopenia.getValor());
        }
        if (infeccion != null) {
            texto = texto.concat(" ");
            texto = texto.concat(infeccion.getValor());
        }

        return texto;
    }

    public static Long getPlantilllaEditorParGestacion() {
        return PLANTILLLA_EDITOR_PAR_GESTACION;
    }

    public static Long getTipoRegistroParto() {
        return TIPO_REGISTRO_PARTO;
    }

    public Variable getVAR_PARTO_GESTA_EDAD() {
        return VAR_PARTO_GESTA_EDAD;
    }

    public Variable getVAR_PARTO_GESTA_EGESTAIONAL() {
        return VAR_PARTO_GESTA_EGESTAIONAL;
    }

    public Variable getVAR_PARTO_GESTA_FUR() {
        return VAR_PARTO_GESTA_FUR;
    }

    public Variable getVAR_PARTO_GESTA_FPP() {
        return VAR_PARTO_GESTA_FPP;
    }

    public Variable getVAR_PARTO_GESTA_PARIDAD() {
        return VAR_PARTO_GESTA_PARIDAD;
    }

    public Variable getVAR_PARTO_GESTA_GABO() {
        return VAR_PARTO_GESTA_GABO;
    }

    public Variable getVAR_PARTO_GESTA_IMC() {
        return VAR_PARTO_GESTA_IMC;
    }

    public Variable getVAR_PARTO_GESTA_HABITOXTABACO() {
        return VAR_PARTO_GESTA_HABITOXTABACO;
    }

    public Variable getVAR_PARTO_GESTA_HABITOXALCOHOL() {
        return VAR_PARTO_GESTA_HABITOXALCOHOL;
    }

    public Variable getVAR_PARTO_GESTA_HABITOXCANABIS() {
        return VAR_PARTO_GESTA_HABITOXCANABIS;
    }

    public Variable getVAR_PARTO_GESTA_HABITOXOROS() {
        return VAR_PARTO_GESTA_HABITOXOROS;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_NO() {
        return VAR_PARTO_GESTA_PAT_MAT_NO;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_TROMBO() {
        return VAR_PARTO_GESTA_PAT_MAT_TROMBO;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_DIABE() {
        return VAR_PARTO_GESTA_PAT_MAT_DIABE;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_TVP() {
        return VAR_PARTO_GESTA_PAT_MAT_TVP;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_DMPG() {
        return VAR_PARTO_GESTA_PAT_MAT_DMPG;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_DMP() {
        return VAR_PARTO_GESTA_PAT_MAT_DMP;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_EII() {
        return VAR_PARTO_GESTA_PAT_MAT_EII;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_REUMATOL() {
        return VAR_PARTO_GESTA_PAT_MAT_REUMATOL;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_CANCER() {
        return VAR_PARTO_GESTA_PAT_MAT_CANCER;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_IP_UTERINAS() {
        return VAR_PARTO_GESTA_PAT_MAT_IP_UTERINAS;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_AR() {
        return VAR_PARTO_GESTA_PAT_MAT_AR;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_HTA() {
        return VAR_PARTO_GESTA_PAT_MAT_HTA;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_APPE() {
        return VAR_PARTO_GESTA_PAT_MAT_APPE;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_EPILEPSIA() {
        return VAR_PARTO_GESTA_PAT_MAT_EPILEPSIA;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_HIPOTIPRE() {
        return VAR_PARTO_GESTA_PAT_MAT_HIPOTIPRE;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_HIPOGESE() {
        return VAR_PARTO_GESTA_PAT_MAT_HIPOGESE;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_PLAQUETO() {
        return VAR_PARTO_GESTA_PAT_MAT_PLAQUETO;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_INFECCGES() {
        return VAR_PARTO_GESTA_PAT_MAT_INFECCGES;
    }

    public Variable getVAR_PARTO_GESTA_PAT_MAT_OTROS() {
        return VAR_PARTO_GESTA_PAT_MAT_OTROS;
    }

    public Variable getVAR_PARTO_GESTA_ALERGIA() {
        return VAR_PARTO_GESTA_ALERGIA;
    }

    public Variable getVAR_PARTO_GESTA_SERO1_RUBE() {
        return VAR_PARTO_GESTA_SERO1_RUBE;
    }

    public Variable getVAR_PARTO_GESTA_SERO1_SIFI() {
        return VAR_PARTO_GESTA_SERO1_SIFI;
    }

    public Variable getVAR_PARTO_GESTA_SERO1_TOXO() {
        return VAR_PARTO_GESTA_SERO1_TOXO;
    }

    public Variable getVAR_PARTO_GESTA_SERO1_VHB() {
        return VAR_PARTO_GESTA_SERO1_VHB;
    }

    public Variable getVAR_PARTO_GESTA_SERO1_VHC() {
        return VAR_PARTO_GESTA_SERO1_VHC;
    }

    public Variable getVAR_PARTO_GESTA_SERO1_VIH() {
        return VAR_PARTO_GESTA_SERO1_VIH;
    }

    public Variable getVAR_PARTO_GESTA_SERO3_RUBE() {
        return VAR_PARTO_GESTA_SERO3_RUBE;
    }

    public Variable getVAR_PARTO_GESTA_SERO3_SIFI() {
        return VAR_PARTO_GESTA_SERO3_SIFI;
    }

    public Variable getVAR_PARTO_GESTA_SERO3_TOXO() {
        return VAR_PARTO_GESTA_SERO3_TOXO;
    }

    public Variable getVAR_PARTO_GESTA_SERO3_VHB() {
        return VAR_PARTO_GESTA_SERO3_VHB;
    }

    public Variable getVAR_PARTO_GESTA_SERO3_VHC() {
        return VAR_PARTO_GESTA_SERO3_VHC;
    }

    public Variable getVAR_PARTO_GESTA_SERO3_VIH() {
        return VAR_PARTO_GESTA_SERO3_VIH;
    }

    public Variable getVAR_PARTO_GESTA_SGB_FECHA() {
        return VAR_PARTO_GESTA_SGB_FECHA;
    }

    public Variable getVAR_PARTO_GESTA_SGB_VALOR() {
        return VAR_PARTO_GESTA_SGB_VALOR;
    }

    public Variable getVAR_PARTO_GESTA_TECREPUASIST() {
        return VAR_PARTO_GESTA_TECREPUASIST;
    }

    public Variable getVAR_PARTO_GESTA_GEMELAR() {
        return VAR_PARTO_GESTA_GEMELAR;
    }

    public Variable getVAR_PARTO_GESTA_NUMEROFETOS() {
        return VAR_PARTO_GESTA_NUMEROFETOS;
    }

    public Variable getVAR_PARTO_GESTA_INSULINA() {
        return VAR_PARTO_GESTA_INSULINA;
    }

    public Variable getVAR_PARTO_GESTA_DIETAEJERCICIO() {
        return VAR_PARTO_GESTA_DIETAEJERCICIO;
    }

    public Variable getVAR_PARTO_GESTA_INSULINATIEMPO() {
        return VAR_PARTO_GESTA_INSULINATIEMPO;
    }

}

/*
 * 
 * Select * from ( SELECT 1, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='SNM3' and
 * code='397669002' UNION SELECT 2,code,codesystem,id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='SNM3' and
 * code='57036006' UNION SELECT 3,code,codesystem,id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='SNM3' and
 * code='21840007' UNION SELECT 4, code,codesystem,id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='SNM3' and
 * code='161714006' UNION SELECT 5,code,codesystem,id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='SNM3' and
 * code='364325004' UNION SELECT 6,code,codesystem,id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='LN' and
 * code='882-1'
 * 
 * UNION SELECT 7,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994579' UNION
 * SELECT 8,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994580' UNION
 * SELECT 9,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='11687002' UNION
 * SELECT 10,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994581' UNION
 * SELECT 11,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='106190000' UNION
 * SELECT 11,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994582' UNION
 * SELECT 12,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994583' UNION
 * SELECT 13,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994598' UNION
 * SELECT 14,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994584' UNION
 * SELECT 15,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994585' UNION
 * SELECT 16,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994586' UNION
 * SELECT 18,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994587' UNION
 * SELECT 19,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994588' UNION
 * SELECT 20,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994589' UNION
 * SELECT 21,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994590' UNION
 * SELECT 22,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994591'
 * 
 * UNION SELECT 23,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994592' UNION
 * SELECT 24,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994593' UNION
 * SELECT 25,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994594' UNION
 * SELECT 26,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994595' UNION
 * SELECT 27,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994596' UNION
 * SELECT 28,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994597' UNION
 * SELECT 29,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994599' UNION
 * SELECT 30,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13994600'
 * 
 * UNION SELECT 30,code,codesystem,id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='65147003'
 * 
 * 
 * ) order by 1 ;
 * 
 * 
 */
