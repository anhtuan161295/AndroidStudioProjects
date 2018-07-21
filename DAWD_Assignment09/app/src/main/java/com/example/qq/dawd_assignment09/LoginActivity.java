package com.example.qq.dawd_assignment09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void login(View view) {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.equals("fpt")){
            if (password.equals("123")){
                //success
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, EmployeeManagerActivity.class);
                startActivity(intent);
                finish();

            }else{
                // sai pass
                Toast.makeText(this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                etPassword.requestFocus();
            }
        }else{
            // user invalid
            Toast.makeText(this, "Nhân viên ko tồn tại", Toast.LENGTH_SHORT).show();
            etUsername.requestFocus();
        }

    }
}
