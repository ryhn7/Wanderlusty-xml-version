package com.example.wanderlusty.utils

import java.io.InputStream

object GetJson {
    fun getJsonFromAssets(fileName: String): String {
        val inputStream: InputStream = GetJson::class.java.classLoader!!.getResourceAsStream("assets/$fileName")
        return inputStream.bufferedReader().use { it.readText() }
    }
}