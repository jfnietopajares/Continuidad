package es.sacyl.hnss.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Campos_r;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroO2Incidencia;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;

/**
 * The Class RegistroO2IncidenciaDao.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroO2IncidenciaDao {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger(RegistroO2IncidenciaDao.class);

    /**
     * Instantiates a new registro O 2 incidencia dao.
     */
    public RegistroO2IncidenciaDao() {
    }

    /**
     * Gets the registro id.
     *
     * @param id the id
     * @return the registro id
     */
    public RegistroO2Incidencia getRegistroId(Long id) {
        Registro registro = new Registro();
        RegistroO2Incidencia registroIncidencia = new RegistroO2Incidencia();
        try {
            registro = new RegistroDAO().getRegistroPorId(id);
            if (registro instanceof RegistroO2Incidencia) {
                registroIncidencia = (RegistroO2Incidencia) registro;
            } else if (registro == null) {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_PADRE_NULL);
                return null;
            } else {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_TIPO_NOVALIDO);
                return null;
            }
            registroIncidencia = new RegistroO2Incidencia((RegistroO2Incidencia) getValoresCamposR(registroIncidencia));
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                // connection.close();
            } catch (Exception e) {
                logger.error("error ", e);
            }
        }
        return registroIncidencia;
    }

    /**
     * Gets the valores campos R.
     *
     * @param registroIncidencia the registro incidencia
     * @return the valores campos R
     */
    public RegistroO2Incidencia getValoresCamposR(RegistroO2Incidencia registroIncidencia) {
        RegistroO2Incidencia registroIncidencia2 = new RegistroO2Incidencia(registroIncidencia);
        for (Campos_r campo : registroIncidencia.getListaCampos()) {
            Variable v = new Variable();
            if (campo.getItem().equals(registroIncidencia.VAR_INCI_FECHA_INICIO.getItem())) {
                v = registroIncidencia.VAR_INCI_FECHA_INICIO;
                v.setValor(campo.getDato());
                registroIncidencia2.setFechaInicio(v);
            } else if (campo.getItem().equals(registroIncidencia.VAR_INCI_FECHA_FIN.getItem())) {
                v = registroIncidencia.VAR_INCI_FECHA_FIN;
                v.setValor(campo.getDato());
                registroIncidencia2.setFechaFin(v);
            } else if (campo.getItem().equals(registroIncidencia.VAR_INCI_TIPO.getItem())) {
                v = registroIncidencia.VAR_INCI_TIPO;
                v.setValor(campo.getDato());
                registroIncidencia2.setTipo(v);
            } else if (campo.getItem().equals(registroIncidencia.VAR_INCI_OBSERVACIONES.getItem())) {
                v = registroIncidencia.VAR_INCI_OBSERVACIONES;
                v.setValor(campo.getDato());
                registroIncidencia2.setObservaciones(v);
            }
        }
        return registroIncidencia2;
    }

}
