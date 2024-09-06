package com.example.firstlineandroidcode.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    private val TAG = "SimpleWorker"
    override fun doWork(): Result {
        Log.d(TAG, "do work in SimpleWorker")

        /**
         * 成功就返回Result.success()，
         * 失败就返回Result.failure()。
         * 还有一个Result.retry()方法，它其实也代表着失败，
         * 可以结合WorkRequest.Builder的setBackoffCriteria()方法来重新执行任务
         * */
        return Result.success()
    }
}