package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.ConstantesClinicas;



public class RegistroQuiHojaCirculaQui extends Registro {

    private Variable horaEntrada;
    private Variable horaInicio;
    private Variable tas;
    private Variable tad;
    private Variable t;
    private Variable fc;
    private Variable satO2;

    private Variable monitorizar;
    private Variable pulsioxímetro;
    private Variable via;
    private Variable viaLocalizacion;
    private Variable sondaVesical;
    private Variable sng;
    private Variable tipoAnestesia;
    private Variable tuboMascarilla;
    private Variable cateterEpidural;

    private Variable colaboraAnestesia;
    private Variable oxigenoterapia;
    private Variable aspiraSecreciones;
    private Variable proteccOcular;
    private Variable mantaTermica;
    private Variable calentaLiqui;
    private Variable posicionPaci;
    private Variable complementos;
    private Variable placaBisturi;
    private Variable medidasAsepsia;
    private Variable materialIneter;
    private Variable cuidadosPaci;
    private Variable seguridadPaci;

    private Variable hematies;
    private Variable plasma;
    private Variable plaquetas;

    private Variable instrumental;
    private Variable instrumentalOtros;
    private Variable instrumentalPresta;
    private Variable instrumentalSuelto;

    private Variable instrumentalMotor;
    private Variable instrumentalOptica;
    private Variable instrumentalCables;

    public final Variable VAR_HOJACIRQUI_HORAENTRADA = new Variable("13827867", "99G2", new Long(13827867),
            "Hora de entrada en quirófano");
    public final Variable VAR_HOJACIRQUI_HORAINICIO = new Variable("10175", "LN", new Long(10175),
            "Hora de inicio de la intervención ");

    public Variable VAR_HOJACIRQUI_TAS = new ConstantesClinicas().VAR_CTES_TAS;
    public Variable VAR_HOJACIRQUI_TAD = new ConstantesClinicas().VAR_CTES_TAD;
    public Variable VAR_HOJACIRQUI_FC = new ConstantesClinicas().VAR_CTES_FC;
    public Variable VAR_HOJACIRQUI_T = new ConstantesClinicas().VAR_CTES_T;
    public Variable VAR_HOJACIRQUI_SATO2 = new ConstantesClinicas().VAR_CTES_SATO2;

    public Variable VAR_HOJACIRQUI_MONITORIZAR = new Variable("13818280", "99G2", new Long(13818280), "Monitorizar");
    public Variable VAR_HOJACIRQUI_PULSIO = new Variable("13818281", "99G2", new Long(13818281), "Poner pulioxímetro");

    public Variable VAR_HOJACIRQUI_VIA = new Variable("13818277", "99G2", new Long(13818277), "Poner vía");
    public Variable VAR_HOJACIRQUI_VIALOCALI = new Variable("35004946", "99G2", new Long(35004946), "Localización vía");
    public Variable VAR_HOJACIRQUI_SONDAVESI = new Variable("13818278", "99G2", new Long(13818278),
            "Poner sonda vesical");
    public Variable VAR_HOJACIRQUI_SNG = new Variable("13818279", "99G2", new Long(13818279),
            "Poner Sonda nasogásgrica");

    public Variable VAR_HOJACIRQUI_TIPOANESIA = new Variable("13994896", "99G2", new Long(13994896), "Tipo anestesia");
    public Variable VAR_HOJACIRQUI_TUBOMASCAR = new Variable("119307008", "SNM3", new Long(5528),
            "Tubo endotraquial/ mascarilla");
    public Variable VAR_HOJACIRQUI_CATETEREPIDURAL = new Variable("13818470", "99G2", new Long(13818470),
            "Colocación catéter epidural");

    public Variable VAR_HOJACIRQUI_COLABORAANESTE = new Variable("13818270", "99G2", new Long(13818270),
            "Colaboración en anestesia ");
    public Variable VAR_HOJACIRQUI_OXIGENOTERAPIA = new Variable("57485005", "SNM3", new Long(35001567),
            "Oxigenoterapia");
    public Variable VAR_HOJACIRQUI_ASPIRASECRECIONES = new Variable("13826365", "99G2", new Long(13826365),
            "Aspiración de secreciones");
    public Variable VAR_HOJACIRQUI_PROTECCIONOCULAR = new Variable("13818282", "99G2", new Long(13818282),
            "Protección ocular ");
    public Variable VAR_HOJACIRQUI_MANTATERMICA = new Variable("13818471", "99G2", new Long(13818471),
            "Manta térmica ");
    public Variable VAR_HOJACIRQUI_CALENTADORLIQ = new Variable("13818644", "99G2", new Long(13818644),
            "Calentador de líquidos");

    public Variable VAR_HOJACIRQUI_POSICIONPACI = new Variable("246273001", "SNM3", new Long(13819504),
            "Posición del paciente");
    public Variable VAR_HOJACIRQUI_COMPLEMENTOS = new Variable("13818283", "99G2", new Long(13818283), "Complementos");
    public Variable VAR_HOJACIRQUI_PLACABISTURI = new Variable("13818284", "99G2", new Long(13818284),
            "Placa bisturí ");

    public Variable VAR_HOJACIRQUI_MEDIDASDEASEPSIA = new Variable("275827007", "SNM3", new Long(13818285),
            "mantenimiento de asepsia  ");
    public Variable VAR_HOJACIRQUI_MATERIALINTERV = new Variable("13818286", "99G2", new Long(13818286),
            "Suministrar material para intervención ");
    public Variable VAR_HOJACIRQUI_CUIDADOSPACIENTE = new Variable("CUIENF", "99G2", new Long(14062749),
            "cuidados enfermería  ");
    public Variable VAR_HOJACIRQUI_SEGURIDADPACIENTE = new Variable("226003000", "SNM3", new Long(13818287),
            "mantenimiento de la seguridad del paciente (procedimiento) ");

    public Variable VAR_HOJACIRQUI_TRANSFUHEMATIES = new Variable("99.04", "I9C", new Long(104551),
            "Transfusión de concentrado de hematíes ");

    public Variable VAR_HOJACIRQUI_TRANSFUPLASMA = new Variable("288177002", "SNM3", new Long(13818468),
            "Transfusión de plasma ");
    public Variable VAR_HOJACIRQUI_TRANSFUPLAQUETAS = new Variable("12719002", "SNM3", new Long(13818467),
            "Transfusión de plaquetas  ");

    public Variable VAR_HOJACIRQUI_INSTRUMENTAL = new Variable("13818294", "99G2", new Long(13818294),
            "Caja de Instrumental  ");

    public Variable VAR_HOJACIRQUI_INSTRUMENTALOTROS = new Variable("13818446", "99G2", new Long(13818446),
            "Caja de instrumental otros ");

    public Variable VAR_HOJACIRQUI_INSTRUMENTALPRESTA = new Variable("13818267", "99G2", new Long(13818267),
            "Instrumental externo     ");
    public Variable VAR_HOJACIRQUI_INSTRUMENTALSUELTO = new Variable("13818268", "99G2", new Long(13818268),
            "Instrumental suelto     ");
    public Variable VAR_HOJACIRQUI_INSTRUMENTALMOTOR = new Variable("13818447", "99G2", new Long(13818447),
            "Instrumental (motor)     ");
    public Variable VAR_HOJACIRQUI_INSTRUMENTALOPTICA = new Variable("13818448", "99G2", new Long(13818448),
            "Instrumental (ópticas)    ");
    public Variable VAR_HOJACIRQUI_INSTRUMENTALCABLES = new Variable("333", "99G2", new Long(33),
            "Instrumental cables    ");

    public final static Long PLANTILLLA_EDITOR = new Long(327663561);
    public final static Long TIPO_REGISTRO = new Long(4);

    /*
	 * 1 13827867 99G2 13827867 Hora recepción 2 10175 99G2 10175 Hora de Inicio 4
	 * 364075005 SNM3 7747515 frecuencia cardíaca 5 246508008 SNM3 776 Temperatura 6
	 * 2711-0 LN 778 Saturación de oxígeno 7 13818280 99G2 13818280 Monitorizar 8
	 * 13818281 99G2 13818281 Poner pulsioxímetro 9 13818277 99G2 13818277 Poner vía
	 * 10 35004946 99G2 35004946 Localización 2 11 35004946 99G2 35004946
	 * Localización 2 12 13818278 99G2 13818278 Poner sonda vesical 13 13818279 99G2
	 * 13818279 Poner S.N.G. 14 119307008 SNM3 5528 Tubo endotraqueal 14 399084002
	 * SNM3 13994896 tipo de anestesia 15 13818470 99G2 13818470 Colocación cateter
	 * epidural 16 13818270 99G2 13818270 Colaboración en anestesia 17 57485005 SNM3
	 * 35001567 Oxigenoterapia 18 13826365 99G2 13826365 Aspiración de secreciones
	 * 19 13818282 99G2 13818282 Protección ocular 20 13818471 99G2 13818471 Manta
	 * térmica 21 13818644 99G2 13818644 Calentador de líquidos 22 246273001 SNM3
	 * 13819504 posición del paciente 23 13818283 99G2 13818283 Complementos 24
	 * 13818284 99G2 13818284 Placa bisturí 25 275827007 SNM3 13818285 mantenimiento
	 * de asepsia 26 13818286 99G2 13818286 Suministrar material para intervención
	 * 27 CUIENF 99G2 14062749 cuidados enfermería 28 226003000 SNM3 13818287
	 * mantenimiento de la seguridad del paciente (procedimiento) 29 99.04 I9C
	 * 104551 TRANSFUSION DE CONCENTRADO DE HEMATIES 30 288177002 SNM3 13818468
	 * transfusión de plasma o sustituto, SAI 31 12719002 SNM3 13818467 transfusión
	 * de plaquetas 32 13818294 99G2 13818294 Cajas de instrumental (Traumatologia
	 * 1) 33 13818296 99G2 13818296 Cajas de instrumental (Ginecología) 34 13818297
	 * 99G2 13818297 Cajas de instrumental (Dermatología) 35 13818298 99G2 13818298
	 * Cajas de instrumental (Cirugía) 36 13818299 99G2 13818299 Cajas de
	 * instrumental (Oftalmología) 37 13818300 99G2 13818300 Cajas de instrumental
	 * (ORL) 38 13818301 99G2 13818301 Cajas de instrumental (Urología) 39 13818446
	 * 99G2 13818446 Cajas de instrumental (otros) 40 13818267 99G2 13818267
	 * Instrumental externo 41 13818268 99G2 13818268 Instrumental suelto 42
	 * 13818447 99G2 13818447 Instrumental (motor) 43 13818448 99G2 13818448
	 * Instrumental (ópticas)
     */
    public RegistroQuiHojaCirculaQui() {
        iniciaQui();
    }

    public RegistroQuiHojaCirculaQui(Long id) {
        super(id);
        iniciaQui();
    }

    public RegistroQuiHojaCirculaQui(RegistroQuiHojaCirculaQui r) {
        super(r);
        this.horaEntrada = r.getHoraEntrada();
        this.horaInicio = r.getHoraInicio();
        this.tas = r.getTas();
        this.tad = r.tad;
        this.t = r.getT();
        this.fc = r.getFc();
        this.satO2 = r.getSatO2();

        this.monitorizar = r.getMonitorizar();
        this.pulsioxímetro = r.getPulsioxímetro();
        this.via = r.getVia();
        this.viaLocalizacion = r.getViaLocalizacion();
        this.sondaVesical = r.getSondaVesical();
        this.sng = r.getSng();
        this.tipoAnestesia = r.getTipoAnestesia();
        this.tuboMascarilla = r.getTuboMascarilla();
        this.cateterEpidural = r.getCateterEpidural();

        this.colaboraAnestesia = r.getColaboraAnestesia();
        this.oxigenoterapia = r.getOxigenoterapia();
        this.aspiraSecreciones = r.getAspiraSecreciones();
        this.proteccOcular = r.getProteccOcular();
        this.mantaTermica = r.getMantaTermica();
        this.calentaLiqui = r.getCalentaLiqui();
        this.posicionPaci = r.getPosicionPaci();
        this.complementos = r.getComplementos();
        this.placaBisturi = r.getPlacaBisturi();
        this.medidasAsepsia = r.getMedidasAsepsia();
        this.materialIneter = r.getMaterialIneter();
        this.cuidadosPaci = r.getCuidadosPaci();
        this.seguridadPaci = r.getSeguridadPaci();

        this.hematies = r.getHematies();
        this.plasma = r.getPlasma();
        this.plaquetas = r.getPlaquetas();

        this.instrumental = r.getInstrumental();
        this.instrumentalOtros = r.getInstrumentalOtros();
        this.instrumentalPresta = r.getInstrumentalPresta();
        this.instrumentalSuelto = r.getInstrumentalSuelto();

        this.instrumentalMotor = r.getInstrumentalMotor();
        this.instrumentalOptica = r.getInstrumentalOptica();
        this.instrumentalCables = r.getInstrumentalCables();
    }

    public void iniciaQui() {
        this.plantilla_editor = PLANTILLLA_EDITOR;
        this.tiporegistro = TIPO_REGISTRO;
        this.descripcion = "Hoja Circulante Fase Quirófano";

        this.horaEntrada = VAR_HOJACIRQUI_HORAENTRADA;
        this.horaInicio = VAR_HOJACIRQUI_HORAINICIO;
        this.tas = VAR_HOJACIRQUI_TAS;
        this.tad = VAR_HOJACIRQUI_TAD;
        this.t = VAR_HOJACIRQUI_T;
        this.fc = VAR_HOJACIRQUI_FC;
        this.satO2 = VAR_HOJACIRQUI_SATO2;

        this.monitorizar = VAR_HOJACIRQUI_MONITORIZAR;
        this.pulsioxímetro = VAR_HOJACIRQUI_PULSIO;
        this.via = VAR_HOJACIRQUI_VIA;
        this.viaLocalizacion = VAR_HOJACIRQUI_VIALOCALI;
        this.sondaVesical = VAR_HOJACIRQUI_SONDAVESI;
        this.sng = VAR_HOJACIRQUI_SNG;
        this.tipoAnestesia = VAR_HOJACIRQUI_TIPOANESIA;
        this.tuboMascarilla = VAR_HOJACIRQUI_TUBOMASCAR;
        this.cateterEpidural = VAR_HOJACIRQUI_CATETEREPIDURAL;

        this.colaboraAnestesia = VAR_HOJACIRQUI_COLABORAANESTE;
        this.oxigenoterapia = VAR_HOJACIRQUI_OXIGENOTERAPIA;
        this.aspiraSecreciones = VAR_HOJACIRQUI_ASPIRASECRECIONES;
        this.proteccOcular = VAR_HOJACIRQUI_PROTECCIONOCULAR;
        this.mantaTermica = VAR_HOJACIRQUI_MANTATERMICA;
        this.calentaLiqui = VAR_HOJACIRQUI_CALENTADORLIQ;
        this.posicionPaci = VAR_HOJACIRQUI_POSICIONPACI;
        this.complementos = VAR_HOJACIRQUI_COMPLEMENTOS;
        this.placaBisturi = VAR_HOJACIRQUI_PLACABISTURI;
        this.medidasAsepsia = VAR_HOJACIRQUI_MEDIDASDEASEPSIA;
        this.materialIneter = VAR_HOJACIRQUI_MATERIALINTERV;
        this.cuidadosPaci = VAR_HOJACIRQUI_COMPLEMENTOS;
        this.seguridadPaci = VAR_HOJACIRQUI_SEGURIDADPACIENTE;

        this.hematies = VAR_HOJACIRQUI_TRANSFUHEMATIES;
        this.plasma = VAR_HOJACIRQUI_TRANSFUPLASMA;
        this.plaquetas = VAR_HOJACIRQUI_TRANSFUPLAQUETAS;

        this.instrumental = VAR_HOJACIRQUI_INSTRUMENTAL;
        this.instrumentalOtros = VAR_HOJACIRQUI_INSTRUMENTALOTROS;
        this.instrumentalPresta = VAR_HOJACIRQUI_INSTRUMENTALPRESTA;
        this.instrumentalSuelto = VAR_HOJACIRQUI_INSTRUMENTALSUELTO;

        this.instrumentalMotor = VAR_HOJACIRQUI_INSTRUMENTALMOTOR;
        this.instrumentalOptica = VAR_HOJACIRQUI_INSTRUMENTALOPTICA;
        this.instrumentalCables = VAR_HOJACIRQUI_INSTRUMENTALCABLES;
    }

    public Variable getHoraEntrada() {
        return horaEntrada;
    }

    public Variable getVariableHoraEntrada() {
        return horaEntrada;
    }

    public String getHoraEntradaString() {
        return horaEntrada.getValor();
    }

    public void setHoraEntrada(Variable horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraEntrada(String valor) {
        this.horaEntrada.setValor(valor);
    }

    public Variable getHoraInicio() {
        return horaInicio;
    }

    public Variable getVariableHoraInicio() {
        return horaInicio;
    }

    public String getHoraInicioString() {
        return horaInicio.getValor();
    }

    public void setHoraInicio(Variable horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraInicio(String valor) {
        this.horaInicio.setValor(valor);
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
        ;
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
        ;
    }

    public Variable getT() {
        return t;
    }

    public Variable getVariableT() {
        return t;
    }

    public String getTString() {
        return t.getValor();
    }

    public void setT(Variable t) {
        this.t = t;
    }

    public void setT(String valor) {
        this.t.setValor(valor);
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

    public Variable getMonitorizar() {
        return monitorizar;
    }

    public Variable getVariableMonitorizar() {
        return monitorizar;
    }

    public Boolean getMonitorizarBoolean() {
        if (monitorizar != null && !monitorizar.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMonitorizar(Variable monitorizar) {
        this.monitorizar = monitorizar;
    }

    public void setMonitorizar(Boolean valor) {
        if (valor == true) {
            this.monitorizar.setValor("Monitorizar");
        } else {
            this.monitorizar.setValor("");
        }
    }

    public Variable getPulsioxímetro() {
        return pulsioxímetro;
    }

    public Variable getVariablePulsioxímetro() {
        return pulsioxímetro;
    }

    public Boolean getPulsioxímetroBoolean() {
        if (pulsioxímetro != null && !pulsioxímetro.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPulsioxímetro(Variable pulsioxímetro) {
        this.pulsioxímetro = pulsioxímetro;
    }

    public void setPulsioxímetro(Boolean valor) {
        if (valor == true) {
            this.pulsioxímetro.setValor("Pulsioxímetro");
        } else {
            this.pulsioxímetro.setValor("");
        }
    }

    public Variable getVia() {
        return via;
    }

    public Variable getVariableVia() {
        return via;
    }

    public String getViaString() {
        return via.getValor();
    }

    public void setVia(Variable via) {
        this.via = via;
    }

    public void setVia(String valor) {
        this.via.setValor(valor);
    }

    public Variable getViaLocalizacion() {
        return viaLocalizacion;
    }

    public Variable getVariableViaLocalizacion() {
        return viaLocalizacion;
    }

    public String getViaLocalizacionString() {
        return viaLocalizacion.getValor();
    }

    public void setViaLocalizacion(Variable viaLocalizacion) {
        this.viaLocalizacion = viaLocalizacion;
    }

    public void setViaLocalizacion(String valor) {
        this.viaLocalizacion.setValor(valor);
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

    public Variable getSng() {
        return sng;
    }

    public Variable getVariableSng() {
        return sng;
    }

    public String getSngString() {
        return sng.getValor();
    }

    public void setSng(Variable sng) {
        this.sng = sng;
    }

    public void setSng(String valor) {
        this.sng.setValor(valor);
    }

    public Variable getTipoAnestesia() {
        return tipoAnestesia;
    }

    public Variable getVariableTipoAnestesia() {
        return tipoAnestesia;
    }

    public String getTipoAnestesiaString() {
        return tipoAnestesia.getValor();
    }

    public void setTipoAnestesia(Variable tipoAnestesia) {
        this.tipoAnestesia = tipoAnestesia;
    }

    public void setTipoAnestesia(String valor) {
        this.tipoAnestesia.setValor(valor);
    }

    public Variable getTuboMascarilla() {
        return tuboMascarilla;
    }

    public Variable getVariableTuboMascarilla() {
        return tuboMascarilla;
    }

    public String getTuboMascarillaString() {
        return tuboMascarilla.getValor();
    }

    public void setTuboMascarilla(Variable tuboMascarilla) {
        this.tuboMascarilla = tuboMascarilla;
    }

    public void setTuboMascarilla(String valor) {
        this.tuboMascarilla.setValor(valor);
    }

    public Variable getCateterEpidural() {
        return cateterEpidural;
    }

    public Variable getVariableCateterEpidural() {
        return cateterEpidural;
    }

    public String getCateterEpiduralString() {
        return cateterEpidural.getValor();
    }

    public void setCateterEpidural(Variable cateterEpidural) {
        this.cateterEpidural = cateterEpidural;
    }

    public void setCateterEpidural(String valor) {
        this.cateterEpidural.setValor(valor);
    }

    public Variable getColaboraAnestesia() {
        return colaboraAnestesia;
    }

    public Variable getVariableColaboraAnestesia() {
        return colaboraAnestesia;
    }

    public Boolean getColaboraAnestesiaBoolean() {
        if (colaboraAnestesia != null && !colaboraAnestesia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setColaboraAnestesia(Variable colaboraAnestesia) {
        this.colaboraAnestesia = colaboraAnestesia;
    }

    public void setColaboraAnestesia(Boolean valor) {
        if (valor == true) {
            this.colaboraAnestesia.setValor("Colaboración anestesia");
        } else {
            this.colaboraAnestesia.setValor("");
        }
    }

    public Variable getOxigenoterapia() {
        return oxigenoterapia;
    }

    public Variable getVariableOxigenoterapia() {
        return oxigenoterapia;
    }

    public String getOxigenoterapiasString() {
        return oxigenoterapia.getValor();
    }

    public void setOxigenoterapia(Variable oxigenoterapia) {
        this.oxigenoterapia = oxigenoterapia;
    }

    public void setOxigenoterapia(String valor) {
        this.oxigenoterapia.setValor(valor);
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

    public Variable getProteccOcular() {
        return proteccOcular;
    }

    public Variable getVariableProteccOcular() {
        return proteccOcular;
    }

    public Boolean getProteccOcularBoolean() {
        if (proteccOcular != null && !proteccOcular.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setProteccOcular(Variable proteccOcular) {
        this.proteccOcular = proteccOcular;
    }

    public void setProteccOcular(Boolean valor) {
        if (valor == true) {
            this.proteccOcular.setValor("Protección ocular");
        } else {
            this.proteccOcular.setValor("Protección ocular");
        }
    }

    public Variable getMantaTermica() {
        return mantaTermica;
    }

    public Variable getVariableMantaTermica() {
        return mantaTermica;
    }

    public Boolean getMantaTermicaBoolean() {
        if (mantaTermica != null && !mantaTermica.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMantaTermica(Variable mantaTermica) {
        this.mantaTermica = mantaTermica;
    }

    public void setMantaTermica(Boolean valor) {
        if (valor == true) {
            this.mantaTermica.setValor("Manta térmica");
        } else {
            this.mantaTermica.setValor("");
        }
    }

    public Variable getCalentaLiqui() {
        return calentaLiqui;
    }

    public Variable getVariableCalentaLiqui() {
        return calentaLiqui;
    }

    public Boolean getCalentaLiquibBoolean() {
        if (calentaLiqui != null && !calentaLiqui.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCalentaLiqui(Variable calentaLiqui) {
        this.calentaLiqui = calentaLiqui;
    }

    public void setCalentaLiqui(Boolean valor) {
        if (valor == true) {
            this.calentaLiqui.setValor("Calentador líquidos IV");
        } else {
            this.calentaLiqui.setValor("");
        }
    }

    public Variable getPosicionPaci() {
        return posicionPaci;
    }

    public Variable getVariablePosicionPaci() {
        return posicionPaci;
    }

    public String getPosicionPacisString() {
        return posicionPaci.getValor();
    }

    public void setPosicionPaci(Variable posicionPaci) {
        this.posicionPaci = posicionPaci;
    }

    public void setPosicionPaci(String valor) {
        this.posicionPaci.setValor(valor);
        ;
    }

    public Variable getComplementos() {
        return complementos;
    }

    public Variable getVariableComplementos() {
        return complementos;
    }

    public String getComplementosString() {
        return complementos.getValor();
    }

    public void setComplementos(Variable complementos) {
        this.complementos = complementos;
    }

    public void setComplementos(String valor) {
        this.complementos.setValor(valor);
    }

    public Variable getPlacaBisturi() {
        return placaBisturi;
    }

    public Variable getVariablePlacaBisturi() {
        return placaBisturi;
    }

    public String getPlacaBisturiString() {
        return placaBisturi.getValor();
    }

    public void setPlacaBisturi(Variable placaBisturi) {
        this.placaBisturi = placaBisturi;
    }

    public void setPlacaBisturi(String valor) {
        this.placaBisturi.setValor(valor);
    }

    public Variable getMedidasAsepsia() {
        return medidasAsepsia;
    }

    public Variable getVariableMedidasAsepsia() {
        return medidasAsepsia;
    }

    public Boolean getMedidasAsepsiabBoolean() {
        if (medidasAsepsia != null && !medidasAsepsia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMedidasAsepsia(Variable medidasAsepsia) {
        this.medidasAsepsia = medidasAsepsia;
    }

    public void setMedidasAsepsia(Boolean valor) {
        if (valor == true) {
            this.medidasAsepsia.setValor("Medidas de asepsia");
        } else {
            this.medidasAsepsia.setValor("");
        }
    }

    public Variable getMaterialIneter() {
        return materialIneter;
    }

    public Variable getVariableMaterialIneter() {
        return materialIneter;
    }

    public Boolean getMaterialIneterbBoolean() {
        if (materialIneter != null && !materialIneter.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMaterialIneter(Variable materialIneter) {
        this.materialIneter = materialIneter;
    }

    public void setMaterialIneter(Boolean valor) {
        if (valor == true) {
            this.materialIneter.setValor("Material para la intervención");
        } else {
            this.materialIneter.setValor("");
        }
    }

    public Variable getCuidadosPaci() {
        return cuidadosPaci;
    }

    public Variable getVariableCuidadosPaci() {
        return cuidadosPaci;
    }

    public Boolean getCuidadosPacibBoolean() {
        if (cuidadosPaci != null && !cuidadosPaci.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCuidadosPaci(Variable cuidadosPaci) {
        this.cuidadosPaci = cuidadosPaci;
    }

    public void setCuidadosPaci(Boolean valor) {
        if (valor == true) {
            this.cuidadosPaci.setValor("Cuidados del paciente");
        } else {
            this.cuidadosPaci.setValor("");
        }

    }

    public Variable getSeguridadPaci() {
        return seguridadPaci;
    }

    public Variable getVariableSeguridadPaci() {
        return seguridadPaci;
    }

    public Boolean getSeguridadPacibBoolean() {
        if (seguridadPaci != null && !seguridadPaci.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSeguridadPaci(Variable seguridadPaci) {
        this.seguridadPaci = seguridadPaci;
    }

    public void setSeguridadPaci(Boolean valor) {
        if (valor == true) {
            this.seguridadPaci.setValor("Seguridad del paciente");
        } else {
            this.seguridadPaci.setValor("");
        }
    }

    public Variable getHematies() {
        return hematies;
    }

    public Variable getVariableHematies() {
        return hematies;
    }

    public Boolean getHematiesbBoolean() {
        if (hematies != null && !hematies.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHematies(Variable hematies) {
        this.hematies = hematies;
    }

    public void setHematies(Boolean valor) {
        if (valor == true) {
            this.hematies.setValor("Transfusión de hematíes");
        } else {
            this.hematies.setValor("");
        }
    }

    public Variable getPlasma() {
        return plasma;
    }

    public Variable getVariable() {
        return plasma;
    }

    public Boolean getPlasmabBoolean() {
        if (plasma != null && !plasma.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPlasma(Variable plasma) {
        this.plasma = plasma;
    }

    public void setPlasma(Boolean valor) {
        if (valor == true) {
            this.plasma.setValor("Transfusión de plasma");
        } else {
            this.plasma.setValor("");
        }
    }

    public Variable getPlaquetas() {
        return plaquetas;
    }

    public Variable getVariablePlaquetas() {
        return plaquetas;
    }

    public Boolean getPlaquetasbBoolean() {
        if (plaquetas != null && !plaquetas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPlaquetas(Variable plaquetas) {
        this.plaquetas = plaquetas;
    }

    public void setPlaquetas(Boolean valor) {
        if (valor == true) {
            this.plaquetas.setValor("Transfusión de plaquetas");
        } else {
            this.plaquetas.setValor("");
        }
    }

    public Variable getInstrumental() {
        return instrumental;
    }

    public Variable getVariableInstrumental() {
        return instrumental;
    }

    public String getInstrumentalString() {
        return instrumental.getValor();
    }

    public void setInstrumental(Variable instrumental) {
        this.instrumental = instrumental;
    }

    public void setInstrumental(String valor) {
        this.instrumental.setValor(valor);
    }

    public Variable getInstrumentalOtros() {
        return instrumentalOtros;
    }

    public Variable getVariableInstrumentalOtros() {
        return instrumentalOtros;
    }

    public String getInstrumentalOtrosString() {
        return instrumentalOtros.getValor();
    }

    public void setInstrumentalOtros(Variable instrumentalOtros) {
        this.instrumentalOtros = instrumentalOtros;
    }

    public void setInstrumentalOtros(String valor) {
        this.instrumentalOtros.setValor(valor);
    }

    public Variable getInstrumentalPresta() {
        return instrumentalPresta;
    }

    public Variable getVariableInstrumentalPresta() {
        return instrumentalPresta;
    }

    public Boolean getInstrumentalPrestaBoolean() {
        if (instrumentalPresta != null && !instrumentalPresta.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInstrumentalPresta(Variable instrumentalPresta) {
        this.instrumentalPresta = instrumentalPresta;
    }

    public void setInstrumentalPresta(Boolean valor) {
        if (valor == true) {
            this.instrumentalPresta.setValor("Instrumental prestado");
        } else {
            this.instrumentalPresta.setValor("");
        }
    }

    public Variable getInstrumentalSuelto() {
        return instrumentalSuelto;
    }

    public Variable getVariableInstrumentalSuelto() {
        return instrumentalSuelto;
    }

    public Boolean getInstrumentalSueltoBoolean() {
        if (instrumentalSuelto != null && !instrumentalSuelto.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInstrumentalSuelto(Variable instrumentalSuelto) {
        this.instrumentalSuelto = instrumentalSuelto;
    }

    public void setInstrumentalSuelto(Boolean valor) {
        if (valor == true) {
            this.instrumentalSuelto.setValor("Instrumental prestado");
        } else {
            this.instrumentalSuelto.setValor("");
        }
    }

    public Variable getInstrumentalMotor() {
        return instrumentalMotor;
    }

    public Variable getVariableInstrumentalMotor() {
        return instrumentalMotor;
    }

    public String getInstrumentalMotorString() {
        return instrumentalMotor.getValor();
    }

    public void setInstrumentalMotor(Variable motor) {
        this.instrumentalMotor = motor;
    }

    public void setInstrumentalMotor(String valor) {
        this.instrumentalMotor.setValor(valor);
    }

    public Variable getInstrumentalOptica() {
        return instrumentalOptica;
    }

    public Variable getVariableInstrumentalOptica() {
        return instrumentalOptica;
    }

    public String getInstrumentalOpticaString() {
        return instrumentalOptica.getValor();
    }

    public void setInstrumentalOptica(Variable optica) {
        this.instrumentalOptica = optica;
    }

    public void setInstrumentalOptica(String valor) {
        this.instrumentalOptica.setValor(valor);
    }

    public Variable getInstrumentalCables() {
        return instrumental;
    }

    public Variable getVariableInstrumentalCables() {
        return instrumental;
    }

    public String getInstrumentalCablesString() {
        return instrumental.getValor();
    }

    public void setInstrumentalCables(Variable cables) {
        this.instrumental = cables;
    }

    public void setInstrumentalCables(String valor) {
        this.instrumental.setValor(valor);
    }
}

/*
 * Select * from (
 * 
 * SELECT 1, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13827867' UNION
 * SELECT 2, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='10175' UNION
 * SELECT 3, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='' and code='75367002' UNION
 * SELECT 4, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='364075005' UNION
 * SELECT 5, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='246508008' UNION
 * SELECT 6, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='LN' and code='2711-0' UNION
 * SELECT 7, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818280' UNION
 * SELECT 8, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818281' UNION
 * -- valores=" ,periférica Drum, -- Abocath nº 14,Abocath nº 16,Abocath nº
 * 18,Abocath nº 20,Abocath nº 22, -- Vía central de dos vias 20 cm,Vía central
 * de dos vias 30 cm,Vía central de tres vias, -- Yugular nº 14,Yugular nº
 * 16,Yugular nº 18,Yugular nº 20,Yugular nº 22,port a cath" SELECT 9,
 * code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818277' UNION
 * SELECT 10, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='35004946' UNION
 * SELECT 11, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='35004946' UNION
 * --valores=" ,Foley nº 14, Foley nº 16, Foley nº 18, Foley nº 20, Foley nº 22,
 * -- Silicona nº 14, Silicona nº 16, Silicona nº 18, Silicona nº 20, Silicona
 * nº 22, --Sonda de tres vías dofour nº 18, Sonda de tres vías dofour nº 20,
 * Sonda de tres vías dofour nº 22, Sonda de tres vías dofour nº 24" SELECT 12,
 * code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818278' UNION
 * SELECT 13, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818279' UNION
 * -- valores=" ,General,Combinada,Local,Locoregional,Tópica,Retrobulbar" SELECT
 * 14, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='399084002' UNION
 * --valores=" ,Tubo endotraqueal,Mascarilla laríngea,Mascarilla facial" SELECT
 * 14, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='119307008' UNION
 * SELECT 15, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818470' UNION
 * SELECT 16, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818270' UNION
 * --valores="Gafas oxigeno,Ventimax" SELECT 17, code,codesystem, id,descripcion
 * FROM catalogo,codigos WHERE catalogo.id=codigos.catalogo AND
 * codesystem='SNM3' and code='57485005' UNION
 * 
 * SELECT 18, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13826365' UNION
 * SELECT 19, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818282' UNION
 * SELECT 20, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818471' UNION
 * SELECT 21, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818644' UNION
 * -- valores=" ,Antitrendelembourg,Decubito Supino. Brazos en cruz,Decubito
 * Supino. Brazos alineados al cuerpo, -- Decubito
 * Prono,Fowler,Ginecológica,Litotomía,Mesa Ortopédica,Lateral Derecho,Lateral
 * Izquierdo,Perneras, -- Piernas Abiertas,Pilé,Semifowler,Trendelembourg" s
 * SELECT 22, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='SNM3' and code='246273001' UNION
 * 
 * -- valores=" ,Almohada. Hueco poplíteo,Cabecero,Correas de sujeción, --
 * Elementos auxiliares de columna,Elementos auxiliares de recto, -- Rodillo
 * escapular,Rodillo cresta ilíaca,Saco de arena. Gluteo,Reposapies, -- Topes
 * sacro-lubmar,Topes cresta ilíaca,Topes hombro,Topes axila,Topes costado
 * derecho, -- Topes costado izquierdo,Topes brazo derecho sujeto a arco,Topes
 * brazo izquierdo sujeto a arco" separador=","> SELECT 23, code,codesystem,
 * id,descripcion FROM catalogo,codigos WHERE catalogo.id=codigos.catalogo AND
 * codesystem='99G2' and code='13818283' UNION --valores=" ,No precisa,Hemitorax
 * derecho,Hemitorax izquierdo,Hemiabdomen derecho,Hemiabdomen izquierdo, --
 * Espalda,Gluteo derecho,Gluteo izquierdo,Miembro inferior derecho,Miembro
 * inferior izquierdo" SELECT 24, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818284' UNION SELECT 25, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='SNM3' and
 * code='275827007' UNION SELECT 26, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818286' UNION SELECT 27, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='CUIENF' UNION SELECT 28, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='SNM3' and
 * code='226003000' UNION -- valores=" ,1,2,3,4,5,6,7,8,9,10" SELECT 29,
 * code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='I9C' and code='99.04' UNION --
 * valores=" ,1,2,3,4,5,6,7,8,9,10" SELECT 30, code,codesystem, id,descripcion
 * FROM catalogo,codigos WHERE catalogo.id=codigos.catalogo AND
 * codesystem='SNM3' and code='288177002' UNION --
 * valores=" ,1,2,3,4,5,6,7,8,9,10" SELECT 31, code,codesystem, id,descripcion
 * FROM catalogo,codigos WHERE catalogo.id=codigos.catalogo AND
 * codesystem='SNM3' and code='12719002' UNION --valores=" ,Clavo intramedular
 * PFNA,Clavo intramedular SMITH Y NEWP INTERTAN, -- Clavo intramedular
 * fémur,Clavo intramedular tibia,Prótesis total -- cadera Bimetric,Prótesis
 * total cadera Aesculap,Prótesis parcial -- cadera Coron,Prótesis parcial
 * cadera Aesculap,Prótesis total rodilla -- Vanguard,Prótesis total rodilla
 * Optetrack,Müller parcial,Müller 1, -- Müller 2,Caja general de trauma,Caja de
 * mano,Caja instrumental pequeño, -- Osteosintesis Synthex,Caja de pie,Caja de
 * muñeca,Caja tornillos A.O., -- Caja minitornillos,tornillos canulados 4.5
 * mm,tornillos canulados -- 7 mm,Caja artroscopia A,Caja artroscopia
 * B,Artrotomo,Vaporizador, -- Cerclaje fémur,Fresas intramedulares,Fijador
 * externo,Rescate cadera, -- Rescate cemento prótesis,Caja cotilo cementado"
 * 
 * SELECT 32, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818294' UNION
 * --valores=" ,Biopsia diferida de mama,Caja de
 * laparoscopia,Cesarea,Conización,Histeroscopia, -- Laparotomía
 * ginecológica,Legrado,Legrado puerperal, -- Mastectomia,Resector,Resector
 * Versapoint" SELECT 33, code,codesystem, id,descripcion FROM catalogo,codigos
 * WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818296'
 * UNION -- valores=" ,Dermatología,Microsutura" SELECT 34, code,codesystem,
 * id,descripcion FROM catalogo,codigos WHERE catalogo.id=codigos.catalogo AND
 * codesystem='99G2' and code='13818297' UNION --valores=" ,C. laparoscopia,C.
 * laparotomía,Clamp vascular,Caja vascular especial,Caja varices, -- Caja
 * amputación,Caja apendicectomía,Separador Omnitrack,Caja cirugia especial, --
 * C. Laparotomía infantil,Caja tiroides,Caja suplemento de torax,Pinzas
 * laparoscopia, -- Caja de Clamps pequeños vasculares,Caja de quistes,Caja con
 * valva de legrado" SELECT 35, code,codesystem, id,descripcion FROM
 * catalogo,codigos WHERE catalogo.id=codigos.catalogo AND codesystem='99G2' and
 * code='13818298' UNION --valores=" ,Catarata,Catarata
 * completa,Dacrio,Desprendimiento de retina,Estrabismo,Faco, -- General de
 * Oftalmología,Sondaje de vías,Vitrectomía" SELECT 36, code,codesystem,
 * id,descripcion FROM catalogo,codigos WHERE catalogo.id=codigos.catalogo AND
 * codesystem='99G2' and code='13818299' UNION -- valores="
 * ,Adenoidectomía,Amigdalectomía,Antoli camdela,Cirugía endoscópica nasal CENS,
 * -- Drenajes timpánicos,Estapedectomía,Laringectomía, -- Microcirugía
 * laringea,Microcirugía laringea por laser,Microsutura,Miringoplastia, --
 * Rinoplastia,Septoplastia,Sinusectomía,Traqueotomía,Timpanoplastia" SELECT 37,
 * code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818300' UNION
 * -- valores=" ,Aditamento biopsia prostata,aditamento
 * nefrostomia,Beniques,Cistectomía,Cistoscopio, -- Cistoscopio
 * compacto,Criptorquidia,Fimosis,Hipospadias,Litotritor,Nefrectomía,
 * --Próstata,Resector,R.T.U.,Ureteroscopia,Uretrotomo,Varicocele,Vasectomía"
 * SELECT 38, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818301' UNION
 * SELECT 39, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818446' UNION
 * SELECT 40, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818267' UNION
 * SELECT 41, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818268' UNION
 * SELECT 42, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818447' UNION
 * SELECT 43, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818448' UNION
 * SELECT 44, code,codesystem, id,descripcion FROM catalogo,codigos WHERE
 * catalogo.id=codigos.catalogo AND codesystem='99G2' and code='13818449'
 * 
 * ) order by 1
 */
