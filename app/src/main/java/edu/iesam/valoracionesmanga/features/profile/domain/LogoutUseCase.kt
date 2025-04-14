package edu.iesam.valoracionesmanga.features.profile.domain

import org.koin.core.annotation.Single

@Single
class LogoutUseCase(private val profileRepository: ProfileRepository) {

    suspend fun invoke() {
        profileRepository.logout()
    }

}