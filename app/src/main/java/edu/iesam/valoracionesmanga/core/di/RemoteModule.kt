package edu.iesam.valoracionesmanga.core.di

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class RemoteModule {

    @Single
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}