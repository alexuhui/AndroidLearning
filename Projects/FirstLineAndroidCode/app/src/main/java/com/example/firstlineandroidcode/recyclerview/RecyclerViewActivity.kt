package com.example.firstlineandroidcode.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstlineandroidcode.R
import com.example.firstlineandroidcode.listview.AnimalItem

class RecyclerViewActivity : AppCompatActivity() {

    private val TAG = "ListViewActivity"

    private val data = listOf("apple", "cherry", "durian", "grape", "lemon", "orange", "prune", "watermelon")
    private val fruits = ArrayList<FruitItem>()

    private  fun init(){
        repeat(10){
            for(ani in data){
                val resId = resources.getIdentifier(ani, "drawable", applicationInfo.packageName)
                fruits.add(FruitItem(ani, resId))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        init()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruits)
        recyclerView.adapter = adapter
    }
}