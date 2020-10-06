package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Constantes;
import java.time.LocalDate;



/**
 * The Class ProcesoOxigeno.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class ProcesoOxigeno {

    /**
     * The id.
     */
    private Long id;

    /**
     * The paciente.
     */
    private Paciente paciente;

    /**
     * The subambito.
     */
    private Long subambito;

    /**
     * The fechaini.
     */
    private LocalDate fechaini;

    /**
     * The fechafin.
     */
    private LocalDate fechafin;

    /**
     * The horaini.
     */
    private Long horaini;

    /**
     * The horafin.
     */
    private Long horafin;

    /**
     * The centro.
     */
    private Centro centro;

    /**
     * The servicio.
     */
    private Servicio servicio;

    /**
     * The subservicio.
     */
    private Long subservicio;

    /**
     * The userid.
     */
    private Usuario userid;

    /**
     * The origen.
     */
    private String origen;

    /**
     * The motivo.
     */
    private String motivo;

    /**
     * The diagnostico.
     */
    private String diagnostico;

    /**
     * The observaciones.
     */
    private String observaciones;

    /**
     * The medico peticionario.
     */
    private String medico_peticionario;

    /**
     * The motivo baja.
     */
    private String motivo_baja;

    /**
     * The servicio peticionario.
     */
    private String servicio_peticionario;

    /**
     * The horainihhmm.
     */
    private String horainihhmm;

    /**
     * The horafinhhmm.
     */
    private String horafinhhmm;

    /**
     * Instantiates a new proceso oxigeno.
     */
    public ProcesoOxigeno() {
        this.inicia();
    }

    /**
     * Instantiates a new proceso oxigeno.
     *
     * @param id the id
     */
    public ProcesoOxigeno(Long id) {
        this.inicia();
        this.id = id;
    }

    /**
     * Instantiates a new proceso oxigeno.
     *
     * @param id the id
     * @param paciente the paciente
     */
    public ProcesoOxigeno(Long id, Paciente paciente) {
        this.inicia();
        this.id = id;
        this.paciente = paciente;

    }

    /**
     * Instantiates a new proceso oxigeno.
     *
     * @param id the id
     * @param paciente the paciente
     * @param subambito the subambito
     * @param fechaini the fechaini
     * @param horaini the horaini
     * @param centro the centro
     * @param servicio the servicio
     * @param origen the origen
     * @param motivo the motivo
     * @param diagnostico the diagnostico
     * @param observaciones the observaciones
     * @param servicio_peticionario the servicio peticionario
     */
    public ProcesoOxigeno(Long id, Paciente paciente, Long subambito, LocalDate fechaini, Long horaini, Centro centro,
            Servicio servicio, String origen, String motivo, String diagnostico, String observaciones,
            String servicio_peticionario) {
        this.inicia();
        this.id = id;
        this.paciente = paciente;
        this.subambito = subambito;
        this.fechaini = fechaini;
        this.horaini = horaini;
        this.centro = centro;
        this.servicio = servicio;
        this.origen = origen;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.observaciones = observaciones;
        this.servicio_peticionario = servicio_peticionario;
    }

    /**
     * Instantiates a new proceso oxigeno.
     *
     * @param paciente the paciente
     * @param subambito the subambito
     * @param fechaini the fechaini
     * @param horaini the horaini
     * @param centro the centro
     * @param servicio the servicio
     * @param origen the origen
     * @param motivo the motivo
     * @param diagnostico the diagnostico
     * @param observaciones the observaciones
     * @param servicio_peticionario the servicio peticionario
     */
    public ProcesoOxigeno(Paciente paciente, Long subambito, LocalDate fechaini, Long horaini, Centro centro,
            Servicio servicio, String origen, String motivo, String diagnostico, String observaciones,
            String servicio_peticionario) {
        this.paciente = paciente;
        this.subambito = subambito;
        this.fechaini = fechaini;
        this.horaini = horaini;
        this.centro = centro;
        this.servicio = servicio;
        this.origen = origen;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.observaciones = observaciones;
        this.servicio_peticionario = servicio_peticionario;
        // if (getFechafin()!= Constantes.FEHAFIN_DEFECTO)
        // setFechafinddmmyy(Utilidades.getFechFormatoayyyymmdd(fechafin,
        // Constantes.SEPARADOR_FECHA));
    }

    /**
     * Instantiates a new proceso oxigeno.
     *
     * @param id the id
     * @param fechainiddmmyy the fechainiddmmyy
     * @param motivo the motivo
     */
    public ProcesoOxigeno(Long id, String fechainiddmmyy, String motivo) {
        this.id = id;
        this.motivo = motivo;

    }

    /**
     * Inicia.
     */
    public void inicia() {
        id = new Long(0);
        paciente = new Paciente(new Long(0));
        subambito = new Long(Proceso.SUBAMBITO_OXIGENO);
        // fechaini = new LocalDate.(0);
        // fechafin = new Long(Constantes.FEHAFIN_DEFECTO);
        horaini = new Long(Constantes.HORAFIN_DEFECTO);
        horafin = new Long(0);
        centro = Centro.CENTRO_DEFECTO;
        servicio = Proceso.getServicioDefecto(subambito);
        subservicio = new Long(0);
        userid = null;
        origen = "";
        motivo = "";
        diagnostico = "";
        observaciones = "";
        medico_peticionario = "";
        motivo_baja = "";
        servicio_peticionario = "";
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
     * Gets the subambito.
     *
     * @return the subambito
     */
    public Long getSubambito() {
        return subambito;
    }

    /**
     * Sets the subambito.
     *
     * @param subambito the new subambito
     */
    public void setSubambito(Long subambito) {
        this.subambito = subambito;
    }

    /**
     * Gets the fechaini.
     *
     * @return the fechaini
     */
    public LocalDate getFechaini() {
        return fechaini;
    }

    /**
     * Sets the fechaini.
     *
     * @param fechaini the new fechaini
     */
    public void setFechaini(LocalDate fechaini) {
        this.fechaini = fechaini;
    }

    /**
     * Gets the fechafin.
     *
     * @return the fechafin
     */
    public LocalDate getFechafin() {
        return fechafin;
    }

    /**
     * Sets the fechafin.
     *
     * @param fechafin the new fechafin
     */
    public void setFechafin(LocalDate fechafin) {
        this.fechafin = fechafin;
    }

    /**
     * Gets the horaini.
     *
     * @return the horaini
     */
    public Long getHoraini() {
        return horaini;
    }

    /**
     * Sets the horaini.
     *
     * @param horaini the new horaini
     */
    public void setHoraini(Long horaini) {
        this.horaini = horaini;
    }

    /**
     * Gets the horafin.
     *
     * @return the horafin
     */
    public Long getHorafin() {
        return horafin;
    }

    /**
     * Sets the horafin.
     *
     * @param horafin the new horafin
     */
    public void setHorafin(Long horafin) {
        this.horafin = horafin;
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
     * @param usuario the new userid
     */
    public void setUserid(Usuario usuario) {
        this.userid = usuario;
    }

    /**
     * Gets the origen.
     *
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Sets the origen.
     *
     * @param origen the new origen
     */
    public void setOrigen(String origen) {
        this.origen = origen;
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
     * Gets the diagnostico.
     *
     * @return the diagnostico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Sets the diagnostico.
     *
     * @param diagnostico the new diagnostico
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Gets the observaciones.
     *
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Sets the observaciones.
     *
     * @param observaciones the new observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Gets the medico peticionario.
     *
     * @return the medico peticionario
     */
    public String getMedico_peticionario() {
        return medico_peticionario;
    }

    /**
     * Sets the medico peticionario.
     *
     * @param medico_peticionario the new medico peticionario
     */
    public void setMedico_peticionario(String medico_peticionario) {
        this.medico_peticionario = medico_peticionario;
    }

    /**
     * Gets the motivo baja.
     *
     * @return the motivo baja
     */
    public String getMotivo_baja() {
        return motivo_baja;
    }

    /**
     * Sets the motivo baja.
     *
     * @param motivo_baja the new motivo baja
     */
    public void setMotivo_baja(String motivo_baja) {
        this.motivo_baja = motivo_baja;
    }

    /**
     * Gets the servicio peticionario.
     *
     * @return the servicio peticionario
     */
    public String getServicio_peticionario() {
        return servicio_peticionario;
    }

    /**
     * Sets the servicio peticionario.
     *
     * @param servicio_peticionario the new servicio peticionario
     */
    public void setServicio_peticionario(String servicio_peticionario) {
        this.servicio_peticionario = servicio_peticionario;
    }

    /**
     * Sets the fechainiddmmyy.
     *
     * @param fechainiddmmyy the new fechainiddmmyy
     */
    public void setFechainiddmmyy(String fechainiddmmyy) {
    }

    /**
     * Gets the horainihhmm.
     *
     * @return the horainihhmm
     */
    public String getHorainihhmm() {
        return horainihhmm;
    }

    /**
     * Sets the horainihhmm.
     *
     * @param horainihhmm the new horainihhmm
     */
    public void setHorainihhmm(String horainihhmm) {
        this.horainihhmm = horainihhmm;
    }

    /**
     * Sets the fechafinddmmyy.
     *
     * @param fechafinddmmyy the new fechafinddmmyy
     */
    public void setFechafinddmmyy(String fechafinddmmyy) {
    }

    /**
     * Gets the horafinhhmm.
     *
     * @return the horafinhhmm
     */
    public String getHorafinhhmm() {
        return horafinhhmm;
    }

    /**
     * Sets the horafinhhmm.
     *
     * @param horafinhhmm the new horafinhhmm
     */
    public void setHorafinhhmm(String horafinhhmm) {
        this.horafinhhmm = horafinhhmm;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "id=" + this.getId() + "\n" + "paciente=" + this.getPaciente() + "\n" + "subambito="
                + this.getSubambito() + "\n" + "fechaini=" + this.getFechaini() + "\n" + "horaini=" + this.getHoraini()
                + "\n" + "centro=" + this.getCentro() + "\n" + "servicio=" + this.getServicio() + "\n" + "subservicio="
                + this.getSubservicio() + "\n" + "userid=" + this.getUserid() + "\n" + "origen=" + this.getOrigen()
                + "\n" + "motivo=" + this.getMotivo() + "\n" + "diagnostico=" + this.getDiagnostico() + "\n"
                + "observaciones=" + this.getObservaciones() + "\n" + "fechafin=" + this.getFechafin() + "\n"
                + "horafin=" + this.getHorafin() + "\n" + "motivo_baja=" + this.getMotivo_baja() + "\n"
                + "medico_peticionario=" + this.getMedico_peticionario() + "\n" + "servicio_peticionario="
                + this.getServicio_peticionario() + "\n";
    }

    /**
     * To html fila.
     *
     * @return the string
     */
    public String toHtmlFila() {
        String html;
        html = "<tr>" + "<td>" + this.getPaciente().getNumerohc() + "</td> " + "<td>"
                + this.getPaciente().getApellidosNombre() + "</td> " + "<td>" + this.getMotivo() + "</td> " + "<td>"
                + this.getFechaini() + "</td> ";

        if (this.getFechafin() != null) {
            html = html.concat("<td>" + this.getFechafin() + "</td> ");
        } else {
            html = html.concat("<td></td> ");
        }

        html = html.concat("</tr>");
        return html;
    }
}
