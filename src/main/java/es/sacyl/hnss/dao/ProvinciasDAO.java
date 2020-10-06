package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Provincia;
import es.sacyl.hnss.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * The Class ProvinciasDAO. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 *
 */
public class ProvinciasDAO extends ConexionDAO implements InterfaceDAO {

    private String sql;

    private Provincia provincia;

    private static final Logger logger = LogManager.getLogger(ProvinciasDAO.class);

    /**
     * Instantiates a new provincias DAO.
     */
    public ProvinciasDAO() {
        super();
    }

    /**
     * Gets the lista provincias.
     *
     * @return the lista provincias
     */
    public ArrayList<Provincia> getListaProvincias() {
        Connection connection = null;
        ArrayList<Provincia> listaProvincias = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT codigo,descripcion FROM provincias  ORDER BY  descripcion ";

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                provincia = new Provincia(resulSet.getString("codigo"), resulSet.getString("descripcion"));
                listaProvincias.add(provincia);
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
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaProvincias;
    }

    /**
     * Gets the por codigo.
     *
     * @param codigo the codigo
     * @return the por codigo
     */
    public Provincia getPorCodigo(String codigo) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT codigo,descripcion FROM provincias WHERE codigo=?  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, codigo);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                provincia = new Provincia(resulSet.getString("codigo"), resulSet.getString("descripcion"));
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
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return provincia;
    }

    @Override
    public boolean getReferenciasExternas(Long id) {
        return false;
    }

    @Override
    public boolean grabaDatos(Object object) {
        return false;
    }

    @Override
    public boolean actualizaDatos(Object mensajeparam) {
        return false;
    }

    @Override
    public boolean insertaDatos(Object mensajeparam) {
        return false;
    }

    @Override
    public Object getRegistroResulset(ResultSet rs) {
        return null;
    }

    @Override
    public Object getRegistroId(Long id) {
        return null;
    }

    @Override
    public String getSqlWhere(String cadena) {
        return null;
    }

    @Override
    public boolean borraDatos(Object objeto) {
        return false;
    }
}
