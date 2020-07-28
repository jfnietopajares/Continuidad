package es.sacyl.hnss.listados;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import es.sacyl.hnss.utilidades.Constantes;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author david
 */
public class PdfEventoPaginaListado implements IEventHandler, Serializable {

    private static final Logger logger = LogManager.getLogger(PdfEventoPaginaListado.class);
    private static final long serialVersionUID = 1L;
    private final Document documento;

    private String texto;
    private String texto1;

    public PdfEventoPaginaListado(Document doc, String texto, String texto1) {
        this.documento = doc;
        this.texto = texto;
        this.texto1 = texto1;
    }

    /**
     * Crea el rectangulo donde pondremos el encabezado
     *
     * @param docEvent Evento de documento
     * @return Area donde colocaremos el encabezado
     */
    /*
    private Rectangle crearRectanguloEncabezado(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xEncabezado = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yEncabezado = pdfDoc.getDefaultPageSize().getTop() - documento.getTopMargin();
        float anchoEncabezado = page.getPageSize().getWidth() - 72;
        float altoEncabezado = 30F;

        Rectangle rectanguloEncabezado = new Rectangle(xEncabezado, yEncabezado, anchoEncabezado, altoEncabezado);

        return rectanguloEncabezado;
    }
     */
    private Rectangle crearRectanguloEncabezadoPag1(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xEncabezado = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yEncabezado = pdfDoc.getDefaultPageSize().getTop() - documento.getTopMargin() - 80;
        float anchoEncabezado = page.getPageSize().getWidth() - 72;
        float altoEncabezado = 50F;

        Rectangle rectanguloEncabezado = new Rectangle(xEncabezado, yEncabezado, anchoEncabezado, altoEncabezado);

        return rectanguloEncabezado;
    }

    /**
     * Crea el rectangulo donde pondremos el pie de pagina
     *
     * @param docEvent Evento del documento
     * @return Area donde colocaremos el pie de pagina
     */
    private Rectangle crearRectanguloPie(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xPie = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yPie = pdfDoc.getDefaultPageSize().getBottom();
        float anchoPie = page.getPageSize().getWidth() - 72;
        float altoPie = 50F;

        Rectangle rectanguloPie = new Rectangle(xPie, yPie, anchoPie, altoPie);

        return rectanguloPie;
    }

    /**
     * Crea la tabla que contendra el mensaje del encabezado
     *
     * @param mensaje Mensaje que desplegaremos
     * @return Tabla con el mensaje de encabezado
     */
    private Table crearTablaEncabezado(String mensaje) {
        float[] anchos = {1F};
        Table tablaEncabezado = new Table(anchos);
        tablaEncabezado.setWidth(527F);

        tablaEncabezado.addCell(mensaje).setFontSize(9);

        return tablaEncabezado;
    }

    /**
     * Crea la tabla de pie de pagina, con el numero de pagina
     *
     * @param docEvent Evento del documento
     * @return Pie de pagina con el numero de pagina
     */
    private Table crearTablaPie(PdfDocumentEvent docEvent) {
        PdfPage page = docEvent.getPage();
        float[] anchos = {1F};
        Table tablaPie = new Table(anchos);
        tablaPie.setWidth(527F);
        Integer pageNum = docEvent.getDocument().getPageNumber(page);

        tablaPie.addCell("Pagina " + pageNum).setFontSize(9).setHorizontalAlignment(HorizontalAlignment.CENTER);

        return tablaPie;
    }

    /**
     * Manejador del evento de cambio de pagina, agrega el encabezado y pie de
     * pagina
     *
     * @param event Evento de pagina
     */
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
        Table tablaEncabezado;
        Rectangle rectanguloEncabezado;
        Canvas canvasEncabezado;
        Integer pageNum = docEvent.getDocument().getPageNumber(page);

        Text first, second, tercero, cuarto;
        Cell celda = new Cell();
        Paragraph parrafo;
        PdfFont normal = null, bold = null;
        DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        float[] anchos2Columnas = {270f, 270f};

        tablaEncabezado = new Table(anchos2Columnas);

        String imageFile = System.getProperty("catalina.home")
                + System.getProperty("file.separator")
                + "webapps"
                + System.getProperty("file.separator")
                + "farmacia"
                + System.getProperty("file.separator")
                //   + "WEB-INF"
                //  + System.getProperty("file.separator")
                + "icons"
                + System.getProperty("file.separator")
                + "logoimagenes.png";
        imageFile = Constantes.DIRECTORIORICONOSRELATIVEPATH + "logoimagenes.png";
        try {
            normal = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);

            ImageData data = ImageDataFactory.create(imageFile);
            Image image = new Image(data);
            tablaEncabezado.addCell(new Cell().add(image)); // primera columna para logo
        } catch (Exception e) {
            logger.error("Fichero de imagen no encontrado" + imageFile);
            //   new NotificacionInfo("Fichero de imagen no encontrado" + imageFile, true);
            tablaEncabezado.addCell(new Cell());
        } finally {

            parrafo = new Paragraph(texto).setFont(bold).setFontSize(15);
            celda.add(parrafo);
            parrafo = new Paragraph(texto1).setFont(bold).setFontSize(15);
            celda.add(parrafo);

            tablaEncabezado.addCell(celda);

            celda = new Cell()
                    .add(new Paragraph(texto).setFont(normal).setFontSize(9)
                            .setTextAlignment(TextAlignment.CENTER))
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            tablaEncabezado.addCell(celda);

            rectanguloEncabezado = this.crearRectanguloEncabezadoPag1(docEvent);

            canvasEncabezado = new Canvas(canvas, pdfDoc, rectanguloEncabezado);

            canvasEncabezado.add(tablaEncabezado);
        }

        // Table tablaNumeracion = this.crearTablaPie(docEvent);
        Rectangle rectanguloPie = this.crearRectanguloPie(docEvent);

        Canvas canvasPie = new Canvas(canvas, pdfDoc, rectanguloPie);

        Paragraph paragraph = new Paragraph().add(new Text("-" + Integer.toString(pageNum) + "-"))
                .setTextAlignment(TextAlignment.CENTER);
        canvasPie.add(paragraph);
    }
}
