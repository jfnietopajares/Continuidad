/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMViasAdm;
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
 * @author JuanNieto
 */
public class FarmatoolsDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FarmatoolsDAO.class);

    public FarmatoolsDAO() {
        super();
    }

    private Usuario getRegistroResulsetUsuario(ResultSet rs) {
        Usuario usuario = new Usuario();
        try {
            usuario.setDni(rs.getString("codigo"));
            usuario.setNombre(rs.getString("nombre"));

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
            sql = " SELECT * FROM farm_fm_viaadm WHERE  1=1 ";

            sql = sql.concat("ORDER BY nombre");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaFramaceuticos.add(getRegistroResulsetUsuario(resulSet));
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaFramaceuticos;
    }

}
