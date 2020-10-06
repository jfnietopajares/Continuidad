package es.sacyl.hnss.entity;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Class Peticion. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Peticion {

    private int numeroOrden;

    private Long id;

    private Usuario userid;

    private Centro centro;

    private Servicio servicio;

    private Paciente paciente;

    private Long episodio;

    private String motivo;

    private int estado;

    private int urgente;

    private Long volante;

    private LocalDate fecha;

    private Long hora;

    private String numeromuestra;

    private LocalDate fechaprogramacion;

    private Long subservicio;

    private Long pertenece;

    private String motivo_cancelacion;

    private LocalDate fecha_muestra;

    private Long hora_muestra;

    private LocalDate fecha_envia;

    private Long hora_envio;

    private ArrayList<Variable> listaVariables = null;
    // public final static String VOLANTE_PETICION_APNEA =
    // "(588930825,588936629,588938432,588940952,588941950,588943536,592333932,601346003)";

    public final static String PLANTILLAS_PETICIONES_ALTAS = "588930825,588936629,588938432,588940952,588941950,588943536,592333932,601346003";

    public final static String PLANTILLAS_PETICIONES_OXIGENO = "588930825,601346003";

    public final static int ESTADO_PETICIONES_PENDIENTES = 5;

    public final static int ESTADO_PETICIONES_PROCESADAS = 99;

    public Peticion() {
        id = new Long(0);
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
     * Gets the userid.
     *
     * @return the userid
     */
    public Usuario getUserid() {
        return userid;
    }

    /**
     * Sets the userid.
     *
     * @param userid the new userid
     */
    public void setUserid(Usuario userid) {
        this.userid = userid;
    }

    /**
     * Gets the centro.
     *
     * @return the centro
     */
    public Centro getCentro() {
        return centro;
    }

    /**
     * Sets the centro.
     *
     * @param centro the new centro
     */
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    /**
     * Gets the servicio.
     *
     * @return the servicio
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * Sets the servicio.
     *
     * @param servicio the new servicio
     */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    /**
     * Gets the paciente.
     *
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
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
     * Sets the fecha.
     *
     * @param fecha the new fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Sets the fecha muestra.
     *
     * @param fecha_muestra the new fecha muestra
     */
    public void setFecha_muestra(LocalDate fecha_muestra) {
        this.fecha_muestra = fecha_muestra;
    }

    /**
     * Sets the fecha envia.
     *
     * @param fecha_envia the new fecha envia
     */
    public void setFecha_envia(LocalDate fecha_envia) {
        this.fecha_envia = fecha_envia;
    }

    /**
     * Gets the episodio.
     *
     * @return the episodio
     */
    public Long getEpisodio() {
        return episodio;
    }

    /**
     * Sets the episodio.
     *
     * @param episodio the new episodio
     */
    public void setEpisodio(Long episodio) {
        this.episodio = episodio;
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
     * Gets the estado.
     *
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Gets the urgente.
     *
     * @return the urgente
     */
    public int getUrgente() {
        return urgente;
    }

    /**
     * Sets the urgente.
     *
     * @param urgente the new urgente
     */
    public void setUrgente(int urgente) {
        this.urgente = urgente;
    }

    /**
     * Gets the volante.
     *
     * @return the volante
     */
    public Long getVolante() {
        return volante;
    }

    /**
     * Sets the volante.
     *
     * @param volante the new volante
     */
    public void setVolante(Long volante) {
        this.volante = volante;
    }

    /**
     * Gets the fecha.
     *
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Gets the hora.
     *
     * @return the hora
     */
    public Long getHora() {
        return hora;
    }

    /**
     * Sets the hora.
     *
     * @param hora the new hora
     */
    public void setHora(Long hora) {
        this.hora = hora;
    }

    /**
     * Gets the numeromuestra.
     *
     * @return the numeromuestra
     */
    public String getNumeromuestra() {
        return numeromuestra;
    }

    /**
     * Sets the numeromuestra.
     *
     * @param numeromuestra the new numeromuestra
     */
    public void setNumeromuestra(String numeromuestra) {
        this.numeromuestra = numeromuestra;
    }

    /**
     * Gets the fechaprogramacion.
     *
     * @return the fechaprogramacion
     */
    public LocalDate getFechaprogramacion() {
        return fechaprogramacion;
    }

    /**
     * Sets the fechaprogramacion.
     *
     * @param fechaprogramacion the new fechaprogramacion
     */
    public void setFechaprogramacion(LocalDate fechaprogramacion) {
        this.fechaprogramacion = fechaprogramacion;
    }

    /**
     * Gets the subservicio.
     *
     * @return the subservicio
     */
    public Long getSubservicio() {
        return subservicio;
    }

    /**
     * Sets the subservicio.
     *
     * @param subservicio the new subservicio
     */
    public void setSubservicio(Long subservicio) {
        this.subservicio = subservicio;
    }

    /**
     * Gets the pertenece.
     *
     * @return the pertenece
     */
    public Long getPertenece() {
        return pertenece;
    }

    /**
     * Sets the pertenece.
     *
     * @param pertenece the new pertenece
     */
    public void setPertenece(Long pertenece) {
        this.pertenece = pertenece;
    }

    /**
     * Gets the motivo cancelacion.
     *
     * @return the motivo cancelacion
     */
    public String getMotivo_cancelacion() {
        return motivo_cancelacion;
    }

    /**
     * Sets the motivo cancelacion.
     *
     * @param motivo_cancelacion the new motivo cancelacion
     */
    public void setMotivo_cancelacion(String motivo_cancelacion) {
        this.motivo_cancelacion = motivo_cancelacion;
    }

    /**
     * Gets the fecha muestra.
     *
     * @return the fecha muestra
     */
    public LocalDate getFecha_muestra() {
        return fecha_muestra;
    }

    /**
     * Gets the hora muestra.
     *
     * @return the hora muestra
     */
    public Long getHora_muestra() {
        return hora_muestra;
    }

    /**
     * Sets the hora muestra.
     *
     * @param hora_muestra the new hora muestra
     */
    public void setHora_muestra(Long hora_muestra) {
        this.hora_muestra = hora_muestra;
    }

    /**
     * Gets the fecha envia.
     *
     * @return the fecha envia
     */
    public LocalDate getFecha_envia() {
        return fecha_envia;
    }

    /**
     * Gets the hora envio.
     *
     * @return the hora envio
     */
    public Long getHora_envio() {
        return hora_envio;
    }

    /**
     * Sets the hora envio.
     *
     * @param hora_envio the new hora envio
     */
    public void setHora_envio(Long hora_envio) {
        this.hora_envio = hora_envio;
    }

    public ArrayList<Variable> getListaVariables() {
        return listaVariables;
    }

    public void setListaVariables(ArrayList<Variable> listaVariables) {
        this.listaVariables = listaVariables;
    }

    public String getNombrePaciente() {
        return getPaciente().getApellidosNombre();
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String toString() {
        String cadena = "";
        cadena = cadena.concat("id:" + getId());
        cadena = cadena.concat("\n Volante:" + getVolante());
        for (Variable variable : listaVariables) {
            cadena = cadena.concat("\n" + variable.getDescripcion() + ":" + variable.getValor());
        }
        return cadena;
    }
}
