package com.example.firstlineandroidcode.workmanager

import android.util.Log
import java.io.File

class Thumbnail(private val name : String, private val imageUrl : String) {
    fun getImageUrl(): String {
        return imageUrl
    }

    fun getName() : String{
        Log.d("Thumbnail", "getName: $name")
        return name
    }
}