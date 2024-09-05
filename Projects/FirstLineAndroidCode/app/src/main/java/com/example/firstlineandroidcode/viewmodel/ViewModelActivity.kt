package com.example.firstlineandroidcode.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.firstlineandroidcode.R

class ViewModelActivity : AppCompatActivity() {
    private val TAG = "ViewModelActivity"
    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    companion object{
        const val COUNT_DATA_KEY = "count_reserved"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(COUNT_DATA_KEY, 0)
        Log.d(TAG, "onCreate, countReserved : $countReserved")
        //不可以直接去创建ViewModel的实例，一定要通过ViewModelProvider来获取ViewModel的实例，
//        viewModel = ViewModelProvider(this, ).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)

        val plusOneBtn : Button = findViewById(R.id.plusOneBtn)
        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
//            refreshCounter()
        }
//        refreshCounter()

        val clear : Button = findViewById(R.id.clearBtn)
        clear.setOnClickListener(){
            viewModel.clear()
//            refreshCounter()
        }


        val plusByTimer : Button = findViewById(R.id.plusByTimerBtn)
        plusByTimer.setOnClickListener(){
            viewModel.plusByTimer()
        }

        val infoText: TextView = findViewById(R.id.infoText)
        viewModel.counter.observe(this) { count ->
            infoText.text = count.toString()
        }

        val userNameBtn : Button =findViewById(R.id.usrNameBtn)
        userNameBtn.setOnClickListener(){
            viewModel.randomName()
        }

        val ageBtn : Button = findViewById(R.id.ageBtn)
        ageBtn.setOnClickListener(){
            viewModel.randomAge()
        }

        val userName : TextView = findViewById(R.id.userName)
        viewModel.userName.observe(this){
            Log.d(TAG, "userName change : $it")
            userName.text = it
        }

        val userAge : TextView = findViewById(R.id.userAge)
        viewModel.age.observe(this){
            Log.d(TAG, "usrAge change: $it")
            userAge.text = it.toString()
        }

        lifecycle.addObserver(MyObserver())
    }

//    private fun refreshCounter() {
//        val infoText : TextView = findViewById(R.id.infoText)
//        infoText.text = viewModel.counter.toString()
//    }

    override fun onPause() {
        super.onPause()
        sp.edit().apply(){
            viewModel.counter.value?.let { putInt(COUNT_DATA_KEY, it) }
        }.apply()
        Log.d(TAG, "onPause, counter : ${ viewModel.counter.value}")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: lifecycle.currentState = ${lifecycle.currentState}")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}