package edu.iesam.valoracionesmanga.features.user.domain

interface UserRepository {

    suspend fun createUser(email: String, passwd: String)
    suspend fun login(email: String, passwd: String)
}
