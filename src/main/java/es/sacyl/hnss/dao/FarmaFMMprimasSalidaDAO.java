/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.entidades.FarmaFMMprimasSalida;
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
public class FarmaFMMprimasSalidaDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FarmaFMMprimasSalidaDAO.class);

    DateTimeFormatter dateTimeFormatterParser = DateTimeFormatter.ofPattern("yyyyMMdd");

    public FarmaFMMprimasSalidaDAO() {
        super();
    }

    private FarmaFMMprimasSalida getRegistroResulset(ResultSet rs) {
        FarmaFMMprimasSalida farmaFMMPrimasSalida = new FarmaFMMprimasSalida();
        try {

            // esto es una chapu esta parte es el método getRegistroResulset de
            LocalDate localDateUlti = null;
            if (rs.getInt("ulti_revi") != 0) {
                String ulti = Integer.toString(rs.getInt("ulti_revi"));
                localDateUlti = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            farmaFMMPrimasSalida.setCod_inte(rs.getInt("cod_inte"));
            farmaFMMPrimasSalida.setProducto(rs.getString("producto"));
            farmaFMMPrimasSalida.setCod_labo(rs.getString("cod_labo"));
            farmaFMMPrimasSalida.setLaboratorio(rs.getString("laboratorio"));
            farmaFMMPrimasSalida.setHomologado(rs.getBoolean("homologado"));
            farmaFMMPrimasSalida.setN_labo(rs.getInt("n_labo"));
            farmaFMMPrimasSalida.setStock_min(rs.getInt("stock_min"));
            farmaFMMPrimasSalida.setObservaciones(rs.getString("observaciones"));
            farmaFMMPrimasSalida.setEspecifica(rs.getString("especifica"));
            farmaFMMPrimasSalida.setUlti_revi(localDateUlti);
            farmaFMMPrimasSalida.setFarmacetuico(rs.getString("farmaceutico"));
            farmaFMMPrimasSalida.setExistencias(rs.getInt("existencias"));
            farmaFMMPrimasSalida.setNlaboratorio(rs.getString("nlaboratorio"));
            farmaFMMPrimasSalida.setPresentacion(rs.getString("presentacion"));
            farmaFMMPrimasSalida.setDescripcion(rs.getString("descripcion"));
            farmaFMMPrimasSalida.setRequisitos(rs.getString("requisitos"));
            farmaFMMPrimasSalida.setConservacion(rs.getString("conservacion"));
// fin de la chapu
            LocalDate localDate = null;
            if (rs.getInt("fecha") != 0) {
                String ulti = Integer.toString(rs.getInt("fecha"));
                localDate = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            farmaFMMPrimasSalida.setFecha(localDate);
            farmaFMMPrimasSalida.setComentario1(rs.getString("comentario1"));
            farmaFMMPrimasSalida.setCantidad(rs.getInt("cantidad"));
            farmaFMMPrimasSalida.setNumero(rs.getInt("numero"));

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return farmaFMMPrimasSalida;
    }

    public FarmaFMMprimasSalida getPorMPNumero(Integer cod_inte, Integer numero) {
        Connection connection = null;
        FarmaFMMprimasSalida farmaFMMPrimasSalida = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_primas_sal,  farm_fm_mprimas  "
                    + " WHERE  farm_fm_primas_sal.cod_inte= farm_fm_mprimas.cod_inte "
                    + " AND farm_fm_primas_sal.numero='" + numero + "' "
                    + " AND farm_fm_primas_sal.cod_inte=" + cod_inte;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMMPrimasSalida = getRegistroResulset(resulSet);
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
        return farmaFMMPrimasSalida;
    }

    public boolean doGrabaDatos(FarmaFMMprimasSalida farmaFMMprimasSalida) {
        boolean actualizado = false;

        if (this.getPorMPNumero(farmaFMMprimasSalida.getCod_inte(), farmaFMMprimasSalida.getNumero()) == null) {
            actualizado = this.doInsertaDatos(farmaFMMprimasSalida);
        } else {
            actualizado = this.doActualizaDatos(farmaFMMprimasSalida);
        }
        return actualizado;
    }

    public boolean doActualizaDatos(FarmaFMMprimasSalida farmaFMMprimasSalida) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "UPDATE   farm_fm_primas_sal SET fecha=?,cantidad=? ,comentario1=? "
                    + "  WHERE cod_inte=? AND numero=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            if (farmaFMMprimasSalida.getFecha() == null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setString(1, dateTimeFormatterParser.format(farmaFMMprimasSalida.getFecha()));
            }
            if (farmaFMMprimasSalida.getCantidad() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, farmaFMMprimasSalida.getCantidad());
            }
            if (farmaFMMprimasSalida.getComentario1() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, farmaFMMprimasSalida.getComentario1());
            }
            if (farmaFMMprimasSalida == null && farmaFMMprimasSalida.getCod_inte() != null) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, farmaFMMprimasSalida.getCod_inte());
            }
            if (farmaFMMprimasSalida.getNumero() == null) {
                statement.setNull(10, Types.INTEGER);
            } else {
                statement.setInt(10, farmaFMMprimasSalida.getNumero());
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

    public boolean doInsertaDatos(FarmaFMMprimasSalida farmaFMMPrimasSalida) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "INSERT INTO    farm_fm_primas_sal  (cod_inte,numero, fecha,cantidad ,comentario1) "
                    + " VALUES (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            if (farmaFMMPrimasSalida == null && farmaFMMPrimasSalida.getCod_inte() != null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, farmaFMMPrimasSalida.getCod_inte());
            }
            if (farmaFMMPrimasSalida.getNumero() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, farmaFMMPrimasSalida.getNumero());
            }
            if (farmaFMMPrimasSalida.getFecha() == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setString(3, dateTimeFormatterParser.format(farmaFMMPrimasSalida.getFecha()));
            }
            if (farmaFMMPrimasSalida.getCantidad() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, farmaFMMPrimasSalida.getCantidad());
            }
            if (farmaFMMPrimasSalida.getComentario1() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, farmaFMMPrimasSalida.getComentario1());
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

    public boolean doBorraDatos(FarmaFMMprimasSalida farmaFMMPrimasSalida) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_primas_sal WHERE cod_inte='" + farmaFMMPrimasSalida.getCod_inte() + "' "
                    + " AND numero=" + farmaFMMPrimasSalida.getNumero();
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

    public ArrayList<FarmaFMMprimasSalida> getListaSalidasMP(LocalDate desde, LocalDate hasta, FarmaFMMPrimas farmaFMMPrimas) {
        Connection connection = null;
        ArrayList<FarmaFMMprimasSalida> listaSalidas = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_primas_sal, farm_fm_mprimas WHERE  "
                    + " farm_fm_primas_sal.cod_inte= farm_fm_mprimas.cod_inte   ";
            if (desde != null) {
                sql = sql.concat(" AND fecha>=" + dateTimeFormatterParser.format(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND hasta>=" + dateTimeFormatterParser.format(hasta));
            }
            if (farmaFMMPrimas != null && farmaFMMPrimas.getCod_inte() != null) {
                sql = sql.concat(" AND farm_fm_primas_sal.cod_inte=" + farmaFMMPrimas.getCod_inte());
            }
            sql = sql.concat(" ORDER BY farm_fm_primas_sal.cod_inte,fecha");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaSalidas.add(getRegistroResulset(resulSet));
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
        return listaSalidas;
    }

    public Integer getNumeroSiguiente(FarmaFMMprimasSalida farmaFMMPrimasSalida) {
        Connection connection = null;
        Integer siguiente = 1;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT max(numero) as valor FROM farm_fm_primas_sal WHERE    cod_inte=" + farmaFMMPrimasSalida.getCod_inte();
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
