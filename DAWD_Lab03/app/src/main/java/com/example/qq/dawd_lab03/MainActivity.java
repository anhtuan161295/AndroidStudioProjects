package com.example.qq.dawd_lab03;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnCong, btnTru, btnNhan, btnChia;
    private EditText soA, soB;
    private TextView ketqua;

    View.OnClickListener myClick = new View.OnClickListener() {
        double a, b;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCong:
                    a = Double.parseDouble(soA.getText().toString());
                    b = Double.parseDouble(soB.getText().toString());
                    ketqua.setText(a + " + " + b + " = " + (a + b));
                    Toast.makeText(MainActivity.this, "Bạn vừa chọn phép cộng", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnTru:
                    a = Double.parseDouble(soA.getText().toString());
                    b = Double.parseDouble(soB.getText().toString());
                    ketqua.setText(a + " - " + b + " = " + (a - b));
                    Toast.makeText(MainActivity.this, "Bạn vừa chọn phép trừ", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNhan:
                    a = Double.parseDouble(soA.getText().toString());
                    b = Double.parseDouble(soB.getText().toString());
                    ketqua.setText(a + " * " + b + " = " + (a * b));
                    Toast.makeText(MainActivity.this, "Bạn vừa chọn phép nhân", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnChia:
                    a = Double.parseDouble(soA.getText().toString());
                    b = Double.parseDouble(soB.getText().toString());
                    ketqua.setText(a + " / " + b + " = " + (a / b));
                    Toast.makeText(MainActivity.this, "Bạn vừa chọn phép chia", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCong = (Button) findViewById(R.id.btnCong);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
        soA = (EditText) findViewById(R.id.txtSoA);
        soB = (EditText) findViewById(R.id.txtSoB);
        ketqua = (TextView) findViewById(R.id.txtKetQua);

        btnCong.setOnClickListener(myClick);
        btnTru.setOnClickListener(myClick);
        btnNhan.setOnClickListener(myClick);
        btnChia.setOnClickListener(myClick);

    }

    public void exitApp(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Xác nhận thoát");
        b.setMessage("Bạn có chắc thoát khỏi ứng dụng ?");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.create().show();
    }
}
