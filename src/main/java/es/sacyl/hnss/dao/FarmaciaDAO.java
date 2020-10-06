package es.sacyl.hnss.dao;

import com.jnieto.entity.ParametBBDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.ui.NotificacionInfo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FarmaciaDAO {

    private static final Logger logger = LogManager.getLogger(FarmaciaDAO.class);

    Connection conn = null;

    public FarmaciaDAO() {
    }

    public Connection conecta() {
        Connection conn = null;

        String dbURL2, username, password;

        ParametBBDD cadena = new ParametroDAO().getRegistroPorCodigo(ConexionDAO.URL_CONEXION_FARMACIA);
        String[] conex = cadena.getValor().split("\\|");
        dbURL2 = "jdbc:oracle:thin:@//" + conex[0];
        username = conex[1];
        password = conex[2];

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

    public String getTratamientoOnco(String nhc, LocalDate fecha) {
        String valor = "";
        String sql = "";
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaString = fechadma.format(fecha);
        Connection connection = this.conecta();

        try {
            sql = " SELECT   dp_paci_proceso.numerohc historia" + ",	dp_paci_proceso.fec_ult_disp fdispensacion"
                    + ",	dp_cab_protocol.nombre protocolo " + ",paci_age.fecha 	  "
                    + "   ,TO_CHAR (TO_DATE(dp_paci_proceso.fec_ult_disp,'DD/MM/YYYY'), 'DD/MM/YYYY') fechac "
                    + " FROM  dp_paci_proceso,dp_prescrip,paci_age ,dp_cab_protocol "
                    + "	WHERE ( dp_paci_proceso.cod_centro = 1 ) and  dp_paci_proceso.numerohc='" + nhc + "' "
                    + " AND  fecha<='" + fechaString + "' "
                    + " and dp_prescrip.cod_protocol=dp_cab_protocol.cod_protocol and ( dp_paci_proceso.cod_centro = dp_prescrip.cod_centro ) "
                    + " and ( dp_paci_proceso.numerohc = dp_prescrip.numerohc ) and ( dp_paci_proceso.nproceso = dp_prescrip.nproceso ) "
                    + " and ( dp_prescrip.estado <> 'S' and dp_prescrip.estado <> 'C' ) and ( dp_prescrip.num_mezcla > 0 ) "
                    + " and ( dp_paci_proceso.tproceso = 'A' ) and ( paci_age.confirmado = 'S' or paci_age.confirmado = 'N' ) "
                    + "  and ( dp_paci_proceso.numerohc not in (1))	and ( dp_prescrip.cod_centro = paci_age.cod_centro ) "
                    + " and ( dp_prescrip.numerohc = paci_age.numerohc ) 	and ( dp_prescrip.nproceso = paci_age.nproceso ) "
                    + " and ( dp_prescrip.linea = paci_age.linea_pres ) " + " order by  paci_age.fecha desc  ";

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                valor = resulSet.getString("fechac");
                valor = valor.concat(" " + resulSet.getString("protocolo"));
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }

        return valor;
    }
}
