/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.citos;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.theme.lumo.Lumo;
import es.sacyl.hnss.dao.CitosDilucionesDAO;
import es.sacyl.hnss.entidades.CitosDiluciones;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.FrmMasterLista;
import es.sacyl.hnss.ui.ObjetosComunes;

/**
 *
 * @author JuanNieto
 */
public final class FrmCitosDiluciones extends FrmMasterLista {

    private static final long serialVersionUID = 1L;

    private final IntegerField id = ObjetosComunes.getIntegerField("Id");

    private final ComboBox<String> base = ObjetosComunes.getComboToString("Diluyente", null, ObjetosComunes.CITOSBASES, "50px");

    private final TextField concentracion = ObjetosComunes.getTextField("Concentración", "concentración", 20, "150px");

    private final IntegerField volumen = ObjetosComunes.getIntegerField("Volumen");

    private final ComboBox<String> volumenu = ObjetosComunes.getComboToString("Vol", null, ObjetosComunes.CITOSBASES, "50px");

    private final ComboBox<String> tipovolumen = ObjetosComunes.getComboToString("Tipo", null, ObjetosComunes.CITOSTIPOVOLUMEN, "50px");

    private final ComboBox<String> presentacion = ObjetosComunes.getComboToString("Presentación", null, ObjetosComunes.CITOSTNATURALEZA,  "50px");

    private final TextArea observaciones = ObjetosComunes.getTextArea("Observaciones", "observaciones", null, "600px", "90px", "90px", "90px");

    private CitosDiluciones citoDiluciones = new CitosDiluciones();

    private final Binder<CitosDiluciones> binder;

    private final  Grid<CitosDiluciones> grid;
    
    public FrmCitosDiluciones() {
        super("900px");
        this.grid = new Grid<>();
        this.binder = new Binder<>();
        doHazFormulario();
    }

    public FrmCitosDiluciones(CitosDiluciones citoDiluciones) {
        super("1200px");
        this.grid = new Grid<>();
        this.binder = new Binder<>();
        this.citoDiluciones = citoDiluciones;
        doHazFormulario();
    }

    public void doHazFormulario() {
        titulo.setText(FMFormula.getLabelFrom());
        
        contenedorFormulario.addClassName(Lumo.LIGHT);
        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("50", 1),
                new FormLayout.ResponsiveStep("150px", 2),
                new FormLayout.ResponsiveStep("100px", 3));
        
        contenedorFormulario.add(id, base);
        contenedorFormulario.setColspan(base, 2);
        contenedorFormulario.add(concentracion, volumen, volumenu);

        contenedorFormulario.add(tipovolumen);
        contenedorFormulario.setColspan(tipovolumen, 3);

        contenedorFormulario.add(presentacion);
        contenedorFormulario.setColspan(presentacion, 3);

        contenedorFormulario.add(observaciones);
        contenedorFormulario.setColspan(observaciones, 3);

         contenedorDerecha.add(grid);
         
        if (citoDiluciones == null || citoDiluciones.getId() == null) {
            id.setEnabled(true);
        } else {
            id.setEnabled(false);
        }

        id.addBlurListener(e -> {
            CitosDiluciones citosDilucionesExiste = new CitosDilucionesDAO().getPorId( id.getValue());
            if (citosDilucionesExiste != null) {
                (new Notification(FrmMaster.AVISODATOSEXISTE, 1000, Notification.Position.MIDDLE)).open();
                citoDiluciones = citosDilucionesExiste;
                binder.readBean(citoDiluciones);

            }
        });
        
        binder.forField(id)
                .asRequired()
                .bind(CitosDiluciones::getId, CitosDiluciones::setId);

        binder.forField(base)
                .asRequired()
                .bind(CitosDiluciones::getBase, CitosDiluciones::setBase);

        binder.forField(concentracion)
                .asRequired()
                .bind(CitosDiluciones::getConcentracion, CitosDiluciones::setConcentracion);

        binder.forField(volumen)
                .asRequired()
                .bind(CitosDiluciones::getVolumen, CitosDiluciones::setVolumen);
        
        binder.forField(volumenu)
                .asRequired()
                .bind(CitosDiluciones::getVolumenu, CitosDiluciones::setVolumenu);
        
        binder.forField(tipovolumen)
                .asRequired()
                .bind(CitosDiluciones::getTipovolumen, CitosDiluciones::setTipovolumen);
        
        binder.forField(presentacion)
                .asRequired()
                .bind(CitosDiluciones::getPresentacion, CitosDiluciones::setPresentacion);
         
        
        binder.forField(observaciones)
                .asRequired()
                .bind(CitosDiluciones::getObservaciones, CitosDiluciones::setObservaciones);
          
                  grid.addColumn(CitosDiluciones::getId).setHeader("Id");
        grid.addColumn(CitosDiluciones::getBase).setHeader("Base").setWidth("300px");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addItemClickListener(event
                -> {
            citoDiluciones = event.getItem();
            doControlEventos(citoDiluciones);
        }
        );
        grid.setItems(
                new CitosDilucionesDAO().getListaDiluciones(null));

        doActualizaGrid();
    }

     public void doControlEventos(CitosDiluciones citoDiluciones) {
         if (citoDiluciones==null) {
        citoDiluciones = new CitosDiluciones();
        binder.readBean(citoDiluciones);
        id.setEnabled(true);
        id.focus();
        botonBorrar.setEnabled(false);
         } else {
              binder.readBean(citoDiluciones);
        id.setEnabled(false);
        base.focus();
        botonBorrar.setEnabled(true);
         }
        doActualizaGrid();
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
        grid.setItems(new CitosDilucionesDAO().getListaDiluciones(null));
    }

}
