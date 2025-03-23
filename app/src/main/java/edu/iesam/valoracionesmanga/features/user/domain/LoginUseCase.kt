package edu.iesam.valoracionesmanga.features.user.domain

import org.koin.core.annotation.Single

@Single
class LoginUseCase(private val profileRepository: UserRepository) {

    suspend fun invoke(email: String, passwd: String){
        profileRepository.login(email, passwd)
    }
}