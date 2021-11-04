package com.example.tre;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.Retrofit.*;

public class ApiClient {
    public static String BASE_URL ="https://librivox.org/api/feed/";
    public static ApiInterface getClient(){


    Retrofit retrofit = new Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
    return service;

    }

    }

