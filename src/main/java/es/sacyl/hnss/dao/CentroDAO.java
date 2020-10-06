package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Centro;
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
 * The Class CentroDao.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class CentroDAO extends ConexionDAO implements InterfaceDAO {

    private String sql;

    private Centro centro;

    private static final Logger logger = LogManager.getLogger(CentroDAO.class);

    /**
     * Instantiates a new centro dao.
     */
    public CentroDAO() {
        super();
    }

    /**
     * Gets the registro resulset.
     *
     * @param rs the rs
     * @return the registro resulset
     */
    @Override
    public Centro getRegistroResulset(ResultSet rs) {
        Centro centro = new Centro();
        try {
            centro = new Centro(rs.getLong("id"), rs.getString("codigo"), rs.getString("descripcion"),
                    rs.getString("nemonico"));
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        }
        return centro;
    }

    /**
     * Gets the lista todoslos registros.
     *
     * @return the lista registros
     */
    public ArrayList<Centro> getListaRegistros() {
        Connection connection = null;
        ArrayList<Centro> listaCentros = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id,codigo,descripcion,nemonico FROM centros  ORDER BY  descripcion ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                centro = getRegistroResulset(resulSet);
                listaCentros.add(centro);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error("Error.", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaCentros;
    }

    public ArrayList<Centro> getListaRegistrosAreaHosCep() {
        Connection connection = null;
        ArrayList<Centro> listaCentros = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id,codigo,descripcion,nemonico FROM centros " + " WHERE area=1 AND (tipo="
                    + Centro.TIPO_CENTRO_HOSPITAL + " OR tipo=" + Centro.TIPO_CENTRO_CEP + " )  ORDER BY  descripcion ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                centro = getRegistroResulset(resulSet);
                listaCentros.add(centro);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error("Error.", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaCentros;
    }

    public ArrayList<Centro> getListaRegistrosAreaAP() {
        Connection connection = null;
        ArrayList<Centro> listaCentros = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id,codigo,descripcion,nemonico FROM centros " + " WHERE area="
                    + Centro.AREA_SALUD_AVILA + " " + "AND tipo="
                    + Centro.TIPO_CENTRO_CS + "   ORDER BY  descripcion ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                centro = getRegistroResulset(resulSet);
                listaCentros.add(centro);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error("Error.", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaCentros;
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
            sqlString = " AND   (codigo LIKE '%" + cadena + "%'   " + " OR descripcion LIKE '%" + cadena + "%' "
                    + " OR nemonico LIKE '%" + cadena + "%'  )";
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
            sql = "SELECT count(*) as numero   FROM centros  WHERE 1=1 ";
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
            logger.error("Error.", e);
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
    public ArrayList<Centro> getListaRegistrosPaginados(String cadena, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Centro> listaCentros = new ArrayList<>();
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
          
                sql = "SELECT  row_number() over (ORDER BY descripcion) as numeroorden ,c.*	FROM centros c	 WHERE  1=1 ";
            sql = sql.concat(getSqlWhere(cadena));

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            Centro centro = new Centro();
            while (resulSet.next()) {
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        centro = getRegistroResulset(resulSet);
                        centro.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaCentros.add(centro);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        centro = getRegistroResulset(resulSet);
                        centro.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaCentros.add(centro);
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
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaCentros;
    }

    /**
     * Gets the por id.
     *
     * @param id the id
     * @return the por id
     */
    public Centro getRegistroId(Long id) {
        Connection connection = null;
        centro = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *   FROM centros  WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                centro = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug("SELECT *   FROM centros  WHERE id=" + id);
        } catch (SQLException e) {
            logger.debug("SELECT *   FROM centros  WHERE id=" + id);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return centro;
    }

    /**
     * Gets the registro codigo.
     *
     * Valida que el código de centro no este repetido en la bbdd
     *
     * @param codigo the codigo
     * @param id the id
     * @return the registro codigo
     */
    public Centro getRegistroCodigo(String codigo, Long id) {
        Connection connection = null;
        centro = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *   FROM centros  WHERE codigo=? AND (id != ? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, codigo);
            statement.setLong(2, id);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                centro = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug("SELECT *   FROM centros  WHERE codigo='" + codigo + "' AND (id != " + id + " )");
        } catch (SQLException e) {
            logger.error("SELECT *   FROM centros  WHERE codigo='" + codigo + "' AND (id != " + id + " )");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return centro;
    }

    public Centro getRegistroDescrip(String descripcion) {
        Connection connection = null;
        centro = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *   FROM centros  WHERE descripcion=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, descripcion.trim());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                centro = getRegistroResulset(resulSet);
            }
            statement.close();
            logger.debug("SELECT *   FROM centros  WHERE descripcion='" + descripcion + "'");
        } catch (SQLException e) {
            logger.error("SELECT *   FROM centros  WHERE descripcion='" + descripcion + "'");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return centro;
    }

    /**
     * Gets the registro por centro.
     *
     * @param centro the centro
     * @return the registro por centro
     */
    public int getRegistroPorCentro(Centro centro) {
        Connection connection = null;
        int casos = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) as casos  FROM registros WHERE centro=?  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, centro.getId());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                casos = resulSet.getInt("casos");
            }
            statement.close();
            logger.debug("SELECT count(*) as casos  FROM registros WHERE centro= " + centro.getId() + "  ");
        } catch (SQLException e) {
            logger.error("SELECT count(*) as casos  FROM registros WHERE centro= " + centro.getId() + "  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error("Error.", e);
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
     * Borra datos.
     *
     * @param centro the centro
     * @return true, if successful
     */
    @Override
    public boolean borraDatos(Object objecto) {
        Connection connection = null;
        Centro centro = (Centro) objecto;
        boolean borrado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM centros WHERE id=?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, centro.getId());
            borrado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" DELETE FROM centros WHERE id=" + centro.getId() + "  ");
        } catch (SQLException e) {
            logger.error(" DELETE FROM centros WHERE id=" + centro.getId() + "  ");
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
        return borrado;
    }

    /**
     * Graba datos.
     *
     * @param centroparam the centroparam
     * @return true, if successful
     */
    @Override
    public boolean grabaDatos(Object centroparam) {
        this.centro = (Centro) centroparam;
        boolean actualizado = false;
        if (centro.getId() == 0) {
            actualizado = this.insertaDatos(centro);
        } else {
            actualizado = this.actualizaDatos(centro);
        }
        return actualizado;
    }

    /**
     * Actualiza datos.
     *
     * @param centroparam the centroparam
     * @return true, if successful
     */
    @Override
    public boolean actualizaDatos(Object centroparam) {
        Connection connection = null;
        this.centro = (Centro) centroparam;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   centros SET codigo=?, descripcion=?, nemonico=? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, centro.getCodigo());
            statement.setString(2, centro.getDescripcion());
            statement.setString(3, centro.getNemonico());
            statement.setLong(4, centro.getId());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(
                    " UPDATE   centros SET codigo='" + centro.getCodigo() + "', descripcion='" + centro.getDescripcion()
                    + "', nemonico='" + centro.getNemonico() + "' WHERE id=" + centro.getId() + " ");
        } catch (SQLException e) {
            logger.error(
                    " UPDATE   centros SET codigo='" + centro.getCodigo() + "', descripcion='" + centro.getDescripcion()
                    + "', nemonico='" + centro.getNemonico() + "' WHERE id=" + centro.getId() + " ");
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
     * @param centroparam the centroparam
     * @return true, if successful
     */
    @Override
    public boolean insertaDatos(Object centroparam) {
        Connection connection = null;
        this.centro = (Centro) centroparam;
        Long id = null;
        boolean insertado = false;
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("centros");
            centro.setId(id);
            sql = " INSERT INTO centros (id,codigo,descripcion,nemonico) VALUES (?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, centro.getId());
            statement.setString(2, centro.getCodigo());
            statement.setString(3, centro.getDescripcion());
            statement.setString(4, centro.getNemonico());
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO centros (id,codigo,descripcion,nemonico) VALUES (" + centro.getId() + ",'"
                    + centro.getCodigo() + "','" + centro.getDescripcion() + "','" + centro.getNemonico() + "')  ");
        } catch (SQLException e) {
            logger.error(" INSERT INTO centros (id,codigo,descripcion,nemonico) VALUES (" + centro.getId() + ",'"
                    + centro.getCodigo() + "','" + centro.getDescripcion() + "','" + centro.getNemonico() + "')  ");
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
     * Gets the referencias externas.
     *
     * @param idcentro the idcentro
     * @return the referencias externas
     */
    @Override
    public boolean getReferenciasExternas(Long idcentro) {
        Connection connection = null;
        boolean referencias = false;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id from problemas where centro=?" + " UNION " + "SELECT id FROM REGISTROS WHERE centro=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idcentro);
            statement.setLong(2, idcentro);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                referencias = true;
            }
            statement.close();
            logger.debug("SELECT id from problemas where centro=" + idcentro + " " + " UNION "
                    + "SELECT id FROM REGISTROS WHERE centro=" + idcentro + " ");
        } catch (SQLException e) {
            logger.error("SELECT id from problemas where centro=" + idcentro + " " + " UNION "
                    + "SELECT id FROM REGISTROS WHERE centro=" + idcentro + " ");
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

    /*
        Crea un centro a partir de rs de un select combinada de episodios, procesos, alertas ...
        Importaten que los campos de la selec se llamen como indica
     */
    public static Centro getCentroRs(ResultSet resulSet, Centro centroParam) {
        Centro centro = null;
        try {
            if (centroParam == null) {
                centro = new Centro(resulSet.getLong("idcentro"), resulSet.getString("codigocentro"),
                        resulSet.getString("descentro"), resulSet.getString("nemonico"));
            } else {
                centro = centroParam;
            }
        } catch (Exception e) {
            logger.error(Constantes.SQLERRORRESULSET, e);
        }
        return centro;
    }

    public static Centro getCentroPrimariaRs(ResultSet resulSet, Centro centroParam) {
        Centro centro = null;
        try {

            if (centroParam == null) {
                centro = new Centro(resulSet.getLong("idcentropri"),
                        resulSet.getString("codigocentropri"),
                        resulSet.getString("descentropri"),
                        resulSet.getString("nemonicopri"));
            } else {
                centro = centroParam;
            }
        } catch (Exception e) {
            logger.error(Constantes.SQLERRORRESULSET, e);
        }
        return centro;
    }

}
