package com.example.firstlineandroidcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

const val TAG = "SecondActivity"
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val button2 :Button = findViewById(R.id.button2)
        val data : String? = intent.getStringExtra("ex")
        Log.d(TAG, "onCreate: data = $data")
        if(data != null){
            Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener {
            close()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                close()
            }
        })
    }

    fun close(){
        val intent = Intent()
        intent.putExtra("ex","data from second activity")
        setResult(RESULT_OK, intent)
        finish()
    }
}