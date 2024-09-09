package com.example.firstlineandroidcode.moduletest

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.firstlineandroidcode.R
import com.example.permissionx.PermissionX
import com.example.testlibrary.LibraryTest

class ModuleTestActivity : AppCompatActivity() {
    private val TAG = "ModuleTestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module_test)
        val makeCallBtn : Button = findViewById(R.id.makeCallBtn)
        makeCallBtn.setOnClickListener {
            PermissionX.request(this, Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val nums = listOf(10, 20, 15, 66, 25, 88)
        Log.d(TAG, "onCreate, nums : ${nums.joinToString(separator = ", ")}")
        Log.d(TAG, "onCreate, max : ${LibraryTest.max(10, 20, 15, 66, 25, 88)}")
        Log.d(TAG, "onCreate, min : ${LibraryTest.min(10, 20, 15, 66, 25, 88)}")
        Log.d(TAG, "onCreate, sum : ${LibraryTest.add(10, 20, 15, 66, 25, 88)}")
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

}