package com.example.qq.bookapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QQ on 4/5/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

        // Khai báo tên csdl và version
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "bookDB.db";
        // Khai báo tên table và các thuộc tính trong table
        public static final String TABLE_BOOK = "book";
        public static final String COLUMN_ID = "book_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_AUTHOR = "author";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo table
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TITLE + " TEXT, " + COLUMN_PRICE + " REAL, " +
                COLUMN_AUTHOR + " TEXT" + ")";
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_BOOK);
        onCreate(db);
    }

    public List<Book> display() {
        List<Book> list = new ArrayList<>();
        String query = "Select * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setCode(cursor.getInt(0));
                book.setTitle(cursor.getString(1));
                book.setPrice(cursor.getDouble(2));
                book.setAuthor(cursor.getString(3));
                list.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    // Thêm mới book
    public void add(Book book) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, book.getTitle());
        values.put(COLUMN_PRICE, book.getPrice());
        values.put(COLUMN_AUTHOR, book.getAuthor());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_BOOK, null, values);
        db.close();
    }

    public void delete(String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, COLUMN_ID + "=?", new String[]{code});
        db.close();
    }

    public boolean delete2(String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_BOOK, COLUMN_ID + "=" + code, null) > 0;
    }

    public void edit(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, book.getTitle());
        values.put(COLUMN_PRICE, book.getPrice());
        values.put(COLUMN_AUTHOR, book.getAuthor());
        db.update(TABLE_BOOK, values, COLUMN_ID + "=?", new String[]{String.valueOf(book.getCode())});
        db.close();
    }

    public boolean edit2(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, book.getTitle());
        values.put(COLUMN_PRICE, book.getPrice());
        values.put(COLUMN_AUTHOR, book.getAuthor());
        return db.update(TABLE_BOOK, values, COLUMN_ID + "=" + book.getCode(), null) > 0;
    }

    public Book find(String code) {
        String query = "Select * FROM " + TABLE_BOOK + " WHERE " + COLUMN_ID + " =?";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        Cursor cursor = db.rawQuery(query, new String[]{code});
        Book book = new Book();
        if (cursor.moveToFirst()) {
            do {
                book.setCode(cursor.getInt(0));
                book.setTitle(cursor.getString(1));
                book.setPrice(cursor.getDouble(2));
                book.setAuthor(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
//        List<Book> list = display();
//        Book book = null;
//        for (Book b : list) {
//            if (b.getCode() == Integer.parseInt(code)) {
//                book = b;
//            }
//        }

        return book;
    }
}
