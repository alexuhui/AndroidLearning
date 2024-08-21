package com.example.firstlineandroidcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ThirdActivity : AppCompatActivity() {
    private val tag = "ThirdActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_layout)
        Log.d(tag, "onCreate $this  taskId = $taskId")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy    taskId = $taskId")
    }
}