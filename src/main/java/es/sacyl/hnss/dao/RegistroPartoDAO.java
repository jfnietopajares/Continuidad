package es.sacyl.hnss.dao;

import java.util.ArrayList;

import com.jnieto.entity.Proceso;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroPartoAlivioDolor;
import com.jnieto.entity.RegistroPartoEvolucion;
import com.jnieto.entity.RegistroPartoGestacionPatFet;
import com.jnieto.entity.RegistroPartoMedicacion;
import com.jnieto.entity.RegistroPartoRecienNacido;

public class RegistroPartoDAO extends RegistroDAO {

    public RegistroPartoDAO() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<RegistroPartoGestacionPatFet> getListaRegistrosGestaPatpFet(Proceso proceso) {
        ArrayList<RegistroPartoGestacionPatFet> lista = new ArrayList<>();
        ArrayList<Registro> listaRegistros = new ArrayList<>();
        listaRegistros = new RegistroDAO().getListaRegistros(proceso,
                RegistroPartoGestacionPatFet.PLANTILLLA_EDITOR_PAR_GESTACIONPATFET);

        for (Registro registro : listaRegistros) {
            RegistroPartoGestacionPatFet registroPF = (RegistroPartoGestacionPatFet) super.getRegistroPorId(
                    registro.getId());
            if (registroPF != null) {
                lista.add(registroPF);
            }
        }
        return lista;
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

    public ArrayList<RegistroPartoEvolucion> getListaRegistrosCtes(Proceso proceso) {
        ArrayList<RegistroPartoEvolucion> listaDolor = new ArrayList<>();
        ArrayList<Registro> listaRegstros = new ArrayList<>();
        RegistroPartoEvolucion registroCtes;
        listaRegstros = new RegistroDAO().getListaRegistros(proceso,
                RegistroPartoEvolucion.PLANTILLLA_EDITOR_PAR_EVOLUCION);

        for (Registro registro : listaRegstros) {
            registroCtes = (RegistroPartoEvolucion) super.getRegistroPorId(registro.getId());
            if (registroCtes != null) {
                listaDolor.add(registroCtes);
            }
        }
        return listaDolor;
    }

    public ArrayList<RegistroPartoMedicacion> getListaRegistrosMedica(Proceso proceso) {
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

    public ArrayList<RegistroPartoRecienNacido> getListaRegistrosRecienNacido(Proceso proceso) {
        ArrayList<RegistroPartoRecienNacido> listaRN = new ArrayList<>();
        ArrayList<Registro> listaRegistros = new ArrayList<>();

        listaRegistros = new RegistroDAO().getListaRegistros(proceso,
                RegistroPartoRecienNacido.PLANTILLLA_EDITOR_PAR_RECIENNACIDO);

        for (Registro registro : listaRegistros) {
            RegistroPartoRecienNacido registroRN = new RegistroPartoRecienNacido();
            registroRN = (RegistroPartoRecienNacido) super.getRegistroPorId(registro.getId());

            if (registroRN != null) {
                listaRN.add(registroRN);
            }
        }
        return listaRN;
    }

}
