package com.example.firstlineandroidcode.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import com.example.firstlineandroidcode.R

class NotifyActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_notify)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                val channel = NotificationChannel("normal", "Normal",NotificationManager.IMPORTANCE_DEFAULT)
//                manager.createNotificationChannel(channel)
                val channel = NotificationChannel("important", "Important",NotificationManager.IMPORTANCE_HIGH)
                manager.createNotificationChannel(channel)
            }

            val sendNotice : Button = findViewById(R.id.sendNotice)
            sendNotice.setOnClickListener {
                val intent : Intent = Intent(this, NotificationActivity::class.java)
                val pi : PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

                val notification = NotificationCompat.Builder(this, "normal")
                    .setContentTitle("This is content title")
                    .setContentText("来一场说走就走的旅行！")
                    .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image)))
                    .setSmallIcon(R.drawable.small_icon)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build()
                manager.notify(1, notification)
            }
        }

    }