package es.sacyl.hnss.dao;

import java.util.ArrayList;

import com.jnieto.entity.Proceso;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroPartoMedicacion;

public class RegistroPartoMedicacionDAO extends RegistroDAO {

    public RegistroPartoMedicacionDAO() {
        super();
    }

    public ArrayList<RegistroPartoMedicacion> getListaRegistros(Proceso proceso) {
        ArrayList<RegistroPartoMedicacion> listaMedicacion = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        RegistroPartoMedicacion registroMedicacion;
        listaRegstros = new RegistroDAO().getListaRegistros(proceso,
                RegistroPartoMedicacion.PLANTILLLA_EDITOR_PAR_MEDICAMENTO);
        for (Registro registro : listaRegstros) {
            registroMedicacion = (RegistroPartoMedicacion) super.getRegistroPorId(registro.getId());
            if (registroMedicacion != null) {
                listaMedicacion.add(registroMedicacion);
            }
        }
        return listaMedicacion;
    }

}
