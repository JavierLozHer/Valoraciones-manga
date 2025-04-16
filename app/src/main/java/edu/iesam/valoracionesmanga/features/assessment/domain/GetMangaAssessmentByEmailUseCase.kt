package edu.iesam.valoracionesmanga.features.assessment.domain

import org.koin.core.annotation.Single

@Single
class GetMangaAssessmentByEmailUseCase(private val repository: AssessmentRepository) {

    suspend fun invoke(email: String, mangaId: String): Result<Assessment?> {
        val result = repository.get()
        return result.fold(
            onSuccess = { assessments ->
                Result.success(assessments.firstOrNull { it.user == email && it.mangaId == mangaId })
            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )
    }

}