package com.example.testlibrary

import kotlin.math.max as mMax
import kotlin.math.min as mMin

object LibraryTest {
    fun add(vararg numbers : Int):Int{
        if(numbers.isEmpty()) return -1
        var v = numbers[0]
        for (i in 1 until numbers.size){
            v += numbers[i]
        }

        return v
    }

    fun max(vararg numbers : Int):Int{
        if(numbers.isEmpty()) return -1
        var v = numbers[0]
        for (i in 1 until numbers.size){
            v = mMax(v, numbers[i])
        }

        return v
    }

    fun min(vararg numbers : Int):Int{
        if(numbers.isEmpty()) return -1
        var v = numbers[0]
        for (i in 1 until numbers.size){
            v = mMin(v, numbers[i])
        }

        return v
    }
}