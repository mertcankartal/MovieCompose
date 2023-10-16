package com.example.moviecompose.data

import com.example.moviecompose.data.remote.model.MovieDetailResponse
import com.example.moviecompose.data.remote.model.MoviesResponse
import com.example.moviecompose.util.const.Const.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    //https://omdbapi.com/?apikey=ff9ae261&s=batman
    //endpoint yok baseden sonra direkt parametrelere geçiyor . koyabiliriz.
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString:String,
        @Query("apikey") apikey:String = API_KEY
    ) : MoviesResponse

    //https://omdbapi.com/?apikey=ff9ae261&i=tt0372784
    @GET(".")
    suspend fun getMovieDetail(
        @Query("apikey") apikey:String = API_KEY,
        @Query("i") imdbId:String
    ) : MovieDetailResponse

}