package com.example.firstlineandroidcode.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.firstlineandroidcode.R
import kotlin.concurrent.thread

class RoomActivity : AppCompatActivity() {
    private val TAG = "RoomActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)

        val bookDao = AppDatabase.getDatabase(this).bookDao()

        val addDataBtn : Button = findViewById(R.id.addDataBtn)
        addDataBtn.setOnClickListener {
            /**
             * 数据库操作属于耗时操作，
             * Room默认是不允许在主线程中进行数据库操作的，
             * 因此上述代码中我们将增删改查的功能都放到了子线程中。
             * */
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)

                Log.d(TAG, "on add user : user1.id = ${user1.id}    user2.id = ${user2.id}")
            }
        }

        val updateDataBtn : Button = findViewById(R.id.updateDataBtn)
        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        val deleteDataBtn : Button = findViewById(R.id.deleteDataBtn)
        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        val queryDataBtn : Button = findViewById(R.id.queryDataBtn)
        queryDataBtn.setOnClickListener {
            thread {
                Log.d(TAG, "---------------- users : ")
                for (user in userDao.loadAllUsers()) {
                    Log.d(TAG, user.toString())
                }

                Log.d(TAG, "---------------- books : ")
                for(book in bookDao.loadAllBooks()){
                    Log.d(TAG, book.toString())
                }
            }
        }

        val addBookBtn : Button =findViewById(R.id.addBookBtn)
        addBookBtn.setOnClickListener(){
            val book = Book("关不上的窗", 500, "二流子")
            thread {
                bookDao.insertBook(book)
                Log.d(TAG, "insertBook: $book")
            }
        }


    }
}