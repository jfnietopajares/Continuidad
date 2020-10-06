package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;


public class RegistroEnfValoracion extends Registro {

    private Variable tratamientoHabitual;
    private Variable diagnostico;
    private Variable alergias;
    private Variable antecedentes;
    private Variable accesoVenoso;
    private Variable tratamientoqt;
    private Variable sitFamiliarSocial;
    private Variable telefono;
    private Variable cliclos;
    private Variable fechaInicio;
    private Variable fechaFin;

    // actividad ejercicio
    private Variable respiNoAlteracion;
    private Variable respiTolreaAVD;
    private Variable respiAutoHigiene;
    private Variable respiRitmoNormal;
    private Variable respiPielMucoRosa;
    private Variable respiKarnofsky;
    private Variable respidisnea;
    private Variable respiCianosis;
    private Variable respiOxigeno;
    private Variable respiEsputoEsce;
    private Variable respiTosinefi;
    private Variable respiSonidosNormales;
    private Variable respiFatigaDebili;
    private Variable respiMoviDescoordin;
    private Variable respiOtros;
    // alimentacion
    private Variable aliDietaHabitual;
    private Variable aliIngestaLiquidos;
    private Variable aliSuplemento;
    private Variable aliMasticaDificultad;
    private Variable aliProtesisDental;
    private Variable aliDificultadDeglu;
    private Variable aliPielIntegra;
    private Variable aliTemperaturaNormal;
    private Variable aliNauseas;
    private Variable aliVomitos;
    private Variable aliAteraGusto;
    private Variable aliXerostomia;
    private Variable aliLesionUlcera;
    private Variable aliReduccTemp;
    private Variable aliAumentoTemp;
    private Variable aliEdemas;
    private Variable aliHerida;
    private Variable aliOtros;
    // eliminacion urinaria
    private Variable eliuriCantidad;
    private Variable eliuriColor;
    private Variable eliuriOtros;
    private Variable eliIntesNormal; // Patrón de eliminación intestinal normal
    private Variable eliUrinaNormal; // Patrón de eliminación urinaria normal
    private Variable eliUrostomia;
    private Variable eliNefrostomia;
    private Variable eliColostomia; // Colostomía normofuncionante
    private Variable eliIleostomia; // Ileostomía normofuncionante
    private Variable eliContiIntes; // Continencia Intestinal
    private Variable eliContiUrinaria; // Continencia urinaria
    private Variable eliuriPortadorSV;
    private Variable eliuriDisminucionF;// Disminución de la frecuencia
    private Variable elifecalIncapacidaHeces;// Incapacidad para eliminar las heces
    private Variable elifecalDificultosa; // Defecación dificultosa
    private Variable elifecalHecesduras; // Eliminación de heces duras. secas y formadas
    private Variable elifecalPlenitud;// Sensación de plenitud o presión rectal
    private Variable elifecalDistension;// Distensión abdominal
    private Variable elifecalDolorAbdominal;
    private Variable elifecal3deposiciones;// Eliminación por o menos de tres deposiciones líquidas por día
    private Variable eliuriGoteo;// Goteo orina al aumentar presión abdominal
    private Variable eliuriUrgencia;// Urgencia urinaria
    private Variable eliuriEscozor;
    private Variable elifecalDiarrea;
    private Variable elifecalEstrenimiento;
    private Variable elifecalOstomia;
    private Variable elifecalOtro;
    // reposo sueño
    private Variable repoSueno;
    private Variable repoSueInfusiones;
    private Variable repoSueMedicacion;
    private Variable repoSueOtros;
// congnitivo
    private Variable cognAprendizaje;
    public final Variable VAR_ONCO_VALORA_COGNAPRENDIZAJE = new Variable("Capacidad aprender", "49213006", "SNM3", new Long(35002943),
            "Capacidad y disposición para aprender");
    private Variable cognConsciente;
    public final Variable VAR_ONCO_VALORA_COGNCONSCIENTE = new Variable("Consciente orientado", "271591004", "SNM3", new Long(13822324),
            "Consciente orientado responde adecuadamente");
    private Variable cognConscienteEnfermdad;
    public final Variable VAR_ONCO_VALORA_COGNCONSCIENTEENFERMEDAD = new Variable("Consciente enfermedad", "35003518", "99G2",
            new Long(35003518), "Consciente de su enfermedad");

    private Variable cognConoceEnfermdad;
    public final Variable VAR_ONCO_VALORA_COGNCONOCEEFERMEDAD = new Variable("Conoce enfermedad", "35002947", "99G2", new Long(35002947),
            "Conoce su enfermedad");

    private Variable cognVerBalizadolor;
    public final Variable VAR_ONCO_VALORA_COGNVERVALIZADOLOR = new Variable("Vervaliza dolor", "13992625", "99G2", new Long(13992625),
            "Verbaliza dolor");

    private Variable cognEvidenciaDolor;
    public final Variable VAR_ONCO_VALORA_COGNEVIDENCIADOLOR = new Variable("Evidencia dolor", "13825345", "99G2", new Long(13825345),
            "Evidencia dolor");

    private Variable cognDesorientacion;
    public final Variable VAR_ONCO_VALORA_COGNDESORIENTACION = new Variable("Desorientación espacial", "112077003", "SNM3", new Long(13824908),
            "Desorientación espacial");

    private Variable cognDolorEva;
    public final Variable VAR_ONCO_VALORA_COGNDOLOREVA = new Variable("Dolor (EVA)", "425401001", "SNM3", new Long(13825347), "EVA");

    private Variable cognDolorLocalizacion;
    public final Variable VAR_ONCO_VALORA_COGNDOLORLOCA = new Variable("Localización dolor", "287495009", "SNM3", new Long(13825346),
            "Localización dolor");
// Autopercepcion - Auto concepto
    private Variable autoAceptaImagen;
    public final Variable VAR_ONCO_VALORA_AUTOACPETAIMAGEN = new Variable("Acepta imagen corporal", "13817972", "99G2", new Long(13817972),
            "Acepta su imagen corporal");
    private Variable autoSentimientoPos;
    public final Variable VAR_ONCO_VALORA_AUTOSETIMIENTOPOS = new Variable("Sentimientos positivos", "35002953", "99G2", new Long(35002953),
            "Sentimientos positivos a cerca de si mismo");
    private Variable autoTranquilo;
    public final Variable VAR_ONCO_VALORA_AUTOTRANQUILO = new Variable("Tranquilo", "13995333", "99G2", new Long(13995333),
            "Se muestra tranquilo y relajado");
    private Variable autoEscalaDME;
    public final Variable VAR_ONCO_VALORA_AUTOESCALADEME = new Variable("Escala DME", "13995334", "99G2", new Long(13995334),
            "Escala de malestar emocional DME");
    private Variable autoAlteraVisioPropia;
    public final Variable VAR_ONCO_VALORA_AUTOALTERAVISIOPRO = new Variable("Alteración visión cuerpo", "13995337", "99G2", new Long(13995337),
            "Alteración de la visión propio cuerpo en cuanto a aspecto, estrucutra o fución");
    private Variable autoSentiNegativo;
    public final Variable VAR_ONCO_VALORA_AUTOSENTINEGA = new Variable("Sentimientos negativos", "13995339", "99G2", new Long(13995339),
            "Sentimientos negativos sobre el cuerPo");
    private Variable autoVerbalizaAuton;
    public final Variable VAR_ONCO_VALORA_AUTOVERBALIZAUTON = new Variable("Vervaliza autonegativas", "13818996", "99G2", new Long(13818996),
            "Verbaliza autonegativas");

    private Variable autoAngustia;
    public final Variable VAR_ONCO_VALORA_AUTOANGUSTIA = new Variable("Angustica", "35003648", "99G2", new Long(35003648),
            "Angustia");

    private Variable autoNerviosismo;
    public final Variable VAR_ONCO_VALORA_AUTONERVIOSISMO = new Variable("Nerviosismo", "R45.0", "I10C", new Long(60027797),
            "Nerviosismo");
    private Variable autoInquietud;
    public final Variable VAR_ONCO_VALORA_AUTOINQUIETUD = new Variable("Inquietud", "35003646", "99G2", new Long(35003646),
            "Inquietud");

    private Variable autoOtros;
    public final Variable VAR_ONCO_VALORA_AUTOOTROS = new Variable("Otros aspectos autopercepción", "13995340", "99G2", new Long(13995340),
            "Otros aspectos autopercepción");

    // sexualidad reproducción
    private Variable sexuVerbalizaProblema;
    public final Variable VAR_ONCO_VALORA_SEXUVERBALIZA = new Variable("Verbaliza problema sexual", "35003651", "99G2", new Long(35003651),
            "Verbaliza problema sexual");
    private Variable sexuIncapacidadSastis;
    public final Variable VAR_ONCO_VALORA_SEXUINCAPACIDADSASTI = new Variable("Incapacidad asitisfacción", "13995336", "99G2", new Long(13995336),
            "Incapaciada para lograr la satisfacción deseada");
    private Variable sexuLimitaciones;
    public final Variable VAR_ONCO_VALORA_SEXULIMITACIONES = new Variable("Limitaciones por enfermedad o tratamiento", "35002968 ", "99G2", new Long(35002968),
            "Limitaciones reales o percibidas por la enfermedad o la terapia");
    private Variable sexuOtros;
    public final Variable VAR_ONCO_VALORA_SEXUOTRAS = new Variable("Otros problemas", "13995341", "99G2", new Long(13995341),
            "Otros problemas sexuales");

    // movilizacion
    private Variable movilizacion;
    private Variable movilizacionOtros;
    private Variable karnofsky;
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

    public final Variable VAR_ONCO_VALORA_TRATAHABITUAL = new Variable("Tratamiento Habitual ", "394829006", "SNM3", new Long(1517974),
            "Tratamiento Habitual ");

    public final Variable VAR_ONCO_VALORA_DIAGNOSTICO = new Variable("Diagnóstico ", "8319008", "SNM3", new Long(7762568),
            "Diagnóstico ");
    public final Variable VAR_ONCO_VALORA_ALERGIA = new Variable("Alergias", "106190000", "SNM3", new Long(13524547), "Alergia");
    public final Variable VAR_ONCO_VALORA_ANTECEDENTES = new Variable("Antecedentes", "307294006", "SNM3", new Long(13524545),
            "Antecedentes");

    public final Variable VAR_ONCO_VALORA_ACCESOVENOSO = new Variable("Acceso Venoso", "P0901.PE111", "99G2", new Long(214037100),
            "Acceso Venoso");

    public final Variable VAR_ONCO_VALORA_TRATAMIENTOQT = new Variable("Tratamiento QT", "413737006", "SNM3", new Long(13824284),
            "Tratamiento QT");
    public final Variable VAR_ONCO_VALORA_SITSOCIALFAMILIAR = new Variable("Situación familiar", "9791", "99G2", new Long(9791),
            "Situación familiar");
    public final Variable VAR_ONCO_VALORA_TELEFONO = new Variable("359993007", "SNM3", new Long(14080524), "Teléfono");

    public final Variable VAR_ONCO_VALORA_CICLOS = new Variable("13827905", "99G2", new Long(13827905), "Nº Ciclos");
    public final Variable VAR_ONCO_VALORA_FINICIO = new Variable("298059007", "SNM3", new Long(35001161),
            "Fecha inicio");
    public final Variable VAR_ONCO_VALORA_FFIN = new Variable("413943001", "SNM3", new Long(13824004), "Fecha fin");
// respiraicon

    public final Variable VAR_ONCO_VALORA_RESPISINALTE = new Variable("35001565", "99G2", new Long(35001565),
            "Sin alteración");
    public final Variable VAR_ONCO_VALORA_RESPITOLERAAVD = new Variable("13995290", "99G2", new Long(13995290),
            "Tolera actividades de la vida diaria");
    public final Variable VAR_ONCO_VALORA_RESPIAUTOHIGIEN = new Variable("13995291", "99G2", new Long(13995291),
            "Autonomía en el autocuidado");
    public final Variable VAR_ONCO_VALORA_RESPIRITMONORMAL = new Variable("13995292", "99G2", new Long(13995292),
            "Ritmo y frecuencia respiratoria normales");
    public final Variable VAR_ONCO_VALORA_RESPIPIELROSADA = new Variable("13995293", "99G2", new Long(13995293),
            "Piel rosada");
    public final Variable VAR_ONCO_VALORA_RESPIKARNOSFSKY = new Variable("13995294", "99G2", new Long(13995294),
            "Escala de Karnosfsky");
    public final Variable VAR_ONCO_VALORA_RESPIDISNEA = new Variable("267036007", "SNM3", new Long(13822584), "Disnea");
    public final Variable VAR_ONCO_VALORA_RESPICIANOSIS = new Variable("13995295", "99G2", new Long(13995295),
            "Cianosis");
    public final Variable VAR_ONCO_VALORA_RESPIOXIGENO = new Variable("24099007", "SNM3", new Long(13825410),
            "Oxigeno a domicilio");
    public final Variable VAR_ONCO_VALORA_RESPIESPUTO = new Variable("13995296", "99G2", new Long(13995296),
            "Cantidad escesiba de esputo");
    public final Variable VAR_ONCO_VALORA_RESPITOSINEFI = new Variable("13995297", "99G2", new Long(13995297),
            "Tos ineficaz");
    public final Variable VAR_ONCO_VALORA_RESPISONIDOSA = new Variable("13995298", "99G2", new Long(13995298),
            "Sonidos respiratoris anormales");

    public final Variable VAR_ONCO_VALORA_RESPIFATIGA = new Variable("13791008", "SNM3", new Long(35001936),
            "Informes verbales de fatiga o debilidad");
    public final Variable VAR_ONCO_VALORA_RESPIMOVI = new Variable("13995299", "99G2", new Long(13995299),
            "Informes verbales de fatiga o debilidad");

    public final Variable VAR_ONCO_VALORA_RESPIOTROS = new Variable("35003903", "99G2", new Long(35003903), "Otros:");

    // alimentacin
    public final Variable VAR_ONCO_VALORA_ALIDDIETAHA = new Variable("Dieta", "418995006", "SNM3", new Long(13825356),
            "Dieta habitual");
    public final Variable VAR_ONCO_VALORA_ALIINGESLIQUIDOS = new Variable("Líquidos", "251992000", "SNM3", new Long(35000655),
            "Ingesta Líquidos");
    public final Variable VAR_ONCO_VALORA_ALISUPLEMENTO = new Variable("Suplementos", "35003925", "99G2", new Long(35003925),
            "Suplemento alimentación");
    public final Variable VAR_ONCO_VALORA_ALIMASTICAFICICUL = new Variable("Dificultad masticar", "1202003", "SNM3", new Long(35004364),
            "Mastica sin dificultad ");
    public final Variable VAR_ONCO_VALORA_ALIPROTESIS = new Variable("Prótesis dental", "277037004", "SNM3", new Long(13822589),
            "Prótesis dental ");

    public final Variable VAR_ONCO_VALORA_ALIDIFICULDEGLU = new Variable("Dificultad delgución", "399122003", "SNM3", new Long(35002684),
            "Dificultad deglución ");
    public final Variable VAR_ONCO_VALORA_ALIPIELINTE = new Variable("Piel íntegra", "13991867", "99G2", new Long(13991867),
            "Piel íntegra ");
    public final Variable VAR_ONCO_VALORA_ALITEMPERATURA = new Variable("Temperatura normal", "386725007", "SNM3", new Long(13596291),
            "Temperatura normal  ");

    public final Variable VAR_ONCO_VALORA_ALINAUSEAS = new Variable("Náuseas", "73879007", "SNM3", new Long(13822632), "Náuseas:");

    public final Variable VAR_ONCO_VALORA_ALIVOMITOS = new Variable("Vómitos", "422400008", "SNM3", new Long(13820925),
            "Vómitos:");
    public final Variable VAR_ONCO_VALORA_ALIALTERAGUSTO = new Variable("Alteracón gusto", "271801002", "SNM3", new Long(35003924),
            "Alteración gusto");
    public final Variable VAR_ONCO_VALORA_ALIXEROSTOMIA = new Variable("Boca seca", "13995314", "99G2", new Long(13995314),
            "Xerostomía (Boca seca)");
    public final Variable VAR_ONCO_VALORA_ALILESIONESULCERAS = new Variable("Úlceras orales", "13995316", "99G2", new Long(13995316),
            "Lesiones o úlceras orales");
    public final Variable VAR_ONCO_VALORA_ALIREDUCCIONTEMP = new Variable("Baja Tª", "13995317", "99G2", new Long(13995317),
            "Reducción Tª por debajo de límites normales");
    public final Variable VAR_ONCO_VALORA_ALIAUMENTOTEMP = new Variable("Alta Tª", "13995318", "99G2", new Long(13995318),
            "Aumento Tª por encima de límites normales");
    public final Variable VAR_ONCO_VALORA_ALIEDEMAS = new Variable("Edemas", "13822585", "99G2", new Long(13822585), "Edemas");
    public final Variable VAR_ONCO_VALORA_ALIHERIDAS = new Variable("Heridas", "879.8", "I9C", new Long(6174), "Heridas");

    public final Variable VAR_ONCO_VALORA_ALIOTROS = new Variable("Otros", "35001884", "99G2", new Long(35001884),
            "Otros cuidados");

    public final Variable VAR_ONCO_VALORA_ELIURICANTIDAD = new Variable("Canridad orina", "35003944", "99G2", new Long(35003944),
            "Eliminación urinaria cantidad");
    public final Variable VAR_ONCO_VALORA_ELIURICOLOR = new Variable("Color orin", "35003943", "99G2", new Long(35003943),
            "Eliminación urinaria color");
    public final Variable VAR_ONCO_VALORA_ELIURINARIANORMAL = new Variable("Orina normal", "13995319", "99G2", new Long(13995319),
            "Patrón de eliminación urinaria normal");
    public final Variable VAR_ONCO_VALORA_ELIINTESTINORMAL = new Variable("Defecación normal", "35002902", "99G2", new Long(35002902),
            "Patrón de eliminación intestinal normal");
    public final Variable VAR_ONCO_VALORA_ELIURIUROSTOMIA = new Variable("Urostomia", "35001609", "99G2", new Long(35001609),
            "Paciente con urostomía  ");
    public final Variable VAR_ONCO_VALORA_ELIURINEFROSTOMIA = new Variable("Nefrostomia", "35001607", "99G2", new Long(35001607),
            "Paciente con sonda nefrostomía  ");
    public final Variable VAR_ONCO_VALORA_ELICOLOSTOMIA = new Variable("Colostomía", "13995320", "99G2", new Long(13995320),
            "Colostomía normofuncionante  ");
    public final Variable VAR_ONCO_VALORA_ELIILEOSTOMIA = new Variable("Ileostomía", "13995321", "99G2", new Long(13995321),
            "Ileostomía normofuncionante  ");
    public final Variable VAR_ONCO_VALORA_ELICONTINENCIAINSTESTINAL = new Variable("Continencia intestinal", "13995322", "99G2",
            new Long(13995322), "Continencia intestinal  ");
    public final Variable VAR_ONCO_VALORA_ELICONTINENCIAURINARIA = new Variable("Continencia urinaria", "13995323", "99G2", new Long(13995323),
            "Continencia urinaria  ");
    public final Variable VAR_ONCO_VALORA_ELIURISV = new Variable("Sonda vesical", "35001606", "99G2", new Long(35001606),
            "Paciente portador de sonda vesical");
    public final Variable VAR_ONCO_VALORA_ELIURIDISMINCIONFRECUENCIA = new Variable("Menor fecuencia micción", "13995324", "99G2",
            new Long(13995324), "Disminución frecuencia micción");
    public final Variable VAR_ONCO_VALORA_ELIURIINCAPACIDAD = new Variable("Incapacidad eliminar heces ", "13995325", "99G2", new Long(13995325),
            "Incapacidad para eliminar las heces");
    public final Variable VAR_ONCO_VALORA_ELIURIDIFICULTOSA = new Variable("Defecación fificultosa ", "13995326", "99G2", new Long(13995326),
            "Defecación dificultosa");
    public final Variable VAR_ONCO_VALORA_ELIURIHECESDURAS = new Variable("Heces duras", "13995327", "99G2", new Long(13995327),
            "Eliminación de heces duras. secas y formadas");
    public final Variable VAR_ONCO_VALORA_ELIURISENSACIONPELNI = new Variable("Presión rectal ", "13995328", "99G2", new Long(13995328),
            "Sensación de plenitud o presión rectal");
    public final Variable VAR_ONCO_VALORA_ELIURIDISTENSION = new Variable("Distensión abdominal ", "13995329", "99G2", new Long(13995329),
            "Distensión abdominal");
    public final Variable VAR_ONCO_VALORA_ELIURIDOLORABDOMINAL = new Variable("Dolor abdominal ", "13992618", "99G2", new Long(13992618),
            "Dolor abdominal");
    public final Variable VAR_ONCO_VALORA_ELIURI3VESDIA = new Variable("Ma´s 3 deposiciones día", "13995330", "99G2", new Long(13995330),
            "Eliminación por o menos de tres deposiciones líquidas por día");
    public final Variable VAR_ONCO_VALORA_ELIURIGOTEO = new Variable("Goteo orina ", "13995331", "99G2", new Long(13995331),
            "Goteo orina al aumentar presión abdominal");
    public final Variable VAR_ONCO_VALORA_ELIURIURGENCIA = new Variable("Urgencia orina ", "13995332", "99G2", new Long(13995332),
            "Urgencia urinaria");

    public final Variable VAR_ONCO_VALORA_ELIURIESCOZOR = new Variable("Escozor ", "410718003", "SNM3", new Long(13827526),
            "Eliminación urinaria escozor");

    public final Variable VAR_ONCO_VALORA_ELIURIOTROS = new Variable("Otros problemas orina", "13825524", "99G2", new Long(13825524),
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
    public final Variable VAR_ONCO_VALORA_REPOSUENO = new Variable("Transtorno sueño", "39898005", "SNM3", new Long(13822550),
            "Transtrono Reposo Sueño");
    public final Variable VAR_ONCO_VALORA_REPOSUEINFUSIONES = new Variable("Infusiones ", "35003963", "99G2", new Long(35003963),
            "Precisa infusiones");
    public final Variable VAR_ONCO_VALORA_REPOSUEMEDICACION = new Variable("Medicación domrir", "260779000", "SNM3", new Long(13825604),
            "Precisa medicación para dormir");
    public final Variable VAR_ONCO_VALORA_REPOSUEOTROS = new Variable("Otros transtornos del sueño", "35002932", "99G2", new Long(35002932),
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
    public RegistroEnfValoracion() {
        super();
        this.iniciaRegOncoValoracion();
    }

    public RegistroEnfValoracion(Long id) {
        super(id);
        this.iniciaRegOncoValoracion();
    }

    public RegistroEnfValoracion(RegistroEnfValoracion r) {
        super(r);
        this.tratamientoHabitual = r.getTratamientoHabitual();
        this.diagnostico = r.getDiagnostico();
        this.alergias = r.getAlergias();
        this.antecedentes = r.getAntecedentes();
        this.accesoVenoso = r.getAccesoVenoso();
        this.tratamientoqt = r.getTratamientoqt();
        this.sitFamiliarSocial = r.getSitFamiliarSocial();
        this.telefono = r.getTelefono();
        this.cliclos = r.getCliclos();
        this.fechaInicio = r.getFechaFin();
        this.fechaFin = r.getFechaFin();
        // respiracion
        this.respiNoAlteracion = r.getRespiNoAlteracion();
        this.respiTolreaAVD = r.getRespiTolreaAVD();
        this.respiAutoHigiene = r.getRespiAutoHigiene();
        this.respiRitmoNormal = r.getRespiRitmoNormal();
        this.respiPielMucoRosa = r.getRespiPielMucoRosa();
        this.respiKarnofsky = r.respiKarnofsky;
        this.respidisnea = r.getRespidisnea();
        this.respiCianosis = r.respiCianosis;
        this.respiOxigeno = r.getRespiOxigeno();
        this.respiEsputoEsce = r.getRespiEsputoEsce();
        this.respiTosinefi = r.getRespiTosinefi();
        this.respiSonidosNormales = r.getRespiSonidosNormales();
        this.respiFatigaDebili = r.getRespiFatigaDebili();
        this.respiMoviDescoordin = r.getRespiMoviDescoordin();
        this.respiOtros = r.getRespiOtros();
        // alimentacion
        this.aliDietaHabitual = r.getAliDietaHabitual();
        this.aliIngestaLiquidos = r.getAliIngestaLiquidos();
        this.aliSuplemento = r.getAliSuplemento();
        this.aliMasticaDificultad = r.getAliMasticaDificultad();
        this.aliProtesisDental = r.getAliProtesisDental();
        this.aliDificultadDeglu = r.getAliDificultadDeglu();
        this.aliPielIntegra = r.getAliPielIntegra();
        this.aliNauseas = r.getAliNauseas();
        this.aliVomitos = r.getAliVomitos();
        this.aliAteraGusto = getAliAteraGusto();
        this.aliXerostomia = r.getAliXerostomia();
        this.aliLesionUlcera = r.getAliLesionUlcera();
        this.aliReduccTemp = r.getAliReduccTemp();
        this.aliAumentoTemp = r.getAliAumentoTemp();
        this.aliEdemas = r.getAliEdemas();
        this.aliHerida = r.getAliHerida();

        this.aliOtros = r.getAliOtros();
        // eliminación urinaria

        this.eliuriCantidad = r.getEliuriCantidad();
        this.eliuriColor = r.getEliuriColor();
        this.eliuriOtros = r.getEliuriOtros();
        this.eliIntesNormal = r.getEliIntesNormal();
        this.eliUrinaNormal = r.getEliUrinaNormal();
        this.eliUrostomia = r.getEliUrostomia();
        this.eliNefrostomia = r.getEliNefrostomia();
        this.eliColostomia = r.getEliColostomia(); // Colostomía normofuncionante
        this.eliIleostomia = r.getEliIleostomia(); // Ileostomía normofuncionante
        this.eliContiIntes = r.getEliContiIntes(); // Continencia Intestinal
        this.eliContiUrinaria = r.getEliContiUrinaria(); // Continencia urinaria
        this.eliuriPortadorSV = r.getEliuriPortadorSV();
        this.eliuriDisminucionF = r.getEliuriDisminucionF();// Disminución de la frecuencia
        this.elifecalIncapacidaHeces = r.getElifecalIncapacidaHeces();// Incapacidad para eliminar las heces
        this.elifecalDificultosa = r.elifecalDificultosa; // Defecación dificultosa
        this.elifecalHecesduras = r.getElifecalHecesduras(); // Eliminación de heces duras. secas y formadas
        this.elifecalPlenitud = r.getElifecalPlenitud();// Sensación de plenitud o presión rectal
        this.elifecalDistension = r.getElifecalDistension();// Distensión abdominal
        this.elifecalDolorAbdominal = r.elifecalDolorAbdominal;
        this.elifecal3deposiciones = r.getElifecal3deposiciones();// Eliminación por o menos de tres deposiciones
        // líquidas por día
        this.eliuriGoteo = r.getEliuriGoteo();// Goteo orina al aumentar presión abdominal
        this.eliuriUrgencia = r.getEliuriUrgencia();// Urgencia urinaria
        this.eliuriEscozor = r.getEliuriEscozor();
        this.elifecalDiarrea = r.getElifecalDiarrea();
        this.elifecalEstrenimiento = r.getElifecalEstrenimiento();
        this.elifecalOstomia = r.getElifecalOstomia();
        this.elifecalOtro = r.getElifecalOtro();
        // reposo sueño
        this.repoSueno = r.getRepoSueno();
        this.repoSueInfusiones = r.getRepoSueInfusiones();
        this.repoSueMedicacion = r.getRepoSueMedicacion();
        this.repoSueOtros = r.getRepoSueOtros();

        // Autopercepcion - Auto concepto
        this.autoAceptaImagen = r.getAutoAceptaImagen();
        this.autoSentimientoPos = r.getAutoSentimientoPos();
        this.autoTranquilo = r.getAutoTranquilo();
        this.autoEscalaDME = r.getAutoEscalaDME();
        this.autoAlteraVisioPropia = r.getAutoAlteraVisioPropia();
        this.autoSentiNegativo = r.getAutoSentiNegativo();
        this.autoVerbalizaAuton = r.getAutoVerbalizaAuton();
        this.autoAngustia = r.getAutoAngustia();
        this.autoNerviosismo = r.getAutoNerviosismo();
        this.autoInquietud = r.getAutoInquietud();
        this.autoOtros = r.getAutoOtros();
//sexualidad		
        this.sexuVerbalizaProblema = r.getSexuVerbalizaProblema();
        this.sexuIncapacidadSastis = r.getSexuIncapacidadSastis();
        this.sexuLimitaciones = r.getSexuLimitaciones();
        this.sexuOtros = r.getSexuOtros();

        // movilizacion
        this.movilizacion = r.getMovilizacion();
        this.movilizacionOtros = r.getMovilizacionOtros();
        this.karnofsky = r.karnofsky;
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
        this.cognAprendizaje = r.getCognAprendizaje();
        this.cognConsciente = r.getCognConsciente();
        this.cognConscienteEnfermdad = r.getCognConscienteEnfermdad();
        this.cognConoceEnfermdad = r.getCognConoceEnfermdad();
        this.cognVerBalizadolor = r.getCognVerBalizadolor();
        this.cognEvidenciaDolor = r.getCognEvidenciaDolor();
        this.cognDesorientacion = r.getCognDesorientacion();
        this.cognDolorEva = r.getCognDolorEva();
        this.cognDolorLocalizacion = r.getCognDolorLocalizacion();

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
        this.setDescripcion("Valoración de enfermería");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_ONCO_VALORACION);
        this.setServicio(Servicio.SERVICIO_ONCOLOGIA);

        this.tratamientoHabitual = VAR_ONCO_VALORA_TRATAHABITUAL;
        this.diagnostico = VAR_ONCO_VALORA_DIAGNOSTICO;
        this.alergias = VAR_ONCO_VALORA_ALERGIA;
        this.antecedentes = VAR_ONCO_VALORA_ANTECEDENTES;
        this.accesoVenoso = VAR_ONCO_VALORA_ACCESOVENOSO;
        this.tratamientoqt = VAR_ONCO_VALORA_TRATAMIENTOQT;
        this.sitFamiliarSocial = VAR_ONCO_VALORA_SITSOCIALFAMILIAR;
        this.telefono = VAR_ONCO_VALORA_TELEFONO;
        this.cliclos = VAR_ONCO_VALORA_CICLOS;
        this.fechaInicio = VAR_ONCO_VALORA_FINICIO;
        this.fechaFin = VAR_ONCO_VALORA_FFIN;
        // respiracion
        this.respiNoAlteracion = VAR_ONCO_VALORA_RESPISINALTE;
        this.respiTolreaAVD = VAR_ONCO_VALORA_RESPITOLERAAVD;
        this.respiAutoHigiene = VAR_ONCO_VALORA_RESPIAUTOHIGIEN;
        this.respiRitmoNormal = VAR_ONCO_VALORA_RESPIAUTOHIGIEN;
        this.respiPielMucoRosa = VAR_ONCO_VALORA_RESPIPIELROSADA;
        this.respiKarnofsky = VAR_ONCO_VALORA_RESPIKARNOSFSKY;
        this.respidisnea = VAR_ONCO_VALORA_RESPIDISNEA;
        this.respiCianosis = VAR_ONCO_VALORA_RESPICIANOSIS;
        this.respiOxigeno = VAR_ONCO_VALORA_RESPIOXIGENO;
        this.respiEsputoEsce = VAR_ONCO_VALORA_RESPIESPUTO;
        this.respiTosinefi = VAR_ONCO_VALORA_RESPITOSINEFI;
        this.respiSonidosNormales = VAR_ONCO_VALORA_RESPISONIDOSA;
        this.respiFatigaDebili = VAR_ONCO_VALORA_RESPIFATIGA;
        this.respiMoviDescoordin = VAR_ONCO_VALORA_RESPIMOVI;
        this.respiOtros = VAR_ONCO_VALORA_RESPIOTROS;
        // alimentacion
        this.aliDietaHabitual = VAR_ONCO_VALORA_ALIDDIETAHA;
        this.aliIngestaLiquidos = VAR_ONCO_VALORA_ALIINGESLIQUIDOS;
        this.aliSuplemento = VAR_ONCO_VALORA_ALISUPLEMENTO;
        this.aliMasticaDificultad = VAR_ONCO_VALORA_ALIMASTICAFICICUL;
        this.aliProtesisDental = VAR_ONCO_VALORA_ALIPROTESIS;
        this.aliDificultadDeglu = VAR_ONCO_VALORA_ALIDIFICULDEGLU;
        this.aliPielIntegra = VAR_ONCO_VALORA_ALIPIELINTE;
        this.aliNauseas = VAR_ONCO_VALORA_ALINAUSEAS;
        this.aliVomitos = VAR_ONCO_VALORA_ALIVOMITOS;
        this.aliAteraGusto = VAR_ONCO_VALORA_ALIALTERAGUSTO;
        this.aliXerostomia = VAR_ONCO_VALORA_ALIXEROSTOMIA;
        this.aliLesionUlcera = VAR_ONCO_VALORA_ALILESIONESULCERAS;
        this.aliReduccTemp = VAR_ONCO_VALORA_ALIREDUCCIONTEMP;
        this.aliAumentoTemp = VAR_ONCO_VALORA_ALIAUMENTOTEMP;
        this.aliEdemas = VAR_ONCO_VALORA_ALIEDEMAS;
        this.aliHerida = VAR_ONCO_VALORA_ALIHERIDAS;

        this.aliOtros = VAR_ONCO_VALORA_ALIOTROS;
        // eliminacion urinaira

        this.eliuriCantidad = VAR_ONCO_VALORA_ELIURICANTIDAD;
        this.eliuriColor = VAR_ONCO_VALORA_ELIURICOLOR;
        this.eliuriOtros = VAR_ONCO_VALORA_ELIURIOTROS;
        this.eliIntesNormal = VAR_ONCO_VALORA_ELIINTESTINORMAL;
        this.eliUrinaNormal = VAR_ONCO_VALORA_ELIURINARIANORMAL;
        this.eliUrostomia = VAR_ONCO_VALORA_ELIURIUROSTOMIA;
        this.eliNefrostomia = VAR_ONCO_VALORA_ELIURINEFROSTOMIA;
        this.eliColostomia = VAR_ONCO_VALORA_ELICOLOSTOMIA; // Colostomía normofuncionante
        this.eliIleostomia = VAR_ONCO_VALORA_ELIILEOSTOMIA; // Ileostomía normofuncionante
        this.eliContiIntes = VAR_ONCO_VALORA_ELICONTINENCIAINSTESTINAL; // Continencia Intestinal
        this.eliContiUrinaria = VAR_ONCO_VALORA_ELICONTINENCIAURINARIA; // Continencia urinaria
        this.eliuriPortadorSV = VAR_ONCO_VALORA_ELIURISV;
        this.eliuriDisminucionF = VAR_ONCO_VALORA_ELIURIDISMINCIONFRECUENCIA;// Disminución de la frecuencia
        this.elifecalIncapacidaHeces = VAR_ONCO_VALORA_ELIURIINCAPACIDAD;// Incapacidad para eliminar las heces
        this.elifecalDificultosa = VAR_ONCO_VALORA_ELIURIDIFICULTOSA; // Defecación dificultosa
        this.elifecalHecesduras = VAR_ONCO_VALORA_ELIURIHECESDURAS; // Eliminación de heces duras. secas y formadas
        this.elifecalPlenitud = VAR_ONCO_VALORA_ELIURISENSACIONPELNI;// Sensación de plenitud o presión rectal
        this.elifecalDistension = VAR_ONCO_VALORA_ELIURIDISTENSION;// Distensión abdominal
        this.elifecalDolorAbdominal = VAR_ONCO_VALORA_ELIURIDOLORABDOMINAL;
        this.elifecal3deposiciones = VAR_ONCO_VALORA_ELIURI3VESDIA;// Eliminación por o menos de tres deposiciones
        // líquidas por día

        this.eliuriGoteo = VAR_ONCO_VALORA_ELIURIGOTEO;// Goteo orina al aumentar presión abdominal
        this.eliuriUrgencia = VAR_ONCO_VALORA_ELIURIURGENCIA;// Urgencia urinaria
        this.eliuriEscozor = VAR_ONCO_VALORA_ELIURIESCOZOR;

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
        // Autopercepcion - Auto concepto
        this.autoAceptaImagen = VAR_ONCO_VALORA_AUTOACPETAIMAGEN;
        this.autoSentimientoPos = VAR_ONCO_VALORA_AUTOSETIMIENTOPOS;
        this.autoTranquilo = VAR_ONCO_VALORA_AUTOTRANQUILO;
        this.autoEscalaDME = VAR_ONCO_VALORA_AUTOESCALADEME;
        this.autoAlteraVisioPropia = VAR_ONCO_VALORA_AUTOALTERAVISIOPRO;
        this.autoSentiNegativo = VAR_ONCO_VALORA_AUTOSENTINEGA;
        this.autoVerbalizaAuton = VAR_ONCO_VALORA_AUTOVERBALIZAUTON;
        this.autoAngustia = VAR_ONCO_VALORA_AUTOANGUSTIA;
        this.autoNerviosismo = VAR_ONCO_VALORA_AUTONERVIOSISMO;
        this.autoInquietud = VAR_ONCO_VALORA_AUTOINQUIETUD;
        this.autoOtros = VAR_ONCO_VALORA_AUTOOTROS;
        // sexualidad
        this.sexuVerbalizaProblema = VAR_ONCO_VALORA_SEXUVERBALIZA;
        this.sexuIncapacidadSastis = VAR_ONCO_VALORA_SEXUINCAPACIDADSASTI;
        this.sexuLimitaciones = VAR_ONCO_VALORA_SEXULIMITACIONES;
        this.sexuOtros = VAR_ONCO_VALORA_SEXUOTRAS;

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
        this.cognAprendizaje = VAR_ONCO_VALORA_COGNAPRENDIZAJE;
        this.cognConsciente = VAR_ONCO_VALORA_COGNCONSCIENTE;
        this.cognConscienteEnfermdad = VAR_ONCO_VALORA_COGNCONSCIENTEENFERMEDAD;
        this.cognConoceEnfermdad = VAR_ONCO_VALORA_COGNCONOCEEFERMEDAD;
        this.cognVerBalizadolor = VAR_ONCO_VALORA_COGNVERVALIZADOLOR;
        this.cognEvidenciaDolor = VAR_ONCO_VALORA_COGNEVIDENCIADOLOR;
        this.cognDesorientacion = VAR_ONCO_VALORA_COGNDESORIENTACION;
        this.cognDolorEva = VAR_ONCO_VALORA_COGNDOLOREVA;
        this.cognDolorLocalizacion = VAR_ONCO_VALORA_COGNDOLORLOCA;

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

    public Variable getTratamientoHabitual() {
        return tratamientoHabitual;
    }

    public Variable getVariableTratamientoHabitual() {
        return tratamientoHabitual;
    }

    public String getTratamientoHabitualString() {
        return tratamientoHabitual.getValor();
    }

    public void setTratamientoHabitual(Variable tratamientoHabitual) {
        this.tratamientoHabitual = tratamientoHabitual;
    }

    public void setTratamientoHabitual(String valor) {
        this.tratamientoHabitual.setValor(valor);
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

    public Variable getAccesoVenoso() {
        return accesoVenoso;
    }

    public Variable getVariableAccesoVenoso() {
        return accesoVenoso;
    }

    public String getAccesoVenosoString() {
        return accesoVenoso.getValor();
    }

    public void setAccesoVenoso(Variable accesoVenoso) {
        this.accesoVenoso = accesoVenoso;
    }

    public void setAccesoVenoso(String valor) {
        this.accesoVenoso.setValor(valor);
        ;
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

    public Variable getRespiTolreaAVD() {
        return respiTolreaAVD;
    }

    public Variable getVariableRespiTolreaAVD() {
        return respiTolreaAVD;
    }

    public Boolean getRespiTolreaAVDBoolean() {
        if (respiTolreaAVD != null && !respiTolreaAVD.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setRespiTolreaAVD(Variable respiTolreaAVD) {
        this.respiTolreaAVD = respiTolreaAVD;
    }

    public void setRespiTolreaAVD(Boolean valor) {
        if (valor == true) {
            respiTolreaAVD.setValor("Tolera realización de las AVD");
        } else {
            respiTolreaAVD.setValor("");
        }
    }

    public Variable getRespiAutoHigiene() {
        return respiAutoHigiene;
    }

    public Variable getVarialbeRespiAutoHigiene() {
        return respiAutoHigiene;
    }

    public Boolean getRespiAutoHigieneBoolean() {
        if (respiAutoHigiene != null && !respiAutoHigiene.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiAutoHigiene(Variable respiAutoHigiene) {
        this.respiAutoHigiene = respiAutoHigiene;
    }

    public void setRespiAutoHigiene(Boolean valor) {
        if (valor == true) {
            respiAutoHigiene.setValor("Autonomía en el autocuidado");
        } else {
            respiAutoHigiene.setValor("");
        }
    }

    public Variable getRespiRitmoNormal() {
        return respiRitmoNormal;
    }

    public Variable getVariableRespiRitmoNormal() {
        return respiRitmoNormal;
    }

    public Boolean getRespiRitmoNormalBooelan() {
        if (respiRitmoNormal != null && !respiRitmoNormal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiRitmoNormal(Variable respiRitmoNormal) {
        this.respiRitmoNormal = respiRitmoNormal;
    }

    public void setRespiRitmoNormal(Boolean valor) {
        if (valor == true) {
            respiRitmoNormal.setValor("Ritmo y frecuencia respiratoria normal");
        } else {
            respiRitmoNormal.setValor("");
        }
    }

    public Variable getRespiPielMucoRosa() {
        return respiPielMucoRosa;
    }

    public Variable getVariableRespiPielMucoRosa() {
        return respiPielMucoRosa;
    }

    public Boolean getRespiPielMucoRosaBoolean() {
        if (respiPielMucoRosa != null && !respiPielMucoRosa.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiPielMucoRosa(Variable respiPielMucoRosa) {
        this.respiPielMucoRosa = respiPielMucoRosa;
    }

    public void setRespiPielMucoRosa(Boolean valor) {
        if (valor == true) {
            respiPielMucoRosa.setValor("Piel y musodas roadas");
        } else {
            respiPielMucoRosa.setValor("");
        }
    }

    public Variable getRespiKarnofsky() {
        return respiKarnofsky;
    }

    public Variable getVariableRespiKarnofsky() {
        return respiKarnofsky;
    }

    public String getRespiKarnofskyString() {
        return respiKarnofsky.getValor();
    }

    /*
	 * public Boolean getRespiKarnofskyBoolean() { if (respiKarnofsky != null &&
	 * !respiKarnofsky.getValor().isEmpty()) return true; else return false; }
     */
    public void setRespiKarnofsky(Variable respiKarnofsky) {
        this.respiKarnofsky = respiKarnofsky;
    }

    public void setRespiKarnofsky(String valor) {
        this.respiKarnofsky.setValor(valor);
    }

    /*
	 * public void setRespiKarnofsky(Boolean valor) { if (valor == true)
	 * respiKarnofsky.setValor("Escala de Karnofsky"); else
	 * respiKarnofsky.setValor(""); }
     */
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

    public Variable getRespiCianosis() {
        return respiCianosis;
    }

    public Variable getVariableRespiCianosis() {
        return respiCianosis;
    }

    public Boolean getRespiCianosisBoolean() {
        if (respiCianosis != null && !respiCianosis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiCianosis(Variable respiCianosis) {
        this.respiCianosis = respiCianosis;
    }

    public void setRespiCianosis(Boolean valor) {
        if (valor == true) {
            this.respiCianosis.setValor("Cianosis");
        } else {
            this.respiCianosis.setValor("");
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

    public Variable getRespiEsputoEsce() {
        return respiEsputoEsce;
    }

    public Variable getVariableRespiEsputoEsce() {
        return respiEsputoEsce;
    }

    public Boolean getRespiEsputoEsceBoolean() {
        if (respiEsputoEsce != null && !respiEsputoEsce.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiEsputoEsce(Variable respiEsputoEsce) {
        this.respiEsputoEsce = respiEsputoEsce;
    }

    public void setRespiEsputoEsce(Boolean valor) {
        if (valor == true) {
            respiEsputoEsce.setValor("Cantidad escesiva de esputo");
        } else {
            respiEsputoEsce.setValor("");
        }
    }

    public Variable getRespiTosinefi() {
        return respiTosinefi;
    }

    public Variable getVariableRespiTosinefi() {
        return respiTosinefi;
    }

    public Boolean getRespiTosinefiBoolean() {
        if (respiTosinefi != null && !respiTosinefi.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiTosinefi(Variable respiTosinefi) {
        this.respiTosinefi = respiTosinefi;
    }

    public void setRespiTosinefi(Boolean valor) {
        if (valor == true) {
            respiTosinefi.setValor("Tos ineficaz");
        } else {
            respiTosinefi.setValor("");
        }
    }

    public Variable getRespiSonidosNormales() {
        return respiSonidosNormales;
    }

    public Variable getVariableRespiSonidosNormales() {
        return respiSonidosNormales;
    }

    public Boolean getRespiSonidosNormalesBoolean() {
        if (respiSonidosNormales != null && !respiSonidosNormales.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiSonidosNormales(Variable respiSonidosNormales) {
        this.respiSonidosNormales = respiSonidosNormales;
    }

    public void setRespiSonidosNormales(Boolean valor) {
        if (valor == true) {
            respiSonidosNormales.setValor("Sonidos respiratorios normales");
        } else {
            respiSonidosNormales.setValor("");
        }
    }

    public Variable getRespiFatigaDebili() {
        return respiFatigaDebili;
    }

    public Variable getVariableRespiFatigaDebili() {
        return respiFatigaDebili;
    }

    public Boolean getRespiFatigaDebiliBoolean() {
        if (respiFatigaDebili != null && !respiFatigaDebili.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiFatigaDebili(Variable respiFatigaDebili) {
        this.respiFatigaDebili = respiFatigaDebili;
    }

    public void setRespiFatigaDebili(Boolean valor) {
        if (valor == true) {
            respiFatigaDebili.setValor("Informes verbales de fatiga o debilidad");
        } else {
            respiFatigaDebili.setValor("");
        }
    }

    public Variable getRespiMoviDescoordin() {
        return respiMoviDescoordin;
    }

    public Variable getVariableRespiMoviDescoordin() {
        return respiMoviDescoordin;
    }

    public Boolean getRespiMoviDescoordinBoolean() {
        if (respiMoviDescoordin != null && !respiMoviDescoordin.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRespiMoviDescoordin(Variable respiMoviDescoordin) {
        this.respiMoviDescoordin = respiMoviDescoordin;
    }

    public void setRespiMoviDescoordin(Boolean valor) {
        if (valor == true) {
            respiMoviDescoordin.setValor("Movimientos descoordinados, espasmódicos, nervios");
        } else {
            respiMoviDescoordin.setValor("");
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

    public Variable getAliMasticaDificultad() {
        return aliMasticaDificultad;
    }

    public Variable getVariableAliMasticaDificultad() {
        return aliMasticaDificultad;
    }

    public Boolean getAliMasticaDificultadBoolean() {
        if (aliMasticaDificultad != null && !aliMasticaDificultad.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliMasticaDificultad(Variable aliMasticaDificultad) {
        this.aliMasticaDificultad = aliMasticaDificultad;
    }

    public void setAliMasticaDificultad(Boolean valor) {
        if (valor == true) {
            this.aliMasticaDificultad.setValor("Mastica sin dificultad");
        } else {
            this.aliMasticaDificultad.setValor("");
        }
    }

    public Variable getAliProtesisDental() {
        return aliProtesisDental;
    }

    public Variable getVariableAliProtesisDental() {
        return aliProtesisDental;
    }

    public Boolean getAliProtesisDentalBoolean() {
        if (aliProtesisDental != null && !aliProtesisDental.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliProtesisDental(Variable aliProtesisDental) {
        this.aliProtesisDental = aliProtesisDental;
    }

    public void setAliProtesisDental(Boolean valor) {
        if (valor == true) {
            this.aliProtesisDental.setValor("Prótesis dental ajustada");
        } else {
            this.aliProtesisDental.setValor("");
        }
    }

    public Variable getAliPielIntegra() {
        return aliPielIntegra;
    }

    public Variable getVariableAliPielIntegra() {
        return aliPielIntegra;
    }

    public Boolean getAliPielIntegraBoolean() {
        if (aliPielIntegra != null && !aliPielIntegra.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliPielIntegra(Variable aliPielIntegra) {
        this.aliPielIntegra = aliPielIntegra;
    }

    public void setAliPielIntegra(Boolean valor) {
        if (valor == true) {
            this.aliPielIntegra.setValor("Piel íntegra");
        } else {
            this.aliPielIntegra.setValor("");
        }
    }

    public Variable getAliTemperaturaNormal() {
        return aliTemperaturaNormal;
    }

    public Variable getVariableAliTemperaturaNormal() {
        return aliTemperaturaNormal;
    }

    public Boolean getAliTemperaturaNormalBoolean() {
        if (aliTemperaturaNormal != null && !aliTemperaturaNormal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliTemperaturaNormal(Variable aliTemperaturaNormal) {
        this.aliTemperaturaNormal = aliTemperaturaNormal;
    }

    public void setAliTemperaturaNormal(Boolean valor) {
        if (valor == true) {
            this.aliTemperaturaNormal.setValor("Temperatura corporal normal");
        }
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

    public Variable getAliXerostomia() {
        return aliXerostomia;
    }

    public Variable getVariableAliXerostomia() {
        return aliXerostomia;
    }

    public Boolean getAliXerostomiaBoolean() {
        if (aliXerostomia != null && !aliXerostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliXerostomia(Variable aliXerostomia) {
        this.aliXerostomia = aliXerostomia;
    }

    public void setAliXerostomia(Boolean valor) {
        if (valor == true) {
            this.aliXerostomia.setValor("Xerostomía");
        } else {
            this.aliXerostomia.setValor("");
        }
    }

    public Variable getAliLesionUlcera() {
        return aliLesionUlcera;
    }

    public Variable getVariableAliLesionUlcera() {
        return aliLesionUlcera;
    }

    public Boolean getAliLesionUlceraBoolean() {
        if (aliLesionUlcera != null && !aliLesionUlcera.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliLesionUlcera(Variable aliLesionUlcera) {
        this.aliLesionUlcera = aliLesionUlcera;
    }

    public void setAliLesionUlcera(Boolean valor) {
        if (valor == true) {
            this.aliLesionUlcera.setValor("Lesiones o úlceras orales");
        } else {
            this.aliLesionUlcera.setValor("");
        }
    }

    public Variable getAliReduccTemp() {
        return aliReduccTemp;
    }

    public Variable getVariableAliReduccTemp() {
        return aliReduccTemp;
    }

    public Boolean getAliReduccTempBoolean() {
        if (aliReduccTemp != null && !aliReduccTemp.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliReduccTemp(Variable aliReduccTemp) {
        this.aliReduccTemp = aliReduccTemp;
    }

    public void setAliReduccTemp(Boolean valor) {
        if (valor == true) {
            this.aliReduccTemp.setValor("Reducción Tª corporal por debajo del límite normal");
        } else {
            this.aliReduccTemp.setValor("");
        }
    }

    public Variable getAliAumentoTemp() {
        return aliAumentoTemp;
    }

    public Variable getVariableAliAumentoTemp() {
        return aliAumentoTemp;
    }

    public Boolean getAliAumentoTempBoolean() {
        if (aliAumentoTemp != null && !aliAumentoTemp.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliAumentoTemp(Variable aliAumentoTemp) {
        this.aliAumentoTemp = aliAumentoTemp;
    }

    public void setAliAumentoTemp(Boolean valor) {
        if (valor == true) {
            this.aliAumentoTemp.setValor("Aumento Tª corporal por encima del límite normal");
        } else {
            this.aliAumentoTemp.setValor("");
        }
    }

    public Variable getAliEdemas() {
        return aliEdemas;
    }

    public Variable getVariableAliEdemas() {
        return aliEdemas;
    }

    public Boolean getAliEdemasBoolean() {
        if (aliEdemas != null && !aliEdemas.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAliEdemas(Variable aliEdemas) {
        this.aliEdemas = aliEdemas;
    }

    public void setAliEdemas(Boolean valor) {
        if (valor == true) {
            this.aliEdemas.setValor("Edemas");
        } else {
            this.aliEdemas.setValor("");
        }
    }

    public Variable getAliHerida() {
        return aliHerida;
    }

    public Variable getVariableAliHerida() {
        return aliHerida;
    }

    public String getAliHeridaString() {
        return aliHerida.getValor();
    }

    public void setAliHerida(Variable aliHerida) {
        this.aliHerida = aliHerida;
    }

    public void setAliHerida(String valor) {
        this.aliHerida.setValor(valor);
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

    public Variable getEliIntesNormal() {
        return eliIntesNormal;
    }

    public Variable getVariableEliIntesNormal() {
        return eliIntesNormal;
    }

    public Boolean getEliIntesNormalBoolean() {
        if (eliIntesNormal != null && !eliIntesNormal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliIntesNormal(Variable eliIntesNormal) {
        this.eliIntesNormal = eliIntesNormal;
    }

    public void setEliIntesNormal(Boolean valor) {
        if (valor == true) {
            this.eliIntesNormal.setValor("Patrón de eliminación intestinal normal");
        } else {
            this.eliIntesNormal.setValor("");
        }
    }

    public Variable getEliUrinaNormal() {
        return eliUrinaNormal;
    }

    public Variable getVariableEliUrinaNormal() {
        return eliUrinaNormal;
    }

    public Boolean getEliUrinaNormalBoolean() {
        if (eliUrinaNormal != null && !eliUrinaNormal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliUrinaNormal(Variable eliUrinaNormal) {
        this.eliUrinaNormal = eliUrinaNormal;
    }

    public void setEliUrinaNormal(Boolean valor) {
        if (valor == true) {
            this.eliUrinaNormal.setValor("Patrón de eliminación urinaria normal");
        } else {
            this.eliUrinaNormal.setValor("");
        }
    }

    public Variable getEliUrostomia() {
        return eliUrostomia;
    }

    public Variable getVariableEliUrostomia() {
        return eliUrostomia;
    }

    public Boolean getEliUrostomiaBoolean() {
        if (eliUrostomia != null && !eliUrostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliUrostomia(Variable eliUrostomia) {
        this.eliUrostomia = eliUrostomia;
    }

    public void setEliUrostomia(Boolean valor) {
        if (valor == true) {
            this.eliUrostomia.setValor("Urostomía normofuncionante");
        } else {
            this.eliUrostomia.setValor("");
        }
    }

    public Variable getEliNefrostomia() {
        return eliNefrostomia;
    }

    public Variable getVariableEliNefrostomia() {
        return eliNefrostomia;
    }

    public Boolean getEliNefrostomiaBoolean() {
        if (eliNefrostomia != null && !eliNefrostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliNefrostomia(Variable eliNefrostomia) {
        this.eliNefrostomia = eliNefrostomia;
    }

    public void setEliNefrostomia(Boolean valor) {
        if (valor == true) {
            this.eliNefrostomia.setValor("Nefrostomía normofuncionante");
        } else {
            this.eliNefrostomia.setValor("");
        }
    }

    public Variable getEliColostomia() {
        return eliColostomia;
    }

    public Variable getVariableEliColostomia() {
        return eliColostomia;
    }

    public Boolean getEliColostomiaBoolean() {
        if (eliColostomia != null && !eliColostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliColostomia(Variable eliColostomia) {
        this.eliColostomia = eliColostomia;
    }

    public void setEliColostomia(Boolean valor) {
        if (valor == true) {
            this.eliColostomia.setValor("Colostomía normofuncionante");
        } else {
            this.eliColostomia.setValor("");
        }
    }

    public Variable getEliIleostomia() {
        return eliIleostomia;
    }

    public Variable getVariableEliIleostomia() {
        return eliIleostomia;
    }

    public Boolean getEliIleostomiaBoolean() {
        if (eliIleostomia != null && !eliIleostomia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliIleostomia(Variable eliIleostomia) {
        this.eliIleostomia = eliIleostomia;
    }

    public void setEliIleostomia(Boolean valor) {
        if (valor == true) {
            this.eliIleostomia.setValor("Ileostomia normofuncionante");
        } else {
            this.eliIleostomia.setValor("");
        }
    }

    public Variable getEliContiIntes() {
        return eliContiIntes;
    }

    public Variable getVariableEliContiIntes() {
        return eliContiIntes;
    }

    public Boolean getEliContiIntesBoolean() {
        if (eliContiIntes != null && !eliContiIntes.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliContiIntes(Variable eliContiIntes) {
        this.eliContiIntes = eliContiIntes;
    }

    public void setEliContiIntes(Boolean valor) {
        if (valor == true) {
            this.eliContiIntes.setValor("Continencia intestinal");
        } else {
            this.eliContiIntes.setValor("");
        }
    }

    public Variable getEliContiUrinaria() {
        return eliContiUrinaria;
    }

    public Variable getVariableEliContiUrinaria() {
        return eliContiUrinaria;
    }

    public Boolean getEliContiUrinariaBoolean() {
        if (eliContiUrinaria != null && !eliContiUrinaria.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliContiUrinaria(Variable eliContiUrinaria) {
        this.eliContiUrinaria = eliContiUrinaria;
    }

    public void setEliContiUrinaria(Boolean valor) {
        if (valor == true) {
            this.eliContiUrinaria.setValor("Continencia urinaria");
        } else {
            this.eliContiUrinaria.setValor("");
        }
    }

    public Variable getEliuriDisminucionF() {
        return eliuriDisminucionF;
    }

    public Variable getVariableEliuriDisminucionF() {
        return eliuriDisminucionF;
    }

    public Boolean getEliuriDisminucionFBoolean() {
        if (eliuriDisminucionF != null && !eliuriDisminucionF.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliuriDisminucionF(Variable eliuriDisminucionF) {
        this.eliuriDisminucionF = eliuriDisminucionF;
    }

    public void setEliuriDisminucionF(Boolean valor) {
        if (valor == true) {
            this.eliuriDisminucionF.setValor("Disminución frecuencia micción");
        } else {
            this.eliuriDisminucionF.setValor("");
        }
    }

    public Variable getElifecalIncapacidaHeces() {
        return elifecalIncapacidaHeces;
    }

    public Variable getVariableElifecalIncapacidaHeces() {
        return elifecalIncapacidaHeces;
    }

    public Boolean getElifecalIncapacidaHecesBoolean() {
        if (elifecalIncapacidaHeces != null && !elifecalIncapacidaHeces.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecalIncapacidaHeces(Variable elifecalIncapacidaHeces) {
        this.elifecalIncapacidaHeces = elifecalIncapacidaHeces;
    }

    public void setElifecalIncapacidaHeces(Boolean valor) {
        if (valor == true) {
            this.elifecalIncapacidaHeces.setValor("Incapacidad para eliminar las heces");
        } else {
            this.elifecalIncapacidaHeces.setValor("");
        }
    }

    public Variable getElifecalDificultosa() {
        return elifecalDificultosa;
    }

    public Variable getVariableElifecalDificultosa() {
        return elifecalDificultosa;
    }

    public Boolean getElifecalDificultosaBoolean() {
        if (elifecalDificultosa != null && !elifecalDificultosa.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecalDificultosa(Variable elifecalDificultosa) {
        this.elifecalDificultosa = elifecalDificultosa;
    }

    public void setElifecalDificultosa(Boolean valor) {
        if (valor == true) {
            this.elifecalDificultosa.setValor("Defecación dificultosa");
        } else {
            this.elifecalDificultosa.setValor("");
        }
    }

    public Variable getElifecalHecesduras() {
        return elifecalHecesduras;
    }

    public Variable getVariableElifecalHecesduras() {
        return elifecalHecesduras;
    }

    public Boolean getElifecalHecesdurasBoolean() {
        if (elifecalHecesduras != null && !elifecalHecesduras.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecalHecesduras(Variable elifecalHecesduras) {
        this.elifecalHecesduras = elifecalHecesduras;
    }

    public void setElifecalHecesduras(Boolean valor) {
        if (valor == true) {
            this.elifecalHecesduras.setValor("Eliminación heces duras,secas y formadas.");
        } else {
            this.elifecalHecesduras.setValor("");
        }
    }

    public Variable getElifecalPlenitud() {
        return elifecalPlenitud;
    }

    public Variable getVariableElifecalPlenitud() {
        return elifecalPlenitud;
    }

    public Boolean getElifecalPlenitudBoolean() {
        if (elifecalPlenitud != null && !elifecalPlenitud.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecalPlenitud(Variable elifecalPlenitud) {
        this.elifecalPlenitud = elifecalPlenitud;
    }

    public void setElifecalPlenitud(Boolean valor) {
        if (valor == true) {
            this.elifecalPlenitud.setValor("Sensación de plenitud o presión rectal");
        } else {
            this.elifecalPlenitud.setValor("");
        }
    }

    public Variable getElifecalDistension() {
        return elifecalDistension;
    }

    public Variable getVariableElifecalDistension() {
        return elifecalDistension;
    }

    public Boolean getElifecalDistensionBoolean() {
        if (elifecalDistension != null && !elifecalDistension.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecalDistension(Variable elifecalDistension) {
        this.elifecalDistension = elifecalDistension;
    }

    public void setElifecalDistension(Boolean valor) {
        if (valor == true) {
            this.elifecalDistension.setValor("Distención abdominal");
        } else {
            this.elifecalDistension.setValor("");
        }
    }

    public Variable getElifecalDolorAbdominal() {
        return elifecalDolorAbdominal;
    }

    public Variable getVariableElifecalDolorAbdominal() {
        return elifecalDolorAbdominal;
    }

    public Boolean getElifecalDolorAbdominalBoolean() {
        if (elifecalDolorAbdominal != null && !elifecalDolorAbdominal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecalDolorAbdominal(Variable elifecalDolorAbdominal) {
        this.elifecalDolorAbdominal = elifecalDolorAbdominal;
    }

    public void setElifecalDolorAbdominal(Boolean valor) {
        if (valor == true) {
            this.elifecalDolorAbdominal.setValor("Dolor abdominal");
        } else {
            this.elifecalDolorAbdominal.setValor("");
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

    public Boolean getElifecalDiarreaBoolean() {
        if (elifecalDiarrea != null && !elifecalDiarrea.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecalDiarrea(Variable elifecalDiarrea) {
        this.elifecalDiarrea = elifecalDiarrea;
    }

    public void setElifecalDiarrea(String valor) {
        this.elifecalDiarrea.setValor(valor);
    }

    public void setElifecalDiarrea(Boolean valor) {
        if (valor == true) {
            this.elifecalDiarrea.setValor("Diarrea");
        } else {
            this.elifecalDiarrea.setValor("");
        }
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

    public Boolean getElifecalEstrenimientoBoolean() {
        if (elifecalEstrenimiento != null && !elifecalEstrenimiento.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecalEstrenimiento(Variable elifecalEstrenimiento) {
        this.elifecalEstrenimiento = elifecalEstrenimiento;
    }

    public void setElifecalEstrenimiento(String valor) {
        this.elifecalEstrenimiento.setValor(valor);
    }

    public void setElifecalEstrenimiento(Boolean valor) {
        if (valor == true) {
            this.elifecalEstrenimiento.setValor("Estreñimiento");
        } else {
            this.elifecalEstrenimiento.setValor("");
        }
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

    public Variable getElifecal3deposiciones() {
        return elifecal3deposiciones;
    }

    public Variable getVariableElifecal3deposiciones() {
        return elifecal3deposiciones;
    }

    public Boolean getElifecal3deposicionesBoolean() {
        if (elifecal3deposiciones != null && !elifecal3deposiciones.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setElifecal3deposiciones(Variable elifecal3deposiciones) {
        this.elifecal3deposiciones = elifecal3deposiciones;
    }

    public void setElifecal3deposiciones(Boolean valor) {
        if (valor == true) {
            this.elifecal3deposiciones.setValor("Eliminación por lo menos de tres deposiciones líquidas por día.");
        } else {
            this.elifecal3deposiciones.setValor("");
        }
    }

    public Variable getEliuriGoteo() {
        return eliuriGoteo;
    }

    public Variable getVariableEliuriGoteo() {
        return eliuriGoteo;
    }

    public Boolean getEliuriGoteoBoolean() {
        if (eliuriGoteo != null && !eliuriGoteo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliuriGoteo(Variable eliuriGoteo) {
        this.eliuriGoteo = eliuriGoteo;
    }

    public void setEliuriGoteo(Boolean valor) {
        if (valor == true) {
            this.eliuriGoteo.setValor("Goteo de orina al aumentar la presión abdominal");
        } else {
            this.eliuriGoteo.setValor("");
        }
    }

    public Variable getEliuriUrgencia() {
        return eliuriUrgencia;
    }

    public Variable getVariableEliuriUrgencia() {
        return eliuriUrgencia;
    }

    public Boolean getEliuriUrgenciaBoolean() {
        if (eliuriUrgencia != null && !eliuriUrgencia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEliuriUrgencia(Variable eliuriUrgencia) {
        this.eliuriUrgencia = eliuriUrgencia;
    }

    public void setEliuriUrgencia(Boolean valor) {
        if (valor == true) {
            this.eliuriUrgencia.setValor("Urgencia urinaria");
        } else {
            this.eliuriUrgencia.setValor("");
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
//autopercecpion

    public Variable getAutoAceptaImagen() {
        return autoAceptaImagen;
    }

    public Variable getVariableAutoAceptaImagen() {
        return autoAceptaImagen;
    }

    public Boolean getAutoAceptaImagenBoolean() {
        if (autoAceptaImagen != null && !autoAceptaImagen.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAutoAceptaImagen(Variable autoAceptaImagen) {
        this.autoAceptaImagen = autoAceptaImagen;
    }

    public void setAutoAceptaImagen(Boolean valor) {
        if (valor == true) {
            this.autoAceptaImagen.setValor("Acepta su imagen corporal");
        } else {
            this.autoAceptaImagen.setValor("");
        }
    }

    public Variable getAutoSentimientoPos() {
        return autoSentimientoPos;
    }

    public Variable getVariableAutoSentimientoPos() {
        return autoSentimientoPos;
    }

    public Boolean getAutoSentimientoPosBoolean() {
        if (autoSentimientoPos != null && !autoSentimientoPos.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAutoSentimientoPos(Variable autoSentimientoPos) {
        this.autoSentimientoPos = autoSentimientoPos;
    }

    public void setAutoSentimientoPos(Boolean valor) {
        if (valor == true) {
            this.autoSentimientoPos.setValor("Sentimientos positivos  a cerca de si mismo");
        } else {
            this.autoSentimientoPos.setValor("");
        }
    }

    public Variable getAutoTranquilo() {
        return autoTranquilo;
    }

    public Variable getVariableAutoTranquilo() {
        return autoTranquilo;
    }

    public Boolean getAutoTranquiloBoolean() {
        if (autoTranquilo != null && !autoTranquilo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAutoTranquilo(Variable autoTranquilo) {
        this.autoTranquilo = autoTranquilo;
    }

    public void setAutoTranquilo(Boolean valor) {
        if (valor == true) {
            this.autoTranquilo.setValor("Se muestra tranquilo y relajado");
        } else {
            this.autoTranquilo.setValor("");
        }
    }

    public Variable getAutoEscalaDME() {
        return autoEscalaDME;
    }

    public Variable getVariableAutoEscalaDME() {
        return autoEscalaDME;
    }

    public String getAutoEscalaDMEString() {
        return autoEscalaDME.getValor();
    }

    public void setAutoEscalaDME(Variable autoEscalaDME) {
        this.autoEscalaDME = autoEscalaDME;
    }

    public void setAutoEscalaDME(String valor) {
        this.autoEscalaDME.setValor(valor);
    }

    public Variable getAutoAlteraVisioPropia() {
        return autoAlteraVisioPropia;
    }

    public Variable getVariableAutoAlteraVisioPropia() {
        return autoAlteraVisioPropia;
    }

    public Boolean getAutoAlteraVisioPropiaBoolean() {
        if (autoAlteraVisioPropia != null && !autoAlteraVisioPropia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAutoAlteraVisioPropia(Variable autoAlteraVisioPropia) {
        this.autoAlteraVisioPropia = autoAlteraVisioPropia;
    }

    public void setAutoAlteraVisioPropia(Boolean valor) {
        if (valor == true) {
            this.autoAlteraVisioPropia.setValor("Alteración de la visión del propio cuerpo");
        } else {
            this.autoAlteraVisioPropia.setValor("");
        }
    }

    public Variable getAutoSentiNegativo() {
        return autoSentiNegativo;
    }

    public Variable getVariableAutoSentiNegativo() {
        return autoSentiNegativo;
    }

    public Boolean getAutoSentiNegativoBoolean() {
        if (autoSentiNegativo != null && !autoSentiNegativo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAutoSentiNegativo(Variable autoSentiNegativo) {
        this.autoSentiNegativo = autoSentiNegativo;
    }

    public void setAutoSentiNegativo(Boolean valor) {
        if (valor == true) {
            this.autoSentiNegativo.setValor("Sentimientos negativos sobre el cuero");
        } else {
            this.autoSentiNegativo.setValor("");
        }
    }

    public Variable getAutoVerbalizaAuton() {
        return autoVerbalizaAuton;
    }

    public Variable getVariableAutoVerbalizaAuton() {
        return autoVerbalizaAuton;
    }

    public Boolean getAutoVerbalizaAutonBoolean() {
        if (autoVerbalizaAuton != null && !autoVerbalizaAuton.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAutoVerbalizaAuton(Variable autoVerbalizaAuton) {
        this.autoVerbalizaAuton = autoVerbalizaAuton;
    }

    public void setAutoVerbalizaAuton(Boolean valor) {
        if (valor == true) {
            this.autoVerbalizaAuton.setValor("Verbalizaciones negativas");
        } else {
            this.autoVerbalizaAuton.setValor("");
        }
    }

    public Variable getAutoAngustia() {
        return autoAngustia;
    }

    public Variable getVaraibleAutoAngustia() {
        return autoAngustia;
    }

    public Boolean getAutoAngustiaBoolean() {
        if (autoAngustia != null && !autoAngustia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAutoAngustia(Variable autoAngustia) {
        this.autoAngustia = autoAngustia;
    }

    public void setAutoAngustia(Boolean valor) {
        if (valor == true) {
            this.autoAngustia.setValor("Angustia");
        } else {
            this.autoAngustia.setValor("");
        }
    }

    public Variable getAutoNerviosismo() {
        return autoNerviosismo;
    }

    public Variable getVariableAutoNerviosismo() {
        return autoNerviosismo;
    }

    public Boolean getAutoNerviosismoBoolean() {
        if (autoNerviosismo != null && !autoNerviosismo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAutoNerviosismo(Variable autoNerviosismo) {
        this.autoNerviosismo = autoNerviosismo;
    }

    public void setAutoNerviosismo(Boolean valor) {
        if (valor == true) {
            this.autoNerviosismo.setValor("Nerviosismo");
        } else {
            this.autoNerviosismo.setValor("");
        }
    }

    public Variable getAutoInquietud() {
        return autoInquietud;
    }

    public Variable getVariableAutoInquietud() {
        return autoInquietud;
    }

    public Boolean getAutoInquietudBoolean() {
        if (autoInquietud != null && !autoInquietud.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setAutoInquitud(Variable autoInquietud) {
        this.autoInquietud = autoInquietud;
    }

    public void setAutoInquitud(Boolean valor) {
        if (valor == true) {
            this.autoInquietud.setValor("Inquietud");
        } else {
            this.autoInquietud.setValor("");
        }
    }

    public Variable getAutoOtros() {
        return autoOtros;
    }

    public Variable getVariableAutoOtros() {
        return autoOtros;
    }

    public String getAutoOtrosString() {
        return autoOtros.getValor();
    }

    public void setAutoOtros(Variable autoOtros) {
        this.autoOtros = autoOtros;
    }

    public void setAutoOtros(String valor) {
        this.autoOtros.setValor(valor);
    }

    // Sexualidad y reproduccion
    public Variable getSexuVerbalizaProblema() {
        return sexuVerbalizaProblema;
    }

    public Variable getVariableSexuVerbalizaProblema() {
        return sexuVerbalizaProblema;
    }

    public Boolean getSexuVerbalizaProblemaBoolean() {
        if (sexuVerbalizaProblema != null && !sexuVerbalizaProblema.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSexuVerbalizaProblema(Variable sexuVerbalizaProblema) {
        this.sexuVerbalizaProblema = sexuVerbalizaProblema;
    }

    public void setSexuVerbalizaProblema(Boolean valor) {
        if (valor == true) {
            this.sexuVerbalizaProblema.setValor("Vervalización del problema");
        } else {
            this.sexuVerbalizaProblema.setValor("");
        }
        ;
    }

    public Variable getSexuIncapacidadSastis() {
        return sexuIncapacidadSastis;
    }

    public Variable getVariableSexuIncapacidadSastis() {
        return sexuIncapacidadSastis;
    }

    public Boolean getSexuIncapacidadSastisBoolean() {
        if (sexuIncapacidadSastis != null && !sexuIncapacidadSastis.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSexuIncapacidadSastis(Variable sexuIncapacidadSastis) {
        this.sexuIncapacidadSastis = sexuIncapacidadSastis;
    }

    public void setSexuIncapacidadSastis(Boolean valor) {
        if (valor == true) {
            this.sexuIncapacidadSastis.setValor("Incapacidad par lograr satisfacción deseada");
        } else {
            this.sexuIncapacidadSastis.setValor("");
        }
        ;
    }

    public Variable getSexuLimitaciones() {
        return sexuLimitaciones;
    }

    public Variable getVariableSexuLimitaciones() {
        return sexuLimitaciones;
    }

    public Boolean getSexuLimitacionesBoolean() {
        if (sexuLimitaciones != null && !sexuLimitaciones.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setSexuLimitaciones(Variable sexuLimitaciones) {
        this.sexuLimitaciones = sexuLimitaciones;
    }

    public void setSexuLimitaciones(Boolean valor) {
        if (valor == true) {
            this.sexuLimitaciones.setValor("Limitacines reales o percibidas impuestas por la enfermedad o la terapira");
        } else {
            this.sexuLimitaciones.setValor("");
        }
        ;
    }

    public Variable getSexuOtros() {
        return sexuOtros;
    }

    public Variable getVariableSexuOtros() {
        return sexuOtros;
    }

    public String getSexuOtrosString() {
        return sexuOtros.getValor();
    }

    public void setSexuOtros(Variable sexuOtros) {
        this.sexuOtros = sexuOtros;
    }

    public void setSexuOtros(String valor) {
        this.sexuOtros.setValor(valor);
    }

    // movilizacion
    public Variable getVariableMovilizacion() {
        return movilizacion;
    }

    public Variable getMovilizacion() {
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

// congnitivo dolor
    public Variable getCognAprendizaje() {
        return cognAprendizaje;
    }

    public Variable getVariableCognAprendizaje() {
        return cognAprendizaje;
    }

    public Boolean getCognAprendizajeBoolean() {
        if (cognAprendizaje != null && !cognAprendizaje.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCognAprendizaje(Variable cognAprendizaje) {
        this.cognAprendizaje = cognAprendizaje;
    }

    public void setCognAprendizaje(Boolean valor) {
        if (valor == true) {
            this.cognAprendizaje.setValor("Muestra capacidad para el aprendizaje");
        } else {
            this.cognAprendizaje.setValor("");
        }
    }

    public Variable getCognConsciente() {
        return cognConsciente;
    }

    public Variable getVariableCognConsciente() {
        return cognConsciente;
    }

    public Boolean getCognConscienteBoolean() {
        if (cognConsciente != null && !cognConsciente.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCognConsciente(Variable cognConsciente) {
        this.cognConsciente = cognConsciente;
    }

    public void setCognConsciente(Boolean valor) {
        if (valor == true) {
            this.cognConsciente.setValor("Consciente orientao y responde adecuadamente");
        } else {
            this.cognConsciente.setValor("");
        }
    }

    public Variable getCognConscienteEnfermdad() {
        return cognConscienteEnfermdad;
    }

    public Variable getVariableCognConscienteEnfermdad() {
        return cognConscienteEnfermdad;
    }

    public Boolean getCognConscienteEnfermdadBoolean() {
        if (cognConscienteEnfermdad != null && !cognConscienteEnfermdad.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCognConscienteEnfermdad(Variable cognConscienteEnfermdad) {
        this.cognConscienteEnfermdad = cognConscienteEnfermdad;
    }

    public void setCognConscienteEnfermdad(Boolean valor) {
        if (valor == true) {
            this.cognConscienteEnfermdad.setValor("Consciente de su enfermedad");
        } else {
            this.cognConscienteEnfermdad.setValor("");
        }
    }

    public Variable getCognConoceEnfermdad() {
        return cognConoceEnfermdad;
    }

    public Variable getVariableCognConoceEnfermdad() {
        return cognConoceEnfermdad;
    }

    public Boolean getCognConoceEnfermdadBoolean() {
        if (cognConoceEnfermdad != null && !cognConoceEnfermdad.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCognConoceEnfermdad(Variable cognConoceEnfermdad) {
        this.cognConoceEnfermdad = cognConoceEnfermdad;
    }

    public void setCognConoceEnfermdad(Boolean valor) {
        if (valor == true) {
            this.cognConoceEnfermdad.setValor("Conoce de su enfermedad. Deseo de manejar tratamiento");
        } else {
            this.cognConoceEnfermdad.setValor("");
        }
    }

    public Variable getCognVerBalizadolor() {
        return cognVerBalizadolor;
    }

    public Variable getVariableCognVerBalizadolor() {
        return cognVerBalizadolor;
    }

    public Boolean getCognVerBalizadolorBoolean() {
        if (cognVerBalizadolor != null && !cognVerBalizadolor.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCognVerBalizadolor(Variable cognVerBalizadolor) {
        this.cognVerBalizadolor = cognVerBalizadolor;
    }

    public void setCognVerBalizadolor(Boolean valor) {
        if (valor == true) {
            this.cognVerBalizadolor.setValor("Verbaliza dolor");
        } else {
            this.cognVerBalizadolor.setValor("");
        }
    }

    public Variable getCognEvidenciaDolor() {
        return cognEvidenciaDolor;
    }

    public Variable getVariableCognEvidenciaDolor() {
        return cognEvidenciaDolor;
    }

    public Boolean getCognEvidenciaDolorBoolean() {
        if (cognEvidenciaDolor != null && !cognEvidenciaDolor.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCognEvidenciaDolor(Variable cognEvidenciaDolor) {
        this.cognEvidenciaDolor = cognEvidenciaDolor;
    }

    public void setCognEvidenciaDolor(Boolean valor) {
        if (valor == true) {
            this.cognEvidenciaDolor.setValor("Evidencia dolor");
        } else {
            this.cognEvidenciaDolor.setValor("");
        }
    }

    public Variable getCognDesorientacion() {
        return cognDesorientacion;
    }

    public Variable getVariableCognDesorientacion() {
        return cognDesorientacion;
    }

    public Boolean getCognDesorientacionBoolean() {
        if (cognDesorientacion != null && !cognDesorientacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setCognDesorientacion(Variable cognDesorientacion) {
        this.cognDesorientacion = cognDesorientacion;
    }

    public void setCognDesorientacion(Boolean valor) {
        if (valor == true) {
            this.cognDesorientacion.setValor("Desorientación en tiempo, espacio y personas");
        } else {
            this.cognDesorientacion.setValor("");
        }
    }

    public Variable getCognDolorEva() {
        return cognDolorEva;
    }

    public Variable getVariableCognDolorEva() {
        return cognDolorEva;
    }

    public String getCognDolorEvaString() {
        return cognDolorEva.getValor();
    }

    public void setCognDolorEva(Variable cognDolorEva) {
        this.cognDolorEva = cognDolorEva;
    }

    public void setCognDolorEva(String valor) {
        this.cognDolorEva.setValor(valor);
    }

    public Variable getCognDolorLocalizacion() {
        return cognDolorLocalizacion;
    }

    public Variable getVariableCognDolorLocalizacion() {
        return cognDolorLocalizacion;
    }

    public String getCognDolorLocalizacionString() {
        return cognDolorLocalizacion.getValor();
    }

    public void setCognDolorLocalizacion(Variable cognDolorLocalizacion) {
        this.cognDolorLocalizacion = cognDolorLocalizacion;
    }

    public void setCognDolorLocalizacion(String valor) {
        this.cognDolorLocalizacion.setValor(valor);
    }

    // situación emocional
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

    public String getCadenaActividadFisica() {
        String cadena = "";
        if (respiNoAlteracion != null && !respiNoAlteracion.getValor().isEmpty()) {
            cadena = cadena.concat(respiNoAlteracion.getValor() + " .");
        }
        if (respiTolreaAVD != null && !respiTolreaAVD.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiTolreaAVD.getValor() + ". ");
        }

        if (respiAutoHigiene != null && !respiAutoHigiene.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiAutoHigiene.getValor() + ". ");
        }

        if (respiRitmoNormal != null && !respiRitmoNormal.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiRitmoNormal.getValor() + ". ");
        }

        if (respiPielMucoRosa != null && !respiPielMucoRosa.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiPielMucoRosa.getValor() + ". ");
        }

        if (respiKarnofsky != null && !respiKarnofsky.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiKarnofsky.getDescripcionCortaValor() + ". ");
        }

        if (respidisnea != null && !respidisnea.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respidisnea.getValor() + ". ");
        }

        if (respiCianosis != null && !respiCianosis.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiCianosis.getValor() + ". ");
        }

        if (respiOxigeno != null && !respiOxigeno.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiOxigeno.getValor() + ". ");
        }

        if (respiEsputoEsce != null && !respiEsputoEsce.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiEsputoEsce.getValor() + ". ");
        }

        if (respiTosinefi != null && !respiTosinefi.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiTosinefi.getValor() + ". ");
        }

        if (respiSonidosNormales != null && !respiSonidosNormales.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiSonidosNormales.getValor() + ". ");
        }

        if (respiFatigaDebili != null && !respiFatigaDebili.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiFatigaDebili.getValor() + ". ");
        }

        if (respiMoviDescoordin != null && !respiMoviDescoordin.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiMoviDescoordin.getValor() + ". ");
        }

        if (respiOtros != null && !respiOtros.getValor().isEmpty()) {
            cadena = cadena.concat(" " + respiOtros.getDescripcionCortaValor() + ". ");
        }

        return cadena;
    }

    public String getCadenaAlimentacion() {
        String cadena = "";
        if (aliDietaHabitual != null && !aliDietaHabitual.getValor().isEmpty()) {
            cadena = cadena.concat(aliDietaHabitual.getDescripcionCortaValor() + ". ");
        }

        if (aliIngestaLiquidos != null && !aliIngestaLiquidos.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliIngestaLiquidos.getDescripcionCortaValor() + ". ");
        }

        if (aliSuplemento != null && !aliSuplemento.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliSuplemento.getDescripcionCortaValor() + ". ");
        }

        if (aliMasticaDificultad != null && !aliMasticaDificultad.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliMasticaDificultad.getValor() + ". ");
        }

        if (aliProtesisDental != null && !aliProtesisDental.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliProtesisDental.getValor() + ". ");
        }

        if (aliDificultadDeglu != null && !aliDificultadDeglu.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliDificultadDeglu.getValor() + ". ");
        }

        if (aliPielIntegra != null && !aliPielIntegra.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliPielIntegra.getValor() + ". ");
        }

        if (aliTemperaturaNormal != null && !aliTemperaturaNormal.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliTemperaturaNormal.getValor() + ". ");
        }

        if (aliNauseas != null && !aliNauseas.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliNauseas.getValor() + ". ");
        }

        if (aliVomitos != null && !aliVomitos.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliVomitos.getValor() + ". ");
        }

        if (aliAteraGusto != null && !aliAteraGusto.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliAteraGusto.getValor() + ". ");
        }

        if (aliXerostomia != null && !aliXerostomia.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliXerostomia.getValor() + ". ");
        }

        if (aliLesionUlcera != null && !aliLesionUlcera.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliLesionUlcera.getValor() + ". ");
        }

        if (aliReduccTemp != null && !aliReduccTemp.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliReduccTemp.getValor() + ". ");
        }

        if (aliAumentoTemp != null && !aliAumentoTemp.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliAumentoTemp.getValor() + ". ");
        }

        if (aliEdemas != null && !aliEdemas.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliEdemas.getValor() + ". ");
        }

        if (aliHerida != null && !aliHerida.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliHerida.getDescripcionCortaValor() + ". ");
        }

        if (aliOtros != null && !aliOtros.getValor().isEmpty()) {
            cadena = cadena.concat(" " + aliOtros.getDescripcionCortaValor() + ". ");
        }

        return cadena;
    }

    public String getCadenaEliminacion() {
        String cadena = "";
        if (eliuriCantidad != null && !eliuriCantidad.getValor().isEmpty()) {
            cadena = cadena.concat(eliuriCantidad.getValor() + ". ");
        }

        if (eliuriColor != null && !eliuriColor.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliuriColor.getValor() + ". ");
        }

        if (eliuriOtros != null && !eliuriOtros.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliuriOtros.getValor() + ". ");
        }

        if (eliIntesNormal != null && !eliIntesNormal.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliIntesNormal.getValor() + ". ");
        }

        if (eliUrinaNormal != null && !eliUrinaNormal.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliUrinaNormal.getValor() + ". ");
        }

        if (eliUrostomia != null && !eliUrostomia.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliUrostomia.getValor() + ". ");
        }

        if (eliNefrostomia != null && !eliNefrostomia.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliNefrostomia.getValor() + ". ");
        }

        if (eliColostomia != null && !eliColostomia.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliColostomia.getValor() + ". ");
        }

        if (eliIleostomia != null && !eliIleostomia.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliIleostomia.getValor() + ". ");
        }

        if (eliContiIntes != null && !eliContiIntes.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliContiIntes.getValor() + ". ");
        }

        if (eliContiUrinaria != null && !eliContiUrinaria.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliContiUrinaria.getValor() + ". ");
        }

        if (eliuriPortadorSV != null && !eliuriPortadorSV.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliuriPortadorSV.getValor() + ". ");
        }

        if (eliuriDisminucionF != null && !eliuriDisminucionF.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliuriDisminucionF.getValor() + ". ");
        }

        if (elifecalIncapacidaHeces != null && !elifecalIncapacidaHeces.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalIncapacidaHeces.getValor() + ". ");
        }

        if (elifecalDificultosa != null && !elifecalDificultosa.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalDificultosa.getValor() + ". ");
        }

        if (elifecalHecesduras != null && !elifecalHecesduras.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalHecesduras.getValor() + ". ");
        }

        if (elifecalPlenitud != null && !elifecalPlenitud.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalPlenitud.getValor() + ". ");
        }

        if (elifecalDistension != null && !elifecalDistension.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalDistension.getValor() + ". ");
        }

        if (elifecalDolorAbdominal != null && !elifecalDolorAbdominal.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalDolorAbdominal.getValor() + ". ");
        }

        if (elifecal3deposiciones != null && !elifecal3deposiciones.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecal3deposiciones.getValor() + ". ");
        }

        if (eliuriGoteo != null && !eliuriGoteo.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliuriGoteo.getValor() + ". ");
        }

        if (eliuriUrgencia != null && !eliuriUrgencia.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliuriUrgencia.getValor() + ". ");
        }

        if (eliuriEscozor != null && !eliuriEscozor.getValor().isEmpty()) {
            cadena = cadena.concat(" " + eliuriEscozor.getValor() + ". ");
        }

        if (elifecalDiarrea != null && !elifecalDiarrea.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalDiarrea.getValor() + ". ");
        }

        if (elifecalEstrenimiento != null && !elifecalEstrenimiento.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalEstrenimiento.getValor() + ". ");
        }

        if (elifecalOstomia != null && !elifecalOstomia.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalOstomia.getValor() + ". ");
        }

        if (elifecalOtro != null && !elifecalOtro.getValor().isEmpty()) {
            cadena = cadena.concat(" " + elifecalOtro.getDescripcionCorta() + ". ");
        }

        return cadena;
    }

    public String getCadenaResposo() {
        String cadena = "";
        if (repoSueno != null && !repoSueno.getValor().isEmpty()) {
            cadena = cadena.concat(repoSueno.getDescripcionCortaValor() + ". ");
        }

        if (repoSueInfusiones != null && !repoSueInfusiones.getValor().isEmpty()) {
            cadena = cadena.concat(" " + repoSueInfusiones.getValor() + ". ");
        }

        if (repoSueMedicacion != null && !repoSueMedicacion.getValor().isEmpty()) {
            cadena = cadena.concat(" " + repoSueMedicacion.getDescripcionCortaValor() + ". ");
        }

        if (repoSueOtros != null && !repoSueOtros.getValor().isEmpty()) {
            cadena = cadena.concat(" " + repoSueOtros.getDescripcionCortaValor() + ". ");
        }

        return cadena;
    }

    public String getCadenaCongnitivo() {
        String cadena = "";
        if (cognAprendizaje != null && !cognAprendizaje.getValor().isEmpty()) {
            cadena = cadena.concat(cognAprendizaje.getValor() + ". ");
        }

        if (cognConsciente != null && !cognConsciente.getValor().isEmpty()) {
            cadena = cadena.concat(" " + cognConsciente.getValor() + ". ");
        }

        if (cognConscienteEnfermdad != null && !cognConscienteEnfermdad.getValor().isEmpty()) {
            cadena = cadena.concat(" " + cognConscienteEnfermdad.getValor() + ". ");
        }

        if (cognConoceEnfermdad != null && !cognConoceEnfermdad.getValor().isEmpty()) {
            cadena = cadena.concat(" " + cognConoceEnfermdad.getValor() + ". ");
        }

        if (cognVerBalizadolor != null && !cognVerBalizadolor.getValor().isEmpty()) {
            cadena = cadena.concat(" " + cognVerBalizadolor.getValor() + ". ");
        }

        if (cognEvidenciaDolor != null && !cognEvidenciaDolor.getValor().isEmpty()) {
            cadena = cadena.concat(" " + cognEvidenciaDolor.getValor() + ". ");
        }

        if (cognDesorientacion != null && !cognDesorientacion.getValor().isEmpty()) {
            cadena = cadena.concat(" " + cognDesorientacion.getValor() + ". ");
        }

        if (cognDolorEva != null && !cognDolorEva.getValor().isEmpty()) {
            cadena = cadena.concat(" Escala eva:" + cognDolorEva.getDescripcionCortaValor() + ". ");
        }

        if (cognDolorLocalizacion != null && !cognDolorLocalizacion.getValor().isEmpty()) {
            cadena = cadena.concat(" " + cognDolorLocalizacion.getDescripcionCortaValor() + ". ");
        }

        return cadena;
    }

    public String getCadenaAutoaceptacion() {
        String cadena = "";
        if (autoAceptaImagen != null && !autoAceptaImagen.getValor().isEmpty()) {
            cadena = cadena.concat(autoAceptaImagen.getValor() + ". ");
        }

        if (autoSentimientoPos != null && !autoSentimientoPos.getValor().isEmpty()) {
            cadena = cadena.concat(" " + autoSentimientoPos.getValor() + ". ");
        }

        if (autoTranquilo != null && !autoTranquilo.getValor().isEmpty()) {
            cadena = cadena.concat("  " + autoTranquilo.getValor() + " .");
        }

        if (autoEscalaDME != null && !autoEscalaDME.getValor().isEmpty()) {
            cadena = cadena.concat(" " + autoEscalaDME.getDescripcionCortaValor() + " .");
        }

        if (autoAlteraVisioPropia != null && !autoAlteraVisioPropia.getValor().isEmpty()) {
            cadena = cadena.concat(" " + autoAlteraVisioPropia.getValor() + ". ");
        }

        if (autoSentiNegativo != null && !autoSentiNegativo.getValor().isEmpty()) {
            cadena = cadena.concat(" " + autoSentiNegativo.getValor() + ". ");
        }

        if (autoVerbalizaAuton != null && !autoVerbalizaAuton.getValor().isEmpty()) {
            cadena = cadena.concat(" " + autoVerbalizaAuton.getValor() + ". ");
        }

        if (autoAngustia != null && !autoAngustia.getValor().isEmpty()) {
            cadena = cadena.concat(" " + autoAngustia.getValor() + ". ");
        }
        if (autoNerviosismo != null && !autoNerviosismo.getValor().isEmpty()) {
            cadena = cadena.concat(" " + autoNerviosismo.getValor() + ". ");
        }

        if (autoInquietud != null && !autoInquietud.getValor().isEmpty()) {
            cadena = cadena.concat(" " + autoInquietud.getValor() + ". ");
        }

        if (autoOtros != null && !autoOtros.getValor().isEmpty()) {
            cadena = cadena.concat(" " + autoOtros.getDescripcionCortaValor() + ". ");
        }

        return cadena;
    }

    public String getCadenaSexualidad() {
        String cadena = "";
        if (sexuVerbalizaProblema != null && !sexuVerbalizaProblema.getValor().isEmpty()) {
            cadena = cadena.concat(sexuVerbalizaProblema.getValor() + ". ");
        }

        if (sexuIncapacidadSastis != null && !sexuIncapacidadSastis.getValor().isEmpty()) {
            cadena = cadena.concat(" " + sexuIncapacidadSastis.getValor() + ". ");
        }

        if (sexuLimitaciones != null && !sexuLimitaciones.getValor().isEmpty()) {
            cadena = cadena.concat(" " + sexuLimitaciones.getValor() + ". ");
        }

        if (sexuOtros != null && !sexuOtros.getValor().isEmpty()) {
            cadena = cadena.concat(" " + sexuOtros.getDescripcionCortaValor());
        }

        return cadena;
    }

    @Override
    public String getContenidoHtml() {
        String contenido = "";
        contenido = contenido.concat("<b>Fecha</b>:" + this.getFechaHora() + " <b>" + this.getUserid().getApellidosNombre() + "</b><br>");
        contenido = contenido.concat(this.tratamientoHabitual.getDescripcionCortaValor() + "<br>");
        contenido = contenido.concat(this.antecedentes.getDescripcionCortaValor() + "<br>");
        contenido = contenido.concat(this.diagnostico.getDescripcionCortaValor() + "<br>");
        contenido = contenido.concat(this.alergias.getDescripcionCortaValor() + "<br>");
        contenido = contenido.concat(this.tratamientoqt.getDescripcionCortaValor() + "<br>");
        contenido = contenido.concat(this.sitFamiliarSocial.getDescripcionCortaValor() + "<br>");
        contenido = contenido.concat("<hr>");
        contenido = contenido.concat("<b>Actividad ejercicio físico</b>");
        contenido = contenido.concat(this.getCadenaActividadFisica());
        contenido = contenido.concat("<hr>");
        contenido = contenido.concat("<b>Nutrición metabólico </b>");
        contenido = contenido.concat(this.getCadenaAlimentacion());
        contenido = contenido.concat("<hr>");
        contenido = contenido.concat("<b>Eliminación </b>");
        contenido = contenido.concat(this.getCadenaEliminacion());
        contenido = contenido.concat("<hr>");
        contenido = contenido.concat("<b>Reposo sueño </b>");
        contenido = contenido.concat(this.getCadenaResposo());
        contenido = contenido.concat("<hr>");
        contenido = contenido.concat("<b>Congnitivo perceptivo </b>");
        contenido = contenido.concat(this.getCadenaCongnitivo());
        contenido = contenido.concat("<hr>");
        contenido = contenido.concat("<b>Autoaceptación autoconcepto </b>");
        contenido = contenido.concat(this.getCadenaAutoaceptacion());
        contenido = contenido.concat("<hr>");
        contenido = contenido.concat("<b>Sexualidad </b>");
        contenido = contenido.concat(this.getCadenaSexualidad());
        return contenido;
    }

}
