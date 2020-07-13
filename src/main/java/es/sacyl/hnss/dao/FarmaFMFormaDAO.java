/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FarmaFMForma;
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
public class FarmaFMFormaDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FarmaFMFormaDAO.class);

    public FarmaFMFormaDAO() {
        super();
    }

    private FarmaFMForma getRegistroResulset(ResultSet rs) {
        FarmaFMForma farmaFMForma = new FarmaFMForma();
        try {
            farmaFMForma.setCodigo(rs.getString("codigo"));
            farmaFMForma.setNombre(rs.getString("nombre"));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return farmaFMForma;
    }

    public FarmaFMForma getPorCodigo(String codigo) {
        Connection connection = null;
        FarmaFMForma farmaFMForma = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_tipoforma WHERE   codigo='" + codigo + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMForma = getRegistroResulset(resulSet);
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
        return farmaFMForma;
    }

    public FarmaFMForma getPorDescripcion(String nombre) {
        Connection connection = null;
        FarmaFMForma farmaFMForma = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_tipoforma WHERE   nombre='" + nombre + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMForma = getRegistroResulset(resulSet);
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
        return farmaFMForma;
    }

    public boolean doGrabaDatos(FarmaFMForma farmaFMForma) {
        boolean actualizado = false;

        if (this.getPorCodigo(farmaFMForma.getCodigo()) == null) {
            actualizado = this.doInsertaDatos(farmaFMForma);
        } else {
            actualizado = this.doActualizaDatos(farmaFMForma);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FarmaFMForma farmaFMForma) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  farm_fm_tipoforma (codigo,nombre) " + " VALUES ('" + farmaFMForma.getCodigo() + "','" + farmaFMForma.getNombre() + "')";
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

    public boolean doActualizaDatos(FarmaFMForma farmaFMForma) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   farm_fm_tipoforma  SET nombre='" + farmaFMForma.getNombre() + "' WHERE codigo='" + farmaFMForma.getCodigo() + "'";
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

    public boolean doBorraDatos(FarmaFMForma farmaFMForma) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_tipoforma WHERE codigo='" + farmaFMForma.getCodigo() + "'";
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

    public ArrayList<FarmaFMForma> getListaFormas(String texto) {
        Connection connection = null;
        ArrayList<FarmaFMForma> listaFormas = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_tipoforma WHERE  1=1 ";
            if (texto != null && !texto.isEmpty()) {
                sql = sql.concat(" AND (codigo like'%" + texto + "%'  OR nombre like'%" + texto + "%')");
            }
            sql = sql.concat("ORDER BY nombre");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaFormas.add(getRegistroResulset(resulSet));
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
        return listaFormas;
    }
}
