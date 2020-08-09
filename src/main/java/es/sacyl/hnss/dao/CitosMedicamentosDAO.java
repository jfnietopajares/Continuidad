/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.CitosDiluciones;
import es.sacyl.hnss.entidades.CitosMedicamentos;
import es.sacyl.hnss.entidades.PrActivo;
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
public class CitosMedicamentosDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(CitosMedicamentosDAO.class);
    private static final long serialVersionUID = 1L;

    public CitosMedicamentosDAO() {
        super();
    }

    private CitosMedicamentos getRegistroResulset(ResultSet rs) {
        CitosMedicamentos citosMedicamentos = new CitosMedicamentos();
        try {
            citosMedicamentos.setId(rs.getInt("id"));
            citosMedicamentos.setPr_activo(new PrActivo(rs.getString("pr_activo")));
            citosMedicamentos.setComercial(rs.getString("comercial"));
            citosMedicamentos.setConcentraciono(rs.getString("concentraciono"));
            citosMedicamentos.setEstabilidado(rs.getString("estabilidado"));
            citosMedicamentos.setEstabilidadiv(rs.getString("estabilidadiv"));
            citosMedicamentos.setVesicante(rs.getString("vesicante"));
            citosMedicamentos.setSiglas(rs.getString("siglas"));
            citosMedicamentos.setCsobrante(rs.getString("csobrante"));
            citosMedicamentos.setReconstitucion(rs.getString("reconstitucion"));
            citosMedicamentos.setObservaciones(rs.getString("observaciones"));
            citosMedicamentos.setExtravasacion(rs.getString("extravasacion"));
            citosMedicamentos.setDerrames(rs.getString("derrames"));
            citosMedicamentos.setExposicion(rs.getString("exposicion"));
            citosMedicamentos.setObscsobrante(rs.getString("obscsobrante"));
            citosMedicamentos.setPeligroso(rs.getString("peligroso"));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return citosMedicamentos;
    }

    public CitosMedicamentos getPorId(Integer id) {
        Connection connection = null;
        CitosMedicamentos citosMedicamentos = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM citos_medicamentos WHERE   id=" + id;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                citosMedicamentos = getRegistroResulset(resulSet);
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return citosMedicamentos;
    }

    public CitosMedicamentos getPorComercial(String comercial) {
        Connection connection = null;
        CitosMedicamentos citosMedicamentos = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM citos_medicamentos WHERE   comercial  ='" + comercial + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                citosMedicamentos = getRegistroResulset(resulSet);
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return citosMedicamentos;
    }

    public boolean doGrabaDatos(CitosMedicamentos citosMedicamentos) {
        boolean actualizado = false;

        if (this.getPorId(citosMedicamentos.getId()) == null) {
            actualizado = this.doInsertaDatos(citosMedicamentos);
        } else {
            actualizado = this.doActualizaDatos(citosMedicamentos);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(CitosMedicamentos citosMedicamentos) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        if (citosMedicamentos == null) {
            return false;
        }
        try {
            connection = super.getConexionBBDD();
            citosMedicamentos.setId(super.getSiguienteId("citos_medicamentos"));

            sql = " INSERT INTO  citos_medicamentos "
                    + "( id,pr_activo,comercial,concentraciono,estabilidado,estabilidadiv,vesicante,siglas,csobrante,reconstitucion,observaciones,extravasacion,derrames,exposicion,obscsobrante,peligroso  ) "
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            if (citosMedicamentos.getId() == null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setLong(1, citosMedicamentos.getId());
            }
            //     id,pr_activo,comercial,concentraciono,estabilidado,estabilidadiv,vesicante,siglas,csobrante,
            if (citosMedicamentos.getPr_activo() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, citosMedicamentos.getPr_activo().getCodigo());
            }
            if (citosMedicamentos.getComercial() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, citosMedicamentos.getComercial());
            }
            if (citosMedicamentos.getConcentraciono() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, citosMedicamentos.getConcentraciono());
            }
            if (citosMedicamentos.getEstabilidado() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, citosMedicamentos.getEstabilidado());
            }
            if (citosMedicamentos.getEstabilidadiv() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, citosMedicamentos.getEstabilidadiv());
            }
            if (citosMedicamentos.getVesicante() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, citosMedicamentos.getVesicante());
            }
            if (citosMedicamentos.getSiglas() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, citosMedicamentos.getSiglas());
            }
            if (citosMedicamentos.getCsobrante() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, citosMedicamentos.getCsobrante());
            }
            //  reconstitucion,observaciones,extravasacion,derrames,exposicion,obscsobrante,peligroso              
            if (citosMedicamentos.getReconstitucion() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, citosMedicamentos.getReconstitucion());
            }
            if (citosMedicamentos.getObservaciones() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, citosMedicamentos.getObservaciones());
            }
            if (citosMedicamentos.getExtravasacion() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setString(12, citosMedicamentos.getExtravasacion());
            }
            if (citosMedicamentos.getDerrames() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, citosMedicamentos.getDerrames());
            }
            if (citosMedicamentos.getExposicion() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, citosMedicamentos.getExposicion());
            }
            if (citosMedicamentos.getObscsobrante() == null) {
                statement.setNull(15, Types.VARCHAR);
            } else {
                statement.setString(15, citosMedicamentos.getObscsobrante());
            }
            if (citosMedicamentos.getPeligroso() == null) {
                statement.setNull(16, Types.VARCHAR);
            } else {
                statement.setString(16, citosMedicamentos.getPeligroso());
            }
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public boolean doActualizaDatos(CitosMedicamentos citosMedicamentos) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        if (citosMedicamentos == null) {
            return false;
        }
        try {
            connection = super.getConexionBBDD();
            citosMedicamentos.setId(super.getSiguienteId("citos_medicamentos"));

            sql = " UPDATE    citos_medicamentos SET  "
                    + "pr_activo=?,comercial=?,concentraciono=?,estabilidado=?,estabilidadiv=?"
                    + ",vesicante=?,siglas=?,csobrante=?,reconstitucion=?,observaciones=?,extravasacion=?"
                    + ",derrames=?,exposicion=?,obscsobrante=?,peligroso=? "
                    + " WERE id=?  ) ";

            PreparedStatement statement = connection.prepareStatement(sql);

            //     id,pr_activo,comercial,concentraciono,estabilidado,estabilidadiv,vesicante,siglas,csobrante,
            if (citosMedicamentos.getPr_activo() == null) {
                statement.setNull(1, Types.VARCHAR);
            } else {
                statement.setString(1, citosMedicamentos.getPr_activo().getCodigo());
            }
            if (citosMedicamentos.getComercial() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, citosMedicamentos.getComercial());
            }
            if (citosMedicamentos.getConcentraciono() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, citosMedicamentos.getConcentraciono());
            }
            if (citosMedicamentos.getEstabilidado() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, citosMedicamentos.getEstabilidado());
            }
            if (citosMedicamentos.getEstabilidadiv() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, citosMedicamentos.getEstabilidadiv());
            }
            if (citosMedicamentos.getVesicante() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, citosMedicamentos.getVesicante());
            }
            if (citosMedicamentos.getSiglas() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, citosMedicamentos.getSiglas());
            }
            if (citosMedicamentos.getCsobrante() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, citosMedicamentos.getCsobrante());
            }
            //  reconstitucion,observaciones,extravasacion,derrames,exposicion,obscsobrante,peligroso              
            if (citosMedicamentos.getReconstitucion() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, citosMedicamentos.getReconstitucion());
            }
            if (citosMedicamentos.getObservaciones() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, citosMedicamentos.getObservaciones());
            }
            if (citosMedicamentos.getExtravasacion() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, citosMedicamentos.getExtravasacion());
            }
            if (citosMedicamentos.getDerrames() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setString(12, citosMedicamentos.getDerrames());
            }
            if (citosMedicamentos.getExposicion() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, citosMedicamentos.getExposicion());
            }
            if (citosMedicamentos.getObscsobrante() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, citosMedicamentos.getObscsobrante());
            }
            if (citosMedicamentos.getPeligroso() == null) {
                statement.setNull(15, Types.VARCHAR);
            } else {
                statement.setString(15, citosMedicamentos.getPeligroso());
            }
            if (citosMedicamentos.getId() == null) {
                statement.setNull(16, Types.INTEGER);
            } else {
                statement.setLong(16, citosMedicamentos.getId());
            }
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            LOGGER.error(sql, e);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public boolean doBorraDatos(CitosMedicamentos citosMedicamentoss) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  citos_medicamentos WHERE id=" + citosMedicamentoss.getId();
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
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public ArrayList<CitosMedicamentos> getListaMedicamentos(String texto) {
        Connection connection = null;
        ArrayList<CitosMedicamentos> listaMedicamentos = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM citos_medicamentos WHERE  1=1 ";
            if (texto != null && !texto.isEmpty()) {
                sql = sql.concat(" AND ( pr_activo like'%" + texto + "%'  OR comercial like '%" + texto + "%')");
            }
            sql = sql.concat("ORDER BY base");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaMedicamentos.add(getRegistroResulset(resulSet));
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaMedicamentos;
    }
}
