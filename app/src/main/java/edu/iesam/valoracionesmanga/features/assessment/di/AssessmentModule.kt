package edu.iesam.valoracionesmanga.features.assessment.di

import edu.iesam.valoracionesmanga.core.data.local.db.ValoracionesMangaDataBase
import edu.iesam.valoracionesmanga.features.assessment.data.local.AssessmentDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class AssessmentModule {

    @Single
    fun provideAssessmentDao(db: ValoracionesMangaDataBase): AssessmentDao {
        return db.assessmentDao()
    }

}