package com.example.firstlineandroidcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

class SecondActivity : AppCompatActivity() {
    private val tag = "SecondActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val button2 :Button = findViewById(R.id.button2)
        val data : String? = intent.getStringExtra("ex")
        Log.d(tag, "onCreate $this  data = $data  taskId = $taskId")
        if(data != null){
            Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener {
            close()
        }

        val open3 : Button = findViewById(R.id.open3)
        open3.setOnClickListener(){
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        val reopen : Button = findViewById(R.id.reopen)
        reopen.setOnClickListener(){
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
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

    /**
     * 在Activity由不可见变为可见的时候调用。
     */
    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    /**
     * 在Activity准备好和用户进行交互的时候调用。
     * 此时的Activity一定位于返回栈的栈顶，并且处于运行状态。
     */
    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    /**
     * 在系统准备去启动或者恢复另一个Activity的时候调用。
     * 我们通常会在这个方法中将一些消耗CPU的资源释放掉，
     * 以及保存一些关键数据，但这个方法的执行速度一定要快，不然会影响到新的栈顶Activity的使用。
     * */
    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    /**
     * onStop()。这个方法在Activity完全不可见的时候调用。
     * 它和onPause()方法的主要区别在于，如果启动的新Activity是一个对话框式的Activity，
     * 那么onPause()方法会得到执行，而onStop()方法并不会执行。
     * */
    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    /**
     * ● onDestroy()。这个方法在Activity被销毁之前调用，之后Activity的状态将变为销毁状态。
     * */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy    taskId = $taskId")
    }

    /**
     * ● onRestart()。这个方法在Activity由停止状态变为运行状态之前调用，也就是Activity被重新启动了。
     * */
    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }
}