package es.sacyl.hnss.listados;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import es.sacyl.hnss.entidades.FMFormula;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
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

    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    private PdfWriter writer = new PdfWriter(out);

    private PdfDocument pdf = new PdfDocument(writer);

    private Document document;

    private DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    private PdfFont normal;

    private PdfFont bold;

    private int fontSize = 13;

    private int altoFila = 12;

    private FMFormula fMFormula = new FMFormula();

    public FMFormulaFicha() {

        createPdf();
    }

    public FMFormulaFicha(FMFormula fMFormula) {
        this.fMFormula = fMFormula;

        createPdf();
    }

    public InputStream getStream() {
        // Here we return the pdf contents as a byte-array
        return new ByteArrayInputStream(out.toByteArray());
    }

    public void createPdf() {

        try {
            normal = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

            bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);

            document = new Document(pdf, PageSize.A4).setTextAlignment(TextAlignment.LEFT);

            document.setMargins(25, 15, 5, 35);

            PdfEventoPaginaListado evento = new PdfEventoPaginaListado(document, "Texto 1",
                    "Texto 2");

            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, evento);

            document.add(new Paragraph(new Text("\n\n\n\n\n\n  Nº:" + fMFormula.getNumero() + " " + fMFormula.getNombre())
                    .setFont(bold).setFontSize(15).setHorizontalAlignment(HorizontalAlignment.CENTER.CENTER)));

            document.add(new Paragraph().add(new Text(fMFormula.getNombre()).setFont(bold).setFontSize(12)
                    .setHorizontalAlignment(HorizontalAlignment.LEFT)));

            document.close();

        } catch (FileNotFoundException e) {
            logger.error("Fichero no encontrado", e);
        } catch (java.io.IOException e) {
            logger.error(" io e", e);
        } catch (Exception e) {
            logger.error("EX", e);
        }
    }

}
