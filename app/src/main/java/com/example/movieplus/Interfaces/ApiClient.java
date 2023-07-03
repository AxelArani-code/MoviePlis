package com.example.movieplus.Interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    public   static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.7:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
