package es.sacyl.hnss.entity;

public class RegistroQuiHojaCirculaPost extends Registro {

    private Variable horaSalida;
    private Variable horaFinalizacion;
    private Variable reseccionInicio;
    private Variable reseccionFin;
    private Variable reseccionVejiga;
    private Variable reseccionProstata;
    private Variable reseccionUtero;

    private Variable isquemiaInicio;
    private Variable isquemiaFin;
    private Variable isquemiaLugar;

    private Variable escopiaTiempo;

    private Variable contaje;

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

    private Variable implantes;
    private Variable implantesModelo;
    private Variable implantesNSerie;
    private Variable implantesPotencia;

    private Variable biopsiaIntra;
    private Variable biopsiaIntraMuestras;
    private Variable piezaAnatomia;
    private Variable piezaMuestras;
    private Variable muestraMicro;
    private Variable muestraMuestras;
    private Variable citologia;
    private Variable citologiaMuestras;
    private Variable analitica;
    private Variable analiticaMuestras;

    private Variable colocaAposito;
    private Variable colaboraExtubacion;
    private Variable aspiraSecreciones;
    private Variable retiradaComplementos;
    private Variable retirarPlacaB;
    private Variable retirarVia;
    private Variable oxigenoterapia;

    private Variable retirarSondaVesi;
    private Variable sondaVesicc;
    private Variable retirarSondaNsg;
    private Variable sondaNsgcc;

    private Variable trasladocama;
    private Variable acompañamiento;
    private Variable destino;
    private Variable observaciones;

    public final Variable VAR_HOJACIRPST_horaSalida = new Variable("13818309", "99G2", new Long(13818309),
            "Hora de salida");
    public final Variable VAR_HOJACIRPST_horaFinalizacion = new Variable("10176", "99G2", new Long(10176),
            "Hora de Finalización");

    public final Variable VAR_HOJACIRPST_reseccionInicio = new Variable("13818288", "99G2", new Long(13818288),
            "Resección (Hora de inicio) ");
    public final Variable VAR_HOJACIRPST_reseccionFin = new Variable("13818289", "99G2", new Long(13818289),
            "Resección  (Hora final) ");
    public final Variable VAR_HOJACIRPST_reseccionVejiga = new Variable("13818444", "99G2", new Long(13818444),
            "Tiempos de resección (vejiga)");
    public final Variable VAR_HOJACIRPST_reseccionProstata = new Variable("13818445", "99G2", new Long(13818445),
            "Tiempos de resección (próstata)");
    public final Variable VAR_HOJACIRPST_reseccionUtero = new Variable("13818469", "99G2", new Long(13818469),
            "Tiempos  de resección (útero)");

    public final Variable VAR_HOJACIRPST_isquemiaInicio = new Variable("13818290", "99G2", new Long(13818290),
            "Isquemia (Hora de inicio) ");
    public final Variable VAR_HOJACIRPST_isquemiaFin = new Variable("13818291", "99G2", new Long(13818291),
            "Isquemia (Hora final) ");
    public final Variable VAR_HOJACIRPST_isquemiaLugar = new Variable("13818292", "99G2", new Long(13818292),
            "Isquemia (Lugar) ");

    public final Variable VAR_HOJACIRPST_escopiaTiempo = new Variable("13818293", "99G2", new Long(13818293),
            " Escopia (Tiempo total)");
    public final Variable VAR_HOJACIRPST_contaje = new Variable("13825713", "99G2", new Long(13825713),
            "Revisión de material o equipos quirúrgicos ");

    public final Variable VAR_HOJACIRPST_catCanualaTra = new Variable("401865000", "SNM3", new Long(35001566),
            "Portador de tubo de traqueostomía ");
    public final Variable VAR_HOJACIRPST_catCanualaTraN = new Variable("13818645", "99G2", new Long(13818645),
            " Portador de cánula traqueostomía (número)");
    public final Variable VAR_HOJACIRPST_catPeritoneal = new Variable("13818646", "99G2", new Long(13818646),
            "Portador de cateter peritoneal");
    public final Variable VAR_HOJACIRPST_catPeritonealN = new Variable("13818647", "99G2", new Long(13818647),
            "Portador de cateter peritoneal (número)");
    public final Variable VAR_HOJACIRPST_catUretral = new Variable("13818648", "99G2", new Long(13818648),
            "Portador cateter ureteral");
    public final Variable VAR_HOJACIRPST_catUretralN = new Variable("13818649", "99G2", new Long(13818649),
            "Portador cateter ureteral(número)");
    public final Variable VAR_HOJACIRPST_catDrenaSilicona = new Variable("13818650", "99G2", new Long(13818650),
            "Portador de drenaje de silicona (Blake)");
    public final Variable VAR_HOJACIRPST_catDrenaSiliconaN = new Variable("13818651", "99G2", new Long(13818651),
            "Portador de drenaje de silicona (Blake) (número)");
    public final Variable VAR_HOJACIRPST_catPenrose = new Variable("13818652", "99G2", new Long(13818652),
            "Portador de drenaje de Penrose");
    public final Variable VAR_HOJACIRPST_catPenroseN = new Variable("13818653", "99G2", new Long(13818653),
            "Portador de drenaje de Penrose (número)");
    public final Variable VAR_HOJACIRPST_catJacksonP = new Variable("13818654", "99G2", new Long(13818654),
            "Portador de drenaje Jackson Pratt");
    public final Variable VAR_HOJACIRPST_catJacksonPN = new Variable("13818655", "99G2", new Long(13818655),
            "Portador de drenaje Jackson Pratt (número)");
    public final Variable VAR_HOJACIRPST_catRedon = new Variable("13818656", "99G2", new Long(13818656),
            "Portador drenaje Redon");
    public final Variable VAR_HOJACIRPST_catRedonN = new Variable("13818657", "99G2", new Long(13818657),
            "Portador drenaje  Redon (número)");
    public final Variable VAR_HOJACIRPST_catSilastic = new Variable("13818658", "99G2", new Long(13818658),
            "Portador de implante de material de silastic");
    public final Variable VAR_HOJACIRPST_catSilasticN = new Variable("13818659", "99G2", new Long(13818659),
            "Portador de implante de material de silastic (número)");
    public final Variable VAR_HOJACIRPST_catGastrostomia = new Variable("13818660", "99G2", new Long(13818660),
            "Portador de sonda gastrostomía");
    public final Variable VAR_HOJACIRPST_catGastrostomiaN = new Variable("13818661", "99G2", new Long(13818661),
            "Portador de sonda gastrostomía (número)");
    public final Variable VAR_HOJACIRPST_catNefrostomia = new Variable("13818662", "99G2", new Long(13818662),
            "Portador sonda nefrostomía");
    public final Variable VAR_HOJACIRPST_catNefrostomiaN = new Variable("13818663", "99G2", new Long(13818663),
            "Portador sonda nefrostomía (número)");
    public final Variable VAR_HOJACIRPST_catTallaVesical = new Variable("13818664", "99G2", new Long(13818664),
            "Portador talla vesical");
    public final Variable VAR_HOJACIRPST_catTallaVesicalN = new Variable("13818665", "99G2", new Long(13818665),
            "Portador talla vesical (número) ");
    public final Variable VAR_HOJACIRPST_catTuboKherr = new Variable("13818666", "99G2", new Long(13818666),
            "Portador de tubo de Kherr");
    public final Variable VAR_HOJACIRPST_catTuboKherrN = new Variable("13818667", "99G2", new Long(13818667),
            "Portador de tubo de Kherr (número)");
    public final Variable VAR_HOJACIRPST_catTuboTorax = new Variable("13818668", "99G2", new Long(13818668),
            "Portador de tubo de torax");
    public final Variable VAR_HOJACIRPST_catTuboToraxN = new Variable("13818669", "99G2", new Long(13818669),
            "Portador de tubo de torax (número)");

    public final Variable VAR_HOJACIRPST_implantes = new Variable("53350007", "99G2", new Long(53350007),
            "Implantes/prótesis");
    public final Variable VAR_HOJACIRPST_implantesModelo = new Variable("13819085", "99G2", new Long(13819085),
            "Implantes modelo");
    public final Variable VAR_HOJACIRPST_implantesNSerie = new Variable("13819225", "99G2", new Long(13819225),
            "Implante: nº de serie");
    public final Variable VAR_HOJACIRPST_implantesPotencia = new Variable("13819086", "99G2", new Long(13819086),
            "Implante: potencia ");

    public final Variable VAR_HOJACIRPST_biopsiaIntra = new Variable("8721-3", "LN", new Long(0), "  ");
    public final Variable VAR_HOJACIRPST_biopsiaIntraMuestras = new Variable("13818472", "99G2", new Long(13818472),
            "Biopsia intraoperatoria (número de muestras)");
    public final Variable VAR_HOJACIRPST_piezaAnatomia = new Variable("271360006", "SNM3", new Long(13818375),
            "Epécimen para anatomía patológica  ");
    public final Variable VAR_HOJACIRPST_piezaMuestras = new Variable("13818473", "99G2", new Long(13818473),
            "Especimen para Anatomía Patológica  (número de muestras)   ");
    public final Variable VAR_HOJACIRPST_muestraMicro = new Variable("13818474", "99G2", new Long(13818474),
            " Muestra enviada a microbiología  ");
    public final Variable VAR_HOJACIRPST_muestraMuestras = new Variable("13818475", "99G2", new Long(13818475),
            "Muestra enviada a microbiología (número de muestras)  ");
    public final Variable VAR_HOJACIRPST_citologia = new Variable("68440009", "SNM3", new Long(7750956),
            "citología general   ");
    public final Variable VAR_HOJACIRPST_citologiaMuestras = new Variable("13818476", "99G2", new Long(13818476),
            "Citologia (número de muestras)   ");
    public final Variable VAR_HOJACIRPST_analitica = new Variable("13818477", "99G2", new Long(13818477),
            "Analítica urgente  ");
    public final Variable VAR_HOJACIRPST_analiticaMuestras = new Variable("3818478", "99G2", new Long(3818478),
            "Analítica urgente (número de muestras)  ");

    public final Variable VAR_HOJACIRPST_colocaAposito = new Variable("225120005", "SNM3", new Long(13818635),
            "Aplicación de apósito en herida (procedimiento) ");
    public final Variable VAR_HOJACIRPST_colaboraExtubacion = new Variable("13818271", "99G2", new Long(13818271),
            "Colaborar en la extubación ");
    public final Variable VAR_HOJACIRPST_aspiraSecreciones = new Variable("13818307", "99G2", new Long(13818307),
            "Aspiración de secreciones en la extubación  ");
    public final Variable VAR_HOJACIRPST_retiradaComplementos = new Variable("13818272", "99G2", new Long(13818272),
            "Retirar complementos ");
    public final Variable VAR_HOJACIRPST_retirarPlacaB = new Variable("13818273", "99G2", new Long(13818273),
            "13818273 ");
    public final Variable VAR_HOJACIRPST_retirarVia = new Variable("13818274", "99G2", new Long(13818274),
            "Retirar vía  ");
    public final Variable VAR_HOJACIRPST_oxigenoterapia = new Variable("13818308", "99G2", new Long(13818308),
            "Oxigenoterapia en la extubación  ");

    public final Variable VAR_HOJACIRPST_retirarSondaVesi = new Variable("13818630", "99G2", new Long(13818630),
            "Retirar sonda  vesical  ");
    public final Variable VAR_HOJACIRPST_sondaVesicc = new Variable("13818631", "99G2", new Long(13818631),
            " Retirar sonda vesical (volumen)  ");
    public final Variable VAR_HOJACIRPST_retirarSondaNsg = new Variable("13818632", "99G2", new Long(13818632),
            "Retirar sonda NSG   ");
    public final Variable VAR_HOJACIRPST_sondaNsgcc = new Variable("13818633", "99G2", new Long(13818633),
            "Retirar sonda NSG  volumen ");

    public final Variable VAR_HOJACIRPST_trasladocama = new Variable("13818634", "99G2", new Long(13818634),
            "Traslado/vigilancia de mesa de quirófano a cama  ");
    public final Variable VAR_HOJACIRPST_acompañamiento = new Variable("13818275", "99G2", new Long(13818275),
            "Acompañar al paciente en el traslado ");
    public final Variable VAR_HOJACIRPST_destino = new Variable("11302-7", "LN", new Long(768), "Destino  ");
    public final Variable VAR_HOJACIRPST_observaciones = new Variable("246453008", "SNM3", new Long(46293677),
            "Observaciones  ");

    public final static Long PLANTILLLA_EDITOR = new Long(327664159);
    public final static Long TIPO_REGISTRO = new Long(4);

    /*
	 * 13818309 99G2 13818309 Hora de salida 10176 99G2 10176 Hora de Fin 13818288
	 * 99G2 13818288 Resección (Hora de inicio) 13818289 99G2 13818289 Resección
	 * (Hora final) 13818444 99G2 13818444 Tiempos de resección (vejiga) 13818445
	 * 99G2 13818445 Tiempos de resección (próstata) 13818469 99G2 13818469 Tiempos
	 * de resección (útero) 13818290 99G2 13818290 Isquemia (Hora de inicio)
	 * 13818291 99G2 13818291 Isquemia (Hora final) 13818292 99G2 13818292 Isquemia
	 * (Lugar) 13818293 99G2 13818293 Escopia (Tiempo total) 13825713 99G2 13825713
	 * Revisión de material o equipos quirúrgicos 257462007 SNM3 13818604 cánula
	 * traqueal 31030004 SNM3 13818606 catéter peritoneal (objeto físico) 47528002
	 * SNM3 13818608 catéter ureteral 13818605 99G2 13818605 Cánula traqueostomía
	 * (número) 13818607 99G2 13818607 Cateter peritoneal (número) 13818609 99G2
	 * 13818609 Cateter ureteral (número) 13818610 99G2 13818610 Drenaje de silicona
	 * (Blake) 13818611 99G2 13818611 Drenaje de silicona (Blake) (número) 45901004
	 * SNM3 13818612 drenaje de Penrose (objeto físico) 13818613 99G2 13818613
	 * Drenaje de Penrose (número) 13818614 99G2 13818614 Drenaje Jackson Pratt
	 * 13818615 99G2 13818615 Drenaje Jackson Pratt (número) 13818617 99G2 13818617
	 * Drenaje Redon (número) 13818304 99G2 13818304 Drenaje con vacio 118373001
	 * SNM3 13818624 implante de material de silastic 360115008 SNM3 13818626 sonda
	 * de gastrostomía 13818625 99G2 13818625 implante de material de silastic
	 * (número) 13818627 99G2 13818627 Sonda de gastrostomía (número) 286628000 SNM3
	 * 13818618 sonda de nefrostomía 13818619 99G2 13818619 Sonda de nefrostomía
	 * (número) 13818620 99G2 13818620 Talla vesical 13818621 99G2 13818621 Talla
	 * vesical (número) 13818622 99G2 13818622 Tubo de Kherr 13818623 99G2 13818623
	 * Tubo de Kherr (número) 13818628 99G2 13818628 Tubo de torax 13818629 99G2
	 * 13818629 Tubo de torax (número) 53350007 SNM3 13825585 Prótesis 13819086 99G2
	 * 13819086 Implante: potencia 13819085 99G2 13819085 Implante: modelo 13819225
	 * 99G2 13819225 Implante: nº de serie 13818472 99G2 13818472 Biopsia
	 * intraoperatoria (número de muestras) 271360006 SNM3 13818375 espécimen para
	 * anatomía patológica 13818473 99G2 13818473 Especimen para Anatomía Patológica
	 * (número de muestras) 13818474 99G2 13818474 Muestra enviada a microbiología
	 * 13818475 99G2 13818475 Muestra enviada a microbiología (número de muestras)
	 * 168440009 SNM3 7750956 citología general 13818476 99G2 13818476 Citologia
	 * (número de muestras) 13818271 99G2 13818271 Colaborar en la extubación
	 * 13818477 99G2 13818477 Analítica urgente 13818307 99G2 13818307 Aspiración de
	 * secreciones en la extubación 13818478 99G2 13818478 Analítica urgente (número
	 * de muestras) 13818308 99G2 13818308 Oxigenoterapia en la extubación 225120005
	 * SNM3 13818635 aplicación de apósito en herida (procedimiento) 13818272 99G2
	 * 13818272 Retirar complementos 13818273 99G2 13818273 Retirar placa bisturí
	 * 13818274 99G2 13818274 Retirar vía 13818630 99G2 13818630 Retirar sonda
	 * vesical 13818631 99G2 13818631 Retirar sonda vesical (volumen) 13818632 99G2
	 * 13818632 Retirar sonda NSG 13818632 99G2 13818632 Retirar sonda NSG 13818634
	 * 99G2 13818634 Traslado/vigilancia de mesa de quirófano a cama 13818275 99G2
	 * 13818275 Acompañar al paciente en el traslado 11302-7 LN 768 Destino
	 * 246453008 SNM3 46293677 observaciones
     */
    public RegistroQuiHojaCirculaPost() {
        super();
        doIniciaHojaPost();
    }

    public RegistroQuiHojaCirculaPost(Long id) {
        super(id);
        doIniciaHojaPost();
    }

    public RegistroQuiHojaCirculaPost(RegistroQuiHojaCirculaPost r) {
        super(r);
        this.horaSalida = r.getHoraSalida();
        this.horaFinalizacion = r.getHoraFinalizacion();
        this.reseccionInicio = r.getReseccionInicio();
        this.reseccionFin = r.getReseccionFin();
        this.reseccionVejiga = r.getReseccionVejiga();
        this.reseccionProstata = r.getReseccionProstata();
        this.reseccionUtero = r.getReseccionUtero();

        this.isquemiaInicio = r.getIsquemiaInicio();
        this.isquemiaFin = r.getIsquemiaFin();
        this.isquemiaLugar = r.getIsquemiaLugar();

        this.escopiaTiempo = r.getEscopiaTiempo();

        this.contaje = r.getContaje();

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

        this.implantes = r.getImplantes();
        this.implantesModelo = r.getImplantesModelo();
        this.implantesNSerie = r.getImplantesNSerie();
        this.implantesPotencia = r.getImplantesPotencia();

        this.biopsiaIntra = r.getBiopsiaIntra();
        this.biopsiaIntraMuestras = r.getBiopsiaIntraMuestras();
        this.piezaAnatomia = r.getPiezaAnatomia();
        this.piezaMuestras = r.getPiezaMuestras();
        this.muestraMicro = r.getMuestraMicro();
        this.muestraMuestras = r.getMuestraMuestras();
        this.citologia = r.getCitologia();
        this.citologiaMuestras = r.getCitologiaMuestras();
        this.analitica = r.getAnalitica();
        this.analiticaMuestras = r.getAnaliticaMuestras();

        this.colocaAposito = r.getColocaAposito();
        this.colaboraExtubacion = r.getColaboraExtubacion();
        this.aspiraSecreciones = r.getAspiraSecreciones();
        this.retiradaComplementos = r.getRetiradaComplementos();
        this.retirarPlacaB = r.getRetirarPlacaB();
        this.retirarVia = r.getRetirarVia();
        this.oxigenoterapia = r.getOxigenoterapia();

        this.retirarSondaVesi = r.getRetirarSondaVesi();
        this.sondaVesicc = r.getSondaVesicc();
        this.retirarSondaNsg = r.getRetirarSondaNsg();
        this.sondaNsgcc = r.getSondaNsgcc();

        this.trasladocama = r.getTrasladocama();
        this.acompañamiento = r.getAcompañamiento();
        this.destino = r.getDestino();
        this.observaciones = r.getObservaciones();

    }

    public void doIniciaHojaPost() {
        this.plantilla_editor = PLANTILLLA_EDITOR;
        this.tiporegistro = TIPO_REGISTRO;
        this.descripcion = "Hoja Circulante Fase postquirúrgica";

        this.horaSalida = VAR_HOJACIRPST_horaSalida;
        this.horaFinalizacion = VAR_HOJACIRPST_horaFinalizacion;
        this.reseccionInicio = VAR_HOJACIRPST_reseccionInicio;
        this.reseccionFin = VAR_HOJACIRPST_reseccionFin;
        this.reseccionVejiga = VAR_HOJACIRPST_reseccionVejiga;
        this.reseccionProstata = VAR_HOJACIRPST_reseccionProstata;
        this.reseccionUtero = VAR_HOJACIRPST_reseccionUtero;

        this.isquemiaInicio = VAR_HOJACIRPST_isquemiaInicio;
        this.isquemiaFin = VAR_HOJACIRPST_isquemiaFin;
        this.isquemiaLugar = VAR_HOJACIRPST_isquemiaLugar;

        this.escopiaTiempo = VAR_HOJACIRPST_escopiaTiempo;

        this.contaje = VAR_HOJACIRPST_contaje;

        this.catCanualaTra = VAR_HOJACIRPST_catCanualaTra;
        this.catCanualaTraN = VAR_HOJACIRPST_catCanualaTraN;
        this.catPeritoneal = VAR_HOJACIRPST_catPeritoneal;
        this.catPeritonealN = VAR_HOJACIRPST_catPeritonealN;
        this.catUretral = VAR_HOJACIRPST_catUretral;
        this.catUretralN = VAR_HOJACIRPST_catUretralN;
        this.catDrenaSilicona = VAR_HOJACIRPST_catDrenaSilicona;
        this.catDrenaSiliconaN = VAR_HOJACIRPST_catDrenaSiliconaN;
        this.catPenrose = VAR_HOJACIRPST_catPenrose;
        this.catPenroseN = VAR_HOJACIRPST_catPenroseN;
        this.catJacksonP = VAR_HOJACIRPST_catJacksonP;
        this.catJacksonPN = VAR_HOJACIRPST_catJacksonPN;
        this.catRedon = VAR_HOJACIRPST_catRedon;
        this.catRedonN = VAR_HOJACIRPST_catRedonN;
        this.catSilastic = VAR_HOJACIRPST_catSilastic;
        this.catSilasticN = VAR_HOJACIRPST_catSilasticN;
        this.catGastrostomia = VAR_HOJACIRPST_catGastrostomia;
        this.catGastrostomiaN = VAR_HOJACIRPST_catGastrostomiaN;
        this.catNefrostomia = VAR_HOJACIRPST_catNefrostomia;
        this.catNefrostomiaN = VAR_HOJACIRPST_catNefrostomiaN;
        this.catTallaVesical = VAR_HOJACIRPST_catTallaVesical;
        this.catTallaVesicalN = VAR_HOJACIRPST_catTallaVesicalN;
        this.catTuboKherr = VAR_HOJACIRPST_catTuboKherr;
        this.catTuboKherrN = VAR_HOJACIRPST_catTuboKherrN;
        this.catTuboTorax = VAR_HOJACIRPST_catTuboTorax;
        this.catTuboToraxN = VAR_HOJACIRPST_catTuboToraxN;

        this.implantes = VAR_HOJACIRPST_implantes;
        this.implantesModelo = VAR_HOJACIRPST_implantesModelo;
        this.implantesNSerie = VAR_HOJACIRPST_implantesNSerie;
        this.implantesPotencia = VAR_HOJACIRPST_implantesPotencia;

        this.biopsiaIntra = VAR_HOJACIRPST_biopsiaIntra;
        this.biopsiaIntraMuestras = VAR_HOJACIRPST_biopsiaIntraMuestras;
        this.piezaAnatomia = VAR_HOJACIRPST_piezaAnatomia;
        this.piezaMuestras = VAR_HOJACIRPST_piezaMuestras;
        this.muestraMicro = VAR_HOJACIRPST_muestraMicro;
        this.muestraMuestras = VAR_HOJACIRPST_citologia;
        this.citologia = VAR_HOJACIRPST_citologia;
        this.citologiaMuestras = VAR_HOJACIRPST_citologiaMuestras;
        this.analitica = VAR_HOJACIRPST_analitica;
        this.analiticaMuestras = VAR_HOJACIRPST_analiticaMuestras;

        this.colocaAposito = VAR_HOJACIRPST_colocaAposito;
        this.colaboraExtubacion = VAR_HOJACIRPST_colaboraExtubacion;
        this.aspiraSecreciones = VAR_HOJACIRPST_aspiraSecreciones;
        this.retiradaComplementos = VAR_HOJACIRPST_retiradaComplementos;
        this.retirarPlacaB = VAR_HOJACIRPST_retirarPlacaB;
        this.retirarVia = VAR_HOJACIRPST_retirarVia;
        this.oxigenoterapia = VAR_HOJACIRPST_oxigenoterapia;

        this.retirarSondaVesi = VAR_HOJACIRPST_retirarSondaVesi;
        this.sondaVesicc = VAR_HOJACIRPST_sondaVesicc;
        this.retirarSondaNsg = VAR_HOJACIRPST_retirarSondaNsg;
        this.sondaNsgcc = VAR_HOJACIRPST_sondaNsgcc;

        this.trasladocama = VAR_HOJACIRPST_trasladocama;
        this.acompañamiento = VAR_HOJACIRPST_acompañamiento;
        this.destino = VAR_HOJACIRPST_destino;
        this.observaciones = VAR_HOJACIRPST_observaciones;

    }

    public Variable getHoraSalida() {
        return horaSalida;
    }

    public Variable getVariableHoraSalida() {
        return horaSalida;
    }

    public String getHoraSalidaString() {
        return horaSalida.getValor();
    }

    public void setHoraSalida(Variable horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraSalida(String valor) {
        this.horaSalida.setValor(valor);
    }

    public Variable getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public Variable getVariableHoraFinalizacion() {
        return horaFinalizacion;
    }

    public String getHoraFinalizacionString() {
        return horaFinalizacion.getValor();
    }

    public void setHoraFinalizacion(Variable horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }

    public void setHoraFinalizacion(String valor) {
        this.horaFinalizacion.setValor(valor);
    }

    public Variable getReseccionInicio() {
        return reseccionInicio;
    }

    public Variable getVariableReseccionInicio() {
        return reseccionInicio;
    }

    public String getReseccionInicioString() {
        return reseccionInicio.getValor();
    }

    public void setReseccionInicio(Variable reseccionInicio) {
        this.reseccionInicio = reseccionInicio;
    }

    public void setReseccionInicio(String valor) {
        this.reseccionInicio.setValor(valor);
    }

    public Variable getReseccionFin() {
        return reseccionFin;
    }

    public Variable getVariableReseccionFin() {
        return reseccionFin;
    }

    public String getReseccionFinString() {
        return reseccionFin.getValor();
    }

    public void setReseccionFin(Variable reseccionFin) {
        this.reseccionFin = reseccionFin;
    }

    public void setReseccionFin(String valor) {
        this.reseccionFin.setValor(valor);
    }

    public Variable getReseccionVejiga() {
        return reseccionVejiga;
    }

    public Variable getVariableReseccionVejiga() {
        return reseccionVejiga;
    }

    public Boolean getReseccionVejigabBoolean() {
        if (reseccionVejiga != null && !reseccionVejiga.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setReseccionVejiga(Variable reseccionVejiga) {
        this.reseccionVejiga = reseccionVejiga;
    }

    public void setReseccionVejiga(Boolean valor) {
        if (valor == true) {
            this.reseccionVejiga.setValor("Resección de vejiga");
        } else {
            this.reseccionVejiga.setValor("");
        }
    }

    public Variable getReseccionProstata() {
        return reseccionProstata;
    }

    public Variable getVariableReseccionProstata() {
        return reseccionProstata;
    }

    public Boolean getReseccionProstatabBoolean() {
        if (reseccionProstata != null && !reseccionProstata.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setReseccionProstata(Variable reseccionProstata) {
        this.reseccionProstata = reseccionProstata;
    }

    public void setReseccionProstata(Boolean valor) {
        if (valor == true) {
            this.reseccionProstata.setValor("Resección de próstata");
        } else {
            this.reseccionProstata.setValor("");
        }
    }

    public Variable getReseccionUtero() {
        return reseccionUtero;
    }

    public Variable getVariableReseccionUtero() {
        return reseccionUtero;
    }

    public Boolean getReseccionUterobBoolean() {
        if (reseccionUtero != null && !reseccionUtero.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setReseccionUtero(Variable reseccionUtero) {
        this.reseccionUtero = reseccionUtero;
    }

    public void setReseccionUtero(Boolean valor) {
        if (valor == true) {
            this.reseccionUtero.setValor("Resección de útero");
        } else {
            this.reseccionUtero.setValor("");
        }
    }

    public Variable getIsquemiaInicio() {
        return isquemiaInicio;
    }

    public Variable getVariableIsquemiaInicio() {
        return isquemiaInicio;
    }

    public String getIsquemiaInicioString() {
        return isquemiaInicio.getValor();
    }

    public void setIsquemiaInicio(Variable isquemiaInicio) {
        this.isquemiaInicio = isquemiaInicio;
    }

    public void setIsquemiaInicio(String valor) {
        this.isquemiaInicio.setValor(valor);
    }

    public Variable getIsquemiaFin() {
        return isquemiaFin;
    }

    public Variable getVariableIsquemiaFin() {
        return isquemiaFin;
    }

    public String getIsquemiaFinString() {
        return isquemiaFin.getValor();
    }

    public void setIsquemiaFin(Variable isquemiaFin) {
        this.isquemiaFin = isquemiaFin;
    }

    public void setIsquemiaFin(String valor) {
        this.isquemiaFin.setValor(valor);
    }

    public Variable getIsquemiaLugar() {
        return isquemiaLugar;
    }

    public Variable getVariableIsquemiaLugar() {
        return isquemiaLugar;
    }

    public String getIsquemiaLugarString() {
        return isquemiaLugar.getValor();
    }

    public void setIsquemiaLugar(Variable isquemiaLugar) {
        this.isquemiaLugar = isquemiaLugar;
    }

    public void setIsquemiaLugar(String valor) {
        this.isquemiaLugar.setValor(valor);
    }

    public Variable getEscopiaTiempo() {
        return escopiaTiempo;
    }

    public Variable getVariableEscopiaTiempo() {
        return escopiaTiempo;
    }

    public String getEscopiaTiemposString() {
        return escopiaTiempo.getValor();
    }

    public void setEscopiaTiempo(Variable escopiaTiempo) {
        this.escopiaTiempo = escopiaTiempo;
    }

    public void setEscopiaTiempo(String valor) {
        this.escopiaTiempo.setValor(valor);
    }

    public Variable getContaje() {
        return contaje;
    }

    public Variable getVariableContaje() {
        return contaje;
    }

    public String getContajeString() {
        return contaje.getValor();
    }

    public Boolean getContajebBoolean() {
        if (contaje != null && !contaje.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setContaje(Variable contaje) {
        this.contaje = contaje;
    }

    public void setContaje(String valor) {
        this.contaje.setValor(valor);
    }

    public void setContaje(Boolean valor) {
        if (valor == true) {
            this.contaje.setValor("Contaje correcto");
        } else {
            this.contaje.setValor("");
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

    public Variable getImplantes() {
        return implantes;
    }

    public Variable getVariableImplantes() {
        return implantes;
    }

    public String getImplantesString() {
        return implantes.getValor();
    }

    public void setImplantes(Variable implantes) {
        this.implantes = implantes;
    }

    public void setImplantes(String valor) {
        this.implantes.setValor(valor);
    }

    public Variable getImplantesModelo() {
        return implantesModelo;
    }

    public Variable getVariable() {
        return implantesModelo;
    }

    public String getImplantesModeloString() {
        return implantesModelo.getValor();
    }

    public void setImplantesModelo(Variable implantesModelo) {
        this.implantesModelo = implantesModelo;
    }

    public void setImplantesModelo(String valor) {
        this.implantesModelo.setValor(valor);
    }

    public Variable getImplantesNSerie() {
        return implantesNSerie;
    }

    public Variable getVariableImplantesNSerie() {
        return implantesNSerie;
    }

    public String getImplantesNSerieString() {
        return implantesNSerie.getValor();
    }

    public void setImplantesNSerie(Variable implantesNSerie) {
        this.implantesNSerie = implantesNSerie;
    }

    public void setImplantesNSerie(String valor) {
        this.implantesNSerie.setValor(valor);
    }

    public Variable getImplantesPotencia() {
        return implantesPotencia;
    }

    public Variable getVariableImplantesPotencia() {
        return implantesPotencia;
    }

    public String getImplantesPotenciaString() {
        return implantesPotencia.getValor();
    }

    public void setImplantesPotencia(Variable implantesPotencia) {
        this.implantesPotencia = implantesPotencia;
    }

    public void setImplantesPotencia(String valor) {
        this.implantesPotencia.setValor(valor);
    }

    public Variable getBiopsiaIntra() {
        return biopsiaIntra;
    }

    public Variable getVariableBiopsiaIntra() {
        return biopsiaIntra;
    }

    public Boolean getBiopsiaIntrabBoolean() {
        if (biopsiaIntra != null && !biopsiaIntra.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setBiopsiaIntra(Variable biopsiaIntra) {
        this.biopsiaIntra = biopsiaIntra;
    }

    public void setBiopsiaIntra(Boolean valor) {
        if (valor == true) {
            this.biopsiaIntra.setValor("Biopsia intraoperatoria");
        } else {
            this.biopsiaIntra.setValor("");
        }
    }

    public Variable getBiopsiaIntraMuestras() {
        return biopsiaIntraMuestras;
    }

    public Variable getVariableBiopsiaIntraMuestras() {
        return biopsiaIntraMuestras;
    }

    public String getBiopsiaIntraMuestrasString() {
        return biopsiaIntraMuestras.getValor();
    }

    public void setBiopsiaIntraMuestras(Variable biopsiaIntraMuestras) {
        this.biopsiaIntraMuestras = biopsiaIntraMuestras;
    }

    public void setBiopsiaIntraMuestras(String valor) {
        this.biopsiaIntraMuestras.setValor(valor);
    }

    public Variable getPiezaAnatomia() {
        return piezaAnatomia;
    }

    public Variable getVariablePiezaAnatomia() {
        return piezaAnatomia;
    }

    public Boolean getPiezaAnatomiabBoolean() {
        if (piezaAnatomia != null && !piezaAnatomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPiezaAnatomia(Variable piezaAnatomia) {
        this.piezaAnatomia = piezaAnatomia;
    }

    public void setPiezaAnatomia(Boolean valor) {
        if (valor == true) {
            this.piezaAnatomia.setValor("Piezas para anatomía");
        } else {
            this.piezaAnatomia.setValor("");
        }
    }

    public Variable getPiezaMuestras() {
        return piezaMuestras;
    }

    public Variable getVariablePiezaMuestras() {
        return piezaMuestras;
    }

    public String getPiezaMuestrasString() {
        return piezaMuestras.getValor();
    }

    public void setPiezaMuestras(Variable piezaMuestras) {
        this.piezaMuestras = piezaMuestras;
    }

    public void setPiezaMuestras(String valor) {
        this.piezaMuestras.setValor(valor);
    }

    public Variable getMuestraMicro() {
        return muestraMicro;
    }

    public Variable getVariableMuestraMicro() {
        return muestraMicro;
    }

    public Boolean getMuestraMicrobBoolean() {
        if (muestraMicro != null && !muestraMicro.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMuestraMicro(Variable muestraMicro) {
        this.muestraMicro = muestraMicro;
    }

    public void setMuestraMicro(Boolean valor) {
        if (valor == true) {
            this.muestraMicro.setValor("Muestra para microbiología");
        } else {
            this.muestraMicro.setValor("");
        }
    }

    public Variable getMuestraMuestras() {
        return muestraMuestras;
    }

    public Variable getVariableMuestraMuestras() {
        return muestraMuestras;
    }

    public String getMuestraMuestrasString() {
        return muestraMuestras.getValor();
    }

    public void setMuestraMuestras(Variable muestraMuestras) {
        this.muestraMuestras = muestraMuestras;
    }

    public void setMuestraMuestras(String valor) {
        this.muestraMuestras.setValor(valor);
    }

    public Variable getCitologia() {
        return citologia;
    }

    public Variable getVariableCitologia() {
        return citologia;
    }

    public Boolean getCitologiaBoolean() {
        if (citologia != null && !citologia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCitologia(Variable citologia) {
        this.citologia = citologia;
    }

    public void setCitologia(Boolean valor) {
        if (valor == true) {
            this.citologia.setValor("Muestras para citología");
        } else {
            this.citologia.setValor("");
        }

    }

    public Variable getCitologiaMuestras() {
        return citologiaMuestras;
    }

    public Variable getVariableCitologiaMuestras() {
        return citologiaMuestras;
    }

    public String getCitologiaMuestrasString() {
        return citologiaMuestras.getValor();
    }

    public void setCitologiaMuestras(Variable citologiaMuestras) {
        this.citologiaMuestras = citologiaMuestras;
    }

    public void setCitologiaMuestras(String valor) {
        this.citologiaMuestras.setValor(valor);
    }

    public Variable getAnalitica() {
        return analitica;
    }

    public Variable getVariableAnalitica() {
        return analitica;
    }

    public Boolean getAnaliticaBoolean() {
        if (analitica != null && !analitica.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAnalitica(Variable analitica) {
        this.analitica = analitica;
    }

    public void setAnalitica(Boolean valor) {
        if (valor == true) {
            this.analitica.setValor("Muestras para analítica");
        } else {
            this.analitica.setValor("");
        }
    }

    public Variable getAnaliticaMuestras() {
        return analiticaMuestras;
    }

    public Variable getVariableAnaliticaMuestras() {
        return analiticaMuestras;
    }

    public String getAnaliticaMuestrasString() {
        return analiticaMuestras.getValor();
    }

    public void setAnaliticaMuestras(Variable analiticaMuestras) {
        this.analiticaMuestras = analiticaMuestras;
    }

    public void setAnaliticaMuestras(String valor) {
        this.analiticaMuestras.setValor(valor);
    }

    public Variable getColocaAposito() {
        return colocaAposito;
    }

    public Variable getVariableColocaAposito() {
        return colocaAposito;
    }

    public Boolean getColocaApositoBoolean() {
        if (colocaAposito != null && !colocaAposito.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setColocaAposito(Variable colocaAposito) {
        this.colocaAposito = colocaAposito;
    }

    public void setColocaAposito(Boolean valor) {
        if (valor == true) {
            this.colocaAposito.setValor("Colocar apósito");
        } else {
            this.colocaAposito.setValor("");
        }
    }

    public Variable getColaboraExtubacion() {
        return colaboraExtubacion;
    }

    public Variable getVariableColaboraExtubacion() {
        return colaboraExtubacion;
    }

    public Boolean getColaboraExtubacionBoolean() {
        if (colaboraExtubacion != null && !colaboraExtubacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setColaboraExtubacion(Variable colaboraExtubacion) {
        this.colaboraExtubacion = colaboraExtubacion;
    }

    public void setColaboraExtubacion(Boolean valor) {
        if (valor == true) {
            this.colaboraExtubacion.setValor("Colabora extubación");
        } else {
            this.colaboraExtubacion.setValor("");
        }
    }

    public Variable getAspiraSecreciones() {
        return aspiraSecreciones;
    }

    public Variable getVariableAspiraSecreciones() {
        return aspiraSecreciones;
    }

    public Boolean getAspiraSecrecionesBoolean() {
        if (aspiraSecreciones != null && !aspiraSecreciones.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAspiraSecreciones(Variable aspiraSecreciones) {
        this.aspiraSecreciones = aspiraSecreciones;
    }

    public void setAspiraSecreciones(Boolean valor) {
        if (valor == true) {
            this.aspiraSecreciones.setValor("Aspiración de secreciones");
        } else {
            this.aspiraSecreciones.setValor("");
        }
    }

    public Variable getRetiradaComplementos() {
        return retiradaComplementos;
    }

    public Variable getVariableRetiradaComplementos() {
        return retiradaComplementos;
    }

    public Boolean getRetiradaComplementosBoolean() {
        if (retiradaComplementos != null && !retiradaComplementos.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRetiradaComplementos(Variable retiradaComplementos) {
        this.retiradaComplementos = retiradaComplementos;
    }

    public void setRetiradaComplementos(Boolean valor) {
        if (valor == true) {
            this.retiradaComplementos.setValor("Retirada  de complementos");
        } else {
            this.retiradaComplementos.setValor("");
        }
    }

    public Variable getRetirarPlacaB() {
        return retirarPlacaB;
    }

    public Variable getVariableRetirarPlacaB() {
        return retirarPlacaB;
    }

    public Boolean getRetirarPlacaBBoolean() {
        if (retirarPlacaB != null && !retirarPlacaB.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRetirarPlacaB(Variable retirarPlacaB) {
        this.retirarPlacaB = retirarPlacaB;
    }

    public void setRetirarPlacaB(Boolean valor) {
        if (valor == true) {
            this.retirarPlacaB.setValor("Retirada  de placa bisturí");
        } else {
            this.retirarPlacaB.setValor("");
        }
    }

    public Variable getRetirarVia() {
        return retirarVia;
    }

    public Variable getVariableRetirarVia() {
        return retirarVia;
    }

    public Boolean getRetirarViabBoolean() {
        if (retirarVia != null && !retirarVia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRetirarVia(Variable retirarVia) {
        this.retirarVia = retirarVia;
    }

    public void setRetirarVia(Boolean valor) {
        if (valor == true) {
            this.retirarVia.setValor("Retirada  de vía ");
        } else {
            this.retirarVia.setValor("");
        }
    }

    public Variable getOxigenoterapia() {
        return oxigenoterapia;
    }

    public Variable getVariableOxigenoterapia() {
        return oxigenoterapia;
    }

    public String getOxigenoterapiaString() {
        return oxigenoterapia.getValor();
    }

    public void setOxigenoterapia(Variable oxigenoterapia) {
        this.oxigenoterapia = oxigenoterapia;
    }

    public void setOxigenoterapia(String valor) {
        this.oxigenoterapia.setValor(valor);
    }

    public Variable getRetirarSondaVesi() {
        return retirarSondaVesi;
    }

    public Variable getVariableRetirarSondaVesi() {
        return retirarSondaVesi;
    }

    public Boolean getRetirarSondaVesiBoolean() {
        if (retirarSondaVesi != null && !retirarSondaVesi.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRetirarSondaVesi(Variable retirarSondaVesi) {
        this.retirarSondaVesi = retirarSondaVesi;
    }

    public void setRetirarSondaVesi(Boolean valor) {
        if (valor == true) {
            this.retirarSondaVesi.setValor("Retirada sonda vesical");
        } else {
            this.retirarSondaVesi.setValor("");
        }
    }

    public Variable getSondaVesicc() {
        return sondaVesicc;
    }

    public Variable getVariableSondaVesicc() {
        return sondaVesicc;
    }

    public String getSondaVesiccString() {
        return sondaVesicc.getValor();
    }

    public void setSondaVesicc(Variable sondaVesicc) {
        this.sondaVesicc = sondaVesicc;
    }

    public void setSondaVesicc(String valor) {
        this.sondaVesicc.setValor(valor);
    }

    public Variable getRetirarSondaNsg() {
        return retirarSondaNsg;
    }

    public Variable getVariableRetirarSondaNsg() {
        return retirarSondaNsg;
    }

    public Boolean getRetirarSondaNsgBoolean() {
        if (retirarSondaNsg != null && !retirarSondaNsg.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRetirarSondaNsg(Variable retirarSondaNsg) {
        this.retirarSondaNsg = retirarSondaNsg;
    }

    public void setRetirarSondaNsg(Boolean valor) {
        if (valor == true) {
            this.retirarSondaNsg.setValor("Retirada SNG");
        } else {
            this.retirarSondaNsg.setValor("");
        }
    }

    public Variable getSondaNsgcc() {
        return sondaNsgcc;
    }

    public Variable getVariableSondaNsgcc() {
        return sondaNsgcc;
    }

    public String getSondaNsgccsString() {
        return sondaNsgcc.getValor();
    }

    public void setSondaNsgcc(Variable sondaNsgcc) {
        this.sondaNsgcc = sondaNsgcc;
    }

    public void setSondaNsgcc(String valor) {
        this.sondaNsgcc.setValor(valor);
    }

    public Variable getTrasladocama() {
        return trasladocama;
    }

    public Variable getVariableTrasladocama() {
        return trasladocama;
    }

    public Boolean getTrasladocamaBoolean() {
        if (trasladocama != null && !trasladocama.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTrasladocama(Variable trasladocama) {
        this.trasladocama = trasladocama;
    }

    public void setTrasladocama(Boolean valor) {
        if (valor == true) {
            this.trasladocama.setValor("Traslado vigilancia de mesa a  cama");
        } else {
            this.trasladocama.setValor("");
        }
    }

    public Variable getAcompañamiento() {
        return acompañamiento;
    }

    public Variable getVariableAcompañamiento() {
        return acompañamiento;
    }

    public Boolean getAcompañamientoBoolean() {
        if (acompañamiento != null && !acompañamiento.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAcompañamiento(Variable acompañamiento) {
        this.acompañamiento = acompañamiento;
    }

    public void setAcompañamiento(Boolean valor) {
        if (valor == true) {
            this.acompañamiento.setValor("Acompañamiento durante traslado");
        } else {
            this.acompañamiento.setValor("");
        }
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

    @Override
    public String toString() {
        return "RegistroQuiHojaCirculaPost [horaSalida=" + horaSalida + ", horaFinalizacion=" + horaFinalizacion
                + ", reseccionInicio=" + reseccionInicio + ", reseccionFin=" + reseccionFin + ", reseccionVejiga="
                + reseccionVejiga + ", reseccionProstata=" + reseccionProstata + ", reseccionUtero=" + reseccionUtero
                + ", isquemiaInicio=" + isquemiaInicio + ", isquemiaFin=" + isquemiaFin + ", isquemiaLugar="
                + isquemiaLugar + ", escopiaTiempo=" + escopiaTiempo + ", contaje=" + contaje + ", catCanualaTra="
                + catCanualaTra + ", catCanualaTraN=" + catCanualaTraN + ", catPeritoneal=" + catPeritoneal
                + ", catPeritonealN=" + catPeritonealN + ", catUretral=" + catUretral + ", catUretralN=" + catUretralN
                + ", catDrenaSilicona=" + catDrenaSilicona + ", catDrenaSiliconaN=" + catDrenaSiliconaN
                + ", catPenrose=" + catPenrose + ", catPenroseN=" + catPenroseN + ", catJacksonP=" + catJacksonP
                + ", catJacksonPN=" + catJacksonPN + ", catRedon=" + catRedon + ", catRedonN=" + catRedonN
                + ", catSilastic=" + catSilastic + ", catSilasticN=" + catSilasticN + ", catGastrostomia="
                + catGastrostomia + ", catGastrostomiaN=" + catGastrostomiaN + ", catNefrostomia=" + catNefrostomia
                + ", catNefrostomiaN=" + catNefrostomiaN + ", catTallaVesical=" + catTallaVesical
                + ", catTallaVesicalN=" + catTallaVesicalN + ", catTuboKherr=" + catTuboKherr + ", catTuboKherrN="
                + catTuboKherrN + ", catTuboTorax=" + catTuboTorax + ", catTuboToraxN=" + catTuboToraxN + ", implantes="
                + implantes + ", implantesModelo=" + implantesModelo + ", implantesNSerie=" + implantesNSerie
                + ", implantesPotencia=" + implantesPotencia + ", biopsiaIntra=" + biopsiaIntra
                + ", biopsiaIntraMuestras=" + biopsiaIntraMuestras + ", piezaAnatomia=" + piezaAnatomia
                + ", piezaMuestras=" + piezaMuestras + ", muestraMicro=" + muestraMicro + ", muestraMuestras="
                + muestraMuestras + ", citologia=" + citologia + ", citologiaMuestras=" + citologiaMuestras
                + ", analitica=" + analitica + ", analiticaMuestras=" + analiticaMuestras + ", colocaAposito="
                + colocaAposito + ", colaboraExtubacion=" + colaboraExtubacion + ", oxigenoterapia=" + oxigenoterapia
                + ", aspiraSecreciones=" + aspiraSecreciones + ", retiradaComplementos=" + retiradaComplementos
                + ", retirarPlacaB=" + retirarPlacaB + ", retirarVia=" + retirarVia + ", retirarSondaVesi="
                + retirarSondaVesi + ", sondaVesicc=" + sondaVesicc + ", retirarSondaNsg=" + retirarSondaNsg
                + ", sondaNsgcc=" + sondaNsgcc + ", trasladocama=" + trasladocama + ", acompañamiento=" + acompañamiento
                + ", destino=" + destino + ", observaciones=" + observaciones + "]";
    }

}

/*
 * 
 * Select * from ( SELECT 1, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818309' UNION SELECT 2, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='10176' UNION SELECT 3, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818288' UNION SELECT 4, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818289' UNION SELECT 5, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818444' UNION SELECT 6, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818445' UNION SELECT 7, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818469' UNION SELECT 8, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818290' UNION SELECT 9, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818291' UNION -- valores=" ,MSI,MSD,MII,MID" SELECT 10,
 * code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818292' UNION
 * SELECT 11, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818293' UNION
 * --
 * valores=" ,Contaje correcto,Contaje incorrecto. Aviso al cirujano, Contaje incorrecto. Avisado Cirujano. Pasa a rayos,No precisa contaje"
 * SELECT 12, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13825713' UNION
 * SELECT 13, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='257462007' UNION
 * SELECT 14, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='31030004' UNION
 * SELECT 15, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='47528002' UNION
 * SELECT 16, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818605' UNION
 * SELECT 17, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818607' UNION
 * SELECT 18, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818609' UNION
 * 
 * SELECT 19, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818610' UNION
 * SELECT 20, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818611' UNION
 * SELECT 21, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='45901004' UNION
 * SELECT 22, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818613' UNION
 * SELECT 23, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818614' UNION
 * SELECT 24, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818615' UNION
 * SELECT 25, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='13818616' UNION
 * SELECT 26, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818617' UNION
 * SELECT 27, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818304' UNION
 * SELECT 28, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='118373001' UNION
 * SELECT 29, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='360115008' UNION
 * SELECT 30, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818625' UNION
 * SELECT 31, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818627' UNION
 * SELECT 32, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='286628000' UNION
 * SELECT 33, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818619' UNION
 * SELECT 34, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818620' UNION
 * SELECT 35, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818621' UNION
 * SELECT 36, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818622' UNION
 * SELECT 37, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818623' UNION
 * SELECT 38, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818628' UNION
 * SELECT 39, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818629' UNION
 * SELECT 40, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='53350007' UNION
 * 
 * SELECT 41, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13819086' UNION
 * SELECT 42, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13819085' UNION
 * SELECT 43, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13819225' UNION
 * 
 * SELECT 44, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='LN' and code='8721-3"' UNION
 * SELECT 45, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818472' UNION
 * 
 * SELECT 46, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='271360006' UNION
 * 
 * SELECT 47, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818473' UNION
 * 
 * SELECT 48, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818474' UNION
 * SELECT 49, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818475' UNION
 * 
 * SELECT 50, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='168440009' UNION
 * 
 * SELECT 51, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818476' UNION
 * 
 * SELECT 52, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818477' UNION
 * SELECT 53, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818478' UNION
 * SELECT 54, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='225120005' UNION
 * 
 * SELECT 52, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818271' UNION
 * SELECT 53, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818307' UNION
 * SELECT 54, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818308' UNION
 * --" ,Gafas oxigeno,Ventimax" SELECT 55, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818272' UNION
 * 
 * 
 * SELECT 56, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818273' UNION
 * SELECT 57, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818274' UNION
 * SELECT 58, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818630' UNION
 * SELECT 59, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818631' UNION
 * SELECT 60, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818632' UNION
 * SELECT 61, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818632' UNION
 * SELECT 62, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818634' UNION
 * SELECT 63, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818275' UNION
 * --,UCI,REA,HospitalizaciÃ³n,Domicilio,Exitus" SELECT 64, code,codesystem,
 * id,descripcion FROM catalogo,codigos WHERE catalogo.id=codigos.catalogo AND
 * codesystem='LN' and code='11302-7' UNION
 * 
 * SELECT 65, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='246453008' )
 * order by 1
 * 
 */
