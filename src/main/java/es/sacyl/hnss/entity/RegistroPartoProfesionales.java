package es.sacyl.hnss.entity;

import es.sacyl.hnss.dao.UsuarioDAO;



public class RegistroPartoProfesionales extends Registro {

    private Variable ginecologo;
    private Variable anestesista;
    private Variable pediatra;
    private Variable matrona;
    private Variable tcae;
    private Variable eir;

    private Variable ginecologo2;
    private Variable anestesista2;
    private Variable pediatra2;
    private Variable matrona2;
    private Variable tcae2;
    private Variable eir2;

    public final Variable VAR_PARTO_PROFESIO_GINE = new Variable("309367003", "SNM3", new Long(13994671), "Ginecólogo");
    public final Variable VAR_PARTO_PROFESIO_GINE2 = new Variable("13995257", "99G2", new Long(13995257),
            "Ginecólogo 2");
    public final Variable VAR_PARTO_PROFESIO_ANEST = new Variable("88189002", "SNM3", new Long(13819464),
            "Anestesista");
    public final Variable VAR_PARTO_PROFESIO_ANEST2 = new Variable("13995258", "99G2", new Long(13995258),
            "Anestesista 2");
    public final Variable VAR_PARTO_PROFESIO_PEDIA = new Variable("82296001", "SNM3", new Long(35004305), "Pediatra");
    public final Variable VAR_PARTO_PROFESIO_PEDIA2 = new Variable("13995259", "99G2", new Long(13995259),
            "Pediatra 2");

    public final Variable VAR_PARTO_PROFESIO_MATRONA = new Variable("158941003", "SNM3", new Long(35004306), "Matrona");
    public final Variable VAR_PARTO_PROFESIO_MATRONA2 = new Variable("13995260", "99G2", new Long(13995260),
            "Matrona 2");

    public final Variable VAR_PARTO_PROFESIO_TCAE = new Variable("13994691", "99G2", new Long(13994691), "TCAE");
    public final Variable VAR_PARTO_PROFESIO_TCAE2 = new Variable("13995261", "99G2", new Long(13995261), "TCAE 2");

    public final Variable VAR_PARTO_PROFESIO_EIR = new Variable("35004308", "99G2", new Long(35004308), "EIR");
    public final Variable VAR_PARTO_PROFESIO_EIR2 = new Variable("13995262", "99G2", new Long(13995262), "EIR 2");

    public final static Long PLANTILLLA_EDITOR_PAR_PROFESIONALES = new Long(794880262);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public RegistroPartoProfesionales() {
        super();
        iniciaProfesionales();
    }

    public RegistroPartoProfesionales(Long id) {
        super(id);
        iniciaProfesionales();
    }

    public RegistroPartoProfesionales(RegistroPartoProfesionales r) {
        super(r);
        this.ginecologo = r.getGinecologo();
        this.anestesista = r.getAnestesista();
        this.pediatra = r.pediatra;
        this.matrona = r.getMatrona();
        this.tcae = r.getTcae();
        this.eir = r.getEir();

        this.ginecologo2 = r.getGinecologo2();
        this.anestesista2 = r.getAnestesista2();
        this.pediatra2 = r.pediatra2;
        this.matrona2 = r.getMatrona2();
        this.tcae2 = r.getTcae2();
        this.eir2 = r.getEir2();

    }

    public void iniciaProfesionales() {
        this.plantilla_editor = PLANTILLLA_EDITOR_PAR_PROFESIONALES;
        this.tiporegistro = TIPO_REGISTRO_PARTO;
        this.setDescripcion("10.Profesionales");
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));
        this.ginecologo = VAR_PARTO_PROFESIO_GINE;
        this.anestesista = VAR_PARTO_PROFESIO_ANEST;
        this.pediatra = VAR_PARTO_PROFESIO_PEDIA;
        this.matrona = VAR_PARTO_PROFESIO_MATRONA;
        this.tcae = VAR_PARTO_PROFESIO_TCAE;
        this.eir = VAR_PARTO_PROFESIO_EIR;

        this.ginecologo2 = VAR_PARTO_PROFESIO_GINE2;
        this.anestesista2 = VAR_PARTO_PROFESIO_ANEST2;
        this.pediatra2 = VAR_PARTO_PROFESIO_PEDIA2;
        this.matrona2 = VAR_PARTO_PROFESIO_MATRONA2;
        this.tcae2 = VAR_PARTO_PROFESIO_TCAE2;
        this.eir2 = VAR_PARTO_PROFESIO_EIR2;

    }

    public Variable getGinecologo() {
        return ginecologo;
    }

    public Variable getVariableGinecologo() {
        return ginecologo;
    }

    public String getGinecologoString() {
        return ginecologo.getValor();
    }

    public Usuario getGinecologoUsuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(ginecologo.getCodigo(), false);
        if (usu != null) {
            ginecologo.setCodigo(usu.getUserid());
            ginecologo.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setGinecologo(Variable ginecologo) {
        this.ginecologo = ginecologo;
    }

    public void setGinecologo(String valor) {
        this.ginecologo.setValor(valor);
    }

    public void setGinecologo(Usuario usu) {
        if (usu != null) {
            ginecologo.setCodigo(usu.getUserid());
            ginecologo.setValor(usu.getApellidosNombre());
        } else {
            ginecologo.setCodigo("");
            ginecologo.setValor("");
        }
    }

    public Variable getAnestesista() {
        return anestesista;
    }

    public Variable getVariableAnestesista() {
        return anestesista;
    }

    public String getAnestesistaString() {
        return anestesista.getValor();
    }

    public Usuario getAnestesistaUsuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(anestesista.getCodigo(), false);
        if (usu != null) {
            anestesista.setCodigo(usu.getUserid());
            anestesista.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setAnestesista(Variable anestesista) {
        this.anestesista = anestesista;
    }

    public void setAnestesista(String valor) {
        this.anestesista.setValor(valor);
    }

    public void setAnestesista(Usuario usu) {
        if (usu != null) {
            anestesista.setCodigo(usu.getUserid());
            anestesista.setValor(usu.getApellidosNombre());
        } else {
            anestesista.setCodigo("");
            anestesista.setValor("");
        }
    }

    public Variable getPediatra() {
        return pediatra;
    }

    public Variable getVariablePediatra() {
        return pediatra;
    }

    public Usuario getPediatrauUsuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(anestesista.getCodigo(), false);
        if (usu != null) {
            pediatra.setCodigo(usu.getUserid());
            pediatra.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public String getPediatraString() {
        return pediatra.getValor();
    }

    public void setPediatra(Variable pediatra) {
        this.pediatra = pediatra;
    }

    public void setPediatra(String valor) {
        this.pediatra.setValor(valor);
    }

    public void setPediatra(Usuario usu) {
        if (usu != null) {
            pediatra.setCodigo(usu.getUserid());
            pediatra.setValor(usu.getApellidosNombre());
        } else {
            pediatra.setCodigo("");
            pediatra.setValor("");

        }
    }

    public Variable getMatrona() {
        return matrona;
    }

    public Variable getVariableMatrona() {
        return matrona;
    }

    public String getMatronaString() {
        return matrona.getValor();
    }

    public Usuario getMatronaUsuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(matrona.getCodigo(), false);
        if (usu != null) {
            matrona.setCodigo(usu.getUserid());
            matrona.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setMatrona(Variable matrona) {
        this.matrona = matrona;
    }

    public void setMatrona(String valor) {
        this.matrona.setValor(valor);
    }

    public void setMatrona(Usuario usu) {
        if (usu != null) {
            matrona.setCodigo(usu.getUserid());
            matrona.setValor(usu.getApellidosNombre());
        } else {
            matrona.setCodigo("");
            matrona.setValor("");

        }
    }

    public Variable getTcae() {
        return tcae;
    }

    public Variable getVariableTcae() {
        return tcae;
    }

    public String getTcaeString() {
        return tcae.getValor();
    }

    public Usuario getTcaeUsuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(tcae.getCodigo(), false);
        if (usu != null) {
            tcae.setCodigo(usu.getUserid());
            tcae.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setTcae(Variable tcae) {
        this.tcae = tcae;
    }

    public void setTcae(String valor) {
        this.tcae.setValor(valor);
    }

    public void setTcae(Usuario usu) {
        if (usu != null) {
            tcae.setCodigo(usu.getUserid());
            tcae.setValor(usu.getApellidosNombre());
        } else {
            tcae.setCodigo("");
            tcae.setValor("");
        }
    }

    public Variable getEir() {
        return eir;
    }

    public Variable getVariableEir() {
        return eir;
    }

    public String getEirString() {
        return eir.getValor();
    }

    public Usuario getEirUsuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(eir.getCodigo(), false);
        if (usu != null) {
            eir.setCodigo(usu.getUserid());
            eir.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setEir(Variable eir) {
        this.eir = eir;
    }

    public void setEir(String valor) {
        this.eir.setValor(valor);
        ;
    }

    public void setEir(Usuario usu) {
        if (usu != null) {
            eir.setCodigo(usu.getUserid());
            eir.setValor(usu.getApellidosNombre());
        } else {
            eir.setCodigo("");
            eir.setValor("");
        }
    }

    public Variable getGinecologo2() {
        return ginecologo2;
    }

    public Variable getVariableGinecologo2() {
        return ginecologo2;
    }

    public String getGinecologo2String() {
        return ginecologo2.getValor();
    }

    public Usuario getGinecologo2Usuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(ginecologo2.getCodigo(), false);
        if (usu != null) {
            ginecologo2.setCodigo(usu.getUserid());
            ginecologo2.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setGinecologo2(Variable ginecologo2) {
        this.ginecologo2 = ginecologo2;
    }

    public void setGinecologo2(Usuario usu) {
        if (usu != null) {
            ginecologo2.setCodigo(usu.getUserid());
            ginecologo2.setValor(usu.getApellidosNombre());
        } else {
            ginecologo2.setCodigo("");
            ginecologo2.setValor("");
        }
    }

    public void setGinecologo2(String valor) {
        this.ginecologo2.setValor(valor);
    }

    public Variable getAnestesista2() {
        return anestesista2;
    }

    public Variable getVariableAnestesista2() {
        return anestesista2;
    }

    public String getAnestesista2String() {
        return anestesista2.getValor();
    }

    public Usuario getAnestesista2Usuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(anestesista2.getCodigo(), false);
        if (usu != null) {
            anestesista2.setCodigo(usu.getUserid());
            anestesista2.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setAnestesista2(Variable anestesista2) {
        this.anestesista2 = anestesista2;
    }

    public void setAnestesista2(String valor) {
        this.anestesista2.setValor(valor);
    }

    public void setAnestesista2(Usuario usu) {
        if (usu != null) {
            anestesista2.setCodigo(usu.getUserid());
            anestesista2.setValor(usu.getApellidosNombre());
        } else {
            anestesista2.setCodigo("");
            anestesista2.setValor("");

        }
    }

    public Variable getPediatra2() {
        return pediatra2;
    }

    public Variable getVariablePediatra2() {
        return pediatra2;
    }

    public String getPediatra2String() {
        return pediatra2.getValor();
    }

    public Usuario getPediatra2Usuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(anestesista2.getCodigo(), false);
        if (usu != null) {
            pediatra2.setCodigo(usu.getUserid());
            pediatra2.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setPediatra2(Variable pediatra2) {
        this.pediatra2 = pediatra2;
    }

    public void setPediatra2(String valor) {
        this.pediatra2.setValor(valor);
    }

    public void setPediatra2(Usuario usu) {
        if (usu != null) {
            pediatra2.setCodigo(usu.getUserid());
            pediatra2.setValor(usu.getApellidosNombre());
        } else {
            pediatra2.setCodigo("");
            pediatra2.setValor("");

        }
    }

    public Variable getMatrona2() {
        return matrona2;
    }

    public Variable getVariableMatrona2() {
        return matrona2;
    }

    public String getMatrona2String() {
        return matrona2.getValor();
    }

    public Usuario getMatrona2Usuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(matrona2.getCodigo(), false);
        if (usu != null) {
            matrona2.setCodigo(usu.getUserid());
            matrona2.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setMatrona2(Variable matrona2) {
        this.matrona2 = matrona2;
    }

    public void setMatrona2(String valor) {
        this.matrona2.setValor(valor);
    }

    public void setMatrona2(Usuario usu) {
        if (usu != null) {
            matrona2.setCodigo(usu.getUserid());
            matrona2.setValor(usu.getApellidosNombre());
        } else {
            matrona2.setCodigo("");
            matrona2.setValor("");
        }
    }

    public Variable getTcae2() {
        return tcae2;
    }

    public Variable getVariableTcae2() {
        return tcae2;
    }

    public String getTcae2String() {
        return tcae2.getValor();
    }

    public Usuario getTcae2Usuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(tcae2.getCodigo(), false);
        if (usu != null) {
            tcae2.setCodigo(usu.getUserid());
            tcae2.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setTcae2(Variable tcae2) {
        this.tcae2 = tcae2;
    }

    public void setTcae2(String valor) {
        this.tcae2.setValor(valor);
    }

    public void setTcae2(Usuario usu) {
        if (usu != null) {
            tcae2.setCodigo(usu.getUserid());
            tcae2.setValor(usu.getApellidosNombre());
        } else {
            tcae2.setCodigo("");
            tcae2.setValor("");

        }
    }

    public Variable getEir2() {
        return eir2;
    }

    public Variable getVariableEir2() {
        return eir2;
    }

    public String getEir2String() {
        return eir2.getValor();
    }

    public Usuario getEir2Usuario() {
        Usuario usu = new UsuarioDAO().getUsuarioUserid(eir2.getCodigo(), false);
        if (usu != null) {
            eir2.setCodigo(usu.getUserid());
            eir2.setValor(usu.getApellidosNombre());
        }
        return usu;
    }

    public void setEir2(Variable eir2) {
        this.eir2 = eir2;
    }

    public void setEir2(String valor) {
        this.eir2.setValor(valor);
    }

    public void setEir2(Usuario usu) {
        if (usu != null) {
            eir2.setCodigo(usu.getUserid());
            eir2.setValor(usu.getApellidosNombre());
        } else {
            eir2.setCodigo("");
            eir2.setValor("");

        }
    }

}
