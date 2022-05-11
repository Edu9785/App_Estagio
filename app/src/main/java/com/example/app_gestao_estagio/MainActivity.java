package com.example.app_gestao_estagio;

import androidx.appcompat.app.AppCompatActivity;

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
        Button btnLogin = null;
        TextView txtEsquecerPass = null, txtConta = null;
        EditText txtusername = null, txtpassword = null;

        btnLogin.findViewById(R.id.loginbtn);
        txtEsquecerPass.findViewById(R.id.txtEsquecerPassword);
        txtConta.findViewById(R.id.txtConta);
        txtusername.findViewById(R.id.txtusername);
        txtpassword.findViewById(R.id.txtpassword);

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

            }
        });


    }
}