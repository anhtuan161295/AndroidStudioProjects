package com.example.qq.bookapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText etTitle, etPrice, etAuthor;
    String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etAuthor = (EditText) findViewById(R.id.etAuthor);

        Intent intent = getIntent();
        code = intent.getExtras().getString("code");
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        Book book = handler.find(code);
//        Toast.makeText(this, book.getTitle(), Toast.LENGTH_SHORT).show();
//        etTitle.setText(book.getTitle());
//        etPrice.setText(String.valueOf(book.getPrice()));
//        etAuthor.setText(book.getAuthor());
        loadData(code);
    }

    public void saveBook(View view) {
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        String title = etTitle.getText().toString().trim();
        Double price = Double.parseDouble(etPrice.getText().toString().trim());
        String author = etAuthor.getText().toString().trim();
        Book book = new Book(Integer.parseInt(code), title, price, author);
        handler.edit(book);
        Toast.makeText(this, "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
        loadData(code);

    }

    public void loadData(String code) {
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        Book book = handler.find(code);
        etTitle.setText(book.getTitle());
        etPrice.setText(String.valueOf(book.getPrice()));
        etAuthor.setText(book.getAuthor());
    }

    public void back(View view) {
        Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
        startActivity(intent);
        finish();
    }
}
