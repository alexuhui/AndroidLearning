package com.example.firstlineandroidcode.workmanager

import java.io.File

class Thumbnail(private val name : String, private val imageUrl : String) {
    fun getImageUrl(): String {
        return imageUrl
    }

    fun getName() : String{
        return name
    }
}