package edu.iesam.valoracionesmanga.features.user.data.remote

import org.koin.core.annotation.Single
import com.google.firebase.auth.FirebaseAuth
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import kotlinx.coroutines.tasks.await

@Single
class UserFirebaseRemoteDataSource(private val firebaseAuth: FirebaseAuth) {

    suspend fun createUser(email: String, passwd: String): Result<Boolean> {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, passwd)

        result.await()
        if (result.isSuccessful) {
            return Result.success(true)
        } else {
            val error = when (result.exception?.message) {
                "The email address is already in use by another account." -> ErrorApp.DataErrorApp
                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> ErrorApp.InternetErrorApp
                else -> ErrorApp.UnknownErrorApp
            }
            return Result.failure(error)
        }

    }

    suspend fun login(email: String, passwd: String): Result<Boolean> {
        val result = firebaseAuth.signInWithEmailAndPassword(email, passwd)

        result.await()
        if (result.isSuccessful) {
            return Result.success(true)
        } else {
            val error = when (result.exception?.message) {
                "The email address is badly formatted." -> ErrorApp.DataErrorApp
                "There is no user record corresponding to this identifier. The user may have been deleted." -> ErrorApp.DataErrorApp
                "The password is invalid or the user does not have a password." -> ErrorApp.DataErrorApp
                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> ErrorApp.InternetErrorApp
                else -> ErrorApp.UnknownErrorApp
            }
            return Result.failure(error)
        }

    }

}