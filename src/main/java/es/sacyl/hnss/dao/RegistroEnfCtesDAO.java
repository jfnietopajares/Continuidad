package es.sacyl.hnss.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Campos_r;
import com.jnieto.entity.Episodio;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroEnfConstantes;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;

public class RegistroEnfCtesDAO extends RegistroDAO {

    private static final Logger logger = LogManager.getLogger(RegistroEnfCtesDAO.class);

    public RegistroEnfCtesDAO() {
    }

    public RegistroEnfConstantes getRegistroId(Long id) {
        Registro registro = new Registro();
        RegistroEnfConstantes registroEnfConstantes = new RegistroEnfConstantes();
        try {
            registro = new RegistroDAO().getRegistroPorId(id);
            if (registro instanceof RegistroEnfConstantes) {
                registroEnfConstantes = (RegistroEnfConstantes) registro;
            } else if (registro == null) {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_PADRE_NULL);
                return null;
            } else {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_TIPO_NOVALIDO);
                return null;
            }
            registroEnfConstantes = new RegistroEnfConstantes(
                    (RegistroEnfConstantes) getValoresCamposR(registroEnfConstantes));
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        } finally {
            try {
                // connection.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return registroEnfConstantes;
    }

    public RegistroEnfConstantes getValoresCamposR(RegistroEnfConstantes registroEnfConstantes) {
        RegistroEnfConstantes registroEnfConstantes2 = new RegistroEnfConstantes(registroEnfConstantes);
        for (Campos_r campo : registroEnfConstantes.getListaCampos()) {
            Variable v = new Variable();
            if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_PESO.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_PESO;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setPeso(v);
            } else if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_TALLA.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_TALLA;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setTalla(v);
            } else if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_IMC.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_IMC;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setImc(v);
            } else if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_TAS.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_TAS;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setTas(v);
            } else if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_TAD.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_TAD;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setTad(v);
            } else if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_T.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_T;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setTemperatura(v);
            } else if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_FC.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_FC;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setFreCardiaca(v);
            } else if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_FR.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_FR;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setFreRespiratorio(v);
            } else if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_SATO2.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_SATO2;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setSatOxigeno(v);
            } else if (campo.getItem().equals(registroEnfConstantes.VAR_ENF_CTES_EVA.getItem())) {
                v = registroEnfConstantes.VAR_ENF_CTES_EVA;
                v.setValor(campo.getDato());
                registroEnfConstantes2.setEva(v);
            }
        }
        return registroEnfConstantes2;
    }

    public ArrayList<RegistroEnfConstantes> getListaRegistrosEnfConstantes(LocalDate desde, LocalDate hasta, Paciente paciente) {
        ArrayList<RegistroEnfConstantes> listaCtes = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        listaRegstros = new RegistroDAO().getListaRegistros(desde, hasta,
                RegistroEnfConstantes.PLANTILLLA_EDITOR_ENF_CTES, paciente);

        for (Registro registro : listaRegstros) {
            if (registro instanceof RegistroEnfConstantes) {
                RegistroEnfConstantes rmcompleto = new RegistroEnfConstantes(
                        getValoresCamposR((RegistroEnfConstantes) registro));
                listaCtes.add(new RegistroEnfConstantes(rmcompleto));
            }
        }
        return listaCtes;
    }

    public ArrayList<RegistroEnfConstantes> getListaRegistrosEnfConstantes(Episodio episodio, String orden) {
        ArrayList<RegistroEnfConstantes> listaCtes = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        listaRegstros = new RegistroDAO().getListaRegistrosEpisodio(episodio,
                RegistroEnfConstantes.PLANTILLLA_EDITOR_ENF_CTES, orden);

        for (Registro registro : listaRegstros) {
            if (registro instanceof RegistroEnfConstantes) {
                RegistroEnfConstantes rmcompleto = new RegistroEnfConstantes(
                        getValoresCamposR((RegistroEnfConstantes) registro));
                listaCtes.add(new RegistroEnfConstantes(rmcompleto));
            }
        }
        return listaCtes;
    }
}
