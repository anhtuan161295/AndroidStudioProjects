package com.example.qq.dawd_session02;

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

    private Button btnTong;
    private EditText soA, soB;
    private TextView result;

    private Button btnToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soA = (EditText) findViewById(R.id.txtSoA);
        soB = (EditText) findViewById(R.id.txtSoB);
        result = (TextView) findViewById(R.id.txtResult);
        btnTong = (Button) findViewById(R.id.btnCalculate);
        btnToast = (Button) findViewById(R.id.btnToast);

    }


    public void tinhTong(View view) {
        double a = Double.parseDouble(soA.getText().toString());
        double b = Double.parseDouble(soB.getText().toString());
        result.setText("Result: " + (a + b));

        // Hiển thị Toast message
        Toast toast = Toast.makeText(MainActivity.this, "Chương trình vừa tính cộng", Toast.LENGTH_LONG);
        toast.show();
    }

    public void exitApp(View v) {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Xác nhận thoát");
        b.setMessage("Bạn có muốn thoát khỏi ứng dụng không ?");
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

        b.show();

    }
}
