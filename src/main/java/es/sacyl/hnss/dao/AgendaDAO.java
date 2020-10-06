package es.sacyl.hnss.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Agenda;
import com.jnieto.entity.Centro;
import com.jnieto.entity.Servicio;
import com.jnieto.ui.NotificacionInfo;

public class AgendaDAO extends ConexionDAO {

    private Agenda agenda;
    private static final Logger logger = LogManager.getLogger(AgendaDAO.class);

    public AgendaDAO() {
        super();
    }

    public Agenda getRegistroResulset(ResultSet res, Centro centro, Servicio servicio) {
        agenda = new Agenda();
        try {
            agenda.setId(res.getLong("id"));
            if (centro != null) {
                agenda.setCentro(centro);
            } else {
                agenda.setCentro(new CentroDAO().getRegistroId(res.getLong("centro")));
            }
            agenda.setCodigo(res.getString("codigo"));
            agenda.setDescripcion(res.getString("descripcion"));
            if (servicio != null) {
                agenda.setServicio(servicio);
            } else {
                agenda.setServicio(new ServiciosDAO().getRegistroId(res.getLong("servicio")));
            }
            agenda.setActiva(res.getInt("activa"));

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        }
        return agenda;
    }

    public Agenda getRegistrPorId(Long id) {
        Connection connection = null;
        agenda = new Agenda();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM agendas WHERE ID=" + id;

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                agenda = getRegistroResulset(resulSet, null, null);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
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
        return agenda;
    }

    public Agenda getRegistrPorCodigo(String codigo) {
        Connection connection = null;
        agenda = new Agenda();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM agendas WHERE codigo=" + codigo;

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                agenda = getRegistroResulset(resulSet, null, null);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
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
        return agenda;
    }

    public ArrayList<Agenda> getListaAgendas(Centro centro, Servicio servicio) {
        Connection connection = null;
        ArrayList<Agenda> listaAgendas = new ArrayList<Agenda>();
        if (centro == null) {
            return listaAgendas;
        }
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT  * FROM agendas WHERE activa=1 AND  centro=" + centro.getId();
            if (servicio != null) {
                sql = sql.concat(" AND servicio=" + servicio.getId());
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Agenda agenda = getRegistroResulset(resulSet, centro, servicio);
                agenda = new Agenda();
                agenda.setId(resulSet.getLong("id"));
                agenda.setCentro(centro);
                agenda.setCodigo(resulSet.getString("codigo"));
                agenda.setDescripcion(resulSet.getString("descripcion"));
                agenda.setServicio(servicio);
                agenda.setActiva(resulSet.getInt("activa"));
                listaAgendas.add(agenda);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
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
        return listaAgendas;
    }

    public ArrayList<Agenda> getListaAgendas(Centro centro, ArrayList<Servicio> servicios) {
        Connection connection = null;
        ArrayList<Agenda> listaAgendas = new ArrayList<Agenda>();
        if (centro == null) {
            return listaAgendas;
        }
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT  * FROM agendas WHERE activa=1 AND  centro=" + centro.getId();
            if (servicios != null) {
                int contador = 0;
                for (Servicio servicio : servicios) {
                    if (contador == 0) {
                        sql = sql.concat(" AND servicio IN (");
                    }
                    if (contador > 0) {
                        sql = sql.concat(",");
                    }
                    sql = sql.concat(Long.toString(servicio.getId()));
                    contador++;
                }
                if (contador > 0) {
                    sql = sql.concat(")");
                }
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Agenda agenda = getRegistroResulset(resulSet, centro, null);
                listaAgendas.add(agenda);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
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
        return listaAgendas;
    }

    /*
        crea una agenda a partir de la sql de episodios que cruza con agendas
        los nombres de los campos tienen que ser los del método
     */
    public static Agenda getAgendaRs(ResultSet resulSet, Agenda agednaParam, Centro centro, Servicio servicio) {
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
}
