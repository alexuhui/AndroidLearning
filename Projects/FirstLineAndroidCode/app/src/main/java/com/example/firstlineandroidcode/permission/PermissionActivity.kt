package com.example.firstlineandroidcode.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import com.example.firstlineandroidcode.R

class PermissionActivity : AppCompatActivity() {
    private val TAG = "PermissionActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        val makeCall :Button = findViewById(R.id.makeCall)
        makeCall.setOnClickListener(){
            Log.d(TAG, "makeCall on click")
            try {
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
                }else{
                    call()
                }

                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:10086")
                startActivity(intent)
                Log.d(TAG, "make call 10086")
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_BOOT_COMPLETED) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_BOOT_COMPLETED), 2)
            Log.d(TAG, "permission RECEIVE_BOOT_COMPLETED not be granted")
        }else {
            Log.d(TAG, "permission RECEIVE_BOOT_COMPLETED granted")
        }

        val permission : Button = findViewById(R.id.permission)
        permission.setOnClickListener(){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED){

                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 3)
                Log.d(TAG, "permission ACCESS_LOCATION not be granted")
            }else{
                Log.d(TAG, "permission ACCESS_LOCATION granted, fine : ${ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)}   coarse : ${ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)}")
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call()
                }
            }

            3 -> {
                if (permissions.isNotEmpty()){
                    for(i in permissions.indices)
                    {
                        Log.d(TAG, "onRequestPermissionsResult: P : ${permissions[i]}   result : ${grantResults[i]}")
                    }
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
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