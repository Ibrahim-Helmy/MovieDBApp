package com.example.ibrahim_pc.rv.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ibrahim_pc on 2018-02-12.
 */

public class RetrofitClient {

    public static Retrofit getRetrovitMovie() {

        return new Retrofit.Builder()

                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
