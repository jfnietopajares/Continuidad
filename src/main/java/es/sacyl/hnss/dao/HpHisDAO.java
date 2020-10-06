package es.sacyl.hnss.dao;

import com.jnieto.entity.CovidDireccion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Episodio;
import com.jnieto.entity.ParametBBDD;
import com.jnieto.entity.RegistroQuiHojaCirculaPre;
import com.jnieto.entity.Usuario;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Utilidades;

public class HpHisDAO {

    private static final Logger logger = LogManager.getLogger(HpHisDAO.class);

    private final static String  QUIRO_TIPOCIRUJANO ="CIR";
    private final static String QUIRO_TIPOCANESTESISTA ="ANS";
    private final static String QUIRO_TIPOENFERMERA ="ENF";
    private final static String QUIRO_TIPOAUXILIAR ="AENF";
    
    private Connection conn = null;
    private final DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("YYYY/MM/dd");
    private final DateTimeFormatter hora = DateTimeFormatter.ofPattern("H:m");

    private final int tipFinancia = 1;
    private final String garante = "   100";
    private final String motivoingr = "1";
    private final String motivoalt = "0";

    private final String motivoalta_hdd_epi = "1";
    private final String codipres = "HDD";
    private final String factura_ici = "0";
    private final String ambito = "N";
    private final String procedede = "1";
    private final String codificado = "N";
    private final String bloqueado = "N";
    private final String ambito_epi = "HDDN";
    private final String usubb = "TRASPASO";
    private final String sala = "SALA4";
    private String descmedi;

    
    public HpHisDAO() {
    }

    public Connection conecta() {
        Connection conn = null;

        ParametBBDD cadena = new ParametroDAO().getRegistroPorCodigo(ConexionDAO.URL_CONEXION_HIS);

        String dbURL2 = "jdbc:informix-sqli://" + cadena.getValor();

        try {
            Class.forName("com.informix.jdbc.IfxDriver");
            conn = DriverManager.getConnection(dbURL2);
        } catch (ClassNotFoundException ex) {
            logger.error("Error conexion his, clase no contrada.", ex);
        } catch (SQLException ex) {
            logger.error("Error conexion his  ", ex);
        }
        return conn;
    }

    public Boolean grabaEpisodioHdia(Episodio epi, String area, Connection con, String descmedi) {
        this.descmedi = descmedi;
        Boolean grabado = false;
        Long idhdd = doInsertahdhisepi(epi, area, con);
        Long idhddses;
        Connection connection = con;

        if (idhdd != null) {
            idhddses = doInsertaHddSesion(epi, area, connection, idhdd);

            grabado = doInsertahdbclin(connection, idhdd);

            grabado = doInsertaHdd_epi(connection, epi, idhdd, idhddses);

            grabado = doInsertaFicus(epi, area, connection, idhddses);

            grabado = doInsertacodif_epi(epi, connection, idhddses);

            //   grabado = doInsertaCcodif_epi(epi, connection, idhddses);
            String icuString = "";

            icuString = idhddses.toString();

            grabado = new EpisodioDAO().doUpdateIcuHisHdia(epi, icuString);
        }

        return grabado;
    }

    public Long getRegistrohdia(Connection con, int cte) {

        Long id = null;
        Connection connection = null;
        String sql = null;
        try {
            if (con == null) {
                connection = this.conecta();
            } else {
                connection = con;
            }

            sql = "select valclave from consclin  where codclav= " + cte;
            logger.debug(sql);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                id = resulSet.getLong("valclave") + 1;
                sql = " UPDATE consclin set valclave = " + id + " where codclav=" + cte;
                logger.debug(sql);
                PreparedStatement statement1 = connection.prepareStatement(sql);
                Boolean resultado = statement1.execute();
                statement1.close();
            }

            statement.close();

        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                if (con == null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return id;
    }

    /**
     *
     * @param epi
     * @param area
     * @param con
     * @return si inserta la fila de forma correcta retorna el número del
     * episodio generado si no un null
     */
    public Long doInsertahdhisepi(Episodio epi, String area, Connection con) {
        Long id = this.getRegistrohdia(con, 30042);
        Boolean resultado = false;
        String hoy = fechadma.format(LocalDate.now());
        String fechaString = fechadma.format(epi.getFinicio());
        String horaString = hora.format(LocalDateTime.now());
        String servicioHis = Utilidades.recodificaServHddHis(epi.getServicioCodigo());

        String sql = " INSERT INTO hdhisepi (epihdd,numerohc,procedede,servpeti,medipeti,tipfinan,garante,fecinicio,areafun, "
                + "sesiones,servreal,fecha_alta,mcierre,grabausu,grabadia,grabahora )	" + "           VALUES  ( " + id
                + "," + epi.getPacienteNhc() + ",1,'" + servicioHis + "',0," + tipFinancia + "," + garante + ",  '"
                + fechaString + "',       '" + area + "',1,'" + servicioHis + "','" + fechaString + "',4,'" + usubb
                + "','" + hoy + "','" + horaString + "' )";

        Connection connection = null;
        try {
            if (con == null) {
                connection = this.conecta();
            } else {
                connection = con;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            resultado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                if (con == null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }

        if (resultado == true) {
            return id;
        } else {
            return null;
        }
    }

    public Long doInsertaHddSesion(Episodio epi, String area, Connection con, Long epihdd) {
        Boolean resultado = false;
        Long idsesion = this.getRegistrohdia(con, 30041);
        // DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        // DateTimeFormatter hora = DateTimeFormatter.ofPattern("H:m");
        String hoy = fechadma.format(LocalDate.now());
        String fechaString = fechadma.format(epi.getFinicio());
        String ahora = hora.format(LocalDateTime.now());
        String horaIniString = Utilidades.getHoraHH_MM(epi.getHinicio());
        String horaFinString = Utilidades.getHoraHH_MM(epi.getHfinal());
        String servicioHis = Utilidades.recodificaServHddHis(epi.getServicioCodigo());

        String sql = " insert into hdsesion (sesion,epihdd,numerohc,fecha,clavep,hora_ini,hora_fin,puesto "
                + ",sala,servreal,persreal,seguim,infec,grabausu,grabadia,grabahora)  values " + "( " + idsesion + ","
                + epihdd + "," + epi.getPacienteNhc() + ",'" + fechaString + "'," + epihdd + ",'" + horaIniString
                + "','" + horaIniString + "','1','" + sala + "','" + servicioHis + "',  0, 0, " + "'N',  '" + usubb
                + "', '" + hoy + "','" + ahora + "')";

        Connection connection = null;
        try {
            if (con == null) {
                connection = this.conecta();
            } else {
                connection = con;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            resultado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                if (con == null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return idsesion;
    }

    public Boolean doInsertahdbclin(Connection con, Long icu) {
        Boolean insertado = false;
        String sql = "";
        sql = " insert into hdbclin (clavep,epihdd,domina)  values(" + icu + "," + icu + ",'S') ";

        Connection connection = null;
        try {
            if (con == null) {
                connection = this.conecta();
            } else {
                connection = con;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                if (con == null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }

        return insertado;
    }

    public Boolean doInsertaHdd_epi(Connection con, Episodio epi, Long epihdd, Long idses) {

        Boolean insertado = false;
        String sql = "";

        String fechaString = fechadma.format(epi.getFinicio());
        String fechaAltaString = fechadma.format(epi.getFfinal());

        sql = "insert into hdd_epi (epihdd,numpses,fec_pris,fec_ults,fec_alta,mot_alta)  values " + "(  " + epihdd + ","
                + idses + ",'" + fechaString + "','" + fechaAltaString + "','" + fechaAltaString + "','"
                + motivoalta_hdd_epi + "') ";

        Connection connection = null;
        try {
            if (con == null) {
                connection = this.conecta();
            } else {
                connection = con;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                if (con == null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }

        return insertado;
    }

    public Boolean doInsertaFicus(Episodio epi, String area, Connection con, Long epihdd) {
        Boolean insertado = false;
        String hoy = fechadma.format(LocalDate.now());
        String fechaIniString = fechadma.format(epi.getFinicio());

        String ahora = hora.format(LocalDateTime.now());
        String horaIniString = Utilidades.getHoraHH_MM(epi.getHinicio());
        String horaFinString = Utilidades.getHoraHH_MM(epi.getHfinal());
        String servicioHis = Utilidades.recodificaServHddHis(epi.getServicioCodigo());
        String motivoingrFicus = "1";
        String motivoaltFicus = "5";
        String codipres = "HDD";

        int factura_icu = 0;
        String ambito = "S";
        int procede = 1;
        String codificado = "N";
        String bloqueado = "N";
        String ambito_epi = "HDD";

        String sql = "	insert into ficus (numicu, financia, tipfinancia, dataingr, motivoingr, "
                + " datalta, respalta, motivoal, horaalta, horaing,  numerohc, codipres, "
                + "servpeti, perspeti, factura_icu, servreal, ambito, procede, codificado, bloqueado, ambito_epi) values  "
                + "(" + epihdd + ",  '" + garante + "', '" + tipFinancia + "', '" + fechaIniString + "' ,'"
                + motivoingrFicus + "','" + fechaIniString + "',0,'" + motivoaltFicus + "', '" + epi.getHfinal() + "','"
                + epi.getHinicio() + "','" + epi.getPacienteNhc() + "','" + codipres + "', '" + servicioHis + "',0,'"
                + factura_icu + "', '" + servicioHis + "',  '" + ambito + "', " + procede + ",  '" + codificado + "','"
                + bloqueado + "','" + ambito_epi + "')";
        Connection connection = null;
        try {
            if (con == null) {
                connection = this.conecta();
            } else {
                connection = con;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                if (con == null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return insertado;
    }

    public Boolean doInsertacodif_epi(Episodio epi, Connection con, Long idhddses) {

        Boolean insertado = false;
        String sql = "";

        String fechaString = fechadma.format(epi.getFinicio());
        String fechaAltaString = fechadma.format(epi.getFfinal());

        String tipopro = "5";
        String tipodiag = "1";
        String tipoproc = "D";
        String codigopro = epi.getCodigProcedimiento();
        String codigodiag = epi.getCodigDiagnostico();
        //String descmedi = "QUIMIOTERAPIA";
        int norden = 1;
        String codihosp = "4";
        String catcie = "1";
        String ind_poa = "E";
        // procedimiento
        sql = " insert into codif_epi (numerohc,numicu, tipo,tipoproc,codigo,descmedi,norden"
                + ",fecha,codihosp,servreal,persreal,servpeti,perspeti,usuario,datacodi,catcie ) " + " values   ( "
                + epi.getPaciente().getNumerohc() + "," + idhddses + ",'" + tipopro + "','" + tipoproc + "','"
                + codigopro + "','" + descmedi + "'," + norden + ",'" + fechaString + "','" + codihosp + "','"
                + epi.getServicioCodigo() + "',0,'" + epi.getServicioCodigo() + "',0,'" + usubb + "'" + ",'"
                + fechaAltaString + "','" + catcie + "') ";

        String sqlError = sql;
        // diagnóstico
        String sqldiag = " insert into codif_epi (numerohc,numicu, tipo,codigo,descmedi,norden"
                + ",fecha,codihosp,servreal,persreal,servpeti,perspeti,usuario,datacodi,ind_poa,catcie ) "
                + " values   ( " + epi.getPaciente().getNumerohc() + "," + idhddses + ",'" + tipodiag + "','"
                + codigodiag + "','" + descmedi + "'," + norden + ",'" + fechaString + "','" + codihosp + "','"
                + epi.getServicioCodigo() + "',0,'" + epi.getServicioCodigo() + "',0,'" + usubb + "'" + ",'"
                + fechaAltaString + "','" + ind_poa + "','" + catcie + "') ";
        Connection connection = null;
        try {
            if (con == null) {
                connection = this.conecta();
            } else {
                connection = con;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(sql);
            sqlError = sqldiag;
            PreparedStatement statement1 = connection.prepareStatement(sqldiag);
            insertado = statement1.executeUpdate() > 0;
            statement1.close();
            logger.debug(sqldiag);
        } catch (SQLException e) {
            logger.error(sqlError, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                if (con == null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }

        return insertado;
    }

    public Boolean doInsertaCcodif_epi(Episodio epi, Connection con, Long idhddses) {

        Boolean insertado = false;
        String sqlProcedimiento = "";

        String fechaAltaString = fechadma.format(epi.getFfinal());

        String numerohc = epi.getPaciente().getNumerohc();
        Long numicu = idhddses;
        String tipodiag = "1";
        String descmedi = "QUIMIOTERAPIA";
        int norden = 1;
        String fecha = fechadma.format(epi.getFinicio());
        String codihosp = "1";
        String servreal = epi.getServicioCodigo();
        int persreal = 0;
        String servpeti = epi.getServicioCodigo();
        int perspeti = 0;
        String usuario = usubb;
        String datacodi = fecha;
        String grabadia = fecha;
        String ind_poa = "D";
        String catcie = "1";
        String tipopro = "5";
        String tipoproc = "D";

        String codigodiag = epi.getCodigDiagnostico();

        String sqldiag = "  insert into ccodif_epi "
                + "(numerohc,numicu,tipo,codigo,descmedi, norden,fecha,codihosp,servreal,persreal,servpeti,perspeti,anestes, usuario,datacodi,grabadia ,ind_poa,catcie) "
                + " values   ( " + numerohc + "," + numicu + ",'" + tipodiag + "','"
                + codigodiag + "','" + descmedi + "'," + norden + ",'" + fecha + "','" + codihosp + "','"
                + servreal + "',0,'" + servpeti + "',0,'" + usubb + "'" + ",'"
                + fechaAltaString + "','" + ind_poa + "','" + catcie + "') ";

        String codigopro = epi.getCodigProcedimiento();

        // procedimiento
        sqlProcedimiento = " insert into codif_epi (numerohc,numicu, tipo,tipoproc,codigo,descmedi,norden"
                + ",fecha,codihosp,servreal,persreal,servpeti,perspeti,usuario,datacodi,catcie ) " + " values   ( "
                + epi.getPaciente().getNumerohc() + "," + idhddses + ",'" + tipopro + "','" + tipoproc + "','"
                + codigopro + "','" + descmedi + "'," + norden + ",'" + fecha + "','" + codihosp + "','"
                + epi.getServicioCodigo() + "',0,'" + epi.getServicioCodigo() + "',0,'" + usubb + "'" + ",'"
                + fechaAltaString + "','" + catcie + "') ";

        String sqlError = sqlProcedimiento;
        // diagnóstico

        Connection connection = null;
        try {
            if (con == null) {
                connection = this.conecta();
            } else {
                connection = con;
            }

            PreparedStatement statement = connection.prepareStatement(sqlProcedimiento);
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(sqlProcedimiento);
            sqlError = sqldiag;
            PreparedStatement statement1 = connection.prepareStatement(sqldiag);
            insertado = statement1.executeUpdate() > 0;
            statement1.close();
            logger.debug(sqldiag);
        } catch (SQLException e) {
            logger.error(sqlError, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                if (con == null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }

        return insertado;
    }

    public String getMotivoAltaIcu(String icu) {
        String valor = "";
        String sql = "";
        Connection connection = null;
        try {
            connection = this.conecta();
            sql = "select  m.descri from histo_paci h JOIN mot_alta m On h.mot_alta=m.codigo  where numicu= '" + icu
                    + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                valor = resulSet.getString("descri");
            }
            statement.close();
            logger.debug(sql);
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
        return valor;
    }

    public CovidDireccion getDireccion(String nhc) {
        CovidDireccion direccion = null;

        String sql = "";

        Connection connection = null;
        try {
            connection = this.conecta();
            if (connection == null) {
                logger.error("Sin conexión a bbdd clínca");
                return direccion;
            }
            sql = " SELECT  codipost,c.nompobla as poblacion,v.descprov as provincia FROM  pacientes p"
                    + " JOIN provincias v ON p.provresi=v.codprov JOIN poblacion c ON c.codiprov=v.codprov and c.codpobla=p.poblares"
                    + " WHERE  numerohc='" + nhc + "'";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                direccion = new CovidDireccion();
                direccion.setCodpostal(resulSet.getString("codipost"));
                direccion.setPoblacion(resulSet.getString("poblacion"));
                direccion.setProvincia(resulSet.getString("provincia"));
            }
            statement.close();
            logger.debug(sql);
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
        return direccion;
    }

    /*
    Parámetro pasado por valor
    Modifico los datos sobre los atributos de calse RegistroQuiHojaCirculaPre
    orden
     */
    public void setDatosIntervencion(RegistroQuiHojaCirculaPre registro) {

        String valor = "";
        String sql = "";
        String qnuminter = "";
        Integer orden = 1;
        Connection connection = null;
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("YYYY/MM/dd");

        String fecha = fechadma.format(registro.getFechaHoraInterTime());
        String servicio = Utilidades.recodificaJimenaHisQuiro(registro.getServicioQui().getValor());
        try {
            connection = this.conecta();
            Statement statement = connection.createStatement();
            // recupera el qqnuminter ,qnorden, ,qdiagnos  , qprocedi
            sql = "select qnuminter,qnorden,qdiagnos  , qprocedi from gq_prov where qfec_qui='" + fecha + "' and qservici='" + servicio + "' and qquirofa='" + registro.getEpisodio().getCama().getCama() + "' "
                    + " AND qnum_hc='" + registro.getPacienteNhc() + "'";
            logger.debug(sql);
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                registro.setNumeroOrden(resulSet.getInt("qnorden"));
                registro.setIntervencion(resulSet.getString("qprocedi"));
                registro.setDiagnostico(resulSet.getString("qdiagnos"));
                qnuminter = resulSet.getString("qnuminter"); // el  qnuminter para recuperar al personal
                registro.setCirujano1(this.getPersonal(connection, qnuminter, "CIR", "S"));
                registro.setAnestesista(this.getPersonal(connection, qnuminter, "ANS", "S"));
                registro.setCirujano2(this.getPersonal(connection, qnuminter, "CIR", "N"));
                registro.setCirculante(this.getPersonal(connection, qnuminter, "ENF", "N"));
                registro.setInstrumentista(this.getPersonal(connection, qnuminter, "ENF", "S"));
                registro.setAuxiliar(this.getPersonal(connection, qnuminter, "AENF", "S"));                
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
    }

    public String getPersonal(Connection connection, String qnuminter, String tipo, String principal) {
        /*
        tipo CIR cirujano
            ANS anetesistas
        principal S/N
        
         */
        String sql = "";
        String medico = "";
        try {
            sql = " select  trim(apellido1)|| ' '|| trim(apellido2) || ' '||trim(nomb)  as medico, persclin.codpers "
                    + "	from gqpersoint,persclin where clase='" + tipo + "' and princip='" + principal + "' "
                    + " and gqpersoint.codpers=persclin.codpers  	and qnuminter='" + qnuminter + "' ";
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                medico = resulSet.getString("medico")+"." +resulSet.getString("codpers");
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }

        return medico;
    }

}
