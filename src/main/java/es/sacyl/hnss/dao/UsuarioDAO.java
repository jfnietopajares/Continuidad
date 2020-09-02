/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class UsuarioDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(UsuarioDAO.class);
    private static final long serialVersionUID = 1L;

    public UsuarioDAO() {
        super();
    }

    private Usuario getRegistroResulsetUsuario(ResultSet rs) {
        Usuario usuario = new Usuario();
        try {
            usuario.setCodigoFarmatools(rs.getString("codigo"));
            usuario.setDni(rs.getString("nifdni"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido1(rs.getString("apellid1"));
            usuario.setApellido2(rs.getString("apellid2"));
            usuario.setServicioFarmatols(rs.getString("servicio"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return usuario;
    }

    public ArrayList<Usuario> getListaFarmaceuticos() {
        Connection connection = null;
        ArrayList<Usuario> listaFramaceuticos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT  f.* FROM  farm_facultad f "
                    + "  JOIN farm_ltusu u ON u.cod_medico=f.codigo "
                    + " WHERE SERVICIO='FAR' AND NOT u.usu_desactivado IS NULL";

            sql = sql.concat("  ORDER BY apellid1,apellid2,nombre ");
            LOGGER.debug(sql);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaFramaceuticos.add(getRegistroResulsetUsuario(resulSet));
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaFramaceuticos;
    }

    public Usuario getFarmaceutico(String codigo, String dni) {
        Usuario farmaceutico = null;
        /*
        Sólo busca si pasas algun valor, por que si los dos datos están vacío o a null
        buscaría todos los usuarios y devolvería el primero 
         */
        if ((codigo != null && !codigo.trim().isEmpty()) ) {
            Connection connection = null;
            try {
                connection = super.getConexionBBDD();
                sql = " SELECT  f.* FROM farm_facultad f "
                        + "  JOIN farm_ltusu u ON u.cod_medico=f.codigo "
                        + " WHERE SERVICIO='FAR' AND NOT u.usu_desactivado IS NULL";
                if (codigo != null && !codigo.isEmpty()) {
                    sql = sql.concat(" AND f.codigo=" + codigo.trim());
                }
                if (dni != null && !dni.isEmpty()) {
                    sql = sql.concat(" AND f.dninif='" + dni.trim() + "'");
                }
                //    sql = sql.concat("ORDER BY apellid1,apellid2,nombre ");
                Statement statement = connection.createStatement();
                ResultSet resulSet = statement.executeQuery(sql);
                if (resulSet.next()) {
                    farmaceutico = getRegistroResulsetUsuario(resulSet);
                }
                statement.close();
                LOGGER.debug(sql);
            } catch (SQLException e) {
                LOGGER.error(sql, e);
            } catch (Exception e) {
                LOGGER.error(e);
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
                }
            }
        }
        return farmaceutico;
    }

}
