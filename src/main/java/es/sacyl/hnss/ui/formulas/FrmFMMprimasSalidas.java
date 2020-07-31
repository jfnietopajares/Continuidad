/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.ui.formulas;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import es.sacyl.hnss.dao.FMFormulaCompoDAO;
import es.sacyl.hnss.dao.FMMprimasDAO;
import es.sacyl.hnss.dao.FMMprimasEntraDAO;
import es.sacyl.hnss.dao.FMMprimasSalidaDAO;
import es.sacyl.hnss.entidades.FMFormulaCompo;
import es.sacyl.hnss.entidades.FMMPrimas;
import es.sacyl.hnss.entidades.FMMPrimasEntrada;
import es.sacyl.hnss.entidades.FMMprimasSalida;
import es.sacyl.hnss.ui.ConfirmDialog;
import es.sacyl.hnss.ui.FrmMaster;
import es.sacyl.hnss.ui.FrmMasterLista;
import es.sacyl.hnss.ui.ObjetosComunes;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author JuanNieto
 */
public class FrmFMMprimasSalidas extends FrmMasterLista {

    //   private IntegerField cod_inte = ObjetosComunes.getIntegerField("Código");
    // private TextField producto = ObjetosComunes.getTextField("Nombre prodcuto", "nombre", 50, "100px");
    private final IntegerField numero = ObjetosComunes.getIntegerField("Número");

    private final IntegerField cantidad = ObjetosComunes.getIntegerField("Cantidad");

    private final TextField comentario1 = ObjetosComunes.getTextField("Comentario", "comentario", 50, "100px");

    private final DatePicker fecha = ObjetosComunes.getDatePicker("Fecha salida", null, LocalDate.now());

    private FMMprimasSalida fMMprimasSalida = new FMMprimasSalida();

    private FMMprimasSalida fMMprimasSalidaAnterior = new FMMprimasSalida();

    private FMMPrimas fMMprimas = new FMMPrimas();

    private TextField producto = ObjetosComunes.getTextField("Producto", "comentario", 50, "100px");

    private Binder<FMMprimasSalida> binder = new Binder<FMMprimasSalida>();

    private Grid<FMMprimasSalida> grid = new Grid<>();

    public FrmFMMprimasSalidas() {
        super();
        doHazFormulario();
    }

    public FrmFMMprimasSalidas(FMMprimasSalida fMMprimasSalidas) {
        super();
        this.fMMprimasSalida = fMMprimasSalidas;
        this.fMMprimasSalidaAnterior = fMMprimasSalidas;
        doHazFormulario();
    }

    public FrmFMMprimasSalidas(FMMPrimas fMMPrimas) {
        super();
        if (fMMPrimas != null) {
            this.fMMprimas = fMMPrimas;
            this.fMMprimasSalida = new FMMprimasSalida(fMMPrimas);
        }
        doHazFormulario();
    }

    public void doHazFormulario() {

        titulo.setText(fMMprimasSalida.getLabelFrom());

        contenedorFormulario.setResponsiveSteps(
                new FormLayout.ResponsiveStep("100px", 1),
                new FormLayout.ResponsiveStep("100px", 2),
                new FormLayout.ResponsiveStep("100px", 3));

        contenedorFormulario.setMaxWidth("600px");
        contenedorFormulario.add(producto, 3);
        contenedorFormulario.add(numero, fecha);
        contenedorFormulario.add(cantidad);
        contenedorFormulario.add(comentario1, 3);

        contenedorDerecha.add(grid);

        producto.setReadOnly(true);
        binder.forField(producto)
                .asRequired()
                .bind(FMMprimasSalida::getProducto, FMMprimasSalida::setProducto);
        binder.forField(numero)
                .asRequired()
                .bind(FMMprimasSalida::getNumero, FMMprimasSalida::setNumero);
        binder.forField(fecha)
                .asRequired()
                .bind(FMMprimasSalida::getFecha, FMMprimasSalida::setFecha);
        binder.forField(cantidad)
                .asRequired()
                .bind(FMMprimasSalida::getCantidad, FMMprimasSalida::setCantidad);
        binder.forField(comentario1)
                .bind(FMMprimasSalida::getComentario1, FMMprimasSalida::setComentario1);

        binder.readBean(fMMprimasSalida);
        doControlBotones(fMMprimasSalida.getNumero());

        grid.addColumn(FMMprimasSalida::getNumero).setHeader("Número");
        grid.addColumn(FMMprimasSalida::getFecha).setHeader("Fecha");
        grid.addColumn(FMMprimasSalida::getCantidad).setHeader("Unidades");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addItemClickListener(event
                -> {
            doFormatoCodigoProducto(event.getItem());
        }
        );
        doActualizaGrid();
        doFormatoCodigoProducto(null);
    }

    public void doFormatoCodigoProducto(FMMprimasSalida fMMprimasSalidaParam) {

        if (fMMprimasSalidaParam == null) {
            fMMprimasSalida = new FMMprimasSalida(fMMprimas);
            binder.readBean(fMMprimasSalida);
            numero.setValue(new FMMprimasSalidaDAO().getNumeroSiguiente(fMMprimasSalida));
            fecha.setValue(LocalDate.now());
            cantidad.focus();
            botonBorrar.setEnabled(false);
        } else {
            fMMprimasSalida = fMMprimasSalidaParam;
            binder.readBean(fMMprimasSalida);
            botonBorrar.setEnabled(true);
            numero.setReadOnly(true);
            numero.getStyle().set("color", "red");
            numero.getStyle().set("fontWeight", "bold");
            numero.getStyle().set("font-weight", "bold");

            cantidad.focus();
        }

    }

    public void doActualizaGrid() {
        grid.setItems(new FMMprimasSalidaDAO().getListaSalidasMP(null, null, fMMprimasSalida));
    }

    public void doActualizaExistencias(String tipoMovimiento) {
        Integer variacionExistencias = fMMprimasSalida.getVariacionExistencias(fMMprimasSalidaAnterior.getCantidad(), tipoMovimiento);
        if (variacionExistencias != 0) {
            if (new FMMprimasDAO().doActualizaExistencias(fMMprimasSalida, variacionExistencias)) {
                Notification.show(FrmMaster.AVISODATOALMACENADO);
            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD + " Sin actualizar existenicas", 1000, Notification.Position.MIDDLE)).open();
            }
        }
    }

    @Override
    public void doGrabar() {

        if (binder.writeBeanIfValid(fMMprimasSalida)) {
            if (new FMMprimasSalidaDAO().doGrabaDatos(fMMprimasSalida) == true) {
                (new Notification(FrmMaster.AVISODATOALMACENADO, 1000, Notification.Position.MIDDLE)).open();
                doActualizaExistencias(FrmFMMprimas.MOVIMIENTOGRABAR);
            } else {
                (new Notification(FrmMaster.AVISODATOERRORBBDD, 1000, Notification.Position.MIDDLE)).open();
            }
            doActualizaGrid();
            doFormatoCodigoProducto(null);// this.close();
        } else {
            BinderValidationStatus<FMMprimasSalida> validate = binder.validate();
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
        ConfirmDialog dialog = new ConfirmDialog("Borrado del registro actual",
                "Seguro que quires borrar el dato actual. Numero de entrada : " + fMMprimasSalidaAnterior.getNumero(),
                "Borrar", this::onDelete);

        dialog.open();

        dialog.addDialogCloseActionListener(e -> {
            this.close();
        });
    }

    public void onDelete() {
        if (new FMMprimasSalidaDAO().doBorraDatos(fMMprimasSalida) == true) {
            Notification.show(FrmMaster.AVISODATOBORRADO);
            // descuenta existencias
            doActualizaExistencias(FrmFMMprimas.MOVIMIENTOBORRAR);
            this.close();
        } else {
            Notification.show(FrmMaster.AVISODATOERRORBORRADO);
        }
    }

    public void onDiscard() {

    }

    public void onCancel() {

    }

    @Override
    public void doAyuda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
