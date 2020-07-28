/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMMPrimas;
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
public class FMMprimasDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FMMprimasDAO.class);
    private static final long serialVersionUID = 1L;

    public FMMprimasDAO() {
        super();
    }

    private FMMPrimas getRegistroResulset(ResultSet rs) {
        FMMPrimas fMMPrimas = new FMMPrimas();
        getgetRegistroResulsetObj(rs, fMMPrimas);
        return fMMPrimas;

    }

    /**
     * param rs el recorset de materias primas param fMMPrimas pasdo por
     * referencia para actualizar objeto. Este parámetro permite hacer set en
     * objetos tipo FMMprimas FMMprimasEntradas FMMprimasEntradasSalidas
     */
    private void getgetRegistroResulsetObj(ResultSet rs, FMMPrimas fMMPrimas) {
        try {
            DateTimeFormatter dateTimeFormatterParser = DateTimeFormatter.ofPattern("yyyyMMdd");

            LocalDate localDate = null;

            if (rs.getInt("ulti_revi") != 0) {
                String ulti = Integer.toString(rs.getInt("ulti_revi"));
                localDate = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            fMMPrimas.setCod_inte(rs.getInt("cod_inte"));
            fMMPrimas.setProducto(rs.getString("producto"));
            fMMPrimas.setCod_labo(rs.getString("cod_labo"));
            fMMPrimas.setLaboratorio(rs.getString("laboratorio"));
            fMMPrimas.setHomologado(rs.getBoolean("homologado"));
            fMMPrimas.setN_labo(rs.getInt("n_labo"));
            fMMPrimas.setStock_min(rs.getInt("stock_min"));
            fMMPrimas.setObservaciones(rs.getString("observaciones"));
            fMMPrimas.setEspecifica(rs.getString("especifica"));
            fMMPrimas.setUlti_revi(localDate);
            fMMPrimas.setFarmacetuico(rs.getString("farmaceutico"));
            fMMPrimas.setExistencias(rs.getInt("existencias"));
            fMMPrimas.setNlaboratorio(rs.getString("nlaboratorio"));
            fMMPrimas.setPresentacion(rs.getString("presentacion"));
            fMMPrimas.setDescripcion(rs.getString("descripcion"));
            fMMPrimas.setRequisitos(rs.getString("requisitos"));
            fMMPrimas.setConservacion(rs.getString("conservacion"));
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    public FMMPrimas getPorCodigo(Integer cod_inte) {
        Connection connection = null;
        FMMPrimas fMMPrimas = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_mprimas WHERE   cod_inte=" + cod_inte;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMMPrimas = getRegistroResulset(resulSet);
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
        return fMMPrimas;
    }

    public void getPorCodigo(Integer cod_inte, FMMPrimas fMMPrimas) {
        Connection connection = null;
        //   FMMPrimas fMMPrimas = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_mprimas WHERE   cod_inte=" + cod_inte;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                getgetRegistroResulsetObj(resulSet, fMMPrimas);
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
    }

    public boolean doGrabaDatos(FMMPrimas fMMPrimas) {
        boolean actualizado = false;
        if (this.getPorCodigo(fMMPrimas.getCod_inte()) == null) {
            actualizado = this.doInsertaDatos(fMMPrimas);
        } else {
            actualizado = this.doActualizaDatos(fMMPrimas);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMMPrimas fMMPrimas) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            /*
          String  sqlLog = "INSERT INTO  farm_fm_mprimas (producto,cod_labo,laboratorio,homologado,stock_min,observaciones,"
                    + " ulti_revi,farmaceutico,existencias,nlaboratorio,presentacion,descripcion,requisitos,conservacion) "
                    + "values('" + fMMPrimas.getProducto().toString() + "','" + fMMPrimas.getCod_labo().toString() + "','" + fMMPrimas.getLaboratorio() + "','" + fMMPrimas.getHomologado() + "'," + fMMPrimas.getStock_min() + ",'" + fMMPrimas.getObservaciones() + "', "
                    + fMMPrimas.getUlti_revi().format(DateTimeFormatter.ofPattern("yyyMMdd")) + ",'" + fMMPrimas.getFarmacetuico() + "'," + fMMPrimas.getEspecifica() + ",'" + fMMPrimas.getNlaboratorio() + "','" + fMMPrimas.getPresentacion() + "','" + fMMPrimas.getDescripcion() + "', "
                    + "'" + fMMPrimas.getRequisitcos() + "','" + fMMPrimas.getConservacion() + "')";

          LOGGER.debug(sqlLog);
             */
            sql = "INSERT INTO  farm_fm_mprimas (cod_inte,producto,cod_labo,laboratorio,homologado,stock_min,observaciones,"
                    + " ulti_revi,farmaceutico,existencias,nlaboratorio,presentacion,descripcion,requisitos,conservacion) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, fMMPrimas.getCod_inte());

            statement.setString(2, fMMPrimas.getProducto());

            if (fMMPrimas.getCod_labo() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, fMMPrimas.getCod_labo());
            }
            if (fMMPrimas.getLaboratorio() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, fMMPrimas.getLaboratorio());
            }
            if (fMMPrimas.getHomologado() == null) {
                statement.setNull(5, Types.BOOLEAN);
            } else {
                statement.setBoolean(5, fMMPrimas.getHomologado());
            }
            if (fMMPrimas.getStock_min() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, fMMPrimas.getStock_min());
            }
            if (fMMPrimas.getObservaciones() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, fMMPrimas.getObservaciones());
            }
            if (fMMPrimas.getUlti_revi() == null) {
                statement.setNull(8, Types.INTEGER);
            } else {
                statement.setInt(8, Integer.parseInt(fMMPrimas.getUlti_revi().format(DateTimeFormatter.ofPattern("yyyMMdd"))));
            }
            if (fMMPrimas.getFarmacetuico() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, fMMPrimas.getFarmacetuico());
            }
            if (fMMPrimas.getExistencias() == null) {
                statement.setNull(10, Types.INTEGER);
            } else {
                statement.setInt(10, fMMPrimas.getExistencias());
            }
            if (fMMPrimas.getNlaboratorio() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, fMMPrimas.getNlaboratorio());
            }
            if (fMMPrimas.getPresentacion() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setString(12, fMMPrimas.getPresentacion());
            }
            if (fMMPrimas.getDescripcion() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, fMMPrimas.getDescripcion());
            }
            if (fMMPrimas.getRequisitos() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, fMMPrimas.getRequisitos());
            }
            if (fMMPrimas.getConservacion() == null) {
                statement.setNull(15, Types.VARCHAR);
            } else {
                statement.setString(15, fMMPrimas.getConservacion());
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

    public boolean doActualizaDatos(FMMPrimas fMMPrimas) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            /*
            sql = sql = "UPDATE   farm_fm_mprimas  SET producto='" + fMMPrimas.getProducto() + "',cod_labo='" + fMMPrimas.getCod_labo() + "'"
                    + ",laboratorio='" + fMMPrimas.getLaboratorio() + "',homologado='" + fMMPrimas.getHomologado() + "',stock_min='" + fMMPrimas.getStock_min() + "'"
                    + ",observaciones='" + fMMPrimas.getObservaciones() + "', ulti_revi='" + fMMPrimas.getUlti_revi()
                    + "',farmaceutico='" + fMMPrimas.getFarmacetuico() + "  '"
                    + ",existencias=" + fMMPrimas.getExistencias() + ",nlaboratorio='" + fMMPrimas.getNlaboratorio() + "',presentacion='" + fMMPrimas.getPresentacion() + "'"
                    + ",descripcion='" + fMMPrimas.getDescripcion() + "',requisitos='" + fMMPrimas.getRequisitos() + "',conservacion='" + fMMPrimas.getConservacion() + "'"
                    + "WHERE cod_inte=" + fMMPrimas.getCod_inte();
             */
            sql = sql = "UPDATE   farm_fm_mprimas  SET producto=?,cod_labo=? ,laboratorio=? ,homologado=?,stock_min=?"
                    + " ,observaciones=? , ulti_revi=?,farmaceutico=?,existencias=?,nlaboratorio=?,presentacion=?"
                    + ",descripcion=?,requisitos=?,conservacion=?"
                    + " WHERE cod_inte=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, fMMPrimas.getProducto());

            if (fMMPrimas.getCod_labo() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, fMMPrimas.getCod_labo());
            }
            if (fMMPrimas.getLaboratorio() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, fMMPrimas.getLaboratorio());
            }
            if (fMMPrimas.getHomologado() == null) {
                statement.setNull(4, Types.BOOLEAN);
            } else {
                statement.setBoolean(4, fMMPrimas.getHomologado());
            }
            if (fMMPrimas.getStock_min() == null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, fMMPrimas.getStock_min());
            }
            if (fMMPrimas.getObservaciones() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, fMMPrimas.getObservaciones());
            }
            if (fMMPrimas.getUlti_revi() == null) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, Integer.parseInt(fMMPrimas.getUlti_revi().format(DateTimeFormatter.ofPattern("yyyMMdd"))));
            }
            if (fMMPrimas.getFarmacetuico() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, fMMPrimas.getFarmacetuico());
            }
            if (fMMPrimas.getExistencias() == null) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, fMMPrimas.getExistencias());
            }
            if (fMMPrimas.getNlaboratorio() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, fMMPrimas.getNlaboratorio());
            }
            if (fMMPrimas.getPresentacion() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, fMMPrimas.getPresentacion());
            }
            if (fMMPrimas.getDescripcion() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setString(12, fMMPrimas.getDescripcion());
            }
            if (fMMPrimas.getRequisitos() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, fMMPrimas.getRequisitos());
            }
            if (fMMPrimas.getConservacion() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, fMMPrimas.getConservacion());
            }
            statement.setLong(15, fMMPrimas.getCod_inte());
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

    public boolean doActualizaExistencias(FMMPrimas fMMPrimas, Integer valorEntraSale) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = "UPDATE   farm_fm_mprimas  SET existencias=?  WHERE cod_inte=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMMPrimas.getExistencias() != null) {
                fMMPrimas.setExistencias(fMMPrimas.getExistencias() + valorEntraSale);
            } else {
                fMMPrimas.setExistencias(valorEntraSale);
            }

            if (fMMPrimas.getExistencias() == null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, fMMPrimas.getExistencias());
            }
            statement.setLong(2, fMMPrimas.getCod_inte());
            LOGGER.debug(sql);
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

    public boolean doBorraDatos(FMMPrimas fMMPrimas) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_mprimas WHERE cod_inte=" + fMMPrimas.getCod_inte();
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

    public ArrayList<FMMPrimas> getListaMprimas(String texto) {
        Connection connection = null;
        ArrayList<FMMPrimas> listaMprimas = new ArrayList<>();
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
