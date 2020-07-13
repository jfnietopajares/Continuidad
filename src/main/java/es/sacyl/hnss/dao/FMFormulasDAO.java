/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaAutoriza;
import es.sacyl.hnss.entidades.FMFormulaTipo;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMViasAdm;
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
public class FMFormulasDAO extends ConexionDAO {

    private static final Logger LOGGER = LogManager.getLogger(FMFormulasDAO.class);

    DateTimeFormatter dateTimeFormatterParser = DateTimeFormatter.ofPattern("yyyyMMdd");

    public FMFormulasDAO() {
        super();
    }

    private FMFormula getRegistroResulset(ResultSet rs) {
        FMFormula fMFormula = new FMFormula();
        try {
            fMFormula.setNumero(rs.getInt("numero"));
            fMFormula.setNombre(rs.getString("nombre"));
            if (rs.getString("tipo") != null) {
                fMFormula.setTipo(new FMFormulaTipo(rs.getString("tipo")));
            }
            fMFormula.setEsteril(rs.getString("esteril"));
            fMFormula.setForma(new FMFormaDAO().getPorCodigo(rs.getString("forma")));
            fMFormula.setVia(new FMViasAdmDAO().getPorCodigo(rs.getString("via")));
            fMFormula.setUnidades_f(rs.getInt("unidades_f"));
            fMFormula.setIndicacion(rs.getString("indicacion"));
            fMFormula.setConservacion(rs.getString("conservacion"));
            fMFormula.setCaducidad(rs.getString("caducidad"));
            fMFormula.setControles(rs.getString("controles"));
            fMFormula.setDunidades_f(rs.getString("dunidades_f"));
            fMFormula.setIndicacion1(rs.getString("indicacion1"));
            fMFormula.setObservaciones(rs.getString("observaciones"));
            if (rs.getString("p_autorizado") != null) {
                fMFormula.setP_autorizado(new FMFormulaAutoriza(rs.getString("p_autorizado")));
            }

            fMFormula.setPedirweb(rs.getString("pedirweb"));
            fMFormula.setRealizado(rs.getString("realizado"));
            LocalDate localDate = null;
            if (rs.getInt("fecha_r") != 0) {
                String ulti = Integer.toString(rs.getInt("fecha_r"));
                localDate = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            fMFormula.setFecha_r(localDate);
            fMFormula.setActualizado(rs.getString("actualizado"));

            localDate = null;
            if (rs.getInt("fecha_a") != 0) {
                String ulti = Integer.toString(rs.getInt("fecha_a"));
                localDate = LocalDate.parse(ulti, dateTimeFormatterParser);
            }
            fMFormula.setFecha_a(localDate);
            fMFormula.setHoja_paci(rs.getString("hoja_paci"));
            /*
    private Blob hoja_paci_fichero;
             */
            fMFormula.setEtiqueta1(rs.getString("etiqueta1"));
            fMFormula.setEtiqueta2(rs.getString("etiqueta2"));
            fMFormula.setObservaciones1(rs.getString("observaciones1"));
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return fMFormula;
    }

    public FMFormula getPorCodigo(Integer numero) {
        Connection connection = null;
        FMFormula fMFormula = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas WHERE   numero=" + numero;
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormula = getRegistroResulset(resulSet);
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
        return fMFormula;
    }

    public FMFormula getPorDescripcion(String nombre) {
        Connection connection = null;
        FMFormula fMFormula = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT * FROM farm_fm_formulas WHERE   nombre like '%" + nombre + "%'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                fMFormula = getRegistroResulset(resulSet);
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
        return fMFormula;
    }

    public boolean doGrabaDatos(FMFormula fMFormula) {
        boolean actualizado = false;

        if (this.getPorCodigo(fMFormula.getNumero()) == null) {
            actualizado = this.doInsertaDatos(fMFormula);
        } else {
            actualizado = this.doActualizaDatos(fMFormula);
        }
        return actualizado;
    }

    public boolean doInsertaDatos(FMFormula fMFormula) {
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

            if (fMFormula == null && fMFormula.getNumero() != null) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, fMFormula.getNumero());
            }
            if (fMFormula.getNombre() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, fMFormula.getNombre());
            }
            if (fMFormula.getTipo() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, fMFormula.getTipo().getCodigo());
            }

            if (fMFormula.getEsteril() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, fMFormula.getEsteril().substring(0));
            }
            if (fMFormula.getForma() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, fMFormula.getForma().getCodigo());
            }
            if (fMFormula.getVia() == null) {
                statement.setNull(6, Types.VARCHAR);
            } else {
                statement.setString(6, fMFormula.getVia().getCodigo());
            }
            if (fMFormula.getUnidades_f() == null) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, fMFormula.getUnidades_f());
            }
            if (fMFormula.getIndicacion() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, fMFormula.getIndicacion());
            }
            if (fMFormula.getConservacion() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, fMFormula.getConservacion());
            }
            if (fMFormula.getCaducidad() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, fMFormula.getCaducidad());
            }
            if (fMFormula.getControles() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setString(11, fMFormula.getControles());
            }
            //   + "(numero,nombre,tipo,esteril,forma,via,unidades_f,indicacion,conservacion, caducidad,controles "

            if (fMFormula.getUnidades_f() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setInt(12, fMFormula.getUnidades_f());
            }
            if (fMFormula.getIndicacion1() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, fMFormula.getIndicacion1());
            }
            if (fMFormula.getObservaciones() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, fMFormula.getObservaciones());
            }
            if (fMFormula.getP_autorizado() == null) {
                statement.setNull(15, Types.VARCHAR);
            } else {
                statement.setString(15, fMFormula.getP_autorizado().getCodigo());
            }
            if (fMFormula.getPedirweb() == null) {
                statement.setNull(16, Types.VARCHAR);
            } else {
                statement.setString(16, fMFormula.getPedirweb());
            }
            if (fMFormula.getRealizado() == null) {
                statement.setNull(17, Types.VARCHAR);
            } else {
                statement.setString(17, fMFormula.getRealizado());
            }
            if (fMFormula.getFecha_r() == null) {
                statement.setNull(18, Types.INTEGER);
            } else {
                statement.setString(18, dateTimeFormatterParser.format(fMFormula.getFecha_r()));
            }
            if (fMFormula.getActualizado() == null) {
                statement.setNull(19, Types.VARCHAR);
            } else {
                statement.setString(19, fMFormula.getActualizado());
            }
            //  + " ,dunidades_f,indicacion1,observacines,p_autorizado,pedirweb,realizado,fecha_r,actualizado "
            if (fMFormula.getFecha_a() == null) {
                statement.setNull(20, Types.INTEGER);
            } else {
                statement.setString(20, dateTimeFormatterParser.format(fMFormula.getFecha_a()));
            }

            if (fMFormula.getHoja_paci() == null) {
                statement.setNull(21, Types.VARCHAR);
            } else {
                statement.setString(21, fMFormula.getHoja_paci());
            }
            if (fMFormula.getEtiqueta1() == null) {
                statement.setNull(22, Types.VARCHAR);
            } else {
                statement.setString(22, fMFormula.getEtiqueta1());
            }
            if (fMFormula.getEtiqueta2() == null) {
                statement.setNull(23, Types.VARCHAR);
            } else {
                statement.setString(23, fMFormula.getEtiqueta2());
            }
            if (fMFormula.getObservaciones1() == null) {
                statement.setNull(24, Types.VARCHAR);
            } else {
                statement.setString(24, fMFormula.getObservaciones1());
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

    public boolean doActualizaDatos(FMFormula fMFormula) {
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

            if (fMFormula == null && fMFormula.getNumero() != null) {
                statement.setNull(24, Types.INTEGER);
            } else {
                statement.setInt(24, fMFormula.getNumero());
            }
//nombre=?,tipo=?,esteril=?,forma=?,via=?,unidades_f=?,
            if (fMFormula.getNombre() == null) {
                statement.setNull(1, Types.VARCHAR);
            } else {
                statement.setString(1, fMFormula.getNombre());
            }
            if (fMFormula.getTipo() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, fMFormula.getTipo().getCodigo());
            }
            if (fMFormula.getEsteril() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, fMFormula.getEsteril().substring(0));
            }
            if (fMFormula.getForma() == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, fMFormula.getForma().getCodigo());
            }
            if (fMFormula.getVia() == null) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, fMFormula.getVia().getCodigo());
            }
            if (fMFormula.getUnidades_f() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, fMFormula.getUnidades_f());
            }
            //   indicacion=?,conservacion=?, caducidad=?,controles =?"
            if (fMFormula.getIndicacion() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, fMFormula.getIndicacion());
            }
            if (fMFormula.getConservacion() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, fMFormula.getConservacion());
            }
            if (fMFormula.getCaducidad() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(9, fMFormula.getCaducidad());
            }
            if (fMFormula.getControles() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, fMFormula.getControles());
            }
            //   " ,dunidades_f=?,indicacion1=?,observacines=?,p_autorizado=?,
            if (fMFormula.getUnidades_f() == null) {
                statement.setNull(11, Types.VARCHAR);
            } else {
                statement.setInt(11, fMFormula.getUnidades_f());
            }
            if (fMFormula.getIndicacion1() == null) {
                statement.setNull(12, Types.VARCHAR);
            } else {
                statement.setString(12, fMFormula.getIndicacion1());
            }
            if (fMFormula.getObservaciones() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, fMFormula.getObservaciones());
            }
            if (fMFormula.getP_autorizado() == null) {
                statement.setNull(14, Types.VARCHAR);
            } else {
                statement.setString(14, fMFormula.getP_autorizado().getCodigo());
            }
            if (fMFormula.getPedirweb() == null) {
                statement.setNull(15, Types.VARCHAR);
            } else {
                statement.setString(15, fMFormula.getPedirweb());
            }
            if (fMFormula.getRealizado() == null) {
                statement.setNull(16, Types.VARCHAR);
            } else {
                statement.setString(16, fMFormula.getRealizado());
            }
            if (fMFormula.getFecha_r() == null) {
                statement.setNull(17, Types.INTEGER);
            } else {
                statement.setString(17, dateTimeFormatterParser.format(fMFormula.getFecha_r()));
            }
            if (fMFormula.getActualizado() == null) {
                statement.setNull(18, Types.VARCHAR);
            } else {
                statement.setString(18, fMFormula.getActualizado());
            }
            //  + " ,dunidades_f,indicacion1,observacines,p_autorizado,pedirweb,realizado,fecha_r,actualizado "
            if (fMFormula.getFecha_a() == null) {
                statement.setNull(19, Types.INTEGER);
            } else {
                statement.setString(19, dateTimeFormatterParser.format(fMFormula.getFecha_a()));
            }

            if (fMFormula.getHoja_paci() == null) {
                statement.setNull(20, Types.VARCHAR);
            } else {
                statement.setString(20, fMFormula.getHoja_paci());
            }
            if (fMFormula.getEtiqueta1() == null) {
                statement.setNull(21, Types.VARCHAR);
            } else {
                statement.setString(21, fMFormula.getEtiqueta1());
            }
            if (fMFormula.getEtiqueta2() == null) {
                statement.setNull(22, Types.VARCHAR);
            } else {
                statement.setString(22, fMFormula.getEtiqueta2());
            }
            if (fMFormula.getObservaciones1() == null) {
                statement.setNull(23, Types.VARCHAR);
            } else {
                statement.setString(23, fMFormula.getObservaciones1());
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

    public boolean doBorraDatos(FMFormula fMFormula) {
        Connection connection = null;
        Boolean insertadoBoolean = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM  farm_fm_formulas WHERE codigo=" + fMFormula.getNumero();
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

    public ArrayList<FMFormula> getListaFormulas(String texto) {
        Connection connection = null;
        ArrayList<FMFormula> listaFormulas = new ArrayList<>();
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
