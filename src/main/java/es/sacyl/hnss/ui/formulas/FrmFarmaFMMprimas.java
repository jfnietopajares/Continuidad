package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
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

    private IntegerField cod_inte = new IntegerField("Códgio");
    private TextField producto = ObjetosComunes.getTextField("Nombre prodcuto", "descripción", 50, "150px");
    private TextField cod_labo = ObjetosComunes.getTextField("Cod.Labor", "códgio labora", 15, "150px");
    private TextField laboratorio = ObjetosComunes.getTextField("Laboratorio", "laboratorio", 50, "150px");
    private TextField homologado = ObjetosComunes.getTextField("Homologado", "homologado", 1, "150px");
    private IntegerField n_labo = new IntegerField("n_labo");
    private IntegerField stock_min = new IntegerField("stock_min");

    private TextField observaciones = ObjetosComunes.getTextField("Observaciones", "observaciones", 255, "150px");

    private TextField especifica = ObjetosComunes.getTextField("especifica", "especifica", 255, "150px");

    private DatePicker ulti_revi = ObjetosComunes.getDatePicker("Ultima Rev", null, LocalDate.now());

    private TextField farmacetuico = ObjetosComunes.getTextField("farmacetuico", "farmacetuico", 25, "150px");

    private IntegerField existencias = new IntegerField("existencias");

    private TextField nlaboratorio = ObjetosComunes.getTextField("nlaboratorio", "nlaboratorio", 10, "150px");

    private TextField presentacion = ObjetosComunes.getTextField("presentacion", "presentacion", 25, "150px");

    private TextField descripcion = ObjetosComunes.getTextField("descripcion", "descripcion", 255, "150px");
    private TextField requisitcos = ObjetosComunes.getTextField("requisitcos", "requisitcos", 255, "150px");
    private TextField conservacion = ObjetosComunes.getTextField("conservacion", "conservacion", 255, "150px");

    private FarmaFMMPrimas famFMMprimas = new FarmaFMMPrimas();

    private Binder<FarmaFMMPrimas> binder = new Binder<>();

    public FrmFarmaFMMprimas() {
        super();
        doHazFormulario();
    }

    public FrmFarmaFMMprimas(FarmaFMMPrimas farmaFMMprimas) {
        super();
        this.famFMMprimas = famFMMprimas;
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(famFMMprimas.getLabelFrom());

        contenedorFormulario.setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("21em", 2),
                new ResponsiveStep("22em", 3));

        contenedorFormulario.add(cod_inte);
        contenedorFormulario.add(producto, 2);

        contenedorFormulario.add(cod_labo);
        contenedorFormulario.add(laboratorio, 2);

        if (famFMMprimas == null || famFMMprimas.getCod_inte() == null) {
            cod_inte.setEnabled(false);
        } else {
            cod_inte.setEnabled(true);
        }

        cod_inte.addBlurListener(e -> {
            FarmaFMMPrimas famFMMprimasExiste = new FarmaFMMprimasDAO().getPorCodigo(cod_inte.getValue());
            if (famFMMprimasExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(famFMMprimasExiste);
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

        binder.forField(cod_labo)
                .asRequired()
                .bind(FarmaFMMPrimas::getCod_labo, FarmaFMMPrimas::setCod_labo);

        binder.forField(laboratorio)
                .asRequired()
                .bind(FarmaFMMPrimas::getLaboratorio, FarmaFMMPrimas::setLaboratorio);

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
            /*
            if (cod_labo.getValue() == null) {
                cod_labo.setValue(cod_labo.getEmptyValue());
            }
            if (laboratorio.getValue() == null) {
                laboratorio.setValue(laboratorio.getEmptyValue());
            }
            if (homologado.getValue() == null) {
                homologado.setValue(homologado.getEmptyValue());
            }
            if (n_labo.getValue() == null) {
                n_labo.setValue(n_labo.getEmptyValue());
            }

            if (stock_min.getValue() == null) {
                stock_min.setValue(stock_min.getEmptyValue());
            }

            if (observaciones.getValue() == null) {
                observaciones.setValue(observaciones.getEmptyValue());
            }
            if (especifica.getValue() == null) {
                especifica.setValue(especifica.getEmptyValue());
            }
//    private LocalDate ulti_revi;
            if (farmacetuico.getValue() == null) {
                farmacetuico.setValue(farmacetuico.getEmptyValue());
            }

            if (existencias.getValue() == null) {
                existencias.setValue(existencias.getEmptyValue());
            }

            if (nlaboratorio.getValue() == null) {
                nlaboratorio.setValue(nlaboratorio.getEmptyValue());
            }

            if (presentacion.getValue() == null) {
                presentacion.setValue(presentacion.getEmptyValue());
            }

            if (descripcion.getValue() == null) {
                descripcion.setValue(descripcion.getEmptyValue());
            }
            if (requisitcos.getValue() == null) {
                requisitcos.setValue(requisitcos.getEmptyValue());
            }
            if (conservacion.getValue() == null) {
                conservacion.setValue(conservacion.getEmptyValue());
            }
             */
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
