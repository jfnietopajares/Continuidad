package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Funcionalidad;
import es.sacyl.hnss.entity.PagiLisReg;
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
 * The Class FuncionalidadDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class FuncionalidadDAO extends ConexionDAO implements InterfaceDAO {

    private String sql;

    private Funcionalidad funcionalidad;

    private static final Logger logger = LogManager.getLogger(FuncionalidadDAO.class);

    /**
     * Instantiates a new funcionalidad DAO.
     */
    public FuncionalidadDAO() {
        super();
    }

    /**
     * Gets the registro resulset.
     *
     * @param resulSet the resul set
     * @return the registro resulset
     */
    @Override
    public Funcionalidad getRegistroResulset(ResultSet resulSet) {
        Funcionalidad funcionalidad = new Funcionalidad();
        try {
            funcionalidad = new Funcionalidad(resulSet.getLong("id"), resulSet.getString("descripcion"), false);
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        return funcionalidad;
    }

    /**
     * Gets the registro por id.
     *
     * @param id the id
     * @return the registro por id
     */
    @Override
    public Funcionalidad getRegistroId(Long id) {
        Connection connection = null;
        funcionalidad = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *   FROM funcionalidades WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                funcionalidad = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug("SELECT *   FROM funcionalidades WHERE id= " + id);
        } catch (SQLException e) {
            logger.error("SELECT *   FROM funcionalidades WHERE id= " + id);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return funcionalidad;
    }

    /**
     * Gets the lista registros.
     *
     * @return the lista registros
     */
    public ArrayList<Funcionalidad> getListaRegistros() {
        Connection connection = null;
        ArrayList<Funcionalidad> listaFuncionalidades = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id,descripcion FROM funcionalidades  ORDER BY  descripcion ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                funcionalidad = getRegistroResulset(resulSet);
                listaFuncionalidades.add(funcionalidad);
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
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaFuncionalidades;
    }

    /**
     * Gets the lista registros filtro.
     *
     * @param filtro the filtro
     * @return the lista registros filtro
     */
    public ArrayList<Funcionalidad> getListaRegistrosFiltro(String filtro) {
        Connection connection = null;
        ArrayList<Funcionalidad> listaFuncionalidades = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            if (Utilidades.isNumeric(filtro)) {
                Long id = Long.parseLong(filtro);
                sql = "SELECT id,descripcion  FROM funcionalidades  WHERE  id=" + id + " ORDER BY  descripcion ";

            } else {
                sql = "SELECT id,descripcion  FROM funcionalidades  WHERE descripcion like '%" + filtro
                        + "%' ORDER BY  descripcion ";
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                funcionalidad = getRegistroResulset(resulSet);
                listaFuncionalidades.add(funcionalidad);
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
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaFuncionalidades;
    }

    /**
     * Gets the sql where.
     *
     * @param cadena the cadena
     * @return the sql where
     */
    @Override
    public String getSqlWhere(String cadena) {
        String sqlString = "";
        if (Utilidades.isNumeric(cadena)) {
            Long id = Long.parseLong(cadena);
            sqlString = " AND id =" + id;
        } else if (!cadena.isEmpty()) {
            sqlString = " AND  descripcion LIKE '%" + cadena + "%' ";
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
            sql = "SELECT count(*) as numero   FROM funcionalidades  WHERE 1=1 ";
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
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
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
    public ArrayList<Funcionalidad> getListaRegistrosPaginados(String cadena, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Funcionalidad> listaFuncionalidades = new ArrayList<>();
        int contador = 0;

        try {
            connection = super.getConexionBBDD();
        
                sql = "SELECT row_number() over (ORDER BY descripcion) as numeroorden ,s.* FROM funcionalidades s  WHERE  1=1 ";
            sql = sql.concat(getSqlWhere(cadena));

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            Funcionalidad funcionalidad = new Funcionalidad();
            while (resulSet.next()) {
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        funcionalidad = getRegistroResulset(resulSet);
                        funcionalidad.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaFuncionalidades.add(funcionalidad);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        funcionalidad = getRegistroResulset(resulSet);
                        funcionalidad.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaFuncionalidades.add(funcionalidad);
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
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaFuncionalidades;
    }

    /**
     * Gets the lista registros solo nombre.
     *
     * @return the lista registros solo nombre
     */
    public ArrayList<String> getListaRegistrosSoloNombre() {
        Connection connection = null;
        ArrayList<String> listaFuncionalidades = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id,descripcion FROM funcionalidades  ORDER BY  descripcion ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaFuncionalidades.add(resulSet.getString("descripcion"));
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
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaFuncionalidades;
    }

    /**
     * Gets the lista funcionalidades filtro.
     *
     * @param filtro the filtro
     * @return the lista funcionalidades filtro
     */
    public ArrayList<Funcionalidad> getListaFuncionalidadesFiltro(String filtro) {
        Connection connection = null;
        ArrayList<Funcionalidad> listaFuncionalidades = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();

            if (Utilidades.isNumeric(filtro)) {
                // es un numero, código del centro
                Long id = Long.parseLong(filtro);
                sql = "SELECT id,descripcion  FROM funcionalidades  WHERE  id=" + id + " ORDER BY  descripcion ";

            } else {
                sql = "SELECT id,descripcion  FROM funcionalidades  WHERE descripcion like '%" + filtro
                        + "%' ORDER BY  descripcion ";
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                funcionalidad = getRegistroResulset(resulSet);
                listaFuncionalidades.add(funcionalidad);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaFuncionalidades;
    }

    /**
     * Gets the por descripcion.
     *
     * @param descripcion the descripcion
     * @return the por descripcion
     */
    public Funcionalidad getPorDescripcion(String descripcion) {
        Connection connection = null;
        funcionalidad = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *   FROM funcionalidades WHERE descripcion=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, descripcion);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                funcionalidad = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug("SELECT *   FROM funcionalidades WHERE descripcion= '" + descripcion + "'");
        } catch (SQLException e) {
            logger.error("SELECT *   FROM funcionalidades WHERE descripcion= '" + descripcion + "'");
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return funcionalidad;
    }

    /**
     * Graba datos.
     *
     * @param funcionalidad the funcionalidad
     * @return true, if successful
     */
    @Override
    public boolean grabaDatos(Object objeto) {
        Funcionalidad funcionalidad = (Funcionalidad) objeto;
        boolean actualizado = false;
        if (funcionalidad.getId().equals(new Long(0))) {
            actualizado = this.insertaDatos(funcionalidad);
        } else {
            actualizado = this.actualizaDatos(funcionalidad);
        }
        return actualizado;
    }

    /**
     * Actualiza datos.
     *
     * @param funcionalidad the funcionalidad
     * @return true, if successful
     */
    @Override
    public boolean actualizaDatos(Object objeto) {
        Connection connection = null;
        Funcionalidad funcionalidad = (Funcionalidad) objeto;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   funcionalidades SET  descripcion=? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, funcionalidad.getDescripcion());
            statement.setLong(2, funcionalidad.getId());
            actualizado = statement.executeUpdate() > 0;
            actualizado = true;
            statement.close();
            logger.debug(" UPDATE   funcionalidades SET  descripcion='" + funcionalidad.getDescripcion()
                    + "' WHERE id= " + funcionalidad.getId());
        } catch (SQLException e) {
            logger.error(" UPDATE   funcionalidades SET  descripcion='" + funcionalidad.getDescripcion()
                    + "' WHERE id= " + funcionalidad.getId());
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
        return actualizado;
    }

    /**
     * Inserta datos.
     *
     * @param funcionalidad the funcionalidad
     * @return true, if successful
     */
    @Override
    public boolean insertaDatos(Object objeto) {
        Connection connection = null;
        Funcionalidad funcionalidad = (Funcionalidad) objeto;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("funcionalidades");
            funcionalidad.setId(id);
            sql = " INSERT INTO funcionalidades (id,descripcion) VALUES (?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, funcionalidad.getId());
            statement.setString(2, funcionalidad.getDescripcion());
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO funcionalidades (id,descripcion) VALUES (" + funcionalidad.getId() + ",'"
                    + funcionalidad.getDescripcion() + "')  ");
            insertado = true;
        } catch (SQLException e) {
            logger.error(" INSERT INTO funcionalidades (id,descripcion) VALUES (" + funcionalidad.getId() + ",'"
                    + funcionalidad.getDescripcion() + "')  ");
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
        return insertado;
    }

    /**
     * Borra datos.
     *
     * @param funcionalidad the funcionalidad
     * @return true, if successful
     */
    @Override
    public boolean borraDatos(Object objeto) {
        Connection connection = null;
        Funcionalidad funcionalidad = (Funcionalidad) objeto;
        boolean borrado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM funcionalidades WHERE id=?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, funcionalidad.getId());
            borrado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" DELETE FROM funcionalidades WHERE id= " + funcionalidad.getId());
        } catch (SQLException e) {
            logger.error(" DELETE FROM funcionalidades WHERE id= " + funcionalidad.getId());
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
        return borrado;
    }

    /**
     * Gets the referencias externas.
     *
     * @param idFuncionalidad the id funcionalidad
     * @return the referencias externas
     */
    @Override
    public boolean getReferenciasExternas(Long idFuncionalidad) {
        Connection connection = null;
        boolean referencias = false;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT funcionalidad from usr_funcionalidades where funcionalidad=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idFuncionalidad);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                referencias = true;
            }
            statement.close();
            logger.debug("SELECT funcionalidad from usr_funcionalidades where funcionalidad=" + idFuncionalidad);
        } catch (SQLException e) {
            logger.error("SELECT funcionalidad from usr_funcionalidades where funcionalidad=" + idFuncionalidad);
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
        return referencias;
    }

}
