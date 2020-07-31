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
import es.sacyl.hnss.dao.FMFormulaFrabicarDAO;
import es.sacyl.hnss.dao.FMMprimasDAO;
import es.sacyl.hnss.entidades.FMFormulaFrabicar;
import es.sacyl.hnss.entidades.FMMPrimas;
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
public class FrmFMMprimas extends FrmMaster {

    private IntegerField cod_inte = ObjetosComunes.getIntegerField("Código");

    private TextField producto = ObjetosComunes.getTextField("Nombre prodcuto", "nombre", 50, "100px");

    private TextField cod_labo = ObjetosComunes.getTextField("Cod.Laboratorio", "código laboratorio   ", 15, "100px");

    private TextField laboratorio = ObjetosComunes.getTextField("Laboratorio", "laboratorio", 50, "100px");

    private Checkbox homologado = new Checkbox("Homologado");

    private IntegerField n_labo = ObjetosComunes.getIntegerField("n_labo");

    private IntegerField stock_min = ObjetosComunes.getIntegerField("stock_min");

    private TextArea observaciones = ObjetosComunes.getTextArea("Observaciones", "observaciones", 255, "100px", "90px", "90px", "90px");

    private TextField especifica = ObjetosComunes.getTextField("Especificaciones", "especifica", 255, "100px");

    private DatePicker ulti_revi = ObjetosComunes.getDatePicker("Última Revision", null, LocalDate.now());

    private TextField farmacetuico = ObjetosComunes.getTextField("Farmacétuico", "farmacetuico", 25, "100px");

    private IntegerField existencias = ObjetosComunes.getIntegerField("Existencias");

    private TextField nlaboratorio = ObjetosComunes.getTextField("N Laboratorio", "nlaboratorio", 10, "100px");

    private TextField presentacion = ObjetosComunes.getTextField("Presentación", "presentación", 25, "100px");

    private TextField descripcion = ObjetosComunes.getTextField("Descripción", "descripción", 255, "100px");

    private TextField requisitos = ObjetosComunes.getTextField("Requisitos", "requisitos", 255, "100px");

    private TextField conservacion = ObjetosComunes.getTextField("Conservación", "conservación", 255, "100px");

    private FMMPrimas fMMPrimas = new FMMPrimas();

    private Binder<FMMPrimas> binder = new Binder<>();

    public static final String MOVIMIENTOGRABAR = "GRABAR";

    public static final String MOVIMIENTOBORRAR = "BORRAR";

    public FrmFMMprimas() {
        super();
        doHazFormulario();
    }

    public FrmFMMprimas(FMMPrimas fMMPrimas) {
        super();
        this.fMMPrimas = fMMPrimas;
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(fMMPrimas.getLabelFrom());

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

        cod_inte.setWidth("25px");
        cod_inte.addBlurListener(e -> {
            FMMPrimas fMMPrimasExiste = new FMMprimasDAO().getPorCodigo(cod_inte.getValue());
            if (fMMPrimasExiste != null) {
                fMMPrimas = fMMPrimasExiste;
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(fMMPrimas);
            }
        });

        binder.forField(cod_inte)
                .asRequired()
                .bind(FMMPrimas::getCod_inte, FMMPrimas::setCod_inte);

        binder.forField(producto)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMMPrimas::getProducto, FMMPrimas::setProducto);
        cod_labo.setWidth("25px");
        binder.forField(cod_labo)
                .asRequired()
                .bind(FMMPrimas::getCod_labo, FMMPrimas::setCod_labo);

        binder.forField(laboratorio)
                .asRequired()
                .bind(FMMPrimas::getLaboratorio, FMMPrimas::setLaboratorio);

        binder.forField(homologado)
                .bind(FMMPrimas::getHomologado, FMMPrimas::setHomologado);

        binder.forField(presentacion)
                .asRequired()
                .bind(FMMPrimas::getPresentacion, FMMPrimas::setPresentacion);

        binder.forField(conservacion)
                .asRequired()
                .bind(FMMPrimas::getConservacion, FMMPrimas::setConservacion);

        binder.forField(descripcion)
                .asRequired()
                .bind(FMMPrimas::getDescripcion, FMMPrimas::setDescripcion);

        binder.forField(requisitos)
                .asRequired()
                .bind(FMMPrimas::getRequisitos, FMMPrimas::setRequisitos);

        binder.forField(observaciones)
                .asRequired()
                .bind(FMMPrimas::getObservaciones, FMMPrimas::setObservaciones);

        binder.forField(existencias)
                .asRequired()
                .bind(FMMPrimas::getExistencias, FMMPrimas::setExistencias);

        binder.forField(stock_min)
                .asRequired()
                .bind(FMMPrimas::getStock_min, FMMPrimas::setStock_min);

        binder.forField(ulti_revi)
                .asRequired()
                .bind(FMMPrimas::getUlti_revi, FMMPrimas::setUlti_revi);

        binder.forField(farmacetuico)
                .asRequired()
                .bind(FMMPrimas::getFarmacetuico, FMMPrimas::setFarmacetuico);

        binder.readBean(fMMPrimas);
        doControlBotones(fMMPrimas);
    }

    @Override
    public void doControlBotones(Object obj) {
        super.doControlBotones(obj);

        if (obj == null || ((FMMPrimas) obj).getCod_inte() == null) {
            fMMPrimas = new FMMPrimas();
            binder.readBean(fMMPrimas);
            ulti_revi.setValue(LocalDate.now());
            cod_inte.setEnabled(true);
            cod_inte.focus();
        } else {
            this.fMMPrimas = (FMMPrimas) obj;
            binder.readBean(fMMPrimas);
            cod_inte.setEnabled(false);
            producto.focus();
        }
    }

    @Override
    /**
     *
     */
    public void doGrabar() {
        if (ulti_revi.getValue() != null) {
            ulti_revi.setValue(LocalDate.now());
        }

        if (binder.writeBeanIfValid(fMMPrimas)) {
            if (new FMMprimasDAO()
                    .doGrabaDatos(fMMPrimas) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }

            this.close();
        } else {
            BinderValidationStatus<FMMPrimas> validate = binder.validate();
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
                    new FMMprimasDAO().doBorraDatos(fMMPrimas);
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
