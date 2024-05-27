package com.rafaelmj.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.rafaelmj.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastroAluno = findViewById(R.id.btnCadastroAluno);
        Button btnListagemAlunos = findViewById(R.id.btnListagemAlunos);

        btnCadastroAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastrarAlunoActivity.class));
            }
        });

        btnListagemAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListarAlunosActivity.class));
            }
        });
    }
}