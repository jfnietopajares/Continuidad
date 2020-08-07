package es.sacyl.hnss.listados;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.entidades.FMFormulaBibliografia;
import es.sacyl.hnss.entidades.FMFormulaCompo;
import es.sacyl.hnss.entidades.FMFormulaElabora;
import es.sacyl.hnss.entidades.FMFormulaMaterial;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author JuanNieto
 */
public class FMFormulaFicha implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(FMFormulaFicha.class);

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final PdfWriter writer = new PdfWriter(out);

    private final PdfDocument pdf = new PdfDocument(writer);

    private Document document = new Document(pdf);

    private final DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    private PdfFont normal;

    private PdfFont bold;

    private int fontSize = 13;

    private int altoFila = 12;

    private FMFormula fMFormula = new FMFormula();

    public FMFormulaFicha(FMFormula fMFormula) {
        this.fMFormula = fMFormula;

        createPdf();
    }

    public InputStream getStream() {
        // Here we return the pdf contents as a byte-array
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayOutputStream getOut() {
        return out;
    }

    public File getFilePdf() {
        return Utilidades.iStoFile(this.getStream(), Constantes.DIRECTORIOREPORTABSOLUTEPATH + fMFormula.getNumero() + ".pdf");
    }

    public void createPdf() {

        try {
            normal = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

            bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);

            document = new Document(pdf, PageSize.A4).setTextAlignment(TextAlignment.LEFT);

            document.setMargins(25, 15, 5, 35);

            PdfFont times = null;
            try {
                times = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PdfEventoPaginaListado evento = new PdfEventoPaginaListado(document, Constantes.SERVICIO_NOMBRE,
                    Constantes.SECCION_NOMBRE);

            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, evento);

            document.add(new Paragraph(new Text("\n\n\n\n\n\n  Nº:" + fMFormula.getNumero() + " " + fMFormula.getNombre())
                    .setFont(bold).setFontSize(15).setHorizontalAlignment(HorizontalAlignment.CENTER.CENTER)));

            document.add(new Paragraph().add(new Text(fMFormula.getNombre()).setFont(bold).setFontSize(17)
                    .setHorizontalAlignment(HorizontalAlignment.LEFT)));
            float[] anchos1 = {530f};
            float[] anchos2 = {130f, 400f};
            float[] anchos3 = {30f, 400f, 100F};
            Table tabla = new Table(anchos1);
            tabla.setMarginTop(10);
            tabla.addCell(new Cell().add(new Paragraph("INDICACIÓN").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos2);
            tabla.addCell(new Cell().add(new Paragraph("\n")));
            if (fMFormula.getIndicacion() != null) {
                tabla.addCell(new Cell().add(new Paragraph(fMFormula.getIndicacion()).setFont(times).setFontSize(11)));
            } else {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
            }
            document.add(tabla);

            tabla = new Table(anchos1);
            tabla.addCell(new Cell().add(new Paragraph("VÍA DE ADMINISTRACIÓN").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos2);
            tabla.addCell(new Cell().add(new Paragraph("\n")));
            if (fMFormula.getVia() != null && fMFormula.getVia().getNombre() != null) {
                tabla.addCell(new Cell().add(new Paragraph(fMFormula.getVia().getNombre()).setFont(times).setFontSize(11)));
            } else {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
            }
            document.add(tabla);

            tabla = new Table(anchos1);
            tabla.addCell(new Cell().add(new Paragraph("COMPOSICIÓN CUALITATIVA Y CUANTITATIVA").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos3);
            for (FMFormulaCompo compo : fMFormula.getLisaComposiciones()) {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
                tabla.addCell(new Cell().add(new Paragraph(compo.getMprimaDescripcion())));
                tabla.addCell(new Cell().add(new Paragraph(compo.getUnidades())));
            }
            document.add(tabla);

            tabla = new Table(anchos1);
            tabla.addCell(new Cell().add(new Paragraph("MATERIAL Y UTILLAJE").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos3);
            for (FMFormulaMaterial material : fMFormula.getLisaMaterial()) {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
                tabla.addCell(new Cell().add(new Paragraph(material.getInstrumentoNombre())));
                tabla.addCell(new Cell().add(new Paragraph(Integer.toString(material.getUnidades()))));
            }
            document.add(tabla);

            tabla = new Table(anchos1);
            tabla.addCell(new Cell().add(new Paragraph("MÉTODO DE ELABORACIÓN").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos3);
            for (FMFormulaElabora elabora : fMFormula.getLisaElaboraciones()) {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
                tabla.addCell(new Cell().add(new Paragraph(elabora.getTexto())));
            }

            tabla = new Table(anchos1);
            tabla.addCell(new Cell().add(new Paragraph("CONSERVACIÓN Y CADUCIDAD").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos3);
            tabla.addCell(new Cell().add(new Paragraph("\n")));
            if (fMFormula.getConservacion() != null) {
                tabla.addCell(new Cell().add(new Paragraph(fMFormula.getConservacion())));
            } else {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
            }
            tabla.addCell(new Cell().add(new Paragraph("\n")));

            tabla.addCell(new Cell().add(new Paragraph("\n")));

            if (fMFormula.getCaducidad() != null) {
                tabla.addCell(new Cell().add(new Paragraph(fMFormula.getCaducidad())));
            } else {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
            }

            tabla.addCell(new Cell().add(new Paragraph("\n")));

            document.add(tabla);

            tabla = new Table(anchos1);
            tabla.addCell(new Cell().add(new Paragraph("CONTROLES").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos3);
            tabla.addCell(new Cell().add(new Paragraph("\n")));
            if (fMFormula.getControles() != null) {
                tabla.addCell(new Cell().add(new Paragraph(fMFormula.getControles())));
            } else {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
            }

            tabla.addCell(new Cell().add(new Paragraph("\n")));

            tabla = new Table(anchos1);
            tabla.addCell(new Cell().add(new Paragraph("OBSERVACIONES").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos3);
            tabla.addCell(new Cell().add(new Paragraph("\n")));
            if (fMFormula.getObservaciones() != null) {
                tabla.addCell(new Cell().add(new Paragraph(fMFormula.getObservaciones())));
            } else {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
            }

            tabla.addCell(new Cell().add(new Paragraph("\n")));

            tabla = new Table(anchos1);
            tabla.addCell(new Cell().add(new Paragraph("UNIDADES").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos3);
            tabla.addCell(new Cell().add(new Paragraph("\n")));
            if (fMFormula.getUnidades_f() != null) {
                tabla.addCell(new Cell().add(new Paragraph(Integer.toString(fMFormula.getUnidades_f()))));
            } else {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
            }
            if (fMFormula.getDunidades_f() != null) {
                tabla.addCell(new Cell().add(new Paragraph(fMFormula.getDunidades_f())));
            } else {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
            }

            document.add(tabla);

            tabla = new Table(anchos1);
            tabla.addCell(new Cell().add(new Paragraph("BIBLIOGRAFIA").setFont(times).setFontSize(11)));
            document.add(tabla);
            tabla = new Table(anchos3);
            for (FMFormulaBibliografia biblio : fMFormula.getLisaBibliografias()) {
                tabla.addCell(new Cell().add(new Paragraph("\n")));
                tabla.addCell(new Cell().add(new Paragraph(biblio.getTexto())));
            }

            document.close();

        } catch (FileNotFoundException e) {
            logger.error("Fichero no encontrado", e);
        } catch (java.io.IOException e) {
            logger.error("IO exception ", e);
        } catch (Exception e) {
            logger.error("Exception ", e);
        }
    }

}
