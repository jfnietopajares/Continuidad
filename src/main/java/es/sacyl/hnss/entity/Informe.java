package es.sacyl.hnss.entity;

import es.sacyl.hnss.dao.InformesDAO;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.OutputStream;

/**
 * The Class Informe. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Informe {

    private int numeroOrden;

    private Long id;

    private String descripcion;

    private Paciente paciente;

    private Long episodio;

    private Centro centro;

    private Servicio servicio;

    private String referecia;

    private LocalDate fecha;

    private Long hora;

    private int estado;

    private Blob docuxml;

    private int tipoxml;

    private Blob docubin;

    private int tipobin;

    private Long peticion;

    private Usuario userid;

    private Long canal;

    private int tipoinforme;

    private Usuario useridauth;

    private Servicio srvauth;

    private Usuario useridredactor;

    private Long plantalla_editor;

    private int flag;

    private Long pertenece;

    private int version;

    private int nive_visibilidad;

    private Long subservicio;

    private Usuario useridpeticionario;

    private int visto;

    private Long ultimoguardado;

    private int bloqueado;

    private Long almacenamiento;

    private Long tipo_documento;

    private Long ambito;

    private Servicio servicio_realizador;

    private Long fecha_proceso;

    private String referencia_almacenamiento;

    private int num_accesos;

    private Proceso problema;

    private Usuario user_visto;

    private Long fecha_visto;

    private String comentario_visto;

    private File ficheroInformeFile;

    private String urlFilePdf;

    private ArrayList<Campos_i> listaCampos = new ArrayList<Campos_i>();
    public final static Long TIPO_DOCUMENTO_MAMA = new Long(21);

    public final static int VAR_RESGISTRO_ESTADO_NORMAL = 2;

    public final static int VAR_RESGISTRO_ESTADO_SUSTITUIDO = 5;

    public final static Long CANAL_DEFECTO = new Long(6);

    public final static String INFORME_RN_DESCRIPCION = "Recién nacido";

    public final static String INFORME_PARTO_DESCRIPCION = "Evolución del parto";

    protected DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    private static final Logger logger = LogManager.getLogger(Informe.class);

    /**
     * Instantiates a new informe.
     */
    public Informe() {
        this.id = new Long(0);
        centro = Centro.CENTRO_DEFECTO;
        estado = VAR_RESGISTRO_ESTADO_NORMAL;
        tipoxml = 0;
        tipobin = 1;
        canal = CANAL_DEFECTO;
        flag = 0;
        version = 1;
        ultimoguardado = new Long(0);
        bloqueado = 0;
        ambito = new Long(17);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDescripcion20() {
        if (descripcion.length() > 20) {
            return descripcion.substring(0, 20);
        } else {
            return descripcion;
        }
    }

    public String getDescripcion12Char() {
        return descripcion.substring(12);
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Long getEpisodio() {
        return episodio;
    }

    public void setEpisodio(Long episodio) {
        this.episodio = episodio;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getReferecia() {
        return referecia;
    }

    public void setReferecia(String referecia) {
        this.referecia = referecia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Blob getDocuxml() {
        return docuxml;
    }

    public void setDocuxml(Blob docuxml) {
        this.docuxml = docuxml;
    }

    public int getTipoxml() {
        return tipoxml;
    }

    public void setTipoxml(int tipoxml) {
        this.tipoxml = tipoxml;
    }

    public Blob getDocubin() {
        return docubin;
    }

    public void setDocubin(Blob docubin) {
        this.docubin = docubin;
    }

    public int getTipobin() {
        return tipobin;
    }

    public void setTipobin(int tipobin) {
        this.tipobin = tipobin;
    }

    public Long getPeticion() {
        return peticion;
    }

    public void setPeticion(Long peticion) {
        this.peticion = peticion;
    }

    public Usuario getUserid() {
        return userid;
    }

    public void setUserid(Usuario userid) {
        this.userid = userid;
    }

    public int getTipoinforme() {
        return tipoinforme;
    }

    public void setTipoinforme(int tipoinforme) {
        this.tipoinforme = tipoinforme;
    }

    public Usuario getUseridauth() {
        return useridauth;
    }

    public void setUseridauth(Usuario useridauth) {
        this.useridauth = useridauth;
    }

    public Servicio getSrvauth() {
        return srvauth;
    }

    public void setSrvauth(Servicio srvauth) {
        this.srvauth = srvauth;
    }

    public Usuario getUseridredactor() {
        return useridredactor;
    }

    public void setUseridredactor(Usuario useridredactor) {
        this.useridredactor = useridredactor;
    }

    public Long getPlantalla_editor() {
        return plantalla_editor;
    }

    public void setPlantalla_editor(Long plantalla_editor) {
        this.plantalla_editor = plantalla_editor;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Long getPertenece() {
        return pertenece;
    }

    public void setPertenece(Long pertenece) {
        this.pertenece = pertenece;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getNive_visibilidad() {
        return nive_visibilidad;
    }

    public void setNive_visibilidad(int nive_visibilidad) {
        this.nive_visibilidad = nive_visibilidad;
    }

    public Long getSubservicio() {
        return subservicio;
    }

    public void setSubservicio(Long subservicio) {
        this.subservicio = subservicio;
    }

    public Usuario getUseridpeticionario() {
        return useridpeticionario;
    }

    public void setUseridpeticionario(Usuario useridpeticionario) {
        this.useridpeticionario = useridpeticionario;
    }

    public int getVisto() {
        return visto;
    }

    public void setVisto(int visto) {
        this.visto = visto;
    }

    public Long getUltimoguardado() {
        return ultimoguardado;
    }

    public void setUltimoguardado(Long ultimoguardado) {
        this.ultimoguardado = ultimoguardado;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Long getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(Long almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public Long getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Long tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public Long getAmbito() {
        return ambito;
    }

    public void setAmbito(Long ambito) {
        this.ambito = ambito;
    }

    public Servicio getServicio_realizador() {
        return servicio_realizador;
    }

    public void setServicio_realizador(Servicio servicio_realizador) {
        this.servicio_realizador = servicio_realizador;
    }

    public Long getFecha_proceso() {
        return fecha_proceso;
    }

    public void setFecha_proceso(Long fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    public String getReferencia_almacenamiento() {
        return referencia_almacenamiento;
    }

    public void setReferencia_almacenamiento(String referencia_almacenamiento) {
        this.referencia_almacenamiento = referencia_almacenamiento;
    }

    public int getNum_accesos() {
        return num_accesos;
    }

    public void setNum_accesos(int num_accesos) {
        this.num_accesos = num_accesos;
    }

    public Proceso getProblema() {
        return problema;
    }

    public void setProblema(Proceso problema) {
        this.problema = problema;
    }

    public Usuario getUser_visto() {
        return user_visto;
    }

    public void setUser_visto(Usuario user_visto) {
        this.user_visto = user_visto;
    }

    public Long getFecha_visto() {
        return fecha_visto;
    }

    public void setFecha_visto(Long fecha_visto) {
        this.fecha_visto = fecha_visto;
    }

    public String getComentario_Visto() {
        return comentario_visto;
    }

    public void setComentario_Visto(String comentario_visto) {
        this.comentario_visto = comentario_visto;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public File getFicheroInformeFile() {
        return ficheroInformeFile;
    }

    public void setFicheroInformeFile(File ficheroInformeFile) {
        this.ficheroInformeFile = ficheroInformeFile;
    }

    public Long getCanal() {
        return canal;
    }

    public void setCanal(Long canal) {
        this.canal = canal;
    }

    public ArrayList<Campos_i> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(ArrayList<Campos_i> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public String getFechaHora() {
        String feString = "";
        if (fecha != null) {
            feString = fechadma.format(fecha);
            if (hora != null) {
                if (!hora.equals(new Long(0))) {
                    feString = feString + " " + Utilidades.getHoraHH_MM(hora);
                }
            }

        }
        return feString;
    }

    public String getFechaHoraServcio() {
        String string = "";
        string = this.getFechaHora() + "  " + this.getServicio().getCodigo();
        return string;
    }

    public String getFechaHoraServcioDescrip() {
        String string = "";
        string = this.getFechaHora() + "  " + this.getServicio().getCodigo() + " " + this.getDescripcion20();

        return string;
    }

    public String getCodigoServicio() {
        return this.getServicio().getCodigo();
    }

    public String getPathFilePdf() {
        return Constantes.DIRECTORIOREPORTS + "inf_" + this.id + ".pdf";
    }

    public String getUrlFilePdf() {
        return Constantes.URLREPORTS + "inf_" + this.id + ".pdf";
    }

    public void setUrlFilePdf(String urlFilePdf) {
        this.urlFilePdf = urlFilePdf;
    }

    /**
     * Gets the descripcion subambito.
     *
     * @param subambito the subambito
     * @return the descripcion subambito
     */
    public static String getDescripcionSubambito(Long subambito) {
        String deString = "";
        if (subambito.equals(Proceso.SUBAMBITO_MAMA)) {
            deString = " Informe Screening Mama";
        } else if (subambito.equals(Proceso.SUBAMBITO_COLON)) {
            deString = " Informe Screening Colon";
        } else if (subambito.equals(Proceso.SUBAMBITO_PALIATIVOS)) {
            deString = " Informe paliativos";
        } else if (subambito.equals(Proceso.SUBAMBITO_OXIGENO)) {
            deString = " Informe Oxígenoterapia ";
        }
        return deString;
    }

    /**
     * Gets the tipo documento subambito.
     *
     * @param subambito the subambito
     * @return the tipo documento subambito
     */
    public static long getTipoDocumentoSubambito(Long subambito) {
        Long tipoLong = new Long(0);
        if (subambito.equals(Proceso.SUBAMBITO_MAMA)) {
            tipoLong = new Long(21);
        } else if (subambito.equals(Proceso.SUBAMBITO_COLON)) {
            tipoLong = new Long(21);
        } else if (subambito.equals(Proceso.SUBAMBITO_PALIATIVOS)) {
            tipoLong = new Long(21);
        } else if (subambito.equals(Proceso.SUBAMBITO_OXIGENO)) {
            tipoLong = new Long(21);
        } else if (subambito.equals(Proceso.SUBAMBITO_PARTOS)) {
            tipoLong = new Long(34);
        }
        return tipoLong;
    }

    public String getHtmlCabecera() {
        String html = "<b>";

        html = html.concat(" " + this.getFechaHora() + " ");

        if (this.servicio != null) {
            html = html.concat(this.getServicio().getCodigo() + " ");
        }

        if (this.getUserid() != null) {
            html = html.concat("Dr/a:" + this.getUserid().getApellidosNombre());
        }
        html = html.concat("</b><br>");

        html = html.concat("Nhc:" + this.getPaciente().getNumerohc() + "&nbsp;Paciente:"
                + this.getPaciente().getApellidosNombre() + "<hr>");
        return html;
    }

    public String getHtmlCampos_i() {
        String html = "";
        for (Campos_i campo : getListaCampos()) {
            if (campo.getDato() != null) {
                try {
                    if (campo.getDescripcion().length() > 5) {
                        if (!campo.getDescripcion().substring(0, 5).equals("DICOM")) {
                            int caracteres = (int) campo.getDato().length();
                            html = html.concat("<b>" + campo.getDescripcion() + ":</b>&nbsp;"
                                    + campo.getDato().getSubString(1, caracteres));
                            if (campo.getUnidades() != null && !campo.getUnidades().isEmpty()) {
                                html = html.concat("&nbsp;&nbsp;" + campo.getUnidades());
                            }
                            html = html.concat("<br>");
                        }
                    } else {
                        int caracteres = (int) campo.getDato().length();
                        html = html.concat("<b>" + campo.getDescripcion() + ":</b>&nbsp;"
                                + campo.getDato().getSubString(1, caracteres));
                        if (campo.getUnidades() != null && !campo.getUnidades().isEmpty()) {
                            html = html.concat("&nbsp;&nbsp;" + campo.getUnidades());
                        }
                        html = html.concat("<br>");

                    }
                } catch (SQLException e) {
                    logger.error("Error conversión campo CLOB ", e);
                }
            }
        }
        return html;
    }

    public File getFilePdf() {
        File file = null;
        try {
            FileOutputStream outpu = null;
            String pathname = this.getPathFilePdf();
            file = new File(pathname);
            outpu = new FileOutputStream(file);
            Blob archivo = new InformesDAO().getBlobPdfId(id);
            InputStream inStream = archivo.getBinaryStream();
            int size = (int) archivo.length();
            byte[] buffer = new byte[size];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                outpu.write(buffer, 0, length);
            }
            outpu.close();
        } catch (Exception ioe) {
            logger.error(ioe);
        }
        return file;
    }

}
