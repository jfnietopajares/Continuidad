/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FMFormulaFrabicarDAO;
import es.sacyl.hnss.dao.FMMprimasDAO;
import es.sacyl.hnss.dao.FMMprimasEntraDAO;
import es.sacyl.hnss.entidades.FMFormulaFrabicar;
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMViasAdm;
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
public class FrmFMMprimasEntradas extends FrmMasterLista {

    //   private IntegerField cod_inte = ObjetosComunes.getIntegerField("Código");
    //  private TextField producto = ObjetosComunes.getTextField("Nombre prodcuto", "nombre", 50, "100px");
    private DatePicker fecha = ObjetosComunes.getDatePicker("Fecha entrada", null, LocalDate.now());
    private IntegerField registro = ObjetosComunes.getIntegerField("Registro");
    private IntegerField envases = ObjetosComunes.getIntegerField("Nº Envases");

    private TextField lote = ObjetosComunes.getTextField("Lote", "lote", 50, "100px");

    private Checkbox verificacion = new Checkbox("Verificacion");

    private TextField ctrlAnalitico = ObjetosComunes.getTextField("Control analitico", "control analitico", 50, "100px");
    private TextField farmaceutico = ObjetosComunes.getTextField("Farmaceutico", "farmaceutico", 50, "100px");
    private TextField caducidad = ObjetosComunes.getTextField("Caducidad", "caducidad", 50, "100px");

    private IntegerField numero = ObjetosComunes.getIntegerField("Número");

    private FMMPrimas fMMPrimas = new FMMPrimas();

    private FMMPrimasEntrada fMMPrimasEntrada = new FMMPrimasEntrada();

    private FMMPrimasEntrada fMMPrimasEntradaAnterior = new FMMPrimasEntrada();

    private ComboBox<FMMPrimas> comboMPrimas = ObjetosComunes.getComboMPrimas(null, null);

    private Binder<FMMPrimasEntrada> binder = new Binder<>();

    private Grid<FMMPrimasEntrada> grid = new Grid<>();

    public FrmFMMprimasEntradas() {
        super();
        doHazFormulario();
    }

    public FrmFMMprimasEntradas(FMMPrimasEntrada fMMPrimasEntrada) {
        super();
        this.fMMPrimasEntrada = fMMPrimasEntrada;

        this.fMMPrimasEntradaAnterior = fMMPrimasEntrada;
        //      cod_inte.setValue(fMMPrimasEntrada.getCod_inte());
        //    producto.setValue(fMMPrimasEntrada.getProducto());

        doHazFormulario();
    }

    public FrmFMMprimasEntradas(FMMPrimas fMMPrimas) {
        super();
        if (fMMPrimas != null) {
            this.fMMPrimas = fMMPrimas;
            this.fMMPrimasEntrada = new FMMPrimasEntrada(fMMPrimas);
        }
        doHazFormulario();
    }

    public void doHazFormulario() {
        titulo.setText(fMMPrimasEntrada.getLabelFrom());

        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("5px", 1),
                new FormLayout.ResponsiveStep("100px", 2),
                new FormLayout.ResponsiveStep("100px", 3));

        contenedorFormulario.setMaxWidth("600px");
        contenedorFormulario.add(comboMPrimas, 3);
        //  contenedorFormulario.add(cod_inte, producto);
        // contenedorFormulario.setColspan(producto, 2);
        contenedorFormulario.add(numero, fecha, registro);
        contenedorFormulario.add(envases, lote, verificacion);
        contenedorFormulario.add(caducidad);
        contenedorFormulario.add(ctrlAnalitico, 2);
        contenedorFormulario.add(farmaceutico, 3);

        contenedorDerecha.add(grid);

        comboMPrimas.addValueChangeListener(e -> {
            fMMPrimasEntrada.setCod_inte(comboMPrimas.getValue().getCod_inte());
            fMMPrimasEntrada.setProducto(comboMPrimas.getValue().getProducto());
            numero.setValue(new FMMprimasEntraDAO().getNumeroSiguiente(fMMPrimasEntrada));
            fecha.setValue(LocalDate.now());
            registro.focus();
            //producto.setReadOnly(true);
        });

        binder.forField(numero)
                .asRequired()
                .bind(FMMPrimasEntrada::getNumero, FMMPrimasEntrada::setNumero);
        binder.forField(fecha)
                .asRequired()
                .bind(FMMPrimasEntrada::getFecha, FMMPrimasEntrada::setFecha);
        binder.forField(registro)
                .asRequired()
                .bind(FMMPrimasEntrada::getRegistro, FMMPrimasEntrada::setRegistro);
        binder.forField(envases)
                .asRequired()
                .bind(FMMPrimasEntrada::getEnvases, FMMPrimasEntrada::setEnvases);
        binder.forField(lote)
                .bind(FMMPrimasEntrada::getLote, FMMPrimasEntrada::setLote);
        binder.forField(verificacion)
                .bind(FMMPrimasEntrada::getVerificacion, FMMPrimasEntrada::setVerificacion);
        binder.forField(caducidad)
                .bind(FMMPrimasEntrada::getCaducidad, FMMPrimasEntrada::setCaducidad);
        binder.forField(ctrlAnalitico)
                .bind(FMMPrimasEntrada::getCtrlAnalitico, FMMPrimasEntrada::setCtrlAnalitico);
        binder.forField(farmaceutico)
                .bind(FMMPrimasEntrada::getFarmaceutico, FMMPrimasEntrada::setFarmaceutico);

        binder.readBean(fMMPrimasEntrada);
        doControlBotones(fMMPrimasEntrada);

        grid.addColumn(FMMPrimasEntrada::getNumero).setHeader("Orden");
        grid.addColumn(FMMPrimasEntrada::getFecha).setHeader("Fecha");
        grid.addColumn(FMMPrimasEntrada::getEnvases).setHeader("Envases");
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

    public void doActualizaGrid() {
        grid.setItems(new FMMprimasEntraDAO().getListaEntradasMP(null, null, fMMPrimas));
    }

    @Override
    public void doControlBotones(Object obj) {
        super.doControlBotones(obj);
        if (obj == null || ((FMMPrimasEntrada) obj).getCod_inte() == null) {
            fMMPrimasEntrada = new FMMPrimasEntrada();
            binder.readBean(fMMPrimasEntrada);
            numero.setEnabled(true);
            numero.focus();
            if (numero.isEmpty()) {
                numero.setValue(new FMMprimasEntraDAO().getNumeroSiguiente(fMMPrimasEntrada));
                fecha.setValue(LocalDate.now());
                registro.focus();
            }
        } else {
            fMMPrimasEntrada = (FMMPrimasEntrada) obj;
            binder.readBean(fMMPrimasEntrada);
            comboMPrimas.setReadOnly(true);
            comboMPrimas.setValue((FMMPrimasEntrada) obj);
            numero.setReadOnly(true);
            registro.focus();
        }

    }

    @Override
    public void doGrabar() {

        if (binder.writeBeanIfValid(fMMPrimasEntrada)) {
            if (new FMMprimasEntraDAO().doGrabaDatos(fMMPrimasEntrada) == true) {
                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();
                doActualizaExistencias(FrmFMMprimas.MOVIMIENTOGRABAR);
            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }

            this.close();
        } else {
            BinderValidationStatus<FMMPrimasEntrada> validate = binder.validate();
            String errorText = validate.getFieldValidationStatuses()
                    .stream().filter(BindingValidationStatus::isError)
                    .map(BindingValidationStatus::getMessage)
                    .map(Optional::get).distinct()
                    .collect(Collectors.joining(", "));
            Notification.show(FrmMaster.AVISODATOERRORVALIDANO + errorText);
        }
    }

    public void doActualizaExistencias(String tipoMovimiento) {
        Integer variacionExistencias = fMMPrimasEntrada.getVariacionExistencias(fMMPrimasEntradaAnterior.getEnvases(), tipoMovimiento);

        /*
        // entrada nueva
        if (fMMPrimasEntradaAnterior.getNumero() == null) {
            variacionExistencias = fMMPrimasEntrada.getEnvases();
        } else if (fMMPrimasEntradaAnterior.getEnvases() != fMMPrimasEntrada.getEnvases()) {
            // edita entrada  cambia envase
            if (fMMPrimasEntradaAnterior.getEnvases() > fMMPrimasEntrada.getEnvases()) {
                variacionExistencias = fMMPrimasEntradaAnterior.getEnvases() - fMMPrimasEntrada.getEnvases();
            } else {
                variacionExistencias = fMMPrimasEntradaAnterior.getEnvases() + fMMPrimasEntrada.getEnvases();
            }
        }
         */
        if (variacionExistencias != 0) {
            if (new FMMprimasDAO().doActualizaExistencias(fMMPrimasEntrada, variacionExistencias)) {
                Notification.show(FrmMaster.AVISODATOALMACENADO);
            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD + " Sin actualizar existenicas", 1000, Notification.Position.MIDDLE)).open();
            }
        }
    }

    @Override
    public void doBorrar() {
        ConfirmDialog dialog = new ConfirmDialog("Borrado del registro actual",
                "Seguro que quires borrar el dato actual. Numero de entrada : " + fMMPrimasEntradaAnterior.getNumero(),
                "Borrar", this::onDelete);

        dialog.open();

        dialog.addDialogCloseActionListener(e -> {
            this.close();
        });

    }

    public void onDelete() {
        if (new FMMprimasEntraDAO().doBorraDatos(fMMPrimasEntrada) == true) {
            Notification.show(FrmMaster.AVISODATOBORRADO);
            // descuenta existencias
            doActualizaExistencias(FrmFMMprimas.MOVIMIENTOBORRAR);
            /*
            if (new FMMprimasDAO().doActualizaExistencias(fMMPrimasEntrada, fMMPrimasEntrada.getNumero() * (-1)) == true) {
                Notification.show("Existencias actualizadas");
            } else {
                Notification.show("Error actualizando existencias actualizadas");
            }
             */
            this.close();
        } else {
            Notification.show(FrmMaster.AVISODATOERRORBORRADO);
        }
    }

    public void onDiscard() {

    }

    public void onCancel() {

    }

    @Override
    public void doAyuda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
