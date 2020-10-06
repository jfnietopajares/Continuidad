package es.sacyl.hnss.dao;

import es.sacyl.hnss.entity.Categoria;
import es.sacyl.hnss.entity.Centro;
import es.sacyl.hnss.entity.Funcionalidad;
import es.sacyl.hnss.entity.PagiLisReg;
import es.sacyl.hnss.entity.Perfil;
import es.sacyl.hnss.entity.Servicio;
import es.sacyl.hnss.entity.Usuario;
import es.sacyl.hnss.entity.Zona;
import es.sacyl.hnss.excepcion.UsuarioBajaException;
import es.sacyl.hnss.excepciones.LoginException;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * The Class UsuarioDao.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class UsuarioDAO extends ConexionDAO {

    private String sql;

    private Usuario usuario;

    private static final Logger logger = LogManager.getLogger(UsuarioDAO.class);

    /**
     * Instantiates a new usuario dao.
     */
    public UsuarioDAO() {
        super();

    }

    /**
     * Gets the funcionalidades.
     *
     * @param usuario the usuario
     * @return ArrayList<Funcionalidad> Este procedimiento se tiene que llamar
     * solo desde la clase y no cierra conexion
     */
    private ArrayList<Funcionalidad> getFuncionalidades(Usuario usuario) {
        Connection connection = null;
        ArrayList<Funcionalidad> funcionalidades = new ArrayList<>();
        if (this.usuario != null) {
            try {
                connection = super.getConexionBBDD();
                sql = " SELECT  u.funcionalidad,f.descripcion "
                        + "FROM  usr_funcionalidades  u JOIN funcionalidades f ON f.id=u.funcionalidad "
                        + " WHERE  u.userid=? AND u.admitida=1 ORDER BY  f.descripcion 	";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, this.usuario.getUserid());
                ResultSet resulSet = statement.executeQuery();
                while (resulSet.next()) {
                    funcionalidades.add(new Funcionalidad((long) resulSet.getInt("funcionalidad"),
                            resulSet.getString("descripcion"), true));
                }
                statement.close();
                logger.debug(" SELECT  u.funcionalidad,f.descripcion "
                        + "FROM  usr_funcionalidades  u JOIN funcionalidades f ON f.id=u.funcionalidad "
                        + " WHERE  u.userid='" + usuario.getUserid() + "' AND u.admitida=1 ORDER BY  f.descripcion 	");
            } catch (SQLException e) {
                logger.error(" SELECT  u.funcionalidad,f.descripcion "
                        + "FROM  usr_funcionalidades  u JOIN funcionalidades f ON f.id=u.funcionalidad "
                        + " WHERE  u.userid='" + usuario.getUserid() + "' AND u.admitida=1 ORDER BY  f.descripcion 	");
                logger.error(CentroDAO.ERROR_BBDD_SQL, e);
            } catch (Exception e) {
                logger.error(Constantes.EXCEPTION_ERROR, e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return funcionalidades;
    }

    /**
     * Gets the usuario userid.
     *
     * @param userid the userid
     * @return the usuario userid
     * @throws LoginException
     * @throws UsuarioBajaException
     */
    public Usuario getUsuarioLogin(String userid) throws LoginException, UsuarioBajaException {
        Connection connection = null;
        usuario = null;
        try {
            connection = super.getConexionBBDD();
            // sql = "SELECT * FROM usuarios WHERE userid=? ";
            sql = " select u.*,p.id as idperfil,p.descripcion as perfildescripcon from  usuarios u "
                    + " JOIN perfiles p ON p.id=u.perfil WHERE userid=?";
            logger.debug(sql + "'" + userid + "'");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userid);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                Perfil perfil = new Perfil(resulSet.getLong("idperfil"), resulSet.getString("perfildescripcon"));
                usuario = getUsuarioResulset(resulSet, perfil);
                if (usuario.getEstado() == 0) {
                    throw new UsuarioBajaException(Constantes.LOGIN_USUARIO_NOACTIVO);
                }
                usuario.setFuncionaliades(this.getFuncionalidades(usuario));
            } else {

                throw new LoginException(Constantes.LOGIN_USUARIO_NOENCONTRADO);
            }
            statement.close();

        } catch (SQLException e) {
            logger.error(sql + "'" + userid + "'");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(sql + "'" + userid + "'");
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return usuario;
    }

    public Usuario getUsuarioMail(String mail, String userid) {
        Connection connection = null;
        usuario = null;
        if (userid == null) {
            userid = "";
        }
        try {
            connection = super.getConexionBBDD();
            sql = " select u.*,p.id as idperfil,p.descripcion as perfildescripcon from  usuarios u  JOIN perfiles p ON p.id=u.perfil  FROM usuarios WHERE email=? and userid!= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mail.trim());
            statement.setString(2, userid.trim());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                Perfil perfil = new Perfil(resulSet.getLong("idperfil"), resulSet.getString("perfildescripcon"));
                usuario = getUsuarioResulset(resulSet, perfil);
            }
            statement.close();
            logger.debug("SELECT *  FROM usuarios WHERE email='" + mail.trim() + "' and userid!= '" + userid + "'");
        } catch (SQLException e) {
            logger.error("SELECT *  FROM usuarios WHERE email='" + mail.trim() + "' and userid!= '" + userid + "'");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return usuario;
    }

    public Usuario getUsuarioTelefono(String telefono, String userid) {
        Connection connection = null;
        if (userid == null) {
            userid = "";
        }
        usuario = null;
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT  u.*,p.id as idperfil,p.descripcion as perfildescripcon from  usuarios u  JOIN perfiles p ON p.id=u.perfil  FROM usuarios "
                    + " WHERE telefono=?  AND userid!=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, telefono.trim());
            statement.setString(2, userid.trim());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                Perfil perfil = new Perfil(resulSet.getLong("idperfil"), resulSet.getString("perfildescripcon"));
                usuario = getUsuarioResulset(resulSet, perfil);
            }
            statement.close();
            logger.debug("SELECT *  FROM usuarios WHERE telefono='" + telefono + "'  AND userid!='" + userid + "' ");
        } catch (SQLException e) {
            logger.error("SELECT *  FROM usuarios WHERE telefono='" + telefono + "'  AND userid!='" + userid + "' ");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return usuario;
    }

    public Usuario getUsuarioUserid(String userid, Boolean conFuncionalidad) {
        Connection connection = null;
        usuario = null;

        if (userid == null) {
            return null;
        }

        try {
            connection = super.getConexionBBDD();
            // sql = "SELECT * FROM usuarios WHERE userid=? ";
            sql = " SELECT  u.*,p.id as idperfil,p.descripcion as perfildescripcon FROM   usuarios u "
                    + " JOIN perfiles p ON p.id=u.perfil WHERE userid=?	";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userid);
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                Perfil perfil = new Perfil(resulSet.getLong("idperfil"), resulSet.getString("perfildescripcon"));
                usuario = getUsuarioResulset(resulSet, perfil);
                if (conFuncionalidad == true) {
                    usuario.setFuncionaliades(this.getFuncionalidades(usuario));
                }
            }
            statement.close();
            logger.debug("SELECT *  FROM usuarios WHERE userid= '" + userid + "'");
        } catch (SQLException e) {
            logger.error("SELECT *  FROM usuarios WHERE userid= '" + userid + "'");
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return usuario;
    }

    /**
     * Gets the lista usuarios.
     *
     * @return the lista usuarios
     */
    public ArrayList<Usuario> getListaUsuarios() {
        Connection connection = null;
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            connection = super.getConexionBBDD();
            sql = " SELECT  u.*,p.id as idperfil,p.descripcion as perfildescripcon from  usuarios u  JOIN perfiles p ON p.id=u.perfil  FROM usuarios "
                    + "  ORDER by apellido1, apellido2, nombre ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Perfil perfil = new Perfil(resulSet.getLong("idperfil"), resulSet.getString("perfildescripcon"));
                usuario = getUsuarioResulset(resulSet, perfil);
                listaUsuarios.add(usuario);
            }
            statement.close();
            logger.debug("SELECT *  FROM usuarios  ORDER by apellido1, apellido2, nombre ");
        } catch (SQLException e) {
            logger.debug("SELECT *  FROM usuarios  ORDER by apellido1, apellido2, nombre ");
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaUsuarios;
    }

    public ArrayList<Usuario> getListaMedicosServicio(Centro centro, Servicio servicio) {
        Connection connection = null;
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        if (servicio != null && centro != null) {
            try {
                connection = super.getConexionBBDD();

                sql = " SELECT u.*  ,p.id as idperfil,p.descripcion as perfildescripcon "
                        + "   FROM usuarios u, usr_servicios s, servicios_centro c , perfiles p"
                        + "   WHERE s.principal=1 " 
                        + " 	AND c.centro=" + centro.getId()
                        + " 	AND u.estado=1 " 
                        + "	AND u.userid=s.userid   "
                        + "	AND c.servicio = s.servicio " ;
               if (servicio!=null && servicio.getId()!=null){ 
                   sql=sql.concat( "     AND s.servicio =  " + servicio.getId());
                                }
               
               sql=sql.concat( "	AND  (categoria= 1  OR categoria = 47 ) "
                        + "     AND  p.id=u.perfil ORDER by apellido1, apellido2, nombre ");

                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resulSet = statement.executeQuery();
                while (resulSet.next()) {
                    Perfil perfil = new Perfil(resulSet.getLong("idperfil"), resulSet.getString("perfildescripcon"));
                    usuario = getUsuarioResulset(resulSet, perfil);
                    listaUsuarios.add(usuario);
                }
                    statement.close();
                    logger.debug(sql);

            } catch (SQLException e) {
                logger.debug(sql, e);

            } catch (Exception e) {
                logger.error(Constantes.EXCEPTION_ERROR, e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        } else {
            if (servicio == null) {
                logger.error("Servicio  nulo para elegir usuarios.  centro=" + centro.toString());
            }
            if (centro == null) {
                logger.error("Centro nulo para elegir usuarios. Servicio=" + servicio.toString());
            }
            
        }
        return listaUsuarios;
    }

    /**
     * Gets the registros por usuario.
     *
     * @param usuario the usuario
     * @return the registros por usuario
     */
    public int getRegistrosPorUsuario(Usuario usuario) {
        Connection connection = null;
        int casos = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*)  as casos FROM registros WHERE userid=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUserid());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                casos = resulSet.getInt("casos");
            }
            statement.close();
            logger.debug("SELECT count(*)  as casos FROM registros WHERE userid=" + usuario.getUserid());
        } catch (SQLException e) {
            logger.error("SELECT count(*)  as casos FROM registros WHERE userid=" + usuario.getUserid());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return casos;
    }

    /**
     * Gets the datosen BBDD.
     *
     * @param usuario the usuario
     * @return the datosen BBDD
     */
    public int getDatosenBBDD(Usuario usuario) {
        Connection connection = null;
        int casos = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*)  as casos FROM registros WHERE userid=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUserid());
            ResultSet resulSet = statement.executeQuery();
            if (resulSet.next()) {
                casos = resulSet.getInt("casos");
            }
            statement.close();
            logger.debug("SELECT count(*)  as casos FROM registros WHERE userid= " + usuario.getUserid());
        } catch (SQLException e) {
            logger.error("SELECT count(*)  as casos FROM registros WHERE userid= " + usuario.getUserid());
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return casos;
    }

    /**
     * Borra datos.
     *
     * @param usuario the usuario
     * @return true, if successful
     */
    public boolean borraDatos(Usuario usuario) {
        Connection connection = null;
        boolean borrado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM usuarios WHERE userid=?   ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUserid());
            borrado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" DELETE FROM usuarios WHERE userid=" + usuario.getUserid());
        } catch (SQLException e) {
            logger.error(" DELETE FROM usuarios WHERE userid=" + usuario.getUserid());
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return borrado;
    }

    /**
     * Graba datos.
     *
     * @param usuario the usuario
     * @return true, if successful
     */
    public boolean grabaDatos(Usuario usuario) {
        boolean actualizado = false;

        if (this.getUsuarioUserid(usuario.getUserid(), false) == null) {
            actualizado = this.insertaDatos(usuario);
        } else {
            actualizado = this.actualizaDatos(usuario);
        }
        if (actualizado == true) {
            actualizado = actualizaFuncionalidades(usuario);
        }
        return actualizado;
    }

    /**
     * Actualiza funcionalidades.
     *
     * @param usuario the usuario
     * @return true, if successful
     */
    public boolean actualizaFuncionalidades(Usuario usuario) {
        Connection connection = null;
        boolean actualizado = false;
        PreparedStatement statement = null;
        try {
            connection = super.getConexionBBDD();
            sql = " DELETE FROM usr_funcionalidades WHERE userid=? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUserid());
            actualizado = statement.executeUpdate() > 0;
            logger.debug(" DELETE FROM usr_funcionalidades WHERE userid= " + usuario.getUserid());
            for (Funcionalidad f : usuario.getFuncionaliades()) {
                sql = " INSERT INTO usr_funcionalidades (funcionalidad, userid, admitida) VALUES (?,?,1)";
                statement = connection.prepareStatement(sql);
                statement.setLong(1, f.getId());
                statement.setString(2, usuario.getUserid());
                actualizado = statement.executeUpdate() > 0;
                logger.debug(" INSERT INTO usr_funcionalidades (funcionalidad, userid, admitida) VALUES (" + f.getId()
                        + "," + usuario.getUserid() + ",1)");
            }
            statement.close();

        } catch (SQLException e) {
            logger.error(" DELETE FROM usr_funcionalidades WHERE userid= " + usuario.getUserid());
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    /**
     * Actualiza datos.
     *
     * @param usuario the usuario
     * @return true, if successful
     */
    public boolean actualizaDatos(Usuario usuario) {
        Connection connection = null;
        boolean actualizado = false;
        int estado = 0;
        try {
            estado = usuario.getEstado();
            connection = super.getConexionBBDD();
            sql = " UPDATE   usuarios  SET apellido1=?, apellido2=? , nombre=?, password=? ,estado=?, email=? , telefono=? WHERE userid=? ";
            sql = " UPDATE   usuarios  SET apellido1=?, apellido2=? , nombre=? ,estado=?, email=? , telefono=? WHERE userid=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getApellido1());
            statement.setString(2, usuario.getApellido2());
            statement.setString(3, usuario.getNombre());
            // statement.setString(4, usuario.getPasswordhash());
            statement.setInt(4, estado);
            statement.setString(5, usuario.getMail());
            statement.setString(6, usuario.getTelefono());
            statement.setString(7, usuario.getUserid());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE   usuarios  SET apellido1='" + usuario.getApellido1() + "', apellido2='"
                    + usuario.getApellido1() + "' , nombre='" + usuario.getNombre() + "' ,estado=" + estado
                    + ", email='" + usuario.getMail() + "' , telefono='" + usuario.getTelefono() + "' WHERE userid='"
                    + usuario.getUserid() + "' ");
        } catch (SQLException e) {
            logger.error(" UPDATE   usuarios  SET apellido1='" + usuario.getApellido1() + "', apellido2='"
                    + usuario.getApellido1() + "' , nombre='" + usuario.getNombre() + "' ,estado=" + estado
                    + ", email='" + usuario.getMail() + "' , telefono='" + usuario.getTelefono() + "' WHERE userid='"
                    + usuario.getUserid() + "' ");
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    /**
     * Do actualiza clave.
     *
     * @param usuario the usuario
     * @return true, if successful
     */
    public boolean doActualizaClave(Usuario usuario) {
        Connection connection = null;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   usuarios  SET password=? WHERE userid=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getPasswordhash());
            statement.setString(2, usuario.getUserid());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE   usuarios  SET password='" + usuario.getPasswordhash() + "' WHERE userid='"
                    + usuario.getUserid() + "' ");
        } catch (SQLException e) {
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    /**
     * Do bloquea cuenta.
     *
     * @param usuario the usuario
     * @return true, if successful
     */
    public boolean doBloqueaCuenta(Usuario usuario) {
        Connection connection = null;
        boolean actualizado = false;
        try {
            connection = super.getConexionBBDD();
            sql = " UPDATE   usuarios  SET estado=0 WHERE userid=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUserid());
            actualizado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(" UPDATE   usuarios  SET estado=0 WHERE userid= " + usuario.getUserid());
        } catch (SQLException e) {
            logger.debug(" UPDATE   usuarios  SET estado=0 WHERE userid= " + usuario.getUserid());
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return actualizado;
    }

    /**
     * Inserta datos.
     *
     * @param usuario the usuario
     * @return true, if successful
     */
    public boolean insertaDatos(Usuario usuario) {
        Connection connection = null;
        boolean insertado = false;
        int estado = 0;
        try {
            connection = super.getConexionBBDD();
            sql = " INSERT INTO usuarios (userid,apellido1,apellido2,nombre,password , estado, email,telefono) VALUES (?,?,?,?,?,?,?,?)  ";
            estado = usuario.getEstado();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUserid());
            statement.setString(2, usuario.getApellido1());
            statement.setString(3, usuario.getApellido2());
            statement.setString(4, usuario.getNombre());
            statement.setString(5, usuario.getPasswordhash());
            statement.setInt(6, estado);
            statement.setString(7, usuario.getMail());
            statement.setString(8, usuario.getTelefono());
            insertado = statement.executeUpdate() > 0;
            statement.close();
            logger.debug(
                    " INSERT INTO usuarios (userid,apellido1,apellido2,nombre,password , estado, email,telefono) VALUES ('"
                    + usuario.getUserid() + "','" + usuario.getApellido1() + "','" + usuario.getApellido2()
                    + "','" + usuario.getNombre() + "','" + usuario.getPasswordhash() + "'," + estado + ",'"
                    + usuario.getMail() + "','" + usuario.getTelefono() + "')  ");
        } catch (SQLException e) {
            logger.error(
                    " INSERT INTO usuarios (userid,apellido1,apellido2,nombre,password , estado, email,telefono) VALUES ('"
                    + usuario.getUserid() + "','" + usuario.getApellido1() + "','" + usuario.getApellido2()
                    + "','" + usuario.getNombre() + "','" + usuario.getPasswordhash() + "'," + estado + ",'"
                    + usuario.getMail() + "','" + usuario.getTelefono() + "')  ");
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return insertado;
    }

    /**
     * Gets the sql where.
     *
     * @param cadena the cadena
     * @return the sql where
     */
    public String getSqlWhere(String cadena) {
        String sqlString = "";
        String[] apellidos = cadena.toUpperCase().split(" ");

        if (Utilidades.validarEmail(cadena) == true) {
            sqlString = sqlString.concat(" AND  email LIKE '%" + cadena + "%'");
        } else if (Utilidades.validarNIF(cadena) == true) {
            sqlString = sqlString.concat(" AND  userid LIKE '%" + cadena + "%'");
        } else if (Utilidades.isNumeric(cadena)) {
            sqlString = sqlString.concat(" AND  (userid LIKE '%" + cadena + "%'  OR telefono=" + cadena + ")");
        } else if (apellidos.length > 0) {
            if (apellidos.length == 1 && apellidos[0] != null && apellidos[0] != "") {
                sqlString = sqlString.concat(" AND ( apellido1 LIKE '" + apellidos[0].trim() + "%' ");
                sqlString = sqlString.concat(" or  apellido2 LIKE '" + apellidos[0].trim() + "%' ");
                sqlString = sqlString.concat(" OR userid LIKE '%" + cadena + "%' )");
            } else if (apellidos.length == 2 && apellidos[0].trim() != null && apellidos[1].trim() != null) {
                sqlString = sqlString.concat(" AND  apellido1 LIKE '" + apellidos[0].trim()
                        + "%'  AND  apellido2 LIKE '" + apellidos[1].trim() + "%' ");
            } else if (apellidos.length == 3 && apellidos[0].trim() != null && apellidos[1].trim() != null
                    && apellidos[2].trim() != null) {
                sqlString = sqlString
                        .concat(" AND  apellido1 LIKE '" + apellidos[0].trim() + "%'  AND  apellido2 LIKE '"
                                + apellidos[1].trim() + "%'  AND nombre LIKE '" + apellidos[2].trim() + "%'");
            }
        }
        sqlString = sqlString.concat(" ORDER BY apellido1,apellido2,nombre ");
        return sqlString;
    }

    /**
     * Gets the paginacion datosa usuarios apellidos.
     *
     * @param cadena the cadena
     * @return the paginacion datosa usuarios apellidos
     */
    public PagiLisReg getPaginacionDatosaUsuariosApellidos(String cadena) {
        Connection connection = null;
        PagiLisReg paginacion = new PagiLisReg(0, 0, 0, 0, 0, 1);
        int contador = 0;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT count(*) as numero   FROM usuarios  WHERE 1=1 ";
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
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return paginacion;
    }

    /**
     * Gets the lista usuarios paginados.
     *
     * @param cadena the cadena
     * @param paginacion the paginacion
     * @return the lista usuarios paginados
     */
    public ArrayList<Usuario> getListaUsuariosPaginados(String cadena, PagiLisReg paginacion) {
        Connection connection = null;
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        int contador = 0;

        try {
            connection = super.getConexionBBDD();

          
                sql = "SELECT  rownum  as numeroorden ,u.*,p.id as idperfil,p.descripcion as perfildescripcon "
                        + "	FROM usuarios u" + " JOIN perfiles p ON p.id=u.perfil" + "	WHERE  1=1 ";
            
            // System.out.println(persistencia + ":" + Constantes.ORACLE_STRING);
            sql = sql.concat(getSqlWhere(cadena));
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                if (paginacion.getDireccion() == 1) {
                    if (resulSet.getInt("numeroorden") > paginacion.getAnterior()) {
                        Perfil perfil = new Perfil(resulSet.getLong("idperfil"),
                                resulSet.getString("perfildescripcon"));
                        usuario = getUsuarioResulset(resulSet, perfil);
                        usuario.setNumeroOrden(resulSet.getInt("numeroorden"));
                        usuario.setFuncionaliades(getFuncionalidades(usuario));
                        listaUsuarios.add(usuario);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                } else {
                    if (resulSet.getInt("numeroorden") >= paginacion.getAnterior()) {
                        Perfil perfil = new Perfil(resulSet.getLong("idperfil"),
                                resulSet.getString("perfildescripcon"));
                        usuario = getUsuarioResulset(resulSet, perfil);
                        usuario.setNumeroOrden(resulSet.getInt("numeroorden"));
                        listaUsuarios.add(usuario);
                        contador++;
                        if (contador >= paginacion.getNumeroRegistrosPagina()) {
                            break;
                        }
                    }
                }
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.debug(sql);
            logger.debug(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaUsuarios;
    }

    /**
     * Gets the usuario resulset.
     *
     * @param resulSet the resul set
     * @return the usuario resulset
     */
    public Usuario getUsuarioResulset(ResultSet resulSet, Perfil perfil) {
        Usuario usuario = null;
        try {
            usuario = new Usuario(resulSet.getString("userid"));
            usuario.setApellido1(resulSet.getString("apellido1"));
            usuario.setApellido2(resulSet.getString("apellido2"));
            usuario.setNombre(resulSet.getString("nombre"));
            usuario.setMail(resulSet.getString("email"));
            // usuario.setPasswordhash(resulSet.getString("password"));
            usuario.setEstado(resulSet.getInt("estado"));
            usuario.setTelefono(resulSet.getString("telefono"));
            usuario.setCodcolegiado(resulSet.getString("codcolegiado"));
            if (perfil == null) {
                usuario.setPerfil(new PerfilDAO().getRegistrPorId(resulSet.getLong("perfil")));
            } else {
                usuario.setPerfil(perfil);
            }

            usuario.setPerfil(new Perfil(resulSet.getLong("perfil")));
            usuario.setCargo(resulSet.getLong("cargo"));
            usuario.setCategoria(resulSet.getLong("categoria"));
            usuario.setCsn(resulSet.getString("csn"));
            usuario.setBusca(resulSet.getString("busca"));
            usuario.setIp(resulSet.getString("ip"));
            usuario.setCias(resulSet.getString("cias"));
            usuario.setSesion(resulSet.getString("sesion"));
            usuario.setCpf(resulSet.getString("cpf"));

            usuario.setIntentos_fallidos(resulSet.getInt("intentos_fallidos"));

            if (resulSet.getLong("fecha") > 0) {
                usuario.setFecha(Utilidades.getFechaLocalDate(resulSet.getLong("fecha")));
            }

            // private Long hora;
            // private Blob foto;
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_BBDD_SQL, e);
        }
        return usuario;
    }

    /**
     * Gets the referencias externas.
     *
     * @param idusuario the idusuario
     * @return the referencias externas
     */
    public boolean getReferenciasExternas(String idusuario) {
        Connection connection = null;
        boolean referencias = false;
        try {
            connection = super.getConexionBBDD();
            sql = "SELECT userid FROM problemas WHERE userid=?" + " UNION  SELECT userid FROM registros WHERE userid=? "
                    + " UNION  SELECT userid FROM usr_funcionalidades WHERE userid=? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idusuario);
            statement.setString(2, idusuario);
            statement.setString(3, idusuario);
            ResultSet resulSet = statement.executeQuery();

            if (resulSet.next()) {
                referencias = true;
            }
            statement.close();
            logger.debug("SELECT userid FROM problemas WHERE userid='" + idusuario + "'"
                    + " UNION  SELECT userid FROM registros WHERE userid=? "
                    + " UNION  SELECT userid FROM usr_funcionalidades WHERE userid='" + idusuario + "' ");
        } catch (SQLException e) {
            logger.error("SELECT userid FROM problemas WHERE userid='" + idusuario + "'"
                    + " UNION  SELECT userid FROM registros WHERE userid=? "
                    + " UNION  SELECT userid FROM usr_funcionalidades WHERE userid='" + idusuario + "' ");
            logger.error(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
            }
        }
        return referencias;
    }

    public ArrayList<String> getListaUsuarioServicioCategoria(Servicio servicio, int categoria, int categoria2) {
        Connection connection = null;
        ArrayList<String> listaUsuarios = new ArrayList<>();
        int contador = 0;

        try {
            connection = super.getConexionBBDD();

            
                sql = " SELECT  u.apellido1,u.apellido2,u.nombre FROM usuarios u "
                        + " JOIN usr_servicios s On s.userid=u.userid AND s.servicio=" + servicio.getId() + " "
                        + "AND s.principal=1 ";
                if (categoria != 0) {
                    sql = sql.concat(" AND  ( categoria=" + categoria);
                }
                if (categoria2 != 0) {
                    sql = sql.concat(" OR  categoria=" + categoria2);
                }
                sql = sql.concat(" )" + " WHERE u.estado=1 ORDER BY u.apellido1,u.apellido2,u.nombre");
            
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                String usua = resulSet.getString("apellido1").trim() + " " + resulSet.getString("apellido2").trim()
                        + "," + resulSet.getString("nombre").trim();
                listaUsuarios.add(usua);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.debug(sql);
            logger.debug(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaUsuarios;

    }

    public ArrayList<Usuario> getListaUsuarioServicioCategoriaUsuario(Servicio servicio,
            ArrayList<Categoria> categorias, Zona zona) {
        Connection connection = null;
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try {
            connection = super.getConexionBBDD();

         
                if (servicio != null) {
                    if (zona != null) {
                        sql = " SELECT  u.* FROM usuarios u JOIN usr_servicios s On s.userid=u.userid AND s.servicio="
                                + servicio.getId() + " " + "AND s.principal=1  "
                                + " JOIN usr_zonas z On z.userid=u.userid AND z.zona=" + zona.getId()
                                + " WHERE u.estado=1 ";

                    } else {
                        sql = " SELECT  u.* FROM usuarios u JOIN usr_servicios s On s.userid=u.userid AND s.servicio="
                                + servicio.getId() + " " + "AND s.principal=1 WHERE u.estado=1 ";
                    }
                } else {
                    if (zona != null) {
                        sql = " SELECT  u.* FROM usuarios  u" + " JOIN usr_zonas z On z.userid=u.userid AND z.zona="
                                + zona.getId() + " WHERE u.estado=1 ";

                    } else {
                        sql = " SELECT  u.* FROM usuarios u WHERE u.estado=1 ";
                    }
                }
                int contador = 0;
                for (Categoria cate : categorias) {
                    if (contador == 0) {
                        sql = sql.concat(" AND (");
                    }
                    if (contador > 0) {
                        sql = sql.concat(" OR ");
                    }
                    contador++;
                    sql = sql.concat("   categoria=" + cate.getId());
                }
                if (contador >= 0) {
                    sql = sql.concat(")");
                }
                sql = sql.concat("  ORDER BY u.apellido1,u.apellido2,u.nombre");
            
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                Usuario usuario = new Usuario();
                usuario = this.getUsuarioResulset(resulSet, null);
                listaUsuarios.add(usuario);
            }
            statement.close();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.debug(sql);
            logger.debug(CentroDAO.ERROR_BBDD_SQL, e);
        } catch (Exception e) {
            logger.error(Constantes.EXCEPTION_ERROR, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(ConexionDAO.ERROR_CLOSE_BBDD_SQL, e);
        }
        return listaUsuarios;

    }

    /*
        Crea obj usuario a partir de datos de sql para select cruzados de episodios,procesos, alertas, etc
        Importante que los campos  sql se llamamen como se indica
     */
    public static Usuario getUsuairoRs(ResultSet resulSet, Usuario usuarioParam) {
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
                logger.error(Constantes.SQLERRORRESULSET, e);
            }
        }
        return usuario;
    }
}
