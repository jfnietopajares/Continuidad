package es.sacyl.hnss.entity;

import java.util.ArrayList;

public class Perfil {

    private Long id;
    private String descripcion;

    public static ArrayList<Perfil> MEDICOS = new ArrayList<Perfil>() {
        /**
         *
         */
        private static final long serialVersionUID = -1736922106333323443L;

        {
            add(new Perfil(new Long(1), "MEDICO ESPECIALIZADA"));
            add(new Perfil(new Long(13), "MEDICO PRIMARAI"));
        }

    };

    public static ArrayList<Perfil> ENFERMERAS = new ArrayList<Perfil>() {
        /**
         *
         */
        private static final long serialVersionUID = 411425279620227128L;

        {
            add(new Perfil(new Long(3), "ENFERMERA CONSULTAS"));
            add(new Perfil(new Long(8), "ENFERMERA CORRETURNOS"));
            add(new Perfil(new Long(15), "ENFERMERA DE DIALISIS"));
            add(new Perfil(new Long(14), "ENFERMERA DE PRIMARIA"));
            add(new Perfil(new Long(5), "ENFERMERA HOSPITALIZACIÓN"));
            add(new Perfil(new Long(9), "ENFERMERA LAB / HEM"));
            add(new Perfil(new Long(6), "ENFERMERA QUIRÓFANO"));
            add(new Perfil(new Long(4), "ENFERMERA URGENCIAS"));

        }

    };

    public Perfil() {
        id = new Long(0);
    }

    public Perfil(Long id) {
        this.id = id;
    }

    public Perfil(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getPertenecePerfil(Usuario usuario, ArrayList<Perfil> perfiltipo) {
        boolean pertenece = false;
        if (usuario == null || perfiltipo == null) {
            return false;
        }
        for (Perfil perfil : perfiltipo) {
            if (perfil.equals(usuario.getPerfil())) {
                pertenece = true;
                break;
            }
        }
        return pertenece;
    }

}
