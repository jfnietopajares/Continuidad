/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.dao;

import com.jnieto.entity.Agenda;
import com.jnieto.entity.Alerta;
import com.jnieto.entity.Cama;
import com.jnieto.entity.Centro;
import com.jnieto.entity.Episodio;
import com.jnieto.entity.Municipio;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.Proceso;
import com.jnieto.entity.Provincia;
import com.jnieto.entity.Servicio;
import com.jnieto.entity.Usuario;
import com.jnieto.entity.Variable;
import com.jnieto.entity.Zona;
import com.jnieto.ui.NotificacionInfo;
import com.jnieto.utilidades.Constantes;
import com.jnieto.utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class AlertasDAO extends ConexionDAO {

    private static final Logger logger = LogManager.getLogger(AlertasDAO.class);

    private String sqlOracle = "SELECT  row_number() over (ORDER BY fechaalta,horaalta) as numeroorden "
            + " ,a.id,a.descripcion,a.fechaalta,a.fechabaja  ,a.horaalta,a.horabaja,a.certeza,a.comentario,a.motivo,a.tipo "
            + " ,p.ape1,p.ape2,p.nombre,p.id as idpaciente,p.fnac,p.sexo,p.tarjeta,p.nss,p.dni,p.telefono,p.movil,p.nbdp,p.cias "
            + " ,p.direccion,p.codigopostal,p.provincia,p.municipio "
            + " ,v.descripcion as provinciadescripcion "
            + " ,w.descripcion as municipiodescripcion, w.id  as  municipioid "
            + " ,h.nhc "
            + " ,u.userid as usuuserid, u.apellido1 as usuapellido1,u.apellido2 as usuapellido2,u.nombre as usunombre, u.categoria as usucategoria,u.estado as usuestado  "
            + " ,pri.id as idcentropri, pri.codigo as codigocentropri,pri.descripcion as descentropri, pri.nemonico as nemonicopri "
            + "  FROM  alertas a	  "
            + "     JOIN pacientes p ON p.id=a.paciente   "
            + "     JOIN historias h ON h.paciente=p.id    "
            + "     JOIN usuarios U ON u.userid=a.userid   "
            + "     LEFT JOIN provincias v ON v.codigo=p.provincia  "
            + "     LEFT JOIN municipios w ON w.codigo=p.municipio and w.provincia=p.provincia "
            + "      LEFT JOIN centros pri ON pri.id=p.centroprimaria   "
            + "        WHERE  1=1 ";

    public AlertasDAO() {
        super();
    }

    public Alerta getRegistroResulset(ResultSet res, Paciente paciente, Usuario usuario) {
        Alerta alerta = new Alerta();
        try {
            alerta.setId(res.getLong("id"));
            if (paciente == null) {
                alerta.setPaciente(new PacienteDAO().getPacientePorId(res.getLong("paciente"), false));
            } else {
                alerta.setPaciente(paciente);
            }
            if (res.getLong("fechaalta") != Constantes.FEHAFIN_CERO) {
                alerta.setFechaalta(Utilidades.getFechaLocalDate(res.getLong("fechaalta")));
            } else {
                alerta.setFechaalta(null);
            }
            if (res.getLong("fechabaja") != Constantes.FEHAFIN_CERO
                    && res.getLong("fechabaja") != Constantes.FEHAFIN_DEFECTO) {
                alerta.setFechabaja(Utilidades.getFechaLocalDate(res.getLong("fechabaja")));
            } else {
                alerta.setFechabaja(null);
            }
            if (res.getLong("horaalta") > 0) {
                alerta.setHoraalta(res.getLong("horaalta"));
            } else {
                alerta.setHoraalta(null);
            }
            if (res.getLong("horabaja") > 0) {
                alerta.setHorabaja(res.getLong("horabaja"));
            } else {
                alerta.setHorabaja(null);
            }
            if (usuario != null) {
                alerta.setUserid(usuario);
            } else {
                alerta.setUserid(new UsuarioDAO().getUsuarioUserid(res.getString("userid"), Boolean.FALSE));
            }
            alerta.setDescripcion(res.getString("descripcion"));
            alerta.setCerteza(res.getString("certeza"));
            alerta.setComentario(res.getString("comentario"));
            alerta.setMotivo(res.getString("motivo"));
            alerta.setTipo(res.getLong("tipo"));

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return alerta;
    }

    public ArrayList<Alerta> getAlertas(Long tipo, LocalDate desde, LocalDate hasta, Paciente paciente, Boolean activas, String cias, Provincia provincia, Municipio municipio) {
        ArrayList<Alerta> lista = new ArrayList<Alerta>();
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            String sql = sqlOracle;
            if (tipo != null) {
                sql = sql.concat(" AND a.tipo=" + tipo);
            }
            if (paciente != null) {
                sql = sql.concat(" AND a.paciente=" + paciente.getId());
            }
            if (cias != null) {
                sql = sql.concat(" AND p.cias='" + cias + "'");
            }
            if (desde != null) {
                sql = sql.concat(" AND a.fechaalta>=" + Utilidades.getFechaNumeroyyymmddDefecha(desde));
            }
            if (hasta != null) {
                sql = sql.concat(" AND a.fechaalta<=" + Utilidades.getFechaNumeroyyymmddDefecha(hasta));
            }
            if (activas == true) {
                sql = sql.concat(" AND a.fechabaja=0");
            }
            if (municipio != null && provincia != null) {
                sql = sql.concat(" AND  (p.municipio=" + municipio.getId() + " AND p.provincia=" + provincia.getCodigo() + ")");
            }

            sql = sql.concat(" ORDER BY  a.fechaalta,a.horaalta ");

            logger.debug(sql);

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            while (resulSet.next()) {
                Paciente pacienteParam = PacienteDAO.getPacienteRs(resulSet, paciente);

                Usuario usuario = UsuarioDAO.getUsuairoRs(resulSet, null);
                Alerta alerta;
                alerta = getRegistroResulset(resulSet, pacienteParam, usuario);

                alerta.setNumeroorden(resulSet.getInt("numeroorden"));

                lista.add(alerta);
            }
        } catch (Exception e) {

        }
        return lista;
    }

    /*
    public Paciente getPacienteRs(ResultSet resulSet, Paciente pacienteParam) {
        Paciente paciente = null;
        if (pacienteParam != null) {
            paciente = pacienteParam;
        } else {
            try {
                paciente = new Paciente(resulSet.getLong("idpaciente"), resulSet.getString("nombre"),
                        resulSet.getString("ape1"), resulSet.getString("ape2"),
                        Utilidades.getFechaLocalDate(resulSet.getLong("fnac")), resulSet.getInt("sexo"),
                        resulSet.getString("nhc"), resulSet.getString("tarjeta"), resulSet.getString("nss"),
                        resulSet.getString("dni"), resulSet.getString("telefono"), resulSet.getString("movil"),
                        resulSet.getString("nbdp"));
                paciente.setDireccion(resulSet.getString("direccion"));
                paciente.setCodigopostal(resulSet.getString("codigopostal"));

                Provincia provincia = new Provincia();
                provincia.setCodigo(resulSet.getString("provincia"));
                provincia.setDescripcion(resulSet.getString("provinciadescripcion"));
                paciente.setProvincia(provincia);

                Municipio municipio = new Municipio();
                municipio.setProvincia(provincia);
                municipio.setDescripcion(resulSet.getString("municipiodescripcion"));
                municipio.setCodigo(resulSet.getString("municipio"));
                paciente.setMunicipio(municipio);

            } catch (Exception e) {
                logger.error(NotificacionInfo.SQLERRORRESULSET, e);
            }
        }
        return paciente;
    }
     */
 /*
    public Usuario getUsuairoRs(ResultSet resulSet, Usuario usuarioParam) {
        Usuario usuario = null;
        if (usuarioParam != null) {
            usuario = usuarioParam;
        } else {
            try {
                usuario = new Usuario();
                usuario.setUserid(resulSet.getString("usuuserid"));
                usuario.setApellido1(resulSet.getString("usuapellido1"));
                usuario.setApellido2(resulSet.getString("usuapellido2"));
                usuario.setNombre(resulSet.getString("usunombre"));
                usuario.setCargo(resulSet.getLong("usucategoria"));
                usuario.setEstado(resulSet.getInt("usuestado"));
            } catch (Exception e) {
                logger.error(NotificacionInfo.SQLERRORRESULSET, e);
            }
        }
        return usuario;
    }
     */
}
