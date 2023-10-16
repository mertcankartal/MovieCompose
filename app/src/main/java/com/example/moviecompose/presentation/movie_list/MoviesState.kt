package com.example.moviecompose.presentation.movie_list

import com.example.moviecompose.domain.model_dto.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String = "",
    val search: String = "godfather"
)
