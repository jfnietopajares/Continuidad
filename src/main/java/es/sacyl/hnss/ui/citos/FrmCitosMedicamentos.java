/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.citos;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.theme.lumo.Lumo;
import es.sacyl.hnss.dao.CitosMedicamentosDAO;
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.entidades.CitosMedicamentos;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.PrActivo;
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
public class FrmCitosMedicamentos extends FrmMasterLista {

    private final IntegerField id = ObjetosComunes.getIntegerField("Id");
    private final ComboBox<PrActivo> pr_activo = ObjetosComunes.getComboPrActivo("Pr.Activo", null);
    private final TextField comercial = ObjetosComunes.getTextField("Comercial", "comercial", 40, "200px");
    private final TextField siglas = ObjetosComunes.getTextField("Siglas", "siglas", 10, "100px");
    private final TextArea reconstitucion = ObjetosComunes.getTextArea("Reconstitución", "reconstitución", null, "600px", "90px", "90px", "90px");
    private final TextField estabilidado = ObjetosComunes.getTextField("Estabilidad solución", null, 255, "200px");
    private final TextField concentraciono = ObjetosComunes.getTextField("Concentración", null, 255, "200px");
    private final ComboBox<String> csobrante = ObjetosComunes.getComboToString("Conser.Sobra.Nevera", null, ObjetosComunes.SINO, "50px");
    private final TextArea obscsobrante = ObjetosComunes.getTextArea("Observ.Sobrante", "observaciones sobrante", null, "600px", "90px", "90px", "90px");
    private final TextField peligroso = ObjetosComunes.getTextField("Peligroso", "peligroso", 255, "200px");
    private final ComboBox<String> vesicante = ObjetosComunes.getComboToString("Vesicante", null, ObjetosComunes.CITOSVESICANTE, "200px");
    private final TextArea extravasacion = ObjetosComunes.getTextArea("Extravasación", "extravasación", null, "600px", "90px", "90px", "90px");
    private final TextArea derrames = ObjetosComunes.getTextArea("Derrames", "derrames", null, "600px", "90px", "90px", "90px");
    private final TextArea exposicion = ObjetosComunes.getTextArea("Exposición", "exposición", null, "600px", "90px", "90px", "90px");
    private final TextArea observaciones = ObjetosComunes.getTextArea("Observaciones", "observaciones", null, "600px", "90px", "90px", "90px");

    private  CitosMedicamentos citosMedicamentos = new CitosMedicamentos();

    private final Binder<CitosMedicamentos> binder = new  Binder<>();
    
    public FrmCitosMedicamentos() {
 super("1200px");
        doHazFormulario();
    }

    public FrmCitosMedicamentos(CitosMedicamentos citosMedicamentos) {
        super();
        this.citosMedicamentos = citosMedicamentos;
           doHazFormulario();
    }
public void    doHazFormulario(){
      titulo.setText(CitosMedicamentos.getLabelFrom());

        contenedorFormulario.addClassName(Lumo.LIGHT);
        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("300px", 1),
                new FormLayout.ResponsiveStep("300px", 2));
        contenedorFormulario.setMaxWidth("1000px");

        contenedorFormulario.add(id);
         contenedorFormulario.setColspan(id, 2);
           contenedorFormulario.add(pr_activo,comercial);
              contenedorFormulario.add(siglas,estabilidado);
              contenedorFormulario.add(reconstitucion);
         contenedorFormulario.setColspan(reconstitucion, 2); 
             contenedorFormulario.add(concentraciono,csobrante);
 
               contenedorFormulario.add(obscsobrante);
         contenedorFormulario.setColspan(obscsobrante, 2); 
              contenedorFormulario.add(peligroso);
         contenedorFormulario.setColspan(peligroso, 2); 
         contenedorFormulario.add(vesicante);
         contenedorFormulario.setColspan(vesicante, 2); 
         
          contenedorFormulario.add(extravasacion);
         contenedorFormulario.setColspan(extravasacion, 2); 
         
          contenedorFormulario.add(derrames);
         contenedorFormulario.setColspan(derrames, 2); 
          contenedorFormulario.add(exposicion);
         contenedorFormulario.setColspan(exposicion, 2); 
         contenedorFormulario.add(observaciones);
         contenedorFormulario.setColspan(observaciones, 2); 

          binder.forField(id)
                .bind(CitosMedicamentos::getId, CitosMedicamentos::setId);

            binder.forField(pr_activo)
                .bind(CitosMedicamentos::getPr_activo, CitosMedicamentos::setPr_activo);
   
    binder.forField(comercial)
                .bind(CitosMedicamentos::getComercial, CitosMedicamentos::setComercial);
            
binder.forField(siglas)
                .bind(CitosMedicamentos::getSiglas, CitosMedicamentos::setSiglas);
     
binder.forField(reconstitucion)
                .bind(CitosMedicamentos::getReconstitucion, CitosMedicamentos::setReconstitucion);

binder.forField(estabilidado)
                .bind(CitosMedicamentos::getEstabilidado, CitosMedicamentos::setEstabilidado);

binder.forField(concentraciono)
                .bind(CitosMedicamentos::getConcentraciono, CitosMedicamentos::setConcentraciono);

binder.forField(csobrante)
                .bind(CitosMedicamentos::getCsobrante, CitosMedicamentos::setCsobrante);

binder.forField(obscsobrante)
                .bind(CitosMedicamentos::getObscsobrante, CitosMedicamentos::setObscsobrante);

binder.forField(peligroso)
                .bind(CitosMedicamentos::getPeligroso, CitosMedicamentos::setPeligroso);

binder.forField(vesicante)
                .bind(CitosMedicamentos::getVesicante, CitosMedicamentos::setVesicante);
binder.forField(extravasacion)
                .bind(CitosMedicamentos::getExtravasacion, CitosMedicamentos::setExtravasacion);

binder.forField(derrames)
                .bind(CitosMedicamentos::getDerrames, CitosMedicamentos::setDerrames);
binder.forField(exposicion)
                .bind(CitosMedicamentos::getExposicion, CitosMedicamentos::setExposicion);
binder.forField(observaciones)
                .bind(CitosMedicamentos::getObservaciones, CitosMedicamentos::setObservaciones);

binder.readBean(citosMedicamentos);

}

     @Override
    public void doGrabar() {
        if (binder.writeBeanIfValid(citosMedicamentos)) {

            if (new CitosMedicamentosDAO().doGrabaDatos(citosMedicamentos) == true) {

                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();

                String clase = this.getParent().get().getClass().getName();

            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            this.close();
        } else {
            BinderValidationStatus<CitosMedicamentos> validate = binder.validate();
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
                    new CitosMedicamentosDAO().doBorraDatos(citosMedicamentos);
                    Notification.show(FrmMaster.AVISODATOBORRADO);
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

    @Override
    public void doActualizaGrid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
