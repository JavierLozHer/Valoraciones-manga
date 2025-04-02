package edu.iesam.valoracionesmanga.features.genres.di

import edu.iesam.valoracionesmanga.core.data.local.db.ValoracionesMangaDataBase
import edu.iesam.valoracionesmanga.features.genres.data.local.GenreDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class GenreModule {

    @Single
    fun provideGenreDao(db: ValoracionesMangaDataBase): GenreDao {
        return db.genreDao()
    }
}