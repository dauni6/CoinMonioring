package dev.dontsu.coinmonitoring.presentation.ui.extensions

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible

fun View.toVisible() {
    this.isVisible = true
}

fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.toGone() {
    this.isGone = true
}