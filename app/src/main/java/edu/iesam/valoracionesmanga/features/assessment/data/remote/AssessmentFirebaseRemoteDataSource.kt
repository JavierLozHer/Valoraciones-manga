package edu.iesam.valoracionesmanga.features.assessment.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import edu.iesam.valoracionesmanga.core.data.remote.firebaseCall
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class AssessmentFirebaseRemoteDataSource (
    private val firestore: FirebaseFirestore
) {

    suspend fun fetch(): Result<List<Assessment>> {
        return firebaseCall {
            val assessmentDocument = firestore.collection("assessment").orderBy("date", Query.Direction.DESCENDING).get().await()
            assessmentDocument.map { document ->
                document.toObject(AssessmentFirebaseModel::class.java).toModel(document.id)
            }
        }
    }

    suspend fun save(assessment: Assessment): Result<Unit> {
        return firebaseCall {
            firestore.collection("assessment").document(assessment.id).set(assessment.toFirebaseModel())
            Result.success(Unit)
        }
    }

    suspend fun create(assessment: Assessment): Result<Assessment> {
        return firebaseCall {
            val document = firestore.collection("assessment").add(assessment.toFirebaseModel()).await()
            document.get().await().toObject(AssessmentFirebaseModel::class.java)?.toModel(document.id) ?: throw ErrorApp.DataErrorApp
        }
    }
}