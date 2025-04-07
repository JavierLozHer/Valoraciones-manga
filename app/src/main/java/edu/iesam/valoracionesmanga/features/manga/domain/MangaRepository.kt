package edu.iesam.valoracionesmanga.features.manga.domain

interface MangaRepository {
    suspend fun get(): Result<List<Manga>>
}