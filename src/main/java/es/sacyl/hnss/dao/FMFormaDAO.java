/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMForma;
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
public class FMFormaDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FMFormaDAO.class);
    private static final long serialVersionUID = 1L;

    public FMFormaDAO() {
        super();
    }

    private FMForma getRegistroResulset(ResultSet rs) {
        FMForma fMForma = new FMForma();
        try {
            fMForma.setCodigo(rs.getString("codigo").trim());
            fMForma.setNombre(rs.getString("nombre").trim());

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return fMForma;
    }

    public FMForma getPorCodigo(String codigo) {
        Connection connection = null;
        FMForma fMForma = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_tipoforma WHERE   codigo='" + codigo + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMForma = getRegistroResulset(resulSet);
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return fMForma;
    }

    public FMForma getPorDescripcion(String nombre) {
        Connection connection = null;
        FMForma fMForma = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_tipoforma WHERE   nombre='" + nombre + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMForma = getRegistroResulset(resulSet);
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return fMForma;
    }

    public boolean doGrabaDatos(FMForma fMForma) {
        boolean actualizado = false;

        if (this.getPorCodigo(fMForma.getCodigo()) == null) {
            actualizado = this.doInsertaDatos(fMForma);
        } else {
            actualizado = this.doActualizaDatos(fMForma);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMForma fMForma) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " INSERT INTO  farm_fm_tipoforma (codigo,nombre) " + " VALUES ('" + fMForma.getCodigo() + "','" + fMForma.getNombre() + "')";
            Statement statement = connection.createStatement();
            insertadoBoolean = statement.execute(sql);
            insertadoBoolean = true;
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return insertadoBoolean;
    }

    public boolean doActualizaDatos(FMForma fMForma) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   farm_fm_tipoforma  SET nombre='" + fMForma.getNombre() + "' WHERE codigo='" + fMForma.getCodigo() + "'";
            Statement statement = connection.createStatement();
            insertadoBoolean = statement.execute(sql);
            insertadoBoolean = true;
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return insertadoBoolean;
    }

    public boolean doBorraDatos(FMForma fMForma) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_tipoforma WHERE codigo='" + fMForma.getCodigo() + "'";
            Statement statement = connection.createStatement();
            insertadoBoolean = statement.execute(sql);
            insertadoBoolean = true;
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return insertadoBoolean;
    }

    public ArrayList<FMForma> getListaFormas(String texto) {
        Connection connection = null;
        ArrayList<FMForma> listaFormas = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_tipoforma WHERE  1=1 ";
            if (texto != null && !texto.isEmpty()) {
                sql = sql.concat(" AND  UPPER(nombre) like'%" + texto.toUpperCase() + "%'");
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
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaFormas;
    }
}
