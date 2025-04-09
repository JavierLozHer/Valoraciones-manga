package edu.iesam.valoracionesmanga.features.assessment.domain

import org.koin.core.annotation.Single

@Single
class GetAssessmentsUseCase(private val repository: AssessmentRepository) {

    suspend fun invoke(): Result<List<Assessment>> {
        return repository.get()
    }

}