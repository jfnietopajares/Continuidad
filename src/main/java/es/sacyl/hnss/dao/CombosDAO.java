package es.sacyl.hnss.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Combo;

public class CombosDAO extends ConexionDAO {

    private static final Logger logger = LogManager.getLogger(CombosDAO.class);

    public CombosDAO() {
    }

    public Combo getRegistroResulset(ResultSet rs) {
        Combo combo = new Combo();
        try {
            combo.setId(rs.getLong("id"));
            combo.setDescripcion(rs.getString("descripcion"));
            combo.setValor(rs.getString("valor"));
            combo.setGrupo(rs.getString("valor"));
            combo.setPertenece(rs.getLong("pertenece"));
            combo.setOrden(rs.getInt("orden"));
            combo.setRama(rs.getString("rama"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        }
        return combo;
    }

    public ArrayList<Combo> getListaRegistros(String grupo, String rama) {
        Connection connection = null;
        ArrayList<Combo> lista = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM combos where grupo='" + grupo + "' AND rama='" + rama + "'  ORDER BY  descripcion ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Combo combo = getRegistroResulset(resulSet);
                lista.add(combo);
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
        return lista;
    }

    public ArrayList<String> getListaString(String grupo, String rama) {
        Connection connection = null;
        ArrayList<String> lista = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM combos where grupo='" + grupo + "' AND rama='" + rama + "'  ORDER BY  orden  ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Combo combo = getRegistroResulset(resulSet);
                lista.add(resulSet.getString("valor"));
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
        return lista;
    }
}
