package com.example.qq.bookapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText etTitle, etPrice, etAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etAuthor = (EditText) findViewById(R.id.etAuthor);

    }

    public void addBook(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String title = etTitle.getText().toString().trim();
        Double price = Double.parseDouble(etPrice.getText().toString().trim());
        String author = etAuthor.getText().toString().trim();
        Book book = new Book(0, title, price, author);
        dbHandler.add(book);
        Toast.makeText(this, "Đã thêm sách thành công", Toast.LENGTH_SHORT).show();
        setNull();
    }

    public void setNull() {
        etTitle.setText("");
        etPrice.setText("");
        etAuthor.setText("");
        etTitle.requestFocus();
    }

    public void back(View view) {
        finish();
    }
}
