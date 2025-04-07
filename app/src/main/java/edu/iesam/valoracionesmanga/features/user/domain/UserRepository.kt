package edu.iesam.valoracionesmanga.features.user.domain

interface UserRepository {

    suspend fun createUser(email: String, passwd: String): Result<Boolean>
    suspend fun login(email: String, passwd: String): Result<Boolean>
}
