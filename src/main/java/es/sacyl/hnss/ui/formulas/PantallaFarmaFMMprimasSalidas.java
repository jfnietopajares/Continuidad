/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import es.sacyl.hnss.dao.FarmaFMMprimasEntraDAO;
import es.sacyl.hnss.dao.FarmaFMMprimasSalidaDAO;
import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.entidades.FarmaFMMprimasSalida;
import es.sacyl.hnss.ui.ObjetosComunes;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PantallaFarmaFMMprimasSalidas extends PantallaMaster {

    private FarmaFMMprimasSalida farmaFMMPrimasSalida = new FarmaFMMprimasSalida();

    private ArrayList<FarmaFMMprimasSalida> listaSalidas = new ArrayList<FarmaFMMprimasSalida>();

    private Grid<FarmaFMMprimasSalida> grid = new Grid<FarmaFMMprimasSalida>();

    private FrmFarmaFMMprimasSalidas frmFarmaFMMprimasSalida;

    private ComboBox<FarmaFMMPrimas> comboMPrimas = ObjetosComunes.getComboMPrimas("Filtro de entradas por materia prima", null);

    public PantallaFarmaFMMprimasSalidas() {
        super();
        doHazPantalla();
    }

    public PantallaFarmaFMMprimasSalidas(FarmaFMMprimasSalida farmaFMMPrimasSalida) {
        super();
        this.farmaFMMPrimasSalida = farmaFMMPrimasSalida;

        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(farmaFMMPrimasSalida.getLabelFrom());

        getContenedorGrid().add(comboMPrimas);
        getContenedorGrid().add(grid);

        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        comboMPrimas.addValueChangeListener(e -> doActualizaGrid());
        //   grid.setColumns("numero", "fecha");
        //    grid.addColumn("")
        grid.addColumn(FarmaFMMprimasSalida::getCodigoNumero).setHeader("Número");
        grid.addColumn(FarmaFMMprimasSalida::getProducto).setHeader("Producto");
        grid.addColumn(FarmaFMMprimasSalida::getFecha).setHeader("Fecha");
        grid.addColumn(FarmaFMMprimasSalida::getCantidad).setHeader("Cantidad");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            frmFarmaFMMprimasSalida = new FrmFarmaFMMprimasSalidas(event.getItem());
            doVentanaModal(frmFarmaFMMprimasSalida);
        }
        );
        doActualizaGrid();
    }

    public void doVentanaModal(FrmFarmaFMMprimasSalidas frmFarmaFMMprimasSalidas) {
        frmFarmaFMMprimasSalidas.open();
        frmFarmaFMMprimasSalidas.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFarmaFMMprimasSalidas.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFarmaFMMprimasSalidas(comboMPrimas.getValue()));
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaSalidas = new FarmaFMMprimasSalidaDAO().getListaSalidasMP(null, null, comboMPrimas.getValue());

        grid.setItems(listaSalidas);

        numeroRegistros.setText(":" + Integer.toString(listaSalidas.size()));

        cabeceraGrid.setText(" Lista de " + FarmaFMMPrimasEntrada.getLabelFrom() + ". Registros: " + Integer.toString(listaSalidas.size()));

    }

}
