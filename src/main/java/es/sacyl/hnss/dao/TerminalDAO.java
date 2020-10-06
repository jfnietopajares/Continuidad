/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class TerminalDAO extends ConexionDAO {

    private static final Logger logger = LogManager.getLogger(TerminalDAO.class);

    public TerminalDAO() {
        super();
    }

    public Terminal getRegistroResulset(ResultSet resulSet) {
        Terminal terminal = new Terminal();
        try {
            terminal.setId(resulSet.getLong("id"));
            terminal.setIp(resulSet.getString("ip"));
            terminal.setDescripcion(resulSet.getString("descripcion"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return terminal;
    }

    public Terminal getTerminalIp(String ip) {
        Connection connection = null;
        Terminal terminal = new Terminal();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT  *  FROM terminales WHERE ip='" + ip + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                terminal = getRegistroResulset(resulSet);
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
        return terminal;
    }
}
