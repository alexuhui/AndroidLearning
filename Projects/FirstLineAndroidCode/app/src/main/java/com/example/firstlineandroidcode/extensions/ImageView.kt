package com.example.firstlineandroidcode.extensions

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.firstlineandroidcode.workmanager.DownloadWorker
import java.io.File

fun ImageView.loadImageFromNet(context: Context, url: String) {
    val TAG = "ImageView"
    Log.d(TAG, "loadImageFromNet, url = $url")
    val regex = Regex("""/([^/]+)$""")
    val matchResult = regex.find(url)
    val name = matchResult?.groups?.get(1)?.value ?: return
    Log.d(TAG, "loadImageFromNet, name = $name")

    fun setImage(cachedImageFile: File){
        // 图片已缓存，直接显示
        Log.d(TAG, "has cache, set the image: name = $name")
        // 图片已缓存，通过Glide加载并显示
        Glide.with(this)
            .load(cachedImageFile)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(this);
    }

    fun downloadImage(){
        Log.d(TAG, "no cache, download the image, name : $name  imageUrl ： $url")

        // 构建下载参数
        val inputData = Data.Builder()
            .putString(DownloadWorker.KEY_NAME, name)
            .putString(DownloadWorker.KEY_URL, url)
            .build()

        // 构建下载任务
        val downloadWorkRequest= OneTimeWorkRequest.Builder(DownloadWorker::class.java)
            .setInputData(inputData)
            .build()

        // 下载任务加入后台事务队列
        WorkManager.getInstance(context).enqueue(downloadWorkRequest);
        val requestWork = WorkManager.getInstance(context).getWorkInfoByIdLiveData(downloadWorkRequest.id)
        // 注册下载任务监听
        requestWork.observe(context as LifecycleOwner) {
                if (it != null && it.state == WorkInfo.State.SUCCEEDED) {
                    Log.d(TAG,"image download succeeded : $url")
                    // 处理下载成功的逻辑
                    val cachedImageFile : File = File(context.applicationContext.cacheDir, name);
                    if (cachedImageFile.exists()) {
                        setImage(cachedImageFile)
                    } else {
                        Log.e(TAG, "image \"${name}\" not exist!")
                    }
                } else if (it != null && it.state == WorkInfo.State.FAILED) {
                    Log.d(TAG, "image download failed : $url")
                    // 处理下载失败的逻辑
                    ("图片下载失败").showToast(context)
                }
            }
    }

    val cachedImageFile : File = File(context.applicationContext.cacheDir, name);
    if (cachedImageFile.exists()) {
        setImage(cachedImageFile)
    } else {
        downloadImage()
    }
}