package com.example.firstlineandroidcode.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * 书中实现异步service的类 IntentService 已经被弃用
 * 推荐WorkManager
 * 有点复杂，后面需要再研究吧
 * */
class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        return Result.success();
    }
}
