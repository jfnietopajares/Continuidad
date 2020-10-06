package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;


/**
 * The Class ResultadoTsoh. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class ResultadoTsoh {

    private int numeroOrden;

    private Long fecha;

    private Long id;

    private Paciente paciente = new Paciente();

    private String resultado;

    private String comentario;

    public ResultadoTsoh() {
        this.id = new Long(0);
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFechaDate() {
        return Utilidades.getFechaLocalDate(fecha);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getNombrePaciente() {
        return paciente.getApellidosNombre();
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
