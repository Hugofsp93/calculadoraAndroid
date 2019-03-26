package com.example.calculadoraandroid.calculadora;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;

public class Operador implements Serializable {

    private Double valor = 0.0;
    private String texto = "0";
    private NumberFormat nf = NumberFormat.getNumberInstance();

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setCaracter(char caracter) throws ParseException {
        if (texto == "0" && caracter != ',' && caracter != '0') {
            texto = Character.toString(caracter);
        } else {
            texto += caracter;

        }
        valor = nf.parse(texto).doubleValue();
    }

    public String getValorTexto() {

        return this.texto;
    }

    public Double getValor() {

        return this.valor;
    }

    public void removerUltimoCaracter() throws ParseException {
        if (texto.length() > 1) {
            texto = texto.substring(0, texto.length() - 1);
            valor = nf.parse(texto).doubleValue();
        } else {
            texto = "0";
            valor = 0.0;
        }
    }
}
