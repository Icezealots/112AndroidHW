package com.example.ordermenu0516;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        // 获取 Intent 中传递的数据
        String orderDetails = getIntent().getStringExtra("order_details");

        // 将数据显示在 TextView 中
        TextView textView = findViewById(R.id.textView_order_details);
        textView.setText(orderDetails);
    }
}
