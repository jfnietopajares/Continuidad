package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.util.ArrayList;

/**
 * The Class Variable. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Variable {

    private String code;

    private String codesystem;

    private Long item;

    private String codigo;

    private String descripcion;

    private String descripcionCorta;

    private String unidades;

    private String valor;

    private Double valorInferior;

    private Double valorSuperior;

    private String textoAyuda;

    private String tipoVariable;

    private LocalDate fechaValor;

    private char diagnosticoprocedimiento;

    private String codigoCie10;

    public final static String TIPO_VARIABLE_STRING = "string";
    public final static String TIPO_VARIABLE_DATE = "date";
    public final static String TIPO_VARIABLE_CHECK = "check";

    public final static Variable ONCO_PRESTACION_TTL = new Variable("Lar", "13995056", "99G2", new Long(13995056),
            "Quim.Intr.largo");
    public final static Variable ONCO_PRESTACION_TTM = new Variable("Med", "13995057", "99G2", new Long(13995057),
            "Quim.Intr. medio");
    public final static Variable ONCO_PRESTACION_TTC = new Variable("Cor", "13995058", "99G2", new Long(13995058),
            "Quim.Intr.corto");
    public final static Variable ONCO_PRESTACION_TTO = new Variable("Ora", "13995059", "99G2", new Long(13995059),
            "Quim.oral");

    public final static Variable CITA_PRESTACION_NUEVO = new Variable("Nue", "28636-9", "LN", new Long(1608), "Nuevo");

    public final static Variable CITA_PRESTACION_REVISION = new Variable("Rev", "11506-3", "LN", new Long(1609),
            "Revision");

    public final static Variable ALERGIAS = new Variable("106190000", "SNM3", new Long(13524547),
            "fecha de última menstruación");

    public final static Variable FECHA_ULTIMA_MENSTRUACION = new Variable("21840007", "SNM3", new Long(7747091),
            "fecha de última menstruación");

    public final static Variable FECHA_ULTIMA_MENSTRUACIONCORREGIDA = new Variable("13821806", "99G2",
            new Long(13821806), "fecha de última menstruación corregida");

    public final static Variable FECHA_ULTIMA_PROBALBEPARTO = new Variable("161714006", "SNM3", new Long(47311455),
            "fecha probable de parto");

    public final static Variable PARIDAD = new Variable("364325004", "SNM3", new Long(7747089), " Paridad");

    public final static Variable GRUPOABO = new Variable("882-1", "LN", new Long(1495), " GABO");

    public final static Variable VAR_ANTECEDENTESPERSONALES = new Variable("307294006", "SNM3", new Long(13524545),
            "Antecedentes personales ");

    public final static Variable VAR_DIAGNOSTICOPRIN = new Variable("", "", new Long(13524545),
            "Diagnóstico principal  ");
    public final static Variable VAR_TRATAMIENTO = new Variable("", "", new Long(13596253), "Tratamiento   ");

    public final static Variable VAR_NOTA_DE_REVISIONES = new Variable("11542-8", "LN", new Long(2234),
            "Nota de revisióN   ");
    public final static Variable VAR_RECOMENACIONES = new Variable("", "", new Long(767), "Recomendaciones   ");

    public final static Variable VAR_OBSERVACIONES = new Variable("10218", "99G2", new Long(10218),
            "Recomendaciones   ");

    public static ArrayList<Variable> variablesCovid = new ArrayList<Variable>();

    static {
        variablesCovid.add(new Variable("SCOV2ICG", "IgG frente a SARS-CoV-2 - Inmunocromatografía", "CoV19 IgG"));
        variablesCovid.add(new Variable("SCOV2ICT", "Ac totales frente a SARS-CoV-2 - Inmunocromatografía", "Cov19 Total"));
        variablesCovid.add(new Variable("SCOV2ICM", "IgM frente a SARS-CoV-2 - Inmunocromatografía", "Cov19 Igm"));
        variablesCovid.add(new Variable("COVID19G", "Anticuerpos anti Coronavirus COVID-19  IgG", "Cov19 IgG"));
        variablesCovid.add(new Variable("COVID19M", "Anticuerpos anti Coronavirus COVID-19  IgM", "Cov19 IgM"));
        variablesCovid.add(new Variable("SARSCOV2", "Detección RNA SARS-CoV-2 por PCR", "Cov19 PCr "));

        variablesCovid.add(new Variable("ACOVID19G", "Ac. SARS-CoV-2 IgG (ELISA)", "SARS-CoV-2 IgG (ELISA)"));
        variablesCovid.add(new Variable("ACOVID19M", "Ac. SARS-CoV-2 IgM (ELISA)", "Ac. SARS-CoV-2 IgM (ELISA)"));

    }
    public static ArrayList<Variable> procedimientosHdiaMed = new ArrayList<Variable>();

    static {
        procedimientosHdiaMed.add(new Variable('P', "3E033GC", "MINURIN"));
        procedimientosHdiaMed.add(new Variable('P', "3E033GC", "MITOXANTRONA"));

    }
    ;
     
    public static ArrayList<Variable> EPISODIO_MEDICAMENTOS_HIDA_CIE10 = new ArrayList<Variable>() {
        {
            add(new Variable());
            add(new Variable("P", "3E033GC", "Ácido Zoledrónico "));
            add(new Variable("P", "3E033GC", "Adalimumab (Humira®)"));
            add(new Variable("P", "3E033GC,Z51.89", "Albúmina"));
            add(new Variable("P", "3E033GC", "Alfa¹-antitripsina (Prolastina®)"));
            add(new Variable("P", "3E033GC", "Corticoides (Solu-moderín®)"));
            add(new Variable("P", "3E033GC", "Eculizumab (Soliris®)"));
            add(new Variable("P", "3E033GC", "Fingolimod (Gilenya®)"));
            add(new Variable("P", "3E033GC,E61.1", "Hierro- Carboximaltosa de  500mg (Ferinject®)"));
            add(new Variable("P", "3E033GC,E61.1", "Hierro- Sacarosa (Venofer®)"));
            add(new Variable("P", "3E033GC", "Infliximab (Remicade®)"));
            add(new Variable("P", "3E033GC,Z51.89", "Inmunoglobulinas"));
            add(new Variable("P", "3E033GC", "Mepolizumab (Nucala®)"));
            add(new Variable("P", "3E033GC", "Natalizumab (TYSABRI®)"));
            add(new Variable("P", "3E033GC", "Ocrelizumab (Ocrevus®)"));
            add(new Variable("P", "3E033GC", "Omalizumab (Xolair®)"));
            add(new Variable("P", "3E033GC", "Prostaglandinas"));
            add(new Variable("P", "3E033GC", "Reslizumab (CINQAERO®)"));
            add(new Variable("P", "3E033GC", "Rituximab"));
            add(new Variable("P", "3E033GC", "Romiplostim (Nplate®)"));
            add(new Variable("P", "3E033GC", "Tocilizumab (RoActemra®)"));
            add(new Variable("P", "3E033GC", "Ustekinumab (Stelara®)"));
            add(new Variable("P", "3E033GC", "Vendolizumab (Entyvio®)"));
        }
    };

    /**
     * Instantiates a new variable.
     */
    public Variable() {

    }

    /**
     * Instantiates a new variable.
     *
     * @param code the code
     * @param codesystem the codesystem
     * @param item the item
     * @param descripcion the descripcion
     */
    public Variable(String code, String codesystem, Long item, String descripcion) {
        this.code = code;
        this.codesystem = codesystem;
        this.item = item;
        this.descripcion = descripcion;
        this.valor = "";
    }

    public Variable(char tipo, String cie10, String descripcion) {
        this.diagnosticoprocedimiento = tipo;
        this.codigoCie10 = cie10;
        this.descripcion = descripcion;
    }

    public Variable(String codigo, String descripcion, String descripcioncorta) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.descripcionCorta = descripcioncorta;
    }

    public Variable(String descripcionCorta, String code, String codesystem, Long item, String descripcion) {
        this.code = code;
        this.codesystem = codesystem;
        this.item = item;
        this.descripcion = descripcion;
        this.descripcionCorta = descripcionCorta;
        this.valor = "";
    }

    public Variable(Long item, String descripcion) {

        this.item = item;
        this.descripcion = descripcion;

    }

    public Variable(String code, String codesystem, Long item, String descripcion, String textoAyuda) {
        this.code = code;
        this.codesystem = codesystem;
        this.item = item;
        this.descripcion = descripcion;
        this.textoAyuda = textoAyuda;
        this.valor = "";
    }

    public Variable(String code, String codesystem, Long item, String descripcion, String textoAyuda, String tipo) {
        this.code = code;
        this.codesystem = codesystem;
        this.item = item;
        this.descripcion = descripcion;
        this.textoAyuda = textoAyuda;
        this.tipoVariable = tipo;
        this.valor = "";
    }

    public Variable(String code, String codesystem, Long item, String descripcion, Double valorInferior,
            Double valorSuperior) {
        this.code = code;
        this.codesystem = codesystem;
        this.item = item;
        this.descripcion = descripcion;
        this.valor = "";
        this.valorInferior = valorInferior;
        this.valorSuperior = valorSuperior;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the codesystem.
     *
     * @return the codesystem
     */
    public String getCodesystem() {
        return codesystem;
    }

    /**
     * Sets the codesystem.
     *
     * @param codesystem the new codesystem
     */
    public void setCodesystem(String codesystem) {
        this.codesystem = codesystem;
    }

    /**
     * Gets the item.
     *
     * @return the item
     */
    public Long getItem() {
        return item;
    }

    /**
     * Sets the item.
     *
     * @param item the new item
     */
    public void setItem(Long item) {
        this.item = item;
    }

    public String getCodigo() {
        return codigo;
    }

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

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionCortaValor() {
        String cadena = "";
        if (this.descripcionCorta != null && !this.getDescripcionCorta().isEmpty()) {
            cadena = cadena.concat(this.getDescripcionCorta() + ":");
        }

        if (this.valor != null && !this.getValor().isEmpty()) {
            cadena = cadena.concat(this.getValor() + " ");
        }

        return cadena;
    }

    /**
     * Gets the valor.
     *
     * @return the valor
     */
    public String getValor() {
        if (valor != null) {
            return valor.trim();
        } else {
            return "";
        }
    }

    /**
     * Sets the valor.
     *
     * @param valor the new valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    public Double getValorInferior() {
        return valorInferior;
    }

    public void setValorInferior(Double valorInferior) {
        this.valorInferior = valorInferior;
    }

    public Double getValorSuperior() {
        return valorSuperior;
    }

    public void setValorSuperior(Double valorSuperior) {
        this.valorSuperior = valorSuperior;
    }

    public String getTextoAyuda() {
        return textoAyuda;
    }

    public void setTextoAyuda(String textoAyuda) {
        this.textoAyuda = textoAyuda;
    }

    public String getTipoVariable() {
        return tipoVariable;
    }

    public void setTipoVariable(String tipoVariable) {
        this.tipoVariable = tipoVariable;
    }

    public LocalDate getFechaValor() {
        return fechaValor;
    }

    public void setFechaValor(LocalDate fechaValor) {
        this.fechaValor = fechaValor;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public char getDiagnosticoprocedimiento() {
        return diagnosticoprocedimiento;
    }

    public void setDiagnosticoprocedimiento(char diagnosticoprocedimiento) {
        this.diagnosticoprocedimiento = diagnosticoprocedimiento;
    }

    public String getCodigoCie10() {
        return codigoCie10;
    }

    public void setCodigoCie10(String codigoCie10) {
        this.codigoCie10 = codigoCie10;
    }

    public static ArrayList<Variable> getProcedimientosHdiaMed() {
        return procedimientosHdiaMed;
    }

    public static void setProcedimientosHdiaMed(ArrayList<Variable> procedimientosHdiaMed) {
        Variable.procedimientosHdiaMed = procedimientosHdiaMed;
    }

    @Override
    public String toString() {
        return "Variable{" + "code=" + code + ", codesystem=" + codesystem + ", item=" + item + ", codigo=" + codigo + ", descripcion=" + descripcion + ", descripcionCorta=" + descripcionCorta + ", unidades=" + unidades + ", valor=" + valor + ", valorInferior=" + valorInferior + ", valorSuperior=" + valorSuperior + ", textoAyuda=" + textoAyuda + ", tipoVariable=" + tipoVariable + ", fechaValor=" + fechaValor + ", diagnosticoprocedimiento=" + diagnosticoprocedimiento + ", codigoCie10=" + codigoCie10 + '}';
    }

    public String getDescripyDato() {
        String resultadoString = "";
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        if (this.getTipoVariable() != null) {
            if (this.getValor() != null && !this.getValor().isEmpty() && !this.getValor().equals("")) {
                switch (this.getTipoVariable()) {
                    case TIPO_VARIABLE_STRING:
                        resultadoString = this.getDescripcion() + ":" + this.getValor();
                        break;
                    case TIPO_VARIABLE_CHECK:
                        resultadoString = this.getDescripcion() + ":ok";
                        break;
                    case TIPO_VARIABLE_DATE:
                        resultadoString = this.getDescripcion() + ":"
                                + fechadma.format(Utilidades.getFechaLocalDate(this.getValor()));
                        break;
                    default:
                        resultadoString = this.getDescripcion() + ":" + this.getValor();
                        break;
                }
            }
        } else {
            if (this.getValor() != null && !this.getValor().isEmpty() && !this.getValor().equals("")) {
                resultadoString = this.getDescripcion() + ":" + this.getValor();
            }
        }
        return resultadoString;
    }

    public String getDatoTipoFormato() {
        String resultadoString = "";
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        if (this.getTipoVariable() != null) {
            if (this.getValor() != null && !this.getValor().isEmpty() && !this.getValor().equals("")) {
                switch (this.getTipoVariable()) {
                    case TIPO_VARIABLE_STRING:
                        resultadoString = this.getValor();
                        break;
                    case TIPO_VARIABLE_CHECK:
                        resultadoString = "ok";
                        break;
                    case TIPO_VARIABLE_DATE:
                        resultadoString = fechadma.format(Utilidades.getFechaLocalDate(this.getValor()));
                        break;
                    default:
                        resultadoString = this.getValor();
                        break;
                }
            }
        } else {
            if (this.getValor() != null && !this.getValor().isEmpty() && !this.getValor().equals("")) {
                resultadoString = this.getValor();
            }
        }
        return resultadoString;
    }
}
