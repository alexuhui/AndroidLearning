package com.example.firstlineandroidcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val Tag = "MainActivity"

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 引入布局文件
        setContentView(R.layout.activity_main)

        Log.d(Tag, "onCreate")
    }
}