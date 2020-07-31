/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.function.ValueProvider;
import es.sacyl.hnss.dao.FMMprimasDAO;
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PntFMprimas extends PantallaMaster {

    private FMMPrimas fMMPrimas = new FMMPrimas();

    private ArrayList<FMMPrimas> listaMprimas = new ArrayList<>();

    private Grid<FMMPrimas> grid = new Grid<>(FMMPrimas.class);

    private FrmFMMprimas frmFarmaFMMPrimas;

    public PntFMprimas() {
        super();
        this.setWidth("1000px");
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FMMPrimas.getLabelFrom());

        getContenedorGrid().add(grid);

        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        grid.setColumns("cod_inte", "producto", "existencias", "laboratorio");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemDoubleClickListener(event -> {
            frmFarmaFMMPrimas = new FrmFMMprimas(event.getItem());
            doVentanaModal(frmFarmaFMMPrimas);
        }
        );
        grid.addItemClickListener(event -> {
            frmFarmaFMMPrimas = new FrmFMMprimas(event.getItem());

        }
        );
        grid.addColumn(new NativeButtonRenderer<>("Entradas",
                clickedItem -> {
                    FMMPrimasEntrada fMMPrimasEntrada = new FMMPrimasEntrada();
                    //   fMMPrimasEntrada.setCod_inte(clickedItem.getCod_inte());
                    // fMMPrimasEntrada.setProducto(clickedItem.getProducto());
                    doVentanaModal(new FrmFMMprimasEntradas(clickedItem));
                    //  Notification.show("Sin dato seleccionado" + grid.getSelectedItems().size());
                }
        ));
        grid.addColumn(new NativeButtonRenderer<>("Salidas",
                clickedItem -> {
                    //  FrmFMMprimasSalidas frmFMMprimasSalidas = new FrmFMMprimasSalidas();
                    doVentanaModal(new FrmFMMprimasSalidas(clickedItem));
                    //  Notification.show("Sin dato seleccionado" + grid.getSelectedItems().size());
                }
        ));
        doActualizaGrid();
    }

    /*
    public void doVentanaModalEntradas(FrmFMMprimasEntradas frmFarmaFMMprimasEntradas) {
        frmFarmaFMMprimasEntradas.open();
        frmFarmaFMMprimasEntradas.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFarmaFMMprimasEntradas.addDetachListener(e -> {
            doActualizaGrid();
        });
    }
     */
    public void doVentanaModal(Dialog frmFarmaFMMPrimas) {
        frmFarmaFMMPrimas.open();
        frmFarmaFMMPrimas.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFarmaFMMPrimas.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        doActualizaGrid();
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFMMprimas());
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaMprimas = new FMMprimasDAO().getListaMprimas(textoABuscar.getValue());

        grid.setItems(listaMprimas);

        numeroRegistros.setText(":" + Integer.toString(listaMprimas.size()));

        cabeceraGrid.setText(" Lista de " + fMMPrimas.getLabelFrom() + ". Registros: " + Integer.toString(listaMprimas.size()));

    }
}
