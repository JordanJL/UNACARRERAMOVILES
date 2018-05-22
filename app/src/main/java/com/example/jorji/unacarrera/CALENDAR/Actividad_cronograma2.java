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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Actividad_cronograma2 extends AppCompatActivity{
    Date date;
    EditText eventos;
    CompactCalendarView compactCalendar;
    public Calendar calendario= Calendar.getInstance();
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.getDefault());
    //Another activity
    private static DatePickerDialog.OnDateSetListener  oyenteSelectorFecha;
    private static final int TIPO_DIALOGO=0;
    private int anno;
    private int mes;
    private int dia;
    private EditText txt_fecha;
    private Button btnfecha;
    String evet;
    int cont=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronograma2);

        //Obtener instancia de los controles dentro del layout
       // txt_fecha=(EditText) findViewById(R.id.txt_fecha);
        btnfecha= (Button) findViewById(R.id.btnfecha);
        eventos= (EditText) findViewById(R.id.txtDescripcion);
        anno=calendario.get(Calendar.YEAR);
        mes=calendario.get(Calendar.MONTH+1);
        dia=calendario.get(Calendar.DAY_OF_MONTH);

            oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    anno = year;
                    mes = monthOfYear;
                    dia = dayOfMonth;
                    //if(cont ==0) {
                    try {
                        mostrarFecha();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //}else {
                      //  Mensaje("Ya habia entrado");
                       // cont=0;
                    //}
                }
            };

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);



        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                // CST
                if (dateClicked.toString().compareTo("Sat May 19 00:00:00 EDT 2018")==0) {
                    eventos.setText(evet);
                    //Toast.makeText(context, "El evento es ir a la U jajaaj" , Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                    //Mensaje("datos :"+dateClicked.toString());
                    eventos.setText("No existen eventos en esta fecha");
                    //Mensaje("datos :"+formatDate(1526799519000L) );

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
        evet=eventos.getText().toString();
        switch (id){
            case 0:
                return new DatePickerDialog(this,oyenteSelectorFecha,anno,mes,dia);
        }
        return null;
    }
    public void mostrarCalendario(View control){
        showDialog(TIPO_DIALOGO);
    }

    public void mostrarFecha() throws ParseException {
        //txt_fecha.setText(dia+"/"+(mes+1)+"/"+anno);

            Mensaje("entro");
            String dateInString = dia + "-" + (mes + 1) + "-" + anno + " 00:00:00L";
            date = dateFormatMonth.parse(dateInString);
            eventos.setText("");
            generadorEventos();
    }

    private void generadorEventos() {
        if (cont==0){
            Event ev3 = new Event(Color.BLACK, date.getTime(), "Teachers' Professional Day");
            compactCalendar.addEvent(ev3);
            cont++;
        }else {
            Mensaje("Ya habia entrado");
        cont=0;
        }

    }


    public void Mensaje(String msg){Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

    public String formatDate(long dateInMillis) {

        Date date = new Date(dateInMillis);
        return dateFormatMonth.getDateInstance().format(date);
    }

}