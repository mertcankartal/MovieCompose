package com.example.moviecompose.presentation.movie_detail

import com.example.moviecompose.domain.model_dto.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val errorMessage: String = "",
)
