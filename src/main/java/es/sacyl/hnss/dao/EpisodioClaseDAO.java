package es.sacyl.hnss.dao;

import com.jnieto.entity.Episodio;
import com.jnieto.entity.EpisodioClase;

public class EpisodioClaseDAO {

    public EpisodioClaseDAO() {
    }

    public EpisodioClase getRegistroPorid(Long id) {
        EpisodioClase episodioClase = new EpisodioClase();
        if (id.equals(new Long(1))) {
            episodioClase = Episodio.CLASE_HOSPITALIZACION;
        } else if (id.equals(new Long(2))) {
            episodioClase = Episodio.CLASE_CONSULTAS;
        } else if (id.equals(new Long(3))) {
            episodioClase = Episodio.CLASE_URGENCIAS;
        } else if (id.equals(new Long(5))) {
            episodioClase = Episodio.CLASE_HDIA;
        } else if (id.equals(new Long(4))) {
            episodioClase = Episodio.CLASE_QUI_INTERVENIDO;
        } else if (id.equals(new Long(7))) {
            episodioClase = Episodio.CLASE_QUI_PROGRMACION;
        }

        return episodioClase;
    }

}
