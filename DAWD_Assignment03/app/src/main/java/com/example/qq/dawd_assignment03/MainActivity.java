package com.example.qq.dawd_assignment03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etTen, etCanNang, etChieuCao;
    private Button btnTinhBMI;
    private TextView tvKetQua, tvChanDoan;

    public void calculateBMI(View view) {
        double chieucao = Double.parseDouble(etChieuCao.getText().toString());
        double cannang = Double.parseDouble(etCanNang.getText().toString());
        double BMI = cannang / Math.pow(chieucao, 2);
        String ketqua = "BMI = ";
        String chandoan = "Chuẩn đoán: \n";
        if (BMI < 18) {
            chandoan += "Bạn bị gầy. \nNên bồi dưỡng vào.";
        } else if (BMI <= 24.9) {
            chandoan += "Bạn bình thường. \nCứ thế mà phát huy.";
        } else if (BMI <= 29.9) {
            chandoan += "Bạn béo phì độ 1. \nHơi mập đấy nhé.";
        } else if (BMI <= 34.9) {
            chandoan += "Bạn béo phì độ 2. \nNên ăn kiêng.";
        } else {
            chandoan += "Bạn béo phì độ 3. \nMập quá rồi, phải ăn kiêng.";
        }

        DecimalFormat df = new DecimalFormat("#.0"); // Định dạng 1 số lẻ
        ketqua += df.format(BMI);
        tvKetQua.setText(ketqua);
        tvChanDoan.setText(chandoan);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTen = (EditText) findViewById(R.id.txtTen);
        etCanNang = (EditText) findViewById(R.id.txtCanNang);
        etChieuCao = (EditText) findViewById(R.id.txtChieuCao);
        btnTinhBMI = (Button) findViewById(R.id.btnTinhBMI);
        tvKetQua = (TextView) findViewById(R.id.txtKetQua);
        tvChanDoan = (TextView) findViewById(R.id.txtChanDoan);

    }


}
