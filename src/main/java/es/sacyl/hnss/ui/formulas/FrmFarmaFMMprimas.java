package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FarmaFMMprimasDAO;
import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFarmaFMMprimas extends FrmMaster {

    private IntegerField cod_inte = ObjetosComunes.getIntegerField("Código");
    private TextField producto = ObjetosComunes.getTextField("Nombre prodcuto", "nombre", 50, "100px");
    private TextField cod_labo = ObjetosComunes.getTextField("Cod.Laboratorio", "código laboratorio   ", 15, "100px");
    private TextField laboratorio = ObjetosComunes.getTextField("Laboratorio", "laboratorio", 50, "100px");
    private Checkbox homologado = new Checkbox("Homologado");
    private IntegerField n_labo = ObjetosComunes.getIntegerField("n_labo");
    private IntegerField stock_min = ObjetosComunes.getIntegerField("stock_min");

    private TextArea observaciones = ObjetosComunes.getTextArea("Observaciones", "observaciones", 255, "100px", "90px");

    private TextField especifica = ObjetosComunes.getTextField("Especificaciones", "especifica", 255, "100px");

    private DatePicker ulti_revi = ObjetosComunes.getDatePicker("Última Revision", null, LocalDate.now());

    private TextField farmacetuico = ObjetosComunes.getTextField("Farmacétuico", "farmacetuico", 25, "100px");

    private IntegerField existencias = ObjetosComunes.getIntegerField("Existencias");

    private TextField nlaboratorio = ObjetosComunes.getTextField("N Laboratorio", "nlaboratorio", 10, "100px");

    private TextField presentacion = ObjetosComunes.getTextField("Presentación", "presentación", 25, "100px");

    private TextField descripcion = ObjetosComunes.getTextField("Descripción", "descripción", 255, "100px");

    private TextField requisitos = ObjetosComunes.getTextField("Requisitos", "requisitos", 255, "100px");

    private TextField conservacion = ObjetosComunes.getTextField("Conservación", "conservación", 255, "100px");

    private FarmaFMMPrimas famFMMprimas = new FarmaFMMPrimas();

    private Binder<FarmaFMMPrimas> binder = new Binder<>();

    public FrmFarmaFMMprimas() {
        super();
        doHazFormulario();
    }

    public FrmFarmaFMMprimas(FarmaFMMPrimas famFMMprimas) {
        super();
        this.famFMMprimas = famFMMprimas;
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(famFMMprimas.getLabelFrom());

        contenedorFormulario.setResponsiveSteps(
                new ResponsiveStep("5px", 1),
                new ResponsiveStep("100px", 2),
                new ResponsiveStep("100px", 3));

        contenedorFormulario.setMaxWidth("600px");
        contenedorFormulario.add(cod_inte, producto);
        contenedorFormulario.setColspan(producto, 2);

        contenedorFormulario.add(cod_labo, laboratorio, homologado);

        contenedorFormulario.add(presentacion, conservacion);
        contenedorFormulario.setColspan(conservacion, 2);

        contenedorFormulario.add(descripcion, 3);
        contenedorFormulario.add(requisitos, 3);
        contenedorFormulario.add(observaciones, 3);
        contenedorFormulario.add(existencias, stock_min, ulti_revi);
        contenedorFormulario.add(farmacetuico, 3);

        if (famFMMprimas == null || famFMMprimas.getCod_inte() == null) {
            cod_inte.setEnabled(true);
            cod_inte.focus();
        } else {
            cod_inte.setEnabled(false);
            producto.focus();
        }
        cod_inte.setWidth("25px");
        cod_inte.addBlurListener(e -> {
            FarmaFMMPrimas famFMMprimasExiste = new FarmaFMMprimasDAO().getPorCodigo(cod_inte.getValue());
            if (famFMMprimasExiste != null) {
                famFMMprimas = famFMMprimasExiste;
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(famFMMprimas);
            }
        });

        binder.forField(cod_inte)
                .asRequired()
                .bind(FarmaFMMPrimas::getCod_inte, FarmaFMMPrimas::setCod_inte);

        binder.forField(producto)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FarmaFMMPrimas::getProducto, FarmaFMMPrimas::setProducto);
        cod_labo.setWidth("25px");
        binder.forField(cod_labo)
                .asRequired()
                .bind(FarmaFMMPrimas::getCod_labo, FarmaFMMPrimas::setCod_labo);

        binder.forField(laboratorio)
                .asRequired()
                .bind(FarmaFMMPrimas::getLaboratorio, FarmaFMMPrimas::setLaboratorio);

        binder.forField(homologado)
                .bind(FarmaFMMPrimas::getHomologado, FarmaFMMPrimas::setHomologado);

        binder.forField(presentacion)
                .asRequired()
                .bind(FarmaFMMPrimas::getPresentacion, FarmaFMMPrimas::setPresentacion);

        binder.forField(conservacion)
                .asRequired()
                .bind(FarmaFMMPrimas::getConservacion, FarmaFMMPrimas::setConservacion);

        binder.forField(descripcion)
                .asRequired()
                .bind(FarmaFMMPrimas::getDescripcion, FarmaFMMPrimas::setDescripcion);

        binder.forField(requisitos)
                .asRequired()
                .bind(FarmaFMMPrimas::getRequisitos, FarmaFMMPrimas::setRequisitos);

        binder.forField(observaciones)
                .asRequired()
                .bind(FarmaFMMPrimas::getObservaciones, FarmaFMMPrimas::setObservaciones);

        binder.forField(existencias)
                .asRequired()
                .bind(FarmaFMMPrimas::getExistencias, FarmaFMMPrimas::setExistencias);

        binder.forField(stock_min)
                .asRequired()
                .bind(FarmaFMMPrimas::getStock_min, FarmaFMMPrimas::setStock_min);

        binder.forField(ulti_revi)
                .asRequired()
                .bind(FarmaFMMPrimas::getUlti_revi, FarmaFMMPrimas::setUlti_revi);

        binder.forField(farmacetuico)
                .asRequired()
                .bind(FarmaFMMPrimas::getFarmacetuico, FarmaFMMPrimas::setFarmacetuico);

        binder.readBean(famFMMprimas);

    }

    @Override
    /**
     *
     */
    public void doGrabar() {
        if (ulti_revi.getValue() != null) {
            ulti_revi.setValue(LocalDate.now());
        }

        if (binder.writeBeanIfValid(famFMMprimas)) {
            if (new FarmaFMMprimasDAO()
                    .doGrabaDatos(famFMMprimas) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }

            this.close();
        } else {
            BinderValidationStatus<FarmaFMMPrimas> validate = binder.validate();
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
                    new FarmaFMMprimasDAO().doBorraDatos(famFMMprimas);
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
