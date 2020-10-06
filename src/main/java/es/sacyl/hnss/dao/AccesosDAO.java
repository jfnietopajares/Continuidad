package es.sacyl.hnss.dao;

import com.vaadin.flow.component.notification.Notification;
import es.sacyl.hnss.entity.Acceso;
import es.sacyl.hnss.entity.AccesoTipos;
import es.sacyl.hnss.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * The Class AccesosDAO. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class AccesosDAO extends ConexionDAO implements InterfaceDAO {

    private static final Logger logger = LogManager.getLogger(AccesosDAO.class);

    private String sql;

    private Acceso acceso;

    /**
     * Instantiates a new accesos DAO.
     */
    public AccesosDAO() {
        super();
    }

    @Override
    public Acceso getRegistroResulset(ResultSet resulSet) {
        Acceso acceso = new Acceso();
        try {
            acceso.setId(resulSet.getLong("id"));
            acceso.setFecha(resulSet.getLong("fecha"));
            acceso.setHora(resulSet.getInt("hora"));
            acceso.setUserid(new UsuarioDAO().getUsuarioUserid(resulSet.getString("userid"), false));
            acceso.setTipo(new AccesoTipos(resulSet.getInt("tipo")));
            acceso.setEpiinfo(resulSet.getLong("epiinfo"));
            acceso.setPaciente(new PacienteDAO().getPacientePorId(resulSet.getLong(("paciente")), false));
            acceso.setMotivo(resulSet.getString("motivo"));
            acceso.setIp(resulSet.getString("ip"));
            logger.debug("Acceso resulset " + resulSet.toString());
            return acceso;
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Notification.show(Constantes.EXCEPTION_ERROR), e);
        }
        return acceso;
    }

    /**
     * Do insert acceso login ok.
     *
     * @param acceso the acceso
     * @return true, if successful
     */
    public boolean doInsertAccesoLoginOk(Acceso acceso) {
        Connection connection = null;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("accesos");
            acceso.setId(id);
            sql = " INSERT INTO accesos (id,fecha,hora,userid,tipo,motivo,ip,canal) VALUES (?,?,?,?,?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, acceso.getId());
            statement.setLong(2, acceso.getFecha());
            statement.setLong(3, acceso.getHora());
            statement.setString(4, acceso.getUserid().getUserid());
            statement.setInt(5, acceso.getTipo().getTipo());
            statement.setString(6, acceso.getMotivo());
            statement.setString(7, acceso.getIp());
            statement.setLong(8, acceso.getCanal());
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO accesos (id,fecha,hora,userid,tipo,motivo,ip,canal) " + "VALUES ("
                    + acceso.getId() + "," + acceso.getFecha() + "," + acceso.getHora() + ",'"
                    + acceso.getUserid().getUserid() + "'," + acceso.getTipo().getTipo() + ",'" + acceso.getMotivo()
                    + "'," + acceso.getIp() + "," + acceso.getCanal() + ")  ");
        } catch (SQLException e) {
            logger.error(" INSERT INTO accesos (id,fecha,hora,userid,tipo,motivo,ip,canal) " + "VALUES ("
                    + acceso.getId() + "," + acceso.getFecha() + "," + acceso.getHora() + ","
                    + acceso.getUserid().getUserid() + "," + acceso.getTipo().getTipo() + ",'" + acceso.getMotivo()
                    + "'," + acceso.getIp() + "," + acceso.getCanal() + ")  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
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

    /**
     * Do insert acceso usuario no autorizado.
     *
     * @param acceso the acceso
     * @return true, if successful
     */
    public boolean doInsertAccesoUsuarioNoAutorizado(Acceso acceso) {
        Connection connection = null;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("accesos");
            acceso.setId(id);
            sql = " INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid) VALUES (?,?,?,?,?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, acceso.getId());
            statement.setLong(2, acceso.getFecha());
            statement.setLong(3, acceso.getHora());
            statement.setInt(4, acceso.getTipo().getTipo());
            statement.setString(5, acceso.getMotivo());
            statement.setString(6, acceso.getIp());
            statement.setLong(7, acceso.getCanal());
            statement.setString(8, acceso.getUserid().getUserid());
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid) VALUES (" + acceso.getId()
                    + "," + acceso.getFecha() + "," + acceso.getHora() + "," + acceso.getTipo().getTipo() + ",'"
                    + acceso.getMotivo() + "'," + acceso.getIp() + "," + acceso.getCanal() + ",'"
                    + acceso.getUserid().getUserid() + "')  ");
        } catch (SQLException e) {
            logger.error(" INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid) VALUES (" + acceso.getId()
                    + "," + acceso.getFecha() + "," + acceso.getHora() + "," + acceso.getTipo().getTipo() + ",'"
                    + acceso.getMotivo() + "'," + acceso.getIp() + "," + acceso.getCanal() + ",'"
                    + acceso.getUserid().getUserid() + "') ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
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

    /**
     * Do insert acceso usuario debaja.
     *
     * @param acceso the acceso
     * @return true, if successful
     */
    public boolean doInsertAccesoUsuarioDebaja(Acceso acceso) {
        Connection connection = null;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("accesos");
            acceso.setId(id);
            sql = " INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid) VALUES (?,?,?,?,?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, acceso.getId());
            statement.setLong(2, acceso.getFecha());
            statement.setLong(3, acceso.getHora());
            statement.setInt(4, acceso.getTipo().getTipo());
            statement.setString(5, acceso.getMotivo());
            statement.setString(6, acceso.getIp());
            statement.setLong(7, acceso.getCanal());
            statement.setString(8, acceso.getUserid().getUserid());
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid) VALUES (" + acceso.getId()
                    + "," + acceso.getFecha() + "," + acceso.getHora() + "," + acceso.getTipo().getTipo() + ","
                    + acceso.getMotivo() + "," + acceso.getIp() + "," + acceso.getCanal() + " ,"
                    + acceso.getUserid().getUserid() + ")");
        } catch (SQLException e) {
            logger.error(" INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid) VALUES (" + acceso.getId()
                    + "," + acceso.getFecha() + "," + acceso.getHora() + "," + acceso.getTipo().getTipo() + ","
                    + acceso.getMotivo() + "," + acceso.getIp() + "," + acceso.getCanal() + " ,"
                    + acceso.getUserid().getUserid() + ")");
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
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

    /**
     * Do insert acceso paciente.
     *
     * @param acceso the acceso
     * @return true, if successful
     */
    public boolean doInsertAccesoPaciente(Acceso acceso) {
        Connection connection = null;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("accesos");
            acceso.setId(id);
            sql = " INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid,paciente) VALUES (?,?,?,?,?,?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, acceso.getId());
            statement.setLong(2, acceso.getFecha());
            statement.setLong(3, acceso.getHora());
            statement.setInt(4, acceso.getTipo().getTipo());
            statement.setString(5, acceso.getMotivo());
            statement.setString(6, acceso.getIp());
            statement.setLong(7, acceso.getCanal());
            statement.setString(8, acceso.getUserid().getUserid());
            statement.setLong(9, acceso.getPaciente().getId());
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid,paciente) VALUES ("
                    + acceso.getId() + "," + acceso.getFecha() + "," + acceso.getHora() + ","
                    + acceso.getTipo().getTipo() + ",'" + acceso.getMotivo() + "'," + acceso.getIp() + ","
                    + acceso.getCanal() + " ," + acceso.getUserid().getUserid() + "," + acceso.getPaciente().getId()
                    + ")");
        } catch (SQLException e) {
            logger.error(" INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid,paciente) VALUES ("
                    + acceso.getId() + "," + acceso.getFecha() + "," + acceso.getHora() + ","
                    + acceso.getTipo().getTipo() + ",'" + acceso.getMotivo() + "'," + acceso.getIp() + ","
                    + acceso.getCanal() + " ," + acceso.getUserid().getUserid() + "," + acceso.getPaciente().getId()
                    + ")");
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
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

    public boolean doInsertAccesoRegistro(Acceso acceso) {
        Connection connection = null;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("accesos");
            acceso.setId(id);
            sql = " INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid,paciente,epiinfo) VALUES (?,?,?,?,?,?,?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, acceso.getId());
            statement.setLong(2, acceso.getFecha());
            statement.setLong(3, acceso.getHora());
            statement.setInt(4, acceso.getTipo().getTipo());
            statement.setString(5, acceso.getMotivo());
            statement.setString(6, acceso.getIp());
            statement.setLong(7, acceso.getCanal());
            statement.setString(8, acceso.getUserid().getUserid());
            statement.setLong(9, acceso.getPaciente().getId());
            statement.setLong(10, acceso.getEpiinfo());
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid,paciente) VALUES ("
                    + acceso.getId() + "," + acceso.getFecha() + "," + acceso.getHora() + ","
                    + acceso.getTipo().getTipo() + ",'" + acceso.getMotivo() + "'," + acceso.getIp() + ","
                    + acceso.getCanal() + " ," + acceso.getUserid().getUserid() + "," + acceso.getPaciente().getId()
                    + "," + acceso.getEpiinfo() + ")");
        } catch (SQLException e) {
            logger.error(" INSERT INTO accesos (id,fecha,hora,tipo,motivo,ip,canal,userid,paciente) VALUES ("
                    + acceso.getId() + "," + acceso.getFecha() + "," + acceso.getHora() + ","
                    + acceso.getTipo().getTipo() + ",'" + acceso.getMotivo() + "'," + acceso.getIp() + ","
                    + acceso.getCanal() + " ," + acceso.getUserid().getUserid() + "," + acceso.getPaciente().getId()
                    + "," + acceso.getEpiinfo() + ")");
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
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

    public String getSqlWhere(LocalDate desde, LocalDate hasta, String usuario, String historia, int tipo) {
        String sqlString = "";
        sqlString = sqlString.concat(" AND fecha>= " + Utilidades.getFechaNumeroyyymmddDefecha(desde));
        sqlString = sqlString.concat(" AND fecha<= " + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
        if (!usuario.isEmpty()) {
            sqlString = sqlString.concat(" AND userid like '" + usuario + "'");
        }
        if (!historia.isEmpty()) {
            sqlString = sqlString.concat(" AND nhc= '" + historia + "'");
        }
        if (tipo != 0) {
            sqlString = sqlString.concat(" AND tipo= " + tipo);
        }
        return sqlString;
    }

    public PagiLisReg getPaginacionRegistros(LocalDate desde, LocalDate hasta, String usuario, String historia,
            int tipo) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) as numero FROM view_accesos  " + "	WHERE  1=1   ";
            sql = sql.concat(getSqlWhere(desde, hasta, usuario, historia, tipo));
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                contador = resulSet.getInt("numero");
            }
            paginacion.setPrimero(1);
            paginacion.setUltimo(contador);
            paginacion.setRegistrosTotales(contador);
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paginacion;
    }

    public ArrayList<Acceso> getListaAccesos(LocalDate desde, LocalDate hasta, String usuario, String historia,
            int tipo, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Acceso> listaAccesos = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = "SELECT  @rownum:=@rownum+1  as numeroorden ,a.*" + "	"
                        + "FROM view_accesos  a,  (SELECT @rownum:=0) r" + "	WHERE  1=1   ";
            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {
                sql = "SELECT row_number() over (ORDER BY fecha,hora) as numeroorden , a.* "
                        + ",u.apellido1 ||' '|| u.apellido2 ||','|| u.nombre as nomnbreusuario "
                        + ",p.ape1 ||' '|| p.ape2 ||','|| u.nombre as nomnbrepaciente " + "FROM accesos a "
                        + "LEFT JOIN pacientes p ON p.id=a.paciente " + "JOIN usuarios u ON u.userid=a.userid "
                        + "LEFT JOIN pacientes p ON p.id=a.paciente ";
            }
            sql = sql.concat(getSqlWhere(desde, hasta, usuario, historia, tipo));
            sql = sql.concat(" ORDER BY fecha,hora");
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            int contador = 1;
            while (resulSet.next() && contador < 10) {
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        acceso = getRegistroResulset(resulSet);
                        acceso.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaAccesos.add(acceso);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        acceso = getRegistroResulset(resulSet);
                        acceso.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaAccesos.add(acceso);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                }
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaAccesos;
    }

    public ArrayList<Acceso> getListaAccesosLight(LocalDate desde, LocalDate hasta, String usuario, String historia,
            int tipo, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Acceso> listaAccesos = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = "SELECT  @rownum:=@rownum+1  as numeroorden ,a.*" + "	"
                        + "FROM view_accesos  a,  (SELECT @rownum:=0) r" + "	WHERE  1=1   ";
            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {
                sql = "SELECT a.* " + ",u.apellido1 ||' '|| u.apellido2 ||','|| u.nombre as nomnbreusuario "
                        + ",p.ape1 ||' '|| p.ape2 ||','|| u.nombre as nomnbrepaciente " + "FROM accesos a "
                        + "LEFT JOIN pacientes p ON p.id=a.paciente " + "JOIN usuarios u ON u.userid=a.userid "
                        + "LEFT JOIN pacientes p ON p.id=a.paciente ";
            }
            sql = sql.concat(getSqlWhere(desde, hasta, usuario, historia, tipo));
            sql = sql.concat(" ORDER BY fecha,hora");

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            int contador = 1;
            while (resulSet.next() && contador < 10) {
                Acceso acceso = new Acceso();
                acceso.setId(resulSet.getLong("id"));
                acceso.setFecha(resulSet.getLong("fecha"));
                acceso.setHora(resulSet.getLong("hora"));
                acceso.setNombreUsuario(resulSet.getString("nombreusuario"));
                acceso.setNhc(resulSet.getString("nhc"));
                acceso.setTipo(new AccesoTipos(resulSet.getInt("tipo")));
                acceso.setNombrePaciente(resulSet.getString("nombrepaciente"));
                acceso.setMotivo(resulSet.getString("motivo"));
                acceso.setIp(resulSet.getString("ip"));
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        acceso.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaAccesos.add(acceso);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        acceso.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaAccesos.add(acceso);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                }
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaAccesos;
    }

    @Override
    public boolean getReferenciasExternas(Long id) {
        return false;
    }

    @Override
    public boolean grabaDatos(Object object) {
        return false;
    }

    @Override
    public boolean actualizaDatos(Object mensajeparam) {
        return false;
    }

    @Override
    public boolean insertaDatos(Object mensajeparam) {
        return false;
    }

    @Override
    public Object getRegistroId(Long id) {
        return null;
    }

    @Override
    public String getSqlWhere(String cadena) {
        return null;
    }

    @Override
    public boolean borraDatos(Object objeto) {
        return false;
    }

}
