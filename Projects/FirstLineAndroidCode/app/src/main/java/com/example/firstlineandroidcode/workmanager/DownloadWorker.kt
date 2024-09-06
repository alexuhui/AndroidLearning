package com.example.firstlineandroidcode.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    private val TAG = "DownloadWorker"

    companion object{
        const val KEY_NAME = "file_name"
        const val KEY_URL = "file_url"
    }

    override fun doWork(): Result {
        val url = inputData.getString(KEY_URL) ?: return Result.failure()
        val fileName = inputData.getString(KEY_NAME) ?: return Result.failure()
        val cacheDir = applicationContext.cacheDir

        return try {
            val url = URL(url)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            val file = File(cacheDir, fileName)
            val output = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (input.read(buffer).also { bytesRead = it } != -1) {
                output.write(buffer, 0, bytesRead)
            }
            output.close()
            input.close()
            Log.d(TAG, "doWork, download file succeeded, imageName : $fileName  cachePath : $cacheDir")
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}