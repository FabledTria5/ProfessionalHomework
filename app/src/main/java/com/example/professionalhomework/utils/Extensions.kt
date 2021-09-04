package com.example.professionalhomework.utils

import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.install.model.UpdateAvailability
import java.util.*

object Extensions {

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.INVISIBLE
    }

    fun String.capitalize() = this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }

    fun EditText.extractText() = this.text.toString().trim()

    fun TextView.extractText() = this.text.toString().trim()

    fun AppUpdateInfo.updateAvailable(): Boolean {
        return updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
    }
}