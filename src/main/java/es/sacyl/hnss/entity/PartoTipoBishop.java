package es.sacyl.hnss.entity;

public class PartoTipoBishop {

    int valor;
    String texto;

    public PartoTipoBishop() {

    }

    public PartoTipoBishop(int valor, String texto) {
        this.valor = valor;
        this.texto = texto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
