package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.ConstantesClinicas;



public class RegistroOncoEvolutivo extends Registro {

    private Variable observaciones;

    public final static Long PLANTILLLA_EDITOR_ONCO_EVOLUTI = new Long(333166622);
    public final static Long TIPO_REGISTRO_ONCO = new Long(6);

    public final Variable VAR_ONCO_EVOL_OBSER = new ConstantesClinicas().VAR_OBSERVACIONES;

    public RegistroOncoEvolutivo() {
        this.iniciaOncoEvolutivo();
    }

    public RegistroOncoEvolutivo(Long id) {
        super(id);
        this.iniciaOncoEvolutivo();
    }

    public RegistroOncoEvolutivo(RegistroOncoEvolutivo r) {
        super(r);
        this.observaciones = r.getObservaciones();
    }

    public void iniciaOncoEvolutivo() {
        this.setTiporegistro(TIPO_REGISTRO_ONCO);
        this.setDescripcion("Registro evolutivo de enfermería");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_ONCO_EVOLUTI);
        this.setServicio(Servicio.SERVICIO_ONCOLOGIA);

        this.observaciones = VAR_ONCO_EVOL_OBSER;

    }

    public Variable getObservaciones() {
        return observaciones;
    }

    public Variable getVariableObservaciones() {
        return observaciones;
    }

    public String getObservacionesString() {
        return observaciones.getValor();
    }

    public void setObservaciones(Variable observaciones) {
        this.observaciones = observaciones;
    }

    public void setObservaciones(String valor) {
        this.observaciones.setValor(valor);
        ;
    }

    public String getContenidoHtml() {
        String texto = "";
        texto = texto.concat("<b>" + this.getUserid().getApellidosNombre() + "</b>");
        texto = texto.concat("&nbsp;&nbsp;" + this.getFechaHora() + "<br>");
        texto = texto.concat(this.getObservacionesString() + "<hr>");
        return texto;
    }
}
