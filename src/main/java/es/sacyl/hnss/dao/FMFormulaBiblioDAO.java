/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaBibliografia;
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
public class FMFormulaBiblioDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FMFormulaBiblioDAO.class);

    public FMFormulaBiblioDAO() {
        super();
    }

    private FMFormulaBibliografia getRegistroResulset(ResultSet rs) {
        FMFormulaBibliografia fMFormulaBibliografia = new FMFormulaBibliografia();
        try {
            fMFormulaBibliografia.setFormula(rs.getInt("formula"));
            fMFormulaBibliografia.setOrden(rs.getInt("orden"));
            fMFormulaBibliografia.setTexto(rs.getString("texto"));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return fMFormulaBibliografia;
    }

    public FMFormulaBibliografia getPorCodigo(FMFormula fMFormula, Integer orden) {
        Connection connection = null;
        FMFormulaBibliografia fMFormulaBibliografia = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_bib WHERE   formula = "
                    + fMFormula.getNumero() + " AND orden =" + orden;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormulaBibliografia = getRegistroResulset(resulSet);
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
        return fMFormulaBibliografia;
    }

    public FMFormulaBibliografia getPorDescripcion(String nombre) {
        Connection connection = null;
        FMFormulaBibliografia fMFormulaBibliografia = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_bib WHERE   texto like '%" + nombre + "%'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormulaBibliografia = getRegistroResulset(resulSet);
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
        return fMFormulaBibliografia;
    }

    public boolean doGrabaDatos(FMFormula fMFormula, FMFormulaBibliografia fMFormulaBibliografia) {
        boolean actualizado = false;

        if (this.getPorCodigo(fMFormula, fMFormulaBibliografia.getOrden()) == null) {
            actualizado = this.doInsertaDatos(fMFormulaBibliografia);
        } else {
            actualizado = this.doActualizaDatos(fMFormulaBibliografia);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMFormulaBibliografia fMFormulaBibliografia) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "INSERT INTO     farm_fm_formulas_bib "
                    + "(forma,orden,texto)"
                    + " VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMFormulaBibliografia == null && fMFormulaBibliografia.getFormula() != null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, fMFormulaBibliografia.getFormula());
            }
            if (fMFormulaBibliografia.getOrden() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setInt(2, fMFormulaBibliografia.getOrden());
            }
            if (fMFormulaBibliografia.getTexto() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, fMFormulaBibliografia.getTexto());
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

    public boolean doActualizaDatos(FMFormulaBibliografia fMFormulaBibliografia) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "UPDATE     farm_fm_formulas_bib SET texto=?"
                    + " WHERE formula=? AND orden?";
            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMFormulaBibliografia.getTexto() == null) {
                statement.setNull(1, Types.VARCHAR);
            } else {
                statement.setString(1, fMFormulaBibliografia.getTexto());
            }

            if (fMFormulaBibliografia == null && fMFormulaBibliografia.getFormula() != null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, fMFormulaBibliografia.getFormula());
            }
            if (fMFormulaBibliografia.getOrden() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setInt(3, fMFormulaBibliografia.getOrden());
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

    public boolean doBorraDatos(FMFormulaBibliografia fMFormulaBibliografia) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_formulas_bib WHERE format=" + fMFormulaBibliografia.getFormula() + " "
                    + " AND orden=" + fMFormulaBibliografia.getOrden();
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

    public ArrayList<FMFormulaBibliografia> getListaBiblio(FMFormulaBibliografia fMFormulaBibliografia) {
        Connection connection = null;
        ArrayList<FMFormulaBibliografia> listaBiblio = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_bib WHERE format=" + fMFormulaBibliografia.getFormula() + " "
                    + " AND orden=" + fMFormulaBibliografia.getOrden() + " ORDER BY orden";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                FMFormulaBibliografia biblio = getRegistroResulset(resulSet);
                biblio.setFormula(fMFormulaBibliografia.getFormula());
                listaBiblio.add(getRegistroResulset(resulSet));

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
        return listaBiblio;
    }
}
