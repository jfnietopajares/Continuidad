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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

       public static LocalTime getHoraNumeroAcualLocalTime() {

        LocalTime time = LocalTime.now();

        return time;

    }
    public static Long getFechaActualYYYYmmdd(){
         DateTimeFormatter fechaYYYYmmdd = DateTimeFormatter.ofPattern("YYYYMMddHH");
         
           DateTimeFormatter horaddmm = DateTimeFormatter.ofPattern("mmss");
        String cadena = fechaYYYYmmdd.format(LocalDateTime.now());
        return Long.parseLong(cadena);
    }
        public  static Integer getHoraActualHHmm(){
         
           DateTimeFormatter horaddmm = DateTimeFormatter.ofPattern("mmss");
        String cadena = horaddmm.format(LocalDateTime.now());
        return Integer.parseInt(cadena);
    }
           public static String getFechFormatoaddmmyyyy(Long fecha, String separador) {
        String unaFecha = "";
        String cadena = Long.toString(fecha);

        unaFecha = cadena.substring(6, 8).concat(separador).concat(cadena.substring(4, 6)).concat(separador)
                .concat(cadena.substring(0, 4));

        return unaFecha;
    }
            public static String getHoraHH_MM(Integer hora) {
        String hhmmString = null;
        String hh = null;
        String mm = null;
        hhmmString = Long.toString(hora);
        if (hhmmString.length() == 4) {
            hh = hhmmString.substring(0, 2);
            mm = hhmmString.substring(2, 4);
        } else if (hhmmString.length() == 3) {
            hh = "0" + hhmmString.substring(0, 1);
            mm = hhmmString.substring(1, 3);
        } else if (hhmmString.length() == 2) {
            hh = "00";
            mm = hhmmString.substring(1, 1);
        } else if (hhmmString.length() == 1) {
            hh = "00";
            mm = "0" + hhmmString;
        }
        return hh + ":" + mm;
    }
            
             public static int getHora(String unahora) {
        int valor;
        String cadena1;
        cadena1 = unahora.substring(0, 2);
        valor = Integer.parseInt(cadena1);
        return valor;
    }

    public static Long getHoraLong(String unahora) {
        Long valor;
        String cadena1;
        cadena1 = unahora.substring(0, 2);
        valor = Long.parseLong(cadena1);
        return valor;
    }

    /**
     * Gets the minuto.
     *
     * @param unahora the unahora
     * @return the minuo
     */
    public static int getMinuo(String unahora) {
        int valor;
        String cadena1;
        cadena1 = unahora.substring(3, 5);
        valor = Integer.parseInt(cadena1);
        return valor;
    }

            
            public static LocalDate getFechaLocalDate(Long fecha) {
        LocalDate date = null;
        if (fecha != null) {
            if (fecha > 19000000) {
                String cadena = Long.toString(fecha);
                int year = Integer.parseInt(cadena.substring(0, 4));
                int month = Integer.parseInt(cadena.substring(4, 6));
                int day = Integer.parseInt(cadena.substring(6, 8));
                date = LocalDate.of(year, month, day);
            }
        }
        return date;
    }

    public static LocalDate getFechaLocalDate(String cadena) {
        LocalDate date = null;
        if (cadena != null) {
            if (cadena.length() == 8) {
                int year = Integer.parseInt(cadena.substring(0, 4));
                int month = Integer.parseInt(cadena.substring(4, 6));
                int day = Integer.parseInt(cadena.substring(6, 8));
                date = LocalDate.of(year, month, day);
                // return date;
            }
        }
        return date;
    }

    public static LocalDateTime getFechaLocalDateTime(Long fecha) {
        LocalDateTime date = null;
        if (fecha != null && fecha != 0) {

            String cadena = Long.toString(fecha);
            /* Para 14 caracteres
                20200417101344
                yyyymmddmmssdd
                para 12 caracteres
                202004151415
                yyyymmddhhss
             */
            if (cadena.length() == 14 || cadena.length() == 12) {
                int year = Integer.parseInt(cadena.substring(0, 4));
                int month = Integer.parseInt(cadena.substring(4, 6));
                int day = Integer.parseInt(cadena.substring(6, 8));
                int hour = Integer.parseInt(cadena.substring(8, 10));
                int min = Integer.parseInt(cadena.substring(10, 12));
                date = LocalDateTime.of(year, month, day, hour, min);
            } else if (cadena.length() == 8) {
                /*
                20110412
                yyyymmdd
                 */
                int year = Integer.parseInt(cadena.substring(0, 4));
                int month = Integer.parseInt(cadena.substring(4, 6));
                int day = Integer.parseInt(cadena.substring(6, 8));
                date = LocalDateTime.of(year, month, day, 0, 0);
            }

        }
        return date;
    }

    public static LocalDateTime getFechaLocalDateTime(String cadena) {
        LocalDateTime date = null;
        if (cadena != null) {

            /* Para 14 caracteres
                20200417101344
                yyyymmddmmssdd
                para 12 caracteres
                202004151415
                yyyymmddhhss
             */
            if (cadena.length() == 14 || cadena.length() == 12) {
                int year = Integer.parseInt(cadena.substring(0, 4));
                int month = Integer.parseInt(cadena.substring(4, 6));
                int day = Integer.parseInt(cadena.substring(6, 8));
                int hour = Integer.parseInt(cadena.substring(8, 10));
                int min = Integer.parseInt(cadena.substring(10, 12));
                date = LocalDateTime.of(year, month, day, hour, min);
            } else if (cadena.length() == 8) {
                /*
                20110412
                yyyymmdd
                 */
                int year = Integer.parseInt(cadena.substring(0, 4));
                int month = Integer.parseInt(cadena.substring(4, 6));
                int day = Integer.parseInt(cadena.substring(6, 8));
                date = LocalDateTime.of(year, month, day, 0, 0);
            }

        }
        return date;
    }

            
    public static Long getFechaYYYYmmdd(LocalDate fecha){
          DateTimeFormatter fechaYYYYmmdd = DateTimeFormatter.ofPattern("YYYYMMddHH");
         
        String cadena = fechaYYYYmmdd.format(fecha);
        return Long.parseLong(cadena);
        
    }
            
              /**
     * Checks if is numeric.
     *
     * @param cadena the cadena
     * @return true, if is numeric
     */
    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isNumericDecimal(String cadena) {
        try {
            cadena.replaceAll(".", ",");
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Checks if is numero.
     *
     * @param cadena the cadena
     * @return true, if is numero
     */
    public static boolean isNumero(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false;
        } else {
            int i = 0;
            for (i = 0; i < cadena.length(); i++) {
                if (!Character.isDigit(cadena.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    
     public static boolean validarEmail(String email) {

        if (email == null) {
            return false;
        }
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

   public static boolean validarNIF(String nif) {

        boolean correcto = false;

        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

        Matcher matcher = pattern.matcher(nif);

        if (matcher.matches()) {

            String letra = matcher.group(2);

            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

            int index = Integer.parseInt(matcher.group(1));

            index = index % 23;

            String reference = letras.substring(index, index + 1);

            if (reference.equalsIgnoreCase(letra)) {

                correcto = true;

            } else {

                correcto = false;

            }

        } else {

            correcto = false;

        }

        return correcto;

    }

    /**
     * Validar telefono fijo.
     *
     * @param telefono the telefono
     * @return true, if successful
     */
    public static boolean validarTelefonoFijo(String telefono) {
        if (telefono.matches("9[0-9]{1,2} [0-9]{7}/")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarTele(String telefono) {
        if (telefono.matches("(?:\\d{3}|\\(\\d{3}\\))([-\\/\\.])\\d{3}\\1\\d{4}")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validar telefono.
     *
     * @param cadena the cadena
     * @return true, if successful
     */
    public static boolean validarTelefono(String cadena) {
        if (cadena.matches("[0-9]{9}")) {
            return true;
        } else {
            return false;
        }
    }

    
        public static String getSexoNombre(int sexo) {
        String nombresexo = "";
        switch (sexo) {
            case 1:
                nombresexo = Constantes.NOMBRESEXOHOMBRE;
                break;
            case 2:
                nombresexo = Constantes.NOMBRESEXOMUJER;
                break;
        }
        return nombresexo;
    }

    /**
     * Gets the sexo valor.
     *
     * @param sexonombre the sexonombre
     * @return the sexo valor
     */
    public static Integer getSexoValor(String sexonombre) {
        Integer sexovalor = new Integer(0);
        switch (sexonombre) {
            case "Hombre":
                sexovalor = 1;
                break;
            case "Mujer":
                sexovalor = 2;
                break;
        }
        return sexovalor;
    }

    public static String getHoraHH_MM(Long hora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
