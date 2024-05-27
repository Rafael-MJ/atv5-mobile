package com.rafaelmj.myapplication.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rafaelmj.myapplication.R;
import com.rafaelmj.myapplication.models.Aluno;
import com.rafaelmj.myapplication.models.Endereco;
import com.rafaelmj.myapplication.utils.ApiClient;
import com.rafaelmj.myapplication.utils.MockApi;
import com.rafaelmj.myapplication.utils.ViaCepApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastrarAlunoActivity extends AppCompatActivity {

    private EditText etRa, etNome, etCep, etLogradouro, etComplemento, etBairro, etCidade, etUf;
    private Button btnSalvar;
    private ViaCepApi viaCepApi;
    private MockApi mockApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_aluno);

        etRa = findViewById(R.id.etRa);
        etNome = findViewById(R.id.etNome);
        etCep = findViewById(R.id.etCep);
        etLogradouro = findViewById(R.id.etLogradouro);
        etComplemento = findViewById(R.id.etComplemento);
        etBairro = findViewById(R.id.etBairro);
        etCidade = findViewById(R.id.etCidade);
        etUf = findViewById(R.id.etUf);
        btnSalvar = findViewById(R.id.btnSalvar);

        Retrofit retrofitViaCep = ApiClient.getViaCepClient();
        viaCepApi = retrofitViaCep.create(ViaCepApi.class);

        Retrofit retrofitMockApi = ApiClient.getMockApiClient();
        mockApi = retrofitMockApi.create(MockApi.class);

        etCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String cep = s.toString();
                if (cep.length() == 8) {
                    getEndereco(cep);
                }
            }
        });

        btnSalvar.setOnClickListener(v -> {
            if (validateFields()) {
                Aluno aluno = new Aluno();
                aluno.setRa(Integer.parseInt(etRa.getText().toString()));
                aluno.setNome(etNome.getText().toString());
                aluno.setCep(etCep.getText().toString());
                aluno.setLogradouro(etLogradouro.getText().toString());
                aluno.setComplemento(etComplemento.getText().toString());
                aluno.setBairro(etBairro.getText().toString());
                aluno.setCidade(etCidade.getText().toString());
                aluno.setUf(etUf.getText().toString());
                saveAluno(aluno);
            } else {
                Toast.makeText(CadastrarAlunoActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getEndereco(String cep) {
        viaCepApi.getEndereco(cep).enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Endereco endereco = response.body();
                    etLogradouro.setText(endereco.getLogradouro());
                    etComplemento.setText(endereco.getComplemento());
                    etBairro.setText(endereco.getBairro());
                    etCidade.setText(endereco.getLocalidade());
                    etUf.setText(endereco.getUf());
                } else {
                    Log.e("ViaCep", "Erro ao buscar endereço");
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Log.e("ViaCep", "Erro ao buscar endereço: " + t.getMessage());
            }
        });
    }

    private void saveAluno(Aluno aluno) {
        mockApi.createAluno(aluno).enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(CadastrarAlunoActivity.this, "Aluno salvo com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("MockApi", "Erro ao salvar aluno: " + response.message());
                    Toast.makeText(CadastrarAlunoActivity.this, "Erro ao salvar aluno", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Log.e("MockApi", "Erro ao salvar aluno: " + t.getMessage());
                Toast.makeText(CadastrarAlunoActivity.this, "Erro ao salvar aluno", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateFields() {
        return !etRa.getText().toString().isEmpty() &&
                !etNome.getText().toString().isEmpty() &&
                !etCep.getText().toString().isEmpty() &&
                !etLogradouro.getText().toString().isEmpty() &&
                !etBairro.getText().toString().isEmpty() &&
                !etCidade.getText().toString().isEmpty() &&
                !etUf.getText().toString().isEmpty();
    }
}