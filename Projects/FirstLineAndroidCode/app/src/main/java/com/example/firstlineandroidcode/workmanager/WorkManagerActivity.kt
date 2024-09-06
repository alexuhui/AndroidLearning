package com.example.firstlineandroidcode.workmanager

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.firstlineandroidcode.R

class WorkManagerActivity : AppCompatActivity() {
    private val TAG = "WorkManagerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        val doWorkBtn : Button = findViewById(R.id.doWorkBtn)
        doWorkBtn.setOnClickListener(){
            /**
             * 后台任务的具体运行时间是由我们所指定的约束以及系统自身的一些优化所决定的，
             * 由于这里没有指定任何约束，因此后台任务基本上会在点击按钮之后立刻运行。
             * */
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            WorkManager.getInstance(this).enqueue(request)
        }

        init()
    }

    private val imgs = listOf("apple", "banana", "mango", "pear", "pineapple", "strawberry")
    private fun init(){
        val fruits = ArrayList<Thumbnail>()
        for(name in imgs){
            val imageUrl = "http://172.25.44.120:9433/texture/${name}_small.jpg"
            fruits.add(Thumbnail(name, imageUrl))
        }

        val layoutManagerGrid = GridLayoutManager(this, 2)
        val adapter = ThumbnailAdapter(fruits)
        adapter.setOwner(this)
        val recyclerViewGrid : RecyclerView = findViewById(R.id.recyclerViewGrid)
        recyclerViewGrid.layoutManager = layoutManagerGrid
        recyclerViewGrid.adapter = adapter
    }
}