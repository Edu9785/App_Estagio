package com.example.app_gestao_estagio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class menuprincipalActivity extends AppCompatActivity {

    TextView txtLogout;
    Button btnFerramentas, btnVeiculos, btnSair;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menuprincipal);

        txtLogout = (TextView) findViewById(R.id.txtLogout);
        btnFerramentas = (Button) findViewById(R.id.btnFerramentas);
        btnVeiculos = (Button) findViewById(R.id.btnVeiculos);
        btnSair = (Button) findViewById(R.id.btnSair);
        db = FirebaseFirestore.getInstance();


        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnFerramentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuprincipalActivity.this, ferramentas_Atcivity.class);
                startActivity(intent);
            }
        });

        btnVeiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}