package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Perfil;
import es.sacyl.hnss.utilidades.Constantes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class PerfilDAO extends ConexionDAO {

    private static final Logger logger = LogManager.getLogger(PerfilDAO.class);

    private Perfil perfil;

    public PerfilDAO() {
        super();
    }

    public Perfil getRegistroResulset(ResultSet resulSet) {
        perfil = new Perfil();
        try {
            perfil.setId(resulSet.getLong("id"));
            perfil.setDescripcion(resulSet.getString("descripcion"));
            logger.debug("Acceso resulset " + resulSet.toString());
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        return perfil;
    }

    public Perfil getRegistrPorId(Long id) {
        Connection connection = null;
        perfil = new Perfil();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM perfiles WHERE ID=" + id;

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                perfil = getRegistroResulset(resulSet);
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
        return perfil;
    }

}
