package edu.iesam.valoracionesmanga.core.data.remote

import com.google.firebase.firestore.FirebaseFirestoreException
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import kotlinx.coroutines.TimeoutCancellationException
import java.net.SocketTimeoutException

suspend fun <T : Any> firebaseCall(call: suspend () -> T): Result<T> {
    return try {
        val result = call.invoke()
        Result.success(result)
    } catch (exception: Throwable) {
        return when(exception) {
            is TimeoutCancellationException -> Result.failure(ErrorApp.InternetErrorApp)
            is FirebaseFirestoreException -> Result.failure(ErrorApp.InternetErrorApp)
            is SocketTimeoutException -> Result.failure(ErrorApp.InternetErrorApp)
            else -> Result.failure(ErrorApp.UnknownErrorApp)
        }
    }
}