/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaBibliografia;
import es.sacyl.hnss.entidades.FMFormulaCompo;
import es.sacyl.hnss.entidades.FMMPrimas;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author JuanNieto
 */
public class FMFormulaCompoDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FMFormulaCompoDAO.class);
    private static final long serialVersionUID = 1L;

    public FMFormulaCompoDAO() {
        super();
    }

    private FMFormulaCompo getRegistroResulset(ResultSet rs, FMMPrimas fMMprima) {
        FMFormulaCompo fMFormulaCompo = new FMFormulaCompo();
        try {
            fMFormulaCompo.setFormula(rs.getInt("formula"));
            fMFormulaCompo.setOrden(rs.getInt("orden"));
            fMFormulaCompo.setCantidad(rs.getBigDecimal("cantidad"));
            fMFormulaCompo.setUnidades(rs.getString("unidades"));
            if (fMMprima == null) {
                fMFormulaCompo.setMprima(new FMMprimasDAO().getPorCodigo(rs.getInt("cod_inte")));
            } else {
                fMFormulaCompo.setMprima(fMMprima);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return fMFormulaCompo;
    }

    public FMFormulaCompo getPorCodigo(FMFormula fMFormula, FMMPrimas fMMprima, Integer orden) {
        Connection connection = null;
        FMFormulaCompo fMFormulaCompo = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_comp WHERE   formula = "
                    + fMFormula.getNumero() + " AND  cod_inte=" + fMMprima.getCod_inte() + " AND  orden =" + orden;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormulaCompo = getRegistroResulset(resulSet, fMMprima);
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
        return fMFormulaCompo;
    }

    public FMFormulaCompo getPorDescripcion(String nombre) {
        Connection connection = null;
        FMFormulaCompo fMFormulaCompo = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_comp WHERE   composicion like '%" + nombre + "%'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormulaCompo = getRegistroResulset(resulSet, null);
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
        return fMFormulaCompo;
    }

    public boolean doGrabaDatos(FMFormula fMFormula, FMFormulaCompo fMFormulaCompo) {
        boolean actualizado = false;

        if (this.getPorCodigo(fMFormula, fMFormulaCompo.getMprima(), fMFormulaCompo.getOrden()) == null) {
            actualizado = this.doInsertaDatos(fMFormulaCompo);
        } else {
            actualizado = this.doActualizaDatos(fMFormulaCompo);

        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMFormulaCompo fMFormulaCompo) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "INSERT INTO     farm_fm_formulas_comp "
                    + "(formula,orden,cantidad,unidades,cod_inte)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMFormulaCompo == null && fMFormulaCompo.getFormula() != null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, fMFormulaCompo.getFormula());
            }
            if (fMFormulaCompo.getOrden() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setInt(2, fMFormulaCompo.getOrden());
            }

            if (fMFormulaCompo.getCantidad() == null) {
                statement.setNull(3, Types.FLOAT);
            } else {
                statement.setBigDecimal(3, fMFormulaCompo.getCantidad());
            }
            if (fMFormulaCompo.getUnidades() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, fMFormulaCompo.getUnidades());
            }
            if (fMFormulaCompo == null && fMFormulaCompo.getMprima() != null && fMFormulaCompo.getMprima().getCod_inte() != null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, fMFormulaCompo.getMprima().getCod_inte());
            }
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

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

    public boolean doActualizaDatos(FMFormulaCompo fMFormulaCompo) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "UPDATE     farm_fm_formulas_comp "
                    + " SET cantidad=?,unidades=?"
                    + " WHERE formula=?  AND orden=?  AND cod_inte=?   ";

            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMFormulaCompo.getCantidad() == null) {
                statement.setNull(1, Types.FLOAT);
            } else {
                statement.setBigDecimal(1, fMFormulaCompo.getCantidad());
            }
            if (fMFormulaCompo.getUnidades() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, fMFormulaCompo.getUnidades());
            }

            if (fMFormulaCompo == null && fMFormulaCompo.getFormula() != null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, fMFormulaCompo.getFormula());
            }
            if (fMFormulaCompo.getOrden() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setInt(4, fMFormulaCompo.getOrden());
            }
            if (fMFormulaCompo == null && fMFormulaCompo.getMprima() != null && fMFormulaCompo.getMprima().getCod_inte() != null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, fMFormulaCompo.getMprima().getCod_inte());
            }
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

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

    public boolean doBorraDatos(FMFormulaCompo fMFormulaCompo) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_formulas_comp WHERE formula=" + fMFormulaCompo.getFormula() + " "
                    + " AND orden=" + fMFormulaCompo.getOrden() + " AND cod_inte=" + fMFormulaCompo.getMprima().getCod_inte();

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

    public ArrayList<FMFormulaCompo> getListaCompos(FMFormula fMFormula) {
        Connection connection = null;
        ArrayList<FMFormulaCompo> listaCompos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_comp WHERE formula=" + fMFormula.getNumero() + " "
                    + " ORDER BY orden";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaCompos.add(getRegistroResulset(resulSet, null));

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
        return listaCompos;
    }
}
