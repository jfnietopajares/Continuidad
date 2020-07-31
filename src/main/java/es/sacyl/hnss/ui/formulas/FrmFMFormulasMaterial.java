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
import es.sacyl.hnss.dao.FMFormulaMaterialDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaElabora;
import es.sacyl.hnss.entidades.FMFormulaMaterial;
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

    private FMFormulaMaterial fMFormulaMeterial = new FMFormulaMaterial();

    private Binder<FMFormulaMaterial> binder = new Binder();

    private Grid<FMFormulaMaterial> grid = new Grid<>();

    public FrmFMFormulasMaterial(FMFormulaMaterial fMFormulaMeterial, FMFormula fMFormula) {
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

        titulo.setText(FMFormulaMaterial.getLabelFrom());

        contenedorDerecha.add(grid);

        contenedorFormulario.add(nombre, comboMaterial, linea, unidades, comentario);

        nombre.setValue(fMFormula.getNombre());
        grid.addColumn(FMFormulaMaterial::getLinea).setHeader("Orden");
        grid.addColumn(FMFormulaMaterial::getInstrumentoNombre).setHeader("Material").setWidth("300px");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addItemClickListener(event
                -> {
            doControlEventosRecpera(event.getItem());
        }
        );
        grid.setItems(
                new FMFormulaMaterialDAO().getListaMateriales(fMFormula));

        doActualizaGrid();

        nombre.setReadOnly(
                true);

        formula.setValue(fMFormula.getNumero());

        comboMaterial.addValueChangeListener(e -> {
            FMFormulaMaterial fMFormulaMaterialExs = new FMFormulaMaterialDAO().getPorCodigo(fMFormula, comboMaterial.getValue(), linea.getValue());
            if (fMFormulaMaterialExs != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(fMFormulaMaterialExs);
                fMFormulaMeterial = fMFormulaMaterialExs;
            }
        });
        linea.addBlurListener(e
                -> {
            FMFormulaMaterial fMFormulaMaterialExs = new FMFormulaMaterialDAO().getPorCodigo(fMFormula, comboMaterial.getValue(), linea.getValue());
            if (fMFormulaMaterialExs != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                doControlEventosRecpera(fMFormulaMaterialExs);
            }
        }
        );

        binder.forField(comboMaterial)
                .asRequired()
                .bind(FMFormulaMaterial::getInstrumento, FMFormulaMaterial::setInstrumento);

        binder.forField(linea)
                .asRequired()
                .bind(FMFormulaMaterial::getLinea, FMFormulaMaterial::setLinea);
        binder.forField(unidades)
                .asRequired()
                .bind(FMFormulaMaterial::getUnidades, FMFormulaMaterial::setUnidades);
        binder.forField(comentario)
                .asRequired()
                .withValidator(new StringLengthValidator(
                        FrmMaster.AVISODATOABLIGATORIO, 1, 50))
                .bind(FMFormulaMaterial::getComentario, FMFormulaMaterial::setComentario);

        binder.readBean(fMFormulaMeterial);
        comboMaterial.focus();
        doControlBotones(fMFormulaMeterial.getLinea());
    }
    //   grid.setSelectionMode(Grid.SelectionMode.NONE);

    public void doControlEventosRecpera(FMFormulaMaterial fMFormulaMeterial) {
        this.fMFormulaMeterial = fMFormulaMeterial;
        binder.readBean(fMFormulaMeterial);
        comboMaterial.setEnabled(false);
        linea.setEnabled(false);
        unidades.focus();
        botonBorrar.setEnabled(true);
    }

    public void doControlEventosNuevo() {
        fMFormulaMeterial = new FMFormulaMaterial();
        fMFormulaMeterial.setFormula(fMFormula.getNumero());

        binder.readBean(fMFormulaMeterial);
        comboMaterial.setEnabled(true);
        linea.setEnabled(true);
        comboMaterial.focus();
        botonBorrar.setEnabled(false);
        doActualizaGrid();
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

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            doControlEventosNuevo();
        } else {
            BinderValidationStatus<FMFormulaMaterial> validate = binder.validate();
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
                    doControlEventosNuevo();
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
