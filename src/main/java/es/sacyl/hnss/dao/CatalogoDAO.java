package es.sacyl.hnss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;

public class CatalogoDAO extends ConexionDAO {

    private static final Logger logger = LogManager.getLogger(CatalogoDAO.class);

    private Variable variable;

    public CatalogoDAO() {
        super();
    }

    public Variable getRegistroResulset(ResultSet res) {
        variable = new Variable();
        try {
            variable.setItem(res.getLong("id"));
            variable.setDescripcion(res.getString("descripcion"));
            variable.setCode(res.getString("code"));
            variable.setCodesystem(res.getString("codesystem"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        }
        return variable;

    }

    public Variable getRegistroPorId(Long id) {
        Connection connection = null;
        Variable variable = new Variable();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT  catalogo.*,codigos.*  FROM catalogo  , codigos WHERE catalogo.id=codigos.catalogo AND catalogo.ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                variable = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug(sql + id);
        } catch (SQLException e) {
            logger.debug(sql + id, e);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return variable;
    }

}
