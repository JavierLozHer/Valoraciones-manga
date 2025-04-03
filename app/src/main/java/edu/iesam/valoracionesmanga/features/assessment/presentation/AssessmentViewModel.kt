package edu.iesam.valoracionesmanga.features.assessment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment
import edu.iesam.valoracionesmanga.features.assessment.domain.GetAssessmentsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AssessmentViewModel(private val getAssessmentsUseCase: GetAssessmentsUseCase): ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getAssessment() {
        _uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            val result = getAssessmentsUseCase.invoke()
            _uiState.postValue(UiState(
                assessment = result.getOrNull(),
                errorApp = result.exceptionOrNull() as? ErrorApp
            ))
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val assessment : List<Assessment>? = null,
        val errorApp: ErrorApp? = null
    )
}