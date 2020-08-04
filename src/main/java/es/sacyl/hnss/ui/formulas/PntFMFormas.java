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
import es.sacyl.hnss.dao.FMFormaDAO;
import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public final class PntFMFormas extends PantallaMaster {

    private static final long serialVersionUID = 1L;

    private FMForma fMForma = new FMForma();

    private ArrayList<FMForma> listaFormas = new ArrayList<>();

    private final Grid<FMForma> grid;

    private FrmFMFormas frmFMFormas;

    public PntFMFormas() {
        super();
        this.grid = new Grid<>(FMForma.class);
        doHazPantalla();
    }

    public PntFMFormas(FMForma fMForma) {
        super();
        this.grid = new Grid<>(FMForma.class);
        this.fMForma = fMForma;
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FMForma.getLabelFrom());

        getContenedorGrid().add(grid);

textoABuscar.focus();
        textoABuscar.setValueChangeMode(ValueChangeMode.EAGER);
        textoABuscar.addValueChangeListener(event -> {
            doActualizaGrid();
            if (listaFormas.size() == 1) {
                doVentanaModal(new FrmFMFormas(listaFormas.get(0)));
            }
        });
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
            frmFMFormas = new FrmFMFormas(event.getItem());
            doVentanaModal(frmFMFormas);
            doActualizaGrid();
        }
        );

        doActualizaGrid();
    }

    public void doVentanaModal(FrmFMFormas frmFMFormas) {
        frmFMFormas.open();
        frmFMFormas.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFMFormas.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFMFormas());
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaFormas = new FMFormaDAO().getListaFormas(textoABuscar.getValue());

        grid.setItems(listaFormas);

        numeroRegistros.setText(":" + Integer.toString(listaFormas.size()));

        cabeceraGrid.setText(" Lista de " + FMViasAdm.getLabelFrom() + ". Registros: " + Integer.toString(listaFormas.size()));

    }

}
