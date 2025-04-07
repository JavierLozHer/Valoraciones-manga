package edu.iesam.valoracionesmanga.features.manga.domain

data class Manga(
    val id: String,
    val title: String,
    val img: String,
    val startDate: String,
    val finishDate: String,
    val synopsis: String,
    val genres: List<String>,
    val staff: List<Staff>
)

data class Staff (
    val name: String,
    val function: String
)
