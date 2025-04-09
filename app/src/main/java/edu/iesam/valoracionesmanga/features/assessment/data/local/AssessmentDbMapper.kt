package edu.iesam.valoracionesmanga.features.assessment.data.local

import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment

fun AssessmentEntity.toModel(): Assessment {
    return Assessment(
        id = this.id,
        comment = this.comment,
        mangaId = this.mangaId,
        score = this.score,
        user = this.user
    )
}

fun Assessment.toEntity(): AssessmentEntity {
    return AssessmentEntity(
        id = this.id,
        comment = this.comment,
        mangaId = this.mangaId,
        score = this.score,
        user = this.user
    )
}