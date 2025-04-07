package edu.iesam.valoracionesmanga.features.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.valoracionesmanga.features.profile.domain.GetUserLoggedUseCase
import edu.iesam.valoracionesmanga.features.profile.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProfileViewModel(private val getUserLoggedUseCase: GetUserLoggedUseCase) : ViewModel() {

    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    fun getUserLogged() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState(getUserLoggedUseCase.invoke()))
        }
    }

    data class UiState(
        val user: User? = null
    )
}