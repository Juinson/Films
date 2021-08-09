package com.example.movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("films")
    Call<List<GetData>> getFilm();



}
