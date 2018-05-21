package com.example.jorji.unacarrera.CALENDAR;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jorji.unacarrera.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Actividad_cronograma2 extends AppCompatActivity{

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    //Another activity
    private static DatePickerDialog.OnDateSetListener  oyenteSelectorFecha;
    private static final int TIPO_DIALOGO=0;
    private int anno;
    private int mes;
    private int dia;
    private EditText txt_fecha;
    private Button btnfecha;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronograma2);

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

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //Set an event for Teachers' Professional Day 2016 which is 21st of October

        Event ev1 = new Event(Color.BLUE, 1526798081000L, "Teachers' Professional Day");
        compactCalendar.addEvent(ev1);

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.toString().compareTo("Mon May 7 00:00:00 AST 2018") == 0) {
                    Toast.makeText(context, "Teachers' Professional Day", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }

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
}