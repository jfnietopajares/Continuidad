/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.theme.lumo.Lumo;
import es.sacyl.hnss.dao.FarmaFMFormaDAO;
import es.sacyl.hnss.dao.FarmaFMFormulasDAO;
import es.sacyl.hnss.entidades.FarmaFMForma;
import es.sacyl.hnss.entidades.FarmaFMFormula;
import es.sacyl.hnss.entidades.FarmaFMFormulaAutoriza;
import es.sacyl.hnss.entidades.FarmaFMFormulaTipo;
import es.sacyl.hnss.entidades.FarmaFMInstrumento;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFarmaFMFormulas extends FrmMaster {

    private final IntegerField numero = ObjetosComunes.getIntegerField("Número");

    private TextField nombre = ObjetosComunes.getTextField("Nombre", "nombre", 50, "150px");

    private ComboBox<FarmaFMFormulaTipo> comboTipo = ObjetosComunes.getComboTipoForm("Tipo fórmula", null);

    private ComboBox<FarmaFMViasAdm> comboVias = ObjetosComunes.getComboVias("Vía  administración", null);

    private ComboBox<String> comboSteril = ObjetosComunes.getComboToString("Estéril", null, ObjetosComunes.SINO, "50px");

    private ComboBox<FarmaFMForma> comboForma = ObjetosComunes.getComboForma("Forma Farmacéutica", null);

    private IntegerField unidades_f = ObjetosComunes.getIntegerField("Unidades F");

    private TextField dunidades_f = ObjetosComunes.getTextField("D.Unidades F", "dunidades_f", 50, "150px");

    private TextField caducidad = ObjetosComunes.getTextField("Caducidad", "Caducidad", 15, "150px");

    private TextArea indicacion = ObjetosComunes.getTextArea("Indicacion", "indicación", null, "600px", "60px");

    private TextField conservacion = ObjetosComunes.getTextField("Conservación", "conservación", 15, "150px");

    private TextField controles = ObjetosComunes.getTextField("Controles", "controles", 15, "150px");

    private ComboBox<FarmaFMFormulaAutoriza> autorizacion = ObjetosComunes.getComboAutoriza("Autoización", null);

    private TextArea observaciones = ObjetosComunes.getTextArea("Observaciones", "observaciones", null, "600px", "60px");

    private TextField realizado = ObjetosComunes.getTextField("Realizado", "Realizado", 15, "150px");

    private DatePicker fecha_r = ObjetosComunes.getDatePicker("Fecha realización", null, null);

    private TextField actualizado = ObjetosComunes.getTextField("Actualizado", "actualizado", 15, "150px");

    private DatePicker fecha_a = ObjetosComunes.getDatePicker("Fecha actualización", null, null);

    private TextField etiqueta1 = ObjetosComunes.getTextField("Etiqueta 1", "", 15, "150px");

    private TextField etiqueta2 = ObjetosComunes.getTextField("Etiqueta 2", "", 15, "150px");

    private ComboBox<String> comboPedirWeb = ObjetosComunes.getComboToString("Pedir Web", null, ObjetosComunes.SINO, "50px");

    private FarmaFMFormula farmaFMFormula = new FarmaFMFormula();

    private Binder<FarmaFMFormula> binder = new Binder<>();

    private Grid<FarmaFMFormula> grid = null;

    private HorizontalLayout contenedorFormula = new HorizontalLayout();
    private HorizontalLayout contenedorBotones1 = new HorizontalLayout();
    private VerticalLayout contenedorIzquierda = new VerticalLayout();
    private VerticalLayout contenedorDerecha = new VerticalLayout();

    private Button blibliografia = ObjetosComunes.getBoton("Bibliog", null, VaadinIcon.BOOK.create());
    private Button composicion = ObjetosComunes.getBoton("Compo", null, VaadinIcon.FLASK.create());
    private Button elaboración = ObjetosComunes.getBoton("Elabor", null, VaadinIcon.COGS.create());
    private Button material = ObjetosComunes.getBoton("Material", null, VaadinIcon.WRENCH.create());

    public FrmFarmaFMFormulas() {
        super();
        doHazFormulario();
    }

    public FrmFarmaFMFormulas(FarmaFMFormula farmaFMFormula) {
        super();
        this.farmaFMFormula = farmaFMFormula;
        doHazFormulario();
    }

    public void doHazFormulario() {
        this.removeAll();
        this.add(contenedorFormula);

        contenedorFormula.add(contenedorIzquierda, contenedorDerecha);
        contenedorIzquierda.add(contenedorTitulo, contenedorBotones, contenedorBotones1, contenedorFormulario);
        contenedorBotones1.add(blibliografia, composicion, elaboración, material);
        titulo.setText(FarmaFMFormula.getLabelFrom());

        contenedorFormulario.addClassName(Lumo.LIGHT);
        //  contenedorFormulario.getStyle().set("theme", Lumo.DARK);
        //     contenedorFormulario.addClassName("padding", Uniform.M);
        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("150px", 1),
                new FormLayout.ResponsiveStep("100px", 2),
                new FormLayout.ResponsiveStep("50px", 3),
                new FormLayout.ResponsiveStep("100px", 4));

        contenedorFormulario.setMaxWidth("1000px");
        contenedorFormulario.add(numero, nombre, comboPedirWeb);
        contenedorFormulario.setColspan(nombre, 2);

        contenedorFormulario.add(comboTipo, comboVias, comboSteril);
        contenedorFormulario.setColspan(comboVias, 2);

        contenedorFormulario.add(comboForma, unidades_f, dunidades_f, caducidad);

        contenedorFormulario.add(indicacion);
        contenedorFormulario.setColspan(indicacion, 4);

        contenedorFormulario.add(conservacion);
        contenedorFormulario.setColspan(conservacion, 4);

        contenedorFormulario.add(controles, autorizacion);
        contenedorFormulario.setColspan(controles, 3);

        contenedorFormulario.add(observaciones);
        contenedorFormulario.setColspan(observaciones, 4);

        contenedorFormulario.add(realizado, fecha_r, actualizado, fecha_a);

        contenedorFormulario.add(etiqueta1, etiqueta2);
        contenedorFormulario.setColspan(etiqueta1, 2);
        contenedorFormulario.setColspan(etiqueta2, 2);

        if (farmaFMFormula == null || farmaFMFormula.getNumero() == null) {
            numero.setEnabled(true);
        } else {
            numero.setEnabled(false);
        }

        numero.addBlurListener(e -> {
            FarmaFMFormula farmaFMFormulaExiste = new FarmaFMFormulasDAO().getPorCodigo(numero.getValue());
            if (farmaFMFormulaExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                nombre.setValue(farmaFMFormulaExiste.getNombre());
            }
        });

        binder.forField(numero)
                .asRequired()
                .bind(FarmaFMFormula::getNumero, FarmaFMFormula::setNumero);

        binder.forField(nombre)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FarmaFMFormula::getNombre, FarmaFMFormula::setNombre);

        binder.forField(comboPedirWeb)
                .asRequired()
                .bind(FarmaFMFormula::getPedirweb, FarmaFMFormula::setPedirweb);

        binder.forField(comboTipo)
                .asRequired()
                .bind(FarmaFMFormula::getTipo, FarmaFMFormula::setTipo);

        binder.forField(comboVias)
                .asRequired()
                .bind(FarmaFMFormula::getVia, FarmaFMFormula::setVia);

        binder.forField(comboSteril)
                .asRequired()
                .bind(FarmaFMFormula::getEsteril, FarmaFMFormula::setEsteril);

        binder.forField(comboForma)
                .asRequired()
                .bind(FarmaFMFormula::getForma, FarmaFMFormula::setForma);

        binder.forField(unidades_f)
                .asRequired()
                .bind(FarmaFMFormula::getUnidades_f, FarmaFMFormula::setUnidades_f);

        binder.forField(dunidades_f)
                .asRequired()
                .bind(FarmaFMFormula::getDunidades_f, FarmaFMFormula::setDunidades_f);

        binder.forField(caducidad)
                .asRequired()
                .bind(FarmaFMFormula::getCaducidad, FarmaFMFormula::setCaducidad);

        binder.forField(indicacion)
                .asRequired()
                .bind(FarmaFMFormula::getIndicacion, FarmaFMFormula::setIndicacion);

        binder.forField(conservacion)
                .bind(FarmaFMFormula::getConservacion, FarmaFMFormula::setConservacion);

        binder.forField(controles)
                .bind(FarmaFMFormula::getControles, FarmaFMFormula::setControles);

        binder.forField(autorizacion)
                .bind(FarmaFMFormula::getP_autorizado, FarmaFMFormula::setP_autorizado);

        binder.forField(observaciones)
                .bind(FarmaFMFormula::getObservaciones, FarmaFMFormula::setObservaciones);

        binder.forField(realizado)
                .asRequired()
                .bind(FarmaFMFormula::getRealizado, FarmaFMFormula::setRealizado);

        binder.forField(fecha_r)
                .asRequired()
                .bind(FarmaFMFormula::getFecha_r, FarmaFMFormula::setFecha_r);

        binder.forField(actualizado)
                .bind(FarmaFMFormula::getActualizado, FarmaFMFormula::setActualizado);

        binder.forField(fecha_a)
                .bind(FarmaFMFormula::getFecha_a, FarmaFMFormula::setFecha_a);

        binder.forField(etiqueta1)
                .bind(FarmaFMFormula::getEtiqueta1, FarmaFMFormula::setEtiqueta1);

        binder.forField(etiqueta2)
                .bind(FarmaFMFormula::getEtiqueta2, FarmaFMFormula::setEtiqueta2);

        binder.readBean(farmaFMFormula);

    }

    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(farmaFMFormula)) {

            if (new FarmaFMFormulasDAO().doGrabaDatos(farmaFMFormula) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            this.close();
        } else {
            BinderValidationStatus<FarmaFMFormula> validate = binder.validate();
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
                    new FarmaFMFormulasDAO().doBorraDatos(farmaFMFormula);
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
