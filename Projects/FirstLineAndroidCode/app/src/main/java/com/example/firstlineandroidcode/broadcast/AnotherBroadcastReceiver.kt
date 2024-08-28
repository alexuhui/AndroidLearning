package com.example.firstlineandroidcode.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AnotherBroadcastReceiver : BroadcastReceiver() {

    private val TAG = "AnotherBroadcast"

    override fun onReceive(context: Context, intent: Intent) {
        val data = intent.getIntExtra("data", 0)
        Toast.makeText(context, "received in AnotherBroadcastReceiver, data is $data",
            Toast.LENGTH_SHORT).show()

        Log.d(TAG, "onReceive: ${intent.action}  $data")
    }

}