package es.sacyl.hnss.entity;

public class RegistroPartoAlumbramiento extends Registro {

    private Variable tipoParto;
    private Variable placenta;
    private Variable membranasFetales;
    private Variable cordonVasos;
    private Variable cordonInsercion;
    private Variable cordonDonacion;
    private Variable hemorragiaCantidad;
    private Variable hemorragiaSingos;
    private Variable hemorragiaTratamiento;

    public final Variable VAR_PARTO_ALUM_TIPOPARTO = new Variable("13992785", "99G2", new Long(13992785),
            "Tipo Parto ");
//,Dirigido,Espontáneo,Manual
    public final Variable VAR_PARTO_ALUM_PLACENTA = new Variable("122736005", "SNM3", new Long(5628), "Placenta ");
    // ,Normal,Patológica
    public final Variable VAR_PARTO_ALUM_MEMBRANASFETALES = new Variable("54134001", "SNM3", new Long(13994652),
            "Membranas fetales ");
    // membranas fetales ,Íntegras,Desgarradas
    public final Variable VAR_PARTO_ALUM_CORDONVASOS = new Variable("13994647", "99G2", new Long(13994647),
            "Cordón vasos");
//	3 Vasos,Arteria única
    public final Variable VAR_PARTO_ALUM_CORDONINSERCION = new Variable("13994648", "99G2", new Long(13994648),
            "Cordón inserción ");
    // ,Central,Lateral,Velamentosa

    public final Variable VAR_PARTO_ALUM_CORDONDONACION = new Variable("N420101.DONCORD", "99G2", new Long(104992),
            "Cordón donación sangre ");
    // ,No,Pública,Privada
    public final Variable VAR_PARTO_ALUM_HEMORRACANTIDAD = new Variable("13994649", "99G2", new Long(13994649),
            "Hemorragia cantidad ");
//<500 ml,>500 ml,>1000 ml
    public final Variable VAR_PARTO_ALUM_HEMORRASINGOS = new Variable("13994650", "99G2", new Long(13994650),
            "Hemorragia signos clínicos ");
//si,no
    public final Variable VAR_PARTO_ALUM_HEMORRATRATAMIENTO = new Variable("13994651", "99G2", new Long(13994651),
            "Hemorragia tratamiento ");
    // ,Trendelemburg,Sueroterapia,Tratamiento

    public final static Long PLANTILLLA_EDITOR_PAR_ALUMBRAMIENTO = new Long(794874773);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public RegistroPartoAlumbramiento() {
        super();
        iniciaAlumbramiento();
    }

    public RegistroPartoAlumbramiento(Long id) {
        super(id);
        iniciaAlumbramiento();
    }

    public RegistroPartoAlumbramiento(RegistroPartoAlumbramiento r) {
        super(r);
        this.tipoParto = r.getTipoParto();
        this.placenta = r.getPlacenta();
        this.membranasFetales = r.getMembranasFetales();
        this.cordonVasos = r.getCordonVasos();
        this.cordonInsercion = r.getCordonInsercion();
        this.cordonDonacion = r.getCordonDonacion();
        this.hemorragiaCantidad = r.getHemorragiaCantidad();
        this.hemorragiaSingos = r.getHemorragiaSingos();
        this.hemorragiaTratamiento = r.getHemorragiaTratamiento();
    }

    public void iniciaAlumbramiento() {
        this.plantilla_editor = PLANTILLLA_EDITOR_PAR_ALUMBRAMIENTO;
        this.tiporegistro = TIPO_REGISTRO_PARTO;
        this.descripcion = "5. Parto Alumbramiento";
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));

        this.tipoParto = VAR_PARTO_ALUM_TIPOPARTO;
        this.placenta = VAR_PARTO_ALUM_PLACENTA;
        this.membranasFetales = VAR_PARTO_ALUM_MEMBRANASFETALES;
        this.cordonVasos = VAR_PARTO_ALUM_CORDONVASOS;
        this.cordonInsercion = VAR_PARTO_ALUM_CORDONINSERCION;
        this.cordonDonacion = VAR_PARTO_ALUM_CORDONDONACION;
        this.hemorragiaCantidad = VAR_PARTO_ALUM_HEMORRACANTIDAD;
        this.hemorragiaSingos = VAR_PARTO_ALUM_HEMORRASINGOS;
        this.hemorragiaTratamiento = VAR_PARTO_ALUM_HEMORRATRATAMIENTO;
    }

    public Variable getTipoParto() {
        return tipoParto;
    }

    public Variable getVariableTipoParto() {
        return tipoParto;
    }

    public String getTipoPartoString() {
        return tipoParto.getValor();
    }

    public void setTipoParto(Variable tipoParto) {
        this.tipoParto = tipoParto;
    }

    public void setTipoParto(String valor) {
        this.tipoParto.setValor(valor);
    }

    public Variable getPlacenta() {
        return placenta;
    }

    public Variable getVariablePlacenta() {
        return placenta;
    }

    public String getPlacentaString() {
        return placenta.getValor();
    }

    public void setPlacenta(Variable placenta) {
        this.placenta = placenta;
    }

    public void setPlacenta(String valor) {
        this.placenta.setValor(valor);
    }

    public Variable getMembranasFetales() {
        return membranasFetales;
    }

    public Variable getVariableMembranasFetales() {
        return membranasFetales;
    }

    public String getMembranasFetalesString() {
        return membranasFetales.getValor();
    }

    public void setMembranasFetales(Variable membranasFetales) {
        this.membranasFetales = membranasFetales;
    }

    public void setMembranasFetales(String valor) {
        this.membranasFetales.setValor(valor);
    }

    public Variable getCordonVasos() {
        return cordonVasos;
    }

    public Variable getVariableCordonVasos() {
        return cordonVasos;
    }

    public String getCordonVasosString() {
        return cordonVasos.getValor();
    }

    public void setCordonVasos(Variable cordonVasos) {
        this.cordonVasos = cordonVasos;
    }

    public void setCordonVasos(String valor) {
        this.cordonVasos.setValor(valor);
    }

    public Variable getCordonInsercion() {
        return cordonInsercion;
    }

    public Variable getVariableCordonInsercion() {
        return cordonInsercion;
    }

    public String getCordonInsercionString() {
        return cordonInsercion.getValor();
    }

    public void setCordonInsercion(Variable cordonInsercion) {
        this.cordonInsercion = cordonInsercion;
    }

    public void setCordonInsercion(String valor) {
        this.cordonInsercion.setValor(valor);
    }

    public Variable getCordonDonacion() {
        return cordonDonacion;
    }

    public Variable getVariableCordonDonacion() {
        return cordonDonacion;
    }

    public String getCordonDonacionString() {
        return cordonDonacion.getValor();
    }

    public void setCordonDonacion(Variable cordonDonacion) {
        this.cordonDonacion = cordonDonacion;
    }

    public void setCordonDonacion(String valor) {
        this.cordonDonacion.setValor(valor);
    }

    public Variable getHemorragiaCantidad() {
        return hemorragiaCantidad;
    }

    public Variable getVariableHemorragiaCantidad() {
        return hemorragiaCantidad;
    }

    public String getHemorragiaCantidadString() {
        return hemorragiaCantidad.getValor();
    }

    public void setHemorragiaCantidad(Variable hemorragiaCantidad) {
        this.hemorragiaCantidad = hemorragiaCantidad;
    }

    public void setHemorragiaCantidad(String valor) {
        this.hemorragiaCantidad.setValor(valor);
    }

    public Variable getHemorragiaSingos() {
        return hemorragiaSingos;
    }

    public Variable getVariableHemorragiaSingos() {
        return hemorragiaSingos;
    }

    public String getHemorragiaSingosString() {
        return hemorragiaSingos.getValor();
    }

    public void setHemorragiaSingos(Variable hemorragiaSingos) {
        this.hemorragiaSingos = hemorragiaSingos;
    }

    public void setHemorragiaSingos(String valor) {
        this.hemorragiaSingos.setValor(valor);
    }

    public Variable getHemorragiaTratamiento() {
        return hemorragiaTratamiento;
    }

    public Variable geVariabletHemorragiaTratamiento() {
        return hemorragiaTratamiento;
    }

    public String getHemorragiaTratamientoString() {
        return hemorragiaTratamiento.getValor();
    }

    public void setHemorragiaTratamiento(Variable hemorragiaTratamiento) {
        this.hemorragiaTratamiento = hemorragiaTratamiento;
    }

    public void setHemorragiaTratamiento(String valor) {
        this.hemorragiaTratamiento.setValor(valor);
    }
}
