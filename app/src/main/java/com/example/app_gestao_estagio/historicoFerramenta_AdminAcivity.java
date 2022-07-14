package com.example.app_gestao_estagio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class historicoFerramenta_AdminAcivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private MyAdapterRequesicoes adapter;
    private ArrayList<Requesicoes> listRequesicoes;

    public void buscarID(String nomeferramenta) {
        db.collection("Ferramentas").whereEqualTo("Nome_Ferramenta", nomeferramenta).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.getResult().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Erro na busca do ID", Toast.LENGTH_LONG).show();
                            Log.i("TAG", "onComplete: erro");
                        }

                        for (DocumentSnapshot snapshot : task.getResult()) {
                            String ID = snapshot.getId();
                            Intent Intent = new Intent(historicoFerramenta_AdminAcivity.this, requesitarFerramentaActivity.class);
                            Intent.putExtra("ID", ID);
                            Toast.makeText(historicoFerramenta_AdminAcivity.this, "ID " + ID, Toast.LENGTH_SHORT).show();
                            startActivity(Intent);
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Ligação Not ok", Toast.LENGTH_LONG).show();
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_ferramenta);
        Button btnRequesitar, btnVoltar;

        btnRequesitar = findViewById(R.id.btnDevolver);
        btnVoltar = findViewById(R.id.btnVoltarDevolver);
        listRequesicoes = new ArrayList<>();
        recyclerView = findViewById(R.id.rc_Requesicoes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        ShowData();


        btnRequesitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(historicoFerramenta_AdminAcivity.this, TouchHelper.class);
                String nome = getIntent().getExtras().getString("nome");
                if(nome != null) {
                    buscarID(nome);
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(historicoFerramenta_AdminAcivity.this, ferramentas_Atcivity.class);
                startActivity(intent);
            }
        });
    }

    private void ShowData(){
        Intent intent = new Intent(historicoFerramenta_AdminAcivity.this, TouchHelper.class);
        String nome = getIntent().getExtras().getString("nome");
        Log.i("TAG", "ShowData: " + nome);
        if(nome != null){

            listarDataFerramenta(nome);
        }
    }

    public void listarDataFerramenta(String nome){
        db.collection("Ferramentas").whereEqualTo("Nome_Ferramenta", nome).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.i("TAG", "onComplete: vweefw1");

                        if (task.getResult().isEmpty())
                        {
                            Toast.makeText(getApplicationContext(), "Erro na busca", Toast.LENGTH_LONG).show();
                            Log.i("TAG", "onComplete: erro");
                        }

                        for (DocumentSnapshot snapshot : task.getResult()){


                            String ID = snapshot.getId();

                            db.collection("Requesicoes").whereEqualTo("Ferramenta_ID", ID).get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            Log.i("TAG", "onComplete: vweefw1");

                                            if (task.getResult().isEmpty())
                                            {
                                                Toast.makeText(getApplicationContext(), "Não existe requisições nesta ferramenta", Toast.LENGTH_LONG).show();
                                            }

                                            for (DocumentSnapshot snapshot : task.getResult()){
                                                Requesicoes requesicoes = new Requesicoes(snapshot.getString("Data_Requesicao"), snapshot.getString("Data_Devolucao"));
                                                listRequesicoes.add(requesicoes);

                                            }
                                            adapter = new MyAdapterRequesicoes(listRequesicoes);
                                            recyclerView.setAdapter(adapter);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Ligação Not ok", Toast.LENGTH_LONG).show();
                                        }
                                    });

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Ligação Not ok", Toast.LENGTH_LONG).show();
                    }
                });
    }
}