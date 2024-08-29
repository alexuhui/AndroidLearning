package com.example.firstlineandroidcode.contentprovider

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.firstlineandroidcode.R

class ContentProviderActivity : AppCompatActivity() {
        private val TAG = "ContentProviderActivity"
        private val contactsList = ArrayList<String>()
        private lateinit var adapter: ArrayAdapter<String>

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_content_provider)
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsList)

            val contactsView : ListView = findViewById(R.id.contactsView)
            contactsView.adapter = adapter

            val refreshBtn : Button = findViewById(R.id.refreshBtn)
            refreshBtn.setOnClickListener(){
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)){
                    Log.d(TAG, "READ_CONTACTS force denied")
                }
                else if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_CONTACTS), 10)
                    Log.d(TAG, "READ_CONTACTS is denied")
                } else {
                    readContacts()
                }
            }
        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                                grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            when (requestCode) {
                10 -> {
                    if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        readContacts()
                    } else {
                        Toast.makeText(this, "You denied the permission",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        private fun readContacts() {
            Log.d(TAG, "readContacts")
            // 查询联系人数据
            contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, null, null, null)?.apply {
                while (moveToNext()) {
                    val nameIndex = getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                    // 获取联系人姓名
                    val displayName = getString(nameIndex)
                    val numberIndex = getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    // 获取联系人手机号
                    val number = getString(numberIndex)
                    contactsList.add("$displayName\n$number")
                }
                adapter.notifyDataSetChanged()
                close()
            }
        }
    }