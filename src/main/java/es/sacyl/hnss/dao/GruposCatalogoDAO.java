package es.sacyl.hnss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.GruposCatalogo;
import com.jnieto.entity.RegistroOxi;
import com.jnieto.ui.NotificacionInfo;

/**
 * The Class GruposCatalogoDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class GruposCatalogoDAO extends ConexionDAO {

    private String sql;

    private static final Logger logger = LogManager.getLogger(GruposCatalogoDAO.class);

    /**
     * Instantiates a new grupos catalogo DAO.
     */
    public GruposCatalogoDAO() {
        super();
    }

    /**
     * Gets the valores por grupo.
     *
     * @param grupo the grupo
     * @return the valores por grupo
     */
    public ArrayList<String> getValoresPorGrupo(String grupo) {
        Connection connection = null;
        ArrayList<String> lista = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT valor   FROM grupos_catalogo WHERE grupo=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, grupo);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                lista.add(res.getString("valor"));
            }
            statement.close();
            logger.debug("SELECT valor   FROM grupos_catalogo WHERE grupo='" + grupo + "'");
        } catch (SQLException e) {
            logger.error("SELECT valor   FROM grupos_catalogo WHERE grupo='" + grupo + "'");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    public ArrayList<String> getRamasPorGrupo(String grupo) {
        Connection connection = null;
        ArrayList<String> lista = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT  DISTINCT rama   FROM  grupos_catalogo WHERE pertenece IS NULL AND  grupo=? ORDER BY rama";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, grupo);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                lista.add(res.getString("rama"));
            }
            statement.close();
            logger.debug("SELECT DISTINCT rama      FROM grupos_catalogo WHERE  pertenece IS NULL AND grupo='" + grupo
                    + "'  ORDER BY rama");
        } catch (SQLException e) {
            logger.error("SELECT DISTINCT rama      FROM grupos_catalogo WHERE  pertenece IS NULL AND  grupo='" + grupo
                    + "'  ORDER BY rama");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    public ArrayList<GruposCatalogo> getItemsGrupoRama(String grupo, String rama) {
        ArrayList<GruposCatalogo> lista = new ArrayList<GruposCatalogo>();
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *   FROM grupos_catalogo WHERE pertenece IS NULL AND grupo=? and rama=? ORDER BY rama,orden,valor";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, grupo);
            statement.setString(2, rama);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                GruposCatalogo gCatalogo = new GruposCatalogo();
                gCatalogo.setDescripcion(res.getString("descripcion"));
                gCatalogo.setGrupo(res.getString("grupo"));
                gCatalogo.setId(res.getLong("id"));
                gCatalogo.setOrden(res.getInt("orden"));
                gCatalogo.setPertence(res.getLong("pertenece"));
                gCatalogo.setValor(res.getString("valor"));
                lista.add(gCatalogo);
            }
            statement.close();
            logger.debug("SELECT *      FROM grupos_catalogo WHERE grupo='" + grupo + "'  AND rama='" + rama
                    + "' ORDER BY rama,orden,valor ");
        } catch (SQLException e) {
            logger.error("SELECT *      FROM grupos_catalogo WHERE grupo='" + grupo + "'  AND rama='" + rama
                    + "' ORDER BY  rama,orden,valor ", e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    public ArrayList<GruposCatalogo> getItemsGrupoRamaPertenece(String grupo, String rama) {
        ArrayList<GruposCatalogo> lista = new ArrayList<GruposCatalogo>();
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *   FROM grupos_catalogo WHERE  NOT pertenece IS NULL AND grupo=? and rama=? ORDER BY rama,valor";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, grupo);
            statement.setString(2, rama);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                GruposCatalogo gCatalogo = new GruposCatalogo();
                gCatalogo.setDescripcion(res.getString("descripcion"));
                gCatalogo.setGrupo(res.getString("grupo"));
                gCatalogo.setId(res.getLong("id"));
                gCatalogo.setOrden(res.getInt("orden"));
                gCatalogo.setPertence(res.getLong("pertenece"));
                gCatalogo.setValor(res.getString("valor"));
                lista.add(gCatalogo);
            }
            statement.close();
            logger.debug("SELECT *      FROM grupos_catalogo WHERE grupo='" + grupo + "'  AND rama='" + rama
                    + "' ORDER BY rama");
        } catch (SQLException e) {
            logger.error("SELECT *      FROM grupos_catalogo WHERE grupo='" + grupo + "'  AND rama='" + rama
                    + "' ORDER BY rama", e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    public ArrayList<String> getValoresGrupoRama(String grupo, String rama) {
        Connection connection = null;
        ArrayList<String> lista = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT  valor    FROM grupos_catalogo WHERE grupo=?  AND rama=? ORDER BY rama";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, grupo);
            statement.setString(2, rama);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                lista.add(res.getString("valor"));
            }
            statement.close();
            logger.debug("SELECT valor      FROM grupos_catalogo WHERE grupo='" + grupo + "'  AND rama='" + rama
                    + "' ORDER BY rama");
        } catch (SQLException e) {
            logger.error("SELECTvalor     FROM grupos_catalogo WHERE grupo='" + grupo + "'  AND rama='" + rama
                    + "' ORDER BY rama");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    /**
     * Gets the id grupo descripion.
     *
     * @param grupo the grupo
     * @param valor the valor
     * @return the id grupo descripion
     */
    public Long getIdGrupoDescripion(String grupo, String valor) {
        Connection connection = null;
        Long id = new Long(0);
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id   FROM grupos_catalogo WHERE grupo=? AND valor=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, grupo);
            statement.setString(2, valor);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                id = res.getLong("id");
            }
            statement.close();
            logger.debug("SELECT id   FROM grupos_catalogo WHERE grupo='" + grupo + "' AND valor='" + valor + "'");
        } catch (SQLException e) {
            logger.error("SELECT id   FROM grupos_catalogo WHERE grupo='" + grupo + "' AND valor='" + valor + "'");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return id;
    }

    /**
     * Gets the id plantilla editor.
     *
     * @param grupo the grupo
     * @param valor the valor
     * @return the id plantilla editor
     */
    /*
	 * En la descripcion de grupos catálogo de la terapia ponemos en primer lugar el
	 * idplantilla_editor
     */
    public Long getIdPlantillaEditor(String grupo, String valor) {
        Connection connection = null;
        Long idPlantillaEditor = new Long(0);
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT descripcion   FROM grupos_catalogo WHERE grupo=? AND valor=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, grupo);
            statement.setString(2, valor);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                String cadena = res.getString("descripcion");
                String[] datos = cadena.split(",");
                idPlantillaEditor = Long.parseLong(datos[0].trim());
            }
            statement.close();
            logger.debug(
                    "SELECT descripcion   FROM grupos_catalogo WHERE grupo='" + grupo + "' AND valor='" + valor + "'");
        } catch (SQLException e) {
            logger.error(
                    "SELECT descripcion   FROM grupos_catalogo WHERE grupo='" + grupo + "' AND valor='" + valor + "'");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return idPlantillaEditor;
    }

    /**
     * Gets the molalidad terapia.
     *
     * @param terapia the terapia
     * @return the molalidad terapia
     */
    public ArrayList<String> getMolalidadTerapia(String terapia) {
        Connection connection = null;
        ArrayList<String> lista = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT valor FROM GRUPOS_CATALOGO WHERE grupo=?  "
                    + "AND pertenece IN ( SELECT id FROM GRUPOS_CATALOGO WHERE grupo=?  and VALOR =? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, RegistroOxi.GRUPOSC_O2_MODALIDAD_TERAPIA);
            statement.setString(2, RegistroOxi.GRUPOSC_O2_TERAPIA);
            statement.setString(3, terapia);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                lista.add(res.getString("valor"));
            }
            statement.close();
            logger.debug("SELECT valor FROM GRUPOS_CATALOGO WHERE grupo='" + RegistroOxi.GRUPOSC_O2_MODALIDAD_TERAPIA
                    + "'  " + " AND pertenece IN ( SELECT id FROM GRUPOS_CATALOGO WHERE grupo='"
                    + RegistroOxi.GRUPOSC_O2_TERAPIA + "'  and VALOR ='" + terapia + "' )");
        } catch (SQLException e) {
            logger.error("SELECT valor FROM GRUPOS_CATALOGO WHERE grupo='" + RegistroOxi.GRUPOSC_O2_MODALIDAD_TERAPIA
                    + "'  " + " AND pertenece IN ( SELECT id FROM GRUPOS_CATALOGO WHERE grupo='"
                    + RegistroOxi.GRUPOSC_O2_TERAPIA + "'  and VALOR ='" + terapia + "' )");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    /**
     * Gets the condicion id plantilla editor.
     *
     * @param grupo the grupo
     * @return the condicion id plantilla editor
     */
    public String getCondicionIdPlantillaEditor(String grupo) {
        Connection connection = null;
        String condicion = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT descripcion   FROM grupos_catalogo WHERE grupo=?  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, grupo);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String cadena = res.getString("descripcion");
                String[] datos = cadena.split(",");
                if (condicion != null) {
                    condicion = condicion.concat(",").concat(datos[0].trim());
                } else {
                    condicion = "(" + datos[0].trim();
                }
            }
            if (condicion != null) {
                condicion = condicion.concat(")");
            }
            statement.close();
            logger.debug("SELECT descripcion   FROM grupos_catalogo WHERE grupo='" + grupo + "'  ");
        } catch (SQLException e) {
            logger.error("SELECT descripcion   FROM grupos_catalogo WHERE grupo='" + grupo + "'  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return condicion;
    }

}
