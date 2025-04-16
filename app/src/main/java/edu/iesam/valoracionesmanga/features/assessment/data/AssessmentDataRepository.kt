package edu.iesam.valoracionesmanga.features.assessment.data

import edu.iesam.valoracionesmanga.features.assessment.data.local.AssessmentDbLocalDataSource
import edu.iesam.valoracionesmanga.features.assessment.data.remote.AssessmentFirebaseRemoteDataSource
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentRepository
import org.koin.core.annotation.Single

@Single
class AssessmentDataRepository(
    private val remoteDataSource: AssessmentFirebaseRemoteDataSource,
    private val localDataSource: AssessmentDbLocalDataSource
): AssessmentRepository {

    override suspend fun get(): Result<List<Assessment>> {
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

    override suspend fun save(assessment: Assessment): Result<Unit> {
        return remoteDataSource.save(assessment).onSuccess {
            localDataSource.save(assessment)
        }
    }

    override suspend fun create(assessment: Assessment): Result<Unit> {
        return remoteDataSource.create(assessment).fold(
            onSuccess = {
                localDataSource.save(it)
                Result.success(Unit)
            },

            onFailure = {
                Result.failure(it)
            }
        )

    }

}