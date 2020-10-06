package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Centro;
import es.sacyl.hnss.entity.Servicio;
import es.sacyl.hnss.entity.Zona;
import es.sacyl.hnss.utilidades.Constantes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ZonaDAO extends ConexionDAO {

    private Zona zona;
    private static final Logger logger = LogManager.getLogger(ZonaDAO.class);

    public ZonaDAO() {
        super();
    }

    public Zona getRegistroResulset(ResultSet res) {
        Zona zona = new Zona();
        try {
            zona.setId(res.getLong("id"));
            zona.setZona(res.getString("zona"));
            zona.setTipo(res.getInt("tipo"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        }
        return zona;
    }

    public ArrayList<Zona> getListaRegistrosTipo(int tipo, Centro centro) {
        Connection connection = null;
        ArrayList<Zona> listaZonas = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM zonas " + " WHERE tipo=" + tipo + " AND centro=" + centro.getId();

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                zona = getRegistroResulset(resulSet);
                listaZonas.add(zona);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error("Error.", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaZonas;
    }

    public ArrayList<Zona> getListaRegistrosTipo(int tipo, Centro centro, Servicio servicio) {
        Connection connection = null;
        ArrayList<Zona> listaZonas = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM zonas " + " WHERE tipo=" + tipo + " AND centro=" + centro.getId();
            if (servicio != null) {
                switch (tipo) {
                    case 1:
                        sql = sql.concat(
                                " AND id IN (SELECT  unique c.zona FROM episodios e " + " JOIN camas c ON c.id=e.idcama "
                                + " WHERE  clase=1 and centro= " + centro.getId() + " AND servicio="
                                + servicio.getId() + " AND e.ffinal=" + Constantes.FEHAFIN_DEFECTO + ")");
                        break;
                }
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                zona = getRegistroResulset(resulSet);
                listaZonas.add(zona);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error("Error.", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaZonas;
    }

    public Zona getRegistrPorId(Long id) {
        Connection connection = null;
        Zona zona = new Zona();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM zonas WHERE ID=" + id;

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                zona = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return zona;
    }
}
