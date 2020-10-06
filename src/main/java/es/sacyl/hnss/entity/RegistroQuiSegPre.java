package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;


public class RegistroQuiSegPre extends Registro {

    private Variable fechaCheck;
    private Variable horaCheck;
    private Variable auxiliar;
    private Variable aspirador;
    private Variable bolsapresion;
    private Variable aparatosred;
    private Variable intubacion;
    private Variable medicacion;

    public final static Long PLANTILLLA_EDITOR_QUISEGPRE = new Long(600974618);
    public final static Long TIPO_REGISTRO_QUI_SEG = new Long(50);

    public final Variable VAR_QUISEG_PREQUI_FECHA = new Variable("410671006", "SNM3", new Long(13822264),
            "Fecha Check ", "Fecha Check ", Variable.TIPO_VARIABLE_DATE);
    public final Variable VAR_QUISEG_PREQUI_HORA = new Variable("258702006", "SNM3", new Long(13822866), "Hora Check ",
            "Hora Check ", Variable.TIPO_VARIABLE_STRING);
    public final Variable VAR_QUISEG_PREQUI_AUXILIAR_ = new Variable("5275007", "SNM3", new Long(13825744),
            "Auxiliar de enfermería", "Auxiliar de enfermería", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PREQUI_ASPIRADOR = new Variable("13818044", "99G2", new Long(13818044),
            "Equipo aspirador de secreciones: Sonda, Yankauer ", "Equipo aspirador de secreciones: Sonda, Yankauer ",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PREQUI_BOLSAPRE = new Variable("13991624", "99G2", new Long(13991624),
            "Bolsa de presión", "Bolsa de presión", Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PREQUI_APARED = new Variable("13991625", "99G2", new Long(13991625),
            "Aparatos con Conexión a Red", "Bisturí Eléctrico, Bomba Persfusión, Calentador Fluidos, Manta Térmica",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PREQUI_INTUBA = new Variable("129339002", "SNM3", new Long(13818636), "Intubación",
            "Chequeo Respirador, Caudalimetro, Ambu, Guedel, Laringo las dos palas con luz,   Tubos Endotraqueales, Mascarillas Laringeas, Airtrack, Fiador, Intercambiador, Pinza de Magill, Fonendo,      Jeringa 20, Extractor de Gases",
            Variable.TIPO_VARIABLE_CHECK);
    public final Variable VAR_QUISEG_PREQUI_MEDICA = new Variable("260779000", "SNM3", new Long(13825604), "Medicación",
            "Termolabil en Nevera, Opiaceos", Variable.TIPO_VARIABLE_CHECK);

    public RegistroQuiSegPre() {
        super();
        iniciaQuiSeg();
    }

    public RegistroQuiSegPre(Long id) {
        super(id);
        iniciaQuiSeg();
    }

    public RegistroQuiSegPre(RegistroQuiSegPre r) {
        super(r);
        this.fechaCheck = r.getFechaCheck();
        this.horaCheck = r.getHoraCheck();
        this.auxiliar = r.getAuxiliar();
        this.aspirador = r.getAspirador();
        this.bolsapresion = r.getBolsapresion();
        this.aparatosred = r.getAparatosred();
        this.intubacion = r.getIntubacion();
        this.medicacion = r.getMedicacion();
    }

    public void iniciaQuiSeg() {
        this.setTiporegistro(TIPO_REGISTRO_QUI_SEG);
        this.setDescripcion("1.CheckList prequirófano ");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_QUISEGPRE);
        // this.setServicio(Servicio.SERVICIO_ONCOLOGIA);
        this.fechaCheck = VAR_QUISEG_PREQUI_FECHA;
        this.horaCheck = VAR_QUISEG_PREQUI_HORA;
        this.auxiliar = VAR_QUISEG_PREQUI_AUXILIAR_;
        this.aspirador = VAR_QUISEG_PREQUI_ASPIRADOR;
        this.bolsapresion = VAR_QUISEG_PREQUI_BOLSAPRE;
        this.aparatosred = VAR_QUISEG_PREQUI_APARED;
        this.intubacion = VAR_QUISEG_PREQUI_INTUBA;
        this.medicacion = VAR_QUISEG_PREQUI_MEDICA;
    }

    public Variable getFechaCheck() {
        return fechaCheck;
    }

    public Variable getVariableFechaCheck() {
        return fechaCheck;
    }

    public LocalDate getFechaCheckDate() {
        LocalDate fechaDate = null;
        if (fechaCheck != null && fechaCheck.getValor() != null && !fechaCheck.getValor().isEmpty()) {
            if (Utilidades.isNumeric(fechaCheck.getValor())) {
                fechaDate = Utilidades.getFechaLocalDate(fechaCheck.getValor());
            }
        }
        return fechaDate;
    }

    public void setFechaCheck(Variable fechaCheck) {
        this.fechaCheck = fechaCheck;
    }

    public void setFechaCheck(LocalDate fecha) {
        this.fechaCheck.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getHoraCheck() {
        return horaCheck;
    }

    public Variable getVariableHoraCheck() {
        return horaCheck;
    }

    public String getHoraCheckString() {
        return horaCheck.getValor();
    }

    public void setHoraCheck(Variable horaCheck) {
        this.horaCheck = horaCheck;
    }

    public void setHoraCheck(String valor) {
        this.horaCheck.setValor(valor);
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

    public Variable getAspirador() {
        return aspirador;
    }

    public Variable getVariableAspirador() {
        return aspirador;
    }

    public String getAspiradorString() {
        return aspirador.getValor();
    }

    public Boolean getAspiradorBoolean() {
        if (aspirador != null && !aspirador.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAspirador(Variable aspirador) {
        this.aspirador = aspirador;
    }

    public void setAspirador(Boolean valor) {
        if (valor == true) {
            this.aspirador.setValor("Aspirador: Sonda, Yankauer");
        } else {
            this.aspirador.setValor("");
        }
    }

    public Variable getBolsapresion() {
        return bolsapresion;
    }

    public Variable getVariableBolsapresion() {
        return bolsapresion;
    }

    public Boolean getBolsapresionBoolean() {
        if (bolsapresion != null && !bolsapresion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setBolsapresion(Variable bolsapresion) {
        this.bolsapresion = bolsapresion;
    }

    public void setBolsapresion(Boolean valor) {
        if (valor == true) {
            this.bolsapresion.setValor("Bolsa de Presión");
        } else {
            this.bolsapresion.setValor("");
        }
    }

    public Variable getAparatosred() {
        return aparatosred;
    }

    public Variable getVariableAparatosred() {
        return aparatosred;
    }

    public Boolean getAparatosredBoolean() {
        if (aparatosred != null && !aparatosred.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAparatosred(Variable aparatosred) {
        this.aparatosred = aparatosred;
    }

    public void setAparatosred(Boolean valor) {
        if (valor == true) {
            this.aparatosred.setValor(
                    "Aparatos con Conexión a Red: Bisturí Eléctrico, Bomba Persfusión, Calentador Fluidos, Manta Térmica");
        } else {
            this.aparatosred.setValor("");
        }
    }

    public Variable getIntubacion() {
        return intubacion;
    }

    public Variable getVariableIntubacion() {
        return intubacion;
    }

    public Boolean getIntubacionBoolean() {
        if (intubacion != null && !intubacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setIntubacion(Variable intubacion) {
        this.intubacion = intubacion;
    }

    public void setIntubacion(Boolean valor) {
        if (valor == true) {
            this.intubacion.setValor(
                    "Intubación:Chequeo Respirador, Caudalimetro, Ambu, Guedel, Laringo las dos palas con luz,"
                    + "                               Tubos Endotraqueales, Mascarillas Laringeas, Airtrack, Fiador, Intercambiador, Pinza de Magill, Fonendo,"
                    + "                               Jeringa 20, Extractor de Gases");
        } else {
            this.intubacion.setValor("");
        }
    }

    public Variable getMedicacion() {
        return medicacion;
    }

    public Variable getVariableMedicacion() {
        return medicacion;
    }

    public Boolean getMedicacionBoolean() {
        if (medicacion != null && !medicacion.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMedicacion(Variable medicacion) {
        this.medicacion = medicacion;
    }

    public void setMedicacion(Boolean valor) {
        if (valor == true) {
            this.medicacion.setValor("Medicación:Termolabil en Nevera, Opiaceos");
        } else {
            this.medicacion.setValor("");
        }
    }
}
