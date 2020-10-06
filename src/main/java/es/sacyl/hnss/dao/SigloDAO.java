/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import com.jnieto.entity.CovidCaso;
import com.jnieto.entity.CovidDireccion;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Utilidades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class SigloDAO {

    private static final Logger logger = LogManager.getLogger(SigloDAO.class);

    Connection conn = null;

    private DateTimeFormatter fechaYYYmmdd = DateTimeFormatter.ofPattern("YYYYMMdd");

    public SigloDAO() {
    }

    public Connection conecta() {
        Connection conn = null;
        String dbURL2 = "jdbc:oracle:thin:@//10.36.64.161:1525/exhnss01";
        String username = "SIGL";
        String password = "SIGL";
        String sql;

        try {

            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(dbURL2, username, password);
        } catch (ClassNotFoundException ex) {
            logger.error("Error conexion his, clase no ecntranda.", ex);
        } catch (SQLException ex) {
            logger.error("Error conexion his, sql ", ex);
        }
        return conn;
    }

    public ArrayList<CovidCaso> getCasosCovi(LocalDate fecha) {
        String sql = "";
        ArrayList<CovidCaso> lista = new ArrayList<>();

        Connection connection = this.conecta();
        String fechaString = fechaYYYmmdd.format(fecha);
        try {
            sql = " SELECT  nvl(p.ape1,' ')as ape1,nvl(p.ape2,' ') as ape2,nvl(p.nombre,'. ') as nombre "
                    + ",nvl(substr(to_char(p.fnac),7,2) || '/'|| substr(to_char(p.fnac),5,2) ||'/'|| substr(to_char(p.fnac),1,4),' ')as  Fnaci "
                    + ",nvl(substr(to_char(d.fecha),7,2) || '/'|| substr(to_char(d.fecha),5,2) ||'/'|| substr(to_char(d.fecha),1,4),' ')as  Fprueba  "
                    + " ,d.sid as muestra "
                    + " ,d.fecha as fecha "
                    + " ,d.pid as nhc,a.prueba,a.valor ,nvl(a.comentario,' ') as comentario "
                    + "FROM adatos d "
                    + "JOIN avalor a ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='01/01/3000'  "
                    + "JOIN paciente p ON d.pid = p.pid AND p.baja='01/01/3000' "
                    + "WHERE d.fecha =" + fechaString
                    + " AND d.baja='01/01/3000' "
                    + " AND a.PRUEBA='SARSCOV2' AND upper(valor) like '%POSITIVO%'";

            logger.debug(sql);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            while (resulSet.next()) {
                CovidCaso caso = new CovidCaso();
                caso.setOrigen("HNSS");
                caso.setIdLocal(resulSet.getString("fecha") + resulSet.getString("nhc"));
                String pid = resulSet.getString("nhc");
                CovidDireccion direccion;
                ArrayList<CovidDireccion> direcciones = new ArrayList<>();
                // buscamos en hp-his
                if (Utilidades.isNumeric(pid)) {
                    direccion = new HpHisDAO().getDireccion(pid);
                    direccion.setTipoDireccion("Residencia");
                    if (direccion != null) {
                        direcciones.add(direccion);
                    }
                } // buscamos en tarjeta
                else {

                }
                caso.setDirecciones(direcciones);
                lista.add(caso);
            }
            statement.close();

        } catch (SQLException e) {
            logger.error(sql, e);
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

    public ArrayList<Variable> getCasosCovi(Paciente paciente) {
        ArrayList<Variable> lista = new ArrayList<>();
        for (Variable var : Variable.variablesCovid) {
            lista.addAll(getCasosCovi(paciente, var));
        }
        return lista;
    }

    public ArrayList<Variable> getCasosCovi(Paciente paciente, Variable variableParam) {
        String sql = "";
        ArrayList<Variable> lista = new ArrayList<>();

        Connection connection = this.conecta();
        try {
            sql = " SELECT  nvl(p.ape1,' ')as ape1,nvl(p.ape2,' ') as ape2,nvl(p.nombre,'. ') as nombre "
                    + "  ,nvl(substr(to_char(p.fnac),7,2) || '/'|| substr(to_char(p.fnac),5,2) ||'/'|| substr(to_char(p.fnac),1,4),' ')as  Fnaci "
                    + "  ,nvl(substr(to_char(d.fecha),7,2) || '/'|| substr(to_char(d.fecha),5,2) ||'/'|| substr(to_char(d.fecha),1,4),' ')as  Fprueba  "
                    + "  ,d.sid as muestra "
                    + "  ,d.fecha as fecha "
                    + "  ,d.pid as nhc,a.prueba,b.descripcion,a.valor ,nvl(a.comentario,' ') as comentario "
                    + " FROM adatos d "
                    + "    JOIN avalor a ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='01/01/3000'  "
                    + "    JOIN paciente p ON d.pid = p.pid AND p.baja='01/01/3000' "
                    + "    JOIN pruebas b ON b.codigo=a.prueba"
                    + " WHERE d.pid ='" + paciente.getNumerohc() + "'"
                    + "       AND d.baja='01/01/3000' "
                    + "       AND  a.prueba ='" + variableParam.getCodigo() + "'"
                    + " ORDER By fecha desc ";
            logger.debug(sql);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Variable variable = new Variable();
                variable.setCodigo(resulSet.getString("prueba"));
                variable.setDescripcion(resulSet.getString("descripcion"));
                variable.setValor(resulSet.getString("valor"));
                variable.setFechaValor(Utilidades.getFechaLocalDate(resulSet.getLong("fecha")));
                variable.setDescripcionCorta(variableParam.getDescripcionCorta());
                lista.add(variable);
            }
            statement.close();

        } catch (SQLException e) {
            logger.error(sql, e);
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

    public String getWhereCovid() {
        String sql = "";
        for (Variable vcov : Variable.variablesCovid) {
            if (sql.length() > 5) {
                sql = sql.concat(",");
            }
            sql = sql.concat("'" + vcov.getCodigo() + "'");
        }

        return sql;
    }

    public ArrayList<Paciente> getResultadosCovidFechas(LocalDate desde, LocalDate hasta) {
        String sql = "";
        ArrayList<Variable> listaVariables = new ArrayList<>();
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        Connection connection = this.conecta();
        if (desde == null) {
            logger.error("Sin valor en fecha desde ");
            return null;
        }
        if (hasta == null) {
            hasta = desde;
        }
        try {
            sql = " SELECT   p.ape1,p.ape2,p.nombre  "
                    + "  ,d.sid as muestra "
                    + "  ,d.pid as nhc,d.fecha "
                    + " ,a.prueba,b.descripcion,a.valor as valor ,nvl(a.comentario,' ') as comentario "
                    + " FROM adatos d "
                    + "    JOIN avalor a ON d.sid=a.SID AND d.FECHA=a.FECHA AND a.baja='01/01/3000'  "
                    + "    JOIN paciente p ON d.pid = p.pid AND p.baja='01/01/3000' "
                    + "    JOIN pruebas b ON b.codigo=a.prueba"
                    + " WHERE  d.fecha>=" + Long.toString(Utilidades.getFechaNumeroyyymmddDefecha(desde))
                    + "    AND d.fecha<=" + Long.toString(Utilidades.getFechaNumeroyyymmddDefecha(hasta))
                    + "    AND  d.baja = '01/01/3000' "
                    + "     AND a.prueba in (" + getWhereCovid() + ")"
                    + "  ORDER By d.pid, d.fecha  desc ";
            logger.debug(sql);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            Paciente paciente = null;
            Paciente pacienteAnterior = null;
            while (resulSet.next()) {
                /*
                Siglo guarda en nhc el número de historia o el número de tarjeta
                si es numeríco busca por nhc
                si no busca por tarjeta
                 */
                if (Utilidades.isNumeric(resulSet.getString("nhc")) == true) {
                    paciente = new PacienteDAO().getPacientePorNhc(resulSet.getString("nhc"), Boolean.FALSE);
                } else {
                    paciente = new PacienteDAO().getPacientePorTarjeta(resulSet.getString("nhc"), Boolean.FALSE);
                }

                if (paciente != null) {
                    if (pacienteAnterior == null) {
                        pacienteAnterior = paciente;
                        listaVariables = new ArrayList<>();
                    }
                    if (!paciente.getId().equals(pacienteAnterior.getId())) {
                        paciente.setListaVariables(listaVariables);
                        listaVariables = new ArrayList<>();
                        listaPacientes.add(paciente);
                        pacienteAnterior = paciente;
                    }
                    Variable variable = new Variable();
                    variable.setCodigo(resulSet.getString("prueba"));
                    variable.setDescripcion(resulSet.getString("descripcion"));
                    variable.setValor(resulSet.getString("valor"));
                    variable.setFechaValor(Utilidades.getFechaLocalDate(resulSet.getLong("fecha")));
                    listaVariables.add(variable);
                } else {
                    logger.error("Paciente de siglo " + resulSet.getString("nhc") + " no encontrado en  jimena");
                }
            }
            statement.close();

        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaPacientes;
    }

}
