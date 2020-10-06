package es.sacyl.hnss.entity;

import es.sacyl.hnss.utilidades.Constantes;
import es.sacyl.hnss.utilidades.Utilidades;
import java.time.LocalDate;



public class RegistroPartoIngreso extends Registro {

    private Variable fechaIngreso;
    private Variable horaIngreso;
    private Variable observador;
    private Variable tipoIngreso;

    private Variable situacion;
    private Variable presentacion;
    private Variable bishop;
    private Variable bishopAltura;
    private Variable bishopBorramiento;
    private Variable bishopConsistencia;
    private Variable bishopDilatacion;
    private Variable bishopPosicion;

    private Variable partoExtrahospitalario;
    private Variable numeroExtrahospitalario;
    private Variable fetomuerto;

    public final static Long PLANTILLLA_EDITOR_PAR_INGRESO = new Long(794856079);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public final Variable VAR_PARTO_INGRESO_FECHA = new Variable("9709", "99G2", new Long(9709), "Fecha Ingreso");
    public final Variable VAR_PARTO_INGRESO_HORA = new Variable("9710", "99G2", new Long(9710), "Hora ingreso");
    public final Variable VAR_PARTO_INGRESO_OBSERVADOR = new Variable("13994601", "99G2", new Long(13994601),
            "Observador");
    public final Variable VAR_PARTO_INGRESO_TIPO_INGRESO = new Variable("13824485", "99G2", new Long(13824485),
            "Tipo ingreso");
    public final Variable VAR_PARTO_INGRESO_SITUACION = new Variable("13994602", "99G2", new Long(13994602),
            "Exploración Situación");
    public final Variable VAR_PARTO_INGRESO_PRESENTACION = new Variable("246105001", "SNM3", new Long(35003303),
            "Presentación");
    public final Variable VAR_PARTO_INGRESO_BISHOP = new Variable("249023008", "SNM3", new Long(13994603),
            "Escala Bishop ");
    public final Variable VAR_PARTO_INGRESO_BISHOPALTURA = new Variable("13995217", "99G2", new Long(13995217),
            "Bishop Altura");
    public final Variable VAR_PARTO_INGRESO_BISHOPBORRAMIENTO = new Variable("13995214", "99G2", new Long(13995214),
            "Bishop Borramiento");
    public final Variable VAR_PARTO_INGRESO_BISHOPCONSCIENCIA = new Variable("13995215", "99G2", new Long(13995215),
            "Bishop Consistencia");
    public final Variable VAR_PARTO_INGRESO_BISHOPDILATACION = new Variable("13995213", "99G2", new Long(13995213),
            "Bishop Dilatación");
    public final Variable VAR_PARTO_INGRESO_BISHOPPOSICION = new Variable("13995216", "99G2", new Long(13995216),
            "Bishop Posición");

    public final Variable VAR_PARTO_INGRESO_EXTRAHOSPITALARIO = new Variable("13995468", "99G2", new Long(13995468),
            "Parto extrahospitalario");

    public final Variable VAR_PARTO_INGRESO_EXTRAHOSPINUMERO = new Variable("13995469", "99G2", new Long(13995469),
            "Número Parto extrahospitalario");

    public final Variable VAR_PARTO_INGRESO_FETOMUERTO = new Variable("13995104", "99G2", new Long(13995104),
            "Feto muerto");

    public RegistroPartoIngreso() {
        super();
        iniciaIngreso();
    }

    public RegistroPartoIngreso(Long id) {
        super(id);
        iniciaIngreso();
    }

    public RegistroPartoIngreso(RegistroPartoIngreso r) {
        super(r);
        this.fechaIngreso = r.fechaIngreso;
        this.horaIngreso = r.horaIngreso;
        this.observador = r.observador;
        this.tipoIngreso = r.tipoIngreso;
        this.situacion = r.tipoIngreso;

        this.presentacion = r.presentacion;
        this.bishop = r.bishop;
        this.bishopAltura = r.getBishopAltura();
        this.bishopBorramiento = r.getBishopBorramiento();
        this.bishopConsistencia = r.getBishopConsistencia();
        this.bishopDilatacion = r.getBishopDilatacion();
        this.bishopPosicion = r.getBishopPosicion();

        this.partoExtrahospitalario = r.getPartoExtrahospitalario();
        this.numeroExtrahospitalario = r.getNumeroExtrahospitalario();
        this.fetomuerto = r.getFetomuerto();

    }

    public void iniciaIngreso() {
        this.setTiporegistro(RegistroPartoIngreso.TIPO_REGISTRO_PARTO);
        this.setDescripcion("1.-Ingreso.");
        this.setPlantilla_edior(RegistroPartoIngreso.PLANTILLLA_EDITOR_PAR_INGRESO);
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));
        this.fechaIngreso = this.VAR_PARTO_INGRESO_FECHA;
        this.horaIngreso = this.VAR_PARTO_INGRESO_HORA;
        this.observador = this.VAR_PARTO_INGRESO_OBSERVADOR;
        this.tipoIngreso = this.VAR_PARTO_INGRESO_TIPO_INGRESO;

        this.situacion = this.VAR_PARTO_INGRESO_SITUACION;
        this.presentacion = this.VAR_PARTO_INGRESO_PRESENTACION;
        this.bishop = this.VAR_PARTO_INGRESO_BISHOP;
        this.bishopAltura = this.VAR_PARTO_INGRESO_BISHOPALTURA;
        this.bishopBorramiento = this.VAR_PARTO_INGRESO_BISHOPBORRAMIENTO;
        this.bishopConsistencia = this.VAR_PARTO_INGRESO_BISHOPCONSCIENCIA;
        this.bishopDilatacion = this.VAR_PARTO_INGRESO_BISHOPDILATACION;
        this.bishopPosicion = this.VAR_PARTO_INGRESO_BISHOPPOSICION;

        this.partoExtrahospitalario = this.VAR_PARTO_INGRESO_EXTRAHOSPITALARIO;
        this.numeroExtrahospitalario = this.VAR_PARTO_INGRESO_EXTRAHOSPINUMERO;
        this.fetomuerto = this.VAR_PARTO_INGRESO_FETOMUERTO;

    }

    public Variable getFechaIngreso() {
        return fechaIngreso;
    }

    public Variable getVariableFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaIngresoDate() {
        LocalDate fecha = null;
        if (this.fechaIngreso != null) {
            if (this.fechaIngreso.getValor() != null && !this.fechaIngreso.getValor().isEmpty()) {
                if (Utilidades.isNumero(this.fechaIngreso.getValor())) {
                    fecha = Utilidades.getFechaLocalDate(Long.parseLong(this.fechaIngreso.getValor()));
                }
            }
        }
        return fecha;
    }

    public void setFechaIngreso(Variable fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaIngreso(String valor) {
        this.fechaIngreso.setValor(valor);
    }

    public void setFechaIngreso(LocalDate fecha) {
        this.fechaIngreso.setValor(Long.toString(Utilidades.getFechaYYYYmmdd(fecha)));
    }

    public Variable getHoraIngreso() {
        return horaIngreso;
    }

    public Variable getVariableHoraIngreso() {
        return horaIngreso;
    }

    public String getHoraIngresoString() {
        return horaIngreso.getValor();
    }

    public void setHoraIngreso(Variable horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public void setHoraIngreso(String valor) {
        this.horaIngreso.setValor(valor);
    }

    public Variable getObservador() {
        return observador;
    }

    public Variable getVariableObservador() {
        return observador;
    }

    public String getObservadorString() {
        return observador.getValor();
    }

    public void setObservador(Variable observador) {
        this.observador = observador;
    }

    public void setObservador(String valor) {
        this.observador.setValor(valor);
        ;
    }

    public Variable getTipoIngreso() {
        return tipoIngreso;
    }

    public Variable getVariableTipoIngreso() {
        return tipoIngreso;
    }

    public String getTipoIngresoString() {
        return tipoIngreso.getValor();
    }

    public void setTipoIngreso(Variable tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public void setTipoIngreso(String valor) {
        this.tipoIngreso.setValor(valor);
    }

    public Variable getSituacion() {
        return situacion;
    }

    public Variable getVariableSituacion() {
        return situacion;
    }

    public String getSituacionString() {
        return situacion.getValor();
    }

    public void setSituacion(Variable situacion) {
        this.situacion = situacion;
    }

    public void setSituacion(String valor) {
        this.situacion.setValor(valor);
        ;
    }

    public Variable getPresentacion() {
        return presentacion;
    }

    public Variable getVariablePresentacion() {
        return presentacion;
    }

    public String getPresentacionString() {
        return presentacion.getValor();
    }

    public void setPresentacion(Variable presentacion) {
        this.presentacion = presentacion;
    }

    public void setPresentacion(String valor) {
        this.presentacion.setValor(valor);
    }

    public Variable getBishop() {
        return bishop;
    }

    public Variable getVariable() {
        return bishop;
    }

    public String getBishopString() {
        return bishop.getValor();
    }

    public void setBishop(Variable bishop) {
        this.bishop = bishop;
    }

    public void setBishop(String valor) {
        this.bishop.setValor(valor);
    }

    public Variable getBishopAltura() {
        return bishopAltura;
    }

    public Variable getVariableBishopAltura() {
        return bishopAltura;
    }

    public String getBishopAlturaString() {
        return bishopAltura.getValor();
    }

    public VariableEscala getBishopAlturaPartoTipoBishop() {
        VariableEscala pt = null;
        if (bishopAltura.getValor().trim().equals("SES")) {
            pt = Constantes.alturaLis.get(0);
        } else if (bishopAltura.getValor().trim().equals("I")) {
            pt = Constantes.alturaLis.get(1);
        } else if (bishopAltura.getValor().trim().equals("II")) {
            pt = Constantes.alturaLis.get(2);
        } else if (bishopAltura.getValor().trim().equals("III")) {
            pt = Constantes.alturaLis.get(3);
        }
        return pt;
    }

    public void setBishopAltura(Variable bishopAltura) {
        this.bishopAltura = bishopAltura;
    }

    public void setBishopAltura(String valor) {
        this.bishopAltura.setValor(valor);
    }

    public void setBishopAltura(VariableEscala pt) {
        if (pt != null) {
            this.bishopAltura.setValor(pt.getTexto());
        }
    }

    public Variable getBishopBorramiento() {
        return bishopBorramiento;
    }

    public VariableEscala getBishopBorramientoPartoTipoBishop() {
        VariableEscala pt = null;
        if (bishopBorramiento.getValor().trim().equals("0-30")) {
            pt = Constantes.borramientoLis.get(0);
        } else if (bishopBorramiento.getValor().trim().equals("40-50")) {
            pt = Constantes.borramientoLis.get(1);
        } else if (bishopBorramiento.getValor().trim().equals("60-70")) {
            pt = Constantes.borramientoLis.get(2);
        } else if (bishopBorramiento.getValor().trim().equals("80")) {
            pt = Constantes.borramientoLis.get(3);
        }
        return pt;
    }

    public String getBishopBorramientoString() {
        return bishopBorramiento.getValor();
    }

    public Variable getVariableBishopBorramiento() {
        return bishopBorramiento;
    }

    public void setBishopBorramiento(Variable bishopBorramiento) {
        this.bishopBorramiento = bishopBorramiento;
    }

    public void setBishopBorramiento(String valor) {
        this.bishopBorramiento.setValor(valor);
    }

    public void setBishopBorramiento(VariableEscala pt) {
        if (pt != null) {
            this.bishopBorramiento.setValor(pt.getTexto());
        }
    }

    public Variable getBishopConsistencia() {
        return bishopConsistencia;
    }

    public Variable getVariableBishopConsistencia() {
        return bishopConsistencia;
    }

    public String getBishopConsistenciaString() {
        return bishopConsistencia.getValor();
    }

    public VariableEscala getBishopConsistenciaBishop() {
        VariableEscala pt = null;
        if (bishopConsistencia.getValor().trim().equals("Dura")) {
            pt = Constantes.consistenciaLis.get(0);
        } else if (bishopConsistencia.getValor().trim().equals("Mediana")) {
            pt = Constantes.consistenciaLis.get(1);
        } else if (bishopConsistencia.getValor().trim().equals("Blanda")) {
            pt = Constantes.consistenciaLis.get(2);
        }
        return pt;
    }

    public void setBishopConsistencia(Variable bishopConsistencia) {
        this.bishopConsistencia = bishopConsistencia;
    }

    public void setBishopConsistencia(String valor) {
        this.bishopConsistencia.setValor(valor);
    }

    public void setBishopConsistencia(VariableEscala pt) {
        if (pt != null) {
            this.bishopConsistencia.setValor(pt.getTexto());
        }
    }

    public Variable getBishopDilatacion() {
        return bishopDilatacion;
    }

    public Variable getVariableBishopDilatacion() {
        return bishopDilatacion;
    }

    public String getBishopDilatacionString() {
        return bishopDilatacion.getValor();
    }

    public VariableEscala getBishopDilatacionPartoTipoBishop() {
        VariableEscala pt = null;
        if (bishopDilatacion.getValor().trim().equals("0")) {
            pt = Constantes.dilatacionLis.get(0);
        } else if (bishopDilatacion.getValor().trim().equals("1-2")) {
            pt = Constantes.dilatacionLis.get(1);
        } else if (bishopDilatacion.getValor().trim().equals("3-4")) {
            pt = Constantes.dilatacionLis.get(2);
        } else if (bishopDilatacion.getValor().trim().equals("4-6")) {
            pt = Constantes.dilatacionLis.get(3);
        }
        return pt;
    }

    public void setBishopDilatacion(Variable bishopDilatacion) {
        this.bishopDilatacion = bishopDilatacion;
    }

    public void setBishopDilatacion(VariableEscala pt) {
        if (pt != null) {
            this.bishopDilatacion.setValor(pt.getTexto());
        }
    }

    public void setBishopDilatacion(String valor) {
        this.bishopDilatacion.setValor(valor);
    }

    public Variable getBishopPosicion() {
        return bishopPosicion;
    }

    public Variable getVariableBishopPosicion() {
        return bishopPosicion;
    }

    public String getBishopPosicionString() {
        return bishopPosicion.getValor();
    }

    public VariableEscala getBishopPosicionPartoTipoBishop() {
        VariableEscala pt = null;
        if (bishopPosicion.getValor().trim().equals("Posterior")) {
            pt = Constantes.posicionLis.get(0);
        }
        if (bishopPosicion.getValor().trim().equals("Mediana")) {
            pt = Constantes.posicionLis.get(1);
        }
        if (bishopPosicion.getValor().trim().equals("Centrado")) {
            pt = Constantes.posicionLis.get(1);
        }
        return pt;
    }

    public void setBishopPosicion(Variable bishopPosicion) {
        this.bishopPosicion = bishopPosicion;
    }

    public void setBishopPosicion(String valor) {
        this.bishopPosicion.setValor(valor);
    }

    public void setBishopPosicion(VariableEscala pt) {
        if (pt != null) {
            this.bishopPosicion.setValor(pt.getTexto());
        }
    }

    public Variable getPartoExtrahospitalario() {
        return partoExtrahospitalario;
    }

    public Variable getVariablePartoExtrahospitalario() {
        return partoExtrahospitalario;
    }

    public Boolean getPartoExtrahospitalarioBoolean() {
        if (partoExtrahospitalario != null && !partoExtrahospitalario.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPartoExtrahospitalario(Variable partoExtrahospitalario) {
        this.partoExtrahospitalario = partoExtrahospitalario;
    }

    public void setPartoExtrahospitalario(Boolean valor) {
        if (valor == true) {
            this.partoExtrahospitalario.setValor("Parto extrahospitalario");
        } else {
            this.partoExtrahospitalario.setValor("");
        }
    }

    public Variable getNumeroExtrahospitalario() {
        return numeroExtrahospitalario;
    }

    public Variable getVariableNumeroExtrahospitalario() {
        return numeroExtrahospitalario;
    }

    public String getNumeroExtrahospitalariosString() {
        return numeroExtrahospitalario.getValor();
    }

    public void setNumeroExtrahospitalario(Variable numeroExtrahospitalario) {
        this.numeroExtrahospitalario = numeroExtrahospitalario;
    }

    public void setNumeroExtrahospitalario(String valor) {
        this.numeroExtrahospitalario.setValor(valor);
    }

    public Variable getFetomuerto() {
        return fetomuerto;
    }

    public Variable getVariableFetomuerto() {
        return fetomuerto;
    }

    public Boolean getFetomuertoBoolean() {
        if (fetomuerto != null && fetomuerto.getValor() != null && !fetomuerto.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFetomuerto(Variable fetomuerto) {
        this.fetomuerto = fetomuerto;
    }

    public void setFetomuerto(Boolean valor) {
        if (valor == true) {
            fetomuerto.setValor("Feto muerto");
        } else {
            fetomuerto.setValor("");
        }
    }

}
