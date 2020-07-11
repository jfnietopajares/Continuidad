/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FarmaFMViasAdmDAO;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author 06551256M
 *
 */
public class FrmFarmaFMViasAdm extends FrmMaster {

    private TextField codigo = ObjetosComunes.getTextField("Código", "codigo", 15, "50px");

    private TextField nombre = ObjetosComunes.getTextField("Nombre", "descripción", 50, "150px");

    private FarmaFMViasAdm farmaFMViasAdm = new FarmaFMViasAdm();

    private Binder<FarmaFMViasAdm> binder = new Binder<>();

    /**
     * Constructor sin parámetro. Usa el valor de farmaFMViasAdm del atributo
     */
    public FrmFarmaFMViasAdm() {
        super();
        doHazFormulario();
    }

    public FrmFarmaFMViasAdm(FarmaFMViasAdm farmaFMViasAdm) {
        super();
        this.farmaFMViasAdm = farmaFMViasAdm;
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(FarmaFMViasAdm.getLabelFrom());
        contenedorFormulario.add(codigo);
        contenedorFormulario.add(nombre);

        codigo.setWidth("50px");
        nombre.setWidth("250px");
        if (farmaFMViasAdm == null || farmaFMViasAdm.getCodigo() == null) {
            codigo.setEnabled(true);
        } else {
            codigo.setEnabled(false);
        }

        codigo.addBlurListener(e -> {
            FarmaFMViasAdm farmaFMViasAdmExiste = new FarmaFMViasAdmDAO().getPorCodigo(codigo.getValue());
            if (farmaFMViasAdmExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Position.MIDDLE)).open();
                nombre.setValue(farmaFMViasAdmExiste.getNombre());
            }
        });

        binder.forField(codigo)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 15))
                .bind(FarmaFMViasAdm::getCodigo, FarmaFMViasAdm::setCodigo);

        binder.forField(nombre)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FarmaFMViasAdm::getNombre, FarmaFMViasAdm::setNombre);

        binder.readBean(farmaFMViasAdm);

    }

    @Override
    /**
     *
     */
    public void doGrabar() {
        if (binder.writeBeanIfValid(farmaFMViasAdm)) {

            if (new FarmaFMViasAdmDAO().doGrabaDatos(farmaFMViasAdm) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Position.MIDDLE)).open();
            }
            this.close();
        } else {
            BinderValidationStatus<FarmaFMViasAdm> validate = binder.validate();
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
                    new FarmaFMViasAdmDAO().doBorraDatos(farmaFMViasAdm);
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
