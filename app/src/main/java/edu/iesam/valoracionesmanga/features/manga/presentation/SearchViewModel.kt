package edu.iesam.valoracionesmanga.features.manga.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.manga.domain.GetMangasUseCase
import edu.iesam.valoracionesmanga.features.manga.domain.SearchMangasUseCase
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    private val searchMangasUseCase: SearchMangasUseCase,
    private val getMangasUseCase: GetMangasUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getMangas() {
        _uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            val result = getMangasUseCase.invoke()
            _uiState.postValue(UiState(
                mangas = result.getOrNull(),
                errorApp = result.exceptionOrNull() as? ErrorApp
            ))
        }
    }

    fun searchMangas(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = searchMangasUseCase.invoke(searchText)
            _uiState.postValue(UiState(
                mangas = result.getOrNull(),
                errorApp = result.exceptionOrNull() as? ErrorApp
            ))
        }
    }


    data class UiState(
        val isLoading: Boolean = false,
        val mangas: List<Manga>? = null,
        val errorApp: ErrorApp? = null
    )

}