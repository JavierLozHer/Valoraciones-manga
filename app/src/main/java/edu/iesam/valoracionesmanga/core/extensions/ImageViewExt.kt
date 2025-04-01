package edu.iesam.valoracionesmanga.core.extensions

import android.widget.ImageView
import coil3.load

fun ImageView.loadUrl(url: String) {
    this.load(url)
}