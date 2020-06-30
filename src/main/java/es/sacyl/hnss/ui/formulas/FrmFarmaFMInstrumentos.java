/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FarmaFMInstrumentosDAO;
import es.sacyl.hnss.dao.FarmaFMViasAdmDAO;
import es.sacyl.hnss.entidades.FarmaFMInstrumento;
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
public class FrmFarmaFMInstrumentos extends FrmMaster {

    private TextField codigo = ObjetosComunes.getTextField("Código", "codigo", 15, "50px");

    private TextField nombre = ObjetosComunes.getTextField("Nombre", "descripción", 50, "150px");

    private FarmaFMInstrumento farmaFMInstrumento = new FarmaFMInstrumento();

    private Binder<FarmaFMInstrumento> binder = new Binder<>();

    private Grid<FarmaFMInstrumento> grid = null;

    public FrmFarmaFMInstrumentos() {
        super();
        doHazFormulario();
    }

    public FrmFarmaFMInstrumentos(FarmaFMInstrumento farmaFMInstrumento) {
        super();
        this.farmaFMInstrumento = farmaFMInstrumento;
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(FarmaFMViasAdm.getLabelFrom());
        contenedorFormulario.add(codigo);
        contenedorFormulario.add(nombre);

        codigo.setWidth("50px");
        nombre.setWidth("250px");
        if (farmaFMInstrumento == null || farmaFMInstrumento.getCodigo() == null) {
            codigo.setEnabled(true);
        } else {
            codigo.setEnabled(false);
        }

        codigo.addBlurListener(e -> {
            FarmaFMInstrumento farmaFMInstrumentosExiste = new FarmaFMInstrumentosDAO().getPorCodigo(codigo.getValue());
            if (farmaFMInstrumentosExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                nombre.setValue(farmaFMInstrumentosExiste.getNombre());
            }
        });

        binder.forField(codigo)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 15))
                .bind(FarmaFMInstrumento::getCodigo, FarmaFMInstrumento::setCodigo);

        binder.forField(nombre)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FarmaFMInstrumento::getNombre, FarmaFMInstrumento::setNombre);

        binder.readBean(farmaFMInstrumento);

    }

    public void doGrabar() {
        if (binder.writeBeanIfValid(farmaFMInstrumento)) {

            if (new FarmaFMInstrumentosDAO().doGrabaDatos(farmaFMInstrumento) == true) {

                Notification.show(FrmMaster.AVISODATOALMACENADO);

            } else {
                Notification.show(FrmMaster.AVISODATOERRORBBDD);
            }
            this.close();
        } else {
            BinderValidationStatus<FarmaFMInstrumento> validate = binder.validate();
            String errorText = validate.getFieldValidationStatuses()
                    .stream().filter(BindingValidationStatus::isError)
                    .map(BindingValidationStatus::getMessage)
                    .map(Optional::get).distinct()
                    .collect(Collectors.joining(", "));
            Notification.show(FrmMaster.AVISODATOERRORVALIDANO + errorText, 3000, Notification.Position.BOTTOM_START);
        }
    }

    @Override
    public void doBorrar() {
        final ConfirmDialog dialog = new ConfirmDialog(
                "Conformación de acción",
                "Seguro que quieres borrar el dato ",
                "Borrar el dato actual ", () -> {
                    new FarmaFMInstrumentosDAO().doBorraDatos(farmaFMInstrumento);
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
