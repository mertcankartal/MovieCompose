package com.example.moviecompose.data.usecase

import com.example.moviecompose.data.remote.model.MoviesResponse
import com.example.moviecompose.data.remote.model.toMovieList
import com.example.moviecompose.data.repository.MovieRepository
import com.example.moviecompose.domain.model_dto.Movie
import com.example.moviecompose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOError
import java.lang.Exception
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(search: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)
            if (movieList.response.equals("True")) {
                emit(Resource.Success(movieList.toMovieList()))
            } else {
                emit(Resource.Error("No Movie Found"))
            }
        } catch (e: IOError) {
            emit(Resource.Error("No Internet Connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }.flowOn(Dispatchers.IO)
}
