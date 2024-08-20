package com.example.firstlineandroidcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

const val TAG = "SecondActivity"
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val button2 :Button = findViewById(R.id.button2)
        val data : String? = intent.getStringExtra("ex")
        Log.d(TAG, "onCreate: data = $data")
        button2.setOnClickListener {
            Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.putExtra("ex","data from second activity")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}