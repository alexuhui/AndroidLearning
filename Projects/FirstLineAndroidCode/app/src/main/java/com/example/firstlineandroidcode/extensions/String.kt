package com.example.firstlineandroidcode.extensions

import android.content.Context
import android.widget.Toast

fun String.showToast(context:Context, len:Int = Toast.LENGTH_SHORT): Unit {
    Toast.makeText(context, this, len).show()
}