/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import java.time.LocalDate;

/**
 *
 * @author 06551256M
 */
public class ObjetosComunes {

    public ObjetosComunes() {

    }

    public static Button getBoton(String text, ButtonVariant buttonVariant, Icon icon) {
        Button boton = new Button();
        if (text != null) {
            boton.setText(text);
        }
        if (buttonVariant != null) {
            boton.addThemeVariants(buttonVariant);
        } else {
            boton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        }
        if (icon != null) {
            boton.setIcon(icon);
        }
        return boton;
    }

    public static TextField getTextField(String text, String Placeholder, Integer maxLength, String width) {
        TextField textField = new TextField();
        if (text != null) {
            textField.setLabel(text);
        }
        if (Placeholder != null) {
            textField.setPlaceholder(Placeholder);
        }
        if (maxLength != null) {
            textField.setMaxLength(maxLength);
        }

        if (width != null) {
            textField.setWidth(width);
        }
        textField.getEmptyValue();
        return textField;
    }

    public static TextArea getTextArea(String text, String Placeholder, Integer maxLength, String width, String height) {
        TextArea textArea = new TextArea();
        if (text != null) {
            textArea.setLabel(text);
        }
        if (Placeholder != null) {
            textArea.setPlaceholder(Placeholder);
        }
        if (maxLength != null) {
            textArea.setMaxLength(maxLength);
        }

        if (width != null) {
            textArea.setWidth(width);
        }
        if (height != null) {
            textArea.setHeight(height);
        }
        textArea.getEmptyValue();
        return textArea;
    }

    public static DatePicker getDatePicker(String label, String placeholder, LocalDate date) {

        DatePicker datePicker = new DatePicker();
        if (label != null) {
            datePicker.setLabel(label);
        }

        if (placeholder != null) {
            datePicker.setPlaceholder(placeholder);
        }

        if (date != null) {
            datePicker.setValue(date);
        }

        return datePicker;
    }

}
