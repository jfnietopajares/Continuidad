package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Municipio;
import es.sacyl.hnss.entity.Provincia;
import es.sacyl.hnss.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * The Class MunicipioDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class MunicipioDAO extends ConexionDAO {

    private String sql;

    private Municipio municipio;

    private static final Logger logger = LogManager.getLogger(MunicipioDAO.class);

    /**
     * Instantiates a new municipio DAO.
     */
    public MunicipioDAO() {
        super();
    }

    public Municipio getRegistroResulset(ResultSet rs, Provincia provincia) {
        Municipio municipio = new Municipio();
        try {
            municipio.setId(rs.getLong("id"));
            municipio.setCodigo(rs.getString("codigo"));
            municipio.setDescripcion(rs.getString("descripcion"));
            if (provincia == null) {
                municipio.setProvincia(new ProvinciasDAO().getPorCodigo(rs.getString("codigo")));
            } else {
                municipio.setProvincia(provincia);
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        return municipio;
    }

    /**
     * Gets the lista municipios.
     *
     * @param provincia the provincia
     * @return the lista municipios
     */
    public ArrayList<Municipio> getListaMunicipios(Provincia provincia) {
        Connection connection = null;
        ArrayList<Municipio> listaMunicipios = new ArrayList<>();
        if (provincia != null) {
            try {
                connection = super.getConexionBBDD();
                sql = "SELECT id,codigo,provincia,descripcion FROM municipios WHERE provincia=?  ORDER BY  descripcion ";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, provincia.getCodigo());
                ResultSet resulSet = statement.executeQuery();
                while (resulSet.next()) {
                    municipio = getRegistroResulset(resulSet, provincia);
                    listaMunicipios.add(municipio);
                }
                statement.close();
                logger.debug("SELECT id,codigo,provincia,descripcion FROM municipios WHERE provincia="
                        + provincia.getCodigo() + "  ORDER BY  descripcion ");
            } catch (SQLException e) {
                logger.error("SELECT id,codigo,provincia,descripcion FROM municipios WHERE provincia="
                        + provincia.getCodigo() + "  ORDER BY  descripcion ");
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
        }
        return listaMunicipios;
    }

    /**
     * Gets the por id.
     *
     * @param id the id
     * @return the por id
     */
    public Municipio getRegistroId(Long id) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id,codigo,provincia,descripcion FROM municipios WHERE id=?  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                municipio = getRegistroResulset(resulSet, null);
            }
            statement.close();
            logger.debug("SELECT id,codigo,provincia,descripcion FROM municipios WHERE id= " + id);
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
        return municipio;
    }

}
