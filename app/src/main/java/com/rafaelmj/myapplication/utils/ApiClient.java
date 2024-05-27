package com.rafaelmj.myapplication.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofitViaCep = null;
    private static Retrofit retrofitMockApi = null;

    public static Retrofit getViaCepClient() {
        if (retrofitViaCep == null) {
            retrofitViaCep = new Retrofit.Builder()
                    .baseUrl("https://viacep.com.br/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitViaCep;
    }

    public static Retrofit getMockApiClient() {
        if (retrofitMockApi == null) {
            retrofitMockApi = new Retrofit.Builder()
                    .baseUrl("https://6654b5611c6af63f467908dc.mockapi.io/atv5/mobile/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitMockApi;
    }
}
