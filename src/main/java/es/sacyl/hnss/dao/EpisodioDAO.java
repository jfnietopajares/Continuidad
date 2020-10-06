package es.sacyl.hnss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Agenda;
import com.jnieto.entity.Cama;
import com.jnieto.entity.Centro;
import com.jnieto.entity.Episodio;
import com.jnieto.entity.EpisodioClase;
import com.jnieto.entity.Municipio;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.PagiLisReg;
import com.jnieto.entity.Provincia;
import com.jnieto.entity.Servicio;
import com.jnieto.entity.Usuario;
import com.jnieto.entity.Variable;
import com.jnieto.entity.Zona;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Constantes;
import com.jnieto.utilidades.Utilidades;
import java.util.LinkedHashMap;
import java.util.Map;

public class EpisodioDAO extends ConexionDAO {

    private Episodio episodio = null;

    private static final Logger logger = LogManager.getLogger(EpisodioDAO.class);

    private String sqlOracle = " e.*"
            + ",p.ape1,p.ape2,p.nombre,p.id as idpaciente,p.fnac,p.sexo,p.tarjeta,p.nss,p.dni,p.telefono,p.movil,p.nbdp,p.cias"
            + " ,p.direccion,p.codigopostal,p.provincia,p.municipio,p.madre"
            + " ,v.descripcion as provinciadescripcion"
            + " ,w.descripcion as municipiodescripcion, w.id  as  municipioid "
            + ",h.nhc"
            + " ,t.id as idcentro, t.codigo as codigocentro,t.descripcion as descentro, t.nemonico, s.id as idservicio,s.codigo as codigoservicio, s.descripcion as descservicio"
            + " , c.cama,c.id as idcama, c.zona,c.estado as camaestado "
            + ",u.userid as usuuserid, u.apellido1 as usuapellido1,u.apellido2 as usuapellido2,u.nombre as usunombre, u.categoria as usucategoria,u.estado as usuestado "
            + " ,l.descripcion as despresta "
            + " ,a.id as idagenda,a.descripcion as desagenda,a.centro as centroagenda,a.codigo as codigoagenda "
            + " ,pri.id as idcentropri, pri.codigo as codigocentropri,pri.descripcion as descentropri, pri.nemonico as nemonicopri"
            + " FROM episodios e	" + " JOIN pacientes p ON p.id=e.paciente  "
            + " JOIN historias h ON h.paciente=p.id " + " JOIN centros t ON t.id=e.centro  "
            + " JOIN servicios s ON s.id=e.servicio" + " LEFT JOIN camas c  ON c.id=e.idcama  "
            + " LEFT JOIN usuarios U ON u.userid=e.userid " + " LEFT JOIN catalogo l ON l.id = e.prestacion "
            + " LEFT JOIN agendas a ON a.id=e.agenda "
            + " LEFT JOIN provincias v ON v.codigo=p.provincia "
            + " LEFT JOIN municipios w ON (w.id=p.municipio and w.provincia=p.provincia)"
            + "  LEFT JOIN centros pri ON pri.id=p.centroprimaria   "
            + " WHERE  1=1 ";

    private String sqlMysql = sql = "SELECT  @rownum:=@rownum+1  as numeroorden ,e.*,p.ape1,p.ape2,p.nombre"
            + "	FROM episodios e ,  (SELECT @rownum:=0) r" + "	  JOIN pacientes p ON p.id=e.paciente  WHERE  1=1 ";

    public static String ORDENPACIENTE="PACIENTE";
     public static String ORDENFECHAHORA="FECHAHORA";
      public static String ORDENFECHAHORADESC="FECHAHORADESC";
       public static String ORDENCLASEPACIENTE="CLASEPACIENTE";
      
       public static String FINALIZADOSI="S";
       public static String FINALIZADONO="N";
               
    public EpisodioDAO() {
        super();
    }

    public boolean grabaDatos(Episodio episodio) {
        this.episodio = episodio;
        boolean actualizado = false;
        if (episodio.getId().equals(new Long(0))) {
            actualizado = this.insertaDatos(episodio);
        } else {
            actualizado = this.actualizaDatos(episodio);
        }
        return actualizado;
    }

    public boolean insertaDatos(Episodio episodio) {
        Connection connection = null;
        this.episodio = episodio;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("episodios");
            episodio.setId(id);
            sql = " INSERT INTO episodios (id,paciente,clase,finicio,hinicio,ffinal,hfinal, centro,servicio,userid, observacion, canal, icu ,idcama,subclase,cie9) "
                    + "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?, ?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, episodio.getId());
            statement.setLong(2, episodio.getPaciente().getId());
            statement.setLong(3, episodio.getClase().getId());
            statement.setLong(4, Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFinicio()));
            statement.setLong(5, episodio.getHinicio());
            if (episodio.getFfinal() == null) {
                statement.setLong(6, Constantes.FEHAFIN_DEFECTO);
            } else {
                statement.setLong(6, Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFfinal()));
            }

            if (episodio.getHfinal() == null) {
                statement.setLong(7, new Long(0));
            } else {
                statement.setLong(7, episodio.getHfinal());
            }
            statement.setLong(8, episodio.getCentro().getId());
            statement.setLong(9, episodio.getServicio().getId());
            statement.setString(10, episodio.getUserid().getUserid());
            if (episodio.getObservacion() != null) {
                statement.setString(11, episodio.getObservacion());
            } else {
                statement.setString(11, null);
            }
            statement.setLong(12, episodio.getCanal());
            // icu se inventa un icu y pone el mismo numero que el id
            statement.setString(13, episodio.getId().toString());

            if (episodio.getCama() != null) {
                statement.setLong(14, episodio.getCama().getId());
            } else {
                statement.setNull(14, java.sql.Types.DOUBLE);
            }

            if (episodio.getSubclase() != null) {
                statement.setLong(15, episodio.getSubclase());
            } else {
                statement.setNull(15, Types.DOUBLE);
            }
            if (episodio.getCie9() != null) {
                statement.setString(16, episodio.getCie9());
            } else {
                statement.setString(16, null);
            }

            logger.debug(
                    " INSERT INTO episodios (id,paciente,clase,finicio,hinicio,ffinal,hfinal, centro,servicio,userid, observacion, canal, icu,subclase ) VALUES ( "
                    + episodio.getId() + "," + episodio.getPaciente().getId() + "," + episodio.getClase().getId() + ","
                    + Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFinicio()) + ","
                    + episodio.getHinicio() + ","
                    + Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFfinal()) + "," + episodio.getHfinal()
                    + "," + episodio.getCentro().getId() + "," + episodio.getServicio().getId() + ",'"
                    + episodio.getUserid().getUserid() + "','" + episodio.getObservacion() + "'," + episodio.getCanal() + "", +episodio.getId() + ", " + episodio.getSubclase() + "  )  ");
            insertado = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            logger.error(
                    " INSERT INTO episodios (id,paciente,clase,finicio,hinicio,ffinal,hfinal, centro,servicio,userid, observacion, canal, icu,subclase ) VALUES ( "
                    + episodio.getId() + "," + episodio.getPaciente().getId() + "," + episodio.getClase().getId() + ","
                    + Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFinicio()) + ","
                    + episodio.getHinicio() + ","
                    + Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFfinal()) + "," + episodio.getHfinal()
                    + "," + episodio.getCentro().getId() + "," + episodio.getServicio().getId() + ",'"
                    + episodio.getUserid().getUserid() + "','" + episodio.getObservacion() + "'," + episodio.getCanal() + "", +episodio.getId() + ", " + episodio.getSubclase() + "  )  ", e);

        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return insertado;
    }

    public boolean actualizaDatos(Episodio episodio) {
        Connection connection = null;
        this.episodio = episodio;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   episodios SET paciente=?, clase=?, finicio=?, hinicio=?"
                    + " , ffinal=?, hfinal=?,centro=?,servicio=?,userid=?, observacion=? , canal=?, idcama=? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, episodio.getPaciente().getId());
            statement.setLong(2, episodio.getClase().getId());
            statement.setLong(3, Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFinicio()));
            statement.setLong(4, episodio.getHinicio());
            statement.setLong(5, Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFfinal()));
            if (episodio.getHfinal() != null) {
                statement.setLong(6, episodio.getHfinal());
            } else {
                statement.setLong(6, new Long(0));
            }
            statement.setLong(7, episodio.getCentro().getId());
            statement.setLong(8, episodio.getServicio().getId());

            statement.setString(9, episodio.getUserid().getUserid());
            statement.setString(10, episodio.getObservacion());
            statement.setLong(11, episodio.getCanal());
            if (episodio.getCama() != null) {
                statement.setLong(12, episodio.getCama().getId());
            } else {
                statement.setNull(12, Types.INTEGER);
            }

            statement.setLong(13, episodio.getId());
            logger.debug(" UPDATE   episodios SET paciente=?, clase=?, finicio=?, hinicio=?"
                    + " , ffinal=?, hfinal=?,centro=?,servicio=?,usuario=? WHERE id=? ");

            actualizado = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            logger.error(" ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    public boolean doUpdateEstadoPresencia(Episodio episodio, int valor) {
        Connection connection = null;
        this.episodio = episodio;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   episodios SET estado_presencia=? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, valor);
            statement.setLong(2, episodio.getId());

            logger.debug(" UPDATE   episodios SET estado_presencia=" + valor + "  WHERE id= " + episodio.getId());

            actualizado = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            logger.error(" UPDATE   episodios SET estado_presencia=" + valor + "  WHERE id= " + episodio.getId(), e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }
 public boolean doUpdateInresoRNSano(Episodio episodioParam) {
        Connection connection = null;

        this.episodio = episodioParam;

        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   episodios SET finicio=?, ffinal=? ,idcama=? , hfinal=0 WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1,Utilidades.getFechaNumeroyyymmddDefecha( episodioParam.getFinicio()));
            statement.setLong(2, Constantes.FEHAFIN_DEFECTO);
            statement.setLong(3, episodioParam.getCama().getId());
            statement.setLong(4, episodio.getId());

            logger.debug(" UPDATE   episodios  finicio="+Utilidades.getFechaNumeroyyymmddDefecha( episodioParam.getFinicio())
                    +" , ffinal="+Constantes.FEHAFIN_DEFECTO+",idcama="+ episodioParam.getCama().getId()+" , hfinal=0  WHERE id=" + episodio.getId());

            actualizado = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            logger.debug(" UPDATE   episodios  finicio="+Utilidades.getFechaNumeroyyymmddDefecha( episodioParam.getFinicio())
                    +" , ffinal="+Constantes.FEHAFIN_DEFECTO+",idcama="+ episodioParam.getCama().getId()+" , hfinal=0  WHERE id=" + episodio.getId(), e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }
    public boolean doUpdateIcuHisHdia(Episodio episodioParam, String valor) {
        Connection connection = null;

        this.episodio = episodioParam;

        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   episodios SET icu=? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, valor);
            statement.setLong(2, episodio.getId());

            logger.debug(" UPDATE   episodios SET icu='" + valor + "'  WHERE id= " + episodio.getId());

            actualizado = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            logger.debug(" UPDATE   episodios SET icu='" + valor + "'  WHERE id= " + episodio.getId(), e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    public boolean borraDatos(Episodio episodio) {
        Connection connection = null;
        boolean borrado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM episodios WHERE id=?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, episodio.getId());
            borrado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug("  DELETE FROM episodios WHERE id=? =" + episodio.getId());
        } catch (SQLException e) {
            logger.error("  DELETE FROM episodios WHERE id=? =" + episodio.getId(), e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return borrado;
    }

    public boolean doDarAltaHospitalizacion(Episodio episodio) {
        Connection connection = null;
        this.episodio = episodio;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   episodios SET  ffinal=?, hfinal=?,observacion=?,motivo_alta=? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFfinal()));
            statement.setLong(2, episodio.getHfinal());
            statement.setString(3, episodio.getObservacion());
            statement.setLong(4, episodio.getMotivo_alta());
            statement.setLong(5, episodio.getId());
            logger.debug(" UPDATE   episodios SET  ffinal="+Utilidades.getFechaNumeroyyymmddDefecha(episodio.getFfinal())
                    +", hfinal="+episodio.getHfinal()+ ",motivo_alta="+episodio.getMotivo_alta()+" WHERE id= " + episodio.getId() );

            actualizado = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    public Episodio getRegistroResulset(ResultSet res, Paciente paciente, Centro centro, Servicio servicio,
            Agenda agenda, Zona zona, Cama cama, Usuario usuario, Variable prestacion) {
        Episodio epi = new Episodio();
        try {
            epi.setId(res.getLong("id"));
            if (paciente == null) {
                epi.setPaciente(new PacienteDAO().getPacientePorId(res.getLong("paciente"), false));
            } else {
                epi.setPaciente(paciente);
            }

            epi.setClase(new EpisodioClaseDAO().getRegistroPorid(res.getLong("clase")));

            if (res.getLong("finicio") != Constantes.FEHAFIN_CERO) {
                epi.setFinicio(Utilidades.getFechaLocalDate(res.getLong("finicio")));
            } else {
                epi.setFinicio(null);
            }

            if (res.getLong("ffinal") != Constantes.FEHAFIN_CERO
                    && res.getLong("ffinal") != Constantes.FEHAFIN_DEFECTO) {
                epi.setFfinal(Utilidades.getFechaLocalDate(res.getLong("ffinal")));
            } else {
                epi.setFfinal(null);
            }
            if (res.getLong("hinicio") > 0) {
                epi.setHinicio(Utilidades.getHoraHH_MM(res.getLong("hinicio")));
            } else {
                epi.setHinicio(res.getLong("hinicio"));
            }

            if (res.getLong("hfinal") > 0) {
                epi.setHfinal(Utilidades.getHoraHH_MM(res.getLong("hfinal")));
            } else {
                epi.setHfinal(res.getLong("hfinal"));
            }

            if (centro == null) {
                epi.setCentro(new CentroDAO().getRegistroId(res.getLong("centro")));
            } else {
                epi.setCentro(centro);
            }
            if (servicio == null) {
                epi.setServicio(new ServiciosDAO().getRegistroId(res.getLong("servicio")));
            } else {
                epi.setServicio(servicio);
            }
            if (usuario == null) {
                epi.setUserid(new UsuarioDAO().getUsuarioUserid(res.getString("userid"), false));
            } else {
                epi.setUserid(usuario);
            }

            epi.setCie9(res.getString("cie9"));
            epi.setObservacion(res.getString("observacion"));

            if (cama != null) {
                epi.setCama(cama);
            } else {
                Cama caman = new Cama();
                caman = new CamaDAO().getRegistrPorId(res.getLong("idcama"), zona);
                epi.setCama(caman);
                if (caman.getZona() != null) {
                    epi.setZona(caman.getZona());
                }
            }
            if (zona != null) {
                epi.setZona(zona);
            }
            // el campo zona no esta en la bbde

            if (agenda != null) {
                epi.setAgenda(agenda);
            } else if (res.getLong("agenda") > 0) {
                epi.setAgenda(new AgendaDAO().getRegistrPorId(res.getLong("agenda")));
            }

            if (prestacion == null) {
                if (res.getLong("prestacion") > 0) {
                    Variable prestacionn = new Variable();
                    prestacionn = new CatalogoDAO().getRegistroPorId(res.getLong("prestacion"));
                    epi.setPrestacion(prestacionn);
                }
            } else {
                epi.setPrestacion(prestacion);
            }

            // private Usuario residente;
            // private Long subservicio;
            // private String turno;
            // private String dias;
            // private String garante;
            epi.setIcu(res.getString("icu"));
//			private Long problema;
            // private Long estado_presencia;

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return epi;
    }

    public String getSqlWhere(Long clase, Centro centro, Servicio servicio, Agenda agenda, LocalDate desde,
            LocalDate hasta, String finalizado, Variable prestacion, String cias, Zona zona, Provincia provincia, Municipio municipio) {
        String sql = "";
        if (clase!=null  &&  !clase.equals(new Long(0))) {
            sql = " AND e.clase=" + clase;
        }
        if (centro != null) {
            sql = sql.concat(" AND e.centro=" + centro.getId());
        }
        if (servicio != null) {
            sql = sql.concat(" AND e.servicio=" + servicio.getId());
        }
        if (agenda != null && agenda.getId() != new Long( 0) ) {
            sql = sql.concat(" AND e.agenda=" + agenda.getId());
        }
        if (prestacion != null) {
            sql = sql.concat(" AND e.prestacion=" + prestacion.getItem());
        }

        if (zona != null && zona.getId() != null) {
            sql = sql.concat(" AND c.zona=" + zona.getId());
        }
        if (cias != null) {
            sql = sql.concat(" AND p.cias='" + cias + "'");
        }
    
        if (clase!=null &&  clase.equals(Episodio.CLASE_HOSPITALIZACION.getId()) && cias == null) {
            if (desde != null) {
                sql = sql.concat(" AND e.finicio<=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND e.ffinal>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            } else {
                sql = sql.concat(" AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO);
            }
        } else if (clase!=null && clase.equals(Episodio.CLASE_HOSPITALIZACION.getId()) && cias != null) {
            if (desde != null) {
                sql = sql.concat(" AND e.finicio>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND e.ffinal<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            } else {
                sql = sql.concat(" AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO);
            }
        } else {
            if (desde != null) {
                sql = sql.concat(" AND e.finicio>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND e.finicio<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            }
        }
        if (finalizado != null && finalizado.equals("S")) {
            sql = sql.concat(" AND e.ffinal!=" + Constantes.FEHAFIN_DEFECTO);
        }
        if (finalizado != null && finalizado.equals("N")) {
            sql = sql.concat(" AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO);
        }

        if (provincia!=null)
              sql = sql.concat(" AND p.provincia=" + Constantes.FEHAFIN_DEFECTO);
        
         if (municipio != null && provincia != null) {
            sql = sql.concat(" AND  (p.municipio=" + municipio.getId() + " AND p.provincia=" + provincia.getCodigo() + ")");
        }
        return sql;
    }

    public String getSqlWhere(ArrayList<Variable> listaItemPrestaciones) {
        String sql = "";
        String cadena = "";
        if (listaItemPrestaciones != null) {
            for (Variable prestacion : listaItemPrestaciones) {
                if (cadena.length() > 1) {
                    cadena = cadena.concat(",");
                }

                cadena = cadena.concat(prestacion.getItem().toString());
            }
            if (cadena.length() > 1) {
                sql = " AND prestacion in (" + cadena + ")";
            }
        }
        return sql;
    }

    public PagiLisReg getPaginacionEpisodio(Long clase, Centro centro, Servicio servicio, Agenda agenda, Zona zona,
            LocalDate desde, LocalDate hasta, String finalizado, Variable prestacion, Paciente paciente, String cias, Provincia  provincia, Municipio municipio) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = "SELECT  counr(*) as numero " + "	FROM episodios e ,  (SELECT @rownum:=0) r"
                        + "	  JOIN pacientes p ON p.id=e.paciente  WHERE  1=1 ";
            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {
                if (zona != null && agenda == null) {
                    sql = " SELECT  count(*) as numero	 FROM episodios e  JOIN camas c ON e.idcama=c.id   WHERE  1=1 ";
                } else if (zona == null && agenda != null) {
                    sql = "SELECT  count(*) as numero	 FROM episodios e  JOIN agendas a ON e.agenda=a.id   WHERE  1=1";
                } else if (zona != null && agenda != null) {
                    sql = "SELECT  count(*) as numero	 FROM episodios e    JOIN camas c ON e.idcama=c.id  "
                            + " JOIN agendas a ON e.agenda=a.id   WHERE  1=1";
                } else {
                    sql = "SELECT  count(*) as numero	" + " FROM episodios e	 WHERE  1=1 ";
                }
            }

            sql = sql.concat(
                    getSqlWhere(clase, centro, servicio, agenda, desde, hasta, finalizado, prestacion, cias, zona, provincia, municipio));

            if (paciente != null) {
                sql = sql.concat(" AND paciente=" + paciente.getId());
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

    public Variable getPrestacionRs(ResultSet resulSet, Variable prestacionParam) {
        Variable prestacion = null;
        if (prestacionParam != null) {
            prestacion = prestacionParam;
        } else {
            try {
                prestacion = new Variable();
                prestacion.setItem(resulSet.getLong("prestacion"));
                prestacion.setDescripcion(resulSet.getString("despresta"));
            } catch (Exception e) {
                logger.error(NotificacionInfo.SQLERRORRESULSET, e);
            }
        }
        return prestacion;
    }

    /*
    public Centro getCentroRs(ResultSet resulSet, Centro centroParam) {
        Centro centro = null;
        try {
            if (centroParam == null) {
                centro = new Centro(resulSet.getLong("idcentro"), resulSet.getString("codigocentro"),
                        resulSet.getString("descentro"), resulSet.getString("nemonico"));
            } else {
                centro = centroParam;
            }
        } catch (Exception e) {
            logger.error(NotificacionInfo.SQLERRORRESULSET, e);
        }
        return centro;
    }
     */
 /*
    public Agenda getAgendaRs(ResultSet resulSet, Agenda agednaParam, Centro centro, Servicio servicio) {
        Agenda agenda = null;
        try {
            if (agednaParam == null) {
                agenda = new Agenda();
                agenda.setId(resulSet.getLong("idagenda"));
                agenda.setDescripcion(resulSet.getString("desagenda"));
                agenda.setCodigo(resulSet.getString("codigoagenda"));
                agenda.setCentro(centro);
                agenda.setServicio(servicio);
            } else {
                agenda = agednaParam;
            }
        } catch (Exception e) {
            logger.error(NotificacionInfo.SQLERRORRESULSET, e);
        }
        return agenda;
    }
     */
 /*
    public Servicio getServicioRs(ResultSet resulSet, Servicio servicioParam) {
        Servicio servicio = null;
        if (servicioParam != null) {
            servicio = servicioParam;
        } else {
            try {
                servicio = new Servicio();
                servicio.setId(resulSet.getLong("idservicio"));
                servicio.setCodigo(resulSet.getString("codigoservicio"));
                servicio.setDescripcion(resulSet.getString("descservicio"));
            } catch (Exception e) {
                logger.error(NotificacionInfo.SQLERRORRESULSET, e);
            }
        }
        return servicio;
    }

     */
    /**
     *
     * @param clase Es el id de la clase de episodio a seleccionar. Si null
     * todas las clases
     * @param centro Centro que se quiere seleccionar. Si null todos los centros
     * @param servicio Servicio de los episodios a seleccionar . Si null todos
     * los servicios
     * @param agenda Agenda de los episodios a selecionar. Si null todas las
     * agendas
     * @param zona Zona de los episodios a seleccionar. Si null todas las zonas
     * @param desde Desde la fecha. Desde la fecha de inicio del episodio
     * @param hasta Hasta la fecha.Hasta la fecha de inicio del episodio
     * @param paginacion. Si no es null hace una selec paginada en funciona de
     * los valores de paginacion
     * @param orden. Tipo de orden
     * @param idEpi. Id del episodio a selecionar
     * @param finalizado. Si finalizado=S fechafin<>99999999 Si finalizado =N
     * fechafin=99999999
     * @param prestacion. Tipo de pestacion a seleccionar. Si null todas las
     * prestaciones
     * @param paciente. Paciente a seleccionar episodios. Si null todos los
     * pacientess
     * @return
     */
    public ArrayList<Episodio> getListaEpisodiossPaginados(Long clase, Centro centro, Servicio servicio, Agenda agenda,
            Zona zona, LocalDate desde, LocalDate hasta, PagiLisReg paginacion, String orden, Long idEpi,
            String finalizado, Variable prestacion, Paciente paciente, String cias, Provincia provincia,Municipio municipio) {
        Connection connection = null;
        ArrayList<Episodio> listaEpisodios = new ArrayList<>();
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            if (orden.equals(ORDENPACIENTE)) {
                sql = "SELECT  row_number() over (ORDER BY p.ape1,p.ape2,p.nombre) as numeroorden ," + sqlOracle;
            } else if (orden.equals(ORDENFECHAHORA)) {
                sql = "SELECT  row_number() over (ORDER BY e.finicio,e.hinicio) as numeroorden ," + sqlOracle;
            } else if (orden.equals(ORDENFECHAHORADESC)) {
                sql = "SELECT  row_number() over (ORDER BY e.finicio DESC,e.hinicio DESC) as numeroorden ," + sqlOracle;
             } else if (orden.equals(ORDENCLASEPACIENTE)) {
                sql = "SELECT  row_number() over (ORDER BY e.clase,p.ape1,p.ape2,p.nombre) as numeroorden ," + sqlOracle;
            } 
            else {
                sql = "SELECT  row_number() over (ORDER BY e.id) as numeroorden ," + sqlOracle;
            }
            if (idEpi != null) {
                sql = sql.concat(" AND id=" + idEpi);
            } else {
                sql = sql.concat(
                        getSqlWhere(clase, centro, servicio, agenda, desde, hasta, finalizado, prestacion, cias, zona,provincia,municipio));
            }
            if (paciente != null) {
                sql = sql.concat(" AND e.paciente=" + paciente.getId());
            }
             if (orden.equals(ORDENPACIENTE)) {
                sql = sql.concat(" ORDER BY p.ape1,p.ape2,p.nombre ");
            } else if (orden.equals(ORDENFECHAHORA)) {
               sql = sql.concat(" ORDER BY e.finicio,e.hinicio ");
            } else if (orden.equals(ORDENFECHAHORADESC)) {
                sql = sql.concat(" ORDER BY e.finicio DESC ");
             } else if (orden.equals(ORDENCLASEPACIENTE)) {
                 sql = sql.concat(" ORDER BY e.clase,p.ape1,p.ape2,p.nombre ");
            } 
            else {
                sql = sql.concat(" ORDER BY finicio,hinicio ");
            }
            logger.debug(sql);

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            episodio = new Episodio();
            while (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, paciente);
                Centro centroPrimariaParam = CentroDAO.getCentroPrimariaRs(resulSet, null);
                pacienteParam.setCentroprimaria(centroPrimariaParam);

                Cama cama = CamaDAO.getCamaRs(resulSet, zona);

                Usuario usuario = UsuarioDAO.getUsuairoRs(resulSet, null);

                Variable prestacionParam = getPrestacionRs(resulSet, prestacion);

                Centro centroparam = CentroDAO.getCentroRs(resulSet, centro);

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, servicio);

                Agenda agendaParam = AgendaDAO.getAgendaRs(resulSet, agenda, centro, servicio);

                episodio = getRegistroResulset(resulSet, pacienteParam, centroparam, servicioParam, agendaParam, zona,
                        cama, usuario, prestacionParam);

                episodio.setNumeroOrden(resulSet.getInt("numeroorden"));

                if (paginacion != null) {
                    if (paginacion.getDireccion() == 1) {
                        if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                            listaEpisodios.add(episodio);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    } else {
                        if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                            listaEpisodios.add(episodio);

                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    }
                } else {
                    listaEpisodios.add(episodio);
                }
            }
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
        return listaEpisodios;
    }

    public Map<Long,Episodio> getMapEpisodiossPaginados(Long clase, Centro centro, Servicio servicio, Agenda agenda,
            Zona zona, LocalDate desde, LocalDate hasta, PagiLisReg paginacion, String orden, Long idEpi,
            String finalizado, Variable prestacion, Paciente paciente, String cias, Provincia provincia,Municipio municipio) {
        Connection connection = null;
        Map<Long,Episodio> listaEpisodios = new LinkedHashMap<>();
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            if (orden.equals(ORDENPACIENTE)) {
                sql = "SELECT  row_number() over (ORDER BY p.ape1,p.ape2,p.nombre) as numeroorden ," + sqlOracle;
            } else if (orden.equals(ORDENFECHAHORA)) {
                sql = "SELECT  row_number() over (ORDER BY e.finicio,e.hinicio) as numeroorden ," + sqlOracle;
            } else if (orden.equals(ORDENFECHAHORADESC)) {
                sql = "SELECT  row_number() over (ORDER BY e.finicio DESC,e.hinicio DESC) as numeroorden ," + sqlOracle;
             } else if (orden.equals(ORDENCLASEPACIENTE)) {
                sql = "SELECT  row_number() over (ORDER BY e.clase,p.ape1,p.ape2,p.nombre) as numeroorden ," + sqlOracle;
            } 
            else {
                sql = "SELECT  row_number() over (ORDER BY e.id) as numeroorden ," + sqlOracle;
            }
            if (idEpi != null) {
                sql = sql.concat(" AND id=" + idEpi);
            } else {
                sql = sql.concat(
                        getSqlWhere(clase, centro, servicio, agenda, desde, hasta, finalizado, prestacion, cias, zona,provincia,municipio));
            }
            if (paciente != null) {
                sql = sql.concat(" AND e.paciente=" + paciente.getId());
            }
             if (orden.equals(ORDENPACIENTE)) {
                sql = sql.concat(" ORDER BY p.ape1,p.ape2,p.nombre ");
            } else if (orden.equals(ORDENFECHAHORA)) {
               sql = sql.concat(" ORDER BY e.finicio,e.hinicio ");
            } else if (orden.equals(ORDENFECHAHORADESC)) {
                sql = sql.concat(" ORDER BY e.finicio DESC ");
             } else if (orden.equals(ORDENCLASEPACIENTE)) {
                 sql = sql.concat(" ORDER BY e.clase,p.ape1,p.ape2,p.nombre ");
            } 
            else {
                sql = sql.concat(" ORDER BY finicio,hinicio ");
            }
            logger.debug(sql);

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            episodio = new Episodio();
            while (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, paciente);
                Centro centroPrimariaParam = CentroDAO.getCentroPrimariaRs(resulSet, null);
                pacienteParam.setCentroprimaria(centroPrimariaParam);

                Cama cama = CamaDAO.getCamaRs(resulSet, zona);

                Usuario usuario = UsuarioDAO.getUsuairoRs(resulSet, null);

                Variable prestacionParam = getPrestacionRs(resulSet, prestacion);

                Centro centroparam = CentroDAO.getCentroRs(resulSet, centro);

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, servicio);

                Agenda agendaParam = AgendaDAO.getAgendaRs(resulSet, agenda, centro, servicio);

                episodio = getRegistroResulset(resulSet, pacienteParam, centroparam, servicioParam, agendaParam, zona,
                        cama, usuario, prestacionParam);

                episodio.setNumeroOrden(resulSet.getInt("numeroorden"));

                if (paginacion != null) {
                    if (paginacion.getDireccion() == 1) {
                        if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                            listaEpisodios.put(episodio.getId(),episodio);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    } else {
                        if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                            listaEpisodios.put(episodio.getId(),episodio);

                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    }
                } else {
                     listaEpisodios.put(episodio.getId(),episodio);
                }
            }
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
        return listaEpisodios;
    }

    
    public ArrayList<Episodio> getListaEpisodiosCitas(Long clase, Centro centro, Servicio servicio, Agenda agenda,
            LocalDate desde, ArrayList<Variable> listaItemPrestaciones, LocalDate unaFecha) {

        ArrayList<Episodio> calendarioCitas = new ArrayList<Episodio>();
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            if (clase.equals(Episodio.CLASE_CONSULTAS.getId())) {
                String sqlCondicion = getSqlWhere(clase, centro, servicio, agenda, null, null, null, null, null, null,null,null);
                if (desde != null) {
                    sqlCondicion = sqlCondicion
                            .concat(" AND finicio> " + Utilidades.getFechaNumeroyyymmddDefecha(desde));
                }
                if (unaFecha != null) {
                    sqlCondicion = sqlCondicion
                            .concat(" AND finicio= " + Utilidades.getFechaNumeroyyymmddDefecha(unaFecha));
                }

                sqlCondicion = sqlCondicion.concat(getSqlWhere(listaItemPrestaciones));
                if (agenda == null) {
                    sql = " Select e.prestacion,e.finicio,c.descripcion,count(*) as citados " + " FROM  episodios e "
                            + " JOIN catalogo c ON e.prestacion=c.id " + " WHERE ffinal=99999999  ";
                    sql = sql.concat(sqlCondicion);
                    sql = sql
                            .concat(" GROUP BY finicio,prestacion,descripcion ORDER by finicio,prestacion,descripcion");
                }
            } else {
                sql = "SELECT z.zona,z.id,e.finicio, count(*) as citados  FROM  episodios e "
                        + " LEFT  JOIN camas c ON c.id=e.idcama " + "  JOIN zonas z ON z.id=c.zona" + " WHERE  ffinal="
                        + Constantes.FEHAFIN_DEFECTO + " " + "  AND e.clase=" + clase
                        + " AND e.centro=1 AND e. finicio= " + Utilidades.getFechaNumeroyyymmddDefecha(unaFecha)
                        + "  GROUP BY z.zona,z.id,e.finicio";
            }
            logger.debug(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            episodio = new Episodio();

            while (resulSet.next()) {
                Episodio calendario = new Episodio();
                calendario.setCentro(centro);
                calendario.setFinicio(Utilidades.getFechaLocalDate(resulSet.getLong("finicio")));

                if (clase.equals(Episodio.CLASE_CONSULTAS.getId())) {
                    calendario.setPrestacion(
                            new Variable(resulSet.getLong("prestacion"), resulSet.getString("descripcion")));
                } else if (clase.equals(Episodio.CLASE_HDIA.getId())) {
                    calendario.setZona(new Zona(resulSet.getLong("id"), resulSet.getString("zona")));
                }

                calendario.setCitasDadas(resulSet.getInt("citados"));
                calendarioCitas.add(calendario);
            }
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

        return calendarioCitas;
    }

    public ArrayList<Episodio> getListaMadresIngresadas() {
        Connection connection = null;
        ArrayList<Episodio> listaepi = new ArrayList<Episodio>();
        try {
            connection = super.getConexionBBDD();
            /*
            unidades de las madres Zona ZONA_UE6D 
            y con el covid 
            
             */
 /*
            sql = "select  e.*,c.cama,c.zona  "
                    + " FROM episodios e JOIN pacientes m ON e.paciente=m.id  JOIN camas c ON e.idcama=c.id	"
                    + " WHERE e.clase=" + Episodio.CLASE_HOSPITALIZACION.getId() + " AND e.ffinal="
                    + Constantes.FEHAFIN_DEFECTO + " AND ( ZONA=" + Zona.ZONA_UE6D.getId() + "OR zona=" + Zona.ZONA_UE4D.getId() + "  ) AND ( servicio="
                    + Servicio.SERVICIO_OBSTERICIA.getId() + " OR servicio=" + Servicio.SERVICIO_GINECOLOGIA.getId()
                    + ")";
             */
            sql = "SELECT  row_number() over (ORDER BY e.id) as numeroorden ," + sqlOracle;
            sql = sql.concat(" AND  e.clase=" + Episodio.CLASE_HOSPITALIZACION.getId() + " AND e.ffinal="
                    + Constantes.FEHAFIN_DEFECTO + " AND ( c.ZONA=" + Zona.ZONA_UE6D.getId() + " OR c.zona=" + Zona.ZONA_UE4P.getId() + "  ) "
                    + " AND ( e.servicio="
                    + Servicio.SERVICIO_OBSTERICIA.getId() + " OR e.servicio=" + Servicio.SERVICIO_GINECOLOGIA.getId()
                    + ")");

            logger.debug(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            while (resulSet.next()) {
                episodio = new Episodio();
                Zona zona = new Zona();
                zona.setId(resulSet.getLong("zona"));

                //      Episodio epi = getRegistroResulset(resulSet, null, Centro.CENTRO_DEFECTO, null, null, zona, cama, null,
                //            null);
                //  listaepi.add(epi);
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                Cama cama = CamaDAO.getCamaRs(resulSet, zona);

                Usuario usuario = UsuarioDAO.getUsuairoRs(resulSet, null);

                Variable prestacionParam = getPrestacionRs(resulSet, null);
                Centro centroparam = Centro.HNSS;

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                //   Agenda agendaParam = AgendaDAO.getAgendaRs(resulSet, agenda, centro, servicio);
                episodio = getRegistroResulset(resulSet, pacienteParam, centroparam, servicioParam, null, zona,
                        cama, usuario, prestacionParam);

                episodio.setNumeroOrden(resulSet.getInt("numeroorden"));

                listaepi.add(episodio);
            }
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
        return listaepi;
    }

    public Episodio  getEpisodioIngreRNSanos(Paciente nino) {
        Connection connection = null;
        Episodio epi = null;
        try {
            connection = super.getConexionBBDD();
            sql = "select  e.*,c.cama,c.zona  "
                    + " FROM episodios e JOIN pacientes m ON e.paciente=m.id  " +""
                    + " JOIN camas c ON e.idcama=c.id	"
                    + " WHERE e.clase=" + Episodio.CLASE_HOSPITALIZACION.getId() 
                    + " AND e.subclase=" + Episodio.SUBCLASE_INGRESO_RECIENNACIDOS 
                    + " AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO
                    + " AND servicio=" + Servicio.SERVICIO_PEDIATRIA.getId();
             sql = "SELECT  row_number() over (ORDER BY e.id) as numeroorden ," + sqlOracle;
            sql = sql.concat(" and e.clase=" + Episodio.CLASE_HOSPITALIZACION.getId() 
                        + " AND e.subclase=" + Episodio.SUBCLASE_INGRESO_RECIENNACIDOS 
                         + " AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO
                        + " AND servicio=" + Servicio.SERVICIO_PEDIATRIA.getId()
                        + " AND e.paciente=" + nino.getId());
            logger.debug(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            if  (resulSet.next()) {
                episodio = new Episodio();
              

                Zona zona = new Zona();
                zona.setId(resulSet.getLong("zona"));

                
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                Cama cama = CamaDAO.getCamaRs(resulSet, zona);

                Usuario usuario = UsuarioDAO.getUsuairoRs(resulSet, null);

                Centro centroparam = Centro.HNSS;

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                episodio = getRegistroResulset(resulSet, pacienteParam, centroparam, servicioParam, null, zona,
                        cama, usuario, null);

                episodio.setNumeroOrden(resulSet.getInt("numeroorden"));

            }
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
        return episodio;
    }
    
    public ArrayList<Episodio> getRNSanosIngresados() {
        Connection connection = null;
        ArrayList<Episodio> listaepi = new ArrayList<Episodio>();
        try {
            connection = super.getConexionBBDD();
            sql = "select  e.*,c.cama,c.zona  "
                    + " FROM episodios e JOIN pacientes m ON e.paciente=m.id  JOIN camas c ON e.idcama=c.id	"
                    + " WHERE e.clase=" + Episodio.CLASE_HOSPITALIZACION.getId() + " AND e.subclase="
                    + Episodio.SUBCLASE_INGRESO_RECIENNACIDOS + " AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO
                    + " AND servicio=" + Servicio.SERVICIO_PEDIATRIA.getId();
             sql = "SELECT  row_number() over (ORDER BY e.id) as numeroorden ," + sqlOracle;
            sql = sql.concat(" and e.clase=" + Episodio.CLASE_HOSPITALIZACION.getId() + " AND e.subclase="
                    + Episodio.SUBCLASE_INGRESO_RECIENNACIDOS + " AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO
                    + " AND servicio=" + Servicio.SERVICIO_PEDIATRIA.getId());
            logger.debug(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            while (resulSet.next()) {
                episodio = new Episodio();
                /*
                Zona zona = new Zona();
                zona.setId(resulSet.getLong("zona"));
                Cama cama = new Cama();
                cama.setCama(resulSet.getString("cama"));
                cama.setId(resulSet.getLong("idcama"));
                cama.setZona(zona);

                Episodio epi = getRegistroResulset(resulSet, null, Centro.CENTRO_DEFECTO, null, null, zona, cama, null,
                        null);
                listaepi.add(epi);*/

                Zona zona = new Zona();
                zona.setId(resulSet.getLong("zona"));

                //      Episodio epi = getRegistroResulset(resulSet, null, Centro.CENTRO_DEFECTO, null, null, zona, cama, null,
                //            null);
                //  listaepi.add(epi);
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                Cama cama = CamaDAO.getCamaRs(resulSet, zona);

                Usuario usuario = UsuarioDAO.getUsuairoRs(resulSet, null);

                //   Variable prestacionParam = getPrestacionRs(resulSet, prestacion);
                Centro centroparam = Centro.HNSS;

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                //   Agenda agendaParam = AgendaDAO.getAgendaRs(resulSet, agenda, centro, servicio);
                episodio = getRegistroResulset(resulSet, pacienteParam, centroparam, servicioParam, null, zona,
                        cama, usuario, null);

                episodio.setNumeroOrden(resulSet.getInt("numeroorden"));

                listaepi.add(episodio);
            }
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
        return listaepi;
    }

    /*
	 * public ArrayList<Episodio> ZgetEpiClaseFechasCiasPrimaria(String cias,
	 * LocalDate desde, LocalDate hasta, EpisodioClase clase) { Connection
	 * connection = null; ArrayList<Episodio> listaepi = new ArrayList<Episodio>();
	 * try { connection = super.getConexionBBDD(); sql =
	 * "SELECT e.* 	,p.id as idpaci, p.ape1,p.ape2,p.nombre,p.cias,h.nhc,p.tarjeta "
	 * + " FROM  episodios e 	JOIN pacientes p ON p.id=e.paciente AND p.cias='" +
	 * cias + "' " + "	JOIN historias h ON h.paciente=e.paciente " +
	 * " ,u.userid,u.apellido1,u.apellido2,u.nombre,u.categoria,u.estado " +
	 * " FROM episodios e	" + " LEFT JOIN usuarios U ON u.userid=e.userid" +
	 * " WHERE e.clase=" + clase.getId() + " AND e.ffinal>=" +
	 * Utilidades.getFechaNumeroyyymmddDefecha(desde) + "" + " AND e.ffinal<=" +
	 * Utilidades.getFechaNumeroyyymmddDefecha(hasta); logger.debug(sql);
	 * PreparedStatement statement = connection.prepareStatement(sql); ResultSet
	 * resulSet = statement.executeQuery();
	 * 
	 * while (resulSet.next()) { Paciente paciente = new Paciente();
	 * paciente.setId(resulSet.getLong("idpaci"));
	 * paciente.setApellido1(resulSet.getString("ape1"));
	 * paciente.setApellido2(resulSet.getString("ape2"));
	 * paciente.setNombre(resulSet.getString("nombre"));
	 * paciente.setNumerohc(resulSet.getString("nhc"));
	 * paciente.setTarjeta(resulSet.getString("tarjeta"));
	 * 
	 * Usuario usuario = new Usuario(resulSet.getString("userid"),
	 * resulSet.getString("apellido1"), resulSet.getString("apellido2"),
	 * resulSet.getString("nombre"), resulSet.getInt("estado"));
	 * 
	 * Episodio epi = getRegistroResulset(resulSet, paciente, null, null, null,
	 * null, null, usuario, null); listaepi.add(epi); } statement.close(); } catch
	 * (SQLException e) { logger.error(sql);
	 * logger.error(ConexionDAO.ERROR_BBDD_SQL, e); } catch (Exception e) {
	 * logger.error(NotificacionInfo.EXCEPTION_ERROR, e); } try {
	 * connection.close(); } catch (SQLException e) {
	 * logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e); } return listaepi; }
	 * 
     */ public ArrayList<Episodio> getListaRNIngresados() {
        Connection connection = null;
        ArrayList<Episodio> listaepi = new ArrayList<Episodio>();
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT  row_number() over (ORDER BY e.id) as numeroorden ," + sqlOracle;
            sql = sql.concat(" AND e.clase=" + Episodio.CLASE_HOSPITALIZACION.getId() + " AND e.subclase="
                    + Episodio.SUBCLASE_INGRESO_RECIENNACIDOS + " AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO);
            logger.debug(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            while (resulSet.next()) {

                Zona zona = new Zona();
                zona.setId(resulSet.getLong("zona"));

                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                if (resulSet.getLong("madre") != 0) {
                    pacienteParam.setMadre(new PacienteDAO().getPacientePorId(resulSet.getLong("madre"), false));
                }

                Cama cama = CamaDAO.getCamaRs(resulSet, zona);

                Usuario usuario = UsuarioDAO.getUsuairoRs(resulSet, null);

                //   Variable prestacionParam = getPrestacionRs(resulSet, prestacion);
                Centro centroparam = Centro.HNSS;

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                //   Agenda agendaParam = AgendaDAO.getAgendaRs(resulSet, agenda, centro, servicio);
                episodio = getRegistroResulset(resulSet, pacienteParam, centroparam, servicioParam, null, zona,
                        cama, usuario, null);

                episodio.setNumeroOrden(resulSet.getInt("numeroorden"));

                listaepi.add(episodio);
            }
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
        return listaepi;
    }

    /*
    public ArrayList<Episodio> getListaRNIngresados() {
        Connection connection = null;
        ArrayList<Episodio> listaepi = new ArrayList<Episodio>();
        try {
            connection = super.getConexionBBDD();
            sql = "select  e.*,c.cama,c.zona  "
                    + " FROM episodios e JOIN pacientes m ON e.paciente=m.id  JOIN camas c ON e.idcama=c.id	"
                    + " WHERE e.clase=" + Episodio.CLASE_HOSPITALIZACION.getId() + " AND e.subclase="
                    + Episodio.SUBCLASE_INGRESO_RECIENNACIDOS + " AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO;
            logger.debug(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            while (resulSet.next()) {
                Zona zona = new Zona();
                zona.setId(resulSet.getLong("zona"));
                Cama cama = new Cama();
                cama.setCama(resulSet.getString("cama"));
                cama.setId(resulSet.getLong("idcama"));
                cama.setZona(zona);
                Paciente paci = new PacienteDAO().getPacientePorIdConMadre(resulSet.getLong("paciente"), true);

                Episodio epi = getRegistroResulset(resulSet, paci, Centro.CENTRO_DEFECTO, null, null, zona, cama, null,
                        null);
                listaepi.add(epi);
            }
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
        return listaepi;
    }
     */
    public ArrayList<Episodio> getCamasEpisodios(Long clase, Centro centro, Zona zona, Usuario usuario) {
        Connection connection = null;
        ArrayList<Episodio> lista = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            if (connection != null) {
                sql = " SELECT  e.*,p.ape1,p.ape2,p.nombre,p.id as idpaciente,p.fnac,p.sexo,h.nhc  "
                        + " ,t.id as idcentro, t.codigo as codigocentro,t.descripcion as descentro, t.nemonico "
                        + " , s.id as idservicio,s.codigo as codigoservicio, s.descripcion as descservicio "
                        + " , c.cama,c.id as idcama, zona, c.id as idcama,c.estado "
                        + " ,u.nombre as usunombre,u.userid as usuuserid,u.apellido1 as usuapellido1,u.apellido2 as usuapellido2,u.email as usuemail "
                        + " FROM camas c "
                        + "   LEFT JOIN episodios e  ON c.id=e.idcama and (ffinal=99999999 OR ffinal=0 )"
                        + " LEFT JOIN pacientes p ON p.id=e.paciente " + "   LEFT JOIN historias h ON h.paciente=p.id "
                        + " LEFT JOIN centros t ON t.id=e.centro  " + "   LEFT JOIN servicios s ON s.id=e.servicio "
                        + " LEFT JOIN usuarios u ON u.userid=e.userid " + " WHERE c.ZONA=" + zona.getId()
                        + " ORDER BY cama";
                  sql = "SELECT  row_number() over (ORDER BY e.id) as numeroorden ," + sqlOracle;
                sql = sql.concat("  AND (e.ffinal=" + Constantes.FEHAFIN_DEFECTO + " OR e.ffinal=" + Constantes.FEHAFIN_CERO + ")");
                if (clase != null) {
                    sql = sql.concat(" AND e.clase=" + clase);
                }
                if (centro != null) {
                    sql = sql.concat(" AND e.centro=" + centro.getId());
                }

                if (zona != null) {
                    sql = sql.concat(" AND c.zona=" + zona.getId());
                }

                logger.debug(sql);
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resulSet = statement.executeQuery();
                Episodio episodio = null;
                while (resulSet.next()) {
                    Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                    Cama cama = CamaDAO.getCamaRs(resulSet, zona);

                    //   Variable prestacionParam = getPrestacionRs(resulSet, null);
                    Centro centroparam = CentroDAO.getCentroRs(resulSet, centro);

                    Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                    //Agenda agendaParam = getAgendaRs(resulSet, agenda, centro, servicio);
                    Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, usuario);

                    episodio = getRegistroResulset(resulSet, pacienteParam, centroparam, servicioParam, null, zona,
                            cama, usuario, null);

                    episodio.setNumeroOrden(resulSet.getInt("numeroorden"));

                    lista.add(episodio);
                }
                statement.close();
            } else {
                logger.error(ConexionDAO.ERROR_BBDD_SIN_CONEXION);
            }
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
        return lista;
    }

    /**
     *
     * @param clase
     * @param centro
     * @return Recupera de la lista actual de pacientes en urgencias los que no
     * se ha impreso hoja de enfermería usa el campo ESTADO_PRESENCIA si NULL no
     * se ha impreso si 1 se ha impreso
     *
     */
    public ArrayList<Episodio> getListaUrgenciasSinHoja(Long clase, Centro centro, Zona zona) {
        Connection connection = null;
        ArrayList<Episodio> lista = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            if (connection != null) {
                 sql = "SELECT  row_number() over (ORDER BY e.id) as numeroorden ," + sqlOracle;
                sql = sql.concat("  AND (e.ffinal=" + Constantes.FEHAFIN_DEFECTO + " OR e.ffinal=" + Constantes.FEHAFIN_CERO + ")");
                if (clase != null) {
                    sql = sql.concat(" AND e.clase=" + clase);
                }
                if (centro != null) {
                    sql = sql.concat(" AND e.centro=" + centro.getId());
                }
                if (zona != null) {
                    sql = sql.concat(" AND c.zona=" + zona.getId());
                }
                sql = sql.concat("  AND  estado_presencia is null");
                logger.debug(sql);
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resulSet = statement.executeQuery();
                Episodio episodio = null;
                while (resulSet.next()) {
                    Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                    Cama cama = CamaDAO.getCamaRs(resulSet, null);

                    Usuario usuario = UsuarioDAO.getUsuairoRs(resulSet, null);

                    Variable prestacionParam = getPrestacionRs(resulSet, null);

                    Centro centroparam = CentroDAO.getCentroRs(resulSet, centro);

                    Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                    Agenda agendaParam = AgendaDAO.getAgendaRs(resulSet, null, centro, servicioParam);

                    episodio = getRegistroResulset(resulSet, pacienteParam, centroparam, servicioParam, agendaParam, null,
                            cama, usuario, prestacionParam);

                    episodio.setNumeroOrden(resulSet.getInt("numeroorden"));

                    lista.add(episodio);
                }
                statement.close();
            } else {
                logger.error(ConexionDAO.ERROR_BBDD_SIN_CONEXION);
            }
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
        return lista;
    }

    public Episodio getRegistroPorId(Long id, Paciente paciente, Centro centro, Servicio servicio, Agenda agenda,
            Zona zona, Cama cama) {
        Connection connection = null;
        Episodio episodio = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT  e.*,p.ape1,p.ape2,p.nombre,p.id as idpaciente,p.fnac,p.sexo,h.nhc"
                    + " ,t.id as idcentro, t.codigo as codigocentro,t.descripcion as descentro, t.nemonico, s.id as idservicio,s.codigo as codigoservicio, s.descripcion as descservicio"
                    + " , c.cama,c.id as idcama, zona, c.id as idcama,c.estado "
                    + " ,u.userid,u.apellido1,u.apellido2,u.nombre,u.categoria,u.estado as estadousu "
                    + " ,l.descripcion as despresta " + " FROM episodios e	" + " JOIN pacientes p ON p.id=e.paciente  "
                    + " JOIN historias h ON h.paciente=p.id " + " JOIN centros t ON t.id=e.centro  "
                    + " JOIN servicios s ON s.id=e.servicio" + " LEFT JOIN camas c  ON c.id=e.idcama  "
                    + " LEFT JOIN usuarios U ON u.userid=e.userid" + " LEFT JOIN catalogo l ON l.id = e.prestacion "
                    + " WHERE   e.id=" + id;

            sql = sqlOracle;
  sql = "SELECT  row_number() over (ORDER BY e.id) as numeroorden ," + sqlOracle;
       //     sql = sql.concat(getSqlWhere(new Long(0), centro, servicio, agenda,
         //           null, null, null, null, null, zona,null,null));
            sql = sql.concat(" AND e.id=" + id);

            logger.debug(sql);

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            episodio = new Episodio();
            if (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, paciente);

                Cama camaParam = CamaDAO.getCamaRs(resulSet, zona);

                Usuario usuario = UsuarioDAO.getUsuairoRs(resulSet, null);

                // Variable prestacionParam = getPrestacionRs(resulSet, prestacion);
                Centro centroparam = CentroDAO.getCentroRs(resulSet, centro);

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, servicio);

                Agenda agendaParam = AgendaDAO.getAgendaRs(resulSet, agenda, centro, servicio);

                episodio = getRegistroResulset(resulSet, pacienteParam, centroparam, servicioParam, agendaParam, zona,
                        camaParam, usuario, null);

                /*
                Cama camaVar;
                asfdasdfa
                if (zona == null) {
                    camaVar = new Cama(resulSet.getLong("idcama"), resulSet.getString("cama"),
                            resulSet.getString("estado"), new Zona(resulSet.getLong("zona")));
                } else {
                    camaVar = new Cama(resulSet.getLong("idcama"), resulSet.getString("cama"),
                            resulSet.getString("estado"), zona);
                }
                Centro centroVar;
                if (centro == null) {
                    centroVar = new Centro(resulSet.getLong("idcentro"), resulSet.getString("codigocentro"),
                            resulSet.getString("descentro"), resulSet.getString("nemonico"));
                } else {
                    centroVar = centro;
                }
                Usuario usuario = new Usuario(resulSet.getString("userid"), resulSet.getString("apellido1"),
                        resulSet.getString("apellido2"), resulSet.getString("nombre"), resulSet.getInt("estadousu"));

                Variable prestacion = new Variable();
                prestacion.setItem(resulSet.getLong("prestacion"));
                prestacion.setDescripcion(resulSet.getString("despresta"));
                episodio = getRegistroResulset(resulSet, paciente, centroVar, servicio, agenda, zona, camaVar, usuario,
                        prestacion);
                 */
            }
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
        return episodio;
    }

    public boolean getReferenciasExternas(Long idEpisodio) {
        Connection connection = null;
        boolean referencias = false;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id from registros where episodio=?" + " UNION " + "SELECT id FROM informes WHERE episodio=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idEpisodio);
            statement.setLong(2, idEpisodio);
            logger.debug("SELECT id from registros where episodio=" + idEpisodio + " UNION "
                    + "SELECT id FROM informes WHERE episodio= " + idEpisodio);

            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                referencias = true;
            }
            statement.close();
        } catch (SQLException e) {
            logger.error("SELECT id from registros where episodio=" + idEpisodio + " UNION "
                    + "SELECT id FROM informes WHERE episodio= " + idEpisodio);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return referencias;
    }

    public boolean getTodosEpisodiosCerrados(Long idpaciente, EpisodioClase clase) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *  FROM  episodios  WHERE paciente=?  AND ffinal=? AND clase=? ";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idpaciente);
            statement.setLong(2, Constantes.FEHAFIN_CERO);
            statement.setLong(3, clase.getId());
            logger.debug("SELECT count(*) as casos  FROM  episodios  WHERE paciente=" + idpaciente + "  AND ffinal="
                    + Constantes.FEHAFIN_CERO + " AND clase= " + clase.getId());

            ResultSet resulSet = statement.executeQuery();

            if (resulSet.next()) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {
            logger.error("SELECT count(*) as casos  FROM  episodios  WHERE paciente=" + idpaciente + "  AND ffinal="
                    + Constantes.FEHAFIN_CERO + " AND clase= " + clase.getId());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return true;
    }

    /**
     *
     * @param episodio
     * @param clase
     * @param zona
     * @return true si el paciente tiene para esa fecha, la clase pasada, la
     * zona un fila ya añadida en episodio
     */
    public boolean getTieneEpisodioFechaClase(Paciente paciente, Long finicio, Long clase) {
        boolean esta = false;
        Connection connection = null;
        String sql = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id  FROM  episodios   WHERE paciente=" + paciente.getId() + " AND clase=" + clase
                    + " AND finicio= " + finicio;
            logger.debug(sql);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            if (resulSet.next()) {
                esta = true;
            } else {
                esta = false;
            }
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {

                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return esta;
    }

    public boolean getTieneEpisodioClase(Paciente paciente, Long clase) {
        boolean esta = false;
        Connection connection = null;
        String sql = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id  FROM  episodios   WHERE paciente=" + paciente.getId() + " AND clase=" + clase;
            logger.debug(sql);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            if (resulSet.next()) {
                esta = true;
            } else {
                esta = false;
            }
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {

                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return esta;
    }

    public Integer getNumeroEpisodiosClase(Paciente paciente, Long clase) {
        Integer casos = 0;
        Connection connection = null;
        String sql = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) as casos  FROM  episodios   WHERE paciente=" + paciente.getId() + " AND clase=" + clase;
            logger.debug(sql);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            if (resulSet.next()) {
                casos = resulSet.getInt("casos");
            }
        } catch (SQLException e) {
            logger.error(sql, e);
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

    public boolean getPacienteIngresado(Paciente paciente) {
        boolean esta = false;
        Connection connection = null;
        String sql = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT e.id  FROM  episodios  e "
                    + "WHERE e.paciente=" + paciente.getId() + " AND e.clase="
                    + Episodio.CLASE_HOSPITALIZACION.getId() + " AND e.ffinal= " + Constantes.FEHAFIN_DEFECTO;

            logger.debug(sql);

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            if (resulSet.next()) {
                esta = true;
            } else {
                esta = false;
            }
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR + sql, e);
        } finally {
            try {

                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return esta;
    }

    public boolean getPacienteIngresadoNeonatos(Paciente paciente) {
        boolean esta = false;
        Connection connection = null;
        String sql = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT e.id  FROM  episodios  e"
                    + " JOIN camas c ON c.id=e.idcama  WHERE paciente=" + paciente.getId() + " AND clase="
                    + Episodio.CLASE_HOSPITALIZACION.getId() + " AND ffinal= " + Constantes.FEHAFIN_DEFECTO
                    + " AND c.zona=" + Zona.ZONA_UE4N.getId();

            logger.debug(sql);

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            if (resulSet.next()) {
                esta = true;
            } else {
                esta = false;
            }
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR + sql, e);
        } finally {
            try {

                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return esta;
    }
}
