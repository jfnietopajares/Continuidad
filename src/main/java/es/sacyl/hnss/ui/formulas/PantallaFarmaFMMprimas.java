/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.function.ValueProvider;
import es.sacyl.hnss.dao.FarmaFMMprimasDAO;
import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PantallaFarmaFMMprimas extends PantallaMaster {

    private FarmaFMMPrimas farmaFMMPrimas = new FarmaFMMPrimas();

    private ArrayList<FarmaFMMPrimas> listaMprimas = new ArrayList<>();

    private Grid<FarmaFMMPrimas> grid = new Grid<>(FarmaFMMPrimas.class);

    private FrmFarmaFMMprimas frmFarmaFMMPrimas;

    public PantallaFarmaFMMprimas() {
        super();
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FarmaFMMPrimas.getLabelFrom());

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
            frmFarmaFMMPrimas = new FrmFarmaFMMprimas(event.getItem());
            doVentanaModal(frmFarmaFMMPrimas);
        }
        );
        grid.addItemClickListener(event -> {
            frmFarmaFMMPrimas = new FrmFarmaFMMprimas(event.getItem());

        }
        );
        grid.addColumn(
                new NativeButtonRenderer<>("Entradas",
                        clickedItem -> {
                            FarmaFMMPrimasEntrada farmaFMMPrimasEntrada = new FarmaFMMPrimasEntrada();
                            farmaFMMPrimasEntrada.setCod_inte(clickedItem.getCod_inte());
                            farmaFMMPrimasEntrada.setProducto(clickedItem.getProducto());
                            doVentanaModalEntradas(new FrmFarmaFMMprimasEntradas(farmaFMMPrimasEntrada));
                            //  Notification.show("Sin dato seleccionado" + grid.getSelectedItems().size());
                        }
                ));

        doActualizaGrid();
    }

    public void doVentanaModalEntradas(FrmFarmaFMMprimasEntradas frmFarmaFMMprimasEntradas) {
        frmFarmaFMMprimasEntradas.open();
        frmFarmaFMMprimasEntradas.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frmFarmaFMMprimasEntradas.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    public void doVentanaModal(FrmFarmaFMMprimas frmFarmaFMMPrimas) {
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
        this.doVentanaModal(new FrmFarmaFMMprimas());
        doActualizaGrid();
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {

        listaMprimas = new FarmaFMMprimasDAO().getListaMprimas(textoABuscar.getValue());

        grid.setItems(listaMprimas);

        numeroRegistros.setText(":" + Integer.toString(listaMprimas.size()));

        cabeceraGrid.setText(" Lista de " + farmaFMMPrimas.getLabelFrom() + ". Registros: " + Integer.toString(listaMprimas.size()));

    }
}
