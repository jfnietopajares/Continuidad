/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.utilidades;

import com.itextpdf.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author JuanNieto
 */
public class Utilidades {

    private static final Logger LOGGER = LogManager.getLogger(Utilidades.class);

    public static File iStoFile(InputStream inputStream, String nombreFichero) {

        OutputStream outputStream = null;
        File fichero = null;
        try {
            // InputStream inputStream = new FileInputStream(nombreFichero);
            fichero = new File(nombreFichero);
            if (fichero.exists() && fichero.isFile()) {
                fichero.delete();
            }

            fichero = new File(nombreFichero);
            outputStream = new FileOutputStream(fichero);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("Fichero no encontrado:" + nombreFichero, e);
        } catch (IOException e) {
            LOGGER.error("Fallo al leer o escribir" + nombreFichero, e);
        } catch (java.io.IOException e) {
            LOGGER.error("Fallo IO" + nombreFichero, e);
        } finally {
            try {
                outputStream.close();

            } catch (java.io.IOException e) {
                e.printStackTrace();
                LOGGER.error("Fallo IO cerrando outputStream", e);
            }
        }
        return fichero;
    }

}
