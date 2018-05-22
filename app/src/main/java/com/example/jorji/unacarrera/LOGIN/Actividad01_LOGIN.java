package com.example.jorji.unacarrera.LOGIN;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorji.unacarrera.JSONParser;
import com.example.jorji.unacarrera.MENU.Actividad02_MENU;
import com.example.jorji.unacarrera.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Actividad01_LOGIN extends AppCompatActivity {


    EditText txt_cedula;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;
    private static final String TAG_PRODUCT = "product";
    // single product url
    //private static String url_product_detials = "http://www.cursoplataformasmoviles.com/unacarrera/usuario/get_user_details.php";
    private static String url_all_products = "http://www.cursoplataformasmoviles.com/unacarrera/usuario/get_all_user_usu_contra.php";
    // JSON Node names
    // products JSONArray
    JSONArray products = null;
    private static final String TAG_SUCCESS = "success";
    @Override


                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_actividad01_login);
                        txt_cedula = (EditText) findViewById(R.id.txt_usuario);

                        OnclickDelTextView(R.id.txtingresar);
                        OnclickDelTextView(R.id.txtregistrar);
                        OnclickDelTextView(R.id.txtolvido);

                        // Hashmap for ListView
                        productsList = new ArrayList<HashMap<String, String>>();


                         // Loading products in Background Thread



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

                        Intent intento2 = new Intent(getApplicationContext(), AllProductsActivity.class);
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
