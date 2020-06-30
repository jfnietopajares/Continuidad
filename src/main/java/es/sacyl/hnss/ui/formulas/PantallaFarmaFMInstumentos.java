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
import es.sacyl.hnss.dao.FarmaFMInstrumentosDAO;
import es.sacyl.hnss.dao.FarmaFMViasAdmDAO;
import es.sacyl.hnss.entidades.FarmaFMInstrumento;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PantallaFarmaFMInstumentos extends PantallaMaster {

    private FarmaFMInstrumento farmaFMInstrumento = new FarmaFMInstrumento();

    private ArrayList<FarmaFMInstrumento> listaInstrumentos = new ArrayList<>();

    private Grid<FarmaFMInstrumento> grid = new Grid<>(FarmaFMInstrumento.class);

    private FrmFarmaFMInstrumentos frmFarmaFMInstrumentos;

    ArrayList<FarmaFMInstrumento> listaFarmaFMInstrumento = new ArrayList<>();

    public PantallaFarmaFMInstumentos() {

        super();
        doHazPantalla();
    }

    public PantallaFarmaFMInstumentos(FarmaFMInstrumento farmaFMInstrumento) {

        super();

        this.farmaFMInstrumento = farmaFMInstrumento;
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(farmaFMInstrumento.getLabelFrom());

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
            frmFarmaFMInstrumentos = new FrmFarmaFMInstrumentos(event.getItem());
            doVentanaModal(frmFarmaFMInstrumentos);
            doActualizaGrid();
        }
        );
        doActualizaGrid();
    }

    public void doVentanaModal(FrmFarmaFMInstrumentos frmFarmaFMInstrumentos) {
        frmFarmaFMInstrumentos.open();
        frmFarmaFMInstrumentos.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFarmaFMInstrumentos.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        doActualizaGrid();
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFarmaFMInstrumentos());
        doActualizaGrid();
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaFarmaFMInstrumento = new FarmaFMInstrumentosDAO().getListaInstrumentos(textoABuscar.getValue());

        grid.setItems(listaFarmaFMInstrumento);

        numeroRegistros.setText(":" + Integer.toString(listaFarmaFMInstrumento.size()));

        cabeceraGrid.setText(" Lista de " + farmaFMInstrumento.getLabelFrom() + ". Registros: " + Integer.toString(listaFarmaFMInstrumento.size()));

    }
}
