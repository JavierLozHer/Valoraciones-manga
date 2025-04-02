package edu.iesam.valoracionesmanga.features.genres.domain

interface GenreRepository {
    suspend fun get(): Result<List<String>>
}