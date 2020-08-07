/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.listados;

/**
 *
 * @author JuanNieto
 */

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.vaadin.server.StreamResource.StreamSource;
import es.sacyl.hnss.dao.FMFormulasDAO;
import es.sacyl.hnss.entidades.FMFormula;
import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FMFormulasLibro implements StreamSource {

    private static final Logger logger = LogManager.getLogger(FMFormulasLibro.class);
    private static final long serialVersionUID = 1L;


    private final ByteArrayOutputStream outTodos = new ByteArrayOutputStream();
    private PdfWriter writerTodos;
    private PdfDocument pdfTodos;
    private Document documentTodos;


    private final String urlfile;
    private String nombreFile;
    private String wwwnombreFile;
    private String linuxnombreFile;
    private int sizeFont = 7;
    

    public FMFormulasLibro() {
        this.nombreFile = "Formulas_Libro.pdf";
        this.urlfile = Constantes.URLREPORTS + nombreFile;
        this.wwwnombreFile = Constantes.WWWPDFS + nombreFile;
      //  this.linuxnombreFile = Constantes.DIRECTORIOREPORTS + nombreFile;

       doJuntarTodosLosPdf();
    }

    public String getUrl() {
        return urlfile;
    }

 

    @Override
    public InputStream getStream() {
        return new ByteArrayInputStream(outTodos.toByteArray());
    }

  
    public File getFilePdfCompleto() {
        //String nombreFile = urlfile ;
        return Utilidades.iStoFile(new ByteArrayInputStream(outTodos.toByteArray()), linuxnombreFile);
    }

    public ByteArrayOutputStream getOut() {
        return outTodos;
    }

    public void doJuntarTodosLosPdf() {
        writerTodos = new PdfWriter(outTodos);
        pdfTodos = new PdfDocument(writerTodos);
        try {
            PdfDocument documentTodos = new PdfDocument(writerTodos);
            PdfReader reader = new PdfReader(new ByteArrayInputStream(new FMFormulasListado().getOut().toByteArray()));
            PdfDocument pdfDoc = new PdfDocument(reader);
            int n1 = pdfDoc.getNumberOfPages();
            for (int i = 1; i <= n1; i++) {
                PdfPage page = pdfDoc.getPage(i).copyTo(documentTodos);
                documentTodos.addPage(page);
            }
            pdfDoc.close();

            ArrayList<FMFormula> listaFormulas = new FMFormulasDAO().getListaFormulas(null);
          for (FMFormula formula : listaFormulas) {
                      reader = new PdfReader( new FMFormulaFicha(formula).getFilePdf());
                     //   reader = new PdfReader(new ByteArrayInputStream(new FMFormulaFicha(formula).getOut().toByteArray()));
                        pdfDoc = new PdfDocument(reader);
                        n1 = pdfDoc.getNumberOfPages();
                        for (int i = 1; i <= n1; i++) {
                            PdfPage page = pdfDoc.getPage(i).copyTo(documentTodos);
                            documentTodos.addPage(page);
                        }
                        pdfDoc.close();
                      //  reader.close();
                    //    break;
                 
            }
            documentTodos.close();
        } catch (com.itextpdf.io.IOException | java.io.IOException e) {
            logger.error(e);
        }
    }

   
   
}
