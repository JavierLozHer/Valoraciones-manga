package edu.iesam.valoracionesmanga.core.presentation.errorView

import android.content.Context
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import org.koin.core.annotation.Single
import kotlin.reflect.KFunction0

@Single
class ErrorAppUIFactory(private val context: Context) {

    fun build(errorApp: ErrorApp, actionRetry: KFunction0<Unit>? = null): ErrorAppUI {
        return when(errorApp) {
            ErrorApp.DataErrorApp -> UnknownErrorUI(context, actionRetry)
            ErrorApp.InternetErrorApp -> InternetErrorUI(context, actionRetry)
            ErrorApp.UnknownErrorApp -> UnknownErrorUI(context, actionRetry)
        }
    }

}