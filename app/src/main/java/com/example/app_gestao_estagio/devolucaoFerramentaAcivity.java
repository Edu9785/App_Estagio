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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class devolucaoFerramentaAcivity extends AppCompatActivity {

    EditText txtData;
    Button btnVoltar, btnDevolver;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devolucao_ferramenta_acivity);
        txtData = findViewById(R.id.txtdatadevolucao);
        btnVoltar = findViewById(R.id.btnVoltarDevolver);
        btnDevolver = findViewById(R.id.btnDevolver);
        db = FirebaseFirestore.getInstance();
        btnDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InserirDevolucao();
            }
        });
    }


    public void InserirDevolucao() {
        db.collection("Contas").whereEqualTo("Logado", 1).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.getResult().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Erro na busca do ID", Toast.LENGTH_LONG).show();
                            Log.i("TAG", "onComplete: erro");
                        }

                        for (DocumentSnapshot snapshot : task.getResult()) {
                            String ID = snapshot.getId();
                            HashMap<String, Object> requisicao = new HashMap<>();
                            requisicao.put("Data_Devolucao", txtData.getText().toString());
                            requisicao.put("ID_UserConfirmacao", ID);




                            db.collection("Requesicoes").document().set(requisicao)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(devolucaoFerramentaAcivity.this, "Data adicionada com sucesso", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(devolucaoFerramentaAcivity.this, historicoFerramenta_AdminAcivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(devolucaoFerramentaAcivity.this, "Failed", Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Tua cota", "Erro");
                    }
                });
    }
}