package edu.iesam.valoracionesmanga.features.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.user.domain.CreateUserUseCase
import edu.iesam.valoracionesmanga.features.user.domain.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserFormViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    fun createUser(email: String, passwd: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = createUserUseCase.invoke(email, passwd)
            _uiState.postValue(UiState(
                isLogged = result.isSuccess,
                error = result.exceptionOrNull() as? ErrorApp
            ))
        }
    }

    fun login(email: String, passwd: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginUseCase.invoke(email, passwd)
            _uiState.postValue(UiState(
                isLogged = result.isSuccess,
                error = result.exceptionOrNull() as? ErrorApp
            ))
        }
    }

    data class UiState(
        val isLogged: Boolean = false,
        val error: ErrorApp? = null
    )

}