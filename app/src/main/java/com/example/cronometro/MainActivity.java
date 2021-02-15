package com.example.cronometro;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseOffset; // Calcular a diferença para que o resume continue de onde parou
    private boolean running; //Para indicar a atividade do cronômetro


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //Cronômetro
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Tempo: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());


    }

    // Métodos do cronômetro
    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset); //Dizer ao contador onde ele deve começar (verificar utilidade)
            chronometer.start();
            running = true; // reset running to true
        }
    }

    public void pauseChronometer(View v) {
        if (running) { //Inverso do método start
            chronometer.stop(); //pausa método anterior
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase(); //retorna ao setbase
            running = false;
        }
    }
    //Botão Continuar
    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0; //Zerar cronômetro
    }
}