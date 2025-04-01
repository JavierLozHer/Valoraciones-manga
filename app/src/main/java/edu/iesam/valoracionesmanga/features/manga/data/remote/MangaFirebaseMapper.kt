package edu.iesam.valoracionesmanga.features.manga.data.remote

import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import edu.iesam.valoracionesmanga.features.manga.domain.Staff

fun MangaFirebaseModel.toModel(id: String): Manga {
    return Manga(
        id = id,
        title = this.title,
        synopsis = this.synopsis,
        img = this.img,
        startDate = this.startDate,
        finishDate = this.finishDate,
        genres = this.genres,
        staff = listToListStaff(this.staff)
    )

}

fun listToListStaff(staffList: List<Map<String, String>>): List<Staff> {
    return staffList.map {
        mapToStaff(it)
    }
}

fun mapToStaff(staffMap: Map<String, String>): Staff {
    val name: String = staffMap["name"] ?: ""
    val function: String = staffMap["function"] ?: ""
    return Staff(name, function)
}