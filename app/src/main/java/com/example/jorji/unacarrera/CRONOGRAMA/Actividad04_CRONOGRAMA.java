package com.example.jorji.unacarrera.CRONOGRAMA;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;



import com.example.jorji.unacarrera.R;

import java.util.Calendar;

public class Actividad04_CRONOGRAMA extends AppCompatActivity {

    private int anno;
    private int mes;
    private int dia;
    private EditText txt_fecha;
    private Button btnfecha;
    private static final int TIPO_DIALOGO=0;
    private static DatePickerDialog.OnDateSetListener  oyenteSelectorFecha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad04_cronograma);
        //Obtener instancia de los controles dentro del layout
        txt_fecha=(EditText) findViewById(R.id.txt_fecha);
        btnfecha= (Button) findViewById(R.id.btnfecha);
        Calendar calendario= Calendar.getInstance();
        anno=calendario.get(Calendar.YEAR);
        mes=calendario.get(Calendar.MONTH+1);
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mostrarFecha();
        oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                anno= year;
                mes = monthOfYear;
                dia = dayOfMonth;
                mostrarFecha();
            }
        };
    } // Fin del Oncreate de la Actividad 01

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return new DatePickerDialog(this,oyenteSelectorFecha,anno,mes,dia);
        }
        return null;
    }
    public void mostrarCalendario(View control){
        showDialog(TIPO_DIALOGO);
    }

    public void mostrarFecha(){
        txt_fecha.setText(dia+"/"+(mes+1)+"/"+anno);


    }


    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

} // [13:27:50] Fin de la Clas