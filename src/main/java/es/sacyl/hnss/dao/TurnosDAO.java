package es.sacyl.hnss.dao;

import com.jnieto.entity.ParametBBDD;
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

import com.jnieto.entity.Usuario;
import com.jnieto.ui.NotificacionInfo;

public class TurnosDAO {

    private static final Logger logger = LogManager.getLogger(TurnosDAO.class);

    Connection conn = null;

    public TurnosDAO() {
    }

    public Connection conecta() {
        Connection conn = null;

        String dbURL2, username, password;

        ParametBBDD cadena = new ParametroDAO().getRegistroPorCodigo(ConexionDAO.URL_CONEXION_TURNOS);

        String[] conex = cadena.getValor().split("\\|");

        dbURL2 = "jdbc:oracle:thin:" + conex[0];

        username = conex[1];

        password = conex[2];

        String sql;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(dbURL2, username, password);
        } catch (ClassNotFoundException ex) {
            logger.error("Error conexion turnos , clase no ecntranda.", ex);
        } catch (SQLException ex) {
            logger.error("Error conexion turnos , sql ", ex);
        }
        return conn;
    }

    public ArrayList<Usuario> getListaUsuColectivo(String colectivo, LocalDate fechaparam) {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fecha = fechadma.format(fechaparam);
        String sql = "select empleado,per.dni,per.nombre,per.apellido1,per.apellido2 	"
                + " FROM  AIDA.TUR_ASIGNACION_15 asig 	"
                + " INNER JOIN AIDA.PERSONAL_15 per ON per.codigo=asig.empleado " + " WHERE  colectivo ='" + colectivo
                + "' " + " AND fechaini<=TO_DATE('" + fecha + "','DD/MM/YYYY')  AND (fechafin>=TO_DATE('" + fecha
                + "','DD/MM/YYYY') or fechafin is null) 	order by per.apellido1,per.apellido2,per.nombre";

        Connection connection = null;
        try {
            connection = this.conecta();
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Usuario usuario = getRegistroResulset(resulSet);
                lista.add(usuario);
            }
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    public ArrayList<String> getListaUsuStringColectivo(String colectivo, LocalDate fechaparam) {
        ArrayList<String> lista = new ArrayList<String>();
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fecha = fechadma.format(fechaparam);
        String sql = "select empleado,per.dni,per.nombre,per.apellido1,per.apellido2 	"
                + " FROM  AIDA.TUR_ASIGNACION_15 asig 	"
                + " INNER JOIN AIDA.PERSONAL_15 per ON per.codigo=asig.empleado " + " WHERE  colectivo ='" + colectivo
                + "' " + " AND fechaini<=TO_DATE('" + fecha + "','DD/MM/YYYY')  AND (fechafin>=TO_DATE('" + fecha
                + "','DD/MM/YYYY') or fechafin is null) 	order by per.apellido1,per.apellido2,per.nombre";

        Connection connection = null;
        try {
            connection = this.conecta();
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Usuario usuario = getRegistroResulset(resulSet);
                lista.add(usuario.getApellidosNombre());
            }
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    public ArrayList<Usuario> getListaUsuColectivoUsuario(String colectivo, LocalDate fechaparam) {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fecha = fechadma.format(fechaparam);
        String sql = "select empleado,per.dni,per.nombre,per.apellido1,per.apellido2 	"
                + " FROM  AIDA.TUR_ASIGNACION_15 asig 	"
                + " INNER JOIN AIDA.PERSONAL_15 per ON per.codigo=asig.empleado " + " WHERE  colectivo ='" + colectivo
                + "' " + " AND fechaini<=TO_DATE('" + fecha + "','DD/MM/YYYY')  AND (fechafin>=TO_DATE('" + fecha
                + "','DD/MM/YYYY') or fechafin is null) 	order by per.apellido1,per.apellido2,per.nombre";

        Connection connection = null;
        try {
            connection = this.conecta();
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                Usuario usuario = getRegistroResulset(resulSet);
                lista.add(usuario);
            }
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    public ArrayList<String> getListaUsuStringColectivos(ArrayList<String> colectivos, LocalDate fechaparam) {
        ArrayList<String> lista = new ArrayList<String>();
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fecha = fechadma.format(fechaparam);
        String cadena = null;
        for (String col : colectivos) {
            if (cadena != null) {
                cadena = cadena.concat(",");
            } else {
                cadena = "";
            }

            cadena = cadena.concat("'" + col + "'");
        }
        String sql = "select empleado,per.dni,per.nombre,per.apellido1,per.apellido2 	"
                + " FROM  AIDA.TUR_ASIGNACION_15 asig 	"
                + " INNER JOIN AIDA.PERSONAL_15 per ON per.codigo=asig.empleado " + " WHERE  colectivo in (" + cadena
                + ") " + " AND fechaini<=TO_DATE('" + fecha + "','DD/MM/YYYY')  AND (fechafin>=TO_DATE('" + fecha
                + "','DD/MM/YYYY') or fechafin is null) 	order by per.apellido1,per.apellido2,per.nombre";

        Connection connection = null;
        try {
            connection = this.conecta();
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resulSet = statement.executeQuery(sql);
                while (resulSet.next()) {
                    Usuario usuario = getRegistroResulset(resulSet);
                    lista.add(usuario.getApellidosNombre());
                }
                logger.debug(sql);
            } else {
                new NotificacionInfo("Error bbdd turnos");
                return null;
            }
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return lista;
    }

    public Usuario getRegistroResulset(ResultSet resulSet) {
        Usuario usuario = new Usuario();
        try {
            usuario.setUserid(resulSet.getString("dni"));
            usuario.setApellido1(resulSet.getString("apellido1"));
            usuario.setApellido2(resulSet.getString("apellido2"));
            usuario.setNombre(resulSet.getString("nombre"));
            return usuario;
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return usuario;
    }
}
