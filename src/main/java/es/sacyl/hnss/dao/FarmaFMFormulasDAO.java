/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FarmaFMForma;
import es.sacyl.hnss.entidades.FarmaFMFormula;
import es.sacyl.hnss.entidades.FarmaFMFormulaAutoriza;
import es.sacyl.hnss.entidades.FarmaFMFormulaTipo;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author JuanNieto
 */
public class FarmaFMFormulasDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FarmaFMFormulasDAO.class);

    DateTimeFormatter dateTimeFormatterParser = DateTimeFormatter.ofPattern("yyyyMMdd");

    public FarmaFMFormulasDAO() {
        super();
    }

    private FarmaFMFormula getRegistroResulset(ResultSet rs) {
        FarmaFMFormula farmaFMFormula = new FarmaFMFormula();
        try {
            farmaFMFormula.setNumero(rs.getInt("numero"));
            farmaFMFormula.setNombre(rs.getString("nombre"));
            if (rs.getString("tipo") != null) {
                farmaFMFormula.setTipo(new FarmaFMFormulaTipo(rs.getString("tipo")));
            }
            farmaFMFormula.setEsteril(rs.getString("esteril"));
            farmaFMFormula.setForma(new FarmaFMFormaDAO().getPorCodigo(rs.getString("forma")));
            farmaFMFormula.setVia(new FarmaFMViasAdmDAO().getPorCodigo(rs.getString("via")));
            farmaFMFormula.setUnidades_f(rs.getInt("unidades_f"));
            farmaFMFormula.setIndicacion(rs.getString("indicacion"));
            farmaFMFormula.setConservacion(rs.getString("conservacion"));
            farmaFMFormula.setCaducidad(rs.getString("caducidad"));
            farmaFMFormula.setControles(rs.getString("controles"));
            farmaFMFormula.setDunidades_f(rs.getString("dunidades_f"));
            farmaFMFormula.setIndicacion1(rs.getString("indicacion1"));
            farmaFMFormula.setObservaciones(rs.getString("observaciones"));
            if (rs.getString("p_autorizado") != null) {
                farmaFMFormula.setP_autorizado(new FarmaFMFormulaAutoriza(rs.getString("p_autorizado")));
            }

            farmaFMFormula.setPedirweb(rs.getString("pedirweb"));
            farmaFMFormula.setRealizado(rs.getString("realizado"));
            LocalDate localDate = null;
            if (rs.getInt("fecha_r") != 0) {
                String ulti = Integer.toString(rs.getInt("fecha_r"));
                localDate = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            farmaFMFormula.setFecha_r(localDate);
            farmaFMFormula.setActualizado(rs.getString("actualizado"));

            localDate = null;
            if (rs.getInt("fecha_a") != 0) {
                String ulti = Integer.toString(rs.getInt("fecha_a"));
                localDate = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            farmaFMFormula.setFecha_a(localDate);
            farmaFMFormula.setHoja_paci(rs.getString("hoja_paci"));
            /*
    private Blob hoja_paci_fichero;
             */
            farmaFMFormula.setEtiqueta1(rs.getString("etiqueta1"));
            farmaFMFormula.setEtiqueta2(rs.getString("etiqueta2"));
            farmaFMFormula.setObservaciones1(rs.getString("observaciones1"));
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return farmaFMFormula;
    }

    public FarmaFMFormula getPorCodigo(Integer numero) {
        Connection connection = null;
        FarmaFMFormula farmaFMFormula = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas WHERE   numero=" + numero;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMFormula = getRegistroResulset(resulSet);
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
        return farmaFMFormula;
    }

    public FarmaFMFormula getPorDescripcion(String nombre) {
        Connection connection = null;
        FarmaFMFormula farmaFMFormula = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas WHERE   nombre like '%" + nombre + "%'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                farmaFMFormula = getRegistroResulset(resulSet);
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
        return farmaFMFormula;
    }

    public boolean doGrabaDatos(FarmaFMFormula farmaFMFormula) {
        boolean actualizado = false;

        if (this.getPorCodigo(farmaFMFormula.getNumero()) == null) {
            actualizado = this.doInsertaDatos(farmaFMFormula);
        } else {
            actualizado = this.doActualizaDatos(farmaFMFormula);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FarmaFMFormula farmaFMFormula) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "INSERT INTO     farm_fm_formulas "
                    + "(numero,nombre,tipo,esteril,forma,via,unidades_f,indicacion,conservacion, caducidad,controles "
                    + " ,dunidades_f,indicacion1,observaciones,p_autorizado,pedirweb,realizado,fecha_r,actualizado "
                    + " ,fecha_a,hoja_paci,etiqueta1,etiqueta2,observaciones1 )"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//private Blob hoja_paci_fichero;
            PreparedStatement statement = connection.prepareStatement(sql);

            if (farmaFMFormula == null && farmaFMFormula.getNumero() != null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, farmaFMFormula.getNumero());
            }
            if (farmaFMFormula.getNombre() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, farmaFMFormula.getNombre());
            }
            if (farmaFMFormula.getTipo() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, farmaFMFormula.getTipo().getCodigo());
            }

            if (farmaFMFormula.getEsteril() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, farmaFMFormula.getEsteril().substring(0));
            }
            if (farmaFMFormula.getForma() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, farmaFMFormula.getForma().getCodigo());
            }
            if (farmaFMFormula.getVia() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, farmaFMFormula.getVia().getCodigo());
            }
            if (farmaFMFormula.getUnidades_f() == null) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, farmaFMFormula.getUnidades_f());
            }
            if (farmaFMFormula.getIndicacion() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, farmaFMFormula.getIndicacion());
            }
            if (farmaFMFormula.getConservacion() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, farmaFMFormula.getConservacion());
            }
            if (farmaFMFormula.getCaducidad() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, farmaFMFormula.getCaducidad());
            }
            if (farmaFMFormula.getControles() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, farmaFMFormula.getControles());
            }
            //   + "(numero,nombre,tipo,esteril,forma,via,unidades_f,indicacion,conservacion, caducidad,controles "

            if (farmaFMFormula.getUnidades_f() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setInt(12, farmaFMFormula.getUnidades_f());
            }
            if (farmaFMFormula.getIndicacion1() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, farmaFMFormula.getIndicacion1());
            }
            if (farmaFMFormula.getObservaciones() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, farmaFMFormula.getObservaciones());
            }
            if (farmaFMFormula.getP_autorizado() == null) {
                statement.setNull(15, Types.VARCHAR);
            } else {
                statement.setString(15, farmaFMFormula.getP_autorizado().getCodigo());
            }
            if (farmaFMFormula.getPedirweb() == null) {
                statement.setNull(16, Types.VARCHAR);
            } else {
                statement.setString(16, farmaFMFormula.getPedirweb());
            }
            if (farmaFMFormula.getRealizado() == null) {
                statement.setNull(17, Types.VARCHAR);
            } else {
                statement.setString(17, farmaFMFormula.getRealizado());
            }
            if (farmaFMFormula.getFecha_r() == null) {
                statement.setNull(18, Types.INTEGER);
            } else {
                statement.setString(18, dateTimeFormatterParser.format(farmaFMFormula.getFecha_r()));
            }
            if (farmaFMFormula.getActualizado() == null) {
                statement.setNull(19, Types.VARCHAR);
            } else {
                statement.setString(19, farmaFMFormula.getActualizado());
            }
            //  + " ,dunidades_f,indicacion1,observacines,p_autorizado,pedirweb,realizado,fecha_r,actualizado "
            if (farmaFMFormula.getFecha_a() == null) {
                statement.setNull(20, Types.INTEGER);
            } else {
                statement.setString(20, dateTimeFormatterParser.format(farmaFMFormula.getFecha_a()));
            }

            if (farmaFMFormula.getHoja_paci() == null) {
                statement.setNull(21, Types.VARCHAR);
            } else {
                statement.setString(21, farmaFMFormula.getHoja_paci());
            }
            if (farmaFMFormula.getEtiqueta1() == null) {
                statement.setNull(22, Types.VARCHAR);
            } else {
                statement.setString(22, farmaFMFormula.getEtiqueta1());
            }
            if (farmaFMFormula.getEtiqueta2() == null) {
                statement.setNull(23, Types.VARCHAR);
            } else {
                statement.setString(23, farmaFMFormula.getEtiqueta2());
            }
            if (farmaFMFormula.getObservaciones1() == null) {
                statement.setNull(24, Types.VARCHAR);
            } else {
                statement.setString(24, farmaFMFormula.getObservaciones1());
            }
            //  + " ,fecha_a,hoja_paci,etiqueta1,etiqueta2,observaciones1 "
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

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
        return insertadoBoolean;
    }

    public boolean doActualizaDatos(FarmaFMFormula farmaFMFormula) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = sql = "UPDATE   farm_fm_formulas set "
                    + "nombre=?,tipo=?,esteril=?,forma=?,via=?,unidades_f=?,indicacion=?,conservacion=?, caducidad=?,controles =?"
                    + " ,dunidades_f=?,indicacion1=?,observaciones=?,p_autorizado=?,pedirweb=?,realizado=?,fecha_r=?,actualizado=? "
                    + " ,fecha_a=?,hoja_paci=?,etiqueta1=?,etiqueta2=?,observaciones1=? "
                    + " WHERE numero=?";
//private Blob hoja_paci_fichero;
            PreparedStatement statement = connection.prepareStatement(sql);

            if (farmaFMFormula == null && farmaFMFormula.getNumero() != null) {
                statement.setNull(24, Types.INTEGER);
            } else {
                statement.setInt(24, farmaFMFormula.getNumero());
            }
//nombre=?,tipo=?,esteril=?,forma=?,via=?,unidades_f=?,
            if (farmaFMFormula.getNombre() == null) {
                statement.setNull(1, Types.VARCHAR);
            } else {
                statement.setString(1, farmaFMFormula.getNombre());
            }
            if (farmaFMFormula.getTipo() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, farmaFMFormula.getTipo().getCodigo());
            }
            if (farmaFMFormula.getEsteril() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, farmaFMFormula.getEsteril().substring(0));
            }
            if (farmaFMFormula.getForma() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, farmaFMFormula.getForma().getCodigo());
            }
            if (farmaFMFormula.getVia() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, farmaFMFormula.getVia().getCodigo());
            }
            if (farmaFMFormula.getUnidades_f() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, farmaFMFormula.getUnidades_f());
            }
            //   indicacion=?,conservacion=?, caducidad=?,controles =?"
            if (farmaFMFormula.getIndicacion() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, farmaFMFormula.getIndicacion());
            }
            if (farmaFMFormula.getConservacion() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, farmaFMFormula.getConservacion());
            }
            if (farmaFMFormula.getCaducidad() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, farmaFMFormula.getCaducidad());
            }
            if (farmaFMFormula.getControles() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, farmaFMFormula.getControles());
            }
            //   " ,dunidades_f=?,indicacion1=?,observacines=?,p_autorizado=?,
            if (farmaFMFormula.getUnidades_f() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setInt(11, farmaFMFormula.getUnidades_f());
            }
            if (farmaFMFormula.getIndicacion1() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setString(12, farmaFMFormula.getIndicacion1());
            }
            if (farmaFMFormula.getObservaciones() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, farmaFMFormula.getObservaciones());
            }
            if (farmaFMFormula.getP_autorizado() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, farmaFMFormula.getP_autorizado().getCodigo());
            }
            if (farmaFMFormula.getPedirweb() == null) {
                statement.setNull(15, Types.VARCHAR);
            } else {
                statement.setString(15, farmaFMFormula.getPedirweb());
            }
            if (farmaFMFormula.getRealizado() == null) {
                statement.setNull(16, Types.VARCHAR);
            } else {
                statement.setString(16, farmaFMFormula.getRealizado());
            }
            if (farmaFMFormula.getFecha_r() == null) {
                statement.setNull(17, Types.INTEGER);
            } else {
                statement.setString(17, dateTimeFormatterParser.format(farmaFMFormula.getFecha_r()));
            }
            if (farmaFMFormula.getActualizado() == null) {
                statement.setNull(18, Types.VARCHAR);
            } else {
                statement.setString(18, farmaFMFormula.getActualizado());
            }
            //  + " ,dunidades_f,indicacion1,observacines,p_autorizado,pedirweb,realizado,fecha_r,actualizado "
            if (farmaFMFormula.getFecha_a() == null) {
                statement.setNull(19, Types.INTEGER);
            } else {
                statement.setString(19, dateTimeFormatterParser.format(farmaFMFormula.getFecha_a()));
            }

            if (farmaFMFormula.getHoja_paci() == null) {
                statement.setNull(20, Types.VARCHAR);
            } else {
                statement.setString(20, farmaFMFormula.getHoja_paci());
            }
            if (farmaFMFormula.getEtiqueta1() == null) {
                statement.setNull(21, Types.VARCHAR);
            } else {
                statement.setString(21, farmaFMFormula.getEtiqueta1());
            }
            if (farmaFMFormula.getEtiqueta2() == null) {
                statement.setNull(22, Types.VARCHAR);
            } else {
                statement.setString(22, farmaFMFormula.getEtiqueta2());
            }
            if (farmaFMFormula.getObservaciones1() == null) {
                statement.setNull(23, Types.VARCHAR);
            } else {
                statement.setString(23, farmaFMFormula.getObservaciones1());
            }
            //  + " ,fecha_a,hoja_paci,etiqueta1,etiqueta2,observaciones1 "
            insertadoBoolean = statement.executeUpdate() > 0;
            statement.close();

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
        return insertadoBoolean;
    }

    public boolean doBorraDatos(FarmaFMFormula farmaFMFormula) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_formulas WHERE codigo=" + farmaFMFormula.getNumero();
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
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return insertadoBoolean;
    }

    public ArrayList<FarmaFMFormula> getListaFormulas(String texto) {
        Connection connection = null;
        ArrayList<FarmaFMFormula> listaFormulas = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas WHERE  1=1 ";
            if (texto != null && !texto.isEmpty()) {
                sql = sql.concat(" AND ( nombre like'%" + texto + "%')");
            }
            sql = sql.concat("ORDER BY nombre");
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaFormulas.add(getRegistroResulset(resulSet));
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
        return listaFormulas;
    }
}
