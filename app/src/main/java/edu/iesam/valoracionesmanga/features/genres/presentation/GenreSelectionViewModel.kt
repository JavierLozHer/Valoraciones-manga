package edu.iesam.valoracionesmanga.features.genres.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.genres.domain.GetGenresUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GenreSelectionViewModel(private val getGenresUseCase: GetGenresUseCase): ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getGenres() {
        _uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            val result = getGenresUseCase.invoke()
            _uiState.postValue(UiState(
                genres = result.getOrNull()?.map { Genre(it) },
                errorApp = result.exceptionOrNull() as? ErrorApp
            ))
        }
    }

    data class Genre(
        val name: String,
        var isSelected: Boolean = false
    )


    data class UiState(
        val isLoading: Boolean = false,
        val genres: List<Genre>? = null,
        val errorApp: ErrorApp? = null
    )
}