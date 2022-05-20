package com.example.app_gestao_estagio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class criarcontaActivity extends AppCompatActivity {
    EditText txtuser, txtpass, txtconfpass, txtemail;
    Spinner spcargo;
    String URL = "https://10.0.2.2/RC/Gestao_Morebiz/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criarconta);
        getSupportActionBar().hide();
        Button btnCriar = (Button) findViewById(R.id.criarbtn);
        txtuser = (EditText) findViewById(R.id.txtusername1);
        txtpass = (EditText) findViewById(R.id.txtpassword1);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtconfpass = (EditText) findViewById(R.id.txtconfpass);
        spcargo = (Spinner) findViewById(R.id.spCargo);
    }

    public void criar(View view) {
        String user = txtuser.getText().toString().trim();
        String password = txtpass.getText().toString().trim();
        String confpass = txtconfpass.getText().toString().trim();
        String email = txtemail.getText().toString().trim();
        String cargo = spcargo.getSelectedItem().toString().trim();

        if (!user.isEmpty() && !password.isEmpty() && !confpass.isEmpty() && !cargo.isEmpty()) {
            if (password.equals(confpass)) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Intent intent = new Intent(criarcontaActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (response.equals("failure")) {
                            Toast.makeText(criarcontaActivity.this, "Algum dos campos está mal preenchido", Toast.LENGTH_SHORT).show();
                        }
                    }
                        }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(criarcontaActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                        }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("user", user);
                            data.put("password", password);
                            data.put("email", email);
                            data.put("tipo", cargo);
                            return data;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                        requestQueue.add(stringRequest);
            }else {
                Toast.makeText(criarcontaActivity.this, "As duas passwords têm que ser iguais", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(criarcontaActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }
}



