package com.spectrum.assignment.tabs

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spectrum.libraries.movie.datasource.remote.MovieApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val apiService: MovieApiService
) : ViewModel() {

    private val filterOptionStateFlow = MutableStateFlow(MovieFilterOption())

    init {
        filterOptionStateFlow
            .onEach {
                val response = apiService.getMovies("now_playing")
                Log.d("---", "$response")
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun dispatchEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.UpdateType -> filterOptionStateFlow.update { it.copy(movieType = event.movieType) }
            is ViewEvent.UpdateKeyWord -> filterOptionStateFlow.update { it.copy(keyWord = event.keyword) }
        }
    }

    data class MovieFilterOption(
        val keyWord: String? = null,
        val movieType: MovieType? = null,
    )

    sealed interface ViewEvent {
        data class UpdateKeyWord(val keyword: String) : ViewEvent
        data class UpdateType(val movieType: MovieType) : ViewEvent
    }

    @Parcelize
    enum class MovieType : Parcelable {
        NowPlaying, TopRated, Popular, Upcoming
    }
}
