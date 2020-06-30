/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            LocalDate localDate = LocalDate.parse(rs.getString("ulti_revi"), dateTimeFormatterParser);

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
            sql = "INSERT INTO  farm_fm_mprimas (producto,cod_labo,laboratorio,homologado,stock_min,observaciones,"
                    + " ulti_revi,farmaceutico,existencias,nlaboratorio,presentacion,descripcion,requisitos,conservacion) "
                    + "values('" + farmaFMMPrimas.getProducto() + "','" + farmaFMMPrimas.getCod_labo() + "','" + farmaFMMPrimas.getLaboratorio() + "','" + farmaFMMPrimas.getHomologado() + "'," + farmaFMMPrimas.getStock_min() + ",'" + farmaFMMPrimas.getObservaciones() + "', "
                    + farmaFMMPrimas.getUlti_revi().format(DateTimeFormatter.ofPattern("yyyMMdd")) + ",'" + farmaFMMPrimas.getFarmacetuico() + "'," + farmaFMMPrimas.getEspecifica() + ",'" + farmaFMMPrimas.getNlaboratorio() + "','" + farmaFMMPrimas.getPresentacion() + "','" + farmaFMMPrimas.getDescripcion() + "', "
                    + "'" + farmaFMMPrimas.getRequisitcos() + "','" + farmaFMMPrimas.getConservacion() + "')";
            LOGGER.debug(sql);
            Statement statement = connection.createStatement();
            insertadoBoolean = statement.execute(sql);
            insertadoBoolean = true;
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
            sql = sql.concat("ORDER BY producto");
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
