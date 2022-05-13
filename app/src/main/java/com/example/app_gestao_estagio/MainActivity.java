package com.example.app_gestao_estagio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLogin = (Button) findViewById(R.id.btnlogin);
        TextView txtEsquecerPass = (TextView) findViewById(R.id.txtEsquecerPassword), txtConta = (TextView) findViewById(R.id.txtConta);
        EditText txtusername = (EditText) findViewById(R.id.txtusername), txtpassword = (EditText) findViewById(R.id.txtpassword);


        txtConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, criarcontaActivity.class);
               startActivity(intent);
            }
        });


    }
}