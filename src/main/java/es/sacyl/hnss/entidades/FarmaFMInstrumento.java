package es.sacyl.hnss.entidades;

/**
 *
 * @author JuanNieto
 */
public class FarmaFMInstrumento {

    private String codigo;

    private String nombre;

    public static final String labelFrom = "Intrumentos y materiales ";

    public FarmaFMInstrumento() {

    }

    public FarmaFMInstrumento(String codigo, String nombre) {

        this.codigo = codigo;

        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String getLabelFrom() {
        return labelFrom;
    }

    @Override
    public String toString() {
        return "FarmaFMInstrumento{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }

}
