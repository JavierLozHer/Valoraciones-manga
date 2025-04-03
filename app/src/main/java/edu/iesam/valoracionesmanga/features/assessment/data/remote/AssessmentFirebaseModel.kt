package edu.iesam.valoracionesmanga.features.assessment.data.remote

import com.google.firebase.firestore.PropertyName

class AssessmentFirebaseModel (
    @get:PropertyName("comment") var comment: String = "",
    @get:PropertyName("mangaId") var mangaId: String = "",
    @get:PropertyName("score") var score: Int = 0,
    @get:PropertyName("user") var user: String = "",
)