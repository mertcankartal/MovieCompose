package com.example.moviecompose.presentation.movie_list

sealed class MoviesEvent {
    data class Search(val searchString: String) : MoviesEvent()
}
