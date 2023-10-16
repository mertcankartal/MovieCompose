package com.example.moviecompose.presentation.movie_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecompose.data.usecase.GetMoviesUseCase
import com.example.moviecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val useCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf<MoviesState>(MoviesState()) //bu burada kullanılacak
    val state : State<MoviesState> = _state //bu view tarafta kullanılacak

    private var job : Job? = null

    init {
        getMovies(_state.value.search) //burası uygulama ilk açıldığında sayfada bir film olsun diye
    }

    private fun getMovies(search:String) {
        job?.cancel()

        job = useCase.invoke(search).onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = MoviesState(errorMessage = it.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = MoviesState(isLoading = true)
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent) {
        when(event) {
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }

            else -> {}
        }
    }
}