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
import es.sacyl.hnss.dao.CitosMedicamentosDAO;
import es.sacyl.hnss.entidades.CitosDiluciones;
import es.sacyl.hnss.entidades.CitosMedicamentos;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PntCitosMedicamentos extends PantallaMaster {

       
      private static final long serialVersionUID = 1L;

    private CitosMedicamentos citosMedicamentos = new CitosMedicamentos();

    private ArrayList<CitosMedicamentos> listaMedicamentos = new ArrayList<>();

    private final Grid<CitosMedicamentos> grid;

       public PntCitosMedicamentos() {
        super();
        this.grid = new Grid<>(CitosMedicamentos.class);
        doHazPantalla();
    }

     public void doHazPantalla() {
        titulo.setText(CitosMedicamentos.getLabelFrom());

        getContenedorGrid().add(grid);

        textoABuscar.focus();
        textoABuscar.setValueChangeMode(ValueChangeMode.EAGER);
        textoABuscar.addValueChangeListener(event -> {
            doActualizaGrid();
            if (listaMedicamentos.size() == 1) {
                doVentanaModal(new FrmCitosMedicamentos(listaMedicamentos.get(0)));
            }
        });
        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);


        grid.setColumns("id", "comercial");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            doVentanaModal(new FrmCitosMedicamentos(event.getItem()));
            doActualizaGrid();
        }
        );
        doActualizaGrid();
    }
    @Override
    public void doBuscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doNuevo() {
         doVentanaModal(new FrmCitosMedicamentos());
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
     @Override
    public void doActualizaGrid() {

        listaMedicamentos = new CitosMedicamentosDAO().getListaMedicamentos(textoABuscar.getValue());

        grid.setItems(listaMedicamentos);

        numeroRegistros.setText(":" + Integer.toString(listaMedicamentos.size()));

        cabeceraGrid.setText(" Lista de " + CitosDiluciones.getLabelFrom() + ". Registros: " + Integer.toString(listaMedicamentos.size()));

    }
    
}
