package edu.iesam.valoracionesmanga.core

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.initialize
import edu.iesam.valoracionesmanga.BuildConfig
import edu.iesam.valoracionesmanga.core.di.AppModule
import edu.iesam.valoracionesmanga.core.di.LocalModule
import edu.iesam.valoracionesmanga.core.di.RemoteModule
import edu.iesam.valoracionesmanga.features.manga.di.MangaModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class ValoracionesManga : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        initAppCheckFirebase()
        startKoin {
            androidContext(this@ValoracionesManga)
            modules(
                AppModule().module,
                RemoteModule().module,
                LocalModule().module,
                MangaModule().module
            )
        }
    }

    private fun initAppCheckFirebase() {
        Firebase.appCheck.installAppCheckProviderFactory(
            if (BuildConfig.DEBUG) {
                DebugAppCheckProviderFactory.getInstance()
            } else {
                PlayIntegrityAppCheckProviderFactory.getInstance()
            }
        )
    }
}