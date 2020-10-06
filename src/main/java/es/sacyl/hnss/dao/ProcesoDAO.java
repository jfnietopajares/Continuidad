package es.sacyl.hnss.dao;

import com.jnieto.entity.Agenda;
import com.jnieto.entity.Centro;
import com.jnieto.entity.Episodio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Indicador;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.PagiLisReg;
import com.jnieto.entity.Proceso;
import com.jnieto.entity.Servicio;
import com.jnieto.entity.Usuario;
import com.jnieto.entity.Variable;
import com.jnieto.entity.Zona;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Constantes;
import com.jnieto.utilidades.Utilidades;

/**
 * The Class ProcesoDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class ProcesoDAO extends ConexionDAO {

    private String sql;

    private Proceso proceso;

    private static final Logger logger = LogManager.getLogger(ProcesoDAO.class);

    /**
     * Instantiates a new proceso DAO.
     */
    public ProcesoDAO() {
        super();
    }

    /**
     * Gets the proceso resulset.
     *
     * @param resulSet the resul set
     * @return the proceso resulset
     */
    public String getSql(String orden) {
        String sqlOracle = "SELECT ";
        // esta parte solo se usa en los listados paginados
        if (orden.equals("INICIO")) {
            sqlOracle = sqlOracle.concat("   row_number() over (ORDER BY fechaini,horaini) as numeroorden ");
        } else if (orden.equals("INICIODESC")) {
            sqlOracle = sqlOracle.concat("   row_number() over (ORDER BY fechaini desc,horaini desc) as numeroorden ");
        } else {
            sqlOracle = sqlOracle.concat("   row_number() over (ORDER BY fechaini,horaini) as numeroorden ");
        }

        sqlOracle = sqlOracle.concat(
                ",m.ID,m.PACIENTE,m.SUBAMBITO,m.FECHAINI,m.FECHAFIN,m.HORAINI,m.HORAFIN,m.CENTRO,m.SERVICIO,m.SUBSERVICIO,m.USERID "
                + "    ,m.ORIGEN, m.MOTIVO,m.DIAGNOSTICO,m.OBSERVACIONES,m.MEDICO_PETICIONARIO,m.MOTIVO_BAJA,m.SERVICIO_PETICIONARIO  "
                + ", p.id as idpaciente ,p.ape1,p.ape2,p.nombre,p.id as idpaciente,p.fnac,p.sexo,p.tarjeta,p.nss,p.dni,p.telefono,p.movil,p.nbdp,p.cias"
                + "     ,p.direccion,p.codigopostal,p.provincia,p.municipio"
                + "     ,v.descripcion as provinciadescripcion"
                + "    ,w.descripcion as municipiodescripcion, w.id  as  municipioid "
                + "     ,h.nhc"
                + "     ,t.id as idcentro, t.codigo as codigocentro,t.descripcion as descentro, t.nemonico"
                + "              ,pri.id as idcentropri, pri.codigo as codigocentropri,pri.descripcion as descentropri, pri.nemonico as nemonicopri "
                + "     , s.id as idservicio,s.codigo as codigoservicio, s.descripcion as descservicio"
                + "      ,u.userid as usuuserid, u.apellido1 as usuapellido1,u.apellido2 as usuapellido2,u.nombre as usunombre, u.categoria as usucategoria,u.estado as usuestado "
                + "FROM problemas m "
                + "JOIN pacientes p ON p.id=m.paciente"
                + " JOIN historias h ON h.paciente=p.id "
                + " LEFT JOIN provincias v ON v.codigo=p.provincia "
                + " LEFT JOIN municipios w ON w.codigo=p.municipio and w.provincia=p.provincia"
                + " JOIN centros t ON t.id=m.centro  "
                + "  LEFT JOIN centros pri ON pri.id=p.centroprimaria  "
                + "  JOIN servicios s ON s.id=m.servicio"
                + "  LEFT JOIN usuarios U ON u.userid=m.userid "
                + "WHERE  1=1");
        return sqlOracle;
    }

    public Proceso getRegistroResulset(ResultSet resulSet, Paciente paciente, Usuario usuario, Centro centro, Servicio servicio) {
        Proceso proceso = null;
        try {
            proceso = new Proceso();
            proceso.setId(resulSet.getLong("id"));
            if (paciente == null) {
                paciente = new PacienteDAO().getPacientePorId(resulSet.getLong("paciente"), false);
                proceso.setPaciente(paciente);
            } else {
                proceso.setPaciente(paciente);
            }

            proceso.setSubambito(resulSet.getLong("subambito"));
            proceso.setFechaini(Utilidades.getFechaLocalDate(resulSet.getLong("fechaini")));
            if (resulSet.getLong("horaini") != 0) {
                LocalTime hora = LocalTime.of(Utilidades.getHoraInt(resulSet.getLong("horaini")),
                        Utilidades.getMinuto(resulSet.getLong("horaini")));
                proceso.setHoraini(hora);
            }
            if (resulSet.getLong("fechafin") != Constantes.FEHAFIN_DEFECTO) {
                proceso.setFechafin(Utilidades.getFechaLocalDate(resulSet.getLong("fechafin")));
            } else {
                proceso.setFechafin(null);
            }
            if (usuario != null) {
                proceso.setUserid(usuario);
            } else {
                proceso.setUserid(new UsuarioDAO().getUsuarioUserid(resulSet.getString("userid"), Boolean.FALSE));
            }
            proceso.setHorafin(resulSet.getLong("horafin"));
            if (centro == null) {
                proceso.setCentro(new CentroDAO().getRegistroId(resulSet.getLong("centro")));
            } else {
                proceso.setCentro(centro);
            }
            if (servicio == null) {
                proceso.setServicio(new ServiciosDAO().getRegistroId(resulSet.getLong("servicio")));
            } else {
                proceso.setServicio(servicio);
            }
            proceso.setSubservicio(resulSet.getLong("subservicio"));
            proceso.setOrigen(resulSet.getString("origen"));
            proceso.setMotivo(resulSet.getString("motivo"));
            proceso.setDiagnostico(resulSet.getString("diagnostico"));
            proceso.setObservaciones(resulSet.getString("observaciones"));
            proceso.setMedico_peticionario(resulSet.getString("medico_peticionario"));
            proceso.setMotivo_baja(resulSet.getString("motivo_baja"));
            proceso.setServicio_peticionario(resulSet.getString("servicio_peticionario"));
            proceso.setDescripcionSubambito();

            proceso.setIdInformeAsociado(new InformesDAO().getIdInformeProceso(
                    paciente, proceso.getId()));

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return proceso;
    }

    /**
     * Gets the numero procesos paciente.
     *
     * @param paciente the paciente
     * @return the numero procesos paciente
     */
    public int getNumeroProcesosPaciente(Paciente paciente) {
        Connection connection = null;
        int resultado = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) AS casos  FROM problemas WHERE paciente=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                resultado = res.getInt("casos");
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return resultado;
    }

    /**
     * Gets the numero registro proceso.
     *
     * @param proceso the proceso
     * @return entero
     *
     * Controla si un proceso tiene asociados registros. Control de integridad
     */
    public int getNumeroRegistroProceso(Proceso proceso) {
        Connection connection = null;
        int resultado = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) AS casos  FROM registros WHERE problema=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, proceso.getId());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                resultado = res.getInt("casos");
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return resultado;
    }

    /**
     * Gets the datos por id.
     *
     * @param id the id
     * @return the datos por id
     */
    public Proceso getRegistroId(Long id, Paciente paciente) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            /*
            sql = "SELECT ID,PACIENTE,SUBAMBITO,FECHAINI,FECHAFIN,HORAINI,HORAFIN,CENTRO,SERVICIO,SUBSERVICIO,USERID "
                    + ",ORIGEN, MOTIVO,DIAGNOSTICO,OBSERVACIONES,MEDICO_PETICIONARIO,MOTIVO_BAJA,SERVICIO_PETICIONARIO  "
                    + "FROM problemas WHERE id =?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resulSet = statement.executeQuery();
             */

            sql = getSql("INICIO");
            sql = sql.concat(" AND m.id=" + id);

            logger.debug(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            if (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);

                Centro centroParam = CentroDAO.getCentroRs(resulSet, null);

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                proceso = getRegistroResulset(resulSet, pacienteParam, usuarioParam, centroParam, servicioParam);
                //  proceso = getRegistroResulset(resulSet, paciente, null, null, null);
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return proceso;
    }

    public PagiLisReg getPaginacionProceso(Long subambito, LocalDate desde, LocalDate hasta, String orden) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = "SELECT  counr(*) as numero " + "	FROM problemas  e ,  (SELECT @rownum:=0) r"
                        + "	  JOIN pacientes p ON p.id=e.paciente  WHERE  1=1 ";
            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {

                sql = "SELECT  count(*) as numero	" + " FROM problemas p	 WHERE  1=1 ";
            }
            sql = sql.concat(" AND subambito=" + subambito);
            if (desde != null) {
                sql = sql.concat(" AND  fechaini>= " + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND  fechaini<= " + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            }

            logger.debug(sql);

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                contador = resulSet.getInt("numero");
            }
            paginacion.setPrimero(1);
            paginacion.setUltimo(contador);
            paginacion.setRegistrosTotales(contador);
            statement.close();

        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return paginacion;
    }

    public ArrayList<Proceso> getListaProcesos(Long subambito, LocalDate desde, LocalDate hasta, String orden, Paciente paciente, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Proceso> listaProcesos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            /*
            sql = "SELECT ID,PACIENTE,SUBAMBITO,FECHAINI,FECHAFIN,HORAINI,HORAFIN,CENTRO,SERVICIO,SUBSERVICIO,USERID "
                    + ",ORIGEN, MOTIVO,DIAGNOSTICO,OBSERVACIONES,MEDICO_PETICIONARIO,MOTIVO_BAJA,SERVICIO_PETICIONARIO  "
                    + "FROM problemas WHERE  subambito=?" + " AND fechaini>= ? "
                    + " AND (fechaini<=?  )   ";
             */
            sql = getSql(orden);
            sql = sql.concat(" AND subambito=" + subambito);
            if (desde != null) {
                sql = sql.concat(" AND  fechaini>= " + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND  fechaini<= " + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            }
            if (paciente != null) {
                sql = sql.concat(" AND m.paciente=" + paciente.getId());
            }

            if (orden.equals("INICIO")) {
                sql = sql.concat(" ORDER BY FECHAINI,HORAINI ");
            } else if (orden.equals("INICIODESC")) {
                sql = sql.concat(" ORDER BY FECHAINI desc,HORAINI desc ");
            } else {
                sql = sql.concat(" ORDER BY FECHAINI,HORAINI ");
            }
            logger.debug(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            int contador = 0;
            while (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);

                Centro centroParam = CentroDAO.getCentroRs(resulSet, null);

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                proceso = getRegistroResulset(resulSet, pacienteParam, usuarioParam, centroParam, servicioParam);
                proceso.setNumeroOrden(resulSet.getInt("numeroorden"));

                if (subambito.equals(Proceso.SUBAMBITO_PARTOS)) {
                    proceso.setNumeroParto(proceso.getNumeroPartoRegistro());
                }
                if (paginacion != null) {
                    if (paginacion.getDireccion() == 1) {
                        if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                            listaProcesos.add(proceso);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    } else {
                        if (resulSet.getInt("numeroorden") >= paginacion.getAnterior() && resulSet.getInt("numeroorden") < paginacion.getSiguiente()) {
                            listaProcesos.add(proceso);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    }
                } else {
                    listaProcesos.add(proceso);
                }
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }

        return listaProcesos;
    }

    /**
     * Gets the lista procesos paciente.
     *
     * @param paciente the paciente
     * @return the lista procesos paciente
     */
    /*
    public ArrayList<Proceso> oldgetListaProcesosPaciente(Paciente paciente) {
        Connection connection = null;
        ArrayList<Proceso> listaProcesos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT ID,PACIENTE,SUBAMBITO,FECHAINI,FECHAFIN,HORAINI,HORAFIN,CENTRO,SERVICIO,SUBSERVICIO,USERID "
                    + ",ORIGEN, MOTIVO,DIAGNOSTICO,OBSERVACIONES,MEDICO_PETICIONARIO,MOTIVO_BAJA,SERVICIO_PETICIONARIO  "
                    + "FROM problemas WHERE  paciente=? ORDER BY  id  desc  ";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, paciente.getId());
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                proceso = (Proceso) getRegistroResulset(resulSet, paciente,null,null,null);
                listaProcesos.add(proceso);
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaProcesos;
    }
     */
    /**
     * Gets the lista procesos paciente subambito.
     *
     * @param paciente the paciente
     * @param subambito the subambito
     * @return the lista procesos paciente subambito
     */
    /*
    public ArrayList<Proceso> oldgetListaProcesosPacienteSubambito(Paciente paciente, long subambito) {
        Connection connection = null;
        ArrayList<Proceso> listaProcesos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT ID,PACIENTE,SUBAMBITO,FECHAINI,FECHAFIN,HORAINI,HORAFIN,CENTRO,SERVICIO,SUBSERVICIO,USERID "
                    + ",ORIGEN, MOTIVO,DIAGNOSTICO,OBSERVACIONES,MEDICO_PETICIONARIO,MOTIVO_BAJA,SERVICIO_PETICIONARIO  "
                    + "FROM problemas WHERE  paciente=? AND subambito=? ORDER BY  id  desc  ";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, paciente.getId());
            statement.setLong(2, subambito);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                proceso = (Proceso) getRegistroResulset(resulSet, paciente, null, null, null);
                listaProcesos.add(proceso);
            }

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaProcesos;
    }
     */
    /**
     * Gets the altas bajas.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @param subambito the subambito
     * @return the altas bajas
     */
    /*
    public ArrayList<Proceso> aagetAltasBajas(LocalDate desde, LocalDate hasta, long subambito) {
        Connection connection = null;
        ArrayList<Proceso> listaProcesos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT ID,PACIENTE,SUBAMBITO,FECHAINI,FECHAFIN,HORAINI,HORAFIN,CENTRO,SERVICIO,SUBSERVICIO,USERID "
                    + ",ORIGEN, MOTIVO,DIAGNOSTICO,OBSERVACIONES,MEDICO_PETICIONARIO,MOTIVO_BAJA,SERVICIO_PETICIONARIO  "
                    + "FROM problemas WHERE subambito=? AND fechaini>=? "
                    + " AND (fechafin<=? OR fechafin=? ) ORDER BY  id  desc  ";
            sql= getSql("INICIO");
            sql=sql.concat("subambito=? AND fechaini>=?  AND (fechafin<=? OR fechafin=? ) ");
            sql = " ORDER BY  fechaini, horaini ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, subambito);
            statement.setLong(2, Utilidades.getFechaNumeroyyymmddDefecha(desde));
            statement.setLong(3, Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            statement.setLong(4, Constantes.FEHAFIN_DEFECTO);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                proceso = (Proceso) getRegistroResulset(resulSet, null, null, null, null);
                listaProcesos.add(proceso);
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaProcesos;
    }
     */
    /**
     * Graba datos.
     *
     * @param proceso the proceso
     * @return true, if successful
     */
    // @Override
    public boolean grabaDatos(Proceso proceso) {
        // ProcesoNew procesoNew = (ProcesoNew) proceso;
        boolean actualizado = false;
        if (proceso.getId() == 0) {
            actualizado = this.insertaDatos(proceso);
        } else {
            actualizado = this.actualizaDatos(proceso);
        }
        return actualizado;
    }

    /**
     * Gets the proceso activo paciente.
     *
     * @param paciente the paciente
     * @return the proceso activo paciente
     */
    /*
    public Proceso oldgetProcesoActivoPaciente(Paciente paciente) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM  problemas  WHERE paciente=? AND fechafin=?  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            statement.setLong(2, Constantes.FEHAFIN_DEFECTO);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                proceso = (Proceso) getRegistroResulset(resulSet, paciente, null, null, null);
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return proceso;
    }
     */
    /**
     * Gets the numero proceso activo paciente.
     *
     * @param idpaciente the idpaciente
     * @param subambito the subambito
     * @return the numero proceso activo paciente
     */
    public Integer getNumeroProcesoActivoPaciente(Long idpaciente, long subambito) {
        Connection connection = null;
        Integer casos = new Integer(0);
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) as casos  FROM  problemas  WHERE paciente=?  AND fechafin=? AND subamtivo=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idpaciente);
            statement.setLong(2, Constantes.FEHAFIN_DEFECTO);
            statement.setLong(3, subambito);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                casos = resulSet.getInt("casos");
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return casos;
    }

    public Proceso getProcesoPaciSubActivo(Long idpaciente, long subambito) {
        Connection connection = null;
        Proceso proceso = null;
        try {
            connection = super.getConexionBBDD();
            /*
              sql = "SELECT *  FROM  problemas  WHERE paciente=?  AND fechafin=? AND subambito=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idpaciente);
            statement.setLong(2, Constantes.FEHAFIN_DEFECTO);
            statement.setLong(3, subambito);
            ResultSet resulSet = statement.executeQuery();
            
             */
            sql = getSql("INICIO");
            sql = sql.concat(" AND subambito=" + subambito);
            sql = sql.concat(" AND m.paciente=" + idpaciente);
            sql = sql.concat(" AND fechafin=" + Constantes.FEHAFIN_DEFECTO);
            logger.debug(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            if (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);

                Centro centroParam = CentroDAO.getCentroRs(resulSet, null);

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                proceso = getRegistroResulset(resulSet, pacienteParam, usuarioParam, centroParam, servicioParam);
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return proceso;
    }

    /**
     * Gets the todos los procesos cerrados.
     *
     * @param idpaciente the idpaciente
     * @param subambito the subambito
     * @return the todos los procesos cerrados
     */
    public boolean getTodosLosProcesosCerrados(Long idpaciente, Long subambito) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) as casos  FROM  problemas  WHERE paciente=?  AND fechafin=? AND subambito=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idpaciente);
            statement.setLong(2, Constantes.FEHAFIN_DEFECTO);
            statement.setLong(3, subambito);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                if (resulSet.getInt("casos") > 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return true;
    }

    public boolean getprocesosSolapados(Proceso proceso, LocalDate fechaini) {
        Long fecha = Utilidades.getFechaNumeroyyymmddDefecha(fechaini);
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) as casos  FROM  problemas  " + " WHERE paciente=?  AND subambito=?  "
                    + "  AND fechafin !=?  AND (fechaini<=? AND ?<=fechafin )" + " AND id != ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, proceso.getPaciente().getId());
            statement.setLong(2, proceso.getSubambito());
            statement.setLong(3, Constantes.FEHAFIN_DEFECTO);
            statement.setLong(4, fecha);
            statement.setLong(5, fecha);
            statement.setLong(6, proceso.getId());

            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                if (resulSet.getInt("casos") > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return true;
    }

    /**
     * Inserta datos.
     *
     * @param proceso the proceso
     * @return true, if successful
     */
    public boolean insertaDatos(Proceso proceso) {
        Connection connection = null;
        boolean insertado = false;
        try {
            Long id = new UtilidadesDAO().getSiguienteId("problemas");
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  problemas  (id,  paciente,  subambito,  fechaini,  horaini,  fechafin,"
                    + " horafin,  centro,  servicio,    userid,  origen,"
                    + " motivo,  diagnostico,observaciones,motivo_baja,medico_peticionario,servicio_peticionario)  "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            proceso.setId(id);
            statement.setLong(1, proceso.getId());
            statement.setLong(2, proceso.getPaciente().getId());
            statement.setLong(3, proceso.getSubambito());
            statement.setLong(4, Utilidades.getFechaNumeroyyymmddDefecha(proceso.getFechaini()));
            if (proceso.getHoraini() != null) {
                DateTimeFormatter horahhmm = DateTimeFormatter.ofPattern("hhmm");
                String h = horahhmm.format(proceso.getHoraini());
                statement.setLong(5, Long.parseLong(h));
            } else {
                statement.setNull(5, Types.INTEGER);
            }

            if (proceso.getFechafin() != null) {
                statement.setLong(6, Utilidades.getFechaNumeroyyymmddDefecha(proceso.getFechafin()));
            } else {
                statement.setLong(6, Constantes.FEHAFIN_DEFECTO);
            }
            statement.setLong(7, proceso.getHorafin());
            statement.setLong(8, proceso.getCentro().getId());
            statement.setLong(9, proceso.getServicio().getId());
            // statement.setLong(10, proceso.getSubservicio());
            statement.setString(10, proceso.getUserid().getUserid());
            statement.setString(11, proceso.getOrigen());
            statement.setString(12, proceso.getMotivo());
            statement.setString(13, proceso.getDiagnostico());
            statement.setString(14, proceso.getObservaciones());
            statement.setString(15, proceso.getMotivo_baja());
            statement.setString(16, proceso.getMedico_peticionario());
            statement.setString(17, proceso.getServicio_peticionario());
            insertado = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return insertado;
    }

    public Long getInsertaDatos(Proceso proceso) {
        Connection connection = null;
        Long id = null;
        try {
            id = new UtilidadesDAO().getSiguienteId("problemas");
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  problemas  (id,  paciente,  subambito,  fechaini,  horaini,  fechafin,"
                    + " horafin,  centro,  servicio,    userid,  origen,"
                    + " motivo,  diagnostico,observaciones,motivo_baja,medico_peticionario,servicio_peticionario)  "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            proceso.setId(id);
            statement.setLong(1, proceso.getId());
            statement.setLong(2, proceso.getPaciente().getId());
            statement.setLong(3, proceso.getSubambito());
            statement.setLong(4, Utilidades.getFechaNumeroyyymmddDefecha(proceso.getFechaini()));
            if (proceso.getHoraini() != null) {
                DateTimeFormatter horahhmm = DateTimeFormatter.ofPattern("hhmm");
                String h = horahhmm.format(proceso.getHoraini());
                statement.setLong(5, Long.parseLong(h));
            } else {
                statement.setNull(5, Types.INTEGER);
            }

            // statement.setLong(5, proceso.getHoraini());
            if (proceso.getFechafin() != null) {
                statement.setLong(6, Utilidades.getFechaNumeroyyymmddDefecha(proceso.getFechafin()));
            } else {
                statement.setLong(6, Constantes.FEHAFIN_DEFECTO);
            }
            statement.setLong(7, proceso.getHorafin());
            statement.setLong(8, proceso.getCentro().getId());
            statement.setLong(9, proceso.getServicio().getId());
            statement.setString(10, proceso.getUserid().getUserid());
            statement.setString(11, proceso.getOrigen());
            statement.setString(12, proceso.getMotivo());
            statement.setString(13, proceso.getDiagnostico());
            statement.setString(14, proceso.getObservaciones());
            statement.setString(15, proceso.getMotivo_baja());
            statement.setString(16, proceso.getMedico_peticionario());
            statement.setString(17, proceso.getServicio_peticionario());
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return id;
    }

    /**
     * Actualiza datos.
     *
     * @param proceso the proceso
     * @return true, if successful
     */
    public boolean actualizaDatos(Proceso proceso) {
        Connection connection = null;
        boolean actualizado = false;
        try {

            connection = super.getConexionBBDD();

            sql = " UPDATE problemas  SET   fechaini=?,  horaini=?,  fechafin=?,"
                    + " horafin=?,  centro=?,  servicio=?,   userid=?,  origen=?,"
                    + " motivo=?,  diagnostico=?,observaciones=?,motivo_baja=?,medico_peticionario=?,servicio_peticionario=?  "
                    + " WHERE id=? ";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, Utilidades.getFechaNumeroyyymmddDefecha(proceso.getFechaini()));
            if (proceso.getHoraini() != null) {
                DateTimeFormatter horahhmm = DateTimeFormatter.ofPattern("hhmm");
                String h = horahhmm.format(proceso.getHoraini());
                statement.setLong(2, Long.parseLong(h));
            } else {
                statement.setNull(2, Types.INTEGER);
            }

            // statement.setLong(2, proceso.getHoraini());
            if (proceso.getFechafin() != null) {
                statement.setLong(3, Utilidades.getFechaNumeroyyymmddDefecha(proceso.getFechafin()));
            } else {
                statement.setLong(3, Constantes.FEHAFIN_DEFECTO);
            }

            statement.setLong(4, proceso.getHorafin());
            statement.setLong(5, proceso.getCentro().getId());
            statement.setLong(6, proceso.getServicio().getId());
            statement.setString(7, proceso.getUserid().getUserid());
            statement.setString(8, proceso.getOrigen());
            statement.setString(9, proceso.getMotivo());
            statement.setString(10, proceso.getDiagnostico());
            statement.setString(11, proceso.getObservaciones());
            statement.setString(12, proceso.getMotivo_baja());
            statement.setString(13, proceso.getMedico_peticionario());
            statement.setString(14, proceso.getServicio_peticionario());
            statement.setLong(15, proceso.getId());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            actualizado = true;
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    /**
     * Do borra registro.
     *
     * @param proceso the proceso
     * @return true, if successful
     */
    public boolean borraDatos(Object objeto) {
        Proceso proceso = (Proceso) objeto;
        Connection connection = null;
        boolean borrado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM problemas WHERE id= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, proceso.getId());
            borrado = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return borrado;
    }

    /**
     * Gets the indicadores.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @param subambito the subambito
     * @return the indicadores
     */
    public ArrayList<Indicador> getIndicadores(LocalDate desde, LocalDate hasta, long subambito) {
        Connection connection = null;
        ArrayList<Indicador> lista = new ArrayList<>();
        Indicador indicador;
        int mes, ano;
        boolean encontrado;
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT * FROM problemas WHERE subambito=? AND fechaini>=?  "
                    + " AND (fechafin<=? OR fechafin=? ) ORDER BY  id  desc  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, subambito);
            statement.setLong(2, Utilidades.getFechaNumeroyyymmddDefecha(desde));
            statement.setLong(3, Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            statement.setLong(4, Constantes.FEHAFIN_DEFECTO);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                proceso = (Proceso) getRegistroResulset(resulSet, null, null, null, null);
                mes = proceso.getFechaini().getMonth().getValue();
                ano = proceso.getFechaini().getYear();
                Iterator<Indicador> italtas = lista.iterator();
                encontrado = false;
                while (italtas.hasNext()) {
                    indicador = (Indicador) italtas.next();
                    if (indicador.getMes() == mes && indicador.getAno() == ano) {
                        indicador.setAltas(indicador.getAltas() + 1);
                        encontrado = true;
                    }
                }
                if (encontrado == false) {
                    indicador = new Indicador(ano, mes);
                    indicador.setAltas(indicador.getAltas() + 1);
                    lista.add(indicador);
                }
                Iterator<Indicador> itbajas = lista.iterator();
                if (proceso.getFechafin() != null) {
                    mes = proceso.getFechafin().getMonth().getValue();
                    ano = proceso.getFechafin().getYear();
                    encontrado = false;
                    while (itbajas.hasNext()) {
                        indicador = (Indicador) itbajas.next();
                        if (indicador.getMes() == mes && indicador.getAno() == ano) {
                            indicador.setBajas(indicador.getBajas() + 1);
                            encontrado = true;
                        }
                    }
                    if (encontrado == false) {
                        indicador = new Indicador(ano, mes);
                        indicador.setBajas(indicador.getBajas() + 1);
                        lista.add(indicador);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    public boolean getReferenciasExternas(Long id) {
        return false;
    }

    public String getSqlWhere(String cadena) {
        return null;
    }

    public boolean grabaDatos(Object object) {
        return false;
    }

    public boolean actualizaDatos(Object mensajeparam) {
        return false;
    }

    public boolean insertaDatos(Object mensajeparam) {
        return false;
    }

}
