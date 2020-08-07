/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import es.sacyl.hnss.dao.FMFormulaFrabicarDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaFrabicar;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.FrmMasterLista;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFMFormulaFabricar extends FrmMasterLista {

    private final IntegerField numero = ObjetosComunes.getIntegerField("Número");

    private final DatePicker fecha = ObjetosComunes.getDatePicker("Fecha", "fecha ", null);

    private final ComboBox<FMFormula> comboFormula;

    private final TextField medico = ObjetosComunes.getTextField("Médico", "medico", 50, "150px");

    private final TextField paciente = ObjetosComunes.getTextField("Paciente", "paciente", 50, "150px");

    private final IntegerField caducidad = ObjetosComunes.getIntegerField("Caducidad");

    //  private final IntegerField registro = ObjetosComunes.getIntegerField("Registro");
    private final TextField lote = ObjetosComunes.getTextField("Lote", "lote", 5, "150px");

    private final ComboBox<String> ccalidad = ObjetosComunes.getComboToString("C.Calidad", null, ObjetosComunes.SINO, "50px");

    private final IntegerField unidades = ObjetosComunes.getIntegerField("Unidades");

    private final TextField preparado = ObjetosComunes.getTextField("Preparado", "preparado", 50, "150px");

    private final TextField farmaceutico = ObjetosComunes.getTextField("Farmacéutico", "farmacéutico", 50, "150px");

    private final BigDecimalField formulas = new BigDecimalField("formulas");

    private final TextField ndescripcion = ObjetosComunes.getTextField("N.Prescripción", "n prest", 50, "150px");

    private final TextArea observaciones = ObjetosComunes.getTextArea("Observaciones", "observaciones", 50, "150px", "90px", null, null);

    private final IntegerField uni_dispen = ObjetosComunes.getIntegerField("Unidades Dispensadas");

    private FMFormulaFrabicar fMFormulaFrabicar = new FMFormulaFrabicar();

    private final Binder<FMFormulaFrabicar> binder;

    private final Grid<FMFormulaFrabicar> grid = new Grid<>();

    private FMFormula fMFormula = new FMFormula();

    public FrmFMFormulaFabricar(FMFormula fMFormula) {
        super();
        this.binder = new Binder<>();
        this.fMFormula = fMFormula;
        fMFormulaFrabicar.setFormula(fMFormula);
        comboFormula = ObjetosComunes.getComboFormula("Nombre Fómula", fMFormula);
        comboFormula.setReadOnly(true);
        doHazFormulario();
    }

    private void doHazFormulario() {
        this.setWidth("1000px");
        titulo.setText(FMFormulaFrabicar.getLabelFrom());

        contenedorDerecha.add(grid);

        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("5px", 1),
                new FormLayout.ResponsiveStep("200px", 2),
                new FormLayout.ResponsiveStep("100px", 3));
        contenedorFormulario.add(comboFormula, 3);
        contenedorFormulario.add(numero, fecha, ndescripcion);

        contenedorFormulario.add(medico, 3);
        contenedorFormulario.add(paciente, 3);
        contenedorFormulario.add(preparado, 3);
        contenedorFormulario.add(farmaceutico, 3);
        contenedorFormulario.add(caducidad, lote);
        contenedorFormulario.setColspan(lote, 2);
        contenedorFormulario.add(unidades, formulas, uni_dispen);
        contenedorFormulario.add(observaciones, 3);
        numero.addBlurListener(e
                -> {
            FMFormulaFrabicar fMFormulaFrabicarExiste = new FMFormulaFrabicarDAO().getPorCodigo(numero.getValue());
            if (fMFormulaFrabicarExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                doControlBotones(fMFormulaFrabicarExiste);
            }
        }
        );
        binder.forField(numero)
                .asRequired()
                .bind(FMFormulaFrabicar::getNumero, FMFormulaFrabicar::setNumero);

        binder.forField(fecha)
                .asRequired()
                .bind(FMFormulaFrabicar::getFecha, FMFormulaFrabicar::setFecha);

        binder.forField(ndescripcion)
                .asRequired()
                .bind(FMFormulaFrabicar::getNdescripcion, FMFormulaFrabicar::setNdescripcion);

        binder.forField(comboFormula)
                .asRequired()
                .bind(FMFormulaFrabicar::getFormula, FMFormulaFrabicar::setFormula);

        binder.forField(medico)
                .asRequired()
                .bind(FMFormulaFrabicar::getMedico, FMFormulaFrabicar::setMedico);

        binder.forField(paciente)
                .asRequired()
                .bind(FMFormulaFrabicar::getPaciente, FMFormulaFrabicar::setPaciente);

        binder.forField(preparado)
                .asRequired()
                .bind(FMFormulaFrabicar::getPreparado, FMFormulaFrabicar::setPreparado);

        binder.forField(farmaceutico)
                .asRequired()
                .bind(FMFormulaFrabicar::getFarmaceutico, FMFormulaFrabicar::setFarmaceutico);

        binder.forField(caducidad)
                .asRequired()
                .bind(FMFormulaFrabicar::getCaducidad, FMFormulaFrabicar::setCaducidad);

        binder.forField(lote)
                .asRequired()
                .bind(FMFormulaFrabicar::getLote, FMFormulaFrabicar::setLote);

        binder.forField(unidades)
                .asRequired()
                .bind(FMFormulaFrabicar::getUnidades, FMFormulaFrabicar::setUnidades);

        binder.forField(formulas)
                .asRequired()
                .bind(FMFormulaFrabicar::getFormulas, FMFormulaFrabicar::setFormulas);

        binder.forField(uni_dispen)
                .asRequired()
                .bind(FMFormulaFrabicar::getUni_dispen, FMFormulaFrabicar::setUni_dispen);

        binder.forField(observaciones)
                .bind(FMFormulaFrabicar::getObservaciones, FMFormulaFrabicar::setObservaciones);

        grid.addColumn(FMFormulaFrabicar::getNumero).setHeader("Orden");
        grid.addColumn(FMFormulaFrabicar::getFecha).setHeader("Fecha");
        grid.addColumn(FMFormulaFrabicar::getUnidades).setHeader("Unidades");
        grid.addColumn(FMFormulaFrabicar::getFormulas).setHeader("Fórmulas");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addItemClickListener(event
                -> {
            doControlBotones(event.getItem());
        }
        );
        doActualizaGrid();
        doControlBotones(null);
    }

    @Override
    public void doActualizaGrid() {
        grid.setItems(new FMFormulaFrabicarDAO().getListaFMFormulaFrabicar(comboFormula.getValue()));
    }

    @Override
    public void doControlBotones(Object obj) {
        super.doControlBotones(obj);
        if (obj == null) {
            fMFormulaFrabicar = new FMFormulaFrabicar();
            fMFormulaFrabicar.setFormula(fMFormula);
            binder.readBean(fMFormulaFrabicar);
            numero.setValue(new FMFormulaFrabicarDAO().getSiguienteNumero(fMFormula));
            fecha.setValue(LocalDate.now());
            ndescripcion.focus();
        } else {
            this.fMFormulaFrabicar = (FMFormulaFrabicar) obj;
            binder.readBean(fMFormulaFrabicar);
            numero.setEnabled(false);
            medico.focus();
        }
    }

    /*
    public void doControlEventosRecpera(FMFormulaFrabicar fMFormulaFrabicar) {
        if (fMFormulaFrabicar == null) {
            fMFormulaFrabicar = new FMFormulaFrabicar();
            fMFormulaFrabicar.setFormula(fMFormula);
            binder.readBean(fMFormulaFrabicar);
            numero.setValue(new FMFormulaFrabicarDAO().getSiguienteNumero(fMFormula));
            botonBorrar.setEnabled(false);
            fecha.setValue(LocalDate.now());
            ndescripcion.focus();
        } else {
            this.fMFormulaFrabicar = fMFormulaFrabicar;
            binder.readBean(fMFormulaFrabicar);
            numero.setEnabled(false);
            medico.focus();
            botonBorrar.setEnabled(true);
        }
        doActualizaGrid();
    }
     */
    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(fMFormulaFrabicar)) {

            if (new FMFormulaFrabicarDAO().doGrabaDatos(fMFormulaFrabicar) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            doControlBotones(null);
        } else {
            BinderValidationStatus<FMFormulaFrabicar> validate = binder.validate();
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
                    new FMFormulaFrabicarDAO().doBorraDatos(fMFormulaFrabicar);
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
    }

}
