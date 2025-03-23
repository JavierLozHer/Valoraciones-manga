package edu.iesam.valoracionesmanga.features.user.presentation

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

    fun createUser(email: String, passwd: String) {
        viewModelScope.launch(Dispatchers.IO) {
            createUserUseCase.invoke(email, passwd)
        }
    }

    fun login(email: String, passwd: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.invoke(email, passwd)
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val isLogged: Boolean = false,
        val error: ErrorApp? = null
    )

}