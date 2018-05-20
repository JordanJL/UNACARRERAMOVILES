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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Actividad01_LOGIN extends AppCompatActivity {
    EditText txt_cedula;
    EditText txt_contrasenna;
    EditText txt_nombre;
    EditText txt_apellido;
    EditText txt_telefono;
    EditText txt_direccion;
    EditText txt_anno;
    EditText txt_campus;
    EditText txt_semestre;
    EditText txt_estado;
    private ProgressDialog pDialog;
    Button ingresar;
    String cedula;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    // url to create new product
    private static String url_product_detials = "http://www.cursoplataformasmoviles.com/unacarrera/usuario/get_product_details.php";
    // JSON Node names

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCT = "products";
    private static final String TAG_CEDULA = "cedula";
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_APELLIDOS = "apellidos";
    private static final String TAG_CONTRASENNA = "contrasenna";
    private static final String TAG_TELEFONO = "telefono";
    private static final String TAG_DIRECCION = "direccion";
    private static final String TAG_CAMPUS = "campus";
    private static final String TAG_ANNO = "anno";
    private static final String TAG_SEMESTRE = "semestre";
    private static final String TAG_ESTADO = "estado";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad01);

        OnclickDelTextView(R.id.txtingresar);
        OnclickDelTextView(R.id.txtregistrar);
        OnclickDelTextView(R.id.txtolvido);
        ingresar = (Button) findViewById(R.id.btningresar);

        //txt_usuario = (EditText) findViewById(R.id.usuario);

        //txt_contrasenna = (EditText) findViewById(R.id.contra);
        // A continuación mi código para OnCreat
        Intent i= getIntent();
        cedula=i.getStringExtra(TAG_CEDULA);
        //new GetProductDetails().execute();

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
                        new GetProductDetails().execute();
                        break;

                    case R.id.txtolvido:
                        Intent intento2 = new Intent(getApplicationContext(), get_all_products.class);
                        startActivity(intento2);

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelTextView

    /**
     * Background Async Task to Get complete product details
     * */
    class GetProductDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        /**
         * Getting product details in background thread
         * */
        protected String doInBackground(String... params) {
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;

                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("cedula", cedula));
                        System.out.println("cedula: " +cedula);
                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_product_detials, "GET", params);

                        // check your log for json response
                        Log.d("Single Product Details", json.toString());

                        // check for success tag
                        try {
                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // product with this pid found
                            // successfully received product details
                            JSONArray productObj = json
                                    .getJSONArray(TAG_PRODUCT); // JSON Array

                            // get first product object from JSON Array
                            JSONObject product = productObj.getJSONObject(0);
                            System.out.println("products :" + product);
                            // product with this pid found
                            // Edit Text
                           // txt_cedula = (EditText) findViewById(R.id.txtcedula);
                            //txt_contrasenna = (EditText) findViewById(R.id.txtcontrasenna);
                           /* txt_nombre = (EditText) findViewById(R.id.txtnombre);
                            txt_apellido = (EditText) findViewById(R.id.txtapellidos);
                            txt_anno = (EditText) findViewById(R.id.txtanno);
                            txt_semestre = (EditText) findViewById(R.id.txtsemestre);
                            txt_estado = (EditText) findViewById(R.id.txtestado);
                            txt_telefono = (EditText) findViewById(R.id.txttelefono);
                            txt_campus = (EditText) findViewById(R.id.txtcampus);
                            txt_direccion = (EditText) findViewById(R.id.txtdireccion);*/


                            // display product data in EditText
                            txt_cedula.setText(product.getString(TAG_CEDULA));
                            // txt_nombre.setText(product.getString(TAG_NOMBRE));
                            // txt_apellido.setText(product.getString(TAG_APELLIDOS));
                            txt_contrasenna.setText(product.getString(TAG_CONTRASENNA));
                            //txt_telefono.setText(product.getString(TAG_TELEFONO));
                            //txt_direccion.setText(product.getString(TAG_DIRECCION));
                            //txt_campus.setText(product.getString(TAG_CAMPUS));
                            //txt_anno.setText(product.getString(TAG_ANNO));
                            //txt_semestre.setText(product.getString(TAG_SEMESTRE));
                            //txt_estado.setText(product.getString(TAG_ESTADO));

                        }else{
                            // product with pid not found
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                });
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }
}
