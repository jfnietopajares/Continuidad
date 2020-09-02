/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaMaterial;
import es.sacyl.hnss.entidades.FMInstrumento;
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
public class FMFormulaMaterialDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FMFormulaMaterialDAO.class);
    private static final long serialVersionUID = 1L;

    public FMFormulaMaterialDAO() {
        super();
    }

    private FMFormulaMaterial getRegistroResulset(ResultSet rs, FMInstrumento fMInstrumento) {
        FMFormulaMaterial fMFormulaMeterial = new FMFormulaMaterial();
        try {
            fMFormulaMeterial.setFormula(rs.getInt("formula"));
            fMFormulaMeterial.setLinea(rs.getInt("linea"));
            fMFormulaMeterial.setUnidades(rs.getInt("unidades"));
            fMFormulaMeterial.setComentario(rs.getString("comentario").trim());
            if (fMInstrumento == null) {
                fMFormulaMeterial.setInstrumento(new FMInstrumentosDAO().getPorCodigo(rs.getString("codigo")));
            } else {
                fMFormulaMeterial.setInstrumento(fMInstrumento);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return fMFormulaMeterial;
    }

    public FMFormulaMaterial getPorCodigo(FMFormula fMFormula, FMInstrumento fMInstrumento, Integer linea) {
        Connection connection = null;
        FMFormulaMaterial fMFormulaMeterial = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_mat WHERE   formula = "
                    + fMFormula.getNumero() + " AND codigo='" + fMInstrumento.getCodigo() + "'" + " AND linea =" + linea;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormulaMeterial = getRegistroResulset(resulSet, fMInstrumento);
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
        return fMFormulaMeterial;
    }

    public boolean doGrabaDatos(FMFormula fMFormula, FMFormulaMaterial fMFormulaMeterial) {
        boolean actualizado = false;

        if (this.getPorCodigo(fMFormula, fMFormulaMeterial.getInstrumento(), fMFormulaMeterial.getLinea()) == null) {
            actualizado = this.doInsertaDatos(fMFormulaMeterial);
        } else {
            actualizado = this.doActualizaDatos(fMFormulaMeterial);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMFormulaMaterial fMFormulaMeterial) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "INSERT INTO     farm_fm_formulas_mat "
                    + "(formula,linea,comentario,codigo,unidades)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMFormulaMeterial == null && fMFormulaMeterial.getFormula() != null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, fMFormulaMeterial.getFormula());
            }
            if (fMFormulaMeterial.getLinea() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setInt(2, fMFormulaMeterial.getLinea());
            }
            if (fMFormulaMeterial.getComentario() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, fMFormulaMeterial.getComentario());
            }
            if (fMFormulaMeterial.getInstrumento() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, fMFormulaMeterial.getInstrumento().getCodigo());
            }
            if (fMFormulaMeterial.getUnidades() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setInt(5, fMFormulaMeterial.getUnidades());
            }
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

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

    public boolean doBorraDatos(FMFormulaMaterial fMFormulaMeterial) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_formulas_mat WHERE formula=" + fMFormulaMeterial.getFormula() + " "
                    + " AND codigo=" + fMFormulaMeterial.getInstrumento().getCodigo()
                    + " AND linea=" + fMFormulaMeterial.getLinea();
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

    public boolean doActualizaDatos(FMFormulaMaterial fMFormulaMeterial) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "UPDATE     farm_fm_formulas_mat SET comentario=?,unidades=?"
                    + " WHERE formula=? AND codigo=? AND linea=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMFormulaMeterial.getComentario() == null) {
                statement.setNull(1, Types.VARCHAR);
            } else {
                statement.setString(1, fMFormulaMeterial.getComentario());
            }
            if (fMFormulaMeterial.getUnidades() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, fMFormulaMeterial.getUnidades());
            }
            if (fMFormulaMeterial == null && fMFormulaMeterial.getFormula() != null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, fMFormulaMeterial.getFormula());
            }
            if (fMFormulaMeterial == null && fMFormulaMeterial.getInstrumento() != null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, fMFormulaMeterial.getInstrumento().getCodigo());
            }

            if (fMFormulaMeterial.getLinea() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setInt(5, fMFormulaMeterial.getLinea());
            }
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

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

    public ArrayList<FMFormulaMaterial> getListaMateriales(FMFormula fMFormula) {
        Connection connection = null;
        ArrayList<FMFormulaMaterial> listaMaterial = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_mat WHERE formula=" + fMFormula.getNumero() + " "
                    + " ORDER BY codigo,linea";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaMaterial.add(getRegistroResulset(resulSet, null));

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
        return listaMaterial;
    }
}
