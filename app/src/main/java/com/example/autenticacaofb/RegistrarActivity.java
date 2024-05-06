package com.example.autenticacaofb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.nio.file.Files;

public class RegistrarActivity extends AppCompatActivity {

    EditText txtEmail, txtSenha;
    Button buttonSalvar;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtEmail = (EditText) findViewById(R.id.editEmail);
        txtSenha = (EditText) findViewById(R.id.editSenha);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        mAuth = FirebaseAuth.getInstance();
        buttonSalvar.setOnClickListener(v -> inserirUsuario());

    }
    public void inserirUsuario(){
        if (TextUtils.isEmpty(txtEmail.getText())){
            Toast.makeText(getApplicationContext(),
                    "Email obrigatório", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(txtSenha.getText())){
            Toast.makeText(getApplicationContext(),
                    "Senha obrigatória", Toast.LENGTH_LONG).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(
                txtEmail.getText().toString(),txtSenha.getText().toString())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Salvo com sucesso !", Toast.LENGTH_SHORT).show();
                        Intent i =
                                new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Erro !!!", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                });
    }


}