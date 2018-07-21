package com.example.qq.dawd_exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShowBookActivity extends AppCompatActivity {

    ListView lvBook;
    ArrayList<String> arrBook = new ArrayList<>();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);
        setTitle("ShowBookActivity");

        lvBook = (ListView) findViewById(R.id.lvBook);
        adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrBook);

        try {
            FileInputStream in = openFileInput("book.dat");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String data = "";
            StringBuilder builder = new StringBuilder();
            while ((data = reader.readLine()) != null) {
                builder.append(data);
                builder.append("\n");
                arrBook.add(data);
                adapter.notifyDataSetChanged();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        lvBook.setAdapter(adapter);

    }

    public void back(View view) {
        finish();
    }
}
