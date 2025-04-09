package edu.iesam.valoracionesmanga.core.presentation

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}