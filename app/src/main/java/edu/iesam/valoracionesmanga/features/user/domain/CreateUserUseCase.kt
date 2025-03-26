package edu.iesam.valoracionesmanga.features.user.domain

import org.koin.core.annotation.Single

@Single
class CreateUserUseCase(private val userRepository: UserRepository) {

    suspend fun invoke(email: String, passwd: String): Result<Boolean>{
        return userRepository.createUser(email, passwd)
    }
}