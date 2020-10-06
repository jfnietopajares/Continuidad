package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * The Class Paciente. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Paciente {

    private int numeroOrden;
    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String numerohc;
    private String dni;
    private String direccion;
    private Provincia provincia;
    private Municipio municipio;
    private String codigopostal;
    private LocalDate fnac;
    private Integer sexo;
    private String telefono;
    private String movil;
    private String tarjeta;
    private String nss;
    private String cias;
    private String nbdp;
    private Centro centroprimaria;
    private String pais;
    private Paciente madre;

 

    private ArrayList<Registro> listaRegistros = new ArrayList<>();
    
    private ArrayList<Variable> listaVariables = new ArrayList<>();

    private int exitus_codigo;
    private LocalDate exitus_fecha;
    private Edad edad;
    DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    DateTimeFormatter horahhmm = DateTimeFormatter.ofPattern("H:m");

    /**
     * Instantiates a new paciente.
     */
    public Paciente() {
        this.id = new Long(0);
        valoresPorDefecto();
    }

    ;

	/**
	 * Instantiates a new paciente.
	 *
	 * @param id the id
	 */
	public Paciente(long id) {
        this.id = id;
        valoresPorDefecto();
    }

    /**
     * Instantiates a new paciente.
     *
     * @param id the id
     * @param nombre the nombre
     * @param apellido1 the apellido 1
     * @param apellido2 the apellido 2
     */
    public Paciente(Long id, String nombre, String apellido1, String apellido2) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        valoresPorDefecto();
    }

    public Paciente(Long id, String nombre, String apellido1, String apellido2, LocalDate fnac, int sexo, String nhc,
            String tarjeta, String nss, String dni, String telefono, String movil, String nbdp) {
        valoresPorDefecto();
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fnac = fnac;
        this.sexo = sexo;
        this.numerohc = nhc;
        this.tarjeta = tarjeta;
        this.nss = nss;
        this.dni = dni;
        this.telefono = telefono;
        this.movil = movil;
        this.nbdp = nbdp;
        if (fnac != null) {
            this.edad = new Edad(fnac);
        }
    }

    /**
     * Valores por defecto.
     */
    public void valoresPorDefecto() {
        this.sexo = 0;
        this.numerohc = "";
        this.dni = "";
        this.provincia = Provincia.PROVINCIA_DEFECTO;
        this.municipio = new Municipio();
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
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
     * Gets the numerohc.
     *
     * @return the numerohc
     */
    public String getNumerohc() {
        return numerohc;
    }

    /**
     * Sets the numerohc.
     *
     * @param numerohc the new numerohc
     */
    public void setNumerohc(String numerohc) {
        this.numerohc = numerohc;
    }

    /**
     * Gets the dni.
     *
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Sets the dni.
     *
     * @param dni the new dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Gets the direccion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the direccion.
     *
     * @param direccion the new direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets the provincia.
     *
     * @return the provincia
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * Sets the provincia.
     *
     * @param provincia the new provincia
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    /**
     * Gets the municipio.
     *
     * @return the municipio
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    public String getMunicipioNombre() {
        if ( municipio!=null)
        return municipio.getDescripcion();
        else return "";
    }
    /**
     * Sets the municipio.
     *
     * @param municipio the new municipio
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * Gets the codigopostal.
     *
     * @return the codigopostal
     */
    public String getCodigopostal() {
        return codigopostal;
    }

    /**
     * Sets the codigopostal.
     *
     * @param codigopostal the new codigopostal
     */
    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    /**
     * Gets the fnac.
     *
     * @return the fnac
     */
    public LocalDate getFnac() {
        return fnac;
    }

     public String getFnacFormato() {
        String feString = "";
        if (fnac != null) {
            
                feString = fechadma.format(fnac);
            
        }
        return feString;
    }
    /**
     * Sets the fnac.
     *
     * @param fnac the new fnac
     */
    public void setFnac(LocalDate fnac) {
        this.fnac = fnac;
        if (fnac != null) {
            this.edad = new Edad(fnac);
        }
    }

    /**
     * Gets the sexo.
     *
     * @return the sexo
     */
    public Integer getSexo() {
        return sexo;
    }

    /**
     * Sets the sexo.
     *
     * @param sexo the new sexo
     */
    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    /**
     * Sets the sexo setring.
     *
     * @param sexo the new sexo setring
     */
    public void setSexoSetring(String sexo) {
        this.sexo = Utilidades.getSexoValor(sexo);
    }

    /**
     * Gets the sexo string.
     *
     * @return the sexo string
     */
    public String getSexoString() {
        return Utilidades.getSexoNombre(this.sexo);
    }

    /**
     * Gets the telefono.
     *
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the telefono.
     *
     * @param telefono the new telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets the movil.
     *
     * @return the movil
     */
    public String getMovil() {
        return movil;
    }

    /**
     * Sets the movil.
     *
     * @param movil the new movil
     */
    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getCias() {
        return cias;
    }

    public void setCias(String cias) {
        this.cias = cias;
    }

    public String getNbdp() {
        return nbdp;
    }

    public void setNbdp(String nbdp) {
        this.nbdp = nbdp;
    }

    public Centro getCentroprimaria() {
        return centroprimaria;
    }

    public void setCentroprimaria(Centro centroprimaria) {
        this.centroprimaria = centroprimaria;
    }

    public String getCentroPrimariaNombre() {
        if (this.centroprimaria != null) {
            return centroprimaria.getDescripcion();
        } else {
            return "";
        }
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Gets the numero orden.
     *
     * @return the numero orden
     */
    public int getNumeroOrden() {
        return numeroOrden;
    }

    public Paciente getMadre() {
        return madre;
    }

    public void setMadre(Paciente madre) {
        this.madre = madre;
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
     * Gets the apellidos nombre.
     *
     * @return the apellidos nombre
     */
    public String getApellidosNombre() {
        String nombre = "";
        if (this.getApellido1() != null) {
            nombre = this.getApellido1().trim();
        }
        if (this.getApellido2() != null) {
            nombre = nombre.concat(" ").concat(this.getApellido2().trim());
        }

        if (this.getNombre() != null) {
            nombre = nombre.concat(", ").concat(this.getNombre().trim());
        }
        return nombre;
    }

    public String getApellidosNombreEdad() {
        String cadena = this.getApellidosNombre();
        if (edad != null) {
            cadena = cadena.concat("  (" + edad.getEdadAnosString() + ")");
        }
        return cadena;
    }

    @Override
    public String toString() {
        return "Paciente{" + "numeroOrden=" + numeroOrden + ", id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", numerohc=" + numerohc + ", dni=" + dni + ", direccion=" + direccion + ", provincia=" + provincia + ", municipio=" + municipio + ", codigopostal=" + codigopostal + ", fnac=" + fnac + ", sexo=" + sexo + ", telefono=" + telefono + ", movil=" + movil + ", tarjeta=" + tarjeta + ", nss=" + nss + ", cias=" + cias + ", nbdp=" + nbdp + ", centroprimaria=" + centroprimaria + ", pais=" + pais + ", madre=" + madre + ", listaRegistros=" + listaRegistros + ", listaVariables=" + listaVariables + ", exitus_codigo=" + exitus_codigo + ", exitus_fecha=" + exitus_fecha + ", edad=" + edad + ", fechadma=" + fechadma + ", horahhmm=" + horahhmm + '}';
    }

  
 
    public ArrayList<Registro> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(ArrayList<Registro> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public ArrayList<Variable> getListaVariables() {
        return listaVariables;
    }

    public void setListaVariables(ArrayList<Variable> listaVariables) {
        this.listaVariables = listaVariables;
    }

    
    public int getExitus_codigo() {
        return exitus_codigo;
    }

    public void setExitus_codigo(int exitus_codigo) {
        this.exitus_codigo = exitus_codigo;
    }

    public LocalDate getExitus_fecha() {
        return exitus_fecha;
    }

    public void setExitus_fecha(LocalDate exitus_fecha) {
        this.exitus_fecha = exitus_fecha;
    }

    public Edad getEdad() {
        return edad;
    }

    public void setEdad(Edad edad) {
        this.edad = edad;
    }

}
