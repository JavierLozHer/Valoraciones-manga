package edu.iesam.valoracionesmanga.features.assessment.domain

import org.koin.core.annotation.Single

@Single
class SaveAssessmentUseCase(private val repository: AssessmentRepository) {

    suspend fun invoke(input: Input): Result<Unit> {
        val result = repository.get()
        return result.fold(
            onSuccess = { assessments ->
                val assessmentReplace = assessments.firstOrNull { it.user == input.email && it.mangaId == input.mangaId }

                val assessment = Assessment(
                    id = assessmentReplace?.id ?: "",
                    comment = input.comment,
                    mangaId = input.mangaId,
                    score = input.score,
                    user = input.email
                )
                if (assessmentReplace == null) {
                    repository.create(assessment)
                } else {
                    repository.save(assessment)
                }

            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )

    }

    data class Input(
        val comment: String,
        val score: Number,
        val email: String,
        val mangaId: String
    )
}