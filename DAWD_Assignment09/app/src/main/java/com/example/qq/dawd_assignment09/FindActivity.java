package com.example.qq.dawd_assignment09;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FindActivity extends AppCompatActivity {

    EditText etFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        etFind = (EditText) findViewById(R.id.etFind);
    }

    public void find(View view) {
        String id = etFind.getText().toString().trim();
        if (id.isEmpty()) {
            Toast.makeText(this, "Chưa nhập id", Toast.LENGTH_SHORT).show();
            etFind.requestFocus();
        } else {
            MyDBHandler handler = new MyDBHandler(this, null, null, 1);
            Employee e = handler.find(id);
            if (e != null) {
                String info = "Id: " + e.getId() + "\n"
                        + "Tên: " + e.getName() + "\n"
                        + "Ngày sinh: " + e.getDateofbirth() + "\n"
                        + "Email: " + e.getEmail() + "\n"
                        + "";
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(this, "Không tìm thấy", Toast.LENGTH_SHORT).show();
            }
            handler.close();
        }

    }

    public void back(View view) {
        finish();
    }
}
