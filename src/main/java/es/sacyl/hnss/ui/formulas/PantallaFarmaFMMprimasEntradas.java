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
import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.ObjetosComunes;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PantallaFarmaFMMprimasEntradas extends PantallaMaster {

    private FarmaFMMPrimasEntrada farmaFMMPrimasEntrada = new FarmaFMMPrimasEntrada();

    private ArrayList<FarmaFMMPrimasEntrada> listaEntradas = new ArrayList<FarmaFMMPrimasEntrada>();

    private Grid<FarmaFMMPrimasEntrada> grid = new Grid<FarmaFMMPrimasEntrada>();

    private FrmFarmaFMMprimasEntradas frmFarmaFMMprimasEntradas;

    private ComboBox<FarmaFMMPrimas> comboMPrimas = ObjetosComunes.getComboMPrimas("Filtro de entradas por materia prima", null);

    public PantallaFarmaFMMprimasEntradas() {
        super();
        doHazPantalla();
    }

    public PantallaFarmaFMMprimasEntradas(FarmaFMMPrimasEntrada farmaFMMPrimasEntrada) {
        super();
        this.farmaFMMPrimasEntrada = farmaFMMPrimasEntrada;

        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(farmaFMMPrimasEntrada.getLabelFrom());

        getContenedorGrid().add(comboMPrimas);
        getContenedorGrid().add(grid);

        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        comboMPrimas.addValueChangeListener(e -> doActualizaGrid());
        //   grid.setColumns("numero", "fecha");
        //    grid.addColumn("")
        grid.addColumn(FarmaFMMPrimasEntrada::getCodigoNumero).setHeader("Número");
        grid.addColumn(FarmaFMMPrimasEntrada::getProducto).setHeader("Producto");
        grid.addColumn(FarmaFMMPrimasEntrada::getFecha).setHeader("Fecha");
        grid.addColumn(FarmaFMMPrimasEntrada::getEnvases).setHeader("Envases");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            frmFarmaFMMprimasEntradas = new FrmFarmaFMMprimasEntradas(event.getItem());
            doVentanaModal(frmFarmaFMMprimasEntradas);
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
        this.doVentanaModal(new FrmFarmaFMMprimasEntradas(comboMPrimas.getValue()));
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaEntradas = new FarmaFMMprimasEntraDAO().getListaEntradasMP(null, null, comboMPrimas.getValue());

        grid.setItems(listaEntradas);

        numeroRegistros.setText(":" + Integer.toString(listaEntradas.size()));

        cabeceraGrid.setText(" Lista de " + FarmaFMMPrimasEntrada.getLabelFrom() + ". Registros: " + Integer.toString(listaEntradas.size()));

    }
}
