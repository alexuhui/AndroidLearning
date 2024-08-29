package com.example.firstlineandroidcode.storage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.firstlineandroidcode.R
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class StorageActivity : AppCompatActivity() {
    private val TAG = "StorageActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        val input : EditText = findViewById(R.id.dataInput)
        val content : String = loadFile()
        if (content.isNotEmpty()){
            input.setText(content)
            input.setSelection(content.length)
        }

        loadSharedPreferences()

        val saveBtn : Button = findViewById(R.id.saveData)
        saveBtn.setOnClickListener(){
            Log.d(TAG, "save : ${input.text}")
            saveFile(input.text.toString())
            savaSharedPreferences(input.text.toString())
        }

        val createDbBtn : Button = findViewById(R.id.createDB)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 3)
        createDbBtn.setOnClickListener(){
            dbHelper.writableDatabase
        }

        val createTable : Button = findViewById(R.id.createTB)
        createTable.setOnClickListener(){
            val db = dbHelper.writableDatabase
            val createFruit = "create table Fruit (" +
                    "id integer primary key autoincrement," +
                    "name text," +
                    "price real)"
           if(!isTableExists(db, "Fruit")){
                db.execSQL(createFruit)
                Log.d(TAG, "create table")
           }

            val values = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "orange")
                put("price", 956)
            }
            db.insert("Fruit", null, values) // 插入第一条数据
            Log.d(TAG, "add fruit data")
            val cursor = db.query("Fruit", null, null, null, null, null, null)
            if (cursor.moveToFirst()){
                do{
                    val nameIndex = cursor.getColumnIndex("name")
                    val name = cursor.getString(nameIndex)

                    val priceIndex = cursor.getColumnIndex("price")
                    val price = cursor.getDouble(priceIndex)

                    Log.d(TAG, "query data, fruit name : $name  price : $price")

                }while (cursor.moveToNext())
            }
            cursor.close()
            Log.d(TAG, "cursor close")
        }

        val addData:Button = findViewById(R.id.addData)
        addData.setOnClickListener(){
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入第二条数据

            Log.d(TAG, "Add data")
        }

        val updateData: Button = findViewById(R.id.updateData)
        updateData.setOnClickListener(){
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 6666)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))

            Log.d(TAG, "Update data")
        }

        val queryData: Button = findViewById(R.id.queryData)
        queryData.setOnClickListener(){
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()){
                do{
                    val nameIndex = cursor.getColumnIndex("name")
                    val name = cursor.getString(nameIndex)
                    val authorIndex = cursor.getColumnIndex("author")
                    val author = cursor.getString(authorIndex)
                    val pagesIndex = cursor.getColumnIndex("pages")
                    val pages = cursor.getInt(pagesIndex)
                    val priceIndex = cursor.getColumnIndex("price")
                    val price = cursor.getDouble(priceIndex)

                    Log.d(TAG, "query data, name : $name   author : $author  pages : $pages  price : $price")

                }while (cursor.moveToNext())
            }
            cursor.close()
        }
    }

    private fun saveFile(inputText: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadFile(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }

    private fun savaSharedPreferences(desc:String){
        val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
        editor.putString("name", "Tom")
        editor.putString("desc", desc)
        editor.putInt("age", 28)
        editor.putBoolean("married", false)
        editor.apply()
    }

    private fun loadSharedPreferences(){
        val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
        val name = prefs.getString("name", "")
        val age = prefs.getInt("age", 0)
        val married = prefs.getBoolean("married", false)
        val desc = prefs.getString("desc", "NO")
        Log.d(TAG, "name is $name")
        Log.d(TAG, "age is $age")
        Log.d(TAG, "married is $married")
        Log.d(TAG, "desc is $desc")
    }

    // 检查表是否存在
    fun isTableExists(db: SQLiteDatabase, tableName: String): Boolean {
        val cursor = db.rawQuery(
            "SELECT name FROM sqlite_master WHERE type='table' AND name=?",
            arrayOf(tableName)
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
}