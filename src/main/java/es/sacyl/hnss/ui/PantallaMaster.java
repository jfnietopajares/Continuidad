/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

/**
 *
 * @author 06551256M
 */
public abstract class PantallaMaster extends VerticalLayout {

    private VerticalLayout contenedorGrid = new VerticalLayout();

    private HorizontalLayout contenedorFiltro = new HorizontalLayout();

    protected H3 titulo = new H3();

    protected TextField textoABuscar = ObjetosComunes.getTextField(null, "contenido a buscar", null, "200px");

    protected Button botonBuscar = ObjetosComunes.getBoton("Buscar", null, VaadinIcon.SEARCH.create());

    protected Button botonAnadir = ObjetosComunes.getBoton("Nuevo", null, VaadinIcon.PLUS_CIRCLE.create());

    protected Button botonListar = ObjetosComunes.getBoton("Listar", null, VaadinIcon.PRINT.create());

    protected Text cabeceraGrid = new Text(" ");

    protected Text numeroRegistros = new Text("");

    public PantallaMaster() {

        this.add(titulo);

        this.add(contenedorGrid);

        textoABuscar.setClearButtonVisible(true);
        textoABuscar.setValueChangeMode(ValueChangeMode.EAGER);
        textoABuscar.addValueChangeListener(event -> {
            doActualizaGrid();
        });
        contenedorGrid.add(contenedorFiltro);

        getContenedorFiltro().add(textoABuscar, botonBuscar, botonAnadir, botonListar);

        getContenedorGrid().add(cabeceraGrid);

        botonAnadir.addClickListener(event
                -> doNuevo()
        );
    }

    public VerticalLayout getContenedorGrid() {
        return contenedorGrid;
    }

    public void setContenedorGrid(VerticalLayout contenedorGrid) {
        this.contenedorGrid = contenedorGrid;
    }

    public HorizontalLayout getContenedorFiltro() {
        return contenedorFiltro;
    }

    public void setContenedorFiltro(HorizontalLayout contenedorFiltro) {
        this.contenedorFiltro = contenedorFiltro;
    }

    public Text getCabeceraGrid() {
        return cabeceraGrid;
    }

    public void setCabeceraGrid(Text cabeceraGrid) {
        this.cabeceraGrid = cabeceraGrid;
    }

    public abstract void doBuscar();

    public abstract void doNuevo();

    public abstract void doListar();

    public abstract void doActualizaGrid();

}
