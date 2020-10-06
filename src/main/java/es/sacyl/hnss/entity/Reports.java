package es.sacyl.hnss.entity;

import java.util.ArrayList;

/**
 * The Class Reports. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Reports {

    private int id;

    private String descripcion;

    private Funcionalidad funcionalidad;

    public static ArrayList<Reports> LISTAREPORTS = new ArrayList<Reports>() {
        /**
         *
         */
        private static final long serialVersionUID = -1688296669003213538L;

        {
            add(new Reports(1, "Mama: listado de  casos ", new Funcionalidad(Funcionalidad.MENU_MAMA_FUNCIONALIDAD)));
            add(new Reports(2, "Colon: Listado de casos  ", new Funcionalidad(Funcionalidad.MENU_COLON_FUNCIONALIDAD)));
            add(new Reports(3, "Colon: Excel TSOH   ", new Funcionalidad(Funcionalidad.MENU_COLON_FUNCIONALIDAD)));

            add(new Reports(10, "Paliativos: Listado de casos.",
                    new Funcionalidad(Funcionalidad.MENU_PALIATIVOS_FUNCIONALIDAD)));

            add(new Reports(20, "Oxigeno : Mail .", new Funcionalidad(Funcionalidad.MENU_OXIGENO_FUNCIONALIDAD)));
            add(new Reports(21, "Oxigeno : Listado de casos .",
                    new Funcionalidad(Funcionalidad.MENU_OXIGENO_FUNCIONALIDAD)));
        }
    };

    /**
     * Instantiates a new reports.
     */
    public Reports() {

    }

    /**
     * Instantiates a new reports.
     *
     * @param id the id
     */
    public Reports(int id) {
        this.id = id;
    }

    /**
     * Instantiates a new reports.
     *
     * @param id the id
     * @param desc the desc
     * @param fun the fun
     */
    public Reports(int id, String desc, Funcionalidad fun) {
        this.id = id;
        this.descripcion = desc;
        this.funcionalidad = fun;
    }

    /**
     * Gets the reports user.
     *
     * @param usuario the usuario
     * @return the reports user
     */
    public ArrayList<Reports> getReportsUser(Usuario usuario) {
        ArrayList<Reports> lista = new ArrayList<>();
        ArrayList<Funcionalidad> funcionalisadesUsu = usuario.getFuncionaliades();
        for (Reports repo : Reports.LISTAREPORTS) {
            Reports reports = new Reports();
            reports = repo;
            for (Funcionalidad f : funcionalisadesUsu) {
                if (reports.getFuncionalidad().getId().equals(f.getId())) {
                    lista.add(reports);
                }
            }
        }
        return lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getDes() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Funcionalidad getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(Funcionalidad funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "id:" + getId() + "\n Descripcion:" + getDescripcion() + "\n Funcionalidad: "
                + getFuncionalidad().toString();

    }

}
