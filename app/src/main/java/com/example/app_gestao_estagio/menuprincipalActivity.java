package com.example.app_gestao_estagio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class menuprincipalActivity extends AppCompatActivity {

    TextView txtUser, txtLogout;
    Button btnFerramentas, btnVeiculos, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        txtUser = (TextView) findViewById(R.id.txtnomeUser);
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

            }
        });

        btnVeiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}