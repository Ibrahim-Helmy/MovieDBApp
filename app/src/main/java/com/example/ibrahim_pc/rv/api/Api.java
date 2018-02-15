package com.example.ibrahim_pc.rv.api;

import com.example.ibrahim_pc.rv.Trailerpakg.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ibrahim_pc on 2018-02-12.
 */

public interface Api {
    @GET ("3/movie/popular")
    Call<Example> getPopular( @Query("api_key") String ai_key);

    @GET("3/movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailer(@Path("movie_id")int id, @Query("api_key") String ai_key);

}
