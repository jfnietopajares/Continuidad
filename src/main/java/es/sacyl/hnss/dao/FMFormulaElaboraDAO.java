/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaBibliografia;
import es.sacyl.hnss.entidades.FMFormulaElabora;
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
public class FMFormulaElaboraDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FMFormulaElaboraDAO.class);
    private static final long serialVersionUID = 1L;

    public FMFormulaElaboraDAO() {
        super();
    }

    private FMFormulaElabora getRegistroResulset(ResultSet rs) {
        FMFormulaElabora fMFormulaElabora = new FMFormulaElabora();
        try {
            fMFormulaElabora.setFormula(rs.getInt("formula"));
            fMFormulaElabora.setOrden(rs.getInt("orden"));
            fMFormulaElabora.setTexto(rs.getString("texto").trim());

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return fMFormulaElabora;
    }

    public FMFormulaElabora getPorCodigo(FMFormula fMFormula, Integer orden) {
        Connection connection = null;
        FMFormulaElabora fMFormulaElabora = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_ela WHERE   formula = "
                    + fMFormula.getNumero() + " AND orden =" + orden;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormulaElabora = getRegistroResulset(resulSet);
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
        return fMFormulaElabora;
    }

    public FMFormulaElabora getPorDescripcion(String nombre) {
        Connection connection = null;
        FMFormulaElabora fMFormulaElabora = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_ela WHERE   texto like '%" + nombre + "%'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormulaElabora = getRegistroResulset(resulSet);
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
        return fMFormulaElabora;
    }

    public boolean doGrabaDatos(FMFormula fMFormula, FMFormulaElabora fMFormulaElabora) {
        boolean actualizado = false;

        if (this.getPorCodigo(fMFormula, fMFormulaElabora.getOrden()) == null) {
            actualizado = this.doInsertaDatos(fMFormulaElabora);
        } else {
            actualizado = this.doActualizaDatos(fMFormulaElabora);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMFormulaElabora fMFormulaElabora) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "INSERT INTO     farm_fm_formulas_ela "
                    + "(formula,orden,texto)"
                    + " VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMFormulaElabora == null && fMFormulaElabora.getFormula() != null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, fMFormulaElabora.getFormula());
            }
            if (fMFormulaElabora.getOrden() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setInt(2, fMFormulaElabora.getOrden());
            }
            if (fMFormulaElabora.getTexto() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, fMFormulaElabora.getTexto());
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

    public boolean doActualizaDatos(FMFormulaElabora fMFormulaElabora) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "UPDATE     farm_fm_formulas_ela SET texto=?"
                    + " WHERE formula=? AND orden=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMFormulaElabora.getTexto() == null) {
                statement.setNull(1, Types.VARCHAR);
            } else {
                statement.setString(1, fMFormulaElabora.getTexto());
            }

            if (fMFormulaElabora == null && fMFormulaElabora.getFormula() != null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, fMFormulaElabora.getFormula());
            }
            if (fMFormulaElabora.getOrden() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setInt(3, fMFormulaElabora.getOrden());
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

    public boolean doBorraDatos(FMFormulaElabora fMFormulaElabora) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_formulas_ela WHERE formula=" + fMFormulaElabora.getFormula() + " "
                    + " AND orden=" + fMFormulaElabora.getOrden();
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

    public ArrayList<FMFormulaElabora> getListaElabora(FMFormula fMFormula) {
        Connection connection = null;
        ArrayList<FMFormulaElabora> listaElabora = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_ela WHERE formula=" + fMFormula.getNumero() + " "
                    + " ORDER BY orden";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                //   FMFormulaElabora biblio = getRegistroResulset(resulSet);
                listaElabora.add(getRegistroResulset(resulSet));

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
        return listaElabora;
    }
}
