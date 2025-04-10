package edu.iesam.valoracionesmanga.features.assessment.domain

import edu.iesam.valoracionesmanga.features.manga.domain.Manga

data class Assessment(
    val id: String,
    val comment: String,
    val mangaId: String,
    val score: Number,
    val user: String
)

data class AssessmentManga(
    val assessment: Assessment,
    val manga: Manga?
)