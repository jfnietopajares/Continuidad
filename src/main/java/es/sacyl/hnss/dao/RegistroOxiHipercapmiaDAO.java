package es.sacyl.hnss.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Campos_r;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroOxiHipercapnia;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;

/**
 * The Class RegistroOxiHipercapmiaDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroOxiHipercapmiaDAO {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger(RegistroOxiHipercapmiaDAO.class);

    /**
     * Instantiates a new registro oxi hipercapmia DAO.
     */
    public RegistroOxiHipercapmiaDAO() {

    }

    /**
     * Gets the registro id.
     *
     * @param id the id
     * @return the registro id
     */
    public RegistroOxiHipercapnia getRegistroId(Long id) {
        Registro registro = new Registro();
        RegistroOxiHipercapnia registroEapoyo = new RegistroOxiHipercapnia();
        try {
            registro = new RegistroDAO().getRegistroPorId(id);
            if (registro instanceof RegistroOxiHipercapnia) {
                registroEapoyo = (RegistroOxiHipercapnia) registro;
            } else if (registro == null) {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_PADRE_NULL);
                return null;
            } else {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_TIPO_NOVALIDO);
                return null;
            }
            registroEapoyo = new RegistroOxiHipercapnia((RegistroOxiHipercapnia) getValoresCamposR(registroEapoyo));

        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                // connection.close();
            } catch (Exception e) {
                logger.error("error ", e);
            }
        }
        return registroEapoyo;
    }

    /**
     * Gets the valores campos R.
     *
     * @param registroEpoyo the registro epoyo
     * @return the valores campos R
     */
    public RegistroOxiHipercapnia getValoresCamposR(RegistroOxiHipercapnia registroEpoyo) {
        RegistroOxiHipercapnia registroEpoyo2 = new RegistroOxiHipercapnia(registroEpoyo);
        for (Campos_r campo : registroEpoyo.getListaCampos()) {
            Variable v = new Variable();
            if (campo.getItem().equals(registroEpoyo.VAR_TERAPIA.getItem())) {
                v = registroEpoyo.VAR_TERAPIA;
                v.setValor(campo.getDato());
                registroEpoyo2.setTerapia(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_MODALIDAD.getItem())) {
                v = registroEpoyo.VAR_MODALIDAD;
                v.setValor(campo.getDato());
                registroEpoyo2.setModalidad(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_TIPO_PRESCRIPCION.getItem())) {
                v = registroEpoyo.VAR_TIPO_PRESCRIPCION;
                v.setValor(campo.getDato());
                registroEpoyo2.setTipoPrescripcion(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_FECHA_INICIO.getItem())) {
                v = registroEpoyo.VAR_FECHA_INICIO;
                v.setValor(campo.getDato());
                registroEpoyo2.setFechaInicio(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_DURACION.getItem())) {
                v = registroEpoyo.VAR_DURACION;
                v.setValor(campo.getDato());
                registroEpoyo2.setDuracion(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_OBSERVACIONES.getItem())) {
                v = registroEpoyo.VAR_OBSERVACIONES;
                v.setValor(campo.getDato());
                registroEpoyo2.setObservaciones(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_FECHA_BAJA.getItem())) {
                v = registroEpoyo.VAR_FECHA_BAJA;
                v.setValor(campo.getDato());
                registroEpoyo2.setFechaBaja(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_MOTIVO_BAJA.getItem())) {
                v = registroEpoyo.VAR_MOTIVO_BAJA;
                v.setValor(campo.getDato());
                registroEpoyo2.setMotivoBaja(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_EQUIPO.getItem())) {
                v = registroEpoyo.VAR_EQUIPO;
                v.setValor(campo.getDato());
                registroEpoyo2.setEquipo(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_FARMACO.getItem())) {
                v = registroEpoyo.VAR_FARMACO;
                v.setValor(campo.getDato());
                registroEpoyo2.setFarmaco(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_PRUEBA_DIAGNOSTICA.getItem())) {
                v = registroEpoyo.VAR_PRUEBA_DIAGNOSTICA;
                v.setValor(campo.getDato());
                registroEpoyo2.setPruebaDiagnostica(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA.getItem())) {
                v = registroEpoyo.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA;
                v.setValor(campo.getDato());
                registroEpoyo2.setResultadosPrueba(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_HIPER_PRESION_INSPIRA.getItem())) {
                v = registroEpoyo.VAR_HIPER_PRESION_INSPIRA;
                v.setValor(campo.getDato());
                registroEpoyo2.setPresiespiracion(v);
            } else if (campo.getItem().equals(registroEpoyo.VAR_HIPER_PRESION_ESPIRACION.getItem())) {
                v = registroEpoyo.VAR_HIPER_PRESION_ESPIRACION;
                v.setValor(campo.getDato());
                registroEpoyo2.setPresiespiracion(v);
            }
        }
        return registroEpoyo2;
    }
}
