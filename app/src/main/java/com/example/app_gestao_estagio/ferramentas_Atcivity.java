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

public class ferramentas_Atcivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private MyAdapter adapter;
    private List<Model> listFerramentas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferramentas_atcivity);

        recyclerView = findViewById(R.id.rc_Ferramentas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        listFerramentas = new ArrayList<>();
        adapter = new MyAdapter(this, listFerramentas);
        recyclerView.setAdapter(adapter);
        ShowData();
    }

    private void ShowData(){
        db.collection("Ferramentas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listFerramentas.clear();
                for(DocumentSnapshot snapshot: task.getResult()){
                    Model model = new Model(snapshot.getString("Nome_Ferramenta"), snapshot.getString("Quant_Ferramenta"));
                    listFerramentas.add(model);
                }
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ferramentas_Atcivity.this, "Alguma coisa correu mal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}