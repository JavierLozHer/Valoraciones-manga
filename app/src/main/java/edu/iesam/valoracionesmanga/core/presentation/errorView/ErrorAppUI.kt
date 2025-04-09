package edu.iesam.valoracionesmanga.core.presentation.errorView

import android.content.Context
import edu.iesam.valoracionesmanga.R
import kotlin.reflect.KFunction0

interface ErrorAppUI {
    fun getTitleError(): String
    fun getDescriptionError(): String
    fun getActionRetry(): KFunction0<Unit>?
}

class InternetErrorUI(private val context: Context, private val actionRetry: KFunction0<Unit>?) : ErrorAppUI {

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_internet)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_internet)
    }

    override fun getActionRetry(): KFunction0<Unit>? {
        return actionRetry
    }
}

class UnknownErrorUI(private val context: Context, private val actionRetry: KFunction0<Unit>?) : ErrorAppUI {

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_unknown)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_unknown)
    }

    override fun getActionRetry(): KFunction0<Unit>? {
        return actionRetry
    }
}