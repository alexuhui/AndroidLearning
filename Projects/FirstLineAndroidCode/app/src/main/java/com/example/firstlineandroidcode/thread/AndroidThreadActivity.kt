package com.example.firstlineandroidcode.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import com.example.firstlineandroidcode.R
import kotlin.concurrent.thread

class AndroidThreadActivity : AppCompatActivity() {

    private var changeVar : Int = 10
    private val updateText = 1

    // ------------------ 这是书上的方案
//    val handler = object : Handler(Looper.getMainLooper()) {
//        override fun handleMessage(msg: Message) {
//            // 在这里可以进行UI操作
//            when (msg.what) {
//                updateText -> {
//                    val textView : TextView = findViewById(R.id.textView)
//                    textView.text = "Nice to meet you"
//                }
//            }
//        }
//    }

    //--------------------------- 这是AI提示的方案
    private val handler = Handler(Looper.getMainLooper())

    /**
     * Handler的sendMessage()和post()方法都可以实现在不同线程之间传递消息和执行代码，但它们之间存在一些差异。
     * 在性能方面，这两种方法的差异通常可以忽略不计，但在某些情况下，它们可能表现出不同的行为。
     *
     * sendMessage()方法：
     * 使用sendMessage()方法时，你需要创建一个Message对象，并将其发送到Handler的消息队列中。
     * Message对象可以携带更多的信息，例如整型、长整型、对象等。
     * sendMessage()方法允许你更详细地控制消息的发送和处理过程。
     *
     * post()方法：
     * 使用post()方法时，你可以直接传递一个Runnable对象，而不需要创建Message对象。
     * Runnable对象在执行时没有返回值，因此它更适合执行简单的任务。
     * post()方法更简洁，适用于快速执行简单的代码片段。
     *
     * 在性能方面，sendMessage()和post()方法的差异通常可以忽略不计。
     * 然而，在某些情况下，post()方法可能略快一些，因为它不需要创建Message对象。
     * 但是，这种性能差异通常不会对应用程序产生显著影响。
     *
     * 在选择sendMessage()还是post()方法时，你应该根据实际需求和代码的可读性来决定。
     * 如果你需要发送复杂的数据结构或更详细地控制消息的处理过程，sendMessage()方法可能更适合。
     * 如果你只需要执行简单的代码片段，post()方法可能更简洁。
     * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_thread)
        val changeTextBtn : Button = findViewById(R.id.changeTextBtn)
        changeTextBtn.setOnClickListener {
            thread {
                // 不能直接在子线程更新UI，会报错的
                // android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
//                val textView : TextView = findViewById(R.id.textView)
//                textView.text = "Nice to meet you"

                // 这是书上提供的方案
//                val msg = Message()
//                msg.what = updateText
//                handler.sendMessage(msg) // 将Message对象发送出去

                // 这是AI提示的方案
                handler.post(){
                    val textView : TextView = findViewById(R.id.textView)
                    textView.text = "Nice to meet you ${changeVar++}"
                }
            }
//            changeVar --;
        }
    }

}