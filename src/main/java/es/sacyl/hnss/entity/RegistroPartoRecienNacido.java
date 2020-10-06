package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.ConstantesClinicas;
import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class RegistroPartoRecienNacido extends Registro {

    private Variable numerohc;
    private Variable fechaNacimiento;
    private Variable horaNacimiento;
    private Variable sexo;

    private Variable apgar1Fc;
    private Variable apgar1Respira;
    private Variable apgar1Tono;
    private Variable apgar1Reflejos;
    private Variable apgar1Color;
    private Variable apgar1Puntuacion;

    private Variable apgar5Fc;
    private Variable apgar5Respira;
    private Variable apgar5Tono;
    private Variable apgar5Reflejos;
    private Variable apgar5Color;
    private Variable apgar5Puntuacion;

    private Variable apgar10Fc;
    private Variable apgar10Respira;
    private Variable apgar10Tono;
    private Variable apgar10Reflejos;
    private Variable apgar10Color;
    private Variable apgar10Puntuacion;

    private Variable cordonArterial;
    private Variable cordonVenoso;
    private Variable reanimacion;
    private Variable pinzaCordon;

    private Variable peso;
    private Variable talla;
    private Variable pCefalico;

    private Variable pielConPiel;
    private Variable lactanciaParitorio;
    private Variable profilaxSBG;
    private Variable mortalidad;
    private Variable ingresNeonatos;

    private Variable profilaxisAntibio;
    private Variable profilaxisOcular;
    private Variable curaCordon;
    private Variable primerMeconio;
    private Variable primeraMiccion;
    private Variable profilaxisAntihemo;
    private Variable odenmedica;

    private Variable madrenhc;  // cuando se duplica el registro del recien nacido al hijo se añade el nhc de la madre
    
    public final static Long PLANTILLLA_EDITOR_PAR_RECIENNACIDO = new Long(794881060);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public final Variable VAR_PARTO_RN_NUMEROHC = new Variable("35001203", "99G2", new Long(35001203), "Nº Historia");
    public final Variable VAR_PARTO_RN_FECHANACI = new Variable("184099003", "SNM3", new Long(13756846),
            "Fecha Nacimiento");
    public final Variable VAR_PARTO_RN_HORANACI = new Variable("397836004", "SNM3", new Long(14062089),
            "Hora Nacimiento");

    public final Variable VAR_PARTO_RN_SEXO = new Variable("57312000", "SNM3", new Long(13660134), "Sexo");

    public final Variable VAR_PARTO_RN_FC1 = new Variable("13994711", "99G2", new Long(13994711), "Fc minuto");
    public final Variable VAR_PARTO_RN_RESPIRACION1 = new Variable("13994712", "99G2", new Long(13994712),
            "Respiración minuto");
    public final Variable VAR_PARTO_RN_TONO1 = new Variable("13994713", "99G2", new Long(13994713), "Tono minuto");
    public final Variable VAR_PARTO_RN_REFLEJOS1 = new Variable("13994714", "99G2", new Long(13994714),
            "Reflejos minuto");
    public final Variable VAR_PARTO_RN_COLOR1 = new Variable("13994715", "99G2", new Long(13994715), "Color minuto");
    public final Variable VAR_PARTO_RN_APGAR1 = new Variable("364741000", "SNM3", new Long(13994708), "Valor 1 apgar");

    public final Variable VAR_PARTO_RN_FC5 = new Variable("13994716", "99G2", new Long(13994716), "Fc 5 minuto");
    public final Variable VAR_PARTO_RN_RESPIRACION5 = new Variable("13994717", "99G2", new Long(13994717),
            "F.Respiratoria 5 minuto");
    public final Variable VAR_PARTO_RN_TONO5 = new Variable("13994718", "99G2", new Long(13994718), "Tono 5 minuto");
    public final Variable VAR_PARTO_RN_REFLEJOS5 = new Variable("13994719", "99G2", new Long(13994719),
            "Reflejos 5 minuto");
    public final Variable VAR_PARTO_RN_COLOR5 = new Variable("13994720", "99G2", new Long(13994720), "Color 5 minuto");
    public final Variable VAR_PARTO_RN_APGAR5 = new Variable("364742007", "SNM3", new Long(13994709), "Valor  5 apgar");

    public final Variable VAR_PARTO_RN_FC10 = new Variable("13994721", "99G2", new Long(13994721), "Fc 10 minuto");
    public final Variable VAR_PARTO_RN_RESPIRACION10 = new Variable("13994722", "99G2", new Long(13994722),
            "Respiración 10 minuto");
    public final Variable VAR_PARTO_RN_TONO10 = new Variable("13994723", "99G2", new Long(13994723), "Tono 10 minuto");
    public final Variable VAR_PARTO_RN_REFLEJOS10 = new Variable("13994724", "99G2", new Long(13994724),
            "Reflejos 10 minuto");
    public final Variable VAR_PARTO_RN_COLOR10 = new Variable("13994725", "99G2", new Long(13994725),
            "Color 10 minuto");
    public final Variable VAR_PARTO_RN_APGAR10 = new Variable("364743002", "SNM3", new Long(13994710),
            "Valor 10 apgar");

    public final Variable VAR_PARTO_RN_CORDONARTERIAL = new Variable("13994694", "99G2", new Long(13994694),
            "Ph cordón arteral ");
    public final Variable VAR_PARTO_RN_CORDONVENOSO = new Variable("13994695", "99G2", new Long(13994695),
            "Ph cordón venoso ");

    public final Variable VAR_PARTO_RN_REANIMACIONNONATAL = new Variable("386412000", "SNM3", new Long(14062093),
            "Reanimimación neonatal ");
    public final Variable VAR_PARTO_RN_PINZAMIENTO = new Variable("13994696", "99G2", new Long(13994696),
            "Pinzamiento cordón");
    // reanimación neonatal ,0-Piel con piel/No
    // reanimación,I-Secado,aspiración,II-CPAP,III-Presión Positiva,IV-Intubación
    // y/o Masaje cardiaco,V-Medicación
    // Pinzamiento cordón ,Precoz,Tardío

    public final Variable VAR_PARTO_RN_PIELCONPIL = new Variable("13994697", "99G2", new Long(13994697),
            "Piel con piel ");
//  99G2      13994697             Piel con piel paritorio/cesárea   ,Madre,Padre

    public final Variable VAR_PARTO_RN_LACTANCIA = new Variable("13994698", "99G2", new Long(13994698),
            "Lactancia materna paritorio ");
    // 13994698 99G2 13994698 Lactancia materna paritorio ",NO,SI anteparto,SI
    // intraparto,SI postparto
    // 13994698 99G2 13994698 Lactancia materna paritorio ,SI,NO

    // 29463-7 LN 13806464 Peso
    // 248328003 SNM3 3066 Talla
//	        
    public final Variable VAR_PARTO_RN_PROFILAXISSGB = new Variable("13994699", "99G2", new Long(13994699),
            "ProfilaxiS SGB ");
    // 99G2 13994699 Profilaxis SBG ,Completa,Incompleta,Sin profilaxis

    public final Variable VAR_PARTO_RN_MORTALIDAD = new Variable("35003325", "99G2", new Long(35003325), "Mortalidad ");
    public final Variable VAR_PARTO_RN_INGRESONENOTANOS = new Variable("32485007", "SNM3", new Long(13750688),
            "Ingreso neonatos");

    public final Variable VAR_PARTO_RN_PROFIANTIBIO = new Variable("422181004", "SNM3", new Long(35002803),
            "Profilaxis antibiótica");
    public final Variable VAR_PARTO_RN_PROFIANTIHEMO = new Variable("13994703", "99G2", new Long(13994703),
            "Profilaxis antihemorrágica");
    public final Variable VAR_PARTO_RN_PROFIOCULAR = new Variable("13994702", "99G2", new Long(13994702),
            "Profilaxis Ocular");

    public final Variable VAR_PARTO_RN_CURACORDON = new Variable("13818671", "99G2", new Long(13818671),
            "Cura cordón umbilical");
    public final Variable VAR_PARTO_RN_PRIMERMECONIO = new Variable("13994701", "99G2", new Long(13994701),
            "Primer meconio partos");
    public final Variable VAR_PARTO_RN_PRIMERAMICCION = new Variable("13994700", "99G2", new Long(13994700),
            "Primera miccioón ");

    public final Variable VAR_PARTO_RN_ORDENMEDICA = new Variable("8752", "99G2", new Long(8752), "Orden médica ");

     	
    public final Variable VAR_PARTO_MADRENHC = new Variable("NOMMAD", "99G2", new Long(14062086), "NHC madre");
    
    public final Variable VAR_PARTO_RN_PESO = new ConstantesClinicas().VAR_CTES_PESO;
    public final Variable VAR_PARTO_RN_TALLA = new ConstantesClinicas().VAR_CTES_TALLA;
    public final Variable VAR_PARTO_RN_PCEFALICO = new ConstantesClinicas().VAR_CTES_PCEFALICO;

    // ingreso hospitalario ,SI,NO
    public RegistroPartoRecienNacido() {
        super();
        iniciaRecienNacido();
    }

    public RegistroPartoRecienNacido(Long id) {
        super(id);
        iniciaRecienNacido();
    }

    public RegistroPartoRecienNacido(RegistroPartoRecienNacido r) {
        super(r);
        this.numerohc = r.getNumerohc();
        this.fechaNacimiento = r.getFechaNacimiento();
        this.horaNacimiento = r.getHoraNacimiento();
        this.sexo = r.getSexo();

        this.apgar1Fc = r.getApgar1Fc();
        this.apgar1Respira = r.getApgar1Respira();
        this.apgar1Tono = r.getApgar1Tono();
        this.apgar1Reflejos = r.getApgar1Reflejos();
        this.apgar1Color = r.getApgar1Color();
        this.apgar1Puntuacion = r.getApgar1Puntuacion();

        this.apgar5Fc = r.getApgar5Fc();
        this.apgar5Respira = r.getApgar5Respira();
        this.apgar5Tono = r.getApgar5Tono();
        this.apgar5Reflejos = r.getApgar5Reflejos();
        this.apgar5Color = r.getApgar5Color();
        this.apgar5Puntuacion = r.getApgar5Puntuacion();

        this.apgar10Fc = r.getApgar10Fc();
        this.apgar10Respira = r.getApgar10Respira();
        this.apgar10Tono = r.getApgar10Tono();
        this.apgar10Reflejos = r.getApgar10Reflejos();
        this.apgar10Color = r.getApgar10Color();
        this.apgar10Puntuacion = r.getApgar10Puntuacion();

        this.cordonArterial = r.getCordonArterial();
        this.cordonVenoso = r.getCordonVenoso();
        this.reanimacion = r.getReanimacion();
        this.pinzaCordon = r.getPinzaCordon();

        this.peso = r.getPeso();
        this.talla = r.getTalla();
        this.pCefalico = r.getpCefalico();

        this.pielConPiel = r.getPielConPiel();
        this.lactanciaParitorio = r.getLactanciaParitorio();
        this.profilaxSBG = r.getProfilaxSBG();
        this.mortalidad = r.getMortalidad();
        this.ingresNeonatos = r.getIngresNeonatos();

        this.profilaxisAntibio = r.getProfilaxisAntibio();
        this.profilaxisAntihemo = r.getProfilaxisAntihemo();
        this.profilaxisOcular = r.getProfilaxisOcular();
        this.curaCordon = r.getCuraCordon();
        this.primerMeconio = r.getPrimerMeconio();
        this.primeraMiccion = r.getPrimeraMiccion();

        this.odenmedica = r.getOdenmedica();
this.madrenhc= r.getMadrenhc();
    }

    public void iniciaRecienNacido() {
        this.setTiporegistro(RegistroPartoRecienNacido.TIPO_REGISTRO_PARTO);
        this.setDescripcion("11.-Recién Nacido");
        this.setPlantilla_edior(RegistroPartoRecienNacido.PLANTILLLA_EDITOR_PAR_RECIENNACIDO);
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));

        this.numerohc = VAR_PARTO_RN_NUMEROHC;
        this.fechaNacimiento = VAR_PARTO_RN_FECHANACI;
        this.horaNacimiento = VAR_PARTO_RN_HORANACI;
        this.sexo = VAR_PARTO_RN_SEXO;

        this.apgar1Fc = VAR_PARTO_RN_FC1;
        this.apgar1Respira = VAR_PARTO_RN_RESPIRACION1;
        this.apgar1Tono = VAR_PARTO_RN_TONO1;
        this.apgar1Reflejos = VAR_PARTO_RN_REFLEJOS1;
        this.apgar1Color = VAR_PARTO_RN_COLOR1;
        this.apgar1Puntuacion = VAR_PARTO_RN_APGAR1;

        this.apgar5Fc = VAR_PARTO_RN_FC5;
        this.apgar5Respira = VAR_PARTO_RN_RESPIRACION5;
        this.apgar5Tono = VAR_PARTO_RN_TONO5;
        this.apgar5Reflejos = VAR_PARTO_RN_REFLEJOS5;
        this.apgar5Color = VAR_PARTO_RN_COLOR5;
        this.apgar5Puntuacion = VAR_PARTO_RN_APGAR5;

        this.apgar10Fc = VAR_PARTO_RN_FC10;
        this.apgar10Respira = VAR_PARTO_RN_RESPIRACION10;
        this.apgar10Tono = VAR_PARTO_RN_TONO10;
        this.apgar10Reflejos = VAR_PARTO_RN_REFLEJOS10;
        this.apgar10Color = VAR_PARTO_RN_COLOR10;
        this.apgar10Puntuacion = VAR_PARTO_RN_APGAR10;

        this.cordonArterial = VAR_PARTO_RN_CORDONARTERIAL;
        this.cordonVenoso = VAR_PARTO_RN_CORDONVENOSO;
        this.reanimacion = VAR_PARTO_RN_REANIMACIONNONATAL;
        this.pinzaCordon = VAR_PARTO_RN_PINZAMIENTO;

        this.peso = VAR_PARTO_RN_PESO;
        this.talla = VAR_PARTO_RN_TALLA;
        this.pCefalico = VAR_PARTO_RN_PCEFALICO;

        this.pielConPiel = VAR_PARTO_RN_PIELCONPIL;
        this.lactanciaParitorio = VAR_PARTO_RN_LACTANCIA;
        this.profilaxSBG = VAR_PARTO_RN_PROFILAXISSGB;
        this.mortalidad = VAR_PARTO_RN_MORTALIDAD;
        this.ingresNeonatos = VAR_PARTO_RN_INGRESONENOTANOS;

        this.profilaxisAntibio = VAR_PARTO_RN_PROFIANTIBIO;
        this.profilaxisAntihemo = VAR_PARTO_RN_PROFIANTIHEMO;
        this.profilaxisOcular = VAR_PARTO_RN_PROFIOCULAR;
        this.curaCordon = VAR_PARTO_RN_CURACORDON;
        this.primerMeconio = VAR_PARTO_RN_PRIMERMECONIO;
        this.primeraMiccion = VAR_PARTO_RN_PRIMERAMICCION;

        this.odenmedica = VAR_PARTO_RN_ORDENMEDICA;
        this.madrenhc= VAR_PARTO_MADRENHC;
    }

    public Variable getNumerohc() {
        return numerohc;
    }

    public Variable getVariableNumerohc() {
        return numerohc;
    }

    public String getNumerohcString() {
        return numerohc.getValor();
    }

    public void setNumerohc(Variable numerohc) {
        this.numerohc = numerohc;
    }

    public void setNumerohc(String valor) {
        this.numerohc.setValor(valor);
    }

    public Variable getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Variable getVariableFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaNacimientoString() {
        return fechaNacimiento.getValor();
    }

    public LocalDate getFechaNacimientoDate() {
        LocalDate fechaDate = null;
        if (fechaNacimiento != null && fechaNacimiento.getValor() != null && !fechaNacimiento.getValor().isEmpty()) {
            if (Utilidades.isNumeric(fechaNacimiento.getValor())) {
                fechaDate = Utilidades.getFechaLocalDate(fechaNacimiento.getValor());
            }
        }
        return fechaDate;
    }

    public void setFechaNacimiento(Variable fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fecha) {
        this.fechaNacimiento.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getHoraNacimiento() {
        return horaNacimiento;
    }

    public Variable getVariableHoraNacimiento() {
        return horaNacimiento;
    }

    public String getHoraNacimientoString() {
        return horaNacimiento.getValor();
    }

    public void setHoraNacimiento(Variable horaNacimiento) {
        this.horaNacimiento = horaNacimiento;
    }

    public void setHoraNacimiento(String valor) {
        this.horaNacimiento.setValor(valor);
    }

    public String getFechaHoraNacimiento() {
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String feString = "";
        if (fechaNacimiento != null && fechaNacimiento.getValor() != null && !fechaNacimiento.getValor().isEmpty()) {
            if (Utilidades.isNumeric(fechaNacimiento.getValor())) {
                if (this.getFechaNacimientoDate() != null) {
                    feString = fechadma.format(this.getFechaNacimientoDate());
                    if (horaNacimiento != null) {
                        feString = feString + " " + horaNacimiento.getValor();
                    }
                }
            }
        }
        return feString;
    }

    public Variable getSexo() {
        return sexo;
    }

    public Variable getVariableSexo() {
        return sexo;
    }

    public String getSexoString() {
        return sexo.getValor();
    }

    public void setSexo(Variable sexo) {
        this.sexo = sexo;
    }

    public void setSexo(String valor) {
        this.sexo.setValor(valor);
    }

    public Variable getApgar1Fc() {
        return apgar1Fc;
    }

    public Variable getVariableApgar1Fc() {
        return apgar1Fc;
    }

    public String getApgar1FcString() {
        return apgar1Fc.getValor();
    }

    public void setApgar1Fc(Variable apgar1Fc) {
        this.apgar1Fc = apgar1Fc;
    }

    public void setApgar1Fc(String valor) {
        this.apgar1Fc.setValor(valor);
    }

    public Variable getApgar1Respira() {
        return apgar1Respira;
    }

    public Variable getVariableApgar1Respira() {
        return apgar1Respira;
    }

    public String getApgar1RespiraString() {
        return apgar1Respira.getValor();
    }

    public void setApgar1Respira(Variable apgar1Respira) {
        this.apgar1Respira = apgar1Respira;
    }

    public void setApgar1Respira(String valor) {
        this.apgar1Respira.setValor(valor);
    }

    public Variable getApgar1Tono() {
        return apgar1Tono;
    }

    public Variable getVariableApgar1Tono() {
        return apgar1Tono;
    }

    public String getApgar1TonoString() {
        return apgar1Tono.getValor();
    }

    public void setApgar1Tono(Variable apgar1Tono) {
        this.apgar1Tono = apgar1Tono;
    }

    public void setApgar1Tono(String valor) {
        this.apgar1Tono.setValor(valor);
    }

    public Variable getApgar1Reflejos() {
        return apgar1Reflejos;
    }

    public Variable getVariableApgar1Reflejos() {
        return apgar1Reflejos;
    }

    public String getApgar1ReflejosString() {
        return apgar1Reflejos.getValor();
    }

    public void setApgar1Reflejos(Variable apgar1Reflejos) {
        this.apgar1Reflejos = apgar1Reflejos;
    }

    public void setApgar1Reflejos(String valor) {
        this.apgar1Reflejos.setValor(valor);
    }

    public Variable getApgar1Color() {
        return apgar1Color;
    }

    public Variable getVariableApgar1Color() {
        return apgar1Color;
    }

    public String getApgar1ColorString() {
        return apgar1Color.getValor();
    }

    public void setApgar1Color(Variable apgar1Color) {
        this.apgar1Color = apgar1Color;
    }

    public void setApgar1Color(String valor) {
        this.apgar1Color.setValor(valor);
    }

    public Variable getApgar1Puntuacion() {
        return apgar1Puntuacion;
    }

    public Variable getVariableApgar1Puntuacion() {
        return apgar1Puntuacion;
    }

    public String getApgar1PuntuacionString() {
        return apgar1Puntuacion.getValor();
    }

    public void setApgar1Puntuacion(Variable apgar1Puntuacion) {
        this.apgar1Puntuacion = apgar1Puntuacion;
    }

    public void setApgar1Puntuacion(String valor) {
        this.apgar1Puntuacion.setValor(valor);
    }

    public Variable getApgar5Fc() {
        return apgar5Fc;
    }

    public Variable getVariableApgar5Fc() {
        return apgar5Fc;
    }

    public String getApgar5FcString() {
        return apgar5Fc.getValor();
    }

    public void setApgar5Fc(Variable apgar5Fc) {
        this.apgar5Fc = apgar5Fc;
    }

    public void setApgar5Fc(String valor) {
        this.apgar5Fc.setValor(valor);
    }

    public Variable getApgar5Respira() {
        return apgar5Respira;
    }

    public Variable getVariableApgar5Respira() {
        return apgar5Respira;
    }

    public String getApgar5RespiraString() {
        return apgar5Respira.getValor();
    }

    public void setApgar5Respira(Variable apgar5Respira) {
        this.apgar5Respira = apgar5Respira;
    }

    public void setApgar5Respira(String valor) {
        this.apgar5Respira.setValor(valor);
    }

    public Variable getApgar5Tono() {
        return apgar5Tono;
    }

    public Variable getVariableApgar5Tono() {
        return apgar5Tono;
    }

    public String getApgar5TonoString() {
        return apgar5Tono.getValor();
    }

    public void setApgar5Tono(Variable apgar5Tono) {
        this.apgar5Tono = apgar5Tono;
    }

    public void setApgar5Tono(String valor) {
        this.apgar5Tono.setValor(valor);
    }

    public Variable getApgar5Reflejos() {
        return apgar5Reflejos;
    }

    public Variable getVariableApgar5Reflejos() {
        return apgar5Reflejos;
    }

    public String getApgar5ReflejosString() {
        return apgar5Reflejos.getValor();
    }

    public void setApgar5Reflejos(Variable apgar5Reflejos) {
        this.apgar5Reflejos = apgar5Reflejos;
    }

    public void setApgar5Reflejos(String valor) {
        this.apgar5Reflejos.setValor(valor);
    }

    public Variable getApgar5Color() {
        return apgar5Color;
    }

    public Variable getVariableApgar5Color() {
        return apgar5Color;
    }

    public String getApgar5ColorString() {
        return apgar5Color.getValor();
    }

    public void setApgar5Color(Variable apgar5Color) {
        this.apgar5Color = apgar5Color;
    }

    public void setApgar5Color(String valor) {
        this.apgar5Color.setValor(valor);
    }

    public Variable getApgar5Puntuacion() {
        return apgar5Puntuacion;
    }

    public Variable getVariableApgar5Puntuacion() {
        return apgar5Puntuacion;
    }

    public String getApgar5PuntuacionString() {
        return apgar5Puntuacion.getValor();
    }

    public void setApgar5Puntuacion(Variable apgar5Puntuacion) {
        this.apgar5Puntuacion = apgar5Puntuacion;
    }

    public void setApgar5Puntuacion(String valor) {
        this.apgar5Puntuacion.setValor(valor);
    }

    public Variable getApgar10Fc() {
        return apgar10Fc;
    }

    public Variable getVariableApgar10Fc() {
        return apgar10Fc;
    }

    public String getApgar10FcString() {
        return apgar10Fc.getValor();
    }

    public void setApgar10Fc(Variable apgar10Fc) {
        this.apgar10Fc = apgar10Fc;
    }

    public void setApgar10Fc(String valor) {
        this.apgar10Fc.setValor(valor);
    }

    public Variable getApgar10Respira() {
        return apgar10Respira;
    }

    public Variable getVariableApgar10Respira() {
        return apgar10Respira;
    }

    public String getApgar10RespiraString() {
        return apgar10Respira.getValor();
    }

    public void setApgar10Respira(Variable apgar10Respira) {
        this.apgar10Respira = apgar10Respira;
    }

    public void setApgar10Respira(String valor) {
        this.apgar10Respira.setValor(valor);
    }

    public Variable getApgar10Tono() {
        return apgar10Tono;
    }

    public Variable getVariableApgar10Tono() {
        return apgar10Tono;
    }

    public String getApgar10TonoString() {
        return apgar10Tono.getValor();
    }

    public void setApgar10Tono(Variable apgar10Tono) {
        this.apgar10Tono = apgar10Tono;
    }

    public void setApgar10Tono(String valor) {
        this.apgar10Tono.setValor(valor);
    }

    public Variable getApgar10Reflejos() {
        return apgar10Reflejos;
    }

    public Variable getVariableApgar10Reflejos() {
        return apgar10Reflejos;
    }

    public String getApgar10ReflejosString() {
        return apgar10Reflejos.getValor();
    }

    public void setApgar10Reflejos(Variable apgar10Reflejos) {
        this.apgar10Reflejos = apgar10Reflejos;
    }

    public void setApgar10Reflejos(String valor) {
        this.apgar10Reflejos.setValor(valor);
    }

    public Variable getApgar10Color() {
        return apgar10Color;
    }

    public Variable getVariableApgar10Color() {
        return apgar10Color;
    }

    public String getApgar10ColorString() {
        return apgar10Color.getValor();
    }

    public void setApgar10Color(Variable apgar10Color) {
        this.apgar10Color = apgar10Color;
    }

    public void setApgar10Color(String valor) {
        this.apgar10Color.setValor(valor);
    }

    public Variable getApgar10Puntuacion() {
        return apgar10Puntuacion;
    }

    public Variable getVariableApgar10Puntuacion() {
        return apgar10Puntuacion;
    }

    public String getApgar10PuntuacionString() {
        return apgar10Puntuacion.getValor();
    }

    public void setApgar10Puntuacion(Variable apgar10Puntuacion) {
        this.apgar10Puntuacion = apgar10Puntuacion;
    }

    public void setApgar10Puntuacion(String valor) {
        this.apgar10Puntuacion.setValor(valor);
    }

    public Variable getCordonArterial() {
        return cordonArterial;
    }

    public Variable getVariableCordonArterial() {
        return cordonArterial;
    }

    public String getCordonArterialString() {
        return cordonArterial.getValor();
    }

    public void setCordonArterial(Variable cordonArterial) {
        this.cordonArterial = cordonArterial;
    }

    public void setCordonArterial(String valor) {
        this.cordonArterial.setValor(valor);
    }

    public Variable getCordonVenoso() {
        return cordonVenoso;
    }

    public Variable getVariableCordonVenoso() {
        return cordonVenoso;
    }

    public String getCordonVenosoString() {
        return cordonVenoso.getValor();
    }

    public void setCordonVenoso(Variable cordonVenoso) {
        this.cordonVenoso = cordonVenoso;
    }

    public void setCordonVenoso(String valor) {
        this.cordonVenoso.setValor(valor);
    }

    public Variable getReanimacion() {
        return reanimacion;
    }

    public Variable getVariableReanimacion() {
        return reanimacion;
    }

    public String getReanimacionString() {
        return reanimacion.getValor();
    }

    public void setReanimacion(Variable reanimacion) {
        this.reanimacion = reanimacion;
    }

    public void setReanimacion(String valor) {
        this.reanimacion.setValor(valor);
    }

    public Variable getPinzaCordon() {
        return pinzaCordon;
    }

    public Variable getVariablePinzaCordon() {
        return pinzaCordon;
    }

    public String getPinzaCordonString() {
        return pinzaCordon.getValor();
    }

    public void setPinzaCordon(Variable pinzaCordon) {
        this.pinzaCordon = pinzaCordon;
    }

    public void setPinzaCordon(String valor) {
        this.pinzaCordon.setValor(valor);
    }

    public Variable getPeso() {
        return peso;
    }

    public Variable getVariabletPeso() {
        return peso;
    }

    public String getPesostring() {
        return peso.getValor();
    }

    public void setPeso(Variable peso) {
        this.peso = peso;
    }

    public void setPeso(String valor) {
        this.peso.setValor(valor);
    }

    public Variable getTalla() {
        return talla;
    }

    public Variable getVariableTalla() {
        return talla;
    }

    public String getTallaString() {
        return talla.getValor();
    }

    public void setTalla(Variable talla) {
        this.talla = talla;
    }

    public void setTalla(String valor) {
        this.talla.setValor(valor);
    }

    public Variable getpCefalico() {
        return pCefalico;
    }

    public Variable getVariablepCefalico() {
        return pCefalico;
    }

    public String getpCefalicoString() {
        return pCefalico.getValor();
    }

    public void setpCefalico(Variable pCefalico) {
        this.pCefalico = pCefalico;
    }

    public void setpCefalico(String valor) {
        this.pCefalico.setValor(valor);
    }

    public Variable getPielConPiel() {
        return pielConPiel;
    }

    public Variable getVariablePielConPiel() {
        return pielConPiel;
    }

    public String getPielConPielSring() {
        return pielConPiel.getValor();
    }

    public void setPielConPiel(Variable pielConPiel) {
        this.pielConPiel = pielConPiel;
    }

    public void setPielConPiel(String valor) {
        this.pielConPiel.setValor(valor);
    }

    public Variable getLactanciaParitorio() {
        return lactanciaParitorio;
    }

    public Variable getVariableLactanciaParitorio() {
        return lactanciaParitorio;
    }

    public String getLactanciaParitorioString() {
        return lactanciaParitorio.getValor();
    }

    public void setLactanciaParitorio(Variable lactanciaParitorio) {
        this.lactanciaParitorio = lactanciaParitorio;
    }

    public void setLactanciaParitorio(String valor) {
        this.lactanciaParitorio.setValor(valor);
    }

    public Variable getProfilaxSBG() {
        return profilaxSBG;
    }

    public Variable getVariableProfilaxSBG() {
        return profilaxSBG;
    }

    public String getProfilaxSBGString() {
        return profilaxSBG.getValor();
    }

    public void setProfilaxSBG(Variable profilaxSBG) {
        this.profilaxSBG = profilaxSBG;
    }

    public void setProfilaxSBG(String valor) {
        this.profilaxSBG.setValor(valor);
    }

    public Variable getMortalidad() {
        return mortalidad;
    }

    public Variable getVariableMortalidad() {
        return mortalidad;
    }

    public String getMortalidadString() {
        return mortalidad.getValor();
    }

    public void setMortalidad(Variable mortalidad) {
        this.mortalidad = mortalidad;
    }

    public void setMortalidad(String valor) {
        this.mortalidad.setValor(valor);
    }

    public Variable getIngresNeonatos() {
        return ingresNeonatos;
    }

    public Variable getVariableIngresNeonatos() {
        return ingresNeonatos;
    }

    public String getIngresNeonatosString() {
        return ingresNeonatos.getValor();
    }

    public void setIngresNeonatos(Variable ingresNeonatos) {
        this.ingresNeonatos = ingresNeonatos;
    }

    public void setIngresNeonatos(String valor) {
        this.ingresNeonatos.setValor(valor);
    }

    public Variable getProfilaxisAntibio() {
        return profilaxisAntibio;
    }

    public Variable getVariableProfilaxisAntibio() {
        return profilaxisAntibio;
    }

    public Boolean getProfilaxisAntibioboolean() {
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
            this.profilaxisAntibio.setValor("Profilaxis antibiótica");
        } else {
            this.profilaxisAntibio.setValor(" ");
        }
    }

    public Variable getProfilaxisAntihemo() {
        return profilaxisAntihemo;
    }

    public Variable getVariableProfilaxisAntihemo() {
        return profilaxisAntihemo;
    }

    public boolean getProfilaxisAntihemoBoolean() {

        if (profilaxisAntihemo != null && !profilaxisAntihemo.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void setProfilaxisAntihemo(Variable profilaxisAntihemo) {
        this.profilaxisAntihemo = profilaxisAntihemo;
    }

    public void setProfilaxisAntihemo(boolean valor) {

        if (valor == true) {
            this.profilaxisAntihemo.setValor("Profilaxis antihemorrágica");
        } else {
            this.profilaxisAntihemo.setValor("");
        }
    }

    public Variable getOdenmedica() {
        return odenmedica;
    }

    public Variable getVariableOdenmedica() {
        return odenmedica;
    }

    public String getOdenmedicaString() {
        return odenmedica.getValor();
    }

    public void setOdenmedica(Variable odenmedica) {
        this.odenmedica = odenmedica;
    }

    public void setOdenmedica(String valor) {
        this.odenmedica.setValor(valor);
    }

    public Variable getProfilaxisOcular() {
        return profilaxisOcular;
    }

    public Variable getVariableProfilaxisOcular() {
        return profilaxisOcular;
    }

    public String getProfilaxisOcularString() {
        return profilaxisOcular.getValor();
    }

    public void setProfilaxisOcular(Variable profilaxisOcular) {
        this.profilaxisOcular = profilaxisOcular;
    }

    public void setProfilaxisOcular(String valor) {
        this.profilaxisOcular.setValor(valor);
    }

    public Variable getCuraCordon() {
        return curaCordon;
    }

    public Variable getVariableCuraCordon() {
        return curaCordon;
    }

    public String getCuraCordonString() {
        return curaCordon.getValor();
    }

    public void setCuraCordon(Variable curaCordon) {
        this.curaCordon = curaCordon;
    }

    public void setCuraCordon(String valor) {
        this.curaCordon.setValor(valor);
    }

    public Variable getPrimerMeconio() {
        return primerMeconio;
    }

    public Variable getVariablePrimerMeconio() {
        return primerMeconio;
    }

    public String getPrimerMeconioString() {
        return primerMeconio.getValor();
    }

    public void setPrimerMeconio(Variable primerMeconio) {
        this.primerMeconio = primerMeconio;
    }

    public void setPrimerMeconio(String valor) {
        this.primerMeconio.setValor(valor);
    }

    public Variable getPrimeraMiccion() {
        return primeraMiccion;
    }

    public Variable getVariablePrimeraMiccion() {
        return primeraMiccion;
    }

    public String getPrimeraMiccionString() {
        return primeraMiccion.getValor();
    }

    public void setPrimeraMiccion(Variable primeraMiccion) {
        this.primeraMiccion = primeraMiccion;
    }

    public void setPrimeraMiccion(String valor) {
        this.primeraMiccion.setValor(valor);
    }

    public Variable getMadrenhc() {
        return madrenhc;
    }

    public void setMadrenhc(Variable madrenhc) {
        this.madrenhc = madrenhc;
    }
    public void setMadrenhc(String valor) {
        this.madrenhc.setValor(valor);
    }
}
