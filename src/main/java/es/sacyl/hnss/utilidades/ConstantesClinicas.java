package es.sacyl.hnss.utilidades;

import es.sacyl.hnss.entity.Variable;



public class ConstantesClinicas {

    public final Variable VAR_CTES_DIURESIS = new Variable("3167-4 ", "LN", new Long(3304), "Diuresis");
    public final Variable VAR_CTES_FC = new Variable("364075005", "SNM3", new Long(7747515), "Fc", new Double(30),
            new Double(200));
    public final Variable VAR_CTES_T = new Variable("246508008", "SNM3", new Long(776), "Tª", new Double(33),
            new Double(45));
    public final Variable VAR_CTES_TAD = new Variable("271650006", "SNM3", new Long(13722241), "Tad", new Double(20),
            new Double(300));
    public final Variable VAR_CTES_TAS = new Variable("'11726-7", "LN", new Long(2230), "Tas", new Double(20),
            new Double(300));
    public final Variable VAR_CTES_PESO = new Variable("272102008", "SNM3", new Long(50002230), "Peso");
    public final Variable VAR_CTES_TALLA = new Variable("248328003", "SNM3", new Long(3066), "Talla");
    public final Variable VAR_CTES_IMC = new Variable("260870009", "SNM3", new Long(1484322), "IMC");
    public final Variable VAR_CTES_SATO2 = new Variable("2711-0", "LN", new Long(778), "SatO2", new Double(20),
            new Double(100));
    public final Variable VAR_CTES_FR = new Variable("18686-6", "LN", new Long(13595208), "Fr", new Double(20),
            new Double(240));
    public final Variable VAR_CTES_GLU = new Variable("14749-6", "LN", new Long(502), "Glucosa", new Double(20),
            new Double(900));

    public final Variable VAR_CTES_EVA = new Variable("425401001", "SNM3", new Long(13825347), "EVA", new Double(0),
            new Double(10));
    public final Variable VAR_CTES_PCEFALICO = new Variable("'363812007", "SNM3", new Long(14061530), "P.Cefálico",
            new Double(20), new Double(600));

    public final Variable VAR_ALERGIAS = new Variable("106190000", "SNM3", new Long(13524547), "Alergias");
    public final Variable VAR_OBSERVACIONES = new Variable("246453008", "SNM3", new Long(46293677), "Observaciones ");
    public final Variable VAR_ANTECEDENTESPERSONALES = new Variable("307294006", "SNM3", new Long(13524545),
            "Antecedentes personales ");

    public ConstantesClinicas() {
    }

}
