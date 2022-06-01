package com.example.app_gestao_estagio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.UUID;

public class mudarpassActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    EditText txtuser, txtpass, txtconfpass;
    Button btnMudar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudarpass);
        getSupportActionBar().hide();

        btnMudar = findViewById(R.id.mudarbtn);
        txtuser = (EditText) findViewById(R.id.txtusername2);
        txtpass = (EditText) findViewById(R.id.txtpassword2);
        txtconfpass = (EditText) findViewById(R.id.txtconfpass1);
        db = FirebaseFirestore.getInstance();
    }


    public void mudar(View view) {
        if(txtpass.getText().toString().equals("") || txtconfpass.getText().toString().equals("") || txtuser.getText().toString().equals(""))
        {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        else{
            if(txtpass.getText().toString().equals(txtconfpass.getText().toString())){
                String id;
                updatePassword();
            }
            else
            {
                Toast.makeText(this, "As duas passwords não são iguais", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void voltar1(View view) {
        Intent intent = new Intent(mudarpassActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updatePassword(){
        db.collection("Contas").whereEqualTo("User", txtuser.getText().toString()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot snapshot : task.getResult()){

                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();

                            HashMap<String, Object> mudarpass = new HashMap<>();


                            mudarpass.put("Password", txtpass.getText().toString());

                            db.collection("Contas").document(documentID).update(mudarpass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(mudarpassActivity.this, "Palavra pass alterada com sucesso", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(mudarpassActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_LONG).show();
                    }
                });
    }
}