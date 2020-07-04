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
import es.sacyl.hnss.dao.FarmaFMMprimasEntraDAO;
import es.sacyl.hnss.dao.FarmaFMViasAdmDAO;
import es.sacyl.hnss.entidades.FarmaFMInstrumento;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PantallaFarmaFMMprimasEntradas extends PantallaMaster {

    private FarmaFMMPrimasEntrada farmaFMMPrimasEntrada = new FarmaFMMPrimasEntrada();

    private ArrayList<FarmaFMMPrimasEntrada> listaEntradas = new ArrayList<FarmaFMMPrimasEntrada>();

    private Grid<FarmaFMMPrimasEntrada> grid = new Grid<>(FarmaFMMPrimasEntrada.class);

    private FrmFarmaFMMprimasEntradas frmFarmaFMMprimasEntradas;

    public PantallaFarmaFMMprimasEntradas(FarmaFMMPrimasEntrada farmaFMMPrimasEntrada) {
        super();
        this.farmaFMMPrimasEntrada = farmaFMMPrimasEntrada;
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(farmaFMMPrimasEntrada.getLabelFrom());

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
            frmFarmaFMMprimasEntradas = new FrmFarmaFMMprimasEntradas(event.getItem());
            doVentanaModal(frmFarmaFMMprimasEntradas);
            doActualizaGrid();
        }
        );
        doActualizaGrid();
    }

    public void doVentanaModal(FrmFarmaFMMprimasEntradas frmFarmaFMMprimasEntradas) {
        frmFarmaFMMprimasEntradas.open();
        frmFarmaFMMprimasEntradas.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFarmaFMMprimasEntradas.addDetachListener(e -> {
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

        listaEntradas = new FarmaFMMprimasEntraDAO().getListaEntradasMP(null, null, farmaFMMPrimasEntrada);

        grid.setItems(listaEntradas);

        numeroRegistros.setText(":" + Integer.toString(listaEntradas.size()));

        cabeceraGrid.setText(" Lista de " + FarmaFMViasAdm.getLabelFrom() + ". Registros: " + Integer.toString(listaEntradas.size()));

    }
}
