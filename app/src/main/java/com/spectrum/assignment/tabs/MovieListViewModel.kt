package com.spectrum.assignment.tabs

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(

) : ViewModel() {

    private val filterOptionStateFlow = MutableStateFlow(MovieFilterOption())

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
