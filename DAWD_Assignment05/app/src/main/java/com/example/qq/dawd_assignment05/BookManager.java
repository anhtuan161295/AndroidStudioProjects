package com.example.qq.dawd_assignment05;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QQ on 3/29/2017.
 */

public class BookManager {
    public static ArrayList<Book> list = new ArrayList<>();
    public static ArrayAdapter<Book> adapter = null;

    public static void addBook(Book b) {
        list.add(b);
        adapter.notifyDataSetChanged();
    }

    public static void removeBook(int index) {
        list.remove(index);
        adapter.notifyDataSetChanged();
    }

    public static ArrayAdapter<Book> getAdapter() {
        return adapter;
    }

    public static void setAdapter(ArrayAdapter<Book> adapter) {
        BookManager.adapter = adapter;
    }

    public static ArrayList<Book> getList() {
        return list;
    }

    public static void setList(ArrayList<Book> list) {
        BookManager.list = list;
    }
}
