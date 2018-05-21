package com.example.jorji.unacarrera.CRONOGRAMA;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jorji.unacarrera.R;

public class Actividad04_CRONOGRAMA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad04_cronograma);
        // A continuación mi código para OnCreate
        Mensaje("Bienvenido Actividad 04");


    } // Fin del Oncreate de la Actividad 01

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

} // [13:27:50] Fin de la Clas