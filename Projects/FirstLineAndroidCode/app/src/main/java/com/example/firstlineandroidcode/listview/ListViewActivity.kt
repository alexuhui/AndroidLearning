package com.example.firstlineandroidcode.listview

import android.content.pm.ApplicationInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.firstlineandroidcode.R

class ListViewActivity : AppCompatActivity() {

    private val TAG = "ListViewActivity"

    private val data = listOf("cat", "dog", "dolphin", "eagle", "lion", "panda", "rabbit", "sheep")
    private val animals = ArrayList<AnimalItem>()
    private  fun init(){
        repeat(10){
            for(ani in data){
                val resId = resources.getIdentifier(ani, "drawable", applicationInfo.packageName)
                animals.add(AnimalItem(ani, resId))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        init()

        val adapter = AnimalAdapter(this, R.layout.animal, animals)
        val listView : ListView = findViewById(R.id.listView)
        listView.adapter = adapter
        listView.setOnItemClickListener(){parent, view, position, id ->
            val animalItem = animals[position]
            Log.d(TAG, "OnItemClick: parent = $parent   view = $view   position = $position   id = $id")
            Toast.makeText(this, "name : ${animalItem.name}", Toast.LENGTH_SHORT).show()
        }
    }
}