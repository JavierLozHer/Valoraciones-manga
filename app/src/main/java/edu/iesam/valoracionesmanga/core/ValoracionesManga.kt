package edu.iesam.valoracionesmanga.core

import android.app.Application
import edu.iesam.valoracionesmanga.core.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class ValoracionesManga : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ValoracionesManga)
            modules(AppModule().module)
        }
    }
}