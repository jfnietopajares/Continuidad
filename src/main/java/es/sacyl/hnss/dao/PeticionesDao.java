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

import com.jnieto.entity.PagiLisReg;
import com.jnieto.entity.Peticion;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Constantes;
import com.jnieto.utilidades.Utilidades;

/**
 * The Class PeticionesDao.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class PeticionesDao extends ConexionDAO {

    private String sql;

    private Peticion peticion;

    private static final Logger logger = LogManager.getLogger(PeticionesDao.class);

    /**
     * Instantiates a new peticiones dao.
     */
    public PeticionesDao() {
        super();
    }

    public boolean doEstadoProcesado(Long id) {
        Connection connection = null;
        boolean rowInserted = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   peticion SET estado=? WHERE id=?  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Peticion.ESTADO_PETICIONES_PROCESADAS);
            statement.setLong(2, id);
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
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
     * Gets the por id.
     *
     * @param peticion the peticion
     * @return the por id
     */
    public Peticion getPorId(Peticion peticion) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT * FROM peticiones  WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, peticion.getId());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                peticion = getPeticionDesdeResulset(resulSet);
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return peticion;
    }

    /**
     * Gets the lista peticiones pendientes.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @return the lista peticiones pendientes
     */
    public PagiLisReg getPaginacionPaciente(LocalDate desde, LocalDate hasta) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT   count(*) as numero 	FROM peticiones " + " WHERE fecha>="
                    + Utilidades.getFechaNumeroyyymmddDefecha(desde) + " AND fecha<= "
                    + Utilidades.getFechaNumeroyyymmddDefecha(hasta) + " AND estado ="
                    + Peticion.ESTADO_PETICIONES_PENDIENTES + " AND volante IN (" + Peticion.PLANTILLAS_PETICIONES_ALTAS
                    + ")";

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            if (resulSet.next()) {
                contador = resulSet.getInt("numero");
            }
            paginacion.setPrimero(1);
            paginacion.setUltimo(contador);
            paginacion.setRegistrosTotales(contador);
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
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

    public ArrayList<Peticion> getListaPeticionesPendientes(LocalDate desde, LocalDate hasta, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Peticion> listaPeticiones = new ArrayList<>();
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            /*
			 * sql =
			 * "SELECT * FROM peticiones  WHERE estado =?  AND fecha>=? AND fecha<=? AND volante IN  "
			 * + Peticion.PLANTILLAS_PETICIONES_ALTAS;
			 * 
			 * sql = " SELECT  @rownum:=@rownum+1  as numeroorden , p.* " +
			 * "	FROM peticiones p ,  (SELECT @rownum:=0) R  " +
			 * " WHERE estado =?  AND fecha>=? AND fecha<=? AND volante IN  (?) ";
             */
            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = " SELECT  @rownum:=@rownum+1  as numeroorden , p.* "
                        + "	FROM peticiones p ,  (SELECT @rownum:=0) R  "
                        + " WHERE fecha>=? AND fecha<=? AND volante IN  (?) ";
            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {
                sql = " SELECT row_number() over (ORDER BY id)  as numeroorden , p.* FROM peticiones p   "
                        + " WHERE fecha>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde) + " " + " AND fecha<="
                        + Utilidades.getFechaNumeroyyymmddDefecha(hasta) + " AND volante IN  ("
                        + Peticion.PLANTILLAS_PETICIONES_ALTAS + ") " + " AND estado= "
                        + Peticion.ESTADO_PETICIONES_PENDIENTES + " ORDER BY id";
            }

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            while (resulSet.next()) {
                peticion = getPeticionDesdeResulset(resulSet);
                peticion.setListaVariables(this.getVariablesPeticion(peticion.getId()));
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        peticion.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaPeticiones.add(peticion);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        peticion.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaPeticiones.add(peticion);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                }
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaPeticiones;
    }

    /**
     * Gets the peticion desde resulset.
     *
     * @param resulSet the resul set
     * @return the peticion desde resulset
     */
    public Peticion getPeticionDesdeResulset(ResultSet resulSet) {
        peticion = new Peticion();
        try {
            peticion.setId(resulSet.getLong("id"));
            if (resulSet.getString("userid") != null) {
                peticion.setUserid(new UsuarioDAO().getUsuarioUserid(resulSet.getString("userid"), false));
            }

            peticion.setCentro(new CentroDAO().getRegistroId(resulSet.getLong("centro")));
            peticion.setServicio(new ServiciosDAO().getRegistroId(resulSet.getLong("servicio")));
            peticion.setPaciente(new PacienteDAO().getPacientePorId(resulSet.getLong("paciente"), false));
            peticion.setEpisodio(resulSet.getLong("episodio"));
            peticion.setMotivo(resulSet.getString("motivo"));
            peticion.setEstado(resulSet.getInt("estado"));
            peticion.setUrgente(resulSet.getInt("urgente"));
            peticion.setVolante(resulSet.getLong("volante"));
            peticion.setFecha(Utilidades.getFechaLocalDate(resulSet.getLong("fecha")));
            peticion.setHora(resulSet.getLong("hora"));
            peticion.setNumeromuestra(resulSet.getString("numeromuestra"));
            if (resulSet.getLong("fechaprogramacion") != 0) {
                peticion.setFechaprogramacion(Utilidades.getFechaLocalDate(resulSet.getLong("fechaprogramacion")));
            }

            peticion.setPertenece(resulSet.getLong("pertenece"));
            peticion.setMotivo_cancelacion(resulSet.getString("motivo_cancelacion"));

            if (resulSet.getLong("fecha_muestra") != 0) {
                peticion.setFecha_muestra(Utilidades.getFechaLocalDate(resulSet.getLong("fecha_muestra")));
            }

            peticion.setHora_muestra(resulSet.getLong("hora_muestra"));
            // if (resulSet.getLong("fecha_envio") ) {
            if (resulSet.getLong("fecha_envio") != new Long(0)) {
                peticion.setFecha_envia(Utilidades.getFechaLocalDate(resulSet.getLong("fecha_envio")));
            }

            peticion.setHora_envio(resulSet.getLong("hora_envio"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return peticion;
    }

    /**
     * Gets the variables peticion.
     *
     * @param idPeticion the id peticion
     * @return the variables peticion
     */
    public ArrayList<Variable> getVariablesPeticion(Long idPeticion) {
        Connection connection = null;
        ArrayList<Variable> listaVariables = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT * FROM pruebas  WHERE peticion=? order by id ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idPeticion);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Variable variable = new Variable();
                variable.setDescripcion(resulSet.getString("descripcion"));
                variable.setValor(resulSet.getString("valor"));
                variable.setItem(resulSet.getLong("catalogo"));
                listaVariables.add(variable);
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaVariables;
    }
}
