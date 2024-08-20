package com.example.firstlineandroidcode

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

private const val Tag = "FirstActivity"
class FirstActivity : AppCompatActivity() {

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(it.data != null && it.resultCode == Activity.RESULT_OK){
            Toast.makeText(this, "Result OK! ${it.data?.getStringExtra("ex")}", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Result Error!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 它会在Activity第一次被创建的时候调用。
     * 你应该在这个方法中完成Activity的初始化操作，比如加载布局、绑定事件等。
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        val button1: Button = findViewById(R.id.button1)
        val data:String = "data from FirstActivity"

        button1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("ex", data)
//            startActivity(intent)
            // 带返回参数的启动Activity
            launcher.launch(intent)
        }

        val implicit: Button = findViewById(R.id.implicit)
        implicit.setOnClickListener(){
            val intent = Intent("com.example.firstlineandroidcode.ACTION_START")
            intent.addCategory("com.example.firstlineandroidcode.MY_CATEGORY")
            startActivity(intent)
        }

        val baidu : Button = findViewById(R.id.baidu)
        baidu.setOnClickListener(){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }

        val call : Button = findViewById(R.id.call)
        call.setOnClickListener(){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }

        Log.d(Tag, "onCreate")
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item -> Toast.makeText(this, "add item", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show()
            R.id.finish_item -> finish()
        }
        return true
    }

    /**
     * 在Activity由不可见变为可见的时候调用。
     */
    override fun onStart() {
        super.onStart()
        Log.d(Tag, "onStart")
    }

    /**
     * 在Activity准备好和用户进行交互的时候调用。
     * 此时的Activity一定位于返回栈的栈顶，并且处于运行状态。
     */
    override fun onResume() {
        super.onResume()
        Log.d(Tag, "onResume")
    }

    /**
     * 在系统准备去启动或者恢复另一个Activity的时候调用。
     * 我们通常会在这个方法中将一些消耗CPU的资源释放掉，
     * 以及保存一些关键数据，但这个方法的执行速度一定要快，不然会影响到新的栈顶Activity的使用。
     * */
    override fun onPause() {
        super.onPause()
        Log.d(Tag, "onPause")
    }

    /**
     * onStop()。这个方法在Activity完全不可见的时候调用。
     * 它和onPause()方法的主要区别在于，如果启动的新Activity是一个对话框式的Activity，
     * 那么onPause()方法会得到执行，而onStop()方法并不会执行。
     * */
    override fun onStop() {
        super.onStop()
        Log.d(Tag, "onStop")
    }

    /**
     * ● onDestroy()。这个方法在Activity被销毁之前调用，之后Activity的状态将变为销毁状态。
     * */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(Tag, "onDestroy")
    }

    /**
     * ● onRestart()。这个方法在Activity由停止状态变为运行状态之前调用，也就是Activity被重新启动了。
     * */
    override fun onRestart() {
        super.onRestart()
        Log.d(Tag, "onRestart")
    }
}