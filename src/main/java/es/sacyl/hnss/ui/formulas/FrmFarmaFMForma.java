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
import es.sacyl.hnss.dao.FarmaFMFormaDAO;
import es.sacyl.hnss.dao.FarmaFMViasAdmDAO;
import es.sacyl.hnss.entidades.FarmaFMForma;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFarmaFMForma extends FrmMaster {

    private TextField codigo = ObjetosComunes.getTextField("Código", "codigo", 15, "50px");

    private TextField nombre = ObjetosComunes.getTextField("Nombre", "descripción", 50, "150px");

    private FarmaFMForma farmaFMForma = new FarmaFMForma();

    private Binder<FarmaFMForma> binder = new Binder<>();

    public FrmFarmaFMForma() {
        super();
        doHazFormulario();
    }

    public FrmFarmaFMForma(FarmaFMForma farmaFMForma) {
        super();
        this.farmaFMForma = farmaFMForma;
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(FarmaFMViasAdm.getLabelFrom());
        contenedorFormulario.add(codigo);
        contenedorFormulario.add(nombre);

        codigo.setWidth("50px");
        nombre.setWidth("250px");
        if (farmaFMForma == null || farmaFMForma.getCodigo() == null) {
            codigo.setEnabled(true);
        } else {
            codigo.setEnabled(false);
        }

        codigo.addBlurListener(e -> {
            FarmaFMForma farmaFMFormaExiste = new FarmaFMFormaDAO().getPorCodigo(codigo.getValue());
            if (farmaFMFormaExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                nombre.setValue(farmaFMFormaExiste.getNombre());
            }
        });

        binder.forField(codigo)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 15))
                .bind(FarmaFMForma::getCodigo, FarmaFMForma::setCodigo);

        binder.forField(nombre)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FarmaFMForma::getNombre, FarmaFMForma::setNombre);

        binder.readBean(farmaFMForma);

    }

    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(farmaFMForma)) {

            if (new FarmaFMFormaDAO().doGrabaDatos(farmaFMForma) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            this.close();
        } else {
            BinderValidationStatus<FarmaFMForma> validate = binder.validate();
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
                    new FarmaFMFormaDAO().doBorraDatos(farmaFMForma);
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
