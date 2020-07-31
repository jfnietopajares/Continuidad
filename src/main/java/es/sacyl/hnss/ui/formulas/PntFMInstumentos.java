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
import com.vaadin.flow.theme.lumo.Lumo;
import es.sacyl.hnss.dao.FMInstrumentosDAO;
import es.sacyl.hnss.dao.FMViasAdmDAO;
import es.sacyl.hnss.entidades.FMInstrumento;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public final class PntFMInstumentos extends PantallaMaster {

    private FMInstrumento fMInstrumento = new FMInstrumento();

    private ArrayList<FMInstrumento> listaInstrumentos = new ArrayList<>();

    private Grid<FMInstrumento> grid = new Grid<>(FMInstrumento.class);

    private FrmFMInstrumentos frmFMInstrumentos;

    ArrayList<FMInstrumento> listaFarmaFMInstrumento = new ArrayList<>();

    public PntFMInstumentos() {
        super();
        doHazPantalla();
    }

    public PntFMInstumentos(FMInstrumento fMInstrumento) {

        super();

        this.fMInstrumento = fMInstrumento;
        doHazPantalla();
    }

    public void doHazPantalla() {
        this.setClassName(Lumo.DARK);
        titulo.setText(fMInstrumento.getLabelFrom());

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
            frmFMInstrumentos = new FrmFMInstrumentos(event.getItem());
            doVentanaModal(frmFMInstrumentos);
        }
        );
        doActualizaGrid();
    }

    public void doVentanaModal(FrmFMInstrumentos frmFMInstrumentos) {
        frmFMInstrumentos.open();
        frmFMInstrumentos.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFMInstrumentos.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        doActualizaGrid();
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFMInstrumentos());
        doActualizaGrid();
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaFarmaFMInstrumento = new FMInstrumentosDAO().getListaInstrumentos(textoABuscar.getValue());

        grid.setItems(listaFarmaFMInstrumento);

        numeroRegistros.setText(":" + Integer.toString(listaFarmaFMInstrumento.size()));

        cabeceraGrid.setText(" Lista de " + fMInstrumento.getLabelFrom() + ". Registros: " + Integer.toString(listaFarmaFMInstrumento.size()));

    }
}
