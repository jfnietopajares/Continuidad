package es.sacyl.hnss.entity;

import java.sql.Blob;

public class Categoria {

    private Long id;
    private String codigo;
    private String grupo;
    private String categoria;
    private Long seguridad;
    private Blob funcionBlob;

    public static Categoria MEDICO = new Categoria(new Long(1), "MEDICO");
    public static Categoria MEDICOESPECIALISTA = new Categoria(new Long(47), "MEDICO ESPECIALISTA");
    public static Categoria MATRONA = new Categoria(new Long(14), "MATRONA");
    public static Categoria AUXILIARSANITARIO = new Categoria(new Long(65), "AUXILIAR DE ENFERMERIA");
    public static Categoria ENFERMERARESIDENTE = new Categoria(new Long(108), "ENFERMERA RESIDENTE");

    public Categoria(Long id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Long seguridad) {
        this.seguridad = seguridad;
    }

    public Blob getFuncionBlob() {
        return funcionBlob;
    }

    public void setFuncionBlob(Blob funcionBlob) {
        this.funcionBlob = funcionBlob;
    }

}
