package es.sacyl.hnss.entidades;

/**
 *
 * @author JuanNieto
 */
public class Usuario {

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;

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

    public String getNombreCompleto() {
        String nombreCompleto = "";
        if (apellido1 != null) {
            nombreCompleto = nombreCompleto.concat(apellido1 + " ");
        }
        if (apellido1 != null) {
            nombreCompleto = nombreCompleto.concat(apellido2 + " ");
        }
        if (nombre != null) {
            nombreCompleto = nombreCompleto.concat(nombre);
        }
        return nombreCompleto;
    }

}
