package com.abi.globalmovie.service;

import com.abi.globalmovie.data.remote.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<MovieResponse> getMovie(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<MovieResponse> getTvShow(@Query("api_key") String apiKey);
}
