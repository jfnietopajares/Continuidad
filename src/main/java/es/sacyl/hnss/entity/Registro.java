package es.sacyl.hnss.entity;

import com.vaadin.server.VaadinSession;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * The Class RegistroNew. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Registro {

    private static final Logger logger = LogManager.getLogger(Registro.class);

    private int numeroOrden;

    protected Long id;

    protected String descripcion;

    protected Paciente paciente;

    protected Centro centro;

    protected LocalDate fecha;

    protected Long hora;

    protected int estado;

    protected Episodio episodio;

    protected Servicio servicio;

    protected String referencia;

    protected Usuario userid;

    protected Long plantilla_editor;

    protected Long canal;

    protected Long tiporegistro;

    protected Long subservicio;

    protected Long pertenece;

    protected Proceso problema;

    protected Usuario useridredactor;

    protected Usuario useridtranscriptor;

    protected ArrayList<Campos_r> listaCampos;

    public final static Long CANAL_DEFECTO = new Long(6);

    public final static int ORDEN_DEFECTO = 1;

    public final static int VAR_RESGISTRO_ESTADO_NORMAL = 2;

    public final static int VAR_RESGISTRO_ESTADO_SUSTITUIDO = 5;

    public final static Long REGISTRO_EVOLUTIVO = new Long(3);

    protected DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    /**
     * Instantiates a new registro new.
     */
    public Registro() {
        this.setId(new Long(0));
        this.iniciaValoresDefecto();
    }

    /**
     * Instantiates a new registro new.
     *
     * @param id the id
     */
    public Registro(Long id) {
        this.id = id;
        this.iniciaValoresDefecto();
    }

    public Registro(Registro r) {
        this.id = r.getId();
        this.descripcion = r.getDescripcion();
        this.paciente = r.getPaciente();
        this.centro = r.getCentro();
        this.fecha = r.getFecha();
        this.hora = r.getHora();
        this.estado = r.getEstado();
        this.episodio = r.getEpisodio();
        this.servicio = r.getServicio();
        this.referencia = r.getReferencia();
        this.userid = r.getUserid();
        this.plantilla_editor = r.getPlantilla_editor();
        this.canal = r.getCanal();
        this.tiporegistro = r.getTiporegistro();
        this.subservicio = r.getSubservicio();
        this.pertenece = r.getPertenece();
        this.problema = r.getProblema();
        this.useridredactor = r.getUseridredactor();
        this.useridtranscriptor = r.getUseridtranscriptor();
        this.listaCampos = r.listaCampos;
    }

    /**
     * Inicia.
     */
    public void iniciaValoresDefecto() {
        this.setCentro(Centro.CENTRO_DEFECTO);
        this.setCanal(CANAL_DEFECTO);
        this.setEstado(VAR_RESGISTRO_ESTADO_NORMAL);
        // llamadas desde servicios rest no hay sesion
        if (VaadinSession.getCurrent() != null) {
            this.setUserid((Usuario) VaadinSession.getCurrent().getAttribute(Constantes.SESSION_USERNAME));
            this.setUseridredactor((Usuario) VaadinSession.getCurrent().getAttribute(Constantes.SESSION_USERNAME));
            this.setUseridtranscriptor((Usuario) VaadinSession.getCurrent().getAttribute(Constantes.SESSION_USERNAME));
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

    public String getIdString() {
        return id.toString();
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void setId(String id) {
        if (Utilidades.isNumeric(id)) {
            this.id = Long.parseLong(id);
        }
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getPacienteNhc() {
        if (paciente != null && !paciente.getNumerohc().isEmpty()) {
            return this.paciente.getNumerohc();
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
     * Gets the fecha.
     *
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
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

    public String getHoraString() {
        if (this.hora != null && !this.hora.equals(new Long(0))) {
            return Utilidades.getHoraHH_MM(this.hora);
        } else {
            return "";
        }

    }

    public void setHora(String hora) {
        this.hora = Long.parseLong(hora.replace(":", ""));
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
     * Gets the episodio.
     *
     * @return the episodio
     */
    public Episodio getEpisodio() {
        return episodio;
    }

    /**
     * Sets the episodio.
     *
     * @param episodio the new episodio
     */
    public void setEpisodio(Episodio episodio) {
        this.episodio = episodio;
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
     * Gets the referencia.
     *
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Sets the referencia.
     *
     * @param referencia the new referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
     * Gets the plantilla editor.
     *
     * @return the plantilla editor
     */
    public Long getPlantilla_editor() {
        return plantilla_editor;
    }

    /**
     * Sets the plantilla edior.
     *
     * @param plantilla_editor the new plantilla edior
     */
    public void setPlantilla_edior(Long plantilla_editor) {
        this.plantilla_editor = plantilla_editor;
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

    /**
     * Gets the tiporegistro.
     *
     * @return the tiporegistro
     */
    public Long getTiporegistro() {
        return tiporegistro;
    }

    /**
     * Sets the tiporegistro.
     *
     * @param tiporegistro the new tiporegistro
     */
    public void setTiporegistro(Long tiporegistro) {
        this.tiporegistro = tiporegistro;
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
     * Gets the problema.
     *
     * @return the problema
     */
    public Proceso getProblema() {
        return problema;
    }

    /**
     * Sets the problema.
     *
     * @param problema the new problema
     */
    public void setProblema(Proceso problema) {
        this.problema = problema;
    }

    /**
     * Gets the useridredactor.
     *
     * @return the useridredactor
     */
    public Usuario getUseridredactor() {
        return useridredactor;
    }

    /**
     * Sets the useridredactor.
     *
     * @param useridredactor the new useridredactor
     */
    public void setUseridredactor(Usuario useridredactor) {
        this.useridredactor = useridredactor;
    }

    /**
     * Gets the useridtranscriptor.
     *
     * @return the useridtranscriptor
     */
    public Usuario getUseridtranscriptor() {
        return useridtranscriptor;
    }

    /**
     * Sets the useridtranscriptor.
     *
     * @param useridtranscriptor the new useridtranscriptor
     */
    public void setUseridtranscriptor(Usuario useridtranscriptor) {
        this.useridtranscriptor = useridtranscriptor;
    }

    /**
     * Sets the plantilla editor.
     *
     * @param plantilla_editor the new plantilla editor
     */
    public void setPlantilla_editor(Long plantilla_editor) {
        this.plantilla_editor = plantilla_editor;
    }

    /**
     * Gets the lista campos.
     *
     * @return the lista campos
     */
    public ArrayList<Campos_r> getListaCampos() {
        return listaCampos;
    }

    /**
     * Sets the lista campos.
     *
     * @param listaCampos the new lista campos
     */
    public void setListaCampos(ArrayList<Campos_r> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public String getFechaHora() {
        String feString = "";
        if (fecha != null) {
            if (!fecha.equals(new Long(0))) {
                feString = fechadma.format(fecha);
                if (hora != null) {
                    if (!hora.equals(new Long(0))) {
                        feString = feString + " " + Utilidades.getHoraHH_MM(hora);
                    }
                }
            }
        }
        return feString;
    }

    public String getHoraFormato() {
        String feString = "";
        if (hora != null) {
            if (!hora.equals(new Long(0))) {
                feString = feString + " " + Utilidades.getHoraHH_MM(hora);
            }
        }
        return feString;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "id=" + getId() + ",descripcion=" + getDescripcion() + " paciente=" + getPaciente().getApellidosNombre()
                + "centro=" + getCentro().toString() + " fecha=" + getFecha() + ", hora=" + getHora();
    }

    public String getHtmlCampos() {
        String cadena = "";
        cadena = "<b>" + cadena.concat(this.getServicio().getCodigo() + " " + this.getFechaHora() + " "
                + this.getUserid().getApellidosNombre()) + "</b><br>";

        for (Campos_r campo : getListaCampos()) {
            if (campo.getDato() != null) {
                cadena = cadena.concat("<b>" + campo.getDescripcion() + "</b>" + campo.getDato() + "<br>");
            }
        }
        return cadena;
    }

    public ArrayList<Variable> getListaVariablesConValor() {
        ArrayList<Variable> listaVariables = new ArrayList<>();

        Method[] metodos = this.getClass().getMethods();
        for (Method m : metodos) {
            if (m.getName().length() > 10) {
                if (m.getName().substring(0, 11).equals("getVariable")) {
                    try {
                        if (m.invoke(this, null) instanceof Variable) {
                            Variable variable = (Variable) m.invoke(this, null);
                            if (variable != null) {
                                if (variable.getValor() != null && !variable.getValor().isEmpty()) {
                                    listaVariables.add(variable);
                                }
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        logger.error(Constantes.EXCEPTION_ERROR, e);
                    } catch (Exception e) {
                        logger.error(Constantes.EXCEPTION_ERROR, e);
                    }
                }
            }
        }
        return listaVariables;
    }

    public String getContenidoHtml() {
        String contenido = "";

        Method[] metodos = this.getClass().getMethods();
        for (Method m : metodos) {
            if (m.getName().length() > 10) {
                if (m.getName().substring(0, 11).equals("getVariable")) {
                    try {
                        if (m.invoke(this, null) instanceof Variable) {
                            Variable variable = (Variable) m.invoke(this, null);
                            if (variable != null) {
                                if (variable.getValor() != null && !variable.getValor().isEmpty()) {
                                    contenido = contenido.concat("<b>" + variable.getDescripcion() + "</b>");
                                    contenido = contenido.concat(variable.getValor());
                                    contenido = contenido.concat("<hr>");
                                }
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        logger.error(Constantes.EXCEPTION_ERROR, e);
                    } catch (Exception e) {
                        logger.error(Constantes.EXCEPTION_ERROR, e);
                    }
                }
            }
        }
        return contenido;
    }
}
