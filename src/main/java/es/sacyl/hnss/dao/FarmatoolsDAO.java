/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.entidades.Medicamento;
import es.sacyl.hnss.entidades.PrActivo;
import es.sacyl.hnss.entidades.Usuario;
import java.io.Serializable;
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
public class FarmatoolsDAO extends ConexionDAO implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(FarmatoolsDAO.class);
    private static final long serialVersionUID = 1L;

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

     public ArrayList<PrActivo> getListaPrActivos() {
        Connection connection = null;
        ArrayList<PrActivo> listaPrActivos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT  DISTINCT articulos.pr_activo as nombre ,articulos.pr_activo as codigo FROM citos_articulos  as articulos join citos_practivo On citos_practivo.nombre=articulos.pr_activo "
                    + "WHERE   (articulos.fgrupa LIKE 'L01%' OR articulos.fgrupa LIKE 'J02%' OR fgrupa IN ('J05AB','V03AF','L04AA','L03AB','L03AC','V03AF')) "
                    + "AND articulos.fec_baja is null";

           
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                PrActivo prActivo = new PrActivo();
               prActivo.setCodigo(resulSet.getString("codigo"));
                prActivo.setNombre(resulSet.getString("nombre"));
                listaPrActivos.add(prActivo);
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
        return listaPrActivos;
    }
     
        public ArrayList<Medicamento> getListaMediPr(String pr) {
        Connection connection = null;
        ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
             sql =" SELECT codigo,des_farma AS nombre  FROM articulos " +
      			"	WHERE  pr_activo in (SELECT  num_interno FROM  practivo WHERE  nombre ='"+pr+"')  ORDER by des_farma ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setCodigo(resulSet.getString("codigo"));
                medicamento.setNombre(resulSet.getString("nombre"));
                listaMedicamentos.add(medicamento);
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
        return listaMedicamentos;
    }
     
}
