package com.example.jorji.unacarrera;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Actividad01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad01);



        OnclickDelTextView(R.id.txtregistrar);
        OnclickDelTextView(R.id.txtingresar);
        OnclickDelTextView(R.id.txtolvido);

        // A continuación mi código para OnCreate
        Mensaje("Bienvenido Actividad 01");


    } // Fin del Oncreate de la Actividad 01

    public void Mensaje(String msg){
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
                        EditText u= (EditText) findViewById(R.id.usuario);
                        EditText c= (EditText) findViewById(R.id.contra);
                        String contra = c.getText().toString();
                        int usu= Integer.parseInt(u.getText().toString());
                        String dato= "jjjj1995";
                        if (usu==604290185 && contra.equalsIgnoreCase(dato)){
                            Mensaje("usuario:" + usu + "contraseña:"+ contra);
                            Intent intento2 = new Intent(getApplicationContext(), Actividad02.class);
                            startActivity(intento2);
                        }else{
                            Mensaje("Error verifique su usuario y contraseña");
                        }
                        break;

                    case R.id.txtolvido:


                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelTextView
}
