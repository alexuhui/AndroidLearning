package com.example.firstlineandroidcode.http_xml_json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.firstlineandroidcode.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class NetworkActivity : AppCompatActivity() {
    private val TAG = "NetworkActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        val sendRequestBtn :Button = findViewById(R.id.sendRequestBtn)
        sendRequestBtn.setOnClickListener {
//            sendRequestWithHttpURLConnection()
            sendRequestWithOkHttp()
        }

        val sendXMLRequestBtn : Button = findViewById(R.id.sendXMLRequestBtn)
        sendXMLRequestBtn.setOnClickListener(){
            sendXmlRequestWithOkHttp()
        }

        val sendJSONRequestBtn : Button = findViewById(R.id.sendJSONRequestBtn)
        sendJSONRequestBtn.setOnClickListener(){
            sendJsonRequestWithOkHttp()
        }

        val url = "http://172.25.44.120:9433/get_data.json"
        val com1 : Button = findViewById(R.id.comRequestBtn1)
        com1.setOnClickListener(){
            HttpUtil.sendHttpRequest(url, object : HttpCallbackListener{
                override fun onFinish(response: String) {
                    Log.d(TAG, "onFinish: url = $url")
                    Log.d(TAG, "onFinish: response = $response")
                    parseJSONWithJSONObject(response)
                }

                override fun onError(e: Exception) {
                    Log.e(TAG, "onError: $e", )
                }
            })
        }

        val com2 : Button = findViewById(R.id.comRequestBtn2)
        com2.setOnClickListener(){
            HttpUtil.sendOkHttpRequest(url, object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "onFailure: $e", )
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d(TAG, "onResponse: url = $url")
                    Log.d(TAG, "onResponse: response = $response")
                    val responseData = response.body?.string()
                    if(responseData != null){
                        parseJSONWithJSONObject(responseData)
                    }
                }
            })
        }
    }

    private fun sendRequestWithHttpURLConnection() {
        // 开启线程发起网络请求
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                // 下面对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            // 在这里进行UI操作，将结果显示到界面上
            val responseText: TextView = findViewById(R.id.responseText)
            responseText.text = response
        }
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.baidu.com")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    showResponse(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun sendXmlRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    // 指定访问的服务器地址是计算机本机
                    .url("http://172.25.44.120:9433/get_data.xml")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    parseXMLWithPull(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseXMLWithPull(xmlData: String) {
        Log.d(TAG, "parseXMLWithPull")
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when (eventType) {
                    // 开始解析某个节点
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    // 完成解析某个节点
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            Log.d(TAG, "eventType : $eventType")
                            Log.d(TAG, "id : $id")
                            Log.d(TAG, "name : $name")
                            Log.d(TAG, "version : $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun sendJsonRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    // 指定访问的服务器地址是计算机本机
                    .url("http://172.25.44.120:9433/get_data.json")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    parseJSONWithJSONObject(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseJSONWithJSONObject(jsonData: String) {
        Log.d(TAG, "parseJSONWithJSONObject")
        try {
            val jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val version = jsonObject.getString("version")
                Log.d(TAG, "id : $id")
                Log.d(TAG, "name : $name")
                Log.d(TAG, "version : $version")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}