/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import es.sacyl.hnss.ui.formulas.PantallaFarmaFMViasAdm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import es.sacyl.hnss.entidades.FarmaFMViasAdm;
import es.sacyl.hnss.ui.formulas.PantallaFarmaFMInstumentos;
import es.sacyl.hnss.ui.formulas.PantallaFarmaFMMprimas;
import es.sacyl.hnss.ui.formulas.PantallaFarmaFMMprimasEntradas;

/**
 *
 * @author 06551256M
 */
public class Menu extends MenuBar {

    private static final String FMMATERIASPRIMAS = "Materias Primas";

    public Menu(HorizontalLayout contenedor) {
        //MenuBar menuBar = new MenuBar();

        MenuItem formulas = this.addItem("Fórmulas");
        MenuItem equivalentes = this.addItem("Equivalenes");
        MenuItem oncologia = this.addItem("Oncología");
        this.addItem("Salir", e -> {
        });

        SubMenu formulasSubMenu = formulas.getSubMenu();
        formulasSubMenu.addItem("Formas farmacéuticas", e -> {
            new Notification("Vias clic", 5000);
        });
        MenuItem materiasPrimas = formulasSubMenu.addItem("Materias primas");

        SubMenu materiasSubMenu = materiasPrimas.getSubMenu();
        MenuItem definicion = materiasSubMenu.addItem("Definición");
        definicion.addClickListener(e -> {
            contenedor.removeAll();
            contenedor.add((new PantallaFarmaFMMprimas()));
        });
        MenuItem entradas = materiasSubMenu.addItem("Entradas");
        entradas.addClickListener(e -> {
            contenedor.removeAll();
            contenedor.add((new PantallaFarmaFMMprimasEntradas()));
        });
        MenuItem salidas = materiasSubMenu.addItem("Salidas");

        formulasSubMenu.addItem("Material e instrumental", e -> {
            contenedor.removeAll();
            contenedor.add((new PantallaFarmaFMInstumentos()));
        });
        formulasSubMenu.addItem("Vias Administración", e -> {
            contenedor.removeAll();
            contenedor.add((new PantallaFarmaFMViasAdm()));

        });

    }

}
