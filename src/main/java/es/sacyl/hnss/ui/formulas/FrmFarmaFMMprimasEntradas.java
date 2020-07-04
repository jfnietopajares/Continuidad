/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import es.sacyl.hnss.dao.FarmaFMMprimasDAO;
import es.sacyl.hnss.dao.FarmaFMMprimasEntraDAO;
import es.sacyl.hnss.entidades.FarmaFMMPrimas;
import es.sacyl.hnss.entidades.FarmaFMMPrimasEntrada;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.time.LocalDate;

/**
 *
 * @author JuanNieto
 */
public class FrmFarmaFMMprimasEntradas extends FrmMaster {

    private IntegerField cod_inte = new IntegerField("Código");
    private TextField producto = ObjetosComunes.getTextField("Nombre prodcuto", "nombre", 50, "100px");

    private DatePicker fecha = ObjetosComunes.getDatePicker("Fecha entrada", null, LocalDate.now());
    private IntegerField registro = new IntegerField("registro");
    private IntegerField envases = new IntegerField("Nº envases");

    private TextField lote = ObjetosComunes.getTextField("lote", "lote", 50, "100px");

    private Checkbox verificacion = new Checkbox("verificacion");

    private TextField ctrlAnalitico = ObjetosComunes.getTextField("ctrlAnalitico", "ctrlAnalitico", 50, "100px");
    private TextField farmaceutico = ObjetosComunes.getTextField("farmaceutico", "farmaceutico", 50, "100px");
    private TextField caducidad = ObjetosComunes.getTextField("caducidad", "caducidad", 50, "100px");

    private IntegerField numero = new IntegerField("numero");

    private FarmaFMMPrimas mprima;

    private FarmaFMMPrimasEntrada farmaFMMPrimasEntrada;

    private FarmaFMMPrimas farmaFMMPrimas;

    private Binder<FarmaFMMPrimasEntrada> binder = new Binder<>();

    /*
     private LocalDate fecha;
    private Integer registro;
    private Integer envases;
    private String lote;
    private Boolean verificacion;
    private String ctrlAnalitico;
    private String farmaceutico;
    private String caducidad;
    private Integer numero;

     */
    public FrmFarmaFMMprimasEntradas(FarmaFMMPrimasEntrada farmaFMMPrimasEntrada) {
        super();
        this.farmaFMMPrimasEntrada = farmaFMMPrimasEntrada;
        doHazFormulario();
    }

    public FrmFarmaFMMprimasEntradas(FarmaFMMPrimas farmaFMMPrimas) {
        super();
        this.farmaFMMPrimas = farmaFMMPrimas;
        cod_inte.setValue(farmaFMMPrimas.getCod_inte());
        producto.setValue(farmaFMMPrimas.getProducto());
        cod_inte.setEnabled(false);
        producto.setEnabled(false);
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(farmaFMMPrimasEntrada.getLabelFrom());

        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("5px", 1),
                new FormLayout.ResponsiveStep("100px", 2),
                new FormLayout.ResponsiveStep("100px", 3));

        contenedorFormulario.setMaxWidth("600px");
        contenedorFormulario.add(cod_inte, producto);
        contenedorFormulario.setColspan(producto, 2);
        contenedorFormulario.add(numero, fecha, registro);
        contenedorFormulario.add(envases, lote, verificacion);
        contenedorFormulario.add(caducidad);
        contenedorFormulario.add(ctrlAnalitico, 2);
        contenedorFormulario.add(farmaceutico, 3);

        if (farmaFMMPrimasEntrada == null || farmaFMMPrimasEntrada.getMprima().getCod_inte() == null) {
            cod_inte.setEnabled(true);
            cod_inte.focus();
        } else {
            cod_inte.setEnabled(false);
            producto.focus();
        }
        cod_inte.setWidth("25px");
        numero.addBlurListener(e -> {
            FarmaFMMPrimasEntrada farmaFMMPrimasEntradaExiste = new FarmaFMMprimasEntraDAO().getPorMPNumero(cod_inte.getValue(), numero.getValue());
            if (farmaFMMPrimasEntradaExiste != null) {
                farmaFMMPrimasEntrada = farmaFMMPrimasEntradaExiste;
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                binder.readBean(farmaFMMPrimasEntrada);
            }
        });

        binder.readBean(farmaFMMPrimasEntrada);

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
