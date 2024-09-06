package com.example.firstlineandroidcode.workmanager

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.firstlineandroidcode.R
import com.example.firstlineandroidcode.extensions.loadImageFromNet

class OriginalImageActivity : AppCompatActivity() {

    companion object{
        const val P_NAME = "param_name"

        fun show(context: Context, name:String){
            val intent = Intent(context, OriginalImageActivity::class.java)
            intent.putExtra(P_NAME, name)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_original_image)

        val name = intent.getStringExtra(P_NAME)
        val imageUrl = "http://172.25.44.120:9433/texture/${name}_big.jpg"

        val originalImage : ImageView = findViewById(R.id.originalImage)
        originalImage.loadImageFromNet(this, imageUrl)
        originalImage.setOnClickListener(){
            finish()
        }
    }
}