package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;



public class RegistroOncoValoracion extends Registro {

    private Variable diagnostico;
    private Variable alergias;
    private Variable antecedentes;
    private Variable tratamientoqt;
    private Variable sitFamiliarSocial;
    private Variable telefono;
    private Variable cliclos;
    private Variable fechaInicio;
    private Variable fechaFin;

    // respiracion
    private Variable respiNoAlteracion;
    private Variable respidisnea;
    private Variable respiOxigeno;
    private Variable respiOtros;
    // alimentacion
    private Variable aliDietaHabitual;
    private Variable aliIngestaLiquidos;
    private Variable aliSuplemento;
    private Variable aliDificultadDeglu;
    private Variable aliNauseas;
    private Variable aliVomitos;
    private Variable aliAteraGusto;
    private Variable aliOtros;
    // eliminacion urinaria
    private Variable eliuriCantidad;
    private Variable eliuriColor;
    private Variable eliuriEscozor;
    private Variable eliuriPortadorSV;
    private Variable eliuriUrostomia;
    private Variable eliuriNefrostomia;
    private Variable eliuriOtros;
    // eliminacion fecal
    private Variable elifecalDiarrea;
    private Variable elifecalEstrenimiento;
    private Variable elifecalOstomia;
    private Variable elifecalOtro;
    // movilizacion
    private Variable movilizacion;
    private Variable movilizacionOtros;
    private Variable karnofsky;
// reposo sueño
    private Variable repoSueno;
    private Variable repoSueInfusiones;
    private Variable repoSueMedicacion;
    private Variable repoSueOtros;
// vestido y aseo personal
    private Variable vestidoAseo;
    private Variable vestidoAseoOtros;
    // higiene piel

    private Variable pielNoAlteracion;
    private Variable pielSeca;
    private Variable pielPalidez;
    private Variable pielIctericia;
    private Variable pielPrurito;
    private Variable pielEritema;
    private Variable pielEdemas;
    private Variable pielHipersensibilidad;
    private Variable pielHematomas;
    private Variable pielFlebitis;
    private Variable pielHeridas;
    private Variable pielOtros;
    // Seguridad
    private Variable segAccesoTipo;
    private Variable segAccesoLoca;
    private Variable segAccesoPermeable;
    // dolor
    private Variable dolorEva;
    private Variable dolorLocalizacion;
    // situación emocial
    private Variable emocioAnsiedad;
    private Variable emocioDepresion;
    private Variable emocioPAD_T;
//comunicación
    private Variable comuAcompanado;
    private Variable comuOtraCompa;
    private Variable comuRelacionFam;
    private Variable comuRelacionPer;
    private Variable comuColabora;
    private Variable comuConspiraSilen;
    private Variable comuCambioSexual;
    private Variable comuOtro;
    // percepcion personal
    private Variable percepImagen;
    private Variable percepAutoestima;
    private Variable percepAlopecia;
    private Variable percepMastectomia;
    private Variable percepColostomia;
    private Variable percepOtros;
//aprender
    private Variable aprenComprende;
    private Variable aprenDemanda;

    public final static Long PLANTILLLA_EDITOR_ONCO_VALORACION = new Long(217953902);
    public final static Long TIPO_REGISTRO_ONCO = new Long(6);

    public final Variable VAR_ONCO_VALORA_DIAGNOSTICO = new Variable("8319008", "SNM3", new Long(7762568),
            "Diagnóstico ");
    public final Variable VAR_ONCO_VALORA_ALERGIA = new Variable("106190000", "SNM3", new Long(13524547), "Alergia");
    public final Variable VAR_ONCO_VALORA_ANTECEDENTES = new Variable("307294006", "SNM3", new Long(13524545),
            "Antecedentes");

    public final Variable VAR_ONCO_VALORA_TRATAMIENTOQT = new Variable("413737006", "SNM3", new Long(13824284),
            "Tratamiento QT");
    public final Variable VAR_ONCO_VALORA_SITSOCIALFAMILIAR = new Variable("9791", "99G2", new Long(9791),
            "Situación familiar");
    public final Variable VAR_ONCO_VALORA_TELEFONO = new Variable("359993007", "SNM3", new Long(14080524), "Teléfono");

    public final Variable VAR_ONCO_VALORA_CICLOS = new Variable("13827905", "99G2", new Long(13827905), "Nº Ciclos");
    public final Variable VAR_ONCO_VALORA_FINICIO = new Variable("298059007", "SNM3", new Long(35001161),
            "Fecha inicio");
    public final Variable VAR_ONCO_VALORA_FFIN = new Variable("413943001", "SNM3", new Long(13824004), "Fecha fin");
// respiraicon

    public final Variable VAR_ONCO_VALORA_RESPISINALTE = new Variable("35001565", "99G2", new Long(35001565),
            "Sin alteración");
    public final Variable VAR_ONCO_VALORA_RESPIDISNEA = new Variable("267036007", "SNM3", new Long(13822584), "Disnea");
    public final Variable VAR_ONCO_VALORA_RESPIOXIGENO = new Variable("24099007", "SNM3", new Long(13825410),
            "Oxigeno");
    public final Variable VAR_ONCO_VALORA_RESPIOTROS = new Variable("35003903", "99G2", new Long(35003903), "Otros:");

    // alimentacin
    public final Variable VAR_ONCO_VALORA_ALIDDIETAHA = new Variable("418995006", "SNM3", new Long(13825356),
            "Dieta habitual");
    public final Variable VAR_ONCO_VALORA_ALIINGESLIQUIDOS = new Variable("251992000", "SNM3", new Long(35000655),
            "Ingesta Líquidos");
    public final Variable VAR_ONCO_VALORA_ALISUPLEMENTO = new Variable("35003925", "99G2", new Long(35003925),
            "Suplemento alimentación");
    public final Variable VAR_ONCO_VALORA_ALIDIFICULDEGLU = new Variable("399122003", "SNM3", new Long(13825384),
            "Dificultad deglución ");
    public final Variable VAR_ONCO_VALORA_ALINAUSEAS = new Variable("73879007", "SNM3", new Long(13822632), "Náuseas:");

    public final Variable VAR_ONCO_VALORA_ALIVOMITOS = new Variable("422400008", "SNM3", new Long(13820925),
            "Vómitos:");
    public final Variable VAR_ONCO_VALORA_ALIALTERAGUSTO = new Variable("271801002", "SNM3", new Long(35003924),
            "Alteración gusto:");
    public final Variable VAR_ONCO_VALORA_ALIOTROS = new Variable("35001884", "99G2", new Long(35001884),
            "Otros cuidados");

    public final Variable VAR_ONCO_VALORA_ELIURICANTIDAD = new Variable("35003944", "99G2", new Long(35003944),
            "Eliminación urinaria cantidad");
    public final Variable VAR_ONCO_VALORA_ELIURICOLOR = new Variable("35003943", "99G2", new Long(35003943),
            "Eliminación urinaria color");

    public final Variable VAR_ONCO_VALORA_ELIURIESCOZOR = new Variable("410718003", "SNM3", new Long(13827526),
            "Eliminación urinaria escozor");
    public final Variable VAR_ONCO_VALORA_ELIURISV = new Variable("35001606", "99G2", new Long(35001606),
            "Paciente portador de sonda vesical");
    public final Variable VAR_ONCO_VALORA_ELIURIUROSTOMIA = new Variable("35001609", "99G2", new Long(35001609),
            "Paciente con urostomía  ");
    public final Variable VAR_ONCO_VALORA_ELIURINEFROSTOMIA = new Variable("35001607", "99G2", new Long(35001607),
            "Paciente con sonda nefrostomía  ");
    public final Variable VAR_ONCO_VALORA_ELIURIOTROS = new Variable("13825524", "99G2", new Long(13825524),
            "Eliminación urinaria otros problemas  ");

    // eliminacion fecal
    public final Variable VAR_ONCO_VALORA_ELIFECALDIARREA = new Variable("62315008", "SNM3", new Long(13822630),
            "Eliminación fecal diarrea  ");
    public final Variable VAR_ONCO_VALORA_ELIFECALESTRENIMIENTO = new Variable("13825548", "99G2", new Long(13825548),
            "Eliminación fecal estreñimiento  ");
    public final Variable VAR_ONCO_VALORA_ELIFECALOSTOMIA = new Variable("35001608", "99G2", new Long(35001608),
            "Eliminación fecal ostomia  ");
    public final Variable VAR_ONCO_VALORA_ELIFECALOTROS = new Variable("13825564", "99G2", new Long(13825564),
            "Eliminación fecal otros problemas  ");
//movilizacion 
    // valores="autónomo,necesita ayuda parcial,necesita ayuda
    // total"separador=",">Movilización</item><

    public final Variable VAR_ONCO_VALORA_MOVILIZA = new Variable("183376001", "SNM3", new Long(13828044),
            "Movilización ");
    public final Variable VAR_ONCO_VALORA_MOVILIZAOTROS = new Variable("35003243", "99G2", new Long(35003243),
            "Movilización otros");

    // valores="100 - Normal. Sin quejas. Sin indicios de enfermedad,90 -
    // Actividades normales pero con signos y síntomas leves de enfermedad,80 -
    // Actividad normal con esfuerzo con algunos signos y síntomas de enfermedad,70
    // - Capaz de cuidarse. Pero incapaz de llevar a término actividades normales de
    // trabajo activo,60 - Rquiere atención ocasional pero puede cuidarse a si
    // mismo,50 - Requiere gran atención incluso de tipo médico. Encamado menos del
    // 50% del día,40 - Inválido incapacitado necesita cuidados y atenciones
    // especiales. Encamado más del 50% del día,30 - Inválido grave severamente
    // incapacitado tratamiento de soporte activo,20 - Encamado por completo.
    // Paciente muy grave. Necesita hospitalización y tratamiento activo,10 -
    // Moribundo,0 - Fallecido"
    public final Variable VAR_ONCO_VALORA_MOVILIZAKARNOFSKY = new Variable("273546003", "SNM3", new Long(13822064),
            "Escala de Karnofsky");
// reposo sueño
    // valores="no manifiesta alteración,insomnio,problemas para conciliar el sueño
    public final Variable VAR_ONCO_VALORA_REPOSUENO = new Variable("39898005", "SNM3", new Long(13822550),
            "Transtrono Reposo Sueño");
    public final Variable VAR_ONCO_VALORA_REPOSUEINFUSIONES = new Variable("35003963", "99G2", new Long(35003963),
            "Precisa infusiones");
    public final Variable VAR_ONCO_VALORA_REPOSUEMEDICACION = new Variable("260779000", "SNM3", new Long(13825604),
            "Precisa medicación para dormir");
    public final Variable VAR_ONCO_VALORA_REPOSUEOTROS = new Variable("35002932", "99G2", new Long(35002932),
            "Otros transtornos del sueño");
// vestio y aseo
    // valores="autónomo,necesita ayuda parcial,necesita ayuda
    public final Variable VAR_ONCO_VALORA_VESTIDO = new Variable("301306007", "SNM3", new Long(35003964),
            "Vestido y Arreglo Personal");
    public final Variable VAR_ONCO_VALORA_VESTIDOOTROS = new Variable("35004123", "99G2", new Long(35004123),
            "Vestido y Arreglo Otros");

    // higiene piel
    public final Variable VAR_ONCO_VALORA_PIELNOALTERA = new Variable("13825267", "99G2", new Long(13825267),
            "No manifiesta alteración");
    public final Variable VAR_ONCO_VALORA_PIELSECA = new Variable("16386004", "SNM3", new Long(13825284), "Piel seca");
    public final Variable VAR_ONCO_VALORA_PIELPALIDEZ = new Variable("16390002", "SNM3", new Long(13825268), "Palidez");
    public final Variable VAR_ONCO_VALORA_PIELICTERICIA = new Variable("18165001", "SNM3", new Long(13822607),
            "Ictericia");
    public final Variable VAR_ONCO_VALORA_PIELPRURITO = new Variable("32738000", "SNM3", new Long(13825269), "Prurito");
    public final Variable VAR_ONCO_VALORA_PIELERITEMA = new Variable("70819003", "SNM3", new Long(35002224), "Eritema");
    public final Variable VAR_ONCO_VALORA_PIELEDEMAS = new Variable("13822585", "SNM3", new Long(13822585), "Edemas");
    public final Variable VAR_ONCO_VALORA_PIELHIPERSENSI = new Variable("91232002", "SNM3", new Long(35003965),
            "Hipersensibilidad");
    public final Variable VAR_ONCO_VALORA_PIELHEMATOMA = new Variable("35566002", "SNM3", new Long(13820845),
            "Hematomas");

    public final Variable VAR_ONCO_VALORA_PIELFLEBITIS = new Variable("61599003", "SNM3", new Long(13821004),
            "Flebitis");
//"quirúrgica,quemadura,UPP,traumática,úlcera vascular
    public final Variable VAR_ONCO_VALORA_PIELHERIDA = new Variable("35001589", "99G2", new Long(35001589), "Herida");
    public final Variable VAR_ONCO_VALORA_PIELOTROS = new Variable("13828564", "99G2", new Long(13828564), "Otros");

    // seguridad
    // valores="reservorio,cateter Hickman,PICC,Abbocath"
    public final Variable VAR_ONCO_VALORA_SEGACCESO = new Variable("13822328", "99G2", new Long(13822328),
            "Portador acceso venoso");
    public final Variable VAR_ONCO_VALORA_SEGACCESOLOCA = new Variable("13825350", "99G2", new Long(13825350),
            "Localización acceso");
    public final Variable VAR_ONCO_VALORA_SEGACCESOPERME = new Variable("82540007", "SNM3", new Long(35003966),
            "Permeable");
//dolor
    public final Variable VAR_ONCO_VALORA_DOLOREVA = new Variable("425401001", "SNM3", new Long(13825347), "EVA");
    public final Variable VAR_ONCO_VALORA_DOLORLOCA = new Variable("287495009", "SNM3", new Long(13825346),
            "Localización dolor");
//situación emocional
    public final Variable VAR_ONCO_VALORA_EMOCIANSIEDAD = new Variable("13817924", "99G2", new Long(13825347),
            "Tiene ansiedad");
    // valores="1,2,3,4,5"
    public final Variable VAR_ONCO_VALORA_EMOCIDEPRE = new Variable("13817925", "99G2", new Long(13817925),
            "Tiene depresión ");
    public final Variable VAR_ONCO_VALORA_EMOCIPAD_T = new Variable("13817926", "99G2", new Long(13817926),
            "Malestar emocional (PAD-T)");
//comunicacion
    // valores="conyuge,padre,madre,hijo,hija,familiar 2º grado,amigo"
    public final Variable VAR_ONCO_VALORA_COMUACOPA = new Variable("159729008", "SNM3", new Long(7756560),
            "Acompañado");
    public final Variable VAR_ONCO_VALORA_COMUOTRACOMPA = new Variable("35003972", "99G2", new Long(35003972),
            "Otro acopañante");
    public final Variable VAR_ONCO_VALORA_COMURELFAM = new Variable("35003973", "99G2", new Long(35003973),
            " Relación con la familia");
    public final Variable VAR_ONCO_VALORA_COMURELPER = new Variable("35003974", "99G2", new Long(35003974),
            " Relación con el personal sanitario");
    public final Variable VAR_ONCO_VALORA_COMUCOLABORA = new Variable("35003975", "99G2", new Long(35003975),
            " Colabora en el tratamiento");
    public final Variable VAR_ONCO_VALORA_COMUCONSPI = new Variable("35003976", "99G2", new Long(35003976),
            "Conspiración de silencio");
    public final Variable VAR_ONCO_VALORA_COMUCASEXU = new Variable("35003977", "99G2", new Long(35003977),
            "Cambios en la sexualidad");
    public final Variable VAR_ONCO_VALORA_COMUOTRO = new Variable("35003978", "99G2", new Long(35003978), "Otros");

    public final Variable VAR_ONCO_VALORA_PERCEPIMAGEN = new Variable("35002900", "99G2", new Long(35002900),
            " Trastorno de la imagen corporal");
    public final Variable VAR_ONCO_VALORA_PERCEPAUTOESTIMA = new Variable("35003981", "99G2", new Long(35003981),
            "Trastorno de la autoestima");
    public final Variable VAR_ONCO_VALORA_PERCEPALOPE = new Variable("270504008", "SNM3", new Long(35003979),
            "Alopecia");
    public final Variable VAR_ONCO_VALORA_PERCEPMASTEC = new Variable("85.4", "I9C", new Long(103653), "Mastectomía");
    public final Variable VAR_ONCO_VALORA_PERCEPCOLOST = new Variable("46.1", "I9C", new Long(101889), "Colostomía");
    public final Variable VAR_ONCO_VALORA_PERCEPOTROS = new Variable("35003980", "99G2", new Long(35003980),
            "Otros valoración personal");
// aprender ????
    public final Variable VAR_ONCO_VALORA_APRECOMPREDE = new Variable("307082005", "SNM3", new Long(35003983),
            "Comprende información");
    public final Variable VAR_ONCO_VALORA_APREDEMANDA = new Variable("35003984", "99G2", new Long(35003984),
            "Colostomía");

    // ---
    public RegistroOncoValoracion() {
        this.iniciaRegOncoValoracion();
    }

    public RegistroOncoValoracion(Long id) {
        super(id);
        this.iniciaRegOncoValoracion();
    }

    public RegistroOncoValoracion(RegistroOncoValoracion r) {
        super(r);
        this.diagnostico = r.getDiagnostico();
        this.alergias = r.getAlergias();
        this.antecedentes = r.getAntecedentes();
        this.tratamientoqt = r.getTratamientoqt();
        this.sitFamiliarSocial = r.getSitFamiliarSocial();
        this.telefono = r.getTelefono();
        this.cliclos = r.getCliclos();
        this.fechaInicio = r.getFechaFin();
        this.fechaFin = r.getFechaFin();
        // respiracion
        this.respiNoAlteracion = r.getRespiNoAlteracion();
        this.respidisnea = r.getRespidisnea();
        this.respiOxigeno = r.getRespiOxigeno();
        this.respiOtros = r.getRespiOtros();
        // alimentacion
        this.aliDietaHabitual = r.getAliDietaHabitual();
        this.aliIngestaLiquidos = r.getAliIngestaLiquidos();
        this.aliSuplemento = r.getAliSuplemento();
        this.aliDificultadDeglu = r.getAliDificultadDeglu();
        this.aliNauseas = r.getAliNauseas();
        this.aliVomitos = r.getAliVomitos();
        this.aliAteraGusto = getAliAteraGusto();
        this.aliOtros = r.getAliOtros();
        // eliminación urinaria
        this.eliuriCantidad = r.getEliuriCantidad();
        this.eliuriColor = r.getEliuriColor();
        this.eliuriEscozor = r.getEliuriEscozor();
        this.eliuriPortadorSV = r.getEliuriPortadorSV();
        this.eliuriUrostomia = r.getEliuriUrostomia();
        this.eliuriNefrostomia = r.getEliuriNefrostomia();
        this.eliuriOtros = r.getEliuriOtros();
        // eliminación fecal
        this.elifecalDiarrea = r.getElifecalDiarrea();
        this.elifecalEstrenimiento = r.getElifecalEstrenimiento();
        this.elifecalOstomia = r.getElifecalOstomia();
        this.elifecalOtro = r.getElifecalOtro();
        // movilizacion
        this.movilizacion = r.getMovilizacion();
        this.movilizacionOtros = r.getMovilizacionOtros();
        this.karnofsky = r.karnofsky;
        // reposo sueño
        this.repoSueno = r.getRepoSueno();
        this.repoSueInfusiones = r.getRepoSueInfusiones();
        this.repoSueMedicacion = r.getRepoSueMedicacion();
        this.repoSueOtros = r.getRepoSueOtros();
        // vestido aseo
        this.vestidoAseo = r.getVestidoAseo();
        this.vestidoAseoOtros = r.getVestidoAseoOtros();
// higiene piel
        this.pielNoAlteracion = r.getPielNoAlteracion();
        this.pielSeca = r.getPielSeca();
        this.pielPalidez = r.getPielPalidez();
        this.pielIctericia = r.getPielIctericia();
        this.pielPrurito = r.getPielPrurito();
        this.pielEritema = r.getPielEritema();
        this.pielEdemas = r.getPielEdemas();
        this.pielHipersensibilidad = r.getPielHipersensibilidad();
        this.pielHematomas = r.getPielHematomas();
        this.pielFlebitis = r.getPielFlebitis();
        this.pielHeridas = r.getPielHeridas();
        this.pielOtros = r.getPielOtros();
        // seguridad
        this.segAccesoTipo = r.getSegAccesoTipo();
        this.segAccesoLoca = r.getSegAccesoLoca();
        this.segAccesoPermeable = r.getSegAccesoPermeable();
        // dolor
        this.dolorEva = r.getDolorEva();
        this.dolorLocalizacion = r.getDolorLocalizacion();
        // situación emocional
        this.emocioAnsiedad = r.getEmocioAnsiedad();
        this.emocioDepresion = r.getEmocioDepresion();
        this.emocioPAD_T = r.getEmocioPAD_T();
        // comunicacion
        this.comuAcompanado = r.getComuAcompanado();
        this.comuOtraCompa = r.getComuOtraCompa();
        this.comuRelacionFam = r.getComuRelacionFam();
        this.comuRelacionPer = r.getComuRelacionPer();
        this.comuColabora = r.getComuColabora();
        this.comuConspiraSilen = r.getComuConspiraSilen();
        this.comuCambioSexual = r.getComuCambioSexual();
        this.comuOtro = r.getComuOtro();
        // perceptión personal
        this.percepImagen = r.getPercepImagen();
        this.percepAutoestima = r.getPercepAutoestima();
        this.percepAlopecia = r.getPercepAlopecia();
        this.percepMastectomia = r.getPercepMastectomia();
        this.percepColostomia = r.getPercepColostomia();
        this.percepOtros = r.getPercepOtros();
        // aprende
        this.aprenComprende = r.getAprenComprende();
        this.aprenDemanda = r.getAprenDemanda();
    }

    private void iniciaRegOncoValoracion() {
        this.setTiporegistro(TIPO_REGISTRO_ONCO);
        this.setDescripcion("Registro Valoración HDIA oncológico");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_ONCO_VALORACION);
        this.setServicio(Servicio.SERVICIO_ONCOLOGIA);

        this.diagnostico = VAR_ONCO_VALORA_DIAGNOSTICO;
        this.alergias = VAR_ONCO_VALORA_ALERGIA;
        this.antecedentes = VAR_ONCO_VALORA_ANTECEDENTES;
        this.tratamientoqt = VAR_ONCO_VALORA_TRATAMIENTOQT;
        this.sitFamiliarSocial = VAR_ONCO_VALORA_SITSOCIALFAMILIAR;
        this.telefono = VAR_ONCO_VALORA_TELEFONO;
        this.cliclos = VAR_ONCO_VALORA_CICLOS;
        this.fechaInicio = VAR_ONCO_VALORA_FINICIO;
        this.fechaFin = VAR_ONCO_VALORA_FFIN;
        // respiracion
        this.respiNoAlteracion = VAR_ONCO_VALORA_RESPISINALTE;
        this.respidisnea = VAR_ONCO_VALORA_RESPIDISNEA;
        this.respiOxigeno = VAR_ONCO_VALORA_RESPIOXIGENO;
        this.respiOtros = VAR_ONCO_VALORA_RESPIOTROS;
        // alimentacion
        this.aliDietaHabitual = VAR_ONCO_VALORA_ALIDDIETAHA;
        this.aliIngestaLiquidos = VAR_ONCO_VALORA_ALIINGESLIQUIDOS;
        this.aliSuplemento = VAR_ONCO_VALORA_ALISUPLEMENTO;
        this.aliDificultadDeglu = VAR_ONCO_VALORA_ALIDIFICULDEGLU;
        this.aliNauseas = VAR_ONCO_VALORA_ALINAUSEAS;
        this.aliVomitos = VAR_ONCO_VALORA_ALIVOMITOS;
        this.aliAteraGusto = VAR_ONCO_VALORA_ALIALTERAGUSTO;
        this.aliOtros = VAR_ONCO_VALORA_ALIOTROS;
        // eliminacion urinaira
        this.eliuriCantidad = VAR_ONCO_VALORA_ELIURICANTIDAD;
        this.eliuriColor = VAR_ONCO_VALORA_ELIURICOLOR;
        this.eliuriEscozor = VAR_ONCO_VALORA_ELIURIESCOZOR;
        this.eliuriPortadorSV = VAR_ONCO_VALORA_ELIURISV;
        this.eliuriUrostomia = VAR_ONCO_VALORA_ELIURIUROSTOMIA;
        this.eliuriNefrostomia = VAR_ONCO_VALORA_ELIURINEFROSTOMIA;
        this.eliuriOtros = VAR_ONCO_VALORA_ELIURIOTROS;
        // eliminación fecal
        this.elifecalDiarrea = VAR_ONCO_VALORA_ELIFECALDIARREA;
        this.elifecalEstrenimiento = VAR_ONCO_VALORA_ELIFECALESTRENIMIENTO;
        this.elifecalOstomia = VAR_ONCO_VALORA_ELIFECALOSTOMIA;
        this.elifecalOtro = VAR_ONCO_VALORA_ELIFECALOTROS;
        // movolizacion
        this.movilizacion = VAR_ONCO_VALORA_MOVILIZA;
        this.movilizacionOtros = VAR_ONCO_VALORA_MOVILIZAOTROS;
        this.karnofsky = VAR_ONCO_VALORA_MOVILIZAKARNOFSKY;
        // reposo sueño
        this.repoSueno = VAR_ONCO_VALORA_REPOSUENO;
        this.repoSueInfusiones = VAR_ONCO_VALORA_REPOSUEINFUSIONES;
        this.repoSueMedicacion = VAR_ONCO_VALORA_REPOSUEMEDICACION;
        this.repoSueOtros = VAR_ONCO_VALORA_REPOSUEOTROS;
        // vestido y aseo
        this.vestidoAseo = VAR_ONCO_VALORA_VESTIDO;
        this.vestidoAseoOtros = VAR_ONCO_VALORA_VESTIDOOTROS;
        // higiene piel
        this.pielNoAlteracion = VAR_ONCO_VALORA_PIELNOALTERA;
        this.pielSeca = VAR_ONCO_VALORA_PIELSECA;
        this.pielPalidez = VAR_ONCO_VALORA_PIELPALIDEZ;
        this.pielIctericia = VAR_ONCO_VALORA_PIELICTERICIA;
        this.pielPrurito = VAR_ONCO_VALORA_PIELPRURITO;
        this.pielEritema = VAR_ONCO_VALORA_PIELERITEMA;
        this.pielEdemas = VAR_ONCO_VALORA_PIELEDEMAS;
        this.pielHipersensibilidad = VAR_ONCO_VALORA_PIELHIPERSENSI;
        this.pielHematomas = VAR_ONCO_VALORA_PIELHEMATOMA;
        this.pielFlebitis = VAR_ONCO_VALORA_PIELFLEBITIS;
        this.pielHeridas = VAR_ONCO_VALORA_PIELHERIDA;
        this.pielOtros = VAR_ONCO_VALORA_PIELOTROS;
        // seguridad
        this.segAccesoTipo = VAR_ONCO_VALORA_SEGACCESO;
        this.segAccesoLoca = VAR_ONCO_VALORA_SEGACCESOLOCA;
        this.segAccesoPermeable = VAR_ONCO_VALORA_SEGACCESOPERME;
        // dolor
        this.dolorEva = VAR_ONCO_VALORA_DOLOREVA;
        this.dolorLocalizacion = VAR_ONCO_VALORA_DOLORLOCA;
        // situación emocional
        this.emocioAnsiedad = VAR_ONCO_VALORA_EMOCIANSIEDAD;
        this.emocioDepresion = VAR_ONCO_VALORA_EMOCIDEPRE;
        this.emocioPAD_T = VAR_ONCO_VALORA_EMOCIPAD_T;
        // comunicacion
        this.comuAcompanado = VAR_ONCO_VALORA_COMUACOPA;
        this.comuOtraCompa = VAR_ONCO_VALORA_COMUOTRACOMPA;
        this.comuRelacionFam = VAR_ONCO_VALORA_COMURELFAM;
        this.comuRelacionPer = VAR_ONCO_VALORA_COMURELPER;
        this.comuColabora = VAR_ONCO_VALORA_COMUCOLABORA;
        this.comuConspiraSilen = VAR_ONCO_VALORA_COMUCONSPI;
        this.comuCambioSexual = VAR_ONCO_VALORA_COMUCASEXU;
        this.comuOtro = VAR_ONCO_VALORA_COMUOTRO;
        // percepción personal
        this.percepImagen = VAR_ONCO_VALORA_PERCEPIMAGEN;
        this.percepAutoestima = VAR_ONCO_VALORA_PERCEPAUTOESTIMA;
        this.percepAlopecia = VAR_ONCO_VALORA_PERCEPALOPE;
        this.percepMastectomia = VAR_ONCO_VALORA_PERCEPMASTEC;
        this.percepColostomia = VAR_ONCO_VALORA_PERCEPCOLOST;
        this.percepOtros = VAR_ONCO_VALORA_PERCEPOTROS;
        // aprende
        this.aprenComprende = VAR_ONCO_VALORA_APRECOMPREDE;
        this.aprenDemanda = VAR_ONCO_VALORA_APREDEMANDA;
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
        ;
    }

    public Variable getAlergias() {
        return alergias;
    }

    public Variable getVariableAlergias() {
        return alergias;
    }

    public void setAlergias(Variable alergias) {
        this.alergias = alergias;
    }

    public String getAlergiasString() {
        return alergias.getValor();
    }

    public void setAlergias(String valor) {
        this.alergias.setValor(valor);
    }

    public Variable getAntecedentes() {
        return antecedentes;
    }

    public Variable getVariableAntecedentes() {
        return antecedentes;
    }

    public String getAntecedentesString() {
        return antecedentes.getValor();
    }

    public void setAntecedentes(Variable antecedentes) {
        this.antecedentes = antecedentes;
    }

    public void setAntecedentes(String valor) {
        this.antecedentes.setValor(valor);
    }

    public Variable getTratamientoqt() {
        return tratamientoqt;
    }

    public Variable getVariableTratamientoqt() {
        return tratamientoqt;
    }

    public String getTratamientoqtString() {
        return tratamientoqt.getValor();
    }

    public void setTratamientoqt(Variable tratamientoqt) {
        this.tratamientoqt = tratamientoqt;
    }

    public void setTratamientoqt(String valor) {
        this.tratamientoqt.setValor(valor);
    }

    public Variable getSitFamiliarSocial() {
        return sitFamiliarSocial;
    }

    public Variable getVariableSitFamiliarSocial() {
        return sitFamiliarSocial;
    }

    public String getSitFamiliarSocialString() {
        return sitFamiliarSocial.getValor();
    }

    public void setSitFamiliarSocial(Variable sitFamiliarSocial) {
        this.sitFamiliarSocial = sitFamiliarSocial;
    }

    public void setSitFamiliarSocial(String valor) {
        this.sitFamiliarSocial.setValor(valor);
    }

    public Variable getTelefono() {
        return telefono;
    }

    public Variable getVariableTelefono() {
        return telefono;
    }

    public String getTelefonoString() {
        return telefono.getValor();
    }

    public void setTelefono(Variable telefono) {
        this.telefono = telefono;
    }

    public void setTelefono(String valor) {
        this.telefono.setValor(valor);
        ;
    }

    public Variable getCliclos() {
        return cliclos;
    }

    public Variable getVariableCliclos() {
        return cliclos;
    }

    public String getCliclosString() {
        return cliclos.getValor();
    }

    public void setCliclos(Variable cliclos) {
        this.cliclos = cliclos;
    }

    public void setCliclos(String valor) {
        this.cliclos.setValor(valor);
    }

    public Variable getFechaInicio() {
        return fechaInicio;
    }

    public Variable getVariableFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioLocalDate() {
        LocalDate fechaDate = null;
        if (fechaInicio != null && fechaInicio.getValor() != null && !fechaInicio.getValor().isEmpty()) {
            if (Utilidades.isNumeric(fechaInicio.getValor())) {
                fechaDate = Utilidades.getFechaLocalDate(fechaInicio.getValor());
            }
        }
        return fechaDate;
    }

    public void setFechaInicio(Variable fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicio(String valor) {
        this.fechaInicio.setValor(valor);
    }

    public void setFechaInicio(LocalDate fecha) {
        this.fechaInicio.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getFechaFin() {
        return fechaFin;
    }

    public Variable getVariableFechaFin() {
        return fechaFin;
    }

    public LocalDate getFechaFinDate() {
        LocalDate fechaDate = null;
        if (fechaFin != null && fechaFin.getValor() != null && !fechaFin.getValor().isEmpty()) {
            if (Utilidades.isNumeric(fechaFin.getValor())) {
                fechaDate = Utilidades.getFechaLocalDate(fechaFin.getValor());
            }
        }
        return fechaDate;
    }

    public void setFechaFin(Variable fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaFin(String valor) {
        this.fechaFin.setValor(valor);
    }

    public void setFechaFin(LocalDate fecha) {
        this.fechaFin.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getRespiNoAlteracion() {
        return respiNoAlteracion;
    }

    public Variable getVariableRespiNoAlteracion() {
        return respiNoAlteracion;
    }

    public String getRespiNoAlteracionString() {
        return respiNoAlteracion.getValor();
    }

    public Boolean getRespiNoAlteracionBoolean() {
        if (respiNoAlteracion != null && !respiNoAlteracion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiNoAlteracion(Variable respiNoAlteracion) {
        this.respiNoAlteracion = respiNoAlteracion;
    }

    public void setRespiNoAlteracion(String valor) {
        this.respiNoAlteracion.setValor(valor);
    }

    public void setRespiNoAlteracion(Boolean valor) {
        if (valor == true) {
            respiNoAlteracion.setValor("Sin alteración");
        } else {
            respiNoAlteracion.setValor("");
        }
    }

    public Variable getRespidisnea() {
        return respidisnea;
    }

    public Variable getVariableRespidisnea() {
        return respidisnea;
    }

    public String getRespidisneaString() {
        return respidisnea.getValor();
    }

    public Boolean getRespidisneaBoolean() {
        if (respidisnea != null && !respidisnea.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespidisnea(Variable respidisnea) {
        this.respidisnea = respidisnea;
    }

    public void setRespidisnea(String valor) {
        this.respidisnea.setValor(valor);
    }

    public void setRespidisnea(Boolean valor) {
        if (valor == true) {
            this.respidisnea.setValor("Disnea");
        } else {
            this.respidisnea.setValor("");
        }
    }

    public Variable getRespiOxigeno() {
        return respiOxigeno;
    }

    public Variable getVariableRespiOxigeno() {
        return respiOxigeno;
    }

    public String getRespiOxigenoString() {
        return respiOxigeno.getValor();
    }

    public Boolean getRespiOxigenoBoolean() {

        if (respiOxigeno != null && !respiOxigeno.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiOxigeno(Variable respiOxigeno) {
        this.respiOxigeno = respiOxigeno;
    }

    public void setRespiOxigeno(String valor) {
        this.respiOxigeno.setValor(valor);
    }

    public void setRespiOxigeno(Boolean valor) {
        if (valor == true) {
            respiOxigeno.setValor("Oxígeno");
        } else {
            respiOxigeno.setValor("");
        }
    }

    public Variable getRespiOtros() {
        return respiOtros;
    }

    public Variable getVariableRespiOtros() {
        return respiOtros;
    }

    public String getRespiOtrosString() {
        return respiOtros.getValor();
    }

    public void setRespiOtros(Variable respiOtros) {
        this.respiOtros = respiOtros;
    }

    public void setRespiOtros(String valor) {
        this.respiOtros.setValor(valor);
    }

// alimentación
    public Variable getAliDietaHabitual() {
        return aliDietaHabitual;
    }

    public Variable getVariableAliDietaHabitual() {
        return aliDietaHabitual;
    }

    public String getAliDietaHabitualString() {
        return aliDietaHabitual.getValor();
    }

    public void setAliDietaHabitual(Variable aliDietaHabitual) {
        this.aliDietaHabitual = aliDietaHabitual;
    }

    public void setAliDietaHabitual(String valor) {
        this.aliDietaHabitual.setValor(valor);
    }

    public Variable getAliIngestaLiquidos() {
        return aliIngestaLiquidos;
    }

    public Variable getVariableAliIngestaLiquidos() {
        return aliIngestaLiquidos;
    }

    public String getAliIngestaLiquidosString() {
        return aliIngestaLiquidos.getValor();
    }

    public void setAliIngestaLiquidos(Variable aliIngestaLiquidos) {
        this.aliIngestaLiquidos = aliIngestaLiquidos;
    }

    public void setAliIngestaLiquidos(String valor) {
        this.aliIngestaLiquidos.setValor(valor);
    }

    public Variable getAliSuplemento() {
        return aliSuplemento;
    }

    public Variable getVariableAliSuplemento() {
        return aliSuplemento;
    }

    public String getAliSuplementoString() {
        return aliSuplemento.getValor();
    }

    public void setAliSuplemento(Variable aliSuplemento) {
        this.aliSuplemento = aliSuplemento;
    }

    public void setAliSuplemento(String valor) {
        this.aliSuplemento.setValor(valor);
    }

    public Variable getAliDificultadDeglu() {
        return aliDificultadDeglu;
    }

    public Variable getVariableAliDificultadDeglu() {
        return aliDificultadDeglu;
    }

    public Boolean getAliDificultadDegluBoolean() {
        if (aliDificultadDeglu != null && !aliDificultadDeglu.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliDificultadDeglu(Variable aliDificultadDeglu) {
        this.aliDificultadDeglu = aliDificultadDeglu;
    }

    public void setAliDificultadDeglu(Boolean valor) {
        if (valor == true) {
            this.aliDificultadDeglu.setValor("Dificultad en la deglución");
        } else {
            this.aliDificultadDeglu.setValor("");
        }
    }

    public Variable getAliNauseas() {
        return aliNauseas;
    }

    public Variable getVariableAliNauseas() {
        return aliNauseas;
    }

    public Boolean getAliNauseasBoolean() {
        if (aliNauseas != null && !aliNauseas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliNauseas(Variable aliNauseas) {
        this.aliNauseas = aliNauseas;
    }

    public void setAliNauseas(Boolean valor) {
        if (valor == true) {
            this.aliNauseas.setValor("Nauseas");
        } else {
            this.aliNauseas.setValor("");
        }
    }

    public Variable getAliVomitos() {
        return aliVomitos;
    }

    public Variable getVariableAliVomitos() {
        return aliVomitos;
    }

    public Boolean getAliVomitosBoolean() {
        if (aliVomitos != null && !aliVomitos.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliVomitos(Variable aliVomitos) {
        this.aliVomitos = aliVomitos;
    }

    public void setAliVomitos(Boolean valor) {
        if (valor == true) {
            this.aliVomitos.setValor("Vómitos");
        } else {
            this.aliVomitos.setValor("");
        }
    }

    public Variable getAliAteraGusto() {
        return aliAteraGusto;
    }

    public Variable getVariableAliAteraGusto() {
        return aliAteraGusto;
    }

    public Boolean getAliAteraGustoBoolean() {
        if (aliAteraGusto != null && !aliAteraGusto.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliAteraGusto(Variable aliAteraGusto) {
        this.aliAteraGusto = aliAteraGusto;
    }

    public void setAliAteraGusto(Boolean valor) {
        if (valor == true) {
            this.aliAteraGusto.setValor("Alteración del gusto");
        } else {
            this.aliAteraGusto.setValor("");
        }
    }

    public Variable getAliOtros() {
        return aliOtros;
    }

    public Variable getVariableAliOtros() {
        return aliOtros;
    }

    public String getAliOtrosString() {
        return aliOtros.getValor();
    }

    public void setAliOtros(Variable aliOtros) {
        this.aliOtros = aliOtros;
    }

    public void setAliOtros(String valor) {
        this.aliOtros.setValor(valor);
    }

    // eliminacion
    public Variable getEliuriCantidad() {
        return eliuriCantidad;
    }

    public Variable getVariableEliuriCantidad() {
        return eliuriCantidad;
    }

    public String getEliuriCantidadString() {
        return eliuriCantidad.getValor();
    }

    public void setEliuriCantidad(Variable eliuriCantidad) {
        this.eliuriCantidad = eliuriCantidad;
    }

    public void setEliuriCantidad(String valor) {
        this.eliuriCantidad.setValor(valor);
    }

    public Variable getEliuriColor() {
        return eliuriColor;
    }

    public Variable getVariableEliuriColor() {
        return eliuriColor;
    }

    public String getEliuriColorString() {
        return eliuriColor.getValor();
    }

    public void setEliuriColor(Variable eliuriColor) {
        this.eliuriColor = eliuriColor;
    }

    public void setEliuriColor(String valor) {
        this.eliuriColor.setValor(valor);
    }

    public Variable getEliuriEscozor() {
        return eliuriEscozor;
    }

    public Variable getVariableEliuriEscozor() {
        return eliuriEscozor;
    }

    public Boolean getEliuriEscozorBoolean() {
        if (eliuriEscozor != null && !eliuriEscozor.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliuriEscozor(Variable eliuriEscozor) {
        this.eliuriEscozor = eliuriEscozor;
    }

    public void setEliuriEscozor(Boolean valor) {
        if (valor == true) {
            this.eliuriEscozor.setValor("Escozor eliminación urinaria");
        } else {
            this.eliuriEscozor.setValor("");
        }
    }

    public Variable getEliuriPortadorSV() {
        return eliuriPortadorSV;
    }

    public Variable getVariableEliuriPortadorSV() {
        return eliuriPortadorSV;
    }

    public Boolean getEliuriPortadorSVBoolean() {
        if (eliuriPortadorSV != null && !eliuriPortadorSV.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliuriPortadorSV(Variable eliuriPortadorSV) {
        this.eliuriPortadorSV = eliuriPortadorSV;
    }

    public void setEliuriPortadorSV(Boolean valor) {
        if (valor == true) {
            this.eliuriPortadorSV.setValor("Portador sonda vesical");
        } else {
            this.eliuriPortadorSV.setValor("");
        }
    }

    public Variable getEliuriUrostomia() {
        return eliuriUrostomia;
    }

    public Variable getVariableEliuriUrostomia() {
        return eliuriUrostomia;
    }

    public Boolean getEliuriUrostomiaBoolean() {
        if (eliuriUrostomia != null && !eliuriUrostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliuriUrostomia(Variable eliuriUrostomía) {
        this.eliuriUrostomia = eliuriUrostomía;
    }

    public void setEliuriUrostomia(Boolean valor) {
        if (valor == true) {
            this.eliuriUrostomia.setValor("Paciente con urostomia");
        } else {
            this.eliuriUrostomia.setValor("");
        }
    }

    public Variable getEliuriNefrostomia() {
        return eliuriNefrostomia;
    }

    public void setEliuriNefrostomia(Variable eliuriNefrostomia) {
        this.eliuriNefrostomia = eliuriNefrostomia;
    }

    public Variable getVariableeliuriNefrostomia() {
        return eliuriNefrostomia;
    }

    public Boolean geteliuriNefrostomiaboolean() {
        if (eliuriNefrostomia != null && !eliuriNefrostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void seteliuriNefrostomia(Variable eliuriNefrostomia) {
        this.eliuriNefrostomia = eliuriNefrostomia;
    }

    public void seteliuriNefrostomia(Boolean valor) {
        if (valor == true) {
            this.eliuriNefrostomia.setValor("Paciente con nefrostomia");
        } else {
            this.eliuriNefrostomia.setValor("");
        }
    }

    public Variable getEliuriOtros() {
        return eliuriOtros;
    }

    public Variable getVariableEliuriOtros() {
        return eliuriOtros;
    }

    public String getEliuriOtrosString() {
        return eliuriOtros.getValor();
    }

    public void setEliuriOtros(Variable eliuriOtros) {
        this.eliuriOtros = eliuriOtros;
    }

    public void setEliuriOtros(String valor) {
        this.eliuriOtros.setValor(valor);
    }

    public Variable getElifecalDiarrea() {
        return elifecalDiarrea;
    }

    public Variable getVariableElifecalDiarrea() {
        return elifecalDiarrea;
    }

    public String getElifecalDiarreaString() {
        return elifecalDiarrea.getValor();
    }

    public void setElifecalDiarrea(Variable elifecalDiarrea) {
        this.elifecalDiarrea = elifecalDiarrea;
    }

    public void setElifecalDiarrea(String valor) {
        this.elifecalDiarrea.setValor(valor);
    }

    public Variable getElifecalEstrenimiento() {
        return elifecalEstrenimiento;
    }

    public Variable getVariableElifecalEstrenimiento() {
        return elifecalEstrenimiento;
    }

    public String getElifecalEstrenimientoString() {
        return elifecalEstrenimiento.getValor();
    }

    public void setElifecalEstrenimiento(Variable elifecalEstrenimiento) {
        this.elifecalEstrenimiento = elifecalEstrenimiento;
    }

    public void setElifecalEstrenimiento(String valor) {
        this.elifecalEstrenimiento.setValor(valor);
    }

    public Variable getElifecalOstomia() {
        return elifecalOstomia;
    }

    public Variable getVariableElifecalOstomia() {
        return elifecalOstomia;
    }

    public Boolean getElifecalOstomiaBoolean() {
        if (elifecalOstomia != null && !elifecalOstomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecalOstomia(Variable elifecalOstomia) {
        this.elifecalOstomia = elifecalOstomia;
    }

    public void setElifecalOstomia(Boolean valor) {
        if (valor == true) {
            this.elifecalOstomia.setValor("Paciente con ostomía");
        } else {
            this.elifecalOstomia.setValor("");
        }
    }

    public Variable getElifecalOtro() {
        return elifecalOtro;
    }

    public Variable getVariableElifecalOtro() {
        return elifecalOtro;
    }

    public String getElifecalOtroString() {
        return elifecalOtro.getValor();
    }

    public void setElifecalOtro(Variable elifecalOtro) {
        this.elifecalOtro = elifecalOtro;
    }

    public void setElifecalOtro(String valor) {
        this.elifecalOtro.setValor(valor);
    }

    // movilizacion
    public Variable getMovilizacion() {
        return movilizacion;
    }

    public Variable getVariableMovilizacion() {
        return movilizacion;
    }

    public String getMovilizacionString() {
        return movilizacion.getValor();
    }

    public void setMovilizacion(Variable movilizacion) {
        this.movilizacion = movilizacion;
    }

    public void setMovilizacion(String valor) {
        this.movilizacion.setValor(valor);
    }

    public Variable getMovilizacionOtros() {
        return movilizacionOtros;
    }

    public Variable getVariableMovilizacionOtros() {
        return movilizacionOtros;
    }

    public String getMovilizacionOtrosString() {
        return movilizacionOtros.getValor();
    }

    public void setMovilizacionOtros(Variable movilizacionOtros) {
        this.movilizacionOtros = movilizacionOtros;
    }

    public void setMovilizacionOtros(String valor) {
        this.movilizacionOtros.setValor(valor);
    }

    public Variable getKarnofsky() {
        return karnofsky;
    }

    public Variable getVariableKarnofsky() {
        return karnofsky;
    }

    public String getKarnofskyString() {
        return karnofsky.getValor();
    }

    public void setKarnofsky(Variable karnofsky) {
        this.karnofsky = karnofsky;
    }

    public void setKarnofsky(String valor) {
        this.karnofsky.setValor(valor);
    }

    // reposo sueño
    public Variable getRepoSueno() {
        return repoSueno;
    }

    public Variable getVariableRepoSueno() {
        return repoSueno;
    }

    public String getRepoSuenoString() {
        return repoSueno.getValor();
    }

    public void setRepoSueno(Variable repoSueno) {
        this.repoSueno = repoSueno;
    }

    public void setRepoSueno(String valor) {
        this.repoSueno.setValor(valor);
        ;
    }

    public Variable getRepoSueInfusiones() {
        return repoSueInfusiones;
    }

    public Variable getVariableRepoSueInfusiones() {
        return repoSueInfusiones;
    }

    public Boolean getRepoSueInfusionesBoolean() {
        if (repoSueInfusiones != null && !repoSueInfusiones.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRepoSueInfusiones(Variable repoSueInfusiones) {
        this.repoSueInfusiones = repoSueInfusiones;
    }

    public void setRepoSueInfusiones(Boolean valor) {
        if (valor == true) {
            this.repoSueInfusiones.setValor("Precisa infusines para dormir");
        } else {
            this.repoSueInfusiones.setValor("");
        }
    }

    public Variable getRepoSueMedicacion() {
        return repoSueMedicacion;
    }

    public Variable getVariableRepoSueMedicacion() {
        return repoSueMedicacion;
    }

    public String getRepoSueMedicacionString() {
        return repoSueMedicacion.getValor();
    }

    public void setRepoSueMedicacion(Variable repoSueMedicacion) {
        this.repoSueMedicacion = repoSueMedicacion;
    }

    public void setRepoSueMedicacion(String valor) {
        this.repoSueMedicacion.setValor(valor);
    }

    public Variable getRepoSueOtros() {
        return repoSueOtros;
    }

    public Variable getVariableRepoSueOtros() {
        return repoSueOtros;
    }

    public String getRepoSueOtrosString() {
        return repoSueOtros.getValor();
    }

    public void setRepoSueOtros(Variable repoSueOtros) {
        this.repoSueOtros = repoSueOtros;
    }

    public void setRepoSueOtros(String valor) {
        this.repoSueOtros.setValor(valor);
    }
// vestido y aseo personal

    public Variable getVestidoAseo() {
        return vestidoAseo;
    }

    public Variable getVariableVestidoAseo() {
        return vestidoAseo;
    }

    public String getVestidoAseoString() {
        return vestidoAseo.getValor();
    }

    public void setVestidoAseo(Variable vestidoAseo) {
        this.vestidoAseo = vestidoAseo;
    }

    public void setVestidoAseo(String valor) {
        this.vestidoAseo.setValor(valor);
        ;
    }

    public Variable getVestidoAseoOtros() {
        return vestidoAseoOtros;
    }

    public Variable getVariableVestidoAseoOtros() {
        return vestidoAseoOtros;
    }

    public String getVestidoAseoOtrosString() {
        return vestidoAseoOtros.getValor();
    }

    public void setVestidoAseoOtros(Variable vestidoAseoOtros) {
        this.vestidoAseoOtros = vestidoAseoOtros;
    }

    public void setVestidoAseoOtros(String valor) {
        this.vestidoAseoOtros.setValor(valor);
    }

// higiene piel
    public Variable getPielNoAlteracion() {
        return pielNoAlteracion;
    }

    public Variable getVariablePielNoAlteracion() {
        return pielNoAlteracion;
    }

    public Boolean getPielNoAlteracionBoolean() {
        if (pielNoAlteracion != null && !pielNoAlteracion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielNoAlteracion(Variable pielNoAlteracion) {
        this.pielNoAlteracion = pielNoAlteracion;
    }

    public void setPielNoAlteracion(Boolean valor) {
        if (valor == true) {
            this.pielNoAlteracion.setValor("No alteración piel ");
        } else {
            this.pielNoAlteracion.setValor("");
        }
    }

    public Variable getPielSeca() {
        return pielSeca;
    }

    public Variable getVariablePielSeca() {
        return pielSeca;
    }

    public Boolean getPielSecaBoolean() {
        if (pielSeca != null && !pielSeca.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielSeca(Variable pielSeca) {
        this.pielSeca = pielSeca;
    }

    public void setPielSeca(Boolean valor) {
        if (valor == true) {
            this.pielSeca.setValor("Piel seca");
        } else {
            this.pielSeca.setValor("");
        }
    }

    public Variable getPielPalidez() {
        return pielPalidez;
    }

    public Variable getVariablePielPalidez() {
        return pielPalidez;
    }

    public Boolean getPielPalidezBoolean() {
        if (pielPalidez != null && !pielPalidez.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielPalidez(Variable pielPalidez) {
        this.pielPalidez = pielPalidez;
    }

    public void setPielPalidez(Boolean valor) {
        if (valor == true) {
            this.pielPalidez.setValor("Piel palidez");
        } else {
            this.pielPalidez.setValor("");
        }
    }

    public Variable getPielIctericia() {
        return pielIctericia;
    }

    public Variable getVariablePielIctericia() {
        return pielIctericia;
    }

    public Boolean getPielIctericiaBoolean() {
        if (pielIctericia != null && !pielIctericia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielIctericia(Variable pielIctericia) {
        this.pielIctericia = pielIctericia;
    }

    public void setPielIctericia(Boolean valor) {
        if (valor == true) {
            this.pielIctericia.setValor("Piel icterica");
        } else {
            this.pielIctericia.setValor("");
        }
    }

    public Variable getPielPrurito() {
        return pielPrurito;
    }

    public Variable getVariablePielPrurito() {
        return pielPrurito;
    }

    public Boolean getPielPruritoBoolean() {
        if (pielPrurito != null && !pielPrurito.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielPrurito(Variable pielPrurito) {
        this.pielPrurito = pielPrurito;
    }

    public void setPielPrurito(Boolean valor) {
        if (valor == true) {
            this.pielPrurito.setValor("Piel prurito");
        } else {
            this.pielPrurito.setValor("");
        }
    }

    public Variable getPielEritema() {
        return pielEritema;
    }

    public Variable getVariablePielEritema() {
        return pielEritema;
    }

    public Boolean getPielEritemaBoolean() {
        if (pielEritema != null && !pielEritema.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielEritema(Variable pielEritema) {
        this.pielEritema = pielEritema;
    }

    public void setPielEritema(Boolean valor) {
        if (valor == true) {
            this.pielEritema.setValor("Piel eritea");
        } else {
            this.pielEritema.setValor("");
        }
    }

    public Variable getPielEdemas() {
        return pielEdemas;
    }

    public Variable getVariablePielEdemas() {
        return pielEdemas;
    }

    public Boolean getPielEdemasBoolean() {
        if (pielEdemas != null && !pielEdemas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielEdemas(Variable pielEdemas) {
        this.pielEdemas = pielEdemas;
    }

    public void setPielEdemas(Boolean valor) {
        if (valor == true) {
            this.pielEdemas.setValor("Piel edemas");
        } else {
            this.pielEdemas.setValor("");
        }
    }

    public Variable getPielHipersensibilidad() {
        return pielHipersensibilidad;
    }

    public Variable getVariablePielHipersensibilidad() {
        return pielHipersensibilidad;
    }

    public Boolean getPielHipersensibilidadBoolean() {
        if (pielHipersensibilidad != null && !pielHipersensibilidad.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielHipersensibilidad(Variable pielHipersensibilidad) {
        this.pielHipersensibilidad = pielHipersensibilidad;
    }

    public void setPielHipersensibilidad(Boolean valor) {
        if (valor == true) {
            this.pielHipersensibilidad.setValor("Piel hipersensibilidad");
        } else {
            this.pielHipersensibilidad.setValor("");
        }
    }

    public Variable getPielHematomas() {
        return pielHematomas;
    }

    public Variable getVariablePielHematomas() {
        return pielHematomas;
    }

    public Boolean getPielHematomasBoolean() {
        if (pielHematomas != null && !pielHematomas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielHematomas(Variable pielHematomas) {
        this.pielHematomas = pielHematomas;
    }

    public void setPielHematomas(Boolean valor) {
        if (valor == true) {
            this.pielHematomas.setValor("Piel hematomas");
        } else {
            this.pielHematomas.setValor("");
        }
    }

    public Variable getPielFlebitis() {
        return pielFlebitis;
    }

    public Variable getVariablePielFlebitis() {
        return pielFlebitis;
    }

    public Boolean getPielFlebitisBoolean() {
        if (pielFlebitis != null && !pielFlebitis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPielFlebitis(Variable pielFlebitis) {
        this.pielFlebitis = pielFlebitis;
    }

    public void setPielFlebitis(Boolean valor) {
        if (valor == true) {
            this.pielFlebitis.setValor("Piel flebitis");
        } else {
            this.pielFlebitis.setValor("");
        }
    }

    public Variable getPielHeridas() {
        return pielHeridas;
    }

    public Variable getVariablePielHeridas() {
        return pielHeridas;
    }

    public String getPielHeridasString() {
        return pielHeridas.getValor();
    }

    public void setPielHeridas(Variable pielHeridas) {
        this.pielHeridas = pielHeridas;
    }

    public void setPielHeridas(String valor) {
        this.pielHeridas.setValor(valor);
    }

    public Variable getPielOtros() {
        return pielOtros;
    }

    public Variable getVariablePielOtros() {
        return pielOtros;
    }

    public String getPielOtrosString() {
        return pielOtros.getValor();
    }

    public void setPielOtros(Variable pielOtros) {
        this.pielOtros = pielOtros;
    }

    public void setPielOtros(String valor) {
        this.pielOtros.setValor(valor);
    }
// seguridad

    public Variable getSegAccesoTipo() {
        return segAccesoTipo;
    }

    public Variable getVariableSegAccesoTipo() {
        return segAccesoTipo;
    }

    public String getSegAccesoTipoString() {
        return segAccesoTipo.getValor();
    }

    public void setSegAccesoTipo(Variable segAccesoTipo) {
        this.segAccesoTipo = segAccesoTipo;
    }

    public void setSegAccesoTipo(String valor) {
        this.segAccesoTipo.setValor(valor);
    }

    public Variable getSegAccesoLoca() {
        return segAccesoLoca;
    }

    public Variable getVariableSegAccesoLoca() {
        return segAccesoLoca;
    }

    public String getSegAccesoLocaString() {
        return segAccesoLoca.getValor();
    }

    public void setSegAccesoLoca(Variable segAccesoLoca) {
        this.segAccesoLoca = segAccesoLoca;
    }

    public void setSegAccesoLoca(String valor) {
        this.segAccesoLoca.setValor(valor);
        ;
    }

    public Variable getSegAccesoPermeable() {
        return segAccesoPermeable;
    }

    public Variable getVariableSegAccesoPermeable() {
        return segAccesoPermeable;
    }

    public String getSegAccesoPermeableString() {
        return segAccesoPermeable.getValor();
    }

    public void setSegAccesoPermeable(Variable segAccesoPermeable) {
        this.segAccesoPermeable = segAccesoPermeable;
    }

    public void setSegAccesoPermeable(String valor) {
        this.segAccesoPermeable.setValor(valor);
    }

//dolor
    public Variable getDolorEva() {
        return dolorEva;
    }

    public Variable getVariableDolorEva() {
        return dolorEva;
    }

    public String getDolorEvaString() {
        return dolorEva.getValor();
    }

    public void setDolorEva(Variable dolorEva) {
        this.dolorEva = dolorEva;
    }

    public void setDolorEva(String valor) {
        this.dolorEva.setValor(valor);
    }

    public Variable getDolorLocalizacion() {
        return dolorLocalizacion;
    }

    public Variable getVariableDolorLocalizacion() {
        return dolorLocalizacion;
    }

    public String getDolorLocalizacionString() {
        return dolorLocalizacion.getValor();
    }

    public void setDolorLocalizacion(Variable dolorLocalizacion) {
        this.dolorLocalizacion = dolorLocalizacion;
    }

    public void setDolorLocalizacion(String valor) {
        this.dolorLocalizacion.setValor(valor);
    }

//situación emocional
    public Variable getEmocioAnsiedad() {
        return emocioAnsiedad;
    }

    public Variable getVariableEmocioAnsiedad() {
        return emocioAnsiedad;
    }

    public String getEmocioAnsiedadString() {
        return emocioAnsiedad.getValor();
    }

    public void setEmocioAnsiedad(Variable emocioAnsiedad) {
        this.emocioAnsiedad = emocioAnsiedad;
    }

    public void setEmocioAnsiedad(String valor) {
        this.emocioAnsiedad.setValor(valor);
        ;
    }

    public Variable getEmocioDepresion() {
        return emocioDepresion;
    }

    public Variable getVariableEmocioDepresion() {
        return emocioDepresion;
    }

    public String getEmocioDepresionString() {
        return emocioDepresion.getValor();
    }

    public void setEmocioDepresion(Variable emocioDepresion) {
        this.emocioDepresion = emocioDepresion;
    }

    public void setEmocioDepresion(String valor) {
        this.emocioDepresion.setValor(valor);
        ;
    }

    public Variable getEmocioPAD_T() {
        return emocioPAD_T;
    }

    public Variable getVariableEmocioPAD_T() {
        return emocioPAD_T;
    }

    public String getEmocioPAD_TString() {
        return emocioPAD_T.getValor();
    }

    public void setEmocioPAD_T(Variable emocioPAD_T) {
        this.emocioPAD_T = emocioPAD_T;
    }

    public void setEmocioPAD_T(String valor) {
        this.emocioPAD_T.setValor(valor);
    }

// comunicacion
    public Variable getComuAcompanado() {
        return comuAcompanado;
    }

    public Variable getVariableComuAcompanado() {
        return comuAcompanado;
    }

    public String getComuAcompanadoString() {
        return comuAcompanado.getValor();
    }

    public void setComuAcompanado(Variable comuAcompanado) {
        this.comuAcompanado = comuAcompanado;
    }

    public void setComuAcompanado(String valor) {
        this.comuAcompanado.setValor(valor);
    }

    public Variable getComuOtraCompa() {
        return comuOtraCompa;
    }

    public Variable getVariableComuOtraCompa() {
        return comuOtraCompa;
    }

    public String getComuOtraCompaString() {
        return comuOtraCompa.getValor();
    }

    public void setComuOtraCompa(Variable comuOtraCompa) {
        this.comuOtraCompa = comuOtraCompa;
    }

    public void setComuOtraCompa(String valor) {
        this.comuOtraCompa.setValor(valor);
    }

    public Variable getComuRelacionFam() {
        return comuRelacionFam;
    }

    public Variable getVariableComuRelacionFam() {
        return comuRelacionFam;
    }

    public String getComuRelacionFamString() {
        return comuRelacionFam.getValor();
    }

    public void setComuRelacionFam(Variable comuRelacionFam) {
        this.comuRelacionFam = comuRelacionFam;
    }

    public void setComuRelacionFam(String valor) {
        this.comuRelacionFam.setValor(valor);
    }

    public Variable getComuRelacionPer() {
        return comuRelacionPer;
    }

    public Variable getVariableComuRelacionPer() {
        return comuRelacionPer;
    }

    public String getComuRelacionPerString() {
        return comuRelacionPer.getValor();
    }

    public void setComuRelacionPer(Variable comuRelacionPer) {
        this.comuRelacionPer = comuRelacionPer;
    }

    public void setComuRelacionPer(String valor) {
        this.comuRelacionPer.setValor(valor);
    }

    public Variable getComuColabora() {
        return comuColabora;
    }

    public Variable getVariableComuColabora() {
        return comuColabora;
    }

    public String getComuColaboraString() {
        return comuColabora.getValor();
    }

    public Boolean getComuColaboraBoolean() {
        if (comuColabora != null && !comuColabora.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setComuColabora(Variable comuColabora) {
        this.comuColabora = comuColabora;
    }

    public void setComuColabora(String valor) {
        this.comuColabora.setValor(valor);
    }

    public void setComuColabora(Boolean valor) {
        if (valor == true) {
            this.comuColabora.setValor("Paciente colabora con tratamiento");
        } else {
            this.comuColabora.setValor("");
        }
    }

    public Variable getComuConspiraSilen() {
        return comuConspiraSilen;
    }

    public Variable getVariableComuConspiraSilen() {
        return comuConspiraSilen;
    }

    public String getComuConspiraSilenString() {
        return comuConspiraSilen.getValor();
    }

    public Boolean getComuConspiraSilenBoolean() {
        if (comuConspiraSilen != null && !comuConspiraSilen.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setComuConspiraSilen(Variable comuConspiraSilen) {
        this.comuConspiraSilen = comuConspiraSilen;
    }

    public void setComuConspiraSilen(String valor) {
        this.comuConspiraSilen.setValor(valor);
    }

    public void setComuConspiraSilen(Boolean valor) {
        if (valor == true) {
            this.comuConspiraSilen.setValor("Conspiración silencio");
        } else {
            this.comuConspiraSilen.setValor("");
        }
    }

    public Variable getComuCambioSexual() {
        return comuCambioSexual;
    }

    public Variable getVariableComuCambioSexual() {
        return comuCambioSexual;
    }

    public String getComuCambioSexualString() {
        return comuCambioSexual.getValor();
    }

    public Boolean getComuCambioSexualBoolean() {
        if (comuCambioSexual != null && !comuCambioSexual.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setComuCambioSexual(Variable comuCambioSexual) {
        this.comuCambioSexual = comuCambioSexual;
    }

    public void setComuCambioSexual(String valor) {
        this.comuCambioSexual.setValor(valor);
    }

    public void setComuCambioSexual(Boolean valor) {
        if (valor == true) {
            this.comuCambioSexual.setValor("Cambio comportamiento sexual");
        } else {
            this.comuCambioSexual.setValor("");
        }
    }

    public Variable getComuOtro() {
        return comuOtro;
    }

    public Variable getVariableComuOtro() {
        return comuOtro;
    }

    public String getComuOtroStrig() {
        return comuOtro.getValor();
    }

    public void setComuOtro(Variable comuOtro) {
        this.comuOtro = comuOtro;
    }

    public void setComuOtro(String valor) {
        this.comuOtro.setValor(valor);
        ;
    }

    public Variable getPercepImagen() {
        return percepImagen;
    }

    public Variable getVariablePercepImagen() {
        return percepImagen;
    }

    public String getPercepImagenString() {
        return percepImagen.getValor();
    }

    public void setPercepImagen(Variable percepImagen) {
        this.percepImagen = percepImagen;
    }

    public void setPercepImagen(String valor) {
        this.percepImagen.setValor(valor);
    }

    public Variable getPercepAutoestima() {
        return percepAutoestima;
    }

    public Variable getVariablePercepAutoestima() {
        return percepAutoestima;
    }

    public String getPercepAutoestimaString() {
        return percepAutoestima.getValor();
    }

    public void setPercepAutoestima(Variable percepAutoestima) {
        this.percepAutoestima = percepAutoestima;
    }

    public void setPercepAutoestima(String valor) {
        this.percepAutoestima.setValor(valor);
    }

    public Variable getPercepAlopecia() {
        return percepAlopecia;
    }

    public Variable getVariablePercepAlopecia() {
        return percepAlopecia;
    }

    public Boolean getPercepAlopeciaBoolean() {
        if (percepAlopecia != null && !percepAlopecia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPercepAlopecia(Variable percepAlopecia) {
        this.percepAlopecia = percepAlopecia;
    }

    public void setPercepAlopecia(Boolean valor) {
        if (valor == true) {
            this.percepAlopecia.setValor("Alopecia");
        } else {
            this.percepAlopecia.setValor("");
        }
    }

    public Variable getPercepMastectomia() {
        return percepMastectomia;
    }

    public Variable getVariablePercepMastectomia() {
        return percepMastectomia;
    }

    public Boolean getPercepMastectomiaBoolean() {
        if (percepMastectomia != null && !percepMastectomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPercepMastectomia(Variable percepMastectomia) {
        this.percepMastectomia = percepMastectomia;
    }

    public void setPercepMastectomia(Boolean valor) {
        if (valor == true) {
            this.percepMastectomia.setValor("Mastestomía");
        } else {
            this.percepMastectomia.setValor("");
        }
    }

    public Variable getPercepColostomia() {
        return percepColostomia;
    }

    public Variable getVariablePercepColostomia() {
        return percepColostomia;
    }

    public Boolean getPercepColostomiaBoolean() {
        if (percepColostomia != null && !percepColostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPercepColostomia(Variable percepColostomia) {
        this.percepColostomia = percepColostomia;
    }

    public void setPercepColostomia(Boolean valor) {
        if (valor == true) {
            this.percepColostomia.setValor("Colostomía");
        } else {
            this.percepColostomia.setValor("");
        }
    }

    public Variable getPercepOtros() {
        return percepOtros;
    }

    public Variable getVariablePercepOtros() {
        return percepOtros;
    }

    public String getPercepOtrosString() {
        return percepOtros.getValor();
    }

    public void setPercepOtros(Variable percepOtros) {
        this.percepOtros = percepOtros;
    }

    public void setPercepOtros(String valor) {
        this.percepOtros.setValor(valor);
    }

//aprencer
    public Variable getAprenComprende() {
        return aprenComprende;
    }

    public Variable getVariableAprenComprende() {
        return aprenComprende;
    }

    public String getAprenComprendeString() {
        return aprenComprende.getValor();
    }

    public void setAprenComprende(Variable aprenComprende) {
        this.aprenComprende = aprenComprende;
    }

    public void setAprenComprende(String valor) {
        this.aprenComprende.setValor(valor);
    }

    public Variable getAprenDemanda() {
        return aprenDemanda;
    }

    public Variable getVariableAprenDemanda() {
        return aprenDemanda;
    }

    public String getAprenDemandaString() {
        return aprenDemanda.getValor();
    }

    public void setAprenDemanda(Variable aprenDemanda) {
        this.aprenDemanda = aprenDemanda;
    }

    public void setAprenDemanda(String valor) {
        this.aprenDemanda.setValor(valor);
    }

}
