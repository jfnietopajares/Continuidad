/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 *
 * @author JuanNieto
 */
public abstract class FrmMasterLista extends FrmMaster {

    protected HorizontalLayout contenedorFormula = new HorizontalLayout();
    protected VerticalLayout contenedorIzquierda = new VerticalLayout();
    protected VerticalLayout contenedorDerecha = new VerticalLayout();

    public FrmMasterLista() {
        super();
        this.removeAll();
        this.add(contenedorFormula);

        contenedorFormula.add(contenedorIzquierda, contenedorDerecha);
        contenedorIzquierda.add(contenedorTitulo, contenedorBotones, contenedorFormulario);
    }
}
