package edu.iesam.valoracionesmanga.features.manga.data.local

import edu.iesam.valoracionesmanga.features.manga.domain.Manga

fun MangaEntity.toModel(): Manga {
    return Manga(
        id = this.id,
        title = this.title,
        synopsis = this.synopsis,
        img = this.img,
        startDate = this.startDate,
        finishDate = this.finishDate,
        genres = this.genres,
        staff = this.staff
    )
}

fun Manga.toEntity(): MangaEntity {
    return MangaEntity(
        id = this.id,
        title = this.title,
        synopsis = this.synopsis,
        img = this.img,
        startDate = this.startDate,
        finishDate = this.finishDate,
        genres = this.genres,
        staff = this.staff
    )
}