package com.example.moviecompose.data.remote.datasource

import com.example.moviecompose.data.MovieApi
import com.example.moviecompose.data.remote.model.MovieDetailResponse
import com.example.moviecompose.data.remote.model.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val api: MovieApi
) {

    suspend fun getMovies(search: String): MoviesResponse = withContext(Dispatchers.IO) {
        api.getMovies(search)
    }


    suspend fun getMovieDetail(imdbId: String): MovieDetailResponse = withContext(Dispatchers.IO) {
        api.getMovieDetail(imdbId = imdbId)
    }
}