package com.example.qq.dawd_lab07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView txtResult;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        txtResult=(TextView) findViewById(R.id.textResult);
        btnBack=(Button) findViewById(R.id.btnBack);
        // Lấy intent gọi Activity này
        Intent callerIntent = getIntent();
        // có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageBundle = callerIntent.getBundleExtra("MyPackage");
        txtResult.setText(packageBundle.getString("informations"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
