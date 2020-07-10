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
import es.sacyl.hnss.dao.FarmaFMMprimasDAO;
import es.sacyl.hnss.dao.FarmaFMMprimasEntraDAO;
import es.sacyl.hnss.dao.FarmaFMMprimasSalidaDAO;
import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.entidades.FarmaFMMprimasSalida;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.time.LocalDate;

/**
 *
 * @author JuanNieto
 */
public class FrmFarmaFMMprimasSalidas extends FrmMaster {

    private IntegerField cod_inte = ObjetosComunes.getIntegerField("Código");

    private TextField producto = ObjetosComunes.getTextField("Nombre prodcuto", "nombre", 50, "100px");

    private final IntegerField numero = ObjetosComunes.getIntegerField("Número");

    private final IntegerField cantidad = ObjetosComunes.getIntegerField("Cantidad");

    private final TextField comentario1 = ObjetosComunes.getTextField("Comentario", "comentario", 50, "100px");

    private final DatePicker fecha = ObjetosComunes.getDatePicker("Fecha salidan", null, LocalDate.now());

    private FarmaFMMprimasSalida farmaFMMprimasSalida = new FarmaFMMprimasSalida();

    private ComboBox<FarmaFMMPrimas> comboMPrimas = ObjetosComunes.getComboMPrimas(null, null);

    private Binder<FarmaFMMprimasSalida> binder = new Binder<FarmaFMMprimasSalida>();

    public FrmFarmaFMMprimasSalidas() {
        super();
        doHazFormulario();
    }

    public FrmFarmaFMMprimasSalidas(FarmaFMMprimasSalida farmaFMMprimasSalidas) {
        super();
        this.farmaFMMprimasSalida = farmaFMMprimasSalidas;
        doHazFormulario();
    }

    public void doFormatoCodigoProducto() {
        if (farmaFMMprimasSalida == null) {
            farmaFMMprimasSalida = new FarmaFMMprimasSalida();
            cod_inte.setEnabled(true);
            cod_inte.focus();
        } else {
            cod_inte.setReadOnly(true);
            producto.setReadOnly(true);

            comboMPrimas.setReadOnly(true);

            comboMPrimas.setValue(farmaFMMprimasSalida);

            cod_inte.getStyle().set("color", "red");
            cod_inte.getStyle().set("fontWeight", "bold");
            cod_inte.getStyle().set("font-weight", "bold");

            producto.getStyle().set("color", "red");
            producto.getStyle().set("fontWeight", "bold");
            producto.getStyle().set("font-weight", "bold");

            numero.getStyle().set("color", "red");
            numero.getStyle().set("fontWeight", "bold");
            numero.getStyle().set("font-weight", "bold");

            numero.setReadOnly(true);
            cantidad.focus();
        }

    }

    public void doHazFormulario() {
        doFormatoCodigoProducto();

        titulo.setText(farmaFMMprimasSalida.getLabelFrom());

        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("5px", 1),
                new FormLayout.ResponsiveStep("100px", 2),
                new FormLayout.ResponsiveStep("100px", 3));

        contenedorFormulario.setMaxWidth("600px");
        contenedorFormulario.add(comboMPrimas, 3);
        contenedorFormulario.add(cod_inte, producto);
        contenedorFormulario.setColspan(producto, 2);
        contenedorFormulario.add(numero, fecha);
        contenedorFormulario.add(cantidad);
        contenedorFormulario.add(comentario1, 3);

        comboMPrimas.addValueChangeListener(e -> {
            cod_inte.setValue(comboMPrimas.getValue().getCod_inte());
            producto.setValue(comboMPrimas.getValue().getProducto());
            farmaFMMprimasSalida.setCod_inte(comboMPrimas.getValue().getCod_inte());
            farmaFMMprimasSalida.setProducto(comboMPrimas.getValue().getProducto());
            numero.setValue(new FarmaFMMprimasSalidaDAO().getNumeroSiguiente(farmaFMMprimasSalida));
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
                FarmaFMMPrimas farmaFMMPrimas = new FarmaFMMprimasDAO().getPorCodigo(cod_inte.getValue());
                if (farmaFMMPrimas == null) {
                    Notification.show("No existe materia prima para exe código:" + cod_inte.getValue());
                    producto.clear();
                    cod_inte.focus();
                } else {
                    producto.setValue(farmaFMMPrimas.getProducto());
                    producto.setReadOnly(true);
                    farmaFMMprimasSalida.setCod_inte(farmaFMMPrimas.getCod_inte());
                    farmaFMMprimasSalida.setProducto(farmaFMMPrimas.getProducto());
                    numero.setValue(new FarmaFMMprimasSalidaDAO().getNumeroSiguiente(farmaFMMprimasSalida));
                    fecha.setValue(LocalDate.now());
                    cantidad.focus();
                }
            }
        });
        numero.addBlurListener(e -> {
            FarmaFMMprimasSalida farmaFMMPrimasSalidaExiste = new FarmaFMMprimasSalidaDAO().getPorMPNumero(cod_inte.getValue(), numero.getValue());
            if (farmaFMMPrimasSalidaExiste != null) {
                farmaFMMprimasSalida = farmaFMMPrimasSalidaExiste;
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(farmaFMMprimasSalida);
            }
        });

        binder.forField(numero)
                .asRequired()
                .bind(FarmaFMMprimasSalida::getNumero, FarmaFMMprimasSalida::setNumero);
        binder.forField(fecha)
                .asRequired()
                .bind(FarmaFMMprimasSalida::getFecha, FarmaFMMprimasSalida::setFecha);
        binder.forField(cantidad)
                .asRequired()
                .bind(FarmaFMMprimasSalida::getCantidad, FarmaFMMprimasSalida::setCantidad);
        binder.forField(comentario1)
                .bind(FarmaFMMprimasSalida::getComentario1, FarmaFMMprimasSalida::setComentario1);

        binder.readBean(farmaFMMprimasSalida);
        if (numero.isEmpty()) {
            numero.setValue(new FarmaFMMprimasSalidaDAO().getNumeroSiguiente(farmaFMMprimasSalida));
            fecha.setValue(LocalDate.now());
            cantidad.focus();
        }

    }

    @Override
    public void doGrabar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
