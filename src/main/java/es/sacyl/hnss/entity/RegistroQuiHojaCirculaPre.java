package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.ConstantesClinicas;
import java.time.LocalDateTime;


public class RegistroQuiHojaCirculaPre extends Registro {

    private LocalDateTime fechaHoraInterTime;
    private Variable fechaHoraInter;
    private Variable quirofano;
    private Variable orden;
    private Variable servicioQui;
    private Variable urgenteProgramado;

    private Variable intervencion;
    private Variable diagnostico;
    
    

    private Variable cirujano1;
    private Variable cirujano2;
    private Variable cirujano3;

    private Variable anestesista;
    private Variable instrumentista;
    private Variable circulante;
    private Variable auxiliar;

    private Variable consciente;
    private Variable intubado;
    private Variable orientado;

    private Variable agitado;
    private Variable nervioso;

    private Variable alergias;

    private Variable viaVenosaTipo;
    private Variable viaVenosaLocali;
    private Variable viaArterialSN;
    private Variable viaArterialLocali;

    private Variable sng;

    private Variable catCanualaTra;
    private Variable catCanualaTraN;
    private Variable catPeritoneal;
    private Variable catPeritonealN;
    private Variable catUretral;
    private Variable catUretralN;
    private Variable catDrenaSilicona;
    private Variable catDrenaSiliconaN;
    private Variable catPenrose;
    private Variable catPenroseN;
    private Variable catJacksonP;
    private Variable catJacksonPN;
    private Variable catRedon;
    private Variable catRedonN;
    private Variable catSilastic;
    private Variable catSilasticN;
    private Variable catGastrostomia;
    private Variable catGastrostomiaN;
    private Variable catNefrostomia;
    private Variable catNefrostomiaN;
    private Variable catTallaVesical;
    private Variable catTallaVesicalN;
    private Variable catTuboKherr;
    private Variable catTuboKherrN;
    private Variable catTuboTorax;
    private Variable catTuboToraxN;

    private Variable sondaVesical;

    private Variable rasurado;
    private Variable ayunas;
    private Variable retiradaObjPel;
    private Variable protocolDiabetico;
    private Variable identificacion;
    private Variable profilaxisAntibio;

    public final Variable VAR_HOJACIRPRE_FECHAHORA = new Variable("8723-9", "LN", new Long(279),
            "Fecha hora inicio intervención");
    public final Variable VAR_HOJACIRPRE_ORDEN = new Variable("13822265", "99G2", new Long(13822265),
            "Orden de la intervención");
    public final Variable VAR_HOJACIRPRE_QUIROFANO = new Variable("225738002", "SNM3", new Long(13819844), "Quirófano");
    public final Variable VAR_HOJACIRPRE_SERVICIO = new Variable("284548004", "SNM3", new Long(1809903),
            "Servicio quirúrgico");
    public final Variable VAR_HOJACIRPRE_URGENTEPROGRAMA = new Variable("13822284", "99G2", new Long(13822284),
            "Tipo de programación");

    public final Variable VAR_HOJACIRPRE_INTERVENCION = new Variable("71388002", "SNM3", new Long(14124584),
            "Intervención");
    public final Variable VAR_HOJACIRPRE_DIAGNOSTICO  = new Variable("8319008", "SNM3", new Long(7762568),
            "Diagnóstico");
    
    public final Variable VAR_HOJACIRPRE_CIRUJANO1 = new Variable("MEDICO", "99G2", new Long(2228), "Cirujano 1");
    public final Variable VAR_HOJACIRPRE_CIRUJANO2 = new Variable("10182", "99G2", new Long(10182), "Cirujano 2");
    public final Variable VAR_HOJACIRPRE_CIRUJANO3 = new Variable("???", "???", new Long(10182), "Cirujano 3");
    public final Variable VAR_HOJACIRPRE_ANESTESISTA = new Variable("88189002", "SNM3", new Long(13819464),
            "Anestesista");
    public final Variable VAR_HOJACIRPRE_INSTRUMENTISTA = new Variable("13822304", "99G2", new Long(13822304),
            "Instrumentista");
    public final Variable VAR_HOJACIRPRE_AUXILAR = new Variable("5275007", " ", new Long(13825744), "Auxiliar");
    public final Variable VAR_HOJACIRPRE_CIRCULANTE = new Variable("413854007", "SNM3", new Long(13818264),
            "Circulante");

    public final Variable VAR_HOJACIRPRE_CONSCINTE = new Variable("271591004", "SNM3", new Long(13822324),
            "Consciente");
    public final Variable VAR_HOJACIRPRE_INTUBADO = new Variable("129339002", "SNM3", new Long(13818636), "Intubado");
    public final Variable VAR_HOJACIRPRE_ORIENTADO = new Variable("247663003", "SNM3", new Long(13822325), "Orientado");
    public final Variable VAR_HOJACIRPRE_AGITADO = new Variable("13822326", "99G2", new Long(13822326), "Agitado");
    public final Variable VAR_HOJACIRPRE_NERVIOSO = new Variable("13822327", "99G2", new Long(13822327), "Nervioso");
    public final Variable VAR_HOJACIRPRE_ALERGIA = new ConstantesClinicas().VAR_ALERGIAS;

    public final Variable VAR_HOJACIRPRE_VIAVENOSA = new Variable("13822328", "99G2", new Long(13822328),
            "Via venosa tipo");
    public final Variable VAR_HOJACIRPRE_VIAVENOSALOCA = new Variable("35003986", "99G2 ", new Long(35003986),
            "Via venosa localización");
    public final Variable VAR_HOJACIRPRE_VIAARTERIAL = new Variable("13822331", "99G2", new Long(13822331),
            "Via Arterial");
    public final Variable VAR_HOJACIRPRE_VIAARTERIALLOCAL = new Variable("13818544", "99G2", new Long(13818544),
            "Via Arterial Localización");
    public final Variable VAR_HOJACIRPRE_SNG = new Variable("13822332", "99G2", new Long(13822332), "SNG");
    // public final Variable VAR_HOJACIRPRE_ = new Variable(" ", " ", new Long( ),
    // "intervención");

    public final Variable VAR_HOJACIRPRE_catCanualaTra = new Variable("401865000", "SNM3", new Long(35001566),
            "Portador de tubo de traqueostomía ");
    public final Variable VAR_HOJACIRPRE_catCanualaTraN = new Variable("13818645", "99G2", new Long(13818645),
            " Portador de cánula traqueostomía (número)");
    public final Variable VAR_HOJACIRPRE_catPeritoneal = new Variable("13818646", "99G2", new Long(13818646),
            "Portador de cateter peritoneal");
    public final Variable VAR_HOJACIRPRE_catPeritonealN = new Variable("13818647", "99G2", new Long(13818647),
            "Portador de cateter peritoneal (número)");
    public final Variable VAR_HOJACIRPRE_catUretral = new Variable("13818648", "99G2", new Long(13818648),
            "Portador cateter ureteral");
    public final Variable VAR_HOJACIRPRE_catUretralN = new Variable("13818649", "99G2", new Long(13818649),
            "Portador cateter ureteral(número)");
    public final Variable VAR_HOJACIRPRE_catDrenaSilicona = new Variable("13818650", "99G2", new Long(13818650),
            "Portador de drenaje de silicona (Blake)");
    public final Variable VAR_HOJACIRPRE_catDrenaSiliconaN = new Variable("13818651", "99G2", new Long(13818651),
            "Portador de drenaje de silicona (Blake) (número)");
    public final Variable VAR_HOJACIRPRE_catPenrose = new Variable("13818652", "99G2", new Long(13818652),
            "Portador de drenaje de Penrose");
    public final Variable VAR_HOJACIRPRE_catPenroseN = new Variable("13818653", "99G2", new Long(13818653),
            "Portador de drenaje de Penrose (número)");
    public final Variable VAR_HOJACIRPRE_catJacksonP = new Variable("13818654", "99G2", new Long(13818654),
            "Portador de drenaje Jackson Pratt");
    public final Variable VAR_HOJACIRPRE_catJacksonPN = new Variable("13818655", "99G2", new Long(13818655),
            "Portador de drenaje Jackson Pratt (número)");
    public final Variable VAR_HOJACIRPRE_catRedon = new Variable("13818656", "99G2", new Long(13818656),
            "Portador drenaje Redon");
    public final Variable VAR_HOJACIRPRE_catRedonN = new Variable("13818657", "99G2", new Long(13818657),
            "Portador drenaje  Redon (número)");
    public final Variable VAR_HOJACIRPRE_catSilastic = new Variable("13818658", "99G2", new Long(13818658),
            "Portador de implante de material de silastic");
    public final Variable VAR_HOJACIRPRE_catSilasticN = new Variable("13818659", "99G2", new Long(13818659),
            "Portador de implante de material de silastic (número)");
    public final Variable VAR_HOJACIRPRE_catGastrostomia = new Variable("13818660", "99G2", new Long(13818660),
            "Portador de sonda gastrostomía");
    public final Variable VAR_HOJACIRPRE_catGastrostomiaN = new Variable("13818661", "99G2", new Long(13818661),
            "Portador de sonda gastrostomía (número)");
    public final Variable VAR_HOJACIRPRE_catNefrostomia = new Variable("13818662", "99G2", new Long(13818662),
            "Portador sonda nefrostomía");
    public final Variable VAR_HOJACIRPRE_catNefrostomiaN = new Variable("13818663", "99G2", new Long(13818663),
            "Portador sonda nefrostomía (número)");
    public final Variable VAR_HOJACIRPRE_catTallaVesical = new Variable("13818664", "99G2", new Long(13818664),
            "Portador talla vesical");
    public final Variable VAR_HOJACIRPRE_catTallaVesicalN = new Variable("13818665", "99G2", new Long(13818665),
            "Portador talla vesical (número) ");
    public final Variable VAR_HOJACIRPRE_catTuboKherr = new Variable("13818666", "99G2", new Long(13818666),
            "Portador de tubo de Kherr");
    public final Variable VAR_HOJACIRPRE_catTuboKherrN = new Variable("13818667", "99G2", new Long(13818667),
            "Portador de tubo de Kherr (número)");
    public final Variable VAR_HOJACIRPRE_catTuboTorax = new Variable("13818668", "99G2", new Long(13818668),
            "Portador de tubo de torax");
    public final Variable VAR_HOJACIRPRE_catTuboToraxN = new Variable("13818669", "99G2", new Long(13818669),
            "Portador de tubo de torax (número)");
    public final Variable VAR_HOJACIRPRE_sondaVesical = new Variable("13822344", "99G2", new Long(13822344),
            "Portador sonda vesical");

    public final Variable VAR_HOJACIRPRE_rasurado = new Variable("29923002", "SNM3", new Long(13820044), "Rasurado");
    public final Variable VAR_HOJACIRPRE_ayunas = new Variable("13822364", "99G2", new Long(13822364), "Ayunas");
    public final Variable VAR_HOJACIRPRE_retiradaObjPel = new Variable("13822365", "99G2", new Long(13822365),
            "Retirada objetos peligrosos");
    public final Variable VAR_HOJACIRPRE_protocolDiabetico = new Variable("13818266", "99G2", new Long(13818266),
            "Protocolo diabético");
    public final Variable VAR_HOJACIRPRE_identificacion = new Variable("370786008", "SNM3", new Long(13825666),
            "Identificación");
    public final Variable VAR_HOJACIRPRE_profilaxisAntibio = new Variable("422181004", "SNM3", new Long(35002803),
            "Profilaxis antibiótica");

    public final static Long PLANTILLLA_EDITOR = new Long(327662670);
    public final static Long TIPO_REGISTRO = new Long(4);
    
    /*
    OJO Estos valores tienen que coincidir con los de la tabla combos 
    select * from combos where grupo='QUIROFANOS' AND RAMA='PRGURG';
    */
    public final static String TIPOPROGRAMADO= "Programado";
    public final static String TIPOURGENTE= "Urgente";

    /*
	 * 8723-9 LN 279 Fecha y hora de inicio de intervencion 13822265 99G2 13822265
	 * numero de orden 225738002 SNM3 13819844 quirófano 284548004 SNM3 1809903
	 * servicio hospitalario 13822284 99G2 13822284 tipo de programación MEDICO 99G2
	 * 2228 Medico 10182 99G2 10182 Primer ayudante 413854007 SNM3 13818264
	 * enfermera circulante 88189002 SNM3 13819464 anestesiólogo (ocupación)
	 * 13822304 99G2 13822304 instrumentista 5275007 SNM3 13825744 Auxiliar de
	 * enfermería
	 * 
	 * 8319008 SNM3 7762568 diagnóstico principal 71388002 SNM3 14124584
	 * procedimiento 271591004 SNM3 13822324 consciente 129339002 SNM3 13818636
	 * intubación (acción) 247663003 SNM3 13822325 orientado 13822326 99G2 13822326
	 * agitado 13822327 99G2 13822327 nervioso 13822328 99G2 13822328 portador de
	 * vía venosa 106190000 SNM3 13524547 alergia 35003986 99G2 35003986
	 * Localización vía venosa periférica 13822331 99G2 13822331 portador de vía
	 * arterial 13818544 99G2 13818544 portador vía arterial (localización) 13822332
	 * 99G2 13822332 portador S.N.G. 401865000 SNM3 35001566 Portador de tubo de
	 * traqueostomía 13818645 99G2 13818645 Portador de cánula traqueostomía
	 * (número) 13818646 99G2 13818646 Portador de cateter peritoneal 13818647 99G2
	 * 13818647 Portador de cateter peritoneal (número) 13818648 99G2 13818648
	 * Portador cateter ureteral 13818649 99G2 13818649 Portador cateter ureteral
	 * (número) 13818650 99G2 13818650 Portador de drenaje de silicona (Blake)
	 * 13818651 99G2 13818651 Portador de drenaje de silicona (Blake) (número)
	 * 13818652 99G2 13818652 Portador de drenaje de Penrose 13818653 99G2 13818653
	 * Portador de drenaje de Penrose (número) 13818654 99G2 13818654 Portador de
	 * drenaje Jackson Pratt 13818655 99G2 13818655 Portador de drenaje Jackson
	 * Pratt (número) 13818662 99G2 13818662 Portador sonda nefrostomía 13818656
	 * 99G2 13818656 Portador drenaje Redon 13818657 99G2 13818657 Portador drenaje
	 * Redon (número) 13818663 99G2 13818663 Portador sonda nefrostomía (número)
	 * 13818658 99G2 13818658 Portador de implante de material de silastic 13818664
	 * 99G2 13818664 Portador talla vesical 13818659 99G2 13818659 Portador de
	 * implante de material de silastic (número) 13818665 99G2 13818665 Portador
	 * talla vesical (número) 13818660 99G2 13818660 Portador de sonda gastrostomía
	 * 13818666 99G2 13818666 Portador de tubo de Kherr 13818661 99G2 13818661
	 * Portador de sonda gastrostomía (número) 13818667 99G2 13818667 Portador de
	 * tubo de Kherr (número) 13818668 99G2 13818668 Portador de tubo de torax
	 * 13818669 99G2 13818669 Portador de tubo de torax (número) 13822344 99G2
	 * 13822344 portador sonda vesical 29923002 SNM3 13820044 rasurado 13822364 99G2
	 * 13822364 ayunas 13822365 99G2 13822365 retirada objetos peligrosos 13818266
	 * 99G2 13818266 Protocolo diabético 370786008 SNM3 13825666 Confirmación de la
	 * identidad del paciente previa intervención quirúrgica 422181004 SNM3 35002803
	 * * Profilaxis antibiótica
     */
    public RegistroQuiHojaCirculaPre() {
        iniciaQuipre();
    }

    public RegistroQuiHojaCirculaPre(Long id) {
        super(id);
        iniciaQuipre();
    }

    public RegistroQuiHojaCirculaPre(RegistroQuiHojaCirculaPre r) {
        super(r);
        this.fechaHoraInter = r.getFechaHoraInter();
        this.quirofano = r.getQuirofano();
        this.orden = r.getOrden();
        this.servicioQui = r.getServicioQui();
        this.urgenteProgramado = r.getUrgenteProgramado();
        this.intervencion = r.getIntervencion();
        this.diagnostico=r.getDiagnostico();
        this.cirujano1 = r.getCirujano1();
        this.cirujano2 = r.getCirujano2();
        this.cirujano3 = r.getCirujano3();
        this.anestesista = r.getAnestesista();
        this.instrumentista = r.getInstrumentista();
        this.circulante = r.getCirculante();
        this.auxiliar = r.getAuxiliar();

        this.consciente = r.getConsciente();
        this.intubado = r.getIntubado();
        this.orientado = r.getOrientado();
        this.agitado = r.getAgitado();
        this.nervioso = r.getNervioso();

        this.alergias = r.getAlergias();

        this.viaVenosaTipo = r.getViaVenosaTipo();
        this.viaVenosaLocali = r.getViaVenosaTipo();
        this.viaArterialSN = r.getViaArterialSN();
        this.viaArterialLocali = r.getViaArterialLocali();
        this.sng = r.getSng();

        this.catCanualaTra = r.getCatCanualaTra();
        this.catCanualaTraN = r.getCatCanualaTraN();
        this.catPeritoneal = r.getCatPeritoneal();
        this.catPeritonealN = r.getCatPeritonealN();
        this.catUretral = r.getCatUretral();
        this.catUretralN = r.getCatUretralN();
        this.catDrenaSilicona = r.getCatDrenaSilicona();
        this.catDrenaSiliconaN = r.getCatDrenaSiliconaN();
        this.catPenrose = r.getCatPenrose();
        this.catPenroseN = r.getCatPenroseN();
        this.catJacksonP = r.getCatJacksonP();
        this.catJacksonPN = r.getCatJacksonPN();
        this.catRedon = r.getCatRedon();
        this.catRedonN = r.getCatRedonN();
        this.catSilastic = r.getCatSilastic();
        this.catSilasticN = r.getCatSilasticN();
        this.catGastrostomia = r.getCatGastrostomia();
        this.catGastrostomiaN = r.getCatGastrostomiaN();
        this.catNefrostomia = r.getCatNefrostomia();
        this.catNefrostomiaN = r.getCatNefrostomiaN();
        this.catTallaVesical = r.getCatTallaVesical();
        this.catTallaVesicalN = r.getCatTallaVesicalN();
        this.catTuboKherr = r.getCatTuboKherr();
        this.catTuboKherrN = r.getCatTuboKherrN();
        this.catTuboTorax = r.catTuboTorax;
        this.catTuboToraxN = r.getCatTuboToraxN();

        this.sondaVesical = r.getSondaVesical();

        this.rasurado = r.getRasurado();
        this.ayunas = r.getAyunas();
        this.retiradaObjPel = r.getRetiradaObjPel();
        this.protocolDiabetico = r.getProtocolDiabetico();
        this.identificacion = r.getIdentificacion();
        this.profilaxisAntibio = r.getProfilaxisAntibio();

    }

    public void iniciaQuipre() {
        this.plantilla_editor = PLANTILLLA_EDITOR;
        this.tiporegistro = TIPO_REGISTRO;
        this.descripcion = "Hoja Circulante Prequirófano";
        // this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y
        // Ginecologia"));

        // this.fechaHoraInter = VAR_HOJACIRPRE_FECHAHORA;
        this.quirofano = VAR_HOJACIRPRE_QUIROFANO;
        this.orden = VAR_HOJACIRPRE_ORDEN;
        this.servicioQui = VAR_HOJACIRPRE_SERVICIO;
        this.urgenteProgramado = VAR_HOJACIRPRE_URGENTEPROGRAMA;
        this.intervencion = VAR_HOJACIRPRE_INTERVENCION;
        this.diagnostico=VAR_HOJACIRPRE_DIAGNOSTICO;
        this.cirujano1 = VAR_HOJACIRPRE_CIRUJANO1;
        this.cirujano2 = VAR_HOJACIRPRE_CIRUJANO2;
        this.cirujano3 = VAR_HOJACIRPRE_CIRUJANO3;
        this.anestesista = VAR_HOJACIRPRE_ANESTESISTA;
        this.instrumentista = VAR_HOJACIRPRE_INSTRUMENTISTA;
        this.circulante = VAR_HOJACIRPRE_CIRCULANTE;
        this.auxiliar = VAR_HOJACIRPRE_AUXILAR;

        this.consciente = VAR_HOJACIRPRE_CONSCINTE;
        this.intubado = VAR_HOJACIRPRE_INTUBADO;
        this.orientado = VAR_HOJACIRPRE_ORIENTADO;
        this.agitado = VAR_HOJACIRPRE_AGITADO;
        this.nervioso = VAR_HOJACIRPRE_NERVIOSO;
        this.alergias = VAR_HOJACIRPRE_ALERGIA;

        this.viaVenosaTipo = VAR_HOJACIRPRE_VIAVENOSA;
        this.viaVenosaLocali = VAR_HOJACIRPRE_VIAVENOSALOCA;
        this.viaArterialSN = VAR_HOJACIRPRE_VIAARTERIAL;
        this.viaArterialLocali = VAR_HOJACIRPRE_VIAARTERIALLOCAL;
        this.sng = VAR_HOJACIRPRE_SNG;

        this.catCanualaTra = VAR_HOJACIRPRE_catCanualaTra;
        this.catCanualaTraN = VAR_HOJACIRPRE_catCanualaTraN;
        this.catPeritoneal = VAR_HOJACIRPRE_catPeritoneal;
        this.catPeritonealN = VAR_HOJACIRPRE_catPeritonealN;
        this.catUretral = VAR_HOJACIRPRE_catUretral;
        this.catUretralN = VAR_HOJACIRPRE_catUretralN;
        this.catDrenaSilicona = VAR_HOJACIRPRE_catDrenaSilicona;
        this.catDrenaSiliconaN = VAR_HOJACIRPRE_catDrenaSiliconaN;
        this.catPenrose = VAR_HOJACIRPRE_catPenrose;
        this.catPenroseN = VAR_HOJACIRPRE_catPenroseN;
        this.catJacksonP = VAR_HOJACIRPRE_catJacksonP;
        this.catJacksonPN = VAR_HOJACIRPRE_catJacksonPN;
        this.catRedon = VAR_HOJACIRPRE_catRedon;
        this.catRedonN = VAR_HOJACIRPRE_catRedonN;
        this.catSilastic = VAR_HOJACIRPRE_catSilastic;
        this.catSilasticN = VAR_HOJACIRPRE_catSilasticN;
        this.catGastrostomia = VAR_HOJACIRPRE_catGastrostomia;
        this.catGastrostomiaN = VAR_HOJACIRPRE_catGastrostomiaN;
        this.catNefrostomia = VAR_HOJACIRPRE_catNefrostomia;
        this.catNefrostomiaN = VAR_HOJACIRPRE_catNefrostomiaN;
        this.catTallaVesical = VAR_HOJACIRPRE_catTallaVesical;
        this.catTallaVesicalN = VAR_HOJACIRPRE_catTallaVesicalN;
        this.catTuboKherr = VAR_HOJACIRPRE_catTuboKherr;
        this.catTuboKherrN = VAR_HOJACIRPRE_catTuboKherrN;
        this.catTuboTorax = VAR_HOJACIRPRE_catTuboTorax;
        this.catTuboToraxN = VAR_HOJACIRPRE_catTuboToraxN;

        this.sondaVesical = VAR_HOJACIRPRE_sondaVesical;

        this.rasurado = VAR_HOJACIRPRE_rasurado;
        this.ayunas = VAR_HOJACIRPRE_ayunas;
        this.retiradaObjPel = VAR_HOJACIRPRE_retiradaObjPel;
        this.protocolDiabetico = VAR_HOJACIRPRE_protocolDiabetico;
        this.identificacion = VAR_HOJACIRPRE_identificacion;
        this.profilaxisAntibio = VAR_HOJACIRPRE_profilaxisAntibio;

    }

    public LocalDateTime getFechaHoraInterTime() {
        return fechaHoraInterTime;
    }

    public void setFechaHoraInterTime(LocalDateTime fechaHoraInterTime) {
        this.fechaHoraInterTime = fechaHoraInterTime;
    }

    public Variable getFechaHoraInter() {
        return fechaHoraInter;
    }

    public void setFechaHoraInter(Variable fechaHoraInter) {
        this.fechaHoraInter = fechaHoraInter;
    }

    public Variable getQuirofano() {
        return quirofano;
    }

    public Variable getVariableQuirofano() {
        return quirofano;
    }

    public String getQuirofanoString() {
        return quirofano.getValor();
    }

    public void setQuirofano(Variable quirofano) {
        this.quirofano = quirofano;
    }

    public void setQuirofano(String valor) {
        this.quirofano.setValor(valor);
    }

    public Variable getOrden() {
        return orden;
    }

    public Variable getVariableOrden() {
        return orden;
    }

    public String getOrdenStrig() {
        return orden.getValor();
    }

    public void setOrden(Variable orden) {
        this.orden = orden;
    }

    public void setOrden(String valor) {
        this.orden.setValor(valor);
    }

    public Variable getServicioQui() {
        return servicioQui;
    }

    public Variable getVariableServicioQui() {
        return servicioQui;
    }

    public String getServicioQuiString() {
        return servicioQui.getValor();
    }

    public void setServicioQui(Variable servicioQui) {
        this.servicioQui = servicioQui;
    }

    public void setServicioQui(String valor) {
        this.servicioQui.setValor(valor);
    }

    public Variable getUrgenteProgramado() {
        return urgenteProgramado;
    }

    public Variable getVariableUrgenteProgramado() {
        return urgenteProgramado;
    }

    public String getUrgenteProgramadoString() {
        return urgenteProgramado.getValor();
    }

    public void setUrgenteProgramado(Variable urgenteProgramado) {
        this.urgenteProgramado = urgenteProgramado;
    }

    public void setUrgenteProgramado(String valor) {
        this.urgenteProgramado.setValor(valor);
    }

    public Variable getIntervencion() {
        return intervencion;
    }

    public Variable getVariableIntervencion() {
        return intervencion;
    }

    public String getIntervencionString() {
        return intervencion.getValor();
    }

    public void setIntervencion(Variable intervencion) {
        this.intervencion = intervencion;
    }

    public void setIntervencion(String valor) {
        this.intervencion.setValor(valor);
    }

    public Variable getDiagnostico() {
        return diagnostico;
    }
    public Variable getVariableDiagnostico() {
        return diagnostico;
    }
    public String getDiagnosticoString() {
        return diagnostico.getValor();
    }

    public void setDiagnostico(Variable diagnostico) {
        this.diagnostico = diagnostico;
    }
    public void setDiagnostico(String valor) {
        this.diagnostico.setValor(valor);
    }

    
    public Variable getCirujano1() {
        return cirujano1;
    }

    public Variable getVariableCirujano1() {
        return cirujano1;
    }

    public String getCirujano1String() {
        return cirujano1.getValor();
    }

    public void setCirujano1(Variable cirujano1) {
        this.cirujano1 = cirujano1;
    }

    public void setCirujano1(String valor) {
        this.cirujano1.setValor(valor);
    }

    public Variable getCirujano2() {
        return cirujano2;
    }

    public Variable getVariableCirujano2() {
        return cirujano2;
    }

    public String getCirujano2String() {
        return cirujano2.getValor();
    }

    public void setCirujano2(Variable cirujano2) {
        this.cirujano2 = cirujano2;
    }

    public void setCirujano2(String valor) {
        this.cirujano2.setValor(valor);
    }

    public Variable getCirujano3() {
        return cirujano3;
    }

    public Variable getVariableCirujano3() {
        return cirujano3;
    }

    public String getCirujano3String() {
        return cirujano3.getValor();
    }

    public void setCirujano3(Variable cirujano3) {
        this.cirujano3 = cirujano3;
    }

    public void setCirujano3(String valor) {
        this.cirujano3.setValor(valor);
    }

    public Variable getAnestesista() {
        return anestesista;
    }

    public Variable getVariableAnestesista() {
        return anestesista;
    }

    public String getAnestesistaString() {
        return anestesista.getValor();
    }

    public void setAnestesista(Variable anestesista) {
        this.anestesista = anestesista;
    }

    public void setAnestesista(String valor) {
        this.anestesista.setValor(valor);
    }

    public Variable getInstrumentista() {
        return instrumentista;
    }

    public Variable getVariableInstrumentista() {
        return instrumentista;
    }

    public String getInstrumentistaString() {
        return instrumentista.getValor();
    }

    public void setInstrumentista(Variable instrumentista) {
        this.instrumentista = instrumentista;
    }

    public void setInstrumentista(String valor) {
        this.instrumentista.setValor(valor);
    }

    public Variable getCirculante() {
        return circulante;
    }

    public Variable getVariableCirculante() {
        return circulante;
    }

    public String getCirculanteString() {
        return circulante.getValor();
    }

    public void setCirculante(Variable circulante) {
        this.circulante = circulante;
    }

    public void setCirculante(String valor) {
        this.circulante.setValor(valor);
    }

    public Variable getAuxiliar() {
        return auxiliar;
    }

    public Variable getVariableAuxiliar() {
        return auxiliar;
    }

    public String getAuxiliarString() {
        return auxiliar.getValor();
    }

    public void setAuxiliar(Variable auxiliar) {
        this.auxiliar = auxiliar;
    }

    public void setAuxiliar(String valor) {
        this.auxiliar.setValor(valor);
    }

    public Variable getConsciente() {
        return consciente;
    }

    public Variable getVariableConsciente() {
        return consciente;
    }

    public Boolean getConscienteBoolean() {
        if (consciente != null && !consciente.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setConsciente(Variable consciente) {
        this.consciente = consciente;
    }

    public void setConsciente(Boolean valor) {
        if (valor == true) {
            this.consciente.setValor("Consciente");
        } else {
            this.consciente.setValor("");
        }
    }

    public Variable getIntubado() {
        return intubado;
    }

    public Variable getVariableIntubado() {
        return intubado;
    }

    public Boolean getIntubadoBoolean() {
        if (intubado != null && !intubado.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIntubado(Variable intubado) {
        this.intubado = intubado;
    }

    public void setIntubado(Boolean valor) {
        if (valor == true) {
            this.intubado.setValor("Intubado");
        } else {
            this.intubado.setValor("");
        }
    }

    public Variable getOrientado() {
        return orientado;
    }

    public Variable getVariableOrientado() {
        return orientado;
    }

    public Boolean getOrientadoBoolean() {
        if (orientado != null && !orientado.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setOrientado(Variable orientado) {
        this.orientado = orientado;
    }

    public void setOrientado(Boolean valor) {
        if (valor == true) {
            this.orientado.setValor("Orientado");
        } else {
            this.orientado.setValor("");
        }
    }

    public Variable getAgitado() {
        return agitado;
    }

    public Variable getVariableAgitado() {
        return agitado;
    }

    public Boolean getAgitadoBoolean() {
        if (agitado != null && !agitado.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAgitado(Variable agitado) {
        this.agitado = agitado;
    }

    public void setAgitado(Boolean valor) {
        if (valor == true) {
            this.agitado.setValor("Agitado");
        } else {
            this.agitado.setValor("");
        }
    }

    public Variable getNervioso() {
        return nervioso;
    }

    public Variable getVariableNervioso() {
        return nervioso;
    }

    public Boolean getNerviosoBoolean() {
        if (nervioso != null && !nervioso.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setNervioso(Variable nervioso) {
        this.nervioso = nervioso;
    }

    public void setNervioso(Boolean valor) {
        if (valor == true) {
            this.nervioso.setValor("Nervioso");
        } else {
            this.nervioso.setValor("");
        }
    }

    public Variable getAlergias() {
        return alergias;
    }

    public Variable getVariableAlergias() {
        return alergias;
    }

    public String getAlergiasString() {
        return alergias.getValor();
    }

    public void setAlergias(Variable alergias) {
        this.alergias = alergias;
    }

    public void setAlergias(String valor) {
        this.alergias.setValor(valor);
    }

    public Variable getViaVenosaTipo() {
        return viaVenosaTipo;
    }

    public Variable getVariableViaVenosaTipo() {
        return viaVenosaTipo;
    }

    public String getViaVenosaTipoString() {
        return viaVenosaTipo.getValor();
    }

    public void setViaVenosaTipo(Variable viaVenosaTipo) {
        this.viaVenosaTipo = viaVenosaTipo;
    }

    public void setViaVenosaTipo(String valor) {
        this.viaVenosaTipo.setValor(valor);
    }

    public Variable getViaVenosaLocali() {
        return viaVenosaLocali;
    }

    public Variable getVariableViaVenosaLocali() {
        return viaVenosaLocali;
    }

    public String getViaVenosaLocaliString() {
        return viaVenosaLocali.getValor();
    }

    public void setViaVenosaLocali(Variable viaVenosaLocali) {
        this.viaVenosaLocali = viaVenosaLocali;
    }

    public void setViaVenosaLocali(String valor) {
        this.viaVenosaLocali.setValor(valor);
        ;
    }

    public Variable getViaArterialSN() {
        return viaArterialSN;
    }

    public Variable getVariableViaArterialSN() {
        return viaArterialSN;
    }

    public Boolean getViaArterialSNBollean() {
        if (viaArterialSN != null && !viaArterialSN.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setViaArterialSN(Variable viaArterialSN) {
        this.viaArterialSN = viaArterialSN;
    }

    public void setViaArterialSN(Boolean valor) {
        if (valor == true) {
            this.viaArterialSN.setValor("Via arterial");
        } else {
            this.viaArterialSN.setValor("");
        }
    }

    public Variable getViaArterialLocali() {
        return viaArterialLocali;
    }

    public Variable getVariableViaArterialLocali() {
        return viaArterialLocali;
    }

    public String getViaArterialLocaliString() {
        return viaArterialLocali.getValor();
    }

    public void setViaArterialLocali(Variable viaArterialLocali) {
        this.viaArterialLocali = viaArterialLocali;
    }

    public void setViaArterialLocali(String valor) {
        this.viaArterialLocali.setValor(valor);
        ;
    }

    public Variable getSng() {
        return sng;
    }

    public Variable getVariableSng() {
        return sng;
    }

    public Boolean getSngBoolean() {
        if (sng != null && !sng.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSng(Variable sng) {
        this.sng = sng;
    }

    public void setSng(Boolean valor) {
        if (valor == true) {
            this.sng.setValor("SNG");
        } else {
            this.sng.setValor("");
        }
    }

    public Variable getCatCanualaTra() {
        return catCanualaTra;
    }

    public Variable getVariableCatCanualaTra() {
        return catCanualaTra;
    }

    public Boolean getCatCanualaTraBoolean() {
        if (catCanualaTra != null && !catCanualaTra.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatCanualaTra(Variable catCanualaTra) {
        this.catCanualaTra = catCanualaTra;
    }

    public void setCatCanualaTra(Boolean valor) {
        if (valor == true) {
            this.catCanualaTra.setValor("Portador de cánula traqueostomía");
        } else {
            this.catCanualaTra.setValor("");
        }
    }

    public Variable getCatCanualaTraN() {
        return catCanualaTraN;
    }

    public Variable getVariableCatCanualaTraN() {
        return catCanualaTraN;
    }

    public String getCatCanualaTraNString() {
        return catCanualaTraN.getValor();
    }

    public void setCatCanualaTraN(Variable catCanualaTraN) {
        this.catCanualaTraN = catCanualaTraN;
    }

    public void setCatCanualaTraN(String valor) {
        this.catCanualaTraN.setValor(valor);
    }

    public Variable getCatPeritoneal() {
        return catPeritoneal;
    }

    public Variable getVariableCatPeritoneal() {
        return catPeritoneal;
    }

    public Boolean getCatPeritonealBoolean() {
        if (catPeritoneal != null && !catPeritoneal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatPeritoneal(Variable catPeritoneal) {
        this.catPeritoneal = catPeritoneal;
    }

    public void setCatPeritoneal(Boolean valor) {
        if (valor == true) {
            this.catPeritoneal.setValor("Portador de catéter peritoneal");
        } else {
            this.catPeritoneal.setValor("");
        }
    }

    public Variable getCatPeritonealN() {
        return catPeritonealN;
    }

    public Variable getVariableCatPeritonealN() {
        return catPeritonealN;
    }

    public String getCatPeritonealNString() {
        return catPeritonealN.getValor();
    }

    public void setCatPeritonealN(Variable catPeritonealN) {
        this.catPeritonealN = catPeritonealN;
    }

    public void setCatPeritonealN(String valor) {
        this.catPeritonealN.setValor(valor);
    }

    public Variable getCatUretral() {
        return catUretral;
    }

    public Variable getVariableCatUretral() {
        return catUretral;
    }

    public Boolean getCatUretralBoolean() {
        if (catUretral != null && !catUretral.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatUretral(Variable catUretral) {
        this.catUretral = catUretral;
    }

    public void setCatUretral(Boolean valor) {
        if (valor == true) {
            this.catUretral.setValor("Portador de catéter uretral");
        } else {
            this.catUretral.setValor("");
        }
    }

    public Variable getCatUretralN() {
        return catUretralN;
    }

    public Variable getVariableCatUretralN() {
        return catUretralN;
    }

    public String getCatUretralNString() {
        return catUretralN.getValor();
    }

    public void setCatUretralN(Variable catUretralN) {
        this.catUretralN = catUretralN;
    }

    public void setCatUretralN(String valor) {
        this.catUretralN.setValor(valor);
    }

    public Variable getCatDrenaSilicona() {
        return catDrenaSilicona;
    }

    public Variable getVariableCatDrenaSilicona() {
        return catDrenaSilicona;
    }

    public Boolean getCatDrenaSiliconaBoolean() {
        if (catDrenaSilicona != null && !catDrenaSilicona.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatDrenaSilicona(Variable catDrenaSilicona) {
        this.catDrenaSilicona = catDrenaSilicona;
    }

    public void setCatDrenaSilicona(boolean valor) {
        if (valor == true) {
            this.catDrenaSilicona.setValor("Portador de drenaje de silicona ");
        } else {
            this.catDrenaSilicona.setValor("");
        }
    }

    public Variable getCatDrenaSiliconaN() {
        return catDrenaSiliconaN;
    }

    public Variable getVariableCatDrenaSiliconaN() {
        return catDrenaSiliconaN;
    }

    public String getCatDrenaSiliconaNString() {
        return catDrenaSiliconaN.getValor();
    }

    public void setCatDrenaSiliconaN(Variable catDrenaSiliconaN) {
        this.catDrenaSiliconaN = catDrenaSiliconaN;
    }

    public void setCatDrenaSiliconaN(String valor) {
        this.catDrenaSiliconaN.setValor(valor);
    }

    public Variable getCatPenrose() {
        return catPenrose;
    }

    public Variable getVariableCatPenrose() {
        return catPenrose;
    }

    public Boolean getCatPenroseBoolean() {
        if (catPenrose != null && !catPenrose.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatPenrose(Variable catPenrose) {
        this.catPenrose = catPenrose;
    }

    public void setCatPenrose(Boolean valor) {
        if (valor == true) {
            this.catPenrose.setValor("Portador de Penrose");
        } else {
            this.catPenrose.setValor("");
        }
    }

    public Variable getCatPenroseN() {
        return catPenroseN;
    }

    public Variable getVariableCatPenroseN() {
        return catPenroseN;
    }

    public String getCatPenroseNString() {
        return catPenroseN.getValor();
    }

    public void setCatPenroseN(Variable catPenroseN) {
        this.catPenroseN = catPenroseN;
    }

    public void setCatPenroseN(String valor) {
        this.catPenroseN.setValor(valor);
    }

    public Variable getCatJacksonP() {
        return catJacksonP;
    }

    public Variable getVariableCatJacksonP() {
        return catJacksonP;
    }

    public Boolean getCatJacksonPBoolean() {
        if (catJacksonP != null && !catJacksonP.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatJacksonP(Variable catJacksonP) {
        this.catJacksonP = catJacksonP;
    }

    public void setCatJacksonP(Boolean valor) {
        if (valor == true) {
            this.catJacksonP.setValor("Portador de Jackson Pratt");
        } else {
            this.catJacksonP.setValor("");
        }
    }

    public Variable getCatJacksonPN() {
        return catJacksonPN;
    }

    public Variable getVariableCatJacksonPN() {
        return catJacksonPN;
    }

    public String getCatJacksonPNString() {
        return catJacksonPN.getValor();
    }

    public void setCatJacksonPN(Variable catJacksonPN) {
        this.catJacksonPN = catJacksonPN;
    }

    public void setCatJacksonPN(String valor) {
        this.catJacksonPN.setValor(valor);
    }

    public Variable getCatRedon() {
        return catRedon;
    }

    public Variable getVariableCatRedon() {
        return catRedon;
    }

    public Boolean getCatRedonBoolean() {
        if (catRedon != null && !catRedon.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatRedon(Variable catRedon) {
        this.catRedon = catRedon;
    }

    public void setCatRedon(Boolean valor) {
        if (valor == true) {
            this.catRedon.setValor("Portador de redon");
        } else {
            this.catRedon.setValor("");
        }
    }

    public Variable getCatRedonN() {
        return catRedonN;
    }

    public Variable getVariableCatRedonN() {
        return catRedonN;
    }

    public String getCatRedonNString() {
        return catRedonN.getValor();
    }

    public void setCatRedonN(Variable catRedonN) {
        this.catRedonN = catRedonN;
    }

    public void setCatRedonN(String valor) {
        this.catRedonN.setValor(valor);
    }

    public Variable getCatSilastic() {
        return catSilastic;
    }

    public Variable getVariableCatSilastic() {
        return catSilastic;
    }

    public Boolean getCatSilasticBoolean() {
        if (catSilastic != null && !catSilastic.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatSilastic(Variable catSilastic) {
        this.catSilastic = catSilastic;
    }

    public void setCatSilastic(Boolean valor) {
        if (valor == true) {
            this.catSilastic.setValor("Portador de catéter de silicona");
        } else {
            this.catSilastic.setValor("");
        }
    }

    public Variable getCatSilasticN() {
        return catSilasticN;
    }

    public Variable getVariableCatSilasticN() {
        return catSilasticN;
    }

    public String getCatSilasticNString() {
        return catSilasticN.getValor();
    }

    public void setCatSilasticN(Variable catSilasticN) {
        this.catSilasticN = catSilasticN;
    }

    public void setCatSilasticN(String valor) {
        this.catSilasticN.setValor(valor);
    }

    public Variable getCatGastrostomia() {
        return catGastrostomia;
    }

    public Variable getVariableCatGastrostomia() {
        return catGastrostomia;
    }

    public Boolean getCatGastrostomiaBoolean() {
        if (catGastrostomia != null && !catGastrostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatGastrostomia(Variable catGastrostomia) {
        this.catGastrostomia = catGastrostomia;
    }

    public void setCatGastrostomia(Boolean valor) {
        if (valor == true) {
            this.catGastrostomia.setValor("Portador de sonda gastrostomía");
        } else {
            this.catGastrostomia.setValor("");
        }
    }

    public Variable getCatGastrostomiaN() {
        return catGastrostomiaN;
    }

    public Variable getVariableCatGastrostomiaN() {
        return catGastrostomiaN;
    }

    public String getCatGastrostomiaNString() {
        return catGastrostomiaN.getValor();
    }

    public void setCatGastrostomiaN(Variable catGastrostomiaN) {
        this.catGastrostomiaN = catGastrostomiaN;
    }

    public void setCatGastrostomiaN(String valor) {
        this.catGastrostomiaN.setValor(valor);
    }

    public Variable getCatNefrostomia() {
        return catNefrostomia;
    }

    public Variable getVariableCatNefrostomia() {
        return catNefrostomia;
    }

    public Boolean getCatNefrostomiaBoolean() {
        if (catNefrostomia != null && !catNefrostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatNefrostomia(Variable catNefrostomia) {
        this.catNefrostomia = catNefrostomia;
    }

    public void setCatNefrostomia(Boolean valor) {
        if (valor == true) {
            this.catNefrostomia.setValor("Portador de sonda nefrostomía");
        } else {
            this.catNefrostomia.setValor("");
        }
    }

    public Variable getCatNefrostomiaN() {
        return catNefrostomiaN;
    }

    public Variable getVariableCatNefrostomiaN() {
        return catNefrostomiaN;
    }

    public String getCatNefrostomiaNString() {
        return catNefrostomiaN.getValor();
    }

    public void setCatNefrostomiaN(Variable catNefrostomiaN) {
        this.catNefrostomiaN = catNefrostomiaN;
    }

    public void setCatNefrostomiaN(String valor) {
        this.catNefrostomiaN.setValor(valor);
    }

    public Variable getCatTallaVesical() {
        return catTallaVesical;
    }

    public Variable getVariableCatTallaVesical() {
        return catTallaVesical;
    }

    public Boolean getCatTallaVesicalBoolean() {
        if (catTallaVesical != null && !catTallaVesical.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatTallaVesical(Variable catTallaVesical) {
        this.catTallaVesical = catTallaVesical;
    }

    public void setCatTallaVesical(Boolean valor) {
        if (valor == true) {
            this.catTallaVesical.setValor("Portador de talla vesical");
        } else {
            this.catTallaVesical.setValor("");
        }
    }

    public Variable getCatTallaVesicalN() {
        return catTallaVesicalN;
    }

    public Variable getVariableCatTallaVesicalN() {
        return catTallaVesicalN;
    }

    public String getCatTallaVesicalNString() {
        return catTallaVesicalN.getValor();
    }

    public void setCatTallaVesicalN(Variable catTallaVesicalN) {
        this.catTallaVesicalN = catTallaVesicalN;
    }

    public void setCatTallaVesicalN(String valor) {
        this.catTallaVesicalN.setValor(valor);
    }

    public Variable getCatTuboKherr() {
        return catTuboKherr;
    }

    public Variable getVariableCatTuboKherr() {
        return catTuboKherr;
    }

    public Boolean getCatTuboKherrBoolean() {
        if (catTuboKherr != null && !catTuboKherr.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatTuboKherr(Variable catTuboKherr) {
        this.catTuboKherr = catTuboKherr;
    }

    public void setCatTuboKherr(Boolean valor) {
        if (valor == true) {
            this.catTuboKherr.setValor("Portador de tubo d Kherr");
        } else {
            this.catTuboKherr.setValor("");
        }
    }

    public Variable getCatTuboKherrN() {
        return catTuboKherrN;
    }

    public Variable getVariableCatTuboKherrN() {
        return catTuboKherrN;
    }

    public String getCatTuboKherrNString() {
        return catTuboKherrN.getValor();
    }

    public void setCatTuboKherrN(Variable catTuboKherrN) {
        this.catTuboKherrN = catTuboKherrN;
    }

    public void setCatTuboKherrN(String valor) {
        this.catTuboKherrN.setValor(valor);
    }

    public Variable getCatTuboTorax() {
        return catTuboTorax;
    }

    public Variable getVariableCatTuboTorax() {
        return catTuboTorax;
    }

    public Boolean getCatTuboToraxBoolean() {
        if (catTuboTorax != null && !catTuboTorax.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCatTuboTorax(Variable catTuboTorax) {
        this.catTuboTorax = catTuboTorax;
    }

    public void setCatTuboTorax(Boolean valor) {
        if (valor == true) {
            this.catTuboTorax.setValor("Portador de tubo de tórax");
        } else {
            this.catTuboTorax.setValor("");
        }
    }

    public Variable getCatTuboToraxN() {
        return catTuboToraxN;
    }

    public Variable getVariableCatTuboToraxN() {
        return catTuboToraxN;
    }

    public String getCatTuboToraxNString() {
        return catTuboToraxN.getValor();
    }

    public void setCatTuboToraxN(Variable catTuboToraxN) {
        this.catTuboToraxN = catTuboToraxN;
    }

    public void setCatTuboToraxN(String valor) {
        this.catTuboToraxN.setValor(valor);
    }

    public Variable getSondaVesical() {
        return sondaVesical;
    }

    public Variable getVariableSondaVesical() {
        return sondaVesical;
    }

    public String getSondaVesicalString() {
        return sondaVesical.getValor();
    }

    public void setSondaVesical(Variable sondaVesical) {
        this.sondaVesical = sondaVesical;
    }

    public void setSondaVesical(String valor) {
        this.sondaVesical.setValor(valor);
    }

    public Variable getRasurado() {
        return rasurado;
    }

    public Variable getVariableRasurado() {
        return rasurado;
    }

    public Boolean getRasuradoBoolean() {
        if (rasurado != null && !rasurado.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRasurado(Variable rasurado) {
        this.rasurado = rasurado;
    }

    public void setRasurado(Boolean valor) {
        if (valor == true) {
            this.rasurado.setValor("Rasurado confirmado");
        } else {
            this.rasurado.setValor("");
        }
    }

    public Variable getAyunas() {
        return ayunas;
    }

    public Variable getVariableAyunas() {
        return ayunas;
    }

    public Boolean getAyunasBoolean() {
        if (ayunas != null && !ayunas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAyunas(Variable ayunas) {
        this.ayunas = ayunas;
    }

    public void setAyunas(Boolean valor) {
        if (valor == true) {
            this.ayunas.setValor("Ayunas confirmado");
        } else {
            this.ayunas.setValor("");
        }
    }

    public Variable getRetiradaObjPel() {
        return retiradaObjPel;
    }

    public Variable getVariableRetiradaObjPel() {
        return retiradaObjPel;
    }

    public Boolean getRetiradaObjPelBoolean() {
        if (retiradaObjPel != null && !retiradaObjPel.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRetiradaObjPel(Variable retiradaObjPel) {
        this.retiradaObjPel = retiradaObjPel;
    }

    public void setRetiradaObjPel(Boolean valor) {
        if (valor == true) {
            this.retiradaObjPel.setValor("Retirada de objetos peligrosos ");
        } else {
            this.retiradaObjPel.setValor("");
        }
    }

    public Variable getProtocolDiabetico() {
        return protocolDiabetico;
    }

    public Variable getVariableProtocolDiabetico() {
        return protocolDiabetico;
    }

    public Boolean getProtocolDiabeticoBoolean() {
        if (protocolDiabetico != null && !protocolDiabetico.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setProtocolDiabetico(Variable protocolDiabetico) {
        this.protocolDiabetico = protocolDiabetico;
    }

    public void setProtocolDiabetico(Boolean valor) {
        if (valor == true) {
            this.protocolDiabetico.setValor("Protocolo diabético ");
        } else {
            this.protocolDiabetico.setValor("");
        }
    }

    public Variable getIdentificacion() {
        return identificacion;
    }

    public Variable getVariableIdentificacion() {
        return identificacion;
    }

    public Boolean getIdentificacionBoolean() {
        if (identificacion != null && !identificacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIdentificacion(Variable identificacion) {
        this.identificacion = identificacion;
    }

    public void setIdentificacion(Boolean valor) {
        if (valor == true) {
            this.identificacion.setValor("Identificación realizada ");
        } else {
            this.identificacion.setValor("");
        }
    }

    public Variable getProfilaxisAntibio() {
        return profilaxisAntibio;
    }

    public Variable getVariableProfilaxisAntibio() {
        return profilaxisAntibio;
    }

    public Boolean getProfilaxisAntibioBoolean() {
        if (profilaxisAntibio != null && !profilaxisAntibio.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setProfilaxisAntibio(Variable profilaxisAntibio) {
        this.profilaxisAntibio = profilaxisAntibio;
    }

    public void setProfilaxisAntibio(Boolean valor) {
        if (valor == true) {
            this.profilaxisAntibio.setValor("Profilaxis antibiótica realizada  ");
        } else {
            this.profilaxisAntibio.setValor("");
        }
    }
}

/*
 * 
 * Select * from ( SELECT 1, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='LN' and
 * code='8723-9' UNION
 * 
 * SELECT 2, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822265' UNION
 * -- valores=" ,QUI1,QUI2,QUI3,QUI4,QUI5,QUI6,QUI7,QUI8,QUI9" SELECT 3,
 * code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='225738002' UNION
 * SELECT 4, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='284548004' UNION
 * 
 * SELECT 5, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822284' UNION
 * SELECT 6, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='MEDICO' UNION
 * 
 * SELECT 7, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='10182' UNION
 * 
 * SELECT 8, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='88189002' UNION
 * 
 * SELECT 9, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822304' UNION
 * SELECT 8, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='413854007' UNION
 * SELECT 9, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='5275007' UNION
 * 
 * SELECT 10, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='8319008' UNION
 * SELECT 11, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='71388002' UNION
 * SELECT 12, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='271591004' UNION
 * SELECT 13, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='129339002' UNION
 * SELECT 14, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='247663003' UNION
 * SELECT 15, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822326' UNION
 * SELECT 16, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822327' UNION
 * SELECT 17, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='106190000' UNION
 * --
 * valores=" ,perifÃ©rica Drum,         Abocath nÂº 14,Abocath nÂº 16,Abocath nÂº 18,Abocath nÂº 20,Abocath nÂº 22,         VÃ­a central de dos vias 20 cm,VÃ­a central de dos vias 30 cm,VÃ­a central de tres vias,         Yugular nÂº 14,Yugular nÂº 16,Yugular nÂº 18,Yugular nÂº 20,Yugular nÂº 22,Port a cath"
 * SELECT 16, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822328' UNION
 * 
 * SELECT 17, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='35003986' UNION
 * SELECT 18, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822331' UNION
 * SELECT 19, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818544' UNION
 * -- valores=" ,nÂº 10, nÂº 12, nÂº 14, nÂº 16, nÂº 18" SELECT 20,
 * code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822332' UNION
 * 
 * SELECT 21, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='401865000' UNION
 * SELECT 22, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818645' UNION
 * SELECT 23, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818646' UNION
 * SELECT 24, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818647' UNION
 * SELECT 25, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818648' UNION
 * SELECT 26, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818649' UNION
 * 
 * 
 * SELECT 27, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818650' UNION
 * SELECT 28, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818651' UNION
 * SELECT 29, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818652' UNION
 * SELECT 30, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818653' UNION
 * SELECT 31, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818654' UNION
 * SELECT 32, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818655' UNION
 * 
 * SELECT 32, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818656' UNION
 * SELECT 33, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818657' UNION
 * SELECT 34, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818658' UNION
 * SELECT 35, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818659' UNION
 * SELECT 36, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818660' UNION
 * SELECT 37, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818661' UNION
 * 
 * 
 * SELECT 32, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818662' UNION
 * SELECT 33, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818663' UNION
 * SELECT 34, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818664' UNION
 * SELECT 35, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818665' UNION
 * SELECT 36, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818666' UNION
 * SELECT 37, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818667' UNION
 * SELECT 38, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818668' UNION
 * SELECT 39, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818669' UNION
 * --valores=" ,Foley nÂº 14, Foley nÂº 16, Foley nÂº 18, Foley nÂº 20, Foley
 * nÂº 22, -- Silicona nÂº 14, Silicona nÂº 16, Silicona nÂº 18, Silicona nÂº
 * 20, Silicona nÂº 22, -- Sonda de tres vÃ­as dofour nÂº 18, Sonda de tres
 * vÃ­as dofour nÂº 20, Sonda de tres vÃ­as dofour nÂº 22, Sonda de tres vÃ­as
 * dofour nÂº 24, -- Sonda de dofour recta nÂº 18,tubo de torax, cateter
 * ureteral,cateter peritoneal,talla
 * vesical,gastrostomÃ­a,colostomÃ­a,ileostomÃ­a, -- traqueotomÃ­a,tubo de kher"
 * SELECT 40, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822344' UNION
 * 
 * SELECT 41, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='29923002' UNION
 * SELECT 42, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822364' UNION
 * SELECT 43, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13822365' UNION
 * SELECT 44, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818266' UNION
 * SELECT 45, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='370786008' UNION
 * SELECT 46, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='422181004'
 * 
 * ) ORDER BY 1
 */
