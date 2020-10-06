/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.hnss.utilidades;

import es.sacyl.hnss.entity.VariableEscala;
import java.util.ArrayList;

/**
 *
 * @author JuanNieto
 */
public class Constantes {
    
      public static final String COOKIE_NAME = "recordar-sesion";

    public static final String SESSION_USERNAME = "usuario";

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
     public static final String PROPERTIESNOMBREFICHERO = System.getProperty("catalina.home")
            + System.getProperty("file.separator") + "conf" + System.getProperty("file.separator")
            + "continuidad.properties";

    public static final String DIRECTORIOREPORTS = System.getProperty("catalina.home")
            + System.getProperty("file.separator") + "webapps" + System.getProperty("file.separator") + "reports"
            + System.getProperty("file.separator");



    
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

     public final static String SEPARADOR_FECHA = "/";
     
     
     
     
      public static final String LONGIN_USUARIO_NO_ENCONTRADO = "Usuario no encontrado.";

    public static final String LONGIN_USUARIO_NO_ACTIVO = "Usuario dasactivado.";

    public static final String CAMBIO_CLAVE_NO_COINCIDEN = "Las claves son distintas";

    public static final String SQLREGISTROBORRADO = " Se ha borrado el dato   ";

    public static final String SQLREGISTROSERRORBORRADO = " No se puede borrar el  dato tiene registros asociados   ";

    public static final String SQLREFERENCIASEXTERNAS = "Registro con referencias foráneas.  ";

    public static final String SQLERRORRESULSET = "Error resulset to registros  ";

    public static final String FORMULARIOCAMPOREQUERIDO = "En necesario registrar valor en este campo ";

    public static final String FORMULARIOFECHAMENOR = "El valor no puede ser menor de  ";

    public final static String ERROR_UI = "Error UI ";

    public final static String EXCEPTION_ERROR = "Excepción general.";
    public final static String EXCEPTION_IO = "IO Excepción.";
    public final static String EXCEPTION_FILENOTFOUND = "Fichero no encontrado.";

    public static final String PDFERROR = "Error pdf  ";

    public static final String BINDER_DATOS_NOVALIDOS = "Datos no válidos";

    public static final String BINDER_DATOS_ERRORVALIDACION = "Error de validación.";

    public static final String FORMULARIO_DATOS_GUARDADOS = "Datos almacenados.";

    public static final String FORMULARIO_DATOS_ERROR_GUARDADOS = "Erro almacenado datos.";

    public static final String FORMULARIO_DATO_BORRADO = "Dato borrado.";

    public static final String FORMULARIO_ERROR_DATO_BORRADO = "Error borrando dato.";

    public static final String FORMULARIO_DATO_DE_BUSQUEDA_NECESARIO = "Es necesario algún dato de búsqueda.";

    public static final String FORMULARIO_TF_EXISTENTE = "Ese teléfono ya está registrado en al base de datos.";

    public static final String GRID_DATO_NO_RECUPERADO = "Error en la recuperacion del dato.";

    public static final String GRID_PACIENTE_SIN_PROCESO = "El paciente no tiene proceso.";

    public static final String GRID_SIN_ELEGIR_DATO = "Dato no seleccionado.";

    public static final String GRID_DATOS_NO_ENCONTRADOS_FILTRO = "No hay datos para esos criterios.";

    public static final String FORMULARIONOHAYDATOSPARACRITERIO = "No se encuentran datos para esecriterio de búsqueda.  ";

    public static final String FIND_PACIENTE_NHC_NO_ENCONTRADO = "El paciente no encontrado. NHC: ";

    public static final String FIND_DATO_DE_BUSQUEDA_NO_VALIDO = "Dato de búsqueda no válido. ";

    public static final String FIND_PACIENTE_DNI_NO_ENCONTRADO = "El paciente no encontrado. DNI: ";

    public static final String FIND_PACIENTE_APELLIDOS_NO_ENCONTRADO = "El paciente no encontrado. APELLIDOS: ";

    public static final String PROCESO_ERROR_SUBAMBITO = "Sin formulario destino. Subambito != ";

    public static final String PROCESO_PACIENTE_PROCESO_ACTIVO = "Paciente con proceso activo.";

    public static final String PROCESO_PACIENTE_SIN_PROCESO_ACTIVO = "Paciente sin proceso activo.";

    public static final String PROCESO_PACIENTE_SIN_ELEIGR_PROCESO = "Sin proceso seleccionado.";

    public static final String BBDD_REGISTRO_PADRE_NULL = "Registro padre es nulo.";

    public static final String BBDD_REGISTRO_TIPO_NOVALIDO = "Tipo de registro no válido.";

    public static final String PACIENTE_NO_ENCONTRADO = "Paciente no encontrado.";

    public static final String PACIENTE_REPETIDO_NHC = "Ya existe un paciente ese número historia   ";

    public static final String PACIENTE_REPETIDO_DNI = "Ya existe un paciente con  es DNI ";

    public static final String ERROR_PACIENTE_ES_NUlO = "El paciente es nulo o no válido (0).";

    public static final String ERROR_PROCESO_ES_NULO = "El proceso es nulo o no válido (0).";

    public static final String ERROR_REGISTRO_ES_NULO = "El registro  es nulo.";

    public static final String ERRORTIPOFICHERONOVALIDO = "El tipo de fichero no es válido, debe ser pdf.";

    public final static String AVISO_DATO_NO_VALIDO = "Este dato no es válido ";

    public final static String MSG_EXCEPTION = "Error excepcion:";

    public final static String FORMULARIO_DATO_REPETIDO = "Ya existe un dato con ese valor";

    private static final long serialVersionUID = -646978632729992305L;

    public static final String DATO_BORRADO = " Dato borrado ";
    public static final String DATO_BORRADO_ERRO = " No se hapodido borrador el dato ";

    public static final String DATO_GRABADO = " Dato grabado ";

    public final static String DATO_ERROR = " Hay un error al registrar los datos en la base de datos";
    
    
      public final static String LOGIN_DATOS_OBLIGATORIOS = "Es necesario registar usuario y clave ";

    public final static String LOGIN_USUARIO_NOENCONTRADO = "Usuario no encontrado ";

    public final static String LOGIN_USUARIO_NOACTIVO = "Usuario no activo en la base de datos ";

    public final static String LOGIN_CONTRASEÑAINCORRECTA = "Constraseña incorrecta ";

    public final static String LOGIN_ERRORAUT = "Error en la autenticacion ";

    public final static String LOGIN_ERRORLDAP = "Error DA y LDAP ";

    public final static String DNI_INCORRECTO = "DNI incorrecto  ";

    public final static String LOGIN_CUENTA_BLOQUEADA = "La cuenta ha sido desactivada. Contacte con el administrador.  ";

    public final static String LOGIN_OK = "Co.Login correcto";
    
    public static Long  FEHAFIN_DEFECTO= new Long(99999999);
     public final static Long HORAFIN_DEFECTO = new Long(0);
    
     public final static String NOMBRESEXOHOMBRE = "Hombre";

    public final static String NOMBRESEXOMUJER = "Mujer";
   
    
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

          
    
       public static ArrayList<VariableEscala> dilatacionLis = new ArrayList<VariableEscala>() {
        {
            add(new VariableEscala(0, "0"));
            add(new VariableEscala(1, "1-2"));
            add(new VariableEscala(2, "3-4"));
            add(new VariableEscala(3, "4-6"));
        }

    };

    public static ArrayList<VariableEscala> borramientoLis = new ArrayList<VariableEscala>();

    static {
        borramientoLis.add(new VariableEscala(0, "0-30"));
        borramientoLis.add(new VariableEscala(1, "40-50"));
        borramientoLis.add(new VariableEscala(2, "60-70"));
        borramientoLis.add(new VariableEscala(3, "80"));
    }
    ;
	public static ArrayList<VariableEscala> consistenciaLis = new ArrayList<VariableEscala>();

    static {
        consistenciaLis.add(new VariableEscala(0, "Dura"));
        consistenciaLis.add(new VariableEscala(1, "Mediana"));
        consistenciaLis.add(new VariableEscala(2, "Blanda"));
    }
    ;

	public static ArrayList<VariableEscala> posicionLis = new ArrayList<VariableEscala>();

    static {
        posicionLis.add(new VariableEscala(0, "Posterior"));
        posicionLis.add(new VariableEscala(1, "Mediana"));
        posicionLis.add(new VariableEscala(2, "Centrado"));

    }
    ;

	@SuppressWarnings("serial")
    public static ArrayList<VariableEscala> alturaLis = new ArrayList<VariableEscala>();

    static {
        alturaLis.add(new VariableEscala(0, "SES"));
        alturaLis.add(new VariableEscala(1, "I"));
        alturaLis.add(new VariableEscala(2, "II"));
        alturaLis.add(new VariableEscala(3, "III"));

    }
    ;

	public static ArrayList<VariableEscala> karnofsky = new ArrayList<VariableEscala>() {
        {
            add(new VariableEscala(100, "Normal, sin quejas, sin indicios de enfermedad."));
            add(new VariableEscala(90, "Actividades normales, pero con signos y síntomas leves de enfermedad."));
            add(new VariableEscala(80, "Actividad normal con esfuerzo, con algunos signos y síntomas de enfermedad."));
            add(new VariableEscala(70,
                    "Capaz de cuidarse, pero incapaz de llevar a término actividades normales o trabajo activo."));
            add(new VariableEscala(60, "Requiere atención ocasional, pero puede cuidarse a sí mismo."));
            add(new VariableEscala(50,
                    "Requiere gran atención, incluso de tipo médico. Encamado menos del 50% del día."));
            add(new VariableEscala(40,
                    "Inválido, incapacitado, necesita cuidados y atenciones especiales. Encamado más del 50% del día."));
            add(new VariableEscala(30, "Inválido grave, severamente incapacitado, tratamiento de soporte activo."));
            add(new VariableEscala(20,
                    "Encamado por completo, paciente muy grave, necesita hospitalización y tratamiento activo."));
            add(new VariableEscala(10, "Moribundo"));
            add(new VariableEscala(0, "Muerto"));
        }
    };
}
