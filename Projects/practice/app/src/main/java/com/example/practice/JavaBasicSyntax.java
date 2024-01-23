package com.example.practice;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.SimpleFormatter;

public class JavaBasicSyntax {

    private static String TAG = "JavaBasicSyntax";

    public void doTest() {
        Log.i(TAG, "doTest");

        System.out.println("JavaBasicSyntax Hello Java");

        testFor();
        testString();
        testDate();
        testGenericMethod();
    }


    /**
     * 加强型循环
     * 类似foreach 的用法
     */
    private void testFor() {
        String[] names = {"James", "Larry", "Tom", "Lacy"};
        System.out.println("JavaBasicSyntax testFor");
        for (String name : names) {
            System.out.println("JavaBasicSyntax : name = " + name);
        }
    }

    private void testString(){
        String sf = String.format("this is a %s app", "practice");
        Log.i(TAG, String.format("testFormat: sf = %s", sf));

        Log.i(TAG, "equal \"abc\" == \"abc\"? : "  + ("abc" == "abc"));
        Log.i(TAG, "equal \"abc\" .compareTo(\"abc\")? : "  + ("abc" .compareTo("abc")));
        String sVal = "string val";
        Log.i(TAG, "equal (sVal == \"string val\") : " + (sVal == "string val"));

        Vector<String> vec = new Vector<String>();
        vec.add("string val");
        vec.add("string 666");
        String v0 = vec.get(0);
        Log.i(TAG, "equal: v0 == \"string val\" ? :" + (v0 == "string val"));
    }

    private void testDate(){
        Date dNow = new Date();
        /**
         * E 星期几
         * a AM/PM  12小时制
         * z 显示时区
          */
        SimpleDateFormat sdf = new SimpleDateFormat("E yyyy/MM/dd 'at' hh:mm:ss a z");
        Log.i(TAG, "testDate: " + sdf.format(dNow));
    }

    private void testGenericMethod(){
        sum(10, 20);
        sum(10.2, 2.3);
    }

    private  <T extends Number> T sum(T a, T b){

        Log.i(TAG, "sum: a = " + a + " b = " + b );

        double result = a.doubleValue() + b.doubleValue();

        return a;//(T)result;
    }
}
