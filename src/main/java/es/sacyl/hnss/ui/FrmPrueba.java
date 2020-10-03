/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.binder.Binder;
import es.sacyl.hnss.entidades.CitosDiluciones;
import es.sacyl.hnss.entidades.Usuario;

/**
 *
 * @author JuanNieto
 */
public class FrmPrueba extends FrmMasterLista {
     private static final long serialVersionUID = 1L;
 
     private final  Grid<Usuario> grid;
     private final Binder<Usuario> binder;
      public FrmPrueba() {
        super("500px");
        this.grid = new Grid<>();
        this.binder = new Binder<>();
        doHazFormulario();
    }
public void doHazFormulario(){
    
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

    @Override
    public void doActualizaGrid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
