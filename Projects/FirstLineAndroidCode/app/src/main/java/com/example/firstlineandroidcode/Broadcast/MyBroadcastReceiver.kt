package com.example.firstlineandroidcode.Broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    private val TAG = "MyBroadcastReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        val data = intent.getIntExtra("data", 0)
        Toast.makeText(context, "received in MyBroadcastReceiver, data is $data",
            Toast.LENGTH_SHORT).show()

        Log.d(TAG, "onReceive: ${intent.action}  $data")

        // 打断广播，有序广播才有用
        abortBroadcast()

        Log.d(TAG, "onReceive: ${intent.action}  $data  abortBroadcast")
    }

}