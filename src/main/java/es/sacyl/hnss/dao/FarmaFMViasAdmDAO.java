/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class FarmaFMViasAdmDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FarmaFMViasAdmDAO.class);

    public FarmaFMViasAdmDAO() {
        super();
    }

    private FarmaFMViasAdm getRegistroResulset(ResultSet rs) {
        FarmaFMViasAdm farmaFMViasAdm = new FarmaFMViasAdm();
        try {
            farmaFMViasAdm.setCodigo(rs.getString("codigo"));
            farmaFMViasAdm.setNombre(rs.getString("nombre"));

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return farmaFMViasAdm;
    }

    public FarmaFMViasAdm getPorCodigo(String codigo) {
        Connection connection = null;
        FarmaFMViasAdm farmaFMViasAdm = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_viaadm WHERE   codigo='" + codigo + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMViasAdm = getRegistroResulset(resulSet);
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
        return farmaFMViasAdm;
    }

    public FarmaFMViasAdm getPorDescripcion(String nombre) {
        Connection connection = null;
        FarmaFMViasAdm farmaFMViasAdm = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_viaadm WHERE   nombre='" + nombre + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMViasAdm = getRegistroResulset(resulSet);
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
        return farmaFMViasAdm;
    }

    public boolean doGrabaDatos(FarmaFMViasAdm farmaFMViasAdm) {
        boolean actualizado = false;

        if (this.getPorCodigo(farmaFMViasAdm.getCodigo()) == null) {
            actualizado = this.doInsertaDatos(farmaFMViasAdm);
        } else {
            actualizado = this.doActualizaDatos(farmaFMViasAdm);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FarmaFMViasAdm farmaFMViasAdm) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  farm_fm_viaadm (codigo,nombre) " + " VALUES ('" + farmaFMViasAdm.getCodigo() + "','" + farmaFMViasAdm.getNombre() + "')";
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

    public boolean doActualizaDatos(FarmaFMViasAdm farmaFMViasAdm) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   farm_fm_viaadm  SET nombre='" + farmaFMViasAdm.getNombre() + "' WHERE codigo='" + farmaFMViasAdm.getCodigo() + "'";
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

    public boolean doBorraDatos(FarmaFMViasAdm farmaFMViasAdm) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_viaadm WHERE codigo='" + farmaFMViasAdm.getCodigo() + "'";
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

    public ArrayList<FarmaFMViasAdm> getListaViasAdm(String texto) {
        Connection connection = null;
        ArrayList<FarmaFMViasAdm> listaVias = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_viaadm WHERE  1=1 ";
            if (texto != null && !texto.isEmpty()) {
                sql = sql.concat(" AND (codigo like'%" + texto + "%'  OR nombre like'%" + texto + "%')");
            }
            sql = sql.concat("ORDER BY nombre");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaVias.add(getRegistroResulset(resulSet));
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
        return listaVias;
    }

}
