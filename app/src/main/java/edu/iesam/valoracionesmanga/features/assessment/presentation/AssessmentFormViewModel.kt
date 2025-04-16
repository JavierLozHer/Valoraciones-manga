package edu.iesam.valoracionesmanga.features.assessment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment
import edu.iesam.valoracionesmanga.features.assessment.domain.GetMangaAssessmentByEmailUseCase
import edu.iesam.valoracionesmanga.features.assessment.domain.SaveAssessmentUseCase
import edu.iesam.valoracionesmanga.features.profile.domain.GetUserLoggedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AssessmentFormViewModel(
    private val getUserLoggedUseCase: GetUserLoggedUseCase,
    private val getMangaAssessmentByEmailUseCase: GetMangaAssessmentByEmailUseCase,
    private val saveAssessmentUseCase: SaveAssessmentUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getAssessment(mangaId: String) {
        UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getUserLoggedUseCase.invoke()?.let { user ->
                val result = getMangaAssessmentByEmailUseCase.invoke(user.email, mangaId)
                _uiState.postValue(
                    UiState(
                        assessment = result.getOrNull(),
                        errorApp = result.exceptionOrNull() as? ErrorApp
                    )
                )
            } ?: run {
                UiState(
                    errorApp = ErrorApp.UserNotLoggedErrorApp
                )
            }
        }
    }

    fun save(score: Int, comment: String, mangaId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserLoggedUseCase.invoke()

            user?.let {
                val result = saveAssessmentUseCase.invoke(SaveAssessmentUseCase.Input(comment, score, user.email, mangaId))

                _uiState.postValue(
                    UiState(
                        isSaved = result.isSuccess,
                        errorApp = result.exceptionOrNull() as? ErrorApp
                    )
                )

            } ?: run {
                _uiState.postValue(
                    UiState(
                        errorApp = ErrorApp.UserNotLoggedErrorApp
                    )
                )
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val isSaved: Boolean = false,
        val assessment : Assessment? = null,
        val errorApp: ErrorApp? = null
    )
}