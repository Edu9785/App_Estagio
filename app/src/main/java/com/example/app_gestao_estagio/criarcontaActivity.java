package com.example.app_gestao_estagio;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Spinner;

public class criarcontaActivity extends AppCompatActivity {

    private Spinner spinner_Tipo;
    private TipoAdapter tipoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criarconta);
        spinner_Tipo = findViewById(R.id.spinner_Tipo);
        tipoAdapter = new TipoAdapter(criarcontaActivity.this, valores.getTipoList());
        spinner_Tipo.setAdapter(tipoAdapter);
    }
}