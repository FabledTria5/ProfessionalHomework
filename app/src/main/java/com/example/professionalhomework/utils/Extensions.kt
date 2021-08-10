package com.example.professionalhomework.utils

import android.view.View
import java.util.*

object Extensions {

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.INVISIBLE
    }

    fun View.enable() {
        isEnabled = true
        alpha = 1f
    }

    fun String.capitalize() = this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }

}