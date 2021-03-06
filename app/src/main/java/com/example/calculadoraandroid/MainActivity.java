package com.example.calculadoraandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;
import android.view.WindowManager;

import com.example.calculadoraandroid.calculadora.Calculadora;
import com.example.calculadoraandroid.calculadora.Operacao;

public class MainActivity extends AppCompatActivity {

    private Calculadora calculadora = new Calculadora();
    private TextView visor;
    private TextView visorPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // tirar a barra padrão de cima
        this.visor = (TextView) findViewById(R.id.editText);
        this.visorPrincipal = (TextView) findViewById(R.id.editText2);
        atualizarVisor();

        visorPrincipal.setMovementMethod(new ScrollingMovementMethod());
        visor.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("calculadora", this.calculadora);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getSerializable("calculadora") != null) {
            this.calculadora = (Calculadora) savedInstanceState.getSerializable("calculadora");
            atualizarVisor();
        }
    }

    private void setCaracter(char caracter) {
        try {
            calculadora.setCaracter(caracter);
            atualizarVisor();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
        }
    }

    private void atualizarVisor() {
        if (this.calculadora != null) {
            visor.setText(calculadora.getValorTexto());
            visorPrincipal.setText(calculadora.getValorTextoPrincipal());
        } else {
            visor.setText("");
            visorPrincipal.setText("");
        }
    }

    private void setOperacao(Operacao operation) {
        calculadora.setOperacao(operation);
        atualizarVisor();
    }

    public void handleButtonVirgula(View view) {
        if(!calculadora.getFinalizado()) {
            if (!calculadora.getValorTextoPrincipal().contains(",")) {
                if (calculadora.getValorTextoPrincipal().length() != 0) {
                    setCaracter(',');
                } else {
                    setCaracter('0');
                    setCaracter(',');
                }
            }
        }
    }

    public void handleButtonUm(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('1');
        }
    }

    public void handleButtonDois(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('2');
        }
    }

    public void handleButtonTres(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('3');
        }
    }

    public void handleButtonQuatro(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('4');
        }
    }

    public void handleButtonCinco(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('5');
        }
    }

    public void handleButtonSeis(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('6');
        }
    }

    public void handleButtonSete(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('7');
        }
    }

    public void handleButtonOito(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('8');
        }
    }

    public void handleButtonNove(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('9');
        }
    }

    public void handleButtonZero(View view) {
        if(!calculadora.getFinalizado()) {
            setCaracter('0');
        }
    }


    public void handleButtonSoma (View view){

        if(!calculadora.getFinalizado() && !calculadora.getValorTextoPrincipal().isEmpty()) {
            setOperacao(Operacao.ADICAO);
        }
    }

    public void handleButtonSubtrai (View view){

        if(!calculadora.getFinalizado() && !calculadora.getValorTextoPrincipal().isEmpty()) {
            setOperacao(Operacao.SUBTRACAO);
        }
    }

    public void handleButtonMultiplica (View view){

        if(!calculadora.getFinalizado() && !calculadora.getValorTextoPrincipal().isEmpty()) {
            setOperacao(Operacao.MULTIPLICACAO);
        }
    }

    public void handleButtonDivide (View view){

        if(!calculadora.getFinalizado() && !calculadora.getValorTextoPrincipal().isEmpty()) {
            setOperacao(Operacao.DIVISAO);
        }
    }

    public void handleButtonPorcentagem (View view){

        if(!calculadora.getFinalizado() && !calculadora.getValorTextoPrincipal().isEmpty()) {
            setOperacao(Operacao.PORCENTAGEM);
        }
    }

    public void handleButtonResultado(View view) {
        if(!calculadora.getFinalizado() && !calculadora.getValorTextoPrincipal().isEmpty()) {
            calculadora.calcular();
            atualizarVisor();
        }
    }

    public void handleButtonLimpar(View view) {
        calculadora = new Calculadora();
        atualizarVisor();
    }

    public void handleButtonDesfazer(View view) {
        if(!calculadora.getFinalizado() && !calculadora.getValorTextoPrincipal().isEmpty()) {
            try {
                if (calculadora.getValorTextoPrincipal().length() == 1) {
                    handleButtonLimpar(view);
                } else {
                    calculadora.removerUltimoCaracter();
                    atualizarVisor();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getBaseContext(), "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
