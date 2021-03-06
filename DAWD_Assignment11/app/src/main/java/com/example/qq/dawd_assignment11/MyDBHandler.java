package com.example.qq.dawd_assignment11;

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
    private static final String DATABASE_NAME = "contactDB.db";
    // Khai báo tên table và các thuộc tính trong table
    private static final String TABLE_BOOK = "contact";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_EMAIL = "email";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo table
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK +
                "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_EMAIL + " TEXT "
                + ")";
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_BOOK);
        onCreate(db);
    }

    public List<Contact> display() {
        List<Contact> list = new ArrayList<>();
        String query = "Select * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setAddress(cursor.getString(3));
                contact.setEmail(cursor.getString(4));
                list.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    // Thêm mới book
    public void add(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_ADDRESS, contact.getAddress());
        values.put(COLUMN_EMAIL, contact.getEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_BOOK, null, values);
        db.close();
    }

    public boolean add2(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_ADDRESS, contact.getAddress());
        values.put(COLUMN_EMAIL, contact.getEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_BOOK, null, values) > 0;
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

    public void edit(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_ADDRESS, contact.getAddress());
        values.put(COLUMN_EMAIL, contact.getEmail());
        db.update(TABLE_BOOK, values, COLUMN_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public boolean edit2(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_ADDRESS, contact.getAddress());
        values.put(COLUMN_EMAIL, contact.getEmail());
        return db.update(TABLE_BOOK, values, COLUMN_ID + "=" + contact.getId(), null) > 0;
    }

    public Contact find(String id) {
        String query = "Select * FROM " + TABLE_BOOK + " WHERE " + COLUMN_ID + " =?";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        Cursor cursor = db.rawQuery(query, new String[]{id});
        Contact contact = new Contact();
        if (cursor.moveToFirst()) {
            do {
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setAddress(cursor.getString(3));
                contact.setEmail(cursor.getString(4));
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

        return contact;
    }
}
