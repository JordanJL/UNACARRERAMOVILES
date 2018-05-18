package com.example.jorji.unacarrera;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Actividad03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad03);
        // A continuación mi código para OnCreate
        Mensaje("Bienvenido Actividad 04");

        OnclickDelImageView(R.id.ircronograma);
        OnclickDelImageView(R.id.irmaya);

    } // Fin del Oncreate de la Actividad 01

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

                    case R.id.ircronograma:
                        Intent intento = new Intent(getApplicationContext(), Actividad04.class);
                        startActivity(intento);

                        break;

                    case R.id.irmaya:
                        Intent intento2 = new Intent(getApplicationContext(), Actividad05.class);
                        startActivity(intento2);

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelImageView
    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

} // [13:27:50] Fin de la Clase Actividad 01