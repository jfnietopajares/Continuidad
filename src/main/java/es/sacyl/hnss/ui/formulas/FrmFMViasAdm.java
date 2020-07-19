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
import es.sacyl.hnss.dao.FMViasAdmDAO;
import es.sacyl.hnss.entidades.FMViasAdm;
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
public class FrmFMViasAdm extends FrmMaster {

    private TextField codigo = ObjetosComunes.getTextField("Código", "codigo", 15, "50px");

    private TextField nombre = ObjetosComunes.getTextField("Nombre", "descripción", 50, "150px");

    private FMViasAdm fMViasAdm = new FMViasAdm();

    private Binder<FMViasAdm> binder = new Binder<>();

    /**
     * Constructor sin parámetro. Usa el valor de fMViasAdm del atributo
     */
    public FrmFMViasAdm() {
        super();
        doHazFormulario();
    }

    public FrmFMViasAdm(FMViasAdm fMViasAdm) {
        super();
        this.fMViasAdm = fMViasAdm;
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(FMViasAdm.getLabelFrom());
        contenedorFormulario.add(codigo);
        contenedorFormulario.add(nombre);

        codigo.setWidth("50px");
        nombre.setWidth("250px");
        if (fMViasAdm == null || fMViasAdm.getCodigo() == null) {
            codigo.setEnabled(true);
        } else {
            codigo.setEnabled(false);
        }

        codigo.addBlurListener(e -> {
            FMViasAdm fMViasAdmExiste = new FMViasAdmDAO().getPorCodigo(codigo.getValue());
            if (fMViasAdmExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Position.MIDDLE)).open();
                nombre.setValue(fMViasAdmExiste.getNombre());
            }
        });

        binder.forField(codigo)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 15))
                .bind(FMViasAdm::getCodigo, FMViasAdm::setCodigo);

        binder.forField(nombre)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMViasAdm::getNombre, FMViasAdm::setNombre);

        binder.readBean(fMViasAdm);
        doControlBotones(fMViasAdm.getCodigo());
    }

    @Override
    /**
     *
     */
    public void doGrabar() {
        if (binder.writeBeanIfValid(fMViasAdm)) {

            if (new FMViasAdmDAO().doGrabaDatos(fMViasAdm) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Position.MIDDLE)).open();
            }
            this.close();
        } else {
            BinderValidationStatus<FMViasAdm> validate = binder.validate();
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
                    new FMViasAdmDAO().doBorraDatos(fMViasAdm);
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
