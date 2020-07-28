/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaFrabicar;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author JuanNieto
 */
public class FMFormulaFrabicarDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FMFormulaFrabicarDAO.class);
    private static final long serialVersionUID = 1L;

    DateTimeFormatter dateTimeFormatterParser = DateTimeFormatter.ofPattern("yyyyMMdd");

    public FMFormulaFrabicarDAO() {
        super();
    }

    private FMFormulaFrabicar getRegistroResulset(ResultSet rs, FMFormula fMFormula) {
        FMFormulaFrabicar fMFormulaFrabicar = new FMFormulaFrabicar();
        try {
            LocalDate localFecha = null;
            if (rs.getInt("fecha") != 0) {
                String ulti = Integer.toString(rs.getInt("fecha"));
                localFecha = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            fMFormulaFrabicar.setNumero(rs.getInt("numero"));
            fMFormulaFrabicar.setFecha(localFecha);
            if (fMFormula == null) {
                fMFormulaFrabicar.setFormula(new FMFormulasDAO().getPorCodigo(rs.getInt("formula")));
            } else {
                fMFormulaFrabicar.setFormula(fMFormula);
            }
            fMFormulaFrabicar.setMedico(rs.getString("medico"));
            fMFormulaFrabicar.setPaciente(rs.getString("paciente"));
            fMFormulaFrabicar.setCaducidad(rs.getInt("caducidad"));
            fMFormulaFrabicar.setRegistro(rs.getInt("registro"));
            fMFormulaFrabicar.setLote(rs.getString("lote"));
            fMFormulaFrabicar.setCcalidad(rs.getString("ccalidad"));
            fMFormulaFrabicar.setUnidades(rs.getInt("unidades"));
            fMFormulaFrabicar.setPreparado(rs.getString("preparado"));
            fMFormulaFrabicar.setFarmaceutico(rs.getString("farmaceutico"));
            fMFormulaFrabicar.setFormulas(rs.getBigDecimal("formulas"));
            fMFormulaFrabicar.setNdescripcion(rs.getString("nprescripcion"));
            fMFormulaFrabicar.setObservaciones(rs.getString("observaciones"));
            fMFormulaFrabicar.setUni_dispen(rs.getInt("uni_dispen"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return fMFormulaFrabicar;
    }

    public FMFormulaFrabicar getPorCodigo(Integer numero) {
        Connection connection = null;
        FMFormulaFrabicar fMFormulaFrabicar = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas_fab WHERE numero=" + numero;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormulaFrabicar = getRegistroResulset(resulSet, null);
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
        return fMFormulaFrabicar;
    }

    public boolean doGrabaDatos(FMFormulaFrabicar fMFormulaFrabicar) {
        boolean actualizado = false;

        if (this.getPorCodigo(fMFormulaFrabicar.getNumero()) == null) {
            actualizado = this.doInsertaDatos(fMFormulaFrabicar);
        } else {
            actualizado = this.doActualizaDatos(fMFormulaFrabicar);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMFormulaFrabicar fMFormulaFrabicar) {
        Connection connection = null;
        Boolean insertadoBoolean = false;

        try {

            connection = super.getConexionBBDD();
            sql = sql = "INSERT INTO     farm_fm_formulas_fab "
                    + "( numero, fecha,formula,paciente,medico,caducidad,registro,lote "
                    + "     ,ccalidad,unidades,preparado,farmaceutico,formulas,nprescripcion,observaciones,uni_dispen)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
//numero, fecha,formula,paciente,medico,caducidad,registro,lote "
            if (fMFormulaFrabicar.getNumero() == null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, fMFormulaFrabicar.getNumero());
            }
            if (fMFormulaFrabicar.getFecha() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, Integer.parseInt(dateTimeFormatterParser.format(fMFormulaFrabicar.getFecha())));
            }
            if (fMFormulaFrabicar.getFormula().getNumero() == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, fMFormulaFrabicar.getFormula().getNumero());
            }
            if (fMFormulaFrabicar.getPaciente() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, fMFormulaFrabicar.getPaciente());
            }
            if (fMFormulaFrabicar.getMedico() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, fMFormulaFrabicar.getMedico());
            }
            if (fMFormulaFrabicar.getCaducidad() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, fMFormulaFrabicar.getCaducidad());
            }
            if (fMFormulaFrabicar.getRegistro() == null) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, fMFormulaFrabicar.getRegistro());
            }
            if (fMFormulaFrabicar.getLote() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, fMFormulaFrabicar.getLote());
            }
            if (fMFormulaFrabicar.getCcalidad() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, fMFormulaFrabicar.getCcalidad());
            }
            if (fMFormulaFrabicar.getUnidades() == null) {
                statement.setNull(10, Types.INTEGER);
            } else {
                statement.setInt(10, fMFormulaFrabicar.getUnidades());
            }
            if (fMFormulaFrabicar.getPreparado() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, fMFormulaFrabicar.getPreparado());
            }
            if (fMFormulaFrabicar.getFarmaceutico() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setString(12, fMFormulaFrabicar.getFarmaceutico());
            }
            if (fMFormulaFrabicar.getFormulas() == null) {
                statement.setNull(13, Types.FLOAT);
            } else {
                statement.setBigDecimal(13, fMFormulaFrabicar.getFormulas());
            }
            if (fMFormulaFrabicar.getNdescripcion() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, fMFormulaFrabicar.getNdescripcion());
            }
            if (fMFormulaFrabicar.getObservaciones() == null) {
                statement.setNull(15, Types.VARCHAR);
            } else {
                statement.setString(15, fMFormulaFrabicar.getObservaciones());
            }
            if (fMFormulaFrabicar.getUni_dispen() == null) {
                statement.setNull(16, Types.INTEGER);
            } else {
                statement.setInt(16, fMFormulaFrabicar.getUni_dispen());
            }

            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error("Error  no bbdd", e.getStackTrace());
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public boolean doActualizaDatos(FMFormulaFrabicar fMFormulaFrabicar) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {

            connection = super.getConexionBBDD();
            sql = sql = "UPDATE      farm_fm_formulas_fab  SET"
                    + " fecha=?,formula=?,paciente=?,medico=?,caducidad=?,registro=?,lote =?"
                    + "     ,ccalidad=?,unidades=?,preparado=?,farmaceutico=?,formulas=?,nprescripcion=?,observaciones=?,uni_dispen=?"
                    + " WHERE numero=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMFormulaFrabicar.getFecha() == null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setString(1, dateTimeFormatterParser.format(fMFormulaFrabicar.getFecha()));
            }
            if (fMFormulaFrabicar.getFormula().getNumero() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, fMFormulaFrabicar.getFormula().getNumero());
            }
            if (fMFormulaFrabicar.getPaciente() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, fMFormulaFrabicar.getPaciente());
            }

            if (fMFormulaFrabicar.getMedico() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, fMFormulaFrabicar.getMedico());
            }
            if (fMFormulaFrabicar.getCaducidad() == null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, fMFormulaFrabicar.getCaducidad());
            }
            if (fMFormulaFrabicar.getRegistro() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, fMFormulaFrabicar.getRegistro());
            }
            if (fMFormulaFrabicar.getLote() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, fMFormulaFrabicar.getLote());
            }
            if (fMFormulaFrabicar.getCcalidad() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, fMFormulaFrabicar.getCcalidad());
            }
            if (fMFormulaFrabicar.getUnidades() == null) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, fMFormulaFrabicar.getUnidades());
            }
            if (fMFormulaFrabicar.getPreparado() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, fMFormulaFrabicar.getPreparado());
            }
            if (fMFormulaFrabicar.getFarmaceutico() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, fMFormulaFrabicar.getFarmaceutico());
            }
            if (fMFormulaFrabicar.getFormulas() == null) {
                statement.setNull(12, Types.FLOAT);
            } else {
                statement.setBigDecimal(12, fMFormulaFrabicar.getFormulas());
            }
            if (fMFormulaFrabicar.getNdescripcion() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, fMFormulaFrabicar.getNdescripcion());
            }
            if (fMFormulaFrabicar.getObservaciones() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, fMFormulaFrabicar.getObservaciones());
            }
            if (fMFormulaFrabicar.getUni_dispen() == null) {
                statement.setNull(15, Types.INTEGER);
            } else {
                statement.setInt(15, fMFormulaFrabicar.getUni_dispen());
            }
            if (fMFormulaFrabicar.getNumero() == null) {
                statement.setNull(16, Types.INTEGER);
            } else {
                statement.setInt(16, fMFormulaFrabicar.getNumero());
            }
            LOGGER.debug(sql);
            System.out.println(statement.executeUpdate());
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
            LOGGER.error(e.getStackTrace());
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public boolean doBorraDatos(FMFormulaFrabicar fMFormulaFrabicar) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_formulas_fab WHERE numero=" + fMFormulaFrabicar.getNumero();

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

    public ArrayList<FMFormulaFrabicar> getListaFMFormulaFrabicar(FMFormula fMFormula) {
        Connection connection = null;
        ArrayList<FMFormulaFrabicar> lista = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            if (fMFormula != null) {
                sql = " SELECT * FROM farm_fm_formulas_fab WHERE formula=" + fMFormula.getNumero() + " "
                        + " ORDER BY numero";
            } else {
                sql = " SELECT * FROM farm_fm_formulas_fab  ORDER BY formula, numero";
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                lista.add(getRegistroResulset(resulSet, fMFormula));

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
        return lista;
    }

    public Integer getSiguienteNumero(FMFormula formula) {
        Connection connection = null;
        Integer numero = 1;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT max(numero) as numero  FROM farm_fm_formulas_fab  WHERE formula= " + formula.getNumero();
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                numero = resulSet.getInt("numero") + 1;
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
        return numero;
    }
}
