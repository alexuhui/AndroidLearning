package com.example.practice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Contacts.Photo
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practice.camera.PhotoAlbum
import com.example.practice.main.FirstActivity
import com.example.practice.main.SecondActivity

const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()


        //        val intent : Intent = Intent(this, FirstActivity::class.java)
        //        this.startActivity(intent)
        //        setContent {
        //            PracticeTheme {
        //                // A surface container using the 'background' color from the theme
        //                Surface(
        //                    modifier = Modifier.fillMaxSize(),
        //                    color = MaterialTheme.colorScheme.background
        //                ) {
        //                    Greeting("Android");
        //                    AddTestBtn(this);
        //
        //
        //                    basicSyntaxTest();
        //                }
        //            }
        //        }
    }

    private fun initView() {
        findViewById<Button>(R.id.firstPageBtn)
            .setOnClickListener {
                val intent: Intent = Intent(this, FirstActivity::class.java)
                this.startActivity(intent)
            }

        findViewById<Button>(R.id.secondPageBtn)
            .setOnClickListener{
                val intent: Intent = Intent(this, SecondActivity::class.java)
                this.startActivity(intent)
            }

        findViewById<Button>(R.id.photoAlbumBtn)
            .setOnClickListener{
                val intent : Intent = Intent(this, PhotoAlbum::class.java)
                this.startActivity(intent)
            }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        //        color = Color.White,
    ) {
        Text(
            text = "Hello $name!",
        )
    }
}

@Composable
fun AddTestBtn(context: Context) {
    Button(onClick = {
        Log.i(TAG, "AddTestBtn: onClick")
        //        Runtime.getRuntime().exit(0);
        val intent: Intent = Intent(context, FirstActivity::class.java)
        context.startActivity(intent)
    }) {
        Text(
            text = "test btn",
        )
    }
}


fun basicSyntaxTest() {
    val ks: KotlinBasicSyntax = KotlinBasicSyntax()
    //    ks.doTest()

    val js: JavaBasicSyntax = JavaBasicSyntax()
    //    js.doTest()
}


//@Preview()
//@Composable
//fun GreetingPreview() {
//    PracticeTheme {
//        Greeting("Android")
//    }
//}