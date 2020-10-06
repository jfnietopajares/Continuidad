package es.sacyl.hnss.entity;

public class RegistroPartoInduccion extends Registro {

    private Variable induccionSiNo;
    private Variable fechaHoraBolsaRota;
    private Variable patologiaMaterna;
    private Variable patologiaFetalCir;
    private Variable patologiaFetal;
    private Variable roturaBolasmas12;
    private Variable alteracionLiqAmni;
    private Variable liquidoMeconal;
    private Variable gestacionFetalProlongada;
    private Variable otrasIndicacionesInduccion;
    private Variable maduracioncervical;
    private Variable horas;
    private Variable preparacionOxiticica;
    private Variable evolucion;

    public final Variable VAR_PARTO_INDU_SINO = new Variable("73.4", "I9C", new Long(102836), "Patología Materna");

    public final Variable VAR_PARTO_INDU_PATOMAT = new Variable("13994604", "99G2", new Long(13994604),
            "Patología Materna");

    public final Variable VAR_PARTO_INDU_PATOFETCIR = new Variable("13994605", "99G2", new Long(13994605),
            "Patología Fetal ");

    public final Variable VAR_PARTO_INDU_PATOFETPEG = new Variable("13994606", "99G2", new Long(13994606),
            "Patología Fetal PEG");

    public final Variable VAR_PARTO_INDU_ROTURAMAS12 = new Variable("13994607", "99G2", new Long(13994607),
            "Rotura mas 12 horas");

    public final Variable VAR_PARTO_INDU_ALTERACIONLA = new Variable("13994609", "99G2", new Long(13994609),
            "Alteración líquido anmiótico");

    public final Variable VAR_PARTO_INDU_LIQUIMECONIAL = new Variable("O77.0", "I10C", new Long(60025551),
            "Alteración líquido anmiótico");

    public final Variable VAR_PARTO_INDU_GESPROLONGADA = new Variable("276616001", "SNM3", new Long(13994608),
            "Gestación fetal prolongada ");
    public final Variable VAR_PARTO_INDU_OTRASINDIC = new Variable("13994610", "99G2", new Long(13994610),
            "Otras indicaciones inducción ");

    public final Variable VAR_PARTO_INDU_MADURACIONCERVICAL = new Variable("13994611", "99G2", new Long(13994611),
            "Maduración cervical ");
    public final Variable VAR_PARTO_INDU_MADURACIONHORAS = new Variable("13994612", "99G2", new Long(13994612),
            "Horas  ");
    public final Variable VAR_PARTO_INDU_PREPARAOXI = new Variable("424999005", "SNM3", new Long(13994613),
            "Preparación oxitícica  ");
    public final Variable VAR_PARTO_INDU_EVOLUCION = new Variable("8599", "99G2", new Long(8599),
            "Preparación oxitícica  ");

    public final Variable VAR_PARTO_INDU_FECHA_HORA_ROTA = new Variable("N420101.ROTMEM", "99G2", new Long(104949),
            "Rotura Membranas");

    public final static Long PLANTILLLA_EDITOR_PAR_INDUCCION = new Long(794856563);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public RegistroPartoInduccion() {
        iniciaInduccion();
    }

    public RegistroPartoInduccion(Long id) {
        super(id);
        iniciaInduccion();
    }

    public RegistroPartoInduccion(RegistroPartoInduccion r) {
        super(r);
        this.induccionSiNo = r.getInduccionSiNo();
        this.fechaHoraBolsaRota = r.getFechaHoraBolsaRota();
        this.patologiaMaterna = r.getPatologiaMaterna();
        this.patologiaFetalCir = r.getPatologiaFetalCir();
        this.patologiaFetal = r.getpatologiaFetal();
        this.roturaBolasmas12 = r.getRoturaBolasmas12();
        this.alteracionLiqAmni = r.getAlteracionLiqAmni();
        this.liquidoMeconal = r.getLiquidoMeconal();
        this.gestacionFetalProlongada = r.getGestacionFetalProlongada();
        this.otrasIndicacionesInduccion = r.getOtrasIndicacionesInduccion();
        this.maduracioncervical = r.getMaduracioncervical();
        this.horas = r.getHoras();
        this.preparacionOxiticica = r.getPreparacionOxiticica();
        this.evolucion = r.getEvolucion();
    }

    public void iniciaInduccion() {
        this.plantilla_editor = PLANTILLLA_EDITOR_PAR_INDUCCION;
        this.tiporegistro = TIPO_REGISTRO_PARTO;
        this.descripcion = "3.-Inducción";
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));

        this.induccionSiNo = VAR_PARTO_INDU_SINO;
        this.fechaHoraBolsaRota = VAR_PARTO_INDU_FECHA_HORA_ROTA;
        this.patologiaMaterna = VAR_PARTO_INDU_PATOMAT;
        this.patologiaFetalCir = VAR_PARTO_INDU_PATOFETCIR;
        this.patologiaFetal = VAR_PARTO_INDU_PATOFETPEG;
        this.roturaBolasmas12 = VAR_PARTO_INDU_ROTURAMAS12;
        this.alteracionLiqAmni = VAR_PARTO_INDU_ALTERACIONLA;
        this.liquidoMeconal = VAR_PARTO_INDU_LIQUIMECONIAL;
        this.gestacionFetalProlongada = VAR_PARTO_INDU_GESPROLONGADA;
        this.otrasIndicacionesInduccion = VAR_PARTO_INDU_OTRASINDIC;
        this.maduracioncervical = VAR_PARTO_INDU_MADURACIONCERVICAL;
        this.horas = VAR_PARTO_INDU_MADURACIONHORAS;
        this.preparacionOxiticica = VAR_PARTO_INDU_PREPARAOXI;
        this.evolucion = VAR_PARTO_INDU_EVOLUCION;
    }

    public Variable getInduccionSiNo() {
        return induccionSiNo;
    }

    public Variable getVariableInduccionSiNo() {
        return induccionSiNo;
    }

    public String getInduccionSiNoString() {
        return induccionSiNo.getValor();
    }

    public void setInduccionSiNo(Variable induccionSiNo) {
        this.induccionSiNo = induccionSiNo;
    }

    public void setInduccionSiNo(String valor) {
        this.induccionSiNo.setValor(valor);
    }

    public Variable getFechaHoraBolsaRota() {
        return fechaHoraBolsaRota;
    }

    public Variable getVariableFechaHoraBolsaRota() {
        return fechaHoraBolsaRota;
    }

    public String getFechaHoraBolsaRotaString() {
        return fechaHoraBolsaRota.getValor();
    }

    public void setFechaHoraBolsaRota(Variable fechaHoraBolsaRota) {
        this.fechaHoraBolsaRota = fechaHoraBolsaRota;
    }

    public void setFechaHoraBolsaRota(String valor) {
        this.fechaHoraBolsaRota.setValor(valor);
    }

    public Variable getPatologiaMaterna() {
        return patologiaMaterna;
    }

    public Variable getVariablePatologiaMaterna() {
        return patologiaMaterna;
    }

    public String getPatologiaMaternaString() {
        return patologiaMaterna.getValor();
    }

    public Boolean getPatologiaMaternaBoolean() {
        if (patologiaMaterna != null && patologiaMaterna.getValor() != null && !patologiaMaterna.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatologiaMaterna(Variable patologiaMaterna) {
        this.patologiaMaterna = patologiaMaterna;
    }

    public void setPatologiaMaterna(String valor) {
        this.patologiaMaterna.setValor(valor);
    }

    public void setPatologiaMaterna(Boolean valor) {
        if (valor == true) {
            this.patologiaMaterna.setValor("Inducción Patología Materna");
        } else {
            this.patologiaMaterna.setValor("");
        }
    }

    public Variable getPatologiaFetalCir() {
        return patologiaFetalCir;
    }

    public Variable getVariablePatologiaFetalCir() {
        return patologiaFetalCir;
    }

    public String getPatologiaFetalCirString() {
        return patologiaFetalCir.getValor();
    }

    public void setPatologiaFetalCir(Variable patologiaFetalCir) {
        this.patologiaFetalCir = patologiaFetalCir;
    }

    public void setPatologiaFetalCir(String valor) {
        this.patologiaFetalCir.setValor(valor);
    }

    public Variable getpatologiaFetal() {
        return patologiaFetal;
    }

    public Variable getVariablepatologiaFetal() {
        return patologiaFetal;
    }

    public String getpatologiaFetalString() {
        return patologiaFetal.getValor();
    }

    public Boolean getPatologiaFetalBoolean() {
        if (patologiaFetal != null && patologiaFetal.getValor() != null && !patologiaFetal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPatologiaFetal(Variable patologiaFetal) {
        this.patologiaFetal = patologiaFetal;
    }

    public void setpatologiaFetal(Boolean valor) {
        if (valor == true) {
            this.patologiaFetal.setValor("Inducción patología fetal");
        } else {
            this.patologiaFetal.setValor("");
        }
    }

    public void setpatologiaFetal(String valor) {
        this.patologiaFetal.setValor(valor);
    }

    public Variable getRoturaBolasmas12() {
        return roturaBolasmas12;
    }

    public Variable getVariableRoturaBolasmas12() {
        return roturaBolasmas12;
    }

    public String getRoturaBolasmas12String() {
        return roturaBolasmas12.getValor();
    }

    public Boolean getRoturaBolasmas12Boolean() {
        if (roturaBolasmas12 != null && roturaBolasmas12.getValor() != null && !roturaBolasmas12.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setRoturaBolasmas12(Variable roturaBolasmas12) {
        this.roturaBolasmas12 = roturaBolasmas12;
    }

    public void setRoturaBolasmas12(String valor) {
        this.roturaBolasmas12.setValor(valor);
    }

    public void setRoturaBolasmas12(Boolean valor) {
        if (valor == true) {
            setRoturaBolasmas12("Rotura bolsa más de 12 horas");
        } else {
            setRoturaBolasmas12("");
        }
    }

    public Variable getAlteracionLiqAmni() {
        return alteracionLiqAmni;
    }

    public Variable getVariableAlteracionLiqAmni() {
        return alteracionLiqAmni;
    }

    public String getAlteracionLiqAmniString() {
        return alteracionLiqAmni.getValor();
    }

    public Boolean getAlteracionLiqAmniBoolean() {
        if (alteracionLiqAmni != null && alteracionLiqAmni.getValor() != null
                && !alteracionLiqAmni.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAlteracionLiqAmni(Variable alteracionLiqAmni) {
        this.alteracionLiqAmni = alteracionLiqAmni;
    }

    public void setAlteracionLiqAmni(String valor) {
        this.alteracionLiqAmni.setValor(valor);
    }

    public void setAlteracionLiqAmni(Boolean valor) {
        if (valor == true) {
            this.alteracionLiqAmni.setValor("Líquido anmiótico alterado ");
        } else {
            this.alteracionLiqAmni.setValor("");
        }
    }

    public Variable getLiquidoMeconal() {
        return liquidoMeconal;
    }

    public Variable getVariableLiquidoMeconal() {
        return liquidoMeconal;
    }

    public Boolean getLiquidoMeconalBoolean() {
        if (liquidoMeconal != null && liquidoMeconal.getValor() != null && !liquidoMeconal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setLiquidoMeconal(Variable liquidoMeconal) {
        this.liquidoMeconal = liquidoMeconal;
    }

    public void setLiquidoMeconal(Boolean valor) {
        if (valor == true) {
            this.liquidoMeconal.setValor("Líquido meconial");
        } else {
            this.liquidoMeconal.setValor("");
        }
    }

    public Variable getGestacionFetalProlongada() {
        return gestacionFetalProlongada;
    }

    public Variable getVariableGestacionFetalProlongada() {
        return gestacionFetalProlongada;
    }

    public String getGestacionFetalProlongadaString() {
        return gestacionFetalProlongada.getValor();
    }

    public Boolean getGestacionFetalProlongadaBoolean() {
        if (gestacionFetalProlongada != null && gestacionFetalProlongada.getValor() != null
                && !gestacionFetalProlongada.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setGestacionFetalProlongada(Variable gestacionFetalProlongada) {
        this.gestacionFetalProlongada = gestacionFetalProlongada;
    }

    public void setGestacionFetalProlongada(String valor) {
        this.gestacionFetalProlongada.setValor(valor);
    }

    public void setGestacionFetalProlongada(Boolean valor) {
        if (valor == true) {
            setGestacionFetalProlongada("Gestación fetal prolongada");
        } else {
            setGestacionFetalProlongada("");
        }
    }

    public Variable getOtrasIndicacionesInduccion() {
        return otrasIndicacionesInduccion;
    }

    public Variable getVariableOtrasIndicacionesInduccion() {
        return otrasIndicacionesInduccion;
    }

    public String getOtrasIndicacionesInduccionString() {
        return otrasIndicacionesInduccion.getValor();
    }

    public void setOtrasIndicacionesInduccion(Variable otrasIndicacionesInduccion) {
        this.otrasIndicacionesInduccion = otrasIndicacionesInduccion;
    }

    public void setOtrasIndicacionesInduccion(String valor) {
        this.otrasIndicacionesInduccion.setValor(valor);
        ;
    }

    public Variable getMaduracioncervical() {
        return maduracioncervical;
    }

    public Variable getVariableMaduracioncervical() {
        return maduracioncervical;
    }

    public String getMaduracioncervicalString() {
        return maduracioncervical.getValor();
    }

    public void setMaduracioncervical(Variable maduracioncervical) {
        this.maduracioncervical = maduracioncervical;
    }

    public void setMaduracioncervical(String valor) {
        this.maduracioncervical.setValor(valor);
    }

    public Variable getHoras() {
        return horas;
    }

    public Variable getVariableHoras() {
        return horas;
    }

    public String getHorasString() {
        return horas.getValor();
    }

    public void setHoras(Variable horas) {
        this.horas = horas;
    }

    public void setHoras(String valor) {
        this.horas.setValor(valor);
    }

    public Variable getPreparacionOxiticica() {
        return preparacionOxiticica;
    }

    public Variable getVariablePreparacionOxiticica() {
        return preparacionOxiticica;
    }

    public String getPreparacionOxiticicaString() {
        return preparacionOxiticica.getValor();
    }

    public void setPreparacionOxiticica(Variable preparacionOxiticica) {
        this.preparacionOxiticica = preparacionOxiticica;
    }

    public void setPreparacionOxiticica(String valor) {
        this.preparacionOxiticica.setValor(valor);
    }

    public Variable getEvolucion() {
        return evolucion;
    }

    public Variable getVariableEvolucion() {
        return evolucion;
    }

    public String getEvolucionString() {
        return evolucion.getValor();
    }

    public void setEvolucion(Variable evolucion) {
        this.evolucion = evolucion;
    }

    public void setEvolucion(String valor) {
        this.evolucion.setValor(valor);
    }

    public String getPatologias() {
        String texto = "";
        if (induccionSiNo != null) {
            texto = texto.concat("Inducción: " + induccionSiNo.getValor());
        }
        if (patologiaMaterna != null) {
            texto = texto.concat(patologiaMaterna.getValor());
        }

        if (patologiaFetalCir != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patologiaFetalCir.getValor());
        }
        if (patologiaFetal != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(patologiaFetal.getValor());
        }
        if (roturaBolasmas12 != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(roturaBolasmas12.getValor());
        }
        if (alteracionLiqAmni != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(alteracionLiqAmni.getValor());
        }
        if (patologiaFetal != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(liquidoMeconal.getValor());
        }
        if (gestacionFetalProlongada != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(gestacionFetalProlongada.getValor());
        }
        return texto;
    }

}
