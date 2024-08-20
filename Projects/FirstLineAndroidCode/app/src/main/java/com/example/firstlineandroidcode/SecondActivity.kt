package com.example.firstlineandroidcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val button2 :Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            Toast.makeText(this, "You clicked Button 2", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}