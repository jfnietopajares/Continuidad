package es.sacyl.hnss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Mensaje;
import com.jnieto.entity.MensajesEstados;
import com.jnieto.entity.MensajesTipos;
import com.jnieto.entity.PagiLisReg;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Constantes;
import com.jnieto.utilidades.Utilidades;
import java.sql.Types;

/**
 * The Class MensajesDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class MensajesDAO extends ConexionDAO {

    private String sql;

    private Mensaje mensaje;

    private static final Logger logger = LogManager.getLogger(MensajesDAO.class);

    /**
     * Instantiates a new mensajes DAO.
     */
    public MensajesDAO() {
        super();
    }

    /**
     * Gets the registro resulset.
     *
     * @param resulSet the resul set
     * @return the registro resulset
     */
    public Mensaje getRegistroResulset(ResultSet resulSet) {
        Mensaje mensaje = new Mensaje();
        try {
            mensaje.setId(resulSet.getLong("id"));
            mensaje.setContenido(resulSet.getString("mensaje"));
            mensaje.setTipo(new MensajesTipos(resulSet.getInt("tipo")));
            mensaje.setUserid_destino(resulSet.getString("userid_destino"));
            //      mensaje.setError(resulSet.getString("error"));
            mensaje.setPaciente(new PacienteDAO().getPacientePorId(resulSet.getLong(("paciente_destino")), false));
            mensaje.setEstado(new MensajesEstados(resulSet.getInt("estado")));
            mensaje.setFecha(resulSet.getLong("fecha"));
            return mensaje;
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }

        return mensaje;
    }

    /**
     * Gets the lista pendientes 10.
     *
     * @return the lista pendientes 10
     */
    public ArrayList<Mensaje> getListaPendientes10() {
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *  FROM smss  WHERE  estado=? AND tipo!=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Mensaje.MENSAJE_PENDIENTE_ENVIO.getEstado());
            statement.setLong(2, Mensaje.MENSAJE_TIPO_PANTALLA.getTipo());
            ResultSet resulSet = statement.executeQuery();
            int contador = 1;
            while (resulSet.next() && contador < 1000) {
                mensaje = getRegistroResulset(resulSet);
                listaMensajes.add(mensaje);
                contador++;
            }
            statement.close();
            logger.debug("SELECT *  FROM smss  WHERE  estado=" + Mensaje.MENSAJE_PENDIENTE_ENVIO.getEstado()
                    + " AND tipo! =" + Mensaje.MENSAJE_TIPO_PANTALLA.getTipo());
        } catch (SQLException e) {
            logger.error("SELECT *  FROM smss  WHERE  estado=" + Mensaje.MENSAJE_PENDIENTE_ENVIO.getEstado()
                    + " AND tipo! =" + Mensaje.MENSAJE_TIPO_PANTALLA.getTipo());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaMensajes;
    }

    /**
     * Gets the sql where.
     *
     * @param cadena the cadena
     * @return the sql where
     */
    public String getSqlWhere(LocalDate desde, LocalDate hasta, String usuarioString, String historia, int estado,
            int tipo) {
        String sqlString = "";
        try {
            sqlString = sqlString.concat(" AND fecha>= " + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            sqlString = sqlString.concat(" AND fecha<= " + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            if (!usuarioString.isEmpty()) {
                sqlString = sqlString.concat(" AND userid_destino like '%" + usuarioString + "%'");
            }
            if (tipo != 0) {
                sqlString = sqlString.concat(" AND tipo= " + tipo);
            }
            if (estado != 0) {
                sqlString = sqlString.concat(" AND estado= " + estado);
            }
            if (!historia.isEmpty()) {
                sqlString = sqlString.concat(" AND nhc= '" + historia + "'");
            }
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return sqlString;
    }

    public PagiLisReg getPaginacionRegistros(LocalDate desde, LocalDate hasta, String usuario, String historia,
            int estado, int tipo) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT count(*) as numero FROM smss  s, pacientes p , historias h"
                    + " WHERE s.paciente_destino = p.id   AND p.id=h.paciente  ";
            sql = sql.concat(getSqlWhere(desde, hasta, usuario, historia, estado, tipo));
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                contador = resulSet.getInt("numero");
            }
            paginacion.setPrimero(1);
            paginacion.setUltimo(contador);
            paginacion.setRegistrosTotales(contador);
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
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

    public ArrayList<Mensaje> getListaMensajes(LocalDate desde, LocalDate hasta, String usuario, String historia,
            int estado, int tipo, PagiLisReg paginacion) {
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = "SELECT  @rownum:=@rownum+1  as numeroorden ,s.*"
                        + "	FROM smss  s,pacientes p  , historias h,(SELECT @rownum:=0) r"
                        + " WHERE  s.paciente_destino = p.id   AND p.id=h.paciente ";
            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {
                sql = "SELECT  row_number() over (ORDER BY fecha,hora ) as numeroorden ,s.*"
                        + "	FROM smss  s,pacientes p  , historias h"
                        + " WHERE  s.paciente_destino = p.id   AND p.id=h.paciente ";
            }
            sql = sql.concat(getSqlWhere(desde, hasta, usuario, historia, estado, tipo));
            sql = sql.concat(" ORDER BY fecha,hora");
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            int contador = 1;
            while (resulSet.next() && contador < 10) {
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        mensaje = getRegistroResulset(resulSet);
                        mensaje.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaMensajes.add(mensaje);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        mensaje = getRegistroResulset(resulSet);
                        mensaje.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaMensajes.add(mensaje);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                }

            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
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
        return listaMensajes;
    }

    public ArrayList<Mensaje> getListaMensajes(String usuario, int estado) {
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * 	FROM smss  s WHERE  userid_destino LIKE '" + usuario + "%' " + " AND estado=" + estado;
            sql = sql.concat(" ORDER BY fecha,hora");
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                mensaje = getRegistroResulset(resulSet);
                listaMensajes.add(mensaje);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
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
        return listaMensajes;
    }

    /**
     * Graba datos.
     *
     * @param mensajeparam the mensajeparam
     * @return true, if successful
     */
    public boolean grabaDatos(Mensaje mensajeparam) {
        this.mensaje = mensajeparam;
        boolean actualizado = false;
        if (mensaje.getId() == 0) {
            actualizado = this.insertaDatos(mensaje);
        } else {
            actualizado = this.actualizaDatos(mensaje);
        }
        return actualizado;
    }

    /**
     * Actualiza datos.
     *
     * @param mensajeparam the mensajeparam
     * @return true, if successful
     */
    public boolean actualizaDatos(Object mensajeparam) {
        this.mensaje = (Mensaje) mensajeparam;
        boolean actualizado = false;
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   smss SET fecha=?, hora=?, tipo=? , estado=? ,userid_destino=?,paciente_destino=?,contenido=? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, mensaje.getFecha());
            statement.setLong(2, mensaje.getHora());
            statement.setInt(3, mensaje.getTipo().getTipo());
            statement.setInt(4, mensaje.getEstado().getEstado());
            statement.setString(5, mensaje.getUserid_destino());
            statement.setLong(6, mensaje.getPaciente().getId());
            statement.setString(7, mensaje.getContenido());
            statement.setLong(8, mensaje.getId());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE   smss SET fecha=" + mensaje.getFecha() + ", hora= " + mensaje.getHora() + ""
                    + ", tipo=" + mensaje.getTipo().getTipo() + " , estado=" + mensaje.getEstado().getEstado()
                    + " ,userid_destino='" + mensaje.getUserid_destino() + "'" + ",paciente_destino="
                    + mensaje.getPaciente().getId() + ",contenido='" + mensaje.getContenido() + "' WHERE id= "
                    + mensaje.getId());
        } catch (SQLException e) {
            logger.error(" UPDATE   smss SET fecha=" + mensaje.getFecha() + ", hora= " + mensaje.getHora() + ""
                    + ", tipo=" + mensaje.getTipo().getTipo() + " , estado=" + mensaje.getEstado().getEstado()
                    + " ,userid_destino='" + mensaje.getUserid_destino() + "'" + ",paciente_destino="
                    + mensaje.getPaciente().getId() + ",contenido='" + mensaje.getContenido() + "' WHERE id= "
                    + mensaje.getId());
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
        return actualizado;
    }

    /**
     * Inserta datos.
     *
     * @param mensajeparam the mensajeparam
     * @return true, if successful
     */
    public boolean insertaDatos(Mensaje mensajeparam) {
        this.mensaje = mensajeparam;
        Long id = null;
        boolean insertado = false;
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("smss");
            mensaje.setId(id);
            sql = " INSERT INTO smss (id,fecha,hora,tipo,estado,userid_destino,paciente_destino,mensaje) VALUES (?,?,?,?,?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, mensaje.getId());
            statement.setLong(2, mensaje.getFecha());
            statement.setLong(3, mensaje.getHora());
            statement.setInt(4, mensaje.getTipo().getTipo());
            statement.setInt(5, mensaje.getEstado().getEstado());
            statement.setString(6, mensaje.getUserid_destino());
            if (mensaje.getPaciente() != null) {
                statement.setLong(7, mensaje.getPaciente().getId());
            } else {
                statement.setNull(7, Types.INTEGER);
            }
            if (mensaje.getContenido().length() < 319) {
                statement.setString(8, mensaje.getContenido());
            } else {
                statement.setString(8, mensaje.getContenido().substring(0, 319));
            }

            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(
                    " INSERT INTO smss (id,fecha,hora,tipo,estado,userid_destino,paciente_destino,mensaje) VALUES ("
                    + mensaje.getId() + "," + mensaje.getFecha() + "," + mensaje.getHora() + ","
                    + mensaje.getTipo().getTipo() + "," + mensaje.getTipo().getTipo() + ","
                    + mensaje.getEstado().getEstado() + ",'" + mensaje.getUserid_destino() + "','"
                    + mensaje.getContenido() + "')  ");
        } catch (SQLException e) {
            logger.error(
                    " INSERT INTO smss (id,fecha,hora,tipo,estado,userid_destino,paciente_destino,mensaje) VALUES ("
                    + mensaje.getId() + "," + mensaje.getFecha() + "," + mensaje.getHora() + ","
                    + mensaje.getTipo().getTipo() + "," + mensaje.getTipo().getTipo() + ","
                    + mensaje.getEstado().getEstado() + ",'" + mensaje.getUserid_destino() + "','"
                    + mensaje.getContenido() + "')  ");
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
        return insertado;
    }

    /**
     * Sets the marca enviado.
     *
     * @param mensaje2 the mensaje 2
     * @return true, if successful
     */
    public boolean setMarcaEnviado(Mensaje mensaje2) {
        Connection connection = null;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   smss  SET estado=?, fecha_proceso=?,hora_proceso=? WHERE ID=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mensaje2.getEstado().getEstado());
            statement.setLong(2, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
            statement.setLong(3, Utilidades.getHoraNumeroAcual());
            statement.setLong(4, mensaje2.getId());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE   smss  SET estado=" + mensaje2.getEstado().getEstado() + ", fecha_proceso="
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ",hora_proceso="
                    + Utilidades.getHoraNumeroAcual() + " WHERE ID= " + mensaje2.getId());
        } catch (SQLException e) {
            logger.error(" UPDATE   smss  SET estado=" + mensaje2.getEstado().getEstado() + ", fecha_proceso="
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ",hora_proceso="
                    + Utilidades.getHoraNumeroAcual() + " WHERE ID= " + mensaje2.getId());
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
        return actualizado;
    }

    /**
     * Sets the marca error.
     *
     * @param mensaje2 the mensaje 2
     * @return true, if successful
     */
    public boolean setMarcaError(Mensaje mensaje2) {
        Connection connection = null;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   smss  SET estado=?, fecha_proceso=?,hora_proceso=?, error=? WHERE ID=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mensaje2.getEstado().getEstado());
            statement.setLong(2, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
            statement.setLong(3, Utilidades.getHoraNumeroAcual());
            statement.setString(4, mensaje2.getError());
            statement.setLong(5, mensaje2.getId());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE   smss  SET estado=" + mensaje2.getEstado().getEstado() + ", fecha_proceso="
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ",hora_proceso="
                    + Utilidades.getHoraNumeroAcual() + ",error='" + mensaje2.getError() + "' WHERE ID= "
                    + mensaje2.getId());
        } catch (SQLException e) {
            logger.error(" UPDATE   smss  SET estado=" + mensaje2.getEstado().getEstado() + ", fecha_proceso="
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ",hora_proceso="
                    + Utilidades.getHoraNumeroAcual() + ",error='" + mensaje2.getError() + "' WHERE ID= "
                    + mensaje2.getId());
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
        return actualizado;
    }
}
