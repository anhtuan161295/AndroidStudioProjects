package com.example.qq.dawd_assignment09;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ReadFileActivity extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);

        tvResult=(TextView) findViewById(R.id.tvResult);
        // Lấy intent gọi Activity này
        Intent callerIntent = getIntent();
        // có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageBundle = callerIntent.getBundleExtra("MyPackage");
        tvResult.setText(packageBundle.getString("informations"));

    }

    public void back(View view) {
        finish();
    }
}
