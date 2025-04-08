package edu.iesam.valoracionesmanga.features.genres.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.valoracionesmanga.core.data.remote.firebaseCall
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class GenreFirebaseRemoteDataSource (
    private val firestore: FirebaseFirestore
) {

    suspend fun fetch(): Result<List<String>> {
        return firebaseCall {
            val mangasDocument = firestore.collection("genres").get().await()
            mangasDocument.map { document ->
                document.toObject(GenreFirebaseModel::class.java).name
            }
        }
    }
}