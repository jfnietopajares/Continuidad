package es.sacyl.hnss.entity;

import java.time.LocalDate;


import com.vaadin.server.Page;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;

/**
 * The Class Acceso.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Acceso {

    private int numeroOrden;

    private Long id;

    private Long fecha;

    private Integer hora;

    private Usuario userid;

    private String nombreUsuario;

    private String nombrePaciente;

    private String nhc;

    private AccesoTipos tipo;

    private Long epiinfo;

    private Paciente paciente;

    private String motivo;

    private String ip;

    private Long canal;

    public final static AccesoTipos ACCESO_USUARIO_NO_ENCONTRADO = new AccesoTipos(1, "Usuario no encontrado");

    public final static AccesoTipos ACCESO_USUARIO_DE_BAJA = new AccesoTipos(2, "Usuario de baja");

    public final static AccesoTipos ACCESO_USUARIO_CLAVE_INCORRECTA = new AccesoTipos(3, "Clave incorrecta");

    public final static AccesoTipos ACCESO_LOGIN = new AccesoTipos(4, "Login correcto");

    public final static AccesoTipos ACCESO_LOGOUT = new AccesoTipos(5, "Fin de sesión");

    public final static AccesoTipos ACCESO_PACIENTE = new AccesoTipos(6, "Consulta paciente");

    public final static AccesoTipos ACCESO_REGISTRO = new AccesoTipos(7, "Consulta registro");

    public final static AccesoTipos ACCESO_INFORME = new AccesoTipos(8, "Consulta informe");

    public final static AccesoTipos ACCESO_PROCESO = new AccesoTipos(9, "Consulta proceso");

    public final static AccesoTipos ACCESO_SERVICO_RES = new AccesoTipos(10, "Consulta servicio REST");

    /**
     * Instantiates a new acceso.
     */
    public Acceso() {
        this.id = new Long(0);
        this.setFecha(Utilidades.getFechaActualYYYYmmdd());
        this.setHora(Utilidades.getHoraActualHHmm());
        if (Page.getCurrent() != null) {
            this.setIp(Page.getCurrent().getWebBrowser().getAddress());
        } else {
            this.setIp("?");
        }
        this.setCanal(Registro.CANAL_DEFECTO);
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
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
     * Gets the userid.
     *
     * @return the userid
     */
    public Usuario getUserid() {
        return userid;
    }

    public String getUseridString() {
        if (userid != null) {
            return userid.getApellidosNombre();
        } else {
            return null;
        }
    }

    /**
     * Sets the userid.
     *
     * @param userid the new userid
     */
    public void setUserid(Usuario userid) {
        this.userid = userid;
    }

    public AccesoTipos getTipo() {
        return tipo;
    }

    public void setTipo(AccesoTipos tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets the epiinfo.
     *
     * @return the epiinfo
     */
    public Long getEpiinfo() {
        return epiinfo;
    }

    /**
     * Sets the epiinfo.
     *
     * @param epiinfo the new epiinfo
     */
    public void setEpiinfo(Long epiinfo) {
        this.epiinfo = epiinfo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getPacienteString() {
        if (paciente != null) {
            if (!paciente.getId().equals(new Long(0))) {
                return paciente.getApellidosNombre();
            }
        }
        return "";

    }

    /**
     * Gets the motivo.
     *
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the motivo.
     *
     * @param motivo the new motivo
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Gets the ip.
     *
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the ip.
     *
     * @param ip the new ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Gets the canal.
     *
     * @return the canal
     */
    public Long getCanal() {
        return canal;
    }

    /**
     * Sets the canal.
     *
     * @param canal the new canal
     */
    public void setCanal(Long canal) {
        this.canal = canal;
    }

    public String getFechaHora() {
        String feString = "";
        if (fecha != null) {
            if (!fecha.equals(new Long(0))) {
                feString = Utilidades.getFechFormatoaddmmyyyy(fecha, Constantes.SEPARADOR_FECHA);
                if (hora != null) {
                    if (!hora.equals(new Long(0)) && hora != null) {
                        feString = feString + " " + Utilidades.getHoraHH_MM(hora);
                    }
                }
            }
        }
        return feString;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNhc() {
        return nhc;
    }

    public void setNhc(String nhc) {
        this.nhc = nhc;
    }

    public String getTipoString() {
        return tipo.getDescripcion();

    }

    public String getHtml() {
        String textoString = "<table border=\"1\">";
        textoString = textoString.concat("<tr><td> Id</td><td>" + this.getId() + "</td></tr>");
        textoString = textoString.concat("<tr><td>Fecha</td><td>" + this.getFechaHora() + "</td></tr>");
        textoString = textoString.concat("<tr><td>Tipo</td><td>" + this.getTipoString() + "</td></tr>");
        textoString = textoString.concat("<tr><td>Usuario</td><td>" + this.getNombreUsuario() + "</td></tr>");
        if (paciente != null) {
            textoString = textoString.concat("<tr><td>Paciente</td><td>" + this.getNombrePaciente() + "</td></tr>");
        } else {
            textoString = textoString.concat("<tr><td>Paciente</td><td></td></tr>");
        }
        textoString = textoString.concat("<tr><td>Motivo</td><td>" + this.getMotivo() + "</td></tr>");
        textoString = textoString.concat("<tr><td>IP</td><td>" + this.getIp() + "</td></tr>");
        textoString = textoString.concat("</table>");
        return textoString;

    }
}
