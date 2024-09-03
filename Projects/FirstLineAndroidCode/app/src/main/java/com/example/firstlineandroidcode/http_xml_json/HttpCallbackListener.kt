package com.example.firstlineandroidcode.http_xml_json

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}