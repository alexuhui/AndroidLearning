package com.example.firstlineandroidcode.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.firstlineandroidcode.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BroadcastActivity : AppCompatActivity() {

    private val TAG = "BroadcastActivity"

    inner class LocalReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "action : ${intent.action}", Toast.LENGTH_SHORT).show()

            Log.d(TAG, "onReceive: action = ${intent.action}")

            when(intent.action){
                "android.intent.action.BATTERY_CHANGED" -> Log.d(TAG, "BATTERY: ${getBatteryPercentage(context)}")
                "android.intent.action.TIME_TICK" -> {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    val currentTime = dateFormat.format(Date())
                    Log.d(TAG, "current : $currentTime")
                }
            }
        }

        private fun getBatteryPercentage(context: Context): Int {
            val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        }
    }
    private lateinit var receiver: LocalReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        val intentFilter = IntentFilter()
        val actions =  readFileByLine(this, "action/broadcast_actions.txt")
        for (action in actions) {
            if(action.isNotEmpty()){
                Log.d(TAG, "onCreate, add action : $action")
                intentFilter.addAction(action)
            }
        }
        receiver = LocalReceiver()
        registerReceiver(receiver, intentFilter)

        val button :Button = findViewById(R.id.broadcastButton)
        button.setOnClickListener(){
            val intent = Intent("com.example.firstlineandroidcode.MY_BROADCAST")
            intent.setPackage(packageName)
            intent.putExtra("data", 2049)
//            sendBroadcast(intent)
            sendOrderedBroadcast(intent, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }


    private fun readFileByLine(context: Context, fileName: String): List<String> {
        val inputStream = context.assets.open(fileName)  // 从assets文件夹中读取文件
        val reader = BufferedReader(InputStreamReader(inputStream))
        return reader.readLines()
    }
}