/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.utilidades;

/**
 *
 * @author JuanNieto
 */
public class Constantes {

    /*
    public static final String DIRECTORIOREPORTS = System.getProperty("catalina.home")
            + System.getProperty("file.separator") + "webapps" + System.getProperty("file.separator") + "reports"
            + System.getProperty("file.separator");
     */
 /*
    public static final String DIRECTORIOREPORTS
            = System.getProperty("file.separator") + "tmp" + System.getProperty("file.separator");
     */
    /*
    public static final String DIRECTORIOREPORTABSOLUTEPATH
            = "/Users/JuanNieto/NetBeansProjects/farmacia/src/main/webapp/reports/";
*/
    public static final String DIRECTORIOREPORTSRELATIVEPATH
            = "reports"+ System.getProperty("file.separator");

    public static final String DIRECTORIOICONOSABSOLUTEPATH
            = "/Users/JuanNieto/NetBeansProjects/farmacia/src/main/webapp/icons/";
    
    public static final String DIRECTORIORICONOSRELATIVEPATH
            = "icons"+ System.getProperty("file.separator");

    public static final String SERVICIO_NOMBRE = "Servicio de Farmacia";
    public static final String SECCION_NOMBRE = "Fórmulas Magistrales";

    public static final String WWWPDFS = "/reports/";

    public static final String URLREPORTS = System.getProperty("file.separator") + "webapps"
            + System.getProperty("file.separator") + "reports" + System.getProperty("file.separator");

    public static final String IMGDIRECTORY = System.getProperty("catalina.home")
            + System.getProperty("file.separator") + "webapps" + System.getProperty("file.separator");

    public static final String URLPUBLICPDFS = "http://10.36.64.219:8080/coasistencial";

    public static final String ERRORIOEXCEPCION = "IOException";

    public static final String SEPARADOR = System.getProperty("file.separator");

    public Constantes() {

    }

    public String getPathAbsoluto() {
        String path = "";
String sSistemaOperativo = System.getProperty("os.name");
//System.out.println(sSistemaOperativo);
        if (sSistemaOperativo.equals("Windows 10")) {
       //     path = "C:" + SEPARADOR + "Users" + SEPARADOR + "06551256M" + SEPARADOR + "Documents" + SEPARADOR + "NetBeansProjects" 
         //           + SEPARADOR + "farmacia" + SEPARADOR + "src" + SEPARADOR + "main" + SEPARADOR ;
         path=  System.getProperty("catalina.home")

            + System.getProperty("file.separator") + "webapps" + System.getProperty("file.separator") ;
      //C:\Users\06551256M\Documents\NetBeansProjects\farmacia\src\main\webapp\reports
        } else {
          path  = "/Users/JuanNieto/NetBeansProjects/farmacia/src/main/webapp/";
        }
        return path;
    }

            
}
