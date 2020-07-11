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
import es.sacyl.hnss.dao.FarmaFMViasAdmDAO;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author 06551256M
 */
public class PantallaFarmaFMViasAdm extends PantallaMaster {

    private FarmaFMViasAdm farmaFMViasAdm = new FarmaFMViasAdm();

    private Grid<FarmaFMViasAdm> grid = new Grid<>(FarmaFMViasAdm.class);

    private FrmFarmaFMViasAdm frmFarmaFMViasAdministracion;

    ArrayList<FarmaFMViasAdm> listaFarmaFMViasAdm = new ArrayList<>();

    public PantallaFarmaFMViasAdm() {
        super();
        doHazPantalla();
    }

    public PantallaFarmaFMViasAdm(FarmaFMViasAdm farmaFMViasAdm) {
        super();
        this.farmaFMViasAdm = farmaFMViasAdm;
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FarmaFMViasAdm.getLabelFrom());

        getContenedorGrid().add(grid);

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
            frmFarmaFMViasAdministracion = new FrmFarmaFMViasAdm(event.getItem());
            doVentanaModal(frmFarmaFMViasAdministracion);
            doActualizaGrid();
        }
        );

        doActualizaGrid();
    }

    public void doVentanaModal(FrmFarmaFMViasAdm frmFarmaFMViasAdm) {
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
        this.doVentanaModal(new FrmFarmaFMViasAdm());
        doActualizaGrid();
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaFarmaFMViasAdm = new FarmaFMViasAdmDAO().getListaViasAdm(textoABuscar.getValue());

        grid.setItems(listaFarmaFMViasAdm);

        numeroRegistros.setText(":" + Integer.toString(listaFarmaFMViasAdm.size()));

        cabeceraGrid.setText(" Lista de " + FarmaFMViasAdm.getLabelFrom() + ". Registros: " + Integer.toString(listaFarmaFMViasAdm.size()));

    }

}
