package edu.iesam.valoracionesmanga.features.manga.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.valoracionesmanga.core.data.remote.firebaseCall
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
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

    suspend fun fetchById(id: String) : Result<Manga> {
        return firebaseCall {
            val mangaDocument = firestore.collection("mangas").document(id).get().await()
            mangaDocument.toObject(MangaFirebaseModel::class.java)?.toModel(mangaDocument.id) ?: throw ErrorApp.DataErrorApp
        }
    }
}