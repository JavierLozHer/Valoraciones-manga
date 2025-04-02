package edu.iesam.valoracionesmanga.features.manga.domain

import org.koin.core.annotation.Single

@Single
class GetMangasByGenresUseCase(private val repository: MangaRepository) {

    suspend fun invoke(genres: List<String>): Result<List<Manga>> {
        val result = repository.get()
        if (genres.isEmpty()) return result

        result.onSuccess { mangas ->
            val mangasFilter = mangas.filter { manga ->
                manga.genres.any { it in genres }
            }
            return Result.success(mangasFilter)
        }
        return result
    }

}