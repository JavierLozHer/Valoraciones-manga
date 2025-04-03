package edu.iesam.valoracionesmanga.features.assessment.data.remote

import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment

fun AssessmentFirebaseModel.toModel(id: String): Assessment {
    return Assessment(
        id = id,
        comment = this.comment,
        mangaId = this.mangaId,
        score = this.score,
        user = this.user
    )
}