/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import es.sacyl.hnss.dao.FMMprimasDAO;
import es.sacyl.hnss.dao.FMMprimasEntraDAO;
import es.sacyl.hnss.dao.FMMprimasSalidaDAO;
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMMprimasSalida;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFMMprimasSalidas extends FrmMaster {

    //   private IntegerField cod_inte = ObjetosComunes.getIntegerField("Código");
    // private TextField producto = ObjetosComunes.getTextField("Nombre prodcuto", "nombre", 50, "100px");
    private final IntegerField numero = ObjetosComunes.getIntegerField("Número");

    private final IntegerField cantidad = ObjetosComunes.getIntegerField("Cantidad");

    private final TextField comentario1 = ObjetosComunes.getTextField("Comentario", "comentario", 50, "100px");

    private final DatePicker fecha = ObjetosComunes.getDatePicker("Fecha salida", null, LocalDate.now());

    private FMMprimasSalida fMMprimasSalida = new FMMprimasSalida();

    private FMMprimasSalida fMMprimasSalidaAnterior = new FMMprimasSalida();

    private ComboBox<FMMPrimas> comboMPrimas = ObjetosComunes.getComboMPrimas(null, null);

    private Binder<FMMprimasSalida> binder = new Binder<FMMprimasSalida>();

    public FrmFMMprimasSalidas() {
        super();
        doHazFormulario();
    }

    public FrmFMMprimasSalidas(FMMprimasSalida fMMprimasSalidas) {
        super();
        this.fMMprimasSalida = fMMprimasSalidas;
        this.fMMprimasSalidaAnterior = fMMprimasSalidas;
        doHazFormulario();
    }

    public FrmFMMprimasSalidas(FMMPrimas fMMPrimas) {
        super();
        if (fMMPrimas != null) {
            this.fMMprimasSalida = new FMMprimasSalida(fMMPrimas);
            comboMPrimas.setValue(fMMPrimas);
            comboMPrimas.setReadOnly(true);
        }
        doHazFormulario();
    }

    public void doFormatoCodigoProducto() {
        if (fMMprimasSalida == null || fMMprimasSalida.getNumero() == null) {
            // fMMprimasSalida = new FMMprimasSalida();
            /*
            cod_inte.setEnabled(true);
            cod_inte.focus();
             */
        } else {
            /*
            cod_inte.setReadOnly(true);
            producto.setReadOnly(true);
  cod_inte.getStyle().set("color", "red");
            cod_inte.getStyle().set("fontWeight", "bold");
            cod_inte.getStyle().set("font-weight", "bold");

            producto.getStyle().set("color", "red");
            producto.getStyle().set("fontWeight", "bold");
            producto.getStyle().set("font-weight", "bold");
             */
            comboMPrimas.setReadOnly(true);

            comboMPrimas.setValue(fMMprimasSalida);

            numero.getStyle().set("color", "red");
            numero.getStyle().set("fontWeight", "bold");
            numero.getStyle().set("font-weight", "bold");

            numero.setReadOnly(true);
            cantidad.focus();
        }

    }

    public void doHazFormulario() {
        doFormatoCodigoProducto();

        titulo.setText(fMMprimasSalida.getLabelFrom());

        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("100px", 1),
                new FormLayout.ResponsiveStep("100px", 2),
                new FormLayout.ResponsiveStep("100px", 3));

        contenedorFormulario.setMaxWidth("600px");
        contenedorFormulario.add(comboMPrimas, 3);
        //   contenedorFormulario.add(cod_inte, producto);
        //  contenedorFormulario.setColspan(producto, 2);
        contenedorFormulario.add(numero, fecha);
        contenedorFormulario.add(cantidad);
        contenedorFormulario.add(comentario1, 3);

        /*
        comboMPrimas.addValueChangeListener(e -> {
        //    cod_inte.setValue(comboMPrimas.getValue().getCod_inte());
            fMMprimasSalida.setProducto(comboMPrimas.getValue().getProducto());
            numero.setValue(new FMMprimasSalidaDAO().getNumeroSiguiente(fMMprimasSalida));
            fecha.setValue(LocalDate.now());
            cantidad.focus();
            cod_inte.setReadOnly(true);
            producto.setReadOnly(true);
        });
        cod_inte.setWidth("25px");
        cod_inte.addBlurListener(e -> {
            if (cod_inte.isEmpty() && comboMPrimas.getValue() == null) {
                Notification.show("Código obligatorio");
                comboMPrimas.focus();
            } else {
                FMMPrimas fMMPrimas = new FMMprimasDAO().getPorCodigo(cod_inte.getValue());
                if (fMMPrimas == null) {
                    Notification.show("No existe materia prima para exe código:" + cod_inte.getValue());
                    producto.clear();
                    cod_inte.focus();
                } else {
                    producto.setValue(fMMPrimas.getProducto());
                    producto.setReadOnly(true);
                    fMMprimasSalida.setCod_inte(fMMPrimas.getCod_inte());
                    fMMprimasSalida.setProducto(fMMPrimas.getProducto());
                    numero.setValue(new FMMprimasSalidaDAO().getNumeroSiguiente(fMMprimasSalida));
                    fecha.setValue(LocalDate.now());
                    cantidad.focus();
                }
            }
        });

        numero.addBlurListener(e -> {
            FMMprimasSalida fMMPrimasSalidaExiste = new FMMprimasSalidaDAO().getPorMPNumero(cod_inte.getValue(), numero.getValue());
            if (fMMPrimasSalidaExiste != null) {
                fMMprimasSalida = fMMPrimasSalidaExiste;
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(fMMprimasSalida);
            }
        });
         */
        binder.forField(numero)
                .asRequired()
                .bind(FMMprimasSalida::getNumero, FMMprimasSalida::setNumero);
        binder.forField(fecha)
                .asRequired()
                .bind(FMMprimasSalida::getFecha, FMMprimasSalida::setFecha);
        binder.forField(cantidad)
                .asRequired()
                .bind(FMMprimasSalida::getCantidad, FMMprimasSalida::setCantidad);
        binder.forField(comentario1)
                .bind(FMMprimasSalida::getComentario1, FMMprimasSalida::setComentario1);

        binder.readBean(fMMprimasSalida);
        doControlBotones(fMMprimasSalida.getNumero());
        if (numero.isEmpty()) {
            numero.setValue(new FMMprimasSalidaDAO().getNumeroSiguiente(fMMprimasSalida));
            fecha.setValue(LocalDate.now());
            cantidad.focus();
        }

    }

    public void doActualizaExistencias() {
        Integer variacionExistencias = fMMprimasSalida.getVariacionExistencias(fMMprimasSalidaAnterior.getCantidad(), "GRABAR");
        if (variacionExistencias != 0) {
            if (new FMMprimasDAO().doActualizaExistencias(fMMprimasSalida, variacionExistencias)) {
                Notification.show(FrmMaster.AVISODATOALMACENADO);
            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD + " Sin actualizar existenicas", 1000, Notification.Position.MIDDLE)).open();
            }
        }
    }

    @Override
    public void doGrabar() {

        if (binder.writeBeanIfValid(fMMprimasSalida)) {
            if (new FMMprimasSalidaDAO().doGrabaDatos(fMMprimasSalida) == true) {
                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();
                doActualizaExistencias();
            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }

            this.close();
        } else {
            BinderValidationStatus<FMMprimasSalida> validate = binder.validate();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doAyuda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
