package com.example.firstlineandroidcode.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {
    private val TAG = "Repository"
    fun getUser(userId: String): LiveData<User> {
        Log.d(TAG, "getUser: userId = $userId")
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }

}