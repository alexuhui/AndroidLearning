package com.example.practice.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.practice.R;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView(this);
    }

    private void initView(Context context){
        Button btn = findViewById(R.id.btn_pre_page);
        btn.setText("back previous page");
        btn.setOnClickListener(view -> {
            finish();
        });
    }
}