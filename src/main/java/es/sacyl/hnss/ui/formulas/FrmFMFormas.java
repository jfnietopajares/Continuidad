/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FMFormaDAO;
import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFMFormas extends FrmMaster {

    private TextField codigo = ObjetosComunes.getTextField("Código", "codigo", 15, "50px");

    private TextField nombre = ObjetosComunes.getTextField("Nombre", "descripción", 50, "150px");

    private FMForma fMForma = new FMForma();

    private Binder<FMForma> binder = new Binder<>();

    public FrmFMFormas() {
        super();
        doHazFormulario();
    }

    public FrmFMFormas(FMForma fMForma) {
        super();
        this.fMForma = fMForma;
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(FMViasAdm.getLabelFrom());
        contenedorFormulario.add(codigo);
        contenedorFormulario.add(nombre);

        codigo.setWidth("50px");
        nombre.setWidth("250px");
        if (fMForma == null || fMForma.getCodigo() == null) {
            codigo.setEnabled(true);
        } else {
            codigo.setEnabled(false);
        }

        codigo.addBlurListener(e -> {
            FMForma fMFormaExiste = new FMFormaDAO().getPorCodigo(codigo.getValue());
            if (fMFormaExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                nombre.setValue(fMFormaExiste.getNombre());
            }
        });

        binder.forField(codigo)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 15))
                .bind(FMForma::getCodigo, FMForma::setCodigo);

        binder.forField(nombre)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMForma::getNombre, FMForma::setNombre);

        binder.readBean(fMForma);
        doControlBotones(fMForma.getCodigo());
    }

    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(fMForma)) {

            if (new FMFormaDAO().doGrabaDatos(fMForma) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            this.close();
        } else {
            BinderValidationStatus<FMForma> validate = binder.validate();
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
                    new FMFormaDAO().doBorraDatos(fMForma);
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
