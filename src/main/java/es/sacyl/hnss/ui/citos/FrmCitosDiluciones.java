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
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.theme.lumo.Lumo;
import es.sacyl.hnss.dao.CitosDilucionesDAO;
import es.sacyl.hnss.dao.FMFormaDAO;
import es.sacyl.hnss.entidades.CitosDiluciones;
import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.FrmMasterLista;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public final class FrmCitosDiluciones extends FrmMaster {

    private static final long serialVersionUID = 1L;

    private final IntegerField id = ObjetosComunes.getIntegerField("Id");

    private final ComboBox<String> base = ObjetosComunes.getComboToString("Diluyente", null, ObjetosComunes.CITOSBASES, "50px");

    private final TextField concentracion = ObjetosComunes.getTextField("Concentración", "concentración", 20, "150px");

    private final IntegerField volumen = ObjetosComunes.getIntegerField("Volumen");

    private final ComboBox<String> volumenu = ObjetosComunes.getComboToString("Unidades", null, ObjetosComunes.CITOSUNIDADESV, "50px");

    private final ComboBox<String> tipovolumen = ObjetosComunes.getComboToString("Tipo", null, ObjetosComunes.CITOSTIPOVOLUMEN, "50px");

    private final ComboBox<String> presentacion = ObjetosComunes.getComboToString("Presentación", null, ObjetosComunes.CITOSTNATURALEZA,  "50px");

    private final TextArea observaciones = ObjetosComunes.getTextArea("Observaciones", "observaciones", null, "600px", "90px", "90px", "90px");

    private CitosDiluciones citoDiluciones = new CitosDiluciones();

    private final Binder<CitosDiluciones> binder;

    private final  Grid<CitosDiluciones> grid;
    
    public FrmCitosDiluciones() {
        super("500px");
        this.grid = new Grid<>();
        this.binder = new Binder<>();
        doHazFormulario();
    }

    public FrmCitosDiluciones(CitosDiluciones citoDiluciones) {
       super("500px");
        this.grid = new Grid<>();
        this.binder = new Binder<>();
        this.citoDiluciones = citoDiluciones;
        doHazFormulario();
    }

    public void doHazFormulario() {
        titulo.setText(CitosDiluciones.getLabelFrom());
        
        contenedorFormulario.addClassName(Lumo.LIGHT);
        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("150px", 1),
                new FormLayout.ResponsiveStep("50px", 2));
        
        contenedorFormulario.add(id);
       contenedorFormulario.setColspan(id, 2);
       contenedorFormulario.add(base, concentracion);
        contenedorFormulario.add( volumen, volumenu);

        contenedorFormulario.add(tipovolumen);
        contenedorFormulario.setColspan(tipovolumen, 2);

        contenedorFormulario.add(presentacion);
        contenedorFormulario.setColspan(presentacion, 2);

        contenedorFormulario.add(observaciones);
        contenedorFormulario.setColspan(observaciones, 2);

         
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
          
           doControlBotones(citoDiluciones);
    }
 @Override
     public void doControlBotones(Object citoDilucionesParam) {
         if (citoDilucionesParam==null) {
        citoDiluciones = new CitosDiluciones();
        binder.readBean(citoDiluciones);
        id.setEnabled(true);
        id.focus();
        botonBorrar.setEnabled(false);
         } else {
             citoDiluciones=  (CitosDiluciones) citoDilucionesParam;
              binder.readBean(citoDiluciones);
        id.setEnabled(false);
        base.focus();
        botonBorrar.setEnabled(true);
         }
    }
    @Override
    public void doGrabar() {
         if (binder.writeBeanIfValid(citoDiluciones)) {

            if (new CitosDilucionesDAO().doGrabaDatos(citoDiluciones) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            this.close();
        } else {
            BinderValidationStatus<CitosDiluciones> validate = binder.validate();
            String errorText = validate.getFieldValidationStatuses()
                    .stream().filter(BindingValidationStatus::isError)
                    .map(BindingValidationStatus::getMessage)
                    .map(Optional::get).distinct()
                    .collect(Collectors.joining(", "));
            Notification.show(FrmMaster.AVISODATOERRORVALIDANO + errorText);
        }
    }

    @Override
    public void doBorrar() {
        final ConfirmDialog dialog = new ConfirmDialog(
                "Conformación de acción",
                "Seguro que quieres borrar el dato ",
                "Borrar el dato actual ", () -> {
            boolean doBorraDatos = new CitosDilucionesDAO().doBorraDatos(citoDiluciones);
            Notification show = Notification.show(FrmMaster.AVISODATOBORRADO);
                    this.close();
                });
        dialog.open();
        dialog.addDialogCloseActionListener(e -> {
            this.close();
        });
    }

    @Override
    public void doAyuda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
