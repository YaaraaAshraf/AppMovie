package com.example.appmovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface Service {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<UpcomingResponse> getUpcomingMovies(@Query("api_key") String apiKey);
}
