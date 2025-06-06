package edu.iesam.valoracionesmanga.features.manga.data.remote

import com.google.firebase.firestore.PropertyName

data class MangaFirebaseModel (
    @get:PropertyName("title") var title: String = "",
    @get:PropertyName("synopsis") var synopsis: String = "",
    @get:PropertyName("img") var img: String = "",
    @get:PropertyName("startDate") var startDate: String = "",
    @get:PropertyName("finishDate") var finishDate: String = "",
    @get:PropertyName("genres") var genres: List<String> = emptyList(),
    @get:PropertyName("staff") var staff: List<Map<String, String>> = emptyList()
)

