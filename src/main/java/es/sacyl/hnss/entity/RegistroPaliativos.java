package es.sacyl.hnss.entity;

/**
 * The Class RegistroPaliativos. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroPaliativos extends Registro {

    public final static Long PLANTILLLA_EDITOR_REGISTROMAMA = new Long(9999999);
    public final static Long TIPO_REGISTRO = new Long(65);

    public RegistroPaliativos() {
    }

    public RegistroPaliativos(Long id) {
        super(id);
    }

    public RegistroPaliativos(Registro r) {
        super(r);
    }

}
