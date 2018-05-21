package com.example.jorji.unacarrera.LOGIN;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jorji.unacarrera.JSONParser;
import com.example.jorji.unacarrera.LOGIN.Actividad01_LOGIN;
import com.example.jorji.unacarrera.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegistroUsuario extends AppCompatActivity {

    // Progress Dialog
    private ProgressDialog pDialog;
    private int conta=0;
    JSONParser jsonParser = new JSONParser();
    public EditText txt_nombre;
    EditText txt_cedula;
    EditText txt_apellido;
    EditText txt_dir;
    String txt_anno;
    String txt_semestre;
    EditText txt_contrasenna;
    EditText txt_tel;
    Button btnCreateProduct;
    Spinner s1;
    Spinner s2;
    // url to create new product
    private static String url_create_product = "http://www.cursoplataformasmoviles.com/unacarrera/usuario/create_product.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrousuario);
        CargarSpinner();
        // Edit Text

         txt_cedula = (EditText) findViewById(R.id.txtCedula);
         txt_nombre = (EditText) findViewById(R.id.txtNombre);
         txt_apellido = (EditText) findViewById(R.id.txtApellido);
         txt_tel = (EditText) findViewById(R.id.txtTel);
         txt_dir= (EditText) findViewById(R.id.txtDir);
         txt_contrasenna= (EditText) findViewById(R.id.txtContra);


        // Create button
        btnCreateProduct = (Button) findViewById(R.id.btnCompletado);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewProduct().execute();
            }
        });
    }
    private void CargarSpinner() {

        Object item;
        String[] anno = {"Seleccione ",
                "Primer",
                "Segundo",
                "Tercer",
                "Cuarto"
        };
        String[] semestre = {"Escoja Ciclo Actual",
                "I CICLO",
                "II CICLO"
        };
        //---Spinner View---
        s1 = (Spinner) findViewById(R.id.spinner01);
        s2 = (Spinner) findViewById(R.id.spinner02);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, anno);

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, semestre);


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (conta==0){conta++; return;}
                //Mensaje(presidents[position]);
                txt_anno = (String) parent.getItemAtPosition(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Poner esto antes del Mensaje
                if (conta==0){conta++; return;}
                //Mensaje(presidents[position]);
                txt_semestre = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        s1.setAdapter(adapter);

        s2.setAdapter(adapter2);


    }// fin de CargarSpinner
    /**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            System.out.println("Entro aqui");
            String nombre = txt_nombre.getText().toString();
            String apellidos = txt_cedula.getText().toString();
            String contrasenna= txt_contrasenna.getText().toString();
            String telefono = txt_tel.getText().toString();
            String direccion = txt_dir.getText().toString();
            String cedula = txt_cedula.getText().toString();
            String anno= txt_anno;
            String semestre= txt_semestre;
            String campus="COTO";
            String estado="A";
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("cedula", cedula));
                params.add(new BasicNameValuePair("nombre", nombre));
                params.add(new BasicNameValuePair("apellidos", apellidos));
                params.add(new BasicNameValuePair("contrasenna", contrasenna));
                params.add(new BasicNameValuePair("telefono", telefono));
                params.add(new BasicNameValuePair("direccion", direccion));
                params.add(new BasicNameValuePair("campus", campus));
                params.add(new BasicNameValuePair("anno", anno));
                params.add(new BasicNameValuePair("semestre", semestre));
                params.add(new BasicNameValuePair("estado", estado));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    // successfully created product
                     //Intent i = new Intent(getApplicationContext(), apñ);
                    //startActivity(i);
                    // closing this screen
                    //finish();
                    Intent intento = new Intent(getApplicationContext(), Actividad01_LOGIN.class);
                    startActivity(intento);
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }



}
