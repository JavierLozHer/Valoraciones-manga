package edu.iesam.valoracionesmanga.features.manga.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.valoracionesmanga.core.data.remote.firebaseCall
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class MangaFirebaseRemoteDataSource(
    private val firestore: FirebaseFirestore
) {

    suspend fun fetch(): Result<List<Manga>> {
        return firebaseCall {
            val mangasDocument = firestore.collection("mangas").get().await()
            mangasDocument.map { document ->
                document.toObject(MangaFirebaseModel::class.java).toModel(document.id)
            }
        }
    }
}