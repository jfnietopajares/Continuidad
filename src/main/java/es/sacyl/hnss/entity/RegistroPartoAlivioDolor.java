package es.sacyl.hnss.entity;

/**
 * @author 06551256M
 *
 */
public class RegistroPartoAlivioDolor extends Registro {

    private Variable aepidural;
    private Variable alivioDolor;
    private Variable farmacologico;
    private Variable farmaARaquidea;
    private Variable farmaAGeneral;
    private Variable farmaDolantina;
    private Variable farmaHaloperidol;
    private Variable farmaAEAG;
    private Variable farmaAnalgesiaLocal;
    private Variable movimiento;
    private Variable calorLocal;
    private Variable masaje;
    private Variable pelota;
    private Variable ducha;
    private Variable musicoterapia;
    private Variable lenteja;
    private Variable otros;

    public final static Long PLANTILLLA_EDITOR_PAR_ALIVIODOLOR = new Long(794859506);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public final Variable VAR_PARTO_DOLOR_FARMA = new Variable("13994617", "99G2", new Long(13994617),
            "Alivio farmacológico");
    public final Variable VAR_PARTO_DOLOR_FARMA_ANESRA = new Variable("13995195", "99G2", new Long(13995195),
            "Anestesia raquídea");
    public final Variable VAR_PARTO_DOLOR_FARMA_ANESGE = new Variable("13995198", "99G2", new Long(13995198),
            "Anestesia general");
    public final Variable VAR_PARTO_DOLOR_FARMA_HALOPERIDOL = new Variable("13995197", "99G2", new Long(13995197),
            "Dolantia");
    public final Variable VAR_PARTO_DOLOR_FARMA_DOLANTINA = new Variable("13995196", "99G2", new Long(13995196),
            "Haloperidol");
    public final Variable VAR_PARTO_DOLOR_FARMA_AEAG = new Variable("13995199", "99G2", new Long(13995199), "AE+AG");
    public final Variable VAR_PARTO_DOLOR_FARMA_ANALGESIALOCAL = new Variable("386761002", "SNM3", new Long(13832810),
            "Analgesia local");

    public final Variable VAR_PARTO_DOLOR_ALIVIODOLORTRASANALGESIA = new Variable("13994617", "99G2",
            new Long(13994617), "Alicio dolor tras analgesia");

    public final Variable VAR_PARTO_DOLOR_EPIDURAL = new Variable("13991585", "99G2", new Long(13991585),
            "A. Epidural.");

    public final Variable VAR_PARTO_DOLOR_MOVIMIENTO = new Variable("13994618", "99G2", new Long(13994618),
            "Movimiento");

    public final Variable VAR_PARTO_DOLOR_CALOR = new Variable("13994619", "99G2", new Long(13994619), "Calor Local");
    public final Variable VAR_PARTO_DOLOR_MASAJE = new Variable("13994620", "99G2", new Long(13994620), "Masaje");
    public final Variable VAR_PARTO_DOLOR_PELOTA = new Variable("13994621", "99G2", new Long(13994621), "Pelota ");
    public final Variable VAR_PARTO_DOLOR_DUCHA = new Variable("13994622", "99G2", new Long(13994622), "Ducha ");
    public final Variable VAR_PARTO_DOLOR_MUSICO = new Variable("13995193", "99G2", new Long(13995193),
            "Musico terapia ");
    public final Variable VAR_PARTO_DOLOR_LENTEJA = new Variable("13995194", "99G2", new Long(13995194), "Lenteja ");
    public final Variable VAR_PARTO_DOLOR_OTROS = new Variable("13994623", "99G2", new Long(13994623), "Otros ");

    public RegistroPartoAlivioDolor() {
        super();
        iniciaDolor();
    }

    public RegistroPartoAlivioDolor(Long id) {
        super(id);
        iniciaDolor();
    }

    public RegistroPartoAlivioDolor(RegistroPartoAlivioDolor r) {
        super(r);
        aepidural = r.getAepidural();
        alivioDolor = r.getAlivioDolor();
        farmacologico = r.getFarmacologico();
        farmaARaquidea = r.getFarmaARaquidea();
        farmaAGeneral = r.getFarmaAGeneral();
        farmaDolantina = r.getFarmaDolantina();
        farmaHaloperidol = r.getFarmaHaloperidol();
        farmaAEAG = r.getFarmaAEAG();
        farmaAnalgesiaLocal = r.getFarmaAnalgesiaLocal();
        movimiento = r.getMovimiento();
        calorLocal = r.getCalorLocal();
        masaje = r.getMasaje();
        pelota = r.getPelota();
        ducha = r.getDucha();
        musicoterapia = r.getMusicoterapia();
        lenteja = r.getLenteja();
        otros = r.getOtros();
    }

    public void iniciaDolor() {
        this.setTiporegistro(RegistroPartoAlivioDolor.TIPO_REGISTRO_PARTO);
        this.setDescripcion("5.-Alivio Dolor");
        this.setPlantilla_edior(RegistroPartoAlivioDolor.PLANTILLLA_EDITOR_PAR_ALIVIODOLOR);
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));
        aepidural = VAR_PARTO_DOLOR_EPIDURAL;
        alivioDolor = VAR_PARTO_DOLOR_ALIVIODOLORTRASANALGESIA;
        farmacologico = VAR_PARTO_DOLOR_FARMA;
        farmaARaquidea = VAR_PARTO_DOLOR_FARMA_ANESRA;
        farmaAGeneral = VAR_PARTO_DOLOR_FARMA_ANESGE;
        farmaDolantina = VAR_PARTO_DOLOR_FARMA_DOLANTINA;
        farmaHaloperidol = VAR_PARTO_DOLOR_FARMA_HALOPERIDOL;
        farmaAEAG = VAR_PARTO_DOLOR_FARMA_AEAG;
        farmaAnalgesiaLocal = VAR_PARTO_DOLOR_FARMA_ANALGESIALOCAL;
        movimiento = VAR_PARTO_DOLOR_MASAJE;
        calorLocal = VAR_PARTO_DOLOR_CALOR;
        masaje = VAR_PARTO_DOLOR_MASAJE;
        pelota = VAR_PARTO_DOLOR_PELOTA;
        ducha = VAR_PARTO_DOLOR_DUCHA;
        musicoterapia = VAR_PARTO_DOLOR_MUSICO;
        lenteja = VAR_PARTO_DOLOR_LENTEJA;
        otros = VAR_PARTO_DOLOR_OTROS;
    }

    public Variable getAepidural() {
        return aepidural;
    }

    public Variable getVariableAepidural() {
        return aepidural;
    }

    public String getAepiduralString() {
        if (aepidural != null) {
            return aepidural.getValor();
        } else {
            return "";
        }
    }

    public void setAepidural(Variable aepidural) {
        this.aepidural = aepidural;
    }

    public void setAepidural(String valor) {
        this.aepidural.setValor(valor);
    }

    public Variable getAlivioDolor() {
        return alivioDolor;
    }

    public Variable getVariableAlivioDolor() {
        return alivioDolor;
    }

    public String getAlivioDolorString() {
        return alivioDolor.getValor();
    }

    public void setAlivioDolor(Variable alivioDolor) {
        this.alivioDolor = alivioDolor;
    }

    public void setAlivioDolor(String valor) {
        this.alivioDolor.setValor(valor);
    }

    public Variable getFarmacologico() {
        return farmacologico;
    }

    public Variable getVariableFarmacologico() {
        return farmacologico;
    }

    public String getFarmacologicoString() {
        return farmacologico.getValor();
    }

    public void setFarmacologico(Variable farmacologico) {
        this.farmacologico = farmacologico;
    }

    public void setFarmacologico(String valor) {
        this.farmacologico.setValor(valor);
    }

    public Variable getFarmaARaquidea() {
        return farmaARaquidea;
    }

    public Variable getVariableFarmaARaquidea() {
        return farmaARaquidea;
    }

    public Boolean getFarmaARaquideaBoolean() {
        if (farmaARaquidea != null && farmaARaquidea.getValor() != null && !farmaARaquidea.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFarmaARaquidea(Variable farmaARaquidea) {
        this.farmaARaquidea = farmaARaquidea;
    }

    public void setFarmaARaquidea(Boolean valor) {
        if (valor == true) {
            farmaARaquidea.setValor("Anestesia raquídea");
        } else {
            farmaARaquidea.setValor("");
        }
    }

    public Variable getFarmaAGeneral() {
        return farmaAGeneral;
    }

    public Variable getVariableFarmaAGeneral() {
        return farmaAGeneral;
    }

    public Boolean getFarmaAGeneralBoolean() {
        if (farmaAGeneral != null && farmaAGeneral.getValor() != null && !farmaAGeneral.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFarmaAGeneral(Variable farmaAGeneral) {
        this.farmaAGeneral = farmaAGeneral;
    }

    public void setFarmaAGeneral(Boolean valor) {
        if (valor == true) {
            farmaAGeneral.setValor("Anestesia general");
        } else {
            farmaAGeneral.setValor("");
        }
    }

    public Variable getFarmaDolantina() {
        return farmaDolantina;
    }

    public Variable getVariableFarmaDolantina() {
        return farmaDolantina;
    }

    public Boolean getFarmaDolantinaBoolean() {
        if (farmaDolantina != null && farmaDolantina.getValor() != null && !farmaDolantina.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFarmaDolantina(Variable farmaDolantina) {
        this.farmaDolantina = farmaDolantina;
    }

    public void setFarmaDolantina(Boolean valor) {
        if (valor == true) {
            farmaDolantina.setValor("Dolantina ");
        } else {
            farmaDolantina.setValor("");
        }
    }

    public Variable getFarmaHaloperidol() {
        return farmaHaloperidol;
    }

    public Variable getVariableFarmaHaloperidol() {
        return farmaHaloperidol;
    }

    public Boolean getFarmaHaloperidolBoolean() {
        if (farmaHaloperidol != null && farmaHaloperidol.getValor() != null && !farmaHaloperidol.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFarmaHaloperidol(Variable farmaHaloperidol) {
        this.farmaHaloperidol = farmaHaloperidol;
    }

    public void setFarmaHaloperidol(Boolean valor) {
        if (valor == true) {
            farmaHaloperidol.setValor("Haloperidol ");
        } else {
            farmaHaloperidol.setValor("");
        }
    }

    public Variable getFarmaAEAG() {
        return farmaAEAG;
    }

    public Variable getVariableFarmaAEAG() {
        return farmaAEAG;
    }

    public Boolean getFarmaAEAGBoolean() {
        if (farmaAEAG != null && farmaAEAG.getValor() != null && !farmaAEAG.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFarmaAEAG(Variable farmaAEAG) {
        this.farmaAEAG = farmaAEAG;
    }

    public void setFarmaAEAG(Boolean valor) {
        if (valor == true) {
            farmaAEAG.setValor("AE+AG ");
        } else {
            farmaAEAG.setValor("");
        }
    }

    public Variable getFarmaAnalgesiaLocal() {
        return farmaAnalgesiaLocal;
    }

    public Variable getVariableFarmaAnalgesiaLocal() {
        return farmaAnalgesiaLocal;
    }

    public Boolean getFarmaAnalgesiaLocalBoolean() {
        if (farmaAnalgesiaLocal != null && farmaAnalgesiaLocal.getValor() != null
                && !farmaAnalgesiaLocal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFarmaAnalgesiaLocal(Variable farmaAnalgesiaLocal) {
        this.farmaAnalgesiaLocal = farmaAnalgesiaLocal;
    }

    public void setFarmaAnalgesiaLocal(Boolean valor) {
        if (valor == true) {
            farmaAnalgesiaLocal.setValor("Analgesia local");
        } else {
            farmaAnalgesiaLocal.setValor("");
        }
    }

    public Variable getMovimiento() {
        return movimiento;
    }

    public Variable getVariableMovimiento() {
        return movimiento;
    }

    public boolean getMovimientoBolean() {
        if (movimiento != null && movimiento.getValor() != null && !movimiento.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMovimiento(Variable movimiento) {
        this.movimiento = movimiento;
    }

    public void setMovimiento(String valor) {
        this.movimiento.setValor(valor);
    }

    public void setMovimiento(Boolean valor) {
        if (valor == true) {
            movimiento.setValor("Movimiento");
        } else {
            movimiento.setValor("");
        }

    }

    public Variable getCalorLocal() {
        return calorLocal;
    }

    public Variable getVariableCalorLocal() {
        return calorLocal;
    }

    public boolean getCalorLocalBoolean() {
        if (calorLocal != null && calorLocal.getValor() != null && !calorLocal.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String getCalorLocalString() {
        return calorLocal.getValor();
    }

    public void setCalorLocal(Variable calorLocal) {
        this.calorLocal = calorLocal;
    }

    public void setCalorLocal(String valor) {
        this.calorLocal.setValor(valor);
    }

    public void setCalorLocal(Boolean valor) {
        if (valor == true) {
            calorLocal.setValor("Local");
        } else {
            calorLocal.setValor("");
        }
    }

    public Variable getMasaje() {
        return masaje;
    }

    public Variable getVariableMasaje() {
        return masaje;
    }

    public String getMasajeString() {
        return masaje.getValor();
    }

    public boolean getMasajeBoolean() {
        if (masaje != null && masaje.getValor() != null && !masaje.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMasaje(Variable masaje) {
        this.masaje = masaje;
    }

    public void setMasaje(String valor) {
        this.masaje.setValor(valor);
    }

    public void setMasaje(boolean valor) {
        if (valor == true) {
            masaje.setValor("Masaje");
        } else {
            masaje.setValor("");
        }
    }

    public Variable getPelota() {
        return pelota;
    }

    public Variable getVariablePelota() {
        return pelota;
    }

    public String getPelotaString() {
        return pelota.getValor();
    }

    public boolean getPelotaBoolean() {
        if (pelota != null && pelota.getValor() != null && !pelota.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPelota(Variable pelota) {
        this.pelota = pelota;
    }

    public void setPelota(String valor) {
        this.pelota.setValor(valor);
        ;
    }

    public void setPelota(boolean valor) {
        if (valor == true) {
            pelota.setValor("Pelota");
        } else {
            pelota.setValor("");
        }
    }

    public Variable getDucha() {
        return ducha;
    }

    public Variable getVariableDucha() {
        return ducha;
    }

    public String getDuchaString() {
        return ducha.getValor();
    }

    public boolean getDuchaBoolean() {
        if (ducha != null && ducha.getValor() != null && !ducha.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setDucha(Variable ducha) {
        this.ducha = ducha;
    }

    public void setDucha(String valor) {
        this.ducha.setValor(valor);
    }

    public void setDucha(boolean valor) {
        if (valor == true) {
            ducha.setValor("Ducha");
        } else {
            ducha.setValor("");
        }
    }

    public Variable getMusicoterapia() {
        return musicoterapia;
    }

    public Variable getVariableMusicoterapia() {
        return musicoterapia;
    }

    public Boolean getMusicoterapiaBooelan() {
        if (musicoterapia != null && musicoterapia.getValor() != null && !musicoterapia.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setMusicoterapia(Variable musicoterapia) {
        this.musicoterapia = musicoterapia;
    }

    public void setMusicoterapia(Boolean valor) {
        if (valor == true) {
            musicoterapia.setValor("Musicoterapia");
        } else {
            musicoterapia.setValor("");
        }

    }

    public Variable getLenteja() {
        return lenteja;
    }

    public Variable getVariableLenteja() {
        return lenteja;
    }

    public Boolean getLentejaBoolena() {
        if (lenteja != null && lenteja.getValor() != null && !lenteja.getValor().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setLenteja(Variable lenteja) {
        this.lenteja = lenteja;
    }

    public void setLenteja(Boolean valor) {

        if (valor == true) {
            lenteja.setValor("Lenteja");
        } else {
            lenteja.setValor("");
        }

    }

    public Variable getOtros() {
        return otros;
    }

    public Variable getVariableOtros() {
        return otros;
    }

    public String getOtrosString() {
        return otros.getValor();
    }

    public void setOtros(Variable otros) {
        this.otros = otros;
    }

    public void setOtros(String valor) {
        this.otros.setValor(valor);
    }

    public String getAlivios() {
        String texto = "";
        if (movimiento != null) {
            texto = texto.concat(movimiento.getValor());
        }

        if (calorLocal != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(calorLocal.getValor());
        }
        if (masaje != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(masaje.getValor());
        }
        if (pelota != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(pelota.getValor());
        }
        if (ducha != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(ducha.getValor());
        }
        return texto;
    }

    public String getTratamientosFramacologicos() {
        String texto = "";
        if (farmacologico != null) {
            texto = texto.concat(farmacologico.getValor());
        }

        if (farmaARaquidea != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(farmaARaquidea.getValor());
        }
        if (farmaAGeneral != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(farmaAGeneral.getValor());
        }
        if (farmaDolantina != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(farmaDolantina.getValor());
        }
        if (farmaHaloperidol != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(farmaHaloperidol.getValor());
        }
        if (farmaAEAG != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(farmaAEAG.getValor());
        }
        if (farmaAnalgesiaLocal != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(farmaAnalgesiaLocal.getValor());
        }

        return texto;
    }

    public String getTratamientosNoFramacologicos() {
        String texto = "";
        if (movimiento != null) {
            texto = texto.concat(movimiento.getValor());
        }

        if (calorLocal != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(calorLocal.getValor());
        }
        if (masaje != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(masaje.getValor());
        }
        if (pelota != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(pelota.getValor());
        }
        if (ducha != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(ducha.getValor());
        }
        if (musicoterapia != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(musicoterapia.getValor());
        }
        if (lenteja != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(lenteja.getValor());
        }
        if (otros != null) {
            if (!texto.equals("")) {
                texto = texto.concat(" ");
            }
            texto = texto.concat(otros.getValor());
        }
        return texto;
    }

}
