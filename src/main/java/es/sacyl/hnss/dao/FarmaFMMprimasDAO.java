/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FarmaFMMPrimas;
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
public class FarmaFMMprimasDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FarmaFMMprimasDAO.class);

    public FarmaFMMprimasDAO() {
        super();
    }

    private FarmaFMMPrimas getRegistroResulset(ResultSet rs) {
        FarmaFMMPrimas farmaFMMPrimas = new FarmaFMMPrimas();
        try {
            DateTimeFormatter dateTimeFormatterParser = DateTimeFormatter.ofPattern("yyyyMMdd");

            LocalDate localDate = null;

            if (rs.getString("ulti_revi") != null) {
                LocalDate.parse(rs.getString("ulti_revi"), dateTimeFormatterParser);
            }

            farmaFMMPrimas.setCod_inte(rs.getInt("codigo"));
            farmaFMMPrimas.setProducto(rs.getString("producto"));
            farmaFMMPrimas.setCod_labo(rs.getString("cod_labo"));
            farmaFMMPrimas.setLaboratorio(rs.getString("laboratorio"));
            farmaFMMPrimas.setHomologado(rs.getString("homologado"));
            farmaFMMPrimas.setN_labo(rs.getInt("n_labo"));
            farmaFMMPrimas.setStock_min(rs.getInt("stock_min"));
            farmaFMMPrimas.setObservaciones(rs.getString("observaciones"));
            farmaFMMPrimas.setEspecifica(rs.getString("especifica"));
            farmaFMMPrimas.setUlti_revi(localDate);
            farmaFMMPrimas.setExistencias(rs.getInt("existencias"));
            farmaFMMPrimas.setNlaboratorio(rs.getString("nlaboratorio"));
            farmaFMMPrimas.setPresentacion(rs.getString("presentacion"));
            farmaFMMPrimas.setDescripcion(rs.getString("descripcion"));
            farmaFMMPrimas.setRequisitcos(rs.getString("requisitcos"));
            farmaFMMPrimas.setConservacion(rs.getString("conservacion"));
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return farmaFMMPrimas;
    }

    public FarmaFMMPrimas getPorCodigo(Integer codigo) {
        Connection connection = null;
        FarmaFMMPrimas farmaFMMPrimas = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_mprimas WHERE   cod_inte=" + codigo;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMMPrimas = getRegistroResulset(resulSet);
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
        return farmaFMMPrimas;
    }

    public boolean doGrabaDatos(FarmaFMMPrimas farmaFMMPrimas) {
        boolean actualizado = false;
        if (this.getPorCodigo(farmaFMMPrimas.getCod_inte()) == null) {
            actualizado = this.doInsertaDatos(farmaFMMPrimas);
        } else {
            actualizado = this.doActualizaDatos(farmaFMMPrimas);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FarmaFMMPrimas farmaFMMPrimas) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            /*
          String  sqlLog = "INSERT INTO  farm_fm_mprimas (producto,cod_labo,laboratorio,homologado,stock_min,observaciones,"
                    + " ulti_revi,farmaceutico,existencias,nlaboratorio,presentacion,descripcion,requisitos,conservacion) "
                    + "values('" + farmaFMMPrimas.getProducto().toString() + "','" + farmaFMMPrimas.getCod_labo().toString() + "','" + farmaFMMPrimas.getLaboratorio() + "','" + farmaFMMPrimas.getHomologado() + "'," + farmaFMMPrimas.getStock_min() + ",'" + farmaFMMPrimas.getObservaciones() + "', "
                    + farmaFMMPrimas.getUlti_revi().format(DateTimeFormatter.ofPattern("yyyMMdd")) + ",'" + farmaFMMPrimas.getFarmacetuico() + "'," + farmaFMMPrimas.getEspecifica() + ",'" + farmaFMMPrimas.getNlaboratorio() + "','" + farmaFMMPrimas.getPresentacion() + "','" + farmaFMMPrimas.getDescripcion() + "', "
                    + "'" + farmaFMMPrimas.getRequisitcos() + "','" + farmaFMMPrimas.getConservacion() + "')";

          LOGGER.debug(sqlLog);
             */
            sql = "INSERT INTO  farm_fm_mprimas (cod_inte,producto,cod_labo,laboratorio,homologado,stock_min,observaciones,"
                    + " ulti_revi,farmaceutico,existencias,nlaboratorio,presentacion,descripcion,requisitos,conservacion) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, farmaFMMPrimas.getCod_inte());
            statement.setString(2, farmaFMMPrimas.getProducto());
            if (farmaFMMPrimas.getCod_labo() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, farmaFMMPrimas.getCod_labo());
            }
            if (farmaFMMPrimas.getLaboratorio() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, farmaFMMPrimas.getLaboratorio());
            }
            if (farmaFMMPrimas.getHomologado() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, farmaFMMPrimas.getHomologado());
            }
            if (farmaFMMPrimas.getStock_min() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, farmaFMMPrimas.getStock_min());
            }
            if (farmaFMMPrimas.getObservaciones() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, farmaFMMPrimas.getObservaciones());
            }
            if (farmaFMMPrimas.getUlti_revi() == null) {
                statement.setNull(8, Types.INTEGER);
            } else {
                statement.setInt(8, Integer.parseInt(farmaFMMPrimas.getUlti_revi().format(DateTimeFormatter.ofPattern("yyyMMdd"))));
            }
            if (farmaFMMPrimas.getFarmacetuico() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, farmaFMMPrimas.getFarmacetuico());
            }
            if (farmaFMMPrimas.getExistencias() == null) {
                statement.setNull(10, Types.INTEGER);
            } else {
                statement.setInt(10, farmaFMMPrimas.getExistencias());
            }
            if (farmaFMMPrimas.getNlaboratorio() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, farmaFMMPrimas.getNlaboratorio());
            }
            if (farmaFMMPrimas.getPresentacion() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setString(12, farmaFMMPrimas.getPresentacion());
            }
            if (farmaFMMPrimas.getDescripcion() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, farmaFMMPrimas.getDescripcion());
            }
            if (farmaFMMPrimas.getRequisitcos() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, farmaFMMPrimas.getRequisitcos());
            }
            if (farmaFMMPrimas.getRequisitcos() == null) {
                statement.setNull(15, Types.VARCHAR);
            } else {
                statement.setString(15, farmaFMMPrimas.getConservacion());
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

    public boolean doActualizaDatos(FarmaFMMPrimas farmaFMMPrimas) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "UPDATE   farm_fm_mprimas  SET producto='" + farmaFMMPrimas.getProducto() + "',cod_labo='" + farmaFMMPrimas.getCod_labo() + "'"
                    + ",laboratorio='" + farmaFMMPrimas.getLaboratorio() + "',homologado='" + farmaFMMPrimas.getHomologado() + "',stock_min='" + farmaFMMPrimas.getStock_min() + "'"
                    + ",observaciones='" + farmaFMMPrimas.getObservaciones() + "', ulti_revi='" + farmaFMMPrimas.getUlti_revi()
                    + "',farmaceutico='" + farmaFMMPrimas.getFarmacetuico() + "  '"
                    + ",existencias=" + farmaFMMPrimas.getExistencias() + ",nlaboratorio='" + farmaFMMPrimas.getNlaboratorio() + "',presentacion='" + farmaFMMPrimas.getPresentacion() + "'"
                    + ",descripcion='" + farmaFMMPrimas.getDescripcion() + "',requisitos='" + farmaFMMPrimas.getRequisitcos() + "',conservacion='" + farmaFMMPrimas.getConservacion() + "'"
                    + "WHERE cod_inte=" + farmaFMMPrimas.getCod_inte();

            sql = sql = "UPDATE   farm_fm_mprimas  SET producto=?,cod_labo=? ,laboratorio=? ,homologado=?,stock_min=?,"
                    + " ,observaciones=? , ulti_revi=?,farmaceutico='?,existencias=?,nlaboratorio=?,presentacion=?,descripcion=?,requisitos=?,conservacion=?"
                    + "WHERE cod_inte=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, farmaFMMPrimas.getProducto());
            if (farmaFMMPrimas.getCod_labo() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, farmaFMMPrimas.getCod_labo());
            }
            if (farmaFMMPrimas.getLaboratorio() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, farmaFMMPrimas.getLaboratorio());
            }
            if (farmaFMMPrimas.getHomologado() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, farmaFMMPrimas.getHomologado());
            }
            if (farmaFMMPrimas.getStock_min() == null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, farmaFMMPrimas.getStock_min());
            }
            if (farmaFMMPrimas.getObservaciones() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, farmaFMMPrimas.getObservaciones());
            }
            if (farmaFMMPrimas.getUlti_revi() == null) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, Integer.parseInt(farmaFMMPrimas.getUlti_revi().format(DateTimeFormatter.ofPattern("yyyMMdd"))));
            }
            if (farmaFMMPrimas.getFarmacetuico() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, farmaFMMPrimas.getFarmacetuico());
            }
            if (farmaFMMPrimas.getExistencias() == null) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, farmaFMMPrimas.getExistencias());
            }
            if (farmaFMMPrimas.getNlaboratorio() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, farmaFMMPrimas.getNlaboratorio());
            }
            if (farmaFMMPrimas.getPresentacion() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, farmaFMMPrimas.getPresentacion());
            }
            if (farmaFMMPrimas.getDescripcion() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setString(12, farmaFMMPrimas.getDescripcion());
            }
            if (farmaFMMPrimas.getRequisitcos() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, farmaFMMPrimas.getRequisitcos());
            }
            if (farmaFMMPrimas.getRequisitcos() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, farmaFMMPrimas.getConservacion());
            }
            statement.setLong(15, farmaFMMPrimas.getCod_inte());
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

    public boolean doBorraDatos(FarmaFMMPrimas farmaFMMPrimas) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_mprimas WHERE cod_inte=" + farmaFMMPrimas.getCod_inte();
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

    public ArrayList<FarmaFMMPrimas> getListaMprimas(String texto) {
        Connection connection = null;
        ArrayList<FarmaFMMPrimas> listaMprimas = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_mprimas WHERE  1=1 ";
            if (texto != null && !texto.isEmpty()) {
                sql = sql.concat(" AND (producto like'%" + texto + "%'  OR presentacion like'%" + texto + "%')");
            }
            sql = sql.concat(" ORDER BY producto");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaMprimas.add(getRegistroResulset(resulSet));
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
        return listaMprimas;
    }
}
