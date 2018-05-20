package com.example.jorji.unacarrera;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Actividad01_LOGIN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad01);

        OnclickDelTextView(R.id.txtingresar);
        OnclickDelTextView(R.id.txtregistrar);
        OnclickDelTextView(R.id.txtolvido);

    } // Fin del Oncreate de la Actividad 01

    public void   Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};


  public void OnclickDelTextView(int ref) {
        // Ejemplo  OnclickDelTextView(R.id.MiTextView);
        // 1 Doy referencia al TextView
        View view =findViewById(ref);
        TextView miTextView = (TextView) view;
        //  final String msg = miTextView.getText().toString();
        // 2.  Programar el evento onclick
        miTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.txtregistrar:

                        Intent intento = new Intent(getApplicationContext(), RegistroUsuario.class);
                        startActivity(intento);

                        break;

                    case R.id.txtingresar:

                        Intent intento2 = new Intent(getApplicationContext(), Actividad02_MENU.class);
                        startActivity(intento2);

                        //new GetProductDetails().execute();
                        break;

                    case R.id.txtolvido:

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelTextView

}
