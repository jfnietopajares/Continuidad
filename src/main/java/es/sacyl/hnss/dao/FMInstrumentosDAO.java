package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMInstrumento;
import java.io.Serializable;
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
public class FMInstrumentosDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FMInstrumentosDAO.class);
    private static final long serialVersionUID = 1L;

    public FMInstrumentosDAO() {
        super();
    }

    private FMInstrumento getRegistroResulset(ResultSet rs) {
        FMInstrumento fMInstrumento = new FMInstrumento();
        try {
            fMInstrumento.setCodigo(rs.getString("codigo").trim());
            fMInstrumento.setNombre(rs.getString("nombre").trim());

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return fMInstrumento;
    }

    public FMInstrumento getPorCodigo(String codigo) {
        Connection connection = null;
        FMInstrumento fMInstrumento = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_instrumentos WHERE   codigo='" + codigo + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMInstrumento = getRegistroResulset(resulSet);
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
        return fMInstrumento;
    }

    public FMInstrumento getPorDescripcion(String nombre) {
        Connection connection = null;
        FMInstrumento fMInstrumento = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_instrumentos WHERE   nombre='" + nombre + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMInstrumento = getRegistroResulset(resulSet);
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
        return fMInstrumento;
    }

    public boolean insertaDatos(FMInstrumento fMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  farm_fm_instrumentos (codigo,nombre) " + " VALUES ('" + fMInstrumento.getCodigo() + "','" + fMInstrumento.getNombre() + "')";
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

    public boolean doGrabaDatos(FMInstrumento fMInstrumento) {
        boolean actualizado = false;

        if (this.getPorCodigo(fMInstrumento.getCodigo()) == null) {
            actualizado = this.doInsertaDatos(fMInstrumento);
        } else {
            actualizado = this.doActualizaDatos(fMInstrumento);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMInstrumento fMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  farm_fm_instrumentos (codigo,nombre) " + " VALUES ('" + fMInstrumento.getCodigo() + "','" + fMInstrumento.getNombre() + "')";
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

    public boolean doActualizaDatos(FMInstrumento fMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   farm_fm_instrumentos  SET nombre='" + fMInstrumento.getNombre() + "' WHERE codigo='" + fMInstrumento.getCodigo() + "'";
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

    public boolean doBorraDatos(FMInstrumento fMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_instrumentos WHERE codigo='" + fMInstrumento.getCodigo() + "'";
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

    public ArrayList<FMInstrumento> getListaInstrumentos(String texto) {
        Connection connection = null;
        ArrayList<FMInstrumento> listaInstrumentos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_instrumentos WHERE  1=1 ";
            if (texto != null && !texto.isEmpty()) {
               // sql = sql.concat(" AND (codigo like'%" + texto + "%'  OR nombre like'%" + texto + "%')");
                sql = sql.concat(" AND  upper(nombre) like'%" + texto.toUpperCase() + "%'");
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
