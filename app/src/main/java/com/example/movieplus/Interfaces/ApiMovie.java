package com.example.movieplus.Interfaces;

import com.example.movieplus.Model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiMovie {
//Petisiones
    @GET("movies/list.php")
    Call<List<Movie>> getMovies();

    @GET("movies/mas.php")
    Call<List<Movie>> getMasMovies();
    //Video
    @GET("movies/video.php")
    Call<List<Movie>> getVideoMovies();
    //Series
    @GET("movies/series.php")
    Call<List<Movie>> getSeries();
    //Terror
    @GET("movies/terror.php")
    Call<List<Movie>> getTerror();
    //Aciion
    @GET("movies/accion.php")
    Call<List<Movie>> getAccion();

    //Search
    @GET("movies/search.php")
    Call<List<Movie>> getSearch();
}
