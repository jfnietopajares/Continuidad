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
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.dao.FMInstrumentosDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PntFMFormulas extends PantallaMaster {

    private FMFormula farmaFMFormulas = new FMFormula();

    private Grid<FMFormula> grid = new Grid<>(FMFormula.class);

    private FrmFMFormulas frmFMFormulas;

    ArrayList<FMFormula> listaFormulas = new ArrayList<>();

    public PntFMFormulas() {
        super();
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FMFormula.getLabelFrom());

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
            frmFMFormulas = new FrmFMFormulas(event.getItem());
            doVentanaModal(frmFMFormulas);
        }
        );

        doActualizaGrid();
    }

    public void doVentanaModal(FrmFMFormulas frmFMFormulas) {
        frmFMFormulas.open();
        frmFMFormulas.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFMFormulas.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        doActualizaGrid();
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFMFormulas());
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {
        listaFormulas = new FMFormulasDAO().getListaFormulas(textoABuscar.getValue());

        grid.setItems(listaFormulas);

        numeroRegistros.setText(":" + Integer.toString(listaFormulas.size()));

        cabeceraGrid.setText(" Lista de " + farmaFMFormulas.getLabelFrom() + ". Registros: " + Integer.toString(listaFormulas.size()));

    }

}
