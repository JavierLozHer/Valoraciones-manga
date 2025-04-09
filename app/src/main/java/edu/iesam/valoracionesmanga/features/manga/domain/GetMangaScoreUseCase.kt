package edu.iesam.valoracionesmanga.features.manga.domain

import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentRepository
import org.koin.core.annotation.Single

@Single
class GetMangaScoreUseCase(private val repository: AssessmentRepository) {

        suspend fun invoke(mangaId: String): Result<Double> {
            val result = repository.get()
            return result.fold(
                onSuccess = { assessments ->
                    val mangaAssessments = assessments.filter { it.mangaId == mangaId }
                    if (mangaAssessments.isNotEmpty()) {
                        val average = mangaAssessments.map { it.score.toDouble() }.average()
                        Result.success(average)
                    } else {
                        Result.success(0.0)
                    }
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        }
}