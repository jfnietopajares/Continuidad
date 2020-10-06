package es.sacyl.hnss.entity;

public class RegistroPartoConstantes extends Registro {

    private Variable temperatura;
    private Variable tas;
    private Variable tad;
    private Variable fc;
    private Variable fiebreMaterna;
    private Variable registroCaridiotocografico;
    private Variable interpretacioRC;
    private Variable phFetal;
    private Variable phFetalValor;

    public final static Long PLANTILLLA_EDITOR_PAR_CONSTANTES = new Long(794870799);

    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public final Variable VAR_PARTO_CTE_TAS = new Variable("'11726-7", "LN", new Long(2230), "Tas", new Double(20),
            new Double(300));

    public final Variable VAR_PARTO_CTE_TAD = new Variable("271650006", "SNM3", new Long(13722241), "Tad",
            new Double(20), new Double(300));

    public final Variable VAR_PARTO_CTE_T = new Variable("246508008", "SNM3", new Long(776), "Tª", new Double(33),
            new Double(45));

    public final Variable VAR_PARTO_CTE_FC = new Variable("364075005", "SNM3", new Long(7747515), "Fc", new Double(30),
            new Double(200));

    public final Variable VAR_PARTO_CTE_FIEBRE_MATERNA = new Variable("13994639", "99G2", new Long(13994639),
            "Fiebre Materna");

    public final Variable VAR_PARTO_CTE_TIPO_REGECOCARDIO = new Variable("13994641", "99G2", new Long(13994641),
            "Tipo Reg cadiotocográfico");

    public final Variable VAR_PARTO_CTE_INTERPRETACIONRECO = new Variable("13994642", "99G2", new Long(13994642),
            "Interpretación RECO");

    public final Variable VAR_PARTO_CTE_PHFETAL = new Variable("13994640", "99G2", new Long(13994640), "PH Fetal");

    public Variable VAR_PARTO_CTE_PHFETAL_VALOR = new Variable("199785006", "SNM3", new Long(13994643),
            "Valor PH Fetal");

    public RegistroPartoConstantes() {
        this.iniciaPartoCtes();
    }

    public RegistroPartoConstantes(Long id) {
        super(id);
        this.iniciaPartoCtes();
    }

    public RegistroPartoConstantes(RegistroPartoConstantes r) {
        super(r);
        this.temperatura = r.getTemperatura();
        this.tas = r.getTas();
        this.tad = r.getTad();
        this.fc = r.getFc();
        this.fiebreMaterna = r.getFiebreMaterna();
        this.registroCaridiotocografico = r.getRegistroCaridiotocografico();
        this.interpretacioRC = r.getInterpretacioRC();
        this.phFetal = r.getPhFetal();
        this.phFetalValor = r.getPhFetalValor();
    }

    public void iniciaPartoCtes() {
        this.setTiporegistro(RegistroPartoConstantes.TIPO_REGISTRO_PARTO);
        this.setDescripcion("Registro Parto constantes");
        this.setPlantilla_edior(RegistroPartoConstantes.PLANTILLLA_EDITOR_PAR_CONSTANTES);
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));
        this.temperatura = VAR_PARTO_CTE_T;
        this.tas = VAR_PARTO_CTE_TAS;
        this.tad = VAR_PARTO_CTE_TAD;
        this.fc = VAR_PARTO_CTE_FC;
        this.fiebreMaterna = VAR_PARTO_CTE_FIEBRE_MATERNA;
        this.registroCaridiotocografico = VAR_PARTO_CTE_TIPO_REGECOCARDIO;
        this.interpretacioRC = VAR_PARTO_CTE_INTERPRETACIONRECO;
        this.phFetal = VAR_PARTO_CTE_PHFETAL;
        this.phFetalValor = VAR_PARTO_CTE_PHFETAL_VALOR;
    }

    public Variable getTemperatura() {
        return temperatura;
    }

    public Variable getVariableTemperatura() {
        return temperatura;
    }

    public String getTemperaturaString() {
        return temperatura.getValor();
    }

    public void setTemperatura(Variable temperatura) {
        this.temperatura = temperatura;
    }

    public void setTemperatura(String valor) {
        this.temperatura.setValor(valor);
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

    public Variable getFiebreMaterna() {
        return fiebreMaterna;
    }

    public Variable getVariableFiebreMaterna() {
        return fiebreMaterna;
    }

    public String getFiebreMaternaString() {
        return fiebreMaterna.getValor();
    }

    public void setFiebreMaterna(Variable fiebreMaterna) {
        this.fiebreMaterna = fiebreMaterna;
    }

    public void setFiebreMaterna(String valor) {
        this.fiebreMaterna.setValor(valor);
    }

    public Variable getRegistroCaridiotocografico() {
        return registroCaridiotocografico;
    }

    public Variable getVariableRegistroCaridiotocográfico() {
        return registroCaridiotocografico;
    }

    public String getRegistroCaridiotocograficoString() {
        return registroCaridiotocografico.getValor();
    }

    public void setRegistroCaridiotocografico(Variable registroCaridiotocografico) {
        this.registroCaridiotocografico = registroCaridiotocografico;
    }

    public void setRegistroCaridiotocografico(String valor) {
        this.registroCaridiotocografico.setValor(valor);
    }

    public Variable getInterpretacioRC() {
        return interpretacioRC;
    }

    public Variable getVariableInterpretacioRC() {
        return interpretacioRC;
    }

    public String getInterpretacioRCString() {
        return interpretacioRC.getValor();
    }

    public void setInterpretacioRC(Variable interpretacioRC) {
        this.interpretacioRC = interpretacioRC;
    }

    public void setInterpretacioRC(String valor) {
        this.interpretacioRC.setValor(valor);
    }

    public Variable getPhFetal() {
        return phFetal;
    }

    public Variable getVariablePhFetal() {
        return phFetal;
    }

    public String getPhFetalString() {
        return phFetal.getValor();
    }

    public void setPhFetal(Variable phFetal) {
        this.phFetal = phFetal;
    }

    public void setPhFetal(String valor) {
        this.phFetal.setValor(valor);
    }

    public Variable getPhFetalValor() {
        return phFetalValor;
    }

    public Variable getVariablePhFetalValor() {
        return phFetalValor;
    }

    public String getPhFetalValorString() {
        return phFetalValor.getValor();
    }

    public void setPhFetalValor(Variable phFetalValor) {
        this.phFetalValor = phFetalValor;
    }

    public void setPhFetalValor(String valor) {
        this.phFetalValor.setValor(valor);
        ;
    }
}
