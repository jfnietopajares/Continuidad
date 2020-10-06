package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;



/**
 * The Class Mensaje.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Mensaje {

    private int numeroOrden;
    private Long id;
    private Long fecha;
    private Integer hora;
    private MensajesTipos tipo;
    private String contenido;
    private MensajesEstados estado;
    private String userid_origen;
    private String userid_destino;
    private Paciente paciente;
    private Long fecha_proceso;
    private Long hora_proceso;
    private String error;

    public final static MensajesEstados MENSAJE_PENDIENTE_ENVIO = new MensajesEstados(1, "Pendiente");
    public final static MensajesEstados MENSAJE__ENVIADO = new MensajesEstados(2, "Enviado");
    public final static MensajesEstados MENSAJE_ERROR_ENVIO = new MensajesEstados(3, "Error");

    public final static MensajesTipos MENSAJE_TIPO_MAIL = new MensajesTipos(1, "Mail");
    public final static MensajesTipos MENSAJE_TIPO_SMS = new MensajesTipos(2, "SMS");
    public final static MensajesTipos MENSAJE_TIPO_WHASTASP = new MensajesTipos(3, "WHASTASP");
    public final static MensajesTipos MENSAJE_TIPO_PANTALLA = new MensajesTipos(4, "Pantalla");

    /**
     * Instantiates a new mensaje.
     */
    public Mensaje() {
        this.id = new Long(0);
        this.fecha = Utilidades.getFechaYYYYmmdd(LocalDate.now());
        this.hora = Utilidades.getHoraActualHHmm();
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public String getIdString() {
        return Long.toString(id);
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the fecha.
     *
     * @return the fecha
     */
    public Long getFecha() {
        return fecha;
    }

    /**
     * Sets the fecha.
     *
     * @param fecha the new fecha
     */
    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets the hora.
     *
     * @return the hora
     */
    public Integer getHora() {
        return hora;
    }

    /**
     * Sets the hora.
     *
     * @param hora the new hora
     */
    public void setHora(Integer hora) {
        this.hora = hora;
    }

    /**
     * Gets the contenido.
     *
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    public String getContenidoFormato() {
        return contenido.replaceAll("\r\n|\n", "<br>");
    }

    public String getContenidoCorto() {
        if (contenido.length() > 50) {
            return contenido.substring(0, 50).concat("...");
        } else {
            return contenido;
        }
    }

    /**
     * Sets the contenido.
     *
     * @param contenido the new contenido
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public MensajesTipos getTipo() {
        return tipo;
    }

    public int getTipoInt() {
        return tipo.getTipo();
    }

    public String getTipoString() {
        if (tipo.getTipo() == Mensaje.MENSAJE_TIPO_MAIL.getTipo()) {
            return "Mail";
        } else if (tipo.getTipo() == Mensaje.MENSAJE_TIPO_SMS.getTipo()) {
            return "Sms";
        } else if (tipo.getTipo() == Mensaje.MENSAJE_TIPO_WHASTASP.getTipo()) {
            return "Whastapp";
        } else if (tipo.getTipo() == Mensaje.MENSAJE_TIPO_PANTALLA.getTipo()) {
            return "Aviso pantalla";
        } else {
            return "Desconocido";
        }
    }

    public void setTipo(MensajesTipos tipo) {
        this.tipo = tipo;
    }

    public MensajesEstados getEstado() {
        return estado;
    }

    public int getEstadoInt() {
        return estado.getEstado();
    }

    public String getEstadoString() {
        if (estado.getEstado() == Mensaje.MENSAJE__ENVIADO.getEstado()) {
            return "Enviado";
        } else if (estado.getEstado() == Mensaje.MENSAJE_PENDIENTE_ENVIO.getEstado()) {
            return "Pendiente";
        } else if (estado.getEstado() == Mensaje.MENSAJE_ERROR_ENVIO.getEstado()) {
            return "Error";
        } else {
            return "Desconocido";
        }

    }

    public void setEstado(MensajesEstados estado) {
        this.estado = estado;
    }

    /**
     * Gets the userid origen.
     *
     * @return the userid origen
     */
    public String getUserid_origen() {
        return userid_origen;
    }

    /**
     * Sets the userid origen.
     *
     * @param userid_origen the new userid origen
     */
    public void setUserid_origen(String userid_origen) {
        this.userid_origen = userid_origen;
    }

    /**
     * Gets the userid destino.
     *
     * @return the userid destino
     */
    public String getUserid_destino() {
        return userid_destino;
    }

    public String getUserid_destinoCorto() {
        if (userid_destino.length() > 15) {
            return userid_destino.substring(0, 15).concat("...");
        } else {
            return userid_destino;
        }
    }

    /**
     * Sets the userid destino.
     *
     * @param userid_destino the new userid destino
     */
    public void setUserid_destino(String userid_destino) {
        this.userid_destino = userid_destino;
    }

    /**
     * Gets the paciente.
     *
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }

    public Long getPacienteId() {
        return paciente.getId();
    }

    public String getPacienteNhc() {
        return paciente.getNumerohc();
    }

    public String getPacienteNombre() {
        if (paciente != null) {
            return paciente.getApellidosNombre();
        } else {
            return "";
        }
    }

    /**
     * Sets the paciente.
     *
     * @param paciente the new paciente
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Gets the fecha proceso.
     *
     * @return the fecha proceso
     */
    public Long getFecha_proceso() {
        return fecha_proceso;
    }

    /**
     * Sets the fecha proceso.
     *
     * @param fecha_proceso the new fecha proceso
     */
    public void setFecha_proceso(Long fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    /**
     * Gets the hora proceso.
     *
     * @return the hora proceso
     */
    public Long getHora_proceso() {
        return hora_proceso;
    }

    /**
     * Sets the hora proceso.
     *
     * @param hora_proceso the new hora proceso
     */
    public void setHora_proceso(Long hora_proceso) {
        this.hora_proceso = hora_proceso;
    }

    /**
     * Gets the error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the new error
     */
    public void setError(String error) {
        this.error = error;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getFechaHora() {
        String feString = "";
        if (fecha != null) {
            if (!fecha.equals(new Long(0))) {
                feString = Utilidades.getFechFormatoaddmmyyyy(fecha, Constantes.SEPARADOR_FECHA);
                if (hora != null) {
                    if (hora.equals(new Long(0))) {
                        feString = feString + " " + Utilidades.getHoraHH_MM(hora);
                    }
                }
            }
        }
        return feString;
    }

    public String getFechaHoraProceso() {
        String feString = "";
        if (fecha_proceso != null) {
            if (!fecha_proceso.equals(new Long(0))) {
                feString = Utilidades.getFechFormatoaddmmyyyy(fecha_proceso, Constantes.SEPARADOR_FECHA);
                if (hora_proceso != null) {
                    if (hora_proceso.equals(new Long(0))) {
                        feString = feString + " " + Utilidades.getHoraHH_MM(hora_proceso);
                    }
                }
            }
        }
        return feString;
    }

    public String toString() {
        String textoString = "\n ------------------------------------";
        textoString = textoString.concat("\n Id:" + this.getId());
        textoString = textoString.concat("\n Fecha:" + this.getFechaHora());
        textoString = textoString.concat("\n Tipo:" + this.getTipoString());
        textoString = textoString.concat("\n Contenido:" + this.getContenido());
        textoString = textoString.concat("\n Estado:" + this.getEstadoString());
        textoString = textoString.concat("\n U.Origen:" + this.getUserid_origen());
        textoString = textoString.concat("\n U.Destino:" + this.getUserid_destino());
        textoString = textoString.concat("\n Paciente:" + this.getPacienteNombre());
        textoString = textoString.concat("\n F.Proceso:" + this.getFechaHoraProceso());
        textoString = textoString.concat("\n Error:" + this.getError());
        return textoString;

    }

    public String getHtml() {
        String textoString = "<table border=\"1\">";
        textoString = textoString.concat("<tr><td> Id</td><td>" + this.getId() + "</td></tr>");
        textoString = textoString.concat("<tr><td>Fecha</td><td>" + this.getFechaHora() + "</td></tr>");
        textoString = textoString.concat("<tr><td>Tipo</td><td>" + this.getTipoString() + "</td></tr>");
        textoString = textoString.concat(
                "<tr><td>Contenido</td><td>" + this.getContenido().replaceAll("(\r\n|\n)", "<br />") + "</td></tr>");
        textoString = textoString.concat("<tr><td>Estado</td><td>" + this.getEstadoString() + "</td></tr>");
        textoString = textoString.concat("<tr><td>U.Origen</td><td>" + this.getUserid_origen() + "</td></tr>");
        textoString = textoString.concat("<tr><td>U.Destino</td><td>" + this.getUserid_destino() + "</td></tr>");
        textoString = textoString.concat("<tr><td>Paciente</td><td>" + this.getPacienteNombre() + "</td></tr>");
        textoString = textoString.concat("<tr><td>F.Proceso</td><td>" + this.getFechaHoraProceso() + "</td></tr>");
        textoString = textoString.concat("<tr><td>Error</td><td>" + this.getError() + "</td></tr>");
        textoString = textoString.concat("</table>");
        return textoString;

    }
}
