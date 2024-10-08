package com.example.firstlineandroidcode.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.firstlineandroidcode.R

class WidgetActivity : AppCompatActivity(), View.OnClickListener {
    private val tag = "WidgetActivity"
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        val button : Button = findViewById(R.id.button)
        button.setOnClickListener(this)

        val sure : Button = findViewById(R.id.sure)
        sure.setOnClickListener(this)

        val switchImg : Button = findViewById(R.id.switch_img)
        switchImg.setOnClickListener(this)

        val progressBtn : Button = findViewById(R.id.progressBtn)
        progressBtn.setOnClickListener(this)

        var progressAddBtn : Button = findViewById(R.id.progressAddBtn)
        progressAddBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d(tag, "onClick: id = ${v?.id}")
        when(v?.id){
            R.id.button ->{
                finish()
            }
            R.id.sure ->{
                val editText : EditText = findViewById(R.id.edittext)
                val text = editText.text.toString()
                Toast.makeText(this, "$text", Toast.LENGTH_SHORT).show()
            }
            R.id.switch_img ->{
                val imgView : ImageView = findViewById(R.id.image)
                when(index) {
                    0 -> imgView.setImageResource(R.drawable.apple)
                    1 -> imgView.setImageResource(R.drawable.cherry)
                    2 -> imgView.setImageResource(R.drawable.watermelon)
                    3 -> imgView.setImageResource(R.drawable.durian)
                    4 -> imgView.setImageResource(R.drawable.lemon)
                }

                index++
                index %= 5
            }
            R.id.progressBtn ->{
                val progressBar:ProgressBar = findViewById(R.id.progressBar)
                when(progressBar.visibility){
                    View.VISIBLE -> progressBar.visibility = View.GONE
                    View.GONE -> progressBar.visibility = View.VISIBLE
                }
            }
            R.id.progressAddBtn ->{
                var progressBar:ProgressBar = findViewById(R.id.progressBar2)
                var p = progressBar.progress + 20
                if(p > 100) {
                    AlertDialog.Builder(this).apply {
                        setTitle("progress is larger than 100")
                        setMessage("progress will back to 0. are you sure?")
                        setCancelable(false)
                        setPositiveButton("OK"){ dialog, which ->
                            p = 0
                            progressBar.progress = p
                            Toast.makeText(applicationContext,
                                "999999", Toast.LENGTH_SHORT).show()
                        }
                        setNegativeButton("Cancel"){ _, _ -> }
                        show()
                    }
                }else{
                    progressBar.progress = p
                    Log.d(tag, "onClick: progressBar.progress = ${progressBar.progress}")
                }
            }
        }
    }
}