package edu.iesam.valoracionesmanga.core.domain

sealed class ErrorApp : Throwable() {
    object InternetErrorApp : ErrorApp() {
        private fun readResolve(): Any = InternetErrorApp
    }

    object DataErrorApp : ErrorApp() {
        private fun readResolve(): Any = DataErrorApp
    }

    object UnknownErrorApp : ErrorApp() {
        private fun readResolve(): Any = UnknownErrorApp
    }

}