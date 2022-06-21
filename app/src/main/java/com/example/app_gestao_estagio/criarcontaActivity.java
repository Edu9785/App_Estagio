package com.example.app_gestao_estagio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

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
import java.util.UUID;

public class criarcontaActivity extends AppCompatActivity {
    EditText txtuser, txtpass, txtconfpass, txtemail;
    Spinner spcargo;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criarconta);
        getSupportActionBar().hide();
        txtuser = (EditText) findViewById(R.id.txtusername1);
        txtpass = (EditText) findViewById(R.id.txtpassword1);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtconfpass = (EditText) findViewById(R.id.txtconfpass);
        spcargo = (Spinner) findViewById(R.id.spCargo);
        db = FirebaseFirestore.getInstance();
    }

    private void guardarFirestore(String ID,String username, String email, String password, String cargo){
        if (username.equals("") || email.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_LONG).show();
        }else {
            HashMap<String, Object> conta  = new HashMap<>();
            conta.put("User", username);
            conta.put("Email", email);
            conta.put("Password", password);
            conta.put("Cargo", cargo);

            db.collection("Contas").document(ID).set(conta)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(criarcontaActivity.this, "Conta criada com sucesso", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(criarcontaActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(criarcontaActivity.this, "Failed", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    public void criar(View view) {
        String id = UUID.randomUUID().toString();
        if(txtpass.getText().toString().equals(txtconfpass.getText().toString())){
            guardarFirestore(id,txtuser.getText().toString(), txtemail.getText().toString(), txtpass.getText().toString(), spcargo.getSelectedItem().toString());
        }
        else
        {
            Toast.makeText(this, "As password n correspondem", Toast.LENGTH_SHORT).show();
        }
    }

    public void voltar(View view) {
        Intent intent = new Intent(criarcontaActivity.this, MainActivity.class);
        startActivity(intent);
    }
}



