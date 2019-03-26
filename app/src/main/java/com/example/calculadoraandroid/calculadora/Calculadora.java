package com.example.calculadoraandroid.calculadora;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;

public class Calculadora implements Serializable {

    private Operador operador1;
    private Operador operador2;
    private Operador resultado;
    private Operacao operacao;
    private boolean finalizado;
    private NumberFormat nf = NumberFormat.getNumberInstance();

    public Calculadora(){
        this.operador1 = new Operador();
        this.operador2 = new Operador();
        this.resultado = new Operador();
        this.operacao = null;
        this.finalizado = false;
    }

    public boolean getFinalizado() {
        return this.finalizado;
    }

    public void setCaracter(char caracter) throws ParseException {
        if(this.finalizado) {
            this.operador1 = new Operador();
            this.operador2 = new Operador();
            this.resultado = new Operador();

            this.operacao = null;
            this.finalizado = false;
        }

        if (this.operacao == null) {
            this.operador1.setCaracter(caracter);
        } else if (!this.finalizado) {
            this.operador2.setCaracter(caracter);
        } else if (this.operacao != null && this.resultado.getValor() != null) {
            this.operador1.setValor(resultado.getValor());
            this.resultado.setValor(0.0);
            this.operador2.setValor(0.0);
        }
    }

    public void setOperacao(Operacao operacao) {

        this.operacao = operacao;
    }

    public String getValorTexto(){
        String op1 = this.operador1.getValorTexto();
        String op2 = this.operador2.getValorTexto();
        String resultado = this.resultado.getValorTexto();

        String texto = "";

        if (this.operacao == null) {
            texto += "";
        } else if (!this.finalizado) {
            texto += op1 + this.operacao;
        } else {
            texto += op1 + this.operacao + op2;
        }

        return texto;
    }

    public String getValorTextoPrincipal() {
        String op1 = this.operador1.getValorTexto();
        String op2 = this.operador2.getValorTexto();

        String texto = "";

        if (this.operacao == null) {
            texto += op1;
        } else if (!this.finalizado) {
            texto += op2;
        } else {
            texto += getResultado();
        }

        return texto;
    }

    public String getResultado() {
        double op1 = this.operador1.getValor();
        double op2 = this.operador2.getValor();
        double resultado = 0;
        if (this.operacao == Operacao.ADICAO) {
            resultado = op1 + op2;
        } else if (this.operacao == Operacao.SUBTRACAO) {
            resultado = op1 - op2;
        } else if (this.operacao == Operacao.MULTIPLICACAO) {
            resultado = op1 * op2;
        } else if (this.operacao == Operacao.DIVISAO) {
            resultado = op1 / op2;
        } else if (this.operacao == Operacao.PORCENTAGEM) {
            resultado = op1 * op2 / 100;
        } else {
            throw new UnsupportedOperationException("Operação não implementada.");
        }

        this.resultado.setValor(resultado);
        this.operacao = null;

        return nf.format(resultado);
    }

    public void calcular() {
        this.finalizado = true;
    }

    public void removerUltimoCaracter() throws ParseException {
        if (this.operacao == null) {
            this.operador1.removerUltimoCaracter();
        } else if (!this.finalizado) {
            this.operador2.removerUltimoCaracter();
        }
    }

    @Override
    public String toString() {
        String texto = getValorTexto();
        if (getValorTextoPrincipal().trim().length() > 0) {
            texto += " = " + getValorTextoPrincipal();
        }
        return texto;
    }

}
