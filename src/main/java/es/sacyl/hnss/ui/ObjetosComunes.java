/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import es.sacyl.hnss.dao.FMFormaDAO;
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.dao.FMInstrumentosDAO;
import es.sacyl.hnss.dao.FMMprimasDAO;
import es.sacyl.hnss.dao.FMViasAdmDAO;
import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaAutoriza;
import es.sacyl.hnss.entidades.FMFormulaTipo;
import es.sacyl.hnss.entidades.FMInstrumento;
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMViasAdm;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author 06551256M
 */
public class ObjetosComunes {

    public static ArrayList<String> SINO = new ArrayList<String>() {
        {
            add("S");
            add("N");
        }
    };

    public ObjetosComunes() {

    }

    public static ComboBox<String> getComboToString(String label, String valor, ArrayList<String> lista, String ancho) {
        ComboBox<String> combo = new ComboBox<>();
        if (label != null) {
            combo.setLabel(label);
        }
        combo.setItems(lista);
        if (valor != null) {
            combo.setValue(valor);
        }
        if (ancho != null) {
            combo.setWidth(ancho);
        }
        return combo;
    }

    public static ComboBox<FMFormulaTipo> getComboTipoForm(String label, FMFormulaTipo valor) {
        ComboBox<FMFormulaTipo> combo = new ComboBox<>();
        if (label != null) {
            combo.setLabel(label);
        }
        combo.setItems(FMFormulaTipo.LISTAFORMULASTIPOS);
        combo.setItemLabelGenerator(FMFormulaTipo::getDescripcion);
        if (valor != null) {
            combo.setValue(valor);
        }
        return combo;
    }

    public static ComboBox<FMFormulaAutoriza> getComboAutoriza(String label, FMFormulaAutoriza valor) {
        ComboBox<FMFormulaAutoriza> combo = new ComboBox<>();
        if (label != null) {
            combo.setLabel(label);
        }
        combo.setItems(FMFormulaAutoriza.LISTAAUTORIZA);
        combo.setItemLabelGenerator(FMFormulaAutoriza::getDescripcion);
        if (valor != null) {
            combo.setValue(valor);
        }
        return combo;
    }

    public static ComboBox<FMViasAdm> getComboVias(String label, FMViasAdm valor) {
        ComboBox<FMViasAdm> combo = new ComboBox<>();
        if (label != null) {
            combo.setLabel(label);
        }
        combo.setItems(new FMViasAdmDAO().getListaViasAdm(null));
        combo.setItemLabelGenerator(FMViasAdm::getNombre);
        if (valor != null) {
            combo.setValue(valor);
        }
        return combo;
    }

    public static ComboBox<FMForma> getComboForma(String label, FMForma valor) {
        ComboBox<FMForma> combo = new ComboBox<>();
        if (label != null) {
            combo.setLabel(label);
        }
        combo.setItems(new FMFormaDAO().getListaFormas(null));

        combo.setItemLabelGenerator(FMForma::getNombre);

        if (valor != null) {
            combo.setValue(valor);
        }
        return combo;
    }

    public static ComboBox<FMFormula> getComboFormula(String label, FMFormula valor) {
        ComboBox<FMFormula> combo = new ComboBox<>();
        if (label != null) {
            combo.setLabel(label);
        }
        combo.setItems(new FMFormulasDAO().getListaFormulas(null));

        combo.setItemLabelGenerator(FMFormula::getNombre);

        if (valor != null) {
            combo.setValue(valor);
        }
        return combo;
    }

    public static ComboBox<FMInstrumento> getComboInstrumento(String label, FMInstrumento valor) {
        ComboBox<FMInstrumento> combo = new ComboBox<>();
        if (label != null) {
            combo.setLabel(label);
        }
        combo.setItems(new FMInstrumentosDAO().getListaInstrumentos(null));

        combo.setItemLabelGenerator(FMInstrumento::getNombre);

        if (valor != null) {
            combo.setValue(valor);
        }
        return combo;
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

    public static TextArea getTextArea(String text, String Placeholder, Integer maxLength, String width, String height, String maxHeight, String minHeight) {
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
        if (maxHeight != null) {
            textArea.getStyle().set("maxHeight", "150px");
        }
        if (minHeight != null) {
            textArea.getStyle().set("minHeight", "150px");
        }

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
        // datePicker.setLocale(Locale.ITALY);
        datePicker.setClearButtonVisible(true);
        //   datePicker.set("dd/MM/yyyy");
        //    date.format(DateTimeFormatter.ofPattern("dd/mm/yyyy"));

        return datePicker;
    }

    public static ComboBox<FMMPrimas> getComboMPrimas(String label, FMMPrimas fMMPrimas) {
        ComboBox<FMMPrimas> combo = new ComboBox<FMMPrimas>();
        if (label != null) {
            combo.setLabel(label);
        }
        if (fMMPrimas != null) {
            combo.setValue(fMMPrimas);
        }
        combo.setItems(new FMMprimasDAO().getListaMprimas(null));

        combo.setItemLabelGenerator(FMMPrimas::getProducto);

        combo.setWidth("300px");
        return combo;
    }

    public static IntegerField getIntegerField(String label) {
        IntegerField campo = new IntegerField();
        if (label != null) {
            campo.setLabel(label);
        }
        return campo;
    }

}
