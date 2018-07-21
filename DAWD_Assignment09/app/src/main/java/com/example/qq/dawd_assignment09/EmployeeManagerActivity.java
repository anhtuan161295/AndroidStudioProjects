package com.example.qq.dawd_assignment09;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class EmployeeManagerActivity extends AppCompatActivity {
    EditText etId, etName, etDateofbirth, etEmail;
    ArrayList<Employee> arrEmployee = new ArrayList<>();
    ArrayAdapter adapter;
    String informations = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_manager);

        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        etDateofbirth = (EditText) findViewById(R.id.etDateofbirth);
        etEmail = (EditText) findViewById(R.id.etEmail);

    }

    public void add(View view) {
        String id = etId.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String dateofbirth = etDateofbirth.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (id.isEmpty()) {
            Toast.makeText(this, "Id is required", Toast.LENGTH_SHORT).show();
            etId.selectAll();
            etId.requestFocus();
            return;
        } else if (name.isEmpty()) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            etName.selectAll();
            etName.requestFocus();
            return;
        } else if (dateofbirth.isEmpty()) {
            Toast.makeText(this, "Date of birth is required", Toast.LENGTH_SHORT).show();
            etDateofbirth.selectAll();
            etDateofbirth.requestFocus();
            return;
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            etEmail.selectAll();
            etEmail.requestFocus();
            return;
        } else {
            Employee emp = new Employee(Integer.parseInt(id), name, dateofbirth, email);
            MyDBHandler handler = new MyDBHandler(this, null, null, 1);
            handler.add(emp);
            Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
            handler.close();
        }


    }

    public void display(View view) {
        Intent intent = new Intent(EmployeeManagerActivity.this, DisplayActivity.class);
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        arrEmployee = (ArrayList<Employee>) handler.display();
        Bundle bundle = new Bundle();
        bundle.putSerializable("arrEmployee", arrEmployee);
        intent.putExtras(bundle);
        startActivity(intent);
        handler.close();
    }

    public void find(View view) {
        Intent intent = new Intent(EmployeeManagerActivity.this, FindActivity.class);
        startActivity(intent);
    }

    public void updateInfomation() {
        informations = "";
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        arrEmployee = (ArrayList<Employee>) handler.display();
        for (Employee e : arrEmployee) {
            informations += "Id: " + e.getId() + "\n"
                    + "Tên: " + e.getName() + "\n"
                    + "Ngày sinh: " + e.getDateofbirth() + "\n"
                    + "Email: " + e.getEmail() + "\n"
                    + "\n";
        }
        handler.close();
    }

    public void write(View view) {
        updateInfomation();
        try {
            FileOutputStream out = openFileOutput("employee.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(informations);
            writer.close();
            Toast.makeText(this, "Lưu file thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(View view) {
        try {
            FileInputStream in = openFileInput("employee.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String data = "";
            StringBuilder builder = new StringBuilder();
            while ((data = reader.readLine()) != null) {
                builder.append(data);
                builder.append("\n");
            }
            in.close();

            // Xử lý hiển thị dữ liệu trong 1 Activity có tên là DisplayActivity
            // Tạo Intent để mở DisplayActivity
            Intent myIntent = new Intent(EmployeeManagerActivity.this, ReadFileActivity.class);
            // Khai báo Bundle
            Bundle bundle = new Bundle();
            // Đưa dữ liệu kiểu string vào bundle
            bundle.putString("informations", builder.toString());
            // Đưa bundle vào Intent
            myIntent.putExtra("MyPackage", bundle);
            // Mở Activity DisplayActivity
            startActivity(myIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exit(View view) {
        finish();
    }
}
