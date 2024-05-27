package com.rafaelmj.myapplication.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rafaelmj.myapplication.R;
import com.rafaelmj.myapplication.models.Aluno;
import com.rafaelmj.myapplication.adapters.AlunoAdapter;
import com.rafaelmj.myapplication.utils.ApiClient;
import com.rafaelmj.myapplication.utils.MockApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListarAlunosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlunos;
    private AlunoAdapter alunoAdapter;
    private MockApi mockApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_alunos);

        recyclerViewAlunos = findViewById(R.id.recyclerViewAlunos);
        recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofitMockApi = ApiClient.getMockApiClient();
        mockApi = retrofitMockApi.create(MockApi.class);

        loadAlunos();
    }

    private void loadAlunos() {
        mockApi.getAlunos().enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                if (response.isSuccessful()) {
                    List<Aluno> alunos = response.body();
                    alunoAdapter = new AlunoAdapter(alunos);
                    recyclerViewAlunos.setAdapter(alunoAdapter);
                } else {
                    Toast.makeText(ListarAlunosActivity.this, "Erro ao carregar alunos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Toast.makeText(ListarAlunosActivity.this, "Erro ao carregar alunos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}