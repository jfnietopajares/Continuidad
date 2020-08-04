/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import es.sacyl.hnss.dao.FMViasAdmDAO;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author 06551256M
 */
public final class PntFMViasAdm extends PantallaMaster {

    private static final long serialVersionUID = 1L;

    private FMViasAdm fMViasAdm = new FMViasAdm();

    private final Grid<FMViasAdm> grid = new Grid<>(FMViasAdm.class);

    private FrmFMViasAdm frmFMViasAdministracion;

    ArrayList<FMViasAdm> listaFarmaFMViasAdm = new ArrayList<>();

    public PntFMViasAdm() {
        super();
        doHazPantalla();
    }

    public PntFMViasAdm(FMViasAdm fMViasAdm) {
        super();
        this.fMViasAdm = fMViasAdm;
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FMViasAdm.getLabelFrom());

        getContenedorGrid().add(grid);
      textoABuscar.focus();
        textoABuscar.setValueChangeMode(ValueChangeMode.EAGER);
        textoABuscar.addValueChangeListener(event -> {
            doActualizaGrid();
            if (listaFarmaFMViasAdm.size() == 1) {
                doVentanaModal(new FrmFMViasAdm(listaFarmaFMViasAdm.get(0)));
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
        grid.setSelectionMode(SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            frmFMViasAdministracion = new FrmFMViasAdm(event.getItem());
            doVentanaModal(frmFMViasAdministracion);
            doActualizaGrid();
        }
        );

        doActualizaGrid();
    }

    public void doVentanaModal(FrmFMViasAdm frmFarmaFMViasAdm) {
        frmFarmaFMViasAdm.open();
        frmFarmaFMViasAdm.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFarmaFMViasAdm.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        doActualizaGrid();
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFMViasAdm());
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaFarmaFMViasAdm = new FMViasAdmDAO().getListaViasAdm(textoABuscar.getValue());

        grid.setItems(listaFarmaFMViasAdm);

        numeroRegistros.setText(":" + Integer.toString(listaFarmaFMViasAdm.size()));

        cabeceraGrid.setText(" Lista de " + FMViasAdm.getLabelFrom() + ". Registros: " + Integer.toString(listaFarmaFMViasAdm.size()));

    }

}
