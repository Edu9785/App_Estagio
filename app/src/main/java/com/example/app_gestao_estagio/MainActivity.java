package com.example.app_gestao_estagio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText txtusername, txtpassword;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String cargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtEsquecerPass = (TextView) findViewById(R.id.txtEsquecerPassword), txtConta = (TextView) findViewById(R.id.txtConta);
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        auth.getCurrentUser();

        txtConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, criarcontaActivity.class);
                startActivity(intent);
            }
        });

        txtEsquecerPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, mudarpassActivity.class);
                 startActivity(intent);
            }
        });
    }

    public void login(View view) {
        if(txtusername.getText().equals("") || txtpassword.getText().equals(""))
        {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            VerificarUser(txtusername.getText().toString(), txtpassword.getText().toString());
        }
    }

    public void VerificarUser(String User, String Password) {
        db.collection("Contas").whereEqualTo("User", User).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.i("TAG", "onComplete: vweefw1");

                        if (task.getResult().isEmpty())
                        {
                            Toast.makeText(getApplicationContext(), "Utilizador não existe", Toast.LENGTH_LONG).show();
                            Log.i("TAG", "onComplete: erro");
                        }

                        for (DocumentSnapshot snapshot : task.getResult()){

                            Log.i("TAG", "onComplete: " + snapshot.getString("Password"));
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                            if (snapshot.getString("Password").equals(Password)){

                                String ID = snapshot.getId();
                                String cargo = snapshot.getString("Cargo");
                                db.collection("Contas").document(ID).update("Logado", 1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        actfunctstart(cargo, ID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.i("TAG", "onComplete: update error");
                                    }
                                });

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Nome de utilizador ou password incorretos", Toast.LENGTH_LONG).show();
                            }

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Ligação Not ok", Toast.LENGTH_LONG).show();
                    }
                });
    }

             private void actfunctstart(String cargo, String ID){
                if (cargo.equals("Admin")){
                    Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, menuprincipalAdminsActivity.class);
                    intent.putExtra("id", ID);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, menuprincipalActivity.class);
                    intent.putExtra("id", ID);
                    startActivity(intent);
                }

             }
}