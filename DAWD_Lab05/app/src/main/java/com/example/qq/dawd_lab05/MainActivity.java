package com.example.qq.dawd_lab05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etCode, etTitle, etType, etPrice, etAuthor;
    private Button btnAddBook;
    private ListView lvBook;
    // Khai báo Data Source lưu trữ danh sách book
    ArrayList<Book> arrBook = new ArrayList<>();
    // Khai báo ArrayAdapter cho ListView
    ArrayAdapter<Book> adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFormWidgets();
        addEventFormWidget();
    }

    // Viết method truy cập đến các control
    public void getFormWidgets(){
        etCode = (EditText) findViewById(R.id.etCode);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etType = (EditText) findViewById(R.id.etType);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etAuthor = (EditText) findViewById(R.id.etAuthor);
        btnAddBook = (Button) findViewById(R.id.btnAddBook);
        lvBook = (ListView) findViewById(R.id.lvBook);
        // Gắn DataSource vào ArrayAdapter
        adapter = new ArrayAdapter<Book>(this, R.layout.support_simple_spinner_dropdown_item, arrBook);
        // gắn adapter vào listview
        lvBook.setAdapter(adapter);
    }


    public void setNull() {
        etCode.setText("");
        etTitle.setText("");
        etType.setText("");
        etPrice.setText("");
        etAuthor.setText("");
        etCode.requestFocus();
    }

    public void addBook(Book b) {
        b.setCode(etCode.getText().toString());
        b.setTitle(etTitle.getText().toString());
        b.setType(etType.getText().toString());
        b.setPrice(Double.parseDouble(etPrice.getText().toString()));
        b.setAuthor(etAuthor.getText().toString());
        arrBook.add(b);
        Toast.makeText(this, "Đã thêm sách thành công", Toast.LENGTH_SHORT).show();
        // Cập nhật lại adapter cho listview
        adapter.notifyDataSetChanged();
        setNull();
    }

    private class MyListViewEvents implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Khi click chuột 1 item trong list view sẽ hiển thị thêm thông tin về giá và tác giả
            Toast.makeText(MainActivity.this, "Giá "+ arrBook.get(position).getPrice() +
                    " - Tác giả: " + arrBook.get(position).getAuthor() , Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // Xóa vị trí position
            arrBook.remove(position);
            adapter.notifyDataSetChanged();
            return false;
        }
    }
    // Viết method add sự kiện cho button và listview
    public void addEventFormWidget(){
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book b = new Book();
                addBook(b);
            }
        });
        lvBook.setOnItemClickListener(new MyListViewEvents());
        lvBook.setOnItemLongClickListener(new MyListViewEvents());
    }



}
