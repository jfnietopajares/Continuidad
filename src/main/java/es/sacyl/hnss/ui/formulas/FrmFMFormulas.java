/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.server.StreamResource.StreamSource;
import es.sacyl.hnss.dao.FMFormulaBiblioDAO;
import es.sacyl.hnss.dao.FMFormulaCompoDAO;
import es.sacyl.hnss.dao.FMFormulaElaboraDAO;
import es.sacyl.hnss.dao.FMFormulaMaterialDAO;
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaAutoriza;
import es.sacyl.hnss.entidades.FMFormulaTipo;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.listados.FMFormulaFicha;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.EmbeddedPdfDocument;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.FrmMasterLista;
import es.sacyl.hnss.ui.ObjetosComunes;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.io.File;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFMFormulas extends FrmMasterLista {

    private final IntegerField numero = ObjetosComunes.getIntegerField("Número");

    private TextField nombre = ObjetosComunes.getTextField("Nombre", "nombre", 50, "150px");

    private ComboBox<FMFormulaTipo> comboTipo = ObjetosComunes.getComboTipoForm("Tipo fórmula", null);

    private ComboBox<FMViasAdm> comboVias = ObjetosComunes.getComboVias("Vía  administración", null);

    private ComboBox<String> comboSteril = ObjetosComunes.getComboToString("Estéril", null, ObjetosComunes.SINO, "50px");

    private ComboBox<FMForma> comboForma = ObjetosComunes.getComboForma("Forma Farmacéutica", null);

    private IntegerField unidades_f = ObjetosComunes.getIntegerField("Unidades F");

    private TextField dunidades_f = ObjetosComunes.getTextField("D.Unidades F", "dunidades_f", 50, "150px");

    private TextField caducidad = ObjetosComunes.getTextField("Caducidad", "Caducidad", 15, "150px");

    private TextArea indicacion = ObjetosComunes.getTextArea("Indicacion", "indicación", null, "600px", "60px", "100px", "100px");

    private TextField conservacion = ObjetosComunes.getTextField("Conservación", "conservación", 15, "150px");

    private TextField controles = ObjetosComunes.getTextField("Controles", "controles", 15, "150px");

    private ComboBox<FMFormulaAutoriza> autorizacion = ObjetosComunes.getComboAutoriza("Autoización", null);

    private TextArea observaciones = ObjetosComunes.getTextArea("Observaciones", "observaciones", null, "600px", "60px", "100px", "100px");

    private TextField realizado = ObjetosComunes.getTextField("Realizado", "Realizado", 15, "150px");

    private DatePicker fecha_r = ObjetosComunes.getDatePicker("Fecha realización", null, null);

    private TextField actualizado = ObjetosComunes.getTextField("Actualizado", "actualizado", 15, "150px");

    private DatePicker fecha_a = ObjetosComunes.getDatePicker("Fecha actualización", null, null);

    private TextField etiqueta1 = ObjetosComunes.getTextField("Etiqueta 1", "", 15, "150px");

    private TextField etiqueta2 = ObjetosComunes.getTextField("Etiqueta 2", "", 15, "150px");

    private ComboBox<String> comboPedirWeb = ObjetosComunes.getComboToString("Pedir Web", null, ObjetosComunes.SINO, "50px");

    private FMFormula fMFormula = new FMFormula();

    private Binder<FMFormula> binder = new Binder<>();

    private Grid<FMFormula> grid = null;

    //   private HorizontalLayout contenedorFormula = new HorizontalLayout();
    //   private HorizontalLayout contenedorBotones1 = new HorizontalLayout();
    // private VerticalLayout contenedorIzquierda = new VerticalLayout();
    //private VerticalLayout contenedorDerecha = new VerticalLayout();
    private Button blibliografia = ObjetosComunes.getBoton("Bibliog", null, VaadinIcon.BOOK.create());
    private Button composicion = ObjetosComunes.getBoton("Compo", null, VaadinIcon.FLASK.create());
    private Button elaboración = ObjetosComunes.getBoton("Elabor", null, VaadinIcon.COGS.create());
    private Button material = ObjetosComunes.getBoton("Material", null, VaadinIcon.WRENCH.create());

    String nombrePdfAbsoluto = null;

    String nombrePdfRelativo = null;

    public FrmFMFormulas() {
        super("1200px");
        doHazFormulario();
    }

    public FrmFMFormulas(FMFormula fMFormula) {
        super("1200px");
        this.fMFormula = fMFormula;

        this.fMFormula.setLisaBibliografias(new FMFormulaBiblioDAO().getListaBiblio(fMFormula));
        this.fMFormula.setLisaComposiciones(new FMFormulaCompoDAO().getListaCompos(fMFormula));
        this.fMFormula.setLisaElaboraciones(new FMFormulaElaboraDAO().getListaElabora(fMFormula));
        this.fMFormula.setLisaMaterial(new FMFormulaMaterialDAO().getListaMateriales(fMFormula));
        nombrePdfAbsoluto = Constantes.DIRECTORIOREPORTABSOLUTEPATH + fMFormula.getNumero() + ".pdf";
        nombrePdfRelativo = Constantes.DIRECTORIOREPORTSRELATIVEPATH + fMFormula.getNumero() + ".pdf";
        //  nombrePdf = "./" + fMFormula.getNombre().replace(" ", "") + ".pdf";
        InputStream is = new FMFormulaFicha(fMFormula).getStream();
        File infPdf = Utilidades.iStoFile(is, nombrePdfAbsoluto);
        doHazFormulario();
    }

    public void doHazFormulario() {
        contenedorBotones.add(blibliografia, composicion, elaboración, material);

        if (fMFormula.getNumero() == null) {
            blibliografia.setEnabled(false);
            composicion.setEnabled(false);
            elaboración.setEnabled(false);
            material.setEnabled(false);
            // contenedorBotones1.setVisible(false);
        }
        blibliografia.addClickListener(e -> {
            FrmFMFormulasBiblio frmFarmaFMFormulasBiblio = new FrmFMFormulasBiblio(fMFormula);
            doVentanaModal(frmFarmaFMFormulasBiblio);

        });
        elaboración.addClickListener(e -> {
            FrmFMFormulasElabora frmFarmaFMFormulasElabora = new FrmFMFormulasElabora(fMFormula);
            doVentanaModal(frmFarmaFMFormulasElabora);

        });
        material.addClickListener(e -> {
            FrmFMFormulasMaterial frmFMFormulasMaterial = new FrmFMFormulasMaterial(fMFormula);
            doVentanaModal(frmFMFormulasMaterial);

        });
        composicion.addClickListener(e -> {
            FrmFMFormulasCompo frmFMFormulasCompo = new FrmFMFormulasCompo(fMFormula);
            doVentanaModal(frmFMFormulasCompo);

        });

        titulo.setText(FMFormula.getLabelFrom());

        contenedorFormulario.addClassName(Lumo.LIGHT);
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

        if (fMFormula == null || fMFormula.getNumero() == null) {
            numero.setEnabled(true);
        } else {
            numero.setEnabled(false);
        }

        numero.addBlurListener(e -> {
            FMFormula fMFormulaExiste = new FMFormulasDAO().getPorCodigo(numero.getValue());
            if (fMFormulaExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                nombre.setValue(fMFormulaExiste.getNombre());
            }
        });

        binder.forField(numero)
                .asRequired()
                .bind(FMFormula::getNumero, FMFormula::setNumero);

        binder.forField(nombre)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMFormula::getNombre, FMFormula::setNombre);

        binder.forField(comboPedirWeb)
                .asRequired()
                .bind(FMFormula::getPedirweb, FMFormula::setPedirweb);

        binder.forField(comboTipo)
                .asRequired()
                .bind(FMFormula::getTipo, FMFormula::setTipo);

        binder.forField(comboVias)
                .asRequired()
                .bind(FMFormula::getVia, FMFormula::setVia);

        binder.forField(comboSteril)
                .asRequired()
                .bind(FMFormula::getEsteril, FMFormula::setEsteril);

        binder.forField(comboForma)
                .asRequired()
                .bind(FMFormula::getForma, FMFormula::setForma);

        binder.forField(unidades_f)
                .asRequired()
                .bind(FMFormula::getUnidades_f, FMFormula::setUnidades_f);

        binder.forField(dunidades_f)
                .asRequired()
                .bind(FMFormula::getDunidades_f, FMFormula::setDunidades_f);

        binder.forField(caducidad)
                .asRequired()
                .bind(FMFormula::getCaducidad, FMFormula::setCaducidad);

        binder.forField(indicacion)
                .asRequired()
                .bind(FMFormula::getIndicacion, FMFormula::setIndicacion);

        binder.forField(conservacion)
                .bind(FMFormula::getConservacion, FMFormula::setConservacion);

        binder.forField(controles)
                .bind(FMFormula::getControles, FMFormula::setControles);

        binder.forField(autorizacion)
                .bind(FMFormula::getP_autorizado, FMFormula::setP_autorizado);

        binder.forField(observaciones)
                .bind(FMFormula::getObservaciones, FMFormula::setObservaciones);

        binder.forField(realizado)
                .asRequired()
                .bind(FMFormula::getRealizado, FMFormula::setRealizado);

        binder.forField(fecha_r)
                .asRequired()
                .bind(FMFormula::getFecha_r, FMFormula::setFecha_r);

        binder.forField(actualizado)
                .bind(FMFormula::getActualizado, FMFormula::setActualizado);

        binder.forField(fecha_a)
                .bind(FMFormula::getFecha_a, FMFormula::setFecha_a);

        binder.forField(etiqueta1)
                .bind(FMFormula::getEtiqueta1, FMFormula::setEtiqueta1);

        binder.forField(etiqueta2)
                .bind(FMFormula::getEtiqueta2, FMFormula::setEtiqueta2);

        binder.readBean(fMFormula);
        doControlBotones(fMFormula.getNumero());
        doVerPdf();

    }

    public void doVerPdf() {
        if (nombrePdfRelativo != null) {
            contenedorDerecha.add(new EmbeddedPdfDocument(nombrePdfRelativo));
        }
        /*
        File fichero = new File("pdf.pdf");
        Embedded pdf = new Embedded("", new FileResource(fichero));
        pdf.setMimeType("application/pdf");
        pdf.setType(Embedded.TYPE_BROWSER);
        pdf.setWidth("750px");
        pdf.setHeight("610px");

        WTPdfViewer pdfViewer = new WTPdfViewer();
         */
    }

    class InputStreamSource implements StreamSource {

        private final InputStream data;

        public InputStreamSource(InputStream data) {
            super();
            this.data = data;
        }

        @Override
        public InputStream getStream() {
            return data;
        }

    }

    public void doVentanaModal(FrmMasterLista frmMasterLista) {
        frmMasterLista.open();
        frmMasterLista.addDialogCloseActionListener(e -> {
            //   doActualizaGrid();
        }
        );
        frmMasterLista.addDetachListener(e -> {
            //  doActualizaGrid();
        });
    }

    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(fMFormula)) {

            if (new FMFormulasDAO().doGrabaDatos(fMFormula) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            this.close();
        } else {
            BinderValidationStatus<FMFormula> validate = binder.validate();
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
                    new FMFormulasDAO().doBorraDatos(fMFormula);
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
