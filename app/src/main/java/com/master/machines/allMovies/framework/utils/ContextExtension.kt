package com.master.machines.allMovies.framework.utils

import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat

object ContextExtension {

    fun Context.obtainColor(idColor: Int): Int =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            ContextCompat.getColor(this, idColor)
        else resources.getColor(idColor)

}