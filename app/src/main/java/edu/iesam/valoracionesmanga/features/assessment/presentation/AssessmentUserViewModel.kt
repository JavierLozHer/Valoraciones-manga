package edu.iesam.valoracionesmanga.features.assessment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentManga
import edu.iesam.valoracionesmanga.features.assessment.domain.GetAssessmentByUserEmailUseCase
import edu.iesam.valoracionesmanga.features.assessment.domain.GetAssessmentMangaUseCase
import edu.iesam.valoracionesmanga.features.assessment.domain.GetAssessmentsUseCase
import edu.iesam.valoracionesmanga.features.manga.domain.GetMangasUseCase
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AssessmentUserViewModel(
    private val getAssessmentByUserEmailUseCase: GetAssessmentByUserEmailUseCase,
    private val getMangasUseCase: GetMangasUseCase,
    private val getAssessmentMangaUseCase: GetAssessmentMangaUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getAssessment(email: String) {
        _uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            val result = getAssessmentByUserEmailUseCase.invoke(email)
            val resultMangas = getMangasUseCase.invoke()

            val assessments = result.getOrNull()
            val mangas = resultMangas.getOrNull()

            assessments?.let {
                mangas?.let {
                    _uiState.postValue(UiState(
                        assessment = getAssessmentMangaUseCase.invoke(assessments, mangas)
                    ))
                } ?: run {
                    _uiState.postValue(UiState(
                        errorApp = resultMangas.exceptionOrNull() as? ErrorApp
                    ))
                }
            } ?: run {
                _uiState.postValue(UiState(
                    errorApp = result.exceptionOrNull() as? ErrorApp
                ))
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val assessment : List<AssessmentManga>? = null,
        val errorApp: ErrorApp? = null
    )
}