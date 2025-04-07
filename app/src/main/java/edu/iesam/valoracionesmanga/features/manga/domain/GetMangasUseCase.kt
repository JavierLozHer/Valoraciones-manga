package edu.iesam.valoracionesmanga.features.manga.domain

import org.koin.core.annotation.Single

@Single
class GetMangasUseCase(private val repository: MangaRepository) {

    suspend fun invoke(): Result<List<Manga>> {
        return repository.get()
    }

}