package es.sacyl.hnss.entity;

import com.vaadin.server.VaadinSession;
import es.sacyl.hnss.dao.RegistroDAO;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * The Class ProcesoNew. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Proceso implements Comparable<Proceso> {

    protected int numeroOrden;
    protected Long id;
    protected Paciente paciente;
    protected Long subambito;
    protected LocalDate fechaini;
    protected LocalDate fechafin;
    protected LocalTime horaini;
    protected Long horafin;
    protected Centro centro;
    protected Servicio servicio;
    protected Long subservicio;
    protected Usuario userid;
    protected String origen;
    protected String motivo;
    protected String diagnostico;
    protected String observaciones;
    protected String medico_peticionario;
    protected String motivo_baja;
    protected String servicio_peticionario;
    protected String horainihhmm;
    protected String horafinhhmm;
    protected Long idInformeAsociado;
    protected String descripcion;

    protected int numeroParto;

    public final static Long SUBAMBITO_OXIGENO = new Long(320);
    public final static Long SUBAMBITO_MAMA = new Long(501);
    public final static Long SUBAMBITO_COLON = new Long(502);
    /*
	 * ojo fala este id
     */
    public final static Long SUBAMBITO_PALIATIVOS = new Long(400);
    public final static Long SUBAMBITO_PARTOS = new Long(600);

    public final static String AVISO_PROCESO_MOTIVO_FECHA = "La fecha y motivo de baja son ambos requeridos.";
    public final static String AVISO_PROCESO_CERRADO = "Proceso cerrado no modificable .";
    public final static String AVISO_PROCESO_FIN_MENOR_INICIO = "La fecha de fin debe ser mayor que inicio.";
    public final static String AVISO_PROCESO_SOLAPADO = "La fecha de inicio se solapa con un proceso cerrado.";

    DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    DateTimeFormatter horahhmm = DateTimeFormatter.ofPattern("hh:mm");

    /**
     * Instantiates a new proceso new.
     */
    public Proceso(Long id) {
        this.id = id;
    }

    public Proceso() {
        this.id = new Long(0);
        this.subambito = new Long(0);
        this.inicia();
    }

    public Proceso(Long id, Long subambito) {
        this.id = id;
        this.subambito = subambito;
        this.inicia();
    }

    public Proceso(Paciente paciente, Long subambito) {
        this.id = new Long(0);
        this.paciente = paciente;
        this.subambito = subambito;
        this.inicia();
    }

    /**
     * Instantiates a new proceso new.
     *
     * @param id the id
     * @param paciente the paciente
     * @param subambito the subambito
     */
    public Proceso(Long id, Paciente paciente, long subambito) {
        this.id = id;
        this.paciente = paciente;
        this.subambito = subambito;
        this.inicia();

    }

    /**
     * Inicia.
     */
    public void inicia() {
        horaini = Utilidades.getHoraNumeroAcualLocalTime();
        horafin = new Long(Constantes.HORAFIN_DEFECTO);
        centro = Centro.CENTRO_DEFECTO;
        servicio = getServicioDefecto(subambito);
        descripcion = getDescripcionSubambito(subambito);
        subservicio = new Long(0);
        userid = null;
        origen = "";
        motivo = "";
        diagnostico = "";
        observaciones = "";
        medico_peticionario = "";
        motivo_baja = "";
        servicio_peticionario = "";
        if (VaadinSession.getCurrent() != null) {
            userid = ((Usuario) VaadinSession.getCurrent().getAttribute(Constantes.SESSION_USERNAME));
        }
        this.setDescripcionSubambito();
        idInformeAsociado = new Long(0);

        if (subambito.equals(Proceso.SUBAMBITO_MAMA)) {
            motivo = "Sospecha malignidad screening mama";
            diagnostico = "Sospecha tumor mama";
            servicio_peticionario = "GIN";
        } else if (subambito.equals(Proceso.SUBAMBITO_COLON)) {
            motivo = "Sospecha malignidad screening colon";
            diagnostico = "Sospecha tumor colon";
            servicio_peticionario = "MFA";
        } else if (subambito.equals(Proceso.SUBAMBITO_PALIATIVOS)) {
        } else if (subambito.equals(Proceso.SUBAMBITO_OXIGENO)) {
        } else if (subambito.equals(Proceso.SUBAMBITO_PARTOS)) {
            motivo = "Embarazo a término";
            diagnostico = "Gestación";
            servicio_peticionario = "GIN";
        } else {
            descripcion = "   ";
        }
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

    public LocalTime getHoraini() {
        return horaini;
    }

    public String getHorainiString() {

        DateTimeFormatter horahhmm = DateTimeFormatter.ofPattern("hh:mm");
        return horahhmm.format(horaini);
    }

    public void setHoraini(LocalTime horaini) {
        this.horaini = horaini;
    }

    public void setHoraini(String hora) {
        int hh = Utilidades.getHora(hora);
        int mm = Utilidades.getMinuo(hora);
        LocalTime horaLocal = LocalTime.of(hh, mm);
        this.horaini = horaLocal;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    public Long getIdInformeAsociado() {
        return idInformeAsociado;
    }

    public void setIdInformeAsociado(Long idInformeAsociado) {
        this.idInformeAsociado = idInformeAsociado;
    }

    public void setDescripcionSubambito() {
        this.descripcion = getDescripcionSubambito(this.getSubambito());
    }

    public String getFechaHoraInicio() {
        String feString = "";
        if (fechaini != null) {
            if (!fechaini.equals(new Long(0))) {
                feString = fechadma.format(fechaini);
                if (horaini != null) {
                    if (!horaini.equals(new Long(0)) && horaini != null) {
                        // feString = feString + " " + Utilidades.getHoraHH_MM(horaini);
                        feString = feString + " " + horahhmm.format(horaini);
                        ;
                    }
                }
            }
        }
        return feString;
    }

    public static String getDescripcionSubambito(Long subambito) {
        String descripcion;
        if (subambito.equals(Proceso.SUBAMBITO_MAMA)) {
            descripcion = "Mama ";
        } else if (subambito.equals(Proceso.SUBAMBITO_COLON)) {
            descripcion = "Colon ";
        } else if (subambito.equals(Proceso.SUBAMBITO_PALIATIVOS)) {
            descripcion = "Paliativos ";
        } else if (subambito.equals(Proceso.SUBAMBITO_OXIGENO)) {
            descripcion = "Oxigenoterapia ";
        } else if (subambito.equals(Proceso.SUBAMBITO_PARTOS)) {
            descripcion = "Partos ";
        } else {
            descripcion = "";
        }
        return descripcion;
    }

    public static Servicio getServicioDefecto(Long subambito) {
        Servicio servicio = null;
        if (subambito.equals(Proceso.SUBAMBITO_MAMA)) {
            servicio = new Servicio(new Long(21), "GIN", "Ginecología");
        } else if (subambito.equals(Proceso.SUBAMBITO_COLON)) {
            servicio = new Servicio(new Long(66), "MFA", "Medicina Familiar");
        } else if (subambito.equals(Proceso.SUBAMBITO_PALIATIVOS)) {
            servicio = new Servicio(new Long(30), "MIR", "Medicina Interna");
        } else if (subambito.equals(Proceso.SUBAMBITO_OXIGENO)) {
            servicio = new Servicio(new Long(37), "NML", "Neumología");
        } else if (subambito.equals(Proceso.SUBAMBITO_PARTOS)) {
            servicio = new Servicio(new Long(41), "OBS", "Obstetricia");
        } else {
            servicio = new Servicio(new Long(78), "ADM", "ADMISIÓN");
        }

        return servicio;
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

    public String getPacienteApellidos() {
        if (paciente != null) {
            return paciente.getApellidosNombre();
        } else {
            return "";
        }
    }

    public String getPacienteApellidosEdad() {
        if (paciente != null) {
            return paciente.getApellidosNombreEdad();
        } else {
            return "";
        }
    }

    public String getPacienteNhc() {
        if (paciente != null) {
            return paciente.getNumerohc();
        } else {
            return "";
        }
    }

    public LocalDate getPacienteFnac() {
        if (paciente != null) {
            return paciente.getFnac();
        } else {
            return null;
        }
    }

    public int getNumeroPartoRegistro() {
        String valor = "";
        if (this != null) {
            RegistroPartoEvolucion registroEvolucion = (RegistroPartoEvolucion) new RegistroDAO().getLisRegistro(this.getPaciente(),
                    this, RegistroPartoEvolucion.PLANTILLLA_EDITOR_PAR_EVOLUCION);
            if (registroEvolucion != null && registroEvolucion.getNumeroOrdenParto() != null) {
                valor = registroEvolucion.getNumeroOrdenParto().getValor();
            }
        }
        if (Utilidades.isNumeric(valor.trim())) {
            return Integer.parseInt(valor.trim());
        } else {
            return 0;
        }
    }

    public int getNumeroParto() {
        return this.numeroParto;
    }

    public void setNumeroParto(int numeroParto) {
        this.numeroParto = numeroParto;
    }

    public String getFechaHoraInicioProceosActual() {
        String feString = "";
        if (this != null && this.getFechaini() != null) {
            if (!this.getFechaini().equals(new Long(0))) {
                feString = fechadma.format(this.getFechaini());
                if (this.getHoraini() != null) {

                    feString = feString + " " + horahhmm.format(this.getHoraini());

                }
            }
        }
        return feString;
    }

    public String getFechaFinProceosActual() {
        String feString = "";
        if (this != null && this.getFechafin() != null) {
            if (!this.getFechafin().equals(new Long(0))) {
                feString = fechadma.format(this.getFechafin());
                //  if (this.getHoraini() != null) {
                //  feString = feString + " " + horahhmm.format(this.getHorafin());
                //   feString = feString + " " + (this.getHorafin());
                //}
            }
        }
        return feString;
    }

    @Override
    public int compareTo(Proceso o) {
        int valor = 0;
        if (o.getSubambito().equals(Proceso.SUBAMBITO_PARTOS)) {
            if (getNumeroParto() < o.getNumeroParto()) {
                valor = 1;
            }
            if (getNumeroParto() > o.getNumeroParto()) {
                valor = -1;
            }
        }
        return valor;
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
