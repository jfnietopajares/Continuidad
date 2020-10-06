package es.sacyl.hnss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.PagiLisReg;
import com.jnieto.entity.ResultadoTsoh;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Utilidades;

/**
 * The Class ResultadosTsohDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class ResultadosTsohDAO {

    private DataSource dataSource = null;

    private final Logger logger = LogManager.getLogger(ConexionDAO.class);

    private InitialContext ctx;

    public final static String ERROR_BBDD_SIN_CONEXION = "No se ha podido establecer la conexión con la base de datos ";

    public final static String ERROR_BBDD_SQL = "Error en sentencia SQL. ";

    public final static String ERROR_CLOSE_BBDD_SQL = "Error cerrando conexión. ";

    public final static String ERROR_BBDD_CONTEXTO = "Error iniciando contexto.";

    private String sql;

    /**
     * Gets the conexion BBDD.
     *
     * @return the conexion BBDD
     */
    public Connection getConexionLIS() {
        try {
            if (dataSource == null) {
                ctx = new InitialContext();
                if (ctx == null) {
                    throw new Exception(ConexionDAO.ERROR_BBDD_CONTEXTO);
                } else {
                    dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/LIS");
                    if (dataSource != null) {
                        return dataSource.getConnection();
                    } else {
                        logger.error(ConexionDAO.ERROR_BBDD_SIN_CONEXION);
                    }
                }
            } else {
                return dataSource.getConnection();
            }
        } catch (Exception e) {
            logger.error(ConexionDAO.ERROR_BBDD_SIN_CONEXION, e);
        }
        return null;
    }

    /**
     * Gets the paginacion paciente.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @return the paginacion paciente
     */
    public PagiLisReg getPaginacionPaciente(LocalDate desde, LocalDate hasta) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = getConexionLIS();
            sql = "SELECT count(*) AS numero " + " FROM  adatos d  "
                    + " JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='1/1/3000' "
                    + " JOIN pacientes p ON d.pid = p.pid  AND p.baja='1/1/3000' "
                    + "  WHERE d.fecha>=?   AND d.fecha<=?" + "  AND d.baja='1/1/3000' " + " AND a.PRUEBA='H.SANC' "
                    + "    AND NOT a.valor IS NULL ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Utilidades.getFechaNumeroyyymmddDefecha(desde));
            statement.setLong(2, Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                contador = resulSet.getInt("numero");
            }
            paginacion.setPrimero(1);
            paginacion.setUltimo(contador);
            paginacion.setRegistrosTotales(contador);
            statement.close();
            logger.debug("SELECT count(*) AS numero " + " FROM  adatos d  "
                    + " JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='1/1/3000' "
                    + " JOIN pacientes p ON d.pid = p.pid  AND p.baja='1/1/3000' " + "  WHERE d.fecha>="
                    + Utilidades.getFechaNumeroyyymmddDefecha(desde) + "   AND d.fecha<="
                    + Utilidades.getFechaNumeroyyymmddDefecha(hasta) + "" + "  AND d.baja='1/1/3000' "
                    + " AND a.PRUEBA='H.SANC' " + "    AND NOT a.valor IS NULL ");
        } catch (SQLException e) {
            logger.error("SELECT count(*) AS numero " + " FROM  adatos d  "
                    + " JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='1/1/3000' "
                    + " JOIN pacientes p ON d.pid = p.pid  AND p.baja='1/1/3000' " + "  WHERE d.fecha>="
                    + Utilidades.getFechaNumeroyyymmddDefecha(desde) + "   AND d.fecha<="
                    + Utilidades.getFechaNumeroyyymmddDefecha(hasta) + "" + "  AND d.baja='1/1/3000' "
                    + " AND a.PRUEBA='H.SANC' " + "    AND NOT a.valor IS NULL ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paginacion;
    }

    /**
     * Gets the resultados tsho.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @param paginacion the paginacion
     * @return the resultados tsho
     */
    public ArrayList<ResultadoTsoh> getResultadosTsho(LocalDate desde, LocalDate hasta, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<ResultadoTsoh> resultados = new ArrayList<>();
        int contador = 0;
        try {
            connection = getConexionLIS();
            sql = "SELECT  p.ape1,p.ape2,p.nombre,p.fnac,d.fecha,d.sid,d.pid,d.alta,d.baja,a.prueba,a.valor ,a.comentario,a.STATUS1,a.sid "
                    + " FROM  adatos d  " + " JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='1/1/3000' "
                    + " JOIN pacientes p ON d.pid = p.pid  AND p.baja='1/1/3000' "
                    + "  WHERE d.fecha>=?   AND d.fecha<=?" + "  AND d.baja='1/1/3000' " + "  AND a.PRUEBA='H.SANC' "
                    + "  AND NOT a.valor IS NULL ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Utilidades.getFechaNumeroyyymmddDefecha(desde));
            statement.setLong(2, Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                ResultadoTsoh resultado = getResutadoResulset(resulSet);
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        resultado.setNumeroOrden(resulSet.getInt("numeroorden"));
                        resultados.add(resultado);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        resultado.setNumeroOrden(resulSet.getInt("numeroorden"));
                        resultados.add(resultado);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                }
            }
            statement.close();
            logger.error(
                    sql = "SELECT  p.ape1,p.ape2,p.nombre,p.fnac,d.fecha,d.sid,d.pid,d.alta,d.baja,a.prueba,a.valor ,a.comentario,a.STATUS1,a.sid "
                    + " FROM  adatos d  "
                    + " JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='1/1/3000' "
                    + " JOIN pacientes p ON d.pid = p.pid  AND p.baja='1/1/3000' " + "  WHERE d.fecha>="
                    + Utilidades.getFechaNumeroyyymmddDefecha(desde) + "   AND d.fecha<="
                    + Utilidades.getFechaNumeroyyymmddDefecha(hasta) + "" + "  AND d.baja='1/1/3000' "
                    + "  AND a.PRUEBA='H.SANC' " + "  AND NOT a.valor IS NULL ");
        } catch (SQLException e) {
            logger.error(
                    sql = "SELECT  p.ape1,p.ape2,p.nombre,p.fnac,d.fecha,d.sid,d.pid,d.alta,d.baja,a.prueba,a.valor ,a.comentario,a.STATUS1,a.sid "
                    + " FROM  adatos d  "
                    + " JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='1/1/3000' "
                    + " JOIN pacientes p ON d.pid = p.pid  AND p.baja='1/1/3000' " + "  WHERE d.fecha>="
                    + Utilidades.getFechaNumeroyyymmddDefecha(desde) + "   AND d.fecha<="
                    + Utilidades.getFechaNumeroyyymmddDefecha(hasta) + "" + "  AND d.baja='1/1/3000' "
                    + "  AND a.PRUEBA='H.SANC' " + "  AND NOT a.valor IS NULL ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return resultados;
    }

    /**
     * Gets the resultados tsho.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @return the resultados tsho
     */
    public ArrayList<ResultadoTsoh> getResultadosTsho(LocalDate desde, LocalDate hasta) {
        Connection connection = null;
        ArrayList<ResultadoTsoh> resultados = new ArrayList<>();
        try {
            connection = getConexionLIS();
            sql = "SELECT  p.ape1,p.ape2,p.nombre,p.fnac,d.fecha,d.sid,d.pid,d.alta,d.baja,a.prueba,a.valor ,a.comentario,a.STATUS1,a.sid "
                    + " FROM  adatos d  " + " JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='1/1/3000' "
                    + " JOIN pacientes p ON d.pid = p.pid  AND p.baja='1/1/3000' "
                    + "  WHERE d.fecha>=?   AND d.fecha<=?" + "  AND d.baja='1/1/3000' " + " AND a.PRUEBA='H.SANC' "
                    + "    AND NOT a.valor IS NULL ";

            sql = "SELECT  p.ape1,p.ape2,p.nombre,p.fnac,d.fecha,d.sid,d.pid,d.alta,d.baja,a.prueba,a.valor ,a.comentario,a.STATUS1,a.sid "
                    + " FROM  adatos d  " + "  JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA  "
                    + " JOIN pacientes p ON d.pid = p.pid   " + "  WHERE d.fecha>=?   AND d.fecha<=?"
                    + "  AND a.PRUEBA='H.SANC' " + "    AND NOT a.valor IS NULL ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Utilidades.getFechaNumeroyyymmddDefecha(desde));
            statement.setLong(2, Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                ResultadoTsoh resultado = getResutadoResulset(resulSet);
                resultados.add(resultado);
            }
            statement.close();
            logger.debug(
                    "SELECT  p.ape1,p.ape2,p.nombre,p.fnac,d.fecha,d.sid,d.pid,d.alta,d.baja,a.prueba,a.valor ,a.comentario,a.STATUS1,a.sid "
                    + " FROM  adatos d  " + "  JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA  "
                    + " JOIN pacientes p ON d.pid = p.pid   " + "  WHERE d.fecha>="
                    + Utilidades.getFechaNumeroyyymmddDefecha(desde) + "   AND d.fecha<="
                    + Utilidades.getFechaNumeroyyymmddDefecha(hasta) + "" + "  AND a.PRUEBA='H.SANC' "
                    + "    AND NOT a.valor IS NULL ");
        } catch (SQLException e) {
            logger.error(
                    "SELECT  p.ape1,p.ape2,p.nombre,p.fnac,d.fecha,d.sid,d.pid,d.alta,d.baja,a.prueba,a.valor ,a.comentario,a.STATUS1,a.sid "
                    + " FROM  adatos d  " + "  JOIN avalor a   ON d.sid=a.SID AND d.FECHA=a.FECHA  "
                    + " JOIN pacientes p ON d.pid = p.pid   " + "  WHERE d.fecha>="
                    + Utilidades.getFechaNumeroyyymmddDefecha(desde) + "   AND d.fecha<="
                    + Utilidades.getFechaNumeroyyymmddDefecha(hasta) + "" + "  AND a.PRUEBA='H.SANC' "
                    + "    AND NOT a.valor IS NULL ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return resultados;
    }

    /**
     * Gets the resutado resulset.
     *
     * @param rs the rs
     * @return the resutado resulset
     */
    public ResultadoTsoh getResutadoResulset(ResultSet rs) {
        ResultadoTsoh resultado = new ResultadoTsoh();

        try {
            resultado.setId(rs.getLong("sid"));
            resultado.setPaciente(new PacienteDAO().getPacientePorNhc(rs.getString("pid"), false));
            resultado.setFecha(rs.getLong("fecha"));
            resultado.setResultado(rs.getString("valor"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }

        return resultado;

    }
}
