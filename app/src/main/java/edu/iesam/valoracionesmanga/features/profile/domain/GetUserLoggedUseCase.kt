package edu.iesam.valoracionesmanga.features.profile.domain

import org.koin.core.annotation.Single

@Single
class GetUserLoggedUseCase(private val profileRepository: ProfileRepository) {

    suspend fun invoke(): User?{
        return profileRepository.getUserLogged()
    }

}