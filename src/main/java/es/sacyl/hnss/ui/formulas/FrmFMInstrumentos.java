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
import es.sacyl.hnss.dao.FMInstrumentosDAO;
import es.sacyl.hnss.dao.FMViasAdmDAO;
import es.sacyl.hnss.entidades.FMInstrumento;
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
public class FrmFMInstrumentos extends FrmMaster {

    private TextField codigo = ObjetosComunes.getTextField("Código", "codigo", 15, "50px");

    private TextField nombre = ObjetosComunes.getTextField("Nombre", "descripción", 50, "150px");

    private FMInstrumento fMInstrumento = new FMInstrumento();

    private Binder<FMInstrumento> binder = new Binder<>();

    private Grid<FMInstrumento> grid = null;

    public FrmFMInstrumentos() {
        super();
        doHazFormulario();
    }

    public FrmFMInstrumentos(FMInstrumento fMInstrumento) {
        super();
        this.fMInstrumento = fMInstrumento;
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(FMViasAdm.getLabelFrom());
        contenedorFormulario.add(codigo);
        contenedorFormulario.add(nombre);

        codigo.setWidth("50px");
        nombre.setWidth("250px");
        if (fMInstrumento == null || fMInstrumento.getCodigo() == null) {
            codigo.setEnabled(true);
        } else {
            codigo.setEnabled(false);
        }

        codigo.addBlurListener(e -> {
            FMInstrumento fMInstrumentosExiste = new FMInstrumentosDAO().getPorCodigo(codigo.getValue());
            if (fMInstrumentosExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                nombre.setValue(fMInstrumentosExiste.getNombre());
            }
        });

        binder.forField(codigo)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 15))
                .bind(FMInstrumento::getCodigo, FMInstrumento::setCodigo);

        binder.forField(nombre)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMInstrumento::getNombre, FMInstrumento::setNombre);

        binder.readBean(fMInstrumento);

    }

    public void doGrabar() {
        if (binder.writeBeanIfValid(fMInstrumento)) {

            if (new FMInstrumentosDAO().doGrabaDatos(fMInstrumento) == true) {

                Notification.show(FrmMaster.AVISODATOALMACENADO);

            } else {
                Notification.show(FrmMaster.AVISODATOERRORBBDD);
            }
            this.close();
        } else {
            BinderValidationStatus<FMInstrumento> validate = binder.validate();
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
                    new FMInstrumentosDAO().doBorraDatos(fMInstrumento);
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
