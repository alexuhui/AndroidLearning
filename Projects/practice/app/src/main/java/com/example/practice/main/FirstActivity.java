package com.example.practice.main;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.practice.R;

public class FirstActivity extends Activity {
    private final static  String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initView(this);
    }

    private void initView(Context context){
        TextView tv = findViewById(R.id.txt_001);
        tv.setText("back previous page");
        tv.setOnClickListener(view -> {
            finish();
        });
    }

    public void onNextPageClick()
    {

    }

    public void onNextPageClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }
}