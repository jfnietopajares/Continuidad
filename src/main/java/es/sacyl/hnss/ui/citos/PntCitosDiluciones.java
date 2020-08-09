/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.citos;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import es.sacyl.hnss.dao.CitosDilucionesDAO;
import es.sacyl.hnss.dao.FMFormaDAO;
import es.sacyl.hnss.entidades.CitosDiluciones;
import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import es.sacyl.hnss.ui.formulas.FrmFMFormas;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PntCitosDiluciones extends PantallaMaster {
    
      private static final long serialVersionUID = 1L;

    private CitosDiluciones citosDiluciones = new CitosDiluciones();

    private ArrayList<CitosDiluciones> listaDiluciones = new ArrayList<>();

    private final Grid<CitosDiluciones> grid;

       public PntCitosDiluciones() {
        super();
        this.grid = new Grid<>(CitosDiluciones.class);
        doHazPantalla();
    }

     public void doHazPantalla() {
        titulo.setText(CitosDiluciones.getLabelFrom());

        getContenedorGrid().add(grid);

        textoABuscar.focus();
        textoABuscar.setValueChangeMode(ValueChangeMode.EAGER);
        textoABuscar.addValueChangeListener(event -> {
            doActualizaGrid();
            if (listaDiluciones.size() == 1) {
                doVentanaModal(new FrmCitosDiluciones(listaDiluciones.get(0)));
            }
        });
        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);


        grid.setColumns("id", "base","concentracion","presentacion");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            doVentanaModal(new FrmCitosDiluciones(event.getItem()));
            doActualizaGrid();
        }
        );
        doActualizaGrid();
    }

    @Override
    public void doBuscar() {
doActualizaGrid();
    }

    @Override
    public void doNuevo() {
        doVentanaModal(new FrmCitosDiluciones());
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public void doActualizaGrid() {

        listaDiluciones = new CitosDilucionesDAO().getListaDiluciones(textoABuscar.getValue());

        grid.setItems(listaDiluciones);

        numeroRegistros.setText(":" + Integer.toString(listaDiluciones.size()));

        cabeceraGrid.setText(" Lista de " + CitosDiluciones.getLabelFrom() + ". Registros: " + Integer.toString(listaDiluciones.size()));

    }
    
}
