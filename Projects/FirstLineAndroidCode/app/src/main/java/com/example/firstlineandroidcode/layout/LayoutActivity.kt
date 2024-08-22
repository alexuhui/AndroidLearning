package com.example.firstlineandroidcode.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstlineandroidcode.R

class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        supportActionBar?.hide()

        val title : TitleLayout = findViewById(R.id.title)
        title.setTitle("自定义标题")
    }
}