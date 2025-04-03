package edu.iesam.valoracionesmanga.features.assessment.domain

data class Assessment(
    val id: String,
    val comment: String,
    val mangaId: String,
    val score: Number,
    val user: String
)