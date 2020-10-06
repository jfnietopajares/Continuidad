package es.sacyl.hnss.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroPartoRoturaMembranas extends Registro {

    private Variable roturamembranas;
    private Variable horaRoturaBolsa;
    private Variable horasbr;
    private Variable liqAmioAspceto;
    private Variable liqAmioCantidad;
    public final static Long PLANTILLLA_EDITOR_PAR_ROTURAMEMBRANA = new Long(794858879);
    public final static Long TIPO_REGISTRO_PARTO = new Long(21);

    public final Variable VAR_PARTO_ROTURAMEMBRANA_RM = new Variable("N420101.ROTMEM", "99G2", new Long(104949),
            "Rotura Membranas");
    public final Variable VAR_PARTO_ROTURAMEMBRANA_HORA_ROTURA_BOLSA = new Variable("N420101.TIEMPOBR", "99G2",
            new Long(104950), "Hora rotura bolsa");

    public final Variable VAR_PARTO_ROTURAMEMBRANA_HORASBR = new Variable("35003329", "99G2", new Long(35003329),
            "Horas bolsa rota");

    public final Variable VAR_PARTO_ROTURAMEMBRANA_LIQUAMNI = new Variable("N420101.ASPLIQAMN", "99G2",
            new Long(104874), "Líquido amniótico");
    public final Variable VAR_PARTO_ROTURAMEMBRANA_LIQUAMNICANTI = new Variable("364354005", "SNM3", new Long(13994616),
            "Líquido amniótico cantidad");

    // valores=",Espontánea,Artificial,Tempestiva,Prematura" separador=",">Rotura
    // membranas</item>
//R" valores=",Normal,Teñido,Sanguinolento" separador=",">
    // "SELECTOR" valores=",Normal,Abundante,Escaso" separador=",">
    public RegistroPartoRoturaMembranas() {
        iniciaRoturaM();
    }

    public RegistroPartoRoturaMembranas(Long id) {
        super(id);
        iniciaRoturaM();
    }

    public RegistroPartoRoturaMembranas(RegistroPartoRoturaMembranas r) {
        super(r);
        this.roturamembranas = r.getRoturamembranas();
        this.horaRoturaBolsa = r.getHoraRoturaBolsa();
        this.horasbr = r.getHorasbr();
        this.liqAmioAspceto = r.getLiqAmioAspceto();
        this.liqAmioCantidad = r.liqAmioCantidad;
    }

    public void iniciaRoturaM() {
        this.setTiporegistro(TIPO_REGISTRO_PARTO);
        this.setDescripcion("4.-Rotura Membranas");
        this.setPlantilla_edior(PLANTILLLA_EDITOR_PAR_ROTURAMEMBRANA);
        this.setServicio(new Servicio(new Long(40), "OBS", "Obstetricia y Ginecologia"));

        this.roturamembranas = VAR_PARTO_ROTURAMEMBRANA_RM;
        this.horaRoturaBolsa = this.VAR_PARTO_ROTURAMEMBRANA_HORA_ROTURA_BOLSA;
        this.horasbr = VAR_PARTO_ROTURAMEMBRANA_HORASBR;
        this.liqAmioAspceto = VAR_PARTO_ROTURAMEMBRANA_LIQUAMNI;
        this.liqAmioCantidad = VAR_PARTO_ROTURAMEMBRANA_LIQUAMNICANTI;

    }

    public Variable getRoturamembranas() {
        return roturamembranas;
    }

    public Variable getVariableRoturamembranas() {
        return roturamembranas;
    }

    public String getRoturamembranasString() {
        return roturamembranas.getValor();
    }

    public void setRoturamembranas(Variable roturamembranas) {
        this.roturamembranas = roturamembranas;
    }

    public void setRoturamembranas(String valor) {
        this.roturamembranas.setValor(valor);
    }

    public Variable getHoraRoturaBolsa() {
        return horaRoturaBolsa;
    }

    public Variable getVariableHoraRoturaBolsa() {
        return horaRoturaBolsa;
    }

    public LocalDateTime getHoraRoturaBolsaDatetime() {
        LocalDateTime fechahora = null;
        if (horaRoturaBolsa != null && !horaRoturaBolsa.getValor().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            fechahora = LocalDateTime.parse(horaRoturaBolsa.getValor(), formatter);
        }
        return fechahora;
    }

    public String getHoraRoturaBolsaString() {
        return horaRoturaBolsa.getValor();
    }

    public void setHoraRoturaBolsa(Variable horaRoturaBolsa) {
        this.horaRoturaBolsa = horaRoturaBolsa;
    }

    public void setHoraRoturaBolsa(String valor) {
        this.horaRoturaBolsa.setValor(valor);
    }

    public void setHoraRoturaBolsa(LocalDateTime valor) {
        if (valor != null) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm");
            String formattedString = valor.format(formatter);
            this.horaRoturaBolsa.setValor(formattedString);
        }

    }

    public Variable getHorasbr() {
        return horasbr;
    }

    public Variable getVariableHorasbr() {
        return horasbr;
    }

    public String getHorasbrString() {
        return horasbr.getValor();
    }

    public void setHorasbr(Variable horasbr) {
        this.horasbr = horasbr;
    }

    public void setHorasbr(String valor) {
        this.horasbr.setValor(valor);
    }

    public Variable getLiqAmioAspceto() {
        return liqAmioAspceto;
    }

    public Variable getVariableLiqAmioAspceto() {
        return liqAmioAspceto;
    }

    public String getLiqAmioAspcetoString() {
        return liqAmioAspceto.getValor();
    }

    public void setLiqAmioAspceto(Variable liqAmioAspceto) {
        this.liqAmioAspceto = liqAmioAspceto;
    }

    public void setLiqAmioAspceto(String valor) {
        this.liqAmioAspceto.setValor(valor);
    }

    public Variable getLiqAmioCantidad() {
        return liqAmioCantidad;
    }

    public Variable getVariableLiqAmioCantidad() {
        return liqAmioCantidad;
    }

    public String getLiqAmioCantidadString() {
        return liqAmioCantidad.getValor();
    }

    public void setLiqAmioCantidad(Variable liqAmioCantidad) {
        this.liqAmioCantidad = liqAmioCantidad;
    }

    public void setLiqAmioCantidad(String valor) {
        this.liqAmioCantidad.setValor(valor);
    }

}
