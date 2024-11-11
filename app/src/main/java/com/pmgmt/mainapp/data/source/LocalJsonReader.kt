package com.pmgmt.mainapp.data.source

import android.content.Context
import androidx.annotation.RawRes
import java.io.InputStreamReader

object LocalJsonReader {
    fun readJson(context: Context, @RawRes rawResId: Int): String {
        val inputStream = context.resources.openRawResource(rawResId)
        return InputStreamReader(inputStream).use { it.readText() }
    }
}