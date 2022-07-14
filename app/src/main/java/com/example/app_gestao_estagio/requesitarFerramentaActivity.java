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

public class requesitarFerramentaActivity extends AppCompatActivity {
    FirebaseFirestore db;
    EditText txtData;
    Button btnRequesitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requesitar_ferramenta);
        txtData = findViewById(R.id.txtdatadevolucao);
        btnRequesitar = findViewById(R.id.btnDevolver);
        db = FirebaseFirestore.getInstance();

        btnRequesitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requesitarFerramentaActivity.this, historicoFerramenta_Activity.class);
                String ID = getIntent().getExtras().getString("ID");
                Log.i("TAG", "Show data " + ID);
                if(ID != null){
                    requesitarFerramenta(ID, txtData.getText().toString());
                }

            }
        });
        }


    private void requesitarFerramenta(String ID,String Data){
        if (Data.equals("")){
            Toast.makeText(getApplicationContext(), "Preencha o campo", Toast.LENGTH_LONG).show();
        }else
        {

            db.collection("Contas").whereEqualTo("Logado", 1).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.getResult().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Erro na busca do ID", Toast.LENGTH_LONG).show();
                                Log.i("TAG", "onComplete: erro");
                            }

                            for (DocumentSnapshot snapshot : task.getResult())   {

                                 String id = snapshot.getId();
                                 HashMap<String, Object> requesicao = new HashMap<>();
                                 requesicao.put("Data_Requesicao", Data);
                                 requesicao.put("Ferramenta_ID", ID);
                                 requesicao.put("User_ID", id);

                                db.collection("Requesicoes").document(ID).set(requesicao).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(requesitarFerramentaActivity.this, "Ferramenta requesitada com sucesso", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(requesitarFerramentaActivity.this, historicoFerramenta_Activity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }

                                });


                            }
                        }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               Toast.makeText(requesitarFerramentaActivity.this, "Failed", Toast.LENGTH_LONG).show();
                            }
                        });
        }

    }
}