package edu.iesam.valoracionesmanga.features.user.data.remote

import org.koin.core.annotation.Single
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import kotlinx.coroutines.tasks.await

@Single
class UserFirebaseRemoteDataSource(private val firebaseAuth: FirebaseAuth) {

    suspend fun createUser(email: String, passwd: String): Result<Boolean> {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, passwd)

        try {
            result.await()
        } catch (_: FirebaseAuthUserCollisionException) {
            return Result.failure(ErrorApp.DataErrorApp)
        }
        return Result.success(true)


    }

    suspend fun login(email: String, passwd: String): Result<Boolean> {
        val result = firebaseAuth.signInWithEmailAndPassword(email, passwd)

        try {
            result.await()
        } catch (_: FirebaseAuthInvalidCredentialsException) {
            return Result.failure(ErrorApp.DataErrorApp)
        }
        return Result.success(true)

    }

}