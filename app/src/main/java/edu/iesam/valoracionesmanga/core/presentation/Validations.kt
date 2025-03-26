package edu.iesam.valoracionesmanga.core.presentation

import android.util.Patterns

fun validateEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validatePassword(password: String): Boolean {
    return password.length >= 6
}