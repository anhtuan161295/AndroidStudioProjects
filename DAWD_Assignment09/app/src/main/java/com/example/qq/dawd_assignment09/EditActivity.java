package com.example.qq.dawd_assignment09;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    EditText etId, etName, etDateofbirth, etEmail;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        etDateofbirth = (EditText) findViewById(R.id.etDateofbirth);
        etEmail = (EditText) findViewById(R.id.etEmail);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        Employee emp = handler.find(id);
//        Toast.makeText(this, emp.toString(), Toast.LENGTH_SHORT).show();

        etId.setText(String.valueOf(emp.getId()));
        etName.setText(emp.getName());
        etDateofbirth.setText(emp.getDateofbirth());
        etEmail.setText(emp.getEmail());

        handler.close();
    }

    public void save(View view) {
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        String id = etId.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String dateofbirth = etDateofbirth.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        Employee employee= new Employee(Integer.parseInt(id), name, dateofbirth, email);
        handler.edit(employee);
        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        loadData(id);
        handler.close();
    }

    public void loadData(String code) {
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        Employee emp = handler.find(code);
        etId.setText(String.valueOf(emp.getId()));
        etName.setText(emp.getName());
        etDateofbirth.setText(emp.getDateofbirth());
        etEmail.setText(emp.getEmail());
        handler.close();
    }

    public void back(View view) {
        Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
//        arrEmployee = (ArrayList<Employee>) handler.display();
        Bundle bundle = new Bundle();
        bundle.putSerializable("arrEmployee", (Serializable) handler.display());
        intent.putExtras(bundle);
        handler.close();
        startActivity(intent);
        finish();
    }

}
