package edu.iesam.valoracionesmanga.features.profile.data

import com.google.firebase.auth.FirebaseUser
import edu.iesam.valoracionesmanga.features.profile.domain.User

fun FirebaseUser.toModel(): User? {
    return this.email?.let { User(this.uid, it) }
}