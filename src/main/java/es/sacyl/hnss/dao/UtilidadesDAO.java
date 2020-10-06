package es.sacyl.hnss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Constantes;

/**
 * The Class UtilidadesDAO. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class UtilidadesDAO extends ConexionDAO {

    private String sql;

    private static final Logger logger = LogManager.getLogger(UtilidadesDAO.class);

    /**
     * Instantiates a new utilidades dao.
     */
    public UtilidadesDAO() {
        super();
    }

    /**
     * Gets the siguiente id.
     *
     * @param tabla the tabla
     * @return the siguiente id
     */
    public Long getSiguienteId(String tabla) {
        Connection connection = null;
        Long resultado = new Long(0);
        try {
            connection = super.getConexionBBDD();
            /*
			 * if (MyUI.objParametros.get(Parametros.KEY_PERSISTENCIA).equals(Constantes.
			 * MYSQL_STRING)) sql = "SELECT max(id) AS id FROM  " + tabla; else if
			 * (MyUI.objParametros.get(Parametros.KEY_PERSISTENCIA).equals(Constantes.
			 * ORACLE_STRING)) sql = "select secuencia.nextval AS id from dual ";
             */
            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = "SELECT max(id) AS id FROM  " + tabla;
            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {
                sql = "select secuencia.nextval AS id from dual ";
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                resultado = res.getLong("id");
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
                logger.error(CentroDAO.ERROR_BBDD_SQL, e);
            }
        }
        if (persistencia.equals(Constantes.MYSQL_STRING)) {
            resultado++;
        }
        return resultado;
    }
}
