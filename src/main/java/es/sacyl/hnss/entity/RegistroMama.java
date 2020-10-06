package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;


/**
 * The Class RegistroMama.
 *
 * *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 *
 * Define el tipo de registro mama Los método getVariable son muy importantes ya
 * que son lo que la clase registroDAO para insertar los datos en la tabla
 * campos_r.
 *
 * El atrituto que no tenga este método no será consolidado en la base de datos
 *
 */
public class RegistroMama extends Registro implements Cloneable {

    protected Variable idpcan;
    protected Variable fechaap;
    protected Variable tnm;
    protected Variable birads;
    protected Variable biradscorregido;
    protected Variable fechagine;
    protected Variable fechacomite;
    protected Variable fechaprueba;
    protected Variable fechatratamiento;
    protected Variable observaciones;

    public final static Long PLANTILLLA_EDITOR_REGISTROMAMA = new Long(650740603);
    public final static Long TIPO_REGISTRO_MAMA = new Long(60);
    // OJO buscar id para idpcam
    public final Variable VAR_MAMA_IDPCAN = new Variable("999999", "99G2", new Long(999999), "Número");
    public final Variable VAR_MAMA_FECHAAP = new Variable("13822263", "99G2", new Long(13822263), "Fecha A.Primaria");

    public final Variable VAR_MAMA_TNM = new Variable("9558", "99G2", new Long(9558), "TNM");
    public final Variable VAR_MAMA_BIRADS = new Variable("13992308", "99G2", new Long(13992308), "BIRADS");
    public final Variable VAR_MAMA_BIRADSCORREGIDO = new Variable("397141009", "SNM3", new Long(50001964),
            "BIRADS 2 corregido");
    public final Variable VAR_MAMA_FECHAGINE = new Variable("13992304", "99G2", new Long(13992304), "Fecha gine/onco");
    public final Variable VAR_MAMA_FECHACOMITE = new Variable("13817685", "99G2", new Long(13817685), "Fecha comité");
    public final Variable VAR_MAMA_FECHAPRUEBA = new Variable("13992305", "99G2", new Long(13992305), "Fecha prueba");
    public final Variable VAR_MAMA_FECHATRATAMIENTO = new Variable("10082", "99G2", new Long(10082),
            "Fecha tratamiento");

    /**
     * Instantiates a new registro mama.
     */
    public RegistroMama() {
        super();
        this.iniciaMama();
    }

    public final Variable VAR_MAMA_OBSERVACIONES = new Variable("COM", "99G2", new Long(34429773), "Comentario");

    /**
     * Instantiates a new registro mama.
     *
     * @param id the id
     */
    public RegistroMama(Long id) {
        super(id);
        this.iniciaMama();
    }

    public RegistroMama(RegistroMama rm) {
        super(rm);
        this.id = rm.getId();
        this.idpcan = rm.getIdpcan();
        this.fechaap = rm.getFechaap();
        this.tnm = rm.getTnm();
        this.birads = rm.getBirads();
        this.biradscorregido = rm.getBiradscorregido();
        this.fechagine = rm.getFechagine();
        this.fechacomite = rm.fechacomite;
        this.fechaprueba = rm.getFechaprueba();
        this.fechatratamiento = rm.getFechatratamiento();
        this.observaciones = rm.getObservaciones();

    }

    /**
     * Inicia.
     */
    public void iniciaMama() {
        this.setTiporegistro(RegistroMama.TIPO_REGISTRO_MAMA);
        this.setDescripcion("Registro screenig mama");
        this.setPlantilla_edior(RegistroMama.PLANTILLLA_EDITOR_REGISTROMAMA);
        this.setServicio(new Servicio(new Long(21)));

        this.idpcan = VAR_MAMA_IDPCAN;
        this.fechaap = VAR_MAMA_FECHAAP;
        this.tnm = VAR_MAMA_TNM;
        this.birads = VAR_MAMA_BIRADS;
        this.fechaprueba = VAR_MAMA_FECHAPRUEBA;
        this.biradscorregido = VAR_MAMA_BIRADSCORREGIDO;
        this.fechagine = VAR_MAMA_FECHAGINE;
        this.fechacomite = VAR_MAMA_FECHACOMITE;
        this.fechatratamiento = VAR_MAMA_FECHATRATAMIENTO;
        this.observaciones = VAR_MAMA_OBSERVACIONES;

    }

    /**
     * Gets the fechaap value.
     *
     * @return the fechaap value
     */
    public LocalDate getFechaapValue() {
        LocalDate fecha = null;
        if (this.fechaap != null) {
            if (this.fechaap.getValor() != null && !this.fechaap.getValor().isEmpty()) {
                if (Utilidades.isNumero(this.fechaap.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechaap.getValor()));
                }
            }
        }
        return fecha;
    }

    /**
     * Sets the fechaap value date.
     *
     * @param fecha the new fechaap value date
     */
    public void setFechaap(LocalDate fecha) {
        this.fechaap.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));

    }

    /**
     * Sets the fechaap value.
     *
     * @param valor the new fechaap value
     */
    public void setFechaap(Long valor) {
        this.fechaap.setValor(Long.toString(valor));
    }

    /**
     * Gets the tnm value.
     *
     * @return the tnm value
     */
    public String getTnmValue() {
        return this.tnm.getValor();
    }

    /**
     * Sets the tnm valude.
     *
     * @param valor the new tnm valude
     */
    public void setTnm(String valor) {
        this.tnm.setValor(valor);
    }

    /**
     * Gets the birads value.
     *
     * @return the birads value
     */
    public String getBiradsValue() {
        return this.birads.getValor();
    }

    /**
     * Sets the birads value.
     *
     * @param valor the new birads value
     */
    public void setBirads(String valor) {
        this.birads.setValor(valor);
    }

    /**
     * Gets the biradscorregido value.
     *
     * @return the biradscorregido value
     */
    public String getBiradscorregidoValue() {
        return this.biradscorregido.getValor();
    }

    /**
     * Sets the biradscorregido value.
     *
     * @param valor the new biradscorregido value
     */
    public void setBiradscorregidoValue(String valor) {
        this.biradscorregido.setValor(valor);
    }

    /**
     * Gets the fechagine value.
     *
     * @return the fechagine value
     */
    public LocalDate getFechagineValue() {
        LocalDate fecha = null;
        if (this.fechagine != null) {
            if (this.fechagine.getValor() != null && !this.fechagine.getValor().isEmpty()) {
                if (Utilidades.isNumero(this.fechagine.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechagine.getValor()));
                }
            }
        }
        return fecha;
    }

    /**
     * Sets the fechagine value date.
     *
     * @param fecha the new fechagine value date
     */
    public void setFechagine(LocalDate fecha) {
        this.fechagine.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    /**
     * Sets the fechagine value.
     *
     * @param valor the new fechagine value
     */
    public void setFechagine(Long valor) {
        this.fechagine.setValor(Long.toString(valor));
    }

    /**
     * Gets the fechacomite value.
     *
     * @return the fechacomite value
     */
    public LocalDate getFechacomiteValue() {
        LocalDate fecha = null;
        if (this.fechacomite != null) {
            if (this.fechacomite.getValor() != null && !this.fechacomite.getValor().isEmpty()) {
                if (Utilidades.isNumero(this.fechagine.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechacomite.getValor()));
                }
            }
        }
        return fecha;
    }

    /**
     * Sets the fechacomite value date.
     *
     * @param fecha the new fechacomite value date
     */
    public void setFechacomite(LocalDate fecha) {
        this.fechacomite.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    /**
     * Sets the fechacomite value.
     *
     * @param valor the new fechacomite value
     */
    public void setFechacomite(Long valor) {
        this.fechacomite.setValor(Long.toString(valor));
    }

    /**
     * Gets the fechaprueba value.
     *
     * @return the fechaprueba value
     */
    public LocalDate getFechapruebaValue() {
        LocalDate fecha = null;
        if (this.fechaprueba != null) {
            if (this.fechaprueba.getValor() != null && !this.fechaprueba.getValor().isEmpty()) {
                if (Utilidades.isNumero(this.fechaprueba.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechaprueba.getValor()));
                }
            }
        }
        return fecha;
    }

    /**
     * Sets the fechaprueba value date.
     *
     * @param fecha the new fechaprueba value date
     */
    public void setFechaprueba(LocalDate fecha) {

        this.fechaprueba.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));

    }

    /**
     * Sets the fechaprueba value.
     *
     * @param valor the new fechaprueba value
     */
    public void setFechaprueba(Long valor) {
        this.fechaprueba.setValor(Long.toString(valor));
    }

    /**
     * Gets the fechatratamiento value.
     *
     * @return the fechatratamiento value
     */
    public LocalDate getFechatratamientoValue() {
        LocalDate fecha = null;
        if (this.fechatratamiento != null) {
            if (this.fechatratamiento.getValor() != null && !this.fechatratamiento.getValor().isEmpty()) {
                if (Utilidades.isNumero(this.fechaprueba.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechatratamiento.getValor()));
                }
            }
        }
        return fecha;
    }

    /**
     * Sets the fechatratamiento value date.
     *
     * @param fecha the new fechatratamiento value date
     */
    public void setFechatratamiento(LocalDate fecha) {
        this.fechatratamiento.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    /**
     * Sets the fechatratamiento value.
     *
     * @param valor the new fechatratamiento value
     */
    public void setFechatratamiento(Long valor) {
        this.fechatratamiento.setValor(Long.toString(valor));
    }

    /**
     * Gets the variable observaciones value.
     *
     * @return the variable observaciones value
     */
    public Variable getVariableObservacionesValue() {
        return this.observaciones;
    }

    /**
     * Gets the observaciones value.
     *
     * @return the observaciones value
     */
    public String getObservacionesValue() {
        return this.observaciones.getValor();
    }

    /**
     * Sets the observaciones value.
     *
     * @param valor the new observaciones value
     */
    public void setObservaciones(String valor) {
        this.observaciones.setValor(valor);
    }

    /**
     * Gets the variable fechaap.
     *
     * @return the variable fechaap
     */
    public Variable getVariableFechaap() {
        return this.fechaap;
    }

    /**
     * Sets the fechaap.
     *
     * @param fechaap the new fechaap
     */
    public void setFechaap(Variable fechaap) {
        this.fechaap = fechaap;
    }

    /**
     * Gets the variable tnm.
     *
     * @return the variable tnm
     */
    public Variable getVariableTnm() {
        return this.tnm;
    }

    /**
     * Sets the tnm.
     *
     * @param tnm the new tnm
     */
    public void setTnm(Variable tnm) {
        this.tnm = tnm;
    }

    /**
     * Gets the variable birads.
     *
     * @return the variable birads
     */
    public Variable getVariableBirads() {
        return this.birads;
    }

    /**
     * Sets the birads.
     *
     * @param birads the new birads
     */
    public void setBirads(Variable birads) {
        this.birads = birads;
    }

    /**
     * Gets the variable biradscorregido.
     *
     * @return the variable biradscorregido
     */
    public Variable getVariableBiradscorregido() {
        return this.biradscorregido;
    }

    /**
     * Sets the biradscorregido.
     *
     * @param biradscorregido the new biradscorregido
     */
    public void setBiradscorregido(Variable biradscorregido) {
        this.biradscorregido = biradscorregido;
    }

    /**
     * Gets the variable fechagine.
     *
     * @return the variable fechagine
     */
    public Variable getVariableFechagine() {
        return this.fechagine;
    }

    /**
     * Sets the fechagine.
     *
     * @param fechagine the new fechagine
     */
    public void setFechagine(Variable fechagine) {
        this.fechagine = fechagine;
    }

    /**
     * Gets the variable fechacomite.
     *
     * @return the variable fechacomite
     */
    public Variable getVariableFechacomite() {
        return this.fechacomite;
    }

    /**
     * Sets the fechacomite.
     *
     * @param fechacomite the new fechacomite
     */
    public void setFechacomite(Variable fechacomite) {
        this.fechacomite = fechacomite;
    }

    /**
     * Gets the variable fechaprueba.
     *
     * @return the variable fechaprueba
     */
    public Variable getVariableFechaprueba() {
        return this.fechaprueba;
    }

    /**
     * Sets the fechaprueba.
     *
     * @param fechaprueba the new fechaprueba
     */
    public void setFechaprueba(Variable fechaprueba) {
        this.fechaprueba = fechaprueba;
    }

    /**
     * Gets the variable fechatratamiento.
     *
     * @return the variable fechatratamiento
     */
    public Variable getVariableFechatratamiento() {
        return this.fechatratamiento;
    }

    /**
     * Sets the fechatratamiento.
     *
     * @param fechatratamiento the new fechatratamiento
     */
    public void setFechatratamiento(Variable fechatratamiento) {
        this.fechatratamiento = fechatratamiento;
    }

    /**
     * Gets the variable observaciones.
     *
     * @return the variable observaciones
     */
    public Variable getVariableObservaciones() {
        return this.observaciones;
    }

    /**
     * Sets the observaciones.
     *
     * @param observaciones the new observaciones
     */
    public void setObservaciones(Variable observaciones) {
        this.observaciones = observaciones;
    }

    public Variable getVariableIdpcan() {
        return this.idpcan;
    }

    public Variable getIdpcan() {
        return this.idpcan;
    }

    public String getIdpcanValue() {
        return this.idpcan.getValor();
    }

    public void setIdpcan(Variable idpcan) {
        this.idpcan = idpcan;
    }

    public void setIdpcan(String idpcan) {
        this.idpcan.setValor(idpcan);
    }

    public Variable getFechaap() {
        return this.fechaap;
    }

    public Variable getTnm() {
        return this.tnm;
    }

    public Variable getBirads() {
        return this.birads;
    }

    public Variable getBiradscorregido() {
        return this.biradscorregido;
    }

    public Variable getFechagine() {
        return this.fechagine;
    }

    public Variable getFechacomite() {
        return this.fechacomite;
    }

    public Variable getFechaprueba() {
        return this.fechaprueba;
    }

    public Variable getFechatratamiento() {
        return this.fechatratamiento;
    }

    public Variable getObservaciones() {
        return this.observaciones;
    }

    public String toString() {
        String txtString = "";

        txtString = "\n ------------------------------------ \n";
        txtString = txtString.concat("id: " + this.getId() + "\n");
        txtString = txtString.concat("Fecha AP: " + this.getFechaapValue() + "\n");
        if (this.getBiradsValue() != null) {
            txtString = txtString.concat("Bidrads: " + this.getBiradsValue() + "\n");
        } else {
            txtString = txtString.concat("Bidrads: " + "\n");
        }
        if (this.getTnmValue() != null) {
            txtString = txtString.concat("Tnm: " + this.getTnmValue() + "\n");
        } else {
            txtString = txtString.concat("Tnm: " + "\n");
        }
        if (this.getFechaprueba() != null) {
            txtString = txtString.concat("FechaPrueba: " + this.getFechapruebaValue() + "\n");
        } else {
            txtString = txtString.concat("FechaPrueba: " + "\n");
        }
        if (this.getBiradscorregidoValue() != null) {
            txtString = txtString.concat("BidradsCorregido: " + this.getBiradscorregidoValue() + "\n");
        } else {
            txtString = txtString.concat("BidradsCorregido:: " + "\n");
        }
        if (this.getFechagine() != null) {
            txtString = txtString.concat("FechaGine: " + this.getFechagineValue() + "\n");
        } else {
            txtString = txtString.concat("FechaGine: " + "\n");
        }
        if (this.getFechacomiteValue() != null) {
            txtString = txtString.concat("FechaComite: " + this.getFechacomiteValue() + "\n");
        } else {
            txtString = txtString.concat("FechaComite: " + "\n");
        }
        if (this.getFechatratamientoValue() != null) {
            txtString = txtString.concat("FechaTratamiento: " + this.getFechatratamientoValue() + "\n");
        } else {
            txtString = txtString.concat("FechaTratamiento: " + "\n");
        }
        if (this.getObservacionesValue() != null) {
            txtString = txtString.concat("Observaciones: " + this.getObservacionesValue() + "\n");
        } else {
            txtString = txtString.concat("Observaciones: " + "\n");
        }
        return txtString;
    }
}
