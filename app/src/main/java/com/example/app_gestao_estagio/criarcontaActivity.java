package com.example.app_gestao_estagio;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class criarcontaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.filled_exposed);
        ArrayList<String> listatipo = new ArrayList<>();
        listatipo.add("Admin");
        listatipo.add("Trabalhador");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.drop_down_item, listatipo);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(criarcontaActivity.this, autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}