package edu.iesam.valoracionesmanga.features.manga.di

import edu.iesam.valoracionesmanga.core.data.local.db.ValoracionesMangaDataBase
import edu.iesam.valoracionesmanga.features.manga.data.local.MangaDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class MangaModule {

    @Single
    fun provideMangaDao(db: ValoracionesMangaDataBase): MangaDao {
        return db.mangaDao()
    }

}