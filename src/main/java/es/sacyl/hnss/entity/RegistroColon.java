package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;



/**
 * The Class RegistroColon.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroColon extends Registro {

    protected Variable analitica = new Variable();

    protected Variable resultado = new Variable();

    protected Variable citaColono = new Variable();

    protected Variable informeColono = new Variable();

    protected Variable informeAnato = new Variable();

    protected Variable idColono = new Variable();

    protected Variable idAnato = new Variable();

    protected Variable observaciones = new Variable();

    public final static Long PLANTILLLA_EDITOR_REGISTROCOLON = new Long(11111);
    public final static Long TIPO_REGISTRO_COLON = new Long(61);

    public final Variable VAR_COLON_FECHAANALITICA = new Variable("13992305", "99G2", new Long(13992305),
            "Fecha Prueba Analitica.");

    public final Variable VAR_COLON_RESULTADO = new Variable("58453-2", "LN", new Long(50002423), "Resultado");

    public final Variable VAR_COLON_FECHACOLONOCITA = new Variable("444783004", "SNM3", new Long(214030193),
            "Fecha Cita colonoscopia");

    public final Variable VAR_COLON_FECHACOLONOINFORME = new Variable("35000263", "99G2", new Long(35000263),
            "Fecha Informe Colonoscopia");

    public final Variable VAR_COLON_IDINFORMECOLONO = new Variable("45.23", "I9C", new Long(101841),
            "Id Informe Colonoscopia");

    public final Variable VAR_COLON_FECHACAANTOMIA = new Variable("13992316", "99G2", new Long(13992316),
            "Fecha Informe Anatomia");

    public final Variable VAR_COLON_IDINFORMEANATO = new Variable("43427-4", "LN", new Long(1505),
            "Id Informe Anatomía");

    public final Variable VAR_COLON_OBSERVACIONES = new Variable("COM", "99G2", new Long(34429773), "Comentario");

    public RegistroColon() {
        super();
        this.iniciaColon();
    }

    /**
     * Instantiates a new registro colon.
     *
     * @param id the id
     */
    public RegistroColon(Long id) {
        super(id);
        this.iniciaColon();
    }

    public RegistroColon(RegistroColon rc) {
        super(rc);
        this.id = rc.getId();
        this.analitica = rc.getVariableAnalitica();
        this.resultado = rc.getVariableResultado();
        this.citaColono = rc.getVariableCitaColono();
        this.informeColono = rc.getVariableinformeColono();
        this.informeAnato = rc.getVariableInformeAnato();
        this.idColono = rc.getVariableIdColono();
        this.idAnato = rc.getVariableIdAnato();
        this.observaciones = rc.getVariableObservaciones();
    }

    /**
     * Inicia.
     */
    public void iniciaColon() {
        this.setTiporegistro(RegistroColon.TIPO_REGISTRO_COLON);
        this.setDescripcion("Registro screenig colon");
        this.setPlantilla_edior(RegistroColon.PLANTILLLA_EDITOR_REGISTROCOLON);
        this.setServicio(new Servicio(new Long(21)));

        this.analitica = this.VAR_COLON_FECHAANALITICA;
        this.resultado = this.VAR_COLON_RESULTADO;
        this.citaColono = this.VAR_COLON_FECHACOLONOCITA;
        this.informeColono = this.VAR_COLON_FECHACOLONOINFORME;
        this.informeAnato = this.VAR_COLON_FECHACAANTOMIA;
        this.idColono = this.VAR_COLON_IDINFORMECOLONO;
        this.idAnato = this.VAR_COLON_IDINFORMEANATO;
        this.observaciones = this.VAR_COLON_OBSERVACIONES;
        this.idAnato.setValor("0");
        this.idColono.setValor("0");

    }

    public Variable getVariableAnalitica() {
        return analitica;
    }

    public String getAnaliticaString() {
        return analitica.getValor();
    }

    public LocalDate getAnaliticaDate() {
        LocalDate fecha = null;
        if (analitica.getValor() != null) {
            if (Utilidades.isNumero(analitica.getValor())) {
                fecha = Utilidades.getFechaLocalDate(Long.parseLong(analitica.getValor()));
            }
        }
        return fecha;
    }

    public void setAnalitica(Variable analitica) {
        this.analitica = analitica;
    }

    public void setAnalitica(LocalDate fecha) {
        analitica.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getVariableResultado() {
        return resultado;
    }

    public void setResultado(Variable resultado) {
        this.resultado = resultado;
    }

    public String getResultadoString() {
        return resultado.getValor();
    }

    public void setResultado(String resultado) {
        this.resultado.setValor(resultado);
    }

    public Variable getVariableCitaColono() {
        return citaColono;
    }

    public LocalDate getCitaColonoDate() {
        LocalDate fecha = null;
        if (citaColono.getValor() != null) {
            if (Utilidades.isNumero(citaColono.getValor())) {
                fecha = Utilidades.getFechaLocalDate(Long.parseLong(citaColono.getValor()));
            }
        }
        return fecha;
    }

    public void setCitaColono(Variable citaColono) {
        this.citaColono = citaColono;
    }

    public void setCitaColono(LocalDate fecha) {
        citaColono.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getVariableinformeColono() {
        return informeColono;
    }

    public String getInformeColonoString() {
        return informeColono.getValor();
    }

    public LocalDate getInformeColonoDate() {
        LocalDate fecha = null;
        if (informeColono != null) {
            if (informeColono.getValor() != null) {
                if (Utilidades.isNumero(informeColono.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(informeColono.getValor()));
                }
            }
        }
        return fecha;
    }

    public void setInformeColono(Variable informeColono) {
        this.informeColono = informeColono;
    }

    public void setInformeColono(String valor) {
        this.informeColono.setValor(valor);
    }

    public void setInformeColono(LocalDate fecha) {
        informeColono.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public void setInformeAnato(Variable informeAnato) {
        this.informeAnato = informeAnato;
    }

    public void setInformeAnato(LocalDate fecha) {
        informeAnato.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getVariableInformeAnato() {
        return informeAnato;
    }

    public LocalDate getInformeAnatoDate() {
        LocalDate fecha = null;
        if (informeAnato.getValor() != null) {
            if (Utilidades.isNumero(informeAnato.getValor())) {
                fecha = Utilidades.getFechaLocalDate(Long.parseLong(informeAnato.getValor()));
            }
        }
        return fecha;
    }

    public Variable getVariableIdColono() {
        return this.idColono;
    }

    public String getIdColonoVarlor() {
        return idColono.getValor();
    }

    public void setIdColono(Variable idColono) {
        this.idColono = idColono;
    }

    public void setIdColono(String valor) {
        this.idColono.setValor(valor);
    }

    public void setIdColono(Long valor) {
        this.idColono.setValor(valor.toString());
    }

    public Long getIdColonLong() {
        if (Utilidades.isNumeric(idColono.getValor())) {
            return Long.parseLong(idColono.getValor());
        } else {
            return null;
        }
    }

    public Variable getVariableIdColon() {
        return idColono;
    }

    public Variable getVariableIdAnato() {
        return idAnato;
    }

    public String getIdAnatoValor() {
        return idAnato.getValor();
    }

    public Long getIdAnatoLong() {
        return Long.parseLong(idAnato.getValor());
    }

    public void setIdAnato(Variable idAnato) {
        this.idAnato = idAnato;
    }

    public void setIdAnato(String valor) {
        this.idAnato.setValor(valor);
    }

    public void setIdAnato(Long valor) {

        this.idAnato.setValor(valor.toString());
    }

    public Variable getVariableObservaciones() {
        return observaciones;
    }

    public String getObservacionesValor() {
        return observaciones.getValor();
    }

    public void setObservaciones(Variable observaciones) {
        this.observaciones = observaciones;
    }

    public void setObservaciones(String valor) {
        this.observaciones.setValor(valor);
        ;
    }

    public String toStringRegistro() {
        return "\n ------------------------------------ \n" + "\n id:" + this.getId() + "\n F.Analítica: "
                + getAnaliticaString().toString() + "\n Resultado:" + getResultadoString() + "\n F.CitaColono: "
                + getCitaColonoDate().toString() + "\n F.InfColono: " + getInformeColonoString().toString()
                + "\n F.InfAnatomia:" + getInformeAnatoDate().toString() + "\n Observacioens:"
                + getObservacionesValor().toString() + "\n ------------------------------------ \n";

    }

}
