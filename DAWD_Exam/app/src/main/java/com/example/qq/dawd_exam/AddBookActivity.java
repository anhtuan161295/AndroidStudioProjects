package com.example.qq.dawd_exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AddBookActivity extends AppCompatActivity {

    EditText etId, etTitle, etPrice;
    TextView tvError;
    String informations = "";
    ArrayList<Book> arrBook = new ArrayList<>();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        setTitle("AddBookActivity");

        etId = (EditText) findViewById(R.id.etId);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etPrice = (EditText) findViewById(R.id.etPrice);
        tvError = (TextView) findViewById(R.id.tvError);

    }

    public void addBook(View view) {
        String id = etId.getText().toString().trim();
        String title = etTitle.getText().toString().trim();
        String price = etPrice.getText().toString().trim();
        int price2 = 0;
        if (!price.isEmpty()) {
            price2 = Integer.parseInt(price);
        }
        if (id.isEmpty()) {
            tvError.setText("Id is required");
            etId.requestFocus();
            return;
        } else if (title.isEmpty()) {
            tvError.setText("Title is required");
            etTitle.requestFocus();
            return;
        } else if (price.isEmpty()) {
            tvError.setText("Price is required");
            etPrice.requestFocus();
            return;
        } else if (title.length() < 3 || title.length() > 20) {
            tvError.setText("Title length must be from 3 to 20 characters");
            etTitle.requestFocus();
            return;
        } else if (price2 <= 0) {
            tvError.setText("Price must be greater than 0");
            etPrice.requestFocus();
            return;
        } else {
            Book book = new Book(Integer.parseInt(id), title, price2);
            arrBook.add(book);
            informations = book.toString() + "\n";
        }
        try {
            FileOutputStream out = openFileOutput("book.dat", MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(informations);
            writer.close();
            Toast.makeText(this, "Add book success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        finish();
    }

}
