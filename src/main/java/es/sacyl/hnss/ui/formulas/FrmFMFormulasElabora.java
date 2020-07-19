/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FMFormulaElaboraDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaCompo;
import es.sacyl.hnss.entidades.FMFormulaElabora;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.FrmMasterLista;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFMFormulasElabora extends FrmMasterLista {

    private IntegerField formula = ObjetosComunes.getIntegerField("Código");

    private IntegerField orden = ObjetosComunes.getIntegerField("Código");

    private TextField texto = ObjetosComunes.getTextField("Texto", "", 50, "100px");

    private TextField nombre = ObjetosComunes.getTextField(null, null, 50, "100px");

    private FMFormula fMFormula;

    private FMFormulaElabora fMFormulaElabora = new FMFormulaElabora();

    private Binder<FMFormulaElabora> binder = new Binder();

    private Grid<FMFormulaElabora> grid = new Grid<>();

    public FrmFMFormulasElabora(FMFormulaElabora fMFormulaElabora, FMFormula fMFormula) {
        super("1000px");

        this.fMFormulaElabora = fMFormulaElabora;

        this.fMFormula = fMFormula;

        doHazFormulario();
    }

    public FrmFMFormulasElabora(FMFormula fMFormula) {
        super("1000px");

        this.fMFormula = fMFormula;

        doHazFormulario();
    }

    public void doHazFormulario() {
        this.setWidth("900px");
        //  this.setMaxWidth("900px");
//        this.setSizeFull();

        titulo.setText(FMFormulaElabora.getLabelFrom());

        contenedorDerecha.add(grid);

        contenedorFormulario.add(nombre, orden, texto);

        nombre.setValue(fMFormula.getNombre());
        grid.addColumn(FMFormulaElabora::getOrden).setHeader("Orden");
        grid.addColumn(FMFormulaElabora::getTexto).setHeader("Texto").setWidth("300px");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        //   grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event
                -> {
            doControlEventosRecpera(event.getItem());
        }
        );
        grid.setItems(
                new FMFormulaElaboraDAO().getListaElabora(fMFormula));

        doActualizaGrid();

        nombre.setReadOnly(
                true);

        formula.setValue(fMFormula.getNumero());

        orden.addBlurListener(e
                -> {
            FMFormulaElabora fMFormulaElaboraExs = new FMFormulaElaboraDAO().getPorCodigo(fMFormula, orden.getValue());
            if (fMFormulaElaboraExs != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                doControlEventosRecpera(fMFormulaElaboraExs);
            }
        }
        );
        binder.forField(orden)
                .asRequired()
                .bind(FMFormulaElabora::getOrden, FMFormulaElabora::setOrden);

        binder.forField(texto)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMFormulaElabora::getTexto, FMFormulaElabora::setTexto);

        binder.readBean(fMFormulaElabora);
        doControlBotones(fMFormulaElabora.getOrden());
    }

    public void doControlEventosRecpera(FMFormulaElabora fMFormulaElabora) {
        this.fMFormulaElabora = fMFormulaElabora;
        binder.readBean(fMFormulaElabora);
        orden.setEnabled(false);
        orden.focus();
        botonBorrar.setEnabled(true);
    }

    public void doControlEventosNuevo() {
        fMFormulaElabora = new FMFormulaElabora();
        fMFormulaElabora.setFormula(fMFormula.getNumero());

        binder.readBean(fMFormulaElabora);
        orden.setEnabled(true);
        orden.focus();
        botonBorrar.setEnabled(false);
        doActualizaGrid();
    }

    public void doActualizaGrid() {
        grid.setItems(new FMFormulaElaboraDAO().getListaElabora(fMFormula));
    }

    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(fMFormulaElabora)) {
            fMFormulaElabora.setFormula(fMFormula.getNumero());

            if (new FMFormulaElaboraDAO().doGrabaDatos(fMFormula, fMFormulaElabora) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            doControlEventosNuevo();
        } else {
            BinderValidationStatus<FMFormulaElabora> validate = binder.validate();
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
                    new FMFormulaElaboraDAO().doBorraDatos(fMFormulaElabora);
                    Notification.show(FrmMaster.AVISODATOBORRADO);
                    doControlEventosNuevo();
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
