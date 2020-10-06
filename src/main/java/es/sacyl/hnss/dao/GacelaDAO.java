package es.sacyl.hnss.dao;

import com.jnieto.entity.ParametBBDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GacelaDAO {

    private static final Logger logger = LogManager.getLogger(GacelaDAO.class);

    Connection conn = null;

    public GacelaDAO() {

    }

    public Connection conecta() {
        Connection conn = null;

        String dbURL2, username, password;

        ParametBBDD cadena = new ParametroDAO().getRegistroPorCodigo(ConexionDAO.URL_CONEXION_GACElA);
        String[] conex = cadena.getValor().split("\\|");
        dbURL2 = "jdbc:oracle:thin:" + conex[0];
        username = conex[1];
        password = conex[2];
        String sql;
        try {
            // registers Oracle JDBC driver - though this is no longer required
            // since JDBC 4.0, but added here for backward compatibility
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(dbURL2, username, password);
        } catch (ClassNotFoundException ex) {
            logger.error("Error conexion his, clase no ecntranda.", ex);
        } catch (SQLException ex) {
            logger.error("Error conexion his, sql ", ex);
        }
        return conn;
    }

    public String getComentarios(String icu) {
        String string = "";
        String sql;
        Connection conn = this.conecta();
        if (conn != null) {
            sql = " SELECT   to_char(EVO_COMENTARIO) as comentario, " + "	EVO_UE_CODE as unidad,"
                    + "	TO_CHAR(EVO_FECHAMOD,  ' DAY DD/MM/YYYY HH24:MM') AS fecha,"
                    + "	TO_CHAR(EVO_FECHAMOD,  'HH24') as hora," + "	a.ACC_NAME    as usuario " + "	,EVO_FECHAMOD "
                    + "	FROM GAC1_EVOLUTIVO  v 	" + " INNER JOIN GAC1_EPISODIO e ON e.EPI_CODE=v.EVO_EPI_CODE "
                    + "	INNER JOIN XG1_ACCOUNTS  a ON v.EVO_MODIFICADOR_CODE= a.ACC_CODE " + "	WHERE e.EPI_NUMEPI='"
                    + icu + "' AND v.EVO_ACTIVO IS NULL ORDER BY EVO_FECHAMOD DESC";
            // System.out.println(sql);
            try {
                Statement statement = conn.createStatement();
                ResultSet resulSet = statement.executeQuery(sql);
                while (resulSet.next()) {
                    string += resulSet.getString("fecha") + " " + resulSet.getString("hora") + " "
                            + resulSet.getString("usuario") + "<br>" + resulSet.getString("comentario") + "<hr>";
                }
                statement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return string;
    }

}
