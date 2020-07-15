/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FMFormulaElaboraDAO;
import es.sacyl.hnss.dao.FMFormulaMaterialDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaElabora;
import es.sacyl.hnss.entidades.FMFormulaMeterial;
import es.sacyl.hnss.entidades.FMInstrumento;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.FrmMasterLista;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFMFormulasMaterial extends FrmMasterLista {

    private IntegerField formula = ObjetosComunes.getIntegerField("Código");

    private IntegerField linea = ObjetosComunes.getIntegerField("Orden");
    private IntegerField unidades = ObjetosComunes.getIntegerField("Unidades");

    private TextField comentario = ObjetosComunes.getTextField("Comentario", "", 50, "100px");

    private ComboBox<FMInstrumento> comboMaterial = ObjetosComunes.getComboInstrumento("Material", null);

    private TextField nombre = ObjetosComunes.getTextField(null, null, 50, "100px");

    private FMFormula fMFormula;

    private FMFormulaMeterial fMFormulaMeterial = new FMFormulaMeterial();

    private Binder<FMFormulaMeterial> binder = new Binder();

    private Grid<FMFormulaMeterial> grid = new Grid<>();

    public FrmFMFormulasMaterial(FMFormulaMeterial fMFormulaMeterial, FMFormula fMFormula) {
        super("1000px");

        this.fMFormulaMeterial = fMFormulaMeterial;

        this.fMFormula = fMFormula;

        doHazFormulario();
    }

    public FrmFMFormulasMaterial(FMFormula fMFormula) {
        super("1000px");

        this.fMFormula = fMFormula;

        doHazFormulario();
    }

    public void doHazFormulario() {
        this.setWidth("900px");
        //  this.setMaxWidth("900px");
        this.contenedorVentana.setWidth("900px");
        this.setSizeFull();

        titulo.setText(FMFormulaMeterial.getLabelFrom());

        contenedorDerecha.add(grid);

        contenedorFormulario.add(nombre, comboMaterial, linea, unidades, comentario);

        nombre.setValue(fMFormula.getNombre());
        grid.addColumn(FMFormulaMeterial::getLinea).setHeader("Orden");
        grid.addColumn(FMFormulaMeterial::getInstrumentoNombre).setHeader("Material").setWidth("300px");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        //   grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addItemClickListener(event
                -> {
            fMFormulaMeterial = event.getItem();
            binder.readBean(fMFormulaMeterial);
        }
        );
        grid.setItems(
                new FMFormulaMaterialDAO().getListaMateriales(fMFormula));

        doActualizaGrid();

        nombre.setReadOnly(
                true);

        formula.setValue(fMFormula.getNumero());

        linea.addBlurListener(e
                -> {
            FMFormulaMeterial fMFormulaElaboraExs = new FMFormulaMaterialDAO().getPorCodigo(fMFormula, comboMaterial.getValue(), linea.getValue());
            if (fMFormulaElaboraExs != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                comentario.setValue(fMFormulaElaboraExs.getComentario());
            }
        }
        );

        binder.forField(comboMaterial)
                .asRequired()
                .bind(FMFormulaMeterial::getInstrumento, FMFormulaMeterial::setInstrumento);

        binder.forField(linea)
                .asRequired()
                .bind(FMFormulaMeterial::getLinea, FMFormulaMeterial::setLinea);
        binder.forField(unidades)
                .asRequired()
                .bind(FMFormulaMeterial::getUnidades, FMFormulaMeterial::setUnidades);
        binder.forField(comentario)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMFormulaMeterial::getComentario, FMFormulaMeterial::setComentario);

        binder.readBean(fMFormulaMeterial);
    }

    public void doActualizaGrid() {
        grid.setItems(new FMFormulaMaterialDAO().getListaMateriales(fMFormula));
    }

    @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(fMFormulaMeterial)) {
            fMFormulaMeterial.setFormula(fMFormula.getNumero());

            if (new FMFormulaMaterialDAO().doGrabaDatos(fMFormula, fMFormulaMeterial) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            fMFormulaMeterial = new FMFormulaMeterial();
            binder.readBean(fMFormulaMeterial);
            formula.setValue(fMFormula.getNumero());
            doActualizaGrid();
        } else {
            BinderValidationStatus<FMFormulaMeterial> validate = binder.validate();
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
                    new FMFormulaMaterialDAO().doBorraDatos(fMFormulaMeterial);
                    Notification.show(FrmMaster.AVISODATOBORRADO);
                    this.close();
                });
        dialog.open();
        dialog.addDialogCloseActionListener(e -> {
            this.close();
        });
        throw new UnsupportedOperationException("Not  yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doAyuda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
