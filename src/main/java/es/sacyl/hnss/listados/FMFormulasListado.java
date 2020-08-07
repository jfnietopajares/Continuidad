/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.listados;

import com.itextpdf.io.IOException;
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
import com.vaadin.server.StreamResource;
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.utilidades.Constantes;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author JuanNieto
 */
public class FMFormulasListado implements StreamResource.StreamSource  {
   private static final Logger LOGGER = LogManager.getLogger(FMFormulasListado.class);
   
       private DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    private PdfFont normal;

    private PdfFont bold;

    private int fontSize = 8;

    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PdfWriter writer;
    private PdfDocument pdf;
    private Document document;
    
    public FMFormulasListado(){
         createPdf();
    }
     @Override
    public InputStream getStream() {
        // Here we return the pdf contents as a byte-array
        return new ByteArrayInputStream(out.toByteArray());
    }
     public ByteArrayOutputStream getOut() {
        return out;
    }
    public void createPdf() {

        writer = new PdfWriter(out);

        pdf = new PdfDocument(writer);

        document = new Document(pdf, PageSize.A4).setTextAlignment(TextAlignment.LEFT);

            document.setMargins(25, 15, 5, 35);
        try {
            
              normal = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

            bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);

            document = new Document(pdf, PageSize.A4).setTextAlignment(TextAlignment.LEFT);

            document.setMargins(25, 15, 5, 35);

            PdfFont times =   PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
           
            PdfEventoPaginaListado evento = new PdfEventoPaginaListado(document,Constantes.SERVICIO_NOMBRE,
                    Constantes.SECCION_NOMBRE);

            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, evento);

            document.add(new Paragraph(new Text("\n\n\n\n\n\n  LISTADO DE FÓRMULAS MAGISTRALES")
                    .setFont(bold).setFontSize(15).setHorizontalAlignment(HorizontalAlignment.CENTER)).setHorizontalAlignment(HorizontalAlignment.CENTER));

            ArrayList<FMFormula> listaFormulas = new FMFormulasDAO().getListaFormulas(null);
            
            for (FMFormula formula:listaFormulas){
                  document.add(new Paragraph(new Text("                  " + formula.getNombre())
                    .setFont(bold).setFontSize(12).setHorizontalAlignment(HorizontalAlignment.LEFT)));
            }
              document.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("Error FileNotFoundException" ,  e);
        } catch (IOException  e) {
              LOGGER.error("Error IO" ,  e);
        } catch (Exception e) {
               LOGGER.error("Error EXC" ,  e);
        }
       
    }
    
}
