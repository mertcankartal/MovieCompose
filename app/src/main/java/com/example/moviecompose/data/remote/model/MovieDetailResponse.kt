package com.example.moviecompose.data.remote.model

import com.example.moviecompose.domain.model_dto.MovieDetail
import java.io.Serializable

data class MovieDetailResponse(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
) : Serializable

data class Rating(
    val Source: String,
    val Value: String
) : Serializable

fun MovieDetailResponse.toMovieDetail(): MovieDetail {
    return MovieDetail(
        Actors = Actors,
        Awards = Awards,
        Country = Country,
        Director,
        Genre,
        Language,
        Poster,
        Rated,
        Released,
        Title,
        Type,
        Year,
        imdbRating
    )
}