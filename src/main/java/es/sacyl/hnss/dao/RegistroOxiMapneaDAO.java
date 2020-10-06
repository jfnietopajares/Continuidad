package es.sacyl.hnss.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Campos_r;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroMama;
import com.jnieto.entity.RegistroOxiMapnea;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;

/**
 * The Class RegistroOxiMapneaDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroOxiMapneaDAO {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger(RegistroOxiMapneaDAO.class);

    /**
     * Instantiates a new registro oxi mapnea DAO.
     */
    public RegistroOxiMapneaDAO() {
    }

    /**
     * Gets the registro id.
     *
     * @param id the id
     * @return the registro id
     */
    public RegistroOxiMapnea getRegistroId(Long id) {
        Registro registro = new Registro();
        RegistroOxiMapnea registroMapnea = new RegistroOxiMapnea();
        try {
            registro = new RegistroDAO().getRegistroPorId(id);
            if (registro instanceof RegistroOxiMapnea) {
                registroMapnea = (RegistroOxiMapnea) registro;
            } else if (registro == null) {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_PADRE_NULL);
                return null;
            } else {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_TIPO_NOVALIDO);
                return null;
            }
            registroMapnea = new RegistroOxiMapnea((RegistroOxiMapnea) getValoresCamposR(registroMapnea));

        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return registroMapnea;
    }

    /**
     * Gets the valores campos R.
     *
     * @param registroMapnea the registro mapnea
     * @return the valores campos R
     */
    public RegistroOxiMapnea getValoresCamposR(RegistroOxiMapnea registroMapnea) {
        RegistroOxiMapnea registroMapnea2 = new RegistroOxiMapnea(registroMapnea);
        for (Campos_r campo : registroMapnea.getListaCampos()) {
            Variable v = new Variable();
            if (campo.getItem().equals(registroMapnea.VAR_TERAPIA.getItem())) {
                v = registroMapnea.VAR_TERAPIA;
                v.setValor(campo.getDato());
                registroMapnea2.setTerapia(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_MODALIDAD.getItem())) {
                v = registroMapnea.VAR_MODALIDAD;
                v.setValor(campo.getDato());
                registroMapnea2.setModalidad(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_TIPO_PRESCRIPCION.getItem())) {
                v = registroMapnea.VAR_TIPO_PRESCRIPCION;
                v.setValor(campo.getDato());
                registroMapnea2.setTipoPrescripcion(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_FECHA_INICIO.getItem())) {
                v = registroMapnea.VAR_FECHA_INICIO;
                v.setValor(campo.getDato());
                registroMapnea2.setFechaInicio(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_DURACION.getItem())) {
                v = registroMapnea.VAR_DURACION;
                v.setValor(campo.getDato());
                registroMapnea2.setDuracion(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_OBSERVACIONES.getItem())) {
                v = registroMapnea.VAR_OBSERVACIONES;
                v.setValor(campo.getDato());
                registroMapnea2.setObservaciones(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_FECHA_BAJA.getItem())) {
                v = registroMapnea.VAR_FECHA_BAJA;
                v.setValor(campo.getDato());
                registroMapnea2.setFechaBaja(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_MOTIVO_BAJA.getItem())) {
                v = registroMapnea.VAR_MOTIVO_BAJA;
                v.setValor(campo.getDato());
                registroMapnea2.setMotivoBaja(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_EQUIPO.getItem())) {
                v = registroMapnea.VAR_EQUIPO;
                v.setValor(campo.getDato());
                registroMapnea2.setEquipo(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_FARMACO.getItem())) {
                v = registroMapnea.VAR_FARMACO;
                v.setValor(campo.getDato());
                registroMapnea2.setFarmaco(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_PRUEBA_DIAGNOSTICA.getItem())) {
                v = registroMapnea.VAR_PRUEBA_DIAGNOSTICA;
                v.setValor(campo.getDato());
                registroMapnea2.setPruebaDiagnostica(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA.getItem())) {
                v = registroMapnea.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA;
                v.setValor(campo.getDato());
                registroMapnea2.setResultadosPrueba(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_APENA_TIEMPO_RAMPA.getItem())) {
                v = registroMapnea.VAR_APENA_TIEMPO_RAMPA;
                v.setValor(campo.getDato());
                registroMapnea.setTiempoRampa(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_APENA_PRESION_RAMPA.getItem())) {
                v = registroMapnea.VAR_APENA_PRESION_RAMPA;
                v.setValor(campo.getDato());
                registroMapnea.setPresionRampa(v);
            } else if (campo.getItem().equals(registroMapnea.VAR_APENA_PRESION.getItem())) {
                v = registroMapnea.VAR_APENA_PRESION;
                v.setValor(campo.getDato());
                registroMapnea.setPresion(v);
            }
        }
        return registroMapnea2;
    }

    /**
     * Lista registro oxi mapnes.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @return the array list
     */
    public ArrayList<RegistroOxiMapnea> listaRegistroOxiMapnes(LocalDate desde, LocalDate hasta, Paciente paciente) {
        ArrayList<RegistroOxiMapnea> mapneas = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        listaRegstros = new RegistroDAO().getListaRegistros(desde, hasta,
                RegistroOxiMapnea.PLANTILLLA_EDITOR_REGISTROMAPNEA, paciente);
        for (Registro registro : listaRegstros) {
            if (registro instanceof RegistroMama) {
                RegistroOxiMapnea rmcompleto = new RegistroOxiMapnea(getValoresCamposR((RegistroOxiMapnea) registro));
                mapneas.add(new RegistroOxiMapnea(rmcompleto));
            }
        }
        return mapneas;
    }
}
