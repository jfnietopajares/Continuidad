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
import com.vaadin.flow.data.value.ValueChangeMode;
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

    private Grid<FMInstrumento> grid = new Grid<>();

    private FrmFMInstrumentos frmFMInstrumentos;

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
        textoABuscar.focus();
        textoABuscar.setValueChangeMode(ValueChangeMode.EAGER);
        textoABuscar.addValueChangeListener(event -> {
            doActualizaGrid();
            if (listaInstrumentos.size() == 1) {
                doVentanaModal(new FrmFMInstrumentos(listaInstrumentos.get(0)));
            }
        });
        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        grid.addColumn(FMInstrumento::getCodigo).setHeader("Código").setAutoWidth(true);
        grid.addColumn(FMInstrumento::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addItemClickListener(event -> {
            frmFMInstrumentos = new FrmFMInstrumentos(event.getItem());
            doVentanaModal(frmFMInstrumentos);
        }
        );
        doActualizaGrid();
    }

    public void doVentanaModal(FrmFMInstrumentos frmFMInstrumentos) {
        frmFMInstrumentos.open();
        this.setEnabled(false);
        frmFMInstrumentos.addDialogCloseActionListener(e -> {
            doActualizaGrid();
            this.setEnabled(true);
        }
        );
        frmFMInstrumentos.addDetachListener(e -> {
            doActualizaGrid();
            this.setEnabled(true);
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

        listaInstrumentos = new FMInstrumentosDAO().getListaInstrumentos(textoABuscar.getValue());

        grid.setItems(listaInstrumentos);

        numeroRegistros.setText(":" + Integer.toString(listaInstrumentos.size()));

        cabeceraGrid.setText(" Lista de " + fMInstrumento.getLabelFrom() + ". Registros: " + Integer.toString(listaInstrumentos.size()));

    }
}
