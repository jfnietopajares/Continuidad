package es.sacyl.hnss.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.vaadin.server.VaadinSession;
import es.sacyl.hnss.entity.Registro;
import es.sacyl.hnss.utilidades.Constantes;



/**
 * The Class RegistroDAO.
 *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroDAO extends ConexionDAO implements InterfaceDAO {

    protected String sql;

    private static final Logger logger = LogManager.getLogger(RegistroDAO.class);

    public static String ORDENFECHAHORA = "FECHAHORA";

    public static String ORDENFECHAHORADESC = "FECHAHORADESC";

    /**
     * Instantiates a new registro DAO.
     */
    public RegistroDAO() {
        super();
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return super.getConexionBBDD();
    }

    public String getSql() {
        return "SELECT  row_number() over (ORDER BY r.fecha,r.hora) as numeroorden, r.*  "
                + " ,t.id as idcentro, t.codigo as codigocentro,t.descripcion as descentro, t.nemonico, s.id as idservicio,s.codigo as codigoservicio, s.descripcion as descservicio "
                + ",u.userid as usuuserid, u.apellido1 as usuapellido1,u.apellido2 as usuapellido2,u.nombre as usunombre, u.categoria as usucategoria,u.estado as usuestado "
                + " FROM registros r "
                + " JOIN centros t ON t.id=r.centro JOIN servicios s ON s.id=r.servicio "
                + " LEFT JOIN usuarios U ON u.userid=r.userid "
                + " WHERE 1=1 ";

    }
    private String sqlOraclePacientePaginado = " r.*  "
            + "    ,t.id as idcentropri, t.codigo as codigocentropri,t.descripcion as descentropri, t.nemonico as nemonicopri, s.id as idservicio,s.codigo as codigoservicio, s.descripcion as descservicio "
            + "    ,u.userid as usuuserid, u.apellido1 as usuapellido1,u.apellido2 as usuapellido2,u.nombre as usunombre, u.categoria as usucategoria,u.estado as usuestado "
            + "    ,p.ape1,p.ape2,p.nombre,p.id as idpaciente,p.fnac,p.sexo,p.tarjeta,p.nss,p.dni,p.telefono,p.movil,p.nbdp,p.cias  "
            + "    ,p.direccion,p.codigopostal,p.provincia,p.municipio  "
            + "    ,v.descripcion as provinciadescripcion  "
            + "    ,w.descripcion as municipiodescripcion "
            + "    ,h.nhc "
            + "FROM registros r "
            + "    JOIN pacientes p ON p.id=r.paciente  "
            + "    JOIN historias h ON h.paciente=p.id "
            + "    JOIN centros t ON t.id=r.centro "
            + "    JOIN servicios s ON s.id=r.servicio "
            + "    LEFT JOIN usuarios U ON u.userid=r.userid "
            + "    LEFT JOIN provincias v ON v.codigo=p.provincia "
            + "    LEFT JOIN municipios w ON (w.id=p.municipio and w.provincia=p.provincia)"
            + "WHERE 1=1  ";

    /**
     * Graba datos.
     *
     * @param registro the registro
     * @return true, if successful
     */
    public boolean grabaDatos(Registro registro) {
        boolean actualizado = false;
        if (registro.getId() == 0) {
            actualizado = this.insertaDatos(registro);
        } else {
            actualizado = this.actualizaDatos(registro);
        }
        return actualizado;
    }

    /**
     * Actualiza datos.
     *
     * @param registro the registro
     * @return true, if successful
     */
    public boolean actualizaDatos(Registro registro) {
        boolean actualizado = false;
        try {
            // actualizado = doActualizaRegistro(registro);
            doActualizaEstadoRegistro(registro);
            actualizado = this.insertaDatos(registro);
            if (actualizado == true) {
                // poner en campos_r actualizado=5
                if (this.doCambiaEstadoCamposR(registro) == true) {
                    doInsertaCamposR(registro);
                } // campos_R estado=5
            } // ok de insercion de registro
        } catch (Exception e) {
            logger.error(Constantes.MSG_EXCEPTION, e);
        }
        return actualizado;
    }

    /**
     * Inserta datos.
     *
     * @param registro the registro
     * @return true, if successful
     */
    public boolean insertaDatos(Registro registro) {
        boolean insertado = false;
        Long idRegistro;
        try {
            idRegistro = new UtilidadesDAO().getSiguienteId("registros");
            registro.setId(idRegistro);
            insertado = doInsertaRegistro(registro);
            if (insertado == true) {
                // campos comunes a los registros
                if (!doInsertaCamposR(registro)) {
                    return false;
                }
            } // ok de insercion de registro
        } catch (Exception e) {
            logger.error(Constantes.MSG_EXCEPTION, e);
        }
        return insertado;
    }

    /**
     * Haz inserta registro.
     *
     * @param registro the registro
     * @return true, if successful
     */
    public boolean doInsertaRegistro(Registro registro) {
        Connection connection = null;
        boolean rowInserted = false;
        PreparedStatement statement;
        Long idproblemaLong = null;
        String sqlDebug="";
        try {
            connection = super.getConexionBBDD();
            if (registro.getEpisodio() == null) {
                sql = " INSERT INTO registros  (id, descripcion, paciente ,  centro,fecha,hora,estado,servicio,userid"
                        + ",plantilla_editor,canal,tiporegistro,problema, useridredactor)  "
                        + "            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
                statement = connection.prepareStatement(sql);
                statement.setLong(1, registro.getId());
                statement.setString(2, registro.getDescripcion());
                statement.setLong(3, registro.getPaciente().getId());
                statement.setLong(4, registro.getCentro().getId());
                statement.setLong(5, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
                if (registro.getHora() == null) {
                    statement.setLong(6, Utilidades.getHoraNumeroAcual());
                } else {
                    statement.setLong(6, registro.getHora());
                }
                statement.setInt(7, registro.getEstado());
                statement.setLong(8, registro.getServicio().getId());
                statement.setString(9, registro.getUserid().getUserid());
                statement.setLong(10, registro.getPlantilla_editor());
                statement.setLong(11, registro.getCanal());
                statement.setLong(12, registro.getTiporegistro());
                if (registro.getProblema() != null) {
                    statement.setLong(13, registro.getProblema().getId());
                } else {
                    statement.setNull(13, Types.INTEGER);
                }
                statement.setString(14, registro.getUseridredactor().getUserid());
                 sqlDebug="INSERT INTO registros  (id, descripcion, paciente ,  centro,fecha,hora,estado,servicio,userid"
                        + ",plantilla_editor,canal,tiporegistro,problema, useridredactor)  "
                        + "            VALUES (" + registro.getId() + ",'" + registro.getDescripcion() + "',"
                        + registro.getPaciente().getId() + "," + registro.getCentro().getId() + ","
                        + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ","
                        + Utilidades.getHoraNumeroAcual() + "," + registro.getEstado() + ","
                        + registro.getServicio().getId() + ",'" + registro.getUserid().getUserid() + "',"
                        + registro.getPlantilla_editor() + "," + registro.getCanal() + ","
                        + registro.getTiporegistro() + "," + idproblemaLong + ",'"
                        + registro.getUseridredactor().getUserid() + "' )  ";
                logger.debug(sqlDebug );
                rowInserted = statement.executeUpdate() > 0;
            } else {
                if (registro.getProblema() != null) {
                    idproblemaLong = registro.getProblema().getId();
                } else {
                    idproblemaLong = null;
                }
                sql = " INSERT INTO registros  (id, descripcion, paciente ,  centro,fecha,hora,estado,episodio,servicio,userid"
                        + ",plantilla_editor,canal,tiporegistro,problema,useridredactor )  "
                        + "            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
                statement = connection.prepareStatement(sql);
                statement.setLong(1, registro.getId());
                statement.setString(2, registro.getDescripcion());
                statement.setLong(3, registro.getPaciente().getId());
                statement.setLong(4, registro.getCentro().getId());
                statement.setLong(5, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));

                //     statement.setLong(6, Utilidades.getHoraNumeroAcual());
                if (registro.getHora() == null) {
                    statement.setLong(6, Utilidades.getHoraNumeroAcual());
                } else {
                    statement.setLong(6, registro.getHora());
                }
                statement.setInt(7, registro.getEstado());
                if (registro.getEpisodio() != null && !registro.getEpisodio().getId().equals(new Long(0))) {
                    statement.setLong(8, registro.getEpisodio().getId()); //
                } else {
                    statement.setNull(8, Type.LONG);
                }
                statement.setLong(9, registro.getServicio().getId());
                statement.setString(10, registro.getUserid().getUserid());
                statement.setLong(11, registro.getPlantilla_editor());
                statement.setLong(12, registro.getCanal());
                statement.setLong(13, registro.getTiporegistro());
                if (idproblemaLong != null) {
                    statement.setLong(14, idproblemaLong);
                } else {
                    statement.setNull(14, Type.LONG);
                }
                statement.setString(15, registro.getUseridredactor().getUserid());
                sqlDebug="INSERT INTO registros  (id, descripcion, paciente ,  centro,fecha,hora,estado,servicio,userid"
                        + ",plantilla_editor,canal,tiporegistro,problema, useridredactor)  "
                        + "            VALUES (" + registro.getId() + ",'" + registro.getDescripcion() + "',"
                        + registro.getPaciente().getId() + "," + registro.getCentro().getId() + ","
                        + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ","
                        + Utilidades.getHoraNumeroAcual() + "," + registro.getEstado() + ","
                        + "," + registro.getServicio().getId() + ",'"
                        + registro.getUserid().getUserid() + "'," + registro.getPlantilla_editor() + ","
                        + registro.getCanal() + "," + registro.getTiporegistro() + "," + idproblemaLong + ",'"
                        + registro.getUseridredactor().getUserid() + "' )  ";
                logger.debug(sqlDebug     );

                rowInserted = statement.executeUpdate() > 0;
            }

            statement.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sqlDebug, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return rowInserted;
    }

    /**
     * Haz borrar registro.
     *
     * @param registro the registro
     * @return true, if successful
     */
    public boolean doBorrarRegistro(Registro registro) {
        Connection connection = null;
        boolean rowInserted = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM campos_r WHERE registro=?   ";
            logger.info(" Borra campos_r  " + sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, registro.getId());
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" DELETE FROM campos_r WHERE registro= " + registro.getId());
            sql = " DELETE FROM registros WHERE id =?   ";
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setLong(1, registro.getId());
            rowInserted = statement1.executeUpdate() > 0;
            statement.close();
            logger.debug(" DELETE FROM registros WHERE id =?   " + registro.getId());
        } catch (SQLException e) {
            logger.error(" DELETE FROM campos_r WHERE registro= " + registro.getId());
            logger.error(" DELETE FROM registros WHERE id =?   " + registro.getId());
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return rowInserted;
    }

    /**
     * Haz inserta campos R.
     *
     * @param registro the registro
     * @param variable the variable
     * @param valor the valor
     * @return true, if successful
     */
    public boolean doInsertaCamposR(Registro registro, Variable variable, String valor) {
        Connection connection = null;
        boolean insertado = false;
        Long idCamposr = null;
        try {

            idCamposr = new UtilidadesDAO().getSiguienteId("campos_r");
            connection = super.getConexionBBDD();
            sql = " INSERT INTO campos_r   (id, registro,item,userid,fecha,hora,descripcion,orden,estado,dato,canal )  "
                    + "            VALUES (?,?,?,?,?,?,?,?,?,?,?)  ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idCamposr);
            statement.setLong(2, registro.getId());
            statement.setLong(3, variable.getItem());
            statement.setString(4, registro.getUserid().getUserid());
            statement.setLong(5, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
            statement.setLong(6, Utilidades.getHoraNumeroAcual());
            statement.setString(7, variable.getDescripcion());
            statement.setLong(8, Registro.ORDEN_DEFECTO); //
            statement.setLong(9, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setString(10, valor);
            statement.setLong(11, Registro.CANAL_DEFECTO);
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(
                    " INSERT INTO campos_r   (id, registro,item,userid,fecha,hora,descripcion,orden,estado,dato,canal )  "
                    + "            VALUES (" + idCamposr + "," + registro.getId() + "," + variable.getItem()
                    + ",'" + registro.getUserid().getUserid() + "',"
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ","
                    + Utilidades.getHoraNumeroAcual() + ",'" + variable.getDescripcion() + "',"
                    + Registro.ORDEN_DEFECTO + "," + Registro.VAR_RESGISTRO_ESTADO_NORMAL + ",'" + valor + "',"
                    + Registro.CANAL_DEFECTO + ")  ");
        } catch (SQLException e) {
            logger.error(
                    " INSERT INTO campos_r   (id, registro,item,userid,fecha,hora,descripcion,orden,estado,dato,canal )  "
                    + "            VALUES (" + idCamposr + "," + registro.getId() + "," + variable.getItem()
                    + ",'" + registro.getUserid().getUserid() + "',"
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ","
                    + Utilidades.getHoraNumeroAcual() + ",'" + variable.getDescripcion() + "',"
                    + Registro.ORDEN_DEFECTO + "," + Registro.VAR_RESGISTRO_ESTADO_NORMAL + ",'" + valor + "',"
                    + Registro.CANAL_DEFECTO + ")  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
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
     * Haz actualiza registro.
     *
     * @param registro the registro
     * @return true, if successful
     */
    public boolean doActualizaRegistro(Registro registro) {
        Connection connection = null;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   registros  SET  centro=?,servicio=?,userid=? , fecha=? , hora=?  WHERE id =?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, registro.getCentro().getId());
            statement.setLong(2, registro.getServicio().getId());
            statement.setString(3, registro.getUserid().getUserid());
            statement.setLong(4, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
            statement.setLong(5, Utilidades.getHoraNumeroAcual());
            statement.setLong(6, registro.getId());
            actualizado = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    public boolean doActualizaEstadoRegistro(Registro registro) {
        Connection connection = null;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   registros  SET  estado=? WHERE id =?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(2, registro.getId());
            statement.setLong(1, Registro.VAR_RESGISTRO_ESTADO_SUSTITUIDO);
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE   registros  SET  estado=" + Registro.VAR_RESGISTRO_ESTADO_SUSTITUIDO + " WHERE id = "
                    + registro.getId());
        } catch (SQLException e) {
            logger.error(" UPDATE   registros  SET  estado=" + Registro.VAR_RESGISTRO_ESTADO_SUSTITUIDO + " WHERE id = "
                    + registro.getId());
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
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
     * Haz cambia estado campos R.
     *
     * @param registro the registro
     * @return true, if successful
     */
    public boolean doCambiaEstadoCamposR(Registro registro) {
        Connection connection = null;
        boolean rowInserted = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   campos_r SET estado=? WHERE registro=? AND estado=?  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Registro.VAR_RESGISTRO_ESTADO_SUSTITUIDO);
            statement.setLong(2, registro.getId());
            statement.setInt(3, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE   campos_r SET estado= " + Registro.VAR_RESGISTRO_ESTADO_SUSTITUIDO
                    + " WHERE registro=" + registro.getId() + " AND estado=  " + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
        } catch (SQLException e) {
            logger.error(" UPDATE   campos_r SET estado= " + Registro.VAR_RESGISTRO_ESTADO_SUSTITUIDO
                    + " WHERE registro=" + registro.getId() + " AND estado=  " + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return rowInserted;
    }

    /**
     * Haz inserta campos R.
     *
     * @param registro the registro. Recibe como parámetro un objeto tipo
     * registro. Todos las clases tipo registro extienden de Registro y puede
     * ser un subtipo de mama, colon, oxigenoterapia, incidencias.
     *
     * Los objetos Bean de estos tipos tienen definido un método que comienza
     * con el profijo getVariableNombreAtributo que devuelve un dato tipo
     * Variable correspondiente a ese atributo.
     *
     * Este método recorre los métodos del objeto y los que su prefijo coinciden
     * con el patrón "getVariable", invoca al método y hace casting al tipo
     * Variable del objeto devuelto.
     *
     * @return true, if successful
     */
    public boolean doInsertaCamposR(Object registro) {

        boolean insertado = true;
        Method[] metodos = registro.getClass().getMethods();
        for (Method m : metodos) {
            if (m.getName().length() > 10) {
                if (m.getName().substring(0, 11).equals("getVariable")) {
                    try {
                        if (m.invoke(registro, null) instanceof Variable) {
                            Variable variable = (Variable) m.invoke(registro, null);
                            if (variable != null) {
                                if (variable.getValor() != null) {
                                    if (!variable.getValor().isEmpty()) {
                                        insertado = doInsertaCamposRBBDD((Registro) registro, variable);
                                    }
                                }
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        logger.error(Constantes.EXCEPTION_ERROR, e);
                    } catch (Exception e) {
                        logger.error(Constantes.EXCEPTION_ERROR, e);
                    }
                }
            }
        }
        return insertado;
    }

    /**
     * Haz inserta campos RBBDD.
     *
     * @param registro the registro
     * @param variable the variable
     * @return true, if successful
     */
    public boolean doInsertaCamposRBBDD(Registro registro, Variable variable) {
        Connection connection = null;
        boolean insertado = false;
        Long idCamposr = null;
        try {

            idCamposr = new UtilidadesDAO().getSiguienteId("campos_r");
            connection = super.getConexionBBDD();
            sql = " INSERT INTO campos_r   (id, registro,item,userid,fecha,hora,descripcion,orden,estado,dato,canal,codigo,unidades )  "
                    + "            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)  ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idCamposr);
            statement.setLong(2, registro.getId());
            statement.setLong(3, variable.getItem());
            statement.setString(4,
                    ((Usuario) VaadinSession.getCurrent().getAttribute(Constantes.SESSION_USERNAME)).getUserid());
            statement.setLong(5, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
            statement.setLong(6, Utilidades.getHoraNumeroAcual());
            statement.setString(7, variable.getDescripcion());
            statement.setLong(8, Registro.ORDEN_DEFECTO); //
            statement.setLong(9, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setString(10, variable.getValor());
            statement.setLong(11, Registro.CANAL_DEFECTO);
            statement.setString(12, variable.getCodigo());
            if (variable.getUnidades() != null) {
                statement.setString(13, variable.getUnidades());
            } else {
                statement.setNull(13, Types.CHAR);
            }
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(
                    " INSERT INTO campos_r   (id, registro,item,userid,fecha,hora,descripcion,orden,estado,dato,canal )  "
                    + "            VALUES (" + idCamposr + "," + registro.getId() + "," + variable.getItem()
                    + ",'"
                    + ((Usuario) VaadinSession.getCurrent().getAttribute(Constantes.SESSION_USERNAME))
                            .getUserid()
                    + "'," + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ","
                    + Utilidades.getHoraNumeroAcual() + ",'" + variable.getDescripcion() + "',"
                    + Registro.ORDEN_DEFECTO + "," + Registro.VAR_RESGISTRO_ESTADO_NORMAL + ",'"
                    + variable.getValor() + "'," + Registro.CANAL_DEFECTO + ")  ");
        } catch (SQLException e) {
            logger.error(
                    " INSERT INTO campos_r   (id, registro,item,userid,fecha,hora,descripcion,orden,estado,dato,canal )  "
                    + "            VALUES (" + idCamposr + "," + registro.getId() + "," + variable.getItem()
                    + ",'"
                    + ((Usuario) VaadinSession.getCurrent().getAttribute(Constantes.SESSION_USERNAME))
                            .getUserid()
                    + "'," + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + ","
                    + Utilidades.getHoraNumeroAcual() + ",'" + variable.getDescripcion() + "',"
                    + Registro.ORDEN_DEFECTO + "," + Registro.VAR_RESGISTRO_ESTADO_NORMAL + ",'"
                    + variable.getValor() + "'," + Registro.CANAL_DEFECTO + ")  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
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
     * Gets the registro por id.
     *
     * @param id the id
     * @return the registro por id
     */
    public Registro getRegistroPorId(Long id) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT r.* FROM registros r  WHERE r.id = ?  AND r.estado=?";
            sql = "SELECT  r.*  "
                    + " ,t.id as idcentro, t.codigo as codigocentro,t.descripcion as descentro, t.nemonico, s.id as idservicio,s.codigo as codigoservicio, s.descripcion as descservicio "
                    + " FROM registros  r JOIN centros t ON t.id=r.centro JOIN servicios s ON s.id=r.servicio "
                    + "  WHERE r.id = ?  AND r.estado=? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            ResultSet resuSulset = statement.executeQuery();
            if (resuSulset.next()) {
                Registro registro = new Registro();
                Centro centroParam = CentroDAO.getCentroRs(resuSulset, null);
                Servicio servicioParam = ServiciosDAO.getServicioRs(resuSulset, null);
                registro = getRegistroResulset(resuSulset, null, null, centroParam, servicioParam, null);
                return registro;
            }
            statement.close();
            logger.debug("SELECT r.* FROM registros r  WHERE r.id = " + id + "  AND r.estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
        } catch (SQLException e) {
            logger.error("SELECT r.* FROM registros r  WHERE r.id = " + id + "  AND r.estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return null;
    }

    /**
     * Gets the registro resulset.
     *
     * @param resuSulset the resu sulset
     * @return the registro resulset
     */
    public Registro getRegistroResulset(ResultSet resuSulset, Paciente paciente, Proceso proceso, Centro centro, Servicio servicio, Usuario usuario) {
        Long id;

        try {
            id = resuSulset.getLong("plantilla_editor");

            if (id.equals(RegistroMama.PLANTILLLA_EDITOR_REGISTROMAMA)) {
                RegistroMama registro = new RegistroMama();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }

                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }

                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                return registro;
            } else if (id.equals(RegistroColon.PLANTILLLA_EDITOR_REGISTROCOLON)) {
                RegistroColon registro = new RegistroColon();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                return registro;
            } else if (id.equals(RegistroOxiAerosol.PLANTILLLA_EDITOR_REGISTROAEROSOL)) {
                RegistroOxiAerosol registro = new RegistroOxiAerosol();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                // registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                return registro;
            } else if (id.equals(RegistroOxiMapnea.PLANTILLLA_EDITOR_REGISTROMAPNEA)) {
                RegistroOxiMapnea registro = new RegistroOxiMapnea();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                return registro;
            } else if (id.equals(RegistroOxiDomi.PLANTILLLA_EDITOR_REGISTRODOMI)) {
                RegistroOxiDomi registro = new RegistroOxiDomi();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                return registro;
            } else if (id.equals(RegistroOxiHipercapnia.PLANTILLLA_EDITOR_REGISTROEQPOYO)) {
                RegistroOxiHipercapnia registro = new RegistroOxiHipercapnia();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //   registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                RegistroOxiHipercapnia registroOxiEapoyo = new RegistroOxiHipercapmiaDAO().getValoresCamposR(registro);
                return registroOxiEapoyo;
            } else if (id.equals(RegistroO2Incidencia.PLANTILLLA_EDITOR_REGISTROINCIDENCIA)) {
                RegistroO2Incidencia registro = new RegistroO2Incidencia();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                return registro;
            } else if (id.equals(RegistroEnfConstantes.PLANTILLLA_EDITOR_ENF_CTES)) {
                RegistroEnfConstantes registro = new RegistroEnfConstantes();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_ENF_CTES_EVA.getItem())) {
                        v = registro.VAR_ENF_CTES_EVA;
                        v.setValor(campo.getDato());
                        registro.setEva(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_FC.getItem())) {
                        v = registro.VAR_ENF_CTES_FC;
                        v.setValor(campo.getDato());
                        registro.setFreCardiaca(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_FR.getItem())) {
                        v = registro.VAR_ENF_CTES_FR;
                        v.setValor(campo.getDato());
                        registro.setFreRespiratorio(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_GLUCEMIA.getItem())) {
                        v = registro.VAR_ENF_CTES_GLUCEMIA;
                        v.setValor(campo.getDato());
                        registro.setGlucemia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_IMC.getItem())) {
                        v = registro.VAR_ENF_CTES_IMC;
                        v.setValor(campo.getDato());
                        registro.setImc(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_IMC.getItem())) {
                        v = registro.VAR_ENF_CTES_IMC;
                        v.setValor(campo.getDato());
                        registro.setImc(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_OBSER.getItem())) {
                        v = registro.VAR_ENF_CTES_OBSER;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_PESO.getItem())) {
                        v = registro.VAR_ENF_CTES_PESO;
                        v.setValor(campo.getDato());
                        registro.setPeso(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_SATO2.getItem())) {
                        v = registro.VAR_ENF_CTES_SATO2;
                        v.setValor(campo.getDato());
                        registro.setSatOxigeno(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_T.getItem())) {
                        v = registro.VAR_ENF_CTES_T;
                        v.setValor(campo.getDato());
                        registro.setTemperatura(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_TAD.getItem())) {
                        v = registro.VAR_ENF_CTES_TAD;
                        v.setValor(campo.getDato());
                        registro.setTad(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_TALLA.getItem())) {
                        v = registro.VAR_ENF_CTES_TALLA;
                        v.setValor(campo.getDato());
                        registro.setTalla(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_TAS.getItem())) {
                        v = registro.VAR_ENF_CTES_TAS;
                        v.setValor(campo.getDato());
                        registro.setTas(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPediatriaRN.PLANTILLLA_EDITOR_ENF_CTES)) {
                RegistroPediatriaRN registro = new RegistroPediatriaRN();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_ENF_CTES_EVA.getItem())) {
                        v = registro.VAR_ENF_CTES_EVA;
                        v.setValor(campo.getDato());
                        registro.setEva(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_FC.getItem())) {
                        v = registro.VAR_ENF_CTES_FC;
                        v.setValor(campo.getDato());
                        registro.setFreCardiaca(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_FR.getItem())) {
                        v = registro.VAR_ENF_CTES_FR;
                        v.setValor(campo.getDato());
                        registro.setFreRespiratorio(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_IMC.getItem())) {
                        v = registro.VAR_ENF_CTES_IMC;
                        v.setValor(campo.getDato());
                        registro.setImc(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_IMC.getItem())) {
                        v = registro.VAR_ENF_CTES_IMC;
                        v.setValor(campo.getDato());
                        registro.setImc(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_OBSER.getItem())) {
                        v = registro.VAR_ENF_CTES_OBSER;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_PESO.getItem())) {
                        v = registro.VAR_ENF_CTES_PESO;
                        v.setValor(campo.getDato());
                        registro.setPeso(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_SATO2.getItem())) {
                        v = registro.VAR_ENF_CTES_SATO2;
                        v.setValor(campo.getDato());
                        registro.setSatOxigeno(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_T.getItem())) {
                        v = registro.VAR_ENF_CTES_T;
                        v.setValor(campo.getDato());
                        registro.setTemperatura(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_TAD.getItem())) {
                        v = registro.VAR_ENF_CTES_TAD;
                        v.setValor(campo.getDato());
                        registro.setTad(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_TALLA.getItem())) {
                        v = registro.VAR_ENF_CTES_TALLA;
                        v.setValor(campo.getDato());
                        registro.setTalla(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CTES_TAS.getItem())) {
                        v = registro.VAR_ENF_CTES_TAS;
                        v.setValor(campo.getDato());
                        registro.setTas(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoGestacion.PLANTILLLA_EDITOR_PAR_GESTACION)) {
                RegistroPartoGestacion registro = new RegistroPartoGestacion();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_GESTA_ALERGIA.getItem())) {
                        v = registro.VAR_PARTO_GESTA_ALERGIA;
                        v.setValor(campo.getDato());
                        registro.setAlergia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_EDAD.getItem())) {
                        v = registro.VAR_PARTO_GESTA_EDAD;
                        v.setValor(campo.getDato());
                        registro.setEdad(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_EGESTAIONAL.getItem())) {
                        v = registro.VAR_PARTO_GESTA_EGESTAIONAL;
                        v.setValor(campo.getDato());
                        registro.setEdadGestacional(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_FPP.getItem())) {
                        v = registro.VAR_PARTO_GESTA_FPP;
                        v.setValor(campo.getDato());
                        registro.setFechaprobableParto(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_FUR.getItem())) {
                        v = registro.VAR_PARTO_GESTA_FUR;
                        v.setValor(campo.getDato());
                        registro.setFechaUltimaRegla(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_GABO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_GABO;
                        v.setValor(campo.getDato());
                        registro.setGrupoABO(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_GEMELAR.getItem())) {
                        v = registro.VAR_PARTO_GESTA_GEMELAR;
                        v.setValor(campo.getDato());
                        registro.setGemelar(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_IMC.getItem())) {
                        v = registro.VAR_PARTO_GESTA_IMC;
                        v.setValor(campo.getDato());
                        registro.setIMC(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_HABITOXALCOHOL.getItem())) {
                        v = registro.VAR_PARTO_GESTA_HABITOXALCOHOL;
                        v.setValor(campo.getDato());
                        registro.setHabitoxAlcohol(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_HABITOXCANABIS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_HABITOXCANABIS;
                        v.setValor(campo.getDato());
                        registro.setHabitoxCanabis(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_HABITOXOROS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_HABITOXOROS;
                        v.setValor(campo.getDato());
                        registro.setHabitoxOtras(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_HABITOXTABACO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_HABITOXTABACO;
                        v.setValor(campo.getDato());
                        registro.setHabitoxTabaco(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_NUMEROFETOS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_NUMEROFETOS;
                        v.setValor(campo.getDato());
                        registro.setNumeroFetos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PARIDAD.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PARIDAD;
                        v.setValor(campo.getDato());
                        registro.setParidad(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_APPE.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_APPE;
                        v.setValor(campo.getDato());
                        registro.setAPPE(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_AR.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_AR;
                        v.setValor(campo.getDato());
                        registro.setAR(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_CANCER.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_CANCER;
                        v.setValor(campo.getDato());
                        registro.setCancer(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_DIABE.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_DIABE;
                        v.setValor(campo.getDato());
                        registro.setPatoMatDiabetesGestional(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_DMP.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_DMP;
                        v.setValor(campo.getDato());
                        registro.setDMG(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_DMPG.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_DMPG;
                        v.setValor(campo.getDato());
                        registro.setDMPG(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_EII.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_EII;
                        v.setValor(campo.getDato());
                        registro.setEII(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_EPILEPSIA.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_EPILEPSIA;
                        v.setValor(campo.getDato());
                        registro.setEpilepsia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_HIPOTIPRE.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_HIPOTIPRE;
                        v.setValor(campo.getDato());
                        registro.setHipotiropre(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_HIPOGESE.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_HIPOGESE;
                        v.setValor(campo.getDato());
                        registro.setHipotiropos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_PLAQUETO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_PLAQUETO;
                        v.setValor(campo.getDato());
                        registro.setPlaquetopenia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_INFECCGES.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_INFECCGES;
                        v.setValor(campo.getDato());
                        registro.setInfeccion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_HTA.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_HTA;
                        v.setValor(campo.getDato());
                        registro.setHTA(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_INSULINA.getItem())) {
                        v = registro.VAR_PARTO_GESTA_INSULINA;
                        v.setValor(campo.getDato());
                        registro.setInsulina(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_INSULINATIEMPO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_INSULINATIEMPO;
                        v.setValor(campo.getDato());
                        registro.setInsulinaTiempo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_DIETAEJERCICIO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_DIETAEJERCICIO;
                        v.setValor(campo.getDato());
                        registro.setDietaejercicio(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_IP_UTERINAS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_IP_UTERINAS;
                        v.setValor(campo.getDato());
                        registro.setIPuterinas_95(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_NO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_NO;
                        v.setValor(campo.getDato());
                        registro.setPatoMatNo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_OTROS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_OTROS;
                        v.setValor(campo.getDato());
                        registro.setPatoMatOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_REUMATOL.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_REUMATOL;
                        v.setValor(campo.getDato());
                        registro.setReumatogicas(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_TROMBO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_TROMBO;
                        v.setValor(campo.getDato());
                        registro.setPatoMatTrombofilia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_MAT_TVP.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_MAT_TVP;
                        v.setValor(campo.getDato());
                        registro.setTVP(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO1_RUBE.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO1_RUBE;
                        v.setValor(campo.getDato());
                        registro.setSero1TRubeola(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO1_SIFI.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO1_SIFI;
                        v.setValor(campo.getDato());
                        registro.setSero1Tsifilis(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO1_TOXO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO1_TOXO;
                        v.setValor(campo.getDato());
                        registro.setSero1TToxo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO1_VHB.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO1_VHB;
                        v.setValor(campo.getDato());
                        registro.setSero1TVHB(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO1_VHC.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO1_VHC;
                        v.setValor(campo.getDato());
                        registro.setSero1TVHC(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO1_VIH.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO1_VIH;
                        v.setValor(campo.getDato());
                        registro.setSero1TVIH(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO3_RUBE.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO3_RUBE;
                        v.setValor(campo.getDato());
                        registro.setSero3TRubeola(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO3_SIFI.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO3_SIFI;
                        v.setValor(campo.getDato());
                        registro.setSero3Tsifilis(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO3_TOXO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO3_TOXO;
                        v.setValor(campo.getDato());
                        registro.setSero3TToxo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO3_VHB.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO3_VHB;
                        v.setValor(campo.getDato());
                        registro.setSero3TVHB(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO3_VHC.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO3_VHC;
                        v.setValor(campo.getDato());
                        registro.setSero3TVHC(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SERO3_VIH.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SERO3_VIH;
                        v.setValor(campo.getDato());
                        registro.setSero3TVIH(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SGB_FECHA.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SGB_FECHA;
                        v.setValor(campo.getDato());
                        registro.setEsteGrupoBFecha(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_SGB_VALOR.getItem())) {
                        v = registro.VAR_PARTO_GESTA_SGB_VALOR;
                        v.setValor(campo.getDato());
                        registro.setEsteGrupoBValor(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_TECREPUASIST.getItem())) {
                        v = registro.VAR_PARTO_GESTA_TECREPUASIST;
                        v.setValor(campo.getDato());
                        registro.setTecReproAsist(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_TRATAMIENTOEMBARAZO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_TRATAMIENTOEMBARAZO;
                        v.setValor(campo.getDato());
                        registro.setTratamientoEmbarazo(v);
                    }

                }
                return registro;
            } else if (id.equals(RegistroPartoGestacionPatFet.PLANTILLLA_EDITOR_PAR_GESTACIONPATFET)) {
                RegistroPartoGestacionPatFet registro = new RegistroPartoGestacionPatFet();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                // registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                //   registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_AMNIO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_AMNIO;
                        v.setValor(campo.getDato());
                        registro.setAmniocentesis(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_BIOCORI.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_BIOCORI;
                        v.setValor(campo.getDato());
                        registro.setBiopsiacorial(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_ECO20.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_ECO20;
                        v.setValor(campo.getDato());
                        registro.setEco20semanas(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_ECO20ALTERA.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_ECO20ALTERA;
                        v.setValor(campo.getDato());
                        registro.setEco20alteracion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO1.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO1;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoCIRTipo1(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO2.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO2;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoCIRTipo2(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO3.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_CRECIMICIRTIPO3;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoCIRTipo3(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_HIDRAMNIOS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_HIDRAMNIOS;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoMalformacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_LIQADMI.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_LIQADMI;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoAlteracionLiq(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_MACROSOMIA.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_MACROSOMIA;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoMacrosomia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_MALFORMACIÓN.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_MALFORMACIÓN;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoMalformacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_NO.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_NO;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoNo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_OLIGOAMNIOS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_OLIGOAMNIOS;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoOligoamnios(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_OTROS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_OTROS;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_PREMATUIDAD.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_PREMATUIDAD;
                        v.setValor(campo.getDato());
                        registro.setPatoFetoPrematuro(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_PEG.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_PEG;
                        v.setValor(campo.getDato());
                        registro.setPatoFetopeg(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_TPN.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_TPN;
                        v.setValor(campo.getDato());
                        registro.setTPN(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_MADURACION.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_MADURACION;
                        v.setValor(campo.getDato());
                        registro.setMaduracionPulmonar(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_MADURACIONSEMANAS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_MADURACIONSEMANAS;
                        v.setValor(campo.getDato());
                        registro.setMaduracionSemanas(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_GESTA_PAT_FET_TOCOLISIS.getItem())) {
                        v = registro.VAR_PARTO_GESTA_PAT_FET_TOCOLISIS;
                        v.setValor(campo.getDato());
                        registro.setTocolisis(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoIngreso.PLANTILLLA_EDITOR_PAR_INGRESO)) {
                RegistroPartoIngreso registro = new RegistroPartoIngreso();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_FECHA.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_FECHA;
                        v.setValor(campo.getDato());
                        registro.setFechaIngreso(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_HORA.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_HORA;
                        v.setValor(campo.getDato());
                        registro.setHoraIngreso(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_OBSERVADOR.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_OBSERVADOR;
                        v.setValor(campo.getDato());
                        registro.setObservador(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_TIPO_INGRESO.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_TIPO_INGRESO;
                        v.setValor(campo.getDato());
                        registro.setTipoIngreso(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_SITUACION.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_SITUACION;
                        v.setValor(campo.getDato());
                        registro.setSituacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_PRESENTACION.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_PRESENTACION;
                        v.setValor(campo.getDato());
                        registro.setPresentacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_BISHOP.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_BISHOP;
                        v.setValor(campo.getDato());
                        registro.setBishop(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_BISHOPALTURA.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_BISHOPALTURA;
                        v.setValor(campo.getDato());
                        registro.setBishopAltura(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_BISHOPBORRAMIENTO.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_BISHOPBORRAMIENTO;
                        v.setValor(campo.getDato());
                        registro.setBishopBorramiento(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_BISHOPCONSCIENCIA.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_BISHOPCONSCIENCIA;
                        v.setValor(campo.getDato());
                        registro.setBishopConsistencia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_BISHOPDILATACION.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_BISHOPDILATACION;
                        v.setValor(campo.getDato());
                        registro.setBishopDilatacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_BISHOPPOSICION.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_BISHOPPOSICION;
                        v.setValor(campo.getDato());
                        registro.setBishopPosicion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_EXTRAHOSPITALARIO.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_EXTRAHOSPITALARIO;
                        v.setValor(campo.getDato());
                        registro.setPartoExtrahospitalario(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_EXTRAHOSPITALARIO.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_EXTRAHOSPITALARIO;
                        v.setValor(campo.getDato());
                        registro.setPartoExtrahospitalario(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_EXTRAHOSPINUMERO.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_EXTRAHOSPINUMERO;
                        v.setValor(campo.getDato());
                        registro.setNumeroExtrahospitalario(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INGRESO_FETOMUERTO.getItem())) {
                        v = registro.VAR_PARTO_INGRESO_FETOMUERTO;
                        v.setValor(campo.getDato());
                        registro.setFetomuerto(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoInduccion.PLANTILLLA_EDITOR_PAR_INDUCCION)) {
                RegistroPartoInduccion registro = new RegistroPartoInduccion();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_INDU_SINO.getItem())) {
                        v = registro.VAR_PARTO_INDU_SINO;
                        v.setValor(campo.getDato());
                        registro.setInduccionSiNo(v);
                    }
                    if (campo.getItem().equals(registro.VAR_PARTO_INDU_FECHA_HORA_ROTA.getItem())) {
                        v = registro.VAR_PARTO_INDU_FECHA_HORA_ROTA;
                        v.setValor(campo.getDato());
                        registro.setFechaHoraBolsaRota(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_PATOMAT.getItem())) {
                        v = registro.VAR_PARTO_INDU_PATOMAT;
                        v.setValor(campo.getDato());
                        registro.setPatologiaMaterna(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_PATOFETCIR.getItem())) {
                        v = registro.VAR_PARTO_INDU_PATOMAT;
                        v.setValor(campo.getDato());
                        registro.setPatologiaFetalCir(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_PATOFETPEG.getItem())) {
                        v = registro.VAR_PARTO_INDU_PATOFETPEG;
                        v.setValor(campo.getDato());
                        registro.setPatologiaFetal(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_ROTURAMAS12.getItem())) {
                        v = registro.VAR_PARTO_INDU_ROTURAMAS12;
                        v.setValor(campo.getDato());
                        registro.setRoturaBolasmas12(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_GESPROLONGADA.getItem())) {
                        v = registro.VAR_PARTO_INDU_GESPROLONGADA;
                        v.setValor(campo.getDato());
                        registro.setGestacionFetalProlongada(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_ALTERACIONLA.getItem())) {
                        v = registro.VAR_PARTO_INDU_ALTERACIONLA;
                        v.setValor(campo.getDato());
                        registro.setAlteracionLiqAmni(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_OTRASINDIC.getItem())) {
                        v = registro.VAR_PARTO_INDU_OTRASINDIC;
                        v.setValor(campo.getDato());
                        registro.setOtrasIndicacionesInduccion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_LIQUIMECONIAL.getItem())) {
                        v = registro.VAR_PARTO_INDU_LIQUIMECONIAL;
                        v.setValor(campo.getDato());
                        registro.setLiquidoMeconal(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_MADURACIONCERVICAL.getItem())) {
                        v = registro.VAR_PARTO_INDU_MADURACIONCERVICAL;
                        v.setValor(campo.getDato());
                        registro.setMaduracioncervical(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_MADURACIONHORAS.getItem())) {
                        v = registro.VAR_PARTO_INDU_MADURACIONHORAS;
                        v.setValor(campo.getDato());
                        registro.setHoras(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_PREPARAOXI.getItem())) {
                        v = registro.VAR_PARTO_INDU_PREPARAOXI;
                        v.setValor(campo.getDato());
                        registro.setPreparacionOxiticica(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_INDU_EVOLUCION.getItem())) {
                        v = registro.VAR_PARTO_INDU_EVOLUCION;
                        v.setValor(campo.getDato());
                        registro.setEvolucion(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoRoturaMembranas.PLANTILLLA_EDITOR_PAR_ROTURAMEMBRANA)) {
                RegistroPartoRoturaMembranas registro = new RegistroPartoRoturaMembranas();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //   registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_ROTURAMEMBRANA_HORASBR.getItem())) {
                        v = registro.VAR_PARTO_ROTURAMEMBRANA_HORASBR;
                        v.setValor(campo.getDato());
                        registro.setHorasbr(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ROTURAMEMBRANA_HORA_ROTURA_BOLSA.getItem())) {
                        v = registro.VAR_PARTO_ROTURAMEMBRANA_HORA_ROTURA_BOLSA;
                        v.setValor(campo.getDato());
                        registro.setHoraRoturaBolsa(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ROTURAMEMBRANA_LIQUAMNI.getItem())) {
                        v = registro.VAR_PARTO_ROTURAMEMBRANA_LIQUAMNI;
                        v.setValor(campo.getDato());
                        registro.setLiqAmioAspceto(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ROTURAMEMBRANA_LIQUAMNICANTI.getItem())) {
                        v = registro.VAR_PARTO_ROTURAMEMBRANA_LIQUAMNICANTI;
                        v.setValor(campo.getDato());
                        registro.setLiqAmioCantidad(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ROTURAMEMBRANA_RM.getItem())) {
                        v = registro.VAR_PARTO_ROTURAMEMBRANA_RM;
                        v.setValor(campo.getDato());
                        registro.setRoturamembranas(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoAlivioDolor.PLANTILLLA_EDITOR_PAR_ALIVIODOLOR)) {
                RegistroPartoAlivioDolor registro = new RegistroPartoAlivioDolor();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_ALIVIODOLORTRASANALGESIA.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_ALIVIODOLORTRASANALGESIA;
                        v.setValor(campo.getDato());
                        registro.setAlivioDolor(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_FARMA.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_FARMA;
                        v.setValor(campo.getDato());
                        registro.setFarmacologico(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_FARMA_AEAG.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_FARMA_AEAG;
                        v.setValor(campo.getDato());
                        registro.setFarmaAEAG(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_FARMA_ANALGESIALOCAL.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_FARMA_ANALGESIALOCAL;
                        v.setValor(campo.getDato());
                        registro.setFarmaAnalgesiaLocal(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_FARMA_ANESGE.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_FARMA_ANESGE;
                        v.setValor(campo.getDato());
                        registro.setFarmaAGeneral(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_FARMA_ANESRA.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_FARMA_ANESRA;
                        v.setValor(campo.getDato());
                        registro.setFarmaARaquidea(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_FARMA_HALOPERIDOL.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_FARMA_HALOPERIDOL;
                        v.setValor(campo.getDato());
                        registro.setFarmaHaloperidol(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_FARMA_DOLANTINA.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_FARMA_DOLANTINA;
                        v.setValor(campo.getDato());
                        registro.setFarmaDolantina(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_CALOR.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_CALOR;
                        v.setValor(campo.getDato());
                        registro.setCalorLocal(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_DUCHA.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_DUCHA;
                        v.setValor(campo.getDato());
                        registro.setDucha(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_EPIDURAL.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_EPIDURAL;
                        v.setValor(campo.getDato());
                        registro.setAepidural(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_LENTEJA.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_LENTEJA;
                        v.setValor(campo.getDato());
                        registro.setLenteja(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_MASAJE.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_MASAJE;
                        v.setValor(campo.getDato());
                        registro.setMasaje(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_MOVIMIENTO.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_MOVIMIENTO;
                        v.setValor(campo.getDato());
                        registro.setMovimiento(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_MUSICO.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_MUSICO;
                        v.setValor(campo.getDato());
                        registro.setMusicoterapia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_PELOTA.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_PELOTA;
                        v.setValor(campo.getDato());
                        registro.setPelota(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_DOLOR_OTROS.getItem())) {
                        v = registro.VAR_PARTO_DOLOR_OTROS;
                        v.setValor(campo.getDato());
                        registro.setOtros(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoEvolucion.PLANTILLLA_EDITOR_PAR_EVOLUCION)) {
                RegistroPartoEvolucion registro = new RegistroPartoEvolucion();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(RegistroPartoEvolucion.DESCRIPCION);
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_CORDONDONACION.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_CORDONDONACION;
                        v.setValor(campo.getDato());
                        registro.setCordonDonacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_CORDONINSERCION.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_CORDONINSERCION;
                        v.setValor(campo.getDato());
                        registro.setCordonInsercion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_CORDONVASOS.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_CORDONVASOS;
                        v.setValor(campo.getDato());
                        registro.setCordonVasos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_CORDONPINZA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_CORDONPINZA;
                        v.setValor(campo.getDato());
                        registro.setCordonPinza(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_HEMORRACANTIDAD.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_HEMORRACANTIDAD;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaCantidad(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_HEMORRASINGOS.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_HEMORRASINGOS;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaSingos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_HEMORRAMAREO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_HEMORRAMAREO;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaMareo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_HEMORRAHIPOTESION.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_HEMORRAHIPOTESION;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaHiptension(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_HEMORRAPERDIDACONO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_HEMORRAPERDIDACONO;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaPerdidaCono(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_HEMORRATAQUIAPNEA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_HEMORRATAQUIAPNEA;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaTaquiaPnea(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_HEMORRATAQUICARDIA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_HEMORRATAQUICARDIA;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaTaquicardia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_HEMORRATRATAMIENTO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_HEMORRATRATAMIENTO;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaTratamiento(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_MEMBRANASFETALES.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_MEMBRANASFETALES;
                        v.setValor(campo.getDato());
                        registro.setMembranasFetales(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_PLACENTA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_PLACENTA;
                        v.setValor(campo.getDato());
                        registro.setPlacenta(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ALUM_TIPOALUMBRA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ALUM_TIPOALUMBRA;
                        v.setValor(campo.getDato());
                        registro.setTipoAlumbramiento(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_CESAREA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_CESAREA;
                        v.setValor(campo.getDato());
                        registro.setCesarea(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_CESAREAINDICACION.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_CESAREAINDICACION;
                        v.setValor(campo.getDato());
                        registro.setCesareaIndicacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_CESAREAOBSERVACIONES.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_CESAREAOBSERVACIONES;
                        v.setValor(campo.getDato());
                        registro.setCesareaObservaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_DISTOCIAHOMBRO_.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_DISTOCIAHOMBRO_;
                        v.setValor(campo.getDato());
                        registro.setDistociaHombros(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_EPISIOTOMIA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_EPISIOTOMIA;
                        v.setValor(campo.getDato());
                        registro.setEpisiotomia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_EXPULAPOYOPLANTAR.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_EXPULAPOYOPLANTAR;
                        v.setValor(campo.getDato());
                        registro.setExpulsivoApoyoPlanar(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_EXPULBIPEDESTACION.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_EXPULBIPEDESTACION;
                        v.setValor(campo.getDato());
                        registro.setExpulsivoBipedestacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_EXPULCUADRUPEDIA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_EXPULCUADRUPEDIA;
                        v.setValor(campo.getDato());
                        registro.setExpulsivoCuadrupedia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_EXPULCUADRUPEDIA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_EXPULCUADRUPEDIA;
                        v.setValor(campo.getDato());
                        registro.setExpulsivoCuadrupedia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_EXPULLATERAL.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_EXPULLATERAL;
                        v.setValor(campo.getDato());
                        registro.setExpulsivoLateral(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_EXPULLITOTOMIA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_EXPULLITOTOMIA;
                        v.setValor(campo.getDato());
                        registro.setExpulsivoLitotomía(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_EXPULSUPINO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_EXPULSUPINO;
                        v.setValor(campo.getDato());
                        registro.setExpulsivoSupino(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_EXPULSENTADA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_EXPULSENTADA;
                        v.setValor(campo.getDato());
                        registro.setExpulsivoSentada(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_FC.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_FC;
                        v.setValor(campo.getDato());
                        registro.setFc(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_FIEBRE_MATERNA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_FIEBRE_MATERNA;
                        v.setValor(campo.getDato());
                        registro.setFiebreMaterna(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_HORASDILATACION.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_HORASDILATACION;
                        v.setValor(campo.getDato());
                        registro.setHorasDilatacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_HORASEXPULSIVO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_HORASEXPULSIVO;
                        v.setValor(campo.getDato());
                        registro.setHorasExpulsivo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_INSTRUMENTAL_.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_INSTRUMENTAL_;
                        v.setValor(campo.getDato());
                        registro.setInstrumental(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_MANIOBRAMACROBERTS.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_MANIOBRAMACROBERTS;
                        v.setValor(campo.getDato());
                        registro.setManiobraMcRoberts(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_MANIOBRAIINIVEL.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_MANIOBRAIINIVEL;
                        v.setValor(campo.getDato());
                        registro.setManiobraIInivel(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_MANIOBRAIIINIVEL.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_MANIOBRAIIINIVEL;
                        v.setValor(campo.getDato());
                        registro.setManiobraIIInivel(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_PHFETAL.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_PHFETAL;
                        v.setValor(campo.getDato());
                        registro.setPhFetal(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_PHFETAL_VALOR.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_PHFETAL_VALOR;
                        v.setValor(campo.getDato());
                        registro.setPhFetalValor(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_T.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_T;
                        v.setValor(campo.getDato());
                        registro.setTemperatura(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TAD.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TAD;
                        v.setValor(campo.getDato());
                        registro.setTad(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TAS.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TAS;
                        v.setValor(campo.getDato());
                        registro.setTas(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_VAGINAL.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_VAGINAL;
                        v.setValor(campo.getDato());
                        registro.setVaginalEutocico(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_VENTOSA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_VENTOSA;
                        v.setValor(campo.getDato());
                        registro.setVaginalVentosa(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_ESPATULAS.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_ESPATULAS;
                        v.setValor(campo.getDato());
                        registro.setVaginalEspatulas(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_FORCESP.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_FORCESP;
                        v.setValor(campo.getDato());
                        registro.setVaginalForceps(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_NUMEROORDENPARTO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_NUMEROORDENPARTO;
                        v.setValor(campo.getDato());
                        registro.setNumeroOrdenParto(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TIPO_REGECOCARDIO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TIPO_REGECOCARDIO;
                        v.setValor(campo.getDato());
                        registro.setRegistroCaridiotocografico(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TIPO_REGECOCARDIOTIPO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TIPO_REGECOCARDIOTIPO;
                        v.setValor(campo.getDato());
                        registro.setTipoRC(v);
                    } else if (campo.getItem()
                            .equals(registro.VAR_PARTO_EVOLU_TIPO_REGECOCARDIOINTERPRETACION.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TIPO_REGECOCARDIOINTERPRETACION;
                        v.setValor(campo.getDato());
                        registro.setInterpretacioRC(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATABALONBAKRI.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATABALONBAKRI;
                        v.setValor(campo.getDato());
                        registro.setTratoBalonBakri(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATABELINCHE.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATABELINCHE;
                        v.setValor(campo.getDato());
                        registro.setTratoBelinche(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATAHAYMAN.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATAHAYMAN;
                        v.setValor(campo.getDato());
                        registro.setTratoHayman(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATAHTOBSTE.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATAHTOBSTE;
                        v.setValor(campo.getDato());
                        registro.setTratoHTobsterica(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATAHEMABATE.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATAHEMABATE;
                        v.setValor(campo.getDato());
                        registro.setTratohemabate(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATALIGADURAHIPO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATALIGADURAHIPO;
                        v.setValor(campo.getDato());
                        registro.setTratoLigadura(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATAMETHERGIN.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATAMETHERGIN;
                        v.setValor(campo.getDato());
                        registro.setTratoMethergin(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATAMISOPROSTOL.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATAMISOPROSTOL;
                        v.setValor(campo.getDato());
                        registro.setTratoMisoprostol(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATAOBSERVA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATAOBSERVA;
                        v.setValor(campo.getDato());
                        registro.setTratoObservaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATAOXITO.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATAOXITO;
                        v.setValor(campo.getDato());
                        registro.setTratoOxitocina(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATASUEROTERA.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATASUEROTERA;
                        v.setValor(campo.getDato());
                        registro.setTratoSueroterapia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_EVOLU_TRATATREDELENBUG.getItem())) {
                        v = registro.VAR_PARTO_EVOLU_TRATATREDELENBUG;
                        v.setValor(campo.getDato());
                        registro.setTratoTrenmdelemburg(v);
                    }
                }
                return registro;
            } /*
				 * else if (id.equals(RegistroPartoExpulsivo.PLANTILLLA_EDITOR_PAR_EXPULSIVO)) {
				 * RegistroPartoExpulsivo registro = new RegistroPartoExpulsivo();
				 * registro.setId(resuSulset.getLong("id"));
				 * registro.setDescripcion(resuSulset.getString("descripcion")); if (paciente ==
				 * null) registro.setPaciente(new
				 * PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"))); else
				 * registro.setPaciente(paciente);
				 * 
				 * registro.setCentro(new
				 * CentroDAO().getRegistroId(resuSulset.getLong("centro")));
				 * registro.setServicio(new
				 * ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
				 * registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
				 * registro.setHora(resuSulset.getLong("hora"));
				 * registro.setEstado(resuSulset.getInt("estado")); registro.setUserid(new
				 * UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
				 * registro.setProblema(new
				 * ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
				 * registro.setUseridredactor(new
				 * Usuario(resuSulset.getString("useridredactor")));
				 * registro.setUseridtranscriptor(new
				 * Usuario(resuSulset.getString("useridredactor")));
				 * registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
				 * registro.setCanal(resuSulset.getLong("canal"));
				 * registro.setListaCampos(getListaCamposRegistro(registro.getId()));
				 * registro.setEpisodio(new
				 * EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
				 * registro.getPaciente(), null, null, null, null, null)); for (Campos_r campo :
				 * registro.getListaCampos()) { Variable v = new Variable(); if
				 * (campo.getItem().equals(registro.VAR_PARTO_EXPUL_HORAS.getItem())) { v =
				 * registro.VAR_PARTO_EXPUL_HORAS; v.setValor(campo.getDato());
				 * registro.setHoras(v); } else if
				 * (campo.getItem().equals(registro.VAR_PARTO_EXPUL_INDICACIONCESAREA.getItem())
				 * ) { v = registro.VAR_PARTO_EXPUL_INDICACIONCESAREA;
				 * v.setValor(campo.getDato()); registro.setIndicacionCesarea(v); } else if
				 * (campo.getItem().equals(registro.VAR_PARTO_EXPUL_INTERPRETACIONRC.getItem()))
				 * { v = registro.VAR_PARTO_EXPUL_INTERPRETACIONRC; v.setValor(campo.getDato());
				 * registro.setInterpretacioRC(v); } else if
				 * (campo.getItem().equals(registro.VAR_PARTO_EXPUL_PHFETAL.getItem())) { v =
				 * registro.VAR_PARTO_EXPUL_PHFETAL; v.setValor(campo.getDato());
				 * registro.setPhFetal(v); } else if
				 * (campo.getItem().equals(registro.VAR_PARTO_EXPUL_T.getItem())) { v =
				 * registro.VAR_PARTO_EXPUL_T; v.setValor(campo.getDato());
				 * registro.setTemperatura(v); } else if
				 * (campo.getItem().equals(registro.VAR_PARTO_EXPUL_TIPOPARTO_.getItem())) { v =
				 * registro.VAR_PARTO_EXPUL_TIPOPARTO_; v.setValor(campo.getDato());
				 * registro.setPartoTipo(v); } } return registro; }
             */ else if (id.equals(RegistroPartoAlumbramiento.PLANTILLLA_EDITOR_PAR_ALUMBRAMIENTO)) {
                RegistroPartoAlumbramiento registro = new RegistroPartoAlumbramiento();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_ALUM_CORDONDONACION.getItem())) {
                        v = registro.VAR_PARTO_ALUM_CORDONDONACION;
                        v.setValor(campo.getDato());
                        registro.setCordonDonacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ALUM_CORDONINSERCION.getItem())) {
                        v = registro.VAR_PARTO_ALUM_CORDONINSERCION;
                        v.setValor(campo.getDato());
                        registro.setCordonInsercion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ALUM_CORDONVASOS.getItem())) {
                        v = registro.VAR_PARTO_ALUM_CORDONVASOS;
                        v.setValor(campo.getDato());
                        registro.setCordonVasos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ALUM_HEMORRACANTIDAD.getItem())) {
                        v = registro.VAR_PARTO_ALUM_HEMORRACANTIDAD;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaCantidad(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ALUM_HEMORRASINGOS.getItem())) {
                        v = registro.VAR_PARTO_ALUM_HEMORRASINGOS;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaSingos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ALUM_HEMORRATRATAMIENTO.getItem())) {
                        v = registro.VAR_PARTO_ALUM_HEMORRATRATAMIENTO;
                        v.setValor(campo.getDato());
                        registro.setHemorragiaTratamiento(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ALUM_MEMBRANASFETALES.getItem())) {
                        v = registro.VAR_PARTO_ALUM_MEMBRANASFETALES;
                        v.setValor(campo.getDato());
                        registro.setMembranasFetales(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ALUM_PLACENTA.getItem())) {
                        v = registro.VAR_PARTO_ALUM_PLACENTA;
                        v.setValor(campo.getDato());
                        registro.setPlacenta(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_ALUM_TIPOPARTO.getItem())) {
                        v = registro.VAR_PARTO_ALUM_TIPOPARTO;
                        v.setValor(campo.getDato());
                        registro.setTipoParto(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoMedicacion.PLANTILLLA_EDITOR_PAR_MEDICAMENTO)) {
                RegistroPartoMedicacion registro = new RegistroPartoMedicacion();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_MEDICA_OXITOCINA.getItem())) {
                        v = registro.VAR_PARTO_MEDICA_OXITOCINA;
                        v.setValor(campo.getDato());
                        registro.setOxitocina(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_MEDICA_ANALGESIAANTIHEMETIC.getItem())) {
                        v = registro.VAR_PARTO_MEDICA_ANALGESIAANTIHEMETIC;
                        v.setValor(campo.getDato());
                        registro.setAnalgesiaAntihemetico(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_MEDICA_CESAREA.getItem())) {
                        v = registro.VAR_PARTO_MEDICA_CESAREA;
                        v.setValor(campo.getDato());
                        registro.setCesarea(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_MEDICA_DIABETES.getItem())) {
                        v = registro.VAR_PARTO_MEDICA_DIABETES;
                        v.setValor(campo.getDato());
                        registro.setDiabetes(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_MEDICA_OTRAS.getItem())) {
                        v = registro.VAR_PARTO_MEDICA_OTRAS;
                        v.setValor(campo.getDato());
                        registro.setOtraMedicacion(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoPuerperio.PLANTILLLA_EDITOR_PAR_PUERPERIO)) {
                RegistroPartoPuerperio registro = new RegistroPartoPuerperio();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_ALTURAUTERO.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_ALTURAUTERO;
                        v.setValor(campo.getDato());
                        registro.setAlturaFondoUtero(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_ANALGESIA.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_ANALGESIA;
                        v.setValor(campo.getDato());
                        registro.setAnalgesia(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_DOLOR.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_DOLOR;
                        v.setValor(campo.getDato());
                        registro.setDolor(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_LACTANCIAM.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_LACTANCIAM;
                        v.setValor(campo.getDato());
                        registro.setLactanciaMaterna(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_MEDICACION.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_MEDICACION;
                        v.setValor(campo.getDato());
                        registro.setMedicacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_MEDICACION_ADMINISTRADA.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_MEDICACION_ADMINISTRADA;
                        v.setValor(campo.getDato());
                        registro.setMedicacionAdministrada(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_PIELPIEL.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_PIELPIEL;
                        v.setValor(campo.getDato());
                        registro.setPielConPiel(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_RECOMENDACIONES.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_RECOMENDACIONES;
                        v.setValor(campo.getDato());
                        registro.setRecomendaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_SANGRADO.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_SANGRADO;
                        v.setValor(campo.getDato());
                        registro.setSangrado(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_TAS.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_TAS;
                        v.setValor(campo.getDato());
                        registro.setTas(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_TAD.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_TAD;
                        v.setValor(campo.getDato());
                        registro.setTad(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_FC.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_FC;
                        v.setValor(campo.getDato());
                        registro.setFc(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PUERPE_DIURESIS.getItem())) {
                        v = registro.VAR_PARTO_PUERPE_DIURESIS;
                        v.setValor(campo.getDato());
                        registro.setDiuresis(v);
                    }

                }
                return registro;
            } else if (id.equals(RegistroPartoBuenasPracticas.PLANTILLLA_EDITOR_PAR_BUENASPRACTICAS)) {
                RegistroPartoBuenasPracticas registro = new RegistroPartoBuenasPracticas();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //   registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_BP_BUENASPRACRN.getItem())) {
                        v = registro.VAR_PARTO_BP_BUENASPRACRN;
                        v.setValor(campo.getDato());
                        registro.setBuenasRN(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_BP_DONACIONC.getItem())) {
                        v = registro.VAR_PARTO_BP_DONACIONC;
                        v.setValor(campo.getDato());
                        registro.setDonaCordon(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_BP_ELECCIONA.getItem())) {
                        v = registro.VAR_PARTO_BP_ELECCIONA;
                        v.setValor(campo.getDato());
                        registro.setAcompanamiento(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_BP_ELECCIONPOST.getItem())) {
                        v = registro.VAR_PARTO_BP_ELECCIONPOST;
                        v.setValor(campo.getDato());
                        registro.setEleccPostura(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_BP_INGESTALIQ.getItem())) {
                        v = registro.VAR_PARTO_BP_INGESTALIQ;
                        v.setValor(campo.getDato());
                        registro.setIngestaLiq(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_BP_MOVIMIENTODILA.getItem())) {
                        v = registro.VAR_PARTO_BP_MOVIMIENTODILA;
                        v.setValor(campo.getDato());
                        registro.setMoviDilatacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_BP_PLANPLARTO.getItem())) {
                        v = registro.VAR_PARTO_BP_PLANPLARTO;
                        v.setValor(campo.getDato());
                        registro.setPlanParto(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_BP_PROTOCOLOACOGIDA.getItem())) {
                        v = registro.VAR_PARTO_BP_PROTOCOLOACOGIDA;
                        v.setValor(campo.getDato());
                        registro.setProtocoloAcogida(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoRecienNacido.PLANTILLLA_EDITOR_PAR_RECIENNACIDO)) {
                RegistroPartoRecienNacido registro = new RegistroPartoRecienNacido();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_RN_APGAR1.getItem())) {
                        v = registro.VAR_PARTO_RN_APGAR1;
                        v.setValor(campo.getDato());
                        registro.setApgar1Puntuacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_APGAR10.getItem())) {
                        v = registro.VAR_PARTO_RN_APGAR10;
                        v.setValor(campo.getDato());
                        registro.setApgar10Puntuacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_APGAR5.getItem())) {
                        v = registro.VAR_PARTO_RN_APGAR5;
                        v.setValor(campo.getDato());
                        registro.setApgar10Puntuacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_COLOR1.getItem())) {
                        v = registro.VAR_PARTO_RN_COLOR1;
                        v.setValor(campo.getDato());
                        registro.setApgar1Color(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_COLOR10.getItem())) {
                        v = registro.VAR_PARTO_RN_COLOR10;
                        v.setValor(campo.getDato());
                        registro.setApgar10Color(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_COLOR5.getItem())) {
                        v = registro.VAR_PARTO_RN_COLOR5;
                        v.setValor(campo.getDato());
                        registro.setApgar5Color(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_CORDONARTERIAL.getItem())) {
                        v = registro.VAR_PARTO_RN_CORDONARTERIAL;
                        v.setValor(campo.getDato());
                        registro.setCordonArterial(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_CORDONVENOSO.getItem())) {
                        v = registro.VAR_PARTO_RN_CORDONVENOSO;
                        v.setValor(campo.getDato());
                        registro.setCordonVenoso(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_CURACORDON.getItem())) {
                        v = registro.VAR_PARTO_RN_CURACORDON;
                        v.setValor(campo.getDato());
                        registro.setCuraCordon(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_FC1.getItem())) {
                        v = registro.VAR_PARTO_RN_FC1;
                        v.setValor(campo.getDato());
                        registro.setApgar1Fc(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_FC10.getItem())) {
                        v = registro.VAR_PARTO_RN_FC10;
                        v.setValor(campo.getDato());
                        registro.setApgar10Fc(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_FC5.getItem())) {
                        v = registro.VAR_PARTO_RN_FC5;
                        v.setValor(campo.getDato());
                        registro.setApgar5Fc(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_FECHANACI.getItem())) {
                        v = registro.VAR_PARTO_RN_FECHANACI;
                        v.setValor(campo.getDato());
                        registro.setFechaNacimiento(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_HORANACI.getItem())) {
                        v = registro.VAR_PARTO_RN_HORANACI;
                        v.setValor(campo.getDato());
                        registro.setHoraNacimiento(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_INGRESONENOTANOS.getItem())) {
                        v = registro.VAR_PARTO_RN_INGRESONENOTANOS;
                        v.setValor(campo.getDato());
                        registro.setIngresNeonatos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_LACTANCIA.getItem())) {
                        v = registro.VAR_PARTO_RN_LACTANCIA;
                        v.setValor(campo.getDato());
                        registro.setLactanciaParitorio(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_MORTALIDAD.getItem())) {
                        v = registro.VAR_PARTO_RN_MORTALIDAD;
                        v.setValor(campo.getDato());
                        registro.setMortalidad(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_NUMEROHC.getItem())) {
                        v = registro.VAR_PARTO_RN_NUMEROHC;
                        v.setValor(campo.getDato());
                        registro.setNumerohc(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_ORDENMEDICA.getItem())) {
                        v = registro.VAR_PARTO_RN_ORDENMEDICA;
                        v.setValor(campo.getDato());
                        registro.setOdenmedica(v);
                    }  else if (campo.getItem().equals(registro.VAR_PARTO_MADRENHC.getItem())) {
                        v = registro.VAR_PARTO_MADRENHC;
                        v.setValor(campo.getDato());
                        registro.setMadrenhc(v);
                    } 
                    else if (campo.getItem().equals(registro.VAR_PARTO_RN_PIELCONPIL.getItem())) {
                        v = registro.VAR_PARTO_RN_PIELCONPIL;
                        v.setValor(campo.getDato());
                        registro.setPielConPiel(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_PCEFALICO.getItem())) {
                        v = registro.VAR_PARTO_RN_PCEFALICO;
                        v.setValor(campo.getDato());
                        registro.setpCefalico(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_PESO.getItem())) {
                        v = registro.VAR_PARTO_RN_PESO;
                        v.setValor(campo.getDato());
                        registro.setPeso(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_PINZAMIENTO.getItem())) {
                        v = registro.VAR_PARTO_RN_PINZAMIENTO;
                        v.setValor(campo.getDato());
                        registro.setPinzaCordon(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_PRIMERMECONIO.getItem())) {
                        v = registro.VAR_PARTO_RN_PRIMERMECONIO;
                        v.setValor(campo.getDato());
                        registro.setPrimerMeconio(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_PRIMERAMICCION.getItem())) {
                        v = registro.VAR_PARTO_RN_PRIMERAMICCION;
                        v.setValor(campo.getDato());
                        registro.setPrimeraMiccion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_PROFIANTIBIO.getItem())) {
                        v = registro.VAR_PARTO_RN_PROFIANTIBIO;
                        v.setValor(campo.getDato());
                        registro.setProfilaxisAntibio(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_PROFILAXISSGB.getItem())) {
                        v = registro.VAR_PARTO_RN_PROFILAXISSGB;
                        v.setValor(campo.getDato());
                        registro.setProfilaxSBG(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_PROFIANTIHEMO.getItem())) {
                        v = registro.VAR_PARTO_RN_PROFIANTIHEMO;
                        v.setValor(campo.getDato());
                        registro.setProfilaxisAntihemo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_PROFIOCULAR.getItem())) {
                        v = registro.VAR_PARTO_RN_PROFIOCULAR;
                        v.setValor(campo.getDato());
                        registro.setProfilaxisOcular(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_REANIMACIONNONATAL.getItem())) {
                        v = registro.VAR_PARTO_RN_REANIMACIONNONATAL;
                        v.setValor(campo.getDato());
                        registro.setReanimacion(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_REFLEJOS1.getItem())) {
                        v = registro.VAR_PARTO_RN_REFLEJOS1;
                        v.setValor(campo.getDato());
                        registro.setApgar1Reflejos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_REFLEJOS10.getItem())) {
                        v = registro.VAR_PARTO_RN_REFLEJOS10;
                        v.setValor(campo.getDato());
                        registro.setApgar10Reflejos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_REFLEJOS5.getItem())) {
                        v = registro.VAR_PARTO_RN_REFLEJOS5;
                        v.setValor(campo.getDato());
                        registro.setApgar5Reflejos(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_RESPIRACION1.getItem())) {
                        v = registro.VAR_PARTO_RN_RESPIRACION1;
                        v.setValor(campo.getDato());
                        registro.setApgar1Respira(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_RESPIRACION10.getItem())) {
                        v = registro.VAR_PARTO_RN_RESPIRACION10;
                        v.setValor(campo.getDato());
                        registro.setApgar10Respira(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_RESPIRACION5.getItem())) {
                        v = registro.VAR_PARTO_RN_RESPIRACION5;
                        v.setValor(campo.getDato());
                        registro.setApgar5Respira(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_SEXO.getItem())) {
                        v = registro.VAR_PARTO_RN_SEXO;
                        v.setValor(campo.getDato());
                        registro.setSexo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_TALLA.getItem())) {
                        v = registro.VAR_PARTO_RN_TALLA;
                        v.setValor(campo.getDato());
                        registro.setTalla(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_TONO1.getItem())) {
                        v = registro.VAR_PARTO_RN_TONO1;
                        v.setValor(campo.getDato());
                        registro.setApgar1Tono(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_TONO10.getItem())) {
                        v = registro.VAR_PARTO_RN_TONO10;
                        v.setValor(campo.getDato());
                        registro.setApgar10Tono(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_RN_TONO5.getItem())) {
                        v = registro.VAR_PARTO_RN_TONO5;
                        v.setValor(campo.getDato());
                        registro.setApgar5Tono(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroPartoProfesionales.PLANTILLLA_EDITOR_PAR_PROFESIONALES)) {
                RegistroPartoProfesionales registro = new RegistroPartoProfesionales();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_ANEST.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_ANEST;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setAnestesista(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_ANEST2.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_ANEST2;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setAnestesista2(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_EIR.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_EIR;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setEir(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_EIR2.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_EIR2;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setEir2(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_GINE.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_GINE;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setGinecologo(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_GINE2.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_GINE2;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setGinecologo2(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_MATRONA.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_MATRONA;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setMatrona(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_MATRONA2.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_MATRONA2;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setMatrona2(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_PEDIA.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_PEDIA;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setPediatra(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_PEDIA2.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_PEDIA2;
                        v.setValor(campo.getDato());
                        registro.setPediatra2(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_TCAE.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_TCAE;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setTcae(v);
                    } else if (campo.getItem().equals(registro.VAR_PARTO_PROFESIO_TCAE2.getItem())) {
                        v = registro.VAR_PARTO_PROFESIO_TCAE2;
                        v.setValor(campo.getDato());
                        v.setCodigo(campo.getCodigo());
                        registro.setTcae2(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroEnfValoracion.PLANTILLLA_EDITOR_ONCO_VALORACION)) {
                RegistroEnfValoracion registro = new RegistroEnfValoracion();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                // registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();

                    if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOACPETAIMAGEN.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOACPETAIMAGEN;
                        v.setValor(campo.getDato());
                        registro.setAutoAceptaImagen(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOSETIMIENTOPOS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOSETIMIENTOPOS;
                        v.setValor(campo.getDato());
                        registro.setAutoSentimientoPos(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOTRANQUILO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOTRANQUILO;
                        v.setValor(campo.getDato());
                        registro.setAutoTranquilo(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOESCALADEME.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOESCALADEME;
                        v.setValor(campo.getDato());
                        registro.setAutoEscalaDME(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOALTERAVISIOPRO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOALTERAVISIOPRO;
                        v.setValor(campo.getDato());
                        registro.setAutoAlteraVisioPropia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOSENTINEGA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOSENTINEGA;
                        v.setValor(campo.getDato());
                        registro.setAutoSentiNegativo(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOVERBALIZAUTON.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOVERBALIZAUTON;
                        v.setValor(campo.getDato());
                        registro.setAutoVerbalizaAuton(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOANGUSTIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOANGUSTIA;
                        v.setValor(campo.getDato());
                        registro.setAutoAngustia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTONERVIOSISMO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTONERVIOSISMO;
                        v.setValor(campo.getDato());
                        registro.setAutoNerviosismo(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOINQUIETUD.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOINQUIETUD;
                        v.setValor(campo.getDato());
                        registro.setAutoInquitud(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_AUTOOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_AUTOOTROS;
                        v.setValor(campo.getDato());
                        registro.setAutoOtros(v);
                    }

                    if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALERGIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALERGIA;
                        v.setValor(campo.getDato());
                        registro.setAlergias(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIALTERAGUSTO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIALTERAGUSTO;
                        v.setValor(campo.getDato());
                        registro.setAliAteraGusto(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIDDIETAHA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIDDIETAHA;
                        v.setValor(campo.getDato());
                        registro.setAliDietaHabitual(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIDIFICULDEGLU.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIDIFICULDEGLU;
                        v.setValor(campo.getDato());
                        registro.setAliDificultadDeglu(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIINGESLIQUIDOS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIINGESLIQUIDOS;
                        v.setValor(campo.getDato());
                        registro.setAliIngestaLiquidos(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIXEROSTOMIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIXEROSTOMIA;
                        v.setValor(campo.getDato());
                        registro.setAliXerostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALILESIONESULCERAS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALILESIONESULCERAS;
                        v.setValor(campo.getDato());
                        registro.setAliLesionUlcera(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIREDUCCIONTEMP.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIREDUCCIONTEMP;
                        v.setValor(campo.getDato());
                        registro.setAliReduccTemp(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIAUMENTOTEMP.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIAUMENTOTEMP;
                        v.setValor(campo.getDato());
                        registro.setAliAumentoTemp(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIEDEMAS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIEDEMAS;
                        v.setValor(campo.getDato());
                        registro.setAliEdemas(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIHERIDAS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIHERIDAS;
                        v.setValor(campo.getDato());
                        registro.setAliHerida(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALINAUSEAS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALINAUSEAS;
                        v.setValor(campo.getDato());
                        registro.setAliNauseas(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIMASTICAFICICUL.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIMASTICAFICICUL;
                        v.setValor(campo.getDato());
                        registro.setAliMasticaDificultad(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIOTROS;
                        v.setValor(campo.getDato());
                        registro.setAliOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIPIELINTE.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIPIELINTE;
                        v.setValor(campo.getDato());
                        registro.setAliPielIntegra(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIPROTESIS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIPROTESIS;
                        v.setValor(campo.getDato());
                        registro.setAliProtesisDental(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALISUPLEMENTO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALISUPLEMENTO;
                        v.setValor(campo.getDato());
                        registro.setAliSuplemento(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALITEMPERATURA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALITEMPERATURA;
                        v.setValor(campo.getDato());
                        registro.setAliTemperaturaNormal(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ALIVOMITOS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ALIVOMITOS;
                        v.setValor(campo.getDato());
                        registro.setAliVomitos(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ANTECEDENTES.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ANTECEDENTES;
                        v.setValor(campo.getDato());
                        registro.setAntecedentes(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ACCESOVENOSO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ACCESOVENOSO;
                        v.setValor(campo.getDato());
                        registro.setAccesoVenoso(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_APRECOMPREDE.getItem())) {
                        v = registro.VAR_ONCO_VALORA_APRECOMPREDE;
                        v.setValor(campo.getDato());
                        registro.setAprenComprende(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_APREDEMANDA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_APREDEMANDA;
                        v.setValor(campo.getDato());
                        registro.setAprenDemanda(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_CICLOS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_CICLOS;
                        v.setValor(campo.getDato());
                        registro.setCliclos(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COMUACOPA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COMUACOPA;
                        v.setValor(campo.getDato());
                        registro.setComuAcompanado(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COMUCASEXU.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COMUCASEXU;
                        v.setValor(campo.getDato());
                        registro.setComuCambioSexual(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COMUCOLABORA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COMUCOLABORA;
                        v.setValor(campo.getDato());
                        registro.setComuColabora(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COMUCONSPI.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COMUCONSPI;
                        v.setValor(campo.getDato());
                        registro.setComuConspiraSilen(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COMUOTRACOMPA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COMUOTRACOMPA;
                        v.setValor(campo.getDato());
                        registro.setComuOtraCompa(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COMUOTRO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COMUOTRO;
                        v.setValor(campo.getDato());
                        registro.setComuOtro(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COMURELFAM.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COMURELFAM;
                        v.setValor(campo.getDato());
                        registro.setComuRelacionFam(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COMURELPER.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COMURELPER;
                        v.setValor(campo.getDato());
                        registro.setComuRelacionPer(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_TRATAHABITUAL.getItem())) {
                        v = registro.VAR_ONCO_VALORA_TRATAHABITUAL;
                        v.setValor(campo.getDato());
                        registro.setTratamientoHabitual(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_DIAGNOSTICO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_DIAGNOSTICO;
                        v.setValor(campo.getDato());
                        registro.setDiagnostico(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COGNAPRENDIZAJE.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COGNAPRENDIZAJE;
                        v.setValor(campo.getDato());
                        registro.setCognAprendizaje(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COGNCONSCIENTE.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COGNCONSCIENTE;
                        v.setValor(campo.getDato());
                        registro.setCognConsciente(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COGNCONSCIENTEENFERMEDAD.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COGNCONSCIENTEENFERMEDAD;
                        v.setValor(campo.getDato());
                        registro.setCognConscienteEnfermdad(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COGNCONOCEEFERMEDAD.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COGNCONOCEEFERMEDAD;
                        v.setValor(campo.getDato());
                        registro.setCognConoceEnfermdad(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COGNVERVALIZADOLOR.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COGNVERVALIZADOLOR;
                        v.setValor(campo.getDato());
                        registro.setCognVerBalizadolor(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COGNEVIDENCIADOLOR.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COGNEVIDENCIADOLOR;
                        v.setValor(campo.getDato());
                        registro.setCognEvidenciaDolor(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COGNDESORIENTACION.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COGNDESORIENTACION;
                        v.setValor(campo.getDato());
                        registro.setCognDesorientacion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COGNDOLOREVA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COGNDOLOREVA;
                        v.setValor(campo.getDato());
                        registro.setCognDolorEva(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_COGNDOLORLOCA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_COGNDOLORLOCA;
                        v.setValor(campo.getDato());
                        registro.setCognDolorLocalizacion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIFECALDIARREA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIFECALDIARREA;
                        v.setValor(campo.getDato());
                        registro.setElifecalDiarrea(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIFECALESTRENIMIENTO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIFECALESTRENIMIENTO;
                        v.setValor(campo.getDato());
                        registro.setElifecalEstrenimiento(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIFECALOSTOMIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIFECALOSTOMIA;
                        v.setValor(campo.getDato());
                        registro.setElifecalOstomia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIFECALOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIFECALOTROS;
                        v.setValor(campo.getDato());
                        registro.setElifecalOtro(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURICANTIDAD.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURICANTIDAD;
                        v.setValor(campo.getDato());
                        registro.setEliuriCantidad(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURICOLOR.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURICOLOR;
                        v.setValor(campo.getDato());
                        registro.setEliuriColor(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIESCOZOR.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIESCOZOR;
                        v.setValor(campo.getDato());
                        registro.setEliuriEscozor(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURINEFROSTOMIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURINEFROSTOMIA;
                        v.setValor(campo.getDato());
                        registro.setEliNefrostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIOTROS;
                        v.setValor(campo.getDato());
                        registro.setEliuriOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURISV.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURISV;
                        v.setValor(campo.getDato());
                        registro.setEliuriPortadorSV(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIUROSTOMIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIUROSTOMIA;
                        v.setValor(campo.getDato());
                        registro.setEliUrostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_EMOCIANSIEDAD.getItem())) {
                        v = registro.VAR_ONCO_VALORA_EMOCIANSIEDAD;
                        v.setValor(campo.getDato());
                        registro.setEmocioAnsiedad(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIINTESTINORMAL.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIINTESTINORMAL;
                        v.setValor(campo.getDato());
                        registro.setEliIntesNormal(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURINARIANORMAL.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURINARIANORMAL;
                        v.setValor(campo.getDato());
                        registro.setEliUrinaNormal(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELICOLOSTOMIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELICOLOSTOMIA;
                        v.setValor(campo.getDato());
                        registro.setEliColostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIILEOSTOMIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIILEOSTOMIA;
                        v.setValor(campo.getDato());
                        registro.setEliIleostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELICONTINENCIAINSTESTINAL.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELICONTINENCIAINSTESTINAL;
                        v.setValor(campo.getDato());
                        registro.setEliContiIntes(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELICONTINENCIAURINARIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELICONTINENCIAURINARIA;
                        v.setValor(campo.getDato());
                        registro.setEliContiUrinaria(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIDISMINCIONFRECUENCIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIDISMINCIONFRECUENCIA;
                        v.setValor(campo.getDato());
                        registro.setEliuriDisminucionF(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIHECESDURAS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIHECESDURAS;
                        v.setValor(campo.getDato());
                        registro.setElifecalHecesduras(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIDIFICULTOSA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIDIFICULTOSA;
                        v.setValor(campo.getDato());
                        registro.setElifecalDificultosa(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIINCAPACIDAD.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIINCAPACIDAD;
                        v.setValor(campo.getDato());
                        registro.setElifecalIncapacidaHeces(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIGOTEO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIGOTEO;
                        v.setValor(campo.getDato());
                        registro.setEliuriGoteo(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIDOLORABDOMINAL.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIDOLORABDOMINAL;
                        v.setValor(campo.getDato());
                        registro.setElifecalDolorAbdominal(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIURGENCIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIURGENCIA;
                        v.setValor(campo.getDato());
                        registro.setEliuriUrgencia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURIDISTENSION.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURIDISTENSION;
                        v.setValor(campo.getDato());
                        registro.setEliuriUrgencia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURI3VESDIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURI3VESDIA;
                        v.setValor(campo.getDato());
                        registro.setElifecal3deposiciones(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_ELIURISENSACIONPELNI.getItem())) {
                        v = registro.VAR_ONCO_VALORA_ELIURISENSACIONPELNI;
                        v.setValor(campo.getDato());
                        registro.setElifecalPlenitud(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_EMOCIDEPRE.getItem())) {
                        v = registro.VAR_ONCO_VALORA_EMOCIDEPRE;
                        v.setValor(campo.getDato());
                        registro.setEmocioDepresion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_EMOCIPAD_T.getItem())) {
                        v = registro.VAR_ONCO_VALORA_EMOCIPAD_T;
                        v.setValor(campo.getDato());
                        registro.setEmocioPAD_T(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_FFIN.getItem())) {
                        v = registro.VAR_ONCO_VALORA_FFIN;
                        v.setValor(campo.getDato());
                        registro.setFechaFin(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_FINICIO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_FINICIO;
                        v.setValor(campo.getDato());
                        registro.setFechaInicio(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_MOVILIZA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_MOVILIZA;
                        v.setValor(campo.getDato());
                        registro.setMovilizacion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_MOVILIZAKARNOFSKY.getItem())) {
                        v = registro.VAR_ONCO_VALORA_MOVILIZAKARNOFSKY;
                        v.setValor(campo.getDato());
                        registro.setKarnofsky(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_MOVILIZAOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_MOVILIZAOTROS;
                        v.setValor(campo.getDato());
                        registro.setMovilizacionOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PERCEPALOPE.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PERCEPALOPE;
                        v.setValor(campo.getDato());
                        registro.setPercepAlopecia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PERCEPAUTOESTIMA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PERCEPAUTOESTIMA;
                        v.setValor(campo.getDato());
                        registro.setPercepAutoestima(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PERCEPCOLOST.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PERCEPCOLOST;
                        v.setValor(campo.getDato());
                        registro.setPercepColostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PERCEPIMAGEN.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PERCEPIMAGEN;
                        v.setValor(campo.getDato());
                        registro.setPercepImagen(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PERCEPMASTEC.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PERCEPMASTEC;
                        v.setValor(campo.getDato());
                        registro.setPercepMastectomia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PERCEPOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PERCEPOTROS;
                        v.setValor(campo.getDato());
                        registro.setPercepOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELEDEMAS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELEDEMAS;
                        v.setValor(campo.getDato());
                        registro.setPielEdemas(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELERITEMA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELERITEMA;
                        v.setValor(campo.getDato());
                        registro.setPielEritema(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELFLEBITIS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELFLEBITIS;
                        v.setValor(campo.getDato());
                        registro.setPielFlebitis(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELHEMATOMA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELHEMATOMA;
                        v.setValor(campo.getDato());
                        registro.setPielHematomas(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELHERIDA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELHERIDA;
                        v.setValor(campo.getDato());
                        registro.setPielHeridas(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELHIPERSENSI.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELHIPERSENSI;
                        v.setValor(campo.getDato());
                        registro.setPielHeridas(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELICTERICIA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELICTERICIA;
                        v.setValor(campo.getDato());
                        registro.setPielHeridas(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELNOALTERA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELNOALTERA;
                        v.setValor(campo.getDato());
                        registro.setPielNoAlteracion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELOTROS;
                        v.setValor(campo.getDato());
                        registro.setPielOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELPALIDEZ.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELPALIDEZ;
                        v.setValor(campo.getDato());
                        registro.setPielPalidez(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELPRURITO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELPRURITO;
                        v.setValor(campo.getDato());
                        registro.setPielPrurito(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_PIELSECA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_PIELSECA;
                        v.setValor(campo.getDato());
                        registro.setPielSeca(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIAUTOHIGIEN.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIAUTOHIGIEN;
                        v.setValor(campo.getDato());
                        registro.setRespiAutoHigiene(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPICIANOSIS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPICIANOSIS;
                        v.setValor(campo.getDato());
                        registro.setRespiCianosis(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIDISNEA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIDISNEA;
                        v.setValor(campo.getDato());
                        registro.setRespidisnea(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIESPUTO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIESPUTO;
                        v.setValor(campo.getDato());
                        registro.setRespiEsputoEsce(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIFATIGA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIFATIGA;
                        v.setValor(campo.getDato());
                        registro.setRespiFatigaDebili(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIKARNOSFSKY.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIKARNOSFSKY;
                        v.setValor(campo.getDato());
                        registro.setRespiKarnofsky(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIMOVI.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIMOVI;
                        v.setValor(campo.getDato());
                        registro.setRespiMoviDescoordin(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIOTROS;
                        v.setValor(campo.getDato());
                        registro.setRespiOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIOXIGENO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIOXIGENO;
                        v.setValor(campo.getDato());
                        registro.setRespiOxigeno(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIPIELROSADA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIPIELROSADA;
                        v.setValor(campo.getDato());
                        registro.setRespiPielMucoRosa(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPIRITMONORMAL.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPIRITMONORMAL;
                        v.setValor(campo.getDato());
                        registro.setRespiRitmoNormal(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPISINALTE.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPISINALTE;
                        v.setValor(campo.getDato());
                        registro.setRespiNoAlteracion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPISONIDOSA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPISONIDOSA;
                        v.setValor(campo.getDato());
                        registro.setRespiSonidosNormales(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPITOLERAAVD.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPITOLERAAVD;
                        v.setValor(campo.getDato());
                        registro.setRespiTolreaAVD(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_RESPITOSINEFI.getItem())) {
                        v = registro.VAR_ONCO_VALORA_RESPITOSINEFI;
                        v.setValor(campo.getDato());
                        registro.setRespiTosinefi(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_REPOSUEINFUSIONES.getItem())) {
                        v = registro.VAR_ONCO_VALORA_REPOSUEINFUSIONES;
                        v.setValor(campo.getDato());
                        registro.setRepoSueInfusiones(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_REPOSUEMEDICACION.getItem())) {
                        v = registro.VAR_ONCO_VALORA_REPOSUEMEDICACION;
                        v.setValor(campo.getDato());
                        registro.setRepoSueMedicacion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_REPOSUENO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_REPOSUENO;
                        v.setValor(campo.getDato());
                        registro.setRepoSueno(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_REPOSUEOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_REPOSUEOTROS;
                        v.setValor(campo.getDato());
                        registro.setRepoSueOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_SEGACCESO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_SEGACCESO;
                        v.setValor(campo.getDato());
                        registro.setSegAccesoTipo(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_SEGACCESOLOCA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_SEGACCESOLOCA;
                        v.setValor(campo.getDato());
                        registro.setSegAccesoLoca(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_SEGACCESOPERME.getItem())) {
                        v = registro.VAR_ONCO_VALORA_SEGACCESOPERME;
                        v.setValor(campo.getDato());
                        registro.setSegAccesoPermeable(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_SITSOCIALFAMILIAR.getItem())) {
                        v = registro.VAR_ONCO_VALORA_SITSOCIALFAMILIAR;
                        v.setValor(campo.getDato());
                        registro.setSitFamiliarSocial(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_TELEFONO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_TELEFONO;
                        v.setValor(campo.getDato());
                        registro.setTelefono(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_TRATAMIENTOQT.getItem())) {
                        v = registro.VAR_ONCO_VALORA_TRATAMIENTOQT;
                        v.setValor(campo.getDato());
                        registro.setTratamientoqt(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_VESTIDO.getItem())) {
                        v = registro.VAR_ONCO_VALORA_VESTIDO;
                        v.setValor(campo.getDato());
                        registro.setVestidoAseo(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_VESTIDOOTROS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_VESTIDOOTROS;
                        v.setValor(campo.getDato());
                        registro.setVestidoAseoOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_SEXUVERBALIZA.getItem())) {
                        v = registro.VAR_ONCO_VALORA_SEXUVERBALIZA;
                        v.setValor(campo.getDato());
                        registro.setSexuVerbalizaProblema(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_SEXUINCAPACIDADSASTI.getItem())) {
                        v = registro.VAR_ONCO_VALORA_SEXUINCAPACIDADSASTI;
                        v.setValor(campo.getDato());
                        registro.setSexuIncapacidadSastis(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_SEXULIMITACIONES.getItem())) {
                        v = registro.VAR_ONCO_VALORA_SEXULIMITACIONES;
                        v.setValor(campo.getDato());
                        registro.setSexuLimitaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_VALORA_SEXUOTRAS.getItem())) {
                        v = registro.VAR_ONCO_VALORA_SEXUOTRAS;
                        v.setValor(campo.getDato());
                        registro.setSexuOtros(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroEnfEvolutivo.PLANTILLLA_EDITOR_ONCO_EVOLUTI)) {
                RegistroEnfEvolutivo registro = new RegistroEnfEvolutivo();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_ONCO_EVOL_OBSER.getItem())) {
                        v = registro.VAR_ONCO_EVOL_OBSER;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroOncoCuidados.PLANTILLLA_EDITOR_ONCO_CUIDA)) {
                RegistroOncoCuidados registro = new RegistroOncoCuidados();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_ACCESO.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_ACCESO;
                        v.setValor(campo.getDato());
                        registro.setAccesoVenoso(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_ACCESOLOCA.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_ACCESOLOCA;
                        v.setValor(campo.getDato());
                        registro.setAccesoLocalizacion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_ALERGIA.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_ALERGIA;
                        v.setValor(campo.getDato());
                        registro.setReaccAlergica(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_EFECTOSADVERSO.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_EFECTOSADVERSO;
                        v.setValor(campo.getDato());
                        registro.setInfoPaEfectos(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_EXTRACCION.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_EXTRACCION;
                        v.setValor(campo.getDato());
                        registro.setAccesoExtraccion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_EXTRADESCRI.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_EXTRADESCRI;
                        v.setValor(campo.getDato());
                        registro.setAccesoExtraDescri(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_EXTRASVA.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_EXTRASVA;
                        v.setValor(campo.getDato());
                        registro.setExtravasacion(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_FC.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_FC;
                        v.setValor(campo.getDato());
                        registro.setFreCardiaca(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_FLEBI.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_FLEBI;
                        v.setValor(campo.getDato());
                        registro.setFlebitis(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_GLU.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_GLU;
                        v.setValor(campo.getDato());
                        registro.setGlucosa(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_INFOPROCE.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_INFOPROCE;
                        v.setValor(campo.getDato());
                        registro.setInfoPaProcedimiento(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_NAUSEAS.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_NAUSEAS;
                        v.setValor(campo.getDato());
                        registro.setNauseas(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_OBSERVACIONES.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_OBSERVACIONES;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_OBSTRUC.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_OBSTRUC;
                        v.setValor(campo.getDato());
                        registro.setObstruccionCate(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_OTRASCOMPL.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_OTRASCOMPL;
                        v.setValor(campo.getDato());
                        registro.setOtrasComplica(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_SAT02.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_SAT02;
                        v.setValor(campo.getDato());
                        registro.setSatOxigeno(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_SIGNOSALARMA.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_SIGNOSALARMA;
                        v.setValor(campo.getDato());
                        registro.setInfoPaSigAlarma(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_T.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_T;
                        v.setValor(campo.getDato());
                        registro.setTemperatura(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_TAD.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_TAD;
                        v.setValor(campo.getDato());
                        registro.setTemperatura(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_TAS.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_TAS;
                        v.setValor(campo.getDato());
                        registro.setTas(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_TTODOSIS.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_TTODOSIS;
                        v.setValor(campo.getDato());
                        registro.setTtoDosis(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_TTOHORAINI.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_TTOHORAINI;
                        v.setValor(campo.getDato());
                        registro.setTtoHoraini(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_TTOVIA.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_TTOVIA;
                        v.setValor(campo.getDato());
                        registro.setTtoVia(v);
                    } else if (campo.getItem().equals(registro.VAR_ONCO_CUIDADOS_VOMITOS.getItem())) {
                        v = registro.VAR_ONCO_CUIDADOS_VOMITOS;
                        v.setValor(campo.getDato());
                        registro.setVomitos(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroEvolutivoMedico.PLANTILLLA_EDITOR_EVOLUTIVOMEDICO)) {
                RegistroEvolutivoMedico registro = new RegistroEvolutivoMedico();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_EVOLUTIVO_SITACTU.getItem())) {
                        v = registro.VAR_EVOLUTIVO_SITACTU;
                        v.setValor(campo.getDato());
                        registro.setSitActual(v);
                    } else if (campo.getItem().equals(registro.VAR_EVOLUTIVO_TRATAMIENTO.getItem())) {
                        v = registro.VAR_EVOLUTIVO_TRATAMIENTO;
                        v.setValor(campo.getDato());
                        registro.setTratamiento(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroQuiSegPre.PLANTILLLA_EDITOR_QUISEGPRE)) {
                RegistroQuiSegPre registro = new RegistroQuiSegPre();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_QUISEG_PREQUI_APARED.getItem())) {
                        v = registro.VAR_QUISEG_PREQUI_APARED;
                        v.setValor(campo.getDato());
                        registro.setAparatosred(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PREQUI_ASPIRADOR.getItem())) {
                        v = registro.VAR_QUISEG_PREQUI_ASPIRADOR;
                        v.setValor(campo.getDato());
                        registro.setAspirador(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PREQUI_AUXILIAR_.getItem())) {
                        v = registro.VAR_QUISEG_PREQUI_AUXILIAR_;
                        v.setValor(campo.getDato());
                        registro.setAuxiliar(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PREQUI_BOLSAPRE.getItem())) {
                        v = registro.VAR_QUISEG_PREQUI_BOLSAPRE;
                        v.setValor(campo.getDato());
                        registro.setBolsapresion(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PREQUI_FECHA.getItem())) {
                        v = registro.VAR_QUISEG_PREQUI_FECHA;
                        v.setValor(campo.getDato());
                        registro.setFechaCheck(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PREQUI_HORA.getItem())) {
                        v = registro.VAR_QUISEG_PREQUI_HORA;
                        v.setValor(campo.getDato());
                        registro.setHoraCheck(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PREQUI_INTUBA.getItem())) {
                        v = registro.VAR_QUISEG_PREQUI_INTUBA;
                        v.setValor(campo.getDato());
                        registro.setIntubacion(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PREQUI_MEDICA.getItem())) {
                        v = registro.VAR_QUISEG_PREQUI_MEDICA;
                        v.setValor(campo.getDato());
                        registro.setMedicacion(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroQuiSegEntrada.PLANTILLLA_EDITOR_QUISEGENTRA)) {
                RegistroQuiSegEntrada registro = new RegistroQuiSegEntrada();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_ALERGIA.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_ALERGIA;
                        v.setValor(campo.getDato());
                        registro.setAlergia(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_ALERGIAOKCORR.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_ALERGIAOKCORR;
                        v.setValor(campo.getDato());
                        registro.setAlergiaOkcorre(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_ANTIBIO.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_ANTIBIO;
                        v.setValor(campo.getDato());
                        registro.setAntibioti60min(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_ANTIBIONOPRO.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_ANTIBIO;
                        v.setValor(campo.getDato());
                        registro.setAntibiotiNoporce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_ANTIBIOOKCORR.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_ANTIBIOOKCORR;
                        v.setValor(campo.getDato());
                        registro.setAlergiaOkcorre(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_HEMO.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_HEMO;
                        v.setValor(campo.getDato());
                        registro.setHemoderiv(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_HEMONOPRO.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_HEMONOPRO;
                        v.setValor(campo.getDato());
                        registro.setHemoderivNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_HEMOOKCORR.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_HEMOOKCORR;
                        v.setValor(campo.getDato());
                        registro.setAntibiotiOkcorre(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_IDENTIDAD.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_IDENTIDAD;
                        v.setValor(campo.getDato());
                        registro.setIdentidad(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_INPLAN.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_INPLAN;
                        v.setValor(campo.getDato());
                        registro.setImplante(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_INPLANNOPRO.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_INPLANNOPRO;
                        v.setValor(campo.getDato());
                        registro.setImplanteNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_INPLANOKCORR.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_INPLANOKCORR;
                        v.setValor(campo.getDato());
                        registro.setImplanteOkcorre(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_INTU.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_INTU;
                        v.setValor(campo.getDato());
                        registro.setIntuDificil(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_INTUNOPROC.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_INTUNOPROC;
                        v.setValor(campo.getDato());
                        registro.setIntuDificilNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_INTUPULXI.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_INTUPULXI;
                        v.setValor(campo.getDato());
                        registro.setPulsioxi(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_INTURIESASPI.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_INTURIESASPI;
                        v.setValor(campo.getDato());
                        registro.setRiesgoAspira(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_LOCALI.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_LOCALI;
                        v.setValor(campo.getDato());
                        registro.setLocalizacion(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_LOCALIDANOPRO.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_LOCALIDANOPRO;
                        v.setValor(campo.getDato());
                        registro.setLocalizacionNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_LOCALIOKCORR.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_LOCALIOKCORR;
                        v.setValor(campo.getDato());
                        registro.setLocalizacionOkcorr(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_OBSSER.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_OBSSER;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_PROCEDIMIENTO.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_PROCEDIMIENTO;
                        v.setValor(campo.getDato());
                        registro.setProcedimiento(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_TEVTVP.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_TEVTVP;
                        v.setValor(campo.getDato());
                        registro.setTep_tvp(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_TEVTVPNOPRO.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_TEVTVPNOPRO;
                        v.setValor(campo.getDato());
                        registro.setTep_tvpNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_ENTRADA_TEVTVPOKCORR.getItem())) {
                        v = registro.VAR_QUISEG_ENTRADA_TEVTVPOKCORR;
                        v.setValor(campo.getDato());
                        registro.setTep_tvpOkcorre(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroQuiSegPausa.PLANTILLLA_EDITOR_QUISEGPAUSA)) {
                RegistroQuiSegPausa registro = new RegistroQuiSegPausa();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_EQUIPO.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_EQUIPO;
                        v.setValor(campo.getDato());
                        registro.setEquipos(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_EQUIPOOKCORR.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_EQUIPO;
                        v.setValor(campo.getDato());
                        registro.setEquiposOkCoree(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_GLUCE.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_GLUCE;
                        v.setValor(campo.getDato());
                        registro.setGlucema(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_GLUNOPRO.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_GLUNOPRO;
                        v.setValor(campo.getDato());
                        registro.setGlucemaNoProce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_HIPONOPROCE.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_HIPONOPROCE;
                        v.setValor(campo.getDato());
                        registro.setHiptermiaNoProce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_HIPOTER.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_HIPOTER;
                        v.setValor(campo.getDato());
                        registro.setHiptermia(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_IMAGENES.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_IMAGENES;
                        v.setValor(campo.getDato());
                        registro.setImagenes(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_INSTRU.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_INSTRU;
                        v.setValor(campo.getDato());
                        registro.setInstrumental(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_INTRUOKCORR.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_INTRUOKCORR;
                        v.setValor(campo.getDato());
                        registro.setInstrumentalOkcorre(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_OBSERVA.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_OBSERVA;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_PASOCRIANES.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_PASOCRIANES;
                        v.setValor(campo.getDato());
                        registro.setPasosCriticosAnes(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_PASOSCRICIR.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_PASOSCRICIR;
                        v.setValor(campo.getDato());
                        registro.setPasosCriticos(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_PROCEPOS.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_PROCEPOS;
                        v.setValor(campo.getDato());
                        registro.setProcePosicion(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_PAUSA_VIAAERE.getItem())) {
                        v = registro.VAR_QUISEG_PAUSA_VIAAERE;
                        v.setValor(campo.getDato());
                        registro.setViaAerea(v);
                    }

                }
                return registro;
            } else if (id.equals(RegistroQuiSegSalida.PLANTILLLA_EDITOR_QUISEGSALIDA)) {
                RegistroQuiSegSalida registro = new RegistroQuiSegSalida();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //   registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_ANTIBIO.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_ANTIBIO;
                        v.setValor(campo.getDato());
                        registro.setProfiAntibi(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_ANTIBIONOPROCE.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_ANTIBIONOPROCE;
                        v.setValor(campo.getDato());
                        registro.setProfiAntibioNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_CONTAJE.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_CONTAJE;
                        v.setValor(campo.getDato());
                        registro.setContaje(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_FINCHECK.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_FINCHECK;
                        v.setValor(campo.getDato());
                        registro.setCheckFinalizado(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_GLUCEMIA.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_GLUCEMIA;
                        v.setValor(campo.getDato());
                        registro.setGlucemiaRealizado(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_GLUCEMIANOPROCE.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_GLUCEMIANOPROCE;
                        v.setValor(campo.getDato());
                        registro.setGlucemiaNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_HIPOTER.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_HIPOTER;
                        v.setValor(campo.getDato());
                        registro.setHipotermiaRealizado(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_HIPOTERNOPROCE.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_HIPOTERNOPROCE;
                        v.setValor(campo.getDato());
                        registro.setHipotermiaNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_IDENMUESTR.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_IDENMUESTR;
                        v.setValor(campo.getDato());
                        registro.setIdentiMuestras(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_IDENMUESTRNOPROCE.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_IDENMUESTRNOPROCE;
                        v.setValor(campo.getDato());
                        registro.setIdentiMuestrasNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_NOMBREPRO.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_NOMBREPRO;
                        v.setValor(campo.getDato());
                        registro.setNombreProce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_OBSERVA.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_OBSERVA;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_PASOSCRIT.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_PASOSCRIT;
                        v.setValor(campo.getDato());
                        registro.setPasosCritRecu(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_TROBONOPROCE.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_TROBONOPROCE;
                        v.setValor(campo.getDato());
                        registro.setProfiTromboNoproce(v);
                    } else if (campo.getItem().equals(registro.VAR_QUISEG_SALIDA_TROMBO.getItem())) {
                        v = registro.VAR_QUISEG_SALIDA_TROMBO;
                        v.setValor(campo.getDato());
                        registro.setProfiTrombo(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroQuiHojaCirculaPre.PLANTILLLA_EDITOR)) {
                RegistroQuiHojaCirculaPre registro = new RegistroQuiHojaCirculaPre();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_HOJACIRPRE_AGITADO.getItem())) {
                        v = registro.VAR_HOJACIRPRE_AGITADO;
                        v.setValor(campo.getDato());
                        registro.setAgitado(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_ALERGIA.getItem())) {
                        v = registro.VAR_HOJACIRPRE_ALERGIA;
                        v.setValor(campo.getDato());
                        registro.setAlergias(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_ANESTESISTA.getItem())) {
                        v = registro.VAR_HOJACIRPRE_ANESTESISTA;
                        v.setValor(campo.getDato());
                        registro.setAnestesista(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_AUXILAR.getItem())) {
                        v = registro.VAR_HOJACIRPRE_AUXILAR;
                        v.setValor(campo.getDato());
                        registro.setAuxiliar(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_ayunas.getItem())) {
                        v = registro.VAR_HOJACIRPRE_ayunas;
                        v.setValor(campo.getDato());
                        registro.setAyunas(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catCanualaTra.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catCanualaTra;
                        v.setValor(campo.getDato());
                        registro.setCatCanualaTra(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catCanualaTraN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catCanualaTraN;
                        v.setValor(campo.getDato());
                        registro.setCatCanualaTraN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catDrenaSilicona.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catDrenaSilicona;
                        v.setValor(campo.getDato());
                        registro.setCatDrenaSilicona(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catDrenaSiliconaN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catDrenaSiliconaN;
                        v.setValor(campo.getDato());
                        registro.setCatDrenaSiliconaN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catGastrostomia.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catGastrostomia;
                        v.setValor(campo.getDato());
                        registro.setCatGastrostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catGastrostomiaN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catGastrostomiaN;
                        v.setValor(campo.getDato());
                        registro.setCatGastrostomiaN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catJacksonP.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catJacksonP;
                        v.setValor(campo.getDato());
                        registro.setCatJacksonP(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catJacksonPN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catJacksonPN;
                        v.setValor(campo.getDato());
                        registro.setCatJacksonPN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catNefrostomia.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catNefrostomia;
                        v.setValor(campo.getDato());
                        registro.setCatNefrostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catNefrostomiaN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catNefrostomiaN;
                        v.setValor(campo.getDato());
                        registro.setCatNefrostomiaN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catPenrose.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catPenrose;
                        v.setValor(campo.getDato());
                        registro.setCatPenrose(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catPenroseN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catPenroseN;
                        v.setValor(campo.getDato());
                        registro.setCatPenroseN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catPeritoneal.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catPeritoneal;
                        v.setValor(campo.getDato());
                        registro.setCatPeritoneal(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catPeritonealN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catPeritonealN;
                        v.setValor(campo.getDato());
                        registro.setCatPeritonealN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catRedon.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catRedon;
                        v.setValor(campo.getDato());
                        registro.setCatRedon(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catRedonN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catRedonN;
                        v.setValor(campo.getDato());
                        registro.setCatRedonN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catSilastic.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catSilastic;
                        v.setValor(campo.getDato());
                        registro.setCatSilastic(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catSilasticN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catSilasticN;
                        v.setValor(campo.getDato());
                        registro.setCatSilasticN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catTallaVesical.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catTallaVesical;
                        v.setValor(campo.getDato());
                        registro.setCatTallaVesical(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catTallaVesicalN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catTallaVesicalN;
                        v.setValor(campo.getDato());
                        registro.setCatTallaVesicalN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catTuboKherr.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catTuboKherr;
                        v.setValor(campo.getDato());
                        registro.setCatTuboKherr(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catTuboKherrN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catTuboKherrN;
                        v.setValor(campo.getDato());
                        registro.setCatTuboKherrN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catTuboTorax.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catTuboTorax;
                        v.setValor(campo.getDato());
                        registro.setCatTuboTorax(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catTuboToraxN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catTuboToraxN;
                        v.setValor(campo.getDato());
                        registro.setCatTuboToraxN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catUretral.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catUretral;
                        v.setValor(campo.getDato());
                        registro.setCatUretral(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_catUretralN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_catUretralN;
                        v.setValor(campo.getDato());
                        registro.setCatUretralN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_CIRCULANTE.getItem())) {
                        v = registro.VAR_HOJACIRPRE_CIRCULANTE;
                        v.setValor(campo.getDato());
                        registro.setCirculante(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_CIRUJANO1.getItem())) {
                        v = registro.VAR_HOJACIRPRE_CIRUJANO1;
                        v.setValor(campo.getDato());
                        registro.setCirujano1(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_CIRUJANO2.getItem())) {
                        v = registro.VAR_HOJACIRPRE_CIRUJANO2;
                        v.setValor(campo.getDato());
                        registro.setCirujano2(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_CIRUJANO3.getItem())) {
                        v = registro.VAR_HOJACIRPRE_CIRUJANO3;
                        v.setValor(campo.getDato());
                        registro.setCirujano3(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_CONSCINTE.getItem())) {
                        v = registro.VAR_HOJACIRPRE_CONSCINTE;
                        v.setValor(campo.getDato());
                        registro.setConsciente(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_FECHAHORA.getItem())) {
                        v = registro.VAR_HOJACIRPRE_FECHAHORA;
                        v.setValor(campo.getDato());
                        registro.setFechaHoraInter(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_identificacion.getItem())) {
                        v = registro.VAR_HOJACIRPRE_identificacion;
                        v.setValor(campo.getDato());
                        registro.setIdentificacion(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_INSTRUMENTISTA.getItem())) {
                        v = registro.VAR_HOJACIRPRE_INSTRUMENTISTA;
                        v.setValor(campo.getDato());
                        registro.setInstrumentista(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_INTERVENCION.getItem())) {
                        v = registro.VAR_HOJACIRPRE_INTERVENCION;
                        v.setValor(campo.getDato());
                        registro.setIntervencion(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_DIAGNOSTICO.getItem())) {
                        v = registro.VAR_HOJACIRPRE_DIAGNOSTICO;
                        v.setValor(campo.getDato());
                        registro.setDiagnostico(v);
                    } 
                    
                    else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_INTUBADO.getItem())) {
                        v = registro.VAR_HOJACIRPRE_INTUBADO;
                        v.setValor(campo.getDato());
                        registro.setIntubado(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_NERVIOSO.getItem())) {
                        v = registro.VAR_HOJACIRPRE_NERVIOSO;
                        v.setValor(campo.getDato());
                        registro.setNervioso(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_ORDEN.getItem())) {
                        v = registro.VAR_HOJACIRPRE_ORDEN;
                        v.setValor(campo.getDato());
                        registro.setOrden(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_ORIENTADO.getItem())) {
                        v = registro.VAR_HOJACIRPRE_ORIENTADO;
                        v.setValor(campo.getDato());
                        registro.setOrientado(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_profilaxisAntibio.getItem())) {
                        v = registro.VAR_HOJACIRPRE_profilaxisAntibio;
                        v.setValor(campo.getDato());
                        registro.setProfilaxisAntibio(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_protocolDiabetico.getItem())) {
                        v = registro.VAR_HOJACIRPRE_protocolDiabetico;
                        v.setValor(campo.getDato());
                        registro.setProtocolDiabetico(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_QUIROFANO.getItem())) {
                        v = registro.VAR_HOJACIRPRE_QUIROFANO;
                        v.setValor(campo.getDato());
                        registro.setQuirofano(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_rasurado.getItem())) {
                        v = registro.VAR_HOJACIRPRE_rasurado;
                        v.setValor(campo.getDato());
                        registro.setRasurado(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_retiradaObjPel.getItem())) {
                        v = registro.VAR_HOJACIRPRE_retiradaObjPel;
                        v.setValor(campo.getDato());
                        registro.setRetiradaObjPel(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_SNG.getItem())) {
                        v = registro.VAR_HOJACIRPRE_SNG;
                        v.setValor(campo.getDato());
                        registro.setSng(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_sondaVesical.getItem())) {
                        v = registro.VAR_HOJACIRPRE_sondaVesical;
                        v.setValor(campo.getDato());
                        registro.setSondaVesical(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_URGENTEPROGRAMA.getItem())) {
                        v = registro.VAR_HOJACIRPRE_URGENTEPROGRAMA;
                        v.setValor(campo.getDato());
                        registro.setUrgenteProgramado(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_VIAARTERIAL.getItem())) {
                        v = registro.VAR_HOJACIRPRE_VIAARTERIAL;
                        v.setValor(campo.getDato());
                        registro.setViaArterialSN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_VIAARTERIALLOCAL.getItem())) {
                        v = registro.VAR_HOJACIRPRE_VIAARTERIALLOCAL;
                        v.setValor(campo.getDato());
                        registro.setViaArterialLocali(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_VIAVENOSA.getItem())) {
                        v = registro.VAR_HOJACIRPRE_VIAVENOSA;
                        v.setValor(campo.getDato());
                        registro.setViaVenosaTipo(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPRE_VIAVENOSALOCA.getItem())) {
                        v = registro.VAR_HOJACIRPRE_VIAVENOSALOCA;
                        v.setValor(campo.getDato());
                        registro.setViaVenosaLocali(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroQuiHojaCirculaQui.PLANTILLLA_EDITOR)) {
                RegistroQuiHojaCirculaQui registro = new RegistroQuiHojaCirculaQui();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //     registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_HOJACIRQUI_ASPIRASECRECIONES.getItem())) {
                        v = registro.VAR_HOJACIRQUI_ASPIRASECRECIONES;
                        v.setValor(campo.getDato());
                        registro.setAspiraSecreciones(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_CALENTADORLIQ.getItem())) {
                        v = registro.VAR_HOJACIRQUI_CALENTADORLIQ;
                        v.setValor(campo.getDato());
                        registro.setCalentaLiqui(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_CATETEREPIDURAL.getItem())) {
                        v = registro.VAR_HOJACIRQUI_CATETEREPIDURAL;
                        v.setValor(campo.getDato());
                        registro.setCateterEpidural(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_COLABORAANESTE.getItem())) {
                        v = registro.VAR_HOJACIRQUI_COLABORAANESTE;
                        v.setValor(campo.getDato());
                        registro.setColaboraAnestesia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_COMPLEMENTOS.getItem())) {
                        v = registro.VAR_HOJACIRQUI_COMPLEMENTOS;
                        v.setValor(campo.getDato());
                        registro.setComplementos(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_CUIDADOSPACIENTE.getItem())) {
                        v = registro.VAR_HOJACIRQUI_CUIDADOSPACIENTE;
                        v.setValor(campo.getDato());
                        registro.setCuidadosPaci(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_FC.getItem())) {
                        v = registro.VAR_HOJACIRQUI_FC;
                        v.setValor(campo.getDato());
                        registro.setCuidadosPaci(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_HORAINICIO.getItem())) {
                        v = registro.VAR_HOJACIRQUI_HORAINICIO;
                        v.setValor(campo.getDato());
                        registro.setHoraInicio(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_HORAENTRADA.getItem())) {
                        v = registro.VAR_HOJACIRQUI_HORAENTRADA;
                        v.setValor(campo.getDato());
                        registro.setHoraEntrada(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_INSTRUMENTAL.getItem())) {
                        v = registro.VAR_HOJACIRQUI_INSTRUMENTAL;
                        v.setValor(campo.getDato());
                        registro.setInstrumental(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_INSTRUMENTALCABLES.getItem())) {
                        v = registro.VAR_HOJACIRQUI_INSTRUMENTALCABLES;
                        v.setValor(campo.getDato());
                        registro.setInstrumentalCables(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_INSTRUMENTALMOTOR.getItem())) {
                        v = registro.VAR_HOJACIRQUI_INSTRUMENTALMOTOR;
                        v.setValor(campo.getDato());
                        registro.setInstrumentalMotor(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_INSTRUMENTALOPTICA.getItem())) {
                        v = registro.VAR_HOJACIRQUI_INSTRUMENTALOPTICA;
                        v.setValor(campo.getDato());
                        registro.setInstrumentalOptica(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_INSTRUMENTALOTROS.getItem())) {
                        v = registro.VAR_HOJACIRQUI_INSTRUMENTALOTROS;
                        v.setValor(campo.getDato());
                        registro.setInstrumentalOtros(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_INSTRUMENTALPRESTA.getItem())) {
                        v = registro.VAR_HOJACIRQUI_INSTRUMENTALPRESTA;
                        v.setValor(campo.getDato());
                        registro.setInstrumentalPresta(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_INSTRUMENTALSUELTO.getItem())) {
                        v = registro.VAR_HOJACIRQUI_INSTRUMENTALSUELTO;
                        v.setValor(campo.getDato());
                        registro.setInstrumentalSuelto(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_MANTATERMICA.getItem())) {
                        v = registro.VAR_HOJACIRQUI_MANTATERMICA;
                        v.setValor(campo.getDato());
                        registro.setMantaTermica(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_MATERIALINTERV.getItem())) {
                        v = registro.VAR_HOJACIRQUI_MATERIALINTERV;
                        v.setValor(campo.getDato());
                        registro.setMaterialIneter(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_MEDIDASDEASEPSIA.getItem())) {
                        v = registro.VAR_HOJACIRQUI_MEDIDASDEASEPSIA;
                        v.setValor(campo.getDato());
                        registro.setMedidasAsepsia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_MONITORIZAR.getItem())) {
                        v = registro.VAR_HOJACIRQUI_MONITORIZAR;
                        v.setValor(campo.getDato());
                        registro.setMonitorizar(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_OXIGENOTERAPIA.getItem())) {
                        v = registro.VAR_HOJACIRQUI_OXIGENOTERAPIA;
                        v.setValor(campo.getDato());
                        registro.setOxigenoterapia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_PLACABISTURI.getItem())) {
                        v = registro.VAR_HOJACIRQUI_PLACABISTURI;
                        v.setValor(campo.getDato());
                        registro.setPlacaBisturi(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_POSICIONPACI.getItem())) {
                        v = registro.VAR_HOJACIRQUI_POSICIONPACI;
                        v.setValor(campo.getDato());
                        registro.setPosicionPaci(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_PROTECCIONOCULAR.getItem())) {
                        v = registro.VAR_HOJACIRQUI_PROTECCIONOCULAR;
                        v.setValor(campo.getDato());
                        registro.setProteccOcular(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_PULSIO.getItem())) {
                        v = registro.VAR_HOJACIRQUI_PULSIO;
                        v.setValor(campo.getDato());
                        registro.setPulsioxímetro(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_SATO2.getItem())) {
                        v = registro.VAR_HOJACIRQUI_SATO2;
                        v.setValor(campo.getDato());
                        registro.setSatO2(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_SEGURIDADPACIENTE.getItem())) {
                        v = registro.VAR_HOJACIRQUI_SEGURIDADPACIENTE;
                        v.setValor(campo.getDato());
                        registro.setSatO2(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_SNG.getItem())) {
                        v = registro.VAR_HOJACIRQUI_SNG;
                        v.setValor(campo.getDato());
                        registro.setSng(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_SONDAVESI.getItem())) {
                        v = registro.VAR_HOJACIRQUI_SONDAVESI;
                        v.setValor(campo.getDato());
                        registro.setSondaVesical(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_T.getItem())) {
                        v = registro.VAR_HOJACIRQUI_T;
                        v.setValor(campo.getDato());
                        registro.setT(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_TAD.getItem())) {
                        v = registro.VAR_HOJACIRQUI_TAD;
                        v.setValor(campo.getDato());
                        registro.setTad(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_TAS.getItem())) {
                        v = registro.VAR_HOJACIRQUI_TAS;
                        v.setValor(campo.getDato());
                        registro.setTas(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_TIPOANESIA.getItem())) {
                        v = registro.VAR_HOJACIRQUI_TIPOANESIA;
                        v.setValor(campo.getDato());
                        registro.setTipoAnestesia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_TRANSFUHEMATIES.getItem())) {
                        v = registro.VAR_HOJACIRQUI_TRANSFUHEMATIES;
                        v.setValor(campo.getDato());
                        registro.setHematies(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_TRANSFUPLAQUETAS.getItem())) {
                        v = registro.VAR_HOJACIRQUI_TRANSFUPLAQUETAS;
                        v.setValor(campo.getDato());
                        registro.setPlaquetas(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_TRANSFUPLASMA.getItem())) {
                        v = registro.VAR_HOJACIRQUI_TRANSFUPLASMA;
                        v.setValor(campo.getDato());
                        registro.setPlasma(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_TUBOMASCAR.getItem())) {
                        v = registro.VAR_HOJACIRQUI_TUBOMASCAR;
                        v.setValor(campo.getDato());
                        registro.setTuboMascarilla(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_VIA.getItem())) {
                        v = registro.VAR_HOJACIRQUI_VIA;
                        v.setValor(campo.getDato());
                        registro.setVia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRQUI_VIALOCALI.getItem())) {
                        v = registro.VAR_HOJACIRQUI_VIALOCALI;
                        v.setValor(campo.getDato());
                        registro.setViaLocalizacion(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroQuiHojaCirculaPost.PLANTILLLA_EDITOR)) {
                RegistroQuiHojaCirculaPost registro = new RegistroQuiHojaCirculaPost();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                //  registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_HOJACIRPST_acompañamiento.getItem())) {
                        v = registro.VAR_HOJACIRPST_acompañamiento;
                        v.setValor(campo.getDato());
                        registro.setAcompañamiento(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_analitica.getItem())) {
                        v = registro.VAR_HOJACIRPST_analitica;
                        v.setValor(campo.getDato());
                        registro.setAnalitica(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_analiticaMuestras.getItem())) {
                        v = registro.VAR_HOJACIRPST_analiticaMuestras;
                        v.setValor(campo.getDato());
                        registro.setAnaliticaMuestras(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_aspiraSecreciones.getItem())) {
                        v = registro.VAR_HOJACIRPST_aspiraSecreciones;
                        v.setValor(campo.getDato());
                        registro.setAspiraSecreciones(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_biopsiaIntra.getItem())) {
                        v = registro.VAR_HOJACIRPST_biopsiaIntra;
                        v.setValor(campo.getDato());
                        registro.setBiopsiaIntra(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_biopsiaIntraMuestras.getItem())) {
                        v = registro.VAR_HOJACIRPST_biopsiaIntraMuestras;
                        v.setValor(campo.getDato());
                        registro.setBiopsiaIntraMuestras(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catCanualaTra.getItem())) {
                        v = registro.VAR_HOJACIRPST_catCanualaTra;
                        v.setValor(campo.getDato());
                        registro.setCatCanualaTra(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catCanualaTraN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catCanualaTraN;
                        v.setValor(campo.getDato());
                        registro.setCatCanualaTraN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catDrenaSilicona.getItem())) {
                        v = registro.VAR_HOJACIRPST_catDrenaSilicona;
                        v.setValor(campo.getDato());
                        registro.setCatDrenaSilicona(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catDrenaSiliconaN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catDrenaSiliconaN;
                        v.setValor(campo.getDato());
                        registro.setCatDrenaSiliconaN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catGastrostomia.getItem())) {
                        v = registro.VAR_HOJACIRPST_catGastrostomia;
                        v.setValor(campo.getDato());
                        registro.setCatGastrostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catGastrostomiaN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catGastrostomiaN;
                        v.setValor(campo.getDato());
                        registro.setCatGastrostomiaN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catJacksonP.getItem())) {
                        v = registro.VAR_HOJACIRPST_catJacksonP;
                        v.setValor(campo.getDato());
                        registro.setCatJacksonP(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catJacksonPN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catJacksonPN;
                        v.setValor(campo.getDato());
                        registro.setCatJacksonPN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catNefrostomia.getItem())) {
                        v = registro.VAR_HOJACIRPST_catNefrostomia;
                        v.setValor(campo.getDato());
                        registro.setCatNefrostomia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catNefrostomiaN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catNefrostomiaN;
                        v.setValor(campo.getDato());
                        registro.setCatNefrostomiaN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catPenrose.getItem())) {
                        v = registro.VAR_HOJACIRPST_catPenrose;
                        v.setValor(campo.getDato());
                        registro.setCatPenrose(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catPenroseN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catPenroseN;
                        v.setValor(campo.getDato());
                        registro.setCatPenroseN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catPeritoneal.getItem())) {
                        v = registro.VAR_HOJACIRPST_catPeritoneal;
                        v.setValor(campo.getDato());
                        registro.setCatPeritoneal(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catPeritonealN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catPeritonealN;
                        v.setValor(campo.getDato());
                        registro.setCatPeritonealN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catRedon.getItem())) {
                        v = registro.VAR_HOJACIRPST_catRedon;
                        v.setValor(campo.getDato());
                        registro.setCatRedon(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catRedonN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catRedonN;
                        v.setValor(campo.getDato());
                        registro.setCatRedonN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catSilastic.getItem())) {
                        v = registro.VAR_HOJACIRPST_catSilastic;
                        v.setValor(campo.getDato());
                        registro.setCatSilastic(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catSilasticN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catSilasticN;
                        v.setValor(campo.getDato());
                        registro.setCatSilasticN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catTallaVesical.getItem())) {
                        v = registro.VAR_HOJACIRPST_catTallaVesical;
                        v.setValor(campo.getDato());
                        registro.setCatTallaVesical(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catTallaVesicalN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catTallaVesicalN;
                        v.setValor(campo.getDato());
                        registro.setCatTallaVesicalN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catTuboKherr.getItem())) {
                        v = registro.VAR_HOJACIRPST_catTuboKherr;
                        v.setValor(campo.getDato());
                        registro.setCatTuboKherr(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catTuboKherrN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catTuboKherrN;
                        v.setValor(campo.getDato());
                        registro.setCatTuboKherrN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catTuboTorax.getItem())) {
                        v = registro.VAR_HOJACIRPST_catTuboTorax;
                        v.setValor(campo.getDato());
                        registro.setCatTuboTorax(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catTuboToraxN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catTuboToraxN;
                        v.setValor(campo.getDato());
                        registro.setCatTuboToraxN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catUretral.getItem())) {
                        v = registro.VAR_HOJACIRPST_catUretral;
                        v.setValor(campo.getDato());
                        registro.setCatUretral(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_catUretralN.getItem())) {
                        v = registro.VAR_HOJACIRPST_catUretralN;
                        v.setValor(campo.getDato());
                        registro.setCatUretralN(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_citologia.getItem())) {
                        v = registro.VAR_HOJACIRPST_citologia;
                        v.setValor(campo.getDato());
                        registro.setCitologia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_citologiaMuestras.getItem())) {
                        v = registro.VAR_HOJACIRPST_citologiaMuestras;
                        v.setValor(campo.getDato());
                        registro.setCitologiaMuestras(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_colaboraExtubacion.getItem())) {
                        v = registro.VAR_HOJACIRPST_colaboraExtubacion;
                        v.setValor(campo.getDato());
                        registro.setColaboraExtubacion(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_colocaAposito.getItem())) {
                        v = registro.VAR_HOJACIRPST_colocaAposito;
                        v.setValor(campo.getDato());
                        registro.setColocaAposito(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_contaje.getItem())) {
                        v = registro.VAR_HOJACIRPST_contaje;
                        v.setValor(campo.getDato());
                        registro.setContaje(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_destino.getItem())) {
                        v = registro.VAR_HOJACIRPST_destino;
                        v.setValor(campo.getDato());
                        registro.setDestino(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_escopiaTiempo.getItem())) {
                        v = registro.VAR_HOJACIRPST_escopiaTiempo;
                        v.setValor(campo.getDato());
                        registro.setEscopiaTiempo(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_horaFinalizacion.getItem())) {
                        v = registro.VAR_HOJACIRPST_horaFinalizacion;
                        v.setValor(campo.getDato());
                        registro.setHoraFinalizacion(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_horaSalida.getItem())) {
                        v = registro.VAR_HOJACIRPST_horaSalida;
                        v.setValor(campo.getDato());
                        registro.setHoraSalida(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_implantes.getItem())) {
                        v = registro.VAR_HOJACIRPST_implantes;
                        v.setValor(campo.getDato());
                        registro.setImplantes(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_implantesModelo.getItem())) {
                        v = registro.VAR_HOJACIRPST_implantesModelo;
                        v.setValor(campo.getDato());
                        registro.setImplantesModelo(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_implantesNSerie.getItem())) {
                        v = registro.VAR_HOJACIRPST_implantesNSerie;
                        v.setValor(campo.getDato());
                        registro.setImplantesNSerie(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_implantesPotencia.getItem())) {
                        v = registro.VAR_HOJACIRPST_implantesPotencia;
                        v.setValor(campo.getDato());
                        registro.setImplantesPotencia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_isquemiaFin.getItem())) {
                        v = registro.VAR_HOJACIRPST_isquemiaFin;
                        v.setValor(campo.getDato());
                        registro.setIsquemiaFin(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_isquemiaInicio.getItem())) {
                        v = registro.VAR_HOJACIRPST_isquemiaInicio;
                        v.setValor(campo.getDato());
                        registro.setIsquemiaInicio(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_isquemiaLugar.getItem())) {
                        v = registro.VAR_HOJACIRPST_isquemiaLugar;
                        v.setValor(campo.getDato());
                        registro.setIsquemiaLugar(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_muestraMicro.getItem())) {
                        v = registro.VAR_HOJACIRPST_muestraMicro;
                        v.setValor(campo.getDato());
                        registro.setMuestraMicro(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_muestraMuestras.getItem())) {
                        v = registro.VAR_HOJACIRPST_muestraMuestras;
                        v.setValor(campo.getDato());
                        registro.setMuestraMuestras(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_observaciones.getItem())) {
                        v = registro.VAR_HOJACIRPST_observaciones;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_oxigenoterapia.getItem())) {
                        v = registro.VAR_HOJACIRPST_oxigenoterapia;
                        v.setValor(campo.getDato());
                        registro.setOxigenoterapia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_piezaAnatomia.getItem())) {
                        v = registro.VAR_HOJACIRPST_piezaAnatomia;
                        v.setValor(campo.getDato());
                        registro.setPiezaAnatomia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_piezaMuestras.getItem())) {
                        v = registro.VAR_HOJACIRPST_piezaMuestras;
                        v.setValor(campo.getDato());
                        registro.setPiezaMuestras(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_reseccionFin.getItem())) {
                        v = registro.VAR_HOJACIRPST_reseccionFin;
                        v.setValor(campo.getDato());
                        registro.setReseccionFin(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_reseccionInicio.getItem())) {
                        v = registro.VAR_HOJACIRPST_reseccionInicio;
                        v.setValor(campo.getDato());
                        registro.setReseccionInicio(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_reseccionProstata.getItem())) {
                        v = registro.VAR_HOJACIRPST_reseccionProstata;
                        v.setValor(campo.getDato());
                        registro.setReseccionProstata(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_reseccionUtero.getItem())) {
                        v = registro.VAR_HOJACIRPST_reseccionUtero;
                        v.setValor(campo.getDato());
                        registro.setReseccionUtero(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_reseccionVejiga.getItem())) {
                        v = registro.VAR_HOJACIRPST_reseccionVejiga;
                        v.setValor(campo.getDato());
                        registro.setReseccionVejiga(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_retiradaComplementos.getItem())) {
                        v = registro.VAR_HOJACIRPST_retiradaComplementos;
                        v.setValor(campo.getDato());
                        registro.setRetiradaComplementos(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_retirarPlacaB.getItem())) {
                        v = registro.VAR_HOJACIRPST_retirarPlacaB;
                        v.setValor(campo.getDato());
                        registro.setRetirarPlacaB(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_retirarSondaNsg.getItem())) {
                        v = registro.VAR_HOJACIRPST_retirarSondaNsg;
                        v.setValor(campo.getDato());
                        registro.setRetirarSondaNsg(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_retirarSondaVesi.getItem())) {
                        v = registro.VAR_HOJACIRPST_retirarSondaVesi;
                        v.setValor(campo.getDato());
                        registro.setRetirarSondaVesi(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_retirarVia.getItem())) {
                        v = registro.VAR_HOJACIRPST_retirarVia;
                        v.setValor(campo.getDato());
                        registro.setRetirarVia(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_sondaNsgcc.getItem())) {
                        v = registro.VAR_HOJACIRPST_sondaNsgcc;
                        v.setValor(campo.getDato());
                        registro.setSondaNsgcc(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_sondaVesicc.getItem())) {
                        v = registro.VAR_HOJACIRPST_sondaVesicc;
                        v.setValor(campo.getDato());
                        registro.setSondaVesicc(v);
                    } else if (campo.getItem().equals(registro.VAR_HOJACIRPST_trasladocama.getItem())) {
                        v = registro.VAR_HOJACIRPST_trasladocama;
                        v.setValor(campo.getDato());
                        registro.setTrasladocama(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroEnfAdministraFarmacos.PLANTILLLA_EDITOR_ENF_ADMIMED)) {
                RegistroEnfAdministraFarmacos registro = new RegistroEnfAdministraFarmacos();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_ENF_ADMI_MEDICAMENTO.getItem())) {
                        v = registro.VAR_ENF_ADMI_MEDICAMENTO;
                        v.setValor(campo.getDato());
                        registro.setMedicamento(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_ADMI_DOSIS.getItem())) {
                        v = registro.VAR_ENF_ADMI_DOSIS;
                        v.setValor(campo.getDato());
                        registro.setDosis(v);
                        // unidades
                        Variable uni = registro.VAR_ENF_ADMI_DOSISUNIDADES;
                        uni.setValor(campo.getUnidades());
                        registro.setDosisUnidades(uni);
                    } else if (campo.getItem().equals(registro.VAR_ENF_ADMI_VIAADM.getItem())) {
                        v = registro.VAR_ENF_ADMI_VIAADM;
                        v.setValor(campo.getDato());
                        registro.setViaAdm(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_ADMI_PAUTA.getItem())) {
                        v = registro.VAR_ENF_ADMI_PAUTA;
                        v.setValor(campo.getDato());
                        registro.setPauta(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_ADMI_OBSERVA.getItem())) {
                        v = registro.VAR_ENF_ADMI_OBSERVA;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroEnfCuras.PLANTILLLA_EDITOR_ENF_CURA)) {
                RegistroEnfCuras registro = new RegistroEnfCuras();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_ENF_CURA_TIPO.getItem())) {
                        v = registro.VAR_ENF_CURA_TIPO;
                        v.setValor(campo.getDato());
                        registro.setCuraTipO(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CURA_GRADO.getItem())) {
                        v = registro.VAR_ENF_CURA_GRADO;
                        v.setValor(campo.getDato());
                        registro.setCuraGrado(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CURA_INFECCION.getItem())) {
                        v = registro.VAR_ENF_CURA_INFECCION;
                        v.setValor(campo.getDato());
                        registro.setInfeccion(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CURA_LOCALIZACION.getItem())) {
                        v = registro.VAR_ENF_CURA_LOCALIZACION;
                        v.setValor(campo.getDato());
                        registro.setLocalizacion(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CURA_TRATAMIENTO.getItem())) {
                        v = registro.VAR_ENF_CURA_TRATAMIENTO;
                        v.setValor(campo.getDato());
                        registro.setTratamiento(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CURA_PAUTA.getItem())) {
                        v = registro.VAR_ENF_CURA_PAUTA;
                        v.setValor(campo.getDato());
                        registro.setPauta(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CURA_OBSERVACIONES.getItem())) {
                        v = registro.VAR_ENF_CURA_OBSERVACIONES;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroEnfCanalizacionVias.PLANTILLLA_EDITOR_ENF_CANALI)) {
                RegistroEnfCanalizacionVias registro = new RegistroEnfCanalizacionVias();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                //  registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_ENF_CANALI_TIPOVIA.getItem())) {
                        v = registro.VAR_ENF_CANALI_TIPOVIA;
                        v.setValor(campo.getDato());
                        registro.setTipoVia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CANALI_LOCALIVIA.getItem())) {
                        v = registro.VAR_ENF_CANALI_LOCALIVIA;
                        v.setValor(campo.getDato());
                        registro.setLocalizacionVia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CANALI_NUMEROVIA.getItem())) {
                        v = registro.VAR_ENF_CANALI_NUMEROVIA;
                        v.setValor(campo.getDato());
                        registro.setLocalizacionVia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_CANALI_OBSERVAVIA.getItem())) {
                        v = registro.VAR_ENF_CANALI_OBSERVAVIA;
                        v.setValor(campo.getDato());
                        registro.setLocalizacionVia(v);
                    }
                }
                return registro;
            } else if (id.equals(RegistroEnfPruebas.PLANTILLLA_EDITOR_ENF_PRUEBAS)) {
                RegistroEnfPruebas registro = new RegistroEnfPruebas();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_ENF_PRUEB_ANALHEMOCUL.getItem())) {
                        v = registro.VAR_ENF_PRUEB_ANALHEMOCUL;
                        v.setValor(campo.getDato());
                        registro.setAnalSangreHemocultivo(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_ANALSANGREARTER.getItem())) {
                        v = registro.VAR_ENF_PRUEB_ANALSANGREARTER;
                        v.setValor(campo.getDato());
                        registro.setAnalSangreArterial(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_ANALSANGREVENA.getItem())) {
                        v = registro.VAR_ENF_PRUEB_ANALSANGREVENA;
                        v.setValor(campo.getDato());
                        registro.setAnalSangreVenosa(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_ANAORINA.getItem())) {
                        v = registro.VAR_ENF_PRUEB_ANAORINA;
                        v.setValor(campo.getDato());
                        registro.setAnalOrina(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_ECG.getItem())) {
                        v = registro.VAR_ENF_PRUEB_ECG;
                        v.setValor(campo.getDato());
                        registro.setEcg(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_ECOCAR.getItem())) {
                        v = registro.VAR_ENF_PRUEB_ECOCAR;
                        v.setValor(campo.getDato());
                        registro.setEcocardiograma(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_RX.getItem())) {
                        v = registro.VAR_ENF_PRUEB_RX;
                        v.setValor(campo.getDato());
                        registro.setRx(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_PUNCION.getItem())) {
                        v = registro.VAR_ENF_PRUEB_PUNCION;
                        v.setValor(campo.getDato());
                        registro.setPuncionLumbar(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_BOPSIA.getItem())) {
                        v = registro.VAR_ENF_PRUEB_BOPSIA;
                        v.setValor(campo.getDato());
                        registro.setBiopsia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_MAPA.getItem())) {
                        v = registro.VAR_ENF_PRUEB_MAPA;
                        v.setValor(campo.getDato());
                        registro.setMAPA(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_FROTIS.getItem())) {
                        v = registro.VAR_ENF_PRUEB_FROTIS;
                        v.setValor(campo.getDato());
                        registro.setFrotis(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_CULTIVO.getItem())) {
                        v = registro.VAR_ENF_PRUEB_CULTIVO;
                        v.setValor(campo.getDato());
                        registro.setCultivo(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_CATERIZAVIA.getItem())) {
                        v = registro.VAR_ENF_PRUEB_CATERIZAVIA;
                        v.setValor(campo.getDato());
                        registro.setCaterizacionViaCentral(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_PARACENTESIS.getItem())) {
                        v = registro.VAR_ENF_PRUEB_PARACENTESIS;
                        v.setValor(campo.getDato());
                        registro.setParacentesis(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_ARTROCEN.getItem())) {
                        v = registro.VAR_ENF_PRUEB_ARTROCEN;
                        v.setValor(campo.getDato());
                        registro.setArtrocentesis(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_TORACO.getItem())) {
                        v = registro.VAR_ENF_PRUEB_TORACO;
                        v.setValor(campo.getDato());
                        registro.setToracocentesis(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_DRENAJETORACO.getItem())) {
                        v = registro.VAR_ENF_PRUEB_DRENAJETORACO;
                        v.setValor(campo.getDato());
                        registro.setDrenajeTora(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_OBSERVACIONES.getItem())) {
                        v = registro.VAR_ENF_PRUEB_OBSERVACIONES;
                        v.setValor(campo.getDato());
                        registro.setDrenajeTora(v);
                    }
                    else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_SANGRIA.getItem())) {
                        v = registro.VAR_ENF_PRUEB_SANGRIA;
                        v.setValor(campo.getDato());
                        registro.setSangria(v);
                    }      else if (campo.getItem().equals(registro.VAR_ENF_PRUEB_FLEBOTOMIA.getItem())) {
                        v = registro.VAR_ENF_PRUEB_FLEBOTOMIA;
                        v.setValor(campo.getDato());
                        registro.setFlebotomia(v);
                    }

                }
                return registro;
            } else if (id.equals(RegistroEnfIntervenciones.PLANTILLLA_EDITOR_INTERV)) {
                RegistroEnfIntervenciones registro = new RegistroEnfIntervenciones();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.VAR_ENF_INTERV_HIPERCALCEMIA.getItem())) {
                        v = registro.VAR_ENF_INTERV_HIPERCALCEMIA;
                        v.setValor(campo.getDato());
                        registro.setManejoHipercalcemia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_SALUDBUCAL.getItem())) {
                        v = registro.VAR_ENF_INTERV_SALUDBUCAL;
                        v.setValor(campo.getDato());
                        registro.setSaludBucal(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_DIARREA.getItem())) {
                        v = registro.VAR_ENF_INTERV_DIARREA;
                        v.setValor(campo.getDato());
                        registro.setManejoDiarrea(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_HIPERTERMIA.getItem())) {
                        v = registro.VAR_ENF_INTERV_HIPERTERMIA;
                        v.setValor(campo.getDato());
                        registro.setManejoHipertermia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_LIQUIELECTROLITOS.getItem())) {
                        v = registro.VAR_ENF_INTERV_LIQUIELECTROLITOS;
                        v.setValor(campo.getDato());
                        registro.setLiquElectroliticos(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_QUIMIO.getItem())) {
                        v = registro.VAR_ENF_INTERV_QUIMIO;
                        v.setValor(campo.getDato());
                        registro.setManejoQuimioterapia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_HIPOGLUCEMIA.getItem())) {
                        v = registro.VAR_ENF_INTERV_HIPOGLUCEMIA;
                        v.setValor(campo.getDato());
                        registro.setManejoHipoglucemia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_NUTRICIOON.getItem())) {
                        v = registro.VAR_ENF_INTERV_NUTRICIOON;
                        v.setValor(campo.getDato());
                        registro.setManejoNutricion(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_HEMODERIVADOS.getItem())) {
                        v = registro.VAR_ENF_INTERV_HEMODERIVADOS;
                        v.setValor(campo.getDato());
                        registro.setAdminHemoderivados(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_PUNCIONVENOSA.getItem())) {
                        v = registro.VAR_ENF_INTERV_PUNCIONVENOSA;
                        v.setValor(campo.getDato());
                        registro.setPuncionVenosa(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_ADMMEDIORAL.getItem())) {
                        v = registro.VAR_ENF_INTERV_ADMMEDIORAL;
                        v.setValor(campo.getDato());
                        registro.setAdmMediOral(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_ADMMEDISUBCUTA.getItem())) {
                        v = registro.VAR_ENF_INTERV_ADMMEDISUBCUTA;
                        v.setValor(campo.getDato());
                        registro.setAdmMediSubcuta(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_ADMMEDIINTRAVENOSA.getItem())) {
                        v = registro.VAR_ENF_INTERV_ADMMEDIINTRAVENOSA;
                        v.setValor(campo.getDato());
                        registro.setAdmMediIntrav(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_DISPOCENTRAL.getItem())) {
                        v = registro.VAR_ENF_INTERV_DISPOCENTRAL;
                        v.setValor(campo.getDato());
                        registro.setManejoDisCentral(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_PICC.getItem())) {
                        v = registro.VAR_ENF_INTERV_PICC;
                        v.setValor(campo.getDato());
                        registro.setInsercionPICC(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_FLEBOTOMIAVIA.getItem())) {
                        v = registro.VAR_ENF_INTERV_FLEBOTOMIAVIA;
                        v.setValor(campo.getDato());
                        registro.setFeblotomiaVia(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_FLEBOTOMIAMUESTRA.getItem())) {
                        v = registro.VAR_ENF_INTERV_FLEBOTOMIAMUESTRA;
                        v.setValor(campo.getDato());
                        registro.setFeblotomiaMuestra(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_CUIDADOINCISION.getItem())) {
                        v = registro.VAR_ENF_INTERV_CUIDADOINCISION;
                        v.setValor(campo.getDato());
                        registro.setCuidadoIncision(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_CUIDADOULCERASP.getItem())) {
                        v = registro.VAR_ENF_INTERV_CUIDADOULCERASP;
                        v.setValor(campo.getDato());
                        registro.setCuidadoUlcerasP(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_CUIDADOHERIDAS.getItem())) {
                        v = registro.VAR_ENF_INTERV_CUIDADOHERIDAS;
                        v.setValor(campo.getDato());
                        registro.setCuidadoHeridas(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_APOYOCUIDADOR.getItem())) {
                        v = registro.VAR_ENF_INTERV_APOYOCUIDADOR;
                        v.setValor(campo.getDato());
                        registro.setApoyoCuidador(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_MANEJODOLOR.getItem())) {
                        v = registro.VAR_ENF_INTERV_MANEJODOLOR;
                        v.setValor(campo.getDato());
                        registro.setManejoDolor(v);
                    } else if (campo.getItem().equals(registro.VAR_ENF_INTERV_ADMMEDICAINTRAMUSCULAR.getItem())) {
                        v = registro.VAR_ENF_INTERV_ADMMEDICAINTRAMUSCULAR;
                        v.setValor(campo.getDato());
                        registro.setMedicaIntramuscular(v);
                    }

                }
                return registro;
            } else if (id.equals(RegistroUrgDerivarUrg.PLANTILLLA_VALORACION_DERIVACIONES_PRIMARIA)) {
                RegistroUrgDerivarUrg registro = new RegistroUrgDerivarUrg();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                registro.setEpisodio(new EpisodioDAO().getRegistroPorId(resuSulset.getLong("episodio"),
                        registro.getPaciente(), null, null, null, null, null));
                for (Campos_r campo : registro.getListaCampos()) {
                    Variable v = new Variable();
                    if (campo.getItem().equals(registro.URG_DERIVA_CTES_FC.getItem())) {
                        v = registro.URG_DERIVA_CTES_FC;
                        v.setValor(campo.getDato());
                        registro.setFc(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_CTES_FR.getItem())) {
                        v = registro.URG_DERIVA_CTES_FR;
                        v.setValor(campo.getDato());
                        registro.setFr(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_CTES_T.getItem())) {
                        v = registro.URG_DERIVA_CTES_T;
                        v.setValor(campo.getDato());
                        registro.setTemperatura(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_CTES_TAD.getItem())) {
                        v = registro.URG_DERIVA_CTES_TAD;
                        v.setValor(campo.getDato());
                        registro.setTad(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_CTES_TAS.getItem())) {
                        v = registro.URG_DERIVA_CTES_TAS;
                        v.setValor(campo.getDato());
                        registro.setTas(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_SATO2.getItem())) {
                        v = registro.URG_DERIVA_SATO2;
                        v.setValor(campo.getDato());
                        registro.setSatO2(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_DESTINO.getItem())) {
                        v = registro.URG_DERIVA_DESTINO;
                        v.setValor(campo.getDato());
                        registro.setDestino(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_DOMICILIO.getItem())) {
                        v = registro.URG_DERIVA_DOMICILIO;
                        v.setValor(campo.getDato());
                        registro.setDomicilio(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_FECHA.getItem())) {
                        v = registro.URG_DERIVA_FECHA;
                        v.setValor(campo.getDato());
                        registro.setFechahoravar(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_CS.getItem())) {
                        v = registro.URG_DERIVA_CS;
                        v.setValor(campo.getDato());
                        registro.setCs(v);

                    } else if (campo.getItem().equals(registro.URG_DERIVA_MEDICOPETICIONARIO.getItem())) {
                        v = registro.URG_DERIVA_MEDICOPETICIONARIO;
                        v.setValor(campo.getDato());
                        registro.setMedicoPeticionario(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_NUMERO.getItem())) {
                        v = registro.URG_DERIVA_NUMERO;
                        v.setValor(campo.getDato());
                        registro.setNumero(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_OBSER.getItem())) {
                        v = registro.URG_DERIVA_OBSER;
                        v.setValor(campo.getDato());
                        registro.setObservaciones(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_PATOLOGIADIABETES.getItem())) {
                        v = registro.URG_DERIVA_PATOLOGIADIABETES;
                        v.setValor(campo.getDato());
                        registro.setPatologiaDiabetes(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_PATOLOGIAEMBARAZO.getItem())) {
                        v = registro.URG_DERIVA_PATOLOGIAEMBARAZO;
                        v.setValor(campo.getDato());
                        registro.setPatologiaEmbarazo(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_PATOLOGIAHTA.getItem())) {
                        v = registro.URG_DERIVA_PATOLOGIAHTA;
                        v.setValor(campo.getDato());
                        registro.setPatologiaHta(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_PATOLOGIAINMUNO.getItem())) {
                        v = registro.URG_DERIVA_PATOLOGIAINMUNO;
                        v.setValor(campo.getDato());
                        registro.setPatologiaInmunosupresion(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_PATOLOGIAIREANL.getItem())) {
                        v = registro.URG_DERIVA_PATOLOGIAIREANL;
                        v.setValor(campo.getDato());
                        registro.setPatologiaIrenal(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_PATOLOGIAIRESPIRA.getItem())) {
                        v = registro.URG_DERIVA_PATOLOGIAIRESPIRA;
                        v.setValor(campo.getDato());
                        registro.setPatologiaIRespira(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_PATOLOGIAO2DOMICI.getItem())) {
                        v = registro.URG_DERIVA_PATOLOGIAO2DOMICI;
                        v.setValor(campo.getDato());
                        registro.setPatologiaO2domicilio(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_PATOLOGIATRATAONCO.getItem())) {
                        v = registro.URG_DERIVA_PATOLOGIATRATAONCO;
                        v.setValor(campo.getDato());
                        registro.setPatologiaTrataOncologico(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_SINTOMASALTERAMENTAL.getItem())) {
                        v = registro.URG_DERIVA_SINTOMASALTERAMENTAL;
                        v.setValor(campo.getDato());
                        registro.setSintomasAlateraMenta(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_SINTOMASAUTOEXPLORA.getItem())) {
                        v = registro.URG_DERIVA_SINTOMASAUTOEXPLORA;
                        v.setValor(campo.getDato());
                        registro.setSintomasAutoexplora(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_SINTOMASDISNEA.getItem())) {
                        v = registro.URG_DERIVA_SINTOMASDISNEA;
                        v.setValor(campo.getDato());
                        registro.setSintomasDisnea(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_SINTOMASTOS.getItem())) {
                        v = registro.URG_DERIVA_SINTOMASTOS;
                        v.setValor(campo.getDato());
                        registro.setSintomasTos(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_SINTOMASOTROS.getItem())) {
                        v = registro.URG_DERIVA_SINTOMASOTROS;
                        v.setValor(campo.getDato());
                        registro.setSintomasOtros(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_SITUACIONBASAL.getItem())) {
                        v = registro.URG_DERIVA_SITUACIONBASAL;
                        v.setValor(campo.getDato());
                        registro.setSituacionBasal(v);
                    } else if (campo.getItem().equals(registro.URG_DERIVA_TIEMPOEVOL.getItem())) {
                        v = registro.URG_DERIVA_TIEMPOEVOL;
                        v.setValor(campo.getDato());
                        registro.setTiempoEvolucion(v);
                    }
                }
                return registro;
            } else {
                Registro registro = new Registro();
                registro.setId(resuSulset.getLong("id"));
                registro.setDescripcion(resuSulset.getString("descripcion"));
                if (paciente == null) {
                    registro.setPaciente(new PacienteDAO().getPacientePorId(resuSulset.getLong("paciente"), false));
                } else {
                    registro.setPaciente(paciente);
                }

                // registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                // registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                if (centro != null) {
                    registro.setCentro(centro);
                } else {
                    registro.setCentro(new CentroDAO().getRegistroId(resuSulset.getLong("centro")));
                }
                if (servicio != null) {
                    registro.setServicio(servicio);
                } else {
                    registro.setServicio(new ServiciosDAO().getRegistroId(resuSulset.getLong("servicio")));
                }
                registro.setFecha(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
                registro.setHora(resuSulset.getLong("hora"));
                registro.setEstado(resuSulset.getInt("estado"));
                if (usuario == null) {
                    registro.setUserid(new UsuarioDAO().getUsuarioUserid(resuSulset.getString("userid"), false));
                } else {
                    registro.setUserid(usuario);
                }
                if (proceso == null) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(resuSulset.getLong("problema"), null));
                } else {
                    registro.setProblema(proceso);
                }
                registro.setUseridredactor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setUseridtranscriptor(new Usuario(resuSulset.getString("useridredactor")));
                registro.setTiporegistro(resuSulset.getLong("tiporegistro"));
                registro.setCanal(resuSulset.getLong("canal"));
                registro.setListaCampos(getListaCamposRegistro(registro.getId()));
                return registro;
            }
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }

        return null;
    }

    public Registro getRegistroPacientePlantilla(Paciente paciente, Long plantilla_editor) {
        Connection connection = null;
        Registro registro = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT * FROM registros  WHERE paciente = ?  AND estado=? AND plantilla_editor=? order by id desc ";
            logger.debug("SELECT * FROM registros  WHERE paciente = " + paciente.getId() + "  AND estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND plantilla_editor=" + plantilla_editor);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(3, plantilla_editor);
            ResultSet resuSulset = statement.executeQuery();
            if (resuSulset.next()) {
                return getRegistroResulset(resuSulset, paciente, null, null, null, null);
            }
            statement.close();
            logger.debug("SELECT id FROM registros  WHERE paciente = " + paciente.getId() + "  AND estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND plantilla_editor=plantilla_editor");
        } catch (SQLException e) {
            logger.error("SELECT id FROM registros  WHERE paciente = " + paciente.getId() + "  AND estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND plantilla_editor=plantilla_editor");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return registro;
    }

    /**
     * Gets the registro paciente proceso.
     *
     * @param paciente the paciente
     * @param proceso the proceso
     * @param plantilla_editor the plantilla editor
     * @return the registro paciente proceso
     */
    public Long getRegistroPacienteProceso(Paciente paciente, Proceso proceso, Long plantilla_editor) {
        Connection connection = null;
        Long id = new Long(0);
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id FROM registros  WHERE paciente = ?  AND estado=? AND problema= ? AND plantilla_editor=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(3, proceso.getId());
            statement.setLong(4, plantilla_editor);
            ResultSet resuSulset = statement.executeQuery();
            if (resuSulset.next()) {
                id = resuSulset.getLong("id");
            }
            statement.close();
            logger.debug("SELECT id FROM registros  WHERE paciente = " + paciente.getId() + "  AND estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND problema= " + proceso.getId()
                    + " AND plantilla_editor=plantilla_editor");
        } catch (SQLException e) {
            logger.error("SELECT id FROM registros  WHERE paciente = " + paciente.getId() + "  AND estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND problema= " + proceso.getId()
                    + " AND plantilla_editor=plantilla_editor");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return id;
    }

    public Registro getLisRegistro(Paciente paciente, Proceso proceso, Long plantilla_editor) {
        Connection connection = null;
        Registro registro = null;
        String sqllog = "";
        try {
            connection = super.getConexionBBDD();
            sql = getSql() + " AND  r.paciente = ?  AND r.estado=? AND r.problema= ? AND r.plantilla_editor=?";
            sqllog = getSql() + " AND  r.paciente = " + paciente.getId() + "  AND r.estado=" + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.problema= " + proceso.getId() + " AND r.plantilla_editor=" + plantilla_editor;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(3, proceso.getId());
            statement.setLong(4, plantilla_editor);
            ResultSet resuSulset = statement.executeQuery();
            if (resuSulset.next()) {
                Centro centroParam = CentroDAO.getCentroRs(resuSulset, null);
                Servicio servicioParam = ServiciosDAO.getServicioRs(resuSulset, null);
                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resuSulset, null);
                registro = getRegistroResulset(resuSulset, paciente, proceso, centroParam, servicioParam, usuarioParam);
            }
            statement.close();
            logger.debug(sqllog);
        } catch (SQLException e) {
            logger.error(sqllog);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return registro;
    }

    /**
     * Gets the registro paciente proceso O 2. Recupera un registro de tipo
     * oxigenoterapia del subtipo plantilla_editor que no este cerrado, es decir
     * que no tenga valor en campos_r la variable fecha fin
     *
     * @param paciente the paciente
     * @param proceso the proceso
     * @param plantilla_editor the plantilla editor
     * @return the registro paciente proceso O 2
     */
    public Long getRegistroPacienteProcesoO2(Paciente paciente, Proceso proceso, Long plantilla_editor) {
        Connection connection = null;
        Long id = new Long(0);
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT r.id ,c.dato FROM registros  r   "
                    + " LEFT JOIN campos_r c ON r.id=c.registro AND c.estado=? AND c.item=?"
                    + "	WHERE r.paciente = ?  AND r.estado=? AND r.problema= ? " + " AND plantilla_editor=?"
                    + " AND ( dato=0 OR dato  IS null )";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(2, RegistroOxi.ITEM_FECHAFIN);
            statement.setLong(3, paciente.getId());
            statement.setInt(4, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(5, proceso.getId());
            statement.setLong(6, plantilla_editor);
            ResultSet resuSulset = statement.executeQuery();
            if (resuSulset.next()) {
                id = resuSulset.getLong("id");
            }
            statement.close();
            logger.debug("SELECT r.id ,c.dato FROM registros  r   "
                    + " LEFT JOIN campos_r c ON r.id=c.registro AND c.estado=" + Registro.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND c.item=" + RegistroOxi.ITEM_FECHAFIN + "" + "	WHERE r.paciente = " + paciente.getId()
                    + "  AND r.estado=? AND r.problema= " + proceso.getId() + " " + " AND plantilla_editor="
                    + plantilla_editor + "" + " AND ( dato=0 OR dato  IS null )");
        } catch (SQLException e) {
            logger.error("SELECT r.id ,c.dato FROM registros  r   "
                    + " LEFT JOIN campos_r c ON r.id=c.registro AND c.estado=" + Registro.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND c.item=" + RegistroOxi.ITEM_FECHAFIN + "" + "	WHERE r.paciente = " + paciente.getId()
                    + "  AND r.estado=? AND r.problema= " + proceso.getId() + " " + " AND plantilla_editor="
                    + plantilla_editor + "" + " AND ( dato=0 OR dato  IS null )");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return id;
    }

    /**
     * Gets the lista campos registro.
     *
     * @param id the id
     * @return the lista campos registro
     */
    public ArrayList<Campos_r> getListaCamposRegistro(Long id) {
        Connection connection = null;
        ArrayList<Campos_r> listaCampos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *  FROM campos_r  WHERE registro = ?  AND estado=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            ResultSet resuSulset = statement.executeQuery();
            while (resuSulset.next()) {
                Campos_r campo_r = getCamposRResulSet(resuSulset);
                listaCampos.add(campo_r);
            }
            statement.close();
            logger.debug("SELECT *  FROM campos_r  WHERE registro = " + id + "  AND estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
        } catch (SQLException e) {
            logger.error("SELECT *  FROM campos_r  WHERE registro = " + id + "  AND estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaCampos;
    }

    /**
     * Gets the registro resul set.
     *
     * @param rsRegistro the rs registro
     * @return the registro resul set
     */
    public Registro getRegistroResulSet(ResultSet rsRegistro) {

        Long id = null;
        try {
            id = rsRegistro.getLong("plantilla_editor");
            if (id.equals(RegistroMama.PLANTILLLA_EDITOR_REGISTROMAMA)) {
                RegistroMama registro = new RegistroMama();
                registro.setId(rsRegistro.getLong("id"));
                registro.setDescripcion(rsRegistro.getString("descripcion"));
                registro.setPaciente(new PacienteDAO().getPacientePorId(rsRegistro.getLong("paciente"), false));
                registro.setCentro(new CentroDAO().getRegistroId(rsRegistro.getLong("centro")));
                registro.setServicio(new ServiciosDAO().getRegistroId(rsRegistro.getLong("servicio")));
                registro.setFecha(Utilidades.getFechaLocalDate(rsRegistro.getLong("fecha")));
                registro.setHora(rsRegistro.getLong("hora"));
                registro.setEstado(rsRegistro.getInt("estado"));
                registro.setUserid(new UsuarioDAO().getUsuarioUserid(rsRegistro.getString("userid"), false));
                if (rsRegistro.getLong("problema") > 0) {
                    registro.setProblema(new ProcesoDAO().getRegistroId(rsRegistro.getLong("problema"), null));
                }
                if (rsRegistro.getLong("episodio") > 0) {
                    registro.setEpisodio(new EpisodioDAO().getRegistroPorId(rsRegistro.getLong("episodio"),
                            registro.getPaciente(), null, null, null, null, null));
                    // registro.setEpisodio(new
                    // EpisodioDAO().getRegistroPorId(rsRegistro.getLong("episodio")));
                }
                registro.setUseridredactor(
                        new UsuarioDAO().getUsuarioUserid(rsRegistro.getString("useridredactor"), false));
                registro.setUseridtranscriptor(
                        new UsuarioDAO().getUsuarioUserid(rsRegistro.getString("useridtranscriptor"), false));
                registro.setTiporegistro(rsRegistro.getLong("tiporegistro"));
                registro.setCanal(rsRegistro.getLong("canal"));
                return registro;
            }
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        return null;
    }

    /**
     * Gets the campos R resul set.
     *
     * @param rsRegistro the rs registro
     * @return the campos R resul set
     */
    public Campos_r getCamposRResulSet(ResultSet rsRegistro) {
        Campos_r campo = new Campos_r();
        try {
            campo.setId(rsRegistro.getLong("id"));
            campo.setRegistro(rsRegistro.getLong("registro"));
            campo.setItem(rsRegistro.getLong("item"));
            campo.setUserid(rsRegistro.getString("userid"));
            campo.setFecha(rsRegistro.getLong("fecha"));
            campo.setHora(rsRegistro.getLong("hora"));
            campo.setDescripcion(rsRegistro.getString("descripcion"));
            campo.setOrden(rsRegistro.getInt("orden"));
            campo.setUnidades(rsRegistro.getString("unidades"));
            campo.setFlags(rsRegistro.getString("flags"));
            campo.setCodigo(rsRegistro.getString("codigo"));
            campo.setTipo_codigo(rsRegistro.getString("tipo_codigo"));
            campo.setEstado(rsRegistro.getInt("estado"));
            campo.setTipobin(rsRegistro.getInt("tipobin"));
            campo.setDato(rsRegistro.getString("dato"));
            campo.setReferencias(rsRegistro.getString("referencias"));
            campo.setDatobin(rsRegistro.getBlob("datobin"));
            campo.setLateralidad(rsRegistro.getLong("lateralidad"));
            campo.setCanal(rsRegistro.getLong("canal"));

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }

        return campo;
    }

    /**
     * Gets the lista registros.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @param plantilla_editor the plantilla editor
     * @return the lista registros
     */
    public ArrayList<Registro> getListaRegistros(LocalDate desde, LocalDate hasta, Long plantilla_editor,
            Paciente paciente) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = getSql();
            // sql = "SELECT * FROM registros WHERE   plantilla_editor=?  and estado = ? ";
            sql = sql.concat(" AND  r.plantilla_editor=" + plantilla_editor + "  and r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            if (desde != null) {
                sql = sql.concat(" AND r.fecha>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND r.fecha<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            }
            if (paciente != null) {
                sql = sql.concat(" AND r.paciente=" + paciente.getId());
            }
            sql = sql.concat(" ORDER BY  r.fecha DESC, r.hora DESC ");
            //  PreparedStatement statement = connection.prepareStatement(sql);
            // statement.setLong(1, plantilla_editor);
            //statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            //ResultSet resulSet = statement.executeQuery();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Centro centroParam = CentroDAO.getCentroRs(resulSet, null);
                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);
                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);
                listaRegistros.add(getRegistroResulset(resulSet, paciente, null, centroParam, servicioParam, usuarioParam));
            }
            statement.close();
            logger.debug(sql + "  AND plantilla_editor=" + plantilla_editor + "  and estado = "
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " ORDER BY  fecha  ");
        } catch (SQLException e) {
            logger.error(sql + "   AND plantilla_editor=" + plantilla_editor + "  and estado = "
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " ORDER BY  fecha  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    public ArrayList<Registro> getListaRegistros(LocalDate desde, LocalDate hasta, Long plantilla_editor) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            sql = getSql();
            //     sql = "SELECT * FROM registros WHERE   plantilla_editor=?  and estado = ? ";
            sql = sql.concat(" AND r.plantilla_editor=?  and r.estado = ? ");
            if (desde != null) {
                sql = sql.concat(" AND r.fecha>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND r.fecha<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            }
            sql = sql.concat(" ORDER BY  r.fecha ");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, plantilla_editor);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Centro centroParam = CentroDAO.getCentroRs(resulSet, null);
                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);
                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);
                listaRegistros.add(getRegistroResulset(resulSet, null, null, centroParam, servicioParam, usuarioParam));
                //listaRegistros.add(getRegistroResulset(resulSet, null, null, null, null, null));
            }
            statement.close();
            logger.debug(sql + "  AND plantilla_editor=" + plantilla_editor + "  and estado = "
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " ORDER BY  fecha  ");
        } catch (SQLException e) {
            logger.error(sql + "   AND plantilla_editor=" + plantilla_editor + "  and estado = "
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " ORDER BY  fecha  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    public PagiLisReg getPaginacionListaRegistros(LocalDate desde, LocalDate hasta, Long plantilla_editor) {
        Connection connection = null;
        String sql = "";
        String sqllog = "";
        int contador = 0;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        try {
            connection = super.getConexionBBDD();

            sql = " Select count(*) AS casos from registros R WHERE 1=1 ";
            sqllog = sql;
            sql = sql.concat(" AND r.plantilla_editor=?  and r.estado = ? ");
            sqllog = sql.concat(" AND r.plantilla_editor=" + plantilla_editor + "  and r.estado =  " + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            if (desde != null) {
                sql = sql.concat(" AND r.fecha>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
                sqllog = sqllog.concat(" AND r.fecha>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND r.fecha<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
                sqllog = sqllog.concat(" AND r.fecha<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, plantilla_editor);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                contador = resulSet.getInt("casos");
            }
            paginacion.setPrimero(1);
            paginacion.setUltimo(contador);
            paginacion.setRegistrosTotales(contador);
            statement.close();
            logger.debug(sqllog);
        } catch (SQLException e) {
            logger.error(sqllog, e);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paginacion;
    }

    public ArrayList<Registro> getListaRegistrosPaginados(LocalDate desde, LocalDate hasta, Long plantilla_editor, String orden, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();
        String sql = "";
        String sqllog = "";
        int contador = 0;
        try {
            connection = super.getConexionBBDD();

            if (orden.equals("FECHAHORA")) {
                sql = "SELECT  row_number() over (ORDER BY r.fecha,r.hora) as numeroorden," + sqlOraclePacientePaginado;
            } else {
                sql = " SELECT   row_number() over (ORDER BY r.id ) as numeroorden,";
            }
            sql = sql.concat(sqlOraclePacientePaginado);
            sqllog = sql;
            sql = sql.concat(" AND r.plantilla_editor=?  and r.estado = ? ");
            sqllog = sqllog.concat(" AND r.plantilla_editor=" + plantilla_editor + "  and r.estado =  " + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            if (desde != null) {
                sql = sql.concat(" AND r.fecha>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
                sqllog = sqllog.concat(" AND r.fecha>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND r.fecha<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
                sqllog = sqllog.concat(" AND r.fecha<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            }
            if (orden.equals("FECHAHORA")) {
                sql = sql.concat(" ORDER BY  r.fecha,r.hora ");
                sqllog = sqllog.concat(" ORDER BY  r.fecha,r.hora ");
            }
            logger.debug(sqllog);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, plantilla_editor);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Registro registro = new Registro();
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);
                Centro centroParam = CentroDAO.getCentroPrimariaRs(resulSet, null);
                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);
                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);

                registro = getRegistroResulset(resulSet, pacienteParam, null, centroParam, servicioParam, usuarioParam);
                registro.setNumeroOrden(resulSet.getInt("numeroorden"));
                if (paginacion != null) {
                    if (paginacion.getDireccion() == 1) {
                        if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                            listaRegistros.add(registro);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    } else {
                        if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                            listaRegistros.add(registro);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    }
                } else {
                    listaRegistros.add(registro);
                }
            }
            statement.close();
            logger.debug(sqllog);
        } catch (SQLException e) {
            logger.error(sqllog, e);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    /**
     * Gets the lista registros.
     *
     * @param subambito the subambito
     * @return the lista registros
     */
    public ArrayList<Registro> getListaRegistros(Long idPaciente, Long subambito) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            if (subambito.equals(new Long(0))) {
                sql = "SELECT r.*  " + " FROM registros r " + " JOIN problemas p ON p.id=r.problema "
                        + "   WHERE   r.estado = ? AND r.paciente=?  " + "  ORDER BY  fecha DESC, hora DESC  ";
            } else {
                sql = "SELECT r.*  " + " FROM registros r " + " JOIN problemas p ON p.id=r.problema "
                        + "   WHERE   r.estado = ? AND r.paciente=?  " + " AND  p.subambito=" + subambito
                        + " ORDER BY  fecha DESC, hora DESC  ";
            }
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(2, idPaciente);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                listaRegistros.add(getRegistroResulset(resulSet, null, null, null, null, null));
            }
            statement.close();
            logger.debug("SELECT r.*  " + " FROM registros r " + " JOIN problemas p ON p.id=r.problema "
                    + "   WHERE   r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente=" + idPaciente
                    + " " + "ORDER BY  fecha DESC, hora DESC  ");
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    public ArrayList<Registro> getListaRegistros(Long subambito, Paciente paciente) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            sql = "SELECT r.*  " + " FROM registros r " + " JOIN problemas p ON p.id=r.problema "
                    + "   WHERE   p.subambito =? AND  r.estado = ? AND r.paciente=? "
                    + "ORDER BY  fecha DESC, hora DESC  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, subambito);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(3, paciente.getId());
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                listaRegistros.add(getRegistroResulset(resulSet, paciente, null, null, null, null));
            }
            statement.close();
            logger.debug("SELECT r.*  " + " FROM registros r " + " JOIN problemas p ON p.id=r.problema "
                    + "   WHERE   p.subambito =" + subambito + " AND  r.estado = "
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente=paciente.getId() "
                    + "ORDER BY  fecha DESC, hora DESC  ");
        } catch (SQLException e) {
            logger.error("SELECT r.*  " + " FROM registros r " + " JOIN problemas p ON p.id=r.problema "
                    + "   WHERE   p.subambito =" + subambito + " AND  r.estado = "
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente=paciente.getId() "
                    + "ORDER BY  fecha DESC, hora DESC  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    public Registro getRegistroPacienteEpisodio(Paciente paciente, Episodio episodio, Long plantillaEditor) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();

            sql = "SELECT r.*  " + " FROM registros r " + " JOIN episodios e ON e.id=r.episodio " + "   WHERE   e.id ="
                    + episodio.getId() + " AND  r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND r.paciente= " + paciente.getId() + " AND  plantilla_editor=" + plantillaEditor
                    + " ORDER BY  fecha DESC, hora DESC  ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                return getRegistroResulset(resulSet, paciente, null, null, null, null);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(sql, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return null;
    }

    public Registro getRegistroEpisodio(Episodio episodio, Long plantillaEditor) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();

            sql = "SELECT r.*  " + " FROM registros r " + " JOIN episodios e ON e.id=r.episodio " + "   WHERE   e.id ="
                    + episodio.getId() + " AND  r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND r.paciente= " + episodio.getPaciente().getId() + " AND  plantilla_editor=" + plantillaEditor
                    + " ORDER BY  fecha DESC, hora DESC  ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                return getRegistroResulset(resulSet, episodio.getPaciente(), null, null, null, null);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(sql, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return null;
    }

    public ArrayList<Registro> getListaRegistros(Proceso proceso, Long plantillaEditor) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            //   sql = "SELECT r.*   FROM registros r WHERE r.estado=" + Registro.VAR_RESGISTRO_ESTADO_NORMAL
            //         + " AND  r.problema=" + proceso.getId() + " AND plantilla_editor= " + plantillaEditor;
            sql = getSql();
            sql = sql.concat(" AND r.estado=" + Registro.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND  r.problema=" + proceso.getId() + " AND plantilla_editor= " + plantillaEditor);

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Centro centroParam = CentroDAO.getCentroRs(resulSet, null);
                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);
                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);

                listaRegistros.add(getRegistroResulset(resulSet, null, proceso, centroParam, servicioParam, usuarioParam));
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    public ArrayList<Registro> getListaRegistrosEpisodio(Episodio episodio, Long plantillaEditor, String orden) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            //  sql = "SELECT r.*   FROM registros r WHERE  estado= " + Registro.VAR_RESGISTRO_ESTADO_NORMAL
            //        + " AND r.episodio=" + episodio.getId() + " AND plantilla_editor= " + plantillaEditor;
            sql = getSql();
            sql = sql.concat(" AND r.estado= " + Registro.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND r.episodio=" + episodio.getId() + " AND r.plantilla_editor= " + plantillaEditor);
            if (orden!=null &&  orden.endsWith(EpisodioDAO.ORDENFECHAHORA)) {
                sql = sql.concat(" ORDER BY r.fecha,r.hora ");
            } else   if (orden!=null &&  orden.endsWith(EpisodioDAO.ORDENFECHAHORADESC)) {
                sql = sql.concat(" ORDER BY r.fecha,r.hora DESC ");
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Centro centroParam = CentroDAO.getCentroRs(resulSet, null);
                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);
                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);
                listaRegistros.add(getRegistroResulset(resulSet, null, null, centroParam, servicioParam, usuarioParam));
            }
            statement.close();
            logger.debug("getListaRegistrosEpisodio:" + sql);
        } catch (SQLException e) {
            logger.error("getListaRegistrosEpisodio:" + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    public ArrayList<Registro> getListaRegistrosLight(Long subambito, Paciente paciente) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            sql = "SELECT r.*  " + " FROM registros r " + " JOIN problemas p ON p.id=r.problema "
                    + "   WHERE   p.subambito =? AND  r.estado = ? AND r.paciente=? "
                    + " ORDER BY  fecha DESC, hora DESC  ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, subambito);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(3, paciente.getId());
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Registro registro = new Registro();
                registro.setId(resulSet.getLong("id"));
                registro.setProblema(new ProcesoDAO().getRegistroId(resulSet.getLong("problema"), null));
                registro.setDescripcion(resulSet.getString("descripcion"));
                registro.setPaciente(new Paciente(resulSet.getLong("paciente")));
                registro.setFecha(Utilidades.getFechaLocalDate(resulSet.getLong("fecha")));
                registro.setHora(resulSet.getLong("hora"));
                registro.setEstado(resulSet.getInt("estado"));
                registro.setUserid(new Usuario(resulSet.getString("userid")));
                registro.setProblema(new Proceso(resulSet.getLong("problema"), registro.getPaciente(), subambito));
                registro.setTiporegistro(resulSet.getLong("tiporegistro"));
                registro.setCanal(resulSet.getLong("canal"));
                registro.setPlantilla_edior(resulSet.getLong("plantilla_editor"));
                listaRegistros.add(getRegistroResulset(resulSet, paciente, null, null, null, null));
            }
            statement.close();
            logger.info(sql = "SELECT r.*  " + " FROM registros r " + " JOIN problemas p ON p.id=r.problema "
                    + "   WHERE   p.subambito =" + subambito + " AND  r.estado = "
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente= " + paciente.getId()
                    + "ORDER BY  fecha DESC, hora DESC  ");
        } catch (SQLException e) {
            logger.error(sql = "SELECT r.*  " + " FROM registros r " + " JOIN problemas p ON p.id=r.problema "
                    + "   WHERE   p.subambito =" + subambito + " AND  r.estado = "
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente= " + paciente.getId()
                    + "ORDER BY  fecha DESC, hora DESC  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    public ArrayList<Registro> getListaRegistrosPaciTipoEpi(Paciente paciente, Long tipoRegistro, Long episodio) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            sql = "SELECT r.*  " + " FROM registros r   WHERE tiporegistro=? " + "AND  r.estado = ? AND r.paciente=? "
                    + " AND episodio = ?" + " ORDER BY  fecha DESC, hora DESC  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tipoRegistro);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(3, paciente.getId());
            statement.setLong(4, episodio);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Registro registro = new Registro();
                registro.setId(resulSet.getLong("id"));
                registro.setProblema(new ProcesoDAO().getRegistroId(resulSet.getLong("problema"), paciente));
                registro.setDescripcion(resulSet.getString("descripcion"));
                registro.setPaciente(new Paciente(resulSet.getLong("paciente")));
                registro.setFecha(Utilidades.getFechaLocalDate(resulSet.getLong("fecha")));
                registro.setHora(resulSet.getLong("hora"));
                registro.setEstado(resulSet.getInt("estado"));
                registro.setUserid(new Usuario(resulSet.getString("userid")));
                // registro.setProblema(new Proceso(resulSet.getLong("problema"),
                // registro.getPaciente(), subambito));
                registro.setTiporegistro(resulSet.getLong("tiporegistro"));
                registro.setCanal(resulSet.getLong("canal"));
                registro.setPlantilla_edior(resulSet.getLong("plantilla_editor"));
                listaRegistros.add(getRegistroResulset(resulSet, paciente, null, null, null, null));
            }
            statement.close();
            logger.info(sql = "SELECT r.*   FROM registros r     " + "   WHERE   tiporegistro= " + tipoRegistro
                    + " AND  r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente= "
                    + paciente.getId() + " ORDER BY  fecha DESC, hora DESC  ");
        } catch (SQLException e) {
            logger.error(sql = "SELECT r.*   FROM registros r     " + "   WHERE   tiporegistro= " + tipoRegistro
                    + " AND  r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente= "
                    + paciente.getId() + " ORDER BY  fecha DESC, hora DESC  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    public ArrayList<Registro> getListaRegistrosPacidESCRIP(Paciente paciente, String descrip, LocalDate desdeFecha,
            Long tipoRegistro) {
        Connection connection = null;
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        try {
            connection = super.getConexionBBDD();
            sql = "SELECT r.*  " + " FROM registros r   WHERE tiporegistro=? " + "AND  r.estado = ? AND r.paciente=? "
                    + " AND descripcion = ?" + " ORDER BY  fecha DESC, hora DESC  ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tipoRegistro);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(3, paciente.getId());
            statement.setString(4, descrip);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Registro registro = new Registro();
                registro.setId(resulSet.getLong("id"));
                registro.setProblema(new ProcesoDAO().getRegistroId(resulSet.getLong("problema"), paciente));
                registro.setDescripcion(resulSet.getString("descripcion"));
                registro.setPaciente(new Paciente(resulSet.getLong("paciente")));
                registro.setFecha(Utilidades.getFechaLocalDate(resulSet.getLong("fecha")));
                registro.setHora(resulSet.getLong("hora"));
                registro.setEstado(resulSet.getInt("estado"));
                registro.setUserid(new Usuario(resulSet.getString("userid")));
                // registro.setProblema(new Proceso(resulSet.getLong("problema"),
                // registro.getPaciente(), subambito));
                registro.setTiporegistro(resulSet.getLong("tiporegistro"));
                registro.setCanal(resulSet.getLong("canal"));
                registro.setPlantilla_edior(resulSet.getLong("plantilla_editor"));
                listaRegistros.add(getRegistroResulset(resulSet, paciente, null, null, null, null));
            }
            statement.close();
            logger.info(sql = "SELECT r.*   FROM registros r     " + "   WHERE   tiporegistro= " + tipoRegistro
                    + " AND  r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente= "
                    + paciente.getId() + " ORDER BY  fecha DESC, hora DESC  ");
        } catch (SQLException e) {
            logger.error(sql = "SELECT r.*   FROM registros r     " + "   WHERE   tiporegistro= " + tipoRegistro
                    + " AND  r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente= "
                    + paciente.getId() + " ORDER BY  fecha DESC, hora DESC  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaRegistros;
    }

    public String getTextoListaRegistrosPaciTipoEpi(Paciente paciente, Long tipoRegistro, Long episodio) {
        Connection connection = null;
        String texto = "";
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT fecha, hora   FROM registros r   WHERE tiporegistro=? "
                    + "AND  r.estado = ? AND r.paciente=? " + " AND episodio = ?"
                    + " ORDER BY  fecha DESC, hora DESC  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tipoRegistro);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(3, paciente.getId());
            statement.setLong(4, episodio);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                texto = Utilidades.getFechFormatoayyyymmdd(resulSet.getLong("fecha"), Constantes.SEPARADOR_FECHA) + " "
                        + Utilidades.getHoraHH_MM(resulSet.getLong("hora"));
                int total = 0;
                while (resulSet.next()) {
                    total++;
                }
                texto = "Evolutivos " + total + " " + texto;
            } else {
                texto = " Sin evolutivos para  episodio ";
            }
            statement.close();
            logger.info(sql = "SELECT count(*)    FROM registros r     " + "   WHERE   tiporegistro= " + tipoRegistro
                    + " AND  r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente= "
                    + paciente.getId() + " ORDER BY  fecha DESC, hora DESC  ");
        } catch (SQLException e) {
            logger.error(sql = "SELECT count(*)  FROM registros r     " + "   WHERE   tiporegistro= " + tipoRegistro
                    + " AND  r.estado = " + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.paciente= "
                    + paciente.getId() + " ORDER BY  fecha DESC, hora DESC  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return texto;
    }

    public String getCampsoRValorMaxAno(Long item, Long ano) {
        Connection connection = null;
        Long id = new Long(0);
        int val = 1;
        Long desde = ano * 10000 + 100 + 1;
        Long hasta = ano * 10000 + 1200 + 31;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT to_char(dato) as numero FROM campos_r " + " WHERE item=?" + " AND estado=?"
                    + " AND fecha>=? and fecha<=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, item);
            statement.setLong(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(3, desde);
            statement.setLong(4, hasta);
            ResultSet resuSulset = statement.executeQuery();
            while (resuSulset.next()) {
                val++;
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return Integer.toString(val);
    }

    public Variable getCamposRUltimoValorItem(Long item, Paciente paciente) {
        Connection connection = null;
        Variable variable = null;
        try {
            connection = super.getConexionBBDD();
            sql = " select to_char(dato) as valor,c.fecha from campos_r c, registros r  "
                    + "  WHERE r.paciente=? AND c.registro=r.id  AND c.item=? "
                    + " AND c.estado=? AND r.estado=? ORDER BY r.id desc ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            statement.setLong(2, item);
            statement.setLong(3, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            statement.setLong(4, Registro.VAR_RESGISTRO_ESTADO_NORMAL);

            ResultSet resuSulset = statement.executeQuery();
            if (resuSulset.next()) {
                variable = new Variable();
                variable.setValor(resuSulset.getString("valor"));
                variable.setFechaValor(Utilidades.getFechaLocalDate(resuSulset.getLong("fecha")));
            }
            statement.close();
            logger.debug(" select to_char(dato) as valor,c.fecha from campos_r c, registros r  "
                    + "  WHERE r.paciente= " + paciente.getId() + " AND c.registro=r.id  AND c.item=" + item
                    + " AND c.estado=" + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " ORDER BY r.id desc ");
        } catch (SQLException e) {
            logger.error(" select to_char(dato) as valor,c.fecha from campos_r c, registros r  "
                    + "  WHERE r.paciente= " + paciente.getId() + " AND c.registro=r.id  AND c.item=" + item
                    + " AND c.estado=" + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND r.estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " ORDER BY r.id desc ", e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return variable;
    }

    public ArrayList<Paciente> getPartosNhcNinos(Long desde, Long hasta, Paciente madre) {
        Connection connection = null;
        ResultSet resuSulset = null;
        ArrayList<Paciente> listaBebes = new ArrayList<Paciente>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT  p.id,c.item,c.descripcion,to_char(c.dato) as nhc " + " "
                    + " FROM problemas p"
                    + " JOIN registros r ON r.problema=p.id AND r.paciente=p.paciente AND r.plantilla_editor= "
                    + RegistroPartoRecienNacido.PLANTILLLA_EDITOR_PAR_RECIENNACIDO + " AND r.estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " JOIN campos_r c ON c.registro=r.id AND c.estado= "
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL + " AND c.item = "
                    + new RegistroPartoRecienNacido().VAR_PARTO_RN_NUMEROHC.getItem() + " WHERE  p.subambito= "
                    + Proceso.SUBAMBITO_PARTOS + " AND p.fechaini>=" + desde + " AND p.fechaini<= " + hasta
                    + " AND p.paciente=" + madre.getId() + " AND r.paciente=" + madre.getId();

            PreparedStatement statement = connection.prepareStatement(sql);
            resuSulset = statement.executeQuery();
            logger.debug(sql);
            while (resuSulset.next()) {
                Paciente bebe = new PacienteDAO().getPacientePorNhcConMadre(resuSulset.getString("nhc"), false);
                listaBebes.add(bebe);
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaBebes;
    }

    public Integer getNumeroRegistrosPaciProcesoPlantilla(Proceso proceso, Long plantilla) {
        Connection connection = null;
        ResultSet resuSulset = null;
        Integer numFetos = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT  count(*) as casos FROM registros r" + " WHERE  r.problema=" + proceso.getId()
                    + " AND r.paciente= " + proceso.getPaciente().getId() + "  AND r.plantilla_editor= " + plantilla
                    + " AND r.estado=" + Registro.VAR_RESGISTRO_ESTADO_NORMAL;

            PreparedStatement statement = connection.prepareStatement(sql);
            resuSulset = statement.executeQuery();
            logger.debug(sql);
            if (resuSulset.next()) {
                numFetos = resuSulset.getInt("casos");
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return numFetos;
    }

    public ArrayList<RegistroUrgDerivarUrg> getListaRegistrosUrgDeriva(Long plantilla, LocalDate desde, LocalDate hasta, String orden, PagiLisReg paginacion) {
        ArrayList<RegistroUrgDerivarUrg> lista = new ArrayList<>();
        ArrayList<Registro> listaRegistros = new ArrayList<>();
        listaRegistros = new RegistroDAO().getListaRegistrosPaginados(desde, hasta, plantilla, orden, paginacion);

        for (Registro registroSintipo : listaRegistros) {
            RegistroUrgDerivarUrg registroPF = (RegistroUrgDerivarUrg) registroSintipo;
            if (registroPF != null) {
                lista.add(registroPF);
            }
        }
        return lista;
    }

    /**
     * Gets the referencias externas.
     *
     * @param id the id
     * @return the referencias externas
     */
    @Override
    public boolean getReferenciasExternas(Long id) {
        return false;
    }

    /**
     * Graba datos.
     *
     * @param object the object
     * @return true, if successful
     */
    @Override
    public boolean grabaDatos(Object object) {
        return false;
    }

    /**
     * Actualiza datos.
     *
     * @param mensajeparam the mensajeparam
     * @return true, if successful
     */
    @Override
    public boolean actualizaDatos(Object mensajeparam) {
        return false;
    }

    /**
     * Inserta datos.
     *
     * @param mensajeparam the mensajeparam
     * @return true, if successful
     */
    @Override
    public boolean insertaDatos(Object mensajeparam) {
        return false;
    }

    /**
     * Gets the registro id.
     *
     * @param id the id
     * @return the registro id
     */
    @Override
    public Object getRegistroId(Long id) {
        return null;
    }

    /**
     * Gets the sql where.
     *
     * @param cadena the cadena
     * @return the sql where
     */
    @Override
    public String getSqlWhere(String cadena) {
        return null;
    }

    /**
     * Borra datos.
     *
     * @param objeto the objeto
     * @return true, if successful
     */
    @Override
    public boolean borraDatos(Object objeto) {
        return false;
    }

    @Override
    public Object getRegistroResulset(ResultSet rs) {
        // TODO Auto-generated method stub
        return null;
    }

}
