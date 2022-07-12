package com.example.app_gestao_estagio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class menuprincipalAdminsActivity extends AppCompatActivity {

    TextView txtLogout;
    Button btnFerramentas, btnVeiculos, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal_admins);

        txtLogout = (TextView) findViewById(R.id.txtLogout);
        btnFerramentas = (Button) findViewById(R.id.btnFerramentas);
        btnVeiculos = (Button) findViewById(R.id.btnVeiculos);
        btnSair = (Button) findViewById(R.id.btnSair);


        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnFerramentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuprincipalAdminsActivity.this, ferramentas_Atcivity.class);
                startActivity(intent);
            }
        });

        btnVeiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(menuprincipalAdminsActivity.this, "Conteudo em breve", Toast.LENGTH_SHORT).show();
            }
        });
    }
}