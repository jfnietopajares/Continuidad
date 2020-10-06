package es.sacyl.hnss.entity;

public class RegistroPartoEvolucion extends Registro {

    private Variable numeroOrdenParto;
    private Variable temperatura;
    private Variable tas;
    private Variable tad;
    private Variable fc;

    private Variable fiebreMaterna;
    private Variable registroCaridiotocografico;
    private Variable interpretacioRC;
    private Variable tipoRC;
    private Variable phFetal;
    private Variable phFetalValor;

    // vaginal
    private Variable horasDilatacion;
    private Variable horasExpulsivo;
    private Variable partoTipo;

    private Variable vaginalEutocico;
    private Variable vaginalVentosa;
    private Variable vaginalForceps;
    private Variable vaginalEspatulas;
    private Variable instrumental;

    private Variable distociaHombros;
    private Variable maniobraMcRoberts;
    private Variable maniobraIInivel;
    private Variable maniobraIIInivel;

    private Variable episiotomia;

    private Variable cesarea;
    private Variable cesareaIndicacion;
    private Variable cesareaObservaciones;

    private Variable expulsivoSupino;
    private Variable expulsivoCuadrupedia;
    private Variable expulsivoLateral;
    private Variable expulsivoBipedestacion;
    private Variable expulsivoSentada;
    private Variable expulsivoLitotomía;
    private Variable expulsivoApoyoPlanar;

    // alumbramiento
    private Variable tipoAlumbramiento;
    private Variable placenta;
    private Variable membranasFetales;
    private Variable cordonPinza;
    private Variable cordonVasos;
    private Variable cordonInsercion;
    private Variable cordonDonacion;
    private Variable hemorragiaCantidad;
    private Variable hemorragiaSingos;
    private Variable hemorragiaMareo;
    private Variable hemorragiaHiptension;
    private Variable hemorragiaPerdidaCono;
    private Variable hemorragiaTaquicardia;
    private Variable hemorragiaTaquiaPnea;
    // private Variable hemorragiaTaquipena;
    private Variable tratoTrenmdelemburg;
    private Variable tratoSueroterapia;
    private Variable tratoOxitocina;
    private Variable tratoMisoprostol;
    private Variable tratoMethergin;
    private Variable tratohemabate;
    private Variable tratoBalonBakri;
    private Variable tratoBelinche;
    private Variable tratoHayman;
    private Variable tratoLigadura;
    private Variable tratoHTobsterica;
    private Variable tratoObservaciones;

    private Variable hemorragiaTratamiento;

    public final static Long PLANTILLLA_EDITOR_PAR_EVOLUCION = new Long(794870799);

    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public final static String DESCRIPCION = "6.-Evolución ";

    public final Variable VAR_PARTO_EVOLU_NUMEROORDENPARTO = new Variable("'13822265", "99G2", new Long(13822265),
            "Nº Orden Parto", new Double(1), new Double(3000));

    public final Variable VAR_PARTO_EVOLU_TAS = new Variable("'11726-7", "LN", new Long(2230), "Tas", new Double(20),
            new Double(300));

    public final Variable VAR_PARTO_EVOLU_TAD = new Variable("271650006", "SNM3", new Long(13722241), "Tad",
            new Double(20), new Double(300));

    public final Variable VAR_PARTO_EVOLU_T = new Variable("246508008", "SNM3", new Long(776), "Tª", new Double(33),
            new Double(45));

    public final Variable VAR_PARTO_EVOLU_FC = new Variable("364075005", "SNM3", new Long(7747515), "Fc",
            new Double(30), new Double(200));

    public final Variable VAR_PARTO_EVOLU_FIEBRE_MATERNA = new Variable("13994639", "99G2", new Long(13994639),
            "Fiebre Materna");

    public final Variable VAR_PARTO_EVOLU_TIPO_REGECOCARDIOTIPO = new Variable("13994641", "99G2", new Long(13994641),
            "Tipo Reg cadiotocográfico");

    public final Variable VAR_PARTO_EVOLU_TIPO_REGECOCARDIO = new Variable("13821845", "99G2", new Long(13821845),
            "Registro  cadiotocográfico");

    public final Variable VAR_PARTO_EVOLU_TIPO_REGECOCARDIOINTERPRETACION = new Variable("13994642", "99G2",
            new Long(13994642), "Interpretación RC");

    public final Variable VAR_PARTO_EVOLU_PHFETAL = new Variable("13994640", "99G2", new Long(13994640), "PH Fetal");

    public Variable VAR_PARTO_EVOLU_PHFETAL_VALOR = new Variable("199785006", "SNM3", new Long(13994643),
            "Valor PH Fetal");

    public final Variable VAR_PARTO_EVOLU_HORASEXPULSIVO = new Variable("13994645", "99G2", new Long(13994645),
            "Horas Epulsivo");
    public final Variable VAR_PARTO_EVOLU_HORASDILATACION = new Variable("13995200", "99G2", new Long(13995200),
            "Horas Dilatación ");

//	public final Variable VAR_PARTO_EVOLU_TIPOPARTO_ = new Variable("13994646", "99G2", new Long(13994646),
//			"Tipo Parto ");
// 	
    public final Variable VAR_PARTO_EVOLU_VAGINAL = new Variable("13994646", "99G2", new Long(13994646),
            "Parto Vaginal eutócico ");
    public final Variable VAR_PARTO_EVOLU_VENTOSA = new Variable("72.7", "I9C", new Long(102822),
            "Parto Vaginal Ventosa ");
    public final Variable VAR_PARTO_EVOLU_FORCESP = new Variable("257230007", "SNM3", new Long(7755658),
            "Parto Vaginal Fórceps ");

    public final Variable VAR_PARTO_EVOLU_ESPATULAS = new Variable("13995133", "99G2", new Long(13995133),
            "Parto Vaginal Espátulas ");

    public final Variable VAR_PARTO_EVOLU_INSTRUMENTAL_ = new Variable("72.8", "I9C", new Long(102825),
            "Indicaciones instrumental ");

    public final Variable VAR_PARTO_EVOLU_DISTOCIAHOMBRO_ = new Variable("O66.0", "I10C", new Long(60025379),
            "Distocia Hombros ");
    public final Variable VAR_PARTO_EVOLU_MANIOBRAMACROBERTS = new Variable("13995134", "99G2", new Long(13995134),
            "Maniobra de McRoberts y Presión suprapúbica");
    public final Variable VAR_PARTO_EVOLU_MANIOBRAIINIVEL = new Variable("13995135", "99G2", new Long(13995135),
            "Maniobra II nivel");
    public final Variable VAR_PARTO_EVOLU_MANIOBRAIIINIVEL = new Variable("13995136", "99G2", new Long(13995136),
            "Maniobra III nivel");
    public final Variable VAR_PARTO_EVOLU_EPISIOTOMIA = new Variable("102840", "73.6", new Long(102840), "Episiotomía");

    public final Variable VAR_PARTO_EVOLU_CESAREA = new Variable("35000503", "99G2", new Long(35000503), "Cesárea");
    public final Variable VAR_PARTO_EVOLU_CESAREAINDICACION = new Variable("13994653", "99G2", new Long(13994653),
            "Cesárea Indicación");
    public final Variable VAR_PARTO_EVOLU_CESAREAOBSERVACIONES = new Variable("10218", "99G2", new Long(10218),
            "Observaciones   ");

    public final Variable VAR_PARTO_EVOLU_EXPULSUPINO = new Variable("13995137", "99G2", new Long(13995137),
            "Expulsivo supino");
    public final Variable VAR_PARTO_EVOLU_EXPULCUADRUPEDIA = new Variable("13995138", "99G2", new Long(13995138),
            "Expulsivo Cuadrupedia");
    public final Variable VAR_PARTO_EVOLU_EXPULLATERAL = new Variable("13995139", "99G2", new Long(13995139),
            "Expulsivo lateral");
    public final Variable VAR_PARTO_EVOLU_EXPULBIPEDESTACION = new Variable("13995140", "99G2", new Long(13995140),
            "Expulsivo Bipedestación");
    public final Variable VAR_PARTO_EVOLU_EXPULSENTADA = new Variable("13995141", "99G2", new Long(13995141),
            "Expulsivo Sentada");

    public final Variable VAR_PARTO_EVOLU_EXPULLITOTOMIA = new Variable("13995218", "99G2", new Long(13995218),
            "Expulsivo Litotomía");
    public final Variable VAR_PARTO_EVOLU_EXPULAPOYOPLANTAR = new Variable("13995219", "99G2", new Long(13995219),
            "Expulsivo Apoyo plantar");

    // alumbramiento
    public final Variable VAR_PARTO_EVOLU_ALUM_TIPOALUMBRA = new Variable("N420101.ALUPAR", "99G2", new Long(104966),
            "Tipo Alumbramiennto ");
//,Dirigido,Espontáneo,Manual
    public final Variable VAR_PARTO_EVOLU_ALUM_PLACENTA = new Variable("122736005", "SNM3", new Long(5628),
            "Placenta ");
    // ,Normal,Patológica
    public final Variable VAR_PARTO_EVOLU_ALUM_MEMBRANASFETALES = new Variable("54134001", "SNM3", new Long(13994652),
            "Membranas fetales ");
    public final Variable VAR_PARTO_EVOLU_ALUM_CORDONPINZA = new Variable("13995153", "99G2", new Long(13995153),
            "Cordón pincamiento");
//	// membranas fetales ,Íntegras,Desgarradas

    public final Variable VAR_PARTO_EVOLU_ALUM_CORDONVASOS = new Variable("13994647", "99G2", new Long(13994647),
            "Cordón vasos");
//	3 Vasos,Arteria única
    public final Variable VAR_PARTO_EVOLU_ALUM_CORDONINSERCION = new Variable("13994648", "99G2", new Long(13994648),
            "Cordón inserción ");
    // ,Central,Lateral,Velamentosa

    public final Variable VAR_PARTO_EVOLU_ALUM_CORDONDONACION = new Variable("N420101.DONCORD", "99G2",
            new Long(104992), "Cordón donación sangre ");
    // ,No,Pública,Privada
    public final Variable VAR_PARTO_EVOLU_ALUM_HEMORRACANTIDAD = new Variable("13994649", "99G2", new Long(13994649),
            "Hemorragia cantidad ");
//<500 ml,>500 ml,>1000 ml
    public final Variable VAR_PARTO_EVOLU_ALUM_HEMORRASINGOS = new Variable("13994650", "99G2", new Long(13994650),
            "Hemorragia signos clínicos ");

    public final Variable VAR_PARTO_EVOLU_ALUM_HEMORRAMAREO = new Variable("780.4", "I9C", new Long(6229),
            "Hemorragia signos mareo ");
    public final Variable VAR_PARTO_EVOLU_ALUM_HEMORRAHIPOTESION = new Variable("O10.93", "I10C", new Long(60011743),
            "Hemorragia signos Hipertensión ");
    public final Variable VAR_PARTO_EVOLU_ALUM_HEMORRAPERDIDACONO = new Variable("419045004", "SNM3",
            new Long(13826668), "Hemorragia signos Pérdida conocimiento ");
    public final Variable VAR_PARTO_EVOLU_ALUM_HEMORRATAQUICARDIA = new Variable("3424008", "SNM3", new Long(13826665),
            "Hemorragia signos Pérdida conocimientoo ");
    public final Variable VAR_PARTO_EVOLU_ALUM_HEMORRATAQUIAPNEA = new Variable("13994781", "99G2", new Long(13994781),
            "Hemorragia signos taquiapnea");

    public final Variable VAR_PARTO_EVOLU_ALUM_HEMORRATRATAMIENTO = new Variable("13994651", "99G2", new Long(13994651),
            "Hemorragia tratamiento ");

    public final Variable VAR_PARTO_EVOLU_TRATATREDELENBUG = new Variable("13995173", "99G2", new Long(13995173),
            "Hemorragia tratamiento Trendelemburg ");
    public final Variable VAR_PARTO_EVOLU_TRATAOXITO = new Variable("2718-5", "LN", new Long(609),
            "Hemorragia tratamiento Oxitocina ");
    public final Variable VAR_PARTO_EVOLU_TRATASUEROTERA = new Variable("9702", "99G2", new Long(9702),
            "Hemorragia tratamiento Sueroterapia ");
    public final Variable VAR_PARTO_EVOLU_TRATAMISOPROSTOL = new Variable("13995174", "99G2", new Long(13995174),
            "Hemorragia tratamiento Misoprostol ");
    public final Variable VAR_PARTO_EVOLU_TRATAMETHERGIN = new Variable("13995175", "99G2", new Long(13995175),
            "Hemorragia tratamiento Methergin ");
    public final Variable VAR_PARTO_EVOLU_TRATAHEMABATE = new Variable("13995176", "99G2", new Long(13995176),
            "Hemorragia tratamiento Hemabate ");
    public final Variable VAR_PARTO_EVOLU_TRATABALONBAKRI = new Variable("13995177", "99G2", new Long(13995177),
            "Hemorragia tratamiento Balón bakri ");
    public final Variable VAR_PARTO_EVOLU_TRATABELINCHE = new Variable("13995179", "99G2", new Long(13995179),
            "Hemorragia tratamiento Beliche ");
    public final Variable VAR_PARTO_EVOLU_TRATAHAYMAN = new Variable("13995178", "99G2", new Long(13995178),
            "Hemorragia tratamiento Hayman ");
    public final Variable VAR_PARTO_EVOLU_TRATALIGADURAHIPO = new Variable("13995180", "99G2", new Long(13995180),
            "Hemorragia tratamiento Ligadura hipgástrica ");
    public final Variable VAR_PARTO_EVOLU_TRATAHTOBSTE = new Variable("13995181", "99G2", new Long(13995181),
            "Hemorragia tratamiento HT obstétrico");
    public final Variable VAR_PARTO_EVOLU_TRATAOBSERVA = new Variable("13995182", "99G2", new Long(13995182),
            "Hemorragia tratamiento Observación");

    public RegistroPartoEvolucion() {
        this.iniciaPartoCtes();
    }

    public RegistroPartoEvolucion(Long id) {
        super(id);
        this.iniciaPartoCtes();
    }

    public RegistroPartoEvolucion(RegistroPartoEvolucion r) {
        super(r);
        this.numeroOrdenParto = r.getNumeroOrdenParto();
        this.temperatura = r.getTemperatura();
        this.tas = r.getTas();
        this.tad = r.getTad();
        this.fc = r.getFc();
        this.fiebreMaterna = r.getFiebreMaterna();
        this.registroCaridiotocografico = r.getRegistroCaridiotocografico();
        this.interpretacioRC = r.getInterpretacioRC();
        this.tipoRC = r.getTipoRC();
        this.phFetal = r.getPhFetal();
        this.phFetalValor = r.getPhFetalValor();
        // Parto vaginal
        this.horasExpulsivo = r.getHorasExpulsivo();
        this.horasDilatacion = r.getHorasDilatacion();
        this.partoTipo = r.getPartoTipo();
        // vaginal
        this.vaginalEutocico = r.getVaginalEutocico();
        this.vaginalVentosa = r.getVaginalVentosa();
        this.vaginalForceps = r.getVaginalForceps();
        this.vaginalEspatulas = r.getVaginalEspatulas();
        this.instrumental = r.getInstrumental();
        this.distociaHombros = r.getDistociaHombros();
        this.maniobraMcRoberts = r.getManiobraMcRoberts();
        this.maniobraIInivel = r.getManiobraIInivel();
        this.maniobraIIInivel = r.getManiobraIIInivel();

        this.episiotomia = r.getEpisiotomia();
        this.cesarea = r.getCesarea();
        this.cesareaIndicacion = r.getCesareaIndicacion();
        this.cesareaIndicacion = r.getCesareaObservaciones();

        this.expulsivoSupino = r.getExpulsivoSupino();
        this.expulsivoCuadrupedia = r.getExpulsivoCuadrupedia();
        this.expulsivoLateral = r.getExpulsivoLateral();
        this.expulsivoBipedestacion = r.getExpulsivoBipedestacion();
        this.expulsivoSentada = r.getExpulsivoSentada();
        this.expulsivoLitotomía = r.getExpulsivoLitotomía();
        this.expulsivoApoyoPlanar = r.getExpulsivoApoyoPlanar();

        this.tipoAlumbramiento = r.getTipoAlumbramiento();
        this.placenta = r.getPlacenta();
        this.membranasFetales = r.getMembranasFetales();
        this.cordonPinza = r.getCordonPinza();
        this.cordonVasos = r.getCordonVasos();
        this.cordonInsercion = r.getCordonInsercion();
        this.cordonDonacion = r.getCordonDonacion();
        this.hemorragiaCantidad = r.getHemorragiaCantidad();
        this.hemorragiaSingos = r.getHemorragiaSingos();
        this.hemorragiaMareo = r.getHemorragiaMareo();
        this.hemorragiaHiptension = r.getHemorragiaHiptension();
        this.hemorragiaPerdidaCono = r.getHemorragiaPerdidaCono();
        this.hemorragiaTaquicardia = r.getHemorragiaTaquicardia();
        this.hemorragiaTaquiaPnea = r.getHemorragiaTaquiaPnea();

        this.hemorragiaTratamiento = r.getHemorragiaTratamiento();
        this.tratoTrenmdelemburg = r.getTratoTrenmdelemburg();
        this.tratoSueroterapia = r.getTratoSueroterapia();
        this.tratoOxitocina = r.getTratoOxitocina();
        this.tratoMisoprostol = r.getTratoMisoprostol();
        this.tratoMethergin = r.getTratoMethergin();
        this.tratohemabate = r.getTratohemabate();
        this.tratoBalonBakri = r.getTratoBalonBakri();
        this.tratoBelinche = r.getTratoBelinche();
        this.tratoHayman = r.getTratoHayman();
        this.tratoLigadura = r.getTratoLigadura();
        this.tratoHTobsterica = r.getTratoHTobsterica();
        this.tratoObservaciones = r.getTratoObservaciones();

    }

    public void iniciaPartoCtes() {
        this.setTiporegistro(RegistroPartoEvolucion.TIPO_REGISTRO_PARTO);
        this.setDescripcion(DESCRIPCION);
        this.setPlantilla_edior(RegistroPartoEvolucion.PLANTILLLA_EDITOR_PAR_EVOLUCION);
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));
        this.numeroOrdenParto = VAR_PARTO_EVOLU_NUMEROORDENPARTO;
        this.temperatura = VAR_PARTO_EVOLU_T;
        this.tas = VAR_PARTO_EVOLU_TAS;
        this.tad = VAR_PARTO_EVOLU_TAD;
        this.fc = VAR_PARTO_EVOLU_FC;
        this.fiebreMaterna = VAR_PARTO_EVOLU_FIEBRE_MATERNA;
        this.registroCaridiotocografico = VAR_PARTO_EVOLU_TIPO_REGECOCARDIO;
        this.interpretacioRC = VAR_PARTO_EVOLU_TIPO_REGECOCARDIOINTERPRETACION;
        this.tipoRC = VAR_PARTO_EVOLU_TIPO_REGECOCARDIOTIPO;
        this.phFetal = VAR_PARTO_EVOLU_PHFETAL;
        this.phFetalValor = VAR_PARTO_EVOLU_PHFETAL_VALOR;
        // vaginal
        this.horasExpulsivo = VAR_PARTO_EVOLU_HORASEXPULSIVO;
        this.horasDilatacion = VAR_PARTO_EVOLU_HORASDILATACION;
        // this.partoTipo = VAR_PARTO_EVOLU_TIPOPARTO_;
        // vaginal
        this.vaginalEutocico = VAR_PARTO_EVOLU_VAGINAL;
        this.vaginalVentosa = VAR_PARTO_EVOLU_VENTOSA;
        this.vaginalForceps = VAR_PARTO_EVOLU_FORCESP;
        this.vaginalEspatulas = VAR_PARTO_EVOLU_ESPATULAS;
        this.instrumental = VAR_PARTO_EVOLU_INSTRUMENTAL_;
        this.distociaHombros = VAR_PARTO_EVOLU_DISTOCIAHOMBRO_;
        this.maniobraMcRoberts = VAR_PARTO_EVOLU_MANIOBRAMACROBERTS;
        this.maniobraIInivel = VAR_PARTO_EVOLU_MANIOBRAIINIVEL;
        this.maniobraIIInivel = VAR_PARTO_EVOLU_MANIOBRAIIINIVEL;
        this.episiotomia = VAR_PARTO_EVOLU_EPISIOTOMIA;

        this.cesarea = VAR_PARTO_EVOLU_CESAREA;
        this.cesareaIndicacion = VAR_PARTO_EVOLU_CESAREAINDICACION;
        this.cesareaObservaciones = VAR_PARTO_EVOLU_CESAREAOBSERVACIONES;
        this.expulsivoSupino = VAR_PARTO_EVOLU_EXPULSUPINO;
        this.expulsivoCuadrupedia = VAR_PARTO_EVOLU_EXPULCUADRUPEDIA;
        this.expulsivoLateral = VAR_PARTO_EVOLU_EXPULLATERAL;
        this.expulsivoBipedestacion = VAR_PARTO_EVOLU_EXPULBIPEDESTACION;
        this.expulsivoSentada = VAR_PARTO_EVOLU_EXPULSENTADA;
        this.expulsivoLitotomía = VAR_PARTO_EVOLU_EXPULLITOTOMIA;
        this.expulsivoApoyoPlanar = VAR_PARTO_EVOLU_EXPULAPOYOPLANTAR;

//
        this.tipoAlumbramiento = VAR_PARTO_EVOLU_ALUM_TIPOALUMBRA;
        this.placenta = VAR_PARTO_EVOLU_ALUM_PLACENTA;
        this.membranasFetales = VAR_PARTO_EVOLU_ALUM_MEMBRANASFETALES;
        this.cordonPinza = VAR_PARTO_EVOLU_ALUM_CORDONPINZA;
        this.cordonVasos = VAR_PARTO_EVOLU_ALUM_CORDONVASOS;
        this.cordonInsercion = VAR_PARTO_EVOLU_ALUM_CORDONINSERCION;
        this.cordonDonacion = VAR_PARTO_EVOLU_ALUM_CORDONDONACION;
        this.hemorragiaCantidad = VAR_PARTO_EVOLU_ALUM_HEMORRACANTIDAD;
        this.hemorragiaSingos = VAR_PARTO_EVOLU_ALUM_HEMORRASINGOS;
        this.hemorragiaMareo = VAR_PARTO_EVOLU_ALUM_HEMORRAMAREO;
        this.hemorragiaHiptension = VAR_PARTO_EVOLU_ALUM_HEMORRAHIPOTESION;
        this.hemorragiaPerdidaCono = VAR_PARTO_EVOLU_ALUM_HEMORRAPERDIDACONO;
        this.hemorragiaTaquicardia = VAR_PARTO_EVOLU_ALUM_HEMORRATAQUICARDIA;
        this.hemorragiaTaquiaPnea = VAR_PARTO_EVOLU_ALUM_HEMORRATAQUIAPNEA;

        this.hemorragiaTratamiento = VAR_PARTO_EVOLU_ALUM_HEMORRATRATAMIENTO;

        this.tratoTrenmdelemburg = VAR_PARTO_EVOLU_TRATATREDELENBUG;
        this.tratoSueroterapia = VAR_PARTO_EVOLU_TRATASUEROTERA;
        this.tratoOxitocina = VAR_PARTO_EVOLU_TRATAOXITO;
        this.tratoMisoprostol = VAR_PARTO_EVOLU_TRATAMISOPROSTOL;
        this.tratoMethergin = VAR_PARTO_EVOLU_TRATAMETHERGIN;
        this.tratohemabate = VAR_PARTO_EVOLU_TRATAHEMABATE;
        this.tratoBalonBakri = VAR_PARTO_EVOLU_TRATABALONBAKRI;
        this.tratoBelinche = VAR_PARTO_EVOLU_TRATABELINCHE;
        this.tratoHayman = VAR_PARTO_EVOLU_TRATAHAYMAN;
        this.tratoLigadura = VAR_PARTO_EVOLU_TRATALIGADURAHIPO;
        this.tratoHTobsterica = VAR_PARTO_EVOLU_TRATAHTOBSTE;
        this.tratoObservaciones = VAR_PARTO_EVOLU_TRATAOBSERVA;

    }

    public Variable getNumeroOrdenParto() {
        return numeroOrdenParto;
    }

    public Variable getVariableNumeroOrdenParto() {
        return numeroOrdenParto;
    }

    public String getNumeroOrdenPartoString() {
        return numeroOrdenParto.getValor();
    }

    public void setNumeroOrdenParto(Variable numeroOrdenParto) {
        this.numeroOrdenParto = numeroOrdenParto;
    }

    public void setNumeroOrdenParto(String valor) {
        this.numeroOrdenParto.setValor(valor);
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

    public Variable getFiebreMaterna() {
        return fiebreMaterna;
    }

    public Variable getVariableFiebreMaterna() {
        return fiebreMaterna;
    }

    public String getFiebreMaternaString() {
        return fiebreMaterna.getValor();
    }

    public void setFiebreMaterna(Variable fiebreMaterna) {
        this.fiebreMaterna = fiebreMaterna;
    }

    public void setFiebreMaterna(String valor) {
        this.fiebreMaterna.setValor(valor);
    }

    public Variable getRegistroCaridiotocografico() {
        return registroCaridiotocografico;
    }

    public Variable getVariableRegistroCaridiotocográfico() {
        return registroCaridiotocografico;
    }

    public String getRegistroCaridiotocograficoString() {
        return registroCaridiotocografico.getValor();
    }

    public void setRegistroCaridiotocografico(Variable registroCaridiotocografico) {
        this.registroCaridiotocografico = registroCaridiotocografico;
    }

    public void setRegistroCaridiotocografico(String valor) {
        this.registroCaridiotocografico.setValor(valor);
    }

    public Variable getInterpretacioRC() {
        return interpretacioRC;
    }

    public Variable getVariableInterpretacioRC() {
        return interpretacioRC;
    }

    public String getInterpretacioRCString() {
        return interpretacioRC.getValor();
    }

    public void setInterpretacioRC(Variable interpretacioRC) {
        this.interpretacioRC = interpretacioRC;
    }

    public void setInterpretacioRC(String valor) {
        this.interpretacioRC.setValor(valor);
    }

    public Variable getTipoRC() {
        return tipoRC;
    }

    public Variable getVariableTipoRC() {
        return tipoRC;
    }

    public String getTipoRCString() {
        return tipoRC.getValor();
    }

    public void setTipoRC(Variable tipoRC) {
        this.tipoRC = tipoRC;
    }

    public void setTipoRC(String valor) {
        this.tipoRC.setValor(valor);
        ;
    }

    public Variable getPhFetal() {
        return phFetal;
    }

    public Variable getVariablePhFetal() {
        return phFetal;
    }

    public String getPhFetalString() {
        return phFetal.getValor();
    }

    public void setPhFetal(Variable phFetal) {
        this.phFetal = phFetal;
    }

    public void setPhFetal(String valor) {
        this.phFetal.setValor(valor);
    }

    public Variable getPhFetalValor() {
        return phFetalValor;
    }

    public Variable getVariablePhFetalValor() {
        return phFetalValor;
    }

    public String getPhFetalValorString() {
        return phFetalValor.getValor();
    }

    public void setPhFetalValor(Variable phFetalValor) {
        this.phFetalValor = phFetalValor;
    }

    public void setPhFetalValor(String valor) {
        this.phFetalValor.setValor(valor);
    }

    public Variable getHorasDilatacion() {
        return horasDilatacion;
    }

    public Variable getVariableHorasDilatacion() {
        return horasDilatacion;
    }

    public String getHorasDilatacionString() {
        return horasDilatacion.getValor();
    }

    public void setHorasDilatacion(Variable horasDilatacion) {
        this.horasDilatacion = horasDilatacion;
    }

    public void setHorasDilatacion(String valor) {
        this.horasDilatacion.setValor(valor);
    }

    public Variable getHorasExpulsivo() {
        return horasExpulsivo;
    }

    public Variable getVariableHorasExpulsivo() {
        return horasExpulsivo;
    }

    public String getHorasExpulsivoString() {
        return horasExpulsivo.getValor();
    }

    public void setHorasExpulsivo(Variable horasExpulsivo) {
        this.horasExpulsivo = horasExpulsivo;
    }

    public void setHorasExpulsivo(String valor) {
        this.horasExpulsivo.setValor(valor);
    }

    public Variable getPartoTipo() {
        return partoTipo;
    }

    public Variable getVariablePartoTipo() {
        return partoTipo;
    }

    public String getPartoTipoString() {
        return partoTipo.getValor();
    }

    public void setPartoTipo(Variable partoTipo) {
        this.partoTipo = partoTipo;
    }

    public void setPartoTipo(String valor) {
        this.partoTipo.setValor(valor);
    }

    public Variable getVaginalEutocico() {
        return vaginalEutocico;
    }

    public Variable getVariableVaginalEutocico() {
        return vaginalEutocico;
    }

    public String getVaginalEutocicoString() {
        return vaginalEutocico.getValor();
    }

    public Boolean getVaginalEutocicoBoolean() {
        if (vaginalEutocico != null && !vaginalEutocico.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setVaginalEutocico(Variable vaginalEutocico) {
        this.vaginalEutocico = vaginalEutocico;
    }

    public void setVaginalEutocico(String valor) {
        this.vaginalEutocico.setValor(valor);
    }

    public void setVaginalEutocico(Boolean valor) {
        if (valor == true) {
            this.vaginalEutocico.setValor("Vaginal eutócico");
        } else {
            this.vaginalEutocico.setValor("");
        }
    }

    public Variable getVaginalVentosa() {
        return vaginalVentosa;
    }

    public Variable getVariableVaginalVentosa() {
        return vaginalVentosa;
    }

    public String getVaginalVentosaString() {
        return vaginalVentosa.getValor();
    }

    public Boolean getVaginalVentosaBoolean() {
        if (vaginalVentosa != null && !vaginalVentosa.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setVaginalVentosa(Variable vaginalVentosa) {
        this.vaginalVentosa = vaginalVentosa;
    }

    public void setVaginalVentosa(String valor) {
        this.vaginalVentosa.setValor(valor);
    }

    public void setVaginalVentosa(Boolean valor) {
        if (valor == true) {
            this.vaginalVentosa.setValor("Vaginal Ventosa");
        } else {
            this.vaginalVentosa.setValor("");
        }
    }

    public Variable getVaginalForceps() {
        return vaginalForceps;
    }

    public Variable getVariableVaginalForceps() {
        return vaginalForceps;
    }

    public String getVaginalForcepsString() {
        return vaginalForceps.getValor();
    }

    public Boolean getVaginalForcepsBoolean() {
        if (vaginalForceps != null && !vaginalForceps.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setVaginalForceps(Variable vaginalForceps) {
        this.vaginalForceps = vaginalForceps;
    }

    public void setVaginalForceps(String valor) {
        this.vaginalForceps.getValor();
    }

    public void setVaginalForceps(Boolean valor) {
        if (valor == true) {
            this.vaginalForceps.setValor("Vaginal Fórceps");
        } else {
            this.vaginalForceps.setValor("");
        }
    }

    public Variable getVaginalEspatulas() {
        return vaginalEspatulas;
    }

    public Variable getVariableVaginalEspatulas() {
        return vaginalEspatulas;
    }

    public String getVaginalEspatulasString() {
        return vaginalEspatulas.getValor();
    }

    public Boolean getVaginalEspatulasBoolean() {
        if (vaginalEspatulas != null && !vaginalEspatulas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setVaginalEspatulas(Variable vaginalEspatulas) {
        this.vaginalEspatulas = vaginalEspatulas;
    }

    public void setVaginalEspatulas(String valor) {
        this.vaginalEspatulas.setValor(valor);
    }

    public void setVaginalEspatulas(Boolean valor) {
        if (valor == true) {
            this.vaginalEspatulas.setValor("Vaginal Espátulas");
        } else {
            this.vaginalEspatulas.setValor("");
        }

    }

    public Variable getInstrumental() {
        return instrumental;
    }

    public Variable getVariableInstrumental() {
        return instrumental;
    }

    public String getInstrumentalStirng() {
        return instrumental.getValor();
    }

    public void setInstrumental(Variable instrumental) {
        this.instrumental = instrumental;
    }

    public void setInstrumental(String valor) {
        this.instrumental.setValor(valor);
    }

    public Variable getDistociaHombros() {
        return distociaHombros;
    }

    public Variable getVariableDistociaHombros() {
        return distociaHombros;
    }

    public String getDistociaHombrosString() {
        return distociaHombros.getValor();
    }

    public Boolean getDistociaHombrosBoolean() {
        if (distociaHombros != null && !distociaHombros.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setDistociaHombros(Variable distociaHombros) {
        this.distociaHombros = distociaHombros;
    }

    public void setDistociaHombros(String valor) {
        this.distociaHombros.setValor(valor);
    }

    public void setDistociaHombros(Boolean valor) {
        if (valor == true) {
            this.distociaHombros.setValor("Distocia hombros:Si");
        } else {
            this.distociaHombros.setValor("Distocia hombros:No");
        }
    }

    public Variable getManiobraMcRoberts() {
        return maniobraMcRoberts;
    }

    public Variable getVariableManiobraMcRoberts() {
        return maniobraMcRoberts;
    }

    public Boolean getManiobraMcRobertsBoolean() {
        if (maniobraMcRoberts != null && !maniobraMcRoberts.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setManiobraMcRoberts(Variable maniobraMcRoberts) {
        this.maniobraMcRoberts = maniobraMcRoberts;
    }

    public void setManiobraMcRoberts(Boolean valor) {
        if (valor == true) {
            this.maniobraMcRoberts.setValor("Maniobra de McRoberts y presión suprapúbica");
        } else {
            this.maniobraMcRoberts.setValor("");
        }
    }

    public Variable getManiobraIInivel() {
        return maniobraIInivel;
    }

    public Variable getVariableManiobraIInivel() {
        return maniobraIInivel;
    }

    public Boolean getManiobraIInivelBoolean() {
        if (maniobraIInivel != null && !maniobraIInivel.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setManiobraIInivel(Variable maniobraIInivel) {
        this.maniobraIInivel = maniobraIInivel;
    }

    public void setManiobraIInivel(Boolean valor) {
        if (valor == true) {
            this.maniobraIInivel.setValor("'Maniobra II nivel (parto distocia hombros)");
        } else {
            this.maniobraIInivel.setValor("");
        }

    }

    public Variable getManiobraIIInivel() {
        return maniobraIIInivel;
    }

    public Variable getVariableManiobraIIInivel() {
        return maniobraIIInivel;
    }

    public Boolean getManiobraIIInivelBoolean() {
        if (maniobraIIInivel != null && !maniobraIIInivel.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setManiobraIIInivel(Variable maniobraIIInivel) {
        this.maniobraIIInivel = maniobraIIInivel;
    }

    public void setManiobraIIInivel(Boolean valor) {
        if (valor == true) {
            this.maniobraIIInivel.setValor("'Maniobra III nivel (parto distocia hombros)");
        } else {
            this.maniobraIIInivel.setValor("");
        }
    }

    public Variable getEpisiotomia() {
        return episiotomia;
    }

    public Variable getVariableEpisiotomia() {
        return episiotomia;
    }

    public String getEpisiotomiaString() {
        return episiotomia.getValor();
    }

    public void setEpisiotomia(Variable episiotomia) {
        this.episiotomia = episiotomia;
    }

    public void setEpisiotomia(String valor) {
        this.episiotomia.setValor(valor);
    }

    public Variable getCesarea() {
        return cesarea;
    }

    public Variable getVariableCesarea() {
        return cesarea;
    }

    public String getCesareaString() {
        return cesarea.getValor();
    }

    public void setCesarea(Variable cesarea) {
        this.cesarea = cesarea;
    }

    public void setCesarea(String valor) {
        this.cesarea.setValor(valor);
    }

    public Variable getCesareaIndicacion() {
        return cesareaIndicacion;
    }

    public Variable getVariableCesareaIndicacion() {
        return cesareaIndicacion;
    }

    public String getCesareaIndicacionString() {
        return cesareaIndicacion.getValor();
    }

    public void setCesareaIndicacion(Variable cesareaIndicacion) {
        this.cesareaIndicacion = cesareaIndicacion;
    }

    public void setCesareaIndicacion(String valor) {
        this.cesareaIndicacion.setValor(valor);
    }

    public Variable getCesareaObservaciones() {
        return cesareaObservaciones;
    }

    public Variable getVariableCesareaObservaciones() {
        return cesareaObservaciones;
    }

    public String getCesareaObservacionesString() {
        return cesareaObservaciones.getValor();
    }

    public void setCesareaObservaciones(Variable cesareaObservaciones) {
        this.cesareaObservaciones = cesareaObservaciones;
    }

    public void setCesareaObservaciones(String valor) {
        this.cesareaObservaciones.setValor(valor);
    }

    public Variable getExpulsivoSupino() {
        return expulsivoSupino;
    }

    public Variable getVariableExpulsivoSupino() {
        return expulsivoSupino;
    }

    public Boolean getExpulsivoSupinoBoolean() {
        if (expulsivoSupino != null && !expulsivoSupino.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setExpulsivoSupino(Variable expulsivoSupino) {
        this.expulsivoSupino = expulsivoSupino;
    }

    public void setExpulsivoSupino(Boolean valor) {
        if (valor == true) {
            this.expulsivoSupino.setValor("Expulsivo supino");
        } else {
            this.expulsivoSupino.setValor("");
        }
    }

    public Variable getExpulsivoCuadrupedia() {
        return expulsivoCuadrupedia;
    }

    public Variable getVariableExpulsivoCuadrupedia() {
        return expulsivoCuadrupedia;
    }

    public Boolean getExpulsivoCuadrupediaBoolean() {
        if (expulsivoCuadrupedia != null && !expulsivoCuadrupedia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setExpulsivoCuadrupedia(Variable expulsivoCuadrupedia) {
        this.expulsivoCuadrupedia = expulsivoCuadrupedia;
    }

    public void setExpulsivoCuadrupedia(Boolean valor) {
        if (valor == true) {
            this.expulsivoCuadrupedia.setValor("Expulsivo cuadrupedia");
        } else {
            this.expulsivoCuadrupedia.setValor("");
        }
    }

    public Variable getExpulsivoLateral() {
        return expulsivoLateral;
    }

    public Variable getVariableExpulsivoLateral() {
        return expulsivoLateral;
    }

    public Boolean getExpulsivoLateralBoolean() {
        if (expulsivoLateral != null && !expulsivoLateral.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setExpulsivoLateral(Variable expulsivoLateral) {
        this.expulsivoLateral = expulsivoLateral;
    }

    public void setExpulsivoLateral(Boolean valor) {
        if (valor == true) {
            this.expulsivoLateral.setValor("Expulsivo lateral");
        } else {
            this.expulsivoLateral.setValor("");
        }
    }

    public Variable getExpulsivoBipedestacion() {
        return expulsivoBipedestacion;
    }

    public Variable getVariableExpulsivoBipedestacion() {
        return expulsivoBipedestacion;
    }

    public Boolean getExpulsivoBipedestacionBoolean() {
        if (expulsivoBipedestacion != null && !expulsivoBipedestacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setExpulsivoBipedestacion(Variable expulsivoBipedestacion) {
        this.expulsivoBipedestacion = expulsivoBipedestacion;
    }

    public void setExpulsivoBipedestacion(Boolean valor) {
        if (valor == true) {
            this.expulsivoBipedestacion.setValor("Expulsivo bipedestacion");
        } else {
            this.expulsivoBipedestacion.setValor("");
        }
    }

    public Variable getExpulsivoSentada() {
        return expulsivoSentada;
    }

    public Variable getVariableExpulsivoSentada() {
        return expulsivoSentada;
    }

    public Boolean getExpulsivoSentadaBoolean() {
        if (expulsivoSentada != null && !expulsivoSentada.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setExpulsivoSentada(Variable expulsivoSentada) {
        this.expulsivoSentada = expulsivoSentada;
    }

    public void setExpulsivoSentada(Boolean valor) {
        if (valor == true) {
            this.expulsivoSentada.setValor("Expulsivo sentada");
        } else {
            this.expulsivoSentada.setValor("");
        }

    }

    public Variable getExpulsivoLitotomía() {
        return expulsivoLitotomía;
    }

    public Variable getVariableExpulsivoLitotomía() {
        return expulsivoLitotomía;
    }

    public Boolean getExpulsivoLitotomíaBoolean() {
        if (expulsivoLitotomía != null && !expulsivoLitotomía.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setExpulsivoLitotomía(Variable expulsivoLitotomía) {
        this.expulsivoLitotomía = expulsivoLitotomía;
    }

    public void setExpulsivoLitotomía(Boolean valor) {
        if (valor == true) {
            this.expulsivoLitotomía.setValor("Expulsivo litotomía");
        } else {
            this.expulsivoLitotomía.setValor("");
        }
    }

    public Variable getExpulsivoApoyoPlanar() {
        return expulsivoApoyoPlanar;
    }

    public Variable getVariableExpulsivoApoyoPlanar() {
        return expulsivoApoyoPlanar;
    }

    public Boolean getExpulsivoApoyoPlanarBoolean() {
        if (expulsivoApoyoPlanar != null && !expulsivoApoyoPlanar.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setExpulsivoApoyoPlanar(Variable expulsivoApoyoPlanar) {
        this.expulsivoApoyoPlanar = expulsivoApoyoPlanar;
    }

    public void setExpulsivoApoyoPlanar(Boolean valor) {
        if (valor == true) {
            this.expulsivoApoyoPlanar.setValor("Expulsivo apoyo plantar");
        } else {
            this.expulsivoApoyoPlanar.setValor("");
        }
    }

    // alumbramiento
    public Variable getTipoAlumbramiento() {
        return tipoAlumbramiento;
    }

    public Variable getVariableTipoAlumbramiento() {
        return tipoAlumbramiento;
    }

    public String getTipoAlumbramientoString() {
        return tipoAlumbramiento.getValor();
    }

    public void setTipoAlumbramiento(Variable tipoAlumbramiento) {
        this.tipoAlumbramiento = tipoAlumbramiento;
    }

    public void setTipoAlumbramiento(String valor) {
        this.tipoAlumbramiento.setValor(valor);
    }

    public Variable getPlacenta() {
        return placenta;
    }

    public Variable getVariablePlacenta() {
        return placenta;
    }

    public String getPlacentaString() {
        return placenta.getValor();
    }

    public void setPlacenta(Variable placenta) {
        this.placenta = placenta;
    }

    public void setPlacenta(String valor) {
        this.placenta.setValor(valor);
    }

    public Variable getMembranasFetales() {
        return membranasFetales;
    }

    public Variable getVariableMembranasFetales() {
        return membranasFetales;
    }

    public String getMembranasFetalesString() {
        return membranasFetales.getValor();
    }

    public void setMembranasFetales(Variable membranasFetales) {
        this.membranasFetales = membranasFetales;
    }

    public void setMembranasFetales(String valor) {
        this.membranasFetales.setValor(valor);
    }

    public Variable getCordonPinza() {
        return cordonPinza;
    }

    public Variable getVariableCordonPinza() {
        return cordonPinza;
    }

    public String getCordonPinzaString() {
        return cordonPinza.getValor();
    }

    public void setCordonPinza(Variable cordonPinza) {
        this.cordonPinza = cordonPinza;
    }

    public void setCordonPinza(String valor) {
        this.cordonPinza.setValor(valor);
    }

    public Variable getCordonVasos() {
        return cordonVasos;
    }

    public Variable getVariableCordonVasos() {
        return cordonVasos;
    }

    public String getCordonVasosString() {
        return cordonVasos.getValor();
    }

    public void setCordonVasos(Variable cordonVasos) {
        this.cordonVasos = cordonVasos;
    }

    public void setCordonVasos(String valor) {
        this.cordonVasos.setValor(valor);
    }

    public Variable getCordonInsercion() {
        return cordonInsercion;
    }

    public Variable getVariableCordonInsercion() {
        return cordonInsercion;
    }

    public String getCordonInsercionString() {
        return cordonInsercion.getValor();
    }

    public void setCordonInsercion(Variable cordonInsercion) {
        this.cordonInsercion = cordonInsercion;
    }

    public void setCordonInsercion(String valor) {
        this.cordonInsercion.setValor(valor);
    }

    public Variable getCordonDonacion() {
        return cordonDonacion;
    }

    public Variable getVariableCordonDonacion() {
        return cordonDonacion;
    }

    public String getCordonDonacionString() {
        return cordonDonacion.getValor();
    }

    public void setCordonDonacion(Variable cordonDonacion) {
        this.cordonDonacion = cordonDonacion;
    }

    public void setCordonDonacion(String valor) {
        this.cordonDonacion.setValor(valor);
    }

    public Variable getHemorragiaCantidad() {
        return hemorragiaCantidad;
    }

    public Variable getVariableHemorragiaCantidad() {
        return hemorragiaCantidad;
    }

    public String getHemorragiaCantidadString() {
        return hemorragiaCantidad.getValor();
    }

    public void setHemorragiaCantidad(Variable hemorragiaCantidad) {
        this.hemorragiaCantidad = hemorragiaCantidad;
    }

    public void setHemorragiaCantidad(String valor) {
        this.hemorragiaCantidad.setValor(valor);
    }

    public Variable getHemorragiaSingos() {
        return hemorragiaSingos;
    }

    public Variable getVariableHemorragiaSingos() {
        return hemorragiaSingos;
    }

    public String getHemorragiaSingosString() {
        return hemorragiaSingos.getValor();
    }

    public void setHemorragiaSingos(Variable hemorragiaSingos) {
        this.hemorragiaSingos = hemorragiaSingos;
    }

    public void setHemorragiaSingos(String valor) {
        this.hemorragiaSingos.setValor(valor);
    }

    public Variable getHemorragiaMareo() {
        return hemorragiaMareo;
    }

    public Variable getVariableHemorragiaMareo() {
        return hemorragiaMareo;
    }

    public Boolean getHemorragiaMareoBoolean() {
        if (hemorragiaMareo != null && !hemorragiaMareo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHemorragiaMareo(Variable hemorragiaMareo) {
        this.hemorragiaMareo = hemorragiaMareo;
    }

    public void setHemorragiaMareo(Boolean valor) {
        if (valor == true) {
            this.hemorragiaMareo.setValor("Mareo");
        } else {
            this.hemorragiaMareo.setValor("");
        }
    }

    public Variable getHemorragiaHiptension() {
        return hemorragiaHiptension;
    }

    public Variable getVariableHemorragiaHiptension() {
        return hemorragiaHiptension;
    }

    public Boolean getHemorragiaHiptensionBoolean() {
        if (hemorragiaHiptension != null && !hemorragiaHiptension.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHemorragiaHiptension(Variable hemorragiaHiptension) {
        this.hemorragiaHiptension = hemorragiaHiptension;
    }

    public void setHemorragiaHiptension(Boolean valor) {
        if (valor == true) {
            this.hemorragiaHiptension.setValor("Hiptension");
        } else {
            this.hemorragiaHiptension.setValor("");
        }
    }

    public Variable getHemorragiaPerdidaCono() {
        return hemorragiaPerdidaCono;
    }

    public Variable getVariableHemorragiaPerdidaCono() {
        return hemorragiaPerdidaCono;
    }

    public Boolean getHemorragiaPerdidaConoBoolean() {
        if (hemorragiaPerdidaCono != null && !hemorragiaPerdidaCono.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHemorragiaPerdidaCono(Variable hemorragiaPerdidaCono) {
        this.hemorragiaPerdidaCono = hemorragiaPerdidaCono;
    }

    public void setHemorragiaPerdidaCono(Boolean valor) {
        if (valor == true) {
            this.hemorragiaPerdidaCono.setValor("Pérdida conocimiento");
        } else {
            this.hemorragiaPerdidaCono.setValor("");
        }
    }

    public Variable getHemorragiaTaquicardia() {
        return hemorragiaTaquicardia;
    }

    public Variable getVariableHemorragiaTaquicardia() {
        return hemorragiaTaquicardia;
    }

    public Boolean getHemorragiaTaquicardiaBoolean() {
        if (hemorragiaTaquicardia != null && !hemorragiaTaquicardia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHemorragiaTaquicardia(Variable hemorragiaTaquicardia) {
        this.hemorragiaTaquicardia = hemorragiaTaquicardia;
    }

    public void setHemorragiaTaquicardia(Boolean valor) {
        if (valor == true) {
            this.hemorragiaTaquicardia.setValor("Taquicardia");
        } else {
            this.hemorragiaTaquicardia.setValor("");
        }
    }

    public Variable getHemorragiaTaquiaPnea() {
        return hemorragiaTaquiaPnea;
    }

    public Variable getVariableHemorragiaTaquiaPnea() {
        return hemorragiaTaquiaPnea;
    }

    public Boolean getVariableHemorragiaTaquiaPneaBoolean() {
        if (hemorragiaTaquiaPnea != null && !hemorragiaTaquiaPnea.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHemorragiaTaquiaPnea(Variable hemorragiaTaquiaPnea) {
        this.hemorragiaTaquiaPnea = hemorragiaTaquiaPnea;
    }

    public void setHemorragiaTaquiaPnea(Boolean valor) {
        if (valor == true) {
            this.hemorragiaTaquiaPnea.setValor("Taquiapnea");
        } else {
            this.hemorragiaTaquicardia.setValor("");
        }

    }

    public Variable getHemorragiaTratamiento() {
        return hemorragiaTratamiento;
    }

    public Variable geVariabletHemorragiaTratamiento() {
        return hemorragiaTratamiento;
    }

    public String getHemorragiaTratamientoString() {
        return hemorragiaTratamiento.getValor();
    }

    public void setHemorragiaTratamiento(Variable hemorragiaTratamiento) {
        this.hemorragiaTratamiento = hemorragiaTratamiento;
    }

    public void setHemorragiaTratamiento(String valor) {
        this.hemorragiaTratamiento.setValor(valor);
    }

    public Variable getTratoTrenmdelemburg() {
        return tratoTrenmdelemburg;
    }

    public Variable getVariableTratoTrenmdelemburg() {
        return tratoTrenmdelemburg;
    }

    public Boolean getTratoTrenmdelemburgBoole() {
        if (tratoTrenmdelemburg != null && !tratoTrenmdelemburg.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoTrenmdelemburg(Variable tratoTrenmdelemburg) {
        this.tratoTrenmdelemburg = tratoTrenmdelemburg;
    }

    public void setTratoTrenmdelemburg(boolean valor) {
        if (valor == true) {
            this.tratoTrenmdelemburg.setValor("Tratamiento Trendelemburg");
        } else {
            this.tratoTrenmdelemburg.setValor("");
        }
    }

    public Variable getTratoSueroterapia() {
        return tratoSueroterapia;
    }

    public Variable getVariableTratoSueroterapia() {
        return tratoSueroterapia;
    }

    public Boolean getTratoSueroterapiaBoolen() {
        if (tratoSueroterapia != null && !tratoSueroterapia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoSueroterapia(Variable tratoSueroterapia) {
        this.tratoSueroterapia = tratoSueroterapia;
    }

    public void setTratoSueroterapia(Boolean valor) {
        if (valor == true) {
            this.tratoSueroterapia.setValor("Tratamiento Sueroterapia");
        } else {
            this.tratoSueroterapia.setValor("");
        }
    }

    public Variable getTratoOxitocina() {
        return tratoOxitocina;
    }

    public Variable getVariableTratoOxitocina() {
        return tratoOxitocina;
    }

    public Boolean getTratoOxitocinaBoolean() {
        if (tratoOxitocina != null && !tratoOxitocina.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoOxitocina(Variable tratoOxitocina) {
        this.tratoOxitocina = tratoOxitocina;
    }

    public void setTratoOxitocina(Boolean valor) {
        if (valor == true) {
            this.tratoOxitocina.setValor("Tratamiento Oxitocina");
        } else {
            this.tratoOxitocina.setValor("");
        }
    }

    public Variable getTratoMisoprostol() {
        return tratoMisoprostol;
    }

    public Variable getVariableTratoMisoprostol() {
        return tratoMisoprostol;
    }

    public Boolean getTratoMisoprostolBoolean() {
        if (tratoMisoprostol != null && !tratoMisoprostol.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoMisoprostol(Variable tratoMisoprostol) {
        this.tratoMisoprostol = tratoMisoprostol;
    }

    public void setTratoMisoprostol(boolean valor) {
        if (valor == true) {
            this.tratoMisoprostol.setValor("Tratamiento Misoprostol");
        } else {
            this.tratoMisoprostol.setValor("");
        }
    }

    public Variable getTratoMethergin() {
        return tratoMethergin;
    }

    public Variable getVariableTratoMethergin() {
        return tratoMethergin;
    }

    public Boolean getTratoMetherginBoolean() {
        if (tratoMethergin != null && !tratoMethergin.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoMethergin(Variable tratoMethergin) {
        this.tratoMethergin = tratoMethergin;
    }

    public void setTratoMethergin(Boolean valor) {
        if (valor == true) {
            this.tratoMethergin.setValor("Tratamiento Methergin");
        } else {
            this.tratoMethergin.setValor("");
        }
    }

    public Variable getTratohemabate() {
        return tratohemabate;
    }

    public Variable getVariableTratohemabate() {
        return tratohemabate;
    }

    public Boolean getTratohemabateBool() {
        if (tratohemabate != null && !tratohemabate.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratohemabate(Variable tratohemabate) {
        this.tratohemabate = tratohemabate;
    }

    public void setTratohemabate(Boolean valor) {
        if (valor == true) {
            this.tratohemabate.setValor("Tratamiento Hemabate");
        } else {
            this.tratohemabate.setValor("");
        }
    }

    public Variable getTratoBalonBakri() {
        return tratoBalonBakri;
    }

    public Variable getVariableTratoBalonBakri() {
        return tratoBalonBakri;
    }

    public Boolean getTratoBalonBakriBoolean() {
        if (tratoBalonBakri != null && !tratoBalonBakri.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoBalonBakri(Variable tratoBalonBakri) {
        this.tratoBalonBakri = tratoBalonBakri;
    }

    public void setTratoBalonBakri(Boolean valor) {
        if (valor == true) {
            this.tratoBalonBakri.setValor("Tratamiento BAlón de Bakri");
        } else {
            this.tratoBalonBakri.setValor("");
        }
    }

    public Variable getTratoBelinche() {
        return tratoBelinche;
    }

    public Variable getVariableTratoBelinche() {
        return tratoBelinche;
    }

    public Boolean getTratoBelincheBoolean() {
        if (tratoBelinche != null && !tratoBelinche.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoBelinche(Variable tratoBelinche) {
        this.tratoBelinche = tratoBelinche;
    }

    public void setTratoBelinche(Boolean valor) {
        if (valor == true) {
            this.tratoBelinche.setValor("Tratamiento Belinche");
        } else {
            this.tratoBelinche.setValor("");
        }
    }

    public Variable getTratoHayman() {
        return tratoHayman;
    }

    public Variable getVariableTratoHayman() {
        return tratoHayman;
    }

    public Boolean getTratoHaymanbool() {
        if (tratoHayman != null && !tratoHayman.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoHayman(Variable tratoHayman) {
        this.tratoHayman = tratoHayman;
    }

    public void setTratoHayman(Boolean valor) {
        if (valor == true) {
            this.tratoHayman.setValor("Tratamiento Hayman");
        } else {
            this.tratoHayman.setValor("");
        }
    }

    public Variable getTratoLigadura() {
        return tratoLigadura;
    }

    public Variable getVariableTratoLigadura() {
        return tratoLigadura;
    }

    public Boolean getTratoLigaduraBool() {
        if (tratoLigadura != null && !tratoLigadura.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoLigadura(Variable tratoLigadura) {
        this.tratoLigadura = tratoLigadura;
    }

    public void setTratoLigadura(Boolean valor) {
        if (valor == true) {
            this.tratoLigadura.setValor("Tratamiento Ligadura hipogástricas");
        } else {
            this.tratoLigadura.setValor("");
        }
    }

    public Variable getTratoHTobsterica() {
        return tratoHTobsterica;
    }

    public Variable getVariableTratoHTobsterica() {
        return tratoHTobsterica;
    }

    public Boolean getTratoHTobstericaBool() {
        if (tratoHTobsterica != null && !tratoHTobsterica.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTratoHTobsterica(Variable tratoHTobsterica) {
        this.tratoHTobsterica = tratoHTobsterica;
    }

    public void setTratoHTobsterica(Boolean valor) {
        if (valor == true) {
            this.tratoHTobsterica.setValor("Tratamiento HT obstétrica");
        } else {
            this.tratoHTobsterica.setValor("");
        }
    }

    public Variable getTratoObservaciones() {
        return tratoObservaciones;
    }

    public Variable getVariableTratoObservaciones() {
        return tratoObservaciones;
    }

    public String getTratoObservacionesString() {
        return tratoObservaciones.getValor();
    }

    public void setTratoObservaciones(Variable tratoObservaciones) {
        this.tratoObservaciones = tratoObservaciones;
    }

    public void setTratoObservaciones(String valor) {
        this.tratoObservaciones.setValor(valor);
    }

    public String getVaginaltipos() {
        String texto = "";
        if (vaginalEutocico != null) {
            texto = texto.concat(vaginalEutocico.getValor());
        }

        if (vaginalVentosa != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(vaginalVentosa.getValor());
        }

        if (vaginalForceps != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(vaginalForceps.getValor());
        }
        if (vaginalEspatulas != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(vaginalEspatulas.getValor());
        }
        return texto;
    }

    public String getDistociastipos() {
        String texto = "";
        if (maniobraMcRoberts != null) {
            texto = texto.concat(maniobraMcRoberts.getValor());
        }

        if (maniobraIInivel != null) {
            if (!texto.equals("")) {
                texto = texto.concat(", ");
            }
            texto = texto.concat(maniobraIInivel.getValor());
        }

        if (maniobraIInivel != null) {
            if (!texto.equals("")) {
                texto = texto.concat(", ");
            }
            texto = texto.concat(maniobraIInivel.getValor());
        }
        return texto;
    }

    public String getExpulsivoTipos() {
        String texto = "";
        if (expulsivoSupino != null) {
            texto = texto.concat(expulsivoSupino.getValor());
        }

        if (expulsivoCuadrupedia != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(expulsivoCuadrupedia.getValor());
        }

        if (expulsivoCuadrupedia != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(expulsivoCuadrupedia.getValor());
        }
        if (expulsivoBipedestacion != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(expulsivoBipedestacion.getValor());
        }
        if (expulsivoSentada != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(expulsivoSentada.getValor());
        }
        if (expulsivoLitotomía != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(expulsivoLitotomía.getValor());
        }

        if (expulsivoApoyoPlanar != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(expulsivoApoyoPlanar.getValor());
        }

        return texto;
    }

    public String getHemorragiatipossignos() {
        String texto = "";
        if (hemorragiaMareo != null) {
            texto = texto.concat(hemorragiaMareo.getValor());
        }

        if (hemorragiaHiptension != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(hemorragiaHiptension.getValor());
        }

        if (hemorragiaPerdidaCono != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(hemorragiaPerdidaCono.getValor());
        }
        if (hemorragiaTaquicardia != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(hemorragiaTaquicardia.getValor());
        }
        return texto;
    }

    public String getHemorraTrataMedico() {
        String texto = "";
        if (tratoTrenmdelemburg != null) {
            texto = texto.concat(tratoTrenmdelemburg.getValor());
        }

        if (tratoSueroterapia != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(tratoSueroterapia.getValor());
        }
        if (tratoOxitocina != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(tratoOxitocina.getValor());
        }
        if (tratoMisoprostol != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(tratoMisoprostol.getValor());
        }
        if (tratoMethergin != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(tratoMethergin.getValor());
        }
        if (tratohemabate != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(tratohemabate.getValor());
        }
        return texto;
    }

    public String getHemorraTrataQuirur() {
        String texto = "";
        if (tratoBalonBakri != null) {
            texto = texto.concat(tratoBalonBakri.getValor());
        }

        if (tratoBelinche != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(tratoBelinche.getValor());
        }

        if (tratoHayman != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(tratoHayman.getValor());
        }
        if (tratoHTobsterica != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(tratoHTobsterica.getValor());
        }
        return texto;
    }

    public String getTipoDePartoCesarea() {
        String texto = "";
        if (vaginalEutocico != null && !vaginalEutocico.getValor().isEmpty()) {
            texto = texto.concat(vaginalEutocico.getValor());
        }
        if (vaginalVentosa != null && !vaginalVentosa.getValor().isEmpty()) {
            texto = texto.concat(vaginalVentosa.getValor());
        }
        if (vaginalForceps != null && !vaginalForceps.getValor().isEmpty()) {
            texto = texto.concat(vaginalForceps.getValor());
        }
        if (vaginalEspatulas != null && !vaginalEspatulas.getValor().isEmpty()) {
            texto = texto.concat(vaginalEspatulas.getValor());
        }

        if (cesarea != null && !cesarea.getValor().isEmpty()) {
            texto = texto.concat(cesarea.getValor());
        }

        if (cesareaIndicacion != null && !cesareaIndicacion.getValor().isEmpty()) {
            texto = texto.concat(cesareaIndicacion.getValor());
        }

        if (cesareaObservaciones != null && !cesareaObservaciones.getValor().isEmpty()) {
            texto = texto.concat(cesareaObservaciones.getValor());
        }

        return texto;
    }
}
