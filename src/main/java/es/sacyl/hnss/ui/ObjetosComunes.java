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
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import es.sacyl.hnss.dao.FMFormaDAO;
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.dao.FMInstrumentosDAO;
import es.sacyl.hnss.dao.FMMprimasDAO;
import es.sacyl.hnss.dao.FMViasAdmDAO;
import es.sacyl.hnss.dao.FarmatoolsDAO;
import es.sacyl.hnss.entidades.FMForma;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaAutoriza;
import es.sacyl.hnss.entidades.FMFormulaTipo;
import es.sacyl.hnss.entidades.FMInstrumento;
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMViasAdm;
import es.sacyl.hnss.entidades.Medicamento;
import es.sacyl.hnss.entidades.PrActivo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author 06551256M
 */
public class ObjetosComunes {

    /*
    $tsino[0][0]='S';
$tsino[1][0]='Si';
$tsino[0][1]='N';
$tsino[1][1]='No';




$tnunidadessup=1;
$tunidadessup[0][0]='M2';
$tunidadessup[1][0]='M2' ;





$tunidadesti[0]='horas';
$tunidadesti[1]='dias' ;



$tconservante[]='Si';
$tconservante[]='No';

$tcsobrante[]='Si';
$tcsobrante[]='No';

$tconservacion[]='Nevera';
$tconservacion[]='T.Ambiente';
$tconservacion[]='T.Ambiente y nevera';


$tformafar[]='Ampolla';
$tformafar[]='Vial';




$tbases[]='Fisiológico';
$tbases[]='Glucosado';
$tbases[]='Glucosalino';
$tbases[]='Agua';
$tbases[]='Hipertónica';
$tbases[]='Salino';
$tbases[]='Salino hipertónico';
$tbases[]='Otros';


$tnaturaleza[]='Vidrio';
$tnaturaleza[]='PVC';
$tnaturaleza[]='Viaflo';
$tnaturaleza[]='Infusor';
$tnaturaleza[]='Otros';


$tvias[]='Infusion';
$tvias[]='Infusion continua';
$tvias[]='Intravenosa directa';
$tvias[]='Intrapleural';
$tvias[]='Intramuscular';
$tvias[]='Subcutanea';
$tvias[]='Intradermica';
$tvias[]='Irrigación vesical';
$tvias[]='Intratecal';
$tvias[]='Conjuntival';
$tvias[]='Intracutanea';


  $torganos[]="ANO";
  $torganos[]="CEREBRAL";
  $torganos[]="CERVIX";
  $torganos[]="COLON";
  $torganos[]="DE PIEL";
  $torganos[]="ESOFAGO";
  $torganos[]="GASTRICO";
  $torganos[]="GANGLIOS";
  $torganos[]="HEPATICO";
  $torganos[]="HUESO";
  $torganos[]="INTESTINO DELGADO";
  $torganos[]="LARINGE";
  $torganos[]="MAMA";
  $torganos[]="MESOTELIOMA";
  $torganos[]="NEUROENDOCRINO";
  $torganos[]="ORIGEN DESCONOCIDO";
  $torganos[]="OTROS TUMORES";
  $torganos[]="OTROS TUMORES ORL";
  $torganos[]="OVARIO";
  $torganos[]="PANCREAS";
  $torganos[]="PROSTATA";
  $torganos[]="PULMSN";
  $torganos[]="RECTO";
  $torganos[]="RENAL";
  $torganos[]="SARCOMA";
  $torganos[]="TEJIDOS BLANDOS";
  $torganos[]="TESTICULO";
  $torganos[]="TIROIDES";
  $torganos[]="UTERO";
  $torganos[]="VEJIGA";
  $torganos[]="VMAS BILIARES";


 $thistologia[]="ADENOCARCINOMA";
 $thistologia[]="ADENOCARCINOMA DE CELULAS EN ANILLO DE SELLO";
 $thistologia[]="ASTROCITOMA ANAPLASTICO";
 $thistologia[]="CARCINOMA EMBIONARIO";
 $thistologia[]="CARCINOMA ANAPLASICO";
 $thistologia[]="CARCINOMA ENDOMETRIAL";
 $thistologia[]="CARCINOMA EPIDERMOIDE";
 $thistologia[]="CARCINOMA DE CELULAS GRANDES";
 $thistologia[]="CARCINOMA DUCTAL INFILTRANTE";
 $thistologia[]="CARCINOMA FOLICULAR";
 $thistologia[]="CARCINOMA INDIFERENCIADO";
 $thistologia[]="CARCINOMA LOBULILLAR INFILTRANTE ";
 $thistologia[]="CARCINOMA MICROCITICO";
 $thistologia[]="CARCINOMA PAPILAR";
 $thistologia[]="CARCINOMA SEROSO";
 $thistologia[]="CARCINOMA UROTELIAL";
 $thistologia[]="COLANGIOCARCINOMA";
 $thistologia[]="CORIOCARCINOMA";
 $thistologia[]="FIBROHISTOCITOMA PLEOMSRFICO MALIGNO";
 $thistologia[]="GASTRINOMA";
 $thistologia[]="GIST";
 $thistologia[]="GLIOBLASTOMA MULTIFORME";
 $thistologia[]="HEPATOCARINOMA";
 $thistologia[]="HIPERNEFROMA";
 $thistologia[]="INSULINOMA";
 $thistologia[]="LINFOMA";
 $thistologia[]="LIPOSARCOMA";
 $thistologia[]="MELANOMA";
 $thistologia[]="MESOTELIOMA";
 $thistologia[]="OLIGODENDROGLINOMA";
 $thistologia[]="OTRA HISTOLOGIA";
 $thistologia[]="OTROS SARCOMAS";
 $thistologia[]="SARCOMA DE KAPOSI";
 $thistologia[]="SEMINOMA";
 $thistologia[]="T. CARCINOIDE";
 $thistologia[]="T. EMBRIONARIO";
     */
    public static ArrayList<String> SINO = new ArrayList<String>() {
        {
            add("S");
            add("N");
        }
    };

    public static ArrayList<String> CITOSBASES = new ArrayList<String>() {
        {
            add("Fisiológico");
            add("Glucosado");
            add("Glucosalino");
            add("Agua");
            add("Hipertónica");
            add("Salino");
            add("Salino hipertónico");
            add("Otros");

        }
    };

    public static ArrayList<String> CITOSUNIDADESV = new ArrayList<String>() {
        {
            add("ml");
            add("ml");
        }
    };

    public static ArrayList<String> CITOSTIPOVOLUMEN = new ArrayList<String>() {
        {
            add("Diluyente");
            add("Mezcla");
        }
    };

    public static ArrayList<String> CITOSTNATURALEZA = new ArrayList<String>() {
        {
            add("Vidrio");
            add("Mezcla");
            add("PVC");
            add("Viaflo");
            add("Infusor");
            add("Otros");
        }
    };

    public static ArrayList<String> CITOSTUNIDADESP = new ArrayList<String>() {
        {
            add("mg");
            add("MU");
            add("UI");
            add("g");
            add("mcp");
        }
    };
    public static ArrayList<String> CITOSTUNIDADES = new ArrayList<String>() {
        {
            add("ml");
            add("mg");
            add("MU");
            add("UI");
            add("g");
            add("M2");
            add("mcg");
        }
    };
    public static ArrayList<String> CITOSVESICANTE = new ArrayList<String>() {
        {
            add("Vesicante");
            add("Irritante");
            add("No irrit. ni vesicante.");
            add("Vesicantei/irritante.");
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

    public static ComboBox<PrActivo> getComboPrActivo(String label, PrActivo valor) {
        ComboBox<PrActivo> combo = new ComboBox<>();
        if (label != null) {
            combo.setLabel(label);
        }
        combo.setItems(new FarmatoolsDAO().getListaPrActivos());

        combo.setItemLabelGenerator(PrActivo::getNombre);

        if (valor != null) {
            combo.setValue(valor);
        }
        return combo;
    }

    public static ComboBox<Medicamento> getCombMedicamentos(String label, Medicamento valor, PrActivo prAvtivo) {
        ComboBox<Medicamento> combo = new ComboBox<>();
        if (label != null) {
            combo.setLabel(label);
        }
        combo.setItems(new FarmatoolsDAO().getListaMediPr(prAvtivo.getCodigo()));

        combo.setItemLabelGenerator(Medicamento::getNombre);

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
            textArea.getStyle().set("maxHeight", maxHeight);
        }
        if (minHeight != null) {
            textArea.getStyle().set("minHeight", minHeight);
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

    public static BigDecimalField getBigDecimalField(String label) {
        BigDecimalField campo = new BigDecimalField();
        if (label != null) {
            campo.setLabel(label);
        }
        return campo;
    }
}
