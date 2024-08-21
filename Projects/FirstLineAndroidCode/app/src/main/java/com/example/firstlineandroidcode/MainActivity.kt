package com.example.firstlineandroidcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast


/**
 * Activity是Android应用程序的门面，凡是在应用中你看得到的东西，都是放在Activity中的。
 * 父类 AppCompatActivity是AndroidX中提供的一种向下兼容的Activity，可以使Activity在不同系统版本中的功能保持一致性。
 * @see AppCompatActivity
 *
 * 每个Activity在其生命周期中最多可能会有4种状态。
 * *** 运行状态
 * *** 暂停状态
 * *** 停止状态
 * *** 销毁状态
 **/
class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * savedInstanceState 这个参数在一般情况下都是null，
         * 但是如果在Activity被系统回收之前，
         * 你通过onSaveInstanceState()方法保存数据，
         * 这个参数就会带有之前保存的全部数据，
         * 我们只需要再通过相应的取值方法将数据取出即可。
         * */
        if(savedInstanceState != null){
            val data = savedInstanceState.getString("data_key")
            if(data != null){
                Toast.makeText(this, "savedInstanceState data is $data", Toast.LENGTH_SHORT).show()
            }
        }

        Log.d(tag, "onCreate ++++++++++++++++++++++++++++++++++++++++++")
        setContentView(R.layout.activity_main)
        val startNormalActivity : Button = findViewById(R.id.startNormalActivity)
        startNormalActivity.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }
        val startDialogActivity : Button = findViewById(R.id.startDialogActivity)
        startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 这个方法可以保证在Activity被回收之前一定会被调用
     * 这个方法会携带一个Bundle类型的参数，Bundle提供了一系列的方法用于保存数据，
     * 比如可以使用putString()方法保存字符串，使用putInt()方法保存整型数据，以此类推。
     * */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tempData = "Something you just typed"
        outState.putString("data_key", tempData)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart ----------------------------------")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume ==================================")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause ==================================")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop ----------------------------------")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy ++++++++++++++++++++++++++++++++++++++++++")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }
}