package com.example.firstlineandroidcode.Broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootCompleteReceiver : BroadcastReceiver() {
    private val TAG = "BootCompleteReceiver"
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onReceive: Boot Complete")
    }

}