package com.example.firstlineandroidcode.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import com.example.firstlineandroidcode.R

class ServiceActivity : AppCompatActivity() {

    private val TAG = "ServiceActivity"

    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            val p = downloadBinder.getProgress()

            Log.d(TAG, "onServiceConnected: progress = $p")
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.d(TAG, "onServiceDisconnected: ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        val startServiceBtn : Button = findViewById(R.id.startServiceBtn)
        startServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent) // 启动Service
        }

        val stopServiceBtn : Button = findViewById(R.id.stopServiceBtn)
        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent) // 停止Service
        }

        val bindServiceBtn : Button = findViewById(R.id.bindServiceBtn)
        bindServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE) // 绑定Service
        }

        val unbindServiceBtn : Button = findViewById(R.id.unbindServiceBtn)
        unbindServiceBtn.setOnClickListener {
            unbindService(connection) // 解绑Service
        }
    }
}