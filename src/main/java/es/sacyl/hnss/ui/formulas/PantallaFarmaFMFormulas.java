/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import es.sacyl.hnss.dao.FarmaFMFormulasDAO;
import es.sacyl.hnss.dao.FarmaFMInstrumentosDAO;
import es.sacyl.hnss.entidades.FarmaFMFormula;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PantallaFarmaFMFormulas extends PantallaMaster {

    private FarmaFMFormula farmaFMFormulas = new FarmaFMFormula();

    private Grid<FarmaFMFormula> grid = new Grid<>(FarmaFMFormula.class);

    private FrmFarmaFMFormulas frmFarmaFMFormulas;

    ArrayList<FarmaFMFormula> listaFormulas = new ArrayList<>();

    public PantallaFarmaFMFormulas() {
        super();
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FarmaFMFormula.getLabelFrom());

        getContenedorGrid().add(grid);

        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        grid.setColumns("numero", "nombre");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            frmFarmaFMFormulas = new FrmFarmaFMFormulas(event.getItem());
            doVentanaModal(frmFarmaFMFormulas);
        }
        );

        doActualizaGrid();
    }

    public void doVentanaModal(FrmFarmaFMFormulas frmFarmaFMFormulas) {
        frmFarmaFMFormulas.open();
        frmFarmaFMFormulas.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFarmaFMFormulas.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        doActualizaGrid();
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFarmaFMFormulas());
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {
        listaFormulas = new FarmaFMFormulasDAO().getListaFormulas(textoABuscar.getValue());

        grid.setItems(listaFormulas);

        numeroRegistros.setText(":" + Integer.toString(listaFormulas.size()));

        cabeceraGrid.setText(" Lista de " + farmaFMFormulas.getLabelFrom() + ". Registros: " + Integer.toString(listaFormulas.size()));

    }

}
