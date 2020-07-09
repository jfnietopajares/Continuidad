/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
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
public class FarmaFMMprimasEntraDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FarmaFMMprimasEntraDAO.class);

    DateTimeFormatter dateTimeFormatterParser = DateTimeFormatter.ofPattern("yyyyMMdd");

    public FarmaFMMprimasEntraDAO() {
        super();
    }

    private FarmaFMMPrimasEntrada getRegistroResulset(ResultSet rs) {
        FarmaFMMPrimasEntrada farmaFMMPrimasEntrada = new FarmaFMMPrimasEntrada();
        try {

            // esto es una chapu esta parte es el método getRegistroResulset de
            LocalDate localDateUlti = null;
            if (rs.getInt("ulti_revi") != 0) {
                String ulti = Integer.toString(rs.getInt("ulti_revi"));
                localDateUlti = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            farmaFMMPrimasEntrada.setCod_inte(rs.getInt("cod_inte"));
            farmaFMMPrimasEntrada.setProducto(rs.getString("producto"));
            farmaFMMPrimasEntrada.setCod_labo(rs.getString("cod_labo"));
            farmaFMMPrimasEntrada.setLaboratorio(rs.getString("laboratorio"));
            farmaFMMPrimasEntrada.setHomologado(rs.getBoolean("homologado"));
            farmaFMMPrimasEntrada.setN_labo(rs.getInt("n_labo"));
            farmaFMMPrimasEntrada.setStock_min(rs.getInt("stock_min"));
            farmaFMMPrimasEntrada.setObservaciones(rs.getString("observaciones"));
            farmaFMMPrimasEntrada.setEspecifica(rs.getString("especifica"));
            farmaFMMPrimasEntrada.setUlti_revi(localDateUlti);
            farmaFMMPrimasEntrada.setFarmacetuico(rs.getString("farmaceutico"));
            farmaFMMPrimasEntrada.setExistencias(rs.getInt("existencias"));
            farmaFMMPrimasEntrada.setNlaboratorio(rs.getString("nlaboratorio"));
            farmaFMMPrimasEntrada.setPresentacion(rs.getString("presentacion"));
            farmaFMMPrimasEntrada.setDescripcion(rs.getString("descripcion"));
            farmaFMMPrimasEntrada.setRequisitos(rs.getString("requisitos"));
            farmaFMMPrimasEntrada.setConservacion(rs.getString("conservacion"));
// fin de la chapu
            LocalDate localDate = null;
            if (rs.getInt("fecha") != 0) {
                String ulti = Integer.toString(rs.getInt("fecha"));
                localDate = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            farmaFMMPrimasEntrada.setFecha(localDate);
            farmaFMMPrimasEntrada.setRegistro(rs.getInt("registro"));
            farmaFMMPrimasEntrada.setEnvases(rs.getInt("envases"));
            farmaFMMPrimasEntrada.setLote(rs.getString("lote"));
            farmaFMMPrimasEntrada.setVerificacion(rs.getBoolean("verificacion"));
            farmaFMMPrimasEntrada.setCtrlAnalitico(rs.getString("ctrl_analitico"));
            farmaFMMPrimasEntrada.setCaducidad(rs.getString("caducidad"));
            farmaFMMPrimasEntrada.setNumero(rs.getInt("numero"));

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return farmaFMMPrimasEntrada;
    }

    public FarmaFMMPrimasEntrada getPorMPNumero(Integer cod_inte, Integer numero) {
        Connection connection = null;
        FarmaFMMPrimasEntrada farmaFMMPrimasEntrada = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_primas_ent,  farm_fm_mprimas  "
                    + " WHERE  farm_fm_primas_ent.cod_inte= farm_fm_mprimas.cod_inte "
                    + " AND farm_fm_primas_ent.numero='" + numero + "' "
                    + " AND farm_fm_primas_ent.cod_inte=" + cod_inte;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMMPrimasEntrada = getRegistroResulset(resulSet);
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
        return farmaFMMPrimasEntrada;

    }

    public boolean doGrabaDatos(FarmaFMMPrimasEntrada farmaFMMPrimasEntrada) {
        boolean actualizado = false;

        if (this.getPorMPNumero(farmaFMMPrimasEntrada.getCod_inte(), farmaFMMPrimasEntrada.getNumero()) == null) {
            actualizado = this.doInsertaDatos(farmaFMMPrimasEntrada);
        } else {
            actualizado = this.doActualizaDatos(farmaFMMPrimasEntrada);
        }
        return actualizado;
    }

    public boolean doActualizaDatos(FarmaFMMPrimasEntrada farmaFMMPrimasEntrada) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "UPDATE   farm_fm_primas_ent  SET fecha=?,registro=? ,envases=? ,lote=?,verificacion=?"
                    + " ,ctrl_analitico=? , farmaceutico=? "
                    + ",caducidad=? "
                    + "  WHERE cod_inte=? AND numero=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            if (farmaFMMPrimasEntrada.getFecha() == null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setString(1, dateTimeFormatterParser.format(farmaFMMPrimasEntrada.getFecha()));
            }
            if (farmaFMMPrimasEntrada.getRegistro() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, farmaFMMPrimasEntrada.getRegistro());
            }
            if (farmaFMMPrimasEntrada.getEnvases() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setInt(3, farmaFMMPrimasEntrada.getEnvases());
            }
            if (farmaFMMPrimasEntrada.getLote() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, farmaFMMPrimasEntrada.getLote());
            }
            if (farmaFMMPrimasEntrada.getVerificacion() == null) {
                statement.setNull(5, Types.BOOLEAN);
            } else {
                statement.setBoolean(5, farmaFMMPrimasEntrada.getVerificacion());
            }
            if (farmaFMMPrimasEntrada.getCtrlAnalitico() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, farmaFMMPrimasEntrada.getCtrlAnalitico());
            }
            if (farmaFMMPrimasEntrada.getFarmaceutico() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, farmaFMMPrimasEntrada.getFarmaceutico());
            }
            if (farmaFMMPrimasEntrada.getCaducidad() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, farmaFMMPrimasEntrada.getCaducidad());
            }
            if (farmaFMMPrimasEntrada == null && farmaFMMPrimasEntrada.getCod_inte() != null) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, farmaFMMPrimasEntrada.getCod_inte());
            }
            if (farmaFMMPrimasEntrada.getNumero() == null) {
                statement.setNull(10, Types.INTEGER);
            } else {
                statement.setInt(10, farmaFMMPrimasEntrada.getNumero());
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

    public boolean doInsertaDatos(FarmaFMMPrimasEntrada farmaFMMPrimasEntrada) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "INSERT INTO    farm_fm_primas_ent  (cod_inte,numero, fecha,registro ,envases ,lote,verificacion"
                    + " ,ctrl_analitico, farmaceutico,caducidad) "
                    + " VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            if (farmaFMMPrimasEntrada == null && farmaFMMPrimasEntrada.getCod_inte() != null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, farmaFMMPrimasEntrada.getCod_inte());
            }
            if (farmaFMMPrimasEntrada.getNumero() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, farmaFMMPrimasEntrada.getNumero());
            }
            if (farmaFMMPrimasEntrada.getFecha() == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setString(3, dateTimeFormatterParser.format(farmaFMMPrimasEntrada.getFecha()));
            }
            if (farmaFMMPrimasEntrada.getRegistro() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, farmaFMMPrimasEntrada.getRegistro());
            }
            if (farmaFMMPrimasEntrada.getEnvases() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setInt(5, farmaFMMPrimasEntrada.getEnvases());
            }
            if (farmaFMMPrimasEntrada.getLote() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, farmaFMMPrimasEntrada.getLote());
            }
            if (farmaFMMPrimasEntrada.getVerificacion() == null) {
                statement.setNull(7, Types.BOOLEAN);
            } else {
                statement.setBoolean(7, farmaFMMPrimasEntrada.getVerificacion());
            }

            if (farmaFMMPrimasEntrada.getCtrlAnalitico() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, farmaFMMPrimasEntrada.getCtrlAnalitico());
            }
            if (farmaFMMPrimasEntrada.getFarmaceutico() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, farmaFMMPrimasEntrada.getFarmaceutico());
            }
            if (farmaFMMPrimasEntrada.getCaducidad() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, farmaFMMPrimasEntrada.getCaducidad());
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

    public boolean doBorraDatos(FarmaFMMPrimasEntrada farmaFMMPrimasEntrada) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_primas_ent WHERE cod_inte='" + farmaFMMPrimasEntrada.getCod_inte() + "' "
                    + " AND numero=" + farmaFMMPrimasEntrada.getNumero();
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

    public ArrayList<FarmaFMMPrimasEntrada> getListaEntradasMP(LocalDate desde, LocalDate hasta, FarmaFMMPrimas farmaFMMPrimas) {
        Connection connection = null;
        ArrayList<FarmaFMMPrimasEntrada> listaEntradas = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_primas_ent, farm_fm_mprimas WHERE  "
                    + " farm_fm_primas_ent.cod_inte= farm_fm_mprimas.cod_inte ";
            if (desde != null) {
                sql = sql.concat(" AND fecha>=" + dateTimeFormatterParser.format(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND hasta>=" + dateTimeFormatterParser.format(hasta));
            }
            if (farmaFMMPrimas != null && farmaFMMPrimas.getCod_inte() != null) {
                sql = sql.concat(" AND farm_fm_primas_ent.cod_inte=" + farmaFMMPrimas.getCod_inte());
            }
            sql = sql.concat(" ORDER BY farm_fm_primas_ent.cod_inte,fecha");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaEntradas.add(getRegistroResulset(resulSet));
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
        return listaEntradas;
    }

    public Integer getNumeroSiguiente(FarmaFMMPrimasEntrada farmaFMMPrimasEntrada) {
        Connection connection = null;
        Integer siguiente = 1;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT max(numero) as valor FROM farm_fm_primas_ent WHERE    cod_inte=" + farmaFMMPrimasEntrada.getCod_inte();
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                siguiente = resulSet.getInt("valor") + 1;
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
        return siguiente;

    }
}
