package edu.iesam.valoracionesmanga.features.manga.domain

import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentRepository
import org.koin.core.annotation.Single

@Single
class GetMangaAssessmentUseCase(private val repository: AssessmentRepository) {

    suspend fun invoke(mangaId: String): Result<List<Assessment>> {
        val result = repository.get()
        return result.onSuccess { assessment ->
            return Result.success(assessment.filter {
                it.mangaId == mangaId })
        }
    }

}