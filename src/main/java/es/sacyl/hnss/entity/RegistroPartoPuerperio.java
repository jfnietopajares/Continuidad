package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.ConstantesClinicas;



public class RegistroPartoPuerperio extends Registro {

    private Variable analgesia;
    private Variable tas;
    private Variable tad;
    private Variable fc;
    private Variable diuresis;

    private Variable sangrado;
    private Variable alturaFondoUtero;
    private Variable dolor;
    private Variable medicacion;
    private Variable medicacionAdministrada;
    private Variable lactanciaMaterna;
    private Variable pielConPiel;
    private Variable recomendaciones;

    public final Variable VAR_PARTO_PUERPE_ANALGESIA = new Variable("38433004", "SNM3", new Long(46002096),
            "Analgesia");

    public final Variable VAR_PARTO_PUERPE_SANGRADO = new Variable("50960005", "SNM3", new Long(13536364), "Sangrado");

    public final Variable VAR_PARTO_PUERPE_ALTURAUTERO = new Variable("249016007", "SNM3", new Long(13994662),
            "Altura fundo uterino");

    public final Variable VAR_PARTO_PUERPE_DOLOR = new Variable("22253000", "SNM3", new Long(35001625), "Dolor");

    public final Variable VAR_PARTO_PUERPE_MEDICACION = new Variable("260779000", "SNM3", new Long(13825604),
            "Medicación");

    public final Variable VAR_PARTO_PUERPE_MEDICACION_ADMINISTRADA = new Variable("P0901.PE107", "99G2",
            new Long(214037098), "Medicación");

    public final Variable VAR_PARTO_PUERPE_LACTANCIAM = new Variable("10225", "99G2", new Long(10225),
            "Lactancia materna");

    public final Variable VAR_PARTO_PUERPE_PIELPIEL = new Variable("13994663", "99G2", new Long(13994663),
            "Piel con piel");

    public final Variable VAR_PARTO_PUERPE_RECOMENDACIONES = new Variable("13993644", "99G2", new Long(13993644),
            "Piel con piel");

    public final Variable VAR_PARTO_PUERPE_TAS = new ConstantesClinicas().VAR_CTES_TAS;
    public final Variable VAR_PARTO_PUERPE_TAD = new ConstantesClinicas().VAR_CTES_TAD;
    public final Variable VAR_PARTO_PUERPE_FC = new ConstantesClinicas().VAR_CTES_FC;
    public final Variable VAR_PARTO_PUERPE_DIURESIS = new ConstantesClinicas().VAR_CTES_DIURESIS;

    public final static Long PLANTILLLA_EDITOR_PAR_PUERPERIO = new Long(794876691);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public RegistroPartoPuerperio() {
        super();
        iniciaPuerperio();
    }

    public RegistroPartoPuerperio(Long id) {
        super(id);
        iniciaPuerperio();
    }

    public RegistroPartoPuerperio(RegistroPartoPuerperio r) {
        super(r);
        this.tas = r.getTas();
        this.tad = r.getTad();
        this.fc = r.getFc();

        this.sangrado = r.getSangrado();
        this.alturaFondoUtero = r.getAlturaFondoUtero();
        this.dolor = r.getDolor();
        this.medicacion = r.getMedicacion();
        this.medicacionAdministrada = r.getMedicacionAdministrada();
        this.diuresis = r.getDiuresis();
        this.lactanciaMaterna = r.getLactanciaMaterna();
        this.pielConPiel = r.getPielConPiel();
        this.recomendaciones = r.getRecomendaciones();

    }

    public void iniciaPuerperio() {
        this.plantilla_editor = PLANTILLLA_EDITOR_PAR_PUERPERIO;
        this.tiporegistro = TIPO_REGISTRO_PARTO;
        this.descripcion = "8.-Puerperio ";
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));
        this.analgesia = VAR_PARTO_PUERPE_ANALGESIA;
        this.tas = VAR_PARTO_PUERPE_TAS;
        this.tad = VAR_PARTO_PUERPE_TAD;
        this.fc = VAR_PARTO_PUERPE_FC;

        this.sangrado = VAR_PARTO_PUERPE_SANGRADO;
        this.alturaFondoUtero = VAR_PARTO_PUERPE_ALTURAUTERO;
        this.dolor = VAR_PARTO_PUERPE_DOLOR;
        this.medicacion = VAR_PARTO_PUERPE_MEDICACION;
        this.medicacionAdministrada = VAR_PARTO_PUERPE_MEDICACION_ADMINISTRADA;
        this.diuresis = VAR_PARTO_PUERPE_DIURESIS;
        this.lactanciaMaterna = VAR_PARTO_PUERPE_LACTANCIAM;
        this.pielConPiel = VAR_PARTO_PUERPE_PIELPIEL;
        this.recomendaciones = VAR_PARTO_PUERPE_RECOMENDACIONES;
    }

    public Variable getAnalgesia() {
        return analgesia;
    }

    public Variable getVariableAnalgesia() {
        return analgesia;
    }

    public String getAnalgesiaString() {
        return analgesia.getValor();
    }

    public void setAnalgesia(Variable analgesia) {
        this.analgesia = analgesia;
    }

    public void setAnalgesia(String valor) {
        this.analgesia.setValor(valor);
    }

    public Variable getTas() {
        return tas;
    }

    public Variable getVariableTas() {
        return tas;
    }

    public String getTasString() {
        return tas.getValor();
    }

    public void setTas(Variable tas) {
        this.tas = tas;
    }

    public void setTas(String valor) {
        this.tas.setValor(valor);
    }

    public Variable getTad() {
        return tad;
    }

    public Variable getVariableTad() {
        return tad;
    }

    public String getTadString() {
        return tad.getValor();
    }

    public void setTad(Variable tad) {
        this.tad = tad;
    }

    public void setTad(String valor) {
        this.tad.setValor(valor);
    }

    public Variable getFc() {
        return fc;
    }

    public Variable getVariableFc() {
        return fc;
    }

    public String getFcString() {
        return fc.getValor();
    }

    public void setFc(Variable fc) {
        this.fc = fc;
    }

    public void setFc(String valor) {
        this.fc.setValor(valor);
    }

    public Variable getSangrado() {
        return sangrado;
    }

    public Variable getVariableSangrado() {
        return sangrado;
    }

    public String getSangradoString() {
        return sangrado.getValor();
    }

    public void setSangrado(Variable sangrado) {
        this.sangrado = sangrado;
    }

    public void setSangrado(String valor) {
        this.sangrado.setValor(valor);
    }

    public Variable getAlturaFondoUtero() {
        return alturaFondoUtero;
    }

    public Variable getVariableAlturaFondoUtero() {
        return alturaFondoUtero;
    }

    public String getAlturaFondoUteroString() {
        return alturaFondoUtero.getValor();
    }

    public void setAlturaFondoUtero(Variable alturaFondoUtero) {
        this.alturaFondoUtero = alturaFondoUtero;
    }

    public void setAlturaFondoUtero(String valor) {
        this.alturaFondoUtero.setValor(valor);
    }

    public Variable getDolor() {
        return dolor;
    }

    public Variable getVariableDolor() {
        return dolor;
    }

    public String getDolorString() {
        return dolor.getValor();
    }

    public void setDolor(Variable dolor) {
        this.dolor = dolor;
    }

    public void setDolor(String valor) {
        this.dolor.setValor(valor);
    }

    public Variable getMedicacion() {
        return medicacion;
    }

    public Variable getVariableMedicacion() {
        return medicacion;
    }

    public String getMedicacionString() {
        return medicacion.getValor();
    }

    public void setMedicacion(Variable medicacion) {
        this.medicacion = medicacion;
    }

    public void setMedicacion(String valor) {
        this.medicacion.setValor(valor);
    }

    public Variable getMedicacionAdministrada() {
        return medicacionAdministrada;
    }

    public Variable getVariableMedicacionAdministrada() {
        return medicacionAdministrada;
    }

    public String getMedicacionAdministradaString() {
        return medicacionAdministrada.getValor();
    }

    public void setMedicacionAdministrada(Variable medicacionAdministrada) {
        this.medicacionAdministrada = medicacionAdministrada;
    }

    public void setMedicacionAdministrada(String valor) {
        this.medicacionAdministrada.setValor(valor);
    }

    public Variable getDiuresis() {
        return diuresis;
    }

    public Variable getVariableDiuresis() {
        return diuresis;
    }

    public String getDiuresisString() {
        return diuresis.getValor();
    }

    public void setDiuresis(Variable diuresis) {
        this.diuresis = diuresis;
    }

    public void setDiuresis(String valor) {
        this.diuresis.setValor(valor);
    }

    public Variable getLactanciaMaterna() {
        return lactanciaMaterna;
    }

    public Variable getVariableLactanciaMaterna() {
        return lactanciaMaterna;
    }

    public String getLactanciaMaternaString() {
        return lactanciaMaterna.getValor();
    }

    public void setLactanciaMaterna(Variable lactanciaMaterna) {
        this.lactanciaMaterna = lactanciaMaterna;
    }

    public void setLactanciaMaterna(String valor) {
        this.lactanciaMaterna.setValor(valor);
    }

    public Variable getPielConPiel() {
        return pielConPiel;
    }

    public Variable getVariablePielConPiel() {
        return pielConPiel;
    }

    public String getPielConPielString() {
        return pielConPiel.getValor();
    }

    public void setPielConPiel(Variable pielConPiel) {
        this.pielConPiel = pielConPiel;
    }

    public void setPielConPiel(String valor) {
        this.pielConPiel.setValor(valor);
    }

    public Variable getRecomendaciones() {
        return recomendaciones;
    }

    public Variable getVariableRecomendaciones() {
        return recomendaciones;
    }

    public String getRecomendacionesString() {
        return recomendaciones.getValor();
    }

    public void setRecomendaciones(Variable recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public void setRecomendaciones(String valor) {
        this.recomendaciones.setValor(valor);
    }

}
