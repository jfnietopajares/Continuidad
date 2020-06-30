package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FarmaFMInstrumento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author JuanNieto
 */
public class FarmaFMInstrumentosDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FarmaFMInstrumentosDAO.class);

    public FarmaFMInstrumentosDAO() {
        super();
    }

    private FarmaFMInstrumento getRegistroResulset(ResultSet rs) {
        FarmaFMInstrumento farmaFMInstrumento = new FarmaFMInstrumento();
        try {
            farmaFMInstrumento.setCodigo(rs.getString("codigo"));
            farmaFMInstrumento.setNombre(rs.getString("nombre"));

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return farmaFMInstrumento;
    }

    public FarmaFMInstrumento getPorCodigo(String codigo) {
        Connection connection = null;
        FarmaFMInstrumento farmaFMInstrumento = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_instrumentos WHERE   codigo='" + codigo + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMInstrumento = getRegistroResulset(resulSet);
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return farmaFMInstrumento;
    }

    public FarmaFMInstrumento getPorDescripcion(String nombre) {
        Connection connection = null;
        FarmaFMInstrumento farmaFMInstrumento = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_instrumentos WHERE   nombre='" + nombre + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMInstrumento = getRegistroResulset(resulSet);
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return farmaFMInstrumento;
    }

    public boolean insertaDatos(FarmaFMInstrumento farmaFMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  farm_fm_instrumentos (codigo,nombre) " + " VALUES ('" + farmaFMInstrumento.getCodigo() + "','" + farmaFMInstrumento.getNombre() + "')";
            Statement statement = connection.createStatement();
            insertadoBoolean = statement.execute(sql);
            insertadoBoolean = true;
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public boolean doGrabaDatos(FarmaFMInstrumento farmaFMInstrumento) {
        boolean actualizado = false;

        if (this.getPorCodigo(farmaFMInstrumento.getCodigo()) == null) {
            actualizado = this.doInsertaDatos(farmaFMInstrumento);
        } else {
            actualizado = this.doActualizaDatos(farmaFMInstrumento);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FarmaFMInstrumento farmaFMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  farm_fm_instrumentos (codigo,nombre) " + " VALUES ('" + farmaFMInstrumento.getCodigo() + "','" + farmaFMInstrumento.getNombre() + "')";
            Statement statement = connection.createStatement();
            insertadoBoolean = statement.execute(sql);
            insertadoBoolean = true;
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public boolean doActualizaDatos(FarmaFMInstrumento farmaFMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   farm_fm_instrumentos  SET nombre='" + farmaFMInstrumento.getNombre() + "' WHERE codigo='" + farmaFMInstrumento.getCodigo() + "'";
            Statement statement = connection.createStatement();
            insertadoBoolean = statement.execute(sql);
            insertadoBoolean = true;
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public boolean doBorraDatos(FarmaFMInstrumento farmaFMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_instrumentos WHERE codigo='" + farmaFMInstrumento.getCodigo() + "'";
            Statement statement = connection.createStatement();
            insertadoBoolean = statement.execute(sql);
            insertadoBoolean = true;
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public ArrayList<FarmaFMInstrumento> getListaInstrumentos(String texto) {
        Connection connection = null;
        ArrayList<FarmaFMInstrumento> listaInstrumentos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_instrumentos WHERE  1=1 ";
            if (texto != null && !texto.isEmpty()) {
                sql = sql.concat(" AND (codigo like'%" + texto + "%'  OR nombre like'%" + texto + "%')");
            }
            sql = sql.concat("ORDER BY nombre");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaInstrumentos.add(getRegistroResulset(resulSet));
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaInstrumentos;
    }

}
