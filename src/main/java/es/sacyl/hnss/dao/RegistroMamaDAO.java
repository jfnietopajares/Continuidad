package es.sacyl.hnss.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Campos_r;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroMama;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;

/**
 * The Class RegistroMamaDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroMamaDAO extends RegistroDAO {

    private static final Logger logger = LogManager.getLogger(RegistroMamaDAO.class);

    /**
     * Gets the registro camposr id.
     *
     * @param id the id
     * @return the registro camposr id
     */
    @Override

    public RegistroMama getRegistroId(Long id) {
        Registro registro = new Registro();
        RegistroMama registromama = new RegistroMama();
        try {
            registro = new RegistroDAO().getRegistroPorId(id);
            if (registro instanceof RegistroMama) {
                registromama = (RegistroMama) registro;
            } else if (registro == null) {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_PADRE_NULL);
                return null;
            } else {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_TIPO_NOVALIDO);
                return null;
            }
            registromama = new RegistroMama((RegistroMama) getValoresCamposR(registromama));
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                // connection.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return registromama;
    }

    public RegistroMama getValoresCamposR(RegistroMama registromama) {
        RegistroMama registroMama2 = new RegistroMama(registromama);
        for (Campos_r campo : registromama.getListaCampos()) {
            Variable v = new Variable();

            if (campo.getItem().equals(registromama.VAR_MAMA_IDPCAN.getItem())) {
                v = registromama.VAR_MAMA_IDPCAN;
                v.setValor(campo.getDato());
                registroMama2.setIdpcan(v);
            } else if (campo.getItem().equals(registromama.VAR_MAMA_TNM.getItem())) {
                v = registromama.VAR_MAMA_TNM;
                v.setValor(campo.getDato());
                registroMama2.setTnm(v);
            } else if (campo.getItem().equals(registromama.VAR_MAMA_FECHAAP.getItem())) {
                v = registromama.VAR_MAMA_FECHAAP;
                v.setValor(campo.getDato());
                registroMama2.setFechaap(v);
            } else if (campo.getItem().equals(registromama.VAR_MAMA_BIRADS.getItem())) {
                v = registromama.VAR_MAMA_BIRADS;
                v.setValor(campo.getDato());
                registroMama2.setBirads(v);
            } else if (campo.getItem().equals(registromama.VAR_MAMA_BIRADSCORREGIDO.getItem())) {
                v = registromama.VAR_MAMA_BIRADSCORREGIDO;
                v.setValor(campo.getDato());
                registroMama2.setBiradscorregido(v);
            } else if (campo.getItem().equals(registromama.VAR_MAMA_FECHAGINE.getItem())) {
                v = registromama.VAR_MAMA_FECHAGINE;
                v.setValor(campo.getDato());
                registroMama2.setFechagine(v);
            } else if (campo.getItem().equals(registromama.VAR_MAMA_FECHACOMITE.getItem())) {
                v = registromama.VAR_MAMA_FECHACOMITE;
                v.setValor(campo.getDato());
                registroMama2.setFechacomite(v);
            } else if (campo.getItem().equals(registromama.VAR_MAMA_FECHAPRUEBA.getItem())) {
                v = registromama.VAR_MAMA_FECHAPRUEBA;
                v.setValor(campo.getDato());
                registroMama2.setFechaprueba(v);
            } else if (campo.getItem().equals(registromama.VAR_MAMA_FECHATRATAMIENTO.getItem())) {
                v = registromama.VAR_MAMA_FECHATRATAMIENTO;
                v.setValor(campo.getDato());
                registroMama2.setFechatratamiento(v);
            } else if (campo.getItem().equals(registromama.VAR_MAMA_OBSERVACIONES.getItem())) {
                v = registromama.VAR_MAMA_OBSERVACIONES;
                v.setValor(campo.getDato());
                registroMama2.setObservaciones(v);
            }
        }
        return registroMama2;
    }

    public ArrayList<RegistroMama> getListaRegistros(LocalDate desde, LocalDate hasta, Paciente paciente) {
        ArrayList<RegistroMama> listaMamas = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        listaRegstros = new RegistroDAO().getListaRegistros(desde, hasta, RegistroMama.PLANTILLLA_EDITOR_REGISTROMAMA,
                paciente);
        for (Registro registro : listaRegstros) {
            if (registro instanceof RegistroMama) {
                RegistroMama rmcompleto = new RegistroMama(getValoresCamposR((RegistroMama) registro));
                listaMamas.add(new RegistroMama(rmcompleto));
            }
        }
        return listaMamas;
    }

    public ArrayList<RegistroMama> getListaRegistros(LocalDate desde, LocalDate hasta) {
        ArrayList<RegistroMama> listaMamas = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        listaRegstros = new RegistroDAO().getListaRegistros(desde, hasta, RegistroMama.PLANTILLLA_EDITOR_REGISTROMAMA);
        for (Registro registro : listaRegstros) {
            if (registro instanceof RegistroMama) {
                RegistroMama rmcompleto = new RegistroMama(getValoresCamposR((RegistroMama) registro));
                listaMamas.add(new RegistroMama(rmcompleto));
            }
        }
        return listaMamas;
    }
}
