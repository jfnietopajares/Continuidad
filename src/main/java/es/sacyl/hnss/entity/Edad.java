package es.sacyl.hnss.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * The Class Edad.
 *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Edad {

    /**
     * The fnac.
     */
    private LocalDate fnac;

    /**
     * The hoy.
     */
    LocalDate hoy;

    /**
     * Instantiates a new edad.
     *
     * @param fnac the fnac
     */
    public Edad(LocalDate fnac) {
        this.fnac = fnac;
        hoy = LocalDate.now();
    }

    /**
     * Gets the edad unidades.
     *
     * @return the edad unidades
     */
    public String getEdadUnidades() {
        String valor = "";
        int anos;
        int meses;
        if (fnac != null) {
            anos = (int) ChronoUnit.YEARS.between(fnac, hoy);
            if (anos < 2) {
                meses = (int) ChronoUnit.MONTHS.between(fnac, hoy);
                valor = Integer.toString(meses) + " meses";
            } else {
                valor = anos + " años";
            }
        }

        return valor;
    }

    /**
     * Gets the edad anos.
     *
     * @return the edad anos
     */
    public int getEdadAnos() {
        int anos = 0;
        anos = (int) ChronoUnit.YEARS.between(fnac, hoy);
        return anos;
    }

    public String getEdadAnosString() {
        int anos = 0;
        anos = (int) ChronoUnit.YEARS.between(fnac, hoy);
        return Integer.toString(anos);
    }

    /**
     * Gets the edad meses.
     *
     * @return the edad meses
     */
    public int getEdadMeses() {
        int meses = 0;
        meses = (int) ChronoUnit.MONTHS.between(fnac, hoy);
        return meses;
    }

    /**
     * Gets the edad dias.
     *
     * @return the edad dias
     */
    public int getEdadDias() {
        int dias = 0;
        dias = (int) ChronoUnit.DAYS.between(fnac, hoy);
        return dias;
    }
}
