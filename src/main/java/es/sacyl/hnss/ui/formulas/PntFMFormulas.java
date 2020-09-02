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
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.dao.FMInstrumentosDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.listados.FMFormulasLibro;
import es.sacyl.hnss.listados.FMFormulasListado;
import es.sacyl.hnss.ui.EmbeddedPdfDocument;
import es.sacyl.hnss.ui.ObjetosComunes;
import es.sacyl.hnss.ui.PantallaMaster;
import es.sacyl.hnss.ui.VentanaPDF;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class PntFMFormulas extends PantallaMaster {

    private FMFormula farmaFMFormulas = new FMFormula();

    private Grid<FMFormula> grid = new Grid<>();

    private FrmFMFormulas frmFMFormulas;

    ArrayList<FMFormula> listaFormulas = new ArrayList<>();

    protected Button botonLibro = ObjetosComunes.getBoton("Libro", null, VaadinIcon.BOOK.create());

    public PntFMFormulas() {
        super();
        doHazPantalla();
    }

    public void doHazPantalla() {
        titulo.setText(FMFormula.getLabelFrom());

        contenedorFiltro.add(botonLibro);

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

        botonListar.addClickListener(e -> doVerListadoPdf());

        botonLibro.addClickListener(e -> doVerLibro());

        grid.addColumn(FMFormula::getNumero).setHeader("Nª").setAutoWidth(true);
        grid.addColumn(FMFormula::getNombre).setHeader("Nombre").setWidth("300px");
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
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
                }
        )).setHeader("Acción").setAutoWidth(true);

        doActualizaGrid();
    }

    public void doVerListadoPdf() {
        //  String nombrePdfAbsoluto = Constantes.DIRECTORIOREPORTABSOLUTEPATH + "listadoformulas" + ".pdf";
        String nombrePdfAbsoluto = new Constantes().getPathAbsoluto() + Constantes.DIRECTORIOREPORTSRELATIVEPATH + "listadoformulas" + ".pdf";
        String nombrePdfRelativo = Constantes.DIRECTORIOREPORTSRELATIVEPATH + "listadoformulas" + ".pdf";
        InputStream is = new FMFormulasListado().getStream();
        File infPdf = Utilidades.iStoFile(is, nombrePdfAbsoluto);
        new VentanaPDF("Listado de fórmulas", nombrePdfRelativo);
    }

    public void doVerLibro() {
        //String nombrePdfAbsoluto = Constantes.DIRECTORIOREPORTABSOLUTEPATH + "libroformulas" + ".pdf";
        String nombrePdfAbsoluto = new Constantes().getPathAbsoluto() + Constantes.DIRECTORIOREPORTSRELATIVEPATH + Constantes.DIRECTORIOREPORTSRELATIVEPATH + "libroformulas" + ".pdf";
        String nombrePdfRelativo = Constantes.DIRECTORIOREPORTSRELATIVEPATH + "libroformulas" + ".pdf";
        InputStream is = new FMFormulasLibro().getStream();
        File infPdf = Utilidades.iStoFile(is, nombrePdfAbsoluto);
        new VentanaPDF("Libro de fórmulas", nombrePdfRelativo);
    }

    public void doVentanaModal(Dialog frm) {
        this.setEnabled(false);
        frm.open();
        frm.addDialogCloseActionListener(e -> {
            doActualizaGrid();
            this.setEnabled(true);
        }
        );
        frm.addDetachListener(e -> {
            doActualizaGrid();
            this.setEnabled(true);
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
