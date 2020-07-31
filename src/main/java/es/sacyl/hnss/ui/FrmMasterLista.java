/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import es.sacyl.hnss.entidades.FMFormulaBibliografia;

/**
 *
 * @author JuanNieto
 */
public abstract class FrmMasterLista extends Dialog {

    protected Button botonGrabar = ObjetosComunes.getBoton("Grabar", null, VaadinIcon.CHECK_CIRCLE.create());

    protected Button botonBorrar = ObjetosComunes.getBoton("Borrar", null, VaadinIcon.MINUS_CIRCLE.create());

    protected Button botonAyuda = ObjetosComunes.getBoton("Ayuda", null, VaadinIcon.QUESTION_CIRCLE.create());

    protected Button botonCancelar = ObjetosComunes.getBoton("Cancelar", null, VaadinIcon.CLOSE_CIRCLE.create());

    protected H6 titulo = new H6();
    protected HorizontalLayout contenedorBotones = new HorizontalLayout();

    protected VerticalLayout contenedorTitulo = new VerticalLayout();

    protected FormLayout contenedorFormulario = new FormLayout();
    //   protected HorizontalLayout contenedorVentana = new HorizontalLayout();
    protected HorizontalLayout contenedorVentanaCabecera = new HorizontalLayout();
    protected HorizontalLayout contenedorVentanaContenido = new HorizontalLayout();
    protected VerticalLayout contenedorIzquierda = new VerticalLayout();
    protected VerticalLayout contenedorDerecha = new VerticalLayout();

    public FrmMasterLista() {
        this.setWidth("900px");
        doHazVentana();

    }

    public FrmMasterLista(String ancho) {
        this.setWidth(ancho);
        doHazVentana();

    }

    public void doHazVentana() {
        this.add(contenedorVentanaCabecera, contenedorVentanaContenido);
        //    contenedorVentana.add(contenedorVentanaCabecera, contenedorVentanaContenido);

        contenedorVentanaCabecera.add(contenedorTitulo, contenedorBotones);
        contenedorTitulo.add(titulo);
        contenedorBotones.add(botonGrabar, botonBorrar, botonAyuda, botonCancelar);

        contenedorVentanaContenido.add(contenedorIzquierda, contenedorDerecha);
        //    contenedorVentana.add(contenedorIzquierda, contenedorDerecha);
        contenedorIzquierda.add(contenedorFormulario);

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

       public abstract void doActualizaGrid();
       
    public void doCancelar() {
        this.close();
    }

    public void doControlBotones(Object obj) {
        if (obj != null) {
            botonBorrar.setEnabled(true);
        } else {
            botonBorrar.setEnabled(false);
        }
    }
}
