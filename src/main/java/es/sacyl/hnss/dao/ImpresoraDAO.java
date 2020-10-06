/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import com.jnieto.entity.Cama;
import com.jnieto.entity.Impresora;
import com.jnieto.entity.Terminal;
import com.jnieto.entity.Zona;
import com.jnieto.ui.NotificacionInfo;
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
public class ImpresoraDAO extends ConexionDAO {

    private static final Logger logger = LogManager.getLogger(ImpresoraDAO.class);

    public ImpresoraDAO() {
        super();
    }

    public Impresora getRegistroResulset(ResultSet resulSet) {
        Impresora impresora = new Impresora();
        try {
            impresora.setId(resulSet.getLong("id"));
            impresora.setIp(resulSet.getString("ip"));
            impresora.setPuerto(resulSet.getLong("puerto"));
            impresora.setTipo(resulSet.getLong("tipo"));
            impresora.setDescripcion(resulSet.getString("descripcion"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return impresora;
    }

    public Impresora getImpresoraTerminalTipo(String ipTermnial, Long tipo) {
        Connection connection = null;
        Impresora impresora = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT  i.* from terminales  t JOIN impresorascb i On t.impresora=i.id and i.tipo=" + tipo + " where  t.ip='" + ipTermnial + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                impresora = getRegistroResulset(resulSet);
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
        return impresora;
    }
}
