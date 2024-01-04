package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    /**
     * onCreate() 函数是此应用的入口点，并会调用其他函数来构建 UI。
     * 在 Kotlin 程序中，main() 函数是 Kotlin 编译器在代码中开始编译的特定位置；
     * 在 Android 应用中，则是由 onCreate() 函数来担任这个角色
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * onCreate() 函数中的 setContent() 函数用于通过可组合函数定义布局。
         * 任何标有 @Composable 注解的函数都可通过 setContent() 函数或其他可组合函数进行调用。
         * 该注解可告知 Kotlin 编译器 Jetpack Compose 使用的这个函数会生成 UI。
         */
        setContent {
            GreetingCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

/**
 * Greeting() 函数是一种可组合函数；请留意它上方的 @Composable 注解。
 * 可组合函数会接受一些输入并生成屏幕上显示的内容。
 * @Composable 函数名称采用首字母大写形式。
 * 需在该函数前面添加 @Composable 注解。
 * @Composable 函数无法返回任何内容。
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello, a la la la~ $name!",
        modifier = modifier
    )
}

/**
 * DefaultPreview() 函数是一项很酷的功能，让您无需构建整个应用就能查看应用的外观。
 * 若要使其成为预览函数，您需要添加 @Preview 注解。
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting("Android")
    }
}