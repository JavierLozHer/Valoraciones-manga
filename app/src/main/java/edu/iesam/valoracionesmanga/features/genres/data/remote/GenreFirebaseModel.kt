package edu.iesam.valoracionesmanga.features.genres.data.remote

import com.google.firebase.firestore.PropertyName

data class GenreFirebaseModel (
    @get:PropertyName("name") var name: String = ""
)