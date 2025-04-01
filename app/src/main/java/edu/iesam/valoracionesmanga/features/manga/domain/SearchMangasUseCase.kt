package edu.iesam.valoracionesmanga.features.manga.domain

import org.koin.core.annotation.Single
import java.util.Locale

@Single
class SearchMangasUseCase(private val repository: MangaRepository) {

    suspend fun invoke(searchText: String): Result<List<Manga>> {
        val result = repository.get()
         result.onSuccess { mangas ->
            return Result.success(mangas.filter { it.title.uppercase(Locale.ROOT).startsWith(searchText.uppercase(Locale.ROOT)) })
        }
        return result
    }

}