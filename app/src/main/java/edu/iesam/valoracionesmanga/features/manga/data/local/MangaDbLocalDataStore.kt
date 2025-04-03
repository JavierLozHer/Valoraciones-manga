package edu.iesam.valoracionesmanga.features.manga.data.local

import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import org.koin.core.annotation.Single

@Single
class MangaDbLocalDataStore(private val mangaDao: MangaDao) {

    private val expireTime : Long = 14400000

    suspend fun saveAll(mangas: List<Manga>) {
        val mangasEntity = mangas.map { it.toEntity() }

        mangaDao.saveAll(*mangasEntity.toTypedArray())
    }

    suspend fun getAll(): Result<List<Manga>> {
        val mangasEntity = mangaDao.findAll()

        if(mangasEntity.isEmpty()) {
            return Result.failure(ErrorApp.DataErrorApp)
        }
        return if (System.currentTimeMillis() - mangasEntity[0].savedAt < expireTime) {
            Result.success(mangasEntity.map { it.toModel() })
        } else {
            Result.failure(ErrorApp.DataErrorApp)
        }
    }

    suspend fun save(manga: Manga) {
        val mangaEntity = manga.toEntity()

        mangaDao.save(mangaEntity)
    }

    suspend fun getById(id: String): Result<Manga> {
        val mangaEntity = mangaDao.findById(id) ?: return Result.failure(ErrorApp.DataErrorApp)

        return if (System.currentTimeMillis() - mangaEntity.savedAt < expireTime) {
            Result.success(mangaEntity.toModel() )
        } else {
            Result.failure(ErrorApp.DataErrorApp)
        }
    }

}