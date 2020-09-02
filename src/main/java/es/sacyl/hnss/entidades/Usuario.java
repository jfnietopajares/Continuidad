package es.sacyl.hnss.entidades;

import java.io.Serializable;

/**
 *
 * @author JuanNieto
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String codigoFarmatools;
    private String servicioFarmatols;

    public Usuario() {

    }

    public Usuario(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCodigoFarmatools() {
        return codigoFarmatools;
    }

    public void setCodigoFarmatools(String codigoFarmatools) {
        this.codigoFarmatools = codigoFarmatools;
    }

    public String getServicioFarmatols() {
        return servicioFarmatols;
    }

    public void setServicioFarmatols(String servicioFarmatols) {
        this.servicioFarmatols = servicioFarmatols;
    }

    public String getNombreCompleto() {
        String nombreCompleto = "";
        if (apellido1 != null) {
            nombreCompleto = nombreCompleto.concat(apellido1 + " ");
        }
        if (apellido2 != null) {
            nombreCompleto = nombreCompleto.concat(apellido2 + " ");
        }
        if (nombre != null) {
            nombreCompleto = nombreCompleto.concat(nombre);
        }
        return nombreCompleto;
    }

}
