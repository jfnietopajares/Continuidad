/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.CitosDiluciones;
import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMFormula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author JuanNieto
 */
public class CitosDilucionesDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(CitosDilucionesDAO.class);
    private static final long serialVersionUID = 1L;

    public CitosDilucionesDAO() {
        super();
    }

    private CitosDiluciones getRegistroResulset(ResultSet rs) {
        CitosDiluciones citosDiluciones = new CitosDiluciones();
        try {
            citosDiluciones.setId(rs.getInt("id"));
            citosDiluciones.setBase(rs.getString("base"));
            citosDiluciones.setConcentracion(rs.getString("concentracion"));
            citosDiluciones.setVolumen(rs.getInt("volumen"));
            citosDiluciones.setVolumenu(rs.getString("volumenu"));
            citosDiluciones.setTipovolumen(rs.getString("tipovolumen"));
            citosDiluciones.setPresentacion(rs.getString("presentacion"));
            citosDiluciones.setObservaciones(rs.getString("observaciones"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return citosDiluciones;
    }
public CitosDiluciones getPorId(Integer id) {
        Connection connection = null;
        CitosDiluciones citosDiluciones = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM citos_diluciones WHERE   id=" + id ;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                citosDiluciones= getRegistroResulset(resulSet);
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            if (connection!=null) {
            connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return citosDiluciones;
    }

public CitosDiluciones getPorBase(String  base) {
        Connection connection = null;
        CitosDiluciones citosDiluciones = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM citos_diluciones WHERE   base  ='" + base + "'" ;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                citosDiluciones= getRegistroResulset(resulSet);
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
           if (connection!=null) {
            connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return citosDiluciones;
    }
    public boolean doGrabaDatos(CitosDiluciones citosDiluciones) {
        boolean actualizado = false;

        if (this.getPorId(citosDiluciones.getId()) == null) {
            actualizado = this.doInsertaDatos(citosDiluciones);
        } else {
            actualizado = this.doActualizaDatos(citosDiluciones);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(CitosDiluciones citosDiluciones) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        if (citosDiluciones == null) return false;
        try {
            connection = super.getConexionBBDD();
            citosDiluciones.setId(super.getSiguienteId("citos_diluciones"));
            
            sql = " INSERT INTO  citos_diluciones (id,base,concentracion,volumen,volumenu,tipovolumen,presentacion,observaciones) " 
                 + " VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            if (  citosDiluciones.getId()== null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setLong(1, citosDiluciones.getId());
            }
            if (citosDiluciones.getBase()== null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, citosDiluciones.getBase());
            }
            if (citosDiluciones.getConcentracion() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, citosDiluciones.getConcentracion());
            }
            if (citosDiluciones.getVolumen() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, citosDiluciones.getVolumen());
            }

            if (citosDiluciones.getVolumenu() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, citosDiluciones.getVolumenu());
            }

            if (citosDiluciones.getTipovolumen() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, citosDiluciones.getTipovolumen());
            }
            if (citosDiluciones.getPresentacion() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, citosDiluciones.getPresentacion());
            }
            if (citosDiluciones.getObservaciones() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, citosDiluciones.getObservaciones());
            }
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
           if (connection!=null) {
            connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }
  public boolean doActualizaDatos(CitosDiluciones citosDiluciones) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            citosDiluciones.setId(super.getSiguienteId("citos_diluciones"));
            
            sql = " UPDATE   citos_diluciones  set"
                    + "base=?,concentracion=?,volumen=?,volumenu=?,tipovolumen=?,presentacion=?,observaciones=? "
                    + " WHERE id=?) "; 

            PreparedStatement statement = connection.prepareStatement(sql);

        
            if (citosDiluciones.getBase()== null) {
                statement.setNull(1, Types.VARCHAR);
            } else {
                statement.setString(1, citosDiluciones.getBase());
            }
            if (citosDiluciones.getConcentracion() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, citosDiluciones.getConcentracion());
            }
            if (citosDiluciones.getVolumen() == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, citosDiluciones.getVolumen());
            }

            if (citosDiluciones.getVolumenu() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, citosDiluciones.getVolumenu());
            }

            if (citosDiluciones.getTipovolumen() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, citosDiluciones.getTipovolumen());
            }
            if (citosDiluciones.getPresentacion() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, citosDiluciones.getPresentacion());
            }
            if (citosDiluciones.getObservaciones() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, citosDiluciones.getObservaciones());
            }
                if (citosDiluciones.getId()== null) {
                statement.setNull(8, Types.INTEGER);
            } else {
                statement.setLong(8, citosDiluciones.getId());
            }
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
           if (connection!=null) {
            connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public boolean doBorraDatos(CitosDiluciones citosDiluciones) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  citos_diluciones WHERE id=" + citosDiluciones.getId();
            Statement statement = connection.createStatement();
            insertadoBoolean = statement.execute(sql);
            insertadoBoolean = true;
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
           if (connection!=null) {
            connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

     public ArrayList<CitosDiluciones> getListaDiluciones(String texto) {
        Connection connection = null;
        ArrayList<CitosDiluciones> listaCitosDiluciones = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM citos_diluciones WHERE  1=1 ";
            if (texto != null && !texto.isEmpty()) {
                sql = sql.concat(" AND ( base like'%" + texto + "%')");
            }
            sql = sql.concat("ORDER BY base");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaCitosDiluciones.add(getRegistroResulset(resulSet));
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            if (connection!=null) {
            connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaCitosDiluciones;
    }
}
