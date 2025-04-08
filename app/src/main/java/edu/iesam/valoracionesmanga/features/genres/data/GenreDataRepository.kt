package edu.iesam.valoracionesmanga.features.genres.data

import edu.iesam.valoracionesmanga.features.genres.data.local.GenreDbLocalDataSource
import edu.iesam.valoracionesmanga.features.genres.data.remote.GenreFirebaseRemoteDataSource
import edu.iesam.valoracionesmanga.features.genres.domain.GenreRepository
import org.koin.core.annotation.Single

@Single
class GenreDataRepository(
    private val remoteDataSource: GenreFirebaseRemoteDataSource,
    private val localDataSource: GenreDbLocalDataSource
): GenreRepository {

    override suspend fun get(): Result<List<String>> {
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