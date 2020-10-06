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
import com.jnieto.entity.RegistroOxiAerosol;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;

/**
 * The Class RegistroOxiAerosolDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroOxiAerosolDAO extends RegistroDAO {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger(RegistroOxiAerosolDAO.class);

    /**
     * Instantiates a new registro oxi aerosol DAO.
     */
    public RegistroOxiAerosolDAO() {
    }

    /**
     * Gets the registro id.
     *
     * @param id the id
     * @return the registro id
     */
    public RegistroOxiAerosol getRegistroId(Long id) {
        Registro registro = new Registro();
        RegistroOxiAerosol registroAerosol = new RegistroOxiAerosol();
        try {
            registro = new RegistroDAO().getRegistroPorId(id);
            if (registro instanceof RegistroOxiAerosol) {
                registroAerosol = (RegistroOxiAerosol) registro;
            } else if (registro == null) {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_PADRE_NULL);
                return null;
            } else {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_TIPO_NOVALIDO);
                return null;
            }
            registroAerosol = new RegistroOxiAerosol((RegistroOxiAerosol) getValoresCamposR(registroAerosol));

        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                // connection.close();
            } catch (Exception e) {
                logger.error("error ", e);
            }
        }
        return registroAerosol;
    }

    /**
     * Gets the valores campos R.
     *
     * @param registroAerosol the registro aerosol
     * @return the valores campos R
     */
    public RegistroOxiAerosol getValoresCamposR(RegistroOxiAerosol registroAerosol) {
        RegistroOxiAerosol registroAerosol2 = new RegistroOxiAerosol(registroAerosol);
        for (Campos_r campo : registroAerosol.getListaCampos()) {
            Variable v = new Variable();
            if (campo.getItem().equals(registroAerosol.VAR_TERAPIA.getItem())) {
                v = registroAerosol.VAR_TERAPIA;
                v.setValor(campo.getDato());
                registroAerosol2.setTerapia(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_MODALIDAD.getItem())) {
                v = registroAerosol.VAR_MODALIDAD;
                v.setValor(campo.getDato());
                registroAerosol2.setModalidad(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_TIPO_PRESCRIPCION.getItem())) {
                v = registroAerosol.VAR_TIPO_PRESCRIPCION;
                v.setValor(campo.getDato());
                registroAerosol2.setTipoPrescripcion(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_FECHA_INICIO.getItem())) {
                v = registroAerosol.VAR_FECHA_INICIO;
                v.setValor(campo.getDato());
                registroAerosol2.setFechaInicio(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_DURACION.getItem())) {
                v = registroAerosol.VAR_DURACION;
                v.setValor(campo.getDato());
                registroAerosol2.setDuracion(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_OBSERVACIONES.getItem())) {
                v = registroAerosol.VAR_OBSERVACIONES;
                v.setValor(campo.getDato());
                registroAerosol2.setObservaciones(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_FECHA_BAJA.getItem())) {
                v = registroAerosol.VAR_FECHA_BAJA;
                v.setValor(campo.getDato());
                registroAerosol2.setFechaBaja(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_MOTIVO_BAJA.getItem())) {
                v = registroAerosol.VAR_MOTIVO_BAJA;
                v.setValor(campo.getDato());
                registroAerosol2.setMotivoBaja(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_EQUIPO.getItem())) {
                v = registroAerosol.VAR_EQUIPO;
                v.setValor(campo.getDato());
                registroAerosol2.setEquipo(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_FARMACO.getItem())) {
                v = registroAerosol.VAR_FARMACO;
                v.setValor(campo.getDato());
                registroAerosol2.setFarmaco(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_PRUEBA_DIAGNOSTICA.getItem())) {
                v = registroAerosol.VAR_PRUEBA_DIAGNOSTICA;
                v.setValor(campo.getDato());
                registroAerosol2.setPruebaDiagnostica(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA.getItem())) {
                v = registroAerosol.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA;
                v.setValor(campo.getDato());
                registroAerosol2.setResultadosPrueba(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_SESIONES_DIA.getItem())) {
                v = registroAerosol.VAR_SESIONES_DIA;
                v.setValor(campo.getDato());
                registroAerosol2.setSesionesDia(v);
            } else if (campo.getItem().equals(registroAerosol.VAR_NUMERO_DE_DIAS.getItem())) {
                v = registroAerosol.VAR_NUMERO_DE_DIAS;
                v.setValor(campo.getDato());
                registroAerosol2.setNumeroDias(v);
            }
        }
        return registroAerosol2;
    }

    public RegistroOxiAerosol getDePeticion(Peticion peticion, Proceso proceso) {
        RegistroOxiAerosol oxiAersol = new RegistroOxiAerosol();
        oxiAersol.setServicio(peticion.getServicio());
        oxiAersol.setCentro(peticion.getCentro());
        oxiAersol.setFecha(peticion.getFecha());
        oxiAersol.setHora(peticion.getHora());
        oxiAersol.setProblema(proceso);
        oxiAersol.setIdPeticion(peticion.getId());

        for (Variable v : peticion.getListaVariables()) {
            if (v.getItem().equals(oxiAersol.VAR_TERAPIA.getItem())) {
                oxiAersol.setTerapia(v);
            } else if (v.getItem().equals(oxiAersol.VAR_MODALIDAD.getItem())) {
                oxiAersol.setModalidad(v);
            } else if (v.getItem().equals(oxiAersol.VAR_TIPO_PRESCRIPCION.getItem())) {
                oxiAersol.setDescripcion(v.getValor());
            } else if (v.getItem().equals(oxiAersol.VAR_FECHA_INICIO.getItem())) {
                oxiAersol.setFechaInicio(v);
            } else if (v.getItem().equals(oxiAersol.VAR_DURACION.getItem())) {
                oxiAersol.setDuracion(v);
            } else if (v.getItem().equals(oxiAersol.VAR_OBSERVACIONES.getItem())) {
                oxiAersol.setObservaciones(v);
            } else if (v.getItem().equals(oxiAersol.VAR_FECHA_BAJA.getItem())) {
                oxiAersol.setFechaBaja(v);
            } else if (v.getItem().equals(oxiAersol.VAR_MOTIVO_BAJA.getItem())) {
                oxiAersol.setMotivoBaja(v);
            } else if (v.getItem().equals(oxiAersol.VAR_EQUIPO.getItem())) {
                oxiAersol.setEquipo(v);
            } else if (v.getItem().equals(oxiAersol.VAR_FARMACO.getItem())) {
                oxiAersol.setFarmaco(v);
            } else if (v.getItem().equals(oxiAersol.VAR_PRUEBA_DIAGNOSTICA.getItem())) {
                oxiAersol.setPruebaDiagnostica(v);
            } else if (v.getItem().equals(oxiAersol.VAR_RESULTADOS_PRUEBA_DIAGNOSTICA.getItem())) {
                oxiAersol.setResultadosPrueba(v);
            } else if (v.getItem().equals(oxiAersol.VAR_NUMERO_DE_DIAS.getItem())) {
                oxiAersol.setNumeroDias(v);
            } else if (v.getItem().equals(oxiAersol.VAR_SESIONES_DIA.getItem())) {
                oxiAersol.setSesionesDia(v);
            }
        }
        return oxiAersol;
    }

    /**
     * Lista registro oxi aerosols.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @return the array list
     */
    public ArrayList<RegistroOxiAerosol> listaRegistroOxiAerosols(LocalDate desde, LocalDate hasta, Paciente paciente) {
        ArrayList<RegistroOxiAerosol> aerosoles = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        listaRegstros = new RegistroDAO().getListaRegistros(desde, hasta,
                RegistroOxiAerosol.PLANTILLLA_EDITOR_REGISTROAEROSOL, paciente);
        for (Registro registro : listaRegstros) {
            if (registro instanceof RegistroMama) {
                RegistroOxiAerosol rmcompleto = new RegistroOxiAerosol(
                        getValoresCamposR((RegistroOxiAerosol) registro));
                aerosoles.add(new RegistroOxiAerosol(rmcompleto));
            }
        }
        return aerosoles;
    }

}
