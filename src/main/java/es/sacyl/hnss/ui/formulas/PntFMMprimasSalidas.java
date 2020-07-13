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
import es.sacyl.hnss.dao.FMMprimasEntraDAO;
import es.sacyl.hnss.dao.FMMprimasSalidaDAO;
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMMprimasSalida;
import es.sacyl.hnss.ui.ObjetosComunes;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PntFMMprimasSalidas extends PantallaMaster {

    private FMMprimasSalida fMMPrimasSalida = new FMMprimasSalida();

    private ArrayList<FMMprimasSalida> listaSalidas = new ArrayList<FMMprimasSalida>();

    private Grid<FMMprimasSalida> grid = new Grid<FMMprimasSalida>();

    private FrmFMMprimasSalidas frmFMMprimasSalida;

    private ComboBox<FMMPrimas> comboMPrimas = ObjetosComunes.getComboMPrimas("Filtro de entradas por materia prima", null);

    public PntFMMprimasSalidas() {
        super();
        doHazPantalla();
    }

    public PntFMMprimasSalidas(FMMprimasSalida fMMPrimasSalida) {
        super();
        this.fMMPrimasSalida = fMMPrimasSalida;

        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(fMMPrimasSalida.getLabelFrom());

        getContenedorGrid().add(comboMPrimas);
        getContenedorGrid().add(grid);

        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        comboMPrimas.addValueChangeListener(e -> doActualizaGrid());
        //   grid.setColumns("numero", "fecha");
        //    grid.addColumn("")
        grid.addColumn(FMMprimasSalida::getCodigoNumero).setHeader("Número");
        grid.addColumn(FMMprimasSalida::getProducto).setHeader("Producto");
        grid.addColumn(FMMprimasSalida::getFecha).setHeader("Fecha");
        grid.addColumn(FMMprimasSalida::getCantidad).setHeader("Cantidad");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            frmFMMprimasSalida = new FrmFMMprimasSalidas(event.getItem());
            doVentanaModal(frmFMMprimasSalida);
        }
        );
        doActualizaGrid();
    }

    public void doVentanaModal(FrmFMMprimasSalidas frmFMMprimasSalidas) {
        frmFMMprimasSalidas.open();
        frmFMMprimasSalidas.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFMMprimasSalidas.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFMMprimasSalidas(comboMPrimas.getValue()));
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaSalidas = new FMMprimasSalidaDAO().getListaSalidasMP(null, null, comboMPrimas.getValue());

        grid.setItems(listaSalidas);

        numeroRegistros.setText(":" + Integer.toString(listaSalidas.size()));

        cabeceraGrid.setText(" Lista de " + FMMPrimasEntrada.getLabelFrom() + ". Registros: " + Integer.toString(listaSalidas.size()));

    }

}
