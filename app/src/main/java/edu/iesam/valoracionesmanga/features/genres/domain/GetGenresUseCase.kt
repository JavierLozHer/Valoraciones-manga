package edu.iesam.valoracionesmanga.features.genres.domain

import org.koin.core.annotation.Single

@Single
class GetGenresUseCase(private val repository: GenreRepository) {

    suspend fun invoke(): Result<List<String>> {
        return repository.get()
    }
}