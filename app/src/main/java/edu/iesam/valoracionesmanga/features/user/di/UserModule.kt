package edu.iesam.valoracionesmanga.features.user.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class UserModule {

    @Single
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}