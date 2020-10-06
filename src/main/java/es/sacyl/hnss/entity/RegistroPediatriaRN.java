package es.sacyl.hnss.entity;

public class RegistroPediatriaRN extends RegistroEnfConstantes {

    public final static Long PLANTILLLA_EDITOR = new Long(189959305);
    public final static Long TIPO_REGISTRO_ENF_CTES = new Long(18);

    public RegistroPediatriaRN() {
        super();
    }

    public RegistroPediatriaRN(Long id) {
        super(id);
    }

    public RegistroPediatriaRN(RegistroPediatriaRN r) {
        super(r);
    }

    @Override
    public void iniciaEnfCtesn() {
        super.iniciaEnfCtesn();
        this.setTiporegistro(RegistroEnfConstantes.TIPO_REGISTRO_ENF_CTES);
        this.setDescripcion("Registro enf constantes");
        this.setPlantilla_edior(RegistroEnfConstantes.PLANTILLLA_EDITOR_ENF_CTES);
    }

}
