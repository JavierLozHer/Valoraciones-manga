package edu.iesam.valoracionesmanga.core.di

import android.content.Context
import androidx.room.Room
import edu.iesam.valoracionesmanga.core.data.local.db.ValoracionesMangaDataBase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class LocalModule {

    @Single
    fun provideDatabase(context: Context): ValoracionesMangaDataBase {
        val db = Room.databaseBuilder(
            context,
            ValoracionesMangaDataBase::class.java,
            "valoraciones-manga-db"
        )
        db.fallbackToDestructiveMigration()
        return db.build()
    }

}