package com.example.practice

import android.util.Log

class KotlinBasicSyntax {
    private val TAG = "KotlinBasicSyntax"

    fun doTest() {
        testFun(10, 20)
        vars(0, 1, 2)
        vars(0, 1, 2, 3, 4)
        lambdaTest()
        nullCheck()
        rangeTest()

        testWhen(1)
        testWhen(2)
        testWhen(10)

        testClass()
        testDelegation()
    }

    /**
     * 函数定义
     */
    private fun testFun(a: Int, b: Int): Unit {
        Log.i(TAG, "testFun  a : $a  b : $b")
    }

    /**
     * 可变参数
     */
    private fun vars(vararg v: Int) {
        Log.i(TAG, "vars 可变参数: ")
        for (vt in v) {
            Log.i(TAG, "v: $vt")
        }
    }

    /**
     * lambda(匿名函数)
     */
    private fun lambdaTest() {
        Log.i(TAG, "lambda(匿名函数): ")
        val sumLambda: (Int, Int) -> Int = {
            // 参数类型 Int 可以省略
                x: Int, y: Int ->
            // 大括号不是一个“代码块”
            // 不加run , {} 内的代码不会执行
            run {
                Log.i(TAG, "lambdaTest: ttttt")
            }
            // 箭头后面的语句依次执行
            val mul = x * y
            Log.i(TAG, "lambdaTest:  $x * $y = $mul ")
            Log.i(TAG, "lambdaTest:  $x - $y = ${x - y} ")
            // 最后一个表达式是返回值
            x + y
        };


        val a: Int = 4
        val b: Int = 6

        // val 不可变变量（相当于java final）
        // var 可变变量
        // 尝试给b赋值： Val cannot be reassigned
        // b = 56
        val sum: Int = sumLambda(a, b)
        Log.i(TAG, "lambdaTest: $a + $b = $sum")
    }

    private fun nullCheck() {
        //类型后面加?表示可为空
        var age: String? = null

        var ages: Int = 0
        try {
            //抛出空指针异常
            ages = age!!.toInt()
        } catch (e: Exception) {
            Log.e(TAG, "nullCheck: ${e.message} \n${e.stackTraceToString()}")
            e.printStackTrace()
        }

        //不做处理返回 null
        val ages1 = age?.toInt()
        //age为空返回-1
        val ages2 = age?.toInt() ?: -1

        Log.i(TAG, "nullCheck: age : $age    ages : $ages   ages1 : $ages1    ages2 : $ages2")
    }

    private fun rangeTest() {
        print("循环输出：")
        for (i in 1..4) print(i) // 输出“1234”
        println("\n----------------")
        print("设置步长：")
        for (i in 1..4 step 2) print(i) // 输出“13”
        println("\n----------------")
        print("使用 downTo：")
        for (i in 4 downTo 1 step 2) print(i) // 输出“42”
        println("\n----------------")
        print("使用 until：")
        // 使用 until 函数排除结束元素
        for (i in 1 until 4) {   // i in [1, 4) 排除了 4
            print(i)
        }
        println("\n----------------")
    }

    /**
     *  js 中也有 when
     *  感觉 kotlin 语法 有点像javaScript ?
     *  when 类似 switch
     */
    private fun testWhen(x : Int){
        Log.i(TAG, "testWhen: x = $x")
        when (x) {
            1 -> Log.i(TAG, "x == 1")
            2 -> Log.i(TAG, "x == 2")
            else -> {
                // 注意这个块
                Log.i(TAG, "x 不是 1 ，也不是 2")
            }
        }


        val items = setOf("apple", "banana", "kiwi")
        when {
            "orange" in items -> Log.i(TAG, "orange in items")
            "apple" in items -> Log.i(TAG, "apple in items")
        }
    }


    /**
     * 类
     */
    private fun testClass(){
        /**用户基类**/
        open class Person(name:String){
            /**次级构造函数**/
            constructor(name:String,age:Int):this(name){
                //初始化
                Log.i(TAG, "-------基类次级构造函数---------  name = $name    age = $age")
            }
        }

        /**子类继承 Person 类**/
        class Student:Person{

            /**次级构造函数**/
            constructor(name:String,age:Int,no:String,score:Int):super(name,age){
                Log.i(TAG, "-------继承类次级构造函数---------")
                Log.i(TAG, "学生名： $name")
                Log.i(TAG, "年龄： $age")
                Log.i(TAG, "学生号： $no")
                Log.i(TAG, "成绩： $score")
            }
        }

        val s1:Student = Student("John", 21, "2010996", 80)
        val s2:Student = Student("Joe", 22, "2010998", 90)
    }



    interface Base {
        fun print()
    }

    /**
     * 委托
     */
    private fun testDelegation(){
        class BaseImpl(val x: Int) : Base {
            override fun print() {
                Log.i(TAG, "print: x = $x")}
        }

        class Derived(b: Base) : Base by b

        class Derived2(b:Base):Base by b{
            override fun print() {
                Log.i(TAG, "override print")
            }
        }

        val b : BaseImpl = BaseImpl(10)
        Derived(b).print()
        Derived2(b).print()
    }

}