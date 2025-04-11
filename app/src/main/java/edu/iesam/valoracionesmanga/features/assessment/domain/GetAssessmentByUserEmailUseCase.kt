package edu.iesam.valoracionesmanga.features.assessment.domain

import org.koin.core.annotation.Single

@Single
class GetAssessmentByUserEmailUseCase(private val repository: AssessmentRepository) {

    suspend fun invoke(email: String): Result<List<Assessment>> {
        return repository.get().onSuccess { assessments ->
            return Result.success(assessments.filter { it.user == email })
        }
    }

}