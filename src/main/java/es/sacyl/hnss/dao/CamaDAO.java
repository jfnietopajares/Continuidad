package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Cama;
import es.sacyl.hnss.entity.Zona;
import es.sacyl.hnss.utilidades.Constantes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CamaDAO extends ConexionDAO {

    private static final Logger logger = LogManager.getLogger(CamaDAO.class);

    private Cama cama;

    /**
     * Instantiates a new accesos DAO.
     */
    public CamaDAO() {
        super();
    }

//	@Override
    public Cama getRegistroResulset(ResultSet resulSet, Zona zona) {
        cama = new Cama();
        try {
            cama.setId(resulSet.getLong("id"));
            if (zona == null) {
                cama.setZona(new ZonaDAO().getRegistrPorId(resulSet.getLong("id")));
            } else {
                cama.setZona(zona);
            }

            cama.setCama(resulSet.getString("cama"));
            return cama;
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        return cama;
    }

    public Cama getRegistrPorId(Long id, Zona zona) {
        Connection connection = null;
        cama = new Cama();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM camas WHERE ID=" + id;

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                cama = getRegistroResulset(resulSet, zona);
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
        return cama;
    }

    public Cama getRegistrPorNcama(String ncama, Zona zona) {
        Connection connection = null;
        cama = new Cama();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM camas WHERE cama='" + ncama + "'";

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                cama = getRegistroResulset(resulSet, zona);
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
        return cama;
    }

    public ArrayList<Cama> getListaCamas(Zona zona) {
        Connection connection = null;
        ArrayList<Cama> listaCamas = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM camas WHERE estado!='B' AND  zona=" + zona.getId() + " ORDER BY cama ";

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Cama cama = getRegistroResulset(resulSet, zona);
                listaCamas.add(cama);
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
        return listaCamas;
    }

    /*
        crea una cama a partir de un rs de episodios que cruza con camas
        los nombres de los campos tienen que ser los del método
     */
    public static Cama getCamaRs(ResultSet resulSet, Zona zona) {
        Cama cama = null;
        try {
            if (zona == null) {
                cama = new Cama(resulSet.getLong("idcama"), resulSet.getString("cama"),
                        resulSet.getString("camaestado"), new Zona(resulSet.getLong("zona")));
            } else {
                cama = new Cama(resulSet.getLong("idcama"), resulSet.getString("cama"),
                        resulSet.getString("camaestado"), zona);
            }
        } catch (Exception e) {
            logger.error(Constantes.SQLERRORRESULSET, e);
        }
        return cama;
    }
}
