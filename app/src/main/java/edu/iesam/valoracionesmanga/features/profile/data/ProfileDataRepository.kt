package edu.iesam.valoracionesmanga.features.profile.data

import com.google.firebase.auth.FirebaseAuth
import edu.iesam.valoracionesmanga.features.profile.domain.ProfileRepository
import edu.iesam.valoracionesmanga.features.profile.domain.User
import org.koin.core.annotation.Single

@Single
class ProfileDataRepository(private val firebaseAuth: FirebaseAuth): ProfileRepository {

    override suspend fun getUserLogged(): User? {
        return firebaseAuth.currentUser?.toModel()
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

}