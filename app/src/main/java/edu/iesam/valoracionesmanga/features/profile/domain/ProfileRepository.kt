package edu.iesam.valoracionesmanga.features.profile.domain

interface ProfileRepository {
    suspend fun getUserLogged(): User?
    suspend fun logout()
}