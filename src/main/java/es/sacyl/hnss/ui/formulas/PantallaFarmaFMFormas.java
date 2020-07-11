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
import es.sacyl.hnss.entidades.FarmaFMForma;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PantallaFarmaFMFormas extends PantallaMaster {

    private FarmaFMForma farmaFMForma = new FarmaFMForma();

    private ArrayList<FarmaFMForma> listaFormas = new ArrayList<>();

    private Grid<FarmaFMForma> grid = new Grid<>(FarmaFMForma.class);

    private FrmFarmaFMForma frmFarmaFMForma;

    public PantallaFarmaFMFormas() {
        super();
        doHazPantalla();
    }

    public PantallaFarmaFMFormas(FarmaFMForma farmaFMForma) {
        super();
        this.farmaFMForma = farmaFMForma;
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FarmaFMForma.getLabelFrom());

        getContenedorGrid().add(grid);

        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        grid.setColumns("codigo", "nombre");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            frmFarmaFMForma = new FrmFarmaFMForma(event.getItem());
            doVentanaModal(frmFarmaFMForma);
            doActualizaGrid();
        }
        );

        doActualizaGrid();
    }

    public void doVentanaModal(FrmFarmaFMForma frmFarmaFMForma) {
        frmFarmaFMForma.open();
        frmFarmaFMForma.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFarmaFMForma.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doNuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
