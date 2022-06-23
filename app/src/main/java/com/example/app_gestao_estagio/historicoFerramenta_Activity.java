package com.example.app_gestao_estagio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
    private List<Requesicoes> listRequesicoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_ferramenta);

        recyclerView = findViewById(R.id.rc_Requesicoes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        listRequesicoes = new ArrayList<>();
        adapter = new MyAdapterRequesicoes(this, listRequesicoes);
        recyclerView.setAdapter(adapter);
        ShowData();
    }

    private void ShowData(){
        db.collection("Requesicoes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listRequesicoes.clear();
                for(DocumentSnapshot snapshot: task.getResult()){
                    Requesicoes requesicoes = new Requesicoes(snapshot.getString("Data_Requesicao"), snapshot.getString("Data_Devolucao"));
                    listRequesicoes.add(requesicoes);
                }
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(historicoFerramenta_Activity.this, "Alguma coisa correu mal", Toast.LENGTH_SHORT).show();
            }
        });

    }
}