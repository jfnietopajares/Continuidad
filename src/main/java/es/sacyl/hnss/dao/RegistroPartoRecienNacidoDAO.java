package es.sacyl.hnss.dao;

import java.util.ArrayList;

import com.jnieto.entity.Proceso;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroPartoRecienNacido;

public class RegistroPartoRecienNacidoDAO extends RegistroDAO {

    public RegistroPartoRecienNacidoDAO() {
    }

    public ArrayList<RegistroPartoRecienNacido> getListaRegistros(Proceso proceso) {
        ArrayList<RegistroPartoRecienNacido> listaRN = new ArrayList<>();
        ArrayList<Registro> listaRegistros = new ArrayList<>();
        listaRegistros = new RegistroDAO().getListaRegistros(proceso,
                RegistroPartoRecienNacido.PLANTILLLA_EDITOR_PAR_RECIENNACIDO);

        for (Registro registro : listaRegistros) {
            RegistroPartoRecienNacido registroRN = (RegistroPartoRecienNacido) super.getRegistroPorId(registro.getId());
            if (registroRN != null) {
                listaRN.add(registroRN);
            }
        }
        return listaRN;
    }
}
