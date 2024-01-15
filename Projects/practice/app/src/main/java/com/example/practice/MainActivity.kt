package com.example.practice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practice.ui.theme.PracticeTheme

const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android");
                    AddTestBtn();


                    basicSyntaxTest();
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
//        color = Color.White,
    ){
        Text(
            text = "Hello $name!",
        )
    }
}

@Composable
fun AddTestBtn(){
    Button(onClick = {
        Log.i(TAG, "AddTestBtn: onClick")
    }) {
        Text(
            text = "test btn",
        )
    }
}


fun basicSyntaxTest() {
    val bs : KotlinBasicSyntax = KotlinBasicSyntax();
    bs.doTest();
}


//@Preview()
//@Composable
//fun GreetingPreview() {
//    PracticeTheme {
//        Greeting("Android")
//    }
//}