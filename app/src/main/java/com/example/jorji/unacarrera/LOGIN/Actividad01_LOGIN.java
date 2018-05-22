package com.example.jorji.unacarrera.LOGIN;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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
    EditText txtCedula;
    EditText txt_contrasenna;
    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    // single product url
    //private static String url_product_detials = "http://www.cursoplataformasmoviles.com/unacarrera/usuario/get_user_details.php";
    private static final String url_product_detials = "http://www.cursoplataformasmoviles.com/unacarrera/usuario/get_user_details.php";
    // JSON Node names
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CEDULA = "cedula";
    private static final String TAG_CONTRA = "contrasenna";

    // products JSONArray
    JSONArray products = null;

    @Override


                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
                .penaltyLog().build());
                        setContentView(R.layout.activity_actividad01_login);
                        txt_cedula = (EditText) findViewById(R.id.txt_usuario);
                        txt_contrasenna= (EditText)findViewById(R.id.txt_contrasenna);
                        OnclickDelTextView(R.id.txtingresar);
                        OnclickDelTextView(R.id.txtregistrar);
                        OnclickDelTextView(R.id.txtolvido);




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
                        // Getting complete product details in background thread
                        new GetProductDetails().execute();
                      //  Intent intento2 = new Intent(getApplicationContext(), Actividad02_MENU.class);
                       // startActivity(intento2);

                        //new GetProductDetails().execute();
                        break;

                    case R.id.txtolvido:

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
           /* pDialog = new ProgressDialog(Actividad01_LOGIN.this);
            pDialog.setMessage("Loading product details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
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
                    String cedula= txt_cedula.getText().toString();
                    String contrasenna= "'"+txt_contrasenna.getText().toString()+"'";
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("cedula", cedula));
                        params.add(new BasicNameValuePair("contrasenna", contrasenna));
                       // System.out.println("cedula: " +cedula);

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_product_detials, "GET", params);

                        success = json.getInt("success");
                        System.out.println(success);
                        // check your log for json response
                        //Log.d("Single Product Details", json.toString());
                        //Mensaje("Single Product Details"+ json.toString());

                        // json success tag

                        System.out.println(success);
                        if (success == 1) {
                            Mensaje("Bienvenido");
                           // Mensaje("Datos"+ json.toString());
                            txt_cedula.setText("");
                            txt_contrasenna.setText("");
                            Intent intento = new Intent(getApplicationContext(), Actividad02_MENU.class);
                            startActivity(intento);

                       }else{
                            Mensaje("Verifique su usuario y contraseña");
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
            //pDialog.dismiss();
        }
    }

}
