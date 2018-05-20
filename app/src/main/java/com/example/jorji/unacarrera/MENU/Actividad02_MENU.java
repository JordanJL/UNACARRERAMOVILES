package com.example.jorji.unacarrera.MENU;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jorji.unacarrera.MATRICULA.Actividad03_MATRICULA;
import com.example.jorji.unacarrera.CRONOGRAMA.Actividad04_CRONOGRAMA;
import com.example.jorji.unacarrera.MALLACURRICULAR.Actividad05_MALLA;
import com.example.jorji.unacarrera.R;

public class Actividad02_MENU extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad02_menu);
        // A continuación mi código para OnCreate
        // alambramos el Button
        // alambramos el ImageView

        OnclickDelImageView(R.id.btnCronograma);
        OnclickDelImageView(R.id.btnMatricula);
        OnclickDelImageView(R.id.btnStatus);
        OnclickDelImageView(R.id.btnMalla);


    } // Fin del Oncreate de la Actividad 01

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

    public void OnclickDelImageView(int ref) {

        // Ejemplo  OnclickDelImageView(R.id.MiImageView);
        // 1 Doy referencia al ImageView
        View view =findViewById(ref);
        ImageView miImageView = (ImageView) view;
        //  final String msg = miImageView.getText().toString();
        // 2.  Programar el evento onclick
        miImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.btnCronograma:

                        Intent crono = new Intent(getApplicationContext(), Actividad04_CRONOGRAMA.class);
                        startActivity(crono);
                        break;

                    case R.id.btnMatricula:
                        Intent matri = new Intent(getApplicationContext(), Actividad03_MATRICULA.class);
                        startActivity(matri);

                        break;

                    case R.id.btnStatus:


                        break;

                    case R.id.btnMalla:
                        Intent malla = new Intent(getApplicationContext(), Actividad05_MALLA.class);
                        startActivity(malla);

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelImageView
} // [13:18:01] Fin de la Clase Actividad 01