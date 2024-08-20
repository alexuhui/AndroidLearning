package com.example.firstlineandroidcode

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val implicit: Button = findViewById(R.id.implicit)
        implicit.setOnClickListener(){
            val intent = Intent("com.example.firstlineandroidcode.ACTION_START")
            intent.addCategory("com.example.firstlineandroidcode.MY_CATEGORY")
            startActivity(intent)
        }

        val baidu : Button = findViewById(R.id.baidu)
        baidu.setOnClickListener(){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }

        val call : Button = findViewById(R.id.call)
        call.setOnClickListener(){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item -> Toast.makeText(this, "add item", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show()
            R.id.finish_item -> finish()
        }
        return true
    }
}