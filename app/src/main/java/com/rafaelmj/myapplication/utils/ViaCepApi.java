package com.rafaelmj.myapplication.utils;

import com.rafaelmj.myapplication.models.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepApi {
    @GET("ws/{cep}/json/")
    Call<Endereco> getEndereco(@Path("cep") String cep);
}
