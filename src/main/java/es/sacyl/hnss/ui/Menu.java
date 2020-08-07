/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.ClickEvent;
import es.sacyl.hnss.ui.formulas.PntFMViasAdm;
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
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.ui.citos.FrmCitosDiluciones;
import es.sacyl.hnss.ui.formulas.FrmFMFormulaFabricar;
import es.sacyl.hnss.ui.formulas.PntFMFormas;
import es.sacyl.hnss.ui.formulas.PntFMFormulas;
import es.sacyl.hnss.ui.formulas.PntFMInstumentos;
import es.sacyl.hnss.ui.formulas.PntFMprimas;
import es.sacyl.hnss.ui.formulas.PntFMMprimasEntradas;
import es.sacyl.hnss.ui.formulas.PntFMMprimasSalidas;

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
        MenuItem citostaticos = this.addItem("Citostáticos");
        
        this.addItem("Salir", e -> {
        });

        SubMenu formulasSubMenu = formulas.getSubMenu();

        formulasSubMenu.addItem("Fórmulas", e -> {
            contenedor.removeAll();
            contenedor.add((new PntFMFormulas()));
        });

        formulasSubMenu.addItem("Formas farmacéuticas", e -> {
            contenedor.removeAll();
            contenedor.add((new PntFMFormas()));
        });
        /*
        MenuItem materiasPrimas = formulasSubMenu.addItem("Materias primas");

        SubMenu materiasSubMenu = materiasPrimas.getSubMenu();
        MenuItem definicion = materiasSubMenu.addItem("Definición");
        definicion.addClickListener(e -> {
            contenedor.removeAll();
            contenedor.add((new PntFMprimas()));
        });
        MenuItem entradas = materiasSubMenu.addItem("Entradas");
        entradas.addClickListener(e -> {
            contenedor.removeAll();
            contenedor.add((new PntFMMprimasEntradas()));
        });
           MenuItem salidas = materiasSubMenu.addItem("Salidas");
         */

        formulasSubMenu.addItem("Materias primas ", e -> {
            contenedor.removeAll();
            contenedor.add((new PntFMprimas()));
        });

        /*
        formulasSubMenu.addItem("Materias primas entradas", e -> {
            contenedor.removeAll();
            contenedor.add((new PntFMMprimasEntradas()));
        });

        formulasSubMenu.addItem("Materias primas salidas", e -> {
            contenedor.removeAll();
            contenedor.add((new PntFMMprimasSalidas()));
        });
         */
        formulasSubMenu.addItem("Material e instrumental", e -> {
            contenedor.removeAll();
            contenedor.add((new PntFMInstumentos()));
        });
        formulasSubMenu.addItem("Vias Administración", e -> {
            contenedor.removeAll();
            contenedor.add((new PntFMViasAdm()));

        });

        
            SubMenu citostaticosSubMenu = citostaticos.getSubMenu();
            
     citostaticosSubMenu.addItem("Diluciones", (ClickEvent<MenuItem> e) -> {
            contenedor.removeAll();
            contenedor.add((new FrmCitosDiluciones()));
        });
       
    }

}
