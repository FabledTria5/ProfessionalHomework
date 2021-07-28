package com.example.professionalhomework.utils

import android.app.Activity
import android.view.View
import android.widget.Toast

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

    fun View.disable() {
        isEnabled = false
        alpha = 0.5f
    }

}