package es.sacyl.hnss.dao;

import java.util.ArrayList;

import com.jnieto.entity.Episodio;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroEnfAdministraFarmacos;

public class RegistroEnfAdmMediDAO extends RegistroDAO {

    public RegistroEnfAdmMediDAO() {
    }

    public ArrayList<RegistroEnfAdministraFarmacos> getListaRegistros(Episodio episodio) {
        ArrayList<RegistroEnfAdministraFarmacos> listaAdm = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        listaRegstros = new RegistroDAO().getListaRegistrosEpisodio(episodio,
                RegistroEnfAdministraFarmacos.PLANTILLLA_EDITOR_ENF_ADMIMED, EpisodioDAO.ORDENFECHAHORA);

        for (Registro registro : listaRegstros) {
            if (registro instanceof RegistroEnfAdministraFarmacos) {
                RegistroEnfAdministraFarmacos rmcompleto = new RegistroEnfAdministraFarmacos();
                rmcompleto = (RegistroEnfAdministraFarmacos) registro;
                listaAdm.add(rmcompleto);
            }
        }
        return listaAdm;
    }
}
