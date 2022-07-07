package com.example.app_gestao_estagio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class historicoFerramenta_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private MyAdapterRequesicoes adapter;
    private ArrayList<Requesicoes> listRequesicoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_ferramenta);
        listRequesicoes = new ArrayList<>();
        recyclerView = findViewById(R.id.rc_Requesicoes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        ShowData();





    }
    private void ShowData(){
        Intent intent = new Intent(historicoFerramenta_Activity.this, TouchHelper.class);
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

                            Log.i("TAG", "oni-cha " + snapshot.getId());

                            String ID = snapshot.getId();

                            db.collection("Requesicoes").whereEqualTo("Ferramenta_ID", ID).get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            Log.i("TAG", "onComplete: vweefw1");

                                            if (task.getResult().isEmpty())
                                            {
                                                Toast.makeText(getApplicationContext(), "Não existe requisições nesta ferramenta", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(historicoFerramenta_Activity.this, ferramentas_Atcivity.class);
                                                startActivity(intent);
                                            }

                                            for (DocumentSnapshot snapshot : task.getResult()){
                                                Log.i("TAG", "oni-cha " + snapshot.getString("Data_Requesicao"));
                                                Requesicoes requesicoes = new Requesicoes(snapshot.getString("Data_Requesicao"), snapshot.getString("Data_Devolucao"));
                                                listRequesicoes.add(requesicoes);
                                                Log.i("fff","onee-san" + listRequesicoes.get(0).getData_Devolucao());

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