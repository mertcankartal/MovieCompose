package com.example.moviecompose.data.repository

import com.example.moviecompose.data.remote.model.MovieDetailResponse
import com.example.moviecompose.data.remote.model.MoviesResponse

interface MovieRepository {

    suspend fun getMovies(search: String) : MoviesResponse
    suspend fun getMovieDetail(imdbId: String) : MovieDetailResponse
}