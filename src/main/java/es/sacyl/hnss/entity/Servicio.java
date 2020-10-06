package es.sacyl.hnss.entity;

import java.util.ArrayList;

/**
 * The Class Servicio. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Servicio {

    /**
     * The numero orden.
     */
    private int numeroOrden;

    /**
     * The id.
     */
    private Long id;

    /**
     * The codigo.
     */
    private String codigo;

    /**
     * The descripcion.
     */
    private String descripcion;

    /**
     * The clase.
     */
    private Long clase;

    /**
     * Instantiates a new servicio.
     */
    public final static Servicio SERVICIO_ALERGIA = new Servicio(new Long(3), "ALG", "Alergología");
    public final static Servicio SERVICIO_ANESTESIA = new Servicio(new Long(4), "ANR", "Anestesia y reanimación");
    public final static Servicio SERVICIO_CIRUGIA = new Servicio(new Long(10), "CGD", "Cirugía general y digestiva");
    public final static Servicio SERVICIO_DERMATOLOGIA = new Servicio(new Long(15), "DER", "Dermatologia");
    public final static Servicio SERVICIO_DIGSTIVO = new Servicio(new Long(16), "DIG", "Digestivo");
    public final static Servicio SERVICIO_GINECOLOGIA = new Servicio(new Long(21), "GIN", "Ginecología");
    public final static Servicio SERVICIO_HEMATOLGIA = new Servicio(new Long(26), "HEM", "Hematolgía");
    public final static Servicio SERVICIO_MEDICINAINERNA = new Servicio(new Long(30), "MIR", "Medicina interna");
    public final static Servicio SERVICIO_NEUMOLOGIA = new Servicio(new Long(37), "NML", "Neumología");
    public final static Servicio SERVICIO_NEUROLGOA = new Servicio(new Long(39), "NRL", "Neurología");
    public final static Servicio SERVICIO_OBSTERICIA = new Servicio(new Long(41), "OBS", "Obstericía");
    public final static Servicio SERVICIO_OFTALMOLOGIA = new Servicio(new Long(42), "OFT", "Oftalmolgía");
    public final static Servicio SERVICIO_ONCOLOGIA = new Servicio(new Long(43), "ONC", "Oncología");
    public final static Servicio SERVICIO_OTORRINO = new Servicio(new Long(45), "ORL", "Otorrinolaringología");
    public final static Servicio SERVICIO_PEDIATRIA = new Servicio(new Long(46), "PED", "Pediatría");
    public final static Servicio SERVICIO_RADIOLOGIA = new Servicio(new Long(48), "RAD", "Radiología");
    public final static Servicio SERVICIO_TRAUMATOLGIA = new Servicio(new Long(51), "PED", "Traumatología");
    public final static Servicio SERVICIO_UROLOGIA = new Servicio(new Long(55), "URO", "Urología");
    public final static Servicio SERVICIO_URGENCIAS = new Servicio(new Long(54), "URG", "Urgencias");

    public final static ArrayList<Servicio> listaServiciosPruebasCutaneas = new ArrayList<Servicio>() {
        {
            add(SERVICIO_ALERGIA);
            add(SERVICIO_DERMATOLOGIA);
            add(SERVICIO_PEDIATRIA);
        }
    };

    public final static ArrayList<Servicio> listaServiciosHdiaOnoco = new ArrayList<Servicio>() {
        {
            add(SERVICIO_ONCOLOGIA);
            add(SERVICIO_HEMATOLGIA);
        }
    };
    public final static ArrayList<Servicio> listaServiciosHdiaMedico = new ArrayList<Servicio>() {
        {

            add(SERVICIO_DERMATOLOGIA);
            add(SERVICIO_DIGSTIVO);
            add(SERVICIO_HEMATOLGIA);
            add(SERVICIO_NEUMOLOGIA);
            add(SERVICIO_NEUROLGOA);
            add(SERVICIO_ONCOLOGIA);
            add(SERVICIO_UROLOGIA);

        }
    };

    public Servicio() {
        this.id = new Long(0);
    }

    /**
     * Instantiates a new servicio.
     *
     * @param id the id
     */
    public Servicio(Long id) {
        this.id = id;
    }

    /**
     * Instantiates a new servicio.
     *
     * @param id the id
     * @param codigo the codigo
     * @param descripcion the descripcion
     */
    public Servicio(Long id, String codigo, String descripcion) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    /**
     * Instantiates a new servicio.
     *
     * @param codigo the codigo
     * @param descripcion the descripcion
     */
    public Servicio(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
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
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the string id.
     *
     * @return the string id
     */
    public String getStringId() {
        return Long.toString(getId());
    }

    /**
     * Sets the id.
     *
     * @param i the new id
     */
    public void setId(Long i) {
        this.id = i;
    }

    /**
     * Sets the string id.
     *
     * @param id the new string id
     */
    public void setStringId(String id) {
        this.id = Long.parseLong(id);
    }

    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the codigo.
     *
     * @param codigo the new codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the clase.
     *
     * @return the clase
     */
    public Long getClase() {
        return clase;
    }

    /**
     * Sets the clase.
     *
     * @param clase the new clase
     */
    public void setClase(Long clase) {
        this.clase = clase;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "id=" + this.getId() + "\n" + "codigo=" + this.getCodigo() + "\n" + "descripcion="
                + this.getDescripcion() + "\n" + "clase=" + this.getClase();
    }
}
