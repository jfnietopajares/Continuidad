/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.dao.FMInstrumentosDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.ui.PantallaMaster;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PntFMFormulas extends PantallaMaster {

    private FMFormula farmaFMFormulas = new FMFormula();

    private Grid<FMFormula> grid = new Grid<>(FMFormula.class);

    private FrmFMFormulas frmFMFormulas;

    ArrayList<FMFormula> listaFormulas = new ArrayList<>();

    public PntFMFormulas() {
        super();
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FMFormula.getLabelFrom());

        getContenedorGrid().add(grid);
textoABuscar.focus();
        textoABuscar.setValueChangeMode(ValueChangeMode.EAGER);
        textoABuscar.addValueChangeListener(event -> {
            doActualizaGrid();
            if (listaFormulas.size() == 1) {
                doVentanaModal(new FrmFMFormulas(listaFormulas.get(0)));
            }
        });
        botonBuscar.addClickListener(e -> {
            doActualizaGrid();
        });

        botonAnadir.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        grid.setColumns("numero", "nombre");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event -> {
            frmFMFormulas = new FrmFMFormulas(event.getItem());
            doVentanaModal(frmFMFormulas);
        }
        );
        grid.addColumn(new NativeButtonRenderer<>("Fabricar",
                clickedItem -> {
                    FrmFMFormulaFabricar frmFMFormulaFabricar = new FrmFMFormulaFabricar(clickedItem);

                    doVentanaModal(new FrmFMFormulaFabricar(clickedItem));
                    //  Notification.show("Sin dato seleccionado" + grid.getSelectedItems().size());
                }
        ));

        doActualizaGrid();
    }

    public void doVentanaModal(Dialog frm) {
        frm.open();
        frm.addDialogCloseActionListener(e -> {
            doActualizaGrid();
        }
        );
        frm.addDetachListener(e -> {
            doActualizaGrid();
        });
    }

    @Override
    public void doBuscar() {
        doActualizaGrid();
    }

    @Override
    public void doNuevo() {
        this.doVentanaModal(new FrmFMFormulas());
    }

    @Override
    public void doListar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doActualizaGrid() {
        listaFormulas = new FMFormulasDAO().getListaFormulas(textoABuscar.getValue());

        grid.setItems(listaFormulas);

        numeroRegistros.setText(":" + Integer.toString(listaFormulas.size()));

        cabeceraGrid.setText(" Lista de " + farmaFMFormulas.getLabelFrom() + ". Registros: " + Integer.toString(listaFormulas.size()));

    }

}
