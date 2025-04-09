package edu.iesam.valoracionesmanga.features.manga.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.manga.domain.GetMangaByIdUseCase
import edu.iesam.valoracionesmanga.features.manga.domain.GetMangaScoreUseCase
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MangaDetailViewModel(
    private val getMangaByIdUseCase: GetMangaByIdUseCase,
    private val getMangaScoreUseCase: GetMangaScoreUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getManga(id: String) {
        _uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            val result = getMangaByIdUseCase.invoke(id)
            val resultScore = getMangaScoreUseCase.invoke(id)
            _uiState.postValue(UiState(
                    manga = result.getOrNull(),
                    score = resultScore.getOrNull(),
                    errorApp = result.exceptionOrNull() as? ErrorApp
            ))
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val manga: Manga? = null,
        val score: Double? = null,
        val errorApp: ErrorApp? = null
    )
}