package es.sacyl.hnss.entity;

import es.sacyl.hnss.dao.FuncionalidadDAO;
import es.sacyl.hnss.utilidades.Utilidades;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * The Class Usuario. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = -5231586971597227517L;

    private int numeroOrden;

    private String userid;

    private String apellido1;

    private String apellido2;

    private String nombre;

    private String password;

    private String passwordhash;

    private int estado;

    private String mail;

    private String telefono;

    private String codcolegiado;
    private Perfil perfil;
    private Long cargo;
    private Long categoria;
    private String busca;
    private String csn;
    private LocalDate fecha;
    private Long hora;
    private String ip;
    private String cias;
    private String sesion;
    private Blob foto;
    private int intentos_fallidos;
    private String cpf;

    private ArrayList<Funcionalidad> funcionaliades = new ArrayList<>();

    public final static int USUARIO_ESTADOADMINISTRADOR = 2;
    public final static int PASSWORD_LONGITUD_MINIMA = 6;

    public final static int PASSWORD_LONGITUD_MAXIMA = 20;

    public final static int LOGIN_INTENTOS = 3;

    public static final String PASSWD_LONGITUDMINIMA = "La clave debe tener más de  "
            + Usuario.PASSWORD_LONGITUD_MINIMA;

    public static final String FUNCIONALIDADES_VACIAS = "Es necesario asignar funcionaliad.";

    public static final String LONGIN_CLAVE_INCORRECTA = "Clave incorrecta.";

    public static final String LONGIN_CLAVE_VACIA = "Clave incorrecta.";

    public static final String LONGIN_CLAVE_ANTIGUA_IGUAL_NUEVA = "Nueva clave debe ser diferente a la antigua.";

    /**
     * Instantiates a new usuario.
     */
    public Usuario() {
    }

    /**
     * Instantiates a new usuario.
     *
     * @param userid the userid
     */
    public Usuario(String userid) {
        this.userid = userid;
    }

    /**
     * Instantiates a new usuario.
     *
     * @param userid the userid
     * @param password the password
     */
    public Usuario(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    /**
     * Instantiates a new usuario.
     *
     * @param userid the userid
     * @param apellido1 the apellido 1
     * @param apellido2 the apellido 2
     * @param nombre the nombre
     * @param estado the estado
     */
    public Usuario(String userid, String apellido1, String apellido2, String nombre, int estado) {
        this.userid = userid;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.estado = estado;
    }

    /**
     * Instantiates a new usuario.
     *
     * @param userid the userid
     * @param apellido1 the apellido 1
     * @param apellido2 the apellido 2
     * @param nombre the nombre
     * @param password the password
     * @param estado the estado
     * @param mail the mail
     * @param passwordhash the passwordhash
     */
    public Usuario(String userid, String apellido1, String apellido2, String nombre, String password, int estado,
            String mail, String passwordhash) {
        this.userid = userid;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.password = password;
        this.estado = estado;
        this.mail = mail;
        this.passwordhash = passwordhash;
    }

    /**
     * Gets the userid.
     *
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Sets the userid.
     *
     * @param userid the new userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * Gets the apellido 1.
     *
     * @return the apellido 1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Sets the apellido 1.
     *
     * @param apellido1 the new apellido 1
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Gets the apellido 2.
     *
     * @return the apellido 2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Sets the apellido 2.
     *
     * @param apellido2 the new apellido 2
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    public String getEstadoString() {
        return Integer.toString(estado);
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setEstado(String estado) {
        if (Utilidades.isNumeric(estado)) {
            this.estado = Integer.parseInt(estado);
        }
    }

    /**
     * Gets the mail.
     *
     * @return the mail
     */
    public String getMail() {

        return mail;
    }

    /**
     * Sets the mail.
     *
     * @param mail the new mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets the numero orden.
     *
     * @return the numero orden
     */
    public int getNumeroOrden() {
        return numeroOrden;
    }

    /**
     * Sets the numero orden.
     *
     * @param numeroOrden the new numero orden
     */
    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    /**
     * Gets the passwordhash.
     *
     * @return the passwordhash
     */
    public String getPasswordhash() {
        return passwordhash;
    }

    /**
     * Sets the passwordhash.
     *
     * @param passwordhash the new passwordhash
     */
    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    /**
     * Valida login.
     *
     * @param userid the userid
     * @param password the password
     * @return the boolean
     */
    public Boolean validaLogin(String userid, String password) {

        if (userid.equals(getUserid()) && password.equals(getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * Gets the apellidos nombre.
     *
     * @return the apellidos nombre
     */
    public String getApellidosNombre() {
        if (getApellido1() != null && getApellido2() != null && getNombre() != null) {
            return getApellido1() + " " + getApellido2() + ", " + getNombre();
        } else {
            return "?";
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodcolegiado() {
        return codcolegiado;
    }

    public void setCodcolegiado(String codcolegiado) {
        this.codcolegiado = codcolegiado;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Long getCargo() {
        return cargo;
    }

    public void setCargo(Long cargo) {
        this.cargo = cargo;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public String getCsn() {
        return csn;
    }

    public void setCsn(String csn) {
        this.csn = csn;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCias() {
        return cias;
    }

    public void setCias(String cias) {
        this.cias = cias;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public int getIntentos_fallidos() {
        return intentos_fallidos;
    }

    public void setIntentos_fallidos(int intentos_fallidos) {
        this.intentos_fallidos = intentos_fallidos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return getApellido1() + " " + getApellido2() + " " + getNombre();
    }

    /**
     * Sets the new funcionalidada.
     *
     * @param funcionalidad the new new funcionalidada
     */
    public void setFuncionalidada(Funcionalidad funcionalidad) {
        funcionaliades.add(funcionalidad);
    }

    /**
     * Gets the funcionaliades.
     *
     * @return the funcionaliades
     */
    public ArrayList<Funcionalidad> getFuncionaliades() {
        return funcionaliades;
    }

    /**
     * Gets the arr funcionaliades.
     *
     * @return the arr funcionaliades
     */
    @SuppressWarnings("null")
    public Funcionalidad[] getArrFuncionaliades() {
        Funcionalidad[] funcionalidads = null;
        int i = 0;
        for (Funcionalidad f : funcionaliades) {
            funcionalidads[i] = f;
        }
        return funcionalidads;
    }

    /**
     * Gets the nombre funcionaliades.
     *
     * @return the nombre funcionaliades
     */
    public HashSet<String> getNombreFuncionaliades() {
        HashSet<String> lista = new HashSet<>();
        if (funcionaliades != null) {
            if (funcionaliades.size() > 0) {
                for (Funcionalidad f : funcionaliades) {
                    lista.add(f.getDescripcion());
                }
            }
        }
        return lista;
    }

    /**
     * Sets the funcionalidad nombre.
     *
     * @param set the new funcionalidad nombre
     */
    public void setFuncionalidadNombre(Set<String> set) {
        funcionaliades.removeAll(funcionaliades);
        for (String f : set) {
            funcionaliades.add(new FuncionalidadDAO().getPorDescripcion(f));
        }
    }

    /**
     * Sets the funcionaliades.
     *
     * @param funcionaliades the new funcionaliades
     */
    public void setFuncionaliades(ArrayList<Funcionalidad> funcionaliades) {
        this.funcionaliades = funcionaliades;
    }

    public boolean getTieneLaFuncionalidad(Long idFuncionalidad) {
        boolean tieneLaFuncionalidad = false;
        for (Funcionalidad fun : this.getFuncionaliades()) {
            if (fun.getId().equals(idFuncionalidad)) {
                tieneLaFuncionalidad = true;
            }
        }
        return tieneLaFuncionalidad;
    }

    /**
     * Gets the html datos.
     *
     * @return the html datos
     */
    public String getHtmlDatos() {
        String texto;
        String mail = getMail() != null ? getMail() : " ";
        texto = "<hr><b>Usuario:</b>" + getUserid() + "<br>";
        texto = texto.concat("<b>Nombre:</b>" + getApellidosNombre() + "<br>");
        texto = texto.concat("<b>Mail:</b>" + mail + "<br>");
        texto = texto.concat("<b>Tef:</b>" + telefono + "<br>");
        texto = texto.concat("Funcionalidades<hr>");
        for (Funcionalidad f : this.getFuncionaliades()) {
            texto = texto.concat("(" + f.getId() + ")" + f.getDescripcion() + "<br>");
        }
        return texto;
    }

}
