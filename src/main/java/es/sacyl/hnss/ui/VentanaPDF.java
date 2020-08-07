/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.dialog.Dialog;

/**
 *
 * @author JuanNieto
 */
public class VentanaPDF extends Dialog {
 
    public VentanaPDF(String texto,String rutaRelativa){
        this.setWidth("600px");
         this.setHeight("500px");
         this.add(texto);
this.add(new EmbeddedPdfDocument(rutaRelativa));
            this.open();
        
    }
    
}
