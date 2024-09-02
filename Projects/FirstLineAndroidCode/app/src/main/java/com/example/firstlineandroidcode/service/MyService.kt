package com.example.firstlineandroidcode.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val TAG = "MyService"

    private val mBinder = DownloadBinder()

    class DownloadBinder : Binder() {
        private val TAG = "MyService"
        fun startDownload() {
            Log.d(TAG, "startDownload executed")
        }

        fun getProgress(): Int {
            Log.d(TAG, "getProgress executed")
            return 20
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return  mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

}