/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.utilidades;

import com.vaadin.flow.server.VaadinServlet;
import java.io.File;
import java.net.URL;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.Level;
import static org.apache.logging.log4j.LogManager.getLogger;

/**
 *
 * @author JuanNieto
 */
public class ServletUtils {

    /*
    public static File getBaseDirectory(VaadinServlet servlet) {
        final String realPath = getResourcePath(servlet.getServletContext(), "/");
        if (realPath == null) {
            return null;
        }
        return new File(realPath);
    }
     */
 /*
    public static String getResourcePath(ServletContext servletContext,
            String path) {
        String resultPath = null;
        resultPath = servletContext.getRealPath(path);
        if (resultPath != null) {
            return resultPath;
        } else {
            try {
                final URL url = servletContext.getResource(path);
                resultPath = url.getFile();
            } catch (final Exception e) {
                // FIXME: Handle exception
                getLogger().log(Level.INFO,
                        "Could not find resource path " + path, e);
            }
        }
        return resultPath;
    }
     */
}
