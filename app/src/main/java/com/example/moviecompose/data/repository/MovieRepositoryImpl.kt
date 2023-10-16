package com.example.moviecompose.data.repository

import com.example.moviecompose.data.MovieApi
import com.example.moviecompose.data.remote.datasource.MovieDataSource
import com.example.moviecompose.data.remote.model.MovieDetailResponse
import com.example.moviecompose.data.remote.model.MoviesResponse
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val dataSource: MovieDataSource): MovieRepository {
    override suspend fun getMovies(search: String): MoviesResponse {
        return dataSource.getMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailResponse {
        return dataSource.getMovieDetail(imdbId)
    }
}