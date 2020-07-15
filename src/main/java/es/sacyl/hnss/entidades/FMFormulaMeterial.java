/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.entidades;

/**
 *
 * @author JuanNieto
 */
public class FMFormulaMeterial {

    private Integer formula;
    private Integer linea;
    private String comentario;
    private FMInstrumento instrumento;
    private Integer unidades;

    public static final String labelFrom = "Material para la elaboración de la fórmula ";

    public FMFormulaMeterial() {

    }

    public Integer getFormula() {
        return formula;
    }

    public void setFormula(Integer formula) {
        this.formula = formula;
    }

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public FMInstrumento getInstrumento() {
        return instrumento;
    }

    public String getInstrumentoNombre() {
        if (instrumento != null) {
            return instrumento.getNombre();
        } else {
            return "";

        }
    }

    public void setInstrumento(FMInstrumento instrumento) {
        this.instrumento = instrumento;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public static String getLabelFrom() {
        return labelFrom;
    }

}
