package es.sacyl.hnss.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Campos_r;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.Peticion;
import com.jnieto.entity.Proceso;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroMama;
import com.jnieto.entity.RegistroOxiDomi;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;

/**
 * The Class RegistroOxiDomiDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018s
 */
public class RegistroOxiDomiDAO extends RegistroDAO {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger(RegistroOxiDomiDAO.class);

    /**
     * Instantiates a new registro oxi domi DAO.
     */
    public RegistroOxiDomiDAO() {
    }

    /**
     * Gets the registro id.
     *
     * @param id the id
     * @return the registro id
     */
    public RegistroOxiDomi getRegistroId(Long id) {
        Registro registro = new Registro();
        RegistroOxiDomi registroDomi = new RegistroOxiDomi();
        try {
            registro = new RegistroDAO().getRegistroPorId(id);
            if (registro instanceof RegistroOxiDomi) {
                registroDomi = (RegistroOxiDomi) registro;
            } else if (registro == null) {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_PADRE_NULL);
                return null;
            } else {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_TIPO_NOVALIDO);
                return null;
            }
            registroDomi = new RegistroOxiDomi((RegistroOxiDomi) getValoresCamposR(registroDomi));

        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                // connection.close();
            } catch (Exception e) {
                logger.error("error ", e);
            }
        }
        return registroDomi;
    }

    /**
     * Gets the valores campos R.
     *
     * @param registroDomi the registro domi
     * @return the valores campos R
     */
    public RegistroOxiDomi getValoresCamposR(RegistroOxiDomi registroDomi) {
        RegistroOxiDomi registroDomi2 = new RegistroOxiDomi(registroDomi);
        for (Campos_r campo : registroDomi.getListaCampos()) {
            Variable v = new Variable();
            if (campo.getItem().equals(registroDomi.VAR_TERAPIA.getItem())) {
                v = registroDomi.VAR_TERAPIA;
                v.setValor(campo.getDato());
                registroDomi2.setTerapia(v);
            } else if (campo.getItem().equals(registroDomi.VAR_MODALIDAD.getItem())) {
                v = registroDomi.VAR_MODALIDAD;
                v.setValor(campo.getDato());
                registroDomi2.setModalidad(v);
            } else if (campo.getItem().equals(registroDomi.VAR_TIPO_PRESCRIPCION.getItem())) {
                v = registroDomi.VAR_TIPO_PRESCRIPCION;
                v.setValor(campo.getDato());
                registroDomi2.setTipoPrescripcion(v);
            } else if (campo.getItem().equals(registroDomi.VAR_FECHA_INICIO.getItem())) {
                v = registroDomi.VAR_FECHA_INICIO;
                v.setValor(campo.getDato());
                registroDomi2.setFechaInicio(v);
            } else if (campo.getItem().equals(registroDomi.VAR_DURACION.getItem())) {
                v = registroDomi.VAR_DURACION;
                v.setValor(campo.getDato());
                registroDomi2.setDuracion(v);
            } else if (campo.getItem().equals(registroDomi.VAR_OBSERVACIONES.getItem())) {
                v = registroDomi.VAR_OBSERVACIONES;
                v.setValor(campo.getDato());
                registroDomi2.setObservaciones(v);
            } else if (campo.getItem().equals(registroDomi.VAR_FECHA_BAJA.getItem())) {
                v = registroDomi.VAR_FECHA_BAJA;
                v.setValor(campo.getDato());
                registroDomi2.setFechaBaja(v);
            } else if (campo.getItem().equals(registroDomi.VAR_MOTIVO_BAJA.getItem())) {
                v = registroDomi.VAR_MOTIVO_BAJA;
                v.setValor(campo.getDato());
                registroDomi2.setMotivoBaja(v);
            } else if (campo.getItem().equals(registroDomi.VAR_EQUIPO.getItem())) {
                v = registroDomi.VAR_EQUIPO;
                v.setValor(campo.getDato());
                registroDomi2.setEquipo(v);
            } else if (campo.getItem().equals(registroDomi.VAR_FARMACO.getItem())) {
                v = registroDomi.VAR_FARMACO;
                v.setValor(campo.getDato());
                registroDomi2.setFarmaco(v);
            } else if (campo.getItem().equals(registroDomi.VAR_PRUEBA_DIAGNOSTICA.getItem())) {
                v = registroDomi.VAR_PRUEBA_DIAGNOSTICA;
                v.setValor(campo.getDato());
                registroDomi2.setPruebaDiagnostica(v);
            } else if (campo.getItem().equals(registroDomi.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA.getItem())) {
                v = registroDomi.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA;
                v.setValor(campo.getDato());
                registroDomi2.setResultadosPrueba(v);
            } else if (campo.getItem().equals(registroDomi.VAR_O2_TIPO_FLUJO.getItem())) {
                v = registroDomi.VAR_O2_TIPO_FLUJO;
                v.setValor(campo.getDato());
                registroDomi2.setTipoFlujo(v);
            } else if (campo.getItem().equals(registroDomi.VAR_O2_FLUJO.getItem())) {
                v = registroDomi.VAR_O2_FLUJO;
                v.setValor(campo.getDato());
                registroDomi2.setFlujo(v);
            } else if (campo.getItem().equals(registroDomi.VAR_O2_HORAS_DIA.getItem())) {
                v = registroDomi.VAR_O2_HORAS_DIA;
                v.setValor(campo.getDato());
                registroDomi2.setHorasDia(v);
            } else if (campo.getItem().equals(registroDomi.VAR_O2_PRESION.getItem())) {
                v = registroDomi.VAR_O2_PRESION;
                v.setValor(campo.getDato());
                registroDomi2.setPresion(v);
            } else if (campo.getItem().equals(registroDomi.VAR_O2_SATURACION.getItem())) {
                v = registroDomi.VAR_O2_SATURACION;
                v.setValor(campo.getDato());
                registroDomi2.setSaturacion(v);
            } else if (campo.getItem().equals(registroDomi.VAR_O2_INTERFASE.getItem())) {
                v = registroDomi.VAR_O2_INTERFASE;
                v.setValor(campo.getDato());
                registroDomi2.setInterfase(v);
            }
        }
        return registroDomi2;
    }

    /**
     * Lista registro oxi domis.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @return the array list
     */
    public ArrayList<RegistroOxiDomi> listaRegistroOxiDomis(LocalDate desde, LocalDate hasta, Paciente paciente) {
        ArrayList<RegistroOxiDomi> domis = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        listaRegstros = new RegistroDAO().getListaRegistros(desde, hasta,
                RegistroOxiDomi.PLANTILLLA_EDITOR_REGISTRODOMI, paciente);
        for (Registro registro : listaRegstros) {
            if (registro instanceof RegistroMama) {
                RegistroOxiDomi rmcompleto = new RegistroOxiDomi(getValoresCamposR((RegistroOxiDomi) registro));
                domis.add(new RegistroOxiDomi(rmcompleto));
            }
        }
        return domis;
    }

    /**
     * Gets the de peticion.
     *
     * @param peticion the peticion
     * @param proceso the proceso
     * @return the de peticion
     */
    public RegistroOxiDomi getDePeticion(Peticion peticion, Proceso proceso) {
        RegistroOxiDomi oxiDomi = new RegistroOxiDomi();
        oxiDomi.setServicio(peticion.getServicio());
        oxiDomi.setCentro(peticion.getCentro());
        oxiDomi.setFecha(peticion.getFecha());
        oxiDomi.setHora(peticion.getHora());
        oxiDomi.setProblema(proceso);
        oxiDomi.setIdPeticion(peticion.getId());

        for (Variable v : peticion.getListaVariables()) {
            if (v.getItem().equals(oxiDomi.VAR_TERAPIA.getItem())) {
                oxiDomi.setTerapia(v);
            } else if (v.getItem().equals(oxiDomi.VAR_MODALIDAD.getItem())) {
                oxiDomi.setModalidad(v);
            } else if (v.getItem().equals(oxiDomi.VAR_TIPO_PRESCRIPCION.getItem())) {
                oxiDomi.setDescripcion(v.getValor());
            } else if (v.getItem().equals(oxiDomi.VAR_FECHA_INICIO.getItem())) {
                oxiDomi.setFechaInicio(v);
            } else if (v.getItem().equals(oxiDomi.VAR_DURACION.getItem())) {
                oxiDomi.setDuracion(v);
            } else if (v.getItem().equals(oxiDomi.VAR_OBSERVACIONES.getItem())) {
                oxiDomi.setObservaciones(v);
            } else if (v.getItem().equals(oxiDomi.VAR_FECHA_BAJA.getItem())) {
                oxiDomi.setFechaBaja(v);
            } else if (v.getItem().equals(oxiDomi.VAR_MOTIVO_BAJA.getItem())) {
                oxiDomi.setMotivoBaja(v);
            } else if (v.getItem().equals(oxiDomi.VAR_EQUIPO.getItem())) {
                oxiDomi.setEquipo(v);
            } else if (v.getItem().equals(oxiDomi.VAR_FARMACO.getItem())) {
                oxiDomi.setFarmaco(v);
            } else if (v.getItem().equals(oxiDomi.VAR_PRUEBA_DIAGNOSTICA.getItem())) {
                oxiDomi.setPruebaDiagnostica(v);
            } else if (v.getItem().equals(oxiDomi.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA.getItem())) {
                oxiDomi.setResultadosPrueba(v);
            } else if (v.getItem().equals(oxiDomi.VAR_O2_FLUJO.getItem())) {
                oxiDomi.setFlujo(v);
            } else if (v.getItem().equals(oxiDomi.VAR_O2_HORAS_DIA.getItem())) {
                oxiDomi.setHorasDia(v);
            } else if (v.getItem().equals(oxiDomi.VAR_O2_INTERFASE.getItem())) {
                oxiDomi.setInterfase(v);
            } else if (v.getItem().equals(oxiDomi.VAR_O2_PRESION.getItem())) {
                oxiDomi.setPresion(v);
            } else if (v.getItem().equals(oxiDomi.VAR_O2_SATURACION.getItem())) {
                oxiDomi.setSaturacion(v);
            } else if (v.getItem().equals(oxiDomi.VAR_O2_TIPO_FLUJO.getItem())) {
                oxiDomi.setTipoFlujo(v);
            }
        }
        return oxiDomi;
    }

}
