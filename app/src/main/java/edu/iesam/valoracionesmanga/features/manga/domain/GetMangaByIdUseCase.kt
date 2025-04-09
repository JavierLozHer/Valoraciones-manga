package edu.iesam.valoracionesmanga.features.manga.domain

import org.koin.core.annotation.Single

@Single
class GetMangaByIdUseCase(private val repository: MangaRepository) {

    suspend fun invoke(id: String): Result<Manga> {
        return repository.getById(id)
    }
}