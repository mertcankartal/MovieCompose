package com.example.moviecompose.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecompose.data.usecase.GetMovieDetailUseCase
import com.example.moviecompose.util.Resource
import com.example.moviecompose.util.const.Const.IMDB_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: GetMovieDetailUseCase,
    private val stateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    fun getMovieDetail(imdbId: String) {
        useCase.invoke(imdbId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = it.data)
                }

                is Resource.Error -> {
                    _state.value =
                        MovieDetailState(errorMessage = it.message ?: "Movie can not loaded")
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }
}