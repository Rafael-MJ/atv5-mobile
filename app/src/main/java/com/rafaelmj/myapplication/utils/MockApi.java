package com.rafaelmj.myapplication.utils;

import com.rafaelmj.myapplication.models.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MockApi {
    @GET("alunos")
    Call<List<Aluno>> getAlunos();

    @POST("alunos")
    Call<Aluno> createAluno(@Body Aluno aluno);
}
