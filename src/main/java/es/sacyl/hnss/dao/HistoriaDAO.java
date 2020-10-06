package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Historia;
import es.sacyl.hnss.entity.Paciente;
import es.sacyl.hnss.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The Class HistoriaDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class HistoriaDAO extends ConexionDAO  {

    private String sql;

    private static final Logger logger = LogManager.getLogger(HistoriaDAO.class);

    /**
     * Instantiates a new historia DAO.
     */
    public HistoriaDAO() {
        super();
    }

    /**
     * Inserta nhc.
     *
     * @param historia the historia
     * @return true, if successful
     */
    public boolean insertaNhc(Historia historia) {
        Connection connection = null;
        boolean rowInserted = false;
        Long id = new Long(0);
        try {
            id = new UtilidadesDAO().getSiguienteId("historias");
            connection = super.getConexionBBDD();
            sql = " INSERT INTO historias  (id,  paciente ,  nhc )  VALUES (?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setLong(2, historia.getPaciente());
            statement.setString(3, historia.getNhc());
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO historias  (id,  paciente ,  nhc )  VALUES (" + id + "," + historia.getPaciente()
                    + ",'" + historia.getNhc() + "')  ");
        } catch (SQLException e) {
            logger.error(" INSERT INTO historias  (id,  paciente ,  nhc )  VALUES (" + id + "," + historia.getPaciente()
                    + ",'" + historia.getNhc() + "')  ");
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
        return rowInserted;
    }

  
    public boolean borraDatos(Object objeto) {
        Connection connection = null;
        Paciente paciente = (Paciente) objeto;
        boolean rowInserted = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM    historias  WHERE paciente=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" DELETE FROM    historias  WHERE paciente=" + paciente.getId());
        } catch (SQLException e) {
            logger.error(" DELETE FROM    historias  WHERE paciente=" + paciente.getId());
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
        return rowInserted;
    }

 

    
    public boolean actualizaDatos(Object mensajeparam) {
        Connection connection = null;
        Historia hitoriaHistoria = (Historia) mensajeparam;
        boolean rowInserted = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   historias  SET nhc=? WHERE paciente =? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, hitoriaHistoria.getNhc());
            statement.setLong(2, hitoriaHistoria.getPaciente());
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            statement.close();
            logger.debug(" UPDATE   historias  SET nhc='" + hitoriaHistoria.getNhc() + "' WHERE paciente = "
                    + hitoriaHistoria.getPaciente());
        } catch (SQLException e) {
            logger.error(" UPDATE   historias  SET nhc='" + hitoriaHistoria.getNhc() + "' WHERE paciente = "
                    + hitoriaHistoria.getPaciente());
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
        return rowInserted;
    }

}
