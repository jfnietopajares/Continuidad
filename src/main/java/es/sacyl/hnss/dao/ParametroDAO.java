package es.sacyl.hnss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.PagiLisReg;
import com.jnieto.entity.ParametBBDD;
import com.jnieto.entity.Registro;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Constantes;
import com.jnieto.utilidades.Utilidades;

/**
 * The Class ParametroDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 *
 */
public class ParametroDAO extends ConexionDAO implements InterfaceDAO {

    private String sql;

    private ParametBBDD parametro;

    private ResultSet resulSet;

    private static final Logger logger = LogManager.getLogger(ParametroDAO.class);

    /**
     * Instantiates a new parametro DAO.
     */
    public ParametroDAO() {
        super();
    }

    /**
     * Gets the registro resulset.
     *
     * @param resulSet the resul set
     * @return the registro resulset
     */
    public ParametBBDD getRegistroResulset(ResultSet resulSet) {
        try {
            parametro = new ParametBBDD();
            parametro.setId(resulSet.getLong("id"));
            parametro.setCodigo(resulSet.getString("codigo"));
            parametro.setDescripcion(resulSet.getString("descripcion"));
            parametro.setValor(resulSet.getString("valor"));
            parametro.setCanal(resulSet.getInt("canal"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return parametro;
    }

    /**
     * Gets the registro por id.
     *
     * @param id the id
     * @return the registro por id
     */
    @Override
    public Object getRegistroId(Long id) {
        Connection connection = null;
        parametro = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT *  FROM parametros  WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                parametro = getRegistroResulset(resulSet);
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
        return parametro;
    }

    public Object getRegistroCodigo(String codigo, Long id) {
        Connection connection = null;
        parametro = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT *  FROM parametros  WHERE codigo=? AND id!=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, codigo);
            statement.setLong(2, id);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                parametro = getRegistroResulset(resulSet);
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
        return parametro;
    }

    /**
     * Gets the registro por codigo.
     *
     * @param codigo the codigo
     * @return the registro por codigo
     */
    public ParametBBDD getRegistroPorCodigo(String codigo) {
        Connection connection = null;
        parametro = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT *  FROM parametros  WHERE codigo=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, codigo);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                parametro = getRegistroResulset(resulSet);
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
        return parametro;
    }

    /**
     * Gets the lista registros.
     *
     * @param cadena the cadena
     * @return the lista registros
     */
    public ArrayList<ParametBBDD> getListaRegistros(String cadena) {
        Connection connection = null;
        ArrayList<ParametBBDD> listasArrayList = new ArrayList<ParametBBDD>();
        try {
            connection = super.getConexionBBDD();
            if (cadena == null) {
                sql = "  SELECT *  FROM parametros ORDER by descripcion ";
            } else if (Utilidades.isNumeric(cadena)) {
                // es un numero, id de parametro
                sql = "  SELECT *  FROM parametros  WHERE id= " + Long.parseLong(cadena);

            } else {
                sql = "  SELECT *  FROM parametros  WHERE codigo LIKE '%" + cadena + "%' " + " OR descripcion LIKE '%"
                        + cadena + "%' " + " OR valor LIKE '%" + cadena + "%' ORDER by descripcion ";
            }
            Statement statement = connection.createStatement();
            resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listasArrayList.add(getRegistroResulset(resulSet));
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
        return listasArrayList;
    }

    /**
     * Gets the lista registros filtro.
     *
     * @param filtro the filtro
     * @return the lista registros filtro
     */
    public ArrayList<ParametBBDD> getListaRegistrosFiltro(String filtro) {
        Connection connection = null;
        ArrayList<ParametBBDD> listaParametros = new ArrayList<>();
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
                ParametBBDD parametro = getRegistroResulset(resulSet);
                listaParametros.add(parametro);
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
        return listaParametros;
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
            sql = "SELECT count(*) as numero   FROM parametros  WHERE 1=1 ";
            sql = sql.concat(getSqlWhere(cadena));
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

    /**
     * Gets the lista registros paginados.
     *
     * @param cadena the cadena
     * @param paginacion the paginacion
     * @return the lista registros paginados
     */
    public ArrayList<ParametBBDD> getListaRegistrosPaginados(String cadena, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<ParametBBDD> listaParametros = new ArrayList<>();
        int contador = 0;

        try {
            connection = super.getConexionBBDD();

            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = "SELECT  @rownum:=@rownum+1  as numeroorden ,s.* FROM parametros s,  (SELECT @rownum:=0) r"
                        + "	 WHERE  1=1 ";
            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {
                sql = "SELECT  row_count  as numeroorden ,s.* FROM parametros s WHERE  1=1 ";
            }

            sql = sql.concat(getSqlWhere(cadena));

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            ParametBBDD parametro = new ParametBBDD();
            while (resulSet.next()) {
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        parametro = getRegistroResulset(resulSet);
                        parametro.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaParametros.add(parametro);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        parametro = getRegistroResulset(resulSet);
                        parametro.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaParametros.add(parametro);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                }
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
        return listaParametros;
    }

    /**
     * Graba datos.
     *
     * @param parametro the parametro
     * @return true, if successful
     */
    @Override
    public boolean grabaDatos(Object objeto) {
        ParametBBDD parametro = (ParametBBDD) objeto;
        boolean actualizado = false;
        if (parametro.getId() == 0) {
            actualizado = this.insertaDatos(parametro);
        } else {
            actualizado = this.actualizaDatos(parametro);
        }
        return actualizado;
    }

    /**
     * Actualiza datos.
     *
     * @param parametro the parametro
     * @return true, if successful
     */
    @Override
    public boolean actualizaDatos(Object objeto) {
        Connection connection = null;
        ParametBBDD parametro = (ParametBBDD) objeto;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   parametros SET codigo=?, descripcion=? , valor =?  WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, parametro.getCodigo());
            statement.setString(2, parametro.getDescripcion());
            statement.setString(3, parametro.getValor());
            statement.setLong(4, parametro.getId());
            actualizado = statement.executeUpdate() > 0;
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
        return actualizado;
    }

    /**
     * Inserta datos.
     *
     * @param parametro the parametro
     * @return true, if successful
     */
    @Override
    public boolean insertaDatos(Object objeto) {
        Connection connection = null;
        ParametBBDD parametro = (ParametBBDD) objeto;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("parametros");
            parametro.setId(id);
            sql = " INSERT INTO parametros (id,codigo,descripcion,valor,canal) VALUES (?,?,?,?,?)  ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, parametro.getId());
            statement.setString(2, parametro.getCodigo());
            statement.setString(3, parametro.getDescripcion());
            statement.setString(4, parametro.getValor());
            statement.setLong(5, Registro.CANAL_DEFECTO);

            insertado = statement.executeUpdate() > 0;
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
        return insertado;
    }

    /**
     * Borra datos.
     *
     * @param parametro the parametro
     * @return true, if successful
     */
    @Override
    public boolean borraDatos(Object objeto) {
        Connection connection = null;
        ParametBBDD parametro = (ParametBBDD) objeto;
        boolean borrado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM parametros WHERE id=?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, parametro.getId());
            borrado = statement.executeUpdate() > 0;
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
        return borrado;
    }

    @Override
    public boolean getReferenciasExternas(Long id) {
        return false;
    }

}
