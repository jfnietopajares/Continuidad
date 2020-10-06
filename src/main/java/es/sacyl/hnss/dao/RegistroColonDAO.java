package es.sacyl.hnss.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jnieto.entity.Campos_r;
import com.jnieto.entity.Paciente;
import com.jnieto.entity.Registro;
import com.jnieto.entity.RegistroColon;
import com.jnieto.entity.Variable;
import com.jnieto.ui.NotificacionInfo;

/**
 * The Class RegistroColonDAO.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class RegistroColonDAO extends RegistroDAO {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger(RegistroColonDAO.class);

    /**
     * Instantiates a new registro colon DAO.
     */
    public RegistroColonDAO() {
    }

    /**
     * Gets the registro id.
     *
     * @param id the id
     * @return the registro id
     */
    public RegistroColon getRegistroId(Long id) {

        RegistroColon registrocolon = new RegistroColon();

        try {
            Registro registro = new RegistroDAO().getRegistroPorId(id);
            if (registro instanceof RegistroColon) {
                registrocolon = (RegistroColon) registro;
            } else if (registro == null) {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_PADRE_NULL);
                return null;
            } else {
                new NotificacionInfo(NotificacionInfo.BBDD_REGISTRO_TIPO_NOVALIDO);
                return null;
            }
            registrocolon = new RegistroColon((RegistroColon) getValoresCamposR(registrocolon));
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return registrocolon;

    }

    /**
     * Gets the valores campos R.
     *
     * @param registrocolon the registrocolon
     * @return the valores campos R
     */
    public RegistroColon getValoresCamposR(RegistroColon registrocolon) {
        RegistroColon registrocolon2 = new RegistroColon(registrocolon);
        try {
            for (Campos_r campo : registrocolon.getListaCampos()) {
                Variable v = new Variable();
                if (campo.getItem().equals(registrocolon.VAR_COLON_FECHAANALITICA.getItem())) {
                    v = registrocolon.VAR_COLON_FECHAANALITICA;
                    v.setValor(campo.getDato());
                    registrocolon2.setAnalitica(v);
                } else if (campo.getItem().equals(registrocolon.VAR_COLON_RESULTADO.getItem())) {
                    v = registrocolon.VAR_COLON_RESULTADO;
                    v.setValor(campo.getDato());
                    registrocolon2.setResultado(v);
                } else if (campo.getItem().equals(registrocolon.VAR_COLON_FECHACOLONOCITA.getItem())) {
                    v = registrocolon.VAR_COLON_FECHACOLONOCITA;
                    v.setValor(campo.getDato());
                    registrocolon2.setCitaColono(v);
                } else if (campo.getItem().equals(registrocolon.VAR_COLON_FECHACOLONOINFORME.getItem())) {
                    v = registrocolon.VAR_COLON_FECHACOLONOINFORME;
                    v.setValor(campo.getDato());
                    registrocolon2.setInformeColono(v);
                } else if (campo.getItem().equals(registrocolon.VAR_COLON_FECHACAANTOMIA.getItem())) {
                    v = registrocolon.VAR_COLON_FECHACAANTOMIA;
                    v.setValor(campo.getDato());
                    registrocolon2.setInformeAnato(v);
                } else if (campo.getItem().equals(registrocolon.VAR_COLON_IDINFORMECOLONO.getItem())) {
                    v = registrocolon.VAR_COLON_IDINFORMECOLONO;
                    v.setValor(campo.getDato());
                    registrocolon2.setIdColono(v);
                } else if (campo.getItem().equals(registrocolon.VAR_COLON_IDINFORMEANATO.getItem())) {
                    v = registrocolon.VAR_COLON_IDINFORMEANATO;
                    v.setValor(campo.getDato());
                    registrocolon2.setIdAnato(v);
                } else if (campo.getItem().equals(registrocolon.VAR_COLON_OBSERVACIONES.getItem())) {
                    v = registrocolon.VAR_COLON_OBSERVACIONES;
                    v.setValor(campo.getDato());
                    registrocolon2.setObservaciones(v);
                }
            }
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return registrocolon2;
    }

    /**
     * Lista registro colon.
     *
     * @param desde the desde
     * @param hasta the hasta
     * @return the array list
     */
    public ArrayList<RegistroColon> listaRegistroColon(LocalDate desde, LocalDate hasta, Paciente paciente) {
        ArrayList<RegistroColon> listaColon = new ArrayList<>();
        ArrayList<Registro> listaRegistros = new ArrayList<>();
        try {
            listaRegistros = new RegistroDAO().getListaRegistros(desde, hasta,
                    RegistroColon.PLANTILLLA_EDITOR_REGISTROCOLON, paciente);
            for (Registro registro : listaRegistros) {
                if (registro instanceof RegistroColon) {
                    RegistroColon rmcompleto = new RegistroColon(getValoresCamposR((RegistroColon) registro));
                    listaColon.add(new RegistroColon(rmcompleto));
                }
            }
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return listaColon;
    }

    public ArrayList<RegistroColon> listaRegistroColon(LocalDate desde, LocalDate hasta) {
        ArrayList<RegistroColon> listaColon = new ArrayList<>();
        ArrayList<Registro> listaRegistros = new ArrayList<>();
        try {
            listaRegistros = new RegistroDAO().getListaRegistros(desde, hasta,
                    RegistroColon.PLANTILLLA_EDITOR_REGISTROCOLON);
            for (Registro registro : listaRegistros) {
                if (registro instanceof RegistroColon) {
                    RegistroColon rmcompleto = new RegistroColon(getValoresCamposR((RegistroColon) registro));
                    listaColon.add(new RegistroColon(rmcompleto));
                }
            }
        } catch (Exception e) {
            logger.error(NotificacionInfo.EXCEPTION_ERROR, e);
        }
        return listaColon;
    }
}
