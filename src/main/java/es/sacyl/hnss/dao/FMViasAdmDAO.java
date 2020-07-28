/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMViasAdm;
import java.io.Serializable;
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
public class FMViasAdmDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FMViasAdmDAO.class);
    private static final long serialVersionUID = 1L;

    public FMViasAdmDAO() {
        super();
    }

    private FMViasAdm getRegistroResulset(ResultSet rs) {
        FMViasAdm fMViasAdm = new FMViasAdm();
        try {
            fMViasAdm.setCodigo(rs.getString("codigo"));
            fMViasAdm.setNombre(rs.getString("nombre"));

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return fMViasAdm;
    }

    public FMViasAdm getPorCodigo(String codigo) {
        Connection connection = null;
        FMViasAdm fMViasAdm = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_viaadm WHERE   codigo='" + codigo + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMViasAdm = getRegistroResulset(resulSet);
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
        return fMViasAdm;
    }

    public FMViasAdm getPorDescripcion(String nombre) {
        Connection connection = null;
        FMViasAdm fMViasAdm = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_viaadm WHERE   nombre='" + nombre + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMViasAdm = getRegistroResulset(resulSet);
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
        return fMViasAdm;
    }

    public boolean doGrabaDatos(FMViasAdm fMViasAdm) {
        boolean actualizado = false;

        if (this.getPorCodigo(fMViasAdm.getCodigo()) == null) {
            actualizado = this.doInsertaDatos(fMViasAdm);
        } else {
            actualizado = this.doActualizaDatos(fMViasAdm);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMViasAdm fMViasAdm) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  farm_fm_viaadm (codigo,nombre) " + " VALUES ('" + fMViasAdm.getCodigo() + "','" + fMViasAdm.getNombre() + "')";
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

    public boolean doActualizaDatos(FMViasAdm fMViasAdm) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   farm_fm_viaadm  SET nombre='" + fMViasAdm.getNombre() + "' WHERE codigo='" + fMViasAdm.getCodigo() + "'";
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

    public boolean doBorraDatos(FMViasAdm fMViasAdm) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_viaadm WHERE codigo='" + fMViasAdm.getCodigo() + "'";
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

    public ArrayList<FMViasAdm> getListaViasAdm(String texto) {
        Connection connection = null;
        ArrayList<FMViasAdm> listaVias = new ArrayList<>();
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
