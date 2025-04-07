package edu.iesam.valoracionesmanga.features.assessment.domain

import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import org.koin.core.annotation.Single

@Single
class GetAssessmentMangaUseCase {

    fun invoke(assessments: List<Assessment>, mangas: List<Manga>): List<AssessmentManga> {
        return assessments.map { assessment ->
            AssessmentManga(assessment, mangas.firstOrNull{ it.id == assessment.mangaId })
        }
    }

    data class AssessmentManga(
        val assessment: Assessment,
        val manga: Manga?
    )

}