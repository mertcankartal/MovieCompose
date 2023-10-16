package com.example.moviecompose.data.usecase

import com.example.moviecompose.data.remote.model.toMovieDetail
import com.example.moviecompose.data.repository.MovieRepository
import com.example.moviecompose.domain.model_dto.MovieDetail
import com.example.moviecompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getMovieDetail(imdbId = imdbId)
            emit(Resource.Success(movie.toMovieDetail()))
        } catch (e: IOError) {
            emit(Resource.Error("No Internet Connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }

}