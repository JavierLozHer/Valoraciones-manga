package edu.iesam.valoracionesmanga.features.manga.data

import edu.iesam.valoracionesmanga.features.manga.data.local.MangaDbLocalDataStore
import edu.iesam.valoracionesmanga.features.manga.data.remote.MangaFirebaseRemoteDataSource
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import edu.iesam.valoracionesmanga.features.manga.domain.MangaRepository
import org.koin.core.annotation.Single

@Single
class MangaDataRepository(
    private val remoteDataSource: MangaFirebaseRemoteDataSource,
    private val localDataSource: MangaDbLocalDataStore
): MangaRepository {

    override suspend fun get(): Result<List<Manga>> {
        val localData = localDataSource.getAll()
        return if (localData.isFailure) {
            val remoteResource = remoteDataSource.fetch()
             remoteResource.onSuccess {
                localDataSource.saveAll(it)
            }
        } else {
            localData
        }
    }

}