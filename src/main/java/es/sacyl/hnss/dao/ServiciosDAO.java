package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Centro;
import es.sacyl.hnss.entity.Episodio;
import es.sacyl.hnss.entity.EpisodioClase;
import es.sacyl.hnss.entity.PagiLisReg;
import es.sacyl.hnss.entity.Servicio;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * The Class ServiciosDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class ServiciosDAO extends ConexionDAO implements InterfaceDAO {

    private String sql;

    private Servicio servicio;

    private static final Logger logger = LogManager.getLogger(ServiciosDAO.class);

    /**
     * Instantiates a new servicios DAO.
     */
    public ServiciosDAO() {
        super();
    }

    /**
     * Gets the registro resulset.
     *
     * @param resulSet the resul set
     * @return the registro resulset
     */
    public Servicio getRegistroResulset(ResultSet resulSet) {
        Servicio servicio = new Servicio();
        try {
            servicio = new Servicio(resulSet.getLong("id"), resulSet.getString("codigo"),
                    resulSet.getString("descripcion"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        return servicio;
    }

    /**
     * Gets the registro por id.
     *
     * @param id the id
     * @return the registro por id
     */
    @Override
    public Servicio getRegistroId(Long id) {
        Connection connection = null;
        servicio = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *   FROM servicios WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                servicio = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug("SELECT *   FROM servicios WHERE id= " + id);
        } catch (SQLException e) {
            logger.debug("SELECT *   FROM servicios WHERE id= " + id);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        }
        return servicio;
    }

    public Servicio getRegistroCodigo(String codigo, Long id) {
        Connection connection = null;
        servicio = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *   FROM servicios WHERE codigo=? AND id!=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, codigo);
            statement.setLong(2, id);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                servicio = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug("SELECT *   FROM servicios WHERE codigo='" + codigo + "' AND id!= " + id);
        } catch (SQLException e) {
            logger.debug("SELECT *   FROM servicios WHERE codigo='" + codigo + "' AND id!= " + id);
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        }
        return servicio;
    }

    /**
     * Gets the lista registos.
     *
     * @return the lista registos
     */
    public ArrayList<Servicio> getListaRegistos() {
        Connection connection = null;
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id,codigo,descripcion FROM servicios  ORDER BY  descripcion ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                servicio = getRegistroResulset(resulSet);
                listaServicios.add(servicio);
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
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
            }
        }
        return listaServicios;
    }

    public ArrayList<Servicio> getListaRegistos(Centro centro) {
        Connection connection = null;
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT  s.* FROM servicios_centro c " + "JOIN servicios s ON s.id=c.servicio " + "WHERE centro= "
                    + centro.getId() + " ORDER BY descripcion ";

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                servicio = getRegistroResulset(resulSet);
                listaServicios.add(servicio);
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
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
            }
        }
        return listaServicios;
    }

    public ArrayList<Servicio> getListaRegistos(Centro centro, String ambito) {
        Connection connection = null;
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            switch (ambito.toUpperCase()) {
                case "HOS":
                    sql = "SELECT  s.* FROM servicios_centro c " + "JOIN servicios s ON s.id=c.servicio " + "WHERE "
                            + " hospitalizacion=1 AND centro= " + centro.getId() + " ORDER BY descripcion ";
                    break;
                case "CEX":
                    sql = "SELECT  s.* FROM servicios_centro c " + "JOIN servicios s ON s.id=c.servicio " + "WHERE "
                            + " consultas=1 AND centro= " + centro.getId() + " ORDER BY descripcion ";
                    break;
                case "QUI":
                    sql = "SELECT  s.* FROM servicios_centro c " + "JOIN servicios s ON s.id=c.servicio " + "WHERE "
                            + " quirofano=1 AND centro= " + centro.getId() + " ORDER BY descripcion ";
                    break;
                case "URG":
                    sql = "SELECT  s.* FROM servicios_centro c " + "JOIN servicios s ON s.id=c.servicio " + "WHERE "
                            + " urgencias=1 AND centro= " + centro.getId() + " ORDER BY descripcion ";
                    break;
                default:
                    sql = "SELECT  s.* FROM servicios_centro c " + "JOIN servicios s ON s.id=c.servicio " + "WHERE centro= "
                            + centro.getId() + " ORDER BY descripcion ";
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                servicio = getRegistroResulset(resulSet);
                listaServicios.add(servicio);
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
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
            }
        }
        return listaServicios;
    }

    public ArrayList<Servicio> getListaRegistos(Centro centro, EpisodioClase episodioClase) {
        Connection connection = null;
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT  s.* FROM servicios_centro c " + "JOIN servicios s ON s.id=c.servicio " + "WHERE centro= "
                    + centro.getId();
            if (episodioClase == Episodio.CLASE_HOSPITALIZACION) {
                sql = sql.concat(" AND  hospitalizacion = 1");
            } else if (episodioClase == Episodio.CLASE_CONSULTAS) {
                sql = sql.concat(" AND  consultas = 1");
            } else if (episodioClase == Episodio.CLASE_URGENCIAS) {
                sql = sql.concat(" AND  urgencias = 1");
            } else if (episodioClase == Episodio.CLASE_QUI_INTERVENIDO) {
                sql = sql.concat(" AND  quirofano = 1");
            } else if (episodioClase == Episodio.CLASE_QUI_PROGRMACION) {
                sql = sql.concat(" AND  quirofano = 1");
            }

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                servicio = getRegistroResulset(resulSet);
                listaServicios.add(servicio);
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
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
            }
        }
        return listaServicios;
    }

    /**
     * Gets the lista registros filtro.
     *
     * @param filtro the filtro
     * @return the lista registros filtro
     */
    public ArrayList<Servicio> getListaRegistrosFiltro(String filtro) {
        Connection connection = null;
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            if (Utilidades.isNumeric(filtro)) {
                Long id = Long.parseLong(filtro);
                sql = "SELECT id,codigo,descripcion FROM servicios  WHERE id =" + id;
            } else {
                sql = "SELECT id,codigo,descripcion FROM servicios  WHERE codigo LIKE '%" + filtro + "%'   "
                        + " OR descripcion LIKE '%" + filtro + "%'ORDER BY  descripcion ";
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                servicio = getRegistroResulset(resulSet);
                listaServicios.add(servicio);
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
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
            }
        }
        return listaServicios;
    }

    /**
     * Gets the sql where.
     *
     * @param cadena the cadena
     * @return the sql where
     */
    public String getSqlWhere(String cadena) {
        String sqlString = "";
        if (Utilidades.isNumeric(cadena)) {
            Long id = Long.parseLong(cadena);
            sqlString = " AND id =" + id;
        } else if (!cadena.isEmpty()) {
            sqlString = " AND  codigo LIKE '%" + cadena + "%'   " + " OR descripcion LIKE '%" + cadena + "%' ";
        }
        sqlString = sqlString.concat(" ORDER BY descripcion  ");
        return sqlString;
    }

    /**
     * Gets the paginacion registros.
     *
     * @param cadena the cadena
     * @return the paginacion registros
     */
    public PagiLisReg getPaginacionRegistros(String cadena) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) as numero   FROM servicios  WHERE 1=1 ";
            sql = sql.concat(getSqlWhere(cadena));
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
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL);
            }
        }
        return paginacion;
    }

    /**
     * Gets the lista registros paginados.
     *
     * @param cadena the cadena
     * @param paginacion the paginacion
     * @return the lista registros paginados
     */
    public ArrayList<Servicio> getListaRegistrosPaginados(String cadena, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        int contador = 0;

        try {
            connection = super.getConexionBBDD();

        
                sql = "SELECT   row_number() over (ORDER BY descripcion ) as numeroorden ,s.*	FROM servicios s 	WHERE  1=1 ";

            sql = sql.concat(getSqlWhere(cadena));

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            while (resulSet.next()) {
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        servicio = getRegistroResulset(resulSet);
                        servicio.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaServicios.add(servicio);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        servicio = getRegistroResulset(resulSet);
                        servicio.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaServicios.add(servicio);
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
            logger.debug(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL);
        }
        return listaServicios;
    }

    /**
     * Graba datos.
     *
     * @param servicio the servicio
     * @return true, if successful
     */
    public boolean grabaDatos(Servicio servicio) {
        boolean actualizado = false;
        if (servicio.getId() == 0) {
            actualizado = this.insertaDatos(servicio);
        } else {
            actualizado = this.actualizaDatos(servicio);
        }
        return actualizado;
    }

    /**
     * Actualiza datos.
     *
     * @param servicio the servicio
     * @return true, if successful
     */
    public boolean actualizaDatos(Servicio servicio) {
        Connection connection = null;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   servicios SET codigo=?, descripcion=? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, servicio.getCodigo());
            statement.setString(2, servicio.getDescripcion());
            statement.setLong(3, servicio.getId());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE   servicios SET codigo='" + servicio.getCodigo() + "', descripcion='"
                    + servicio.getDescripcion() + "' WHERE id= " + servicio.getId());
        } catch (SQLException e) {
            logger.error(" UPDATE   servicios SET codigo='" + servicio.getCodigo() + "', descripcion='"
                    + servicio.getDescripcion() + "' WHERE id= " + servicio.getId());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL);
            }
        }
        return actualizado;
    }

    /**
     * Inserta datos.
     *
     * @param servicio the servicio
     * @return true, if successful
     */
    public boolean insertaDatos(Servicio servicio) {
        Connection connection = null;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("servicios");
            servicio.setId(id);
            sql = " INSERT INTO servicios (id,codigo,descripcion) VALUES (?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, servicio.getId());
            statement.setString(2, servicio.getCodigo());
            statement.setString(3, servicio.getDescripcion());
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO servicios (id,codigo,descripcion) VALUES (" + servicio.getId() + ",'"
                    + servicio.getCodigo() + "','" + servicio.getDescripcion() + "')  ");
        } catch (SQLException e) {
            logger.error(" INSERT INTO servicios (id,codigo,descripcion) VALUES (" + servicio.getId() + ",'"
                    + servicio.getCodigo() + "','" + servicio.getDescripcion() + "')  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL);
            }
        }
        return insertado;
    }

    /**
     * Borra datos.
     *
     * @param servicio the servicio
     * @return true, if successful
     */
    @Override
    public boolean borraDatos(Object objeto) {
        Connection connection = null;
        Servicio servicio = (Servicio) objeto;
        boolean borrado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM servicios WHERE id=?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, servicio.getId());
            borrado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" DELETE FROM servicios WHERE id=   " + servicio.getId());
        } catch (SQLException e) {
            logger.error(" DELETE FROM servicios WHERE id=   " + servicio.getId());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL);
            }
        }
        return borrado;
    }

    /**
     * Gets the registro por servicio.
     *
     * @param servicio the servicio
     * @return the registro por servicio
     */
    public int getRegistroPorServicio(Servicio servicio) {
        Connection connection = null;
        int casos = 0;
        try {
            connection = super.getConexionBBDD();

            sql = "SELECT count(*) as casos  FROM registros WHERE servicio=?  ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, servicio.getId());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                casos = resulSet.getInt("casos");
            }
            statement.close();
            logger.debug("SELECT count(*) as casos  FROM registros WHERE servicio=  " + servicio.getId());
        } catch (SQLException e) {
            logger.error("SELECT count(*) as casos  FROM registros WHERE servicio=  " + servicio.getId());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return casos;
    }

    /**
     * Gets the por codigo.
     *
     * @param codigo the codigo
     * @return the por codigo
     */
    public Servicio getPorCodigo(String codigo) {
        Connection connection = null;

        try {
            connection = super.getConexionBBDD();

            sql = "SELECT *  FROM servicios WHERE codigo=? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, codigo);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                servicio = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug("SELECT *  FROM servicios WHERE codigo= '" + codigo + "'");
        } catch (SQLException e) {
            logger.error("SELECT *  FROM servicios WHERE codigo= '" + codigo + "'");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return servicio;
    }

    /**
     * Gets the referencias externas.
     *
     * @param idServicio the id servicio
     * @return the referencias externas
     */
    public boolean getReferenciasExternas(Long idServicio) {
        Connection connection = null;
        boolean referencias = false;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id from problemas where servicio=?" + " UNION "
                    + "SELECT id FROM REGISTROS WHERE servicio=? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idServicio);
            statement.setLong(2, idServicio);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                referencias = true;
            }
            statement.close();
            logger.debug("SELECT id from problemas where servicio=" + idServicio + "" + " UNION "
                    + "SELECT id FROM REGISTROS WHERE servicio=" + idServicio + " ");
        } catch (SQLException e) {
            logger.error("SELECT id from problemas where servicio=" + idServicio + "" + " UNION "
                    + "SELECT id FROM REGISTROS WHERE servicio=" + idServicio + " ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL);
            }
        }
        return referencias;
    }

    @Override
    public boolean grabaDatos(Object object) {
        return false;
    }

    @Override
    public boolean actualizaDatos(Object mensajeparam) {
        return false;
    }

    @Override
    public boolean insertaDatos(Object mensajeparam) {
        return false;
    }

    /*
    retorno un servicio a partir de sql
    cuando cruza desde episodios, procesos o alertas
    importante que la sql llame los campos con las descripciones del método
     */
    public static Servicio getServicioRs(ResultSet resulSet, Servicio servicioParam) {
        Servicio servicio = null;
        if (servicioParam != null) {
            servicio = servicioParam;
        } else {
            try {
                servicio = new Servicio();
                servicio.setId(resulSet.getLong("idservicio"));
                servicio.setCodigo(resulSet.getString("codigoservicio"));
                servicio.setDescripcion(resulSet.getString("descservicio"));
            } catch (Exception e) {
                logger.error(Constantes.SQLERRORRESULSET, e);
            }
        }
        return servicio;
    }
}
