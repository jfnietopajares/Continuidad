package es.sacyl.hnss.dao;

import java.util.ArrayList;

import com.jnieto.entity.Proceso;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroPartoConstantes;

public class RegistroPartoConstantesDAO extends RegistroDAO {

    public RegistroPartoConstantesDAO() {
        super();
    }

    public ArrayList<RegistroPartoConstantes> getListaRegistros(Proceso proceso) {
        ArrayList<RegistroPartoConstantes> listaDolor = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        RegistroPartoConstantes registroCtes;
        listaRegstros = new RegistroDAO().getListaRegistros(proceso,
                RegistroPartoConstantes.PLANTILLLA_EDITOR_PAR_CONSTANTES);

        for (Registro registro : listaRegstros) {
            registroCtes = (RegistroPartoConstantes) super.getRegistroPorId(registro.getId());
            if (registroCtes != null) {
                listaDolor.add(registroCtes);
            }
        }
        return listaDolor;
    }
}
