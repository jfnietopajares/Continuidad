/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FMFormaDAO;
import es.sacyl.hnss.dao.FMFormulaBiblioDAO;
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaBibliografia;
import es.sacyl.hnss.entidades.FMViasAdm;
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
public class FrmFMFormulasBiblio extends FrmMasterLista {

    private IntegerField formula = ObjetosComunes.getIntegerField("Código");

    private IntegerField orden = ObjetosComunes.getIntegerField("Código");

    private TextField texto = ObjetosComunes.getTextField("Texto", "", 50, "100px");

    private TextField nombre = ObjetosComunes.getTextField(null, null, 50, "100px");

    private FMFormula fMFormula;

    private FMFormulaBibliografia fMFormulaBibliografia = new FMFormulaBibliografia();

    private Binder<FMFormulaBibliografia> binder = new Binder();

    public FrmFMFormulasBiblio(FMFormulaBibliografia fMFormulaBibliografia, FMFormula fMFormula) {

        this.fMFormulaBibliografia = fMFormulaBibliografia;

        this.fMFormula = fMFormula;
        doHazFormulario();
    }

    public FrmFMFormulasBiblio(FMFormula fMFormula) {

        this.fMFormula = fMFormula;

        doHazFormulario();
    }

    public void doHazFormulario() {
        titulo.setText(FMFormulaBibliografia.getLabelFrom());

        contenedorFormulario.add(nombre, formula, orden, texto);

        nombre.setValue(fMFormula.getNombre());

        nombre.setReadOnly(true);

        formula.setValue(fMFormula.getNumero());

        orden.addBlurListener(e -> {
            FMFormulaBibliografia fMFormulaBibliografiaExs = new FMFormulaBiblioDAO().getPorCodigo(fMFormula, orden.getValue());
            if (fMFormulaBibliografiaExs != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                texto.setValue(fMFormulaBibliografiaExs.getTexto());
            }
        });
        /*
        binder.forField(formula)
                .asRequired()
                .bind(FMFormulaBibliografia::getFormula, FMFormulaBibliografia::setFormula);
         */
        binder.forField(orden)
                .asRequired()
                .bind(FMFormulaBibliografia::getOrden, FMFormulaBibliografia::setOrden);

        binder.forField(texto)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMFormulaBibliografia::getTexto, FMFormulaBibliografia::setTexto);

        binder.readBean(fMFormulaBibliografia);
    }

    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(fMFormulaBibliografia)) {

            if (new FMFormulaBiblioDAO().doGrabaDatos(fMFormula, fMFormulaBibliografia) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            this.close();
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
