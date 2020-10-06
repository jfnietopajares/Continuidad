package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Agenda;
import es.sacyl.hnss.entity.Cama;
import es.sacyl.hnss.entity.Centro;
import es.sacyl.hnss.entity.Episodio;
import es.sacyl.hnss.entity.Historia;
import es.sacyl.hnss.entity.Municipio;
import es.sacyl.hnss.entity.Paciente;
import es.sacyl.hnss.entity.PagiLisReg;
import es.sacyl.hnss.entity.Provincia;
import es.sacyl.hnss.entity.Servicio;
import es.sacyl.hnss.entity.Zona;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * The Class PacienteDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class PacienteDAO extends ConexionDAO implements InterfaceDAO {

    private Paciente paciente;

    private ResultSet resulSet;

    private static final Logger logger = LogManager.getLogger(PacienteDAO.class);

    private String sql = "  p.id as idpaciente, p.* "
            + "              ,v.descripcion as provinciadescripcion "
            + "              ,w.descripcion as municipiodescripcion, w.id  as  municipioid "
            + "             ,h.nhc "
            + "              ,pri.id as idcentropri, pri.codigo as codigocentropri,pri.descripcion as descentropri"
            + "             , pri.nemonico as nemonicopri "
            + "              FROM pacientes  p	"
            + "              JOIN historias h ON h.paciente=p.id   "
            + "              LEFT JOIN provincias v ON v.codigo=p.provincia "
            + "              LEFT JOIN municipios w ON (w.id=p.municipio and w.provincia=p.provincia)"
            + "              LEFT JOIN centros pri ON pri.id=p.centroprimaria  "
            + "              WHERE  1=1 ";

    public static String ORDENAPELLIDOS = "APELLIDOS";
    public static String ORDENNHMEROHC = "NUMEROHC";

    /**
     * Instantiates a new paciente DAO.
     */
    public PacienteDAO() {
        super();
    }

    /**
     * Gets the paciente resulset.
     *
     * @param resulSet the resul set
     * @return the paciente resulset
     */
    public Paciente getRegistroResulset(ResultSet resulSet,  Centro centro, Servicio servicio,
            Agenda agenda, Zona zona, Cama cama, Boolean completo, Boolean conMadre) {
        try {
            paciente = new Paciente(resulSet.getLong("id"));
            paciente.setNombre(resulSet.getString("nombre"));
            paciente.setApellido1(resulSet.getString("ape1"));
            paciente.setApellido2(resulSet.getString("ape2"));
            paciente.setCodigopostal(resulSet.getString("codigopostal"));
            paciente.setDireccion(resulSet.getString("direccion"));
            paciente.setDni(resulSet.getString("dni"));
            paciente.setPais(resulSet.getString("pais"));
            Long fnacLong = resulSet.getLong("fnac");
            if (!fnacLong.equals(new Long(0))) {
                paciente.setFnac(Utilidades.getFechaLocalDate(resulSet.getLong("fnac")));
            } else {
                paciente.setFnac(null);
            }
            paciente.setTelefono(resulSet.getString("telefono"));
            paciente.setMovil(resulSet.getString("movil"));
            paciente.setNumerohc(resulSet.getString("nhc"));
            if (conMadre == true) {
                if (resulSet.getLong("madre") != 0) {
                    paciente.setMadre(new PacienteDAO().getPacientePorId(resulSet.getLong("madre"), false));
                }
            }
            if (completo == true) {
                paciente.setMunicipio(new MunicipioDAO().getRegistroId(resulSet.getLong("municipio")));
                paciente.setProvincia(new ProvinciasDAO().getPorCodigo(resulSet.getString("provincia")));
            }
            paciente.setSexo(resulSet.getInt("sexo"));
            paciente.setExitus_codigo(resulSet.getInt("exitus_codigo"));
            paciente.setExitus_fecha(Utilidades.getFechaLocalDate(resulSet.getLong("exitus_fecha")));
            
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        return paciente;
    }

    /**
     * Gets the paciente por id.
     *
     * @param id the id
     * @return the paciente por id
     */
    public Paciente getPacientePorId(Long id, Boolean conMadre) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                paciente = getRegistroResulset(resulSet,  null, null, null, null, null, true, conMadre);
            }
            statement.close();
            logger.debug("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.id= " + id);
        } catch (SQLException e) {
            logger.error("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.id= " + id);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paciente;
    }

    public Paciente getPacientePorIdConMadre(Long id, Boolean conMadre) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.id=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                paciente = getRegistroResulset(resulSet, null, null, null, null, null, true, conMadre);
                if (conMadre == true) {
                    if (resulSet.getLong("madre") > 0) {
                        Paciente madre = new PacienteDAO().getPacientePorId(resulSet.getLong("madre"), false);
                        paciente.setMadre(madre);
                    }
                }
            }
            statement.close();
            logger.debug("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.id= " + id);
        } catch (SQLException e) {
            logger.error("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.id= " + id);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paciente;
    }

    public Paciente getPacientePorNhc(String nhc, Boolean conMadre) {
        Connection connection = null;
        String sqlbb = "";
        try {
            connection = super.getConexionBBDD();
            sqlbb = " SELECT " + sql + " AND h.nhc=?  ";
            //  sqlbb = "  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
            //        + " WHERE h.nhc=? ";

            logger.debug(" SELECT " + sql + " AND h.nhc='" + nhc + "'");

            PreparedStatement statement = connection.prepareStatement(sqlbb);
            statement.setString(1, nhc);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                //              paciente = getRegistroResulset(resulSet, "PACIENTE", null, null, null, null, null, true, conMadre);
                paciente = getPacienteRs(resulSet, null);
//                dsafd
            }
            statement.close();

        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + " SELECT " + sql + " AND h.nhc='" + nhc + "'", e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paciente;
    }

    public Paciente getPacientePorDNI(String dni, Boolean conMadre) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.dni=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                paciente = getRegistroResulset(resulSet,  null, null, null, null, null, true, conMadre);
            }
            statement.close();
            logger.debug("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.dni= '" + dni + "'");
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paciente;
    }

    public Paciente getPacientePorTarjeta(String tarjeta, Boolean conMadre) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.tarjeta=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tarjeta);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                paciente = getRegistroResulset(resulSet, null, null, null, null, null, true, conMadre);
            }
            statement.close();
            logger.debug("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.tarjeta= '" + tarjeta + "'");
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paciente;
    }

    public Paciente getPacientePorNhcConMadre(String nhc, Boolean conMadre) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE h.nhc=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nhc);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                paciente = getRegistroResulset(resulSet, null, null, null, null, null, true, conMadre);
                if (resulSet.getLong("madre") > 0) {
                    Paciente madre = new PacienteDAO().getPacientePorId(resulSet.getLong("madre"), false);
                    paciente.setMadre(madre);
                }
            }
            statement.close();
            logger.debug("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE h.nhc= " + nhc);
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paciente;
    }

    public Paciente getPacientePorTf(String tf, Long id, Boolean conMadre) {
        Connection connection = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.telefono=?  OR p.movil=? AND p.id != ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tf);
            statement.setString(2, tf);
            statement.setLong(3, id);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                paciente = getRegistroResulset(resulSet,  null, null, null, null, null, true, conMadre);
            }
            statement.close();
            logger.debug("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.telefono='" + tf + "'  OR p.movil='" + tf + "' AND p.id != " + id);
        } catch (SQLException e) {
            logger.error("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE p.telefono=" + tf + "  OR p.movil=" + tf + " AND p.id != " + id);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paciente;
    }

    public Paciente getPacientePorNhcConRegistros(String nhc, Long subambito, Boolean conMadre) {
        Connection connection = null;
        paciente = null;
        try {
            connection = super.getConexionBBDD();
            sql = "  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE h.nhc=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nhc);
            resulSet = statement.executeQuery();
            if (resulSet.next()) {
                paciente = getRegistroResulset(resulSet,  null, null, null, null, null, false, conMadre);
                paciente.setListaRegistros(new RegistroDAO().getListaRegistros(paciente.getId(), subambito));
            }
            statement.close();
            logger.debug("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE h.nhc='" + nhc + "' ");
        } catch (SQLException e) {
            logger.error("  SELECT p.*,h.nhc " + " FROM pacientes p " + " JOIN historias h ON h.paciente=p.id "
                    + " WHERE h.nhc='" + nhc + "' ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paciente;
    }

    @Override
    public boolean grabaDatos(Object objeto) {
        Paciente paciente = (Paciente) objeto;
        boolean actualizado = false;
        if (paciente.getId() == 0) {
            actualizado = this.insertaDatos(paciente);
        } else {
            actualizado = this.actualizaDatos(paciente);
        }
        return actualizado;
    }

    /**
     * Inserta datos.
     *
     * @param paciente the paciente
     * @return true, if successful
     */
    @Override
    public boolean insertaDatos(Object objeto) {
        Paciente paciente = (Paciente) objeto;
        Connection connection = null;
        boolean rowInserted = false;
        long id = 0;
        try {
            id = new UtilidadesDAO().getSiguienteId("pacientes");
            connection = super.getConexionBBDD();
            sql = " INSERT INTO pacientes (id,  nombre,  ape1,  ape2,  dni,"
                    + " direccion,  provincia,  municipio,  codigopostal,  fnac,  sexo,"
                    + " telefono,  movil)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setString(2, paciente.getNombre());
            statement.setString(3, paciente.getApellido1());
            statement.setString(4, paciente.getApellido2());
            statement.setString(5, paciente.getDni());
            statement.setString(6, paciente.getDireccion());
            statement.setString(7, paciente.getProvincia().getCodigo());
            statement.setLong(8, paciente.getMunicipio().getId());
            statement.setString(9, paciente.getCodigopostal());
            statement.setLong(10, Utilidades.getFechaYYYYmmdd(paciente.getFnac()));
            statement.setInt(11, paciente.getSexo());
            statement.setString(12, paciente.getTelefono());
            statement.setString(13, paciente.getMovil());
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" INSERT INTO pacientes (id,  nombre,  ape1,  ape2,  dni,"
                    + " direccion,  provincia,  municipio,  codigopostal,  fnac,  sexo,"
                    + " telefono,  movil)  VALUES (" + id + ",'" + paciente.getNombre() + "','"
                    + paciente.getApellido1() + "','" + paciente.getApellido2() + "','" + paciente.getDni() + "','"
                    + paciente.getDireccion() + "','" + paciente.getProvincia().getCodigo() + "',"
                    + paciente.getMunicipio().getId() + ",'" + paciente.getCodigopostal() + "',"
                    + Utilidades.getFechaYYYYmmdd(paciente.getFnac()) + "," + paciente.getSexo() + ",'"
                    + paciente.getTelefono() + "','" + paciente.getMovil() + "')  ");
            
            new HistoriaDAO().insertaNhc(new Historia(id, paciente.getNumerohc()));

        } catch (SQLException e) {
            logger.error(" INSERT INTO pacientes (id,  nombre,  ape1,  ape2,  dni,"
                    + " direccion,  provincia,  municipio,  codigopostal,  fnac,  sexo,"
                    + " telefono,  movil)  VALUES (" + id + ",'" + paciente.getNombre() + "','"
                    + paciente.getApellido1() + "','" + paciente.getApellido2() + "','" + paciente.getDni() + "','"
                    + paciente.getDireccion() + "','" + paciente.getProvincia().getCodigo() + "',"
                    + paciente.getMunicipio().getId() + ",'" + paciente.getCodigopostal() + "',"
                    + Utilidades.getFechaYYYYmmdd(paciente.getFnac()) + "," + paciente.getSexo() + ",'"
                    + paciente.getTelefono() + "','" + paciente.getMovil() + "')  ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL + sql, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return rowInserted;
    }

    /**
     * Actualiza datos.
     *
     * @param paciente the paciente
     * @return true, if successful
     */
    @Override
    public boolean actualizaDatos(Object objeto) {
        Paciente paciente = (Paciente) objeto;
        Connection connection = null;
        boolean rowUpdated = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE  pacientes SET   nombre=?,  ape1=?,  ape2=?,  dni=?,"
                    + " direccion=?,  provincia=?,  municipio=?,  codigopostal=?,  fnac=?,  sexo=?,"
                    + " telefono=?,  movil=? WHERE id=?  ";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, paciente.getNombre());
            statement.setString(2, paciente.getApellido1());
            statement.setString(3, paciente.getApellido2());
            statement.setString(4, paciente.getDni());
            statement.setString(5, paciente.getDireccion());
            statement.setString(6, paciente.getProvincia().getCodigo());
            statement.setLong(7, paciente.getMunicipio().getId());
            statement.setString(8, paciente.getCodigopostal());
            statement.setLong(9, Utilidades.getFechaYYYYmmdd(paciente.getFnac()));
            statement.setInt(10, paciente.getSexo());
            statement.setString(11, paciente.getTelefono());
            statement.setString(12, paciente.getMovil());
            statement.setLong(13, paciente.getId());
            statement.executeUpdate();
            rowUpdated = true;
            statement.close();
            logger.debug(sql = " UPDATE  pacientes SET   nombre='" + paciente.getNombre() + "',  ape1='"
                    + paciente.getApellido1() + "',  ape2='" + paciente.getApellido2() + "',  dni='" + paciente.getDni()
                    + "'," + " direccion='" + paciente.getDireccion() + "',  provincia='"
                    + paciente.getProvincia().getCodigo() + "',  municipio=" + paciente.getMunicipio().getId()
                    + ",  codigopostal='" + paciente.getCodigopostal() + "',  fnac="
                    + Utilidades.getFechaYYYYmmdd(paciente.getFnac()) + ",  sexo=" + paciente.getSexo()
                    + "," + " telefono='" + paciente.getTelefono() + "',  movil=" + paciente.getMovil() + " WHERE id=  "
                    + paciente.getId());
            new HistoriaDAO().actualizaDatos(new Historia(paciente.getId(), paciente.getNumerohc()));

        } catch (SQLException e) {
            logger.error(sql = " UPDATE  pacientes SET   nombre='" + paciente.getNombre() + "',  ape1='"
                    + paciente.getApellido1() + "',  ape2='" + paciente.getApellido2() + "',  dni='" + paciente.getDni()
                    + "'," + " direccion='" + paciente.getDireccion() + "',  provincia='"
                    + paciente.getProvincia().getCodigo() + "',  municipio=" + paciente.getMunicipio().getId()
                    + ",  codigopostal='" + paciente.getCodigopostal() + "',  fnac="
                    + Utilidades.getFechaYYYYmmdd(paciente.getFnac()) + ",  sexo=" + paciente.getSexo()
                    + "," + " telefono='" + paciente.getTelefono() + "',  movil=" + paciente.getMovil() + " WHERE id=  "
                    + paciente.getId());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return rowUpdated;
    }

    /**
     * Gets the sql where.
     *
     * @param cadena the cadena
     * @return the sql where
     */
    public String getSqlWhere(String cadena) {
        String[] apellidos = cadena.toUpperCase().split(" ");

        String sqlString = "";

        if (Utilidades.validarNIF(cadena) == true) {
            sqlString = sqlString.concat(" AND  dni='" + cadena + "'");
        } else if (Utilidades.isNumeric(cadena)) {
            sqlString = sqlString.concat(" AND nhc='" + cadena + "'");
        } else if (apellidos.length > 0) {
            if (apellidos.length == 1 && apellidos[0] != null && apellidos[0] != "") {
                sqlString = sqlString.concat(" AND ( ape1 LIKE '" + apellidos[0].trim() + "%' ");
                sqlString = sqlString.concat(" OR ape2 LIKE '" + apellidos[0].trim() + "%' ");
                sqlString = sqlString.concat(" OR nombre LIKE '" + apellidos[0].trim() + "%'   )");
            } else if (apellidos.length == 2 && apellidos[0].trim() != null && apellidos[1].trim() != null) {
                sqlString = sqlString.concat(" AND  ape1 LIKE '" + apellidos[0].trim() + "%'  AND  ape2 LIKE '"
                        + apellidos[1].trim() + "%' ");
            } else if (apellidos.length == 3 && apellidos[0].trim() != null && apellidos[1].trim() != null
                    && apellidos[2].trim() != null) {
                sqlString = sqlString.concat(" AND  ape1 LIKE '" + apellidos[0].trim() + "%'  AND  ape2 LIKE '"
                        + apellidos[1].trim() + "%'  AND nombre LIKE '" + apellidos[2].trim() + "%'");
            }
        }

        //   sqlString = sqlString.concat(" ORDER BY p.ape1,p.ape2,p.nombre ");
        return sqlString;
    }

    public String getSqlWhere(String ape1, String ape2, String nombre, String numerohc) {
        String sqlString = "";
        if (ape1 != null && !ape1.isEmpty()) {
            sqlString = sqlString.concat(" AND ape1 LIKE '" + ape1.trim().toUpperCase() + "%' ");
        }
        if (ape2 != null && !ape2.isEmpty()) {
            sqlString = sqlString.concat(" AND ape2 LIKE '" + ape2.trim().toUpperCase() + "%' ");
        }
        if (nombre != null && !nombre.isEmpty()) {
            sqlString = sqlString.concat(" AND nombre LIKE '%" + nombre.trim().toUpperCase() + "%' ");
        }
        if (numerohc != null && !numerohc.isEmpty()) {
            sqlString = sqlString.concat(" AND nhc LIKE '%" + numerohc.trim() + "%' ");
        }
        return sqlString;
    }

    public String getSqlWhere(Centro centro, Servicio servicio) {
        String sqlString = "";
        if (centro != null) {
            sqlString = sqlString.concat(" AND e.centro=" + centro.getId());
        }
        if (servicio != null) {
            sqlString = sqlString.concat(" AND e.servicio=" + servicio.getId());
        }
        return sqlString;
    }

    public String getSqlWhere(Centro centro, Servicio servicio, LocalDate desde, LocalDate hasta) {
        String sqlString = "";
        if (centro != null) {
            sqlString = sqlString.concat(" AND e.centro=" + centro.getId());
        }
        if (servicio != null) {
            sqlString = sqlString.concat(" AND e.servicio=" + servicio.getId());
        }
        if (desde != null) {
            sqlString = sqlString.concat(" AND e.fechaini>=" + Utilidades.getFechaYYYYmmdd(desde));
        }
        if (hasta != null) {
            sqlString = sqlString.concat(" AND e.fechafin<=" + Utilidades.getFechaYYYYmmdd(hasta));
        }
        return sqlString;
    }

    public String getSqlWhere(Long claseEpisodio, Centro centro, Servicio servicio, Zona zona, LocalDate fecha,
            Agenda agenda) {
        String sqlString = "";
        if (!claseEpisodio.equals(new Long(0))) {
            sqlString = sqlString.concat(" AND e.clase=  " + claseEpisodio);
        }
        if (centro != null) {
            sqlString = sqlString.concat(" AND e.centro=" + centro.getId());
        }
        if (servicio != null) {
            sqlString = sqlString.concat(" AND e.servicio=" + servicio.getId());
        }
        if (zona != null && zona.getId() != null) {
            sqlString = sqlString.concat(" AND c.zona=" + zona.getId());
        }
        if (agenda != null) {
            sqlString = sqlString.concat(" AND e.agenda =" + agenda.getId());
        }

        /**
         * Si el episodio es de ingreso clase =1 la condición de fecha es que
         * fecha de inicio sea menor o igual a el parámetro y fecha final
         * =99999999 o mayor que la fecha del parámetro.
         *
         */
        if (fecha != null) {
            if (claseEpisodio.equals(Episodio.CLASE_HOSPITALIZACION.getId())) {
                sqlString = sqlString.concat(" AND  finicio<=" + Utilidades.getFechaYYYYmmdd(fecha)
                        + " AND ( ffinal=" + Constantes.FEHAFIN_DEFECTO + " OR  ffinal>="
                        + Utilidades.getFechaYYYYmmdd(fecha) + ")");
            } else if (claseEpisodio.equals(Episodio.CLASE_URGENCIAS.getId())) {
                sqlString = sqlString.concat(" AND ffinal=" + Constantes.FEHAFIN_DEFECTO);
            } else {
                sqlString = sqlString.concat(" AND finicio=" + Utilidades.getFechaYYYYmmdd(fecha));
            }
        }
        return sqlString;
    }

    public String getSqlWhere(Long claseEpisodio, Centro centro, Servicio servicio, Zona zona, LocalDate desde,
            LocalDate hasta, Agenda agenda) {
        String sqlString = "";
        if (!claseEpisodio.equals(new Long(0))) {
            sqlString = sqlString.concat(" AND e.clase=  " + claseEpisodio);
        }
        if (centro != null) {
            sqlString = sqlString.concat(" AND e.centro=" + centro.getId());
        }
        if (servicio != null) {
            sqlString = sqlString.concat(" AND e.servicio=" + servicio.getId());
        }
        if (zona != null && zona.getId() != null) {
            sqlString = sqlString.concat(" AND c.zona=" + zona.getId());
        }
        if (agenda != null) {
            sqlString = sqlString.concat(" AND e.agenda =" + agenda.getId());
        }

        /**
         * Si el episodio es de ingreso clase =1 la condición de fecha es que
         * fecha de inicio sea menor o igual a el parámetro y fecha final
         * =99999999 o mayor que la fecha del parámetro.
         *
         */
        if (hasta == null && claseEpisodio.equals(Episodio.CLASE_HOSPITALIZACION.getId())) {
            sqlString = sqlString.concat(" AND  e.finicio<=" + Utilidades.getFechaYYYYmmdd(desde));
            sqlString = sqlString.concat(" AND  e.ffinal=" + Constantes.FEHAFIN_DEFECTO);
        } else if (hasta == null && claseEpisodio.equals(Episodio.CLASE_URGENCIAS.getId())) {
            sqlString = sqlString.concat(" AND  e.finicio<=" + Utilidades.getFechaYYYYmmdd(desde));
            sqlString = sqlString.concat(" AND  e.ffinal=" + Constantes.FEHAFIN_DEFECTO);
        } else if (hasta == null && claseEpisodio.equals(Episodio.CLASE_CONSULTAS.getId())) {
            sqlString = sqlString.concat(" AND  e.finicio=" + Utilidades.getFechaYYYYmmdd(desde));
        } else {
            if (desde != null) {
                sqlString = sqlString.concat(" AND  e.finicio>=" + Utilidades.getFechaYYYYmmdd(desde));
            }
            if (hasta != null) {
                sqlString = sqlString.concat(" AND  e.ffinal<=" + Utilidades.getFechaYYYYmmdd(hasta));
            }
        }
        return sqlString;
    }

    public String getSqlWhereProceso(Centro centro, Servicio servicio, LocalDate desde, LocalDate hasta) {
        String sqlString = "";
        if (centro != null) {
            sqlString = sqlString.concat(" AND s.centro=" + centro.getId());
        }
        if (servicio != null) {
            sqlString = sqlString.concat(" AND s.servicio=" + servicio.getId());
        }
        if (desde != null) {
            sqlString = sqlString.concat(" AND s.fechaini>=" + Utilidades.getFechaYYYYmmdd(desde));
        }
        if (hasta != null) {
            sqlString = sqlString.concat(" AND s.fechafin<=" + Utilidades.getFechaYYYYmmdd(hasta));
        }
        return sqlString;
    }

    /**
     * Gets the paginacion datosa pacientes apellidos.
     *
     * @param cadena the cadena
     * @param subambito the subambito
     * @return the paginacion datosa pacientes apellidos
     */
    public PagiLisReg getPaginacionPaciente(String cadena, String tipo, Long valor, Centro centro, Servicio servicio,
            Zona zona, LocalDate fecha, Agenda agenda) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            switch (tipo) {
                case "PROCESOS":
                    if (!valor.equals(new Long(0))) {
                        sql = "SELECT   count(*) as numero " + "	" + "FROM pacientes p,  problemas s, historias h"
                                + " WHERE h.paciente = p.id AND p.id = s.paciente  AND s.subambito=  " + valor
                                + " AND s.fechafin= " + Constantes.FEHAFIN_DEFECTO;
                        sql = sql.concat(getSqlWhere(centro, servicio));
                    } else {
                        sql = " SELECT count(*) as numero  "
                                + " FROM pacientes  p "
                                + "	JOIN historias h ON h.paciente=p.id"
                                + "  WHERE 1=1  ";
                    }
                    break;
                case "EPISODIOS":
                    sql = "SELECT  count(*) as numero " + "	FROM   episodios e" + "	JOIN pacientes p ON p.id=e.paciente"
                            + "	JOIN historias h ON h.paciente=e.paciente"
                            + " LEFT JOIN camas c ON c.id=e.idcama	WHERE 1=1  ";
                    sql = sql.concat(getSqlWhere(valor, centro, servicio, zona, fecha, agenda));
                    break;
            }

            sql = sql.concat(getSqlWhere(cadena));
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                contador = resulSet.getInt("numero");
            }
            paginacion.setPrimero(1);
            paginacion.setUltimo(contador);
            paginacion.setRegistrosTotales(contador);
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paginacion;
    }

    public PagiLisReg getPaginacionPaciente(String cadena, String tipo, Long valor, Centro centro, Servicio servicio,
            Zona zona, LocalDate desde, LocalDate hasta, Agenda agenda) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            switch (tipo) {
                case "PROCESOS":
                    if (!valor.equals(new Long(0))) {
                        sql = "SELECT   count(*) as numero " + "	" + "FROM pacientes p,  problemas s, historias h"
                                + " WHERE h.paciente = p.id AND p.id = s.paciente  AND s.subambito=  " + valor;

                        if (desde == null && hasta == null) {
                            sql = sql.concat(" AND s.fechafin= " + Constantes.FEHAFIN_DEFECTO);
                        } else {
                            sql = sql.concat(getSqlWhereProceso(centro, servicio, desde, hasta));
                        }
                    } else {
                        sql = " SELECT count(*) as numero  FROM pacientes  WHERE 1=1 ";
                    }
                    break;
                case "EPISODIOS":
                    sql = "SELECT  count(*) as numero " + "	FROM   episodios e" + "	JOIN pacientes p ON p.id=e.paciente"
                            + "	JOIN historias h ON h.paciente=e.paciente"
                            + " LEFT JOIN camas c ON c.id=e.idcama	WHERE 1=1  ";
                    sql = sql.concat(getSqlWhere(valor, centro, servicio, zona, desde, hasta, agenda));
                    break;
            }

            sql = sql.concat(getSqlWhere(cadena));
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                contador = resulSet.getInt("numero");
            }
            paginacion.setPrimero(1);
            paginacion.setUltimo(contador);
            paginacion.setRegistrosTotales(contador);
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paginacion;
    }

    public PagiLisReg getPaginacionPaciente(String ape1, String ape2, String nombre, String numerohc, Integer registrosPorPagina) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        paginacion.setNumeroRegistrosPagina(registrosPorPagina);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();

            sql = " SELECT count(*) as numero  FROM pacientes  WHERE 1=1 ";

            sql = sql.concat(getSqlWhere(ape1, ape2, nombre, numerohc));
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                contador = resulSet.getInt("numero");
            }
            paginacion.setPrimero(1);
            paginacion.setAnterior(1);
            paginacion.setUltimo(contador);
            paginacion.setRegistrosTotales(contador);
            paginacion.setSiguiente(paginacion.getAnterior() + paginacion.getNumeroRegistrosPagina());
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paginacion;
    }

    /**
     * Gets the lista pacientes. Método sobre esccrito que recupera datos de
     * pacientes. Si el valor del parametro es un dni hace la búsqueda por dni.
     * Si es no hace la busqueda por nhc
     *
     * @param dninhc the dninhc
     * @return the lista pacientes
     */
    public ArrayList<Paciente> getListaPacientes(String dninhc, Boolean conMadre) {
        Connection connection = null;
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();

            sql = " SELECT p.*,h.nhc FROM pacientes p,historias h  WHERE p.id=h.paciente  ";

            if (Utilidades.validarNIF(dninhc) == true) {
                sql = sql.concat(" AND  dni='" + dninhc + "'");
            } else {
                sql = sql.concat(" AND nhc='" + dninhc + "'");
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                listaPacientes.add((Paciente) getRegistroResulset(resulSet,  null, null, null, null, null,
                        false, conMadre));
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaPacientes;
    }

   
    public ArrayList<Paciente> getListaPacientesPaginados(String ape1, String ape2, String nombre, String numerohc, PagiLisReg paginacion, String orden
    ) {
        Connection connection = null;
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        int contador = 0;
        String sqlbb = "";
        try {

            connection = super.getConexionBBDD();
            if (orden.equals(ORDENAPELLIDOS)) {
                sqlbb = "SELECT  row_number() over (ORDER BY ape1,ape2,nombre) as numeroorden ,";
            } else if (orden.equals(ORDENNHMEROHC)) {
                sqlbb = "SELECT  row_number() over (ORDER BY nhc ) as numeroorden, ";
            } else {
                sqlbb = "SELECT  row_number() over (ORDER BY ape1,ape2,nombre) as numeroorden, ";
            }

            sqlbb = sqlbb.concat(sql);
            sqlbb = sqlbb.concat(getSqlWhere(ape1, ape2, nombre, numerohc));

            if (orden.equals("APELLIDOS")) {
                sqlbb = sqlbb.concat("ORDER BY ape1,ape2,nombre ");
            } else if (orden.equals("NUMEROHC")) {
                sqlbb = sqlbb.concat("ORDER BY ape1,ape2,nombre ");
            } else {
                sqlbb = sqlbb.concat("ORDER BY ape1,ape2,nombre ");
            }
            Statement statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sqlbb);

            while (resulSet.next()) {
                Paciente paciente = getPacienteRs(resulSet, null);
                paciente.setNumeroOrden(resulSet.getInt("numeroOrden"));
                if (paginacion != null) {
                    paciente.setNumeroOrden(resulSet.getInt("numeroorden"));
                    if (paginacion.getDireccion() == 1) {
                        if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                            listaPacientes.add(paciente);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    } else {
                        if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                            listaPacientes.add(paciente);
                            contador++;
                            if (contador >= paginacion.getNumeroRegistrosPagina()) {
                                break;
                            }
                        }
                    }
                } else {
                    listaPacientes.add(paciente);
                }
            }
            statement.close();
            logger.debug(sqlbb);
        } catch (SQLException e) {
            logger.error(sqlbb, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return listaPacientes;
    }

    /**
     * Borra datos.
     *
     * @param paciente the paciente
     * @return true, if successful
     */
    @Override

    public boolean borraDatos(Object objeto) {
        Paciente paciente2 = (Paciente) objeto;
        Connection connection = null;
        boolean borrado = false;
        try {
            borrado = new HistoriaDAO().borraDatos(paciente2);
            connection = super.getConexionBBDD();
            sql = " DELETE FROM pacientes WHERE id=?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, paciente2.getId());
            borrado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" DELETE FROM pacientes WHERE id=  " + paciente2.getId());
        } catch (SQLException e) {
            logger.error(" DELETE FROM pacientes WHERE id=  " + paciente2.getId());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return borrado;
    }

    public boolean getReferenciasExternas(Long idPaciente) {
        Connection connection = null;
        boolean referencias = true;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT paciente FROM  problemas WHERE paciente=? "
                    + " UNION SELECT paciente FROM registros WHERE paciente=? "
                    + " UNION SELECT paciente FROM peticiones WHERE paciente=? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idPaciente);
            statement.setLong(2, idPaciente);
            statement.setLong(3, idPaciente);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                referencias = false;
            }
            statement.close();
            logger.debug("SELECT paciente FROM  problemas WHERE paciente= " + idPaciente
                    + " UNION SELECT paciente FROM registros WHERE paciente= " + idPaciente
                    + " UNION SELECT paciente FROM peticiones WHERE paciente= " + idPaciente);
        } catch (SQLException e) {
            logger.error("SELECT paciente FROM  problemas WHERE paciente= " + idPaciente
                    + " UNION SELECT paciente FROM registros WHERE paciente= " + idPaciente
                    + " UNION SELECT paciente FROM peticiones WHERE paciente= " + idPaciente);
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return referencias;
    }

    public boolean setDatosMadre(Paciente hijo, Paciente madre) {
        Connection connection = null;
        boolean rowUpdated = false;
        try {
            if (hijo.getId() != null && madre.getId() != null) {
                connection = super.getConexionBBDD();
                sql = " UPDATE  pacientes SET   madre=? WHERE id=?  ";

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setLong(1, madre.getId());
                statement.setLong(2, hijo.getId());

                statement.executeUpdate();
                rowUpdated = true;
                statement.close();
                logger.debug(" UPDATE  pacientes SET   madre=" + madre.getId() + " WHERE id=" + hijo.getId());
            }
        } catch (SQLException e) {
            logger.error(" UPDATE  pacientes SET   madre=" + madre.getId() + " WHERE id=" + hijo.getId());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return rowUpdated;
    }

    @Override
    public Object getRegistroId(Long id) {
        return null;
    }

    @Override
    public Object getRegistroResulset(ResultSet rs) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
        Método estático para crear pacientes a partir de los sql de episodios,problemas, alertas
        importante que los campos sql se llaman como indica rs
        tienes que cruzar con provincias y municipios
     */
    public static Paciente getPacienteRs(ResultSet resulSet, Paciente pacienteParam) {
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
                paciente.setCias(resulSet.getString("cias"));
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
                municipio.setId(resulSet.getLong("municipioid"));
                paciente.setMunicipio(municipio);

                Centro centroPrimariaParam = CentroDAO.getCentroPrimariaRs(resulSet, null);
                paciente.setCentroprimaria(centroPrimariaParam);

            } catch (Exception e) {
                logger.error(Constantes.SQLERRORRESULSET, e);
            }
        }
        return paciente;
    }

}
