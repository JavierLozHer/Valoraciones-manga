package edu.iesam.valoracionesmanga.features.assessment.domain

interface AssessmentRepository {
    suspend fun get(): Result<List<Assessment>>
}