package edu.iesam.valoracionesmanga.core.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("edu.iesam.valoracionesmanga")
class AppModule {
    @Single
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}