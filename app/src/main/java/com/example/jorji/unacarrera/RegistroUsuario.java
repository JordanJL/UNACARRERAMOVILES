package com.example.jorji.unacarrera;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegistroUsuario extends AppCompatActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    public EditText txt_nombre;
    EditText txt_cedula;
    EditText txt_apellido;
    EditText txt_dir;
    EditText txt_anno;
    EditText txt_tel;
    Button btnCreateProduct;

    // url to create new product
    private static String url_create_product = "http://www.cursoplataformasmoviles.com/unacarrera/usuario/create_product.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrousuario);

        // Edit Text
         txt_cedula = (EditText) findViewById(R.id.txtCedula);
         txt_nombre = (EditText) findViewById(R.id.txtNombre);
         txt_apellido = (EditText) findViewById(R.id.txtApellido);
         txt_tel = (EditText) findViewById(R.id.txtTel);
         txt_dir= (EditText) findViewById(R.id.txtDir);
         txt_anno = (EditText) findViewById(R.id.txtAnno);



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

            String nombre = txt_nombre.getText().toString();
            String apellidos = txt_cedula.getText().toString();
            String telefono = txt_dir.getText().toString();
            String direccion = txt_dir.getText().toString();
            String cedula = txt_cedula.getText().toString();
            String anno= txt_anno.getText().toString();
            String campus="COTO";
            String estado="A";
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("nombre", nombre));
            params.add(new BasicNameValuePair("cedula", cedula));
            params.add(new BasicNameValuePair("apellidos", apellidos));
            params.add(new BasicNameValuePair("telefono", telefono));
            params.add(new BasicNameValuePair("direccion", direccion));
            params.add(new BasicNameValuePair("campus", campus));
            params.add(new BasicNameValuePair("anno", anno));
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
                    // Intent i = new Intent(getApplicationContext(), ass);
                    //startActivity(i);
                    
                    // closing this screen
                    finish();
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
