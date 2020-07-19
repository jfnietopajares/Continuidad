/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.KeyPressEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 *
 * @author 06551256M
 */
public abstract class FrmMaster extends Dialog {

    public static final String AVISODATOSEXISTE = "Dato ya existe, es posible su modificación ";

    public static final String AVISODATOABLIGATORIO = "El dato es obligatorio, no puede estar en blanco o vacío ";

    public static final String AVISODATOALMACENADO = "Dato almacenado ";

    public static final String AVISODATOBORRADO = "Dato borrado ";
    public static final String AVISODATOERRORBORRADO = "Error borrando el dato ";

    public static final String AVISODATOERRORBBDD = "Erro de datos en base de datos ";

    public static final String AVISODATOERRORVALIDANO = "Error validanod  datos del formulario ";

    protected Button botonGrabar = ObjetosComunes.getBoton("Grabar", null, VaadinIcon.CHECK_CIRCLE.create());

    protected Button botonBorrar = ObjetosComunes.getBoton("Borrar", null, VaadinIcon.MINUS_CIRCLE.create());

    protected Button botonAyuda = ObjetosComunes.getBoton("Ayuda", null, VaadinIcon.QUESTION_CIRCLE.create());

    protected Button botonCancelar = ObjetosComunes.getBoton("Cancelar", null, VaadinIcon.CLOSE_CIRCLE.create());

    protected HorizontalLayout contenedorBotones = new HorizontalLayout();

    protected VerticalLayout contenedorTitulo = new VerticalLayout();

    protected FormLayout contenedorFormulario = new FormLayout();

    protected H3 titulo = new H3();

    public FrmMaster() {
        //  this.setWidth("400px");
        // this.setHeight("150px");
        doHazDialogo();
    }

    public FrmMaster(String ancho) {
        this.setWidth(ancho);
        // this.setHeight("150px");
        doHazDialogo();
    }

    public void doHazDialogo() {

        this.add(contenedorTitulo);

        this.add(contenedorBotones);

        this.add(contenedorFormulario);

        //    contenedorBorrado.getStyle().set("background-color", "#098742");
        contenedorTitulo.add(titulo);

        contenedorBotones.add(botonGrabar, botonBorrar, botonAyuda, botonCancelar);

        botonGrabar.addClickListener(e -> {
            doGrabar();
        });
        botonCancelar.addClickListener(e -> {
            doCancelar();
        });
        botonAyuda.addClickListener(e -> {
            doAyuda();
        });
        botonBorrar.addClickListener(e -> {
            doBorrar();
        });
    }

    public abstract void doGrabar();

    public abstract void doBorrar();

    public abstract void doAyuda();

    public void doCancelar() {
        this.close();
    }

    public Dialog getFrmMaster() {
        return this;
    }

    public void doControlBotones(Object obj) {
        if (obj != null) {
            botonBorrar.setEnabled(true);
        } else {
            botonBorrar.setEnabled(false);
        }
    }
}
