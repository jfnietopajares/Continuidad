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
import es.sacyl.hnss.dao.FarmaFMMprimasDAO;
import es.sacyl.hnss.dao.FarmaFMMprimasEntraDAO;
import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
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
public class FrmFarmaFMMprimasEntradas extends FrmMaster {

    private IntegerField cod_inte = new IntegerField("Código");
    private TextField producto = ObjetosComunes.getTextField("Nombre prodcuto", "nombre", 50, "100px");

    private DatePicker fecha = ObjetosComunes.getDatePicker("Fecha entrada", null, LocalDate.now());
    private IntegerField registro = new IntegerField("Registro");
    private IntegerField envases = new IntegerField("Nº envases");

    private TextField lote = ObjetosComunes.getTextField("Lote", "lote", 50, "100px");

    private Checkbox verificacion = new Checkbox("Verificacion");

    private TextField ctrlAnalitico = ObjetosComunes.getTextField("Control analitico", "control analitico", 50, "100px");
    private TextField farmaceutico = ObjetosComunes.getTextField("Farmaceutico", "farmaceutico", 50, "100px");
    private TextField caducidad = ObjetosComunes.getTextField("Caducidad", "caducidad", 50, "100px");

    private IntegerField numero = new IntegerField("Núumero");

    private FarmaFMMPrimasEntrada farmaFMMPrimasEntrada = null;

    private FarmaFMMPrimasEntrada farmaFMMPrimasEntradaAnterior = new FarmaFMMPrimasEntrada();

    private ComboBox<FarmaFMMPrimas> comboMPrimas = ObjetosComunes.getComboMPrimas(null, null);

    // private FarmaFMMPrimas farmaFMMPrimas = null;
    private Binder<FarmaFMMPrimasEntrada> binder = new Binder<>();

    public FrmFarmaFMMprimasEntradas() {
        super();
        doHazFormulario();
    }

    public FrmFarmaFMMprimasEntradas(FarmaFMMPrimasEntrada farmaFMMPrimasEntrada) {
        super();
        this.farmaFMMPrimasEntrada = farmaFMMPrimasEntrada;

        this.farmaFMMPrimasEntradaAnterior = farmaFMMPrimasEntrada;
        cod_inte.setValue(farmaFMMPrimasEntrada.getCod_inte());
        producto.setValue(farmaFMMPrimasEntrada.getProducto());
        doHazFormulario();
    }

    public FrmFarmaFMMprimasEntradas(FarmaFMMPrimas farmaFMMPrimas) {
        super();
        farmaFMMPrimasEntrada = new FarmaFMMPrimasEntrada();
        this.farmaFMMPrimasEntrada.setCod_inte(farmaFMMPrimas.getCod_inte());
        this.farmaFMMPrimasEntrada.setProducto(farmaFMMPrimas.getProducto());

        cod_inte.setValue(farmaFMMPrimasEntrada.getCod_inte());
        producto.setValue(farmaFMMPrimasEntrada.getProducto());
        
        doHazFormulario();
    }

    public void doFormatoCodigoProducto() {
        cod_inte.getStyle().set("color", "red");
        cod_inte.getStyle().set("fontWeight", "bold");
        cod_inte.getStyle().set("font-weight", "bold");

        producto.getStyle().set("color", "red");
        producto.getStyle().set("fontWeight", "bold");
        producto.getStyle().set("font-weight", "bold");

        numero.focus();
    }

    public void doHazFormulario() {

        if (farmaFMMPrimasEntrada == null) {
            farmaFMMPrimasEntrada = new FarmaFMMPrimasEntrada();
            cod_inte.setEnabled(true);
            cod_inte.focus();
        } else {
            cod_inte.setReadOnly(true);
            producto.setReadOnly(true);
            doFormatoCodigoProducto();
        }
        if (farmaFMMPrimasEntrada != null) {
            numero.setReadOnly(true);
        }

        titulo.setText(farmaFMMPrimasEntrada.getLabelFrom());

        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("5px", 1),
                new FormLayout.ResponsiveStep("100px", 2),
                new FormLayout.ResponsiveStep("100px", 3));

        contenedorFormulario.setMaxWidth("600px");
        contenedorFormulario.add(comboMPrimas, 3);
        contenedorFormulario.add(cod_inte, producto);
        contenedorFormulario.setColspan(producto, 2);
        contenedorFormulario.add(numero, fecha, registro);
        contenedorFormulario.add(envases, lote, verificacion);
        contenedorFormulario.add(caducidad);
        contenedorFormulario.add(ctrlAnalitico, 2);
        contenedorFormulario.add(farmaceutico, 3);

        comboMPrimas.addValueChangeListener(e -> {
            cod_inte.setValue(comboMPrimas.getValue().getCod_inte());
            producto.setValue(comboMPrimas.getValue().getProducto());
            farmaFMMPrimasEntrada.setCod_inte(comboMPrimas.getValue().getCod_inte());
            farmaFMMPrimasEntrada.setProducto(comboMPrimas.getValue().getProducto());
            numero.setValue(new FarmaFMMprimasEntraDAO().getNumeroSiguiente(farmaFMMPrimasEntrada));
            fecha.setValue(LocalDate.now());
            registro.focus();
            cod_inte.setReadOnly(true);
            producto.setReadOnly(true);
        });
        cod_inte.setWidth("25px");
        cod_inte.addBlurListener(e -> {
            if (cod_inte.isEmpty() && comboMPrimas.getValue() == null) {
                Notification.show("Código obligatorio");
                comboMPrimas.focus();
            } else {
                FarmaFMMPrimas farmaFMMPrimas = new FarmaFMMprimasDAO().getPorCodigo(cod_inte.getValue());
                if (farmaFMMPrimas == null) {
                    Notification.show("No existe materia prima para exe código:" + cod_inte.getValue());
                    producto.clear();
                    cod_inte.focus();
                } else {
                    producto.setValue(farmaFMMPrimas.getProducto());
                    producto.setReadOnly(true);
                    farmaFMMPrimasEntrada.setCod_inte(farmaFMMPrimas.getCod_inte());
                    farmaFMMPrimasEntrada.setProducto(farmaFMMPrimas.getProducto());
                    numero.setValue(new FarmaFMMprimasEntraDAO().getNumeroSiguiente(farmaFMMPrimasEntrada));
                    fecha.setValue(LocalDate.now());
                    registro.focus();
                }
            }
        });
        numero.addBlurListener(e -> {
            FarmaFMMPrimasEntrada farmaFMMPrimasEntradaExiste = new FarmaFMMprimasEntraDAO().getPorMPNumero(cod_inte.getValue(), numero.getValue());
            if (farmaFMMPrimasEntradaExiste != null) {
                farmaFMMPrimasEntrada = farmaFMMPrimasEntradaExiste;
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(farmaFMMPrimasEntrada);
            }
        });

        binder.forField(numero)
                .asRequired()
                .bind(FarmaFMMPrimasEntrada::getNumero, FarmaFMMPrimasEntrada::setNumero);
        binder.forField(fecha)
                .asRequired()
                .bind(FarmaFMMPrimasEntrada::getFecha, FarmaFMMPrimasEntrada::setFecha);
        binder.forField(registro)
                .asRequired()
                .bind(FarmaFMMPrimasEntrada::getRegistro, FarmaFMMPrimasEntrada::setRegistro);
        binder.forField(envases)
                .asRequired()
                .bind(FarmaFMMPrimasEntrada::getEnvases, FarmaFMMPrimasEntrada::setEnvases);
        binder.forField(lote)
                .bind(FarmaFMMPrimasEntrada::getLote, FarmaFMMPrimasEntrada::setLote);
        binder.forField(verificacion)
                .bind(FarmaFMMPrimasEntrada::getVerificacion, FarmaFMMPrimasEntrada::setVerificacion);
        binder.forField(caducidad)
                .bind(FarmaFMMPrimasEntrada::getCaducidad, FarmaFMMPrimasEntrada::setCaducidad);
        binder.forField(ctrlAnalitico)
                .bind(FarmaFMMPrimasEntrada::getCtrlAnalitico, FarmaFMMPrimasEntrada::setCtrlAnalitico);
        binder.forField(farmaceutico)
                .bind(FarmaFMMPrimasEntrada::getFarmaceutico, FarmaFMMPrimasEntrada::setFarmaceutico);

        binder.readBean(farmaFMMPrimasEntrada);
        if (numero.isEmpty()) {
            numero.setValue(new FarmaFMMprimasEntraDAO().getNumeroSiguiente(farmaFMMPrimasEntrada));
            fecha.setValue(LocalDate.now());
            registro.focus();
        }

    }

    @Override
    public void doGrabar() {

        if (binder.writeBeanIfValid(farmaFMMPrimasEntrada)) {
            if (new FarmaFMMprimasEntraDAO().doGrabaDatos(farmaFMMPrimasEntrada) == true) {
                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();
                doActualizaExistencias();
            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }

            this.close();
        } else {
            BinderValidationStatus<FarmaFMMPrimasEntrada> validate = binder.validate();
            String errorText = validate.getFieldValidationStatuses()
                    .stream().filter(BindingValidationStatus::isError)
                    .map(BindingValidationStatus::getMessage)
                    .map(Optional::get).distinct()
                    .collect(Collectors.joining(", "));
            Notification.show(FrmMaster.AVISODATOERRORVALIDANO + errorText);
        }
    }

    public void doActualizaExistencias() {
        Integer variacionExistencias = farmaFMMPrimasEntrada.getVariacionExistencias(farmaFMMPrimasEntradaAnterior.getEnvases(), "GRABAR");

        // entrada nueva
        if (farmaFMMPrimasEntradaAnterior.getNumero() == null) {
            variacionExistencias = farmaFMMPrimasEntrada.getEnvases();
        } else if (farmaFMMPrimasEntradaAnterior.getEnvases() != farmaFMMPrimasEntrada.getEnvases()) {
            // edita entrada  cambia envase
            if (farmaFMMPrimasEntradaAnterior.getEnvases() > farmaFMMPrimasEntrada.getEnvases()) {
                variacionExistencias = farmaFMMPrimasEntradaAnterior.getEnvases() - farmaFMMPrimasEntrada.getEnvases();
            } else {
                variacionExistencias = farmaFMMPrimasEntradaAnterior.getEnvases() + farmaFMMPrimasEntrada.getEnvases();
            }
        }

        if (variacionExistencias != 0) {
            if (new FarmaFMMprimasDAO().doActualizaExistencias(farmaFMMPrimasEntrada, variacionExistencias)) {
                Notification.show(FrmMaster.AVISODATOALMACENADO);
            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD + " Sin actualizar existenicas", 1000, Notification.Position.MIDDLE)).open();
            }
        }
    }

    @Override
    public void doBorrar() {
        ConfirmDialog dialog = new ConfirmDialog("Borrado del registro actual",
                "Seguro que quires borrar el dato actual. Numero de entrada : " + farmaFMMPrimasEntradaAnterior.getNumero(),
                "Borrar", this::onDelete);

        dialog.open();

        dialog.addDialogCloseActionListener(e -> {
            this.close();
        });

    }

    public void onDelete() {
        if (new FarmaFMMprimasEntraDAO().doBorraDatos(farmaFMMPrimasEntrada) == true) {
            Notification.show(FrmMaster.AVISODATOBORRADO);
            // descuenta existencias
            if (new FarmaFMMprimasDAO().doActualizaExistencias(farmaFMMPrimasEntrada, farmaFMMPrimasEntrada.getNumero() * (-1)) == true) {
                Notification.show("Existencias actualizadas");
            } else {
                Notification.show("Error actualizando existencias actualizadas");
            }
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
