/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FMFormulaCompoDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaCompo;
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.FrmMasterLista;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFMFormulasCompo extends FrmMasterLista {

    private IntegerField formula = ObjetosComunes.getIntegerField("Código");

    private IntegerField orden = ObjetosComunes.getIntegerField("Orden");

    // private IntegerField cantidad = ObjetosComunes.getIntegerField("Cantidad");
    private BigDecimalField cantidad = new BigDecimalField("Cantidad");

    private ComboBox<FMMPrimas> comboMprimas = ObjetosComunes.getComboMPrimas("Mprimas ", null);

    private TextField unidades = ObjetosComunes.getTextField("unidades", "", 50, "100px");

    private TextField nombre = ObjetosComunes.getTextField(null, null, 50, "100px");

    private FMFormula fMFormula;

    private FMFormulaCompo fMFormulaCompo = new FMFormulaCompo();

    private Binder<FMFormulaCompo> binder = new Binder();

    private Grid<FMFormulaCompo> grid = new Grid<>();

    public FrmFMFormulasCompo() {

    }

    public FrmFMFormulasCompo(FMFormulaCompo fMFormulaCompo, FMFormula fMFormula) {
        super("1000px");

        this.fMFormulaCompo = fMFormulaCompo;

        this.fMFormula = fMFormula;

        doHazFormulario();
    }

    public FrmFMFormulasCompo(FMFormula fMFormula) {
        super("1000px");

        this.fMFormula = fMFormula;

        doHazFormulario();
    }

    public void doHazFormulario() {
        this.setWidth("900px");
        //  this.setMaxWidth("900px");
        this.contenedorVentana.setWidth("900px");
        this.setSizeFull();

        titulo.setText(FMFormulaCompo.getLabelFrom());

        contenedorDerecha.add(grid);

        contenedorFormulario.add(nombre, comboMprimas, orden, cantidad, unidades);

        nombre.setValue(fMFormula.getNombre());
        grid.addColumn(FMFormulaCompo::getOrden).setHeader("Orden");
        grid.addColumn(FMFormulaCompo::getMprimaDescripcion).setHeader("M.Prima").setWidth("300px");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        //   grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event
                -> {
            fMFormulaCompo = event.getItem();
            binder.readBean(fMFormulaCompo);
        }
        );
        grid.setItems(
                new FMFormulaCompoDAO().getListaCompos(fMFormula));

        doActualizaGrid();

        nombre.setReadOnly(
                true);

        formula.setValue(fMFormula.getNumero());

        orden.addBlurListener(e
                -> {
            FMFormulaCompo fMFormulaCompoExiste = new FMFormulaCompoDAO().getPorCodigo(fMFormula, comboMprimas.getValue(), orden.getValue());
            if (fMFormulaCompoExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(fMFormulaCompoExiste);
            }
        }
        );

        binder.forField(comboMprimas)
                .asRequired()
                .bind(FMFormulaCompo::getMprima, FMFormulaCompo::setMprima);

        binder.forField(orden)
                .asRequired()
                .bind(FMFormulaCompo::getOrden, FMFormulaCompo::setOrden);

        binder.forField(cantidad)
                .asRequired()
                .bind(FMFormulaCompo::getCantidad, FMFormulaCompo::setCantidad);

        binder.forField(unidades)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMFormulaCompo::getUnidades, FMFormulaCompo::setUnidades);

        binder.readBean(fMFormulaCompo);
        cantidad.setValue(new BigDecimal(0).setScale(2));
    }

    public void doActualizaGrid() {
        grid.setItems(new FMFormulaCompoDAO().getListaCompos(fMFormula));
    }

    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(fMFormulaCompo)) {
            fMFormulaCompo.setFormula(fMFormula.getNumero());

            if (new FMFormulaCompoDAO().doGrabaDatos(fMFormula, fMFormulaCompo) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            fMFormulaCompo = new FMFormulaCompo();
            binder.readBean(fMFormulaCompo);
            formula.setValue(fMFormula.getNumero());
            // orden.clear();
            //texto.clear();
            doActualizaGrid();
            // this.close();
        } else {
            BinderValidationStatus<FMFormulaCompo> validate = binder.validate();
            String errorText = validate.getFieldValidationStatuses()
                    .stream().filter(BindingValidationStatus::isError)
                    .map(BindingValidationStatus::getMessage)
                    .map(Optional::get).distinct()
                    .collect(Collectors.joining(", "));
            Notification.show(FrmMaster.AVISODATOERRORVALIDANO + errorText);
        }
    }

    @Override
    public void doBorrar() {
        final ConfirmDialog dialog = new ConfirmDialog(
                "Conformación de acción",
                "Seguro que quieres borrar el dato ",
                "Borrar el dato actual ", () -> {
                    new FMFormulaCompoDAO().doBorraDatos(fMFormulaCompo);
                    Notification.show(FrmMaster.AVISODATOBORRADO);
                    this.close();
                });
        dialog.open();
        dialog.addDialogCloseActionListener(e -> {
            this.close();
        });
    }

    @Override
    public void doAyuda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
