package es.sacyl.hnss.dao;

import com.jnieto.entity.Cama;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Campos_i;
import com.jnieto.entity.Canal;
import com.jnieto.entity.Centro;
import com.jnieto.entity.Informe;
import com.jnieto.entity.Municipio;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.PagiLisReg;
import com.jnieto.entity.Proceso;
import com.jnieto.entity.Provincia;
import com.jnieto.entity.Registro;
import com.jnieto.entity.Servicio;
import com.jnieto.entity.Usuario;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Constantes;
import com.jnieto.utilidades.Utilidades;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Class InformesDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class InformesDAO extends ConexionDAO implements InterfaceDAO {

    private static final Logger logger = LogManager.getLogger(InformesDAO.class);

    private Informe informe = null;

    public static String INFORMESORDENFECHA = "FECHAHORA";
    public static String INFORMESORDENFECHADESC = "FECHAHORADESC";
    public static String INFORMESORDENPACIENTE = "PACIENTE";

    private String sql = "  i.id,i.fecha,i.hora,i.userid,i.centro,i.servicio,i.referencia,i.paciente,i.descripcion   "
            + ",i.problema,i.episodio,i.estado,i.tipoxml,i.tipobin,i.peticion,i.userid"
            + ",i.canal,i.tipoinforme,i.useridauth,i.srvauth,i.useridredactor"
            + ",i.plantilla_editor,i.flag,i.pertenece,i.version,i.nivel_visibilidad,i.subservicio, i.useridpeticionario"
            + ",i.visto,i.ultimoguardado ,i.bloqueado,i.almacenamiento,i.tipo_documento, i.ambito ,i.servicio_realizador"
            + ",i.fecha_proceso,i.referencia_almacenamiento,i.num_accesos,i.problema,i.user_visto,i.fecha_visto,i.comentario_visto "
            + ",p.ape1,p.ape2,p.nombre,p.id as idpaciente,p.fnac,p.sexo,p.tarjeta,p.nss,p.dni,p.telefono,p.movil,p.nbdp,p.cias"
            + ",p.direccion,p.codigopostal,p.provincia,p.municipio,p.madre,p.centroprimaria"
            + ",v.descripcion as provinciadescripcion"
            + ",w.descripcion as municipiodescripcion,  w.id  as  municipioid "
            + " ,h.nhc"
            + "              ,t.id as idcentro, t.codigo as codigocentro,t.descripcion as descentro, t.nemonico"
            + "              , s.id as idservicio,s.codigo as codigoservicio, s.descripcion as descservicio"
            + "             ,u.userid as usuuserid, u.apellido1 as usuapellido1,u.apellido2 as usuapellido2,u.nombre as usunombre, u.categoria as usucategoria,u.estado as usuestado "
            + "           "
            + "              ,pri.id as idcentropri, pri.codigo as codigocentropri,pri.descripcion as descentropri, pri.nemonico as nemonicopri"
            + "              FROM informes  i	   "
            + "              JOIN pacientes p ON p.id=i.paciente  "
            + "              JOIN historias h ON h.paciente=p.id    "
            + "              JOIN centros t ON t.id=i.centro  "
            + "              JOIN servicios s ON s.id=i.servicio   "
            + "               JOIN usuarios U ON u.userid=i.userid    "
            + "              LEFT JOIN provincias v ON v.codigo=p.provincia "
            + "              LEFT JOIN municipios w ON (w.id=p.municipio and w.provincia=p.provincia)"
            + "               LEFT JOIN centros pri ON pri.id=p.centroprimaria   "
            + "              WHERE  1=1 ";

    public InformesDAO() {
    }

    @Override
    public boolean getReferenciasExternas(Long id) {
        return false;
    }

    @Override
    public boolean grabaDatos(Object object) {
        this.informe = (Informe) object;
        boolean actualizado = false;
        if (informe.getId() == 0) {
            actualizado = this.insertaDatos(informe);
        } else {
            actualizado = this.actualizaDatos(informe);
        }
        return actualizado;
    }

    @Override
    public boolean actualizaDatos(Object mensajeparam) {
        Connection connection = null;
        informe = (Informe) mensajeparam;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE informes SET  descripcion=?,  fecha=? , hora =? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, informe.getDescripcion());
            statement.setLong(2, Utilidades.getFechaNumeroyyymmddDefecha(informe.getFecha()));
            statement.setLong(3, informe.getHora());
            statement.setLong(4, informe.getId());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE informes SET  descripcion= '" + informe.getDescripcion() + "',  fecha="
                    + informe.getFecha() + " , hora =" + informe.getHora() + " WHERE id= " + informe.getId());
        } catch (SQLException e) {
            logger.error(" UPDATE informes SET  descripcion= '" + informe.getDescripcion() + "',  fecha="
                    + informe.getFecha() + " , hora =" + informe.getHora() + " WHERE id= " + informe.getId());
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    @Override
    public boolean insertaDatos(Object mensajeparam) {
        Connection connection = null;
        informe = (Informe) mensajeparam;
        Long id = null;
        boolean insertado = false;
        FileInputStream is = null;
        String idp = "null";

        /*
		 * new NotificacionInfo("Abspath" +
		 * informe.getFicheroInformeFile().getAbsolutePath(), true);
		 * logger.debug("Abspath" + informe.getFicheroInformeFile().getAbsolutePath());
		 * try { new NotificacionInfo("Canoni" +
		 * informe.getFicheroInformeFile().getCanonicalPath(), true);
		 * logger.debug("Canoni" + informe.getFicheroInformeFile().getCanonicalPath());
		 * 
		 * } catch (IOException e1) { // TODO Auto-generated catch block
		 * logger.debug("error file", e1); e1.printStackTrace(); } new
		 * NotificacionInfo("path" + informe.getFicheroInformeFile().getPath(), true);
		 * logger.debug("path" + informe.getFicheroInformeFile().getPath()); new
		 * NotificacionInfo("name" + informe.getFicheroInformeFile().getPath(), true);
		 * logger.debug(Long.toString(informe.getId())); logger.debug("name" +
		 * informe.getFicheroInformeFile().getPath());
         */
        try {
            connection = super.getConexionBBDD();
            id = new UtilidadesDAO().getSiguienteId("informes");
            informe.setId(id);
            /*
			 * if (informe.getProblema() != null &&
			 * informe.getProblema().getSubambito().equals(Proceso.SUBAMBITO_PARTOS)) { is =
			 * new FileInputStream(new PartoPdf(informe.getProblema()).getFile()); } else {
			 * is = new FileInputStream(informe.getFicheroInformeFile()); }
             */
            is = new FileInputStream(informe.getFicheroInformeFile());

            // File blob = new File("/path/to/picture.png");
            FileInputStream in = new FileInputStream(informe.getFicheroInformeFile());

            // the cast to int is necessary because with JDBC 4 there is
            // also a version of this method with a (int, long)
            // but that is not implemented by Oracle
            // pstmt.setBinaryStream(1, in, (int)blob.length());
            // new NotificacionInfo(is.toString(), true);
            sql = " INSERT INTO informes (id,centro, estado ,tipoxml ,tipobin,canal,flag,version,ultimoguardado"
                    + ",bloqueado,ambito ,descripcion,fecha,hora,servicio,docubin,paciente,tipo_documento,problema,fecha_proceso,referencia)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            // final Blob blod = statement.getConnection().createBlob();
            // FileCopyUtils.copy(is, blob.setBinaryStream(1));
            statement.setLong(1, informe.getId());
            statement.setLong(2, informe.getCentro().getId());
            statement.setInt(3, informe.getEstado());
            statement.setInt(4, informe.getTipoxml());
            statement.setInt(5, informe.getTipobin());
            statement.setLong(6, informe.getCanal());
            statement.setInt(7, informe.getFlag());
            statement.setInt(8, informe.getVersion());
            statement.setLong(9, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
            statement.setInt(10, informe.getBloqueado());
            statement.setLong(11, informe.getAmbito());
            statement.setString(12, informe.getDescripcion());
            statement.setLong(13, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
            statement.setLong(14, Utilidades.getHoraNumeroAcual());
            statement.setLong(15, informe.getServicio().getId());
            // statement.setBlob(16, is);
            // statement.setBinaryStream(16, is);
            logger.debug("Antes de binary........................................");
            statement.setBinaryStream(16, in, (int) informe.getFicheroInformeFile().length());
            statement.setLong(17, informe.getPaciente().getId());
            statement.setLong(18, informe.getTipo_documento());
            if (informe.getProblema() == null) {
                statement.setNull(19, Types.INTEGER);
            } else {
                statement.setLong(19, informe.getProblema().getId());
            }

            statement.setLong(20, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
            statement.setString(21, Long.toString(informe.getId()));

            // tiene efecto para que no de error el log de sql . Si  no hay proceso se pone null
            // esto  pasa por ejemplo cuando se insertan los informes de los rn 
            if (informe.getProblema() != null) {
                idp = Long.toString(informe.getProblema().getId());
            }

            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO informes (id,centro, estado ,tipoxml ,tipobin,canal,flag,version,ultimoguardado"
                    + ",bloqueado,ambito ,descripcion,fecha,hora,servicio,docubin,paciente,tipo_documento,problema,fecha_proceso)"
                    + " VALUES (" + informe.getId() + "," + informe.getEstado() + "," + informe.getTipoxml() + ","
                    + informe.getTipobin() + "," + informe.getCanal() + "," + informe.getFlag() + ","
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()).toString() + "," + informe.getBloqueado()
                    + "," + informe.getAmbito() + ",'" + informe.getDescripcion() + "',"
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()).toString() + ","
                    + Utilidades.getHoraNumeroAcual() + "," + is + "," + informe.getPaciente().getId() + ","
                    + informe.getTipo_documento() + "," + idp + ","
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()).toString() + ")  ");
        } catch (SQLException e) {
            logger.error(" INSERT INTO informes (id,centro, estado ,tipoxml ,tipobin,canal,flag,version,ultimoguardado"
                    + ",bloqueado,ambito ,descripcion,fecha,hora,servicio,docubin,paciente,tipo_documento,problema,fecha_proceso)"
                    + " VALUES (" + informe.getId() + "," + informe.getEstado() + "," + informe.getTipoxml() + ","
                    + informe.getTipobin() + "," + informe.getCanal() + "," + informe.getFlag() + ","
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()).toString() + "," + informe.getBloqueado()
                    + "," + informe.getAmbito() + ",'" + informe.getDescripcion() + "',"
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()).toString() + ","
                    + Utilidades.getHoraNumeroAcual() + "," + is + "," + informe.getPaciente().getId() + ","
                    + informe.getTipo_documento() + "," + idp + ","
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()).toString() + ")  ", e);
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return insertado;
    }

    public Informe getRegistroResulset(ResultSet rs, boolean conCampos_I, Paciente paciente, Centro centro,
            Servicio servicio, Usuario usuario) {
        Informe informe = new Informe();

        try {
            informe.setNumeroOrden(rs.getInt("numeroorden"));
            informe.setId(rs.getLong("id"));
            informe.setDescripcion(rs.getString("descripcion"));
            if (paciente == null) {
                informe.setPaciente(new PacienteDAO().getPacientePorId(rs.getLong("paciente"), false));
            } else {
                informe.setPaciente(paciente);
            }
            informe.setProblema(new Proceso(rs.getLong("problema")));
            informe.setEpisodio(rs.getLong("episodio"));
            if (centro == null) {
                informe.setCentro(new CentroDAO().getRegistroId(rs.getLong("centro")));
            } else {
                informe.setCentro(centro);
            }
            if (servicio == null) {
                informe.setServicio(new ServiciosDAO().getRegistroId(rs.getLong("servicio")));
            } else {
                informe.setServicio(servicio);
            }
            informe.setReferecia(rs.getString("referencia"));
            informe.setFecha(Utilidades.getFechaLocalDate(rs.getLong("fecha")));
            informe.setHora(rs.getLong("hora"));
            informe.setEstado(rs.getInt("estado"));
            // informe.setDocuxml(docuxml);
            informe.setTipoxml(rs.getInt("tipoxml"));
            // informe.setDocubin(docubin);
            informe.setTipobin(rs.getInt("tipobin"));
            informe.setPeticion(rs.getLong("peticion"));
            if (usuario == null) {
                informe.setUserid(new UsuarioDAO().getUsuarioUserid(rs.getString("userid"), false));
            } else {
                informe.setUserid(usuario);
            }

            informe.setCanal(rs.getLong("canal"));
            informe.setTipoinforme(rs.getInt("tipoinforme"));
            informe.setUseridauth(new Usuario(rs.getString("useridauth")));
            informe.setSrvauth(new Servicio(rs.getLong("srvauth")));
            informe.setUseridredactor(new Usuario(rs.getString("useridredactor")));

            informe.setPlantalla_editor(rs.getLong("plantilla_editor"));

            informe.setFlag(rs.getInt("flag"));
            informe.setPertenece(rs.getLong("pertenece"));
            informe.setVersion(rs.getInt("version"));
            informe.setNive_visibilidad(rs.getInt("nivel_visibilidad"));
            informe.setSubservicio(rs.getLong("subservicio"));
            informe.setUseridpeticionario(new Usuario(rs.getString("useridpeticionario")));
            informe.setVisto(rs.getInt("visto"));
            informe.setUltimoguardado(rs.getLong("ultimoguardado"));
            informe.setBloqueado(rs.getInt("bloqueado"));
            informe.setAlmacenamiento(rs.getLong("almacenamiento"));
            informe.setTipo_documento(rs.getLong("tipo_documento"));
            informe.setAmbito(rs.getLong("ambito"));
            informe.setServicio_realizador(new Servicio(rs.getLong("servicio_realizador")));
            informe.setFecha_proceso(rs.getLong("fecha_proceso"));
            informe.setReferencia_almacenamiento(rs.getString("referencia_almacenamiento"));
            informe.setNum_accesos(rs.getInt("num_accesos"));
            informe.setProblema(new Proceso(rs.getLong("problema")));
            informe.setUser_visto(new Usuario(rs.getString("user_visto")));
            informe.setFecha_visto(rs.getLong("fecha_visto"));
            informe.setComentario_Visto(rs.getString("comentario_visto"));
            if (conCampos_I == true) {
                informe.setListaCampos(getListaCamposInformeso(informe.getId()));
            }
        } catch (SQLException e) {
            logger.error(NotificacionInfo.SQLERRORRESULSET, e);
        }
        return informe;
    }

    public Campos_i getCamposResulSet(ResultSet rs) {
        Campos_i campo_i = new Campos_i();

        try {
            campo_i.setId(rs.getLong("id"));
            campo_i.setInforme(rs.getLong("informe"));
            campo_i.setItem(rs.getLong("item"));
            campo_i.setUserid(rs.getString("userid"));
            campo_i.setFecha(rs.getLong("fecha"));
            campo_i.setHora(rs.getLong("hora"));
            campo_i.setDescripcion(rs.getString("descripcion"));
            campo_i.setUnidades(rs.getString("unidades"));
            campo_i.setReferencias(rs.getString("referencias"));
            campo_i.setFlags(rs.getString("flags"));
            campo_i.setDato(rs.getClob("dato"));
            campo_i.setCodigo(rs.getString("codigo"));
            campo_i.setTipo_codigo(rs.getString("tipo_codigo"));
            campo_i.setEstado(rs.getInt("estado"));
            campo_i.setPegado_en_informe(rs.getInt("pegado_en_informe"));
            campo_i.setDatobin(rs.getBlob("datobin"));
            campo_i.setNivel_visibilida(rs.getInt("nivel_visibilidad"));
            campo_i.setLateralidad(rs.getLong("lateralidad"));
            campo_i.setItemvalor(rs.getLong("itemvalor"));
            campo_i.setCanal(rs.getLong("canal"));
            campo_i.setPrueba(rs.getLong("prueba"));
        } catch (SQLException e) {
            logger.error(NotificacionInfo.SQLERRORRESULSET, e);
        }

        return campo_i;
    }

    public ArrayList<Campos_i> getListaCamposInformeso(Long id) {
        Connection connection = null;
        ArrayList<Campos_i> listaCampos = new ArrayList<Campos_i>();
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT *  FROM campos_i  WHERE informe = ?  AND estado=? ORDER BY  id";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setInt(2, Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            ResultSet resuSulset = statement.executeQuery();
            while (resuSulset.next()) {
                Campos_i campo_i = getCamposResulSet(resuSulset);
                listaCampos.add(campo_i);
            }
            statement.close();
            logger.debug("SELECT *  FROM campos_i  WHERE informe = " + id + "  AND estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
        } catch (SQLException e) {
            logger.error("SELECT *  FROM campos_i  WHERE informe = " + id + "  AND estado="
                    + Registro.VAR_RESGISTRO_ESTADO_NORMAL);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaCampos;
    }

    @Override
    public Informe getRegistroId(Long id) {
        Connection connection = null;
        Informe informe = null;
        String sqlbb = "";
        try {
            connection = super.getConexionBBDD();
            sqlbb = "SELECT  row_number()  as numeroorden," + sql + " AND  id= " + id;
            logger.debug(sqlbb);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sqlbb);

            if (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, null);

                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);

                Centro centroParam = CentroDAO.getCentroRs(resulSet, null);

                Centro centroPriParam = CentroDAO.getCentroPrimariaRs(resulSet, null);

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, null);

                informe = getRegistroResulset(resulSet, false, pacienteParam, centroParam, servicioParam, usuarioParam);

            }
            statement.close();
            logger.debug(sqlbb);
        } catch (SQLException e) {
            logger.debug(sqlbb, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return informe;
    }

    @Override
    public String getSqlWhere(String cadena) {
        return null;
    }

    @Override
    public boolean borraDatos(Object objeto) {
        return false;
    }

    /**
     * Gets the id informe des tipo. Recupera el id del informe para ese
     * paciente, descripcion y tipo de documento. Este método se usa para no
     * repetir informes en los procesos que tienen que ser únicos; por ejemplo
     * en el de screening de mama donde el informe que hace la empresa es único
     *
     * @param paciente un paciente
     * @param descrString la descripcion del informe
     * @param tipo el tipo de documento
     * @return el id del infome si existe , 0 si no existe.
     */
    public Long getIdInformeDesTipo(Paciente paciente, String descrString, Long tipo, Long proceso) {
        Connection connection = null;
        Long id = new Long(0);
        informe = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id FROM informes  WHERE estado=2 AND paciente=?  AND trim(descripcion)= '"
                    + descrString.trim() + "' AND tipo_documento=?  AND problema=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            statement.setLong(2, tipo);
            statement.setLong(3, proceso);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                id = resulSet.getLong("id");
            }
            statement.close();
            logger.debug(
                    sql = "SELECT id FROM informes  WHERE paciente=" + paciente.getId() + "  AND trim(descripcion)= '"
                    + descrString.trim() + "' AND tipo_documento=" + tipo + "  AND problema=" + proceso + "");
        } catch (SQLException e) {
            logger.error(
                    sql = "SELECT id FROM informes  WHERE paciente=" + paciente.getId() + "  AND trim(descripcion)= '"
                    + descrString.trim() + "' AND tipo_documento=" + tipo + "  AND problema=" + proceso + "");
            logger.error(CentroDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return id;
    }

    public Long getIdInformeDescripcion(Paciente paciente, String descrString) {
        Connection connection = null;
        Long id = new Long(0);
        informe = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id FROM informes  WHERE estado=2 AND paciente=?  AND trim(descripcion)= '"
                    + descrString.trim() + "' ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());

            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                id = resulSet.getLong("id");
            }
            statement.close();
            logger.debug(sql = "SELECT id FROM informes  WHERE paciente=" + paciente.getId()
                    + "  AND trim(descripcion)= '" + descrString.trim() + "' ");
        } catch (SQLException e) {
            logger.error(sql = "SELECT id FROM informes  WHERE paciente=" + paciente.getId()
                    + "  AND trim(descripcion)= '" + descrString.trim() + "' ");
            logger.error(CentroDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return id;
    }

    public String geTtextoBotonInfCanal(Paciente paciente, Long canal) {
        Connection connection = null;
        String texto = "";
        String texto1 = "";
        if (paciente != null)
			try {
            connection = super.getConexionBBDD();
            sql = "SELECT  i.fecha,i.hora,SUBSTR( c.nombre,6,100) as nombre FROM informes i JOIN canales c ON c.id=i.canal "
                    + "WHERE paciente=?  AND canal=? AND estado=? ORDER BY fecha desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            statement.setLong(2, canal);
            statement.setLong(3, Informe.VAR_RESGISTRO_ESTADO_NORMAL);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                texto1 = resulSet.getString("nombre");
                texto = Utilidades.getFechFormatoayyyymmdd(resulSet.getLong("fecha"), Constantes.SEPARADOR_FECHA)
                        + " " + Utilidades.getHoraHH_MM(resulSet.getLong("hora"));
                int total = 0;
                while (resulSet.next()) {
                    total++;
                }
                texto = texto1 + " " + total + " " + texto;
            } else {
                if (canal == Canal.CANAL_ANATOMIA) {
                    texto = " Sin informes de anatomia";
                } else if (canal == Canal.CANAL_JIMENA) {
                    texto = " Sin informes de jimena";
                } else if (canal == Canal.CANAL_LABORATORIO) {
                    texto = " Sin informes de laboratorio";
                } else if (canal == Canal.CANAL_RADIOLOGIA) {
                    texto = " Sin informes de radiologia";
                } else {
                    texto = " tipo ? ";
                }
            }
            statement.close();
            logger.debug(
                    "SELECT  i.fecha,i.hora,SUBSTR( c.nombre,6,100) as nombre FROM informes i JOIN canales c ON c.id=i.canal WHERE paciente="
                    + paciente.getId() + "  AND canal =" + canal + " and estado="
                    + Informe.VAR_RESGISTRO_ESTADO_NORMAL);
        } catch (SQLException e) {
            logger.error(
                    "SELECT  i.fecha,i.hora,SUBSTR( c.nombre,6,100) as nombre FROM informes i JOIN canales c ON c.id=i.canal WHERE paciente="
                    + paciente.getId() + "  AND canal =" + canal + " and estado="
                    + Informe.VAR_RESGISTRO_ESTADO_NORMAL);
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return texto;
    }

    public String getWhereSql(Paciente paciente, Centro centro, Servicio servicio, Long canal,
            LocalDate desde, LocalDate hasta, String orden, Centro centroPri, String cias, Long plantilla_editor,
            Provincia provincia, Municipio municipio, PagiLisReg paginacion, String sqlParam) {
        String sqlbb = sqlParam;

        sqlbb = sqlbb.concat(" AND i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL);
        if (paciente != null) {
            sqlbb = sqlbb.concat("   AND i.paciente=" + paciente.getId());
        }
        if (centro != null) {
            sqlbb = sqlbb.concat("   AND i.centro=" + centro.getId());
        }
        if (servicio != null) {
            sqlbb = sqlbb.concat("   AND i.servicio=" + servicio.getId());
        }
        if (canal != null) {
            sqlbb = sqlbb.concat("   AND i.canal=" + canal);
        }
        if (desde != null) {
            sqlbb = sqlbb.concat(" AND i.fecha>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
        }
        if (hasta != null) {
            sqlbb = sqlbb.concat(" AND i.fecha<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
        }
        if (centroPri != null) {
            sqlbb = sqlbb.concat(" AND p.p.centroprimaria=" + centroPri.getId());
        }
        if (cias != null) {
            sqlbb = sqlbb.concat(" AND p.cias='" + cias + "'");
        }
        if (plantilla_editor != null) {
            sqlbb = sqlbb.concat(" AND i.plantilla_editor=" + plantilla_editor);
        }
        if (provincia != null) {
            sqlbb = sqlbb.concat(" AND    p.provincia=" + provincia.getCodigo());
        }
        if (municipio != null && provincia != null) {
            sqlbb = sqlbb.concat(" AND  p.municipio=" + municipio.getId() + " AND p.provincia=" + provincia.getCodigo());
        }
        if (orden.equals(INFORMESORDENFECHA)) {
            sqlbb = sqlbb.concat(" ORDER BY i.fecha,i.hora");
        } else if (orden.equals(INFORMESORDENFECHADESC)) {
            sqlbb = sqlbb.concat(" ORDER BY i.fecha DESC,i.hora DESC ");
        } else if (orden.equals(INFORMESORDENPACIENTE)) {
            sqlbb = sqlbb.concat(" ORDER BY i.paciente,i.fecha,i.hora");
        }

        return sqlbb;
    }

    public Map<Long, Informe> getMapInformes(Paciente paciente, Centro centro, Servicio servicio, Long canal,
            LocalDate desde, LocalDate hasta, String orden, Centro centroPri, String cias, Long plantilla_editor,
            Provincia provincia, Municipio municipio, PagiLisReg paginacion) {

        Connection connection = null;
        Map<Long, Informe> linkedHashMap = new LinkedHashMap<Long, Informe>();
        String sqlbb;
        if (orden.equals(INFORMESORDENFECHA)) {
            sqlbb = "SELECT  row_number() over (ORDER BY i.fecha,i.hora) as numeroorden ,";
        } else if (orden.equals(INFORMESORDENFECHADESC)) {
            sqlbb = "SELECT  row_number() over (ORDER BY i.fecha DESC,i.hora DESC) as numeroorden ,";
        } else if (orden.equals(INFORMESORDENPACIENTE)) {
            sqlbb = "SELECT  row_number() over (ORDER BY i.paciente,i.fecha,i.hora) as numeroorden ,";
        } else {
            sqlbb = "SELECT  row_number() over (ORDER BY i.fecha,i.hora) as numeroorden ,";
        }
        sqlbb = sqlbb.concat(sql);
        sqlbb = getWhereSql(paciente, centro, servicio, canal, desde, hasta, orden, centroPri, cias, plantilla_editor, provincia, municipio, paginacion, sqlbb);

        try {
            connection = super.getConexionBBDD();

            logger.debug(sqlbb);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sqlbb);

            int contador = 0;
            while (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, paciente);

                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);

                Centro centroParam = CentroDAO.getCentroRs(resulSet, centro);

                Centro centroPriParam = CentroDAO.getCentroPrimariaRs(resulSet, null);

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, servicio);

                Informe informe = getRegistroResulset(resulSet, false, pacienteParam, centroParam, servicioParam, usuarioParam);

                if (paginacion != null) {
                    if (paginacion.getDireccion() == 1) {
                        if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                            linkedHashMap.put(informe.getId(), informe);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    } else {
                        if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                            linkedHashMap.put(informe.getId(), informe);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    }
                } else {
                    linkedHashMap.put(informe.getId(), informe);
                }
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(sqlbb);
            logger.error(NotificacionInfo.SQLERRORRESULSET, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return linkedHashMap;
    }

    public ArrayList<Informe> getListaInformesPaginados(Paciente paciente, Centro centro, Servicio servicio, Long canal,
            LocalDate desde, LocalDate hasta, String orden, Centro centroPri, String cias, Long plantilla_editor,
            Provincia provincia, Municipio municipio, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Informe> listaInformes = new ArrayList<Informe>();
        String sqlbb = "";

        try {
            connection = super.getConexionBBDD();
            if (orden.equals(INFORMESORDENFECHA)) {
                sqlbb = "SELECT  row_number() over (ORDER BY i.fecha,i.hora) as numeroorden ,";
            } else if (orden.equals(INFORMESORDENFECHADESC)) {
                sqlbb = "SELECT  row_number() over (ORDER BY i.fecha DESC,i.hora DESC) as numeroorden ,";
            } else if (orden.equals(INFORMESORDENPACIENTE)) {
                sqlbb = "SELECT  row_number() over (ORDER BY i.paciente,i.fecha,i.hora) as numeroorden ,";
            } else {
                sqlbb = "SELECT  row_number() over (ORDER BY i.fecha,i.hora) as numeroorden ,";
            }
            sqlbb = sqlbb.concat(sql);
            sqlbb = getWhereSql(paciente, centro, servicio, canal, desde, hasta, orden, centroPri, cias, plantilla_editor, provincia, municipio, paginacion, sqlbb);

            logger.debug(sqlbb);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sqlbb);

            int contador = 0;
            while (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, paciente);

                Usuario usuarioParam = UsuarioDAO.getUsuairoRs(resulSet, null);

                Centro centroParam = CentroDAO.getCentroRs(resulSet, centro);

                Centro centroPriParam = CentroDAO.getCentroPrimariaRs(resulSet, null);

                Servicio servicioParam = ServiciosDAO.getServicioRs(resulSet, servicio);

                Informe informe = getRegistroResulset(resulSet, false, pacienteParam, centroParam, servicioParam, usuarioParam);

                if (paginacion != null) {
                    if (paginacion.getDireccion() == 1) {
                        if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                            listaInformes.add(informe);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    } else {
                        if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                            listaInformes.add(informe);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    }
                } else {
                    listaInformes.add(informe);
                }
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(sqlbb);
            logger.error(NotificacionInfo.SQLERRORRESULSET, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaInformes;
    }

    public ArrayList<Informe> getListaInformesPaciCanalNumero(Paciente paciente, Long canal, int cuantos) {
        Connection connection = null;
        ArrayList<Informe> listaInformes = new ArrayList<Informe>();
        if (paciente != null)
			try {
            connection = super.getConexionBBDD();
            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = "SELECT  @rownum:=@rownum+1  as numeroorden ,i.* "
                        + ", c.id as idcentro,c.codigo as codigocentro, c.descripcion as descentro,c.nemonico "
                        + " , s.id as idservicio, s.codigo as codigoservicio, s.descripcion as descservcicio  "
                        + "	FROM informes i,  (SELECT @rownum:=0) r" + " JOIN centros c ON c.id=i.centro "
                        + " JOIN servicios s ON s.id=i.servicio " + " WHERE estado = "
                        + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " paciente=" + paciente.getId() + "   AND canal="
                        + canal + "  ORDER BY fecha desc";

            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {
                sql = "SELECT   row_number() over (ORDER BY fecha,hora desc ) as numeroorden ,i.*"
                        + ", c.id as idcentro,c.codigo as codigocentro, c.descripcion as descentro,c.nemonico "
                        + " , s.id as idservicio, s.codigo as codigoservicio, s.descripcion as descservcicio  "
                        + " FROM informes i " + " JOIN centros c ON c.id=i.centro "
                        + " JOIN servicios s ON s.id=i.servicio " + " WHERE estado = "
                        + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND i.paciente=" + paciente.getId();
                if (canal != null) {
                    sql = sql.concat("   AND i.canal=" + canal);
                }

                sql = sql.concat("  ORDER BY fecha desc");
            }
            logger.debug(sql);
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            int contador = 0;
            while (resulSet.next() && (contador <= cuantos) || contador == 0) {
                Centro centroBd = new Centro(resulSet.getLong("idcentro"), resulSet.getString("codigocentro"),
                        resulSet.getString("descentro"), resulSet.getString("nemonico"));
                Servicio servicioBd = new Servicio(resulSet.getLong("idservicio"),
                        resulSet.getString("codigoservicio"), resulSet.getString("descservcicio"));
                Informe informe = getRegistroResulset(resulSet, false, paciente, centroBd, servicioBd, null);
                listaInformes.add(informe);
                contador++;
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(NotificacionInfo.SQLERRORRESULSET, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaInformes;
    }

    /**
     *
     * @param paciente
     * @param cuantos
     * @return Array list con los 'cuantos' informes del paciente que no son de
     * los canales. Canal.CANAL_ANATOMIA Canal.CANAL_JIMENA
     * Canal.CANAL_LABORATORIO Canal.CANAL_RADIOLOGIA
     */
    public ArrayList<Informe> getListaInformesPaciOtrosCanalesNumero(Paciente paciente, int cuantos) {
        Connection connection = null;
        ArrayList<Informe> listaInformes = new ArrayList<Informe>();
        if (paciente != null)
			try {
            connection = super.getConexionBBDD();
            if (persistencia.equals(Constantes.MYSQL_STRING)) {
                sql = "SELECT  @rownum:=@rownum+1  as numeroorden ,i.*" + "	FROM informes i,  (SELECT @rownum:=0) r"
                        + " WHERE estado = " + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND paciente="
                        + paciente.getId() + "   AND canal NOT IN (" + Canal.CANAL_ANATOMIA + ","
                        + Canal.CANAL_JIMENA + "," + Canal.CANAL_LABORATORIO + "," + Canal.CANAL_RADIOLOGIA
                        + ")  ORDER BY fecha desc ";
            } else if (persistencia.equals(Constantes.ORACLE_STRING)) {
                sql = "SELECT   row_number() over (ORDER BY fecha,hora desc ) as numeroorden ,i.* FROM informes i "
                        + " WHERE estado = " + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND paciente="
                        + paciente.getId() + "   AND canal NOT IN (" + Canal.CANAL_ANATOMIA + ","
                        + Canal.CANAL_JIMENA + "," + Canal.CANAL_LABORATORIO + "," + Canal.CANAL_RADIOLOGIA
                        + ")  ORDER BY fecha desc ";
            }

            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);

            logger.debug(sql);

            int contador = 0;
            while (resulSet.next() && (contador <= cuantos) || contador == 0) {
                Informe informe = getRegistroResulset(resulSet, false, paciente, null, null, null);
                listaInformes.add(informe);
                contador++;
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(NotificacionInfo.SQLERRORRESULSET, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaInformes;
    }

    public Long getIdInformeProceso(Paciente paciente, Long proceso) {

        Connection connection = null;
        Long id = new Long(0);
        if (paciente == null || proceso.equals(new Long(0))) {
            return id;
        }
        // informe = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT id FROM informes  WHERE paciente=?  AND problema=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente.getId());
            statement.setLong(2, proceso);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                id = resulSet.getLong("id");
            }
            statement.close();
            logger.debug(
                    "SELECT id FROM informes  WHERE paciente=" + paciente.getId() + "  AND problema=" + proceso + "");
        } catch (SQLException e) {
            logger.error(
                    "SELECT id FROM informes  WHERE paciente=" + paciente.getId() + "  AND problema=" + proceso + "");
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return id;
    }

    public boolean actualizaFichero(Object mensajeparam) {
        Connection connection = null;
        informe = (Informe) mensajeparam;
        boolean actualizado = false;
        FileInputStream is = null;
        try {
            connection = super.getConexionBBDD();
            FileInputStream in = new FileInputStream(informe.getFicheroInformeFile());
            sql = " UPDATE informes SET  docubin=?,  fecha=? , hora =? WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            // statement.setBlob(1, is);
            statement.setBinaryStream(1, in, (int) informe.getFicheroInformeFile().length());
            statement.setLong(2, Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()));
            statement.setLong(3, Utilidades.getHoraNumeroAcual());
            statement.setLong(4, informe.getId());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(sql = " UPDATE informes SET  docubin=" + is + ",  fecha="
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + " , hora ="
                    + Utilidades.getHoraNumeroAcual() + " WHERE id=" + informe.getId());
            actualizado = true;
        } catch (SQLException e) {
            logger.error(sql = " UPDATE informes SET  docubin=" + is + ",  fecha="
                    + Utilidades.getFechaNumeroyyymmddDefecha(LocalDate.now()) + " , hora ="
                    + Utilidades.getHoraNumeroAcual() + " WHERE id=" + informe.getId());
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    public java.sql.Blob getBlobPdfId(Long id) {
        Connection connection = null;
        java.sql.Blob pdfBlob = null;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT docubin  FROM informes  WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                pdfBlob = resulSet.getBlob("docubin");
            }
            statement.close();
            logger.debug("SELECT docubin  FROM informes  WHERE id= " + id);
        } catch (SQLException e) {
            logger.error("SELECT docubin  FROM informes  WHERE id= " + id);
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return pdfBlob;
    }

    public String getUltimoValorVariable(Paciente paciente, Variable variable) {
        String valor = "";
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "   SELECT  to_char(dato) as valor FROM campos_i,informes    WHERE"
                    + "  informes.id=campos_i.informe and informes.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND campos_i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND  item=? AND paciente =? ORDER BY informes.fecha desc ";
            ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(2, paciente.getId());
            statement.setLong(1, variable.getItem());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                valor = resulSet.getString("valor");
            }
            statement.close();
            logger.debug("   SELECT  to_char(dato) as valor FROM campos_i,informes    WHERE"
                    + "  informes.id=campos_i.informe and informes.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND campos_i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND  item=" + variable.getItem()
                    + " AND paciente =" + paciente.getId() + " ORDER BY informes.fecha desc ");
        } catch (SQLException e) {
            logger.error("   SELECT  to_char(dato) as valor FROM campos_i,informes    WHERE"
                    + "  informes.id=campos_i.informe and informes.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND campos_i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND  item=" + variable.getItem()
                    + " AND paciente =" + paciente.getId() + " ORDER BY informes.fecha desc ", e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return valor;
    }

    public Variable getUltimoValorVariableVariable(Paciente paciente, Variable variable, LocalDate fecha) {
        Variable variableResultado = null;
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            if (fecha == null) {
                sql = "   SELECT  to_char(dato) as valor,c.*, i.fecha as fechai FROM campos_i c,informes i   WHERE"
                        + "  i.id=c.informe and i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND c.estado="
                        + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                        + " AND  c.item=? AND i.paciente =? ORDER BY i.fecha desc ";
                ;
            } else {
                sql = "   SELECT  to_char(dato) as valor,c.*, i.fecha as fechai FROM campos_i c,informes i   WHERE"
                        + " c.fecha<=" + Utilidades.getFechaNumeroyyymmddDefecha(fecha)
                        + " AND  i.id=c.informe and i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND c.estado="
                        + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                        + " AND  c.item=? AND i.paciente =? ORDER BY i.fecha desc ";
                ;

            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(2, paciente.getId());
            statement.setLong(1, variable.getItem());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                variableResultado = new Variable();
                variableResultado.setItem(resulSet.getLong("item"));
                variableResultado.setValor(resulSet.getString("valor"));
                variableResultado.setFechaValor(Utilidades.getFechaLocalDate(resulSet.getLong("fechai")));
            }
            statement.close();
            logger.debug("   SELECT  to_char(dato) as valor FROM campos_i,informes    WHERE"
                    + "  informes.id=campos_i.informe and informes.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND campos_i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND  item=" + variable.getItem()
                    + " AND paciente =" + paciente.getId() + " ORDER BY informes.fecha desc ");
        } catch (SQLException e) {
            logger.error("   SELECT  to_char(dato) as valor FROM campos_i,informes    WHERE"
                    + "  informes.id=campos_i.informe and informes.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND campos_i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND  item=" + variable.getItem()
                    + " AND paciente =" + paciente.getId() + " ORDER BY informes.fecha desc ", e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return variableResultado;
    }

    public Variable getPenUltimoValorVariableVariable(Paciente paciente, Variable variable) {
        Variable variableResultado = null;
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "   SELECT  to_char(dato) as valor,c.* FROM campos_i c,informes i   WHERE"
                    + "  i.id=c.informe and i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND c.estado="
                    + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND  c.item=? AND i.paciente =? ORDER BY i.fecha desc ";
            ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(2, paciente.getId());
            statement.setLong(1, variable.getItem());
            ResultSet resulSet = statement.executeQuery();
            int contador = 0;
            while (resulSet.next() && contador < 2) {
                variableResultado = new Variable();
                variableResultado.setItem(resulSet.getLong("item"));
                variableResultado.setValor(resulSet.getString("valor"));
                variableResultado.setFechaValor(Utilidades.getFechaLocalDate(resulSet.getLong("fecha")));
                contador++;

            }
            statement.close();
            logger.debug("   SELECT  to_char(dato) as valor FROM campos_i,informes    WHERE"
                    + "  informes.id=campos_i.informe and informes.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND campos_i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND  item=" + variable.getItem()
                    + " AND paciente =" + paciente.getId() + " ORDER BY informes.fecha desc ");
        } catch (SQLException e) {
            logger.error("   SELECT  to_char(dato) as valor FROM campos_i,informes    WHERE"
                    + "  informes.id=campos_i.informe and informes.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL
                    + " AND campos_i.estado=" + Informe.VAR_RESGISTRO_ESTADO_NORMAL + " AND  item=" + variable.getItem()
                    + " AND paciente =" + paciente.getId() + " ORDER BY informes.fecha desc ", e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return variableResultado;
    }

    @Override
    public Object getRegistroResulset(ResultSet rs) {
        // TODO Auto-generated method stub
        return null;
    }
}
