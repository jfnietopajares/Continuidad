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
import es.sacyl.hnss.dao.FMFormulaBiblioDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaBibliografia;
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
public class  FrmFMFormulasBiblio extends FrmMasterLista {

    private static final long serialVersionUID = 1L;

    private final IntegerField formula = ObjetosComunes.getIntegerField("Código");

    private final IntegerField orden = ObjetosComunes.getIntegerField("Código");

    private final TextField texto = ObjetosComunes.getTextField("Texto", "", 50, "100px");

    private final TextField nombre = ObjetosComunes.getTextField(null, null, 50, "100px");

    private  FMFormula fMFormula;

    private FMFormulaBibliografia fMFormulaBibliografia = new FMFormulaBibliografia();

    private Binder<FMFormulaBibliografia> binder = new Binder();

    private Grid<FMFormulaBibliografia> grid = new Grid<>();

    public FrmFMFormulasBiblio(FMFormulaBibliografia fMFormulaBibliografia, FMFormula fMFormula) {
        super("1000px");

        this.fMFormulaBibliografia = fMFormulaBibliografia;

        this.fMFormula = fMFormula;

        doHazFormulario();
    }

    public FrmFMFormulasBiblio(FMFormula fMFormula) {
        super("1000px");

        this.fMFormula = fMFormula;

        doHazFormulario();
    }

    public void doHazFormulario() {
        this.setWidth("900px");

        titulo.setText(FMFormulaBibliografia.getLabelFrom());

        contenedorDerecha.add(grid);

        contenedorFormulario.add(nombre, orden, texto);

        nombre.setValue(fMFormula.getNombre());

        grid.addColumn(FMFormulaBibliografia::getOrden).setHeader("Orden");
        grid.addColumn(FMFormulaBibliografia::getTexto).setHeader("Texto").setWidth("300px");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        //   grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event
                -> {
            fMFormulaBibliografia = event.getItem();
            doControlEventosRecpera(fMFormulaBibliografia);
        }
        );
        grid.setItems(
                new FMFormulaBiblioDAO().getListaBiblio(fMFormula));

        doActualizaGrid();

        nombre.setReadOnly(
                true);

        formula.setValue(fMFormula.getNumero());

        orden.addBlurListener(e
                -> {
            FMFormulaBibliografia fMFormulaBibliografiaExs = new FMFormulaBiblioDAO().getPorCodigo(fMFormula, orden.getValue());
            if (fMFormulaBibliografiaExs != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                doControlEventosRecpera(fMFormulaBibliografiaExs);
            }
        }
        );
        binder.forField(orden)
                .asRequired()
                .bind(FMFormulaBibliografia::getOrden, FMFormulaBibliografia::setOrden);

        binder.forField(texto)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMFormulaBibliografia::getTexto, FMFormulaBibliografia::setTexto);

        binder.readBean(fMFormulaBibliografia);
        orden.focus();
        doControlBotones(fMFormulaBibliografia.getOrden());
    }

    public void doActualizaGrid() {
        grid.setItems(new FMFormulaBiblioDAO().getListaBiblio(fMFormula));
    }

    public void doControlEventosRecpera(FMFormulaBibliografia fMFormulaBibliografia) {
        this.fMFormulaBibliografia = fMFormulaBibliografia;
        binder.readBean(fMFormulaBibliografia);
        orden.setEnabled(false);
        texto.focus();
        botonBorrar.setEnabled(true);
    }

    public void doControlEventosNuevo() {
        fMFormulaBibliografia = new FMFormulaBibliografia();
        fMFormulaBibliografia.setFormula(fMFormula.getNumero());
        binder.readBean(fMFormulaBibliografia);
        orden.setEnabled(true);
        orden.focus();
        botonBorrar.setEnabled(false);
        doActualizaGrid();
    }

    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(fMFormulaBibliografia)) {
            fMFormulaBibliografia.setFormula(fMFormula.getNumero());

            if (new FMFormulaBiblioDAO().doGrabaDatos(fMFormula, fMFormulaBibliografia) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                doControlEventosNuevo();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            doControlEventosNuevo();
        } else {
            BinderValidationStatus<FMFormulaBibliografia> validate = binder.validate();
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
                    new FMFormulaBiblioDAO().doBorraDatos(fMFormulaBibliografia);
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
