package com.example.moviecompose.data.remote.model

import com.example.moviecompose.domain.model_dto.Movie
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesResponse(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Search>,
    val totalResults: String
) : Serializable

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
) : Serializable

fun MoviesResponse.toMovieList() : List<Movie> {
    return search.map {search ->
        Movie(imageUrl = search.Poster,
            title = search.Title,
            release_year = search.Year,
            imdbID = search.imdbID)
    }
}