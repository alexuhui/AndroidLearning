package com.example.firstlineandroidcode.viewmodel

import android.os.CountDownTimer
import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap


class MainViewModel(countReserved: Int) : ViewModel() {

    private val TAG = "MainViewModel"

    val counter : LiveData<Int> get() = _counter
    private val _counter = MutableLiveData<Int>()

    private val userData = MutableLiveData<User>()
    // 支持单独监听名字变化(不过好像意义也不是特别大，改变某个成员，全部成员的监听都会收到消息，不只是对应的监听收到)
    val userName : LiveData<String> = userData.map { user: User? ->  "First Name : ${user?.firstName}  Last Name : ${user?.lastName}"}
    val age : LiveData<Int> = userData.map { user: User? -> user?.age ?: 0 }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1

        Log.d(TAG, "plusOne: count = ${_counter.value}")
    }

    fun clear() {
        _counter.value = 0
    }


    val name = listOf<String>("A", "B", "C", "D", "E", "F")
    fun randomName()
    {
        val index = (0..< name.count()).random()
        val n = name[index]
        var user = userData.value
        if(user != null){
            user.firstName = n
            user.lastName = n
        }else{
            user = User(n, n, 20)
        }
        userData.value = user
    }

    fun randomAge()
    {
        val age = (16 .. 36).random()
        var user = userData.value
        if(user != null){
            user.age = age
        }else{
            user = User("n", "n", age)
        }
        userData.value = user
    }



    fun plusByTimer(){
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d(TAG, "onTick: millisUntilFinished = $millisUntilFinished")
                // 更新UI显示剩余时间
                plusOne()
            }

            override fun onFinish() {
                // 倒计时结束时的操作

            }
        }.start()
    }

    private val userIdLiveData = MutableLiveData<String>()
    val user: LiveData<User> = userIdLiveData.switchMap() { userId ->
        Log.d(TAG, "switchMap: userId = $userId")
        Repository.getUser(userId)
    }

    fun getUser(userId: String) {
        Log.d(TAG, "getUser: userId = $userId")
        userIdLiveData.value = userId
    }
}