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
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.ui.ObjetosComunes;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PntFMMprimasEntradas extends PantallaMaster {

    private FMMPrimasEntrada fMMPrimasEntrada = new FMMPrimasEntrada();

    private ArrayList<FMMPrimasEntrada> listaEntradas = new ArrayList<FMMPrimasEntrada>();

    private Grid<FMMPrimasEntrada> grid = new Grid<FMMPrimasEntrada>();

    private FrmFMMprimasEntradas frmFarmaFMMprimasEntradas;

    private ComboBox<FMMPrimas> comboMPrimas = ObjetosComunes.getComboMPrimas("Filtro de entradas por materia prima", null);

    public PntFMMprimasEntradas() {
        super();
        doHazPantalla();
    }

    public PntFMMprimasEntradas(FMMPrimasEntrada fMMPrimasEntrada) {
        super();
        this.fMMPrimasEntrada = fMMPrimasEntrada;

        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(fMMPrimasEntrada.getLabelFrom());

        getContenedorGrid().add(comboMPrimas);
        getContenedorGrid().add(grid);

        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        comboMPrimas.addValueChangeListener(e -> doActualizaGrid());
        //   grid.setColumns("numero", "fecha");
        //    grid.addColumn("")
        grid.addColumn(FMMPrimasEntrada::getCodigoNumero).setHeader("Número");
        grid.addColumn(FMMPrimasEntrada::getProducto).setHeader("Producto");
        grid.addColumn(FMMPrimasEntrada::getFecha).setHeader("Fecha");
        grid.addColumn(FMMPrimasEntrada::getEnvases).setHeader("Envases");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            frmFarmaFMMprimasEntradas = new FrmFMMprimasEntradas(event.getItem());
            doVentanaModal(frmFarmaFMMprimasEntradas);
        }
        );
        doActualizaGrid();
    }

    public void doVentanaModal(FrmFMMprimasEntradas frmFarmaFMMprimasEntradas) {
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
        this.doVentanaModal(new FrmFMMprimasEntradas(comboMPrimas.getValue()));
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaEntradas = new FMMprimasEntraDAO().getListaEntradasMP(null, null, comboMPrimas.getValue());

        grid.setItems(listaEntradas);

        numeroRegistros.setText(":" + Integer.toString(listaEntradas.size()));

        cabeceraGrid.setText(" Lista de " + FMMPrimasEntrada.getLabelFrom() + ". Registros: " + Integer.toString(listaEntradas.size()));

    }
}
