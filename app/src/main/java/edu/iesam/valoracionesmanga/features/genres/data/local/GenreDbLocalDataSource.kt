package edu.iesam.valoracionesmanga.features.genres.data.local

import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import org.koin.core.annotation.Single

@Single
class GenreDbLocalDataSource(private val genreDao: GenreDao) {

    private val expireTime : Long = 14400000

    suspend fun saveAll(genres: List<String>) {
        val genresEntity = genres.map { GenreEntity(it) }
        genreDao.saveAll(*genresEntity.toTypedArray())
    }

    suspend fun getAll(): Result<List<String>> {
        val genresEntity = genreDao.findAll()

        if(genresEntity.isEmpty()) {
            return Result.failure(ErrorApp.DataErrorApp)
        }
        return if (System.currentTimeMillis() - genresEntity[0].savedAt < expireTime) {
            Result.success(genresEntity.map { it.name })
        } else {
            Result.failure(ErrorApp.DataErrorApp)
        }
    }
}