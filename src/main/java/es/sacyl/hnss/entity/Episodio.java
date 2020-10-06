package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import java.io.Serializable;



public class Episodio implements Serializable {

    private static final long serialVersionUID = 1L;

    private int numeroOrden;
 
    private Long id;
    private Paciente paciente;
    private EpisodioClase clase;
    private Long subclase;
    private LocalDate finicio;
    private Integer hinicio;
    private LocalDate ffinal;
    private Integer hfinal;
    private Centro centro;
    private Servicio servicio;
    private Usuario userid;
    private String cie9;
    private String observacion;
    private Variable prestacion;
    private Integer prioridad;
    private Long canal;
    private Long origen;
    private Long motivo_llegada;
    private Long motivo_alta;
    private Long tipo_alta;
    private Long demora;
    private Long tipo_financiacion;
    private Long alta_resulucion;
    private Long procede;
    private Long triaje;
    private Agenda agenda;
    private Cama cama;
    private Usuario residente;
    private Long subservicio;
    private String turno;
    private String dias;
    private String garante;
    private String icu;
    private Long problema;
    private Long estado_presencia;
    // para his
    private String codigProcedimiento;
    private String codigDiagnostico;

    private Zona zona;
    // atributo para listados y calendarios de citas sobre todo
    private Integer citasDadas;

    DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    private static ArrayList<EpisodioClase> listaClases = new ArrayList<>();

    public final static EpisodioClase CLASE_HOSPITALIZACION = new EpisodioClase(new Long(1), "Hos", 1, false);
    public final static EpisodioClase CLASE_CONSULTAS = new EpisodioClase(new Long(2), "Cex", 0, false);
    public final static EpisodioClase CLASE_URGENCIAS = new EpisodioClase(new Long(3), "Urg", 2, false);
    public final static EpisodioClase CLASE_QUI_INTERVENIDO = new EpisodioClase(new Long(4), "Inte", 3, false);
    public final static EpisodioClase CLASE_HDIA = new EpisodioClase(new Long(5), "Hdia", 4, true);
    public final static EpisodioClase CLASE_QUI_PROGRMACION = new EpisodioClase(new Long(7), "Qpro", 3, false);
    public final static Long SUBCLASE_INGRESO_RECIENNACIDOS = new Long(1);

    public Episodio() {
        inicio();
        this.id = new Long(0);
        inicioAtributos();
    }

    public Episodio(Long id) {
        inicio();
        this.id = id;
        inicioAtributos();
    }

// para clonado de objetos
    public Episodio(Episodio epi) {
        this.numeroOrden = epi.getNumeroOrden();
        this.id = epi.getId();
        this.paciente = epi.getPaciente();
        this.clase = epi.getClase();
        this.subclase = epi.getSubclase();
        this.finicio = epi.getFinicio();
        this.hinicio = epi.getHinicio();
        this.ffinal = epi.getFfinal();
        this.hfinal = epi.getHfinal();
        this.centro = epi.getCentro();
        this.servicio = epi.getServicio();
        this.userid = epi.getUserid();
        this.cie9 = epi.getCie9();
        this.observacion = epi.getObservacion();
        this.prestacion = epi.getPrestacion();
        this.prioridad = epi.getPrioridad();
        this.canal = epi.getCanal();
        this.origen = epi.getOrigen();
        this.motivo_llegada = epi.getMotivo_llegada();
        this.motivo_alta = epi.getMotivo_alta();
        this.tipo_alta = epi.getTipo_alta();
        this.demora = epi.getDemora();
        this.tipo_financiacion = epi.getTipo_financiacion();
        this.alta_resulucion = epi.getAlta_resulucion();
        this.procede = epi.getProcede();
        this.triaje = epi.getTriaje();
        this.agenda = epi.getAgenda();
        this.cama = epi.getCama();
        this.residente = epi.getResidente();
        this.subservicio = epi.getSubservicio();
        this.turno = epi.getTurno();
        this.dias = epi.getDias();
        this.garante = epi.getGarante();
        this.icu = epi.getIcu();
        this.problema = epi.getProblema();
        this.estado_presencia = epi.getEstado_presencia();

    }

    public void inicioAtributos() {
        this.canal = Registro.CANAL_DEFECTO;
        this.centro = Centro.CENTRO_DEFECTO;
        // this.userid = ((Usuario)
        // VaadinSession.getCurrent().getAttribute(Constantes.SESSION_USERNAME));
        this.finicio = LocalDate.now();
        this.hinicio = Utilidades.getHoraActualHHmm();
    }

    public static void inicio() {
        listaClases.add(Episodio.CLASE_HOSPITALIZACION);
        listaClases.add(Episodio.CLASE_CONSULTAS);
        listaClases.add(Episodio.CLASE_URGENCIAS);
        listaClases.add(Episodio.CLASE_HDIA);
        listaClases.add(Episodio.CLASE_QUI_INTERVENIDO);
        listaClases.add(Episodio.CLASE_QUI_PROGRMACION);
    }

    public static ArrayList<EpisodioClase> getListaclases(Centro centro) {
        if (centro.equals(Centro.HNSS)) {
            listaClases.removeAll(listaClases);
            listaClases.add(Episodio.CLASE_HOSPITALIZACION);
            listaClases.add(Episodio.CLASE_CONSULTAS);
            listaClases.add(Episodio.CLASE_URGENCIAS);
            listaClases.add(Episodio.CLASE_HDIA);
            listaClases.add(Episodio.CLASE_QUI_INTERVENIDO);
            listaClases.add(Episodio.CLASE_QUI_PROGRMACION);
        } else if (centro.equals(Centro.PROVINCIAL)) {
            listaClases.removeAll(listaClases);
            listaClases.add(Episodio.CLASE_HOSPITALIZACION);
            listaClases.add(Episodio.CLASE_CONSULTAS);
            listaClases.add(Episodio.CLASE_HDIA);
        } else if (centro.equals(Centro.CEPAVILA)) {
            listaClases.removeAll(listaClases);
            listaClases.add(Episodio.CLASE_CONSULTAS);
        }
        if (centro.equals(Centro.CEPAREANAS)) {
            listaClases.removeAll(listaClases);
            listaClases.add(Episodio.CLASE_CONSULTAS);
            listaClases.add(Episodio.CLASE_HDIA);
            listaClases.add(Episodio.CLASE_QUI_INTERVENIDO);
            listaClases.add(Episodio.CLASE_QUI_PROGRMACION);
        } else {
            listaClases.removeAll(listaClases);
            listaClases.add(Episodio.CLASE_HOSPITALIZACION);
            listaClases.add(Episodio.CLASE_CONSULTAS);
            listaClases.add(Episodio.CLASE_URGENCIAS);
            listaClases.add(Episodio.CLASE_HDIA);
            listaClases.add(Episodio.CLASE_QUI_INTERVENIDO);
            listaClases.add(Episodio.CLASE_QUI_PROGRMACION);
        }
        return listaClases;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
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

    public EpisodioClase getClase() {
        return clase;
    }

    public Long getClaseLong() {
        return clase.getId();
    }

    public void setClase(EpisodioClase clase) {
        this.clase = clase;
    }

    public Long getSubclase() {
        return subclase;
    }

    public void setSubclase(Long subclase) {
        this.subclase = subclase;
    }

    public LocalDate getFinicio() {
        return finicio;
    }

    public void setFinicio(LocalDate finicio) {
        this.finicio = finicio;
    }

    public String getFechaHoraInicio() {
        String feString = "";
        if (finicio != null) {
            if (!finicio.equals(new Long(0))) {
                feString = fechadma.format(finicio);
                if (hinicio != null) {
                    if (!hinicio.equals(new Long(0))) {
                        feString = feString + " " + Utilidades.getHoraHH_MM(hinicio);
                    }
                }
            }
        }
        return feString;
    }

    public String getFechaHoraFin() {
        String feString = "";
        if (ffinal != null) {
            if (!ffinal.equals(new Long(0))) {
                feString = fechadma.format(ffinal);
                if (hfinal != null) {
                    if (!hfinal.equals(new Long(0))) {
                        feString = feString + " " + Utilidades.getHoraHH_MM(hfinal);
                    }
                }
            }
        }
        return feString;
    }

    public Integer getHinicio() {
        return hinicio;
    }

    public String getHinicioString() {
        if (this.hinicio != null && this.hinicio != 0) {
            return Utilidades.getHoraHH_MM(this.hinicio);
        } else {
            return " ";
        }

    }

    public void setHinicio(Integer hinicio) {
        this.hinicio = hinicio;
    }

    public void setHinicio(String hinicio) {
        this.hinicio = Integer.parseInt(hinicio.replace(":", ""));
    }

    public LocalDate getFfinal() {
        return ffinal;
    }

    public void setFfinal(LocalDate ffinal) {
        this.ffinal = ffinal;
    }

    public Integer getHfinal() {
        return hfinal;
    }

    public String getHfinalString() {
        if (this.hfinal != null && this.hfinal != 0) {
            return Utilidades.getHoraHH_MM(this.hfinal);
        } else {
            return " ";
        }
    }

    public void setHfinal(Integer hfinal) {
        this.hfinal = hfinal;
    }

    public void setHfinal(String hfinal) {
        if (hfinal != null && !hfinal.isEmpty() && hfinal.trim().length() == 5) {
            if (!hfinal.isEmpty()) {
                String cadena = hfinal.replace(":", "");
                this.hfinal = Integer.parseInt(cadena);
            }
        }
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

    public String getServicioCodigo() {
        return servicio.getCodigo();
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Usuario getUserid() {
        return userid;
    }

    public void setUserid(Usuario userid) {
        this.userid = userid;
    }

    public String getCie9() {
        return cie9;
    }

    public void setCie9(String cie9) {
        this.cie9 = cie9;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Variable getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(Variable prestacion) {
        this.prestacion = prestacion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Long getCanal() {
        return canal;
    }

    public void setCanal(Long canal) {
        this.canal = canal;
    }

    public Long getOrigen() {
        return origen;
    }

    public void setOrigen(Long origen) {
        this.origen = origen;
    }

    public Long getMotivo_llegada() {
        return motivo_llegada;
    }

    public void setMotivo_llegada(Long motivo_llegada) {
        this.motivo_llegada = motivo_llegada;
    }

    public Long getMotivo_alta() {
        return motivo_alta;
    }

    public void setMotivo_alta(Long motivo_alta) {
        this.motivo_alta = motivo_alta;
    }

    public Long getTipo_alta() {
        return tipo_alta;
    }

    public void setTipo_alta(Long tipo_alta) {
        this.tipo_alta = tipo_alta;
    }

    public Long getDemora() {
        return demora;
    }

    public void setDemora(Long demora) {
        this.demora = demora;
    }

    public Long getTipo_financiacion() {
        return tipo_financiacion;
    }

    public void setTipo_financiacion(Long tipo_financiacion) {
        this.tipo_financiacion = tipo_financiacion;
    }

    public Long getAlta_resulucion() {
        return alta_resulucion;
    }

    public void setAlta_resulucion(Long alta_resulucion) {
        this.alta_resulucion = alta_resulucion;
    }

    public Long getProcede() {
        return procede;
    }

    public void setProcede(Long procede) {
        this.procede = procede;
    }

    public Long getTriaje() {
        return triaje;
    }

    public void setTriaje(Long triaje) {
        this.triaje = triaje;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public String getAgendaCodigo() {
        if (agenda != null) {
            return agenda.getCodigo();
        } else {
            return "";
        }
    }

    public String getAgendaCodigoHora() {
        String cadenaString = "";
        cadenaString = this.getAgendaCodigo();
        cadenaString = cadenaString.concat(" " + getHinicioString());
        return cadenaString;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Cama getCama() {
        return cama;
    }

    public String getCamaCodigo() {
        if (cama != null) {
            return cama.getCama();
        } else {
            return "";
        }
    }

    public String getCamaCodigoHora() {
        String cadenaString = "";
        if (this != null) {
            if (cama != null && this.getCamaCodigo() != null) {
                cadenaString = this.getCamaCodigo();
            }
            if (this.hinicio != null && this.hinicio != 0) {
                if (getHinicioString() != null) {
                    cadenaString = cadenaString.concat(" " + getHinicioString());
                }
            }
        }
        return cadenaString;
    }

    public String getCamaZona() {
        String zonString = "";
        if (this != null) {
            if (cama != null && this.cama.getZona() != null) {
                zonString = this.cama.getZona().getZona();
            }

        }
        return zonString;
    }

    public void setCama(Cama cama) {
        this.cama = cama;
    }

    public Usuario getResidente() {
        return residente;
    }

    public void setResidente(Usuario residente) {
        this.residente = residente;
    }

    public Long getSubservicio() {
        return subservicio;
    }

    public void setSubservicio(Long subservicio) {
        this.subservicio = subservicio;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getGarante() {
        return garante;
    }

    public void setGarante(String garante) {
        this.garante = garante;
    }

    public String getIcu() {
        return icu;
    }

    public void setIcu(String icu) {
        this.icu = icu;
    }

    public Long getProblema() {
        return problema;
    }

    public void setProblema(Long problema) {
        this.problema = problema;
    }

    public Long getEstado_presencia() {
        return estado_presencia;
    }

    public void setEstado_presencia(Long estado_presencia) {
        this.estado_presencia = estado_presencia;
    }

    public Zona getZona() {
        return zona;
    }

    public String getZonaCodigo() {
        if (zona != null) {
            return zona.getZona();
        } else {
            return "";
        }
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Integer getCitasDadas() {
        return citasDadas;
    }

    public void setCitasDadas(Integer citasDadas) {
        this.citasDadas = citasDadas;
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

    public String getPrestacionDescripcion() {
        if (prestacion != null) {
            return prestacion.getDescripcion();
        } else {
            return "";
        }
    }

    public String getCodigProcedimiento() {
        return codigProcedimiento;
    }

    public void setCodigProcedimiento(String codigProcedimiento) {
        this.codigProcedimiento = codigProcedimiento;
    }

    public String getCodigDiagnostico() {
        return codigDiagnostico;
    }

    public void setCodigDiagnostico(String codigDiagnostico) {
        this.codigDiagnostico = codigDiagnostico;
    }

    public static String getDescripcionClase(Long clase) {
        String descripcion;
        if (clase.equals(Episodio.CLASE_HOSPITALIZACION.getId())) {
            descripcion = " Ingresados ";
        } else if (clase.equals(Episodio.CLASE_CONSULTAS.getId())) {
            descripcion = "  Consultas ";
        } else if (clase.equals(Episodio.CLASE_URGENCIAS.getId())) {
            descripcion = "  Urgencias ";
        } else if (clase.equals(Episodio.CLASE_HDIA.getId())) {
            descripcion = " H.Día ";
        } else {
            descripcion = "   ";
        }
        return descripcion;
    }

    public static EpisodioClase getClaseFromIdlcase(Long clase) {
        EpisodioClase epiclase = null;
        if (clase.equals(Episodio.CLASE_HOSPITALIZACION.getId())) {
            epiclase = CLASE_HOSPITALIZACION;
        } else if (clase.equals(Episodio.CLASE_CONSULTAS.getId())) {
            epiclase = CLASE_CONSULTAS;
        } else if (clase.equals(Episodio.CLASE_URGENCIAS.getId())) {
            epiclase = CLASE_URGENCIAS;
        } else if (clase.equals(Episodio.CLASE_HDIA.getId())) {
            epiclase = CLASE_HDIA;
        } else {

        }
        return epiclase;
    }

    @Override
    public String toString() {
        return "Episodio{" + "numeroOrden=" + numeroOrden + ", id=" + id + ", paciente=" + paciente + ", clase=" + clase + ", subclase=" + subclase + ", finicio=" + finicio + ", hinicio=" + hinicio + ", ffinal=" + ffinal + ", hfinal=" + hfinal + ", centro=" + centro + ", servicio=" + servicio + ", userid=" + userid + ", cie9=" + cie9 + ", observacion=" + observacion + ", prestacion=" + prestacion + ", prioridad=" + prioridad + ", canal=" + canal + ", origen=" + origen + ", motivo_llegada=" + motivo_llegada + ", motivo_alta=" + motivo_alta + ", tipo_alta=" + tipo_alta + ", demora=" + demora + ", tipo_financiacion=" + tipo_financiacion + ", alta_resulucion=" + alta_resulucion + ", procede=" + procede + ", triaje=" + triaje + ", agenda=" + agenda + ", cama=" + cama + ", residente=" + residente + ", subservicio=" + subservicio + ", turno=" + turno + ", dias=" + dias + ", garante=" + garante + ", icu=" + icu + ", problema=" + problema + ", estado_presencia=" + estado_presencia + ", codigProcedimiento=" + codigProcedimiento + ", codigDiagnostico=" + codigDiagnostico + ", zona=" + zona + ", citasDadas=" + citasDadas + ", fechadma=" + fechadma + '}';
    }

    

}
