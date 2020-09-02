package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMViasAdm;
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
public class FMMprimasEntraDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FMMprimasEntraDAO.class);
    private static final long serialVersionUID = 1L;

    DateTimeFormatter dateTimeFormatterParser = DateTimeFormatter.ofPattern("yyyyMMdd");

    public FMMprimasEntraDAO() {
        super();
    }

    private FMMPrimasEntrada getRegistroResulset(ResultSet rs) {
        FMMPrimasEntrada fMInstrumento = new FMMPrimasEntrada();
        try {

            // esto es una chapu esta parte es el método getRegistroResulset de
            LocalDate localDateUlti = null;
            if (rs.getInt("ulti_revi") != 0) {
                String ulti = Integer.toString(rs.getInt("ulti_revi"));
                localDateUlti = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            fMInstrumento.setCod_inte(rs.getInt("cod_inte"));
            fMInstrumento.setProducto(rs.getString("producto"));
            fMInstrumento.setCod_labo(rs.getString("cod_labo"));
            fMInstrumento.setLaboratorio(rs.getString("laboratorio"));
            fMInstrumento.setHomologado(rs.getBoolean("homologado"));
            fMInstrumento.setN_labo(rs.getInt("n_labo"));
            fMInstrumento.setStock_min(rs.getInt("stock_min"));
            fMInstrumento.setObservaciones(rs.getString("observaciones"));
            fMInstrumento.setEspecifica(rs.getString("especifica"));
            fMInstrumento.setUlti_revi(localDateUlti);
            fMInstrumento.setFarmacetuico(rs.getString("farmaceutico"));
            fMInstrumento.setExistencias(rs.getInt("existencias"));
            fMInstrumento.setNlaboratorio(rs.getString("nlaboratorio"));
            fMInstrumento.setPresentacion(rs.getString("presentacion"));
            fMInstrumento.setDescripcion(rs.getString("descripcion"));
            fMInstrumento.setRequisitos(rs.getString("requisitos"));
            fMInstrumento.setConservacion(rs.getString("conservacion"));
// fin de la chapu
            LocalDate localDate = null;
            if (rs.getInt("fecha") != 0) {
                String ulti = Integer.toString(rs.getInt("fecha"));
                localDate = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            fMInstrumento.setFecha(localDate);
            fMInstrumento.setRegistro(rs.getInt("registro"));
            fMInstrumento.setEnvases(rs.getInt("envases"));
            fMInstrumento.setLote(rs.getString("lote"));
            fMInstrumento.setVerificacion(rs.getBoolean("verificacion"));
            fMInstrumento.setCtrlAnalitico(rs.getString("ctrl_analitico"));
            fMInstrumento.setCaducidad(rs.getString("caducidad"));
            fMInstrumento.setNumero(rs.getInt("numero"));

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return fMInstrumento;
    }

    public FMMPrimasEntrada getPorMPNumero(Integer cod_inte, Integer numero) {
        Connection connection = null;
        FMMPrimasEntrada fMInstrumento = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_primas_ent,  farm_fm_mprimas  "
                    + " WHERE  farm_fm_primas_ent.cod_inte= farm_fm_mprimas.cod_inte "
                    + " AND farm_fm_primas_ent.numero='" + numero + "' "
                    + " AND farm_fm_primas_ent.cod_inte=" + cod_inte;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMInstrumento = getRegistroResulset(resulSet);
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
        return fMInstrumento;

    }

    public boolean doGrabaDatos(FMMPrimasEntrada fMInstrumento) {
        boolean actualizado = false;

        if (this.getPorMPNumero(fMInstrumento.getCod_inte(), fMInstrumento.getNumero()) == null) {
            actualizado = this.doInsertaDatos(fMInstrumento);
        } else {
            actualizado = this.doActualizaDatos(fMInstrumento);
        }
        return actualizado;
    }

    public boolean doActualizaDatos(FMMPrimasEntrada fMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "UPDATE   farm_fm_primas_ent  SET fecha=?,registro=? ,envases=? ,lote=?,verificacion=?"
                    + " ,ctrl_analitico=? , farmaceutico=? "
                    + ",caducidad=? "
                    + "  WHERE cod_inte=? AND numero=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            if (fMInstrumento.getFecha() == null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setString(1, dateTimeFormatterParser.format(fMInstrumento.getFecha()));
            }
            if (fMInstrumento.getRegistro() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, fMInstrumento.getRegistro());
            }
            if (fMInstrumento.getEnvases() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setInt(3, fMInstrumento.getEnvases());
            }
            if (fMInstrumento.getLote() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, fMInstrumento.getLote());
            }
            if (fMInstrumento.getVerificacion() == null) {
                statement.setNull(5, Types.BOOLEAN);
            } else {
                statement.setBoolean(5, fMInstrumento.getVerificacion());
            }
            if (fMInstrumento.getCtrlAnalitico() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, fMInstrumento.getCtrlAnalitico());
            }
            if (fMInstrumento.getFarmaceutico() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, fMInstrumento.getFarmaceutico());
            }
            if (fMInstrumento.getCaducidad() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, fMInstrumento.getCaducidad());
            }
            if (fMInstrumento == null || fMInstrumento.getCod_inte() == null) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, fMInstrumento.getCod_inte());
            }
            if (fMInstrumento.getNumero() == null) {
                statement.setNull(10, Types.INTEGER);
            } else {
                statement.setInt(10, fMInstrumento.getNumero());
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

    public boolean doInsertaDatos(FMMPrimasEntrada fMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "INSERT INTO    farm_fm_primas_ent  (cod_inte,numero, fecha,registro ,envases ,lote,verificacion"
                    + " ,ctrl_analitico, farmaceutico,caducidad) "
                    + " VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            if (fMInstrumento == null || fMInstrumento.getCod_inte() == null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, fMInstrumento.getCod_inte());
            }
            if (fMInstrumento.getNumero() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, fMInstrumento.getNumero());
            }
            if (fMInstrumento.getFecha() == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setString(3, dateTimeFormatterParser.format(fMInstrumento.getFecha()));
            }
            if (fMInstrumento.getRegistro() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, fMInstrumento.getRegistro());
            }
            if (fMInstrumento.getEnvases() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setInt(5, fMInstrumento.getEnvases());
            }
            if (fMInstrumento.getLote() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, fMInstrumento.getLote());
            }
            if (fMInstrumento.getVerificacion() == null) {
                statement.setNull(7, Types.BOOLEAN);
            } else {
                statement.setBoolean(7, fMInstrumento.getVerificacion());
            }

            if (fMInstrumento.getCtrlAnalitico() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, fMInstrumento.getCtrlAnalitico());
            }
            if (fMInstrumento.getFarmaceutico() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, fMInstrumento.getFarmaceutico());
            }
            if (fMInstrumento.getCaducidad() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, fMInstrumento.getCaducidad());
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

    public boolean doBorraDatos(FMMPrimasEntrada fMInstrumento) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_primas_ent WHERE cod_inte='" + fMInstrumento.getCod_inte() + "' "
                    + " AND numero=" + fMInstrumento.getNumero();
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

    public ArrayList<FMMPrimasEntrada> getListaEntradasMP(LocalDate desde, LocalDate hasta, FMMPrimas farmaFMMPrimas) {
        Connection connection = null;
        ArrayList<FMMPrimasEntrada> listaEntradas = new ArrayList<>();
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
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaEntradas;
    }

    public Integer getNumeroSiguiente(FMMPrimasEntrada fMInstrumento) {
        Connection connection = null;
        Integer siguiente = 1;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT max(numero) as valor FROM farm_fm_primas_ent WHERE    cod_inte=" + fMInstrumento.getCod_inte();
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
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return siguiente;

    }
}
