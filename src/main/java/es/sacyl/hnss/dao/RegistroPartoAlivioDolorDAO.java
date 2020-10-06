package es.sacyl.hnss.dao;

import java.util.ArrayList;

import com.jnieto.entity.Proceso;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroPartoAlivioDolor;

public class RegistroPartoAlivioDolorDAO extends RegistroDAO {

    public RegistroPartoAlivioDolorDAO() {
    }

    public ArrayList<RegistroPartoAlivioDolor> getListaRegistros(Proceso proceso) {
        ArrayList<RegistroPartoAlivioDolor> listaDolor = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        RegistroPartoAlivioDolor registroDolor;
        listaRegstros = new RegistroDAO().getListaRegistros(proceso,
                RegistroPartoAlivioDolor.PLANTILLLA_EDITOR_PAR_ALIVIODOLOR);

        for (Registro registro : listaRegstros) {
            registroDolor = (RegistroPartoAlivioDolor) super.getRegistroPorId(registro.getId());
            if (registroDolor != null) {
                listaDolor.add(registroDolor);
            }
        }
        return listaDolor;
    }
}
