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
import es.sacyl.hnss.dao.FMMprimasDAO;
import es.sacyl.hnss.dao.FMMprimasEntraDAO;
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMViasAdm;
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
public class FrmFMMprimasEntradas extends FrmMaster {

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

    private FMMPrimasEntrada fMMPrimasEntrada = null;

    private FMMPrimasEntrada fMMPrimasEntradaAnterior = new FMMPrimasEntrada();

    private ComboBox<FMMPrimas> comboMPrimas = ObjetosComunes.getComboMPrimas(null, null);

    private Binder<FMMPrimasEntrada> binder = new Binder<>();

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

    public FrmFMMprimasEntradas(FMMPrimas farmaFMMPrimas) {
        super();
        if (farmaFMMPrimas != null) {
            this.fMMPrimasEntrada = new FMMPrimasEntrada(farmaFMMPrimas);

            //  this.fMMPrimasEntrada.setDatosMprima(farmaFMMPrimas);
            //   this.fMMPrimasEntrada.setCod_inte(farmaFMMPrimas.getCod_inte());
            //  this.fMMPrimasEntrada.setProducto(farmaFMMPrimas.getProducto());
            //       cod_inte.setValue(fMMPrimasEntrada.getCod_inte());
            //     producto.setValue(fMMPrimasEntrada.getProducto());
        }
        doHazFormulario();
    }

    public void doFormatoCodigoProducto() {
        if (fMMPrimasEntrada == null) {
            fMMPrimasEntrada = new FMMPrimasEntrada();
            //     cod_inte.setEnabled(true);
            //   cod_inte.focus();
        } else {
            //  cod_inte.setReadOnly(true);
            // producto.setReadOnly(true);

            comboMPrimas.setReadOnly(true);

            comboMPrimas.setValue(fMMPrimasEntrada);

            //cod_inte.getStyle().set("color", "red");
            //cod_inte.getStyle().set("fontWeight", "bold");
            //cod_inte.getStyle().set("font-weight", "bold");
            //producto.getStyle().set("color", "red");
            //producto.getStyle().set("fontWeight", "bold");
            //producto.getStyle().set("font-weight", "bold");
            numero.getStyle().set("color", "red");
            numero.getStyle().set("fontWeight", "bold");
            numero.getStyle().set("font-weight", "bold");

            numero.setReadOnly(true);
            registro.focus();
        }

    }

    public void doHazFormulario() {
        doFormatoCodigoProducto();

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

        comboMPrimas.addValueChangeListener(e -> {
            //   cod_inte.setValue(comboMPrimas.getValue().getCod_inte());
            //  producto.setValue(comboMPrimas.getValue().getProducto());
            fMMPrimasEntrada.setCod_inte(comboMPrimas.getValue().getCod_inte());
            fMMPrimasEntrada.setProducto(comboMPrimas.getValue().getProducto());
            numero.setValue(new FMMprimasEntraDAO().getNumeroSiguiente(fMMPrimasEntrada));
            fecha.setValue(LocalDate.now());
            registro.focus();
            // cod_inte.setReadOnly(true);
            //producto.setReadOnly(true);
        });
        // cod_inte.setWidth("25px");
        /*
       cod_inte.addBlurListener(e -> {
            if (cod_inte.isEmpty() && comboMPrimas.getValue() == null) {
                Notification.show("Código obligatorio");
                comboMPrimas.focus();
            } else {
                FMMPrimas farmaFMMPrimas = new FMMprimasDAO().getPorCodigo(cod_inte.getValue());
                if (farmaFMMPrimas == null) {
                    Notification.show("No existe materia prima para exe código:" + cod_inte.getValue());
                    producto.clear();
                    cod_inte.focus();
                } else {
                    producto.setValue(farmaFMMPrimas.getProducto());
                    producto.setReadOnly(true);
                    fMMPrimasEntrada.setCod_inte(farmaFMMPrimas.getCod_inte());
                    fMMPrimasEntrada.setProducto(farmaFMMPrimas.getProducto());
                    numero.setValue(new FMMprimasEntraDAO().getNumeroSiguiente(fMMPrimasEntrada));
                    fecha.setValue(LocalDate.now());
                    registro.focus();
                }
            }
        });
         */
 /*
        numero.addBlurListener(e -> {
            FMMPrimasEntrada fMMPrimasEntradaExiste = new FMMprimasEntraDAO().getPorMPNumero(cod_inte.getValue(), numero.getValue());
            if (fMMPrimasEntradaExiste != null) {
                fMMPrimasEntrada = fMMPrimasEntradaExiste;
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(fMMPrimasEntrada);
            }
        });
         */
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
        doControlBotones(fMMPrimasEntrada.getNumero());
        if (numero.isEmpty()) {
            numero.setValue(new FMMprimasEntraDAO().getNumeroSiguiente(fMMPrimasEntrada));
            fecha.setValue(LocalDate.now());
            registro.focus();
        }

    }

    @Override
    public void doGrabar() {

        if (binder.writeBeanIfValid(fMMPrimasEntrada)) {
            if (new FMMprimasEntraDAO().doGrabaDatos(fMMPrimasEntrada) == true) {
                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();
                doActualizaExistencias();
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

    public void doActualizaExistencias() {
        Integer variacionExistencias = fMMPrimasEntrada.getVariacionExistencias(fMMPrimasEntradaAnterior.getEnvases(), "GRABAR");

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
            if (new FMMprimasDAO().doActualizaExistencias(fMMPrimasEntrada, fMMPrimasEntrada.getNumero() * (-1)) == true) {
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
