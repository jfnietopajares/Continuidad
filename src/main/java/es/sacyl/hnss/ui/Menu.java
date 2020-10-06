
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

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
          //  contenedor.add((new FrmPrueba()));
        });

       
      
    }

}
